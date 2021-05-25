package leviathan;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class WaterTile extends FluidTile implements ActionListener{
	
	Timer t = new Timer(10,this);
	private boolean hasPlayer = false;
	
	public WaterTile(int x, int y) {
		super(x,y,new ImageIcon("Build/Water2.gif").getImage());
		t.start();
	}

	public WaterTile(int x, int y, Image pic) {
		super(x, y, pic);
		t.start();
		// TODO Auto-generated constructor stub
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
	
	public boolean hasPlayer() {
		return this.hasPlayer;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (this.coords.get()[0] >= 44 && this.coords.get()[0] <= 52) {
			//System.out.println(this.getY());
			if (this.coords.get()[1] > 24 && this.coords.get()[1] <= 34) {
				//Grid.g.shiftUp(1);
				this.hasPlayer  = true;
				//System.out.println(this.hasPlayer);
			}
			else {
				this.hasPlayer = false;
			}
			

		}
		else {
			this.hasPlayer = false;
		}
		
	}
	
	public String getType() {
		return "WaterTile";
	}

}
