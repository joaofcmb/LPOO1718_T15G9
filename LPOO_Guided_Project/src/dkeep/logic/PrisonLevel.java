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
}
