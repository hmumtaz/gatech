import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
* Represents a Mailbox object.
*
* @author Hussain Mumtaz
* @version 1.0
*/
public class Mailbox {
    private String name;
    private ArrayList<Message> messages;
    /**
    * Constructs a Mailbox Object
    *
    * @param name Maibox name
    * @param messages ArrayList of messages within Mailbox
    */
    public Mailbox(String name, ArrayList<Message> messages) {
        this.name = name;
        this.messages = messages;
    }
    /**
    * gets and returns the Mailbox name
    *
    * @return name
    */
    public String getName() {
        return name;
    }
    /**
    * gets and returns the Mailbox messages
    *
    * @return messages
    */
    public ArrayList<Message> getMessages() {
        return messages;
    }
    /**
    * adds message to messages
    * @param msg message to be added
    */
    public void add(Message msg) {
        messages.add(msg);
    }
    /**
    * Creates a dateComparator() object
    * @return new Comparator() {
    *       public int compare(Message a, Message b) {
    *           return b.getDateTime().compareTo(a.getDateTime());
    *        }
    */
    public Comparator<Message> dateComparator() {
        return new Comparator<Message>() {
            public int compare(Message a, Message b) {
                return b.getDateTime().compareTo(a.getDateTime());
            };
        };
    }
    /**
    * implements dateComparator() object
    */
    public void dateSort() {
        Collections.sort(messages, dateComparator());
    }
    /**
    * Creates a senderComparator() object
    *@return new Comparator() {
    *       public int compare(Message a, Message b) {
    *           return a.getSender().compareTo(b.getSender());
    *        }
    */
    public Comparator<Message> senderComparator() {
        return new Comparator<Message>() {
            public int compare(Message a, Message b) {
                return a.getSender().compareTo(b.getSender());
            };
        };
    }
    /**
    * implements senderComparator() object
    */
    public void senderSort() {
        Collections.sort(messages, senderComparator());
    }
    /**
    * Creates a subjectComparator() object
    *@return new Comparator() {
    *       public int compare(Message a, Message b) {
    *           return a.getSubject().compareTo(b.getSubject())
    */
    public Comparator<Message> subjectComparator() {
        return new Comparator<Message>() {
            public int compare(Message a, Message b) {
                return a.getSubject().compareTo(b.getSubject());
            };
        };
    }
    /**
    * implements subjectComparator() object
    */
    public void subjectSort() {
        Collections.sort(messages, subjectComparator());
    }
    /**
     * overrides object's toString method to return Mailbox name
     * @return getName()
     */
    @Override
    public String toString() {
        return getName();
    }
}