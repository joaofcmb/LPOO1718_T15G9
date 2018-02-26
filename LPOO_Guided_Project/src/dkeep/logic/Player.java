package dkeep.logic;

public class Player extends MapEntity /*implements Movable*/ {

	public Player()
	{
		super();
	}
	
	public Player(int x, int y, char symbol) {
		super(x, y, symbol);
	}
	
	public void updatePosition(int x, int y) {
		xPos = x;
		yPos = y;
	}
	
	public void move(MapEntity.Direction dir) {
		switch(dir) {
		case UP: 
			yPos--;
			break;
		case LEFT:
			xPos--;
			break;
		case DOWN:
			yPos++;
			break;
		case RIGHT:
			xPos++;
			break;
		}
	}
}
