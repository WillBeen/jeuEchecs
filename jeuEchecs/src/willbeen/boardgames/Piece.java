package willbeen.boardgames;
 
import java.awt.Image;
import java.io.Serializable;

public abstract class Piece implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int color;
	protected String img;
	public static final int BLACK = 0;
	public static final int WHITE = 1;
	protected boolean canMove = false;
	
	Piece(int color) {
		this.color = color;
	}

	public abstract boolean isMoveAllowed(Cell from, Cell to, Board board);
	
	public abstract boolean canEat(Cell cell, Board board);
	
	public int getColor() {
		return this.color;
	}

	public abstract Image getImage();
	
	public abstract void movePiece(Cell from, Cell to, Board board);
	
	public boolean isMyTurn(Board board) {
		return board.getTurn() == color;
	}
	public boolean getCanMove() {
		return canMove;
	}
	public void setCanMove(Cell cell, Board board) {
//		canMove = isMyTurn(board);
		canMove = true;
	}
}
