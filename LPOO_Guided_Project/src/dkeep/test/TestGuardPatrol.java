package dkeep.test;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import dkeep.logic.*;

public class TestGuardPatrol {
	char[][] blueprint = {
			{'X','X','X','X','X'},
			{'I',' ',' ',' ','X'},
			{'X',' ',' ',' ','X'},
			{'X','k','P',' ','X'},
			{'X','X','X','X','X'}
	};
	
	Guard guard = new Guard(1, 1, Guard.Personality.ROOKIE,	  "1, 1, r, " + "1, 3, l");
	
	PrisonLevel testLevel = new PrisonLevel(blueprint, guard);
	
	@Test
	public void testBeforeNode() {
		Game testGame = new Game(testLevel);
		
		assertEquals(3, testLevel.getHero().getX());
		assertEquals(2, testLevel.getHero().getY());
		
		testGame.update(Game.Direction.UP);

		assertEquals(true, testGame.gameLost());
	}
	
	@Test
	public void testAfterNode() {
		Game testGame = new Game(testLevel);
		
		assertEquals(3, testLevel.getHero().getX());
		assertEquals(2, testLevel.getHero().getY());
		
		testGame.update(Game.Direction.RIGHT);
		testGame.update(Game.Direction.LEFT);
		testGame.update(Game.Direction.UP);

		assertEquals(true, testGame.gameLost());
	}
}
