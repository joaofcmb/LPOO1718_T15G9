package dkeep.logic;

/*
 * CLASS - MAP - Stores current map info
 * 
 * 
 * * * * *
 * WARNING:
 * 
 * Transpose coordinates (Entity(x, y) == layout[y][x])
 * * * * *
 */

public class Map {
	private static char[][] layout; // keeps track of GameEntities and has fixed MapEntities on it
	private static char[][] blueprint; // static layout of map itself

	public static String layoutString() {
		String str = "";

		for(char[] line: layout) {
			for(char symbol: line) {
				str += symbol + " "; 
			}
			str += "\n";
		}

		return str;
	}

	public static void changeMap(int index) {
		switch(index) {
		case 0:
				setupMap(startPrison);
			break;
		case 1:
				setupMap(crazyOgreRoom);	
			break;
		}
	}

	/*
	 * FUNCTION: validTile - checks if a give tile at the current map is valid for movement (X, Y coordinates)
	 * Returns true if valid, false otherwise.
	 */
	
	public static boolean validTile(int x, int y) {
		switch(layout[y][x]) {
		case ' ':
			return true;
		default:
			return false;
		}
	}
	
	
	public static void setTile(int x, int y, char symbol) {
		layout[y][x] = symbol;
	}
	
	public static void updateTile(int sourceX, int sourceY, int destX, int destY) {
		char symbol = layout[sourceY][sourceX];
		
		layout[sourceY][sourceX] = blueprint[sourceY][sourceX];
		
		layout[destY][destX] = symbol;
	}

	
	
	private static void setupMap(char[][] map) {
		blueprint = map; // assign map to blueprint since blueprint is constant
		
		// make copy of map to layout
		layout = new char[map.length][];
		
		for (int i = 0; i < map.length; i++) {
			layout[i] = map[i].clone();
		}
	}
	
	/*
	 * Map Layouts
	 */
	private static char[][] startPrison = {
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X',' ',' ',' ','I',' ','X',' ',' ','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'X',' ','I',' ','I',' ','X',' ',' ','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X',' ','X','X','X','X',' ','X'},
			{'X',' ','I',' ','I',' ','X','k',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}
	};

	private static char[][] crazyOgreRoom = {
			{'X','X','X','X','X','X','X','X','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X'}
	};

}