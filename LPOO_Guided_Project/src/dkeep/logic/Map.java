package dkeep.logic;

import java.util.ArrayList;

public class Map {
	protected char[][] blueprint; 	// static layout of map itself (Walls and doors and stuff)
	protected char[][] tacticalMap; // layout of map with the patrol layout, and other related stuff
	
	protected Player hero; 
	protected ArrayList<GameEntity> enemyList;
	protected ArrayList<MapEntity>  propList;
	
	
	/*
	 * FUNCTION: validTile - checks if a give tile at the current map is valid for movement (X, Y coordinates)
	 * Returns true if valid, false otherwise.
	 */
	
	private boolean validTile(int x, int y) {
		switch(blueprint[x][y]) {
		case ' ':
			return true;
		default:
			return false;
		}
	}
	
	protected int playerMove(Player hero, Game.Direction direction) {
		hero.nextPosition(direction); // calculate next Position
		
		if (validTile(hero.getNextX(), hero.getNextY())) {
			hero.move();
			return 0;
		}
		return -1;
	}
	
	
	protected int guardMove(Guard guard) {
		// check for patrol tile to see in which direction to go
		switch(tacticalMap[guard.getX()][guard.getY()]) {
		case 'u':
			guard.move(Game.Direction.UP);
			break;
		case 'l':
			guard.move(Game.Direction.LEFT);
			break;
		case 'd':
			guard.move(Game.Direction.DOWN);
			break;
		case 'r':
			guard.move(Game.Direction.RIGHT);
			break;
		default:
			return -1;
		}
		return 0;
	}
	
	public Map() {
		enemyList = new ArrayList<GameEntity>();
		propList = new ArrayList<MapEntity>();
	}
	
	public int update() {
		for(GameEntity enemy : enemyList) {
			if (enemy instanceof Guard)
				guardMove((Guard) enemy);
		}
		
		return 0;
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
}