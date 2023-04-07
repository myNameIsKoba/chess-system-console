package application;

import chess.ChessMatch;

public class MainChess {

	public static void main(String args[]) {
		
		ChessMatch match = new ChessMatch();
		ViewChessBoard.printBoard(match.getPecasTabuleiro());
	}
}
