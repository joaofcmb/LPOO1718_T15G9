package dkeep.gui;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import dkeep.logic.Game;
import dkeep.logic.Game.Personality;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class NewGameConfig {
	private JDialog frame;
	private JLabel ogreLabel, guardLabel;
	private JTextField ogreTextField;
	private JComboBox<String> guardComboBox;
	private JButton btnStart, btnBack, btnLoad, btnCustomMap;
	
	protected Game game;
	private char[][] customMap = null;

	public NewGameConfig(JFrame mainFrame)
	{
		frame = new JDialog(mainFrame, "New Game Configuration", true);
		initialise(mainFrame);
	}

	private  void initialise(JFrame mainFrame)
	{	
		initFrame();
		initButtons(mainFrame);
		initOtherObjects();

		addToContentPane();
	}

	private void initFrame()
	{
		frame.setBounds(300, 300, 350, 220);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}

	private void initButtons(JFrame mainFrame)
	{
		initBtnStart(mainFrame);
		initBtnBack();
		initBtnLoad(mainFrame);
		initBtnCustomMap(mainFrame);	
	}

	private void initBtnStart(JFrame mainFrame)
	{
		btnStart = new JButton("Start Game");
		btnStart.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nOgres;
				
				try {
					nOgres = Integer.parseUnsignedInt(ogreTextField.getText());
					if(nOgres > 5)	throw new NumberFormatException();
				} catch(NumberFormatException n) { 
					JOptionPane.showMessageDialog(frame, "Enter a positive integer number below 6.");
					return;
				}
				
				Personality guardType;
				switch(guardComboBox.getItemAt(guardComboBox.getSelectedIndex())) {
				case "Rookie": guardType = Personality.ROOKIE;break;
				case "Suspicious": guardType = Personality.SUSPICIOUS;break;
				case "Drunk": guardType = Personality.DRUNK;break;
				default: guardType = Personality.STATIC;break;
				}
				
				game = new Game(nOgres, guardType, customMap);
				GameWindow gameWindow = new GameWindow(game);
				gameWindow.getFrame().setVisible(true);
				gameWindow.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.dispose();
				mainFrame.dispose(); //to close the main menu window
			}
		});
	}
	
	private void initBtnBack()
	{
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}
	
	private void initBtnLoad(JFrame mainframe)
	{
		btnLoad = new JButton("Load Game");
		btnLoad.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game = (Game) loadObject("Dungeon Keep Save Files", "dksf");
			
				if (game == null)	return;
				
				GameWindow gameWindow = new GameWindow(game);
				gameWindow.getFrame().setVisible(true);
				gameWindow.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.dispose();
				mainframe.dispose();
			}
		});
	}
	
	private void initBtnCustomMap(JFrame mainframe)
	{
		btnCustomMap = new JButton("Load Custom Map");
		btnCustomMap.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnCustomMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customMap = (char[][]) loadObject("Crazy Ogre Map Files", "comf");
				
				if (customMap != null)
					JOptionPane.showMessageDialog(frame,  "Custom Map loaded successfully. "
														+ "Finish setting up and start game to proceed.");
			}
		});
	}
	
	private Object loadObject(String extensionDescription, String extension) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter(extensionDescription, extension));
		
		if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
			try {
				return load(fileChooser);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private Object load(JFileChooser fileChooser) throws IOException, FileNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileChooser.getSelectedFile().getPath()));
		try { 
			Object object = in.readObject();
			in.close();
			return object;
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			in.close();
			return null;
		}
	}

	private void addToContentPane(){
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{178, 135, 0, 0};
		gridBagLayout.rowHeights = new int[]{29, 22, 48, 0, 33, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		GridBagConstraints gbc_btnLoad = new GridBagConstraints();
		gbc_btnLoad.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLoad.insets = new Insets(0, 0, 5, 5);
		gbc_btnLoad.gridx = 1;
		gbc_btnLoad.gridy = 3;
		frame.getContentPane().add(btnLoad, gbc_btnLoad);
		
		GridBagConstraints gbc_btnCustomMap = new GridBagConstraints();
		gbc_btnCustomMap.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCustomMap.insets = new Insets(0, 0, 5, 5);
		gbc_btnCustomMap.gridx = 0;
		gbc_btnCustomMap.gridy = 3;
		frame.getContentPane().add(btnCustomMap, gbc_btnCustomMap);

		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBack.insets = new Insets(0, 0, 0, 5);
		gbc_btnBack.gridx = 0;
		gbc_btnBack.gridy = 4;
		frame.getContentPane().add(btnBack, gbc_btnBack);
		
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.insets = new Insets(0, 0, 0, 5);
		gbc_btnStart.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnStart.gridx = 1;
		gbc_btnStart.gridy = 4;
		frame.getContentPane().add(btnStart, gbc_btnStart);
		
		GridBagConstraints gbc_guardLabel = new GridBagConstraints();
		gbc_guardLabel.anchor = GridBagConstraints.WEST;
		gbc_guardLabel.insets = new Insets(0, 0, 5, 5);
		gbc_guardLabel.gridx = 0;
		gbc_guardLabel.gridy = 0;
		frame.getContentPane().add(guardLabel, gbc_guardLabel);

		GridBagConstraints gbc_guardComboBox = new GridBagConstraints();
		gbc_guardComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_guardComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_guardComboBox.gridx = 1;
		gbc_guardComboBox.gridy = 0;
		frame.getContentPane().add(guardComboBox, gbc_guardComboBox);

		GridBagConstraints gbc_ogreLabel = new GridBagConstraints();
		gbc_ogreLabel.anchor = GridBagConstraints.WEST;
		gbc_ogreLabel.insets = new Insets(0, 0, 5, 5);
		gbc_ogreLabel.gridx = 0;
		gbc_ogreLabel.gridy = 1;
		frame.getContentPane().add(ogreLabel, gbc_ogreLabel);
								
		GridBagConstraints gbc_ogreTextField = new GridBagConstraints();
		gbc_ogreTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_ogreTextField.insets = new Insets(0, 0, 5, 5);
		gbc_ogreTextField.gridx = 1;
		gbc_ogreTextField.gridy = 1;
		frame.getContentPane().add(ogreTextField, gbc_ogreTextField);
	}

	private void initOtherObjects()
	{
		initGuardLabel();
		initOgreLabel();
		initOgreTextField();
		initGuardComboBox();
	}

	private void initGuardLabel()
	{
		guardLabel = new JLabel("Guard's Personality");
		guardLabel.setFont(new Font("Courier New", Font.PLAIN, 15));
	}

	private void initOgreLabel()
	{
		ogreLabel = new JLabel("Number of Ogres");
		ogreLabel.setFont(new Font("Courier New", Font.PLAIN, 15));
	}

	private void initOgreTextField()
	{
		ogreTextField = new JTextField("1");
		ogreTextField.setFont(new Font("Courier New", Font.PLAIN, 15));
		ogreTextField.setBackground(Color.WHITE);
		ogreTextField.setColumns(1);
	}

	private void initGuardComboBox()
	{
		guardComboBox = new JComboBox<String>();
		guardComboBox.setFont(new Font("Courier New", Font.PLAIN, 15));
		guardComboBox.addItem("Rookie");
		guardComboBox.addItem("Suspicious");
		guardComboBox.addItem("Drunk");
		guardComboBox.setEditable(false);
	}

	public JDialog getFrame() {return frame;}
}
