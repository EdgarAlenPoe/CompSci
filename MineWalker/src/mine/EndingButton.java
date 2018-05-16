package mine;

import java.awt.Color;

public class EndingButton extends NormalButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean clicked = false;

	public EndingButton(int x, int y, int size, int numbAdjacent, MineFieldPanel parentPanel) {
		super(x, y, size, numbAdjacent, parentPanel);
		super.setBackground(Color.MAGENTA);
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
				super.parentPanel.endClicked();
			}
		}
	}
	
	@Override
	public void whenMouseExits() {
		if (!revealed) {
			super.setBackground(Color.MAGENTA);
		}
		else {
			super.whenMouseExits();
		}
	}
	
	@Override
	public boolean canMoveTo(MineFieldButton end) {
		if (clicked) {
			return false;
		}
		else {
			return true;
		}
	}
}
