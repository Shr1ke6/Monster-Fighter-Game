package monsterfighter.ui.gui;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import monsterfighter.core.GameEnvironment;
import monsterfighter.core.Monster;
import monsterfighter.core.RandomEvent;

import javax.swing.JButton;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainScreen extends Screen{
	
	private JLabel lblDay;
	private ArrayList<Monster> partyCopy;

	public MainScreen(GameEnvironment incomingGameEnvironment) {
		super("Monster Fighter Main Menu", incomingGameEnvironment, null);
	}
	
	@Override
	protected void initialise(Container container) {
		container.setSize(490, 675);
		addLabels(container);
		addBtns(container);
	}
	
	private void addLabels(Container container) { 
		JLabel lblTrainerLabel = new JLabel("Trainer: " + getGameEnvironment().getPlayer().getName());
		lblTrainerLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTrainerLabel.setBounds(10, 26, 300, 22);
		container.add(lblTrainerLabel);
		
		lblDay = new JLabel("Day: " + getGameEnvironment().getDay() + " / " + getGameEnvironment().getTotalDays());
		lblDay.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDay.setBounds(372, 23, 100, 29);
		container.add(lblDay);
			
		JLabel lblGold = new JLabel("Gold: " + getGameEnvironment().getPlayer().getGoldBalance());
		lblGold.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGold.setBounds(10, 59, 100, 22);
		container.add(lblGold);
		
		JLabel lblPoints = new JLabel("Points: " + getGameEnvironment().getPlayer().getPoints());
		lblPoints.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPoints.setBounds(354, 63, 100, 14);
		container.add(lblPoints);
	}
	
	private void addBtns(Container container) {
		JButton btnBattle = new JButton("Battle");
		btnBattle.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnBattle.setBounds(165, 128, 143, 73);
		btnBattle.addActionListener(e -> getGameEnvironment().transitionScreen("BATTLE_SELECT", "MAIN_MENU", true));
		container.add(btnBattle);

		JButton btnShop = new JButton("Shop");
		btnShop.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnShop.setBounds(165, 212, 143, 73);
		btnShop.addActionListener(e -> getGameEnvironment().transitionScreen("SHOP", "MAIN_MENU", true));
		container.add(btnShop);

		JButton btnViewParty = new JButton("View Party");
		btnViewParty.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnViewParty.setBounds(165, 295, 143, 73);
		btnViewParty.addActionListener(e -> getGameEnvironment().transitionScreen("PARTY", "MAIN_MENU", true));
		container.add(btnViewParty);

		JButton btnInventory = new JButton("Inventory");
		btnInventory.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnInventory.setBounds(165, 378, 143, 73);
		btnInventory.addActionListener(e -> getGameEnvironment().transitionScreen("INVENTORY", "MAIN_MENU", true));
		container.add(btnInventory);

		JButton btnRest = new JButton("Rest");
		btnRest.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRest.setBounds(165, 462, 143, 73);
		btnRest.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				boolean lastDay = getGameEnvironment().getDay()==getGameEnvironment().getTotalDays();
				if (lastDay) {
					getGameEnvironment().transitionScreen("GAME_OVER", "MAIN_MENU", true);
				} else {
					partyCopy = new ArrayList<Monster>();
					for (Monster monster: getGameEnvironment().getPlayer().getParty()) {
						partyCopy.add(monster);
					}
					getGameEnvironment().nextDay();
					optionPanelRandomEvent();
					lastDay = getGameEnvironment().getDay()==getGameEnvironment().getTotalDays();
					if (lastDay) {
						btnRest.setText("End Game");
					}
					lblDay.setText("Day: " + getGameEnvironment().getDay() + " / " + getGameEnvironment().getTotalDays());
				}
			}
		});
		container.add(btnRest);

		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(10, 583, 105, 42);
		btnQuit.addActionListener(e -> getGameEnvironment().onFinish());
		container.add(btnQuit);
	}
	
	private void optionPanelRandomEvent() {
		final JButton btnOkay = new JButton("Okay");
		JLabel lblMessage = new JLabel();
		String message = "<html>";
		
		btnOkay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.getRootFrame().dispose();
			}
			
		});
		
		RandomEvent randomEvents = getGameEnvironment().getRandomEvent();
		if (getGameEnvironment().getPlayer().getParty().size()>0) {
			message += "Your Monsters received a good night's rest and healed to full health.<br>";
		} for (int i = 0; i < partyCopy.size(); i++) {
			if (randomEvents.getMonsterLeaves().get(i)) {
				message += partyCopy.get(i).getNickname() +
						" left your party overnight. Best of luck " + getGameEnvironment().getPlayer().getParty().get(i).getNickname() + ".<br>";
			} else if (randomEvents.getLevelUp().get(i) && !randomEvents.getMonsterLeaves().get(i)) {
				message += partyCopy.get(i).getNickname() + "'s stats increased overnight.<br>";
			} 
		}
		if (randomEvents.getMonsterJoins()) {
			message += "A monster decided to join your party overnight.<br>";
		}
		if (message.length()==6) {
			message += "Nothing of note occured overnight.<br>";
		}
		message+="</html>";
		lblMessage.setText(message);
		
		JOptionPane.showOptionDialog(null,  new Object[] {lblMessage}, "Nightly Happenings", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, new JButton[]
		        {btnOkay}, btnOkay);
	}
	

}
