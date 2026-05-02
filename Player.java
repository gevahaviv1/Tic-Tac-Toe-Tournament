/**
 * Defines the contract for a Tic-Tac-Toe player strategy.
 *
 * @author Geva Haviv
 */
public interface Player {

	/**
	 * Plays a single turn by placing the given mark on the board.
	 *
	 * @param board the <code>Board</code> on which to play.
	 * @param mark  the <code>Mark</code> assigned to this player.
	 */
	void playTurn(Board board, Mark mark);
}
