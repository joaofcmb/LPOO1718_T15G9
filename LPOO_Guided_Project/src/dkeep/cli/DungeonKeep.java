package dkeep.cli;

import java.util.Scanner;

import dkeep.logic.Game;

public class DungeonKeep {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Game game = new Game();
		
		game.printMap();
		
		boolean playing = false;//true;
		
		while(playing)
		{
			char keyInput = input.next().charAt(0);
			//print map
			//input
			//update
			//check state
			
		}
		input.close();
		System.out.println("Victory!");
	}
}
