import java.util.Scanner;

public class DungeonKeep {
	public static void main(String[] args) {
		Scanner playerInput = new Scanner(System.in);

		char[][] startingMap = {
				{'X','X','X','X','X','X','X','X','X','X'},
				{'X','H',' ',' ','I',' ','X',' ','G','X'},
				{'X','X','X',' ','X','X','X',' ',' ','X'},
				{'X',' ','I',' ','I',' ','X',' ',' ','X'},
				{'X','X','X',' ','X','X','X',' ',' ','X'},
				{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X','X','X',' ','X','X','X','X',' ','X'},
				{'X',' ','I',' ','I',' ','X','k',' ','X'},
				{'X','X','X','X','X','X','X','X','X','X'}
		};

		int[] playerPos = {1,1}, guardPos = {1,8};
		int[] nextplayerPos = new int[2];

		
		while(true) {
			Map.toString(startingMap);

			nextplayerPos = playerPos.clone();

			char keyInput = playerInput.next().charAt(0);
			switch(keyInput) {
			case 'w':
				nextplayerPos[0]--;
				break;
			case 'a':
				nextplayerPos[1]--;
				break;
			case 'd':
				nextplayerPos[1]++;
				break;
			case 's':
				nextplayerPos[0]++;
				break;
			default:
				System.out.println("End of program");
				playerInput.close();
				return;
			}

			switch(startingMap[nextplayerPos[0]][nextplayerPos[1]]) {
			case 'k':
				startingMap[5][0] = startingMap[6][0] = 'S';
				break;
			case ' ': {
				startingMap[playerPos[0]][playerPos[1]] = ' ';
				playerPos=nextplayerPos.clone();
				startingMap[playerPos[0]][playerPos[1]] = 'H';
				break;
			}
			case 'S': {
				System.out.println("Escaped!!");
				playerInput.close();
				return;
			}
			}
		}		
	}
}
