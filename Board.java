public class Board {

	private final int size;

	Board() {
		size = 4;
	}

	Board(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	boolean putMark(Mark mark, int row, int col) {
		if (row >= size || row < 0 || col >= size || col < 0)
			return false;
		return mark != null;
	}
}