package dkeep.logic;

public abstract class GameEntity extends MapEntity {
	public abstract void updatePosition(int x, int y);
	public abstract void move(Game.Direction dir);

	
	public GameEntity(int x, int y, char symbol) {
		super(x, y, symbol);
	}

}
