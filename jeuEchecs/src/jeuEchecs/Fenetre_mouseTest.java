package jeuEchecs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class Fenetre_mouseTest extends JFrame implements MouseMotionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	  private Panneau_mouseTest pan = new Panneau_mouseTest();
	  
	  public Fenetre_mouseTest(){        
	    this.setTitle("Animation");
	    this.setSize(300, 300);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setContentPane(pan);
	    pan.addMouseMotionListener(this);
	    addMouseMotionListener(this);
	    this.setVisible(true);
	    go();
	  }
	  
	  public void mouseClicked(MouseEvent e) {
		  pan.setCursorX(e.getX());
		  pan.setCursorY(e.getY());
	  }

	  private void go(){
		  double xSpeed = .55;
		  double ySpeed = .75;
	      double x = (double)pan.getPosX(), y = (double)pan.getPosY();
	    while(true){
	      x += xSpeed;
	      y += ySpeed;
	      if (x > pan.areaWidth()) { 
	    	  xSpeed = -randomSpeed(); 
	      }
	      if (x < 0) { 
	    	  xSpeed = randomSpeed(); 
	      }
	      if (y > pan.areaHeigth()) { 
	    	  ySpeed =  -randomSpeed(); 
	      }
	      if (y < 0) { 
	    	  ySpeed = randomSpeed(); 
	      }
	      pan.setPosX((int)x);
	      pan.setPosY((int)y);
	      pan.repaint();  
	      try {
	        Thread.sleep(10);
	      } catch (InterruptedException e) {
	        e.printStackTrace();
	      }
	    }
	  }   
	  
	  private static double randomSpeed() {
		  return (0.5 + (Math.random() * 0.5));
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
