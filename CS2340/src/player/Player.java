package player;

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;
import javafx.scene.Node;

import map.MapTile;
import resources.Manager;

/**
 * @author Janet Liang
 * @version 1.0
 */
public class Player {

    private String name;
    private Race race;
    private Color color;
    private double x, y;
    public int money, food, energy, ore;
    private double speed;
    private Mule mule;
    private ArrayList<MapTile> ownedLand;
    private int landOwned;
    private Text label;
    private StackPane sprite;
    private double width;
    private double height;

    /**
     * Contructs a Player with chosen Color and Race
     * and sets all other values to default.
     *
     * @param name The name of this player.
     * @param r The race of this player.
     * @param c The color of this player.
     * @param money The money this player has.
     * @param food The food this player has.
     * @param energy The energy this player has.
     * @param ore The ore this player has.
     */
    public Player(String name, Race r, Color c, Integer money, Integer food, Integer energy, Integer ore) {
        if (name == null) {
            this.name = "name";
        } else {
            this.name = name;
        }
        race = r;
        color = c;
        if (money == null) {
            this.money = race.getStartingMoney();
        } else {
            this.money = money;
        }
        if (food ==  null) {
            this.food = 8;
        } else {
            this.food = food;
        }
        if (energy == null) {
            this.energy = 4;
        } else {
            this.energy = energy;
        }
        if (ore ==  null) {
            this.ore = 0;
        } else {
            this.ore = ore;
        }
        landOwned = 0;
        speed = 0.02;
        ownedLand = new ArrayList<MapTile>();
        label = new Text();
        sprite = new StackPane();
        sprite.getChildren().add(new ImageView(color.getPlayerColor()));
        removeImage();
        updateLabel();
    }

    /**
     * Gets the sprite for this player.
     *
     * @return The sprite for this player.
     */
    public StackPane getSprite() {
        return sprite;
    }

    /**
     * Calculates the dimensions of a given node.
     *
     * @param node The node to calculate the dimensions for.
     */
    public void calculateDimensions(Node node) {
        width = node.getBoundsInParent().getWidth();
        height = node.getBoundsInParent().getHeight();
    }

    /**
     * Reset player position to town and display sprite.
     */
    public void resetPosition() {
        x = 4.5;
        y = 2.5;
        updateImage();
        displayImage();
    }

    /**
     * Makes this player opaque.
     */
    public void displayImage() {
        sprite.setOpacity(1);
    }

    /**
     * Makes this player transparent.
     */
    public void removeImage() {
        sprite.setOpacity(0);
    }

    /**
     * Update the position of the sprite.
     */
    private void updateImage() {
        sprite.setLayoutX(x / 9 * width);
        sprite.setLayoutY(y / 5 * height);
    }

    /**
     * Call to player on y axis.
     *
     * @param positive Use true to move up, false to move down.
     */
    public void moveVertical (boolean positive) {
        if (positive) {
            if (y >= speed * 5) {
                y -= speed * 5;
            }
        } else {
            if (y <= 5 - speed * 5 - 0.3) {
                y += speed * 5;
            }
        }
        updateImage();
    }

    /**
     * Call to player on x axis.
     *
     * @param positive Use true to move right, false to move left.
     */
    public void moveHorizontal (boolean positive) {
        if (positive) {
            if (x <= 9 - speed * 9 - 0.3) {
                x += speed * 9;
            }
        } else {
            if (x >= speed * 9) {
                x -= speed * 9;
            }
        }
        updateImage();
    }

    /**
     * Get name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name.
     *
     * @param name The new name for player.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the label for this player.
     *
     * @return The label for this player.
     */
    public Text getLabel() {
        return label;
    }

    /**
     * Updates the label for this player.
     */
    public void updateLabel() {
        String str = name;
        str += "\nScore: " + getScore();
        str += "\nMoney: " + money;
        str += "\nFood: " + food;
        str += "\nOre: " + ore;
        str += "\nEnergy: " + energy;
        label.setText(str);
    }

    /**
     * Get race.
     *
     * @return race
     */
    public Race getRace() {
        return race;
    }

    /**
     * Set race.
     *
     * @param r The new race for this player.
     */
    public void setRace(Race r) {
        this.race = race;
    }

    /**
     * Get color.
     *
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Get money.
     *
     * @return food
     */
    public int getMoney() {
        return money;
    }

    /**
     * Set money.
     *
     * @param n The new money for this player
     */
    public void setMoney(int n) {
        money = n;
    }

    /**
     * Add money.
     *
     * @param n The money to add
     */
    public void addMoney(int n) {
        money += n;
    }

    /**
     * Get food.
     *
     * @return food
     */
    public int getFood() {
        return food;
    }

    /**
     * Set food.
     *
     * @param n The new food for the player
     */
    public void setFood(int n) {
       food = n;
    }

    /**
     * Add food.
     *
     * @param n The amount of food to add
     */
    public void addFood(int n) {
        food += n;
    }

    /**
     * Get energy.
     *
     * @return energy
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * Set energy.
     *
     * @param n The new energy
     */
    public void setEnergy(int n) {
        energy = n;
    }

    /**
     * Add energy.
     *
     * @param n The amount of energy to add
     */
    public void addEnergy(int n) {
        energy += n;
    }

    /**
     * Add land.
     *
     * @param tile Tile to be added.
     */
    public void addLand(MapTile tile) {
        ownedLand.add(tile);
        landOwned++;
    }

    /**
     * Remove land.
     *
     * @param tile Tile to be removed.
     */
    public void removeLand (MapTile tile) {
        ownedLand.remove(tile);
        landOwned--;
    }

    public void clearLand () {
        ownedLand.clear();
        landOwned = 0;
    }

    /**
     * Get ore.
     *
     * @return amount of ore this player owns
     */
    public int getOre() {
       return ore;
    }

    /**
     * Set ore.
     *
     * @param n The new ore
     */
    public void setOre(int n) {
       ore = n;
    }

    /**
     * Add ore.
     *
     * @param n The ore to add
     */
    public void addOre(int n) {
        ore += n;
    }

    /**
     * Get score.
     *
     * @return this player's score
     */
    public int getScore() {
       return money + (ownedLand.size() * 500) + energy + food + ore;
    }

    /**
     * Get X value.
     *
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * Set X value.
     *
     * @param x The new x value
     */
    public void setX(double x) {
       this.x = x;
    }

    /**
     * Get Y value.
     *
     * @return y
     */
    public double getY() {
        return y;
    }

    /**
     * Set Y value.
     *
     * @param y The new y value
     */
    public void setY(double y) {
       this.y = y;
    }

    /**
     * Adds a mule to this player. This drops any previous mule.
     *
     * @param m The mule to add.
     */
    public void addMule(Mule m) {
        dropMule();
        mule = m;
        sprite.getChildren().add(mule.getSprite());
    }

    /**
     * Returns this player's mule.
     *
     * @return This player's mule.
     */
    public Mule getMule() {
        return mule;
    }

    /**
     * Drops the mule the player is holding.
     */
    public void dropMule() {
        if (mule != null) {
            sprite.getChildren().remove(mule.getSprite());
            mule = null;
        }
    }

    /**
    * Checks if player owns land
    *
    * @return player owns land
    */
    public boolean ownsLand() {
        return landOwned > 0;
    }

    /**
     * Returns the number of lands the player owns.
     *
     * @return The number of lands the player owns.
     */
    public int landOwned() {
        return landOwned;
    }

    /**
    * get ownedLand
    *
    * @return arrayList containing the tiles owned by player
    */
    public ArrayList<MapTile> getOwnedLand() {
        return ownedLand;
    }

    /**
     * Equality check.
     *
     * @param other The object to check for equality.
     * @return Whether the object is the same.
     */
    public boolean equals(Object other) {
        if (null == other) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (!(other instanceof Player)) {
            return false;
        }
        Player that = (Player) other;
        return (this.landOwned == that.landOwned
            && this.name.equals(that.name)
            && this.money == that.money
            && this.ore == that.ore
            && this.energy == that.energy);
    }

    /**
    * Calculates the production of resources at the end of turn
    */
    public void calculateProduction() {
        Mule m;
        int addedEnergy = 0;
        for (MapTile tile: ownedLand) {
            if (tile.getMule() != null && energy > 0) {
                m = tile.getMule();
                if (m.getType() == Type.FARM) {
                    switch(tile.getTileType()) {
                        case("River"):
                            addFood(4);
                            break;
                        case("Plains"):
                            addFood(2);
                            break;
                        case("OneMT"):
                            addFood(1);
                            break;
                        case("TwoMT"):
                            addFood(1);
                            break;
                        case("ThreeMT"):
                            addFood(1);
                            break;
                    }
                } else if (m.getType() == Type.ENERGY) {
                    switch(tile.getTileType()) {
                        case("River"):
                            addedEnergy += 2;
                            break;
                        case("Plains"):
                            addedEnergy += 3;
                            break;
                        case("OneMT"):
                            addedEnergy += 1;
                            break;
                        case("TwoMT"):
                            addedEnergy += 1;
                            break;
                        case("ThreeMT"):
                            addedEnergy += 1;
                            break;
                    }
                } else if (m.getType() == Type.MINE) {
                    switch(tile.getTileType()) {
                        case("River"):
                            addOre(0);
                            break;
                        case("Plains"):
                            addOre(1);
                            break;
                        case("OneMT"):
                            addOre(2);
                            break;
                        case("TwoMT"):
                            addOre(3);
                            break;
                        case("ThreeMT"):
                            addOre(4);
                            break;
                    }
                }
                energy -= 1;
            }
        }
        energy += addedEnergy;
    }
}
