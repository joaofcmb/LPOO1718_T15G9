package dkeep.logic;

public class Key extends MapEntity {
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
