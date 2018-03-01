package dkeep.cli;

import java.util.Scanner;

import dkeep.logic.Game;
import dkeep.logic.MapEntity;

public class DungeonKeep {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Game game = new Game();
		game.printMap();

		int state = 1;//0 is victory, 1 is no movement, 2 is capture

		while(state != 0)
		{
			state = game.keyInput(input.next().charAt(0));
			game.printMap();
			if(state == 2)
			{
				System.out.println("You were captured\n");
				return;
			}
		}
		input.close();
		System.out.println("Victory!");
	}

	private MapEntity.Direction playerInput() 
	{
		switch(dir)
		{
		case 'w':
			return MapEntity.Direction.UP;
		case 'a':
			return MapEntity.Direction.LEFT;

		case 's':
			return MapEntity.Direction.DOWN;

		case 'd':
			return MapEntity.Direction.RIGHT;

		default:
			break;
		}
		
	}
}
