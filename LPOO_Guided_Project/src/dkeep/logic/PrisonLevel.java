package dkeep.logic;

public class PrisonLevel extends Map {
	public PrisonLevel() {
		super();

		blueprint =  new char[][] {
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X',' ',' ',' ','I',' ','X',' ',' ','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'X',' ','I',' ','I',' ','X',' ',' ','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X',' ','X','X','X','X',' ','X'},
			{'X',' ','I',' ','I',' ','X','k',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}
		};
		
		hero = new Player(1, 1);
		enemyList.add(new Guard(1, 8, Guard.Personality.DRUNK, "1, 8, l, " + "1, 7, d, "
															 + "5, 7, l, " + "5, 1, d, "
															 + "6, 1, r, " + "6, 8, u"));
	}
	
	public boolean validTile(int x, int y) {
		switch(blueprint[x][y]) {
		case 'k':
			blueprint[5][0] = 'S';
			blueprint[6][0] = 'S';
			return false;
		case 'S':
			state = Game.GameState.NEXT_LEVEL;
			return true;
		}
		
		return super.validTile(x, y);
	}
}
