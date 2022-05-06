package monsterfighter.ui.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.MutableComboBoxModel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import monsterfighter.core.GameEnvironment;
import monsterfighter.core.Item;
import monsterfighter.core.Purchasable;

import javax.swing.JComboBox;

public class ShopScreen extends Screen{

	private String backButtonRoute;
	private JComboBox<Integer> comboBoxNumItems;
	private JList<ArrayList<Purchasable>> listShop;
	private JLabel lblBuyAmount;
	private JButton btnBuy;
	private JLabel lblPrice;

	protected ShopScreen(GameEnvironment gameEnvironment, String back) {
		super("Monster Fighter Shop", gameEnvironment);
		backButtonRoute = back;
	}

	@Override
	protected void initialise(Container container) {
		container.setSize(550, 450);
		
		addLabels(container);
		addButtons(container);
		addComboBox(container);
		addListShop(container);
	}

	private void addLabels(Container container) {
		
		JLabel lblShop = new JLabel("Shop");
		lblShop.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblShop.setBounds(21, 11, 56, 43);
		container.add(lblShop);
		
		lblBuyAmount = new JLabel("How many would you like to buy?");
		lblBuyAmount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBuyAmount.setVisible(false);
		lblBuyAmount.setBounds(140, 331, 186, 14);
		container.add(lblBuyAmount);
		
		JLabel lblSell = new JLabel("Sell:");
		lblSell.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSell.setBounds(255, 11, 39, 43);
		container.add(lblSell);
		
		JLabel lblGold = new JLabel("Gold: " + getGameEnvironment().getGoldBalance());
		lblGold.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGold.setBounds(21, 48, 225, 43);
		container.add(lblGold);
		
		
		lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrice.setVisible(false);
		lblPrice.setBounds(221, 356, 92, 43);
		container.add(lblPrice);
		
	}
	
	private void addButtons(Container container) {
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> getGameEnvironment().transitionScreen(backButtonRoute, "SHOP"));
		btnBack.setBounds(10, 358, 105, 42);
		container.add(btnBack);
		
		btnBuy = new JButton("Buy");
		btnBuy.setEnabled(false);
		btnBuy.setBounds(419, 358, 105, 42);
		container.add(btnBuy);
		
		JButton btnItem = new JButton("Item");
		btnItem.setBounds(419, 11, 105, 42);
		container.add(btnItem);
		
		JButton btnMonster = new JButton("Monster");
		btnMonster.setBounds(304, 11, 105, 42);
		container.add(btnMonster);
	}
	
	private void addComboBox(Container container) {
		comboBoxNumItems = new JComboBox<Integer>();
		comboBoxNumItems.setToolTipText("");
		comboBoxNumItems.setVisible(false);
		comboBoxNumItems.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lblPrice.setText("Price: " + (listShop.getSelectedValue().get(0).getBuyPrice() * (Integer)comboBoxNumItems.getSelectedItem()));
			}
		});
	
		comboBoxNumItems.setBounds(337, 328, 56, 22);
		container.add(comboBoxNumItems);
		
	}
	
	private void addListShop(Container container) {

		DefaultListModel<ArrayList<Purchasable>> listShopModel = new DefaultListModel<ArrayList<Purchasable>>();
		// Add the existing difficulties to the ListModel
		listShopModel.addAll(getGameEnvironment().getShop());
		
		listShop = new JList<ArrayList<Purchasable>>(listShopModel);
		listShop.setVisibleRowCount(-1);
		listShop.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listShop.setCellRenderer(new ShopRenderer());
		listShop.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listShop.setBackground(Color.WHITE);
		listShop.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				lblBuyAmount.setVisible(true);
				comboBoxNumItems.setVisible(true);
				/*
				List<Integer> range = IntStream.rangeClosed(0, listShop.getSelectedValue().size())
					    .boxed().collect(Collectors.toList());
				comboBoxNumItems.setModel((ComboBoxModel<Integer>) range);
				*/
				lblPrice.setVisible(true);
				lblPrice.setText("Price: " + listShop.getSelectedValue().get(0).getBuyPrice());
				btnBuy.setEnabled(true);
			}
		});

		listShop.setBounds(10, 96, 514, 216);
		container.add(listShop);
		
	}
		




}
