package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.*;

public class TestGuardPatrol {
	char[][] blueprint = {
			{'X','X','X','X','X'},
			{'I',' ',' ',' ','X'},
			{'X',' ',' ',' ','X'},
			{'X','k','P',' ','X'},
			{'X','X','X','X','X'}
	};
	
	@Test
	public void testBeforeNode() {
		Guard guard = new Guard(1, 1, Game.Personality.ROOKIE,	  "1, 1, r, " + "1, 3, l");
		PrisonLevel testLevel = new PrisonLevel(blueprint, guard);
		Game testGame = new Game(testLevel);
		
		assertEquals(3, testLevel.getHero().getX());
		assertEquals(2, testLevel.getHero().getY());
		
		testGame.update(Game.Direction.UP);

		assertEquals(true, testGame.gameLost());
	}
	
	@Test
	public void testAfterNode() {
		Guard guard = new Guard(1, 1, Game.Personality.ROOKIE,	  "1, 1, r, " + "1, 3, l");
		PrisonLevel testLevel = new PrisonLevel(blueprint, guard);
		Game testGame = new Game(testLevel);
		
		assertEquals(3, testLevel.getHero().getX());
		assertEquals(2, testLevel.getHero().getY());
		
		testGame.update(Game.Direction.RIGHT);
		testGame.update(Game.Direction.LEFT);
		testGame.update(Game.Direction.UP);

		assertEquals(true, testGame.gameLost());
	}
	
	@Test
	public void testSuspicious() {
		Guard guard = new Guard(1, 1, Game.Personality.SUSPICIOUS,	  "1, 1, r, " + "1, 3, l");
		PrisonLevel testLevel = new PrisonLevel(blueprint, guard);
		Game testGame = new Game(testLevel);
		
		int gX = 1; int[] gY = {1, 2, 3, 2}; 
		int guardIndex = 0, indexModifier = 1;
		for (int turnCount = 0; turnCount < 50; turnCount++) {
			// Keep the Player going back and forth without going near the guard
			testGame.update(turnCount % 2 == 0 ? Game.Direction.RIGHT : Game.Direction.LEFT);
			guardIndex += indexModifier;
			
			assertEquals(gX, guard.getX());
			assertEquals(gY[Math.abs(guardIndex % 4)], guard.getY());
			
			indexModifier = guard.hasInverted() ? -1 : 1;
		}
	}
}
