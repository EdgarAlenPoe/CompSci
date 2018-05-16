package mine;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public abstract class MineFieldButton extends JButton {
	
	public enum ButtonType {
		Mine,
		Normal,
		Path,
		StartingPoint,
		EndingPoint
	}

	protected boolean revealed = false;
	private int mineFieldX;
	private int mineFieldY;
	protected MineFieldPanel parentPanel;
	private boolean frozen = false;
	
	public MineFieldButton(int x, int y, int size, MineFieldPanel parentPanel) {
		this.mineFieldX = x;
		this.mineFieldY = y;
		this.parentPanel = parentPanel;
		
//		super.setSize(size, size);
		
		// children will handle changing color, this is just the default
		super.setBackground(Color.WHITE);
		
		
		this.addMouseListener(new MouseListener() {
			
			// TODO add right click flag feature?
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if (!frozen) {
					whenMouseExits();
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if (!frozen) {
					whenMouseEnters();
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!frozen) {
					whenClicked();
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
	}
	
	public void freeze() {
		frozen = true;
	}
	
	public int getMineFieldX() {
		return mineFieldX;
	}
	
	public int getMineFieldY() {
		return mineFieldY;
	}
	
	public abstract void whenClicked();
	
	public void whenMouseEnters() {
		if (!revealed) {
			super.setBackground(Color.DARK_GRAY);
		}
	}
	
	public void whenMouseExits() {
		if (!revealed) {
			super.setBackground(Color.WHITE);
		}
	}
	
	/**
	 * <p>Used for player movement. Called when it's being checked if a move is legal. Checks is this button allows the move from the provided button.</p>
	 * <p>Overriding this method allows making special buttons that disallow moving onto them, like exploded mines not being able to be walked on.</p>
	 * @param start button the movement is coming from. Allows button to discriminate between different buttons for special behavior
	 */
	public boolean canMoveFrom(MineFieldButton start) {
		return true;
	}
	
	/**
	 * <p>Used for player movement. Called when it's being checked if a move is legal. Checks is this button allows the move to the provided button. </p>
	 * <p>Overriding this method allows making special buttons that disallow moving to certain buttons, like one that lets you move diagonally.</p>
	 * @param end button the movement is going to. Allows button to discriminate between different buttons for special behavior
	 */
	public boolean canMoveTo(MineFieldButton end) {
		boolean verticallyAdjacent = (Math.abs(this.getMineFieldY() - end.getMineFieldY()) == 1 && (this.getMineFieldX() - end.getMineFieldX()) == 0);
		boolean horizontallyAdjacent = ((Math.abs(this.getMineFieldX() - end.getMineFieldX()) == 1) && (this.getMineFieldY() - end.getMineFieldY()) == 0);
		
		if (verticallyAdjacent ^ horizontallyAdjacent) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Used at end of game to show what board looked like
	 */
	public abstract void revealForEnd();
	
	/**
	 * Used to activate mines visible mode for debug and cheaters
	 * @param onOff
	 */
	public abstract void peekMine(boolean onOff);
	
	/**
	 * Used to activate path visible mode for debug and cheaters
	 * @param onOff
	 */
	public abstract void peekPath(boolean onOff);
	
	public abstract ButtonType getButtonType();
	
	public abstract void setAsCurrentPosition(boolean isCurrentPosition);
}
