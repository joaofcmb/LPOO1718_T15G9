import java.util.Random;

public class Club {
	private static Position.Direction direction = Position.Direction.NONE;
	private static Position nextPosition = new Position();

	public Position position;
	
	private static char letter = '*';
	private static Random random = new Random();

	public Club()
	{
		position = new Position(1,5);
	}

	public void updatePosition(Position wielderPosition)
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
			nextPosition = wielderPosition.move(direction);
			switch(nextPosition.tile(Map.current))
			{
			case ' ':
			{
				if(letter == '$')
					letter = '*';
				return;
			}
			case 'k':
				letter = '$';
			}
		}
	}
	public char getLetter() {
		return letter;
	}
}
