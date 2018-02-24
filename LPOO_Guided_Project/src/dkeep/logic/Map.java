package dkeep.logic;

public class Map {
	private static char[][] layout; // keeps track of GameEntities
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
			layout = blueprint = startPrison;
			break;
		case 1:
			layout = blueprint = crazyOgreRoom;
			break;
		}
	}

	public static boolean validTile(int x, int y) {
		switch(tileOn(x, y)) {
		case ' ':
			return true;
		default:
			return false;
		}
	}
	
	public static void setTile(int x, int y, char symbol) {
		layout[x][y] = symbol;
	}
	
	public static void updateTile(int sourceX, int sourceY, int destX, int destY) {
		char symbol = layout[sourceX][sourceY];
		
		layout[sourceX][sourceY] = blueprint[sourceX][sourceY];
		
		layout[destX][destY] = symbol;
	}

	private static char tileOn(int x, int y) {
		return layout[x][y];
	}

	/*
	 * Map Layouts
	 */
	private static char[][] startPrison = {
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X','P',' ',' ','I',' ','X',' ','G','X'},
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
			{'I',' ',' ',' ',' ',' ',' ','k','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X'}
	};

}