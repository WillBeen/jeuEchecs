package jeuEchecs;

import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DraughtPiece extends Piece {

	 DraughtPiece(int color) {
		super(color);
		try {
			if (color == Piece.BLACK) {
				this.img = ImageIO.read(new File("images/draughts/pion_dame_noir.png"));
			} else {
				this.img = ImageIO.read(new File("images/draughts/pion_dame_blanc.png"));
			}
        } catch (IOException e) {
        	e.printStackTrace();
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
		boolean destinationIsEmpty = false;
		boolean simpleMoveOK = false;
		Point from = new Point(fromCell.getColumn(), fromCell.getRow());
		Point to = new Point(toCell.getColumn(), toCell.getRow());
		Piece toPiece = toCell.getPiece();
//		False if there is already a piece on the target cell
		destinationIsEmpty = toPiece == null;
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
		boolean destinationIsEmpty = false;
		boolean doubleMoveOK = false;
		Point from = new Point(fromCell.getColumn(), fromCell.getRow());
		Point to = new Point(toCell.getColumn(), toCell.getRow());
		Piece fromPiece = fromCell.getPiece();
		Piece toPiece = toCell.getPiece();
//		False if there is already a piece on the target cell
		destinationIsEmpty = toPiece == null;
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
}
