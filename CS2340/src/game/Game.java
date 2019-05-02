package game;

import javafx.stage.Stage;

import graphics.configuration.View;
import configuration.Manager;
import configuration.IConfiguration;
import configuration.option.IOption;
import configuration.option.Difficulty;
import configuration.option.MapType;
import configuration.option.Race;
import configuration.option.Name;
import configuration.option.Color;
import graphics.IWindow;
import graphics.Window;
import graphics.IView;
import map.Map;
import map.MapTile;
import map.LandGrant;
import player.Player;
import player.Type;
import player.Mule;
import timer.ITimer;
import timer.Timer;
import timer.Turn;
import input.Key;
import town.Building;
import town.MuleBuilding;
import town.EnergyBuilding;
import town.FoodBuilding;
import town.MineBuilding;
import town.LandBuilding;
import town.Pub;
import input.IInput;

import java.util.List;
import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import java.io.PrintWriter;
import java.io.File;

public class Game implements IGame {

    private View configView;
    private graphics.map.View mapView;
    private graphics.town.View townView;
    private IWindow window;
    private Turn turn;
    private Player[] players;
    private Map map;
    private Pane playerLayer;
    private HBox labelLayer;
    private boolean inTown;
    private Building[] buildings;
    private LandBuilding landBuilding;

    @Override
    public void provideStage(Stage stage) {
        graphics.Manager.setMainStage(stage);
        this.window = graphics.Manager.getMainWindow();
    }

    @Override
    public Turn getTurn() {
        return turn;
    }

    @Override
    public void start() {
        players = new Player[4];

        IConfiguration conf = configuration.Manager.getConfiguration();

        for (int i = 0; i < 4; i++) {
            players[i] = new Player(
                (String) conf.get("player."  +  i  +  ".name"),
                player.Race.toRace((Integer) conf.get("player."  +  i  +  ".race")),
                player.Color.toColor((String) conf.get("player."  +  i  +  ".color")),
                (Integer) conf.get("player."  +  i  +  ".money"),
                (Integer) conf.get("player."  +  i  +  ".food"),
                (Integer) conf.get("player."  +  i  +  ".energy"),
                (Integer) conf.get("player."  +  i  +  ".ore"));
        }
        map = new Map(0);
        for (int i = 0; i < 5; i += 1) {
            for (int j = 0; j < 9; j += 1) {
                String owner = (String) conf.get("tile." + i + "." + j + ".owner");
                if (owner != null) {
                    Player p = players[0];
                    for (int k = 0; k < 4; k += 1) {
                        if (players[k].getName().equals(owner)) {
                            p = players[k];
                            break;
                        }
                    }
                    map.getTile(i, j).setOwner(p);
                    String muleType = (String) conf.get("tile." + i + "." + j + ".mule");
                    if (muleType != null) {
                        map.getTile(i, j).setMule(new Mule(muleType));
                    }
                }
            }
        }
        mapView = new graphics.map.View(map);
        townView = new graphics.town.View();
        if ((Integer) configuration.Manager.getConfiguration().get("game.round") != null) {
        } else {
            LandGrant landSelection = new LandGrant(map, players);
        }
        playerLayer = new Pane();
        labelLayer = new HBox();
        labelLayer.setSpacing(40);
        for (int i = 0; i < players.length; i += 1) {
            playerLayer.getChildren().add(players[i].getSprite());
            labelLayer.getChildren().add(players[i].getLabel());
        }
        createBuildings();
        window.render(mapView);
        mapView.getRoot().getChildren().add(playerLayer);
        mapView.getRoot().getChildren().add(labelLayer);
        inTown = false;
        window.display();
        if ((Integer) configuration.Manager.getConfiguration().get("game.round") != null) {
            this.beginTurns();
        }
    }

    /*
     * Generate building objects in an array.
     */
    private void createBuildings() {
        buildings = new Building[6];
        buildings[0] = new EnergyBuilding();
        buildings[1] = new FoodBuilding();
        buildings[2] = new MineBuilding();
        buildings[3] = new MuleBuilding();
        buildings[4] = new Pub();
        landBuilding = new LandBuilding();
        buildings[5] = landBuilding;
    }

    @Override
    public void beginTurns() {
        registerMapHandlers();
        for (int i = 0; i < players.length; i += 1) {
            players[i].calculateDimensions(mapView.getRoot());
        }
        Integer round = (Integer) configuration.Manager.getConfiguration().get("game.round");
        if (round == null) {
            round = 1;
        }
        turn = new Turn(players, round);
        turn.beginTurn(0);
    }

    @Override
    public void enterTown() {
        mapView.getRoot().getChildren().remove(playerLayer);
        mapView.getRoot().getChildren().remove(labelLayer);
        window.render(townView);
        inTown = true;
        townView.getRoot().getChildren().add(playerLayer);
        townView.getRoot().getChildren().add(labelLayer);
        IInput i = input.Manager.getInput();
        i.pushFrame();
        registerMovementHandlersWithExitHandler();
    }

    @Override
    public boolean inTown() {
        return inTown;
    }

    @Override
    public void leaveTown() {
        townView.getRoot().getChildren().remove(playerLayer);
        townView.getRoot().getChildren().remove(labelLayer);
        window.render(mapView);
        inTown = false;
        mapView.getRoot().getChildren().add(playerLayer);
        mapView.getRoot().getChildren().add(labelLayer);
        turn.getPlayer().resetPosition();
        input.Manager.getInput().popFrame();
    }

    /**
     * Registers the correct handlers for the map screen to the global Input instance.
     */
    private void registerMapHandlers() {
        IInput i = input.Manager.getInput();
        i.clearFrame();
        i.registerKeyHandler(Key.PRIMARY, () -> {
            Player p = turn.getPlayer();
            MapTile tile = map.getTile((int) p.getY(), (int) p.getX());
            if (tile.isTown()) {
                enterTown();
            } else {
                if (p.getMule() != null) {
                    map.placeMule(p, tile);
                }
            }
        });
        i.registerKeyHandler(Key.SECONDARY, () -> {
            System.out.print(turn.getPlayer().getName());
            System.out.print(" " + turn.getPlayer().getX());
            System.out.println(" " + turn.getPlayer().getY());
        });
        registerMovementHandlers();
    }

    /**
     * Registers the movement handlers for the town to the global Input instance.
     */
    private void registerMovementHandlersWithExitHandler() {
        IInput i = input.Manager.getInput();
        i.registerKeyHandler(Key.UP, () -> turn.getPlayer().moveVertical(true));
        i.registerKeyHandler(Key.DOWN, () -> turn.getPlayer().moveVertical(false));
        i.registerKeyHandler(Key.RIGHT, () -> {
            turn.getPlayer().moveHorizontal(true);
            Player p = turn.getPlayer();
            if (p.getX() >= 6.8) {
                leaveTown();
            }
        });
        i.registerKeyHandler(Key.PRIMARY, () -> {
            Player p = turn.getPlayer();
            int index = -1;
            if (p.getX() >= 4.14 && p.getX() <= 4.5 && p.getY() >= 0.39 && p.getY() <= 0.8) {
                index = 0; //Energy
            } else if (p.getX() >= 5.58 && p.getX() <= 6.1 && p.getY() >= 0.39 && p.getY() <= 0.8) {
                index = 1; //Food
            } else if (p.getX() >= 2.7 && p.getX() <= 3.24 && p.getY() >= 0.39 && p.getY() <= 0.8) {
                index = 2; //Ore
            } else if (p.getX() >= 5 && p.getX() <= 6.3 && p.getY() >= 2.09 && p.getY() <= 4.2) {
                index = 3; //Mule
            } else if (p.getX() >= 3.6 && p.getX() <= 4.5 && p.getY() >= 2.09 && p.getY() <= 3.2) {
                index = 4; //Pub
            } else if (p.getX() >= 2.5 && p.getX() <= 3.25 && p.getY() >= 2.2 && p.getY() <= 3.4) {
                index = 5; //Land
            }
            if (index > -1 && index != 5) {
                buildings[index].action(turn);
                p.updateLabel();
                p.getLabel().setText(p.getLabel().getText() + "------");
            } else if (index == 5) {
                landBuilding.setMap(map);
                buildings[index].action(turn);
                p.updateLabel();
                p.getLabel().setText(p.getLabel().getText() + "------");
            }
        });
        i.registerKeyHandler(Key.LEFT, () -> {
            turn.getPlayer().moveHorizontal(false);
            Player p = turn.getPlayer();
            if (p.getX() <= 2) {
                leaveTown();
            }
        });
    }

    /**
     * Registers standard movement controls.
     */
    private void registerMovementHandlers() {
        IInput i = input.Manager.getInput();
        i.registerKeyHandler(Key.UP, () -> turn.getPlayer().moveVertical(true));
        i.registerKeyHandler(Key.DOWN, () -> turn.getPlayer().moveVertical(false));
        i.registerKeyHandler(Key.RIGHT, () -> turn.getPlayer().moveHorizontal(true));
        i.registerKeyHandler(Key.LEFT, () -> turn.getPlayer().moveHorizontal(false));
    }

    /*
     * Set up configuration screen.
     */
    private void configure() {
        List<IOption> options = new ArrayList<>();
        IConfiguration conf = Manager.getConfiguration();
        options.add(new Difficulty(conf));
        options.add(new MapType(conf));
        options.add(new Name(conf));
        options.add(new Race(conf));
        options.add(new Color(conf));
        configView = new View(options);
        window.render(configView);
    }

    @Override
    public void saveGame() {
        IConfiguration conf = configuration.Manager.getConfiguration();
        for (int i = 0; i < 4; i++) {
            conf.set("player."  +  i  +  ".name", players[i].getName());
            conf.set("player."  +  i  +  ".race", players[i].getRace().getRace());
            conf.set("player."  +  i  +  ".color", players[i].getColor().toString());
            conf.set("player."  +  i  +  ".money", players[i].money);
            conf.set("player."  +  i  +  ".food", players[i].food);
            conf.set("player."  +  i  +  ".ore", players[i].ore);
            conf.set("player."  +  i  +  ".energy", players[i].energy);
        }
        conf.set("game.round", turn.getRound());
        for (int i = 0; i < 5; i += 1) {
            for (int j = 0; j < 9; j += 1) {
                Player p = map.getOwner(i, j);
                if (p != null) {
                    conf.set("tile." + i + "." + j + ".owner", p.getName());
                    MapTile t = map.getTile(i, j);
                    if (t.getMule() != null) {
                        conf.set("tile." + i + "." + j + ".mule", t.getMule().getType().getName());
                    }
                }
            }
        }

        PrintWriter saver = null;
        try {
            saver = new PrintWriter(new File("save.json"));
            saver.print(conf.getJSON().toString()  +  "\n");
            saver.close();
        } catch (Exception e) {
            System.out.println("Failed to save: "  +  e);
        } finally {
            try {saver.close();} catch (Exception e) {}
        }
    }
}
