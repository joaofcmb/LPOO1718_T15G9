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

import dkeep.logic.CustomMap;

public class LevelEditor {
	private JFrame frame;
	private JLabel lblInstruction;
	private JButton btnHero, btnDoor, btnOgre, btnKey, btnQuit, btnWall, btnSaveCustomMap;
	private JPanel graphics;

	private int width, height, nOgres;
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
		frame.setResizable(false);
		frame.setBounds(100, 100, 837, 754);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}

	private void initMap()
	{
		for(int i = 0; i < customMap.length; i++)
		{
			for(int j = 0; j < customMap[i].length; j++)
			{
				customMap[i][j] = ' ';
			}
		}
	}

	private void initOtherObjects() {
		graphics = new LevelEditorGraphics(width, height,this);
		graphics.setLayout(null);
		graphics.setBounds(10, 10, width*LevelEditorGraphics.SIZE, height*LevelEditorGraphics.SIZE);
		graphics.setBackground(Color.GRAY);

		lblInstruction = new JLabel("<html>Select an element.</html>");
		lblInstruction.setHorizontalAlignment(SwingConstants.LEFT);
		lblInstruction.setFont(new Font("Courier New", Font.PLAIN, 17));
		lblInstruction.setBounds(561, 5, 263, 141);

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
		btnHero.setBounds(561, 156, 116, 32);
		btnListener(btnHero, 'A', "the Hero");
	}

	private void initBtnDoor()
	{
		btnDoor = new JButton("5. Door");
		btnDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDoor.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnDoor.setBounds(561, 240, 116, 32);
		btnListener(btnDoor, 'I', "a Door");
	}
	private void initBtnWall()
	{
		btnWall = new JButton("2. Wall");
		btnWall.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnWall.setBounds(708, 156, 116, 32);
		btnListener(btnWall, 'X', "a Wall");
	}

	private void initBtnOgre()
	{
		btnOgre = new JButton("3. Ogre");
		btnOgre.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnOgre.setBounds(561, 198, 116, 32);
		btnListener(btnOgre, 'O', "an Ogre");

	}
	private void initBtnKey()
	{
		btnKey = new JButton("4. Key");
		btnKey.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnKey.setBounds(708, 198, 116, 32);
		btnListener(btnKey, 'k', "a Key");

	}
	private void initBtnQuit()
	{
		btnQuit = new JButton("QUIT");
		btnQuit.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnQuit.setBounds(718, 675, 106, 32);
		btnQuit.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						quitToMainMenu();					}
				});
	}

	private void initBtnSaveCustomMap()
	{
		btnSaveCustomMap = new JButton("<html>Save Custom Map</html>");
		btnSaveCustomMap.setVerticalAlignment(SwingConstants.TOP);
		btnSaveCustomMap.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnSaveCustomMap.setBounds(708, 243, 116, 63);

		btnSaveCustomMap.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(checkElements())
						{
							int xPlayer = 0, yPlayer = 0;
							int xOgre = 0, yOgre = 0;
							int xKey = 0, yKey = 0;
							for(int i = 0; i < customMap.length; i++)
								for(int j = 0; j < customMap[i].length; j++)
								{
									switch(customMap[i][j])
									{
									case 'A': 
									{
										xPlayer = i;
										yPlayer = j;
										customMap[i][j] = ' ';
										break;
									}
									case 'O': 
									{
										xOgre = i;
										yOgre = j;
										customMap[i][j] = ' ';
										break;
									}
									case 'k':
									{
										xKey = i;
										yKey = j;
										customMap[i][j] = ' ';
										break;
									}
									}
								}
							CustomMap cMap = new CustomMap(xPlayer, yPlayer, xOgre, yOgre, xKey, yKey, customMap, nOgres);
							//TODO save cMap
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
						lblInstruction.setText("<html>Choose where to place " + name +".</html>");
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
							lblInstruction.setText("<html>Choose where to place the Hero.</html>");
							break;
						}
						case KeyEvent.VK_2: {//Wall
							selection  = 'X';
							lblInstruction.setText("<html>Choose where to place a Wall.</html>");
							break;
						}
						case KeyEvent.VK_3: {//Ogre
							selection  = 'O';
							lblInstruction.setText("<html>Choose where to place an Ogre.</html>");
							break;
						}
						case KeyEvent.VK_4: {//Key
							selection  = 'k';
							lblInstruction.setText("<html>Choose where to place a Key.</html>");
							break;
						}
						case KeyEvent.VK_5: {//Door
							selection  = 'I';
							lblInstruction.setText("<html>Choose where to place a Key.</html>");
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

	private void addToContentPane()
	{	
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

	public boolean checkElements(){

		if ( !checkDynamic() || nDoorsPlaced == 0 || nWallsPlaced == 0 || nKeysPlaced == 0){
			lblInstruction.setText("<html>There's, at least, a missing element to the map!</html>");
			return false;
		}		
		return checkNumbElements();
	}

	public boolean checkDynamic(){
		if( nOgresPlaced == 0 || nHeroesPlaced == 0)
			return false;
		return true;
	}
	public boolean checkNumbElements(){
		if (nOgresPlaced > 1){
			lblInstruction.setText("<html>Place only ONE Ogre</html>");
			return false;
		}	
		if (nHeroesPlaced > 1){
			lblInstruction.setText("<html>Place only ONE Hero</html>");
			return false;
		}	

		if (nKeysPlaced > 1){
			lblInstruction.setText("<html>Place only ONE Key</html>");
			return false;
		}		

		if (nDoorsPlaced > 1){
			lblInstruction.setText("<html>Place only ONE Door</html>");
			return false;
		}	
		return true;
	}

	public boolean checkCorners(){
		if (customMap[0][0] == 'I' || customMap[width-1][0] == 'I' || customMap[0][height-1] == 'I' || customMap[width-1][height-1] == 'I')
			return false;
		return true;

	}

	public boolean checkBorders(){
		if (!upperBorder() || !lowerBorder() || !leftBorder() || !rightBorder())
			return false;		
		return true;

	}

	public boolean upperBorder(){
		for(int i = 0; i < width;i++)
			if (!checkUpper(i))
				return false;	
		return true;
	}

	public boolean checkUpper(int i){
		if(customMap[i][0] == 'O'|| customMap[i][0] == 'A' || customMap[i][0] == 'k' ||customMap[i][0] == ' ')
			return false;
		return true;
	}


	public boolean lowerBorder(){
		for(int i = 0; i < width;i++)
			if (!checkLower(i))
				return false;
		return true;
	}

	public boolean checkLower(int i){
		if(customMap[i][height-1] == 'O'||customMap[i][height-1] == 'A' || customMap[i][height-1] == 'k' ||customMap[i][height-1] == ' ')
			return false;
		return true;
	}

	public boolean leftBorder(){
		for(int j = 0; j < height;j++)
			if (!checkLeft(j))
				return false;	
		return true;
	}

	public boolean checkLeft(int j){
		if(customMap[0][j] == 'O' || customMap[0][j] == 'A'  || customMap[0][j] == 'k' || customMap[0][j] == ' ')
			return false;	
		return true;

	}

	public boolean rightBorder(){
		for(int j = 0; j < height;j++)
			if (!checkRight(j))
				return false;		
		return true;
	}
	public boolean checkRight(int j){
		if(customMap[width-1][j] == 'O' || customMap[width-1][j] == 'A'  || customMap[width-1][j] == 'k' || customMap[width-1][j] == ' ')					
			return false;		
		return true;

	}

	public JFrame getFrame() {return frame;}

	public char[][] getMap() { return customMap;}

	public char getSelection() { return selection;}
}
