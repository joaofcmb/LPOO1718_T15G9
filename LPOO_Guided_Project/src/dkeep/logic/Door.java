package dkeep.logic;

/*Door class, serves as exit*/

public class Door extends MapEntity {
	boolean isLocked = true;
	
	public Door(int x, int y, char symbol) {
		super(x, y, symbol);
	}
	public int getX() { return this.xPos;}
	
	public int getY() { return this.yPos;}
	
	public char getSymbol() { return this.mapSymbol;}
	
	public void unlock() 
	{
		isLocked = false;
		mapSymbol = 'S';
	}
}
