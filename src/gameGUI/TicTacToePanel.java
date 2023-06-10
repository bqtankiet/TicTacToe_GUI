package gameGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gameEngine.TicTacToeGame;

public class TicTacToePanel extends JPanel {
	public static final int SQUARE_DEFAULT_SIZE = 32;
	public static final int ROW = 3, COL = 3;
	public static final int SCALE = 3;
	public static final int SQUARE_SIZE = SQUARE_DEFAULT_SIZE * SCALE;
	private JPanel gameplayPanel, topPanel, bottomPanel;
	private JLabel gameTitle, gameStat;
	private TicTacToeGame game;
	private Dimension gameplayPanelSize = new Dimension(SQUARE_SIZE * ROW, SQUARE_SIZE * COL);
	private ArrayList<JButton> squares;
	private JButton againBtn;

	public ArrayList<JButton> getSquares() {
		return squares;
	}

	public void setSquares(ArrayList<JButton> squares) {
		this.squares = squares;
	}

	public TicTacToePanel(TicTacToeGame game) {
		this.game = game;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.DARK_GRAY);

		addTopPanel();
		addGameplayPanel();
		addBottomPanel();
	}

	private void addBottomPanel() {
		bottomPanel = new BottomPanel();
		add(bottomPanel);
	}

	private void addTopPanel() {
		topPanel = new TopPanel();
		add(topPanel);
	}

	private void addGameplayPanel() {
		gameplayPanel = new GameplayPanel();
		add(gameplayPanel);
	}

	private class GameplayPanel extends JPanel {
		public GameplayPanel() {
			setMaximumSize(gameplayPanelSize);
			setLayout(new GridLayout(3, 3, SCALE, SCALE));
			setBackground(null);
			squares = new ArrayList<>();
			for (int i = 0; i < ROW; i++) {
				for (int j = 0; j < COL; j++) {
					JButton square = new JButton();
					square.setPreferredSize(new Dimension(SQUARE_SIZE, SQUARE_SIZE));
					square.setBackground(Color.gray);
					square.setActionCommand("square" + i + j);
					square.setBorder(null);
					square.setFocusable(false);
					square.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if (!game.gameOver() && square.getText().isBlank()) {
								square.setText(game.getCurrentPlayer() + "");
								square.setFont(new Font(null, Font.BOLD, SQUARE_SIZE));
								square.setBackground(Color.pink);

								int length = e.getActionCommand().length();
								int row = Integer.parseInt(e.getActionCommand().charAt(length - 2) + "");
								int col = Integer.parseInt(e.getActionCommand().charAt(length - 1) + "");
								game.updateBoard(row, col);
								game.switchTurn();

								switch (game.getGameStat()) {
								case X_WIN -> gameStat.setText("X WIN");
								case O_WIN -> gameStat.setText("O WIN");
								case DRAW -> gameStat.setText("DRAW");
								default -> gameStat.setText(" ");
								}
								if (game.gameOver()) {
									againBtn.setVisible(true);
								}
							}
						}
					});
					add(square);
					squares.add(square);
				}
			}
		}
	}

	private class TopPanel extends JPanel {

		public TopPanel() {
			setLayout(new GridLayout());
			setBackground(null);
			gameTitle = new JLabel("Tic Tac Toe", JLabel.CENTER);
			gameTitle.setFont(new Font("Segoe UI Black", Font.BOLD, SQUARE_SIZE / 2));
			gameTitle.setForeground(Color.ORANGE);
			add(gameTitle);
		}
	}

	private class BottomPanel extends JPanel {

		public BottomPanel() {
			setBackground(null);
			setLayout(new GridLayout(2, 1));
			gameStat = new JLabel(" ", JLabel.CENTER);
			gameStat.setFont(new Font("Segoe UI Black", Font.BOLD, SQUARE_SIZE / 3));
			gameStat.setForeground(Color.WHITE);
			add(gameStat);

			JPanel panel = new JPanel();
			panel.setBackground(null);
			againBtn = new JButton("Again");
			againBtn.setPreferredSize(new Dimension(SQUARE_SIZE, ((SQUARE_SIZE / 2) - 15)));
			againBtn.setFont(new Font(null, 1, 20));
			againBtn.setBackground(Color.gray);
			againBtn.setFocusable(false);
			againBtn.setVisible(false);
			panel.add(againBtn);

			againBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					game.reset();
					for (JButton square : squares) {
						square.setText("");
						square.setBackground(Color.gray);
					}
					gameStat.setText(" ");
					againBtn.setVisible(false);
				}
			});
			add(panel);
		}
	}
}
