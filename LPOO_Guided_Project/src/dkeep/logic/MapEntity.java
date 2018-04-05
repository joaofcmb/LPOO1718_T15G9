package dkeep.logic;

import java.io.Serializable;

public class MapEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	protected int xPos;
	protected int yPos;

	protected char mapSymbol; // Representation on the map layout (Command Line Interface)
	
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
	
	public char getSymbol() {
		return mapSymbol;
	}
}
