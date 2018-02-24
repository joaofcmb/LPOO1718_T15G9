package dkeep.logic;

public abstract class GameEntity extends MapEntity {
	public enum Direction {UP, LEFT, DOWN, RIGHT}
		
	public abstract void updatePosition(int x, int y);
	public abstract void move(Direction dir);

	
	public GameEntity(int x, int y, char symbol) {
		super(x, y, symbol);
	}

}
