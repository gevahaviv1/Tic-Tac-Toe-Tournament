/**
 * Defines the contract for rendering a Tic-Tac-Toe board.
 *
 * @author Geva Haviv
 */
public interface Renderer {

	/**
	 * Renders the given board.
	 *
	 * @param board the <code>Board</code> to render.
	 */
	void renderBoard(Board board);
}
