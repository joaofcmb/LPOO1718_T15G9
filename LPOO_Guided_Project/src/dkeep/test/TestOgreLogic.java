package dkeep.test;

import static org.junit.Assert

import org.junit.jupiter.api.Test;

import dkeep.logic.Door;
import dkeep.logic.Player;
import dkeep.logic.CrazyOgreLevel;
import dkeep.logic.Game;

class TestOgreLogic {

	char[][] blueprint = {
			{'X','X','X','X','X'},
			{'I',' ',' ',' ','X'},
			{'I',' ',' ',' ','X'},
			{'X','k',' ',' ','X'},
			{'X','X','X','X','X'}
	};
	
	Door door1 = new Door(1, 0);
	Door door2 = new Door(2, 0);
	
	CrazyOgreLevel testLevel = new CrazyOgreLevel(blueprint, new Player(1, 1), new Guard(1, 3, Guard.Personality.STATIC, ""), door1, door2);
	
	@Test
	public void testMoveHeroFree() {

}
