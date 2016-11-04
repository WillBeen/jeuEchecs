package jeuEchecs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class Window extends JFrame implements MouseMotionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Panel pan = new Panel();

	public Window(){
		this.setSize(800, 600);
		this.setTitle("Jeu d'échecs");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(pan);
	    pan.addMouseMotionListener(this);
	    addMouseMotionListener(this);
		this.setVisible(true);
		Thread threadAnim = new Thread(new Animation(pan));
		threadAnim.start();
	}

	private void go(){
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		pan.setCursorX(e.getX());
		pan.setCursorY(e.getY());
	}
}
