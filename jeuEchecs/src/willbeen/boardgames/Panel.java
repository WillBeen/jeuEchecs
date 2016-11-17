package willbeen.boardgames;
 
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Panel extends JPanel implements ActionListener {
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
	public Point moveFrom = null;
	public Point moveTo = null;
	private Cell fromCell = null;
	private Cell toCell = null;
	private boolean mousePressed = false;
	private ButtonDescription[] buttonDescriptions;
	private JButton[] actionButtons;
//	private JButton btnSaveGame;
//	private JButton btnLoadGame;
	
	public Panel() {
//		board = new Board(8,8);
		board = (Board)new EnglishDraughtBoard();
		
//		Initializes button
		int buttonNumber = 3;
		buttonDescriptions = new ButtonDescription[buttonNumber];
		actionButtons = new JButton[buttonNumber];
		int index = 0;
		buttonDescriptions[index++] = new ButtonDescription("Start a new game", "newGame");
		buttonDescriptions[index++] = new ButtonDescription("Save this game", "saveGame");
		buttonDescriptions[index++] = new ButtonDescription("Load a saved game", "loadGame");
		
		for(int i = 0; i < buttonDescriptions.length; i++) {
			actionButtons[i] = new JButton(buttonDescriptions[i].getText());
			actionButtons[i].setVerticalTextPosition(AbstractButton.CENTER);
			actionButtons[i].setHorizontalTextPosition(AbstractButton.LEADING);
			actionButtons[i].setMnemonic(KeyEvent.VK_D);
			actionButtons[i].setActionCommand(buttonDescriptions[i].getAction());
			actionButtons[i].addActionListener(this);
			add(actionButtons[i]);
		}
	}
	
//	Determines the width (= height) of each cell
	public void setSizes() {
		this.coteCellule = Math.min((this.getHeight() - 2 * bordure) / this.board.getHeight(),
				(this.getWidth() - 2 * bordure) / this.board.getWidth());
	}
	
	public void paintComponent(Graphics g) {
		displayBoard((Graphics2D)g);
	}
	
//	Displays the board
	private void displayBoard(Graphics2D g) {
//		DRAWS THE BOARD
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for (int row = 0; row < this.board.getHeight(); row++) {
			for (int column = 0; column < this.board.getWidth(); column++) {
				Cell cell = this.board.getCell(column, row);
				g.setColor(cell.getColor());
				g.fillRect(bordure + (column * this.coteCellule), bordure + (row * this.coteCellule), this.coteCellule, this.coteCellule);
//				DRAWS THE PIECE
				if (cell.getPiece() != null) {
					g.drawImage(cell.getPiece().getImage(), 
							this.columnToX(column) + (int)(this.coteCellule * 0.05f), 
							this.rowToY(row) + (int)(this.coteCellule * 0.05f), 
							(int)(this.coteCellule * 0.9f), (int)(this.coteCellule * 0.9f), null);
				}
			}
		}
		g.setColor(Color.BLACK);
		g.drawRect(bordure, bordure, 
				this.board.getWidth() * this.coteCellule,
				this.board.getHeight() * this.coteCellule);
		
//		POSITIONNEMENT DU BOUTON DE SAUVEGARDE DE LA PARTIE
//		btnSaveGame.setLocation(2 * bordure + getWidth(), bordure);
		for (int i = 0; i < actionButtons.length; i++) {
			actionButtons[i].setLocation(2 * bordure + this.getBoardWidth(), 
					bordure + (actionButtons[i].getHeight() + 10) * i);
		}
		
//		DEBUG MSG : displays true if the piece can eat, false if it can't
		String str = "";
		if (isOnBoard()) {
			Cell c = board.getCell(this.xToColumn(cursorX), yToRow(cursorY));
//			if (c.getPiece() != null) {
//				str =  c.getPiece().canEat(c, board) + "";
//			}
			str =  c.getCanEat() + "";
		}
		g.drawString(str, 10, 10);
		
		setCursorOnBoard(cursorX, cursorY);
//		SAVES THE DEFAULT STOKE
		BasicStroke defaultBS = (BasicStroke)g.getStroke();
//		CHANGES THE DRAWINLINE TO A DASHED LINE (the dashPhase property is animated)
		BasicStroke bs = new BasicStroke(3.0f, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_MITER, 10.0f, this.dash, this.dashPhase);
		g.setStroke(bs);
		if (this.cursorOnPlate) {
////			HIGHLIGHTS THE MOUSEOVERED CELL IF THE MOUSE BUTTON IS NOT PRESSED
//			if (!mousePressed) {
//				g.setColor(Color.BLUE);
//				g.drawRect(this.columnToX(this.xToColumn(cursorX)),
//						this.rowToY(this.yToRow(cursorY)),
//						this.coteCellule, this.coteCellule);
//			}
//			DRAWS THE MOVEMENT LINE
			if (fromCell != null && toCell != null) {
				if (fromCell.getPiece() != null) {
					g.setColor(Color.RED);
					if (fromCell.getPiece().isMoveAllowed(fromCell, toCell, board)) {
						g.setColor(Color.GREEN);
					}
					g.drawLine(cellCenter(fromCell).x, cellCenter(fromCell).y, cellCenter(toCell).x, cellCenter(toCell).y);
				}
			}
		}	
//		HIGHLIGHTS THE CELLS CONTAINING A PIECE THAT CAN EAT
		g.setColor(Color.GREEN);
		for(Cell[] row : board.getBoard()) {
			for(Cell c : row) {
				if (c.getCanEat()) {
					g.drawRect(columnToX(c.getColumn()), rowToY(c.getRow()), coteCellule, coteCellule);
				}
			}
		}
//		SETS THE DEFAULT STOKE BACK AS ACTIVE STROKE
		g.setStroke(defaultBS);
	}
	
	public void animation() {
		while (true) {
			while (true) {
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				coteCellule = Math.min((getHeight() - 2 * bordure) / board.getHeight(),
						(getWidth() - 2 * bordure) / board.getWidth());
				increaseDashPhase();
				repaint();
			}
		}
		
	}
	
	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}
	
	public void setCursorX(int x) {
		this.cursorX = x;
	}
	
	public void setCursorY(int y) {
		this.cursorY = y;
	}
	
//	Return the cell under the coordinates (x,y)
	public Cell getCell(int x, int y) {
		int cellX = Math.max(0, Math.min( this.board.getWidth() - 1, (x - bordure) / this.coteCellule));
		int cellY = Math.max(0, Math.min( this.board.getHeight() - 1, (y - bordure) / this.coteCellule));
		return this.board.getCell(cellX, cellY);
	}
	public Cell getCell(Point p){
		return getCell(p.x, p.y);
	}
	
//	Return the board column on this X coordinate	
	private int xToColumn(int x) {
		return Math.max(0, Math.min( this.board.getWidth() - 1, (x - bordure) / this.coteCellule));
	}
	
//	Return the board row on this Y coordinate
	private int yToRow(int y) {
		return Math.max(0, Math.min( this.board.getHeight() - 1, (y - bordure) / this.coteCellule));
	}

//	Return the X position of this board column
	private int columnToX(int column) {
		return bordure + column * this.coteCellule;
	}
	
//	Return the Y position of this board row
	private int rowToY(int row) {
		return bordure + row * this.coteCellule;
	}
	
//	Return the center position of a cell in this panel
	private Point cellCenter(Cell c) {
		int x = this.columnToX(c.getColumn()) + this.coteCellule / 2;
		int y = this.columnToX(c.getRow()) + this.coteCellule / 2;
		return new Point(x, y);
	}
	
//	Return the board width (x coordinate)
	private int getBoardWidth() {
		return this.board.getWidth() * this.coteCellule;
	}
	
//	Return the board height (y coordinate)
	private int getBoardHeight() {
		return this.board.getHeight() * this.coteCellule;
	}
	
//	Says if the cursor is on the plate
	private void setCursorOnBoard(int x, int y) {
		this.cursorOnPlate = ((x > bordure) && (y > bordure)
				&& (x < (bordure + this.getBoardWidth()))
				&& (y < (bordure + this.getBoardHeight())));
	}
	
//	Access the isOnPlate property in order to know if the cursor is on the plate
	public boolean isOnBoard() {
		return this.cursorOnPlate;
	}
	public boolean isOnBoard(Point p) {
		return ((p.x > bordure) && (p.y > bordure)
				&& (p.x < (bordure + this.getBoardWidth()))
				&& (p.y < (bordure + this.getBoardHeight())));
	}
	
//	Access the dashPhase property in order to animate it
	public float getDashPhase() {
		return this.dashPhase;
	}
	public void setDashPhase(float dashPhase) {
		this.dashPhase = dashPhase;
	}
	public void increaseDashPhase() {
		dashPhase = ((dash[0] + dash[1]  + dashPhase - 0.4f) % (dash[0] + dash[1]));
	}
	
	public Board getBoard(){
		return board;
	}
	
	public void setMoveFrom(Point p) {
		moveFrom = p;
		if (p != null) {
			if (isOnBoard(p)) {
			fromCell = getCell(p);
			}
		} else {
			fromCell = null;
		}
	}
	public void setMoveTo(Point p) {
		moveTo = p;
		if (p != null) {
			if (isOnBoard(p)) {
			toCell = getCell(p);
			}
		} else {
			toCell = null;
		}
	}
	
	public void movePiece(){
		movePiece(moveFrom,moveTo);
	}
	public void movePiece(Point from, Point to) {
		if (isOnBoard()) {
			if ((getCell(from) != getCell(to)) && (getCell(from).getPiece() != null)) {
				board.movePiece(getCell(from), getCell(to));
			}
		}
//		Sets the "canEat" property for every cell of the board
		board.setCanEat();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "newGame" : board = (Board) new EnglishDraughtBoard();
		break;
		case "saveGame" : board.saveGame();
		break;
		case "loadGame" : board.loadGame();
		break;
		}
	}
}
