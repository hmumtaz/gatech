package player;

import javafx.scene.image.Image;
import resources.Manager;

/**
 * @author Janet Liang
 * @version 1.0
 */
public enum Color {

    /**
     * Possible player colors.
     * Only one per player.
     *
     */
    BLACK("Selection"), ORANGE("Orange"), PURPLE("Purple"), BLUE("Blue"),
        GREEN("Green");

    /**
     * The name of this color.
     */
    private String name;

    /**
     * Constructs a color.
     *
     * @param name The name of this color.
     */
    private Color(String name) {
        this.name = name;
    }

    /**
     * Returns the border image with this border color.
     *
     * @return the border image.
     */
    public Image getBorderColor() {
        return Manager.getResources()
                      .getMapImage(
                          name + "_Border.png"
                      );
    }

    /**
     * Gets the player image with the current color.
     *
     * @return The player image.
     */
    public Image getPlayerColor() {
        return Manager.getResources()
                      .getMapImage(
                          "Player.png"
                      );
    }

    /**
     * Converts a color string to a color.
     *
     * @param c The string to convert.
     * @return The color equal string.
     */
    public static Color toColor(String c) {
        if (c == null) {
            return ORANGE;
        }
        switch (c) {
            case "Selection":
                return BLACK;
            case "Orange":
                return ORANGE;
            case "Purple":
                return PURPLE;
            case "Blue":
                return BLUE;
            case "Green":
                return GREEN;
            default:
                return ORANGE;
        }
    }

    /**
     * Gets the name for this color.
     *
     * @return The name for this color.
     */
    public String toString() {
        return name;
    }

    /* public Image getMuleColor(int n) {
        if (n == 0) {
            return new Image("Orange_Mule.png");
        } else if (n == 1) {
            return new Image("Purple_Mule.png");
        } else if (n == 2) {
            return new Image("Blue_Mule.png");
        } else {
            return new Image("Green_Mule.png");
        }
    } */
}
