package gameGUI;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static gameGUI.TicTacToePanel.ROW;
import static gameGUI.TicTacToePanel.SQUARE_SIZE;

public class TicTacToeFrame extends JFrame{

	public TicTacToeFrame(JPanel ticTacToePanel) {
		setTitle("Tic Tac Toe");
		setIconImage(new ImageIcon("res/xo.png").getImage());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(ticTacToePanel);
		setMinimumSize(new Dimension(SQUARE_SIZE*(ROW*2),SQUARE_SIZE*(ROW*2)));
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
}
