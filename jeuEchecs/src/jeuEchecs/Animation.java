package jeuEchecs;

public class Animation implements Runnable{
	Panneau pan;
	
	public Animation(Panneau pan) {
		this.pan = pan;
	}

	@Override
	public void run() {
		pan.animation();		
	}

}
