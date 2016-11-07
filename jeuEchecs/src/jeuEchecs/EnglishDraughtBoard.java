package jeuEchecs;

public class EnglishDraughtBoard extends DraughtBoard {

	public EnglishDraughtBoard() {
		super(8, 8, 3);
//		temporary : to test the King moves
		this.getCell(1, 0).setPiece(new EnglishDraughtKing(Piece.WHITE));
		this.getCell(2,1).setPiece(null);
		this.getCell(3,2).setPiece(null);
	}

	public void setSuperPiece(Cell c, int color) {
		c.setPiece(new DraughtPiece(color));
	}
}
