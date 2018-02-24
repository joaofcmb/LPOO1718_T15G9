package dkeep.logic;

public class Game {
	public enum GameState {DEFAULT, GAME_OVER, VICTORY}
	
	private int currentMapIndex;
	private MapEntity[] gameEntity; // Must be movable (0 = Player)
	
	private GameState state = GameState.DEFAULT; 
	
	public Game() {
		gameEntity[0] = new Player();
		changeLevel(0);
	}
	public Game(int level) {
		
	}
	
	private void changeLevel(int index) {
		currentMapIndex = index;
		changeMap(index);
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
