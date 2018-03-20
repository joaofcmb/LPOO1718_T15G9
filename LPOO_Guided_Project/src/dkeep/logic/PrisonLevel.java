package dkeep.logic;

import dkeep.logic.Game.Direction;
import dkeep.logic.Game.GameState;

import java.util.LinkedList;

public class PrisonLevel extends Map {
	/*
	 * Type of map that supports:
	 * 
	 * Multiple doors triggered by a lever "k" (if multiple levers present, anyone will open all the doors. 
	 * Doors cannot be closed after being opened.)
	 * 
	 * Multiple guards each with their own Patrol (the automatic generation from blueprint constructor 
	 * will assign no patrol and make guards static).
	 * 
	 */
	
	boolean isUnlocked = false;
	LinkedList<Door> doorList = new LinkedList<Door>();
	LinkedList<Guard> guardList = new LinkedList<Guard>();
	
	// Default Constructors
	public PrisonLevel() {
		this(Guard.Personality.ROOKIE);
	}
	public PrisonLevel(Guard.Personality personality) {
		blueprint =  new char[][] {
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X',' ',' ',' ','I',' ','X',' ',' ','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'X',' ','I',' ','I',' ','X',' ',' ','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X',' ','X','X','X','X',' ','X'},
			{'X',' ','I',' ','I',' ','X','k',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}
		};
		
		hero = new Player(1, 1);
		
		doorList.add(new Door(5, 0));
		doorList.add(new Door(6, 0));
		
		guardList.add(new Guard(1, 8, personality, "1, 8, l, " + "1, 7, d, "
										 		 + "5, 7, l, " + "5, 1, d, "
										 		 + "6, 1, r, " + "6, 8, u"));
	}
	
	/* 
	 * Constructor for testing purposes (other maps with same logic)
	 * 
	 * Uses the given matrix to determine entities in map
	 * 
	 * Player(P): If more than one, last one iterated (up to down, left to right) will be the added one
	 * 
	 * Guard(G):  No patrols nor movement (remains static)
	 * 
	 * Door(I):   Normal behavior
	 */
	public PrisonLevel(char[][] blueprint) {
		// iterate blueprint to look for entities (doors, guards and player) and add them
		for (int i = 0; i < blueprint.length; i++) {
			for (int j = 0; j < blueprint[i].length; j++) {
				switch(blueprint[i][j]) {
				case 'P':
					hero = new Player(i, j);
					blueprint[i][j] = ' ';
					break;
				case 'I':
					doorList.add(new Door(i, j));
					break;
				case 'G':
					guardList.add(new Guard(i, j, Guard.Personality.STATIC, ""));
					blueprint[i][j] = ' ';
					break;
				}
			}
		}
		
		this.blueprint = blueprint;
	}

	
	private boolean playerMove(Game.Direction direction) {
		hero.nextPosition(direction); // calculate next Position
		
		// check where next position goes to and act accordingly
		switch(blueprint[hero.getNextX()][hero.getNextY()]) {
		case 'S':
			state = Game.GameState.NEXT_LEVEL;
		case ' ':
			hero.move();
			return true;
		case 'k': // Lever that unlocks door
			isUnlocked = true;
			for (Door door : doorList) {
				door.unlock();
				blueprint[door.getX()][door.getY()] = 'S';
			}
			return true;
		default:
			return false;
		}
	}

	public GameState update(Direction heroDirection) {
		if (!playerMove(heroDirection)) // cancel turn if player movement isn't to a valid position
			return state;
		
		// move enemies and check hero triggers with them
		for(Guard guard : guardList) {
			guard.move();
			
			if (hero.entityTrigger(guard))
				return Game.GameState.GAME_OVER; // enemy trigger with hero signifies hero death (game over)
		}
		
		return state;
	}

	// TODO Make class called Layout or something to encapsulate the operations to add entities to matrixes and avoid repeating code
	public String toString() {
		// make copy of blueprint
		char[][] map = new char[blueprint.length][];

		for (int i = 0;i < blueprint.length; i++) {
			map[i] = blueprint[i].clone();
		}


		// Add entities to map matrix
		map[hero.getX()][hero.getY()] = hero.getSymbol();

		for (Guard guard : guardList) {
			map[guard.getX()][guard.getY()] = guard.getSymbol();
		}

		
		// Assemble string from matrix
		String str = "";
		
		for(char[] line: map) {
			for(char symbol: line) {
				str += symbol + " "; 
			}
			str += "\n";
		}
		
		return str;
	}
	
	public boolean isUnlocked() {
		return isUnlocked;
	}
}
