package dkeep.logic;

public class Guard extends GameEntity {
	private Patrol patrol;
	private Game.Direction direction;
	
	public Guard(int x, int y, char symbol, Patrol patrol) {
		super(x, y, 'G');
		this.patrol = patrol;
	}
	
	public void move() {
		// Get Direction from its patrol if there's any change in direction (if it is not on node, it returns NONE)
		Game.Direction patrolDirection = patrol.nodeDirection(xPos, yPos);
		
		if (patrolDirection == Game.Direction.NONE)
			// guard is not on node, use last direction
			patrolDirection = direction;
		else
			// otherwise save the new direction
			direction = patrolDirection;
			
		// use GameEntity move() to move as usual
		super.move(patrolDirection);
	}
}
