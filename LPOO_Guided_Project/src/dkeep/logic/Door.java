package dkeep.logic;

/*Door class, serves as exit*/

public class Door extends MapEntity {
	public Door(int x, int y) {
		super(x, y, 'I');
	}
		
	public void unlock() {
		mapSymbol = 'S';
	}
}
