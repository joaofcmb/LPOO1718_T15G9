package dkeep.logic;

public class Player extends MapEntity /*implements Movable*/ {
	private boolean pickedKey;
	public Player()
	{
		super();
		pickedKey = false;
	}

	public Player(int x, int y, char symbol) {
		super(x, y, symbol);
		pickedKey = false;
	}

	public void updatePosition(int y, int x) {
		xPos = x;
		yPos = y;
	}

	public void move(MapEntity.Direction dir) {
		switch(dir) {
		case UP: 
			xPos--;
			break;
		case LEFT:
			yPos--;
			break;
		case DOWN:
			xPos++;
			break;
		case RIGHT:
			yPos++;
			break;
		}
	}
	
	public void pickKey()
	{
		pickedKey = true;
		this.mapSymbol = 'K';
	}
	
	public boolean hasKey(){ return pickedKey;}
}
