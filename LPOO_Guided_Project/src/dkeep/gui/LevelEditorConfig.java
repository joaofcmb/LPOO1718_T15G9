package dkeep.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dkeep.logic.Game;

public class LevelEditorConfig {
	private JDialog frame;
	private JButton btnContinue, btnBack;
	private JLabel lblHeight, lblWidth, lblOgre;
	private JTextField txtHeight, txtWidth, txtOgre;

	protected Game game;


	public LevelEditorConfig(JFrame mainFrame)
	{
		frame = new JDialog(mainFrame, "New Game Configuration", true);
		initialise(mainFrame);
	}

	private void initialise(JFrame mainFrame)
	{	
		initFrame();
		initButtons(mainFrame);
		initOtherObjects();

		addToContentPane();
	}



	private void initFrame()
	{
		frame.setResizable(false);
		frame.setBounds(300, 300, 312, 175);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}

	private void initButtons(JFrame mainFrame)
	{
		btnContinue = new JButton("Continue");
		btnContinue.setBounds(161, 101, 135, 33);
		btnContinue.setFont(new Font("Courier New", Font.PLAIN, 15));

		btnBack = new JButton("Back");
		btnBack.setBounds(0, 103, 135, 29);
		btnBack.setFont(new Font("Courier New", Font.PLAIN, 15));

		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int width, height, nOgres;
				try {
					width = Integer.parseUnsignedInt(txtWidth.getText());
					if(width > 10) {
						throw new NumberFormatException();
					}
				} catch(NumberFormatException n) { 
					JOptionPane.showMessageDialog(frame, "Enter a positive integer number below 11 for Width.");
					return;
				}
				
				try {
					height = Integer.parseUnsignedInt(txtHeight.getText());
					if(height > 10) {
						throw new NumberFormatException();
					}
				} catch(NumberFormatException n) { 
					JOptionPane.showMessageDialog(frame, "Enter a positive integer number below 11 for Height.");
					return;
				}

				try {
					nOgres = Integer.parseUnsignedInt(txtOgre.getText());
					if(nOgres > 5) {
						throw new NumberFormatException();
					}
				} catch(NumberFormatException n) { 
					JOptionPane.showMessageDialog(frame, "Enter a positive integer number below 6 for Ogre Number.");
					return;
				}
				
				LevelEditor lvle = new LevelEditor(width, height, nOgres);
				lvle.getFrame().setVisible(true);
				lvle.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				frame.dispose();
				mainFrame.dispose();
			}
		});

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}

	private void initOtherObjects()
	{

		lblOgre = new JLabel("Number of Ogres");
		lblOgre.setFont(new Font("Courier New", Font.PLAIN, 15));
		lblOgre.setBounds(10, 59, 141, 22);

		txtOgre = new JTextField("1");
		txtOgre.setBounds(161, 59, 45, 22);
		txtOgre.setFont(new Font("Courier New", Font.PLAIN, 15));
		txtOgre.setBackground(Color.WHITE);
		txtOgre.setColumns(1);

		lblHeight = new JLabel("Height");
		lblHeight.setFont(new Font("Courier New", Font.PLAIN, 15));
		lblHeight.setBounds(161, 10, 59, 29);

		lblWidth = new JLabel("Width");
		lblWidth.setFont(new Font("Courier New", Font.PLAIN, 15));
		lblWidth.setBounds(10, 10, 59, 29);

		txtHeight = new JTextField("1");
		txtHeight.setFont(new Font("Courier New", Font.PLAIN, 15));
		txtHeight.setColumns(1);
		txtHeight.setBackground(Color.WHITE);
		txtHeight.setBounds(230, 13, 45, 22);

		txtWidth = new JTextField("1");
		txtWidth.setFont(new Font("Courier New", Font.PLAIN, 15));
		txtWidth.setColumns(1);
		txtWidth.setBackground(Color.WHITE);
		txtWidth.setBounds(79, 13, 45, 22);
	}

	public void addToContentPane(){

		frame.getContentPane().add(btnBack);
		frame.getContentPane().add(btnContinue);

		frame.getContentPane().add(txtOgre);
		frame.getContentPane().add(lblOgre);

		frame.getContentPane().add(lblHeight);
		frame.getContentPane().add(lblWidth);

		frame.getContentPane().add(txtHeight);
		frame.getContentPane().add(txtWidth);
	}

	public JDialog getFrame() {return frame;}
}
