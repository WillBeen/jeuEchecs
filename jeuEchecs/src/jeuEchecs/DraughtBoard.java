package jeuEchecs;

public class DraughtBoard extends Board{
	public DraughtBoard() {
		super(10,10);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (((i + j) % 2) == 1) {
					if (j < 4) {
						this.getCell(i, j).setPiece(new DraughtPiece(Piece.BLACK));
					} else if (j > 6){
						this.getCell(i, j).setPiece(new DraughtPiece(Piece.WHITE));
					}
				}
			}
		}
	}

}
