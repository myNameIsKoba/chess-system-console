package chess;

import boardgame.Board;
import boardgame.Position;
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
	
	private void initSetup() {
		/// White place
		this.tabuleiro.placePiece(new Torre(this.tabuleiro, Color.WHITE), new Position(0,0));
		this.tabuleiro.placePiece(new Torre(this.tabuleiro, Color.WHITE), new Position(0,7));
		this.tabuleiro.placePiece(new Rei(this.tabuleiro, Color.WHITE), new Position(0,3));
		
		/// Black place
		this.tabuleiro.placePiece(new Torre(this.tabuleiro, Color.BLACK), new Position(7,0));
		this.tabuleiro.placePiece(new Torre(this.tabuleiro, Color.BLACK), new Position(7,7));
		this.tabuleiro.placePiece(new Rei(this.tabuleiro, Color.BLACK), new Position(7,4));
	}
}
