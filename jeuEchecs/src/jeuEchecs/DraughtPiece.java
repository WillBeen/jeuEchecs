package jeuEchecs;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DraughtPiece extends Piece {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DraughtPiece(int color) {
		super(color);
		if (color == Piece.BLACK) {
			img = "images/draughts/blackDraughtPiece.png";
		} else {
			img = "images/draughts/whiteDraughtPiece.png";
		}
	}
		
	 @Override
	public boolean isMoveAllowed(Cell from, Cell to, Board board) {
		 if (from.getPiece() == null) {
			 return false;
		 } else {
			 return isSimpleMove(from, to, board) || isDoubleMove(from, to, board);
		 }
	}
	 
	 

//	Returns a boolean to determines if the move is a simple move
	public boolean isSimpleMove(Cell fromCell, Cell toCell, Board board) {
		boolean simpleMoveOK = false;
		Point from = new Point(fromCell.getColumn(), fromCell.getRow());
		Point to = new Point(toCell.getColumn(), toCell.getRow());
		Piece toPiece = toCell.getPiece();
//		False if there is already a piece on the target cell
		boolean destinationIsEmpty = toPiece == null;
//		Look at the direction of the move if it's a simple move
		int direction;
		if (color == Piece.BLACK) {
			direction = 1;
		} else {
			direction = -1;
		}
		simpleMoveOK = ((to.y - from.y) == direction) && (Math.abs(to.x - from.x) == 1);
		
		return destinationIsEmpty && simpleMoveOK;
	}

//	Returns a boolean to determines if the move is a double move
	public boolean isDoubleMove(Cell fromCell, Cell toCell, Board board) {
		boolean doubleMoveOK = false;
		Point from = new Point(fromCell.getColumn(), fromCell.getRow());
		Point to = new Point(toCell.getColumn(), toCell.getRow());
		Piece fromPiece = fromCell.getPiece();
		Piece toPiece = toCell.getPiece();
//		False if there is already a piece on the target cell
		boolean destinationIsEmpty = toPiece == null;
//		If it's a double move
		if((Math.abs(to.x - from.x) == 2) && (Math.abs(to.y - from.y) == 2) && destinationIsEmpty) {
//			If there is a piece in the "eaten" cell
			Point eaten = new Point((from.x + to.x) / 2, (from.y + to.y) /2);
			Piece eatenPiece = board.getCell(eaten.x, eaten.y).getPiece();
			if (eatenPiece != null) {
//				Determines whether if this piece is enemy or not
				doubleMoveOK = eatenPiece.getColor() != fromPiece.getColor();
			}
		}
		
		return doubleMoveOK;
	}
	
	public void movePiece(Cell from, Cell to, Board board) {
		if (isSimpleMove(from, to, board)) {
			to.setPiece(from.getPiece());
			from.setPiece(null);
		} else if (isDoubleMove(from, to, board)) {
			to.setPiece(from.getPiece());
			from.setPiece(null);
			board.getCell((from.getColumn() + to.getColumn()) /2, (from.getRow() + to.getRow()) /2).setPiece(null);
		}
		
		if ((to.getRow() == board.getHeight() - 1) && (color == Piece.BLACK)) {
			to.setPiece(new EnglishDraughtKing(Piece.BLACK));
		} else if ((to.getRow() == 0) && (color == Piece.WHITE)) {
			to.setPiece(new EnglishDraughtKing(Piece.WHITE));
		}
		
	}

	@Override
	public Image getImage() {
		Image image = null;
		try {
				image = ImageIO.read(new File(img));
        } catch (IOException e) {
        	e.printStackTrace();
        }
		return image;
	}

	public boolean canEat(Cell cell, Board board) {
		boolean canEat = false;
		int[] possibleDirectionX = {-1, 1};
		int[] possibleDirectionY = {-1, 1};
		for (int dirX : possibleDirectionX) {
			for (int dirY : possibleDirectionY) {
				Point dest = new Point(cell.getColumn() + 2 * dirX, cell.getRow() + 2 * dirY);
				Point eaten = new Point(cell.getColumn() + dirX, cell.getRow() + dirY);
				if ((dest.x >= 0) && (dest.x < board.getWidth())
						&& (dest.y >= 0) && (dest.y < board.getHeight())) {
					if (board.getCell(eaten.x, eaten.y).getPiece() != null) {
						canEat |= (board.getCell(eaten.x, eaten.y).getPiece().getColor() != color)
								&& (board.getCell(dest.x, dest.y).getPiece() == null);
					}
				}
			}
		}
		return canEat;
	}
}
