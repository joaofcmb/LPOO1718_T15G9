package dkeep.logic;

import java.util.Random;

public class CrazyOgre extends GameEntity {
	private GameEntity club = null;
	
	private boolean isStunned = false;
	private int stunTurns = 0;
	
	private Random random = new Random();

	public CrazyOgre(int x, int y) {
		super(x, y, 'O');
		
		club = new GameEntity(x, y, '*');
		club.nextPosition(randomDirection());
		club.move();
	}
	
	// STUN MECHANICS
	public void stun() {
		isStunned = true;
		stunTurns = 2;
		mapSymbol = '8';
	}
	
	public void stunModifier() {
		stunTurns--;
		if (stunTurns <= 0) {
			isStunned = false;
			mapSymbol = 'O';
		}
	}
	
	
	public void nextOgrePos() {
		if (isStunned) {
			stunModifier();
			nextPosition(null); // nextPos stays the same, so doesnt move
		}
		else			
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
	
	
	public void moveClub() {
		club.move();
	}

	// club getters
	
	public int getClubX() {
		return club.xPos;
	}
	
	public int getClubY() {
		return club.yPos;
	}
	
	public char getClubSymbol() {
		return club.mapSymbol;
	}

	public int getNextClubX() {
		return club.nextXPos;
	}
	
	public int getNextClubY() {
		return club.nextYPos;
	}
}