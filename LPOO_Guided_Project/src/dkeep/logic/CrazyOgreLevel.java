package dkeep.logic;

import java.util.ArrayList;

public class CrazyOgreLevel extends Map{
	private int row=0, column=0;

	private Player hero = new Player(7,1, 'H');

	private Ogre ogre = new Ogre(1,8,'G');

	private Key key = new Key(1,7,'k');

	private ArrayList<Door> doors = new ArrayList<Door>();

	private char[][] map = {
			{'X','X','X','X','X','X','X','X','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X'}
	};

	public CrazyOgreLevel() {
		doors.add(new Door(1,0,'I'));
		row = map.length;
		column = map[0].length;
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
}