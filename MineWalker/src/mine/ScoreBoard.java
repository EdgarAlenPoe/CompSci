package mine;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreBoard extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ScoreBoard instance;
	private int score;
	private int lives;
	private JLabel livesDisplay;
	private JLabel scoreDisplay;
	
	
	private ScoreBoard() {
		super(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 20, 0, 20);
		
		livesDisplay = new JLabel();
		scoreDisplay = new JLabel();
		gbc.gridy = 0;
		super.add(livesDisplay,gbc);
		gbc.gridy = 1;
		super.add(scoreDisplay,gbc);
		
		reset();
	}
	
	public static ScoreBoard getInstance() {
		if (instance == null) {
			instance = new ScoreBoard();
		}
		return instance;
	}
	
	public void takeAwayLife() {
		lives--;
		setLives(lives);
		takeFromScore(100);
	}
	
	public int getLives() {
		return lives;
	}
	
	private void setLives(int lives) {
		this.lives = lives;
		livesDisplay.setText("Lives: " + lives);
	}
	
	public void takeFromScore(int value) {
		score -= value;
		setScore(score);
	}
	
	public int getScore() {
		return score;
	}
	
	private void setScore(int score) {
		this.score = score;
		scoreDisplay.setText("Score: " + score);
	}
	
	public void reset() {
		setScore(500);
		setLives(5);
	}
}
