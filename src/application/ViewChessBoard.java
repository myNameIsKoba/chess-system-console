package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import application.codes.AnsiCodes;
import application.common.Color;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

/**
 * 'UI'
 * @author User
 *▪▫■□
 */
public class ViewChessBoard extends AnsiCodes{

	public static void clear() {
		System.out.println("\033[H\033[2J");
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
				printPiece(pieces[x][y], x, y, Boolean.FALSE);
			}
			System.out.println("\n" + " || - |");
		}
		System.out.println(" ||   |-------------------------------\n" +
						   " ||[=]|" + ANSI_GREEN + "[A] [B] [C] [D] [E] [F] [G] [H]\n" + ANSI_RESET
						  +" []===================================");
	}
	/**
	 * 
	 * @param pieces
	 * @param possibleMoves
	 */
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		System.out.println(" ||   |-------------------------------");
		for(Integer x=0; x < pieces.length; x++) {
			
			System.out.print(" ||" +ANSI_GREEN+"["+(8 - x)+"]"+ANSI_RESET+"|");
			for(Integer y=0; y < pieces.length; y++) {
				printPiece(pieces[x][y], x, y, possibleMoves[x][y]);
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
	private static void printPiece(ChessPiece peca, Integer posX, Integer posY, Boolean background) {
		if(background) {
			System.out.print(ANSI_BLACK_BACKGROUND);
		}
		if (peca == null) {
			String sqr = (posX % 2 == 0) ? 
					((posY % 2 == 0) ? " □ " : " ■ ") : ((posY % 2 == 1) ? " □ " : " ■ ") ;
			System.out.print(sqr + ANSI_RESET);
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
	 * @param match
	 * @param captured
	 */
	public static void printMatch(ChessMatch match, List<ChessPiece> captured) {
		printBoard(match.getPecasTabuleiro());
		printCapturedPieces(captured);
		
		System.out.println("\n--Turno ..........: " + match.getTurno());
		
		if (!match.getCheckMate()) {

			System.out.println("--Aguardando peça.: " + match.getCurrentPlayer());
			if (match.getCheck()) {
				System.out.println( ANSI_RED + " [-- CHECK ! --]" + ANSI_RESET);
			}
		}	
		else {
			System.out.println(
					ANSI_GREEN +
					" -- !!CHECKMATE!! --" + ANSI_RESET + "\n" +
					" -- Vencedor : " + match.getCurrentPlayer()
					);
		}
	}
	
	/**
	 * 
	 * @param captured
	 */
	private static void printCapturedPieces(List<ChessPiece> captured) {
		List<ChessPiece> white = captured
				.stream()
				.filter(x -> x.getCor() == Color.WHITE)
				.collect(Collectors.toList());
		
		List<ChessPiece> black = captured
				.stream()
				.filter(x -> x.getCor() == Color.BLACK)
				.collect(Collectors.toList());
		
		System.out.println("\n -: Peças capturadas :-");
		System.out.println(" - Brancas.: " 
					+ ANSI_CYAN 
					+ Arrays.toString(white.toArray()) + ANSI_RESET);
		System.out.println(" - Pretas..: " 
					+ ANSI_YELLOW 
					+ Arrays.toString(black.toArray()) + ANSI_RESET);
	}
	
	/**
	 * método para scanear a entrada dada pelo usuário
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
