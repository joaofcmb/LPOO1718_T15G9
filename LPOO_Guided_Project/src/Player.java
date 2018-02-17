import java.util.Scanner;

public class Player {
	static Scanner playerInput = new Scanner(System.in);
	
	public static Position position = new Position(1, 1);
	public static Position nextPosition = new Position();

	public static int updatePosition() {
		Position.Direction direction = Position.Direction.NONE;

		char keyInput = playerInput.next().charAt(0);
		switch(keyInput) {
		case 'w':
			direction = Position.Direction.UP;
			break;
		case 'a':
			direction = Position.Direction.LEFT;
			break;
		case 's':
			direction = Position.Direction.DOWN;
			break;
		case 'd':
			direction = Position.Direction.RIGHT;
			break;
		default:
			endgame();
			System.out.println("End of program");
			return -1;
		}
		// TODO Separate Input retrieval from this function (maybe add it to a Game class)
		
		nextPosition = position.move(direction);
		
		return 0;
	}

	//function for when player enters new level, updates its position
	public static void levelPosition(int level) {
		switch(level) {
		case 2: 
			position = new Position(7, 1);
			break;
		default:
			System.out.println("levelPosition() - Unknown level");
		}
	}
	
	//public function that calls the private function that closes the Scanner
	public static void endgame() {
		closePlayerInput();
	}

	private static void closePlayerInput() {
		playerInput.close();
	}
}
