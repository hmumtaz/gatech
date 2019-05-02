package town;

import player.Player;
import timer.Turn;

/**
* @author Hussain Mumtaz
* @version 1.0
*/
public interface Store {

    /**
    * performs the sale
    *
    * @param player who the item is sold to
    */
    public void buy(Player player);

    /**
    * Allows player to sell to store
    *
    * @param player who sells Item
    */
    public void sell(Player player);

    /**
    * Gets price of item
    *
    * @return price of item
    */
    public int getPrice();

    /**
    * gets quantity of item
    *
    * @return quantity of item
    */
    public int getQuantity();


    /**
    * Prints a message to inform player of prices
    */
    public void printMsg();

    /**
    * Checks if player is in the store
    *
    * @param player the player checked against
    *
    * @return in store or not
    **/
    public boolean isInStore(Player player);
}
