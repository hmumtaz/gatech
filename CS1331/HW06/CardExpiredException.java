import java.time.LocalDate;
/**
 * CardExpiredException gets thrown by CheckoutMachine when Payment fails.
 *
 * @author Hussain Mumtaz
 * @version 1.0
 */
public class CardExpiredException extends PaymentFailedException {
    /**
     * Creates a new PaymentFailedException with the given message.
     * @param  date The date included in the message.
     */
    public CardExpiredException(LocalDate date) {
        super("Card expired on " + date);
    }
}