package mine;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MineFieldPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	MineFieldButton[][] mineField;
	int mineFieldSize;
	MineWalkerPanel parent;

	GridBagConstraints gbc = new GridBagConstraints();
	
	/**
	 * Field is read and changed by the buttons as they decide when to move and change their state. 
	 */
	MineFieldButton currentPosition;
	
	public MineFieldPanel(int size, MineWalkerPanel parent) {
		super(new GridBagLayout());
		
		this.parent = parent;
		
		mineFieldSize = size;
		mineField = new MineFieldButton[size][size];
		
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		
		/* ========================
		 * MineField generation code. Move somewhere else later and generalize code.
		 */
		
		/* 
		 * used to plan out minefield.
		 * the number in each slot denotes the number of adjacent mines.
		 * negative numbers denote path members, abs value of number is adjacent mines minus 1 (ex. -3 means there are 2 adjacent bombs).
		 */
		int[][] mineFieldPrototype = new int[size][size];

		int sizeForButtons = 800 / size; 
		
		Random rand = new Random();
		
//		// Draw path on prototype
//		int pathX = 0;
//		int pathY = 0; //size - 1;
//		
//		/*
//		 * Loop will run from bottom left corner to top right.
//		 * Moves up or right in a random pattern until it reaches the top or right edge.
//		 * Will place a -1 in edge reached
//		 * Successfully tested
//		 */
//		while ((pathX <= size - 1) && pathY <= size - 1 /* >= 0 */) {
//			mineFieldPrototype[pathY][pathX] = -1;
//			if (rand.nextBoolean()) {
//				// path goes up
//				pathY++;
//			}
//			else {
//				// path goes down
//				pathX++;
//			}
//		}
//		
//		/*
//		 * Loops finish path to edge that may have been missed
//		 * Successfully tested
//		 */
//		while (pathX <= size - 1) {
//			mineFieldPrototype[pathY -1][pathX] = -1;
//			pathX++;
//		}
//		while (pathY <= size - 1) { //>= 0) {
//			mineFieldPrototype[pathY][pathX - 1] = -1;
//			pathY++;
//		}
		
		
		/*
		 * Places mines in actual grid, and onto panel.
		 */
		int targetNumberOfMines = (int) ((size * size) * (1.0 / 6.0)); // TODO make mine percentage variable (pass value through constructor and stick it in here) 
		int currentNumberOfMines = 0;
		while (currentNumberOfMines != targetNumberOfMines) {
			int minePlacementX = rand.nextInt(size);
			int minePlacementY = rand.nextInt(size);
			if (mineFieldPrototype[minePlacementY][minePlacementX] > -1 && mineField[minePlacementY][minePlacementX] == null) {
				MineFieldButton button = new MineButton(minePlacementX, minePlacementY, sizeForButtons, this);
				super.add(button, this.constraintsAtPoint(minePlacementX, minePlacementY));
				mineField[minePlacementY][minePlacementX] = button;
				markMineNextTo(minePlacementX,minePlacementY,mineFieldPrototype);
				currentNumberOfMines++;
			}
		}
		
		
		
		
		for (int y = 0; y < mineField.length; y++) {
			for (int x = 0; x < mineField.length; x++) {
				if (mineField[y][x] == null) {
//					if (x == 0 && y == 0) {
//						MineFieldButton button = new StartingButton(x, y, sizeForButtons, Math.abs(mineFieldPrototype[y][x]) - 1, this);
//						super.add(button, this.constraintsAtPoint(x, y));
//						mineField[y][x] = button;
//					}
//					else if (x == mineField.length - 1 && y == mineField.length - 1) {
//						MineFieldButton button = new EndingButton(x, y, sizeForButtons, Math.abs(mineFieldPrototype[y][x]) - 1, this);
//						super.add(button, this.constraintsAtPoint(x, y));
//						mineField[y][x] = button;
//					}
//					else if (mineFieldPrototype[y][x] < 0) {
//						MineFieldButton button = new PathButton(x, y, sizeForButtons, Math.abs(mineFieldPrototype[y][x]) - 1, this);
//						super.add(button, this.constraintsAtPoint(x, y));
//						mineField[y][x] = button;
//					}
//					else {
					MineFieldButton button = new NormalButton(x, y, sizeForButtons, mineFieldPrototype[y][x], this);
					super.add(button, this.constraintsAtPoint(x, y));
					mineField[y][x] = button;
//					}
				}
			}
		}
	}
	
	private void markMineNextTo(int x, int y, int[][] mineFieldPrototype) {
		safelyIncrementPoint(x-1,y,mineFieldPrototype);
		safelyIncrementPoint(x+1,y,mineFieldPrototype);
		safelyIncrementPoint(x,y+1,mineFieldPrototype);
		safelyIncrementPoint(x,y-1,mineFieldPrototype);
		
		safelyIncrementPoint(x-1,y-1,mineFieldPrototype);
		safelyIncrementPoint(x+1,y+1,mineFieldPrototype);
		safelyIncrementPoint(x-1,y+1,mineFieldPrototype);
		safelyIncrementPoint(x+1,y-1,mineFieldPrototype);
	}
	
	private void safelyIncrementPoint(int x, int y, int[][] mineFieldPrototype) {
		if (y < mineFieldPrototype.length && y >= 0) {
			if (x < mineFieldPrototype.length && x >= 0) {
				if (mineFieldPrototype[y][x] >= 0)
					mineFieldPrototype[y][x] += 1;
				else if (mineFieldPrototype[y][x] < 0) 
					mineFieldPrototype[y][x] -= 1;
			}
		}
	}

	public GridBagConstraints constraintsAtPoint(int x, int y) {
		gbc.gridx = x;
		gbc.gridy = mineFieldSize - y - 1;
		return gbc;
	}
	
	public void mineClicked() {
		ScoreBoard.getInstance().takeAwayLife();
		if (ScoreBoard.getInstance().getLives() <= 0) {
			livesOut();
		}
	}
	
	public void livesOut() {
		endGame();
		parent.options.gameEnded();
		JOptionPane.showMessageDialog(this, "Heh, you lost.");
	}
	
	public void endClicked() {
		endGame();
		parent.options.gameEnded();
		JOptionPane.showMessageDialog(this, "Congrats, you won!");
	}
	
	public void endGame() {
		for (MineFieldButton[] thing : mineField) {
			for (MineFieldButton button : thing) {
				button.freeze();
			}
		}
		if (currentPosition != null) {
			currentPosition.setAsCurrentPosition(false);
		}
		peekMines(true);
	}
	
	public void forfeitGame() {
		endGame();
	}
	
	public void peekMines(boolean onOff) {
		for (MineFieldButton[] thing : mineField) {
			for (MineFieldButton button : thing) {
				button.peekMine(onOff);
			}
		}
	}
	
	public void peekPath(boolean onOff) {
		for (MineFieldButton[] thing : mineField) {
			for (MineFieldButton button : thing) {
				button.peekPath(onOff);
			}
		}
	}

	
}
