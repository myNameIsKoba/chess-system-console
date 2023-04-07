package application;

import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class MainChess {

	public static void main(String args[]) {
		
		Scanner scaner = new Scanner(System.in);
		ChessMatch match = new ChessMatch();
//		ViewChessBoard.printBoard(match.getPecasTabuleiro());
		
		while(true) {
			ViewChessBoard.printBoard(match.getPecasTabuleiro());
			System.out.println("\n Source: ");
			ChessPosition source = ViewChessBoard.readChessPosition(scaner);
			
			System.out.println("\n Target: ");
			ChessPosition target = ViewChessBoard.readChessPosition(scaner);
			
			ChessPiece capturedPiece = match.performChessMove(source, target);
		}
	}
}
