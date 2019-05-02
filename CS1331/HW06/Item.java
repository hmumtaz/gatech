/**
 * Item class represents an item, having name, weight, price, and barcode.
 * @author Alex Epifano & Thomas Shields & Hussain Mumtaz
 * @version 1.0
 */
public class Item {

    private String name;
    private double weight;
    private double price;
    private long barcode;

    /**
     * Creates a new item with the specified weight and barcode. Initializes
     * name as null and price as 0.0.
     * @param  weight  The weight in grams of the item.
     * @param  barcode The barcode of the item.
     */
    public Item(double weight, long barcode) {
        this("null", weight, 0.0, barcode);
    }

    /**
     * Create new item with the specified properties.
     * @param  name    The name of the item.
     * @param  weight  The weight of the item in grams.
     * @param  price   The price of the item in USD.
     * @param  barcode The barcode of the item.
     */
    public Item(String name, double weight, double price, long barcode) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.barcode = barcode;
    }
    /**
    * accessor method to get price
    * @return returns the price
    */
    public double getPrice() {
        return price;
    }
    /**
    * overrites the defauth equals method
    * @param other takes in an object to equate to
    * @return returns true or false
    */
    public boolean equals(Object other) {
        if (null == other) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (!(other instanceof Item)) {
            return false;
        }
        Item that = (Item) other;
        return (this.weight == that.weight
            && this.barcode == that.barcode);
    }
}
