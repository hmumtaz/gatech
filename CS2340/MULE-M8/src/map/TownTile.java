package map;

import javafx.scene.image.Image;
import resources.Manager;

import player.Player;

/**
 * @author Janet Liang
 * @version 1.0
 */

public class TownTile extends MapTile {

    /**
     * Constructs the Town at location (x, y) on the map.
     *
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public TownTile(int x, int y) {
        this.x = x;
        this.y = y;
        image = Manager.getResources().getMapImage("Town_Tile.png");
    }

    /**
     * Returns whether this is a town.
     *
     * @return town
     */
    public boolean isTown() {
        return true;
    }
}
