package dkeep.logic;

public class MapEntity { // Key, Levers, etc..
	
	// Variable for entity position and nextPosition
	
	protected int xPos; // Coordinates of the Entity's position
	protected int yPos;
	protected char mapSymbol; // Representation on the map layout (Command Line Interface)
	
	public MapEntity(int x, int y, char symbol) {
		xPos = x;
		yPos = y;
		mapSymbol = symbol;
	}
}
