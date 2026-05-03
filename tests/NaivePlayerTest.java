import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the <code>NaivePlayer</code> class.
 *
 * @author Geva Haviv
 */
public class NaivePlayerTest {

	/* Board size used across all tests. */
	private static final int BOARD_SIZE = 4;

	@Test
	public void testFirstMoveAtTopLeft() {
		Board board = new Board(BOARD_SIZE);
		NaivePlayer player = new NaivePlayer();
		player.playTurn(board, Mark.X);
		assertEquals(Mark.X, board.getMark(0, 0));
	}

	@Test
	public void testSecondMoveFillsNextCell() {
		Board board = new Board(BOARD_SIZE);
		NaivePlayer player = new NaivePlayer();
		player.playTurn(board, Mark.X);
		player.playTurn(board, Mark.O);
		assertEquals(Mark.X, board.getMark(0, 0));
		assertEquals(Mark.O, board.getMark(0, 1));
	}

	@Test
	public void testFillsFirstRowLeftToRight() {
		Board board = new Board(BOARD_SIZE);
		NaivePlayer player = new NaivePlayer();
		for (int col = 0; col < BOARD_SIZE; col++) {
			player.playTurn(board, Mark.X);
		}
		for (int col = 0; col < BOARD_SIZE; col++) {
			assertEquals(Mark.X, board.getMark(0, col));
		}
	}

	@Test
	public void testWrapsToNextRow() {
		Board board = new Board(BOARD_SIZE);
		NaivePlayer player = new NaivePlayer();
		/* Fill entire first row. */
		for (int col = 0; col < BOARD_SIZE; col++) {
			player.playTurn(board, Mark.X);
		}
		/* Next move should go to (1, 0). */
		player.playTurn(board, Mark.O);
		assertEquals(Mark.O, board.getMark(1, 0));
	}

	@Test
	public void testSkipsOccupiedCells() {
		Board board = new Board(BOARD_SIZE);
		board.putMark(Mark.O, 0, 0);
		board.putMark(Mark.O, 0, 1);
		NaivePlayer player = new NaivePlayer();
		player.playTurn(board, Mark.X);
		assertEquals(Mark.X, board.getMark(0, 2));
	}

	@Test
	public void testFillsEntireBoardInOrder() {
		Board board = new Board(BOARD_SIZE);
		NaivePlayer player = new NaivePlayer();
		int totalCells = BOARD_SIZE * BOARD_SIZE;
		for (int i = 0; i < totalCells; i++) {
			player.playTurn(board, Mark.X);
		}
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				assertEquals(Mark.X, board.getMark(row, col));
			}
		}
	}
}
