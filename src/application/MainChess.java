package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.exception.ChessException;

public class MainChess {

	public static void main(String args[]) {
		
		Scanner scaner = new Scanner(System.in);
		ChessMatch match = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();
		
		while(!match.getCheckMate()) {
			try {
				ViewChessBoard.clear();
				ViewChessBoard.printMatch(match, captured);
				
				System.out.println("\n Selecione uma peça: ");
				ChessPosition source = ViewChessBoard.readChessPosition(scaner);
				
				boolean[][] possibleMoves = match.possibleMoves(source);
				ViewChessBoard.clear();
				ViewChessBoard.printBoard(match.getPecasTabuleiro(), possibleMoves);
				
				System.out.println("\n ir para: ");
				ChessPosition target = ViewChessBoard.readChessPosition(scaner);
				
				ChessPiece capturedPiece = match.performChessMove(source, target);
				
				if (capturedPiece != null) {
					captured.add(capturedPiece);
				}
				
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
		ViewChessBoard.clear();
		ViewChessBoard.printMatch(match, captured);
	}
}
