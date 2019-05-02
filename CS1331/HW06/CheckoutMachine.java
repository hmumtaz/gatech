import java.util.ArrayList;
/**
 * creates a CheckoutMachine Object
 *
 * @author Hussain Mumtaz
 * @version 1.0
 */
public class CheckoutMachine {

    private String storeName;
    private ArrayList<Item> validItems = new ArrayList<Item>();
    private ArrayList<Item> cart = new ArrayList<Item>();
    /**
    *instantiates a CheckoutMachine object
    * @param  storeName states the storeName
    */
    public CheckoutMachine(String storeName) throws WrongStoreError {
        int num = 0;
        while (num == 0) {
            try {
                boolean aBool = Server.isStoreNameValid(storeName);
                num = num + 1;
                if (aBool == false) {
                    throw new WrongStoreError();
                }
            } catch (ServerException e) {
                num = 0;
            }
        }
        int ber = 0;
        while (ber == 0) {
            try {
                validItems = Server.getValidItems();
                ber = ber + 1;
            } catch (ServerException e) {
                ber = 0;
            }
        }
    }
    /**
    *instantiates a scan object
    * @param  item to scan
    */
    public void scan(Item item) throws InvalidItemException {
        if (validItems.contains(item)) {
            cart.add(validItems.get(validItems.indexOf(item)));
        } else {
            throw new InvalidItemException();
        }
    }
    /**
    *accessor method for total price
    * @return price gives the price
    */
    public double getTotalPrice() {
        double price = 0;
        for (int x = 0; x < cart.size(); x = x + 1) {
            price = price + cart.get(x).getPrice();
        }
        return price;
    }
    /**
    *pay for you cart
    * @param  method lists your payment method to pay for your cart
    */
    public void payForCart(PaymentMethod method) {
        try {
            method.pay(this.getTotalPrice());
        } catch (PaymentFailedException e) {
            System.out.println(e.getMessage());
        }
    }
}