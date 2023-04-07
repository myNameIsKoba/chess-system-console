package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import application.codes.AnsiCodes;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

/**
 * 'UI'
 * @author User
 *▪▫■□
 */
public class ViewChessBoard extends AnsiCodes{

	public static void clear() {
		System.out.println("\n\n\n\n\n\n");
		System.out.flush();
	}
	
	/**
	 * 
	 * @param pieces
	 */
	public static void printBoard(ChessPiece[][] pieces) {
		System.out.println(" ||   |-------------------------------");
		for(Integer x=0; x < pieces.length; x++) {
			
			System.out.print(" ||" +ANSI_GREEN+"["+(8 - x)+"]"+ANSI_RESET+"|");
			for(Integer y=0; y < pieces.length; y++) {
				printPiece(pieces[x][y], x, y);
			}
			System.out.println("\n" + " || - |");
		}
		System.out.println(" ||   |-------------------------------\n" +
						   " ||[=]|" + ANSI_GREEN + "[A] [B] [C] [D] [E] [F] [G] [H]\n" + ANSI_RESET
						  +" []===================================");
	}
	
	/**
	 * 
	 * @param peca
	 * @param posX
	 * @param posY
	 */
	private static void printPiece(ChessPiece peca, Integer posX, Integer posY) {
		if (peca == null) {
			String sqr = (posX % 2 == 0) ? 
					((posY % 2 == 0) ? " □ " : " ■ ") : ((posY % 2 == 1) ? " □ " : " ■ ") ;
			System.out.print(sqr);
		}
		else {
			if (peca.getCor() == Color.WHITE) {
                System.out.print(ANSI_CYAN + peca + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
            }
		}
		System.out.print(" ");
	}
	
	/**
	 * 
	 * @param scan
	 * @return
	 */
	public static ChessPosition readChessPosition(Scanner scan) {
		try {
			String strread = scan.nextLine();
			char column = strread.charAt(0);
			Integer row = Integer.parseInt(strread.substring(1));
			
			return new ChessPosition(column, row);
		}
		catch(RuntimeException e) {
			throw new InputMismatchException("Erro ao ler a posição da peça. \n Valore válidos pertencem entre a1 e h8. \n");
		}
		
	}
}
