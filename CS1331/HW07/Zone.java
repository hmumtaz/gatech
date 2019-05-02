import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
/**
* Represents a Zone object.
*
* @author Hussain Mumtaz
* @version 1.0
*/
public class Zone implements Iterable<Card> {
    protected ArrayList<Card> cardList;

    /**
    * Constructs a Zone Object
    */
    public Zone() {
        cardList = new ArrayList<Card>();
    }
    /**
    * gets and returns Card at index in cardList
    *
    * @param x index
    * @return cardList.get(x)
    */
    public Card get(int x) {
        return cardList.get(x);
    }
    /**
    * adds card to cardList
    *
    * @param c Card to add
    * @return cardList.add(x)
    */
    public boolean add(Card c) {
        return cardList.add(c);
    }
    /**
    * removes card from cardList
    *
    * @param c Card to remove
    * @return cardList.remove(c)
    */
    public boolean remove(Card c) {
        return cardList.remove(c);
    }
    /**
    * removes card from cardList
    *
    * @param x position of Card to remove
    * @return cardList.remove(x)
    */
    public Card remove(int x) {
        return cardList.remove(x);
    }
    /**
    * removes all cards from cardList
    *
    * @return discardedCards
    */
    public ArrayList<Card> discardAll() {
        Iterator<Card> iter = this.iterator();
        ArrayList<Card> discardedCards = new ArrayList<Card>();
        while (iter.hasNext()) {
            discardedCards.add(iter.next());
            iter.remove();
        }
        return discardedCards;
    }
    /**
    * shuffles cardList
    *
    */
    public void shuffle() {
        Collections.shuffle(cardList);
    }
    /**
    * iterates through cardList
    * @return cardList.iterator()
    */
    public Iterator<Card> iterator() {
        return cardList.iterator();
    }
    /**
    * moves cardList to another zone
    * @param other Zone to add cards to
    */
    public void moveCardsTo(Zone other) {
        Iterator<Card> iter = this.iterator();
        while (iter.hasNext()) {
            other.cardList.add(iter.next());
        }
        this.cardList.clear();
    }
    /**
    * gets cardList size
    * @return cardList.size()
    */
    public int size() {
        return cardList.size();
    }
    /**
    * checks if cardList contains a card
    * @param c Card to check for comparison
    * @return cardList.contains(c)
    */
    public boolean contains(Card c) {
        return cardList.contains(c);
    }
    /**
    * gets the numbers of Gnomes
    * @return gnomeNum
    */
    public int numGnomes() {
        int gnomeNum = 0;
        Iterator<Card> iter = this.iterator();
        while (iter.hasNext()) {
            if (iter.next().hashCode() == new Gnome().hashCode()) {
                gnomeNum = gnomeNum + 1;
            }
        }
        return gnomeNum;
    }
    /**
     * overrides object's toString method to return all cards in the zone
     */
    @Override
    public String toString() {
        String j = "";
        Iterator<Card> iter = this.iterator();
        int x = 0;
        while (iter.hasNext()) {
            j = j + x + ": " + iter.next().toString() + "\n";
            x = x + 1;
        }
        return j;
    }
}