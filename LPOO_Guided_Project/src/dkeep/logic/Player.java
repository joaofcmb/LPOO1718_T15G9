package dkeep.logic;

public class Player extends GameEntity {
	private boolean isArmed = false;
	
	public Player(int x, int y) {
		super(x, y, 'P');
	}
	
	protected boolean entityTrigger(MapEntity entity) {
		if (entity instanceof Guard) {
			return (isAdjacent(entity) && entity.mapSymbol == 'G');
		}
		else if (entity instanceof CrazyOgre) { // must check adjacency with club aswell
			if (isAdjacent(new MapEntity(((CrazyOgre) entity).getClubX(), ((CrazyOgre) entity).getClubY(), '*'))) {
				return true;
			}
			else if (isAdjacent(entity)) {
				if (isArmed) {
					// Stun the Ogre
					((CrazyOgre) entity).stun();
				}
				else return true;
			}
		}
		return false;
	}
	
	private boolean isAdjacent(MapEntity entity) {
		return (Math.abs(this.xPos - entity.xPos) + Math.abs(this.yPos - entity.yPos) < 2);
	}

	// Key Interactions
	
	public void pickKey() {
		mapSymbol = 'K';
	}

	public boolean hasKey() {
		return (mapSymbol == 'K');
	}

	// Weapon Interactions
	
	public void arm() {
		isArmed = true;
		mapSymbol = 'A';
	}
}
