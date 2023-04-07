package chess;

import boardgame.Board;

public class ChessMatch {

	private Board tabuleiro;
	
	public ChessMatch() {
		this.tabuleiro = new Board(8, 8);
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
}
