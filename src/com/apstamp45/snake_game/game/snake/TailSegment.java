package com.apstamp45.snake_game.game.snake;

/**
 * Used for the Snake class as 
 * the snake's tail.
 * @author apstamp45
 */
public class TailSegment extends Segment{

    /** The next segment. Will be used 
     * when moving the tail.
     */
    private Segment next;

    /**
     * Creates a tail segment.
     * @param next The next segment of the snake.
     */
    public TailSegment(Segment next) {
        this.next = next;
    }

    /**
     * Moves the tail segment to the next 
     * segment's coordinates.
     */
    public void move() {
        this.x = next.x;
        this.y = next.y;
    }
}
