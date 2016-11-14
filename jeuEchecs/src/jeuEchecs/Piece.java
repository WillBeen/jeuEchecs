package jeuEchecs;

import java.awt.Image;
import java.io.Serializable;

public abstract class Piece implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int color;
	protected String img;
	public static final int BLACK = 0;
	public static final int WHITE = 1;
	
	Piece(int color) {
		this.color = color;
	}

	public abstract boolean isMoveAllowed(Cell from, Cell to, Board board);
	
	public int getColor() {
		return this.color;
	}

	public abstract Image getImage();
	
	public abstract void movePiece(Cell from, Cell to, Board board);
}
