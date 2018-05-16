package mine;

import java.awt.Color;

public class MineButton extends MineFieldButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean peeking;

	public MineButton(int x, int y, int size, MineFieldPanel parentPanel) {
		super(x, y, size, parentPanel);

		
	}

	@Override
	public void whenClicked() {
		MineFieldButton currentPosition = super.parentPanel.currentPosition;
		if (currentPosition != null) {
			if (currentPosition.canMoveFrom(this) && this.canMoveTo(currentPosition) && !revealed) {
				super.revealed = true;
				super.setBackground(Color.BLACK);
				parentPanel.mineClicked();
			}
		}
	}

	@Override
	public void revealForEnd() {
		super.setBackground(Color.BLACK);
	}

	@Override
	public void peekMine(boolean onOff) {
		if (onOff) {
			super.setBackground(Color.BLACK);
		}
		else if (!super.revealed) {
			super.setBackground(Color.WHITE);
		}
		peeking = onOff;
	}

	@Override
	public void peekPath(boolean onOff) {
		// Do nothing, this is not part of the path
	}

	@Override
	public ButtonType getButtonType() {
		return ButtonType.Mine;
	}

	@Override
	public void setAsCurrentPosition(boolean isCurrentPosition) {
		System.err.println("Mine being set as or refrenced as current position");
	}
	
	@Override
	public void whenMouseExits() {
		if (peeking && !revealed) {
			super.setBackground(Color.BLACK);
		}
		else {
			super.whenMouseExits();
		}
	}

}
