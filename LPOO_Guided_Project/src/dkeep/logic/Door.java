package dkeep.logic;

import java.io.Serializable;

/*Door class, serves as exit*/

public class Door extends MapEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public Door(int x, int y) {
		super(x, y, 'I');
	}
		
	public void unlock() {
		mapSymbol = 'S';
	}
	
	public boolean isUnlocked() {
		return (mapSymbol == 'S');
	}
}
