package dkeep.logic;

import java.util.ArrayList;

public class Map {
	protected char[][] blueprint; 	// static layout of map itself (Walls and doors and stuff)
	protected char[][] tacticalMap; // layout of map with the patrol layout, and other related stuff
	
	protected Player hero;
	protected ArrayList<GameEntity> enemyList;
	protected ArrayList<MapEntity>  propList;
	
	
	public Map() {
		enemyList = new ArrayList<GameEntity>();
		propList = new ArrayList<MapEntity>();
	}
	
	public String toString() {
		// make copy of blueprint
		String str = "";
		char[][] map = new char[blueprint.length][];
		
		for (int i = 0;i < blueprint.length; i++) {
			map[i] = blueprint[i].clone();
		}

		// TODO Add entities to map matrix
		
		// Assemble string from matrix
		for(char[] line: map) {
			for(char symbol: line) {
				str += symbol + " "; 
			}
			str += "\n";
		}

		return str;
	}

	
	/*
	 * FUNCTION: validTile - checks if a give tile at the current map is valid for movement (X, Y coordinates)
	 * Returns true if valid, false otherwise.
	 */
	
	public boolean validTile(int x, int y) {
		switch(blueprint[x][y]) {
		case ' ':
			return true;
		default:
			return false;
		}
	}
}