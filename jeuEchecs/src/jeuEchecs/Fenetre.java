package jeuEchecs;

import javax.swing.JFrame;

public class Fenetre extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Panneau pan = new Panneau();

	public Fenetre(){
		this.setSize(800, 600);
		this.setTitle("Jeu d'�checs");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		// Instanciation d'un objet JPanel
//		JPanel pan = new JPanel();
//		// D�finition de la couleur de fond
//		pan.setBackground(Color.ORANGE);
//		
//		this.setContentPane(pan);

		this.setContentPane(pan);
		
		this.setVisible(true);
		pan.setSizes();
	}
}
