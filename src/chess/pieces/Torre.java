package chess.pieces;

import application.common.Color;
import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;

/**
 * Herança aplicada
 * @author User
 *
 */
public class Torre extends ChessPiece{

	public Torre(Board tabuleiro, Color cor) {
		super(tabuleiro, cor);
		
	}

	@Override
	public String toString() {
//		return " ∏ ";
		return " T "; // ou R
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Position pos = new Position(0,0);
		
		//movimentar para acima
		pos.setValues(this.position.getRow() - 1, this.position.getCol());
		while (getTabuleiro().posExists(pos) && !getTabuleiro().thereIsAPiece(pos)) {
			
			matriz[pos.getRow()][pos.getCol()] = true;
			pos.setRow(pos.getRow() - 1);
		}
		
		if(getTabuleiro().posExists(pos) && isThereOpponentPiece(pos)) {
			matriz[pos.getRow()][pos.getCol()] = true;
		}
		
		//movimentar para abaixo
		pos.setValues(this.position.getRow() + 1, this.position.getCol());
		while (getTabuleiro().posExists(pos) && !getTabuleiro().thereIsAPiece(pos)) {
					
			matriz[pos.getRow()][pos.getCol()] = true;
			pos.setRow(pos.getRow() + 1);
		}
				
		if(getTabuleiro().posExists(pos) && isThereOpponentPiece(pos)) {
			matriz[pos.getRow()][pos.getCol()] = true;
		}
		
		//movimentar para esquerda
		pos.setValues(this.position.getRow(), this.position.getCol() - 1);
		while (getTabuleiro().posExists(pos) && !getTabuleiro().thereIsAPiece(pos)) {
					
			matriz[pos.getRow()][pos.getCol()] = true;
			pos.setCol(pos.getCol() - 1);
		}
				
		if(getTabuleiro().posExists(pos) && isThereOpponentPiece(pos)) {
			matriz[pos.getRow()][pos.getCol()] = true;
		}
		
		//movimentar para direita
		pos.setValues(this.position.getRow(), this.position.getCol() + 1);
		while (getTabuleiro().posExists(pos) && !getTabuleiro().thereIsAPiece(pos)) {
							
			matriz[pos.getRow()][pos.getCol()] = true;
			pos.setCol(pos.getCol() + 1);
		}
						
		if(getTabuleiro().posExists(pos) && isThereOpponentPiece(pos)) {
			matriz[pos.getRow()][pos.getCol()] = true;
		}
		
		return matriz;
	}
}
