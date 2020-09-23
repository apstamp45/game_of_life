package com.apstamp45.snake_game.event;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * This class handles all of the 
 * keyboard events.
 * @author apstamp45
 */
public class KeyboardEventHandler implements EventHandler<KeyEvent> {

    /**
     * Stores the keycode of the last 
     * pressed key.
     */
    public static KeyCode lastKeyPressed;

    /**
     * Stores the keycode of the last 
     * released key.
     */
    public static KeyCode lastKeyReleased;

    /**
     * Will be called when a key is pressed.
     */
    private static Runnable onKeyDown;

    /**
     * Will be called when a key is released.
     */
    private static Runnable onKeyUp;

    /** Handles the key events. */
    @Override
    public void handle(KeyEvent e) {
        if (e.getEventType() == KeyEvent.KEY_PRESSED) {
            lastKeyPressed = e.getCode();
            if (onKeyDown != null) {
                onKeyDown.run();
            }
        } else if (e.getEventType() == KeyEvent.KEY_RELEASED) {
            lastKeyReleased = e.getCode();
            if (onKeyUp != null) {
                onKeyUp.run();
            }
        }
    }

    /**
     * Sets the function that will be 
     * called when a key is pressed.
     * 
     * @param runnable The runnable class.
     */
    public static void setOnKeyDown(Runnable runnable) {
        onKeyDown = runnable;
    }

    /**
     * Sets the function that will be 
     * called when a key is released.
     * 
     * @param runnable The runnable class.
     */
    public static void setOnKeyUp(Runnable runnable) {
        onKeyUp = runnable;
    }
}
