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
	public boolean isMoveAllowed(Cell from, Cell to) {
		boolean result = true;
//		False if there is already a piece on the target cell
		result = to.getPiece() == null;
//		Look at the direction of the move
		int direction;
		if (color == Piece.BLACK) {
			direction = 1;
		} else {
			direction = -1;
		}
		result &= (to.getRow() - from.getRow()) == direction;
		return result;
	}

}
