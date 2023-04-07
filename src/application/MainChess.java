package application;

import boardgame.Board;
import chess.ChessMatch;

public class MainChess {

	public static void main(String args[]) {
		
		ChessMatch match = new ChessMatch();
		ViewChess.printBoard(match.getPecasTabuleiro());
	}
}
