package dkeep.logic;

import java.util.ArrayList;

public class PrisonLevel extends Level{
	private int row=0, column=0;
	
	private Player hero = new Player(1,1, 'H');

	private Guard guard = new Guard(1,8,'G',2); // Suspicious for testing

	private Lever lever = new Lever(8,7,'k');

	private ArrayList<Door> doors = new ArrayList<Door>();

	private char[][] map = {
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X','H',' ',' ','I',' ','X',' ','G','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'X',' ','I',' ','I',' ','X',' ',' ','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X',' ','X','X','X','X',' ','X'},
			{'X',' ','I',' ','I',' ','X',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}
	};

	public PrisonLevel() {
		doors.add(new Door(5,0,'I'));
		doors.add(new Door(6,0,'I'));
		row = map.length;
		column = map[0].length;
	}

	public Player getHero() {return this.hero;}

	public ArrayList<Door> getDoors(){return this.doors;}

	public boolean hasGuard() {return true;}
	public Guard getGuard() {return this.guard;}

	public boolean hasOgre() {return false;}
	public Ogre getOgre() {return null;}

	public boolean hasLever() {return true;}
	public Lever getLever() {return this.lever;}

	public boolean hasKey() {return false;}
	public Key getKey() {return null;}

	public boolean hasHeroClub() {return false;}

	public char[][] getMap() {return this.map;}

	public int getRows() {return this.row;}

	public int getColumns() {return this.column;}
}
