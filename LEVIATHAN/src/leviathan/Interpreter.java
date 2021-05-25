package leviathan;

import leviathan.Grid;

public class Interpreter {
	// A class to turn the Human-recognizable output made by Joshua's Maze Generator into a usable form.
	public static String[] getMaze() {//get the maze
		MazeGenerator maze = new MazeGenerator(Grid.side, Grid.side);
		maze.display();
		String[] strList = MazeGenerator.mazeRet.split("_");
		for (String s : strList) {
			System.out.println(s);
		}
		//returns a list that contains a stream of characters. The characters represent a maze.
		return strList;
		
	}



}
