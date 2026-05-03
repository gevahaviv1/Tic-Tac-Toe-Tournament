/**
 * Represents a single Tic-Tac-Toe game between two players.
 * Manages the game flow, determines when the game ends, and returns the winner.
 *
 * @author Geva Haviv
 */
public class Game {

	/* Default board size used when no size is specified. */
	private static final int DEFAULT_SIZE = 4;

	/* Default win streak length used when none is specified. */
	private static final int DEFAULT_WIN_STREAK = 3;

	/* The player assigned the X mark. */
	private final Player playerX;

	/* The player assigned the O mark. */
	private final Player playerO;

	/* The size of the board. */
	private final int size;

	/* The number of consecutive marks required to win. */
	private final int winStreak;

	/* The renderer used to display the board after each turn. */
	private final Renderer renderer;

	/**
	 * Constructs a new game with default board size and win streak.
	 *
	 * @param playerX  the player assigned the <code>X</code> mark.
	 * @param playerO  the player assigned the <code>O</code> mark.
	 * @param renderer the <code>Renderer</code> used to display the board.
	 */
	public Game(Player playerX, Player playerO, Renderer renderer) {
		this(playerX, playerO, DEFAULT_SIZE, DEFAULT_WIN_STREAK, renderer);
	}

	/**
	 * Constructs a new game with the given board size and win streak.
	 *
	 * @param playerX   the player assigned the <code>X</code> mark.
	 * @param playerO   the player assigned the <code>O</code> mark.
	 * @param size      the number of rows and columns on the board.
	 * @param winStreak the number of consecutive marks required to win.
	 * @param renderer  the <code>Renderer</code> used to display the board.
	 */
	public Game(Player playerX, Player playerO, int size, int winStreak, Renderer renderer) {
		this.playerX = playerX;
		this.playerO = playerO;
		this.size = size;
		this.winStreak = winStreak;
		this.renderer = renderer;
	}

	/**
	 * Returns the win streak length of this game.
	 *
	 * @return the number of consecutive marks required to win.
	 */
	public int getWinStreak() {
		return winStreak;
	}

	/**
	 * Returns the board size of this game.
	 *
	 * @return the number of rows and columns on the board.
	 */
	public int getBoardSize() {
		return size;
	}

	/**
	 * Runs the game from start to finish and returns the winning mark.
	 *
	 * @return the <code>Mark</code> of the winner, or <code>Mark.BLANK</code> if the game is a draw.
	 */
	public Mark run() {
		Board board = new Board(size);
		Mark[] turnOrder = {Mark.X, Mark.O};
		Player[] players = {playerX, playerO};
		int totalCells = size * size;
		for (int turn = 0; turn < totalCells; turn++) {
			Mark currentMark = turnOrder[turn % 2];
			Player currentPlayer = players[turn % 2];
			currentPlayer.playTurn(board, currentMark);
			renderer.renderBoard(board);
			if (checkWin(board, currentMark)) {
				return currentMark;
			}
		}
		return Mark.BLANK;
	}

	/* Returns true if the given mark has a winning streak on the board. */
	private boolean checkWin(Board board, Mark mark) {
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (checkStreak(board, mark, row, col)) {
					return true;
				}
			}
		}
		return false;
	}

	/* Returns true if mark starts a winning streak from (row, col) in any direction. */
	private boolean checkStreak(Board board, Mark mark, int row, int col) {
		return checkDirection(board, mark, row, col, 1, 0)
				|| checkDirection(board, mark, row, col, 0, 1)
				|| checkDirection(board, mark, row, col, 1, 1)
				|| checkDirection(board, mark, row, col, 1, -1);
	}

	/* Returns true if mark has winStreak consecutive marks starting at (row,col) in direction (dr,dc). */
	private boolean checkDirection(Board board, Mark mark, int row, int col, int dr, int dc) {
		for (int step = 0; step < winStreak; step++) {
			if (board.getMark(row + step * dr, col + step * dc) != mark) {
				return false;
			}
		}
		return true;
	}
}
