import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
/**
* Represents a Field object.
*
* @author Hussain Mumtaz
* @version 1.0
*/
public class Field {
    protected Map<Card, Integer> fieldMap;
    /**
    * Constructs a Field Object
    */
    public Field() {
        fieldMap = new HashMap<Card, Integer>();
        fieldMap.put(new Gnome(), 10);
        fieldMap.put(new MegaKeeble(), 5);
        fieldMap.put(new GardenBread(), 20);
    }
    /**
    * removes card from Field
    *
    * @param c Card type to remove
    */
    public void buyCard(Card c) {
        fieldMap.replace(c, fieldMap.get(c), fieldMap.get(c) - 1);
    }
    /**
    * returns cards in field as an arrayList
    *
    * @return cardList
    */
    public ArrayList<Card> cards() {
        ArrayList<Card> cardList = new ArrayList<>();
        for (Card aCard: fieldMap.keySet()) {
            if (fieldMap.get(aCard) > 0) {
                cardList.add(aCard);
            }
        }
        return cardList;
    }
}