package jeuEchecs;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panneau_mouseTest extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	  private int posX = 100;
	  private int posY = 100;
	  private int cursorX = 200;
	  private int cursorY = 200;
	  private int cursorRay = 10;
	  private int cursorInt = cursorRay - 2;
	  public static final int largeurBordure = 10;
	  public static final int ballRay = 25;
	  static final String NEWLINE = System.getProperty("line.separator");

	  public void paintComponent(Graphics g){
		  g.setColor(Color.BLACK);
		  g.fillRect(0, 0, this.getWidth(), this.getHeight());
		  g.setColor(Color.WHITE);
		  g.fillRect(this.minX(), this.minY(), this.areaWidth(), this.areaHeigth());
		  g.setColor(Color.BLUE);
		  g.fillOval(posX + ballRay, posY + ballRay,
				  (ballRay * 2), (ballRay * 2));
		  g.setColor(Color.BLACK);
		  g.fillOval(cursorX - (cursorRay / 2), cursorY - (cursorRay / 2),
				  cursorRay, cursorRay);
		  g.setColor(Color.GREEN);
		  g.fillOval(cursorX - (cursorInt / 2), cursorY - (cursorInt / 2),
				  cursorInt, cursorInt);
	  }

	  public int getPosX() {
	    return posX;
	  }

	  public void setPosX(int posX) {
	    this.posX = posX;
	  }

	  public int getPosY() {
	    return posY;
	  }

	  public void setPosY(int posY) {
	    this.posY = posY;
	  }   
	  
	  public void setCursorX(int x) {
		  this.cursorX = Math.max(this.minX() + this.cursorRay, Math.min(this.maxX() - this.cursorRay, x));
	  }   
	  
	  public void setCursorY(int y) {
		  this.cursorY = Math.max(this.minY() + this.cursorRay, Math.min(this.maxY() - this.cursorRay, y));
	  }
	  
	  public int minX() {
		  return largeurBordure;
	  }
	  
	  public int minY() {
		  return largeurBordure;
	  }
	  
	  public int maxX() {
		  return this.getWidth() - largeurBordure;
	  }
	  
	  public int maxY() {
		  return this.getHeight() - largeurBordure;
	  }
	  
	  public int areaWidth() {
		  return maxX() - minX();
	  }
	  
	  public int areaHeigth() {
		  return maxY() - minY();
	  }
}
