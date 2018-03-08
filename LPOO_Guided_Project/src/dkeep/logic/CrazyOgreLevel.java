package dkeep.logic;

public class CrazyOgreLevel extends Map {
	//private Key key = new Key(1,7,'k');

	public CrazyOgreLevel() {
		super();

		blueprint =  new char[][] {
			{'X','X','X','X','X','X','X','X','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X'}
		};
		
		//private Ogre ogre = new Ogre(1,8,'G');
		hero = new Player(7, 1);
	}
}