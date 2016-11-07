package jeuEchecs;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class Window extends JFrame implements MouseListener, MouseMotionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Panel pan = new Panel();
	private Point dragOrigin = new Point();

	public Window(){
		this.setSize(800, 600);
		this.setTitle("Jeu d'échecs");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(pan);
	    pan.addMouseMotionListener(this);
//	    addMouseMotionListener(this);
	    pan.addMouseListener(this);
//	    addMouseListener(this);
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
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		pan.movePiece();
		pan.setMoveFrom(null);
		pan.setMoveTo(null);
	}

	public Point getDragOrigin() {
		return dragOrigin;
	}
}
