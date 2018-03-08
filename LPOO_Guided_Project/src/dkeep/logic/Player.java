package dkeep.logic;


public class Player extends GameEntity {
	public Player(int x, int y) {
		super(x, y, 'P');
	}
	
	protected boolean entityTrigger(MapEntity entity) {
		if (entity instanceof Guard) {
			if (Math.abs(this.xPos - entity.xPos) + Math.abs(this.yPos - entity.yPos) < 2)
				return true;
		}
		
		return false;
	}
}
