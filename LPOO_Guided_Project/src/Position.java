
/*
 *  CLASS - Position
 *  
 *  Class responsible for position arithmetics used in the movement of entities
 *  and the characterization of certain triggers and enemy behaviors.
 *
 *	String Sintax: "(xPos, yPos)"
 */

public class Position {
	public enum Direction{ NONE, UP, DOWN, LEFT, RIGHT };
	
	private int xPos;
	private int yPos;
	
	public Position() {
		this.xPos = 0;
		this.yPos = 0;
	}
	public Position(Position pos) {
		this.xPos = pos.xPos;
		this.yPos = pos.yPos;
	}
	public Position(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}		
	
	public String toString() {
		return "(" + xPos + ", " + yPos + ")";
	}
	
	// moves a tile in a certain position to another place, updating with the new position in the end (assumes nothing in background)
	public void moveTile(Position destination, char entityTile, char[][] map) {
		map[this.xPos][this.yPos] = ' ';
		map[destination.xPos][destination.yPos] = entityTile;
		
		this.xPos = destination.xPos;
		this.yPos = destination.yPos;
	}
	
	// returns the tile on which the position is located, at a given map
	public char tile(char[][] map) {
		if (map.length > xPos && map[xPos].length > yPos) {
			return map[xPos][yPos];
		}
		return '\0';
	}
	
	// Updates position to move one tile in a given direction
	public Position move(Position.Direction direction) {
		switch(direction) {
		case UP:
			return this.add(new Position(-1, 0));
		case DOWN:
			return this.add(new Position(1, 0));
		case LEFT:
			return this.add(new Position(0, -1));
		case RIGHT:
			return this.add(new Position(0, 1));
		default:
			break;
		}
		return this;
	}
	
	// Adjacency Trigger (non-diagonal adjacency)
	public boolean isAdjacent(Position pos) {
		return (abs(this.sub(pos)).size() <= 1);
	}
	
	
	
	/*
	 * Vector Arithmetics
	 */

	// -(x, y)
	private Position negative() {
		return new Position(-xPos, -yPos);
	}
	
	// (abs(x), abs(y))
	private static Position abs(Position p) {
		return new Position(Math.abs(p.xPos), Math.abs(p.yPos));
	}

	// (x, y) + (a, b)
	private Position add(Position pos) {
		return new Position(this.xPos + pos.xPos,  this.yPos + pos.yPos);
	}
	
	// (x, y) - (a, b)
	private Position sub(Position pos) {
		return add(pos.negative());
	}
	
	
	private int size() { // Useful for evaluating distances
		return xPos + yPos;
	}
}
