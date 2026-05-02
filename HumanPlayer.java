/**
 * Represents a human player in a Tic-Tac-Toe game.
 * Input is received from the user via the keyboard.
 *
 * @author Geva Haviv
 */
public class HumanPlayer implements Player {

	/* Divisor used to extract the row digit from the input number. */
	private static final int ROW_DIVISOR = 10;

	/* Modulus used to extract the column digit from the input number. */
	private static final int COL_MODULUS = 10;

	/* Prompt printed before requesting coordinates from the player. */
	private static final String PROMPT_FORMAT = "Player %s, type coordinates:";

	/* Error message for out-of-bounds or otherwise invalid coordinates. */
	private static final String INVALID_POSITION_MSG =
			"Invalid mark position. Please choose a valid position:";

	/* Error message when the chosen cell is already occupied. */
	private static final String OCCUPIED_POSITION_MSG =
			"Mark position is already occupied. Please choose a valid position:";

	/**
	 * Constructs a new human player.
	 */
	public HumanPlayer() {
	}

	/**
	 * Plays a single turn by reading the desired position from the user.
	 * Keeps prompting until a valid, unoccupied position is entered.
	 *
	 * @param board the <code>Board</code> on which to play.
	 * @param mark  the <code>Mark</code> assigned to this player.
	 */
	@Override
	public void playTurn(Board board, Mark mark) {
		System.out.println(String.format(PROMPT_FORMAT, mark));
		while (true) {
			int input = KeyboardInput.readInt();
			int row = input / ROW_DIVISOR;
			int col = input % COL_MODULUS;
			if (row >= board.getSize() || row < 0 || col >= board.getSize() || col < 0) {
				System.out.println(INVALID_POSITION_MSG);
				continue;
			}
			if (!board.putMark(mark, row, col)) {
				System.out.println(OCCUPIED_POSITION_MSG);
				continue;
			}
			break;
		}
	}
}
