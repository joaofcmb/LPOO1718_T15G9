package dkeep.gui;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import dkeep.logic.Game;
import dkeep.logic.Game.Personality;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Window;

public class NewGameConfig {
	private JDialog frame;
	private JLabel ogreLabel, guardLabel;
	private JTextField ogreTextField;
	private JComboBox<String> guardComboBox;
	private JButton btnStart, btnBack;
	protected Game game;

	public NewGameConfig(JFrame mainFrame)
	{
		frame = new JDialog(mainFrame, "New Game Configuration", true);
		initialise(mainFrame);
	}

	public void initialise(JFrame mainFrame)
	{	
		initFrame();
		initButtons(mainFrame);
		initOtherObjects();

		addToContentPane();
	}



	public void initFrame()
	{
		frame.setResizable(false);
		frame.setBounds(300, 300, 350, 220);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		frame.getContentPane().setLayout(null);
		frame.getContentPane().setLayout(null);
	}

	private void initButtons(JFrame mainFrame)
	{
		btnStart = new JButton("Start Game");
		btnStart.setBounds(191, 128, 135, 33);
		btnStart.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nOgres;
				try {
					nOgres = Integer.parseUnsignedInt(ogreTextField.getText());
				} catch(NumberFormatException n) { 
					JOptionPane.showMessageDialog(frame, "Enter a positive integer number.");
					return;
				}
				Personality guardType;
				switch(guardComboBox.getItemAt(guardComboBox.getSelectedIndex()))
				{
				case "Rookie": guardType = Personality.ROOKIE;break;
				case "Suspicious": guardType = Personality.SUSPICIOUS;break;
				case "Drunk": guardType = Personality.DRUNK;break;
				default: guardType = Personality.STATIC;break;
				}
				game = new Game(nOgres, guardType);
				GameWindow gameWindow = new GameWindow(game);
				gameWindow.getFrame().setVisible(true);
				gameWindow.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.dispose();
				mainFrame.dispose();
			}
		});

		btnBack = new JButton("Back");
		btnBack.setBounds(10, 130, 135, 29);
		btnBack.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}

	private void initOtherObjects()
	{
		guardLabel = new JLabel("Guard's Personality");
		guardLabel.setBounds(10, 20, 178, 29);
		guardLabel.setFont(new Font("Courier New", Font.PLAIN, 15));

		ogreLabel = new JLabel("Number of Ogres");
		ogreLabel.setFont(new Font("Courier New", Font.PLAIN, 15));
		ogreLabel.setBounds(10, 59, 141, 21);

		ogreTextField = new JTextField("1");
		ogreTextField.setBounds(191, 58, 135, 22);
		ogreTextField.setFont(new Font("Courier New", Font.PLAIN, 15));
		ogreTextField.setBackground(Color.WHITE);
		ogreTextField.setColumns(1);

		guardComboBox = new JComboBox<String>();
		guardComboBox.setBounds(191, 23, 135, 22);
		guardComboBox.setFont(new Font("Courier New", Font.PLAIN, 15));
		guardComboBox.addItem("Rookie");
		guardComboBox.addItem("Suspicious");
		guardComboBox.addItem("Drunk");
		guardComboBox.setEditable(false);
	}

	public void addToContentPane(){

		frame.getContentPane().add(btnBack);
		frame.getContentPane().add(btnStart);

		frame.getContentPane().add(guardComboBox);
		frame.getContentPane().add(guardLabel);

		frame.getContentPane().add(ogreTextField);
		frame.getContentPane().add(ogreLabel);
	}

	public JDialog getFrame() {return frame;}
}
