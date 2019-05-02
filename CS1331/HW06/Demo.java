import java.time.LocalDate;
/**
* Tester for CheckoutMachine
* @author Hussain Mumtaz
* @version 1.0
*/
public class Demo {
    /**
    * main method to test CheckoutMachine
    * @param args listed arguments
    */
    public static void main(String[] args) {
        CheckoutMachine store = new CheckoutMachine("TechConvenience");
        //CheckoutMachine notStore = new CheckoutMachine("TechieDeck");
        //Causes error
        Item coffee = new Item("Ground coffee", 961.05, 9.68, 9220570);
        Item mac = new Item(74.84, 12);
        Item dne = new Item(1, 1);
        Item pizza = new Item("Frozen pizza", 902.93, 2.98, 9263670);
        Item dope = new Item("Dope", 420, 420, 420);

        Cash money2blow = new Cash(100);
        Cash brokeAF = new Cash(12);
        CreditCard platinum = new CreditCard("Burdell", 1927,
            LocalDate.of(3005, 12, 31));
        CreditCard basic = new CreditCard("Brokey", 12, LocalDate.of(2015,
            12, 31));
        CreditCard expired = new CreditCard("Pirate", 1927, LocalDate.of(2005,
            12, 31));
        BuzzCard buzz = new BuzzCard("Buzz", 1950);
        BuzzCard uga = new BuzzCard("u(sic)ga", 12);

        try {
            store.scan(coffee);
            store.scan(mac);
            store.payForCart(money2blow);
            System.out.println("Success");
        } catch (InvalidItemException e) {
            System.out.println(e.getMessage());
        }

        try {
            store.scan(dope);
            System.out.println("Success");
        } catch (InvalidItemException e) {
            System.out.println(e.getMessage());
        }

        try {
            store.scan(coffee);
            store.scan(mac);
            store.payForCart(brokeAF);
            System.out.println("Success");
        } catch (InvalidItemException e) {
            System.out.println(e.getMessage());
        }

        try {
            store.scan(coffee);
            store.scan(mac);
            store.payForCart(platinum);
            System.out.println("Success");
        } catch (InvalidItemException e) {
            System.out.println(e.getMessage());
        }

        try {
            store.scan(coffee);
            store.scan(mac);
            store.payForCart(basic);
            System.out.println("Success");
        } catch (InvalidItemException e) {
            System.out.println(e.getMessage());
        }

        try {
            store.scan(coffee);
            store.scan(mac);
            store.payForCart(expired);
            System.out.println("Success");
        } catch (InvalidItemException e) {
            System.out.println(e.getMessage());
        }

        try {
            store.scan(coffee);
            store.scan(mac);
            store.payForCart(buzz);
            System.out.println("Success");
        } catch (InvalidItemException e) {
            System.out.println(e.getMessage());
        }

        try {
            store.scan(coffee);
            store.scan(mac);
            store.payForCart(uga);
            System.out.println("Success");
        } catch (InvalidItemException e) {
            System.out.println(e.getMessage());
        }
    }
}