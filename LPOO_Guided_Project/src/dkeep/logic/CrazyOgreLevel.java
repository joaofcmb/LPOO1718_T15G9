package dkeep.logic;

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
		// arm the hero
		
		// Add all dem Ogres
		for (int i = 0; i < ogreNum; i++)	
			enemyList.add(new CrazyOgre(2, 4));
	}
	
	public CrazyOgreLevel() {
		this(1);
	}
	
	public boolean validTile(int x, int y) {
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
}