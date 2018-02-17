public class DungeonKeep {
	public static void main(String[] args) {	

		while(true) {
			// Render map
			Map.toString(Map.startingMap);

			// check if guard moved near player
			if (Player.position.isAdjacent(Guard.position)) {
				Player.endgame();
				System.out.println("You were caught!");
				return;
			}

			// calculate new player position
			Player.updatePosition();

			// check for map collisions and triggers caused by new player position
			switch (Player.nextPosition.tile(Map.startingMap)) {
			case 'k':
				Map.startingMap[5][0] = Map.startingMap[6][0] = 'S';
				break;
			case ' ': {
				
				// TODO create a superclass or interface for player and guard since they share variables and methods
				// TODO wrapper function for Position.move in the referred superclass or interface
				
				// update player's position and change map accordingly
				Player.position.moveTile(Player.nextPosition, 'H', Map.startingMap);
				
				// update guard's position and change map accordingly
				Guard.updatePosition(); 
				Guard.position.moveTile(Guard.nextPosition, 'G', Map.startingMap);
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
