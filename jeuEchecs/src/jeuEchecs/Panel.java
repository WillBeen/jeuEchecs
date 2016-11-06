package jeuEchecs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

public class Panel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Board board;
	private int bordure = 20;
	private int coteCellule;
	private int cursorX = -100;
	private int cursorY = -100;
	private boolean cursorOnPlate = false;
	private float dash[] = {10.0f, 5.0f};
	private float dashPhase = 0.0f;
	public Point moveFrom = new Point();
	public Point moveTo = new Point();
	
//	Debug msg to display
	public String dbgMsg = "";
	
	public Panel() {
//		board = new Board(8,8);
		board = (Board)new DraughtBoard();
	}
	
//	Determines the width (= height) of each cell
	public void setSizes() {
		this.coteCellule = Math.min((this.getHeight() - 2 * bordure) / this.board.getHauteur(),
				(this.getWidth() - 2 * bordure) / this.board.getLargeur());
	}
	
	public void paintComponent(Graphics g) {
		displayBoard((Graphics2D)g);
	}
	
//	Displays the board
	private void displayBoard(Graphics2D g) {
//		DRAWS THE BOARD
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for (int i = 0; i < this.board.getLargeur(); i++) {
			for (int j = 0; j < this.board.getHauteur(); j++) {
				Cell cell = this.board.getCell(i, j);
				g.setColor(cell.getCouleur());
				g.fillRect(bordure + (i * this.coteCellule), bordure + (j * this.coteCellule), this.coteCellule, this.coteCellule);
//				DRAWS THE PIECE
				if (cell.getPiece() != null) {
					g.drawImage(cell.getPiece().getImage(), 
							this.columnToX(i) + (int)(this.coteCellule * 0.05f), 
							this.rowToY(j) + (int)(this.coteCellule * 0.05f), 
							(int)(this.coteCellule * 0.9f), (int)(this.coteCellule * 0.9f), null);
				}
			}
		}
		g.setColor(Color.BLACK);
		g.drawRect(bordure, bordure, 
				this.board.getLargeur() * this.coteCellule,
				this.board.getHauteur() * this.coteCellule);
		
		setCursorOnPlate(cursorX, cursorY);
		if (this.cursorOnPlate) {
//			HIGHLIGHTS THE MOUSEOVERED CELL
			this.dashPhase = (this.dashPhase + 0.1f) % (this.dash[0] + this.dash[1]);
			BasicStroke bs = new BasicStroke(3.0f, BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_MITER, 10.0f, this.dash, this.dashPhase);
			g.setStroke(bs);
			g.setColor(Color.BLUE);
			g.drawRect(this.columnToX(this.xToColumn(cursorX)),
					this.rowToY(this.yToRow(cursorY)),
					this.coteCellule, this.coteCellule);
//			DRAWS THE MOVEMENT LINE
			if (moveFrom != null) {
				g.setColor(Color.GREEN);
				g.drawLine(moveFrom.x, moveFrom.y, moveTo.x, moveTo.y);
			}
		}				
		
		
//		MESSAGE DE DEBUG
		g.setColor(Color.BLACK);
//		this.dbgMsg = from.toString() + "###" + to.toString();
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
				this.coteCellule = Math.min((this.getHeight() - 2 * bordure) / this.board.getHauteur(),
						(this.getWidth() - 2 * bordure) / this.board.getLargeur());
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
		int cellX = Math.max(0, Math.min( this.board.getLargeur() - 1, (x - bordure) / this.coteCellule));
		int cellY = Math.max(0, Math.min( this.board.getHauteur() - 1, (y - bordure) / this.coteCellule));
		return this.board.getCell(cellX, cellY);
	}
	public Cell getCell(Point p){
		return getCell(p.x, p.y);
	}
	
//	Return the board column on this X coordinate	
	private int xToColumn(int x) {
		return Math.max(0, Math.min( this.board.getLargeur() - 1, (x - bordure) / this.coteCellule));
	}
	
//	Return the board row on this Y coordinate
	private int yToRow(int y) {
		return Math.max(0, Math.min( this.board.getHauteur() - 1, (y - bordure) / this.coteCellule));
	}

//	Return the X position of this board column
	private int columnToX(int column) {
		return bordure + column * this.coteCellule;
	}
	
//	Return the Y position of this board row
	private int rowToY(int row) {
		return bordure + row * this.coteCellule;
	}
	
//	Return the board width (x coordinate)
	private int getPlateWidth() {
		return this.board.getLargeur() * this.coteCellule;
	}
	
//	Return the board height (y coordinate)
	private int getPlateHeight() {
		return this.board.getHauteur() * this.coteCellule;
	}
	
//	Says if the cursor is on the plate
	private void setCursorOnPlate(int x, int y) {
		this.cursorOnPlate = ((x > bordure) && (y > bordure)
				&& (x < (bordure + this.getPlateWidth()))
				&& (y < (bordure + this.getPlateHeight())));
	}
	
//	Access the isOnPlate property in order to know if the cursor is on the plate
	public boolean isOnPlate() {
		return this.cursorOnPlate;
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
	
	public Board getBoard(){
		return board;
	}
	
	public void setMoveFrom(Point from) {
		moveFrom = from;
	}
	public void setMoveTo(Point to) {
		moveTo = to;
	}
	
	public void movePiece(){
		movePiece(moveFrom,moveTo);
	}
	public void movePiece(Point from, Point to) {
		if ((getCell(from) != getCell(to)) && (getCell(from).getPiece() != null)) {
			board.movePiece(getCell(from), getCell(to));
		}
	}
}
