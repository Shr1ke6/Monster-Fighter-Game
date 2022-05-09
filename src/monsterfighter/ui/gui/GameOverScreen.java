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
		container.setSize(550, 450);
		
		addLabels(container);
		addBtnQuit(container);
		
	}

	private void addLabels(Container container) {
		JLabel gameOverLabel = new JLabel("Game Over");
		gameOverLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		gameOverLabel.setBounds(217, 11, 100, 80);
		container.add(gameOverLabel);
		
		JLabel lblPoints = new JLabel("Points: " + getGameEnvironment().getPoints());
		lblPoints.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPoints.setBounds(40, 171, 167, 30);
		container.add(lblPoints);
		
		JLabel lblTotalGold = new JLabel("Total Gold: " + getGameEnvironment().getTotalGold());
		lblTotalGold.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTotalGold.setBounds(40, 238, 167, 30);
		container.add(lblTotalGold);
		
		JLabel lblDays = new JLabel("Day: " + getGameEnvironment().getDay() + "/" + getGameEnvironment().getTotalDays());
		lblDays.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDays.setBounds(40, 304, 167, 30);
		container.add(lblDays);
		
		JLabel lblName = new JLabel("Name: " + getGameEnvironment().getName());
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setBounds(40, 101, 167, 30);
		container.add(lblName);
	}

	private void addBtnQuit(Container container) {
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(e -> quit());
		btnQuit.setBounds(419, 358, 105, 42);
		container.add(btnQuit);
	}


}
