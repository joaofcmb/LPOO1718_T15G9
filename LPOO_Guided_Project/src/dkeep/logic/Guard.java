package dkeep.logic;

import java.util.Random;

public class Guard extends MapEntity /*implements Movable*/ {
	private enum Personality {ROOKIE, DRUNK, SUSPICIOUS}
	private Random rand = new Random();

	private Direction direction = Direction.LEFT;
	private boolean isSuspicious;
	private Personality personality;

	public Guard()
	{
		super();
		personality = Personality.ROOKIE;
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
	}

	public void updatePosition(MapEntity.Direction dir) {
		// check if there is movement direction change for the guard patrol

		if(this.personality == Personality.SUSPICIOUS ) //or if guard woke up and switches direction TODO add later
			patrolShift();

		switch(dir) {
		case UP: 
			yPos--;
			break;
		case LEFT:
			xPos--;
			break;
		case DOWN:
			yPos++;
			break;
		case RIGHT:
			xPos++;
			break;
		}
	}
	//check if in position to change direction
	private void patrolShift() {
		triggerSuspicion();
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
		if(rand.nextBoolean()) {
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
