package map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import player.Mule;
import player.Player;
import player.Color;
import resources.Manager;

/**
 * @author Janet Liang
 * @version 1.0
 */
public class MapTile {
    //Location on a 9 by 5 grid
    protected int x = 0;
    protected int y = 0;
    protected Player owner; //Current owner of the tile
    protected Image image; //Graphic display image of the tile
    private StackPane pane = new StackPane();
    protected String tileType;
    protected Mule mule;

    /**
     * Get X value.
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Get Y value.
     *
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Get Player who owns the tile.
     *
     * @return owner
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Get Player who owns the tile.
     *
     * @param owner The new owner.
     */
    public void setOwner(Player owner) {
        this.owner = owner;
        regenTile();
    }

    /**
     * Get color of tile.
     *
     * @return x
     */
    public Color getColor() {
        if (this.getOwner() == null) {
            return null;
        }
        return owner.getColor();
    }

    /**
     * Returns whether this is a town.
     *
     * @return town
     */
    public boolean isTown() {
        return false;
    }

    /*
     * Returns the mule on this tile.
     * @return the Mule on this tile.
     */
    public Mule getMule() {
        return mule;
    }

    /*
     * Place a mule on this tile.
     * @param m the mule to place on the tile.
     */
    public void setMule(Mule m) {
        if (mule != null) {
            pane.getChildren().remove(mule.getTileSprite());
        }
        mule = m;
        pane.getChildren().add(mule.getTileSprite());
    }

    /*
     * Returns the type of this tile as a string.
     * @return A String representing the type of this tile.
     */
    public String getTileType() {
        return tileType;
    }

    /*
     * Sets this tile to a certain type.
     * @param t the type to set this tile to.
     */
    public void setTileType(String t) {
        tileType = t;
    }

    /*
     * Accessor for this tile's javafx Pane structure
     * @return the Pane of this maptile.
     */
    public Pane getPane() {
        return pane;
    }

    /*
     * Carries out the logic to create this maptile's image pane
     */
    private void regenTile() {
        pane.getChildren().clear();
        pane.getChildren().add(new ImageView(image));
        if (this.getColor() != null) {
            pane.getChildren()
                .add(
                    new ImageView(
                        this.getColor()
                            .getBorderColor()
                    )
                );
        }
        if (mule != null) {
            pane.getChildren().add(mule.getTileSprite());
        }
    }

    /*
     * Generate a pane to represent this tile and return it.
     * @return the pane as a Node.
     */
    public Node render() {
        regenTile();
        return pane;
    }
}
