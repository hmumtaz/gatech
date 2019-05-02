import java.util.ArrayList;
/**
* Represents an Important Mailbox.
*
* @author Hussain Mumtaz
* @version 1.0
*/
public class Important extends Mailbox {

    private static ArrayList<Message> mess = new ArrayList<>();
    /**
    * Constructs an Important object
    */
    public Important() {
        super("Important", mess);
    }
}