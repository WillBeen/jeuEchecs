package jeuEchecs;

public abstract class Board {
	protected Cell[][] board;
	
	public Board() {
	}
	
	public Board(int hauteur, int largeur) {
		initCells(hauteur, largeur);
	}
	
	protected void initCells(int hauteur, int largeur) {
		board = new Cell[hauteur][largeur];
		for (int i = 0; i < hauteur; i++) {
			for (int j = 0; j < largeur; j++) {
				if ((i + j) % 2 == 0) {
					board[i][j] = new Cell(Cell.WHITE, i, j);
				} else {
					board[i][j] = new Cell(Cell.BLACK, i, j);
				}
			}
		}
	}
	
	public abstract void movePiece(Cell from, Cell to);
	
//	ACCESSEURS
	public int getHeigh() {
		return this.board.length;
	}
	public int getWidth() {
		return this.board[0].length;
	}
	public Cell getCell(int row, int column) {
		return this.board[row][column];
	}

	public Cell getCell2(int column, int row) {
		return this.board[row][column];
	}
}
