import java.util.ArrayList;
/**
* Represents an Trash Mailbox.
*
* @author Hussain Mumtaz
* @version 1.0
*/
public class Trash extends Mailbox {
    private static ArrayList<Message> mess = new ArrayList<>();
    /**
    * Constructs an Trash object
    */
    public Trash() {
        super("Trash", mess);
    }
}