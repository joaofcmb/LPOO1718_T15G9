package DetachedDevelopment;

import dkeep.logic.MapEntity;

public abstract class AbstractLevel {

	private MapEntity[] entities;
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
}
