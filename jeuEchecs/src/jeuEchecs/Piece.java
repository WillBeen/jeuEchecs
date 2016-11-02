package jeuEchecs;

import java.awt.Color;

public class Piece {
	private String nom;
	private Color couleur;
	private Cell cellule;
	
	Piece(String nom, Color couleur, Cell cellule) {
		this.nom = nom;
		this.couleur = couleur;
		this.cellule = cellule;
	}
	
	public String getNom() {
		return this.nom;
	}
	public Color getCouleur() {
		return this.couleur;
	}
	public Cell getCellule() {
		return this.cellule;
	}
	public void setCellule(Cell cellule) {
		this.cellule = cellule;
	}

}
