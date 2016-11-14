package jeuEchecs;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class Board implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Cell[][] board;
//	turn : to know whose turn it is
	int turn = Piece.BLACK;
	
	public Board() {
		this(8,8);
	}
	
	public Board(int hauteur, int largeur) {
		initCells(hauteur, largeur);
	}
	
	protected void initCells(int largeur, int hauteur) {
		board = new Cell[hauteur][largeur];
		for (int i = 0; i < hauteur; i++) {
			for (int j = 0; j < largeur; j++) {
				if ((i + j) % 2 == 0) {
					board[i][j] = new Cell(Cell.WHITE, j, i);
				} else {
					board[i][j] = new Cell(Cell.BLACK, j, i);
				}
			}
		}
	}
	
	public abstract void movePiece(Cell from, Cell to);
	
//	ACCESSEURS
	public int getHeight() {
		return this.board.length;
	}
	public int getWidth() {
		return this.board[0].length;
	}
	public Cell getCell(int column, int row) {
		return this.board[row][column];
	}

	public void saveGame() {
		ObjectOutputStream oos = null;
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream("d:\\saveboard.ser"));
			oos.writeObject(board);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.flush();
					oos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void loadGame() {
		ObjectInputStream ois = null;
		
		try {
			ois = new ObjectInputStream(new FileInputStream("d:\\saveboard.ser"));
			board = (Cell[][]) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
