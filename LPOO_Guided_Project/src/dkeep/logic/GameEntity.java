package dkeep.logic;

public class GameEntity extends MapEntity {
	protected int nextXPos;
	protected int nextYPos;
	
	public GameEntity(int x, int y, char symbol) {
		super(x, y, symbol);
		nextXPos = x;
		nextYPos = y;
	}
	
	
	/*
	 * FUNCTION - move -	this family is functions is used to process an entity's movement, 
	 * 						whose movement logic is implemented in nextPosition();
	 */
	
	public int move(Game.Direction dir) {
		nextXPos = xPos;
		nextYPos = yPos;
		
		// Calculate next position to which the entity will move
		nextPosition(dir);
		
		// Interrogate Map if the movement is valid
		if (Map.validTile(nextXPos, nextYPos)) {
			xPos = nextXPos;
			yPos = nextYPos;
			return 0;
		}
		else
			return -1;
	}
	
	private void nextPosition(Game.Direction dir) {
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
}
