package map;

import javafx.scene.image.Image;
import resources.Manager;

import player.Player;

/**
 * @author Janet Liang
 * @version 1.0
 */
public class OneMountainTile extends MapTile {
    /**
     * Constructs a Tile with one mountain at location (x, y) on the map.
     *
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public OneMountainTile(int x, int y) {
        this.x = x;
        this.y = y;
        image = Manager.getResources().getMapImage("OneMt_Tile.png");
        tileType = "OneMT";
    }
}
