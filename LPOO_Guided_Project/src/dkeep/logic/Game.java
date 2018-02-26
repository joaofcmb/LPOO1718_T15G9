package dkeep.logic;

import java.util.ArrayList;
import java.util.Random;

public class Game {
	private Random rand = new Random();

	private ArrayList<MapEntity> enemies = new ArrayList<MapEntity>();
	private ArrayList<MapEntity> staticObjects =  new ArrayList<MapEntity>();
	public ArrayList<Level> levels =  new ArrayList<Level>();
	private ArrayList<Door> doors =  new ArrayList<Door>();

	private Player hero = new Player();
	private Guard guard = new Guard();
	private Lever lever = new Lever();
	private Key key = new Key();

	private boolean hasLever = false, hasKey = false;

	private Level map;

	public Game()
	{
		createLevelsList();
		this.map = levels.get(0);
		initMapObjects();
	}

	/*NullPointerException on line 34*/
	private void createLevelsList() 
	{
		Level p = new PrisonLevel();
		//Level o = new CrazyOgreLevel();
		this.levels.add(0,p);
		//levels.add(o);
	}

	private void initMapObjects() {
		this.staticObjects = new ArrayList<MapEntity>();
		this.enemies = new ArrayList<MapEntity>();
		this.doors = this.map.getDoors();

		this.hero = new Player(this.map.getHero().getX(),
				this.map.getHero().getY(),
				this.map.getHero().getSymbol());
		
		if(this.map.hasGuard())
		{
			this.guard = new Guard(this.map.getGuard().getX(), 
					this.map.getGuard().getY(),
					this.map.getGuard().getSymbol(),
					this.rand.nextInt(3));
			this.enemies.add(guard);
		}
		if(this.map.hasOgre()) {/*
		NEEDS SPECIAL IMPLEMENTATION, FOR WHEN THERE'S MULTIPLE OGRES
			ogre = new Ogre(this.map.getOgre().getX(), 
					this.map.getOgre().getY(),
					this.map.getOgre().getSymbol());*/ 
		}
		
		if(this.map.hasHeroClub())
		{

		}
		
		if(map.hasLever())
		{
			this.lever = new Lever(this.map.getLever().getX(),
					this.map.getLever().getY(),
					this.map.getLever().getSymbol());

			this.staticObjects.add(this.lever);
			this.hasLever = true;
		}
		
		if(map.hasKey())
		{
			this.key = new Key(this.map.getKey().getX(),
					this.map.getKey().getY(),
					this.map.getKey().getSymbol());
			
			this.staticObjects.add(this.key);
			this.hasKey = true;
		}

	}

	private char[][] copyLevelMap()
	{
		int row = this.map.getRows();
		int column = this.map.getColumns();
		char[][] m = new char[row][column];
		
		
		for(int i = 0; i< row; i++)
				m[i] = this.map.getMap()[i].clone();
		return m;
	}
	
	public void printMap() {
		char[][] m = copyLevelMap();
		
		//Objects
		for(MapEntity o: this.staticObjects)
			m[o.getX()][o.getY()] = o.getSymbol();
		
		//Doors
		for(MapEntity d: this.doors)
			m[d.getX()][d.getY()] = d.getSymbol();
		
		//Player
		m[this.hero.getX()][this.hero.getY()] = this.hero.getSymbol();
		
		//Enemies
		for(MapEntity e: this.enemies)
		{
			m[e.getX()][e.getY()] = e.getSymbol();
			//TODO add print of clubs
		}
	}
}
