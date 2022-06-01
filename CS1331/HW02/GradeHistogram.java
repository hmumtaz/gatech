import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class GradeHistogram {
    static final int MAX_NUM_BINS = 101;

    public static void main(String[] args) throws FileNotFoundException {
        String fileName;
        try {
            fileName = args[0];
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("ERROR: File Name must be specified");
            return;
        }

        int num_bins;
        try {
            num_bins = Integer.parseInt(args[1]);
        } catch (ArrayIndexOutOfBoundsException ex) {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("An integer must be specified for number of bins to create. Please specify an integer:");
            try {
                num_bins = keyboard.nextInt();
                keyboard.close();
            } catch (InputMismatchException e) {
                System.out.println("ERROR: An integer value was not provided");
                return;
            }

        }

        StringBuilder[] bins = createHistogram(fileName, num_bins);

        for (int i = bins.length - 1; i >= 0; i--) {
            System.out.println(bins[i].toString());
        }
    }

    public static StringBuilder[] createHistogram(String file_name, int num_bins)
            throws FileNotFoundException {
        if (num_bins > MAX_NUM_BINS || num_bins < 1) {
            throw new IndexOutOfBoundsException(
                    "You cannot have more than one-hundred or less than one bin");
        }
        int bin_size = MAX_NUM_BINS / num_bins;
        int extras = MAX_NUM_BINS % num_bins;
        StringBuilder[] bins = createBins(num_bins, bin_size, extras);

        Scanner grade_file = new Scanner(new File(file_name));
        while (grade_file.hasNext()) {
            String line = grade_file.nextLine();
            int grade = Integer.parseInt(line.split(",")[1].strip());

            int bin_for_grade;
            if (grade < extras + bin_size) {
                bin_for_grade = 0;
            } else {
                bin_for_grade = ((grade - extras) / bin_size);
            }
            bins[bin_for_grade].append("[]");
        }

        return bins;
    }

    public static StringBuilder[] createBins(int num_bins, int bin_size, int extras) {
        int start = 0;
        int end = bin_size - 1 + extras;
        StringBuilder[] bins = new StringBuilder[num_bins];
        for (int i = 0; i < num_bins; i++) {
            bins[i] = new StringBuilder()
                    .append(String.format("%3s", Integer.toString(end)))
                    .append(" - ")
                    .append(String.format("%3s", Integer.toString(start)))
                    .append(" | ");
            start = end + 1;
            end += bin_size;
        }
        return bins;
    }
}
