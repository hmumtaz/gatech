package town;

import javafx.application.Platform;
import javafx.scene.text.Text;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import input.IInput;
import input.Key;
import input.Manager;
import player.Color;
import player.Player;
import timer.Turn;
import map.Map;
import map.MapTile;
import game.Game;

/**
* @author Hussain Mumtaz
* @version 1.0
*/
public class LandBuilding implements Building, Store {
    
    int price = 0;
    int value = 0;
    int quantity = 0;
    boolean active = false;
    Player p;
    Turn t;
    Thread thread;
    Map map;
    int x = 0;
    int y = 0;

    /**
    * Sets the map to traverse
    *
    * @param m Map to set
    */
    public void setMap(Map m) {
        map = m;
    }

    @Override
    public boolean isOutfitter() {
        return false;
    }

    @Override
    public void action (Turn turn) {
        t = turn;
        p = turn.getPlayer();
        setPrice(turn);
        if (p.getMoney() > price || p.ownsLand()) {
            registerHandlers();
            setPrice(turn);
            setValue();
            printMsg();
        }
    }

    @Override
    public int getQuantity() {
        resetQuantity();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (map.getTile(i,j).getOwner() == null) {
                    quantity ++;
                }
            }
        }
        return quantity;
    }

    /**
    * resets the quantity in store
    */
    public void resetQuantity() {
        quantity = 0;
    }

    @Override
    public int getPrice() {
        return price;
    }

    /**
    * Sets price of land
    *
    * @param turn provides round data
    */
    private void setPrice(Turn turn) {
        Random random = new Random();
        int adjustment = random.nextInt(100);
        price = 300 + (turn.getRound() * adjustment);
    }

    /**
    * Gets value of land owned by players
    *
    * @return Current value of land.
    */
    public int getValue() {
        return value;
    }

    /**
    * Sets value of land owned by players
    */
    private void setValue() {
        Random random = new Random();
        int adjustment = random.nextInt(200);
        value = 400 + adjustment;
    }

    @Override
    public void buy(Player player) {
        if (quantity > 0 && player.getMoney() >= getPrice() && isInStore(player)) {
            input.Manager.getInput().popFrame();
            t.getTimer().pause();
            beginBuy(player);
            x = 0;
            y = 0;
        } else {
            System.out.println("You have left the Land Store");
        }
    }

    /**
    * Initiates traversal to buy
    *
    * @param player the player buying
    */
    private void beginBuy(Player player) {
        game.Manager.getGame().leaveTown();
        player.getLabel().setText(player.getLabel().getText() + "------");
        active = true;
        registerSelecter();
        traverseBuy(player);
    }

        /**
    * Traverses over unowned properties
    *
    * @param player the player buying
    */
    private void traverseBuy(Player player) {
        thread = new Thread(() -> {
            while (active) {
                try {
                    Platform.runLater(() -> markTile());
                    Thread.sleep(500);
                    Platform.runLater(() -> {
                        unmarkTile();
                        advancePointerToNextUnowned();
                    });
                } catch (InterruptedException e) {
                    MapTile selected = map.getTile(y, x);
                    Platform.runLater(() -> {
                        selected.setOwner(player);
                        player.addLand(selected);
                        player.addMoney(0 - getPrice());
                        player.updateLabel();
                        player.getLabel().setText(player.getLabel().getText() + "------");
                        active = false;
                        input.Manager.getInput().popFrame();
                        t.getTimer().play();
                    });
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void sell(Player player) {
        if (player.ownsLand() && isInStore(player)) {
            if (player.landOwned() == 1) {
                input.Manager.getInput().popFrame();
                game.Manager.getGame().leaveTown();
                player.getLabel().setText(player.getLabel().getText() + "------");
                player.getOwnedLand().get(0).setOwner(null);
                player.clearLand();
                player.updateLabel();
                player.getLabel().setText(player.getLabel().getText() + "------");
            } else {
                input.Manager.getInput().popFrame();
                t.getTimer().pause();
                beginSell(player);
                x = 0;
                y = 0;
            }
        } else {
            System.out.println("You have left the Land Store");
        }
    }

        /**
    * Initiates traversal to sell
    *
    * @param player the player selling
    */
    private void beginSell(Player player) {
        game.Manager.getGame().leaveTown();
        player.getLabel().setText(player.getLabel().getText() + "------");
        active = true;
        registerSelecter();
        traverseSell(player);
    }

        /**
    * Traverses over player's properties
    *
    * @param player the player selling
    */
    private void traverseSell(Player player) {
        thread = new Thread(() -> {
            while (active) {
                try {
                    Platform.runLater(() -> markTile());
                    Thread.sleep(500);
                    Platform.runLater(() -> {
                        unmarkTile();
                        advancePointerToNextOwned(player);
                    });
                } catch (InterruptedException e) {
                    MapTile selected = map.getTile(y, x);
                    Platform.runLater(() -> {
                        selected.setOwner(null);
                        player.removeLand(selected);
                        player.addMoney(getValue());
                        player.updateLabel();
                        player.getLabel().setText(player.getLabel().getText() + "------");
                        active = false;
                        input.Manager.getInput().popFrame();
                        t.getTimer().play();
                    });
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void printMsg() {
        String str = "There is " + getQuantity() + " Land left.";
        str += "\nThe current price of Land is " + getPrice();
        str += "\nThe current value of Owned Land is " + getValue();
        str += "\nWould you like to buy or sell Land?";
        System.out.println(str);
    }

    @Override
    public boolean isInStore(Player player) {
        if (player.getX() >= 2.5
            && player.getX() <= 3.25
            && player.getY() >= 2.2
            && player.getY() <= 3.4) {
            return true;
        } else {
            input.Manager.getInput().popFrame();
            return false;
        }
    }

    /**
    * Registers handler
    */
    private void registerHandlers() {
        IInput i = input.Manager.getInput();
        i.pushFrame();
        i.registerKeyHandler(Key.PRIMARY, () -> buy(p));
        i.registerKeyHandler(Key.SECONDARY, () -> sell(p));
        i.registerKeyHandler(Key.UP, () -> p.moveVertical(true));
        i.registerKeyHandler(Key.DOWN, () -> p.moveVertical(false));
        i.registerKeyHandler(Key.RIGHT, () -> p.moveHorizontal(true));
        i.registerKeyHandler(Key.LEFT, () -> p.moveHorizontal(false));
    }

    /**
    * Registers handler
    */
    private void registerSelecter() {
        IInput i = input.Manager.getInput();
        i.pushFrame();
        i.registerKeyHandler(Key.PRIMARY, () -> selectLand());
    }

    /**
    * Selects Land
    */
    private void selectLand() {
        if (active) {
            thread.interrupt();
        }
    }

    /**
    * Marks tile
    */
    private void markTile() {
        Pane pane = map.getTile(y, x).getPane();
        ImageView image = new ImageView(Color.BLACK.getBorderColor());
        pane.getChildren().add(image);
    }

    /**
    * Unmarks Tile
    */
    private void unmarkTile() {
        Pane pane = map.getTile(y, x).getPane();
        pane.getChildren().remove(pane.getChildren().size() - 1);
    }

    /**
    * Goes to next unowned tile
    */
    private void advancePointerToNextUnowned() {
        do {
            advancePointer();
        } while (!map.checkIfUnowned(y, x) || (x == 4 && y == 2));
    }

    /**
    * Goes to next player tile
    *
    * @param player the player selling
    */
    private void advancePointerToNextOwned(Player player) {
        ArrayList<MapTile> owned = player.getOwnedLand();
        MapTile tile;
        do {
            advancePointer();
        }
        while(map.checkIfUnowned(y, x)
            || !(map.getTile(y, x).getOwner().equals(player))
            || (x == 4 && y == 2));
    }

    /**
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
