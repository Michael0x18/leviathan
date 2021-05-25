package leviathan;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class TrapTile extends GroundTile implements ActionListener {

	private static Image tile = new ImageIcon("Build/netherrack.png").getImage();
	private Timer hit = new Timer(1000, this);

	public TrapTile(int x, int y) {
		super(x, y, tile);
		hit.start();
	}

	public TrapTile(int x, int y, Image pic) {
		super(x, y, pic);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {// TODO Auto-generated method stub
		//System.out.println(this.getY());

		if (this.getX() >= 42 && this.getX() <= 54) {
			if (this.getY() == 40) {
				// if(Grid.g.player.getY())
				//System.out.println(true);

				Grid.g.player.doDamage(10);
			}
		}
	}

}
