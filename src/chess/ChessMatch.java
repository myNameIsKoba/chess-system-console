package chess;

import boardgame.Board;
import chess.pieces.Rei;
import chess.pieces.Torre;

public class ChessMatch {

	private Board tabuleiro;
	
	public ChessMatch() {
		this.tabuleiro = new Board(8, 8);
		initSetup();
	}
	
	/**
	 * camada de 'xadrez' e não de 'tabuleiro', retorna a matriz de peças para a partida de xadrez
	 * @return
	 */
	public ChessPiece[][] getPecasTabuleiro(){
		ChessPiece[][] jogo = 
				new ChessPiece[this.tabuleiro.getLinhas()][this.tabuleiro.getColunas()];
		
		for(Integer x=0; x < this.tabuleiro.getLinhas(); x++) {
			
			for(Integer y=0; y < this.tabuleiro.getColunas(); y++) {
				
				jogo[x][y] = (ChessPiece) this.tabuleiro.piece(x, y);
			}
		}
		return jogo;
	}
	
	private void placeNewPiece(char col, int row, ChessPiece peca) {
		this.tabuleiro.placePiece(peca, new ChessPosition(col, row).toPos());
	}
	
	private void initSetup() {
		/// White place
//		this.tabuleiro.placePiece(new Torre(this.tabuleiro, Color.WHITE), new Position(0,0)); <- jeito antigo de posicionar
		placeNewPiece('a', 8, new Torre(this.tabuleiro, Color.WHITE));
		placeNewPiece('h', 8, new Torre(this.tabuleiro, Color.WHITE));
		placeNewPiece('d', 8, new Rei(this.tabuleiro, Color.WHITE));
		
		/// Black place
		placeNewPiece('a', 1, new Torre(this.tabuleiro, Color.BLACK));
		placeNewPiece('h', 1, new Torre(this.tabuleiro, Color.BLACK));
		placeNewPiece('e', 1, new Rei(this.tabuleiro, Color.BLACK));
	}
}
