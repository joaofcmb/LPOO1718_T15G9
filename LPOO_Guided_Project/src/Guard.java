import java.util.Arrays;

public class Guard {
	public enum Direction{ NONE, UP, DOWN, LEFT, RIGHT } // Temporary till a uniform class is developed for handling positions

	public static Direction guardDirection = Direction.LEFT;
	static int[] guardPos = {1,8};

	// FUNCTION: updates guard's position based on its patrol (Doesn't render new position)
	public static void updatePos() {
		// check if there is movement direction change for the guard patrol
		switch(Arrays.toString(guardPos)) {
		case "[1, 8]":
		case "[5, 7]":
			guardDirection = Direction.LEFT;
			break;
		case "[1, 7]":
		case "[5, 1]":
			guardDirection = Direction.DOWN;
			break;
		case "[6, 1]":
			guardDirection = Direction.RIGHT;
			break;
		case "[6, 8]":
			guardDirection = Direction.UP;
			break;
		default:
			break;
		}

		// update guard's position in the matrix
		switch(guardDirection) {
		case UP:		
			guardPos[0]--;
			break;
		case DOWN: 		
			guardPos[0]++;
			break;
		case LEFT:		
			guardPos[1]--;
			break;
		case RIGHT:		
			guardPos[1]++;
			break;
		default:
			break;
		}			
	}
	
	// FUNCTION: Checks if the player is nearby guard
	public static boolean playerTrigger(int[] playerPos) {
		// notice how the calculated gap is only equal to 1 when the player is near the guard (non diagonally)
		return (Math.abs(playerPos[0] - guardPos[0]) + Math.abs(playerPos[1] - guardPos[1]) < 2); 	
	}
}