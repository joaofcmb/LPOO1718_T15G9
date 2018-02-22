package dkeep.logic;

public class Guard extends MapEntity implements Movable {

	public Guard(int x, int y, char symbol) {
		super(x, y, symbol);
	}
	
	public void updatePosition(Movable.Direction dir) {
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
