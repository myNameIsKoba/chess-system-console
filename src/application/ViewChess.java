package application;

import chess.ChessPiece;

/**
 * 'UI'
 * @author User
 *
 */
public class ViewChess {

	public static void printBoard(ChessPiece[][] pieces) {
		for(int x=0; x < pieces.length; x++) {
			
			System.out.print((8 - x) + " ");
			for(int y=0; y < pieces.length; y++) {
				printPiece(pieces[x][y]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	private static void printPiece(ChessPiece peca) {
		if (peca == null) {
			System.out.print(".");
		}
		else {
			System.out.print(peca);
		}
		System.out.print(" ");
	}
}
