package willbeen.boardgames;

public class EnglishDraughtBoard extends DraughtBoard {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EnglishDraughtBoard() {
		super(8, 8, 3);
////		temporary : to test the King moves
//		this.getCell(1, 0).setPiece(new EnglishDraughtKing(Piece.WHITE));
//		this.getCell(2,1).setPiece(null);
//		this.getCell(3,2).setPiece(null);
//		this.setCanEat();
	}

	public void setSuperPiece(Cell c, int color) {
		c.setPiece(new DraughtPiece(color));
	}
}
