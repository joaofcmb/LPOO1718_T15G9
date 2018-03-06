package dkeep.logic;

public class Game {
	public enum GameState {DEFAULT, GAME_OVER, VICTORY}
	public enum Direction {NONE, UP, LEFT, DOWN, RIGHT}
	private GameState state = GameState.DEFAULT;
	
	// private ArrayList<Door> doors =  new ArrayList<Door>();
	private Map map;
	
	
	public Game() {
		changeLevel(0);
	}
	
	private void changeLevel(int index) {
		switch(index) {
		case 0:
			map = new PrisonLevel();
			break;
		case 1:
			// Call Map Constructor
			break;
		}
	}
	
	// TODO Add function to go to next level (using changeLevel)
	
	
	
	/*
	 * Functions interacting with CLI (notOver, update and toString)
	 * 
	 * notOver - returns true if game has ended, otherwise returns false
	 * 
	 * update - updates the game to the next turn, considering the player input (playerDirection)
	 * 
	 * toString - returns a string with a ASCII art representation of the game
	 */
	
	public boolean notOver() {
		return (state != GameState.GAME_OVER && state != GameState.VICTORY);
	}
	
	public int update(Direction heroDirection) {
		if (heroDirection == Direction.NONE)
			return -1;
		
		// tell map to process its entities
		if (map.update(heroDirection) == -1)
			state = GameState.GAME_OVER;
		
		return 0;
	}
	
	public String toString() {
		return map.toString();
	}
}
