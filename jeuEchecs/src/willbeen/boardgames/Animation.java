package willbeen.boardgames;
 
public class Animation implements Runnable{
	private Panel pan;
	
	Animation(Panel pan) {
		this.pan = pan;
	}

	@Override
	public void run() {
		pan.animation();		
	}

}
