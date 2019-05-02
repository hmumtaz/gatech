/**
*This class represents a Student object
* @author Hussain Mumtaz
* @version 1.00
*/
public class Student extends Person {
    protected int studyHours = 0;
    protected int nonStudyHours = 0;
    /**
    *gets and returns student's studyHours
    *@return studyHours
    */
    public int getStudyHours() {
        return studyHours;
    }
    /**
    *gets and returns student's nonStudyHours
    *@return nonStudyHours
    */
    public int getNonStudyHours() {
        return nonStudyHours;
    }
    /**
    *gets and returns student's studyPercentage
    *@return studyPercentage
    */
    public double getStudyPercentage() {
        double totalHours = (getStudyHours() + getNonStudyHours());
        double studyPercentage = (getStudyHours() / totalHours);
        return studyPercentage;
    }
    /**
    *adds hours to a student's study hours
    *@param hours added to studyHours
    */
    public void study(int hours) {
        this.studyHours = studyHours + hours;
    }
    /**
    *adds hours to a student's non-Study hours
    *@param hours added to nonStudyHours
    */
    public void relax(int hours) {
        this.nonStudyHours = nonStudyHours + hours;
    }
    /**
    *creates a student object
    * @param first first name of student
    * @param last last name of student
    * @param user username of student
    */
    Student(String first, String last, String user) {
        super(first, last, user);
    }
}
