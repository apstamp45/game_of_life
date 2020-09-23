package com.apstamp45.snake_game;

import com.apstamp45.snake_game.event.*;
import com.apstamp45.snake_game.graphics.Pixel;

import javafx.animation.AnimationTimer;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class handles the window.
 * @author apstamp45
 */
public class Window extends Application {

    /** The widow's canvas. */
    private static Canvas canvas;

    /**
     * Holds the graphics context 
     * for the canvas.
     */
    private static GraphicsContext gc;

    /**
     * Holds all of the command line 
     * arguments.
     */
    private static String[] args;

    /** Used to call tht loop function. */
    private static AnimationTimer timer;

    /** Launches the window. */
    public static void run(String[] args) {
        Window.args = args;
        launch(Window.args);
    }

    /** Starts the window. */
    @Override
    public void start(Stage primaryStage) throws Exception {
        canvas = new Canvas(Main.CANVAS_WIDTH, Main.CANVAS_HEIGHT);
        gc = canvas.getGraphicsContext2D();
        BorderPane pane = new BorderPane();
        pane.setCenter(canvas);
        Scene mainScene = new Scene(pane);
        mainScene.addEventHandler(MouseEvent.ANY, new MouseEventHandler());
        mainScene.addEventHandler(KeyEvent.ANY, new KeyboardEventHandler());
        primaryStage.setTitle(Main.WINDOW_TITLE);
        primaryStage.setScene(mainScene);
        primaryStage.setResizable(false);
        primaryStage.show();
        Main.start(args);
        timer = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
                Main.loop(args);
			}
        };
        timer.start();
    }

    /** Runs at close of window. */
    @Override
    public void stop() {
        timer.stop();
        Main.end(args);
    }

    /**
     * Draws a rectangle onto the canvas.
     * @param x The starting x coordinate.
     * @param y The starting y coordinate.
     * @param width The rectangle's width.
     * @param height The rectangle's height.
     * @param pixel The pixel to be drawn.
     */
    public static void drawRect(double x, double y, double width, double height, Pixel pixel) {
        gc.setFill(Color.rgb(pixel.r(), pixel.g(), pixel.b()));
        gc.fillRect(x, y, width, height);
    }

    /**
     * Draws a image onto the canvas.
     * @param x The starting x coordinate.
     * @param y The starting y coordinate.
     * @param image The image.
     */
    public static void drawImage(double x, double y, Image image) {
        gc.drawImage(image, x, y);
    }

    /**
     * Draws an image part onto the canvas.
     * @param x The x coordinate on the canvas.
     * @param y The y coordinate on the canvas.
     * @param image The image.
     * @param startingX The starting x coordinate of the image.
     * @param startingY The starting y coordinate of the image.
     * @param width The width of the image to draw.
     * @param height The height of the image to draw.
     */
    public static void drawImagePart(double x, double y, Image image, 
            int startingX, int startingY, int width, int height) {
        gc.drawImage(image, startingX, startingY, width, height, x, y, width, height);
    }

    /**
     * Fills the canvas with a specific pixel.
     * @param pixel The pixel to fill the canvas with.
     */
    public static void fill(Pixel pixel) {
        if (!pixel.isTransparent()) {
            gc.setFill(Color.rgb(pixel.r(), pixel.g(), pixel.b()));
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        }
    }
}
