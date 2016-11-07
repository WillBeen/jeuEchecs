package jeuEchecs;

import java.awt.Color;

public class Cell {
	private Color color;
	private int row;
	private int column;
	private Piece piece = null;
	
	public final static int BLACK = 0;
	public final static int WHITE = 1;
	
	public Cell(int color, int column, int row) {
		if (color == BLACK) {
			this.color = new Color(50, 10, 10);
		} else {
			this.color = new Color(244, 235, 124);
		}
		this.row = row;
		this.column = column;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public int getRow() {
		return this.row;
	}
	public int getColumn() {
		return this.column;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Piece getPiece() {
		return this.piece;
	}
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	public String toString() {
		return "Column : " + column + " - Row : " + row;
	}
}
