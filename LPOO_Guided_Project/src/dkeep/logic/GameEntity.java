package dkeep.logic;

public class GameEntity extends MapEntity {
	protected int nextXPos;
	protected int nextYPos;
	
	public GameEntity(int x, int y, char symbol) {
		super(x, y, symbol);
		nextXPos = x;
		nextYPos = y;
	}
	
	
	public void nextPosition(Game.Direction dir) {
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
	
	public void updatePosition() {
		Map.updateTile(xPos, yPos, nextYPos, nextYPos);
		xPos = nextXPos;
		yPos = nextYPos;
	}
	
	
	public int getNextX() {
		return nextXPos;
	}
	public int getNextY() {
		return nextXPos;
	}
}
