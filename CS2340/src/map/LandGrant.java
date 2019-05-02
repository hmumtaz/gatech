package map;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import player.Player;
import player.Color;
import input.Manager;
import input.IInput;
import input.Key;

/**
 * @author Alan Jiang
 * @version 1.1
 */

public class LandGrant {

    int x, y;
    int round;
    int index;
    Map map;
    Player[] players;
    int purchases;
    Thread thread;
    boolean active;

    /**
     * The Land Grant Class is bound to a player.
     * @param m Map being played.
     * @param players players playing on map
     */
    public LandGrant (Map m, Player[] players) {
        x = 0;
        y = 0;
        map = m;
        round = 1;
        index = 0;
        purchases = 0;
        active = false;
        this.players = players;
        registerInitial();
    }

    /**
     * Sets the indicator label showing the current player.
     */
    private void showCurrentPlayer() {
        players[index].getLabel().setText(players[index].getLabel().getText() + "------");
    }

    /**
     * Registers the control to begin the land selection phase.
     */
    private void registerInitial() {
        Manager.getInput().registerKeyHandler(Key.PRIMARY, () -> beginSelection());
    }

    /**
     * Registers controls for the land selection phase: select and pass.
     */
    private void registerHandlers() {
        IInput input = Manager.getInput();
        input.clearFrame();
        input.registerKeyHandler(Key.PRIMARY, () -> selectLand());
        input.registerKeyHandler(Key.SECONDARY, () -> pass());
    }

    /**
     * Begins the land selection phase.
     */
    private void beginSelection() {
        showCurrentPlayer();
        active = true;
        registerHandlers();
        traverseMap();
    }

    /**
     * Selects the current highlighted land plot for the current player.
     */
    private void selectLand() {
        if (active) {
            if (round < 3) {
                players[index].updateLabel();
                thread.interrupt();
            } else {
                if (players[index].getMoney() < 300) {
                    System.out.println("You are a broke person.");
                    //error message
                } else {
                    players[index].setMoney(players[index].getMoney() - 300);
                    players[index].updateLabel();
                    thread.interrupt();
                }
            }
        }
    }

    /**
     * Passes the current player's turn, buying no land.
     */
    private void pass() {
        players[index].updateLabel();
        index += 1;
        if (index >= players.length) {
            round += 1;
            if (purchases == 0) {
                endSelection();
                return;
            } else {
                index = 0;
                purchases = 0;
            }
        }
        showCurrentPlayer();
    }

    /**
     * Ends the land selection phase.
     */
    private void endSelection() {
        active = false;
        x = 9;
        y = 5;
        game.Manager.getGame().beginTurns();
    }

    /**
     * Scrolls across the map, highlighting each land plot in turn for selection.
     */
    private void traverseMap() {
        thread = new Thread(() -> {
            while (active) {
                try {
                    Platform.runLater(() -> markTile());
                    Thread.sleep(500);
                    Platform.runLater(() -> {
                        try {
                            unmarkTile();
                            advancePointerToNextUnowned();
                        } catch (ArrayIndexOutOfBoundsException e) {
                            advancePointerToNextUnowned();
                        }
                    });
                } catch (InterruptedException e) {
                    MapTile selected = map.getTile(y, x);
                    Player player = players[index];
                    Platform.runLater(() -> {
                        selected.setOwner(player);
                        player.addLand(selected);
                    });
                    index += 1;
                    purchases += 1;
                    if (index >= players.length) {
                        round += 1;
                        index = 0;
                        purchases = 0;
                    }
                    advancePointerToNextUnowned();
                    Platform.runLater(() -> showCurrentPlayer());
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    /*
     * Marks the current tile as the current selection.
     */
    private void markTile() {
        Pane pane = map.getTile(y, x).getPane();
        ImageView image = new ImageView(Color.BLACK.getBorderColor());
        pane.getChildren().add(image);
    }

    /*
     * Erases the black selection border from the current tile.
     */
    private void unmarkTile() {
        Pane pane = map.getTile(y, x).getPane();
        pane.getChildren().remove(pane.getChildren().size() - 1);
    }

    /*
     * Move Pointer to next unowned tile.
     */
    private void advancePointerToNextUnowned () {
        do {
            advancePointer();
        } while (!map.checkIfUnowned(y, x) || (x == 4 && y == 2));
    }

    /*
     * Moves the pointer forward. (left to right then top to bottom)
     */
    private void advancePointer () {
        x += 1;
        if (x > 8) {
            x = 0;
            y += 1;
        }
        if (y > 4) {
            y = 0;
        }
    }
}
