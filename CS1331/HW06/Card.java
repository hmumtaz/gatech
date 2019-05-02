/**
 * Card is an abstract type of Payment Method
 *
 * @author Hussain Mumtaz
 * @version 1.0
 */
abstract class Card implements PaymentMethod {
    private String ownerName;
    private double balance;
    /**
     * Creates a Card instance
     * @param  ownerName person who owns card
     * @param  balance balance on card
     */
    public Card(String ownerName, double balance) {
        this.ownerName = ownerName;
        this.balance = balance;
    }
    /**
     * implements pay
     * @param  amount states amount to pay
     */
    public void pay(double amount) throws PaymentFailedException {
        if (balance >= amount) {
            balance = balance - amount;
        } else {
            throw new NotEnoughFundsException("Good Credit, no problem!"
                + " Bad credit, big problem. Come back with money.");
        }
    }

}