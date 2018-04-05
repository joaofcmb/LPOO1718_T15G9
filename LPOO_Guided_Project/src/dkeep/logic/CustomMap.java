package dkeep.logic;

import java.util.LinkedList;

public class CustomMap extends Map{
	LinkedList<CrazyOgre> ogreList = new LinkedList<CrazyOgre>();
	Key key;

	public CustomMap(int xP,int yP,int xO,int yO,int xK,int yK, char[][] map, int nOgres) {
		blueprint =  map;

		hero = new Player(xP, yP);
		hero.arm();

		for (int i = 0; i < nOgres; i++)	
			ogreList.add(new CrazyOgre(xO, yO, true));
		key = new Key(xK, yK);
	}

	private boolean playerMove(Game.Direction direction) {
		hero.nextPosition(direction); // calculate next Position

		if (validTileHero(hero.getNextX(), hero.getNextY())) {
			hero.move();
			return true;
		}
		return false;
	}

	private void ogreMove(CrazyOgre ogre) {
		// move ogre
		do {
			ogre.nextOgrePos(); // calculate next Position
		} while (!validTileOgre(ogre.getNextX(), ogre.getNextY()));

		ogre.move();
		if (!ogre.hasClub())	return;

		// move club
		do {
			ogre.nextClubPos();
		} while (!validTileOgre(ogre.getNextClubX(), ogre.getNextClubY()));

		ogre.moveClub();
	}


	@Override
	public Game.GameState update(Game.Direction heroDirection) {
		if (!playerMove(heroDirection)) // cancel turn if player movement isn't to a valid position
			return state;

		if (key != null)	key.uncover();

		for(CrazyOgre ogre : ogreList) {
			ogreMove(ogre);

			if (hero.entityTrigger(ogre))
				return Game.GameState.GAME_OVER; // enemy trigger with hero signifies hero death (game over)
		}

		return state;
	}

	private boolean validTileHero(int x, int y) {
		switch(blueprint[x][y]) {
		case 'k': // key
			hero.pickKey();
			key = null;
			blueprint[x][y] = ' ';
			return true;
		case 'S':
			state = Game.GameState.NEXT_LEVEL;
		case ' ':
			return true;
		case 'I':
			if (hero.hasKey()) {
				isUnlocked = true;
				blueprint[x][y] = 'S';
			}
		default:
			return false;
		}
	}

	private boolean validTileOgre(int x, int y) {		
		switch(blueprint[x][y]) {
		case 'k':
			key.hide();
		case ' ':
			return true;
		default:
			return false;
		}
	}

	// TODO Make class to encapsulate the operations to add entities to matrixes and avoid repeating code with other levels, etc..
	public String toString() {
		return matrixToString(charMap());
	}

	@Override
	public char[][] charMap() {
		char[][] map = copyBlueprint();

		addEntity(map, hero);
		for (CrazyOgre ogre : ogreList) {
			addEntity(map, ogre);
		}
		addEntity(map, key);
		return map;
	}
}

