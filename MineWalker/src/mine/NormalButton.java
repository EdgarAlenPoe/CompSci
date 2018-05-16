package mine;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class NormalButton extends MineFieldButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected int numberAdjacent;
	
	Timer animationTimer = new Timer(500, new TimerActionListener());

	/**
     * Performs action when timer event fires.
     */
	private class TimerActionListener implements ActionListener {
		public void actionPerformed(ActionEvent evt)
		{
//			if (NormalButton.this.getText() == "") {
//				NormalButton.this.setText("*");
//			}
//			else {
//				NormalButton.this.setText("");
//			}
			if (NormalButton.this.getBackground() != Color.LIGHT_GRAY) {
				NormalButton.this.setBackground(Color.LIGHT_GRAY);;
			}
			else {
				switch (numberAdjacent) {
				case 0:
					NormalButton.this.setBackground(Color.GREEN);
					break;
				case 1:
					NormalButton.this.setBackground(Color.YELLOW);
					break;
				case 2:
					NormalButton.this.setBackground(Color.ORANGE);
					break;
				case 3:
				default:
					NormalButton.this.setBackground(Color.RED);
					break;
				}
			}
		}
	}


	public NormalButton(int x, int y, int size, int numbAdjacent, MineFieldPanel parentPanel) {
		super(x, y, size, parentPanel);
		numberAdjacent = numbAdjacent;
	}

	@Override
	public void whenClicked() {
		MineFieldButton currentPosition = parentPanel.currentPosition;
		if (currentPosition != null) {
			if (currentPosition.canMoveFrom(this) && this.canMoveTo(currentPosition)) {
				super.revealed = true;
				this.setAsCurrentPosition(true);
				switch (numberAdjacent) {
					case 0:
						super.setBackground(Color.GREEN);
						break;
					case 1:
						super.setBackground(Color.YELLOW);
						break;
					case 2:
						super.setBackground(Color.ORANGE);
						break;
					case 3:
					default:
						super.setBackground(Color.RED);
						break;
				}
			}
		}
	}

	@Override
	public void revealForEnd() {
		// Do nothing, these spots don't display at end game
	}

	@Override
	public void peekMine(boolean onOff) {
		// Do nothing, this is not a mine
	}

	@Override
	public void peekPath(boolean onOff) {
		// Do nothing, this is not part of the path
	}

	@Override
	public ButtonType getButtonType() {
		return ButtonType.Normal;
	}

	@Override
	public void setAsCurrentPosition(boolean isCurrentPosition) {
		if (isCurrentPosition) {
			if (parentPanel.currentPosition != null) {
				parentPanel.currentPosition.setAsCurrentPosition(false);
			}
			parentPanel.currentPosition = this;
			startAnimation();
			ScoreBoard.getInstance().takeFromScore(1);
		}
		else if (parentPanel.currentPosition == this) {
			parentPanel.currentPosition = null;
			stopAnimation();
		}
	}
	
	
   /**
    * Create an animation thread that runs periodically
    */
    private void startAnimation() {
	    animationTimer.start();
    }
    
    private void stopAnimation() {
    	animationTimer.stop();
    	switch (numberAdjacent) {
		case 0:
			NormalButton.this.setBackground(Color.GREEN);
			break;
		case 1:
			NormalButton.this.setBackground(Color.YELLOW);
			break;
		case 2:
			NormalButton.this.setBackground(Color.ORANGE);
			break;
		case 3:
		default:
			NormalButton.this.setBackground(Color.RED);
			break;
		}
    }


}
