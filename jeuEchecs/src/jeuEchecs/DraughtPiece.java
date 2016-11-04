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

}
