/**
*This class represents a GradStudent object
* @author Hussain Mumtaz
* @version 1.00
*/
public class GradStudent extends Student {
/**
* Overrides the default relax method for student class
* @param hours regardless of hours nonStudyhours always equal 0
*/
    @Override
    public void relax(int hours) {
        this.nonStudyHours = 0;
    }
    /**
    *creates a GradStudent object
    * @param first first name of GradStudent
    * @param last last name of GradStudent
    * @param user username of GradStudent
    */
    GradStudent(String first, String last, String user) {
        super(first, last, user);
    }
}