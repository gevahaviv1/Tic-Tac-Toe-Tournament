import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the <code>Game</code> class.
 *
 * @author Geva Haviv
 */
public class GameTest {

	/* Board size used across all game tests. */
	private static final int BOARD_SIZE = 4;

	/* Win streak used across all game tests. */
	private static final int WIN_STREAK = 3;

	/* A Player that places marks at predetermined coordinates. */
	private static class ScriptedPlayer implements Player {

		/* Sequence of row values to play in order. */
		private final int[] rows;

		/* Sequence of column values to play in order. */
		private final int[] cols;

		/* Index of the next move to play. */
		private int moveIndex;

		private ScriptedPlayer(int[] rows, int[] cols) {
			this.rows = rows;
			this.cols = cols;
			this.moveIndex = 0;
		}

		@Override
		public void playTurn(Board board, Mark mark) {
			board.putMark(mark, rows[moveIndex], cols[moveIndex]);
			moveIndex++;
		}
	}

	@Test
	public void testGettersWithDefaults() {
		Game game = new Game(
				new ScriptedPlayer(new int[]{}, new int[]{}),
				new ScriptedPlayer(new int[]{}, new int[]{}),
				new VoidRenderer());
		assertEquals(BOARD_SIZE, game.getBoardSize());
		assertEquals(WIN_STREAK, game.getWinStreak());
	}

	@Test
	public void testGettersWithCustomValues() {
		int customSize = 5;
		int customStreak = 4;
		Game game = new Game(
				new ScriptedPlayer(new int[]{}, new int[]{}),
				new ScriptedPlayer(new int[]{}, new int[]{}),
				customSize, customStreak, new VoidRenderer());
		assertEquals(customSize, game.getBoardSize());
		assertEquals(customStreak, game.getWinStreak());
	}

	@Test
	public void testHorizontalWinX() {
		/* X plays (0,0), (0,1), (0,2) — horizontal streak of 3 on row 0.
		   O plays (1,0), (1,1) — does not complete a streak. */
		ScriptedPlayer playerX = new ScriptedPlayer(
				new int[]{0, 0, 0}, new int[]{0, 1, 2});
		ScriptedPlayer playerO = new ScriptedPlayer(
				new int[]{1, 1}, new int[]{0, 1});
		Game game = new Game(playerX, playerO, BOARD_SIZE, WIN_STREAK, new VoidRenderer());
		assertEquals(Mark.X, game.run());
	}

	@Test
	public void testVerticalWinX() {
		/* X plays (0,0), (1,0), (2,0) — vertical streak of 3 on column 0.
		   O plays (0,1), (1,1) — does not complete a streak. */
		ScriptedPlayer playerX = new ScriptedPlayer(
				new int[]{0, 1, 2}, new int[]{0, 0, 0});
		ScriptedPlayer playerO = new ScriptedPlayer(
				new int[]{0, 1}, new int[]{1, 1});
		Game game = new Game(playerX, playerO, BOARD_SIZE, WIN_STREAK, new VoidRenderer());
		assertEquals(Mark.X, game.run());
	}

	@Test
	public void testDiagonalWinX() {
		/* X plays (0,0), (1,1), (2,2) — main diagonal streak of 3.
		   O plays (0,1), (0,2) — does not complete a streak. */
		ScriptedPlayer playerX = new ScriptedPlayer(
				new int[]{0, 1, 2}, new int[]{0, 1, 2});
		ScriptedPlayer playerO = new ScriptedPlayer(
				new int[]{0, 0}, new int[]{1, 2});
		Game game = new Game(playerX, playerO, BOARD_SIZE, WIN_STREAK, new VoidRenderer());
		assertEquals(Mark.X, game.run());
	}

	@Test
	public void testAntiDiagonalWinX() {
		/* X plays (0,2), (1,1), (2,0) — anti-diagonal streak of 3.
		   O plays (3,0), (3,1) — does not complete a streak. */
		ScriptedPlayer playerX = new ScriptedPlayer(
				new int[]{0, 1, 2}, new int[]{2, 1, 0});
		ScriptedPlayer playerO = new ScriptedPlayer(
				new int[]{3, 3}, new int[]{0, 1});
		Game game = new Game(playerX, playerO, BOARD_SIZE, WIN_STREAK, new VoidRenderer());
		assertEquals(Mark.X, game.run());
	}

	@Test
	public void testHorizontalWinO() {
		/* X plays (3,0), (3,1), (3,2) — spread across row 3, no streak before O wins.
		   O plays (1,0), (1,1), (1,2) — horizontal streak of 3 on row 1. */
		ScriptedPlayer playerX = new ScriptedPlayer(
				new int[]{3, 3, 3}, new int[]{0, 1, 2});
		ScriptedPlayer playerO = new ScriptedPlayer(
				new int[]{1, 1, 1}, new int[]{0, 1, 2});
		Game game = new Game(playerX, playerO, BOARD_SIZE, WIN_STREAK, new VoidRenderer());
		assertEquals(Mark.O, game.run());
	}

	@Test
	public void testDrawOnFullBoard() {
		/* Fill a 3x3 board with winStreak=3 in a pattern that produces no winner.
		   X: (0,0),(0,2),(1,1),(2,0),(2,1) — no streak of 3.
		   O: (0,1),(1,0),(1,2),(2,2) — no streak of 3. */
		int smallSize = 3;
		ScriptedPlayer playerX = new ScriptedPlayer(
				new int[]{0, 0, 1, 2, 2}, new int[]{0, 2, 1, 0, 1});
		ScriptedPlayer playerO = new ScriptedPlayer(
				new int[]{0, 1, 1, 2}, new int[]{1, 0, 2, 2});
		Game game = new Game(playerX, playerO, smallSize, smallSize, new VoidRenderer());
		assertEquals(Mark.BLANK, game.run());
	}

	@Test
	public void testXAlwaysGoesFirst() {
		/* X places at (0,0) first. Verify the mark at (0,0) is X after the game. */
		ScriptedPlayer playerX = new ScriptedPlayer(
				new int[]{0, 0, 0}, new int[]{0, 1, 2});
		ScriptedPlayer playerO = new ScriptedPlayer(
				new int[]{1, 1}, new int[]{0, 1});
		Game game = new Game(playerX, playerO, BOARD_SIZE, WIN_STREAK, new VoidRenderer());
		Mark result = game.run();
		assertEquals(Mark.X, result);
	}
}
