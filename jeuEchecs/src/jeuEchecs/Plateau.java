package jeuEchecs;

public class Plateau {
	Object[][] plateau;
	
	public Plateau() {
		this.plateau = new Object[8][8];
	}
	
	public Plateau(int hauteur, int largeur) {
		this.plateau = new Object[hauteur][largeur];
	}
	
//	ACCESSEURS
	public int getHauteur() {
		return this.plateau.length;
	}
	public int getLargeur() {
		return this.plateau[0].length;
	}

}
