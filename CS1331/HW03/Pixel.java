/* Put your Pixel class here */

/**
* This class represents a Pixel object.
* @author Hussain Mumtaz
* @version 1.00
*/

public class Pixel {
    private int red;
    private int green;
    private int blue;
    private int alpha;

    /**
    * Takes color/alpha values and sets them to replace set color/alpha 
    * values
    * @param red given red value
    * @param green given green value
    * @param blue given blue value
    * @param alpha given alpha value
    */
    Pixel(int red, int green, int blue, int alpha) {
        setRed(red);
        setGreen(green);
        setBlue(blue);
        setAlpha(alpha);
    }
    /**
    * Gets and returns the red value of a given pixel
    * @return red
    */
    public int getRed() {
        return red;
    }
    /**
    * Gets and returns the green value of a given pixel
    * @return green
    */
    public int getGreen() {
        return green;
    }
    /**
    * Gets and returns the blue value of a given pixel
    * @return blue
    */
    public int getBlue() {
        return blue;
    }
    /**
    * Gets and returns the alpha value of a given pixel
    * @return alpha
    */
    public int getAlpha() {
        return alpha;
    }
    /**
    * Sets the red value of a given pixel
    * @param red value to set
    */
    public void setRed(int red) {
        if (red <= 0) {
            this.red = 0;
        } else if (red >= 255) {
            this.red = 255;
        } else {
            this.red = red;
        }
    }
    /**
    * Sets the green value of a given pixel
    * @param green value to set
    */
    public void setGreen(int green) {
        if (green <= 0) {
            this.green = 0;
        } else if (green >= 255) {
            this.green = 255;
        } else {
            this.green = green;
        }
    }
    /**
    * Sets the blue value of a given pixel
    * @param blue value to set
    */
    public void setBlue(int blue) {
        if (blue <= 0) {
            this.blue = 0;
        } else if (blue >= 255) {
            this.blue = 255;
        } else {
            this.blue = blue;
        }
    }
    /**
    * Sets the alpha value of a given pixel
    * @param alpha value to set
    */
    public void setAlpha(int alpha) {
        if (alpha <= 0) {
            this.alpha = 0;
        } else if (alpha >= 255) {
            this.alpha = 255;
        } else {
            this.alpha = alpha;
        }
    }
}