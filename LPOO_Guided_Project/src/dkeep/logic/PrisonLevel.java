package dkeep.logic;

import java.util.ArrayList;

public class PrisonLevel extends Map {
	private Lever lever = new Lever(8,7,'k');
	private ArrayList<Door> doors = new ArrayList<Door>(); 

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
				{'X',' ','I',' ','I',' ','X',' ',' ','X'},
				{'X','X','X','X','X','X','X','X','X','X'}
				};
		
		hero = new Player(1, 1);
		enemyList.add(new Guard(1, 8, /*PATROL*/));
		
		// Add props (levers, etc...)
	}

	public Player getHero() {return this.hero;}

	public ArrayList<Door> getDoors(){return this.doors;}

	public boolean isValidPosition(int x, int y)
	{
		switch(map[x][y]) {
		case 'X':
		case 'I':
			return false;
		}
		return true;
	}

	
	// Starting Prison Guard Patrol
	/*entityList.add(	new Guard(7, 1,*/
}
