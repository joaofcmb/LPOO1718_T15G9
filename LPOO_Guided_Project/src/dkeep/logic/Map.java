package dkeep.logic;

public abstract class Map {
	protected Game.GameState state = Game.GameState.DEFAULT;
	
	boolean isUnlocked = false;
	
	protected char[][] blueprint = { {'X','X','X'}, {'X',' ','X'}, {'X','X','X'} }; // static layout of map itself (Walls and doors and stuff)
	
	protected Player hero = new Player(1, 1); 
	
	public abstract Game.GameState update(Game.Direction heroDirection);
	public abstract String toString();
	
	protected char[][] copyBlueprint() {
		char[][] map = new char[blueprint.length][];

		for (int i = 0;i < blueprint.length; i++) {
			map[i] = blueprint[i].clone();
		}
		
		return map;
	}
	
	protected void addEntity(char[][] map, MapEntity entity) {
		if (entity == null)	return;
		
		map[entity.getX()][entity.getY()] = entity.getSymbol();
		if (entity instanceof CrazyOgre)
			map[((CrazyOgre) entity).getClubX()][((CrazyOgre) entity).getClubY()] = ((CrazyOgre) entity).getClubSymbol();
	}
	
	protected String matrixToString(char[][] map) {
		String str = "";

		for(char[] line: map) {
			for(char symbol: line) {
				str += symbol + " "; 
			}
			str += "\n";
		}

		return str;
	}
	
	public Player getHero() {
		return hero;
	}
	
	public boolean isUnlocked() {
		return isUnlocked;
	}
	
	public abstract char[][] charMap();
}