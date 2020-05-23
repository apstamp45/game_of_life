import javax.swing.Timer;

import javafx.scene.input.MouseEvent;

/**
 * This program imitates Conway's "Game of Life".
 * 
 * @author apstamp45
 * @version 1.5
 * @see <a href="https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life">Conway's "Game of Life"</a>
 */
public class Main {
	
	/**
	 * This constant stores the color of the live cells;
	 */
	private static final int[] LIVE_CELL_COLOR = {255, 255, 255};
	
	/**
	 * This constant stores the color of the dead cells;
	 */
	private static final int[] DEAD_CELL_COLOR = {0, 0, 0};
	
	/**
	 * grid stores all the cells on the canvas;
	 */
	private static Cell[][] grid;
	
	/** t is used to call the runGeneration() function over and over. */
	private static Timer t;
	
	/**
	 * This field states if the generations
	 * are running or not.
	*/
	private static boolean playing;
	
	/**
	 * Runs the window.
	 * 
	 * @param args these aren't used.
	 */
	public static void main(String[] args) {
		Window.run(args);
	}
	
	/**
	 * This function is called by Window when
	 * the window loads.
	 */
	public static void setup() {
		t = new Timer(0, null);
		grid = new Cell[Window.CANVAS_WIDTH][Window.CANVAS_HEIGHT];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = new Cell(i, j);
			}
		}
		draw();
	}
	
	/**
	 * This function updates each cell to the
	 * next generation.
	 */
	private static void runGeneration() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j].checkNextState(grid);
			}
		}
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j].update();
			}
		}
		draw();
	}
	
	/**
	 * This function runs when the window is closed.
	 */
	public static void end() {
		if (t.isRunning()) {
			t.stop();
		}
	}
	
	/**
	 * draw() draws all the pixels on the canvas depending
	 * on the living status of the cell at that position.
	 */
	private static void draw() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j].isAlive) {
					Window.drawPixel(i, j,
							LIVE_CELL_COLOR[0],
							LIVE_CELL_COLOR[1],
							LIVE_CELL_COLOR[2]);
				} else {
					Window.drawPixel(i, j,
							DEAD_CELL_COLOR[0],
							DEAD_CELL_COLOR[1],
							DEAD_CELL_COLOR[2]);
				}
			}
		}
		Window.paint();
	}
	
	/**
	 * Runs on the press of a button.
	 * 
	 * @param b the button that was pressed.
	 */
	public static void onStartStopPress() {
		if (Window.startStop.getText().equals("Start")) {
			playing = true;
			int delay = (int) Math.round(1000 / Window.runSpeed.getValue());
			t = new Timer(delay, e -> runGeneration());
			t.start();
			Window.startStop.setText("Stop");
			Window.clear.setVisible(false);
			Window.runSpeed.setVisible(false);
			Window.speedLabel.setVisible(false);
			Window.step.setVisible(false);
		} else {
			playing = false;
			t.stop();
			Window.startStop.setText("Start");
			Window.clear.setVisible(true);
			Window.runSpeed.setVisible(true);
			Window.speedLabel.setVisible(true);
			Window.step.setVisible(true);
		}
	}
	
	/** @see onStartPress */
	public static void onStepPress() {
		runGeneration();
	}
	
	/** @see onStartPress */
	public static void onClearPress() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j].isAlive = false;
			}
		}
		draw();
	}
	
	/**
	 * Runs on the click of the mouse
	 * on the canvas.
	 * 
	 * @param e
	 */
	public static void onMouseDown(MouseEvent e) {
		if (!playing) {
			int x = (int) (e.getX() / (Window.PIXEL_WIDTH + Window.SPACING_WIDTH));
			int y = (int) (e.getY() / (Window.PIXEL_WIDTH + Window.SPACING_WIDTH));
			if (x >= Window.CANVAS_WIDTH) {
				x = Window.CANVAS_WIDTH - 1;
			}
			if (y >= Window.CANVAS_HEIGHT) {
				y = Window.CANVAS_HEIGHT - 1;
			}
			if (e.isSecondaryButtonDown()) {
				grid[x][y].isAlive = false;
			} else {
				grid[x][y].isAlive = true;
			}
			draw();
		}
	}
	
	/**
	 * Runs when the mouse is dragged over
	 * the window.
	 * 
	 * @param e
	 */
	public static void onMouseDrag(MouseEvent e) {
		if (!playing) {
			int x = (int) (e.getX() / (Window.PIXEL_WIDTH + Window.SPACING_WIDTH));
			int y = (int) (e.getY() / (Window.PIXEL_WIDTH + Window.SPACING_WIDTH));
			if (x >= Window.CANVAS_WIDTH) {
				x = Window.CANVAS_WIDTH - 1;
			}
			if (x < 0) {
				x = 0;
			}
			if (y >= Window.CANVAS_HEIGHT) {
				y = Window.CANVAS_HEIGHT - 1;
			}
			if (y < 0) {
				y = 0;
			}
			if (e.isSecondaryButtonDown()) {
				grid[x][y].isAlive = false;
			} else {
				grid[x][y].isAlive = true;
			}
			draw();
		}
	}
}