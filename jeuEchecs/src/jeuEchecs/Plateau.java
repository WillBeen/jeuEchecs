package jeuEchecs;

public class Plateau {
	Cellule[][] plateau;
	
	public Plateau() {
		this.plateau = new Cellule[8][8];
	}
	
	public Plateau(int hauteur, int largeur) {
		this.plateau = new Cellule[hauteur][largeur];
	}
	
//	ACCESSEURS
	public int getHauteur() {
		return this.plateau.length;
	}
	public int getLargeur() {
		return this.plateau[0].length;
	}

}
