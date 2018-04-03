package dkeep.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dkeep.logic.CrazyOgreLevel;
import dkeep.logic.Game;

public class TestOgreLogic {

	char[][] blueprint = {
			{'X','X','X','X','X'},
			{'X','P',' ','T','X'},
			{'I',' ',' ',' ','X'},
			{'X','k',' ',' ','X'},
			{'X','X','X','X','X'}
	};
	
	CrazyOgreLevel testLevel = new CrazyOgreLevel(blueprint);
	
	@Test
	public void testMoveHeroWall() {
		Game testGame = new Game(testLevel);
		
		assertEquals(1, testLevel.getHero().getX());
		assertEquals(1, testLevel.getHero().getY());
		
		testGame.update(Game.Direction.LEFT);
		
		assertEquals(1, testLevel.getHero().getX());
		assertEquals(1, testLevel.getHero().getY());
	}
	
	@Test
	public void testMoveHeroOgre() {
		Game testGame = new Game(testLevel);
		
		assertEquals(1, testLevel.getHero().getX());
		assertEquals(1, testLevel.getHero().getY());
		
		testGame.update(Game.Direction.RIGHT);
		
		assertEquals(true, testGame.gameLost());
	}
	
	@Test
	public void testMoveHeroKey() {
		Game testGame = new Game(testLevel);
		
		assertEquals(1, testLevel.getHero().getX());
		assertEquals(1, testLevel.getHero().getY());
		
		testGame.update(Game.Direction.DOWN);
		testGame.update(Game.Direction.DOWN);
		
		assertEquals(3, testLevel.getHero().getX());
		assertEquals(1, testLevel.getHero().getY());
		
		assertEquals('K', testLevel.getHero().getSymbol());
	}
	
	@Test
	public void testMoveHeroClosedDoor() {
		Game testGame = new Game(testLevel);
		
		assertEquals(1, testLevel.getHero().getX());
		assertEquals(1, testLevel.getHero().getY());
		
		testGame.update(Game.Direction.DOWN);
		testGame.update(Game.Direction.LEFT);
		
		assertEquals(2, testLevel.getHero().getX());
		assertEquals(1, testLevel.getHero().getY());
	}
	
	@Test
	public void testHeroUnlockDoor() {
		Game testGame = new Game(testLevel);
		
		assertEquals(1, testLevel.getHero().getX());
		assertEquals(1, testLevel.getHero().getY());
		
		testGame.update(Game.Direction.DOWN);
		testGame.update(Game.Direction.DOWN);
		
		testGame.update(Game.Direction.UP);
		testGame.update(Game.Direction.LEFT);
		
		assertEquals(2, testLevel.getHero().getX());
		assertEquals(1, testLevel.getHero().getY());
		
		testGame.update(Game.Direction.LEFT);
		assertEquals(true, testLevel.isUnlocked());
	}
	
	@Test
	public void testHeroOpenDoor() {
		Game testGame = new Game(testLevel);
		
		assertEquals(1, testLevel.getHero().getX());
		assertEquals(1, testLevel.getHero().getY());
		
		testGame.update(Game.Direction.DOWN);
		testGame.update(Game.Direction.DOWN);
		
		testGame.update(Game.Direction.UP);
		testGame.update(Game.Direction.LEFT);
		testGame.update(Game.Direction.LEFT);
		
		assertEquals(Game.GameState.VICTORY, testGame.getState());
	}
	
	@Test
	public void testToString() {
		Game testGame = new Game(testLevel);
		
		String mapString =	"X X X X X \n" +
						  	"X P   O X \n" +
						  	"I       X \n" +
						  	"X k     X \n" +
						  	"X X X X X \n";
		assertEquals(mapString, testGame.toString());
		
		testGame.update(Game.Direction.DOWN);
		
		mapString =			"X X X X X \n" +
			  				"X     O X \n" +
			  				"I P     X \n" +
			  				"X k     X \n" +
			  				"X X X X X \n";
		assertEquals(mapString, testGame.toString());
	}
}
