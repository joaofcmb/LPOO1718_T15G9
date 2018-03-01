package dkeep.logic;

import java.util.ArrayList;
import java.util.Random;

import dkeep.logic.MapEntity.Direction;

public class Game {
	private Random rand = new Random();

	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<MapEntity> staticObjects =  new ArrayList<MapEntity>();
	public ArrayList<Level> levels =  new ArrayList<Level>();
	private ArrayList<Door> doors =  new ArrayList<Door>();

	private Player hero = new Player();
	private Guard guard = new Guard();
	private Ogre ogre = new Ogre(); //temporary
	private Lever lever = new Lever();
	private Key key = new Key();

	private Level map;
	private int levelInt;

	public Game()
	{
		createLevelsList();
		levelInt = 0;
		this.map = levels.get(0);
		initMapObjects();
	}

	private void createLevelsList() 
	{
		Level p = new PrisonLevel();
		Level o = new CrazyOgreLevel();
		this.levels.add(0,p);
		this.levels.add(1,o);
	}

	private void initMapObjects() {
		this.staticObjects = new ArrayList<MapEntity>();
		this.enemies = new ArrayList<Enemy>();
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
		if(this.map.hasOgre())
		{
			this.ogre = new Ogre(6,2,'O');
			this.enemies.add(ogre);
		}/*
		NEEDS SPECIAL IMPLEMENTATION, FOR WHEN THERE'S MULTIPLE OGRES
			ogre = new Ogre(this.map.getOgre().getX(), 
					this.map.getOgre().getY(),
					this.map.getOgre().getSymbol());*/ 

		if(this.map.hasHeroClub())
		{

		}

		if(map.hasLever())
		{
			this.lever = new Lever(this.map.getLever().getX(),
					this.map.getLever().getY(),
					this.map.getLever().getSymbol());

			this.staticObjects.add(this.lever);
		}

		if(map.hasKey())
		{
			this.key = new Key(this.map.getKey().getX(),
					this.map.getKey().getY(),
					this.map.getKey().getSymbol());

			this.staticObjects.add(this.key);
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

		for(int i=0; i<m.length;i++) {
			for(int j=0;j<m[i].length;j++)
				System.out.print(m[i][j] + " ");
			System.out.println();
		}
	}

	private boolean nextLevel()
	{
		this.levelInt++;
		if(this.levelInt < this.levels.size())
		{
			this.map = this.levels.get(levelInt);
			initMapObjects();
			return true;
		}
		return false;
	}

	public int keyInput(MapEntity.Direction dir) 
	{
		int state = 1;
		state = update(dir);


		if(state == 1 || state == 0)
			return state;
		//move enemies and check if captured
		enemyMovement();
		if(playerWasCaptured()) return 2;
		interactions();
		return 3;
	}
	/*update() returns:
	 * 1 if can't move
	 * 2 if lost game (captured)
	 * 0 if won
	 * 3 if move
	 * */

	private int update(Direction direction) {
		int heroX = this.hero.getX();
		int heroY = this.hero.getY();

		switch(direction)
		{
		case UP:
			heroX--;
			break;
		case LEFT:
			heroY--;
			break;
		case DOWN:
			heroX++;
			break;
		case RIGHT:
			heroY++;
			break;
		}

		for(Door d: this.doors)
		{
			if(d.getX() == heroX && d.getY() == heroY) {
				if(d.isLocked) {
					if(this.hero.hasKey()) {
						d.unlock();
						return 1;
					}
					return 1;
				}
				else
				{
					this.hero.move(direction);
					if (!nextLevel())
						return 0;
					return 1;
				}
			}
		}

		if(!(this.map.isValidPosition(heroX,heroY)))
			return 1;

		this.hero.move(direction);
		return 3;
	}

	private void enemyMovement()
	{
		for(Enemy foe : this.enemies)
			foe.move();
	}

	private boolean playerWasCaptured()
	{
		for(Enemy foe : this.enemies)
		{
			if(samePosition(this.hero, foe))
				return true;

			if(foe.getClass() == this.guard.getClass()  && nearEnemy(foe))
				return true;
			// TODO club cases if(this.hero.hasClub && )
		}
		return false;
	}

	private boolean nearEnemy(Enemy foe)
	{
		return Math.abs(foe.getX() - this.hero.getX()) + Math.abs(foe.getY() - this.hero.getY()) <= 1;
	}

	private void interactions()
	{	
		//lista staticobjj percorrer
		interactionHeroLever();
		interactionHeroKey();
		//TODO ogrekey clubkey
		//HeroClub
	}

	private void interactionHeroLever()
	{
		if(samePosition(this.hero, this.lever))
			for(Door d: this.doors)
				d.unlock();
	}

	private void interactionHeroKey()
	{
		if(this.map.hasKey() && samePosition(this.hero, this.key))
		{
			this.hero.pickKey();
			this.key.wasPicked();
		}
	}

	private boolean samePosition(MapEntity a, MapEntity b)
	{
		return a.getX() == b.getX() && a.getY() == b.getY();
	}
}
