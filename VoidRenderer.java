/**
 * A no-op implementation of <code>Renderer</code> that does not render anything.
 * Used to run a game without printing the board to the screen.
 *
 * @author Geva Haviv
 */
public class VoidRenderer implements Renderer {

	/**
	 * Does nothing.
	 *
	 * @param board the <code>Board</code> to render.
	 */
	@Override
	public void renderBoard(Board board) {
	}
}
