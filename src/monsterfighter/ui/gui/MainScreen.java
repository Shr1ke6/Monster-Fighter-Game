package monsterfighter.ui.gui;



import javax.swing.JFrame;
import javax.swing.JLabel;


import monsterfighter.core.GameEnvironment;


import javax.swing.JButton;

import java.awt.Container;
import java.awt.Font;

public class MainScreen extends Screen{
	
	private JLabel lblDay;

	public MainScreen(GameEnvironment incomingGameEnvironment) {
		super("Monster Fighter Main Menu", incomingGameEnvironment);
	}
	
	@Override
	protected void initialise(Container container) {
		container.setSize(490, 675);
		addLabels(container);
		addBtns(container);
	}
	
	private void addLabels(Container container) { 
		JLabel lblTrainerLabel = new JLabel("Trainer: " + getGameEnvironment().getName());
		lblTrainerLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTrainerLabel.setBounds(10, 26, 300, 22);
		container.add(lblTrainerLabel);
		
		lblDay = new JLabel("Day: " + getGameEnvironment().getDay() + " / " + getGameEnvironment().getTotalDays());
		lblDay.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDay.setBounds(372, 23, 100, 29);
		container.add(lblDay);
			
		JLabel lblGold = new JLabel("Gold: " + getGameEnvironment().getGoldBalance());
		lblGold.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGold.setBounds(10, 59, 100, 22);
		container.add(lblGold);
		
		JLabel lblPoints = new JLabel("Points: " + getGameEnvironment().getPoints());
		lblPoints.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPoints.setBounds(354, 63, 100, 14);
		container.add(lblPoints);
	}
	
	private void addBtns(Container container) {
		JButton btnBattle = new JButton("Battle");
		btnBattle.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnBattle.setBounds(165, 128, 143, 73);
		btnBattle.addActionListener(e -> getGameEnvironment().transitionScreen("BATTLE_SELECT"));
		container.add(btnBattle);

		JButton btnShop = new JButton("Shop");
		btnShop.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnShop.setBounds(165, 212, 143, 73);
		btnShop.addActionListener(e -> getGameEnvironment().transitionScreen("SHOP"));
		container.add(btnShop);

		JButton btnViewParty = new JButton("View Party");
		btnViewParty.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnViewParty.setBounds(165, 295, 143, 73);
		btnViewParty.addActionListener(e -> getGameEnvironment().transitionScreen("PARTY"));
		container.add(btnViewParty);

		JButton btnInventory = new JButton("Inventory");
		btnInventory.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnInventory.setBounds(165, 378, 143, 73);
		btnInventory.addActionListener(e -> getGameEnvironment().transitionScreen("INVENTORY"));
		container.add(btnInventory);

		JButton btnRest = new JButton("Rest");
		btnRest.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRest.setBounds(165, 462, 143, 73);
		btnRest.addActionListener(e -> getGameEnvironment().nextDay());
		btnRest.addActionListener(e -> lblDay.setText("Day: " + getGameEnvironment().getDay() + "/" + getGameEnvironment().getTotalDays()));
		container.add(btnRest);

		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(10, 583, 105, 42);
		btnQuit.addActionListener(e -> getGameEnvironment().onFinish());
		container.add(btnQuit);
	}
	

}
