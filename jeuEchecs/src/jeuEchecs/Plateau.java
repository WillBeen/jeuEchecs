package jeuEchecs;

import java.awt.Color;

public class Plateau {
	private Cell[][] plateau;
	
	public Plateau() {
		initCellules(8,8);
	}
	
	public Plateau(int hauteur, int largeur) {
		initCellules(hauteur, largeur);
	}
	
	private void initCellules(int hauteur, int largeur) {
		Color couleur;
		this.plateau = new Cell[hauteur][largeur];
		for (int i = 0; i < hauteur; i++) {
			for (int j = 0; j < largeur; j++) {
				if ((i + j) % 2 == 0) {
					couleur = Color.WHITE;
				} else {
					couleur = Color.BLACK;
				}
				this.plateau[i][j] = new Cell(couleur, i, j);
			}
		}
	}
	
//	ACCESSEURS
	public int getHauteur() {
		return this.plateau.length;
	}
	public int getLargeur() {
		return this.plateau[0].length;
	}
	public Cell getCell(int row, int column) {
		return this.plateau[row][column];
	}

}
