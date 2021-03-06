package jeuEchecs;

public abstract class DraughtBoard extends Board{
//	public DraughtBoard() {
//		this(8, 8);
//	}
	
	public DraughtBoard(int width, int height, int rowNbPerPlayer) {
		this.initCells(width, height);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (((i + j) % 2) == 1) {
					if (j < rowNbPerPlayer) {
						this.getCell(i, j).setPiece(new DraughtPiece(Piece.BLACK));
					} else if (j >= (height - rowNbPerPlayer)){
						this.getCell(i, j).setPiece(new DraughtPiece(Piece.WHITE));
					}
				}
			}
		}
	}
	
	public void movePiece(Cell from, Cell to) {
		from.getPiece().movePiece(from, to, this);
	}
	
	public abstract void setSuperPiece(Cell c, int color);
}
