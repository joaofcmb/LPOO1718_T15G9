package dkeep.logic;

public class MapEntity { // Key, Levers, etc..
	protected int xPos;
	protected int yPos;

	protected char mapSymbol; // Representation on the map layout (Command Line Interface)
	
	public MapEntity()
	{
		xPos = -1;
		yPos = -1;
		mapSymbol = '\0';
	}
	
	public MapEntity(int x, int y, char symbol) {
		xPos = x;
		yPos = y;
		mapSymbol = symbol;
	}

	public int getX() {
		return xPos;
	}

	public int getY() {
		return yPos;
	}
	
	
}
