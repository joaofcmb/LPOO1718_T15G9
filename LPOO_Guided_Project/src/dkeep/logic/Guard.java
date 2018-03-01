package dkeep.logic;

public class Guard extends Enemy /*implements Movable*/ {
	private enum Personality {ROOKIE, DRUNK, SUSPICIOUS}
	private static final int MIN_STEPS_SUSPICION = 3; //Steps until suspicious might trigger his patrol reversion

	private boolean isSuspicious;
	private Personality personality;
	private int suspicionInc;


	public Guard()
	{
		super();
		personality = Personality.ROOKIE;
		suspicionInc = 0;
	}

	public Guard(int x, int y, char symbol, int type) {
		super(x, y, symbol);
		switch(type){
		case 1: 
			personality = Personality.DRUNK;
			System.out.println("The Guard is Drunk");
			break;
		case 2: 
			personality = Personality.SUSPICIOUS;
			System.out.println("The Guard is Suspicious");
			break;
		default : 
			personality = Personality.ROOKIE;
			System.out.println("The Guard is a Rookie");
			break;
		}
		suspicionInc = 0;
	}

	public void move() {
		// check if there is movement direction change for the guard patrol

		patrolShift();

		switch(this.direction) {
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


	//check if in position to change direction
	private void patrolShift() {
		if(this.personality == Personality.SUSPICIOUS) //or if guard woke up and switches direction TODO add later
		{	
			this.suspicionInc++;

			if(this.suspicionInc > MIN_STEPS_SUSPICION) {
				triggerSuspicion();
				this.suspicionInc = 0;
			}
		}

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
	}

	private void triggerSuspicion()
	{
		if(random.nextBoolean()) {
			isSuspicious = !isSuspicious;
			switch(direction) {
			case UP: 
				direction = Direction.DOWN;
				break;
			case DOWN: 
				direction = Direction.UP;
				break;
			case LEFT: 
				direction = Direction.RIGHT;
				break;
			case RIGHT: 
				direction = Direction.LEFT;
				break;
			}
		}
	}
}
