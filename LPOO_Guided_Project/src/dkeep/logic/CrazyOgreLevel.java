package dkeep.logic;

import java.util.LinkedList;

import dkeep.logic.Game;

public class CrazyOgreLevel extends Map {
	/*
	 * Type of map that supports:
	 * 
	 * A Door triggered by a player with key "K" adjacent to it
	 * 
	 * A Key "k" which can be picked up by a player "K"
	 * 
	 * Multiple ogres procedurally created from custom blueprint constructor 
	 * (clubs added automatically depending on Ogre symbol)
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	LinkedList<CrazyOgre> ogreList = new LinkedList<CrazyOgre>();
	Key key;

	// Default constructors
	public CrazyOgreLevel() {
		this(1); // Single ogre in level
	}
	public CrazyOgreLevel(int ogreNum) {
		blueprint =  new char[][] {
			{'X','X','X','X','X','X','X','X','X'},
			{'I',' ',' ',' ',' ',' ',' ','k','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X'}
		};

		hero = new Player(7, 1);
		hero.arm();

		// Add all dem Ogres
		for (int i = 0; i < ogreNum; i++)	
			ogreList.add(new CrazyOgre(2, 4, true));

		// add key
		key = new Key(1, 7);
	}

	/* 
	 * Constructor for testing purposes (other maps with same logic)
	 * 
	 * Uses the given matrix to determine entities in map
	 * 
	 * Player(P): If more than one, last one iterated (up to down, left to right) will be the added one
	 * 
	 * Ogre(O):  Ogre with club
	 * Ogre(o):  Ogre without club
	 * Ogre(T):  Ogre for testing purposes (no club, no movement)
	 * 
	 * Key(k):   If more than one, last one iterated (up to down, left to right) will be the added one
	 * 
	 * NOTICE: The isUnlocked boolean assumes there is only one door in the level, therefore
	 * it'll trigger when any door is opened (This does not affect gameplay, it's just for testing purposes)
	 */
	public CrazyOgreLevel(char[][] blueprint, int ogreNum) {
		// iterate blueprint to look for entities (doors, guards and player) and add them
		for (int i = 0; i < blueprint.length; i++) {
			for (int j = 0; j < blueprint[i].length; j++) {
				switch(blueprint[i][j]) {
				case 'A':
				case 'P':
					hero = new Player(i, j);
					if (blueprint[i][j] == 'A')	hero.arm();
					blueprint[i][j] = ' ';
					break;
				case 'O':
					for (int k = 0; k < ogreNum; k++) ogreList.add(new CrazyOgre(i, j, true));
					blueprint[i][j] = ' ';
					break;
				case 'o':
					for (int k = 0; k < ogreNum; k++) ogreList.add(new CrazyOgre(i, j, false));
					blueprint[i][j] = ' ';
					break;
				case 'T':
					for (int k = 0; k < ogreNum; k++) ogreList.add(new CrazyOgre(i, j));
					blueprint[i][j] = ' ';
					break;
				case 'k':
					key = new Key(i, j);
					break;
				}
			}
		}

		this.blueprint = blueprint;
	}
	public CrazyOgreLevel(char[][] blueprint, int ogreNum, CrazyOgre ogre) {
		this(blueprint, ogreNum);
		ogreList.add(ogre);
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