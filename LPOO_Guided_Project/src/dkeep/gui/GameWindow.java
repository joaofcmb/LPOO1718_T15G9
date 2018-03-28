package dkeep.gui;


import dkeep.logic.Game;
import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class GameWindow {
	private Game game;

	private GameGraphics graphics;
	private JFrame frame;
	private JLabel lblGameState;
	private JButton btnUp, btnDown, btnLeft, btnRight, btnQuit;

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
		frame.getContentPane().setLayout(null);
	}

	private void initButtons() {
		btnUp = new JButton("UP");
		btnUp.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnUp.setBounds(669, 266, 91, 32);

		btnDown = new JButton("DOWN");
		btnDown.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnDown.setBounds(669, 361, 91, 32);

		btnLeft = new JButton("LEFT");
		btnLeft.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnLeft.setBounds(622, 319, 91, 32);

		btnRight = new JButton("RIGHT");
		btnRight.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnRight.setBounds(723, 319, 91, 32);

		btnQuit = new JButton("QUIT");
		btnQuit.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnQuit.setBounds(669, 642, 91, 32);

		btnUp.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pressedKey(Game.Direction.UP);
					}
				});
		btnDown.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pressedKey(Game.Direction.DOWN);
					}
				});
		btnLeft.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pressedKey(Game.Direction.LEFT);
					}
				});
		btnRight.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pressedKey(Game.Direction.RIGHT);
					}
				});

		btnQuit.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MainMenu menu = new MainMenu();
						menu.getFrame().setVisible(true);
						menu.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

						frame.dispose();
					}
				});
	}

	private void initOtherObjects() {
		graphics = new GameGraphics(game);
		graphics.setBackground(Color.LIGHT_GRAY);
		graphics.setBounds(10, 10, 600, 700);

		lblGameState = new JLabel("Try to Escape!");
		lblGameState.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameState.setFont(new Font("Courier New", Font.PLAIN, 17));
		lblGameState.setBounds(622, 10, 192, 95);

		initKeys();
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
						}
						checkGameState();
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

		frame.getContentPane().add(lblGameState);
	}

	private void pressedKey(Game.Direction direction)
	{
		game.update(direction);
		graphics.update();
		checkGameState();
	}

	private void checkGameState()
	{
		if(game.gameLost()) {
			btnUp.setEnabled(false);
			btnLeft.setEnabled(false);
			btnDown.setEnabled(false);
			btnRight.setEnabled(false);
			lblGameState.setText("You were caught!");
		}
		else if(game.gameWon()) {
			btnUp.setEnabled(false);
			btnLeft.setEnabled(false);
			btnDown.setEnabled(false);
			btnRight.setEnabled(false);
			lblGameState.setText("You Escaped!!");
		}
	}

	public JFrame getFrame() {return frame;}
}
