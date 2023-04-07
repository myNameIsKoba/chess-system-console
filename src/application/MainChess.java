package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.exception.ChessException;

public class MainChess {

	public static void main(String args[]) {
		
		Scanner scaner = new Scanner(System.in);
		ChessMatch match = new ChessMatch();
//		ViewChessBoard.printBoard(match.getPecasTabuleiro());
		
		while(true) {
			try {
				ViewChessBoard.clear();
				ViewChessBoard.printBoard(match.getPecasTabuleiro());
				System.out.println("\n Source: ");
				ChessPosition source = ViewChessBoard.readChessPosition(scaner);
				
				boolean[][] possibleMoves = match.possibleMoves(source);
				ViewChessBoard.clear();
				ViewChessBoard.printBoard(match.getPecasTabuleiro(), possibleMoves);
				
				System.out.println("\n Target: ");
				ChessPosition target = ViewChessBoard.readChessPosition(scaner);
				
				ChessPiece capturedPiece = match.performChessMove(source, target);
			}
			catch(ChessException e) {
				System.out.println(e.getMessage());
				scaner.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				scaner.nextLine();
			}
		}
	}
}
