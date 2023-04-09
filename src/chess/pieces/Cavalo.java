package chess.pieces;

import application.common.Color;
import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;

public class Cavalo extends ChessPiece{

	public Cavalo(Board tabuleiro, Color cor) {
		super(tabuleiro, cor);
		
	}

	@Override
	public String toString() {
		return " Ѓ ";
	}
	
	private boolean canMove(Position pos) {
		return !getTabuleiro().thereIsAPiece(pos) || isThereOpponentPiece(pos);	
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Position p = new Position(0,0);
		
		p.setValues(this.position.getRow() - 1, this.position.getCol() - 2);
		if ( getTabuleiro().posExists(p) && canMove(p) ) {
			matriz[p.getRow()][p.getCol()] = true;
		}
		
		p.setValues(this.position.getRow() - 2, this.position.getCol() - 1);
		if (getTabuleiro().posExists(p) && canMove(p) ) {
			matriz[p.getRow()][p.getCol()] = true;
		}
		
		p.setValues(this.position.getRow() - 2, this.position.getCol() + 1);
		if (getTabuleiro().posExists(p) && canMove(p)) {
			matriz[p.getRow()][p.getCol()] = true;
		}
		
		p.setValues(this.position.getRow() - 1, this.position.getCol() + 2);
		if (getTabuleiro().posExists(p) && canMove(p)) {
			matriz[p.getRow()][p.getCol()] = true;
		}

		p.setValues(this.position.getRow() + 1, this.position.getCol() + 2);
		if (getTabuleiro().posExists(p) && canMove(p)) {
			matriz[p.getRow()][p.getCol()] = true;
		}

		p.setValues(this.position.getRow() + 2, this.position.getCol() + 1);
		if (getTabuleiro().posExists(p) && canMove(p)) {
			matriz[p.getRow()][p.getCol()] = true;
		}

		p.setValues(this.position.getRow() + 2, this.position.getCol() - 1);
		if (getTabuleiro().posExists(p) && canMove(p)) {
			matriz[p.getRow()][p.getCol()] = true;
		}

		p.setValues(this.position.getRow() + 1, this.position.getCol() - 1);
		if (getTabuleiro().posExists(p) && canMove(p)) {
			matriz[p.getRow()][p.getCol()] = true;
		}
		
		return matriz;
	}
	
}