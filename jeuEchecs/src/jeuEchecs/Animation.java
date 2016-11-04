package jeuEchecs;

public class Animation implements Runnable{
	Panel pan;
	
	public Animation(Panel pan) {
		this.pan = pan;
	}

	@Override
	public void run() {
		pan.animation();		
	}

}
