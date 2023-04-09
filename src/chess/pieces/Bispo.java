package chess.pieces;

import application.common.Color;
import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;

public class Bispo extends ChessPiece{

	public Bispo(Board tabuleiro, Color cor) {
		super(tabuleiro, cor);
		
	}

	@Override
	public String toString() {
		return " ф ";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Position pos = new Position(0,0);
		
		//movimentar para noroeste
		pos.setValues(this.position.getRow() - 1, this.position.getCol() - 1);
		while (getTabuleiro().posExists(pos) && !getTabuleiro().thereIsAPiece(pos)) {
			
			matriz[pos.getRow()][pos.getCol()] = true;
			pos.setValues(pos.getRow() - 1, pos.getCol() - 1);
		}
		
		if(getTabuleiro().posExists(pos) && isThereOpponentPiece(pos)) {
			matriz[pos.getRow()][pos.getCol()] = true;
		}
		
		//movimentar para nordeste
		pos.setValues(this.position.getRow() - 1, this.position.getCol() + 1);
		while (getTabuleiro().posExists(pos) && !getTabuleiro().thereIsAPiece(pos)) {
					
			matriz[pos.getRow()][pos.getCol()] = true;
			pos.setValues(pos.getRow() - 1, pos.getCol() + 1);
		}
				
		if(getTabuleiro().posExists(pos) && isThereOpponentPiece(pos)) {
			matriz[pos.getRow()][pos.getCol()] = true;
		}
		
		//movimentar para sudeste
		pos.setValues(this.position.getRow() + 1, this.position.getCol() + 1);
		while (getTabuleiro().posExists(pos) && !getTabuleiro().thereIsAPiece(pos)) {
					
			matriz[pos.getRow()][pos.getCol()] = true;
			pos.setValues(pos.getRow() + 1, pos.getCol() + 1);
		}
				
		if(getTabuleiro().posExists(pos) && isThereOpponentPiece(pos)) {
			matriz[pos.getRow()][pos.getCol()] = true;
		}
		
		//movimentar para sudoeste
		pos.setValues(this.position.getRow() + 1, this.position.getCol() - 1);
		while (getTabuleiro().posExists(pos) && !getTabuleiro().thereIsAPiece(pos)) {
							
			matriz[pos.getRow()][pos.getCol()] = true;
			pos.setValues(pos.getRow() + 1, pos.getCol() - 1);
		}
						
		if(getTabuleiro().posExists(pos) && isThereOpponentPiece(pos)) {
			matriz[pos.getRow()][pos.getCol()] = true;
		}
		
		return matriz;
	}
}
