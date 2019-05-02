package town;

import player.Player;
import timer.Turn;

/**
* @author Hussain Mumtaz
* @version 1.0
*/
public interface Building {
    /**
    * Asserts whether the building is used to outfit M.U.L.E.s
    *
    * @return the building's outfitter property
    */
    public boolean isOutfitter();
    
    /**
    * Performs the action assigned to the building 
    * @param turn the current turn.
    */
    public void action(Turn turn);
}
