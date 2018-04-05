package dkeep.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;
//TODO  Add Background Images
public class MainMenu {

	private JFrame frame;
	private JButton btnNewGame, btnExit, btnLevelEditor;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainMenu() {
		initialize();
	}

	private void initialize() {
		initFrame();

		initButtons();

		addToContentPane();
	}

	private void initFrame() 
	{
		frame = new JFrame("Main Menu");
		frame.setBounds(100, 100, 800, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
	}

	private void initButtons()
	{
	}

	private void addToContentPane()
	{
		btnNewGame = new JButton("New Game");
		btnNewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewGame.setFont(new Font("Courier New", Font.PLAIN, 15));

		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				NewGameConfig ng = new NewGameConfig(frame);
				ng.getFrame().setVisible(true);

			}
		});
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		frame.getContentPane().add(btnNewGame);

		btnLevelEditor = new JButton("Level Editor");
		btnLevelEditor.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnLevelEditor.setFont(new Font("Courier New", Font.PLAIN, 15));

		btnLevelEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				LevelEditorConfig le = new LevelEditorConfig(frame);
				le.getFrame().setVisible(true);

			}
		});
		frame.getContentPane().add(btnLevelEditor);

		btnExit = new JButton("Exit");
		btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnExit.setFont(new Font("Courier New", Font.PLAIN, 15));

		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		frame.getContentPane().add(btnExit);
	}

	public JFrame getFrame() {return this.frame;}
}
