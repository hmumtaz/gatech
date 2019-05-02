/**
* Runtime Exception if the store is incorrect the program terminates.
* @author Hussain Mumtaz
* @version 1.0
*/
public class WrongStoreError extends RuntimeException {
    /**
     * Creates a new WrongStoreError with the given message.
     */
    public WrongStoreError() {
        super("Wrong Store");
    }
}