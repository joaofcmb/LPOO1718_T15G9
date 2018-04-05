package dkeep.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Patrol implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> nodeList = new ArrayList<String>();
	private ArrayList<Game.Direction> directionList = new ArrayList<Game.Direction>();
	
	/*
	 * Patrol syntax: "x, y, Dir,..." // each 3 elems is a node, must be ordered (to infer the suspicion direction too)
	 * Spacing of elements must be exactly ", ".  Dir = u, l, d, r (initials of direction)
	 */
	
	Patrol(String config) {
		// Add the nodes and dir to the object
		Scanner patrolConfig = new Scanner(config);
		patrolConfig.useDelimiter(", ");
		
		// Iterate each node and add it to the Map
		while (patrolConfig.hasNextInt()) {
			int x = patrolConfig.nextInt();
			int y = patrolConfig.nextInt();
			
			nodeList.add(x + "," + y);
			directionList.add(directionConfig(patrolConfig.next()));
		}
		
		patrolConfig.close();
	}
	
	private Game.Direction directionConfig(String s) {
		switch(s.charAt(0)) {
		case 'u':
			return Game.Direction.UP;
		case 'l':
			return Game.Direction.LEFT;
		case 'd':
			return Game.Direction.DOWN;
		case 'r':
			return Game.Direction.RIGHT;
		default:
			return null;	
		}
	}
	
	public Game.Direction nodeDirection(int x, int y, boolean inverseDirection) {
		int i;
		String pos = x + "," + y;
		
		if ((i = nodeList.indexOf(pos)) != -1) {
			if (!inverseDirection)
				return directionList.get(i);
			else {
				return directionList.get(i > 0 ? i - 1 : directionList.size() - 1);
			}
		}
			
		
		// no node found, return no direction
		return null;
	}
}
