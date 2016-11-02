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
		g.setColor(Color.BLACK);
		g.drawRect(bordure, bordure, 
				this.plateau.getLargeur() * this.coteCellule,
				this.plateau.getHauteur() * this.coteCellule);
		for (int i = 0; i < this.plateau.getHauteur(); i++) {
			for (int j = 0; j < this.plateau.getLargeur(); j++) {
				if (((i + j) % 2) == 0) {
					g.fillRect(bordure + (j * this.coteCellule), bordure + (i * this.coteCellule), this.coteCellule, this.coteCellule);
				}
			}
		}
		g.setColor(Color.RED);
//		g.fillOval(100, 100, 50, 50);
		g.fillOval(cursorX - cursorRay, cursorY - cursorRay, 2* cursorRay, 2 * cursorRay);
	}
	
	public void setCursorX(int x) {
		this.cursorX = x;
	}
	
	public void setCursorY(int y) {
		this.cursorY = y;
	}
}
