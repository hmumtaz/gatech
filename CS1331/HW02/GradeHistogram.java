import java.util.Scanner;
import java.io.File;

public class GradeHistogram {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(new File(args[0]));
        int bucket;
        try {
            bucket = Integer.parseInt(args[1]);
        } catch (IndexOutOfBoundsException e) {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("What bucket size would you like?");
            bucket = keyboard.nextInt();
        }

        String fullText = "";
        while (scan.hasNextLine()) {
            fullText = fullText + "   " + scan.nextLine();
        }

        String[] lines = fullText.split("   ");

        String[] sep;
        String grade = "";
        int[] grades = new int[lines.length - 1];

        //Starting index with 1 because lines[0] is blank
        int i = 1;
        while (i < lines.length) {
            sep = lines[i].split(",");
            grade = sep[1];
            grades[i - 1] = Integer.parseInt(grade.trim());
            i = i + 1;
        }

        String brackets = "";
        int decrement = 100;
        while (decrement > 0) {
            for (int j = 0; j < grades.length; j = j + 1) {
                if (grades[j] <= decrement && grades[j] >= (decrement
                    - bucket)) {
                    brackets = brackets + "[]";
                }
            }
            decrement = decrement - bucket;
            if (decrement < 0) {
                System.out.printf("%-3d - %-3d  | %-1s \n",
                    0, (decrement + bucket), brackets);
                brackets = "";
            } else {
                System.out.printf("%-3d - %-3d  | %-1s \n",
                    (decrement + bucket), decrement, brackets);
                brackets = "";
            }
        }
    }
}
