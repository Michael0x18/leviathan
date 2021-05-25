package leviathan;

public class SoundEffect extends Thread{

	private String path;

	public SoundEffect(String path) {
		this.path = path;
	}
	
	public void run() {
		
		Sound.play(path);
	}

}
