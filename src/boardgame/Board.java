package boardgame;

public class Board {

	private Integer linhas;
	private Integer colunas;
	private Piece[][] pecas;
	
	public Board(Integer linhas, Integer colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Piece[linhas][colunas]; //deve ser instanciada informando linhas e colunas 
	}

	public Integer getLinhas() {
		return linhas;
	}

	public void setLinhas(Integer linhas) {
		this.linhas = linhas;
	}

	public Integer getColunas() {
		return colunas;
	}

	public void setColunas(Integer colunas) {
		this.colunas = colunas;
	}
	
	
	public Piece piece(Integer row, Integer col) {
		return pecas[row][col];
	}
	
	public Piece piece(Position pos) {
		return pecas[pos.getRow()][pos.getCol()];
	}
	
}
