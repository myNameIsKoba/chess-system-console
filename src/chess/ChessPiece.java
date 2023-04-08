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

	public ChessPiece(Board tabuleiro, Color cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Color getCor() {
		return cor;
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
