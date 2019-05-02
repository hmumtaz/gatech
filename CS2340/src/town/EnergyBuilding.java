package town;

import input.IInput;
import input.Key;
import input.Manager;
import player.Player;
import player.Mule;
import player.Type;
import timer.Turn;

/**
* @author Hussain Mumtaz
* @version 1.0
*/
public class EnergyBuilding implements Building, Store {

    private int quantity = 8;
    private int price = 50;
    Player p;

    @Override
    public boolean isOutfitter() {
        return true;
    }

    @Override
    public void action(Turn turn) {
        p = turn.getPlayer();
        if (p.getMule() != null) {
            int cost = Type.ENERGY.getCost();
            if (p.getMoney() >= cost) {
                p.getMule().setType(Type.ENERGY);
                p.addMoney(0 - cost);
            }
        } else {
            registerHandlers();
            printMsg();
        }
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void buy(Player player) {
        if (getQuantity() > 0 && player.getMoney() >= getPrice() && isInStore(player)) {
            quantity --;
            player.addEnergy(1);
            price += 20;
            player.addMoney(0 - getPrice());
            printMsg();
        } else {
            System.out.println("You have left the Energy Store");
        }
    }

    @Override
    public void sell(Player player) {
        if (player.getEnergy() >= 1 && isInStore(player)) {
            quantity ++;
            player.addEnergy(-1);
            player.addMoney(getPrice());
            if (price > 20) {
                price = price - 20;
            }
            printMsg();
        } else {
            System.out.println("You have left the Energy Store");
        }
    }

    @Override
    public void printMsg() {
        String str = "There is " + getQuantity() + " Energy left.";
        str += "\nThe current price of Energy is " + getPrice();
        str += "\nWould you like to buy or sell Energy?";
        System.out.println(str);
    }

    @Override
    public boolean isInStore(Player player) {
        if (player.getX() >= 4.14
            && player.getX() <= 4.5
            && player.getY() >= 0.39
            && player.getY() <= 0.8) {
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
}
