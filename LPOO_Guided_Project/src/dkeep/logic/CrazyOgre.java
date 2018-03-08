package dkeep.logic;

import java.util.Random;

public class CrazyOgre extends GameEntity {
	public MapEntity club;
	
	private Random random = new Random();

	public CrazyOgre(int x, int y) {
		super(x, y, 'O');
		
		club = new MapEntity(x, y, '*');
	}

	
	public void nextPosition() {
		Game.Direction dir = null;
		
		switch(random.nextInt(4)) {
		case 0:
			dir = Game.Direction.UP; 
			break;
		case 1:
			dir = Game.Direction.LEFT;
			break;
		case 2:
			dir = Game.Direction.DOWN;
			break;
		case 3:
			dir = Game.Direction.RIGHT;
			break;
		}
		
		nextPosition(dir);
	}
}