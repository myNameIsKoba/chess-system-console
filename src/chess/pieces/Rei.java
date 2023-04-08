package chess.pieces;

import application.common.Color;
import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;

public class Rei extends ChessPiece{

	public Rei(Board tabuleiro, Color cor) {
		super(tabuleiro, cor);
		
	}

	@Override
	public String toString() {
		return " Ω ";
	}
	
	private boolean canMove(Position pos) {
		return !getTabuleiro().thereIsAPiece(pos) || isThereOpponentPiece(pos);	
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Position p = new Position(0,0);
		
		//movimentar para cima
		p.setValues(this.position.getRow() - 1, this.position.getCol());
		if ( getTabuleiro().posExists(p) && canMove(p) ) {
			matriz[p.getRow()][p.getCol()] = true;
		}
		
		//movimentar para baixo
		p.setValues(this.position.getRow() + 1, this.position.getCol());
		if (getTabuleiro().posExists(p) && canMove(p) ) {
			matriz[p.getRow()][p.getCol()] = true;
		}
		
		//movimentar para esquerda
		p.setValues(this.position.getRow(), this.position.getCol() - 1);
		if (getTabuleiro().posExists(p) && canMove(p)) {
			matriz[p.getRow()][p.getCol()] = true;
		}
		
		//movimentar para direita
		p.setValues(this.position.getRow(), this.position.getCol() + 1);
		if (getTabuleiro().posExists(p) && canMove(p)) {
			matriz[p.getRow()][p.getCol()] = true;
		}
		
		//movimentar para noroeste
		p.setValues(position.getRow() - 1, position.getCol() - 1);
		if (getTabuleiro().posExists(p) && canMove(p)) {
			matriz[p.getRow()][p.getCol()] = true;
		}
		
		//movimentar para nordeste
		p.setValues(position.getRow() + 1, position.getCol() + 1);
		if (getTabuleiro().posExists(p) && canMove(p)) {
			matriz[p.getRow()][p.getCol()] = true;
		}
		
		//movimentar para sudoeste
		p.setValues(position.getRow() + 1, position.getCol() - 1);
		if (getTabuleiro().posExists(p) && canMove(p)) {
			matriz[p.getRow()][p.getCol()] = true;
		}
		
		//movimentar para sudeste
		p.setValues(position.getRow() - 1, position.getCol() + 1);
		if (getTabuleiro().posExists(p) && canMove(p)) {
			matriz[p.getRow()][p.getCol()] = true;
		}
		
		return matriz;
	}
	
}
