package leviathan;

// Michael Ferolito
// Generates a humongous string that contains a maze.
// Coupled to MazeGenerator and Interpreter.

import java.util.LinkedList;

public class HyperRoom {
	private static int side = Grid.side;
	
	
	public static String generate() {
		HyperRoom.side = Grid.side;
		String hyper = "";
		LinkedList<String> h = new LinkedList<String>();
		for (int i = 0; i < 256; i++) {
			h.add("");
		}
		
		
		for (int i = 0; i < side; i ++) {
			for (int j = 0; j < side; j++) {
				String[] s1 = Room.getTemplate().split(" ");
				if (i == side-1 && j == side-1) {
					s1 = (""+Room.end).split(" ");
				}
				else if (i == 0 && j == 0) {
					s1 = (""+Room.startRoom).split(" ");
				}
				for (int k = 0; k < 16; k++) {
					h.set(i*16+k, h.get(i*16+k)+s1[k]);
				}
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		for (String e : h) {
			hyper += e;
		}
		
		
		
		
		return hyper;
	}

}
