package chess.pieces;

import application.common.Color;
import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;

public class Rei extends ChessPiece{

	private ChessMatch match;
	
	public Rei(Board tabuleiro, Color cor, ChessMatch m) {
		super(tabuleiro, cor);
		this.match = m;
	}

	@Override
	public String toString() {
		return " Щ ";
	}
	
	/**
	 * Verifica se a torre esta apta para o "Roque"
	 * @param pos
	 * @return
	 */
	private boolean isRookCastling(Position pos) {
		ChessPiece p = (ChessPiece)getTabuleiro().piece(pos);
		return  p != null && 
				p instanceof Torre && 
				p.getCor() == getCor() && 
				p.getMoveCount() == 0;
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
		
		/////#specialmove -  ROQUE
		if (getMoveCount() == 0 && !match.getCheck()) {
			//// ROQUE  pequeno (kingside rook)
			Position posT1 = new Position(position.getRow(), position.getCol() + 3);
			
			if (isRookCastling(posT1)) {
				Position p1 = new Position(position.getRow(), position.getCol() + 1);
				Position p2 = new Position(position.getRow(), position.getCol() + 2);
				
				if (getTabuleiro().piece(p1) == null && 
					getTabuleiro().piece(p2) == null) {
					
					// move o rei duas casas para a direita
					matriz[position.getRow()][position.getCol() + 2] = true;
				}
			}
			
			////#specialmove -  ROQUE  maior (queenside rook)
			Position posT2 = new Position(position.getRow(), position.getCol() - 4);
			
			if (isRookCastling(posT2)) {
				Position p1 = new Position(position.getRow(), position.getCol() - 1);
				Position p2 = new Position(position.getRow(), position.getCol() - 2);
				Position p3 = new Position(position.getRow(), position.getCol() - 3);
				
				if (getTabuleiro().piece(p1) == null && 
					getTabuleiro().piece(p2) == null &&
					getTabuleiro().piece(p3) == null) {
					
					// move o rei duas casas para a esquerda
					matriz[position.getRow()][position.getCol() - 2] = true;
				}
			}
		}
		
		return matriz;
	}
	
}
