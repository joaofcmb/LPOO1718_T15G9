public class Guard {
	
	private static Position.Direction direction = Position.Direction.LEFT;
	public static Position position = new Position(1, 8);
	public static Position nextPosition = new Position();

	// FUNCTION: updates guard's position based on its patrol (Doesn't render new position)
	public static void updatePosition() {
		// check if there is movement direction change for the guard patrol
		switch(position.toString()) {
		case "(1, 8)":
		case "(5, 7)":
			direction = Position.Direction.LEFT;
			break;
		case "(1, 7)":
		case "(5, 1)":
			direction = Position.Direction.DOWN;
			break;
		case "(6, 1)":
			direction = Position.Direction.RIGHT;
			break;
		case "(6, 8)":
			direction = Position.Direction.UP;
			break;
		default:
			break;
		}
		
		nextPosition = position.move(direction);
	}
}