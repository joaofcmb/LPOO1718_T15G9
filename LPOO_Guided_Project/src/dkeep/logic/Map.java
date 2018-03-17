package dkeep.logic;

import java.util.ArrayList;

// TODO Maybe remove subclass (makes things messy)

public class Map {
	protected Game.GameState state = Game.GameState.DEFAULT;
	
	protected char[][] blueprint; 	// static layout of map itself (Walls and doors and stuff)
	
	protected Player hero; 
	protected ArrayList<GameEntity> enemyList;
	protected ArrayList<MapEntity>  propList;
	
	
	public Map() {
		enemyList = new ArrayList<GameEntity>();
		propList = new ArrayList<MapEntity>();
	}
	
	
	/*
	 * FUNCTION: validTile - checks if a give tile at the current map is valid for movement (X, Y coordinates)
	 * Returns true if valid, false otherwise.
	 */
	
	public boolean validTile(int x, int y) {
		switch(blueprint[x][y]) {
		case 'S':
			state = Game.GameState.NEXT_LEVEL;
		case ' ':
		case 'k':
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
	
	
	public Game.GameState update(Game.Direction heroDirection) {
		if (!playerMove(heroDirection))
			return state;
		
		// move enemies and check hero triggers with them
		for(GameEntity enemy : enemyList) {
			if (enemy instanceof Guard)
				enemy.move();
			
			if (hero.entityTrigger(enemy))
				return Game.GameState.GAME_OVER; // enemy trigger with hero signifies hero death (game over)
		}
		
		return state;
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
			if (enemy instanceof CrazyOgre)
				map[((CrazyOgre)enemy).getClubX()][((CrazyOgre)enemy).getClubY()] = ((CrazyOgre)enemy).getClubSymbol();
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
	
	public void openDoors() {
		for (MapEntity prop : propList) {
			// TODO remove redundancy of both blueprint and door
			if (prop instanceof Door)	((Door) prop).unlock();
			
			blueprint[prop.getX()][prop.getY()] = 'S';
		}
	}
	
	public Player getHero() {
		return hero;
	}
}