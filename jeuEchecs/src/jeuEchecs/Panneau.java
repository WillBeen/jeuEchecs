package jeuEchecs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Panneau extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Plateau plateau;
	private int bordure = 10;
	private int coteCellule;
	private int cursorX = -100;
	private int cursorY = -100;
	
	public String tmpTxt = "";
	
	public void setSizes() {
		plateau = new Plateau(8, 8);
		this.coteCellule = Math.min((this.getHeight() - 2 * bordure) / this.plateau.getHauteur(),
				(this.getWidth() - 2 * bordure) / this.plateau.getLargeur());
	}
	
	public void paintComponent(Graphics g) {
		displayPlate((Graphics2D)g);
	}
	
//	Displays the chessplate
	private void displayPlate(Graphics2D g) {
//		DRAW THE CHESSPLATE
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for (int i = 0; i < this.plateau.getLargeur(); i++) {
			for (int j = 0; j < this.plateau.getHauteur(); j++) {
				g.setColor(this.plateau.getCell(i, j).getCouleur());
				g.fillRect(bordure + (i * this.coteCellule), bordure + (j * this.coteCellule), this.coteCellule, this.coteCellule);
			}
		}
		g.setColor(Color.BLACK);
		g.drawRect(bordure, bordure, 
				this.plateau.getLargeur() * this.coteCellule,
				this.plateau.getHauteur() * this.coteCellule);
		
//		HIGHLIGHT THE MOUSEOVERED CELL
		if (onPlate(cursorX, cursorY)) {
			float dash1[] = {10.0f, 5.0f};
			BasicStroke bs = new BasicStroke(3.0f, BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_MITER, 10.0f, dash1, 5.0f);
			g.setStroke(bs);
			g.setColor(Color.BLUE);
			g.drawRect(this.columnToX(this.xToColumn(cursorX)),
					this.rowToY(this.yToRow(cursorY)),
					this.coteCellule, this.coteCellule);
		}		
	}
	
	public void setCursorX(int x) {
		this.cursorX = x;
	}
	
	public void setCursorY(int y) {
		this.cursorY = y;
	}
	
	public Cell getCell(int x, int y) {
		Cell cell;
		int cellX = Math.max(0, Math.min( this.plateau.getLargeur() - 1, (x - bordure) / this.coteCellule));
		int cellY = Math.max(0, Math.min( this.plateau.getHauteur() - 1, (y - bordure) / this.coteCellule));
		this.tmpTxt = cellX + ";" + cellY;
		return this.plateau.getCell(cellX, cellY);
	}
	
//	Return the chessplate column on this X line	
	private int xToColumn(int x) {
		return Math.max(0, Math.min( this.plateau.getLargeur() - 1, (x - bordure) / this.coteCellule));
	}
	
//	Return the chessplate row on this Y line
	private int yToRow(int y) {
		return Math.max(0, Math.min( this.plateau.getHauteur() - 1, (y - bordure) / this.coteCellule));
	}

//	Return the X position of this chessplate column
	private int columnToX(int column) {
		return bordure + column * this.coteCellule;
	}
	
//	Return the Y position of this chessplate row
	private int rowToY(int row) {
		return bordure + row * this.coteCellule;
	}
	
//	Return the chessplate's width
	private int getPlateWidth() {
		return this.plateau.getLargeur() * this.coteCellule;
	}
	
//	Return the chessplate's height
	private int getPlateHeight() {
		return this.plateau.getHauteur() * this.coteCellule;
	}
	
//	True if the X,Y position is on the chessplate
	private boolean onPlate(int x, int y) {
		return ((x > bordure) && (y > bordure)
				&& (x < (bordure + this.getPlateWidth()))
				&& (y < (bordure + this.getPlateHeight())));
	}
}
