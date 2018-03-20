package dkeep.logic;

import dkeep.logic.Game;

public class CrazyOgreLevel extends Map {
	public CrazyOgreLevel(int ogreNum) {
		super();

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
			enemyList.add(new CrazyOgre(2, 4)); // TODO make spawn random (or let them all spawn in the same place)
		
		// add key
		propList.add(new Key(1, 7));
	}

	public CrazyOgreLevel() {
		this(1);
	}
	
	protected boolean playerMove(Game.Direction direction) {
		hero.nextPosition(direction); // calculate next Position
		
		if (validTileHero(hero.getNextX(), hero.getNextY())) {
			hero.move();
			return true;
		}
		return false;
	}
	
	protected void ogreMove(CrazyOgre ogre) {
		// move ogre
		do {
			ogre.nextOgrePos(); // calculate next Position
		} while (!validTileOgre(ogre.getNextX(), ogre.getNextY()));
					
		ogre.move();
		
		// move club
		do {
			ogre.nextClubPos();
		} while (!validTileOgre(ogre.getNextClubX(), ogre.getNextClubY()));
		
		ogre.moveClub();
	}
	

	@Override
	public Game.GameState update(Game.Direction heroDirection) {
		Game.GameState ret = super.update(heroDirection);
		
		if (!propList.isEmpty())
			((Key) propList.get(0)).uncover();
		
		for(GameEntity enemy : enemyList) {
			if (enemy instanceof CrazyOgre)
				ogreMove((CrazyOgre)enemy);
			
			if (hero.entityTrigger(enemy))
				return Game.GameState.GAME_OVER; // enemy trigger with hero signifies hero death (game over)
		}
		
		return ret;
	}

	// TODO check key collisions independantly from the blueprint
	
	private boolean validTileHero(int x, int y) {
		switch(blueprint[x][y]) {
		case 'k': // key
			hero.pickKey();
			propList.remove(0);
			blueprint[x][y] = ' ';
			return true;
		case 'I': // closed door
			if (hero.hasKey())
				blueprint[x][y] = 'S';
			return false;
		case 'S':
			state = Game.GameState.NEXT_LEVEL;
			return true;
		}

		return super.validTile(x, y);
	}

	private boolean validTileOgre(int x, int y) {		
		if (blueprint[x][y] == 'k') { // Ogre on key
			((Key) propList.get(0)).hide();
		}
		
		
		return super.validTile(x, y);
	}
}