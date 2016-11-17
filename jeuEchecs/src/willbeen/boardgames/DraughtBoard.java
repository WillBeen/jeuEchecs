package willbeen.boardgames;
 
public abstract class DraughtBoard extends Board{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DraughtBoard(int width, int height, int rowNbPerPlayer) {
		this.initCells();
		Cell cell;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (((i + j) % 2) == 1) {
					cell = getCell(i, j);
					if (j < rowNbPerPlayer) {
						cell.setPiece(new DraughtPiece(Piece.BLACK));
					} else if (j >= (height - rowNbPerPlayer)){
						cell.setPiece(new DraughtPiece(Piece.WHITE));
					}
				}
			}
		}
		for (Cell[] row : board) {
			for (Cell c : row) {
				if (c.getPiece() != null) {
					c.getPiece().setCanMove(c, this);
					c.setCanMove(c.getPiece().getCanMove());
				}
			}
		}
	}
	
	public void movePiece(Cell from, Cell to) {
		Piece fromPiece = from.getPiece();
		if ((fromPiece.getColor() == getTurn()) && 
				((getCanEat(fromPiece.getColor()) && from.getCanEat())
						|| !getCanEat(fromPiece.getColor()))) {
			from.getPiece().movePiece(from, to, this);
			if (fromPiece == to.getPiece()) {
				nextPlayerTurn();
			}
		}
	}
	
	public abstract void setSuperPiece(Cell c, int color);
	
	public void setTurn(int turn) {
		if ((turn == Piece.BLACK) || (turn == Piece.WHITE)) {
			super.setTurn(turn);
		}
	}
	
	public void nextPlayerTurn() {
		if (getTurn() == Piece.BLACK) {
			setTurn(Piece.WHITE);
		} else {
			setTurn(Piece.BLACK);
		}
	}

	
	public void setCanMove() {
		for (Cell[] row : board) {
			for (Cell c : row) {
				if (c.getPiece() != null) {
					c.setCanMove(c.getPiece().getCanMove());
				}
			}
		}
	}
}
