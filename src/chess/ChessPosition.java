package chess;

import boardgame.Position;
import chess.exception.ChessException;

public class ChessPosition {

	private char column;
	private Integer row;
	
	public ChessPosition(char column, Integer row) {
		if (column < 'a' || column > 'h' || row < 1 || row > 8) {
			throw new ChessException(" Posição inválida:\n valores válidos são aceitos entre a1 e h8 !");
		}
		
		this.column = column;
		this.row = row;
	}

	public char getColumn() {
		return column;
	}

	public Integer getRow() {
		return row;
	}
	
	protected Position toPos() {
		System.out.println(new Position(8 - row, column - 'a'));
		return new Position(8 - row, column - 'a');
	}
	
	protected static ChessPosition fromPosition(Position pos) {	
		return new ChessPosition((char)('a' - pos.getCol()), 8 - pos.getRow());
	}

	@Override
	public String toString() {
		return "" + column + row;
	}
}
