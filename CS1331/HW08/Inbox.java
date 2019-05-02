import java.util.ArrayList;
/**
* Represents an Inbox Mailbox.
*
* @author Hussain Mumtaz
* @version 1.0
*/
public class Inbox extends Mailbox {
    private static ArrayList<Message> mess = new ArrayList<>();
    /**
    * Constructs an Inbox object
    */
    public Inbox() {
        super("Inbox", mess);
    }
}