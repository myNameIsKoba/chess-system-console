package boardgame;

import boardgame.exception.BoardException;

public class Board {

	private Integer linhas;
	private Integer colunas;
	private Piece[][] pecas;
	
	public Board(Integer linhas, Integer colunas) {
		if (linhas < 1 || colunas < 1) {
			throw new BoardException(" Erro ao criar o Tabuleiro: devem haver no minimo 1(uma) linha e 1(uma) coluna !");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Piece[linhas][colunas]; //deve ser instanciada informando linhas e colunas 
	}

	public Integer getLinhas() {
		return linhas;
	}

//	public void setLinhas(Integer linhas) {
//		this.linhas = linhas;
//	}

	public Integer getColunas() {
		return colunas;
	}

//	public void setColunas(Integer colunas) {
//		this.colunas = colunas;
//	}
	
	/**
	 * sobrecarga de metodos - método que acessa uma peça em uma determindada posição
	 * @param row
	 * @param col
	 * @return
	 */
	public Piece piece(Integer row, Integer col) {
		if (!this.posExists(row.shortValue(), col.shortValue())) {
			throw new BoardException("Posição inválida !");
		}
		return pecas[row][col];
	}
	
	public Piece piece(Position pos) {
		if (!this.posExists(pos)) {
			throw new BoardException("Posição inválida !");
		}
		return pecas[pos.getRow()][pos.getCol()];
	}
	
	/**
	 * método onde a posição da matriz de peças atribuí para a peça como argumento 
	 * @param peca
	 * @param pos
	 */
	public void placePiece(Piece peca, Position pos) {
		if (this.thereIsAPiece(pos)) {
			throw new BoardException("Já existe uma peça nesta posição -> " + pos);
		}
		this.pecas[pos.getRow()][pos.getCol()] = peca;
		peca.pos = pos;
	}
	
	/**
	 * sobrecarga de metodos - identifica se uma posição existe
	 * @param row
	 * @param col
	 * @return
	 */
	private Boolean posExists(Short row, Short col) {
		return row >= 0 && row < this.linhas &&
				col >= 0 && col < this.colunas;
	}
	
	public Boolean posExists(Position pos) {
		return posExists(pos.getRow().shortValue(), pos.getCol().shortValue());
	}
	
	/**
	 * identifica se há peças
	 * @param pos
	 * @return
	 */
	public Boolean thereIsAPiece(Position pos) {
		if (!this.posExists(pos)) {
			throw new BoardException("Posição inválida !");
		}
		return this.piece(pos) != null;
	}
}
