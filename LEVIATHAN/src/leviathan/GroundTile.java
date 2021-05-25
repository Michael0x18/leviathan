package leviathan;

//Timothy Liu
// Represents tile to be stood on.

import java.awt.Image;

public class GroundTile extends Tile {

	public GroundTile(int x, int y) {
		super(x, y);

	}

	public GroundTile(int i, int j, Image pic) {
		super(i, j, pic);
	}

	public GroundTile c() {
		return new GroundTile(0, 0, this.getSprite());
	}

	@Override
	public void act() {

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "GroundTile";
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "GroundTile";
	}
}
