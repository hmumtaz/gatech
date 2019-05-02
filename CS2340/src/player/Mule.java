package player;

import javafx.scene.image.ImageView;

/**
 * @author Janet Liang
 * @version 1.0
 */
public class Mule {

    /**
     * The type for this mule.
     */
    private Type type;

    /**
     * The sprite for this mule.
     */
    private ImageView sprite;

    /**
     * The tilesprite for this mule.
     */
    private ImageView tileSprite;

    /**
     * Contructs a default MULE.
     *
     */
    public Mule() {
        this(Type.NONE);
    }

    /**
     * Constructs a mule for the given type.
     *
     * @param type The type for the mule.
     */
    public Mule(Type type) {
        sprite = new ImageView();
        tileSprite = new ImageView();
        setType(type);
    }

    /**
     * Constructs a mule from the given type string.
     *
     * @param name The type for the mule.
     */
    public Mule(String name) {
        sprite = new ImageView();
        tileSprite = new ImageView();
        setType(Type.getType(name));
    }

    /**
     * Gets the sprite for this mule.
     *
     * @return The sprite for this mule.
     */
    public ImageView getSprite() {
        return sprite;
    }

    /**
     * Gets the tilesprite for this mule.
     *
     * @return The tilesprite for this mule.
     */
    public ImageView getTileSprite() {
        return tileSprite;
    }

    /**
     * Get type of mule.
     *
     * @return The type of the mule.
     */
    public Type getType() {
       return type;
    }

    /**
     * Set type of mule.
     *
     * @param t The new type of the mule
     */
    public void setType(Type t) {
       type = t;
       sprite.setImage(type.getMuleColor());
       tileSprite.setImage(type.getTileColor());
    }

    /**
     * Get cost.
     *
     * @return The cost of the mule.
     */
    public int getCost() {
       return type.getCost();
    }
}
