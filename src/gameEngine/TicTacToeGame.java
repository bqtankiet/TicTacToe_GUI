package gameEngine;

import gameGUI.*;

public class TicTacToeGame {
	public static final char PLAYER_X = '✕', PLAYER_O = '○';
	private TicTacToeFrame ticTacToeFrame;
	private TicTacToePanel ticTacToePanel;
	private char currentPlayer, player1, player2, computer;
	private char[][] board;

	public TicTacToeGame() {
		initGameGUI();
		startGameLoop();
	}

	private void startGameLoop() {
		initGameBoard();
		player1 = PLAYER_X;
		player2 = PLAYER_O;
		currentPlayer = player1;
	}

	public boolean gameOver() {
		return (getGameStat() == GameStat.X_WIN || getGameStat() == GameStat.O_WIN || getGameStat() == GameStat.DRAW);
	}

	public GameStat getGameStat() {
		// Win case:
		String diagonal1 = "" + board[0][0] + board[1][1] + board[2][2];
		String diagonal2 = "" + board[0][2] + board[1][1] + board[2][0];
		String row1 = "" + board[0][0] + board[0][1] + board[0][2];
		String row2 = "" + board[1][0] + board[1][1] + board[1][2];
		String row3 = "" + board[2][0] + board[2][1] + board[2][2];
		String col1 = "" + board[0][0] + board[1][0] + board[2][0];
		String col2 = "" + board[0][1] + board[1][1] + board[2][1];
		String col3 = "" + board[0][2] + board[1][2] + board[2][2];
		String[] winCases = { diagonal1, diagonal2, row1, row2, row3, col1, col2, col3 };
		for (String cases : winCases) {
			if (cases.equals("" + PLAYER_X + PLAYER_X + PLAYER_X)) {
				return GameStat.X_WIN;
			}
			if (cases.equals("" + PLAYER_O + PLAYER_O + PLAYER_O)) {
				return GameStat.O_WIN;
			}
		}
		// Draw case:
		if (!(row1 + row2 + row3).contains(" ")) {
			return GameStat.DRAW;
		}
		return GameStat.NONE;
	}

	public char getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(char currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public void switchTurn() {
		currentPlayer = currentPlayer == player1 ? player2 : player1;
	}

	public void updateBoard(int row, int col) {
		board[row][col] = currentPlayer;
	}

	private void initGameGUI() {
		ticTacToePanel = new TicTacToePanel(this);
		ticTacToeFrame = new TicTacToeFrame(ticTacToePanel);
	}

	private void initGameBoard() {
		board = new char[3][3];
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				board[row][col] = ' ';
			}
		}
	}

	public void printBoard() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				System.out.print(board[row][col]);
			}
			System.out.println();
		}

	}

	public void reset() {
		player1 = PLAYER_X;
		player2 = PLAYER_O;
		currentPlayer = player1;
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				board[row][col] = ' ';
			}
		}
	}
}
