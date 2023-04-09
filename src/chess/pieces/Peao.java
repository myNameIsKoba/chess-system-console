package chess.pieces;

import application.common.Color;
import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;

public class Peao extends ChessPiece{

	public Peao(Board tabuleiro, Color cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return " Δ ";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Position p = new Position(0,0);
		
		if (getCor() == Color.WHITE) {
			
			p.setValues( position.getRow() - 1, position.getCol() );
			if (getTabuleiro().posExists(p) && !getTabuleiro().thereIsAPiece(p)) {
				matriz[p.getRow()][p.getCol()] = true;
			}
			
			p.setValues( position.getRow() - 2, position.getCol() );
			Position pos2 = new Position( position.getRow() - 1, position.getCol() );
			if (getTabuleiro().posExists(p) 			&& 
					!getTabuleiro().thereIsAPiece(p)    &&
					
					getTabuleiro().posExists(pos2)      &&
					!getTabuleiro().thereIsAPiece(pos2) &&
					getMoveCount() == 0) {
				
				matriz[p.getRow()][p.getCol()] = true;
			}
			
			p.setValues( position.getRow() - 1, position.getCol() - 1);
			if (getTabuleiro().posExists(p) && isThereOpponentPiece(p)) {
				matriz[p.getRow()][p.getCol()] = true;
			}
			
			p.setValues( position.getRow() - 1, position.getCol() + 1);
			if (getTabuleiro().posExists(p) && isThereOpponentPiece(p)) {
				matriz[p.getRow()][p.getCol()] = true;
			}
			
		}
		else {
			p.setValues( position.getRow() + 1, position.getCol() );
			if (getTabuleiro().posExists(p) && !getTabuleiro().thereIsAPiece(p)) {
				matriz[p.getRow()][p.getCol()] = true;
			}
			
			p.setValues( position.getRow() + 2, position.getCol() );
			Position pos2 = new Position( position.getRow() + 1, position.getCol() );
			if (getTabuleiro().posExists(p) 			&& 
					!getTabuleiro().thereIsAPiece(p)    &&
					
					getTabuleiro().posExists(pos2)      &&
					!getTabuleiro().thereIsAPiece(pos2) &&
					getMoveCount() == 0) {
				
				matriz[p.getRow()][p.getCol()] = true;
			}
			
			p.setValues( position.getRow() + 1, position.getCol() + 1);
			if (getTabuleiro().posExists(p) && isThereOpponentPiece(p)) {
				matriz[p.getRow()][p.getCol()] = true;
			}
			
			p.setValues( position.getRow() + 1, position.getCol() - 1);
			if (getTabuleiro().posExists(p) && isThereOpponentPiece(p)) {
				matriz[p.getRow()][p.getCol()] = true;
			}
		}
		
		return matriz;
	}

}
