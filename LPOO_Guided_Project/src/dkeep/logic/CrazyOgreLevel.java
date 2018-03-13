package dkeep.logic;

import dkeep.logic.Game.Direction;
import dkeep.logic.Game.GameState;

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
		//TODO arm the hero

		// Add all dem Ogres
		for (int i = 0; i < ogreNum; i++)	
			enemyList.add(new CrazyOgre(2, 4));
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
	public GameState update(Direction heroDirection) {
		GameState ret = super.update(heroDirection);
		
		for(GameEntity enemy : enemyList) {
			if (enemy instanceof CrazyOgre)
				ogreMove((CrazyOgre)enemy);
			
			if (hero.entityTrigger(enemy))
				return Game.GameState.GAME_OVER; // enemy trigger with hero signifies hero death (game over)
		}
		
		return ret;
	}
	

	private boolean validTileHero(int x, int y) {
		switch(blueprint[x][y]) {
		case 'k': // key
			hero.pickKey();
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
		// TODO change key back to default
		
		if (blueprint[x][y] == 'k') // Ogre on key
			blueprint[x][y] = '$';
		
		
		return super.validTile(x, y);
	}
}