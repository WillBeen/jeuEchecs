package willbeen.boardgames;

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

	@Override
	public boolean canEat(Cell cell, Board board) {
		boolean canEat = false;
		int[] possibleDirX = {-1, 1};
		int[] possibleDirY = {-1, 1};
		System.out.println("####################");
		for (int dirX : possibleDirX) {
			for (int dirY : possibleDirY) {
				canEat |= canEatOnThisDirection(cell, board, dirX, dirY);
			}
		}
		return canEat;
	}
	
	private boolean canEatOnThisDirection(Cell cell, Board board, int dirX, int dirY) {
		int cellX = cell.getColumn();
		int cellY = cell.getRow();
		Cell eaten;
		Cell freeSpace;
		int i = 1;
		while (((cellX + (i + 1) * dirX) >= 0) && ((cellX + (i + 1) * dirX) < board.getWidth())
				&& ((cellY + (i + 1) * dirY >= 0)) && ((cellY + (i + 1) * dirY) < board.getHeight())) {
			eaten = board.getCell(cellX + i * dirX,  cellY + i * dirY);
			freeSpace = board.getCell(cellX + (i + 1) * dirX,  cellY + (i + 1) * dirY);
			if (eaten.getPiece() != null) {
				if ((eaten.getPiece().getColor() != color) && (freeSpace.getPiece() == null)) {
					return true;
				} else {
					return false;
				}
			} 
			i++;
		}
		return false;
	}

}
