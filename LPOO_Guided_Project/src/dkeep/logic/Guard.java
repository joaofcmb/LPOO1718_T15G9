package dkeep.logic;

import java.util.Random;

public class Guard extends GameEntity {
	private Random random;

	private enum Personality {ROOKIE, DRUNK, SUSPICIOUS} // TODO Make enum public and use it to refer to a personality (constructor arg, etc)
	private static final int MIN_STEPS_SUSPICION = 3; // Steps until suspicious might trigger his patrol reversion

	private boolean isSuspicious = false;
	private Personality personality;
	private int suspicionInc;

	private Patrol patrol;
	private Game.Direction lastDirection;


	public Guard(int x, int y, String patrolConfig) {
		super(x, y, 'G');
		personality = Personality.ROOKIE;
		suspicionInc = 0;
		patrol = new Patrol(patrolConfig);
	}

	public Guard(int x, int y, int type, String patrolConfig) {
		this(x, y, patrolConfig);

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
	

	public void move() {
		Game.Direction dir = patrol.nodeDirection(xPos, yPos, isSuspicious);
		
		if (dir == null)		dir = lastDirection;
		else					lastDirection = dir;
			
		
		// TODO Change movement depending on Personality	

		// use GameEntity nextPosition() and move() to move as usual
		nextPosition(dir);
		super.move();
	}
	

	/*
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
