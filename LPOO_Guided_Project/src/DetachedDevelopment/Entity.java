package DetachedDevelopment;

public abstract class Entity {
	
	// Variable for entity position and nextPosition
	
	private char mapSymbol; // Representation on the map layout (Command Line Interface)
	
	public Entity(char symbol) {
		// position = nextPosition = new Position();
		mapSymbol = symbol;
	}
	
	
	/* Updates the Entity's current position.
	 * Use it after calculating the next position and checking its compatibility on the map.
	 */
	public void updatePosition() { 
		// Position nextPosition = position;
	}
	
	
	// No default implementation, depends on entity
	public abstract void calculatePosition();

}
