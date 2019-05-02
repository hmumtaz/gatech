/**
* This class represents a Person object.
* @author Hussain Mumtaz
* @version 1.00
*/
public class Person {
    protected String firstName;
    protected String lastName;
    protected String username;

    /**
    * gets and returns the first name of a given person
    * @return firstName
    */
    public String getFirstName() {
        return firstName;
    }
    /**
    * gets and returns the last name of a given person
    * @return lastName
    */
    public String getLastName() {
        return lastName;
    }
    /**
    * gets and returns the username of a given person
    * @return username
    */
    public String getUsername() {
        return username;
    }
    /**
    *creates a person object
    * @param firstName first name of person
    * @param lastName last name of person
    * @param username username of person
    */
    Person(String firstName, String lastName, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }
    /**
    *converts the data of a person object to a single string
    *@return this.getFirstName() + " " + this.getLastName() + " "
    *     + this.getUsername()
    */
    public String toString() {
        return (this.getFirstName() + " " + this.getLastName() + " "
            + this.getUsername());
    }
}
