package jeuEchecs;

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

//	Returns a (row,column) position array in order to say where this piece can go
	public boolean isMoveAllowed(Cell from, Cell to, Board board) {
		boolean destinationIsEmpty;
		boolean moveIsOK;
//		False if there is already a piece on the target cell
		destinationIsEmpty = to.getPiece() == null;
//		Look at the direction of the move
		int direction;
		if (color == Piece.BLACK) {
			direction = 1;
		} else {
			direction = -1;
		}
		moveIsOK = (to.getRow() - from.getRow()) == direction;
//		True if the piece "eats" an ennemy pice
		moveIsOK |= (Math.abs(from.getRow() - to.getRow()) == 2)
				&& (Math.abs(from.getColumn() - to.getColumn()) == 2);
//				&& (board.getCell((to.getRow() + from.getRow()) / 2, (to.getColumn() +from.getColumn()) / 2).getPiece().getColor()
//						!= from.getPiece().getColor());
		return destinationIsEmpty && moveIsOK;
	}

}
