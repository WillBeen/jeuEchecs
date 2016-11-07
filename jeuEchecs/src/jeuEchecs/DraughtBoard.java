package jeuEchecs;

public class DraughtBoard extends Board{
	public DraughtBoard() {
		this(10, 10);
	}
	
	public DraughtBoard(int width, int height) {
		this.initCells(width, height);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (((i + j) % 2) == 1) {
					if (j < 4) {
						this.getCell(i, j).setPiece(new DraughtPiece(Piece.BLACK));
					} else if (j >= (height - 4)){
						this.getCell(i, j).setPiece(new DraughtPiece(Piece.WHITE));
					}
				}
			}
		}
	}
	
	public void movePiece(Cell from, Cell to) {
		if (((DraughtPiece)from.getPiece()).isSimpleMove(from, to, this)) {
			to.setPiece(from.getPiece());
			from.setPiece(null);
		} else if (((DraughtPiece)from.getPiece()).isDoubleMove(from, to, this)) {
			to.setPiece(from.getPiece());
			from.setPiece(null);
			this.board[(from.getColumn() + to.getColumn()) /2][(from.getRow() + to.getRow()) /2].setPiece(null);
//			getCell2((from.getColumn() + to.getColumn()) /2, (from.getRow() + to.getRow()) /2).setPiece(null);
		}
	}

}
