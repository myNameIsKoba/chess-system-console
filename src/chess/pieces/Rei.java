package chess.pieces;

import application.common.Color;
import boardgame.Board;
import chess.ChessPiece;

public class Rei extends ChessPiece{

	public Rei(Board tabuleiro, Color cor) {
		super(tabuleiro, cor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return " Ω ";
	}

	@Override
	public boolean[][] possibleMoves() {
		
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		return matriz;
	}
}
