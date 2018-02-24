package dkeep.logic;

import java.util.List;
import java.util.ArrayList;

public class Game {
	public enum GameState {DEFAULT, GAME_OVER, VICTORY}
	public enum Direction {NONE, UP, LEFT, DOWN, RIGHT}
	
	
	private GameEntity player;
	private List<MapEntity> entityList = new ArrayList<MapEntity>(); // Must be movable (0 = Player)
	
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
		
		// calculate next position
		player.nextPosition(playerDirection);
		
		// Interrogate Map if the player movement is valid
		if (Map.validTile(player.getNextX(), player.getNextY())) {
			player.updatePosition(); // Updates player position in both the class and the map layout
		}
		
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
