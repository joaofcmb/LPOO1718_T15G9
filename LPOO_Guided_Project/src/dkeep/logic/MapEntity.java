package dkeep.logic;

public class MapEntity { // Key, Levers, etc..
	protected int xPos;
	protected int yPos;

	protected char mapSymbol; // Representation on the map layout (Command Line Interface)
	
	public MapEntity(int x, int y, char symbol) {
		xPos = x;
		yPos = y;
		mapSymbol = symbol;
	}

	protected int getX() {
		return xPos;
	}

	protected int getY() {
		return yPos;
	}
	
	protected char getSymbol() {
		return mapSymbol;
	}
}
