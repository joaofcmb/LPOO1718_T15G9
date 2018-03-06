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
		
		while (game.notOver()) {
			// Process input and game logic
			System.out.println("Move Player(P) in a direction (W|A|S|D). End input with RET, first letter processed");
			game.update(playerInput());
			
			// Process output
			System.out.println(game);
		}
		
		// Game End	
		System.out.println("Game Over");
	}

	private static Game.Direction playerInput() {
		String input = inputScanner.nextLine();

		switch(input.charAt(0)) {
		case 'w':
			return Game.Direction.UP;
		case 'a':
			return Game.Direction.LEFT;
		case 's':
			return Game.Direction.DOWN;
		case 'd':
			return Game.Direction.RIGHT;
		default:
			return Game.Direction.NONE;
		}
	}
}
