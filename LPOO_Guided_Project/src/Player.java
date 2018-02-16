import java.util.Scanner;

public class Player {
	static Scanner playerInput = new Scanner(System.in);

	public static int[] position = {1,1};
	static int[] nextPosition = new int[2];

	public static int move() {
		nextPosition = position.clone();

		char keyInput = playerInput.next().charAt(0);
		switch(keyInput) {
		case 'w':
			nextPosition[0]--;
			break;
		case 'a':
			nextPosition[1]--;
			break;
		case 'd':
			nextPosition[1]++;
			break;
		case 's':
			nextPosition[0]++;
			break;
		default:
			endgame();
			System.out.println("End of program");
			return -1;
		}
		return 0;
	}

	//function for when player enters new level, updates its position
	public static void levelPosition(int level) {
		switch(level) {
		case 2: 
			position[0] = 7;
			position[1] = 1;
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
