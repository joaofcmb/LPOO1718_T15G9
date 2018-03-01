package dkeep.logic;

import java.util.Random;

public abstract class Enemy extends MapEntity {
	protected Direction direction = Direction.LEFT;
	protected Random random = new Random();

	public Enemy() { super();}
	
	public Enemy(int x, int y, char symbol) {
		super(x, y, symbol);
	}
	
	public abstract void move();

}
