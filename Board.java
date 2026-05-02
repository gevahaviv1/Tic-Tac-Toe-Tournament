/**
 * Represents the status of a Tic-Tac-Toe board.
 *
 * @author Geva Haviv
 */
public class Board {

	/* Default board size when none is specified. */
	private static final int DEFAULT_SIZE = 4;

	/* The number of rows and columns on this board. */
	private final int size;

	/* The grid storing the mark at each position. */
	private final Mark[][] board;

	/**
	 * Constructs a board with the default size.
	 */
	Board() {
		this(DEFAULT_SIZE);
	}

	/**
	 * Constructs a board with the given size.
	 *
	 * @param size the number of rows and columns on the board.
	 */
	Board(int size) {
		this.size = size;
		this.board = new Mark[size][size];
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				board[row][col] = Mark.BLANK;
			}
		}
	}

	/**
	 * Returns the size of this board.
	 *
	 * @return the number of rows and columns on the board.
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Places a mark on the board at the given row and column.
	 *
	 * @param mark the <code>Mark</code> to place.
	 * @param row  the zero-based row index.
	 * @param col  the zero-based column index.
	 * @return <code>true</code> if the mark was placed successfully,
	 * <code>false</code> if the coordinates are out of bounds or the spot is already occupied.
	 */
	public boolean putMark(Mark mark, int row, int col) {
		if (row >= size || row < 0 || col >= size || col < 0)
			return false;
		if (board[row][col] != Mark.BLANK)
			return false;
		board[row][col] = mark;
		return true;
	}
}