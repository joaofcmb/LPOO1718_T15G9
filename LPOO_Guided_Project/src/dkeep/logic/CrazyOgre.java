package dkeep.logic;

import java.util.Random;

public class CrazyOgre extends GameEntity {
	private GameEntity club = null;
	private boolean isStatic = false;
	
	private boolean isStunned = false;
	private int stunTurns = 0;
	
	private Random random = new Random();
	
	public CrazyOgre(int x, int y) {
		this(x, y, false);
		isStatic = true;
	}

	public CrazyOgre(int x, int y, boolean hasClub) {
		super(x, y, 'O');
		
		if (hasClub) {
			club = new GameEntity(x, y, '*');
			club.nextPosition(randomDirection());
			club.move();
		}
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
	
	public boolean isStunned() {
		return isStunned;
	}

	
	
	public void nextOgrePos() {
		if (isStunned) {
			stunModifier();
			nextPosition(null); // nextPos stays the same, so doesnt move
		}
		else if (isStatic)
			nextPosition(null);
		else
			nextPosition(randomDirection());
	}
	
	public void nextClubPos() {
		if (club == null)	return;
		
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
		if (club != null)
			club.move();
	}

	// club getters
	public boolean hasClub() {
		return (club != null);
	}
	
	public int getClubX() {
		return club != null ? club.xPos : -5;
	}
	
	public int getClubY() {
		return club != null ? club.yPos : -5;
	}
	
	public char getClubSymbol() {
		return club != null ? club.mapSymbol : mapSymbol;
	}

	public int getNextClubX() {
		return club != null ? club.nextXPos : 0;
	}
	
	public int getNextClubY() {
		return club != null ? club.nextYPos : 0;
	}
}