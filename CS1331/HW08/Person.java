/**
* Represents a Person object.
*
* @author Hussain Mumtaz
* @version 1.0
*/
public class Person implements Comparable<Person> {
    private String email;
    private String name;
    /**
    * Constructs a Person Object
    *
    * @param name Person name
    * @param email E-mail address
    */
    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }
    /**
    * overrides object's equals method comparing it to Person parameters instead
    *
    * @param other person checked for equality
    * @return this.getName().equals(that.getName())
    *       && this.getEmail().equals(that.getEmail())
    */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Person)) {
            return false;
        }
        Person that = (Person) other;
        return this.getName().equals(that.getName())
            && this.getEmail().equals(that.getEmail());
    }
    /**
    * overrides object's hashCode method
    * @return result
    */
    public int hashCode() {
        int result = 20;
        result = 31 * result * getName().hashCode() * getEmail().hashCode();
        return result;
    }
    /**
    * compares a Person with inputted Person
    *
    * @param other Person used for comparison
    * @return this.hashCode() - other.hashCode()
    */
    public int compareTo(Person other) {
        return this.hashCode() - other.hashCode();
    }
    /**
    * gets and returns the name
    *
    * @return name
    */
    public String getName() {
        return name;
    }
    /**
    * gets and returns the email
    *
    * @return email
    */
    public String getEmail() {
        return email;
    }
    /**
     * overrides object's toString method to return name
     * @return name
     */
    @Override
    public String toString() {
        return name;
    }
}