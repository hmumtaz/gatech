package player;

import javafx.scene.image.Image;

import resources.Manager;

/**
 * @author Janet Liang
 * @version 1.0
 */
public enum Type {

    NONE("Standard", 0),
    FARM("Food", 25),
    MINE("Ore", 75),
    ENERGY("Energy", 50);

    private String name;
    private int cost;

    /**
     * Constructor.
     *
     * @param name The name of this type.
     * @param cost The cost of this type.
     */
    private Type(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    /**
     * Converts a string to a type.
     *
     * @param name The name of the type.
     * @return The type.
     */
    public static Type getType(String name) {
        if (name.equals("Standard")) {
            return Type.NONE;
        } else if (name.equals("Food")) {
            return Type.FARM;
        } else if (name.equals("Ore")) {
            return Type.MINE;
        } else if (name.equals("Energy")) {
            return Type.ENERGY;
        } else {
            return null;
        }
    }

    /**
     * Gets the cost of this type.
     *
     * @return The cost of this type.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Gets the image for a colored mule.
     *
     * @return The image for a colored mule.
     */
    public Image getMuleColor() {
        return Manager.getResources()
                      .getMapImage(
                          name + "_Mule.png"
                      );
    }

    /**
     * Gets a tile colored by this type.
     *
     * @return The tile.
     */
    public Image getTileColor() {
        return Manager.getResources()
                      .getMapImage(
                          name + "_Mule_Tile.png"
                      );
    }

    /**
     * Gets the name of this type.
     *
     * @return The name of this type.
     */
    public String getName() {
        return name;
    }
}
