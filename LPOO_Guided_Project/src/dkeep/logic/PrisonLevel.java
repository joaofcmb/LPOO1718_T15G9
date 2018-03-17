package dkeep.logic;

public class PrisonLevel extends Map {
	public PrisonLevel(Guard.Personality personality) {
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
		propList.add(new Door(5, 0));
		propList.add(new Door(6, 0));
		
		hero = new Player(1, 1);
		enemyList.add(new Guard(1, 8, personality, "1, 8, l, " + "1, 7, d, "
										 		 + "5, 7, l, " + "5, 1, d, "
										 		 + "6, 1, r, " + "6, 8, u"));
	}
	
	public PrisonLevel() {
		this(Guard.Personality.ROOKIE);
	}

	// TODO use blueprint to infer these objects
	// Constructor for testing purposes (other maps with same logic)
	public PrisonLevel(char[][] blueprint, Player hero, Guard guard, Door door1, Door door2) {
		this.blueprint = blueprint;
		
		propList.add(door1);
		propList.add(door2);
		
		this.hero = hero;
		enemyList.add(guard);
	}
	
	public boolean validTile(int x, int y) {
		switch(blueprint[x][y]) {
		case 'k':
			openDoors();
			return false;
		}
		
		return super.validTile(x, y);
	}
}
