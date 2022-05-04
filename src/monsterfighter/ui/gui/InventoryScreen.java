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
import java.awt.Color;
import java.awt.Container;

import javax.swing.border.EtchedBorder;
import javax.swing.ListSelectionModel;

public class InventoryScreen extends Screen{

	private JList<ArrayList<Item>> listInventory;

	protected InventoryScreen(GameEnvironment gameEnvironment) {
		super("Monster Fighter Inventory", gameEnvironment);
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
		
		addLabelInventory(container);
		addBtns(container);
		addListInventory(container);

	}
	
	private void addLabelInventory(Container container) {
		JLabel inventroyLabel = new JLabel("Inventory");
		inventroyLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		inventroyLabel.setBounds(21, 11, 168, 43);
		container.add(inventroyLabel);
	}
	
	private void addBtns(Container container) {
		JButton btnUseItem = new JButton("Use Item");
		btnUseItem.setBounds(419, 358, 105, 42);
		container.add(btnUseItem);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> getGameEnvironment().transitionScreen("MAIN_MENU"));
		btnBack.setBounds(10, 358, 105, 42);
		container.add(btnBack);
	}
	
	private void addListInventory(Container container) {
		
		DefaultListModel<ArrayList<Item>> difficultyListModel = new DefaultListModel<ArrayList<Item>>();
		// Add the existing difficulties to the ListModel
		difficultyListModel.addAll(getGameEnvironment().getInventory());
		
		listInventory = new JList<ArrayList<Item>>(difficultyListModel);
		listInventory.setCellRenderer(null);
		listInventory.setVisibleRowCount(-1);
		listInventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listInventory.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listInventory.setBackground(Color.WHITE);
		listInventory.setBounds(10, 65, 514, 282);
		container.add(listInventory);
	}

}
