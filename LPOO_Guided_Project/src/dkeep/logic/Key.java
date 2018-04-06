package dkeep.logic;

public class Key extends MapEntity {
	private static final long serialVersionUID = 1L;

	public Key(int x, int y) {
		super(x, y, 'k');
	}
	
	public void hide() {
		this.mapSymbol = '$';
	}
	
	public void uncover() {
		this.mapSymbol = 'k';
	}

}
