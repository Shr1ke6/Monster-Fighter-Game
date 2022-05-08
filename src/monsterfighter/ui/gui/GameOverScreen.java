package monsterfighter.ui.gui;

import java.awt.Container;
import java.awt.EventQueue;

import monsterfighter.core.GameEnvironment;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class GameOverScreen extends Screen{


	/**
	 * Create the application.
	 */
	public GameOverScreen(GameEnvironment gameEnvironment) {
		super("Game Over Screen", gameEnvironment, null);
	}
	
	@Override
	protected void initialise(Container container) {
		initialize(container);
		
	}

	/**
	 * Initialize the contents of the container.
	 */
	private void initialize(Container container) {
		container.setSize(550, 450);
		
		JLabel gameOverLabel = new JLabel("Game Over");
		gameOverLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		gameOverLabel.setBounds(217, 11, 100, 80);
		container.add(gameOverLabel);
		
		JLabel lblPoints = new JLabel("Points:");
		lblPoints.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPoints.setBounds(40, 171, 167, 30);
		container.add(lblPoints);
		
		JLabel lblTotalGold = new JLabel("Total Gold:");
		lblTotalGold.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTotalGold.setBounds(40, 238, 167, 30);
		container.add(lblTotalGold);
		
		JLabel lblDays = new JLabel("Days");
		lblDays.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDays.setBounds(40, 304, 167, 30);
		container.add(lblDays);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setBounds(40, 101, 167, 30);
		container.add(lblName);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(419, 358, 105, 42);
		container.add(btnQuit);
	}


}
