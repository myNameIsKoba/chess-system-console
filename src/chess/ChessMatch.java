package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.exception.ChessException;
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
	
	/**
	 * 
	 * @param sourcePos
	 * @param targetPos
	 * @return
	 */
	public ChessPiece performChessMove(ChessPosition sourcePos, ChessPosition targetPos) {
		Position source = sourcePos.toPos();
		Position target = targetPos.toPos();
		
		ValidateSourcePos(source);
		Piece capturedPiece = releaseMov(source, target);
		
		return (ChessPiece) capturedPiece;
	}
	
	private Piece releaseMov(Position source, Position target) {
		Piece peca = this.tabuleiro.removePiece(source);
		Piece captPeca = this.tabuleiro.removePiece(target);
		
		this.tabuleiro.placePiece(peca, target);		
		return captPeca;
	}

	private void ValidateSourcePos(Position pos) {
		if (!this.tabuleiro.thereIsAPiece(pos)) {
			throw new ChessException("Não existe peça na posição de origem");
		}
		if (!this.tabuleiro.piece(pos).isStuck()) {
			throw new ChessException("Não existe movimentos possiveis para a peça escolhida");
		}
	}

	/**
	 * 
	 * @param col
	 * @param row
	 * @param peca
	 */
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
