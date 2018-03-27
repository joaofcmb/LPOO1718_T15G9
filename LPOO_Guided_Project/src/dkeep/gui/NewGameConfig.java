package dkeep.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import dkeep.logic.Game;
import java.awt.BorderLayout;
import java.awt.Font;

public class NewGameConfig {
	private JDialog frame;
	private JLabel ogreLabel = new JLabel("Number of Ogres");
	private JTextField ogreTextField = new JTextField();
	private JLabel guardLabel = new JLabel("Guard's Personality");
	private JComboBox<String> guardComboBox = new JComboBox<String>();
	private JButton btnStart = new JButton("Start Game");
	private JButton btnBack = new JButton("Back");
	private JLabel GameStatus = new JLabel(" ");
	protected Game g;

	public NewGameConfig(JFrame mainFrame) {
		frame = new JDialog(mainFrame, "New Game Configuration", true);
		initialise();
	}

	public void initialise(){	

		windowConf();
		fldOgresConf();	
		buttonStart();
		initButtons();

		addContent();
	}
	
	public void windowConf(){

		frame.setResizable(false);
		frame.setBounds(100, 100, 350, 220);
		frame.getContentPane().setLayout(null);	

		ogreLabel.setBounds(22, 84, 98, 16);	
		ogreLabel.setHorizontalAlignment(SwingConstants.LEFT);

	}

	public void fldOgresConf(){
		ogreTextField.setFont(new Font("Courier New", Font.PLAIN, 15));
		ogreTextField.setBounds(210, 23, 88, 22);
		ogreTextField.setBackground(Color.WHITE);
		ogreTextField.setColumns(5);

		guardComboBox.setFont(new Font("Courier New", Font.PLAIN, 15));
		guardComboBox.setBounds(210, 63, 89, 22);

		guardComboBox.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		guardComboBox.addItem("Rookie");
		guardComboBox.addItem("Suspicious");
		guardComboBox.addItem("Drunk");
		guardComboBox.setEditable(false);
	}

	public void buttonStart(){
		//		ButtonStart.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		//				if(fldOgres.getText().charAt(0) < 49 || fldOgres.getText().charAt(0) > 53){
		//					GameStatus.setText("Invalid number of Ogres");
		//					return;
		//				}
		//
		//				g = new Game(Integer.parseInt(fldOgres.getText()));
		//				Guard G = g.getGuard();
		//				g.setGuard( new Guard(G.getCoordenateI(),G.getCoordenateJ(),G.getSprite(),fldBehaviour.getSelectedIndex()));
		//
		//				frame.setVisible(false);				
		//				StartGame sg = new StartGame(g);	
		//				sg.getGameWindow().setVisible(true);				
		//				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//			}
		//
		//		});
	}

	private void initButtons()
	{
		frame.requestFocus();
		btnStart.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnStart.setBounds(179, 128, 135, 33);

		btnBack.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnBack.setBounds(22, 130, 135, 29);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.dispose();
			}
		});
	}

	public void addContent(){
		frame.getContentPane().setLayout(null);

		frame.getContentPane().add(btnBack);
		frame.getContentPane().add(btnStart);
		frame.getContentPane().add(guardComboBox);

		guardLabel.setFont(new Font("Courier New", Font.PLAIN, 15));
		guardLabel.setBounds(42, 60, 178, 29);

		frame.getContentPane().add(guardLabel);
		frame.getContentPane().add(ogreTextField);

		ogreLabel.setFont(new Font("Courier New", Font.PLAIN, 15));
		ogreLabel.setBounds(22, 23, 141, 21);

		frame.getContentPane().add(ogreLabel);
	}

	public JDialog getFrame() {return frame;}

}

