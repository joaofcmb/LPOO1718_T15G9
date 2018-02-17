
/*
 *  CLASS - Position
 *  
 *  Class responsible for position arithmetics used in the movement of entities
 *  and the characterization of certain triggers and enemy behaviors.
 *
 *	String Sintax: "(xPos, yPos)"
 */

public class Position {
	private int xPos;
	private int yPos;
	
	public Position(int x, int y) {
		this.xPos = x;
		this.yPos = y;

	}	
	public Position(Position pos) {
		this.xPos = pos.xPos;
		this.yPos = pos.yPos;
	}

	
	public String toString() {
		return "(" + xPos + ", " + yPos + ")";
	}
	
	/*
	 * Vector Arithmetics
	 */

	// -(x, y)
	public Position negative() {
		return new Position(-xPos, -yPos);
	}
	
	// (abs(x), abs(y))
	public static Position abs(Position p) {
		return new Position(Math.abs(p.xPos), Math.abs(p.yPos));
	}

	// (x, y) + (a, b)
	public Position add(Position pos) {
		return new Position(this.xPos + pos.xPos,  this.yPos + pos.yPos);
	}
	
	// (x, y) - (a, b)
	public Position sub(Position pos) {
		return add(pos.negative());
	}

	
	/*
	 * Grid Operations
	 */
	
	// Adjacency Trigger (non-diagonal adjacency)
	public boolean isAdjacent(Position pos) {
		return (abs(this.sub(pos)).size() <= 1);
	}
	
	
	private int size() { // Useful for evaluating distances
		return xPos + yPos;
	}
}
