package leviathan;

import java.awt.Image;

public abstract class FluidTile extends FGTile{

	public FluidTile(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public FluidTile(int x, int y, Image pic) {
		super(x,y,pic);
	}
	
	public String getType() {
		return "FluidTile";
	}

}
