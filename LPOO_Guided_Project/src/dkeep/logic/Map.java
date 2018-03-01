package dkeep.logic;

import java.util.ArrayList;

public class Map {
	protected char[][] blueprint; // static layout of map itself
	
	protected Player hero;
	protected ArrayList<GameEntity> enemyList = new ArrayList<GameEntity>();
	protected ArrayList<MapEntity>  propList =  new ArrayList<MapEntity>();
	
	
	public Map() {
		enemyList.clear();
		propList.clear();
	}
	
	public String toString() {
		String str = "";
		char[][] map = new char[blueprint.length][];
		
		for (int i = 0;i < blueprint.length; i++) {
			map[i] = blueprint[i].clone();
		}

		// TODO Add entities to map matrix
		
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