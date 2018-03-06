package dkeep.logic;

import java.util.Random;

public class Guard extends GameEntity {
	private Random random;
	
	private enum Personality {ROOKIE, DRUNK, SUSPICIOUS} // TODO Make enum public and use it to refer to a personality (constructor arg, etc)
	private static final int MIN_STEPS_SUSPICION = 3; // Steps until suspicious might trigger his patrol reversion

	private boolean isSuspicious;
	private Personality personality;
	private int suspicionInc;
	
	private Game.Direction lastDirection;

	
	public Guard(int x, int y) {
		super(x, y, 'G');
		personality = Personality.ROOKIE;
		suspicionInc = 0;
	}

	public Guard(int x, int y, int type) {
		this(x, y);
		
		switch(type){
		case 1: 
			personality = Personality.DRUNK;
			// System.out.println("The Guard is Drunk");
			break;
		case 2: 
			personality = Personality.SUSPICIOUS;
			// System.out.println("The Guard is Suspicious");
			break;
		default: 
			personality = Personality.ROOKIE;
			// System.out.println("The Guard is a Rookie");
			break;
		}
	}
	
	private void triggerSuspicion()
	{
		if(random.nextBoolean()) {
			isSuspicious = !isSuspicious;
			switch(lastDirection) {
			case UP: 
				lastDirection = Game.Direction.DOWN;
				break;
			case DOWN: 
				lastDirection = Game.Direction.UP;
				break;
			case LEFT: 
				lastDirection = Game.Direction.RIGHT;
				break;
			case RIGHT: 
				lastDirection = Game.Direction.LEFT;
				break;
			case NONE:
				break;
			}
		}
	}
	
	public void move(Game.Direction dir) {
		// TODO Change movement depending on Personality	
		
		// use GameEntity nextPosition() and move() to move as usual
		nextPosition(dir);
		super.move();
	}
	
	/*
	new Integer[][] 		{{1, 8}, {5, 7}, {1, 7}, {5, 1}, {6, 1}, {6, 8}}, 
	new Game.Direction[]	{Game.Direction.LEFT, Game.Direction.LEFT,
							Game.Direction.DOWN, Game.Direction.DOWN,
							Game.Direction.RIGHT, Game.Direction.UP})));
							
	
	if(this.xPos == 1)
		{
			if( this.yPos == 8)
			{
				if(isSuspicious)
					direction = Direction.DOWN;
				else 
					direction = Direction.LEFT;	
			}
			else if ( this.yPos == 7)
			{
				if(isSuspicious) 
					direction = Direction.RIGHT;	
				else
					direction = Direction.DOWN;	
			}
		}
		else if(this.xPos == 5)
		{
			if(this.yPos == 1)
			{
				if(isSuspicious) 
					direction = Direction.RIGHT;	
				else
					direction = Direction.DOWN;	
			}
			else if(this .yPos == 7)
			{
				if(isSuspicious) 
					direction = Direction.UP;	
				else
					direction = Direction.LEFT;
			}
		}
		else if (this.xPos == 6 && this.yPos == 1)
		{
			if(isSuspicious) 
				direction = Direction.UP;	
			else
				direction = Direction.RIGHT;
		}
		else if(this.xPos == 6 && this.yPos == 8)
		{
			if(isSuspicious) 
				direction = Direction.LEFT;	
			else
				direction = Direction.UP;
		}
		*/
}
