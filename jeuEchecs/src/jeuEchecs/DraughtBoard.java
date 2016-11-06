package jeuEchecs;

public class DraughtBoard extends Board{
	public DraughtBoard() {
		this(10, 10);
	}
	
	public DraughtBoard(int width, int height) {
		this.initCellules(width, height);
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

}
