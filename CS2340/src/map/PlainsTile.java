package map;

import javafx.scene.image.Image;
import resources.Manager;

import player.Player;

/**
 * @author Janet Liang
 * @version 1.0
 */

public class PlainsTile extends MapTile {
    /**
     * Constructs a Plains Tile at location (x, y) on the map.
     *
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public PlainsTile(int x, int y) {
        this.x = x;
        this.y = y;
        image = Manager.getResources().getMapImage("Plains_Tile.png");
        tileType = "Plains";
    }
}
