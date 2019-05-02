/**
 * NotEnoughFundsException gets thrown by CheckoutMachine when payment is
 * insufficient.
 *
 * @author Hussain Mumtaz
 * @version 1.0
 */
public class NotEnoughFundsException extends PaymentFailedException {
    /**
     * Creates a new NotEnoughFundsException with the given message.
     * @param  msg The message of the exception.
     */
    public NotEnoughFundsException(String msg) {
        super(msg);
    }
}