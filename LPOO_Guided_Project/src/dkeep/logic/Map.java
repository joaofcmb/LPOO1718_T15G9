package dkeep.logic;

import java.util.ArrayList;


public class Map {
	protected char[][] blueprint; 	// static layout of map itself (Walls and doors and stuff)
	
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
	
	protected boolean playerMove(Game.Direction direction) {
		hero.nextPosition(direction); // calculate next Position
		
		if (validTile(hero.getNextX(), hero.getNextY())) {
			hero.move();
			return true;
		}
		return false;
	}
	
	
	public Map() {
		enemyList = new ArrayList<GameEntity>();
		propList = new ArrayList<MapEntity>();
	}
	
	public Game.GameState update(Game.Direction heroDirection) {
		if (!playerMove(heroDirection))
			return Game.GameState.DEFAULT;
		
		for(GameEntity enemy : enemyList) {
			enemy.move();
			
			if (hero.entityTrigger(enemy))
				return Game.GameState.GAME_OVER; // enemy trigger with hero signifies hero death (game over)
		}
		return Game.GameState.DEFAULT;
	}
	
	public String toString() {
		// make copy of blueprint
		String str = "";
		char[][] map = new char[blueprint.length][];
		
		for (int i = 0;i < blueprint.length; i++) {
			map[i] = blueprint[i].clone();
		}

		// Add entities to map matrix
		map[hero.getX()][hero.getY()] = hero.getSymbol();
		
		for (GameEntity enemy : enemyList) {
			map[enemy.getX()][enemy.getY()] = enemy.getSymbol();
		}
		for (MapEntity prop : propList) {
			map[prop.getX()][prop.getY()] = prop.getSymbol();
		}
		
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