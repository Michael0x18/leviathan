package leviathan;

public class BGMusic extends Thread{

	public void run() {
		while (true)
		Sound.play("Build/BattleLoop.wav");
	}

}
