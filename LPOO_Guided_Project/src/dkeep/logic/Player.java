package dkeep.logic;

public class Player extends MapEntity implements Movable {

	public Player() {
		this(0, 0);
	}
	public Player(int x, int y) {
		super(x, y, 'P');
	}
	
	public void updatePosition(int x, int y) {
		xPos = x;
		yPos = y;
	}
	
	public void move(Movable.Direction dir) {
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
