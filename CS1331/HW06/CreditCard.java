import java.time.LocalDate;
/**
 * CreditCard is an type of Card
 *
 * @author Hussain Mumtaz
 * @version 1.0
 */
public class CreditCard extends Card {
    private LocalDate expirationDate;
    /**
     * Creates a CreditCard instance
     * @param  ownerName person who owns card
     * @param  balance balance on card
     * @param  expirationDate expirationDate on card
     */
    public CreditCard(String ownerName, double balance,
        LocalDate expirationDate) {
        super(ownerName, balance);
        this.expirationDate = expirationDate;
    }

    /**
     * implements pay
     * @param  amount states amount to pay
     */
    public void pay(double amount) throws PaymentFailedException {
        if (expirationDate.isAfter(LocalDate.now())) {
            super.pay(amount);
        } else {
            throw new CardExpiredException(expirationDate);
        }
    }
}