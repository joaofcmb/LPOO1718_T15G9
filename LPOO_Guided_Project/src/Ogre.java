import java.util.Random;

public class Ogre {
	private static Position.Direction direction = Position.Direction.NONE;
	public static Position position = new Position(1, 4);
	public  static Position nextPosition = new Position();
	private static char letter = 'O';
	
	private static char club = '*';
	public static Position clubPos = new Position(2, 4);
	public static Position nextClubPos = new Position();
	
	private static Random ogreRand = new Random(), clubRand = new Random();

	public static void updatePosition() 
	{
		while(true) {
			//choose direction randomly 
			int  n = ogreRand.nextInt(4);
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
			switch(nextPosition.tile(Map.mapLevel2))
			{
			case ' ':
			{
				if(letter == '$') {
					
				letter = 'O';
				}
				return;

			}
			case 'k':
			{
				letter = '$';
				return;
			}
			}
		}
	}
	
	public static void updateClub() {
		while(true) {
			//choose direction randomly 
			int  n = clubRand.nextInt(4);
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
			nextClubPos = nextPosition.move(direction);
			switch(nextClubPos.tile(Map.mapLevel2))
			{
			case ' ':
			{
				if(club == '$') {
					
					club = '*';
				}
				return;

			}
			case 'k':
			{
				club = '$';
				return;
			}
			}
		}
	}
	
	public static char getLetter() {
		return letter;
	}
	public static char getClubLetter() {
		return club;
	}
}