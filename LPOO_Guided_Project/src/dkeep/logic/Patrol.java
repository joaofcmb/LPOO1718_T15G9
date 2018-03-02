package dkeep.logic;

import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Patrol {
	private List<Integer[]> nodes; // (x, y)
	private List<Game.Direction> direction;
	
	
	/*
	 * Patrol syntax: "x, y, Dir,..." // each 3 elems is a node, must be ordered (to infer the suspicion direction too)
	 * Spacing of elements must be exactly ", ".  Dir = U, L, D, R (initials of direction)
	 */
	
	Patrol(String config) {
		// Add the nodes and dir to the object
		Scanner patrolConfig = new Scanner(config);
		
		patrolConfig.useDelimiter(", ");
		
		while (patrolConfig.hasNextInt()) {
			int x = patrolConfig.nextInt();
			int y = patrolConfig.nextInt();
			Game.Direction direction = directionConfig(patrolConfig.next());
			
			// add to ArrayLists
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
	
	public Game.Direction nodeDirection(int x, int y) {
		for(Integer[] node: nodes) {
			if (node[0] == x && node[1] == y) {
				// Found a node, return new direction
				return directions.get(nodes.indexOf(node));
			}
		}
		
		// no node found, return no direction
		return Game.Direction.NONE;
	}
	
}
