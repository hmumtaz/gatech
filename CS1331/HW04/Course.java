/**
*This class represents a Course object
* @author Hussain Mumtaz
* @version 1.00
*/
public class Course {
    protected String title;
    protected Professor professor;
    protected TA headTA;
    protected TA[] tas = new TA[15];
    protected Student[] students = new Student[0];
    /**
    *gets and returns course title
    *@return title
    */
    public String getTitle() {
        return title;
    }
    /**
    *gets and returns course professor
    *@return professor
    */
    public Professor getProfessor() {
        return professor;
    }
    /**
    *gets and returns course headTA
    *@return headTA
    */
    public TA getHeadTA() {
        return headTA;
    }
    /**
    *gets and returns course tas
    *@return tas
    */
    public TA[] getTAs() {
        return tas;
    }
    /**
    *gets and returns course students
    *@return students
    */
    public Student[] getStudents() {
        return students;
    }
    /**
    *gets and returns course students averageStudyPercentage
    *@return averageStudyPercentage
    */
    public double getAverageStudyPercentage() {
        int x = 0;
        double totalStudyPercentage = 0;
        while (x < students.length) {
            totalStudyPercentage = totalStudyPercentage
                + students[x].getStudyPercentage();
            x = x + 1;
        }
        double averageStudyPercentage = totalStudyPercentage / students.length;
        return averageStudyPercentage;
    }
    /**
    *adds a student to students bounded [0,300]
    *@param s student to be added
    */
    public void addStudent(Student s) {
        if (students.length != 300) {
            Student[] temp = students.clone();
            students = new Student[students.length + 1];
            for (int x = 0; x < temp.length; x = x + 1) {
                students[x] = temp[x];
            }
            students[students.length - 1] = s;
        }
    }
    /**
    *creates a course object
    *@param title course title
    *@param professor course professor
    *@param headTA course headTA
    */
    Course(String title, Professor professor, TA headTA) {
        this.title = title;
        this.professor = professor;
        this.headTA = headTA;
    }
    /**
    *creates a course object
    *@param title course title
    *@param professor course professor
    *@param headTA course headTA
    *@param aStudents an array of course students bounded [0,300]
    */
    Course(String title, Professor professor, TA headTA, Student[] aStudents) {
        this.title = title;
        this.professor = professor;
        this.headTA = headTA;
        if (aStudents.length <= 300) {
            this.students = aStudents;
        } else if (aStudents.length > 300) {
            int x = 0;
            students = new Student[300];
            while (x < 300) {
                students[x] = aStudents[x];
                x = x + 1;
            }
        }
    }

}
