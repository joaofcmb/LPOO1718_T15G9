package dkeep.logic;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class Patrol {
	private List<Integer[]> nodes;
	private List<Game.Direction> direction;
	
	
	Patrol(Integer[][] nodes, Game.Direction[] dir) {
		// Add the nodes and dir to the object
		this.nodes = new ArrayList<Integer[]>();
		for (Integer[] node : nodes) {
			this.nodes.add(node);
		}
		
		direction = new ArrayList<Game.Direction>(Arrays.asList(dir));
	}
	
	public Game.Direction nodeDirection(int x, int y) {
		for(Integer[] node: nodes) {
			if (node[0] == x && node[1] == y) {
				// Found a node, return new direction
				return direction.get(nodes.indexOf(node));
			}
		}
		
		// no node found, return no direction
		return Game.Direction.NONE;
	}
	
}
