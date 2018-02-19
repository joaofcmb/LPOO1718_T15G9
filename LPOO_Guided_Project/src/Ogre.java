import java.util.Random;

public class Ogre {
	private static Position.Direction direction = Position.Direction.NONE;
	public static Position position = new Position(1, 4);
	public  static Position nextPosition = new Position();

	private static Random random = new Random();

	public static void updatePosition() 
	{
		while(true) {
			//choose direction randomly 
			int  n = random.nextInt(4);
			switch(n) {
			case 0:
			{
				direction=Position.Direction.LEFT;
				break;
			}
			case 1:
			{
				direction=Position.Direction.RIGHT;
				break;
			}
			case 2:
			{
				direction=Position.Direction.UP;
				break;
			}
			case 3:
			{
				direction=Position.Direction.DOWN;
				break;
			}
			}
			
			//check if possible to move in that direction
			nextPosition = position.move(direction);
			if(nextPosition.tile(Map.mapLevel2) == ' ')
			{
				return;
			}
		}
	}
}
