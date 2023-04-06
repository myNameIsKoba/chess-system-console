package boardgame;

public class Position {

	private Integer row;
	private Integer col;
	
	public Position(Integer row, Integer col) {
		this.row = row;
		this.col = col;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getCol() {
		return col;
	}

	public void setCol(Integer col) {
		this.col = col;
	}
	
	@Override
	public String toString() {
		return row + ", " + col;
	}
}
