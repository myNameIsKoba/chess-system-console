package chess;

import application.common.Color;
/**
 * Classe genérica para determinar peças
 */
import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece{

	private Color cor;
	private Integer moveCount;
	

	public ChessPiece(Board tabuleiro, Color cor) {
		super(tabuleiro);
		this.cor = cor;
		this.moveCount = 0;
	}
	

	public Color getCor() {
		return cor;
	}
	
	public Integer getMoveCount() {
		return this.moveCount;
	}
	
	public void increaseCount() {
		this.moveCount++;
	}
	
	public void decreaseCount() {
		this.moveCount--;
	}
	
	public ChessPosition getChessPos() {
		return ChessPosition.fromPosition(position);
	}
	
	/**
	 * Dieferencia peças aliadas de adversários
	 * @param pos
	 * @return
	 */
	protected Boolean isThereOpponentPiece(Position pos) {
		ChessPiece p = (ChessPiece)getTabuleiro().piece(pos);
		return p != null && p.getCor() != cor;
	}
}
