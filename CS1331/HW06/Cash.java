/**
 * Cash is a type of Payment Method
 *
 * @author Hussain Mumtaz
 * @version 1.0
 */
public class Cash implements PaymentMethod {

    private double cashOnHand;
    /**
     * Creates an instance of Cash
     * @param  cashOnHand gives a value to cashOnHand
     */
    public Cash(double cashOnHand) {
        this.cashOnHand = cashOnHand;
    }
    /**
     * implements pay
     * @param  amount states amount to pay
     */
    public void pay(double amount) throws PaymentFailedException {
        if (cashOnHand >= amount) {
            cashOnHand = cashOnHand - amount;
        } else {
            throw new NotEnoughFundsException("Ain't got no money, ain't "
                + "got no style, ain't got no girl to make you smile. Don't "
                + "worry, be happy");
        }
    }
}