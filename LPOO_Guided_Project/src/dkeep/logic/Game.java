package dkeep.logic;

public class Game {
	public enum GameState {DEFAULT, NEXT_LEVEL, GAME_OVER, VICTORY}
	public enum Direction {UP, LEFT, DOWN, RIGHT}
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
			map = new CrazyOgreLevel();
			break;
		}
	}
	
	private void nextLevel() {
		if (map instanceof PrisonLevel)
			changeLevel(1);
		else if (map instanceof CrazyOgreLevel)
			state = GameState.VICTORY;
	}
	
	
	
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
		if (heroDirection == null)
			return -1;
		
		// tell map to process its entities
		state = map.update(heroDirection);
		
		if (state == GameState.NEXT_LEVEL)
			nextLevel();
		
		return 0;
	}

	public String toString() {
		return map.toString();
	}
}
