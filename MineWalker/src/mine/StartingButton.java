package mine;

import java.awt.Color;

public class StartingButton extends NormalButton {

	public StartingButton(int x, int y, int size, int numbAdjacent, MineFieldPanel parentPanel) {
		super(x, y, size, numbAdjacent, parentPanel);
		super.setBackground(Color.CYAN);
	}

	// TODO make it clickable when nothing else is.
	@Override
	public void whenClicked() {
		if (!super.revealed) {
			super.revealed = true;
			this.setAsCurrentPosition(true);
			switch (super.numberAdjacent) {
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
		else {
			super.whenClicked();
		}
	}
	
	@Override
	public void whenMouseExits() {
		if (!revealed) {
			super.setBackground(Color.CYAN);
		}
		else {
			super.whenMouseExits();
		}
	}
}
