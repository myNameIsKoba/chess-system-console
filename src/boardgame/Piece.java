package boardgame;

/**
 * atributos #protected - posição simples de matrizes - visivel apenas neste pacote
 * @author User
 *
 */
public class Piece {

	protected Position pos; //inicialmente, pos = null
	private Board tabua;
	
	public Piece(Board tabua) {
		this.tabua = tabua;
	}

	protected Board getTabua() {
		return tabua;
	}
	
}
