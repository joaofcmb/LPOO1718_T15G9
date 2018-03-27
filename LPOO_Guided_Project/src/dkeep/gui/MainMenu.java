package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MainMenu {

	private JFrame frame = new JFrame("Main Menu");
	private JButton btnNewGame = new JButton("New Game");
	private JButton btnExit = new JButton("Exit");


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
		frame.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				
			}
		});
		frame.setResizable(false);
		frame.setBounds(100, 100, 800, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		initButtons();
		
		addToContentPane();
	}

	private void initButtons()
	{
		btnNewGame.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnNewGame.setBounds(300, 64, 120, 50);
		
		btnExit.setFont(new Font("Courier New", Font.PLAIN, 15));
		btnExit.setBounds(300, 521, 120, 50);
		
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(true); // hide this window
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				NewGameConfig ng = new NewGameConfig(frame);
				ng.getFrame().setVisible(true);

			}
		});

		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	private void addToContentPane()
	{
		frame.getContentPane().add(btnNewGame);
		frame.getContentPane().add(btnExit);
	}
	
	public JFrame getFrame() {return this.frame;}
}
