package chess;

import java.util.ArrayList;
import java.util.List;

import application.common.Color;
import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.exception.ChessException;
import chess.pieces.Rei;
import chess.pieces.Torre;

public class ChessMatch {

	private Integer turno;
	private Color currentPlayer;
	private Board tabuleiro;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPiecesList = new ArrayList<>();
	
	public ChessMatch() {
		this.tabuleiro = new Board(8, 8);
		this.turno = 1;
		this.currentPlayer = Color.WHITE;
		initSetup();
	}
	
	public Integer getTurno() {
		return turno;
	}
	
	public Color getCurrentPlayer() {
		return this.currentPlayer;
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
	 * imprime todas as possiveis posições de uma peça
	 * @param sourcePos
	 * @return
	 */
	public boolean[][] possibleMoves(ChessPosition sourcePos){
		
		Position pos = sourcePos.toPos();
		ValidateSourcePos(pos);

		return this.tabuleiro.piece(pos).possibleMoves();
	}
	
	/**
	 * Validação de movimento das peças
	 * @param sourcePos
	 * @param targetPos
	 * @return
	 */
	public ChessPiece performChessMove(ChessPosition sourcePos, ChessPosition targetPos) {
		Position source = sourcePos.toPos();
		Position target = targetPos.toPos();
		
		ValidateSourcePos(source);
		ValidateTargetPos(source, target);
		
		Piece capturedPiece = releaseMov(source, target);
		nextTurn();
		return (ChessPiece) capturedPiece;
	}
	
	/**
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	private Piece releaseMov(Position source, Position target) {
		Piece peca = this.tabuleiro.removePiece(source);
		Piece capturedPiece = this.tabuleiro.removePiece(target);
		
		this.tabuleiro.placePiece(peca, target);	
		
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPiecesList.add(capturedPiece);
		}
		
		return capturedPiece;
	}

	/**
	 * 
	 * @param pos
	 */
	private void ValidateSourcePos(Position pos) {
		if (!this.tabuleiro.thereIsAPiece(pos)) {
			throw new ChessException("Não existe peça na posição de origem");
		}
		
		if (this.currentPlayer != ((ChessPiece)this.tabuleiro.piece(pos)).getCor()) {
			throw new ChessException("A peça escolhida não é sua");
		}
		
		if (!this.tabuleiro.piece(pos).isStuck()) {
			throw new ChessException("Não existe movimentos possiveis para a peça escolhida");
		}
	}
	
	/**
	 * 
	 * @param source
	 * @param target
	 */
	private void ValidateTargetPos(Position source, Position target) {
		if (!this.tabuleiro.piece(source).possibleMove(target)) {
			throw new ChessException("A peça não pode se mover nesta posição");
		}
	}

	
	/**
	 * 
	 */
	private void nextTurn() {
		this.turno++;
		this.currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK: Color.WHITE;
	}
	
	
	/**
	 * método que inicia o tabuleiro com as peças
	 * @param col
	 * @param row
	 * @param peca
	 */
	private void placeNewPiece(char col, int row, ChessPiece peca) {
		this.tabuleiro.placePiece(peca, new ChessPosition(col, row).toPos());
		piecesOnTheBoard.add(peca);
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
