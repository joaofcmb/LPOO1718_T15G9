package dkeep.logic;

public class GameEntity extends MapEntity {
	protected int nextXPos;
	protected int nextYPos;
	
	protected GameEntity(int x, int y, char symbol) {
		super(x, y, symbol);
		nextXPos = x;
		nextYPos = y;
	}
	
	
	/*
	 * FUNCTION - move -	this family is functions is used to process an entity's movement, 
	 * 						whose movement logic is implemented in nextPosition();
	 */
	
	protected void move() {	
		xPos = nextXPos;
		yPos = nextYPos;
	}

	protected void nextPosition(Game.Direction dir) {
		nextXPos = xPos;
		nextYPos = yPos;
		
		if (dir == null) 	return;
		
		switch(dir) {
		case UP: 
			nextXPos--;
			break;
		case LEFT:
			nextYPos--;
			break;
		case DOWN:
			nextXPos++;
			break;
		case RIGHT:
			nextYPos++;
			break;
		default:
			break;
		}
	}
	
	/*
	 * Checks for triggers with others entities and changes these accordingly (or none if it's merely a boolean trigger)
	 */
	
	protected int getNextX() {
		return nextXPos;
	}
	
	protected int getNextY() {
		return nextYPos;
	}
}
