package dkeep.logic;

import java.io.Serializable;

import dkeep.logic.Game.GameState;

/**
 * Map is responsible for keeping track of the various entities relationship with the environment and themselves.
 * Since these properties depend on the type of level logic, Map serves as a common ground for both CrazyOgreLevel 
 * and PrisonLevel, who both inherit this class.
 */
public abstract class Map implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Game.GameState state = Game.GameState.DEFAULT;
	
	boolean isUnlocked = false;
	
	protected char[][] blueprint = { {'X','X','X'}, {'X',' ','X'}, {'X','X','X'} }; // static layout of map itself (Walls and doors and stuff)
	
	protected Player hero= new Player(1, 1); 
	
	/**
	 * /**
	 * Progresses one tick/turn of the environment and its entities
	 * 
	 * @param 	heroDirection		Direction where the hero is meant to move.
	 * @return						Returns the state the game is in after this tick/turn.
	 * @see		GameState
	 */
	public abstract Game.GameState update(Game.Direction heroDirection);
	
	/**
	 * Returns a visual representation of the current state of the map as a string, for the CLI.
	 * 
	 * @return 	A string with a visual representation of the map.
	 */
	public abstract String toString();
	
	protected char[][] copyBlueprint() {
		char[][] map = new char[blueprint.length][];

		for (int i = 0;i < blueprint.length; i++) {
			map[i] = blueprint[i].clone();
		}
		
		return map;
	}
	
	protected void addEntity(char[][] map, MapEntity entity) {
		if (entity == null)	return;
		
		map[entity.getX()][entity.getY()] = entity.getSymbol();
		if (entity instanceof CrazyOgre && ((CrazyOgre) entity).hasClub())
			map[((CrazyOgre) entity).getClubX()][((CrazyOgre) entity).getClubY()] = ((CrazyOgre) entity).getClubSymbol();
	}
	
	protected String matrixToString(char[][] map) {
		String str = "";

		for(char[] line: map) {
			for(char symbol: line) {
				str += symbol + " "; 
			}
			str += "\n";
		}

		return str;
	}
	
	/**
	 * Getter of an object of the Player entity in the map
	 * 
	 * @return  Hero in the map
	 */
	public Player getHero() {
		return hero;
	}
	
	/**
	 * Evaluates whether the doors are unlocked or not. Used for testing purposes
	 * 
	 * @return  <code> true </code> if the doors are unlocked, <code> false </code> if not.
	 */
	public boolean isUnlocked() {
		return isUnlocked;
	}
	
	/**
	 * @return 	 A matrix representing the current state of the map.
	 */
	public abstract char[][] charMap();
}