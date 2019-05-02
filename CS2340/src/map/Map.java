package map;

import player.Player;

/**
 * @author Alan Jiang
 * @version 1.0
 *
 * Map uses a y,x coordinate system with origin in upper left and with
 * x positive going right and y positive going down.
 */

public class Map{
    MapContainer map = new MapContainer();;

    /**
     * Map Constructor.  Takes in a type to determine what is requested.
     * @param type the type of map to generate.  0 is default map, 1 is random.
     */
    public Map(int type) {
        if (type == 0) {
            map.setTile(0,4, new RiverTile (4, 0));
            map.setTile(1,4, new RiverTile (4, 1));
            map.setTile(3,4, new RiverTile (4, 3));
            map.setTile(4,4, new RiverTile (4, 4));
            map.setTile(0,2, new OneMountainTile (2, 0));
            map.setTile(0,6, new ThreeMountainTile (6, 0));
            map.setTile(1,1, new OneMountainTile (1,1));
            map.setTile(2,4, new TownTile (2,4));
            map.setTile(1,8, new ThreeMountainTile (8,1));
            map.setTile(2,0, new ThreeMountainTile (0,2));
            map.setTile(2,8, new OneMountainTile(8,2));
            map.setTile(3,1, new TwoMountainTile(1,3));
            map.setTile(3,6, new TwoMountainTile(6,3));
            map.setTile(4,2, new TwoMountainTile(2,4));
            map.setTile(4,8, new TwoMountainTile(8,4));
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 9; j++) {
                    if (map.getTile(i,j) == null) {
                        map.setTile(i,j, new PlainsTile(i,j));
                    }
                }
            }
        }
    }

    /**
     * Returns the height of the map.
     *
     * @return The height of the map.
     */
    public int height() {
        return 9;
    }

    /**
     * Returns the width of the map.
     *
     * @return The width of the map.
     */
    public int width() {
        return 5;
    }

    /**
     * Returns the maptile at coordinates y,x
     * @param y Target y
     * @param x Target x
     * @return The MapTile at the specified location.
     */
    public MapTile getTile(int y, int x) {
        return map.getTile(y,x);
    }

    /**
     * Attempts to place a mule at the specified tile.
     *
     * @param player Player with the mule.
     * @param tile Tile attempted.
     */
    public void placeMule(Player player, MapTile tile) {
        if (tile.getOwner() == player && tile.getMule() == null) {
            tile.setMule(player.getMule());
        }
        player.dropMule();
    }

    /*
     * Returns owner of tile at y,x
     * @param y Target y
     * @param x Target x
     */
    public Player getOwner(int y, int x) {
        return map.getTile(y,x).getOwner();
    }

    public String getTileType(int y, int x) {
        return map.getTile(y,x).getTileType();
    }

    /*
     * Returns true if tile at y,x is not owned.
     * @param y Target y
     * @param x Target x
     */
    public boolean checkIfUnowned (int y, int x) {
        return getOwner(y,x) == null;
    }

    /**
     * Private wrapper class.  Provides an alternative to storing
     * the map tiles which has accessor functions.
     */
    private class MapContainer{
        MapTile[][] container;

        /**
         * Default constructor creates an empty container.
         * Should be changed to generating the map.  Or, add function
         * to do it.
         */
        private MapContainer() {
            container = new MapTile[5][9];
        }

        /**
         * Write javadoc after confirming which coordinate system will be used
         */
        private MapTile getTile (int y, int x) {
            return container[y][x];
        }

        private void setTile (int y, int x, MapTile tile) {
            container[y][x] = tile;
        }
    }
}
