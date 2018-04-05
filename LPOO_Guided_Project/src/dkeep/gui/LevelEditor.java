package dkeep.gui;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class LevelEditor {
	
	//Constants for Panel Position
	private static final int XPANEL = 150;
	private static final int YPANEL = 10;
		
	private JFrame frame;
	private JLabel lblInstruction;
	private JButton btnHero, btnDoor, btnOgre, btnKey, btnQuit, btnWall, btnSaveCustomMap;
	private JPanel graphics;

	private int width=10, height=10, nOgres;
	private char selection = ' ';
	private char[][] customMap;
	private int  nOgresPlaced, nHeroesPlaced, nWallsPlaced, nDoorsPlaced, nKeysPlaced;


	public LevelEditor(int w, int h, int o) {
		width = w;
		height = h;
		o = nOgres;
		customMap = new char[width][height];
		initialise();
	}

	private void initialise() {
		initFrame();
		initMap();
		initOtherObjects();
		initButtons();
		addToContentPane();
	}

	private void initFrame() {
		frame = new JFrame("Level Editor");
		int y = height*LevelEditorGraphics.SIZE;
		if(y < 500) y = 500;
		frame.setBounds(100, 100,XPANEL + width*LevelEditorGraphics.SIZE + 10, YPANEL + y + 50);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}

	private void initMap()
	{
		for(int i = 0; i < customMap.length; i++)
			for(int j = 0; j < customMap[i].length; j++)
				customMap[i][j] = ' ';
	}

	private void initOtherObjects() {
		graphics = new LevelEditorGraphics(width, height,this);
		graphics.setLayout(null);
		graphics.setBounds(150, 10, width*LevelEditorGraphics.SIZE, height*LevelEditorGraphics.SIZE);
		graphics.setBackground(Color.GRAY);

		lblInstruction = new JLabel("<html>Select an element.</html>");
		lblInstruction.setHorizontalAlignment(SwingConstants.LEFT);
		lblInstruction.setFont(new Font("Courier New", Font.PLAIN, 17));
		lblInstruction.setBounds(10, 10, 156, 141);

		initKeys();
	}

	private void initButtons() {
		initBtnHero();
		initBtnDoor();
		initBtnWall();
		initBtnOgre();
		initBtnKey();
		initBtnQuit();
		initBtnSaveCustomMap();
	}

	private void initBtnHero()
	{
		btnHero = new JButton("1. Hero");
		btnHero.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnHero.setBounds(10, 162, 116, 32);
		btnListener(btnHero, 'A', "the Hero");
	}

	private void initBtnDoor()
	{
		btnDoor = new JButton("5. Door");
		btnDoor.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnDoor.setBounds(10, 333, 116, 32);
		btnListener(btnDoor, 'I', "a Door");
	}
	private void initBtnWall()
	{
		btnWall = new JButton("2. Wall");
		btnWall.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnWall.setBounds(10, 204, 116, 32);
		btnListener(btnWall, 'X', "a Wall");
	}

	private void initBtnOgre()
	{
		btnOgre = new JButton("3. Ogre");
		btnOgre.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnOgre.setBounds(10, 246, 116, 32);
		btnListener(btnOgre, 'O', "an Ogre");

	}
	private void initBtnKey()
	{
		btnKey = new JButton("4. Key");
		btnKey.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnKey.setBounds(10, 291, 116, 32);
		btnListener(btnKey, 'k', "a Key");

	}
	private void initBtnQuit()
	{
		btnQuit = new JButton("QUIT");
		btnQuit.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnQuit.setBounds(10, 477, 116, 32);
		btnQuit.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						quitToMainMenu();}
				});
	}

	private void initBtnSaveCustomMap()
	{
		btnSaveCustomMap = new JButton("<html>Save Custom Map</html>");
		btnSaveCustomMap.setVerticalAlignment(SwingConstants.TOP);
		btnSaveCustomMap.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnSaveCustomMap.setBounds(10, 383, 116, 63);

		btnSaveCustomMap.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(checkElements())
						{	
							//TODO save cMap with nOgres
						}
					}
				});
	}

	private void btnListener(JButton btn, char symbol, String name)
	{
		btn.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						selection  = symbol;
						setInstruction("Choose where to place " + name +".");
					}
				});
	}

	private void initKeys()
	{
		graphics.addKeyListener(
				new KeyListener() {
					@Override
					public void keyReleased(KeyEvent k) {
						switch(k.getKeyCode())
						{
						case KeyEvent.VK_1: {//Hero
							selection  = 'A';
							setInstruction("Choose where to place the Hero.");
							break;
						}
						case KeyEvent.VK_2: {//Wall
							selection  = 'X';
							setInstruction("Choose where to place a Wall.");
							break;
						}
						case KeyEvent.VK_3: {//Ogre
							selection  = 'O';
							setInstruction("Choose where to place an Ogre.");
							break;
						}
						case KeyEvent.VK_4: {//Key
							selection  = 'k';
							setInstruction("Choose where to place a Key.");
							break;
						}
						case KeyEvent.VK_5: {//Door
							selection  = 'I';
							setInstruction("Choose where to place a Key.");
							break;
						}
						case KeyEvent.VK_ESCAPE: {
							quitToMainMenu();
							break;
						}
						default:
						}
					}

					@Override
					public void keyPressed(KeyEvent k) {}

					@Override
					public void keyTyped(KeyEvent k) {}
				});
	}

	protected void quitToMainMenu() {
		MainMenu menu = new MainMenu();
		menu.getFrame().setVisible(true);
		menu.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.dispose();
	}

	private void addToContentPane()
	{	
		JPanel graphics1 = new JPanel();
		graphics1.setLayout(null);
		graphics1.setBounds(150, 10, 565, 585);
		graphics1.setBackground(Color.GRAY);
		frame.getContentPane().add(graphics);

		frame.getContentPane().add(btnHero);	
		frame.getContentPane().add(btnDoor);
		frame.getContentPane().add(btnOgre);
		frame.getContentPane().add(btnKey);		
		frame.getContentPane().add(btnQuit);
		frame.getContentPane().add(btnWall);

		frame.getContentPane().add(btnSaveCustomMap);

		frame.getContentPane().add(lblInstruction);
	}

	public void mouseHandler(int i, int j) {
		if (customMap[i-1][j-1] == ' ' && selection != ' ') //if cell is empty
		{
			customMap[i-1][j-1] = selection;
			checkElement(i-1,j-1, 1);
		}
		else if (customMap[i-1][j-1]  == selection) // if you select the wrong cell
		{
			checkElement(i-1,j-1, -1);
			customMap[i-1][j-1] = ' ';
		}
		else if (customMap[i-1][j-1]  != ' ') //if you want to replace a cell
		{
			checkElement(i-1,j-1, -1);
			customMap[i-1][j-1] = selection;	
			checkElement(i-1,j-1,1);
		}
	}
	
	public void checkElement(int x, int y, int inc){
		if (customMap[x][y] == 'O')
			nOgresPlaced += inc;
		else if (customMap[x][y] == 'A')
			nHeroesPlaced+= inc;
		else if (customMap[x][y] == 'k')
			nKeysPlaced+= inc;
		else if (customMap[x][y] == 'X')
			nWallsPlaced+= inc;
		else if(customMap[x][y] == 'I')
			nDoorsPlaced+= inc;
	}

	public boolean checkElements(){
		if ( nOgresPlaced == 0 || nHeroesPlaced == 0 || nDoorsPlaced == 0 || nWallsPlaced == 0 || nKeysPlaced == 0){
			setInstruction("There's, at least, a missing element to the map!");
			return false;
		}		
		return checkNumberOfElements();
	}

	public boolean checkNumberOfElements(){
		if (nOgresPlaced > 1){
			setInstruction("Place only ONE Ogre");
			return false;
		}	
		if (nHeroesPlaced > 1){
			setInstruction("Place only ONE Hero");
			return false;
		}	

		if (nKeysPlaced > 1){
			setInstruction("Place only ONE Key");
			return false;
		}		

		if (nDoorsPlaced > 1){
			setInstruction("Place only ONE Door");
			return false;
		}	
		return true;
	}

	public boolean checkCorners(){
		if (customMap[0][0] == 'X' || customMap[width-1][0] == 'X' || customMap[0][height-1] == 'X' || customMap[width-1][height-1] == 'X')
			return true;
		return false;
	}

	public boolean checkBorders(){
		if (checkUpperBorder() && checkLowerBorder() && checkLeftBorder() && checkRightBorder())
			return true;		
		setInstruction("Borders must have only doors and walls");	
		return false;
	}

	public boolean checkUpperBorder(){
		for(int i = 0; i < width;i++)
			if (!checkUpperCell(i))
				return false;	
		return true;
	}

	public boolean checkUpperCell(int i){
		if(customMap[i][0] == 'X'|| customMap[i][0] == 'I')
			return true;
		return false;
	}

	public boolean checkLowerBorder(){
		for(int i = 0; i < width;i++)
			if (!checkLowerCell(i))
				return false;
		return true;
	}

	public boolean checkLowerCell(int i){
		if(customMap[i][0] == 'X'|| customMap[i][0] == 'I')
			return true;
		return false;
	}

	public boolean checkLeftBorder(){
		for(int j = 0; j < height;j++)
			if (!checkLeftCell(j))
				return false;	
		return true;
	}

	public boolean checkLeftCell(int j){
		if(customMap[j][0] == 'X'|| customMap[j][0] == 'I')
			return true;
		return false;
	}

	public boolean checkRightBorder(){
		for(int j = 0; j < height;j++)
			if (!checkRightCell(j))
				return false;		
		return true;
	}
	public boolean checkRightCell(int j){
		if(customMap[j][0] == 'X'|| customMap[j][0] == 'I')
			return true;
		return false;
	}

	private void setInstruction(String instruction)
	{
		lblInstruction.setText("<html>" + instruction + "</html>");	
	}
	
	public JFrame getFrame() {return frame;}

	public char[][] getMap() { return customMap;}

	public char getSelection() { return selection;}
}
