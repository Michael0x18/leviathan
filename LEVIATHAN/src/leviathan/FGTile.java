//Timothy
// A background tile.
// literally does nothing.
package leviathan;

import java.awt.Image;

public abstract class FGTile extends Tile {

	public FGTile(int x, int y) {
		super(x, y);
	}

	public FGTile(int x, int y, Image pic) {
		super(x, y, pic);
	}

}
