package com.apstamp45.snake_game.game.snake;

/**
 * Represents the snake's head.
 * @author apstamp45
 */
public class Head extends Segment {

    /** The head's x speed. */
    public int xSpeed;

    /** The head's y speed. */
    public int ySpeed;

    /**
     * Creates a snake's head.
     * @param x The head's x coordinate.
     * @param y The head's y coordinate.
     * @param xSpeed The head's x speed.
     * @param ySpeed the head's y speed.
     */
    public Head(int x, int y, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    /**
     * Moves the snake's head.
     * @param canvasWidth
     * @param canvasHeight
     */
    public void move(int canvasWidth, int canvasHeight) {
        x += xSpeed;
        y += ySpeed;
        if (x < 0) {
            x = canvasWidth - 1;
        } else if (x >= canvasWidth) {
            x = 0;
        }
        if (y < 0) {
            y = canvasHeight - 1;
        } else if (y >= canvasHeight) {
            y = 0;
        }
    }
}
