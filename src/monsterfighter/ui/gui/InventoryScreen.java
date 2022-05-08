package monsterfighter.ui.gui;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JList;

import monsterfighter.core.GameEnvironment;
import monsterfighter.core.Item;
import monsterfighter.core.Monster;

import java.awt.Color;
import java.awt.Container;

import javax.swing.border.EtchedBorder;
import javax.swing.ListSelectionModel;

public class InventoryScreen extends Screen{

	private JList<ArrayList<Item>> listInventory;
	private DefaultListModel<ArrayList<Item>> inventoryListModel;
	private JButton btnUseItem;
	private Monster selectedMonster;
	private JLabel lblMonster;
	private JLabel lblHealth;
	private JLabel lblAttack;
	

	protected InventoryScreen(GameEnvironment gameEnvironment, String backButtonRoute) {
		super("Monster Fighter Inventory", gameEnvironment, backButtonRoute);
	}
	

	/**
	 * Initialize the contents of the container.
	 */
	@Override
	protected void initialise(Container container) {
		container.setSize(550, 450);
		
		if (getBackButtonRoute().equals("PARTY")) {
			selectedMonster = (Monster)getGameEnvironment().getSelectedObject();
		} else if (getBackButtonRoute().equals("BATTLE")) {
			selectedMonster = getGameEnvironment().getParty().get(0);
		}
		
		addLabelInventory(container);
		addBtns(container);
		addListInventory(container);
		if (getBackButtonRoute().equals("PARTY") || getBackButtonRoute().equals("BATTLE")) {
			addLabelMonster(container);
		}
	}
	
	private void addLabelInventory(Container container) {
		JLabel inventroyLabel = new JLabel("Inventory");
		inventroyLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		inventroyLabel.setBounds(21, 11, 168, 43);
		container.add(inventroyLabel);
	}
	
	private void addLabelMonster(Container container) {
		lblMonster = new JLabel();
		lblMonster.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMonster.setBounds(125, 312, 284, 54);
		container.add(lblMonster);
		
		lblHealth = new JLabel();
		lblHealth.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHealth.setBounds(125, 358, 225, 23);
		container.add(lblHealth);
		
		lblAttack = new JLabel();
		lblAttack.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAttack.setBounds(125, 377, 225, 23);
		container.add(lblAttack);
		
		setTextLabelMonster();
	}
	
	private void setTextLabelMonster() {
		String txtMonsterName = "Monster: " + selectedMonster.getNickname();
		if (selectedMonster.getStatus().equals(Monster.Status.FAINTED)) {
			txtMonsterName += "[FAINTED]";
		}
		lblMonster.setText(txtMonsterName);
		lblHealth.setText("Health: " + selectedMonster.getCurrentHealth() + "/" + selectedMonster.getMaxHealth());
		lblAttack.setText("Attack: " + selectedMonster.getAttack());
	}
	
	private void addBtns(Container container) {
		btnUseItem = new JButton("Use Item");
		btnUseItem.setEnabled(false);
		btnUseItem.addActionListener(e -> {
			
		if (getBackButtonRoute().equals("PARTY") || getBackButtonRoute().equals("BATTLE")) {
				int inventorySize = getGameEnvironment().getInventory().size();
				getGameEnvironment().useItem(selectedMonster, listInventory.getSelectedValue().get(0));
				if (inventorySize != getGameEnvironment().getInventory().size()) {
					listInventory.clearSelection();
					inventoryListModel.removeAllElements();
					inventoryListModel.addAll(getGameEnvironment().getInventory());
				}
				setTextLabelMonster();
		} else {
			getGameEnvironment().setSelectedObject(listInventory.getSelectedValue().get(0));
			getGameEnvironment().transitionScreen("PARTY", "INVENTORY", false);
			
		}});
		btnUseItem.setBounds(419, 358, 105, 42);
		container.add(btnUseItem);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> {
		if (getBackButtonRoute().equals("PARTY")) {
			getGameEnvironment().transitionScreen(getBackButtonRoute(), "MAIN_MENU", true);
			getGameEnvironment().setSelectedObject(null);
		} else if (getBackButtonRoute().equals("BATTLE")) {
			getGameEnvironment().transitionScreen(getBackButtonRoute(), "BATTLE_SELECT", true);
		} else {
			getGameEnvironment().transitionScreen(getBackButtonRoute(), "INVENTORY", true);
		}});
		btnBack.setBounds(10, 358, 105, 42);
		container.add(btnBack);
	}
	
	private void addListInventory(Container container) {
		
		inventoryListModel = new DefaultListModel<ArrayList<Item>>();
		// Add the existing difficulties to the ListModel
		inventoryListModel.addAll(getGameEnvironment().getInventory());
		
		listInventory = new JList<ArrayList<Item>>(inventoryListModel);
		listInventory.setCellRenderer(new InventoryRenderer());
		listInventory.setVisibleRowCount(-1);
		listInventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listInventory.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listInventory.setBackground(Color.WHITE);
		listInventory.addListSelectionListener(e -> btnUseItem.setEnabled(true));
		if (selectedMonster!=null) {
			listInventory.setBounds(10, 65, 514, 247);
		} else {
			listInventory.setBounds(10, 65, 514, 282);
		}
		container.add(listInventory);
	}

}
