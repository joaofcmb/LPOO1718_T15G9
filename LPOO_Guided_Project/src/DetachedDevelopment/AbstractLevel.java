package DetachedDevelopment;

public abstract class AbstractLevel {

	private Entity[] entities;
	private char[][] layout; // Matrix with a representation of the map using characters
	
	private void OnLevelStart() {
		// Setup Start positions for entities
	}
	
	private void OnLevelExit() {
		// Perform certain actions before leaving level
	}
	
	public AbstractLevel(char[][] mapLayout) 
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
