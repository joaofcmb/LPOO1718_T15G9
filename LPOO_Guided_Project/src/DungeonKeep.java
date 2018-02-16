public class DungeonKeep {
	public static void main(String[] args) {	

		while(true) {
			// Render map
			Map.toString(Map.startingMap);

			// check if guard moved near player
			if (Guard.playerTrigger(Player.position)) {
				Player.endgame();
				System.out.println("You were caught!");
				return;
			}

			// calculate new player position
			Player.move();

			// check for map collisions and triggers caused by new player position
			switch (Map.startingMap[Player.nextPosition[0]][Player.nextPosition[1]]) {
			case 'k':
				Map.startingMap[5][0] = Map.startingMap[6][0] = 'S';
				break;
			case ' ': {
				// update player's position and change map accordingly
				Map.startingMap[Player.position[0]][Player.position[1]] = ' ';
				Player.position=Player.nextPosition.clone();
				Map.startingMap[Player.position[0]][Player.position[1]] = 'H';

				// update guard's position and change map accordingly (Notice it's duplicate code from player)
				Map.startingMap[Guard.guardPos[0]][Guard.guardPos[1]] = ' ';
				Guard.updatePos();
				Map.startingMap[Guard.guardPos[0]][Guard.guardPos[1]] = 'G';
				break;
			}
			case 'S': {
				Player.endgame();
				System.out.println("Escaped!!");
				return;
			}
			}
		}		
	}
}
