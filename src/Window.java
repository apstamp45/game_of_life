import java.math.RoundingMode;
import java.text.DecimalFormat;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;


/**
 * This class is used by class Main for all the window stuff.
 * 
 * @author apstamp45
 * @version 1.4
 */
public class Window extends Application {
	
	/**
	 * CANVAS_COLOR defines the color that the
	 * canvas is painted before drawing the pixels.
	 */
	private static int[] CANVAS_COLOR = {0, 100, 100};
	
	/**
	 * DEFAULT_PIXEL_COLOR defines the default
	 * color of all  the "pixels" on the canvas.
	 */
	private static int[] DEFAULT_PIXEL_COLOR = {0, 0, 0};
	
	/**
	 * PIXEL_WIDTH defines the width of each
	 * "pixel" on the canvas in real screen pixels.
	 */
	public static int PIXEL_WIDTH = 10;
	
	/**
	 * SPACING_WIDTH defines the space in
	 * between each "pixel" in screen pixels.
	 */
	public static int SPACING_WIDTH = 2;
	
	/**
	 * CANVAS_WIDTH and CANVAS_HEIGHT define
	 * the width and height of the canvas in "pixels".
	 */
	public static int CANVAS_WIDTH = 50;
	
	/** @see CANVAS_WIDTH */
	public static int CANVAS_HEIGHT = 50;
	
	/** canvas is the canvas on the window. */
	private static Canvas canvas;
	
	/** gc stores the context of canvas. */
	private static GraphicsContext gc;
	
	/** layout stores all the "pixels" on the canvas. */
	public static Pixel[][] layout;
	
	/** This function launches the window. */
	public static void run(String[] s) {
		launch(s);
	}
	
	/**
	 * speed stores how many generations will pass
	 * per second.
	 */
	public static double speed;
	
	/**
	 * This Button starts and stops the automatic
	 * generation running.
	 */
	public static Button startStop;
	
	/** step is used to run a single generation at a time. */
	public static Button step;
	
	/**
	 * clear is used to kill all the cells on the canvas.
	 * It is hidden when the generations are running.
	 */
	public static Button clear;
	
	/**
	 * This slider is used to set how many cell generations
	 * will pass in one second when running.
	 */
	public static Slider runSpeed;
	
	/** This field displays the run speed. */
	public static Label speedLabel;
	
    /** Starts the window. */
    @Override
    public void start(Stage primaryStage) {
    	speed = 10;
    	createCanvas();
    	canvas.setOnMousePressed(e -> Main.onMouseDown(e));
    	canvas.setOnMouseDragged(e -> Main.onMouseDrag(e));
    	
    	startStop = new Button();
    	startStop.getStyleClass().add("button");
    	startStop.setOnAction(e -> Main.onStartStopPress());
    	HBox.setMargin(startStop, new Insets(10, 5, 0, 5));
    	startStop.setText("Start");
    	
    	step = new Button();
    	step.getStyleClass().add("button");
    	step.setOnAction(e -> Main.onStepPress());
    	HBox.setMargin(step, new Insets(10, 5, 0, 5));
    	step.setText("Run Generation");
    	
    	clear = new Button();
    	clear.getStyleClass().add("button");
    	clear.setOnAction(e -> Main.onClearPress());
    	HBox.setMargin(clear, new Insets(10, 5, 0, 5));
    	clear.setText("Clear");
    	
    	speedLabel = new Label();
    	speedLabel.getStyleClass().add("label");
    	HBox.setMargin(speedLabel, new Insets(10, 5, 0, 5));
    	speedLabel.setText("Generations Per Second: " + speed);
    	
    	runSpeed = new Slider();
    	runSpeed.getStyleClass().add("slider");
    	runSpeed.setValue(speed);
    	runSpeed.setOnMouseDragged(e -> onSliderDrag(runSpeed, speedLabel));
    	HBox.setMargin(runSpeed, new Insets(10, 5, 0, 5));
    	runSpeed.setMin(0.25);
    	runSpeed.setMax(20);
		
    	HBox left = new HBox(startStop, step);
    	HBox right = new HBox(speedLabel, runSpeed, clear);
    	
    	BorderPane bottom = new BorderPane();
    	bottom.setLeft(left);
    	bottom.setRight(right);
    	
    	BorderPane pane = new BorderPane();
    	pane.setCenter(canvas);
    	pane.setBottom(bottom);
    	pane.setPadding(new Insets(5));
    	
    	Scene mainScene = new Scene(pane);
    	mainScene.getStylesheets().add("style.css");
    	
    	primaryStage.setScene(mainScene);
    	primaryStage.setResizable(false);
    	primaryStage.show();
    	Main.setup();
    }
    
    /** Runs at close of window. */
    @Override
    public void stop() {
    	Main.end();
    }
    
    /**
     * This function crates the canvas and
     * everything that has to do with it.
     */
    public static void createCanvas() {
		canvas = new Canvas(
				CANVAS_WIDTH * (PIXEL_WIDTH + SPACING_WIDTH) - SPACING_WIDTH,
				CANVAS_HEIGHT * (PIXEL_WIDTH + SPACING_WIDTH) - SPACING_WIDTH);
		gc = canvas.getGraphicsContext2D();
		Pixel[][] $layout = new Pixel[CANVAS_WIDTH][CANVAS_HEIGHT];
		layout = $layout;
		for (int i = 0; i < CANVAS_WIDTH; i++) {
			for (int j = 0; j < CANVAS_HEIGHT; j++) {
				layout[i][j] = new Pixel();
				layout[i][j].r = DEFAULT_PIXEL_COLOR[0];
				layout[i][j].g = DEFAULT_PIXEL_COLOR[1];
				layout[i][j].b = DEFAULT_PIXEL_COLOR[2];
			}
		}
		gc.setFill(Color.rgb(
				CANVAS_COLOR[0],
				CANVAS_COLOR[1],
				CANVAS_COLOR[2]));
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for (int i = 0; i < CANVAS_WIDTH; i++) {
			for (int j = 0; j < CANVAS_HEIGHT; j++) {
				gc.setFill(Color.rgb(
						layout[i][j].r,
						layout[i][j].g,
						layout[i][j].b));
				gc.fillRect(
						i * PIXEL_WIDTH  + i * SPACING_WIDTH,
						j * PIXEL_WIDTH + j * SPACING_WIDTH,
						PIXEL_WIDTH, PIXEL_WIDTH);
			}
		}
	}
    
    /**
     * This function paints all the "pixels" in the
     * layout variable onto the canvas.
     */
    public static void paint() {
		gc.setFill(Color.rgb(CANVAS_COLOR[0], CANVAS_COLOR[1], CANVAS_COLOR[2]));
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for (int i = 0; i < CANVAS_WIDTH; i++) {
			for (int j = 0; j < CANVAS_HEIGHT; j++) {
				gc.setFill(Color.rgb(
						(int) Math.round(layout[i][j].r),
						(int) Math.round(layout[i][j].g),
						(int) Math.round(layout[i][j].b)));
				gc.fillRect(
					(int)(i * PIXEL_WIDTH  + i * SPACING_WIDTH),
					(int)(j * PIXEL_WIDTH + j * SPACING_WIDTH),
					PIXEL_WIDTH, PIXEL_WIDTH);
			}
		}
	}
    
    /**
	 * This function adds a "pixel" to the layout variable.
	 * 
	 * @param x the x position of the pixel you want to draw.
	 * @param y the y position of the pixel you want to draw.
	 * @param r	the r value of the pixel you want to draw.
	 * @param g the r value of the pixel you want to draw.
	 * @param b the r value of the pixel you want to draw.
	 */
	public static void drawPixel(int x, int y, int r, int g, int b) {
		layout[x][y].r = r;
		layout[x][y].g = g;
		layout[x][y].b = b;
	}
	
	/**
	 * This function gets the value of
	 * a slider when it is changed.
	 * 
	 * @param s the slider that was changed.
	 * @param l the label of the slider.
	 */
	private static void onSliderDrag(Slider s, Label l) {
		double d = s.getValue();
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		speed = Double.parseDouble(df.format(d));
		l.setText("Generations Per Second: " + speed);
	}
}