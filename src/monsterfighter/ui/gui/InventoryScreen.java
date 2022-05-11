package monsterfighter.ui.gui;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
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
	private JButton btnSellItem;
	private Monster selectedMonster;
	private JLabel lblMonster;
	private JLabel lblHealth;
	private JLabel lblAttack;
	private JComboBox<Integer> comboBoxNumItems;
	private JLabel lblSellAmount;
	private JLabel lblSellPrice;
	private JLabel lblGold;
	private List<AbstractButton> listOptionButtons;
	

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
			selectedMonster = getGameEnvironment().getPlayer().getParty().get(0);
		}
		
		addLabelInventory(container);
		addBtns(container);
		addListInventory(container);
		if (getBackButtonRoute().equals("PARTY") || getBackButtonRoute().equals("BATTLE")) {
			addLabelsMonster(container);
		} else if (getBackButtonRoute().equals("SHOP")) {
			addComboBox(container);
			addLabelsSell(container);
		}
	}
	
	private void addLabelsSell(Container container) {
		
		lblSellAmount = new JLabel("How many would you like to sell?");
		lblSellAmount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSellAmount.setVisible(false);
		lblSellAmount.setBounds(140, 331, 186, 14);
		container.add(lblSellAmount);
				
		lblSellPrice = new JLabel("Sell Price:");
		lblSellPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSellPrice.setVisible(false);
		lblSellPrice.setBounds(198, 356, 137, 43);
		container.add(lblSellPrice);
		
		lblGold = new JLabel("Gold: " + getGameEnvironment().getPlayer().getGoldBalance());
		lblGold.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGold.setBounds(411, 11, 113, 43);
		container.add(lblGold);	
	}


	private void addLabelInventory(Container container) {
		JLabel inventroyLabel = new JLabel("Inventory");
		inventroyLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		inventroyLabel.setBounds(21, 11, 168, 43);
		container.add(inventroyLabel);
	}
	
	private void addLabelsMonster(Container container) {
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
		listOptionButtons = new ArrayList<AbstractButton>();
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> {
		if (getBackButtonRoute().equals("PARTY") || getBackButtonRoute().equals("SHOP")) {
			getGameEnvironment().transitionScreen(getBackButtonRoute(), "MAIN_MENU", true);
			getGameEnvironment().setSelectedObject(null);
		} else if (getBackButtonRoute().equals("BATTLE")) {
			getGameEnvironment().transitionScreen(getBackButtonRoute(), "BATTLE_SELECT", true);
		} else {
			getGameEnvironment().transitionScreen(getBackButtonRoute(), "INVENTORY", true);
		}
		});
		btnBack.setBounds(10, 358, 105, 42);
		container.add(btnBack);
		
		if (getBackButtonRoute().equals("SHOP")) {
			btnSellItem = new JButton("Sell");
			btnSellItem.setEnabled(false);
			btnSellItem.addActionListener(e -> {
				int inventorySize = getGameEnvironment().getInventoryUI().size();
				for (int i = 0; i < (int)comboBoxNumItems.getSelectedItem(); i++) {
					getGameEnvironment().sellItem(listInventory.getSelectedValue().get(0));
				}
				lblGold.setText("Gold: " + getGameEnvironment().getPlayer().getGoldBalance());
				if (inventorySize != getGameEnvironment().getInventoryUI().size()) {
					listInventory.clearSelection();
					inventoryListModel.removeAllElements();
					inventoryListModel.addAll(getGameEnvironment().getInventoryUI());
				}
				getParentComponent().repaint();
				sellDisplay();
		});
			btnSellItem.setBounds(419, 358, 105, 42);
			container.add(btnSellItem);
			listOptionButtons.add(btnSellItem);
		} else {
			btnUseItem = new JButton("Use Item");
			btnUseItem.setEnabled(false);
			btnUseItem.addActionListener(e -> {
			if (getBackButtonRoute().equals("PARTY") || getBackButtonRoute().equals("BATTLE")) {
				int inventorySize = getGameEnvironment().getInventoryUI().size();
				getGameEnvironment().useItem(selectedMonster, listInventory.getSelectedValue().get(0));
				if (inventorySize != getGameEnvironment().getInventoryUI().size()) {
					listInventory.clearSelection();
					inventoryListModel.removeAllElements();
					inventoryListModel.addAll(getGameEnvironment().getInventoryUI());
				}
				setTextLabelMonster();
			} else {
				getGameEnvironment().setSelectedObject(listInventory.getSelectedValue().get(0));
				getGameEnvironment().transitionScreen("PARTY", "INVENTORY", false);
				
			}});
			btnUseItem.setBounds(419, 358, 105, 42);
			container.add(btnUseItem);
			listOptionButtons.add(btnUseItem);
		}	
	}
	
	private void addListInventory(Container container) {
		
		inventoryListModel = new DefaultListModel<ArrayList<Item>>();
		// Add the existing difficulties to the ListModel
		inventoryListModel.addAll(getGameEnvironment().getInventoryUI());
		
		listInventory = new JList<ArrayList<Item>>(inventoryListModel);
		listInventory.setCellRenderer(new InventoryRenderer());
		listInventory.setVisibleRowCount(-1);
		listInventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listInventory.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listInventory.setBackground(Color.WHITE);
		listInventory.addListSelectionListener(e -> {
			for (AbstractButton button: listOptionButtons) {
				button.setEnabled(listInventory.getSelectedValue()!=null);
				if (getBackButtonRoute().equals("SHOP")) {
					sellDisplay();
				}
			}
		});
		if (selectedMonster!=null || getBackButtonRoute().equals("SHOP")) {
			listInventory.setBounds(10, 65, 514, 247);
		} else {
			listInventory.setBounds(10, 65, 514, 282);
		}
		container.add(listInventory);
	}
	
	private void addComboBox(Container container) {
		comboBoxNumItems = new JComboBox<Integer>();
		comboBoxNumItems.setVisible(false);
		comboBoxNumItems.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBoxNumItems.getSelectedItem()!=null) {
					int totalPrice = listInventory.getSelectedValue().get(0).getSellPrice() * (int)comboBoxNumItems.getSelectedItem();
					lblSellPrice.setText("Sell Price: " + totalPrice);
				}
			}
		});
		comboBoxNumItems.setBounds(337, 328, 56, 22);
		container.add(comboBoxNumItems);
	}
	
	private void sellDisplay() {
		
		comboBoxNumItems.setVisible(listInventory.getSelectedValue()!=null);
		if (listInventory.getSelectedValue()!=null) {
			comboBoxNumItems.removeAllItems();
			for (int i = 1; i <= listInventory.getSelectedValue().size(); i++) {
				comboBoxNumItems.addItem(i);
			}
			comboBoxNumItems.setSelectedIndex(0);
			lblSellPrice.setText("Sell Price: " + listInventory.getSelectedValue().get(0).getSellPrice());
		}
		lblSellPrice.setVisible(listInventory.getSelectedValue()!=null);
		lblSellAmount.setVisible(listInventory.getSelectedValue()!=null);
		btnSellItem.setEnabled(listInventory.getSelectedValue()!=null);
	}
}
