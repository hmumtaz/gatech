/**
 * PaymentException gets thrown by CheckoutMachine when Payment fails.
 *
 * @author Hussain Mumtaz
 * @version 1.0
 */
public class PaymentFailedException extends Exception {
    /**
     * Creates a new PaymentFailedException with the given message.
     * @param  msg The message of the exception.
     */
    public PaymentFailedException(String msg) {
        super(msg);
    }
}