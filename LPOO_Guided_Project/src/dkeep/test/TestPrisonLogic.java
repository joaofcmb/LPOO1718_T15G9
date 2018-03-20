package dkeep.test;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import dkeep.logic.*;

// NOTE: X axis goes up to down, Y axis goes left to right
public class TestPrisonLogic { // assures the Prison Level logic is valid
	char[][] blueprint = {
			{'X','X','X','X','X'},
			{'I','P',' ','G','X'},
			{'I',' ',' ',' ','X'},
			{'X','k',' ',' ','X'},
			{'X','X','X','X','X'}
	};
	
	PrisonLevel testLevel = new PrisonLevel(blueprint);
	
	@Test
	public void testMoveHeroFree() {
		Game testGame = new Game(testLevel);
		
		assertEquals(1, testLevel.getHero().getX());
		assertEquals(1, testLevel.getHero().getY());
		
		testGame.update(Game.Direction.DOWN);
		
		assertEquals(2, testLevel.getHero().getX());
		assertEquals(1, testLevel.getHero().getY());
	}
	
	@Test
	public void testMoveHeroWall() {
		Game testGame = new Game(testLevel);
		
		assertEquals(1, testLevel.getHero().getX());
		assertEquals(1, testLevel.getHero().getY());
		
		testGame.update(Game.Direction.UP);
		
		assertEquals(1, testLevel.getHero().getX());
		assertEquals(1, testLevel.getHero().getY());
	}
	
	@Test
	public void testMoveHeroGuard() {
		Game testGame = new Game(testLevel);
		
		assertEquals(1, testLevel.getHero().getX());
		assertEquals(1, testLevel.getHero().getY());
		
		testGame.update(Game.Direction.RIGHT);
		
		assertEquals(true, testGame.gameLost());
	}
	
	@Test
	public void testMoveHeroClosedDoor() {
		Game testGame = new Game(testLevel);
		
		assertEquals(1, testLevel.getHero().getX());
		assertEquals(1, testLevel.getHero().getY());
		
		testGame.update(Game.Direction.LEFT);
		
		assertEquals(1, testLevel.getHero().getX());
		assertEquals(1, testLevel.getHero().getY());
	}
	
	@Test
	public void testMoveHeroLever() {
		Game testGame = new Game(testLevel);
		
		assertEquals(1, testLevel.getHero().getX());
		assertEquals(1, testLevel.getHero().getY());
		
		testGame.update(Game.Direction.DOWN);
		testGame.update(Game.Direction.DOWN);
		
		assertEquals(2, testLevel.getHero().getX());
		assertEquals(1, testLevel.getHero().getY());
		
		assertEquals(true, testLevel.isUnlocked());		
	}
	
	@Test
	public void testMoveHeroOpenDoor() { // also tests player not going through lever
		Game testGame = new Game(testLevel);
		
		assertEquals(1, testLevel.getHero().getX());
		assertEquals(1, testLevel.getHero().getY());
		
		testGame.update(Game.Direction.DOWN);
		testGame.update(Game.Direction.DOWN);
		
		testGame.update(Game.Direction.LEFT);
		
		assertEquals(Game.GameState.NEXT_LEVEL, testGame.getState());
	}
}
