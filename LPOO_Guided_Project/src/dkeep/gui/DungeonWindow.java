package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.SwingConstants;

import dkeep.logic.Game;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DungeonWindow {
	private Game game = new Game();
	private static JFrame frame;
	private JTextField ogreTextField;
	private JLabel gameStatuslbl;
	private JComboBox<String> guardComboBox;
	private JTextArea gameArea = new JTextArea();

	private JButton btnStart = new JButton("Start Game");
	private JButton btnExit = new JButton("Exit");
	private JButton btnLeft = new JButton("Left");
	private JButton btnRight = new JButton("Right");
	private JButton btnDown = new JButton("Down");
	private JButton btnUp = new JButton("Up");

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		frame = new JFrame("Dungeon Keep");

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					DungeonWindow window = new DungeonWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DungeonWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame.setResizable(false);
		frame.setBounds(100, 100, 591, 495);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		initButtons();

		JLabel ogreLabel = new JLabel("Number of Ogres");
		ogreLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ogreLabel.setBounds(328, 10, 98, 22);

		ogreTextField = new JTextField();
		ogreTextField.setBounds(436, 12, 130, 19);
		ogreTextField.setColumns(10);

		JLabel guardLabel = new JLabel("Guard Personallity");
		guardLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		guardLabel.setBounds(328, 41, 98, 22);

		guardComboBox = new JComboBox<String>();
		guardComboBox.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		guardComboBox.addItem("Rookie");
		guardComboBox.addItem("Suspicious");
		guardComboBox.addItem("Drunk");
		guardComboBox.setBounds(436, 41, 130, 22);

		btnStart.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnStart.setBounds(302, 406, 96, 41);

		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnExit.setBounds(10, 406, 108, 41);
		
		gameArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		gameArea.setEditable(false);
		gameArea.setBounds(10, 87, 327, 302);

		btnLeft.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnLeft.setEnabled(false);
		btnLeft.setBounds(347, 202, 98, 47);

		btnRight.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnRight.setEnabled(false);
		btnRight.setBounds(468, 202, 98, 47);

		btnDown.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnDown.setEnabled(false);
		btnDown.setBounds(407, 259, 98, 47);

		btnUp.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnUp.setEnabled(false);
		btnUp.setBounds(407, 145, 98, 47);

		gameStatuslbl = new JLabel("Welcome to Dungeon Keep");
		gameStatuslbl.setHorizontalAlignment(SwingConstants.CENTER);
		gameStatuslbl.setToolTipText("Nothing to see here");
		gameStatuslbl.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		gameStatuslbl.setBounds(10, 15, 308, 65);

//	  	Panel panel = new Panel(game);
//		panel.setBackground(Color.LIGHT_GRAY);
//		panel.setBounds(0, 0, 600, 600);
		
		/*
		 * ADD TO CONTENT PANE
		 */
		//frame.getContentPane().add(panel);
		
		frame.getContentPane().add(ogreLabel);
		frame.getContentPane().add(ogreTextField);
		frame.getContentPane().add(guardLabel);
		frame.getContentPane().add(guardComboBox);
		frame.getContentPane().add(btnStart);
		frame.getContentPane().add(btnExit);
		frame.getContentPane().add(gameArea);
		frame.getContentPane().add(btnLeft);
		frame.getContentPane().add(btnRight);
		frame.getContentPane().add(btnDown);
		frame.getContentPane().add(btnUp);
		frame.getContentPane().add(gameStatuslbl);
	}

	private void initButtons() {
		/*EXIT BUTTON*/
		btnExit.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
		/* START BUTTON*/
		btnStart.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						startGame(gameArea, btnUp, btnLeft, btnDown, btnRight, btnStart);
					}
				});
		/* DIRECTIONAL BUTTONS*/

		btnUp.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pressedKey(Game.Direction.UP, gameArea,btnUp,btnLeft,btnDown,btnRight, btnStart);
					}
				});

		btnDown.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pressedKey(Game.Direction.DOWN, gameArea,btnUp,btnLeft,btnDown,btnRight, btnStart);
					}
				});

		btnLeft.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pressedKey(Game.Direction.LEFT, gameArea,btnUp,btnLeft,btnDown,btnRight, btnStart);
					}
				});

		btnRight.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pressedKey(Game.Direction.RIGHT, gameArea,btnUp,btnLeft,btnDown,btnRight, btnStart);
					}
				});

	}

	private int checkState(JButton up, JButton left, JButton down, JButton right, JButton start) {
		if(game.gameLost()) {
			up.setEnabled(false);
			left.setEnabled(false);
			down.setEnabled(false);
			right.setEnabled(false);
			start.setEnabled(true);
			ogreTextField.setEnabled(true);
			guardComboBox.setEnabled(true);
			gameStatuslbl.setText("You were caught!");
			return -1;
		}

		else if(game.gameWon()) {
			up.setEnabled(false);
			left.setEnabled(false);
			down.setEnabled(false);
			right.setEnabled(false);
			start.setEnabled(true);
			ogreTextField.setEnabled(true);
			guardComboBox.setEnabled(true);
			gameStatuslbl.setText("You Escaped!!");

			return 1;
		}
		else 
			return 0;
	}

	private void pressedKey(Game.Direction dir, JTextArea gameArea,JButton up, JButton left, JButton down, JButton right, JButton start ) {
		game.update(dir);
		gameArea.setText(game.toString());
		checkState(up,left,down,right, start);

	}

	private void startGame(JTextArea gameArea,JButton btnUp, JButton btnLeft, JButton btnDown, JButton btnRight, JButton btnStart) {
		int n = 0;
		try {
			n = Integer.parseUnsignedInt(ogreTextField.getText());
		}
		catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(ogreTextField, "Introduza um inteiro positivo");
			return;
		}
		if(n == 0) {
			JOptionPane.showMessageDialog(ogreTextField, "Introduza um inteiro positivo.");
			return;
		}
		//TODO make arguments affect game
		btnUp.setEnabled(true);
		btnLeft.setEnabled(true);
		btnRight.setEnabled(true);
		btnDown.setEnabled(true);
		btnStart.setEnabled(false);
		ogreTextField.setEnabled(false);
		guardComboBox.setEnabled(false);
		game = new Game();
		gameArea.setText(game.toString());
		gameStatuslbl.setText("You must escape!!. Good Luck!");
	}
}
