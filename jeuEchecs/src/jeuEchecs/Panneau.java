package jeuEchecs;

import java.awt.Color;
import java.awt.Graphics;

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
	private int cursorRay = 5;
	
	public void setSizes() {
		plateau = new Plateau(8, 8);
		this.coteCellule = Math.min((this.getHeight() - 2 * bordure) / this.plateau.getHauteur(),
				(this.getWidth() - 2 * bordure) / this.plateau.getLargeur());
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		Cell selectedCell = this.getCell(cursorX, cursorY);
		selectedCell.setColor(Color.RED);
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
		g.setColor(Color.RED);
		if (selectedCell != null) {
			g.drawRect(bordure + (selectedCell.getColumn() * this.coteCellule), 
					bordure + (selectedCell.getRow() * this.coteCellule), this.coteCellule, this.coteCellule);
		}
		g.fillOval(cursorX - cursorRay, cursorY - cursorRay, 2* cursorRay, 2 * cursorRay);
	}
	
	public void setCursorX(int x) {
		this.cursorX = x;
	}
	
	public void setCursorY(int y) {
		this.cursorY = y;
	}
	
	public Cell getCell(int x, int y) {
//		Cell cell;
//		if (x > bordure && y > bordure 
//				&& x < bordure + this.plateau.getLargeur() * this.coteCellule
//				&& y < bordure + this.plateau.getHauteur() * this.coteCellule) {
//			cell = this.plateau.getCell(x - bordure - (x / this.coteCellule), 
//					y - bordure - (y / this.coteCellule));
//		} else {
//			cell = null;
//		}
		return this.plateau.getCell(2,4);
	}
	
}
