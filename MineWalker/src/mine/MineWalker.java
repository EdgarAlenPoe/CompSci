package mine;
import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MineWalker {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(1200, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MineWalkerPanel panel = new MineWalkerPanel();
//		MineFieldPanel panel = new MineFieldPanel(10);
		frame.add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
	}

}
