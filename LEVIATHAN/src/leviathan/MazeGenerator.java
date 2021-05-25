package leviathan;

import java.util.Collections;
import java.util.Arrays;

//Generates a maze. Algorithm by Michael Ferolito. Code by Michael Ferolito.

public class MazeGenerator {
	public static String mazeRet = "";

	private final int x;
	private final int y;
	private final int[][] maze;

	public MazeGenerator(int x, int y) {
		this.x = x;
		this.y = y;
		maze = new int[this.x][this.y];
		generateMaze(0, 0);
	}

	public void display() {
		mazeRet = "";
		for (int i = 0; i < y; i++) {
			// draw the north edge
			for (int j = 0; j < x; j++) {
				mazeRet += ((maze[j][i] & 1) == 0 ? "+-" : "+X");
			}
			mazeRet += ("+_");
			// draw the west edge
			for (int j = 0; j < x; j++) {
				mazeRet += ((maze[j][i] & 8) == 0 ? "|~" : "X~");
			}
			mazeRet += ("|_");
		}
		// draw the bottom line
		for (int j = 0; j < x; j++) {
			mazeRet += ("+-");
		}
		mazeRet += ("+");
	}

	private void generateMaze(int cx, int cy) {
		DIR[] dirs = DIR.values();
		Collections.shuffle(Arrays.asList(dirs));// rd
		for (DIR dir : dirs) {
			int nx = cx + dir.dx;
			int ny = cy + dir.dy;
			if (btw(nx, x) && btw(ny, y) && (maze[nx][ny] == 0)) {
				maze[cx][cy] |= dir.bit;
				maze[nx][ny] |= dir.opposite.bit;
				generateMaze(nx, ny);// create
			}
		}
	}

	private static boolean btw(int v, int upper) {
		return (v >= 0) && (v < upper);
	}

	private enum DIR {
		N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);
		private final int bit;
		private final int dx;
		private final int dy;
		private DIR opposite;

		// use the static initializer to resolve forward references
		static {
			N.opposite = S;
			S.opposite = N;
			E.opposite = W;
			W.opposite = E;
		}

		private DIR(int bit, int dx, int dy) {
			this.bit = bit;
			this.dx = dx;
			this.dy = dy;
		}
	};

//	public static void main(String[] args) {
//		int x = args.length >= 1 ? (Integer.parseInt(args[0])) : 8;
//		int y = args.length == 2 ? (Integer.parseInt(args[1])) : 8;
//		MazeGenerator maze = new MazeGenerator(16, 16);
//		maze.display();
//		System.out.println(mazeRet);
//	}

}
