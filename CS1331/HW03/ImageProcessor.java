/* Put your ImageProcessor class here */
/**
* This class represents an ImageProcessor object.
* @author Hussain Mumtaz
* @version 1.00
*/
public class ImageProcessor {

    public Pic apic;
    /**
    * Takes a Pic object and sets it to the apic variable in
    * the ImageProcessor class.
    * @param apic instatiated Pic object
    */
    ImageProcessor(Pic apic) {
    	this.apic = apic;
    }
    /**
    * Creates a new Pic with incremented RGBA values
    * @param increment represents the value added to RGBA values
    * @return addPic
    */
    public Pic add(int increment) {
    	Pic addPic = apic.deepCopy();
    	for (int x = 0; x < addPic.getWidth(); x = x + 1) {
    		for (int y = 0; y < addPic.getHeight(); y = y + 1) {
    			Pixel add = addPic.getPixel(x,y);
        		add.setRed(add.getRed() + increment);
        		add.setGreen(add.getGreen() + increment);
        		add.setBlue(add.getBlue() + increment);  			
    		}
    	}
    	return addPic; 
    }
    /**
    * Creates a new Pic with scaled RGBA values
    * @param scale represents the scale multiplied with RGBA values
    * @return multPic
    */
    public Pic multiply(double scale) {
    	Pic multPic = apic.deepCopy();
    	for (int x = 0; x < multPic.getWidth(); x = x + 1) {
    		for (int y = 0; y < multPic.getHeight(); y = y + 1) {
    			Pixel multiply = multPic.getPixel(x,y);
				double scaleR = multiply.getRed() * scale;
        		int red = (int) scaleR;
        		multiply.setRed(red);
        		double scaleG = multiply.getGreen() * scale;
        		int green = (int) scaleG;
        		multiply.setGreen(green);
        		double scaleB = multiply.getBlue() * scale;
        		int blue = (int) scaleB;
        		multiply.setBlue(blue);
        		double scaleA = multiply.getAlpha() * scale;
        		int alpha = (int) scaleA;
        		multiply.setAlpha(alpha);   			
    		}
    	}
    	return multPic; 
    }
    /**
    * Creates a new Pic with chromed RGBA values from a key value and
    * RGB thresholds
    * @param key represents a given pixel value
    * @param dr represents a red threshold
    * @param dg represents a green threshold
    * @param db represents a blue threshold
    * @return chrPic
    */
    public Pic chroma(Pixel key, int dr, int dg, int db) {
    	    Pic chrPic = apic.deepCopy();
    	    for (int x = 0; x < chrPic.getWidth(); x = x + 1) {
    		for (int y = 0; y < chrPic.getHeight(); y = y + 1) {
    			Pixel chroma = chrPic.getPixel(x,y);
        		if ((Math.abs(chroma.getRed() - key.getRed()) < dr) && (Math.abs(chroma.getGreen() - 
                    key.getGreen()) < dg) && (Math.abs(chroma.getBlue() - key.getBlue()) < db)) {
            		chroma.setAlpha(0);
            		}  			
    		}
    	}
    	return chrPic; 
    }
    /**
    * Substitutes an image's background with a 0 alpha value
    * with a given background
    * @param bg represents the given background
    * @return forePic
    */
    public Pic background(Pic bg) {
        Pic forePic = apic.deepCopy();
    	for (int x = 0; x < forePic.getWidth(); x = x + 1) {
    		for (int y = 0; y < forePic.getHeight(); y = y + 1) {
    			Pixel foreground = forePic.getPixel(x,y);
        		if (foreground.getAlpha() == 0) {
        			Pixel bgPix = bg.getPixel(x,y);
        			foreground.setRed(bgPix.getRed());
        			foreground.setGreen(bgPix.getGreen());
        			foreground.setBlue(bgPix.getBlue());
        			foreground.setAlpha(bgPix.getAlpha());
        		}
    		}
    	}
    	return forePic; 
    }

}