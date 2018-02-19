public class DungeonKeep {
	public static void main(String[] args) {	
		boolean level=false;
		//level 1 cycle
		while(level) {
			// Render map
			Map.toString(Map.current);

			// check if guard moved near player
			if (Player.position.isAdjacent(Guard.position)) {
				Player.endgame();
				System.out.println("You were caught!");
				return;
			}

			// calculate new player position
			if(Player.updatePosition() == -1)
			{
				Player.endgame();
				return;
			}

			// check for map collisions and triggers caused by new player position
			switch (Player.nextPosition.tile(Map.current)) {
			case 'k':
				Map.current[5][0] = Map.current[6][0] = 'S';
				break;
			case ' ': {

				// TODO create a superclass or interface for player and guard since they share variables and methods
				// TODO wrapper function for Position.move in the referred superclass or interface

				// update player's position and change map accordingly
				Player.position.moveTile(Player.nextPosition, 'H', Map.current);

				// update guard's position and change map accordingly
				Guard.updatePosition(); 
				Guard.position.moveTile(Guard.nextPosition, 'G', Map.current);
				break;
			}
			case 'S': {
				Map.nextLevel();
				level=false;
				System.out.println("You went up the stairs.");
				break;
			}
			}	
		}
		
		Map.nextLevel();

		//LEVEL 2
		level=true;
		while(level) {
			Map.toString(Map.current);
			
			if (Player.position.isAdjacent(Ogre.position)) {
				Player.endgame();
				System.out.println("You were caught!");
				return;
			}
			
			if(Player.updatePosition() == -1)
			{
				Player.endgame();
				return;
			}
			
			// check for map collisions and triggers caused by new player position
			switch (Player.nextPosition.tile(Map.current)) {
			case 'k':
				Map.current[5][0] = Map.current[6][0] = 'S';
				break;
			case ' ': {

				// TODO create a superclass or interface for player and guard since they share variables and methods
				// TODO wrapper function for Position.move in the referred superclass or interface

				// update player's position and change map accordingly
				Player.position.moveTile(Player.nextPosition, 'H', Map.current);

				// update ogre's position and change map accordingly
				Ogre.updatePosition(); 
				Ogre.position.moveTile(Ogre.nextPosition, 'O', Map.current);
				break;
			}
			case 'S': {
				level=false;
				Player.endgame();
				System.out.println("Game Over.");
				break;
			}
			}
		}
	}
}
