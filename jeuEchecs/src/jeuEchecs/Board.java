package jeuEchecs;

import java.awt.Color;

public class Board {
	private Cell[][] board;
	
	public Board() {
	}
	
	public Board(int hauteur, int largeur) {
		initCellules(hauteur, largeur);
	}
	
	private void initCellules(int hauteur, int largeur) {
		Color couleur;
		this.board = new Cell[hauteur][largeur];
		for (int i = 0; i < hauteur; i++) {
			for (int j = 0; j < largeur; j++) {
				if ((i + j) % 2 == 0) {
					couleur = new Color(244, 235, 124);
				} else {
					couleur = new Color(50, 10, 10);
				}
				this.board[i][j] = new Cell(couleur, i, j);
			}
		}
	}
	
	public void initGame(){
		
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

}
