package map;

import resources.Manager;
import javafx.scene.image.Image;

import player.Player;

/**
 * @author Janet Liang
 * @version 1.0
 */

public class RiverTile extends MapTile {
    /**
     * Constructs a River Tile at location (x, y) on the map.
     *
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public RiverTile(int x, int y) {
        this.x = x;
        this.y = y;
        image = Manager.getResources().getMapImage("River_Tile.png");
        tileType = "River";
    }
}
