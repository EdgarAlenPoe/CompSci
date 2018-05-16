package mine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OptionsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	MineWalkerPanel parent;
	JButton showMines;
	JButton showPath;
	JButton newGame;
	JTextField size;
	
	private boolean isGameOn = true;
	private boolean minesShown = false;
	private boolean pathShown = false;
	
	
	public OptionsPanel(MineWalkerPanel parent) {
		this.parent = parent;
		
		showMines = new JButton("Show Mines");
		showMines.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isGameOn) {
					if (minesShown) {
						parent.mineField.peekMines(false);
						showMines.setText("Show Mines");
						minesShown = false;
					}
					else {
						parent.mineField.peekMines(true);
						showMines.setText("Hide Mines");
						minesShown = true;
					}
				}
			}
		});
		add(showMines);
		
		showPath = new JButton("Show Path");
		showPath.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isGameOn) {
					if (pathShown) {
						parent.mineField.peekPath(false);
						showPath.setText("Show Path");
						pathShown = false;
					}
					else {
						parent.mineField.peekPath(true);
						showPath.setText("Hide Path");
						pathShown = true;
					}
				}
			}
		});
		add(showPath);
		
		newGame = new JButton("Give Up?");
		newGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isGameOn) {
					parent.mineField.forfeitGame();
					newGame.setText("New Game");
					isGameOn = false;
				}
				else {
					int s = Integer.parseInt(size.getText());
					parent.makeNewGame(s);
					newGame.setText("Give Up?");
					isGameOn = true;
				}
			}
		});
		this.add(newGame);
		
		size = new JTextField("10");
		this.add(size);
	}
	
	public void gameEnded() {
		newGame.setText("New Game");
		isGameOn = false;
		showMines.setText("Show Mines");
		minesShown = false;
		showPath.setText("Show Path");
		pathShown = false;
	}
}
