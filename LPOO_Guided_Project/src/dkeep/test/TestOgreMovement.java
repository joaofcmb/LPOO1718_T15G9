package dkeep.test;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

import dkeep.logic.*;

public class TestOgreMovement {
	Random random = new Random();
	
	char[][] blueprint = {
			{'X','X','X','X','X'},
			{'I','k',' ',' ','X'},
			{'X',' ',' ',' ','X'},
			{'X','P',' ',' ','X'},
			{'X','X','X','X','X'}
	};
	
	@Test
	public void testUnarmedHero() {
		testOgreMovement(false);
	}
	
	@Test
	public void testArmedHero() {
		testOgreMovement(true);
	}

	private void testOgreMovement(boolean heroIsArmed) {
		CrazyOgre testOgre = new CrazyOgre(1, 3, !heroIsArmed); // Test club only when unarmed, otherwise hard to get a stun to happen
		CrazyOgreLevel testLevel = new CrazyOgreLevel(blueprint, testOgre);
		Game testGame = new Game(testLevel);
		
		if (heroIsArmed)	testLevel.getHero().arm();
		
		
		boolean adjacentOgre = false, adjacentClub = false;
		
		while (!adjacentOgre && !adjacentClub) {
			testGame.update(randomDirection());
			
			int hX = testLevel.getHero().getX();	int hY = testLevel.getHero().getY();
			int oX = testOgre.getX();				int oY = testOgre.getY();
			int cX = testOgre.getClubX();			int cY = testOgre.getClubY();
			if (heroIsArmed) 	cX = cY = -5; // override cX and cY since behavior is undefined when no club is created
			
			
			if (Math.abs(hX - cX) + Math.abs(hY - cY) < 2) {
				adjacentClub = true;
				assertEquals(true, testGame.gameLost());
			}
			else if (Math.abs(hX - oX) + Math.abs(hY - oY) < 2) {
				adjacentOgre = true;
				
				if (heroIsArmed)	assertEquals(true, testOgre.isStunned());
				else				assertEquals(true, testGame.gameLost());
			}
			else if (testGame.gameWon())
				return;
		}
	}

	private Game.Direction randomDirection() {
		switch(random.nextInt(4)) {
		case 0:
			return Game.Direction.UP; 
		case 1:
			return Game.Direction.LEFT;
		case 2:
			return Game.Direction.DOWN;
		case 3:
			return Game.Direction.RIGHT;
		default:
			return null;
		}
	}
}