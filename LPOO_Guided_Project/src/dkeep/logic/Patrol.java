package dkeep.logic;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Patrol {
	private HashMap<Integer[],  Game.Direction> nodes;
	
	/*
	 * Patrol syntax: "x, y, Dir,..." // each 3 elems is a node, must be ordered (to infer the suspicion direction too)
	 * Spacing of elements must be exactly ", ".  Dir = U, L, D, R (initials of direction)
	 */
	
	Patrol(String config) {
		// Add the nodes and dir to the object
		Scanner patrolConfig = new Scanner(config);
		patrolConfig.useDelimiter(", ");
		
		Integer[] pos = new Integer[2];
		nodes = new LinkedHashMap<Integer[], Game.Direction>();
		
		// Iterate each node and add it to the Map
		while (patrolConfig.hasNextInt()) {
			int x = patrolConfig.nextInt();
			int y = patrolConfig.nextInt();
			pos[0] = x; 	pos[1] = y;
			
			Game.Direction direction = directionConfig(patrolConfig.next());
		
			nodes.put(pos, direction);
		}
		
		patrolConfig.close();
	}
	
	private Game.Direction directionConfig(String s) {
		switch(s.charAt(0)) {
		case 'U':
			return Game.Direction.UP;
		case 'L':
			return Game.Direction.LEFT;
		case 'D':
			return Game.Direction.DOWN;
		case 'R':
			return Game.Direction.RIGHT;
		default:
			return Game.Direction.NONE;	
		}
	}
	
	public Game.Direction nodeDirection(int x, int y, boolean guardIsSuspicious) {
		Integer[] pos = new Integer[2];
		pos[0] = x;
		pos[1] = y;
		
		if (nodes.containsKey(pos)) {
			if (guardIsSuspicious)
				return nodes.get(pos);
			else {
				// use API to get a Set of positions to get to the node before this one 
				// which contains the reverse direction for a suspicious guard
			}
		}
			
		
		// no node found, return no direction
		return Game.Direction.NONE;
	}
}
