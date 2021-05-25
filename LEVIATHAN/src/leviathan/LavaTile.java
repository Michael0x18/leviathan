package leviathan;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class LavaTile extends FluidTile implements ActionListener{
	
	public static final Image lava = new ImageIcon("Build/Lava2.gif").getImage();
	private Timer t = new Timer(100,this);
	private boolean hasPlayer = false;

	public LavaTile(int x, int y, Image pic) {
		super(x, y, pic);
		t.start();
		// TODO Auto-generated constructor stub
	}
	
	public LavaTile(int x, int y) {
		super(x,y,lava);
		t.start();
	}

	@Override
	public Tile c() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getType() {
		return "lavatile";
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (this.coords.get()[0] >= 44 && this.coords.get()[0] <= 52) {
			//System.out.println(this.getY());
			if (this.coords.get()[1] > 24 && this.coords.get()[1] <= 34) {
				//Grid.g.shiftUp(1);
				this.hasPlayer  = true;
				//System.out.println(this.hasPlayer);
				if(Math.random() > 0.0) {
					Grid.player.doDamage(10);
				}
			}
			else {
				this.hasPlayer = false;
			}
			

		}
		else {
			this.hasPlayer = false;
		}
		
	}
	
	public boolean hasPlayer() {
		return this.hasPlayer;
	}

}
