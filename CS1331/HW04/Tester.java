import java.util.Arrays;
/**
* Driver class to test all classes
* @author Hussain Mumtaz
* @version 1.00
*/
public class Tester {
    /**
    *tests all classes
    *@param args none
    */
    public static void main(String[] args) {
        Person bob = new Person("Bob", "Smith", "rsmith0");
        System.out.println(bob.toString());
        System.out.println("");

        Professor simpkins = new Professor("Chris", "Simpkins", "simpkins3", 3
            , 1);
        System.out.println(simpkins.toString());

        System.out.println(simpkins.getGPA());
        simpkins.setGPA(4.2);
        System.out.println(simpkins.getGPA());
        simpkins.setGPA(-0.2);
        System.out.println(simpkins.getGPA());

        System.out.println(simpkins.getFunnyFactor());
        simpkins.setFunnyFactor(3);
        System.out.println(simpkins.getFunnyFactor());
        simpkins.setFunnyFactor(-1);
        System.out.println(simpkins.getFunnyFactor());
        System.out.println("");

        Student bobby = new Student("Bobby", "Smith", "rsmith1");
        System.out.println(bobby.toString());

        bobby.study(5);
        System.out.println(bobby.getStudyHours());

        bobby.relax(5);
        System.out.println(bobby.getNonStudyHours());

        System.out.println(bobby.getStudyPercentage());
        System.out.println("");

        GradStudent robert = new GradStudent("Robert", "Smith", "rsmith2");
        System.out.println(robert.toString());

        robert.relax(5);
        System.out.println(robert.getNonStudyHours());
        System.out.println("");

        UndergradStudent robbie = new UndergradStudent("Robbie", "Smith",
            "rsmith3");
        System.out.println(robbie.toString());

        robbie.loseHope(10);
        System.out.println(robbie.getHope());
        robbie.loseHope(-2);
        System.out.println(robbie.getHope());
        robbie.loseHope(92);
        System.out.println(robbie.getHope());

        robbie.eatPizza(2);
        System.out.println(robbie.getPizza());
        robbie.eatPizza(-2);
        System.out.println(robbie.getPizza());
        System.out.println("");

        TA rob = new TA("Rob", "Smith", "rsmith5", 0.5, 0.5, 0.5);
        System.out.println(rob.toString());

        System.out.println(rob.getPiazzaScore());
        rob.setPiazzaScore(-.1);
        System.out.println(rob.getPiazzaScore());
        rob.setPiazzaScore(1.2);
        System.out.println(rob.getPiazzaScore());

        System.out.println(rob.getRecitationScore());
        rob.setRecitationScore(-.1);
        System.out.println(rob.getRecitationScore());
        rob.setRecitationScore(1.2);
        System.out.println(rob.getRecitationScore());

        System.out.println(rob.getOfficeHoursScore());
        rob.setOfficeHoursScore(-.1);
        System.out.println(rob.getOfficeHoursScore());
        rob.setOfficeHoursScore(1.2);
        System.out.println(rob.getOfficeHoursScore());
        System.out.println("");

        Course cs = new Course("CS1331", simpkins, rob);
        System.out.println(cs.getTitle());
        System.out.println(cs.getProfessor());
        System.out.println(cs.getHeadTA());
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        cs.addStudent(bobby);
        cs.addStudent(robert);
        cs.addStudent(robbie);
        System.out.println(Arrays.toString(cs.getStudents()));
        System.out.println("");

        Student[] studentsTestArray = {bobby, robert, robbie, bobby,
            robert, robbie, bobby, robert, robbie, bobby, robert, robbie,
            bobby, robert, robbie, bobby, robert, robbie, bobby, robert,
            robbie, bobby, robert, robbie, bobby, robert, robbie, bobby,
            robert, robbie, bobby, robert, robbie, bobby, robert, robbie,
            bobby, robert, robbie, bobby, robert, robbie, bobby, robert,
            robbie, bobby, robert, robbie, bobby, robert, robbie, bobby,
            robert, robbie, bobby, robert, robbie, bobby, robert, robbie,
            bobby, robert, robbie, bobby, robert, robbie, bobby, robert,
            robbie, bobby, robert, robbie, bobby, robert, robbie, bobby,
            robert, robbie, bobby, robert, robbie, bobby, robert, robbie,
            bobby, robert, robbie, bobby, robert, robbie, bobby, robert,
            robbie, bobby, robert, robbie, bobby, robert, robbie, bobby,
            robert, robbie, bobby, robert, robbie, bobby, robert, robbie,
            bobby, robert, robbie, bobby, robert, robbie, bobby, robert,
            robbie, bobby, robert, robbie, bobby, robert, robbie, bobby,
            robert, robbie, bobby, robert, robbie, bobby, robert, robbie,
            bobby, robert, robbie, bobby, robert, robbie, bobby, robert,
            robbie, bobby, robert, robbie, bobby, robert, robbie, bobby,
            robert, robbie, bobby, robert, robbie, bobby, robert, robbie,
            bobby, robert, robbie, bobby, robert, robbie, bobby, robert,
            robbie, bobby, robert, robbie, bobby, robert, robbie, bobby,
            robert, robbie, bobby, robert, robbie, bobby, robert, robbie,
            bobby, robert, robbie, bobby, robert, robbie, bobby, robert,
            robbie, bobby, robert, robbie, bobby, robert, robbie, bobby,
            robert, robbie, bobby, robert, robbie, bobby, robert, robbie,
            bobby, robert, robbie, bobby, robert, robbie, bobby, robert,
            robbie, bobby, robert, robbie, bobby, robert, robbie, bobby,
            robert, robbie, bobby, robert, robbie, bobby, robert, robbie,
            bobby, robert, robbie, bobby, robert, robbie, bobby, robert,
            robbie, bobby, robert, robbie, bobby, robert, robbie, bobby,
            robert, robbie, bobby, robert, robbie, bobby, robert, robbie,
            bobby, robert, robbie, bobby, robert, robbie, bobby, robert,
            robbie, bobby, robert, robbie, bobby, robert, robbie, bobby,
            robert, robbie, bobby, robert, robbie, bobby, robert, robbie,
            bobby, robert, robbie, bobby, robert, robbie, bobby, robert,
            robbie, bobby, robert, robbie, bobby, robert, robbie, bobby,
            robert, robbie, bobby, robert, robbie, bobby, robert, robbie,
            bobby, robert, robbie, bobby, robert, robbie, bobby, robert,
            robbie, bobby, robert, robbie, bobby, robert, robbie, bobby,
            robert, robbie, bobby, robert, robbie, bobby, robert, robbie,
            bobby, robert, robbie, bobby, robert, robbie, bobby, robert,
            robbie, bobby, robert, robbie, bobby, robert, robbie, bobby,
            robert, robbie, bobby, robert, robbie, bobby, robert, robbie,
            bobby, robert, robbie, bobby, robert, robbie, bobby, robert,
            robbie, bobby, robert, robbie, bobby, robert, robbie, bobby,
            robert, robbie, bobby, robert, robbie, bobby, robert, robbie,
            bobby, robert, robbie, bobby, robert, robbie, bobby, robert,
            robbie, bobby, robert, robbie, bobby, robert, robbie, bobby,
            robert, robbie, bobby, robert, robbie, bobby, robert, robbie,
            bobby, robert, robbie, bobby, robert, robbie, bobby, robert,
            robbie, bobby, robert, robbie, bobby, robert, robbie, bobby,
            robert, robbie, bobby, robert, robbie, bobby, robert, robbie,
            bobby, robert, robbie, bobby, robert, robbie, bobby, robert,
            robbie, bobby, robert, robbie, bobby, robert, robbie, bobby,
            robert, robbie, bobby, robert, robbie, bobby, robert, robbie,
            bobby, robert, robbie, bobby, robert, robbie, bobby, robert,
            robbie, bobby, robert, robbie, bobby, robert, robbie, bobby,
            robert, robbie, bobby, robert, robbie, bobby, robert, robbie,
            bobby, robert, robbie, bobby, robert, robbie, bobby, robert,
            robbie, bobby, robert, robbie, bobby, robert, robbie, bobby,
            robert, robbie, bobby, robert, robbie, bobby, robert, robbie,
            bobby, robert, robbie, bobby, robert, robbie, bobby, robert,
            robbie, bobby, robert, robbie, bobby, robert, robbie, bobby,
            robert, robbie, bobby, robert, robbie, bobby, robert, robbie,
            bobby, robert, robbie, bobby, robert, robbie, bobby, robert,
            robbie, bobby, robert, robbie, bobby, robert, robbie, bobby,
            robert, robbie, bobby, robert, robbie, bobby, robert, robbie,
            bobby, robert, robbie, bobby, robert, robbie, bobby, robert,
            robbie, bobby, robert, robbie, bobby, robert, robbie, bobby,
            robert, robbie, bobby, robert, robbie, bobby, robert, robbie,
            bobby, robert, robbie, bobby, robert, robbie, bobby, robert,
            robbie, bobby, robert, robbie, bobby, robert, robbie, bobby,
            robert, robbie, bobby, robert, robbie, bobby, robert, robbie,
            bobby, robert, robbie, bobby, robert, robbie, bobby, robert,
            robbie, bobby, robert, robbie, bobby, robert, robbie, bobby,
            robert, robbie, bobby, robert, robbie, bobby, robert, robbie};
        Course cs2 = new Course("CS1331", simpkins, rob, studentsTestArray);
        System.out.println(Arrays.toString(cs2.getStudents()));
        System.out.println("");
    }
}