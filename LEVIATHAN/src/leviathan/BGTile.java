//Timothy Liu
// Does nothing.

package leviathan;

import java.awt.Image;

public class BGTile extends Tile {
	// A class that contains a tile that the user cannot interact with.
	// Define the draw method contained in the Tile class.
	//call super
	public BGTile(int x, int y) {
		super(x, y);
	
	}
	
	public BGTile c(){
		return new BGTile(0,0,this.getSprite());
	}
	
	public BGTile(int x, int y, Image pic) {
		super(x,y,pic);
	}

	@Override
	public void act() {
		
		
	}

	@Override
	public String toString() {

		return null;
	}

	@Override
	public String getType() {

		return "BGTile";
	}
	
	public int getX() {
		return super.getX();
	}
	
	public int getY() {
		return super.getY();
	}
	
}
