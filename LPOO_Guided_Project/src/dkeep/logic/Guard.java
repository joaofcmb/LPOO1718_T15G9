package dkeep.logic;

import java.io.Serializable;
import java.util.Random;

import dkeep.logic.Game.Personality;

public class Guard extends GameEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Random random = new Random();

	private static final int MIN_STEPS_SUSPICION = 3; // Steps until suspicious might trigger his patrol reversion
	private int suspicionStep; // Counter of steps

	private Personality personality;
	private boolean inverseDirection = false;

	private Patrol patrol;
	private Game.Direction lastDirection;

	/**
	 * Constructor for Guard
	 * 
	 * Defaults Guard personality to Rookie
	 * 
	 * @param x Initial position in X Axis
	 * @param y Initial position in Y Axis
	 * @param patrolConfig Patrol pattern of Guard
	 */
	public Guard(int x, int y, String patrolConfig) {
		super(x, y, 'G');
		personality = Personality.ROOKIE;
		patrol = new Patrol(patrolConfig);
	}

	/**
	 * Constructor for Guard
	 * 
	 * @param x Initial position in X Axis
	 * @param y	Initial position in Y Axis
	 * @param type	Personality of Guard
	 * @param patrolConfig	Patrol pattern of Guard
	 */
	public Guard(int x, int y, Personality type, String patrolConfig) {
		this(x, y, patrolConfig);

		personality = type;
	}


	public void move() {
		if (personality == Personality.STATIC)	return;

		Game.Direction dir = nextDirection(xPos, yPos, inverseDirection);

		// TODO Figure out rare bug of inverting position while not asleep
		switch(personality) {
		case DRUNK:
			if (random.nextBoolean()) // Guard fall asleep?
				this.mapSymbol = 'g';

			if (this.mapSymbol == 'g') { // Guard is sleeping
				if (random.nextBoolean()) { // guard wake up?
					this.mapSymbol = 'G';
					inverseDirection = random.nextBoolean();

					dir = nextDirection(xPos, yPos, inverseDirection);
				}
				else 	dir = null;
			}
			break;
		case SUSPICIOUS:
			if (suspicionStep >= MIN_STEPS_SUSPICION) {
				if (random.nextBoolean()) {
					inverseDirection = !inverseDirection;
					suspicionStep = 0;
				}
			}
			else suspicionStep++;
			break;
		default:
			break;
		}

		// use GameEntity nextPosition() and move() to move as usual
		nextPosition(dir);
		super.move();
	}

	private Game.Direction nextDirection(int xPos, int yPos, boolean inverseDirection) {
		Game.Direction dir = patrol.nodeDirection(xPos, yPos, inverseDirection);

		if (dir == null) 	dir = lastDirection;
		else 				lastDirection = dir;

		if (inverseDirection)	dir = invert(dir); // invert direction to go the other way

		return dir;
	}

	private Game.Direction invert(Game.Direction dir) {
		if (dir == null)	return null;

		switch(dir) {
		case DOWN:
			return Game.Direction.UP;
		case LEFT:
			return Game.Direction.RIGHT;
		case RIGHT:
			return Game.Direction.LEFT;
		case UP:
			return Game.Direction.DOWN;
		default:
			return null;
		}
	}

	public boolean hasInverted() {
		return inverseDirection;
	}
}
