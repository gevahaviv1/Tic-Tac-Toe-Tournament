/**
 * Represents the possible marks on a Tic-Tac-Toe board.
 *
 * @author Geva Haviv
 */
public enum Mark {
	BLANK, X, O;

	/* String representation of the X mark. */
	private static final String X_SYMBOL = "X";

	/* String representation of the O mark. */
	private static final String O_SYMBOL = "O";

	/**
	 * Returns the string representation of this mark.
	 *
	 * @return "X" for <code>X</code>, "O" for <code>O</code>, or <code>null</code>
	 *         for
	 *         <code>BLANK</code>.
	 */
	@Override
	public String toString() {
		switch (this) {
		case X:
			return X_SYMBOL;
		case O:
			return O_SYMBOL;
		case BLANK:
			return null;
		}
		return "";
	}
}