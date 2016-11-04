package jeuEchecs;

import java.awt.Image;

public abstract class Piece {
	private int color;
	protected Image img;
	public static final int BLACK = 0;
	public static final int WHITE = 1;
	
	Piece(int color) {
		this.color = color;
	}
	
	public int getColor() {
		return this.color;
	}

	public Image getImage() {
		return this.img;
	}
}
