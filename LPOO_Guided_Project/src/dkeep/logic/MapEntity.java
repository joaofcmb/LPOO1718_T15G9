package dkeep.logic;

public abstract class MapEntity { // Key, Levers, etc..
	public enum Direction {UP, LEFT, DOWN, RIGHT}
	// Variable for entity position and nextPosition
	
	protected int xPos; // Coordinates of the Entity's position
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
	
	
	public int getX() { return this.xPos;}
	
	public int getY() { return this.yPos;}
	
	public char getSymbol() { return this.mapSymbol;}
	
	public void setCoordenateX(int x) { this.xPos=x;}
	
	public void setCoordenateY(int y) { this.yPos=y;}
}
