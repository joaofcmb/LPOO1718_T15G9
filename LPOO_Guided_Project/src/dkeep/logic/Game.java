package dkeep.logic;
import java.io.Serializable;

/**
 * Game is the main class of the Logic Component.
 * A Game object represents a specific game logic status, as it manages the overall state of the game,
 * as well as the changes between levels.
 * <p>
 * The Game is, therefore, the upper most layer of the Logic component, through which most of the interface is established
 * with the other components (The Test component remains as an exception, since it is meant to test the behaviors of the various
 * classes of Logic).
 * 
 */
public class Game implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Stores the various possible personalities of guards in the Prison Level
	 */
	public enum Personality {
		/**
		 * Default personality (Guard follows patrol as normal)
		 */
		ROOKIE,
		
		/**
		 * Guard may fall asleep during patrol
		 */
		DRUNK,
		
		/**
		 * Guard may invert its patrol, from time to time.
		 */
		SUSPICIOUS, 
		
		/**
		 * Guard doesn't move (Used for testing purposes)
		 */
		STATIC
	}
	
	/**
	 * Stores the various possible states the game can be in
	 */
	public enum GameState {
		/**
		 * Default State (no particular event)
		 */
		DEFAULT, 
		
		/**
		 * The current level has been finished and the next level is to be loaded
		 */
		NEXT_LEVEL, 
		
		/**
		 * The game has ended with the user having lost
		 */
		GAME_OVER, 
		
		/**
		 * The game has ended with the user having won
		 */
		VICTORY
	}
	public enum Direction {UP, LEFT, DOWN, RIGHT}
	
	private GameState state = GameState.DEFAULT;
	
	private Map map;
	private char[][] customMap;
	
	private int nOgres = 1;
	
	/**
	 * Starts the game with a certain type of map object. Mostly used by test classes to start a game with a specific map,
	 * set with a specific configuration.
	 * 
	 * @param map		the map to be loaded into the game
	 * @see 			Map
	 */
	public Game(Map map) {
		this.map = map;
	}
	
	/**
	 * Default Constructor
	 * <p>
	 * Starts the game with the PrisonLevel default constructor as the initial map.
	 * The flow of levels throughout the game, managed by the class, has been laid out with this map (PrisonLevel)
	 * featured as the first level.
	 */
	public Game() {
		this(new PrisonLevel());
	}
	
	/**
	 * Starts the game in the same manner as the Default Constructor, but with certain parameters (Guard
	 * personality, number of Ogres and a custom Crazy Ogre Map).
	 * 
	 * @param nOgres		number of Ogres to be spawned in the Crazy Ogre Level.
	 * @param type			type of personality the guard at the Prison Level should have.
	 * @param customMap		matrix with a custom layout for the Crazy Ogre Level. If <code> null </code>, the default layout is used 
	 * 
	 * @see Personality
	 */
	public Game(int nOgres, Personality type, char[][] customMap) {
		this.customMap = customMap;
		this.nOgres = nOgres;
		this.map = new PrisonLevel(type);
	}
	
	private void nextLevel() {
		if (map instanceof PrisonLevel)
			map = customMap != null ? new CrazyOgreLevel(customMap, nOgres) : new CrazyOgreLevel(nOgres);
		else if (map instanceof CrazyOgreLevel)
			state = GameState.VICTORY;
	}
	
	/**
	 * Determines if the game has been lost
	 * 
	 * @return <code> true </code> if the game has been lost, <code> false </code> if not.
	 */
	public boolean gameLost() {
		return (state == GameState.GAME_OVER);
	}
	
	/**
	 * Determines if the game has been won
	 * 
	 * @return <code> true </code> if the game has been won, <code> false </code> if not.
	 */
	public boolean gameWon() {
		return (state == GameState.VICTORY);
	}
	
	/**
	 * Progresses one tick/turn of the game logic
	 * 
	 * @param 	heroDirection		Direction where the hero is meant to move.
	 * @return	<code> 0 </code> if the heroDirection isn't <code> null </code>. <code> -1 </code> otherwise.
	 */
	public int update(Direction heroDirection) {
		if (heroDirection == null)
			return -1;
		
		// tell map to process its entities
		state = map.update(heroDirection);
		
		if (state == GameState.NEXT_LEVEL)
			nextLevel();
		
		return 0;
	}
	
	/**
	 * Returns a visual representation of the current game state for the CLI, as a string
	 * 
	 * @return 	A string with a visual representation of the game.
	 */
	public String toString() {
		return map.toString();
	}

	/**
	 * Returns the current game state.
	 * 
	 * @return 	current state of the game.
	 * @see 	GameState
	 */
	public GameState getState() {return state;}
	
	/**
	 * Returns the current game map.
	 * 
	 * @return 	current map in the game.
	 * @see 	Map
	 */
	public Map getMap() {return map;}
}
