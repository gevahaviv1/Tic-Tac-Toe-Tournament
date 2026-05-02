/**
 * A <code>Player</code> implementation that places its mark on the first available cell,
 * scanning the board row by row, left to right.
 *
 * @author Geva Haviv
 */
public class NaivePlayer implements Player {

	/**
	 * Constructs a new NaivePlayer.
	 */
	public NaivePlayer() {
	}

	/**
	 * Plays a single turn by placing the mark on the first empty cell found,
	 * scanning row by row from top-left to bottom-right.
	 *
	 * @param board the <code>Board</code> on which to play.
	 * @param mark  the <code>Mark</code> assigned to this player.
	 */
	@Override
	public void playTurn(Board board, Mark mark) {
		int size = board.getSize();
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (board.putMark(mark, row, col))
					return;
			}
		}
	}
}
