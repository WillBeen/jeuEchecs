package jeuEchecs;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class Game extends JFrame implements MouseListener, MouseMotionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Panel pan = new Panel();
	private Point dragOrigin = new Point();
	
	public static void main(String[] args) {
		Game g = new Game();
		g.setVisible(true);
	}

	public Game(){
	    pan.addMouseMotionListener(this);
	    pan.addMouseListener(this);
	    pan.setOpaque(true);
		this.setSize(800, 600);
		this.setTitle("Jeu de dames anglaises");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(pan);
		this.setVisible(true);
		Thread threadAnim = new Thread(new Animation(pan));
		threadAnim.start();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		pan.setMoveTo(e.getPoint());
		pan.setCursorX(e.getX());
		pan.setCursorY(e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		pan.setCursorX(e.getX());
		pan.setCursorY(e.getY());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		pan.setMoveFrom(e.getPoint());
		pan.setMoveTo(e.getPoint());
		pan.setMousePressed(true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		pan.movePiece();
		pan.setMoveFrom(null);
		pan.setMoveTo(null);
		pan.setMousePressed(false);
	}

	public Point getDragOrigin() {
		return dragOrigin;
	}
}
