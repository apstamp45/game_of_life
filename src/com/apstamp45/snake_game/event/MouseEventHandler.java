package com.apstamp45.snake_game.event;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * This class handles mouse events.
 * @author apstamp45
 */
public class MouseEventHandler implements EventHandler<MouseEvent> {

    /** The mouse's x position. */
    public static int mouseX = 0;

    /** The mouse's y position. */
    public static int mouseY = 0;

    /**
     * Will be called when the mouse 
     * is pushed down.
     */
    private static Runnable onMouseDown;

    /**
     * Will be called when the mouse 
     * is moved.
     */
    private static Runnable onMouseMoved;

    /**
     * Will be called when the mouse is 
     * released.
     */
    private static Runnable onMouseUp;

    /** Handles the mouse events. */
    @Override
    public void handle(MouseEvent e) {
        if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
            if (onMouseDown != null) {
                onMouseDown.run();
            }
        } else if (e.getEventType() == MouseEvent.MOUSE_MOVED) {
            mouseX = (int) e.getX();
            mouseY = (int) e.getY();
            if (onMouseMoved != null) {
                onMouseMoved.run();
            }
        } else if (e.getEventType() == MouseEvent.MOUSE_RELEASED) {
            if (onMouseUp != null) {
                onMouseUp.run();
            }
        }
    }

    /**
     * Sets the function that will be called 
     * when the mouse is pushed down.
     * @param runnable The runnable class.
     */
    public static void setOnMouseDown(Runnable runnable) {
        onMouseDown = runnable;
    }

    /**
     * Sets the function that will be called 
     * when the mouse moves.
     * @param runnable The runnable class.
     */
    public static void setOnMouseMove(Runnable runnable) {
        onMouseMoved = runnable;
    }

    /**
     * Sets the function that will be called 
     * when the mouse is released.
     * @param runnable The runnable class.
     */
    public static void setOnMouseUp(Runnable runnable) {
        onMouseUp = runnable;
    }
}
