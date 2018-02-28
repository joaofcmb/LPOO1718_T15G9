package dkeep.logic;

public class GameEntity extends MapEntity {
	protected int nextXPos;
	protected int nextYPos;
	
	public GameEntity(int x, int y, char symbol) {
		super(x, y, symbol);
		nextXPos = x;
		nextYPos = y;
		Map.setTile(x, y, symbol);
	}
	
	/*
	 * FUNCTION - move -	this family is functions is used to process an entity's movement, 
	 * 						whose movement logic is implemented in nextPosition();
	 */
	public void move(Game.Direction dir) {
		nextXPos = xPos;
		nextYPos = yPos;
		
		// Calculate next position to which the entity will move
		nextPosition(dir);
		
		// Interrogate Map if the movement is valid
		if (Map.validTile(nextXPos, nextYPos)) {
			Map.updateTile(xPos, yPos, nextXPos, nextYPos);
			xPos = nextXPos;
			yPos = nextYPos;
		}
	}
	
	private void nextPosition(Game.Direction dir) {
		switch(dir) {
		case UP: 
			nextYPos--;
			break;
		case LEFT:
			nextXPos--;
			break;
		case DOWN:
			nextYPos++;
			break;
		case RIGHT:
			nextXPos++;
			break;
		default:
			break;
		}
	}
}
