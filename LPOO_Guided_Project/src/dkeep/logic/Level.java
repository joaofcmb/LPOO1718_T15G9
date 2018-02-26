package dkeep.logic;

import java.util.ArrayList;

public abstract class Level {
	
	public abstract Player getHero();	

	public abstract ArrayList<Door> getDoors();

	public abstract boolean hasGuard();
	public abstract Guard getGuard();

	public abstract boolean hasOgre();
	public abstract Ogre getOgre();

	public abstract boolean hasLever();
	public abstract Lever getLever();

	public abstract boolean hasKey();
	public abstract Key getKey();

	public abstract boolean hasHeroClub();

	public abstract char[][] getMap();
	public abstract int getRows();
	public abstract int getColumns();

}
