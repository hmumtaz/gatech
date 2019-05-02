/**
*This class represents a Professor object
* @author Hussain Mumtaz
* @version 1.00
*/
public class Professor extends Person {

    protected double funnyFactor;
    protected double gpa;
    /**
    *gets and returns professor's average gpa
    *@return gpa
    */
    public double getGPA() {
        return gpa;
    }
    /**
    *gets and returns professor's funny factor
    *@return funnyFactor
    */
    public double getFunnyFactor() {
        return funnyFactor;
    }
    /**
    *Sets a new value for gpa
    * @param aGpa the value to set for gpa, this value is bounded [0,4]
    */
    public void setGPA(double aGpa) {
        if (aGpa <= 0) {
            this.gpa = 0;
        } else if (aGpa >= 4) {
            this.gpa = 4;
        } else {
            this.gpa = aGpa;
        }
    }
    /**
    *Sets a new value for funny factor
    *@param aFunnyFactor the value to set for funny factor, this value
    *     is bounded [0,1]
    */
    public void setFunnyFactor(double aFunnyFactor) {
        if (aFunnyFactor <= 0) {
            this.funnyFactor = 0;
        } else if (aFunnyFactor >= 1) {
            this.funnyFactor = 1;
        } else {
            this.funnyFactor = aFunnyFactor;
        }
    }
    /**
    *creates a professor object
    * @param first first name of professor
    * @param last last name of professor
    * @param user username of professor
    */
    Professor(String first, String last, String user,
        int gpa, int funnyFactor) {
        super(first, last, user);
        setGPA(gpa);
        setFunnyFactor(funnyFactor);
    }
}
