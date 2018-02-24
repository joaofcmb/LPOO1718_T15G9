package dkeep.logic;

import java.util.List;
import java.util.ArrayList;

public class Game {
	public enum GameState {DEFAULT, GAME_OVER, VICTORY}
	
	private int currentMapIndex;
	
	private GameEntity player;
	private List<MapEntity> entityList = new ArrayList<MapEntity>(); // Must be movable (0 = Player)
	
	private GameState state = GameState.DEFAULT;
	
	
	public Game() {
		player = new Player();
		changeLevel(0);
	}
	
	
	private void changeLevel(int index) {
		// Change Map Layout
		currentMapIndex = index;
		Map.changeMap(index);
		
		// Setup Entities
		entityList.clear();
		
		switch(index) {
		case 0:
			player.updatePosition(1,  1);
			// entityList.add(new Guard(1, 7, 'G'));
		}
		
	}
	
	
	
	
	/*
	public String toString() {
		String str = "";
	
		for(char[] line: currentMap) {
			for(char symbol: line) {
				str += symbol + " "; 
			}
			str += "\n";
		}
		
		return str;
	}
	*/
}
