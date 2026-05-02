/**
 * A <code>Player</code> implementation that uses the minimax algorithm to choose
 * the best available move, winning against both <code>WhateverPlayer</code> and
 * <code>NaivePlayer</code> in the majority of games.
 *
 * @author Geva Haviv
 */
public class SmartPlayer implements Player {

	/* Score assigned to a winning board state for the maximizing player. */
	private static final int WIN_SCORE = 1000;

	/* Score assigned to a losing board state for the maximizing player. */
	private static final int LOSE_SCORE = -1000;

	/* Score for a drawn or undecided board state. */
	private static final int DRAW_SCORE = 0;

	/* Maximum recursion depth for the minimax search. */
	private static final int MAX_DEPTH = 5;

	/**
	 * Constructs a new SmartPlayer.
	 */
	public SmartPlayer() {
	}

	/**
	 * Plays a single turn by selecting the best move via the minimax algorithm.
	 *
	 * @param board the <code>Board</code> on which to play.
	 * @param mark  the <code>Mark</code> assigned to this player.
	 */
	@Override
	public void playTurn(Board board, Mark mark) {
		int size = board.getSize();
		int bestScore = Integer.MIN_VALUE;
		int bestRow = 0;
		int bestCol = 0;
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (board.putMark(mark, row, col)) {
					int score = minimax(board, mark, getOpponent(mark), MAX_DEPTH, false);
					board.putMark(Mark.BLANK, row, col);
					if (score > bestScore) {
						bestScore = score;
						bestRow = row;
						bestCol = col;
					}
				}
			}
		}
		board.putMark(mark, bestRow, bestCol);
	}

	/* Recursively evaluates board for player self against current mover using minimax.
	   Returns the best score reachable from this state within the given depth. */
	private int minimax(Board board, Mark self, Mark current,
						int depth, boolean isMaximizing) {
		int size = board.getSize();
		if (checkWin(board, self, size))
			return WIN_SCORE + depth;
		if (checkWin(board, getOpponent(self), size))
			return LOSE_SCORE - depth;
		if (depth == 0 || isBoardFull(board, size))
			return DRAW_SCORE;

		int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (board.putMark(current, row, col)) {
					int score = minimax(board, self, getOpponent(current),
							depth - 1, !isMaximizing);
					board.putMark(Mark.BLANK, row, col);
					if (isMaximizing)
						bestScore = Math.max(bestScore, score);
					else
						bestScore = Math.min(bestScore, score);
				}
			}
		}
		return bestScore;
	}

	/* Returns the opponent of the given mark: X returns O and vice versa. */
	private Mark getOpponent(Mark mark) {
		if (mark == Mark.X) {
			return Mark.O;
		}
		return Mark.X;
	}

	/* Returns true if mark has a complete row, column, or diagonal on board of the given size. */
	private boolean checkWin(Board board, Mark mark, int size) {
		for (int i = 0; i < size; i++) {
			if (checkRow(board, mark, i, size) || checkCol(board, mark, i, size))
				return true;
		}
		return checkDiagonal(board, mark, size) || checkAntiDiagonal(board, mark, size);
	}

	/* Returns true if mark fills every cell in the specified row on the given board. */
	private boolean checkRow(Board board, Mark mark, int row, int size) {
		for (int col = 0; col < size; col++) {
			if (board.getMark(row, col) != mark)
				return false;
		}
		return true;
	}

	/* Returns true if mark fills every cell in the specified column on the given board. */
	private boolean checkCol(Board board, Mark mark, int col, int size) {
		for (int row = 0; row < size; row++) {
			if (board.getMark(row, col) != mark)
				return false;
		}
		return true;
	}

	/* Returns true if mark fills the main diagonal of a board with the given size. */
	private boolean checkDiagonal(Board board, Mark mark, int size) {
		for (int i = 0; i < size; i++) {
			if (board.getMark(i, i) != mark)
				return false;
		}
		return true;
	}

	/* Returns true if mark fills the anti-diagonal of a board with the given size. */
	private boolean checkAntiDiagonal(Board board, Mark mark, int size) {
		for (int i = 0; i < size; i++) {
			if (board.getMark(i, size - 1 - i) != mark)
				return false;
		}
		return true;
	}

	/* Returns true if every cell on the given board of the given size is occupied. */
	private boolean isBoardFull(Board board, int size) {
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (board.getMark(row, col) == Mark.BLANK)
					return false;
			}
		}
		return true;
	}
}
