import java.util.Scanner;
import java.io.File;
import java.io.PrintStream;
import java.io.FileNotFoundException;

public class GpaCalc {
    public static void main(String[] args) throws Exception {
        processInput();
    }

    public static void processInput() throws FileNotFoundException {
        Scanner keyboard = new Scanner(System.in);
        String semester = "";
        String outFileName = "";
        PrintStream outfile;
        String anotherCourse = "y";
        String anotherSemester = "y";
        String course = "";
        double credits = 0;
        double grade = 0;
        double totalCredits = 0;
        double qualityPoints = 0;
        double gpa = 0;

        System.out.println("Enter the Semester:");
        semester = keyboard.nextLine();

        outFileName = semester.toLowerCase() + ".txt";
        outFileName = outFileName.replaceAll(" ", "");
        outfile = new PrintStream(new File(outFileName));

        while (anotherCourse.equals("y")) {

            System.out.println("Enter the Course:");
            course = keyboard.nextLine();

            System.out.println("Enter the number of credits:");
            credits = keyboard.nextDouble();

            System.out.println("Enter the grade (A=4, B=3. etc.)");
            grade = keyboard.nextDouble();

            outfile.println(course + " - " + credits + " credits. Grade: "
                    + grade);
            keyboard.nextLine();

            totalCredits = totalCredits + credits;
            qualityPoints = qualityPoints + (grade * credits);

            System.out.println("Would you like to enter another course?"
                    + "(y/n)");
            anotherCourse = keyboard.nextLine();

        }
        if (anotherCourse.equals("n")) {
            gpa = (qualityPoints / totalCredits);
            outfile.printf("GPA: %3.2f\n" , gpa);
	    System.out.printf("Overall GPA: %3.2f\n", gpa); 

            System.out.println("Would you like to enter another semester?"
                    + "(y/n)");
            anotherSemester = keyboard.nextLine();

            if (anotherSemester.equals("n")) {
                return;
            } else if (anotherSemester.equals("y")) {
                totalCredits = 0;
                qualityPoints = 0;
                processInput();
            }
        }
    }
}
