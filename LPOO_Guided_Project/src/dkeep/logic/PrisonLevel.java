package dkeep.logic;

import java.util.ArrayList;

public class PrisonLevel extends Map {
	private Lever lever = new Lever(8,7,'k'); // TODO remove this and add it to propList
	private ArrayList<Door> doors = new ArrayList<Door>(); // TODO remove this to add to propList

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

		tacticalMap =  new char[][] {
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X',' ',' ',' ','I',' ','X','d','l','X'},
			{'X','X','X',' ','X','X','X','d','u','X'},
			{'X',' ','I',' ','I',' ','X','d','u','X'},
			{'X','X','X',' ','X','X','X','d','u','X'},
			{'I','d','l','l','l','l','l','l','u','X'},
			{'I','r','r','r','r','r','r','r','u','X'},
			{'X','X','X',' ','X','X','X','X',' ','X'},
			{'X',' ','I',' ','I',' ','X',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}
		};

		hero = new Player(1, 1);
		enemyList.add(new Guard(1, 8));

		// Add props (levers, etc...)
	}
}
