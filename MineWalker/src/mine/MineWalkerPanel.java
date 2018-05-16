package mine;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MineWalkerPanel extends JPanel {

	MineFieldPanel mineField;
	OptionsPanel options;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MineWalkerPanel() {
		super(new BorderLayout());
		// TODO left aligned cheat sheet thing
		
		// TODO right aligned score board, must receive score and lives information, will be used as storage for this info.
		
		// TODO bottom aligned options panel. show/hides mines/path, and creates new or quits games.
		
		// TODO create a default game and display it.
		mineField = new MineFieldPanel(10, this);
		add(mineField);
		JPanel panel = new CheatSheet();
		add(panel, BorderLayout.WEST);
		options = new OptionsPanel(this);
		add(options, BorderLayout.SOUTH);
		add(ScoreBoard.getInstance(), BorderLayout.EAST);
		this.setVisible(true);
	}
	
	public void makeNewGame(int size) {
		this.remove(mineField);
		mineField = new MineFieldPanel(size, this);
		add(mineField);
		this.validate();
		ScoreBoard.getInstance().reset();
	}
}

class CheatSheet extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CheatSheet() {
		super(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 20, 0, 20);
		gbc.gridy = 0;
		this.add(makeLabel("0 Nearby Mines", Color.GREEN), gbc);
		gbc.gridy = 1;
		this.add(makeLabel("1 Nearby Mines", Color.YELLOW), gbc);
		gbc.gridy = 2;
		this.add(makeLabel("2 Nearby Mines", Color.ORANGE), gbc);
		gbc.gridy = 3;
		this.add(makeLabel("3 Nearby Mines", Color.RED), gbc);
		gbc.gridy = 4;
		JLabel label = makeLabel("Exploded Mine", Color.BLACK);
		label.setForeground(Color.white);
		this.add(makeLabel("Exploded Mine", Color.BLACK), gbc);
		
		gbc.gridy = 5;
		label = makeLabel(" ", Color.BLACK);
		label.setOpaque(false);
		this.add(label, gbc);
		
		gbc.gridy = 6;
		this.add(makeLabel("Start", Color.CYAN), gbc);
		gbc.gridy = 7;
		this.add(makeLabel("End", Color.MAGENTA), gbc);
	}
	
	public JLabel makeLabel(String text, Color color) {
		JLabel label = new JLabel();
		label.setText(text);
		label.setBackground(color);
		label.setOpaque(true);
		label.setPreferredSize(new Dimension(150, 100));
		return label;
	}
	
}