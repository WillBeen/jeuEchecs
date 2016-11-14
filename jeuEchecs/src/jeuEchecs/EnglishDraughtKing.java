package jeuEchecs;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EnglishDraughtKing extends Piece {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	EnglishDraughtKing(int color) {
		super(color);
		if (color == Piece.BLACK) {
			img ="images/draughts/blackDraughtKing.png";
		} else {
			img = "images/draughts/whiteDraughtKing.png";
		}
	}

	@Override
	public boolean isMoveAllowed(Cell from, Cell to, Board board) {
		boolean destinationIsEmpty = to.getPiece() == null;
		boolean isDiagonal = Math.abs(to.getColumn() - from.getColumn()) == Math.abs(to.getRow() - from.getRow());
		boolean oneEnemyOnTheWay = false;
		if (destinationIsEmpty && isDiagonal) {
//			Is there only 1 enemy piece on the way right before the destination
			int columnDirection = (int)Math.signum(to.getColumn() - from.getColumn());
			int rowDirection = (int)Math.signum(to.getRow() - from.getRow());
			Point enemyPosition = new Point(from.getColumn() + columnDirection, from.getRow() + rowDirection);
			while ((board.getCell(enemyPosition.x, enemyPosition.y).getPiece() == null)
					&& (enemyPosition.y < to.getRow())) {
				enemyPosition = new Point(enemyPosition.x + columnDirection, enemyPosition.y + rowDirection);
			}
			if (board.getCell(enemyPosition.x, enemyPosition.y).getPiece() != null){
				oneEnemyOnTheWay = (board.getCell(enemyPosition.x, enemyPosition.y).getPiece().getColor() != color)
						&& ((to.getRow() - enemyPosition.y) == rowDirection);
			} else {
				oneEnemyOnTheWay = true;
			}
		}
		return oneEnemyOnTheWay;
	}

	@Override
	public void movePiece(Cell from, Cell to, Board board) {
		if (isMoveAllowed(from, to, board)) {
			to.setPiece(this);
			from.setPiece(null);
			int columnDirection = (int)Math.signum(to.getColumn() - from.getColumn());
			int rowDirection = (int)Math.signum(to.getRow() - from.getRow());
			board.getCell(to.getColumn() - columnDirection, to.getRow() - rowDirection).setPiece(null);
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

}
