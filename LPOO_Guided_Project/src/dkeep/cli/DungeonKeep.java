package dkeep.cli;

import java.util.Scanner;

import dkeep.logic.Game;

public class DungeonKeep {
	private static Scanner inputScanner; 

	public static void main(String[] args) {
		// Game Startup
		inputScanner = new Scanner(System.in);
		Game game = new Game();

		// Game Loop
		
		// Process output
		System.out.println(game);
		
		while (!game.gameLost() && !game.gameWon()) {
			// Process input and game logic
			System.out.println("Move Player(P) in a direction (W|A|S|D). End input with RET, first letter processed");
			game.update(playerInput());
			
			// Process output
			System.out.println(game);
		}
		
		// Game End
		if (game.gameLost())
			System.out.println("Game Over");
		else
			System.out.println("Victory");
	}

	private static Game.Direction playerInput() {
		if (!inputScanner.hasNext())	return null;

		switch(inputScanner.nextLine().charAt(0)) {
		case 'w':
			return Game.Direction.UP;
		case 'a':
			return Game.Direction.LEFT;
		case 's':
			return Game.Direction.DOWN;
		case 'd':
			return Game.Direction.RIGHT;
		default:
			return null;
		}
	}
}
