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
		return Mark.BLANK;
	}
}
