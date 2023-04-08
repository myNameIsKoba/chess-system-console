package boardgame;

/**
 * atributos #protected - posição simples de matrizes - visivel apenas neste pacote
 * Classe que generaliza todo tipo de peça
 * @author User
 *
 */
public abstract class Piece {

	protected Position position; //inicialmente, pos = null
	private Board tabua;
	
	public Piece(Board tabuleiro) {
		this.tabua = tabuleiro;
	}

	protected Board getTabuleiro() {
		return tabua;
	}
	
	/**
	 * Demonstração de um hook method
	 * @return
	 */
	public abstract boolean[][] possibleMoves();
	
	public boolean possibleMove(Position pos) {
		return possibleMoves()[pos.getRow()][pos.getCol()];
	}
	
	/**
	 * método que verifica se há algum movimento possivel
	 * @return
	 */
	public boolean isStuck() {
		boolean[][] boolMatriz = possibleMoves();

		for(Integer i=0; i <  boolMatriz.length; i++) {
			for(Integer j=0; j <  boolMatriz.length; j++) {
				if(boolMatriz[i][j]) {
					return Boolean.TRUE;
				}
			}
		}
		return Boolean.FALSE;
	}
}
