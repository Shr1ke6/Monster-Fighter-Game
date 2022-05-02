package monsterfighter.ui.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import monsterfighter.core.Monster;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class MainScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
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
	public MainScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("MF Main Menu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(490, 675);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel trainerLabel = new JLabel("Trainer:");
		trainerLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		trainerLabel.setBounds(10, 26, 61, 22);
		frame.getContentPane().add(trainerLabel);
		
		JLabel trainerNameLabel = new JLabel("New label");
		trainerNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		trainerNameLabel.setBounds(81, 30, 224, 14);
		frame.getContentPane().add(trainerNameLabel);
		
		JLabel dayLabel = new JLabel("Day:");
		dayLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dayLabel.setBounds(372, 23, 36, 29);
		frame.getContentPane().add(dayLabel);
		
		JLabel currentDayLabel = new JLabel("15");
		currentDayLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		currentDayLabel.setBounds(418, 31, 46, 14);
		frame.getContentPane().add(currentDayLabel);
		
		JLabel goldLabel = new JLabel("Gold:");
		goldLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		goldLabel.setBounds(10, 59, 46, 22);
		frame.getContentPane().add(goldLabel);
		
		JLabel currentGoldLabel = new JLabel("1000");
		currentGoldLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		currentGoldLabel.setBounds(66, 64, 46, 14);
		frame.getContentPane().add(currentGoldLabel);
		
		JLabel pointsLabel = new JLabel("Points:");
		pointsLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pointsLabel.setBounds(354, 63, 54, 14);
		frame.getContentPane().add(pointsLabel);
		
		JLabel currentPointsLabel = new JLabel("1500");
		currentPointsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		currentPointsLabel.setBounds(418, 64, 46, 14);
		frame.getContentPane().add(currentPointsLabel);
		
		JButton btnNewButton = new JButton("Battle");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(165, 128, 143, 73);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnShop = new JButton("Shop");
		btnShop.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnShop.setBounds(165, 212, 143, 73);
		frame.getContentPane().add(btnShop);
		
		JButton btnViewParty = new JButton("View Party");
		btnViewParty.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnViewParty.setBounds(165, 295, 143, 73);
		frame.getContentPane().add(btnViewParty);
		
		JButton btnInventory = new JButton("Inventory");
		btnInventory.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnInventory.setBounds(165, 378, 143, 73);
		frame.getContentPane().add(btnInventory);
		
		JButton btnRest = new JButton("Rest");
		btnRest.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRest.setBounds(165, 462, 143, 73);
		frame.getContentPane().add(btnRest);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(10, 583, 105, 42);
		frame.getContentPane().add(btnQuit);
		

	}
}
