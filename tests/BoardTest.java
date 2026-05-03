import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the <code>Board</code> class.
 *
 * @author Geva Haviv
 */
public class BoardTest {

	/* Default board size matching the Board default constructor. */
	private static final int DEFAULT_SIZE = 4;

	/* Custom board size used in parameterized tests. */
	private static final int CUSTOM_SIZE = 6;

	@Test
	public void testDefaultConstructorSize() {
		Board board = new Board();
		assertEquals(DEFAULT_SIZE, board.getSize());
	}

	@Test
	public void testCustomConstructorSize() {
		Board board = new Board(CUSTOM_SIZE);
		assertEquals(CUSTOM_SIZE, board.getSize());
	}

	@Test
	public void testDefaultBoardAllBlank() {
		Board board = new Board();
		for (int row = 0; row < DEFAULT_SIZE; row++) {
			for (int col = 0; col < DEFAULT_SIZE; col++) {
				assertEquals(Mark.BLANK, board.getMark(row, col));
			}
		}
	}

	@Test
	public void testPutMarkSuccess() {
		Board board = new Board();
		assertTrue(board.putMark(Mark.X, 0, 0));
		assertEquals(Mark.X, board.getMark(0, 0));
	}

	@Test
	public void testPutMarkOccupiedCell() {
		Board board = new Board();
		board.putMark(Mark.X, 1, 1);
		assertFalse(board.putMark(Mark.O, 1, 1));
		assertEquals(Mark.X, board.getMark(1, 1));
	}

	@Test
	public void testPutMarkBlankOverwrite() {
		Board board = new Board();
		board.putMark(Mark.X, 0, 0);
		assertTrue(board.putMark(Mark.BLANK, 0, 0));
		assertEquals(Mark.BLANK, board.getMark(0, 0));
	}

	@Test
	public void testPutMarkNegativeRow() {
		Board board = new Board();
		assertFalse(board.putMark(Mark.X, -1, 0));
	}

	@Test
	public void testPutMarkNegativeCol() {
		Board board = new Board();
		assertFalse(board.putMark(Mark.X, 0, -1));
	}

	@Test
	public void testPutMarkRowTooLarge() {
		Board board = new Board();
		assertFalse(board.putMark(Mark.X, DEFAULT_SIZE, 0));
	}

	@Test
	public void testPutMarkColTooLarge() {
		Board board = new Board();
		assertFalse(board.putMark(Mark.X, 0, DEFAULT_SIZE));
	}

	@Test
	public void testGetMarkOutOfBoundsReturnsBlank() {
		Board board = new Board();
		assertEquals(Mark.BLANK, board.getMark(-1, 0));
		assertEquals(Mark.BLANK, board.getMark(0, -1));
		assertEquals(Mark.BLANK, board.getMark(DEFAULT_SIZE, 0));
		assertEquals(Mark.BLANK, board.getMark(0, DEFAULT_SIZE));
	}

	@Test
	public void testGetMarkValidPosition() {
		Board board = new Board();
		board.putMark(Mark.O, 2, 3);
		assertEquals(Mark.O, board.getMark(2, 3));
	}

	@Test
	public void testMultipleMarksOnBoard() {
		Board board = new Board();
		board.putMark(Mark.X, 0, 0);
		board.putMark(Mark.O, 0, 1);
		board.putMark(Mark.X, 1, 0);
		assertEquals(Mark.X, board.getMark(0, 0));
		assertEquals(Mark.O, board.getMark(0, 1));
		assertEquals(Mark.X, board.getMark(1, 0));
		assertEquals(Mark.BLANK, board.getMark(1, 1));
	}
}
