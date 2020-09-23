package com.apstamp45.snake_game.graphics;

/**
 * This class represents a pixel.
 * @author apstamp45
 */
public class Pixel {

    /** The r value of the pixel. */
    private int r;

    /** The g value of the pixel. */
    private int g;

    /** The b value of the pixel. */
    private int b;

    /** The pixel's transparency status. */
    private boolean isTransparent;

    /**
     * Creates a Pixel.
     * @param r The pixel's r value.
     * @param g The pixel's g value.
     * @param b The pixel's b value.
     * @param isTransparent The pixel's transparency status.
     */
    public Pixel(int r, int g, int b, boolean isTransparent) {
        int[] rgb = {r, g, b};
        for (int i = 0; i < 3; i++) {
            if (rgb[i] > 255) {
                rgb[i] = 255;
            }
            if (rgb[i] < 0) {
                rgb[i] = 0;
            }
        }
        this.r = rgb[0];
        this.g = rgb[1];
        this.b = rgb[2];
        this.isTransparent = isTransparent;
    }

    /**
     * Returns the r value of this pixel.
     * @return The r value.
     */
    public int r() {
        return r;
    }

    /**
     * Returns the g value of this pixel.
     * @return The g value.
     */
    public int g() {
        return g;
    }

    /**
     * Returns the b value of this pixel.
     * @return The b value.
     */
    public int b() {
        return b;
    }

    /** 
     * Returns wether or not the pixel 
     * is transparent.
     * @return The pixel's transparent status.
     */
    public boolean isTransparent() {
        return isTransparent;
    }
}
