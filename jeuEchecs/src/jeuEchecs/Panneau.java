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
	private int bordure = 20;
	private int coteCellule;
	private int cursorX = -100;
	private int cursorY = -100;
	private boolean onPlate = false;
	private float dash[] = {10.0f, 5.0f};
	private float dashPhase = 0.0f;
	
//	Debug msg to display
	public String dbgMsg = "";
	
	public Panneau() {
		plateau = new Plateau(8,8);
	}
	
//	Determines the width (= height) of each cell
	public void setSizes() {
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
		setOnPlate(cursorX, cursorY);
		if (this.onPlate) {
//			this.dashPhase = ((this.dashPhase * 10.0f + 1) % 150.0f) / 10.0f;
			this.dashPhase = (this.dashPhase + 0.1f) % (this.dash[0] + this.dash[1]);
			BasicStroke bs = new BasicStroke(3.0f, BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_MITER, 10.0f, this.dash, this.dashPhase);
			g.setStroke(bs);
			g.setColor(Color.BLUE);
			g.drawRect(this.columnToX(this.xToColumn(cursorX)),
					this.rowToY(this.yToRow(cursorY)),
					this.coteCellule, this.coteCellule);
		}		
		
//		MESSAGE DE DEBUG
		g.setColor(Color.BLACK);
//		this.dbgMsg = "pan height : " + this.getHeight() + " | hauteurCellule : " + this.coteCellule;
		g.drawString(this.dbgMsg, 10, bordure - 5);
	}
	
	public void animation() {
		while (true) {
			while (true) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.coteCellule = Math.min((this.getHeight() - 2 * bordure) / this.plateau.getHauteur(),
						(this.getWidth() - 2 * bordure) / this.plateau.getLargeur());
				this.increaseDashPhase();
				this.repaint();
			}
		}
		
	}
	
	public void setCursorX(int x) {
		this.cursorX = x;
	}
	
	public void setCursorY(int y) {
		this.cursorY = y;
	}
	
//	Return the cell under the coordinates (x,y)
	public Cell getCell(int x, int y) {
		int cellX = Math.max(0, Math.min( this.plateau.getLargeur() - 1, (x - bordure) / this.coteCellule));
		int cellY = Math.max(0, Math.min( this.plateau.getHauteur() - 1, (y - bordure) / this.coteCellule));
		return this.plateau.getCell(cellX, cellY);
	}
	
//	Return the chessplate column on this X coordinate	
	private int xToColumn(int x) {
		return Math.max(0, Math.min( this.plateau.getLargeur() - 1, (x - bordure) / this.coteCellule));
	}
	
//	Return the chessplate row on this Y coordinate
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
	
//	Return the chessplate's width (x coordinate)
	private int getPlateWidth() {
		return this.plateau.getLargeur() * this.coteCellule;
	}
	
//	Return the chessplate's height (y coordinate)
	private int getPlateHeight() {
		return this.plateau.getHauteur() * this.coteCellule;
	}
	
//	Says if the cursor is on the plate
	private void setOnPlate(int x, int y) {
		this.onPlate = ((x > bordure) && (y > bordure)
				&& (x < (bordure + this.getPlateWidth()))
				&& (y < (bordure + this.getPlateHeight())));
	}
	
//	Access the isOnPlate property in order to know if the cursor is on the plate
	public boolean isOnPlate() {
		return this.onPlate;
	}
	
//	Access the dashPhase property in order to animate it
	public float getDashPhase() {
		return this.dashPhase;
	}
	public void setDashPhase(float dashPhase) {
		this.dashPhase = dashPhase;
	}
	public void increaseDashPhase() {
		this.dashPhase = ((this.dashPhase + 0.1f) % (dash[0] + dash[1]));
	}
}
