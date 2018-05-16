package mine;

import java.awt.Dimension;
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
//		livesDisplay.setPreferredSize(new Dimension(70,20));
		scoreDisplay = new JLabel();
//		scoreDisplay.setPreferredSize(new Dimension(70,20));
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
		if (score < 0) {
			scoreDisplay.setText("Score: " + score);
		}
		if (score < 10) {
			scoreDisplay.setText("Score:  " + score);
		}
		else if (score < 100) {
			scoreDisplay.setText("Score:  " + score);
		}
		else {
			scoreDisplay.setText("Score: " + score);
		}
	}
	
	public void reset() {
		setScore(500);
		setLives(5);
	}
}
