import java.util.Scanner;

public class DungeonKeep {
	public static void main(String[] args) {
		Scanner playerInput = new Scanner(System.in);

		int[] playerPos = {1,1};
		int[] nextplayerPos = new int[2];

		while(true) {
			// Render map
			Map.toString(Map.startingMap);
			
			// check if guard moved near player

			// calculate new player position
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

			// check for map collisions and triggers caused by new player position
			switch(Map.startingMap[nextplayerPos[0]][nextplayerPos[1]]) {
			case 'k':
				Map.startingMap[5][0] = Map.startingMap[6][0] = 'S';
				break;
			case ' ': {
				Map.startingMap[playerPos[0]][playerPos[1]] = ' ';
				playerPos=nextplayerPos.clone();
				Map.startingMap[playerPos[0]][playerPos[1]] = 'H';
				break;
			}
			case 'S': {
				System.out.println("Escaped!!");
				playerInput.close();
				return;
			}
			}
			
			// check if player moved near guard
			
			// update guard's position and change map accordingly (Notice it's duplicate code from player)
			Map.startingMap[Guard.guardPos[0]][Guard.guardPos[1]] = ' ';
			Guard.updatePos();
			Map.startingMap[Guard.guardPos[0]][Guard.guardPos[1]] = 'G';
		}		
	}
}
