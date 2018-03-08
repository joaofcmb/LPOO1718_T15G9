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

	protected boolean propTrigger(MapEntity prop) {
		switch(prop.mapSymbol) {
		case 'k': // Lever or Key
			if (prop instanceof Lever) {
				if (this.nextXPos == prop.xPos && this.nextYPos == prop.yPos)
					return true;
			}
			else {
				// change player icon to symbolize it has a key
				prop.mapSymbol = ' ';
				return true;
			}
		case 'I': // Closed door
			if (this.nextXPos == prop.xPos && this.nextYPos == prop.yPos)
				return true;
		case 'S': // Open door
			return true;
		}
		return false;
	}
}
