/**
 * This class represents a pixel on a canvas.
 * 
 * @author apstamp45
 * @version 1.1
 */
public class Pixel {
	
	/**
	 * This field represents one of the rgb values of the pixel.
	 */
	public int r = 0;
	
	/** @see r */
	public int g = 0;
	
	/** @see r */
	public int b = 0;

	/**
	 * Simple constructor that sets the
	 * rgb values.
	 * 
	 * @param r the r value.
	 * @param g the g value.
	 * @param b the b value.
	 */
	public Pixel (int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
}