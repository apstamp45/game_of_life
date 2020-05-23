/**
 * This class represents a cell in Conway's
 * "Game of Life".
 * 
 * @author apstamp45
 * @version 1.0
 * @see Main
 */
public class Cell {

	/** Shows if the cell is alive or dead. */
	public boolean isAlive;

	/**
	 * Is used to check the next living status 
	 * before actually changing isAlive.
	 */
	public boolean nextLiveStatus;

	/** The cell's x position. */
	public int x;

	/** The cell's y position. */
	public int y;
	
	/**
	 * This function determines the next state the
	 * cell depending on how many live cells
	 * it is touching.
	 * 
	 * @param grid the array containing all the other
	 * cells, used in determining the next state.
	 */
	public void checkNextState(Cell[][] grid) {
		int touchingLive = 0;
		int[][] toCheck = {
				{-1,-1},
				{0,-1},
				{1,-1},
				{-1,0},
				{1,0},
				{-1,1},
				{0,1},
				{1,1}};
		for (int i = 0; i < 8; i++) {
			if (toCheck[i][0] + x < 0 || toCheck[i][1] + y < 0) {
				continue;
			} else if (toCheck[i][0] + x > (grid.length - 1) || toCheck[i][1] + y > (grid[0].length - 1)) {
				continue;
			} else if (grid[toCheck[i][0] + x][toCheck[i][1] + y].isAlive) {
				touchingLive++;
			}
		}
		if (isAlive) {
			if (touchingLive < 2) {
				nextLiveStatus = false;
			} else if (touchingLive > 3) {
				nextLiveStatus = false;
			} else {
				nextLiveStatus = true;
			}
		} else {
			if (touchingLive == 3) {
				nextLiveStatus = true;
			} else {
				nextLiveStatus = false;
			}
		}
	}
	
	/**
	 * This function updates the cell's
	 * living status.
	 */
	public void update() {
		isAlive = nextLiveStatus;
	}
	
	/**
	 * This constructor sets the x and y position of the cell.
	 * 
	 * @param x the x position of the Cell to create.
	 * @param y the y position of the Cell to create.
	 */
	public Cell(int x, int y) {
		if (x < 0) {
			x = 0;
		}
		if (y < 0) {
			y = 0;
		}
		this.x = x;
		this.y = y;
	}
}