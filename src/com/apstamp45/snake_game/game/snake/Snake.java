package com.apstamp45.snake_game.game.snake;

import java.util.ArrayList;

import com.apstamp45.snake_game.Main;
import com.apstamp45.snake_game.graphics.Pixel;

/**
 * Represents the snake in the snake game.
 * @author apstamp45
 */
public class Snake {
    public enum Direction {UP, DOWN, RIGHT, LEFT};

    /** The snake's color. */
    private Pixel color;

    /** The snake's head. */
    private Head head;

    /** The snake's tail. */
    private ArrayList<TailSegment> tail;
    
    /**
     * Creates a snake.
     * @param x The snake head's x coordinate.
     * @param y The snake head's y coordinate.
     * @param xSpeed The snake's head's x speed.
     * @param ySpeed The snake's head's y speed.
     * @param tailLength the length of the tail
     * to be added to the snake.
     * @param color The snake's color.
     */
    public Snake(int x, int y, int xSpeed, int ySpeed, int tailLength, Pixel color) {
        this.color = color;
        tail = new ArrayList<>();
        head = new Head(x, y, xSpeed, ySpeed);
        for (int i = 0; i < tailLength; i++) {
            addSegment();
            move();
        }
    }

	/** Adds a segment to the snake's tail. */
    public void addSegment() {
        if (tail.size() == 0) {
            tail.add(new TailSegment(head));
        } else {
            tail.add(new TailSegment(tail.get(tail.size() - 1)));
        }
    }

    /** Draws the snake. */
    public void draw() {
        Main.setPixel(head.x, head.y, color);
        for (TailSegment tailSegment: tail) {
            Main.setPixel(tailSegment.x, tailSegment.y, color);
        }
    }

    /**
     * Gets the snake's head's direction.
     * @return The snake's head's direction.
     */
    public Snake.Direction getDirection() {
        if (head.xSpeed == 0 && head.ySpeed == -1) {
            return Direction.UP;
        } else if (head.xSpeed == 0 && head.ySpeed == 1) {
            return Direction.DOWN;
        } else if (head.xSpeed == 1 && head.ySpeed == 0) {
            return Direction.RIGHT;
        } else if (head.xSpeed == -1 && head.ySpeed == 0) {
            return Direction.LEFT;
        } else {
            System.out.println("The snake's head's direction isn't valid.");
            System.exit(1);
        }
        return null;
    }

    /** Moves the snake. */
    public void move() {
        for (int i = tail.size() - 1; i >= 0; i--) {
            tail.get(i).move();
        }
        head.move(Main.PIXEL_ARRAY_WIDTH, Main.PIXEL_ARRAY_HEIGHT);
    }

    /**
     * Sets the snake's head's position.
     * @param xSpeed The x speed.
     * @param ySpeed The y speed.
     */
    public void setDirection(Snake.Direction direction) {
        if (direction == Snake.Direction.UP) {
            head.xSpeed = 0;
            head.ySpeed = -1;
        } else if (direction == Snake.Direction.DOWN) {
            head.xSpeed = 0;
            head.ySpeed = 1;
        } else if (direction == Snake.Direction.RIGHT) {
            head.xSpeed = 1;
            head.ySpeed = 0;
        } else if (direction == Snake.Direction.LEFT) {
            head.xSpeed = -1;
            head.ySpeed = 0;
        }
    }
}
