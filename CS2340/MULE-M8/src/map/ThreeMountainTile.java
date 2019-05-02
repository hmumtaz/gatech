package map;

import javafx.scene.image.Image;
import resources.Manager;

import player.Player;

/**
 * @author Janet Liang
 * @version 1.0
 */

public class ThreeMountainTile extends MapTile {
    /**
     * Constructs a Tile with three mountains at location (x, y) on the map.
     *
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public ThreeMountainTile(int x, int y) {
        this.x = x;
        this.y = y;
        image = Manager.getResources().getMapImage("ThreeMt_Tile.png");
        tileType = "ThreeMT";
    }
}
