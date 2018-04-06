package dkeep.gui;

import dkeep.logic.Game;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GameWindow {
	private Game game;

	private JFrame frame;
	protected JPanel graphics;
	private JLabel lblGameState;
	private JButton btnUp, btnDown, btnLeft, btnRight, btnQuit, btnSaveGame;

	public GameWindow(Game g) {
		this.game = g;
		initialise();
	}

	private void initialise() {
		initFrame();
		initOtherObjects();
		initButtons();
		addToContentPane();
	}

	private void initFrame() {
		frame = new JFrame("Dungeon Keep");
		frame.setResizable(false);
		frame.setBounds(100, 100, 837, 754);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}


	private void initOtherObjects() {
		graphics = new GameGraphics(game);
		graphics.setLayout(null);
		graphics.setBounds(10, 10, 600, 700);
		graphics.setBackground(Color.GRAY);

		lblGameState = new JLabel("<html>Try to Escape!</html>");
		lblGameState.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameState.setFont(new Font("Courier New", Font.PLAIN, 17));
		lblGameState.setBounds(622, 10, 192, 95);

		initKeys();
	}

	private void initButtons() {
		initBtnUp();
		initBtnDown();
		initBtnLeft();
		initBtnRight();
		initBtnQuit();
		initBtnSaveGame();
	}
	private void initBtnUp()
	{
		btnUp = new JButton("UP");
		btnUp.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnUp.setBounds(669, 266, 91, 32);
		btnListener(btnUp, Game.Direction.UP);
	}

	private void initBtnDown()
	{

		btnDown = new JButton("DOWN");
		btnDown.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnDown.setBounds(669, 361, 91, 32);

		btnListener(btnDown, Game.Direction.DOWN);
	}

	private void initBtnLeft()
	{

		btnLeft = new JButton("LEFT");
		btnLeft.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnLeft.setBounds(622, 319, 91, 32);

		btnListener(btnLeft, Game.Direction.LEFT);
	}

	private void initBtnRight()
	{
		btnRight = new JButton("RIGHT");
		btnRight.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnRight.setBounds(723, 319, 91, 32);

		btnListener(btnRight, Game.Direction.RIGHT);
	}

	private void initBtnQuit()
	{
		btnQuit = new JButton("QUIT");
		btnQuit.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnQuit.setBounds(669, 642, 91, 32);

		btnQuit.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						quitToMainMenu();
					}
				});
	}
	
	private void initBtnSaveGame()
	{
		btnSaveGame = new JButton("<html>SAVE GAME</html>");
		btnSaveGame.setHorizontalAlignment(SwingConstants.RIGHT);
		btnSaveGame.setVerticalAlignment(SwingConstants.TOP);
		btnSaveGame.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnSaveGame.setBounds(669, 555, 91, 45);
		
		btnSaveGame.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JFileChooser fileChooser = new JFileChooser();
						fileChooser.setFileFilter(new FileNameExtensionFilter("Dungeon Keep Save Files", "dksf"));
					
						if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
							try {	
								File f = fileChooser.getSelectedFile();
								if (!f.getName().endsWith(".dksf")) {
									int i = f.getPath().lastIndexOf('.');
									f.renameTo(new File(f.getPath().substring(0,i) + ".dksf"));
								}
								f.createNewFile(); // create new file if it doesn't exist yet
								ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f.getPath()));
								out.writeObject(game);
								out.close();
							} catch (IOException e2) {
								e2.printStackTrace();
								return;
							}
						}
					}
				});
	}

	private void btnListener(JButton btn, Game.Direction dir)
	{
		btn.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pressedKey(dir);
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
						case KeyEvent.VK_W: {
							pressedKey(Game.Direction.UP);
							break;
						}
						case KeyEvent.VK_A: {
							pressedKey(Game.Direction.LEFT);
							break;
						}
						case KeyEvent.VK_S: {
							pressedKey(Game.Direction.DOWN);
							break;
						}
						case KeyEvent.VK_D: {
							pressedKey(Game.Direction.RIGHT);
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

	private void addToContentPane()
	{	
		frame.getContentPane().add(graphics);

		frame.getContentPane().add(btnUp);	
		frame.getContentPane().add(btnDown);
		frame.getContentPane().add(btnLeft);
		frame.getContentPane().add(btnRight);		
		frame.getContentPane().add(btnQuit);
		frame.getContentPane().add(btnSaveGame);

		frame.getContentPane().add(lblGameState);
	}

	private void pressedKey(Game.Direction direction)
	{
		game.update(direction);
		checkGameState();
	}

	private void quitToMainMenu() 
	{
		MainMenu menu = new MainMenu();
		menu.getFrame().setVisible(true);
		menu.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.dispose();
	}

	private void checkGameState()
	{
		if(game.gameLost()) {
			deactivateButtons();
			disablePanel();
			setGameState("You were caught!</html>");
		}
		else if(game.gameWon()) {
			deactivateButtons();
			disablePanel();
			setGameState("You Escaped!!");
		}
	}

	private void deactivateButtons()
	{
		btnUp.setEnabled(false);
		btnLeft.setEnabled(false);
		btnDown.setEnabled(false);
		btnRight.setEnabled(false);
	}

	private void disablePanel()
	{
		graphics.setEnabled(false);
	}
	
	private void setGameState(String state)
	{
		lblGameState.setText("<html>" + state + "</html>");
	}

	public JFrame getFrame() {return frame;}
}
