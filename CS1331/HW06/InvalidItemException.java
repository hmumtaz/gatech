/**
 * InvalidItemException gets thrown by CheckoutMachine when the item is
 * invalid.
 *
 * @author Hussain Mumtaz
 * @version 1.0
 */
public class InvalidItemException extends Exception {
    /**
     * Creates a new InvalidItemException with the given message.
     */
    public InvalidItemException() {
        super("This item is not valid");
    }
}

