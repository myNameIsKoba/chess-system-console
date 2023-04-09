package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import application.common.Color;
import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.exception.ChessException;
import chess.pieces.Bispo;
import chess.pieces.Peao;
import chess.pieces.Rei;
import chess.pieces.Torre;

public class ChessMatch {

	private Integer turno;
	private Color currentPlayer;
	private Board tabuleiro;
	private Boolean check;
	private Boolean checkMate;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPiecesList = new ArrayList<>();
	
	public ChessMatch() {
		this.tabuleiro = new Board(8, 8);
		this.turno = 1;
		this.currentPlayer = Color.WHITE;
		this.check = false;
		this.checkMate = false;
		
		initSetup();
	}
	
	public Integer getTurno() {
		return turno;
	}
	
	public Color getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
	}
	
	/**
	 * camada de 'xadrez' e não de 'tabuleiro', retorna a matriz de peças para a partida de xadrez
	 * @return
	 */
	public ChessPiece[][] getPecasTabuleiro(){
		ChessPiece[][] jogo = 
				new ChessPiece[this.tabuleiro.getLinhas()][this.tabuleiro.getColunas()];
		
		for(Integer x=0; x < this.tabuleiro.getLinhas(); x++) {
			for(Integer y=0; y < this.tabuleiro.getColunas(); y++) {
				
				jogo[x][y] = (ChessPiece) this.tabuleiro.piece(x, y);
			}
		}
		return jogo;
	}
	
	/**
	 * imprime todas as possiveis posições de uma peça
	 * @param sourcePos
	 * @return
	 */
	public boolean[][] possibleMoves(ChessPosition sourcePos){
		
		Position pos = sourcePos.toPos();
		ValidateSourcePos(pos);

		return this.tabuleiro.piece(pos).possibleMoves();
	}
	
	/**
	 * Validação de movimento das peças
	 * @param sourcePos
	 * @param targetPos
	 * @return
	 */
	public ChessPiece performChessMove(ChessPosition sourcePos, ChessPosition targetPos) {
		Position source = sourcePos.toPos();
		Position target = targetPos.toPos();
		
		ValidateSourcePos(source);
		ValidateTargetPos(source, target);
		
		Piece capturedPiece = releaseMov(source, target);
		
		if (isCheck(currentPlayer)) {
			undoMov(source, target, capturedPiece);
			throw new ChessException(" voce não pode se colocar em Check ");
		}
		
		this.check = (isCheck(opponent(currentPlayer))) ? true : false ;
		
		if (isCheckMate(opponent(currentPlayer))) {
			this.checkMate = true;
		}
		else {
			nextTurn();
		}

		return (ChessPiece) capturedPiece;
	}
	
	/**
	 * Realiza o movimento 
	 * @param source -> seleciona uma peça
	 * @param target -> selecionaum "alvo", pode ser: qualquer lugar disponível ou adversários
	 * @return -> se houve uma captura adiciona em uma lista ou retorna null em caso de se movimentar no tabuleiro
	 */
	private Piece releaseMov(Position source, Position target) {
		ChessPiece peca = (ChessPiece)this.tabuleiro.removePiece(source);
		peca.increaseCount();
		
		Piece capturedPiece = this.tabuleiro.removePiece(target);
		this.tabuleiro.placePiece(peca, target);	
		
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPiecesList.add(capturedPiece);
		}
		
		return capturedPiece;
	}
	
	/**
	 * Desfaz o movimento. Geralmete usado para detectar movimentos inpróprios
	 * @param source
	 * @param target
	 * @param captured
	 */
	private void undoMov(Position source, Position target, Piece captured) {
		ChessPiece peca = (ChessPiece)this.tabuleiro.removePiece(target);
		peca.decreaseCount();
		
		this.tabuleiro.placePiece(peca, source);
		if (captured != null) {
			this.tabuleiro.placePiece(captured, target);
			this.capturedPiecesList.remove(captured);
			piecesOnTheBoard.add(captured);
		}
	}

	/**
	 * 
	 * @param pos
	 */
	private void ValidateSourcePos(Position pos) {
		if (!this.tabuleiro.thereIsAPiece(pos)) {
			throw new ChessException("Não existe peça na posição de origem");
		}
		
		if (this.currentPlayer != ((ChessPiece)this.tabuleiro.piece(pos)).getCor()) {
			throw new ChessException("A peça escolhida não é sua");
		}
		
		if (!this.tabuleiro.piece(pos).isStuck()) {
			throw new ChessException("Não existe movimentos possiveis para a peça escolhida");
		}
	}
	
	/**
	 * 
	 * @param source
	 * @param target
	 */
	private void ValidateTargetPos(Position source, Position target) {
		if (!this.tabuleiro.piece(source).possibleMove(target)) {
			throw new ChessException("A peça não pode se mover nesta posição");
		}
	}

	
	private void nextTurn() {
		this.turno++;
		this.currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK: Color.WHITE;
	}
	
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	/**
	 * método que valida a defesa do rei quando encontra-se em Check
	 * @param color
	 * @return 
	 */
	private ChessPiece king(Color color) {
		List<Piece> list = piecesOnTheBoard
				.stream()
				.filter(x -> ((ChessPiece) x)
						.getCor() == color)
				.collect(Collectors.toList());
	
		for (Piece p : list) {
			if(p instanceof Rei) {
				return (ChessPiece) p;
			}
		}
		// NUNCA deve acontecer este erro
		throw new IllegalStateException("NÃO EXISTE " + color + " \'KING\' NO TABULEIRO");
	}
	
	/**
	 * Lógica do 'Check'. 
	 * 	<br> Seleciona a posiçao do rei e junta todas as peças do adversário em uma lista e então itera 
	 * em uma matriz de booleanos com movimentos possíveis. 
	 * 	<br> Se houver algum oponente que ameaçe tomar sua posição e que seja possível evitar 
	 * tomando a peça inimiga ou escapando em alguma outra casa consta como um Check. 
	 *  <br> Retorna true se satisfazer a condição acima e false se não houver ameaça
	 * @param color 
	 * @return 
	 */
	private boolean isCheck(Color color) {
		Position kingPos = king(color)
				.getChessPos()
				.toPos();
		
		List<Piece> opponentPieceList = piecesOnTheBoard
				.stream()
				.filter(x -> ((ChessPiece) x)
						.getCor() == opponent(color))
				.collect(Collectors.toList());
	
		for (Piece p : opponentPieceList) {
			boolean[][] matrizOppnt = p.possibleMoves();
			
			if( matrizOppnt[kingPos.getRow()][kingPos.getCol()] ) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Lógica do 'Check-Mate'
	 * <br> Primeiro verificamos se NÃO está em check.
	 * <br> E então selecionamos todas as peças aliadas em uma lista. <br>
	 * <br> Segunda etapa: verificar todos os casos que não são check-mate
	 * <p> 
	 * 	Para isso, iteramos em uma matriz de booleanos com todos os movimentos possiveis. 
	 * 	Selecionamos o destino da peça com o iterador e depois realizamos o movimento e 
	 *  então testamos se o 'parâmetro color' está em Cheque armazenando em uma váriavel.  
	 * </p>
	 * 
	 * @param color
	 * @return
	 */
	private boolean isCheckMate(Color color) {
		if (!isCheck(color)) {
			return false;
		}
		List<Piece> sameColorPieces = piecesOnTheBoard
				.stream()
				.filter(x -> ((ChessPiece) x)
						.getCor() == color)
				.collect(Collectors.toList());
		
		for (Piece p : sameColorPieces) {
			
			boolean[][] mat = p.possibleMoves();
			for (int i=0; i<this.tabuleiro.getLinhas(); i++) {
				for (int j=0; j<this.tabuleiro.getColunas(); j++) {
					//// Condição para NÃO ser check-mate
					if (mat[i][j]) {
						Position sourcePos = ((ChessPiece)p)
								.getChessPos()
								.toPos();
						
						Position targetPos = new Position(i, j); 
						Piece capturedP = releaseMov(sourcePos, targetPos);
						
						boolean testCheck = isCheck(color);
						undoMov(sourcePos, targetPos, capturedP);
						
						if (!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	
	/**
	 * método que inicia o tabuleiro com as peças
	 * @param col
	 * @param row
	 * @param peca
	 */
	private void placeNewPiece(char col, int row, ChessPiece peca) {
		this.tabuleiro.placePiece(peca, new ChessPosition(col, row).toPos());
		piecesOnTheBoard.add(peca);
	}
	
	private void initSetup() {
		
//		this.tabuleiro.placePiece(new Torre(this.tabuleiro, Color.WHITE), new Position(0,0)); <- jeito antigo de posicionar
		/// White place
		placeNewPiece('a', 1, new Torre(this.tabuleiro, Color.WHITE));
		placeNewPiece('c', 1, new Bispo(this.tabuleiro, Color.WHITE));
		placeNewPiece('e', 1, new Rei(this.tabuleiro, Color.WHITE));
		placeNewPiece('f', 1, new Bispo(this.tabuleiro, Color.WHITE));
		placeNewPiece('h', 1, new Torre(this.tabuleiro, Color.WHITE));
		
		placeNewPiece('a', 2, new Peao(this.tabuleiro, Color.WHITE));
		placeNewPiece('b', 2, new Peao(this.tabuleiro, Color.WHITE));
		placeNewPiece('c', 2, new Peao(this.tabuleiro, Color.WHITE));
		placeNewPiece('d', 2, new Peao(this.tabuleiro, Color.WHITE));
		placeNewPiece('e', 2, new Peao(this.tabuleiro, Color.WHITE));
		placeNewPiece('f', 2, new Peao(this.tabuleiro, Color.WHITE));
		placeNewPiece('g', 2, new Peao(this.tabuleiro, Color.WHITE));
		placeNewPiece('h', 2, new Peao(this.tabuleiro, Color.WHITE));
		
		/// Black place
		placeNewPiece('a', 8, new Torre(this.tabuleiro, Color.BLACK));
		placeNewPiece('c', 8, new Bispo(this.tabuleiro, Color.BLACK));
		placeNewPiece('e', 8, new Rei(this.tabuleiro, Color.BLACK));
		placeNewPiece('f', 8, new Bispo(this.tabuleiro, Color.BLACK));
		placeNewPiece('h', 8, new Torre(this.tabuleiro, Color.BLACK));
		
		placeNewPiece('a', 7, new Peao(this.tabuleiro, Color.BLACK));
		placeNewPiece('b', 7, new Peao(this.tabuleiro, Color.BLACK));
		placeNewPiece('c', 7, new Peao(this.tabuleiro, Color.BLACK));
		placeNewPiece('d', 7, new Peao(this.tabuleiro, Color.BLACK));
		placeNewPiece('e', 7, new Peao(this.tabuleiro, Color.BLACK));
		placeNewPiece('f', 7, new Peao(this.tabuleiro, Color.BLACK));
		placeNewPiece('g', 7, new Peao(this.tabuleiro, Color.BLACK));
		placeNewPiece('h', 7, new Peao(this.tabuleiro, Color.BLACK));
	}

}
