package dkeep.logic;

import java.util.ArrayList;

public class CrazyOgreLevel extends Level{
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

	public boolean hasGuard() {return false;}
	public Guard getGuard() {return null;}

	public boolean hasOgre() {return true;}
	public Ogre getOgre() {return ogre;} 

	public boolean hasLever() {return false;}
	public Lever getLever() {return null;}

	public boolean hasKey() {return true;}
	public Key getKey() {return key;} 

	public boolean hasHeroClub() {return false;}

	public char[][] getMap() {return this.map;}

	public int getRows() {return this.row;}

	public int getColumns() {return this.column;}
}