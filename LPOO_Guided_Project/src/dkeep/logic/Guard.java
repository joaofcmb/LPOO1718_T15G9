package dkeep.logic;

import java.util.Random;

public class Guard extends GameEntity {
	private Random random;
	
	private enum Personality {ROOKIE, DRUNK, SUSPICIOUS}
	private static final int MIN_STEPS_SUSPICION = 3; // Steps until suspicious might trigger his patrol reversion

	private boolean isSuspicious;
	private Personality personality;
	private int suspicionInc;
	
	private Patrol patrol;
	private Game.Direction direction;

	
	public Guard(int x, int y, Patrol patrol) {
		super(x, y, 'G');
		this.patrol = patrol;
		personality = Personality.ROOKIE;
		suspicionInc = 0;
	}

	public Guard(int x, int y, Patrol patrol, int type) {
		this(x, y, patrol);
		
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
			switch(direction) {
			case UP: 
				direction = Game.Direction.DOWN;
				break;
			case DOWN: 
				direction = Game.Direction.UP;
				break;
			case LEFT: 
				direction = Game.Direction.RIGHT;
				break;
			case RIGHT: 
				direction = Game.Direction.LEFT;
				break;
			case NONE:
				break;
			}
		}
	}
	
	// Override the default move, to prevent accidentally using it
	public int move(Game.Direction dir) {
		move();
		return 0;
	}

	public void move() {
		Game.Direction patrolDirection = patrolMovement();
		
		// TODO Change movement depending on Personality	
		
		// use GameEntity move() to move as usual
		super.move(patrolDirection);
	}

	
	private Game.Direction patrolMovement() {
		// Get Direction from its patrol if there's any change in direction (if it is not on node, it returns NONE)
		Game.Direction patrolDirection = patrol.nodeDirection(xPos, yPos);
		
		if (patrolDirection == Game.Direction.NONE)
			// guard is not on node, use last direction
			patrolDirection = direction;
		else
			// otherwise save the new direction
			direction = patrolDirection;
		return patrolDirection;
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
