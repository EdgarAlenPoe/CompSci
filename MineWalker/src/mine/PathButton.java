package mine;

import java.awt.Color;

import mine.MineFieldButton.ButtonType;

public class PathButton extends NormalButton {

	private boolean peeking = false;
	
	public PathButton(int x, int y, int size, int numbAdjacent, MineFieldPanel parentPanel) {
		super(x, y, size, numbAdjacent, parentPanel);
	}
	
	@Override
	public void peekPath(boolean onOff) {
		// Display as blue if not revealed
		if (onOff && !super.revealed) {
			super.setBackground(Color.BLUE);
		}
		else if (!super.revealed) {
			super.setBackground(Color.WHITE);
		}
		peeking = onOff;
	}
	
	@Override
	public ButtonType getButtonType() {
		return ButtonType.Path;
	}
	
	@Override
	public void whenMouseExits() {
		if (peeking && !revealed) {
			super.setBackground(Color.BLUE);
		}
		else {
			super.whenMouseExits();
		}
	}
}
