package dkeep.logic;

import java.io.Serializable;

public class Game implements Serializable {
	private static final long serialVersionUID = 1L;

	public enum Personality {ROOKIE, DRUNK, SUSPICIOUS, STATIC}
	public enum GameState {DEFAULT, NEXT_LEVEL, GAME_OVER, VICTORY}
	public enum Direction {UP, LEFT, DOWN, RIGHT}
	
	private GameState state = GameState.DEFAULT;
	
	private Map map;
	
	private int nOgres = 1;
	
	public Game(Map map) {
		this.map = map;
	}
	
	public Game() {
		this(new PrisonLevel());
	}
	
	public Game(int nOgres, Personality type)
	{
		this.nOgres = nOgres;
		this.map = new PrisonLevel(type);
	}
	
	private void nextLevel() {
		if (map instanceof PrisonLevel)
			map = new CrazyOgreLevel(nOgres);
		else if (map instanceof CrazyOgreLevel)
			state = GameState.VICTORY;
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

	public GameState getState() {return state;}
	
	public Map getMap() {return map;}
}
