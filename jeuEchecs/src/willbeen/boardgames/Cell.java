package willbeen.boardgames;

import java.awt.Color;
import java.io.Serializable;

public class Cell implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color color;
	private int row;
	private int column;
	private Piece piece = null;
	private boolean canEat = false;
	
	public final static int BLACK = 0;
	public final static int WHITE = 1;
	
	public Cell(int color, int column, int row) {
		if (color == BLACK) {
			this.color = new Color(50, 10, 10);
		} else {
			this.color = new Color(244, 235, 124);
		}
		this.column = column;
		this.row = row;
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
	
	public void setCanEat(Board board) {
		if (getPiece() != null) {
			canEat = getPiece().canEat(this, board);
		} else {
			canEat = false;
		}
	}
	public boolean getCanEat() {
		return canEat;
	}
}