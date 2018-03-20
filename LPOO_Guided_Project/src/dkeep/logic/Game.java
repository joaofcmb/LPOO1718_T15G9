package dkeep.logic;

public class Game {
	public enum GameState {DEFAULT, NEXT_LEVEL, GAME_OVER, VICTORY}
	public enum Direction {UP, LEFT, DOWN, RIGHT}
	private GameState state = GameState.DEFAULT;
	
	private Map map;
	
	public Game(Map map) {
		this.map = map;
	}
	
	public Game() {
		this(new PrisonLevel());
	}
	
	private void nextLevel() {
		//if (map instanceof PrisonLevel)
			//map = new CrazyOgreLevel();
		//else if (map instanceof CrazyOgreLevel)
			//state = GameState.VICTORY;
	}
	
	
	/*
	 * Functions interacting with CLI (notOver, update and toString)
	 * 
	 * gameLost - returns true if game has ended (Game Over), otherwise returns false
	 * gameWon - returns true if game has ended (Victory), otherwise returns false
	 * 
	 * update - updates the game to the next turn, considering the player input (playerDirection)
	 * 
	 * toString - returns a string with a ASCII art representation of the game
	 */
	
	public boolean gameLost() {
		return (state == GameState.GAME_OVER);
	}
	
	public boolean gameWon() {
		return (state == GameState.VICTORY);
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

	public GameState getState() {
		// TODO Auto-generated method stub
		return state;
	}
}
