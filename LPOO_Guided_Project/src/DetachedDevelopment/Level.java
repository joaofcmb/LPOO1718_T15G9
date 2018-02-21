package DetachedDevelopment;

/*
 * 	CLASS - LEVEL (Will replace Map class)
 */

public class Level {
	private char[][] layout; // Matrix with a representation of the map using characters
	
	public Level(char[][] mapLayout) 
	{
		this.layout = mapLayout;
	}
	
	public String toString() {
		String str = "";
	
		for(char[] line: layout) {
			for(char symbol: line) {
				str += symbol + " "; 
			}
			str += "\n";
		}
		
		return str;
	}
}
