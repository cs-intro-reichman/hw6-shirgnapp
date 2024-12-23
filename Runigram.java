import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		Color[][] tinypic = read("tinypic.ppm");
		print(tinypic);

		// Creates an image which will be the result of various 
		// image processing operations:
		Color[][] image;
		image = flippedHorizontally(tinypic);
		// Tests the horizontal flipping of an image:

		System.out.println();
		print(image);
		
		print(image);
		
		//// Write here whatever code you need in order to test your work.
		//// You can continue using the image array.
	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		Color[][] ImageColors = new Color[numRows][numCols];
		for (int i = 0; i < numRows;i++){
			for (int j = 0; j < numCols;j++){
				int r = in.readInt();
				int g = in.readInt();
				int b = in.readInt();
				ImageColors[i][j] = new Color (r,g,b);
			}
		}
		// Reads the RGB values from the file into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		//// Replace the following statement with your code.
		return ImageColors;
	}

    // Prints the RGB values of a given color.
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	private static void print(Color[][] image) {
		for(int i =0; i<image.length; i++){
			for(int j=0; j<image[0].length; j++){
				print(image[i][j]);
			}
			System.out.println("");
		}
		//// Notice that all you have to so is print every element (i,j) of the array using the print(Color) function.
	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		Color[][] flippedHorizontally = new Color[image.length][image[0].length];
		for(int i=0; i<image.length; i++){
			int column = (image[0].length) - 1;
			for(int j=0; j<(image[0].length)/2; j++){
				Color c = image[i][j];	
				flippedHorizontally[i][j] = image[i][column];
				flippedHorizontally[i][column] = c;
				column --;
			}
		}
		return flippedHorizontally;
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		Color[][] flippedVertically = new Color[image.length][image[0].length];
		int rows = flippedVertically.length;
		int column = 0;
		for(int i=0; i<(flippedVertically.length)/2; i++){
				Color c = flippedVertically[i][column];	
				flippedVertically[i][column] = flippedVertically[rows][column];
				flippedVertically[rows][column] = c;
				rows --;
			}
		return flippedVertically;
	}
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	/**
 * Computes the luminance of the RGB values of the given pixel.
 * The formula used is: lum = 0.299 * red + 0.587 * green + 0.114 * blue.
 * The result is returned as a Color object where all RGB values are set to the luminance.
 * 
 * @param pixel The input Color object.
 * @return A new Color object in grayscale based on the luminance value.
 */
private static Color luminance(Color pixel) {
    int lum = (int) (pixel.getRed() * 0.299 + pixel.getGreen() * 0.587 + pixel.getBlue() * 0.114);
    return new Color(lum, lum, lum);
}

	private static Color luminance1(Color pixel) {
		int r = (int)(pixel.getRed() * 0.299);
		int b = (int)(pixel.getBlue() * 0.114);
		int g = (int)(pixel.getGreen()*0.587);
		Color newColor = new Color(r, g, b);
		return newColor;
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */

	 public static Color[][] grayScaled(Color[][] image) {
		
		Color[][] grayScaled = new Color[image.length][image[0].length];
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[0].length; j++) {
				if (image[i][j] != null) {
					grayScaled[i][j] = luminance(image[i][j]);
				} else {
					grayScaled[i][j] = new Color(0, 0, 0);
				}
			}
		}
	
		return grayScaled;
	}
	
	public static Color[][] grayScaled1(Color[][] image) {
		Color[][] grayScaled = new Color[image.length][image[0].length];
		for(int i=0; i<image.length; i++){
			for(int j=0; j<image[0].length; j++){
				if(grayScaled[i][j] != null){
				grayScaled[i][j] = luminance(image[i][j]);
				}
			}
		}
		return grayScaled;
	}	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		int h0 = image.length;       
		int w0 = image[0].length;    
		double scaleY = (double) h0 / height;  
		double scaleX = (double) w0 / width;  
	
		Color[][] scaledImage = new Color[height][width];
	
		for (int i = 0; i < height; i++) {         
			for (int j = 0; j < width; j++) {      
				int originalY = Math.min((int) (i * scaleY), h0 - 1); 
				int originalX = Math.min((int) (j * scaleX), w0 - 1); 
				scaledImage[i][j] = image[originalY][originalX];      
			}
		}
	
		return scaledImage; 
	}
	

	public static Color[][] scaled1(Color[][] image, int width, int height) {
		double h0 = image.length;
		double w0= image[0].length;
		double scaleX = h0 / height;
		double scaleY= w0 / width;
		Color[][] scaled = new Color[height][width];
		for(int i=0; i<image.length; i++){
			for(int j=0; j< image[0].length; j++){
				int index = (int)(i * scaleX);
				int index2 = (int)(j * scaleY);
				scaled[i][j] = image[index][index2];
			}
		}
		return scaled;
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		int r1 = (int)(c1.getRed()), b1 = (int)(c1.getBlue()), g1 = (int)(c1.getGreen());
		int r2 = (int)(c2.getRed()) , b2 = (int)(c2.getBlue()), g2 = (int)(c2.getGreen());
		int newr, newb, newg; 
		newb = (int)(alpha * b1 + (1- alpha) * b2);
		newg = (int)(alpha * g1 + (1- alpha) * g2);
		newr = (int)(alpha * r1 + (1- alpha) * r2);
		Color blend = new Color(newr, newg, newb);
		return blend;
	}
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		Color[][] blend = new Color[image1.length][image1[0].length];
		for(int i=0; i<blend.length; i++){
			for(int j=0; j<blend[0].length; j++){
				blend[i][j] = blend(image1[i][j], image2[i][j], alpha);
			}
		}
		return blend;
		
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		Runigram.setCanvas(source);
			Runigram.display(source);
			StdDraw.pause(3000); 
		if (source[0].length != target[0].length || source.length != target.length) {
			scaled(source, target[0].length, target.length);
		}
		double limit = 1;
		double fraction = limit / n; 
		while (fraction < 1) {	
			Runigram.setCanvas(blend(source, target, fraction));
			Runigram.display(blend(source, target, fraction));
			StdDraw.pause(3000); 
			limit++;
			fraction = limit / n; 
		}
	}
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

