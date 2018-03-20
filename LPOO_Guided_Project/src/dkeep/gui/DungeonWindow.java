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
	private JFrame frame;
	private JTextField ogreTextField;
	private JLabel gameStatuslbl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DungeonWindow window = new DungeonWindow();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setBounds(100, 100, 591, 495);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel ogreLabel = new JLabel("Number of Ogres");
		ogreLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ogreLabel.setBounds(328, 10, 98, 22);

		ogreTextField = new JTextField();
		ogreTextField.setBounds(436, 12, 130, 19);
		ogreTextField.setColumns(10);

		JLabel guardLabel = new JLabel("Guard Personallity");
		guardLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		guardLabel.setBounds(328, 41, 98, 22);

		JComboBox<String> guardComboBox = new JComboBox<String>();
		guardComboBox.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		guardComboBox.addItem("Rookie");
		guardComboBox.addItem("Suspicious");
		guardComboBox.addItem("Drunk");
		guardComboBox.setBounds(436, 41, 130, 22);

		JButton btnStart = new JButton("Start Game");
		btnStart.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnStart.setBounds(302, 406, 96, 41);

		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnExit.setBounds(10, 406, 108, 41);

		JTextArea gameArea = new JTextArea();
		gameArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		gameArea.setEditable(false);
		gameArea.setBounds(10, 87, 327, 302);

		JButton btnLeft = new JButton("Left");
		btnLeft.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnLeft.setEnabled(false);
		btnLeft.setBounds(347, 202, 98, 47);

		JButton btnRight = new JButton("Right");
		btnRight.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnRight.setEnabled(false);
		btnRight.setBounds(468, 202, 98, 47);

		JButton btnDown = new JButton("Down");
		btnDown.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnDown.setEnabled(false);
		btnDown.setBounds(407, 259, 98, 47);

		JButton btnUp = new JButton("Up");
		btnUp.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnUp.setEnabled(false);
		btnUp.setBounds(407, 145, 98, 47);

		gameStatuslbl = new JLabel("Welcome to Dungeon Keep");
		gameStatuslbl.setHorizontalAlignment(SwingConstants.CENTER);
		gameStatuslbl.setToolTipText("Nothing to see here");
		gameStatuslbl.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		gameStatuslbl.setBounds(10, 15, 308, 65);

		btnExit.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
		/*
		 * START BUTTON
		 */
		btnStart.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
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
						if(n >= 2) { //FOR TESTING
							JOptionPane.showMessageDialog(ogreTextField, "Use this to choose the level for now");
							return;
						}

						btnUp.setEnabled(true);
						btnLeft.setEnabled(true);
						btnRight.setEnabled(true);
						btnDown.setEnabled(true);
						btnStart.setEnabled(false);
						game = new Game();
						gameArea.setText(game.toString());
						gameStatuslbl.setText("You must escape!!. Good Luck!");
					}
				});

		/*
		 * DIRECTIONAL BUTTONS
		 */
		
		btnUp.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						game.update(Game.Direction.UP);
						gameArea.setText(game.toString());
						if(game.gameLost() || game.gameWon()) {
							btnUp.setEnabled(false);
							btnLeft.setEnabled(false);
							btnRight.setEnabled(false);
							btnDown.setEnabled(false);
							gameStatuslbl.setText("Game Over");
						}
					}
				});

		btnDown.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						game.update(Game.Direction.DOWN);
						gameArea.setText(game.toString());
						if(game.gameLost() || game.gameWon()) {
							btnUp.setEnabled(false);
							btnLeft.setEnabled(false);
							btnRight.setEnabled(false);
							btnDown.setEnabled(false);
							gameStatuslbl.setText("Game Over");
						}
					}
				});

		btnLeft.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						game.update(Game.Direction.LEFT);
						gameArea.setText(game.toString());
						if(game.gameLost() || game.gameWon()) {
							btnUp.setEnabled(false);
							btnLeft.setEnabled(false);
							btnRight.setEnabled(false);
							btnDown.setEnabled(false);
							gameStatuslbl.setText("Game Over");
						}
					}
				});

		btnRight.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						game.update(Game.Direction.RIGHT);
						gameArea.setText(game.toString());
						if(game.gameLost() || game.gameWon()) {
							btnUp.setEnabled(false);
							btnLeft.setEnabled(false);
							btnRight.setEnabled(false);
							btnDown.setEnabled(false);
							gameStatuslbl.setText("Game Over");
						}
					}
				});

		/*
		 * ADD TO CONTENT PANE
		 */
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
}
