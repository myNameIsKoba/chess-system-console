package application;

import chess.ChessPiece;

/**
 * 'UI'
 * @author User
 *▪▫■□
 */
public class ViewChessBoard {

	public static void printBoard(ChessPiece[][] pieces) {
		for(Integer x=0; x < pieces.length; x++) {
			
			System.out.print(" || " +(8 - x) + " |");
			for(Integer y=0; y < pieces.length; y++) {
				printPiece(pieces[x][y], x, y);
			}
			System.out.println("\n" + " || - |");
		}
		System.out.println(" ||   |-------------------------------\n"
						  +" ||[=]| a   b   c   d   e   f   g   h\n"
						  +" []===================================");
	}
	
	private static void printPiece(ChessPiece peca, Integer posX, Integer posY) {
		if (peca == null) {
			String sqr = (posX % 2 == 0) ? 
					((posY % 2 == 0) ? " □ " : " ▪ ") : ((posY % 2 == 1) ? " □ " : " ▪ ") ;
			System.out.print(sqr);
		}
		else {
			System.out.print(peca);
		}
		System.out.print(" ");
	}
}
