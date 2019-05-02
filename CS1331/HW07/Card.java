/**
* Represents a Card object.
*
* @author Hussain Mumtaz
* @version 1.0
*/
public abstract class Card implements Comparable<Card> {
    private String name;
    private String description;
    private String flavorText;
    private Boolean scorable;
    private int cost;

    /**
    * Constructs a Card Object
    *
    * @param name Card name
    * @param description Card functional description
    * @param flavorText Card representational description
    * @param scorable property representing Card's scorability
    * @param cost Card cost
    */
    public Card(String name, String description, String flavorText,
        Boolean scorable, int cost) {
        this.name = name;
        this.description = description;
        this.flavorText = flavorText;
        this.scorable = scorable;
        this.cost = cost;
    }

    /**
    * gets and returns the Card name
    *
    * @return name
    */
    public String getName() {
        return name;
    }

    /**
    * gets and returns the Card functional description
    *
    * @return description
    */
    public String getDescription() {
        return description;
    }

    /**
    * gets and returns the Card representational description
    *
    * @return flavorText
    */
    public String getFlavorText() {
        return flavorText;
    }

    /**
    * gets and returns the Card's scorability
    *
    * @return scorable
    */
    public Boolean isScorable() {
        return scorable;
    }

    /**
    * gets and returns the Card cost
    *
    * @return cost
    */
    public int getCost() {
        return cost;
    }

    /**
    * Card's default play method. Removes card from hand.
    *
    * @param p the game object to affect
    */
    public void play(PlasterClash p) {
        p.currentPlayer().getHand().remove(this);
    }

    /**
    * Plays card to the play zone.
    *
    * @param p the game object to affect
    */
    public void playToPlayZone(PlasterClash p) {
        p.getPlayZone().add(this);
    }

    /**
    * compares the cost of a card with inputted card
    *
    * @param other card used for comparison
    * @return this.getCost() - other.getCost()
    */
    public int compareTo(Card other) {
        return this.getCost() - other.getCost();
    }

    /**
    * overrides object's equals method comparing it to Card name instead
    *
    * @param other card checked for equality
    * @return this.getName().equals(that.getName())
    */
    @Override
    public boolean equals(Object other) {
        if (null == other) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (!(other instanceof Card)) {
            return false;
        }
        Card that = (Card) other;
        return this.getName().equals(that.getName());
    }

    /**
    * overrides object's hashCode method
    * @return result
    */
    public int hashCode() {
        int result = 6;
        result = 31 * result * name.hashCode();
        return result;
    }

    /**
     * overrides object's toString method to return card name and cost
     * @return (getName() + " (" + getCost() + ")")
     */
    @Override
    public String toString() {
        return (getName() + " (" + getCost() + ")");
    }
}