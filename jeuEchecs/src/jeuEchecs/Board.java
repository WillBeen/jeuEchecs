package jeuEchecs;

public abstract class Board {
	protected Cell[][] board;
	
	public Board() {
	}
	
	public Board(int hauteur, int largeur) {
		initCells(hauteur, largeur);
	}
	
	protected void initCells(int largeur, int hauteur) {
		board = new Cell[hauteur][largeur];
		for (int i = 0; i < hauteur; i++) {
			for (int j = 0; j < largeur; j++) {
				if ((i + j) % 2 == 0) {
					board[i][j] = new Cell(Cell.WHITE, j, i);
				} else {
					board[i][j] = new Cell(Cell.BLACK, j, i);
				}
			}
		}
	}
	
	public abstract void movePiece(Cell from, Cell to);
	
//	ACCESSEURS
	public int getHeight() {
		return this.board.length;
	}
	public int getWidth() {
		return this.board[0].length;
	}
	public Cell getCell(int column, int row) {
		return this.board[row][column];
	}
}
