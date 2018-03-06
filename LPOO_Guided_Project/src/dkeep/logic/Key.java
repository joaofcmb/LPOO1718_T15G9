package dkeep.logic;

public class Key extends MapEntity {
	public Key(int x, int y, char symbol) {
		super(x, y, symbol);
		// TODO Auto-generated constructor stub
	}
	
	//maybe think of a way to remove it instead of hiding it
	public void wasPicked()
	{
		this.mapSymbol = ' ';
	}
}
