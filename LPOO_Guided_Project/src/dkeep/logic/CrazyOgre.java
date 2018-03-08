package dkeep.logic;

import java.util.Random;

public class CrazyOgre extends GameEntity {
	public GameEntity club;
	
	private Random random = new Random();

	public CrazyOgre(int x, int y) {
		super(x, y, 'O');
		
		club = new GameEntity(x, y, '*');
	}
	
	public void nextOgrePos() {
		nextPosition(randomDirection());
	}
	
	public void nextClubPos() {
		club.xPos = xPos;
		club.yPos = yPos;
		
		club.nextPosition(randomDirection());
	}
	
	private Game.Direction randomDirection() {
		switch(random.nextInt(4)) {
		case 0:
			return Game.Direction.UP; 
		case 1:
			return Game.Direction.LEFT;
		case 2:
			return Game.Direction.DOWN;
		case 3:
			return Game.Direction.RIGHT;
		default:
			return null;
		}
	}
}