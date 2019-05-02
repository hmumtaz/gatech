import java.io.IOException;
/**
* Driver class to test methods created in ImageProcessor.java
* @author Hussain Mumtaz
* @version 1.00
*/
public class Photoslop {
    /**
    * Tests ImageProcessor's method
    * @param args args[0] represents the called method from ImageProcessor 
    * args[1] represents the basepic
    * args[2] represents an increment, scalar, or background depending on
    * args[0]
    * args[3] represents the name of a file to save to
    * @throws IOException so the class compiles
    */
    public static void main(String... args) throws IOException {
        if (args.length < 4) {
            printUsage();
            System.exit(0);
        }
        try {
            //your code here
            //hint the base pic for use with the image processor is in args[1]
            Pic apic = new Pic(args[1]);
            ImageProcessor img = new ImageProcessor(apic);
            if (args[0].equals("-a")) {
                //add mode
                //your code here
                //add args[2] to the picture, then call
                //Save to args[3]
                int num = Integer.parseInt(args[2]);
                Pic added = img.add(num);
                added.save(args[3]);

            } else if (args[0].equals("-m")) {
                //same as add, but multiply
                double num = Double.parseDouble(args[2]);
                Pic multiplied = img.multiply(num);
                multiplied.save(args[3]); 
            } else if (args[0].equals("-c")) {
                //use this as your chroma key to match the greenscreen in the lego guy image
                //Your code here to apply the chroma key transformation
                //It's up to you what you want the rgb deltas (thresholds) to be,
                //I used something like 20, 40, 20
                Pixel chromaKey = new Pixel(26, 185, 19, 1);
                Pic chromed = img.chroma(chromaKey,20,40,20);
                //Hint: to do this part, consider making another ImageProcessor
                //Your code here to apply the background (args[2])
                ImageProcessor newImg = new ImageProcessor(chromed);
                Pic pic = new Pic(args[2]);
                Pic greenscreen = newImg.background(pic);
                greenscreen.save(args[3]);
                //Finally, save to args[3]
            } else {
                printUsage();
            }
        } catch (IOException ex) {
            System.out.println("One of the files you referenced does not exist,"
                + " or is corrupted. Double-check and try again.");
        }
    }
    /**
    *Prints how to use Photoslop.java if enough args are not provided.
    */
    private static void printUsage() {
        System.out.println("Add mode: ");
        System.out.println("java -a imageFile n outputFile");
        System.out.println("");
        System.out.println("Multiply mode: ");
        System.out.println("java -m imageFile n outputFile");
        System.out.println("");
        System.out.println("Greenscreen mode: ");
        System.out.println("java -c imageFile backgroundFile outputFile");
    }
}
