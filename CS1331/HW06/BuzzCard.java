/**
 * BuzzCard is a type of Card
 *
 * @author Hussain Mumtaz
 * @version 1.0
 */
public class BuzzCard extends Card {
    /**
     * Creates a BuzzCard instance
     * @param  ownerName person who owns card
     * @param  balance balance on card
     */
    public BuzzCard(String ownerName, double balance) {
        super(ownerName, balance);
    }
}