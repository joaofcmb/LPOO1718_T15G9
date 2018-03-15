package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.*;

public class DungeonWindow {

	private JFrame frame;
	private JTextField ogreTxt;
	JLabel lblGamestatus;

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
		frame.setBounds(100, 100, 629, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel ogreLabel = new JLabel("Number of Ogres");
		ogreLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		ogreLabel.setBounds(10, 10, 128, 27);
		ogreLabel.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(ogreLabel);

		JLabel guardLabel = new JLabel("Guard Personality");
		guardLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		guardLabel.setBounds(10, 52,  128, 27);
		frame.getContentPane().add(guardLabel);


		JComboBox<String> guardCombo = new JComboBox<String>();
		guardCombo.addItem("Rookie");
		guardCombo.addItem("Drunkard");
		guardCombo.addItem("Suspicious");
		guardCombo.setBounds(126, 53, 243, 27);
		frame.getContentPane().add(guardCombo);


		JTextArea txtGame = new JTextArea();
		txtGame.setBounds(10, 94,  359, 375);
		txtGame.setFont(new Font("Courier New", Font.PLAIN, 13));
		txtGame.setRows(12);
		txtGame.setColumns(12);
		txtGame.setEditable(false);
		frame.getContentPane().add(txtGame);

		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}

		});
		exitButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		exitButton.setBounds(407, 422,  186, 41);
		frame.getContentPane().add(exitButton);

		JButton upButton = new JButton("UP");
		upButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/*ACTION ON CLICK*/
			}

		});
		upButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		upButton.setBounds(437, 163, 103, 56);
		upButton.setEnabled(false);
		frame.getContentPane().add(upButton);

		JButton leftButton = new JButton("LEFT");
		leftButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/*ACTION ON CLICK*/
			}

		});
		leftButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		leftButton.setBounds(379, 240, 92, 56);
		leftButton.setEnabled(false);
		frame.getContentPane().add(leftButton);

		JButton downButton = new JButton("DOWN");
		downButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/*ACTION ON CLICK*/
			}

		});
		downButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		downButton.setBounds(437, 315, 103, 56);
		downButton.setEnabled(false);
		frame.getContentPane().add(downButton);

		JButton rightButton = new JButton("RIGHT");
		rightButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/*ACTION ON CLICK*/
			}

		});
		rightButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rightButton.setBounds(514, 240, 92, 56);
		rightButton.setEnabled(false);
		frame.getContentPane().add(rightButton);

		lblGamestatus = new JLabel("GAME_STATUS");
		lblGamestatus.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblGamestatus.setBounds(10, 479, 411, 22);
		frame.getContentPane().add(lblGamestatus);

		JButton startButton = new JButton("New Game");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer ogreNumber;
				String guardPersonality;
				try {
					ogreNumber = Integer.parseUnsignedInt(ogreTxt.getText());
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "Must introduce a positive integer number");
					return;
				}
				guardPersonality = (String) guardCombo.getSelectedItem();

				/* For future use	*/
				upButton.setEnabled(true);
				leftButton.setEnabled(true);
				downButton.setEnabled(true);
				rightButton.setEnabled(true);
				/**/
				return;
				/* TODO
				 * ChangeStatus
				 * startGame(ogreNumber, guardPersonality);
				 */
			}

		});
		startButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		startButton.setBounds(407, 82,  186, 41);
		frame.getContentPane().add(startButton);

		ogreTxt = new JTextField();
		ogreTxt.setBounds(126, 11,  67, 27);
		frame.getContentPane().add(ogreTxt);
		ogreTxt.setColumns(10);
	}
}
