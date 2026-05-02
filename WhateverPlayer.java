import java.util.Random;

/**
 * A <code>Player</code> implementation that places its mark on a random empty cell.
 *
 * @author Geva Haviv
 */
public class WhateverPlayer implements Player {

	/* Random number generator used to pick empty cells. */
	private final Random random = new Random();

	/**
	 * Constructs a new WhateverPlayer.
	 */
	public WhateverPlayer() {
	}

	/**
	 * Plays a single turn by placing the mark on a randomly chosen empty cell.
	 *
	 * @param board the <code>Board</code> on which to play.
	 * @param mark  the <code>Mark</code> assigned to this player.
	 */
	@Override
	public void playTurn(Board board, Mark mark) {
		int size = board.getSize();
		int row;
		int col;
		do {
			row = random.nextInt(size);
			col = random.nextInt(size);
		} while (!board.putMark(mark, row, col));
	}
}
