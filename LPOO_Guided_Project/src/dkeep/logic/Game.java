package dkeep.logic;

import java.util.List;
import java.util.LinkedList;

public class Game {
	public enum GameState {DEFAULT, GAME_OVER, VICTORY}
	public enum Direction {NONE, UP, LEFT, DOWN, RIGHT}
	
	
	private GameEntity player;
	private List<MapEntity> entityList = new LinkedList<MapEntity>(); // Must be movable
	
	private GameState state = GameState.DEFAULT;
	
	
	public Game() {
		changeLevel(0);
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
	
	public int update(Direction playerDirection) {
		if (playerDirection == Direction.NONE)
			return -1;
		
		// move player in input direction
		player.move(playerDirection);
		
		return 0;
	}

	public String toString() {
		return Map.layoutString();
	}

	
	
	private void changeLevel(int index) {
		// Change Map Layout
		Map.changeMap(index);
		
		// Setup Entities
		entityList.clear();
		
		switch(index) {
		case 0:
			player = new Player(1, 1);
			// entityList.add(new Guard(1, 7, 'G'));
		}
		
	}
}
