package jeuEchecs;

import java.awt.Color;

public class Cell {
	private Color color;
	private int row;
	private int column;
	private Piece piece = null;
	
	Cell(Color color, int row, int column) {
		this.color = color;
	}
	
	public Color getCouleur() {
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
}
