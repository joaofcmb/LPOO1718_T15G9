package dkeep.logic;

public class Player extends GameEntity {
	public Player(int x, int y) {
		super(x, y, 'P');
	}
	
	protected boolean entityTrigger(MapEntity entity) {
		if (entity instanceof Guard) {
			return isAdjacent(entity);
		}
		else if (entity instanceof CrazyOgre) { // must check adjacency with club aswell
			return (isAdjacent(entity) || isAdjacent(new MapEntity(((CrazyOgre) entity).getClubX(), 
																   ((CrazyOgre) entity).getClubY(), '*')));
		}
		
		return false;
	}
	
	private boolean isAdjacent(MapEntity entity) {
		return (Math.abs(this.xPos - entity.xPos) + Math.abs(this.yPos - entity.yPos) < 2);
	}

	
	public void pickKey() {
		mapSymbol = 'K';
	}

	public boolean hasKey() {
		return (mapSymbol == 'K');
	}
}
