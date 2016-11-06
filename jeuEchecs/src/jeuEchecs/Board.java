package jeuEchecs;

import java.awt.Color;

public abstract class Board {
	private Cell[][] board;
	
	public Board() {
	}
	
	public Board(int hauteur, int largeur) {
		initCellules(hauteur, largeur);
	}
	
	protected void initCellules(int hauteur, int largeur) {
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
	
//	ACCESSEURS
	public int getHauteur() {
		return this.board.length;
	}
	public int getLargeur() {
		return this.board[0].length;
	}
	public Cell getCell(int row, int column) {
		return this.board[row][column];
	}
	
	public void movePiece(Cell from, Cell to) {
//		from.setPiece(board[from.x][from.y].getPiece());
		if (from.getPiece().isMoveAllowed(from, to)) {
			to.setPiece(from.getPiece());
			from.setPiece(null);
		}
	}

}
