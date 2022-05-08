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

	private JComboBox<Integer> comboBoxNumItems;
	private JList<ArrayList<Purchasable>> listShop;
	private JLabel lblBuyAmount;
	private JButton btnBuy;
	private JLabel lblPrice;
	private JLabel lblGold;
	private DefaultListModel<ArrayList<Purchasable>> listShopModel;

	protected ShopScreen(GameEnvironment gameEnvironment, String backButtonRoute) {
		super("Monster Fighter Shop", gameEnvironment, backButtonRoute);
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
		
		lblGold = new JLabel("Gold: " + getGameEnvironment().getGoldBalance());
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
		btnBack.addActionListener(e -> getGameEnvironment().transitionScreen(getBackButtonRoute(), "SHOP", true));
		btnBack.setBounds(10, 358, 105, 42);
		container.add(btnBack);
		
		btnBuy = new JButton("Buy");
		btnBuy.addActionListener(e -> {
				int shopSize = getGameEnvironment().getShop().size();
				for (int i = 0; i < (int)comboBoxNumItems.getSelectedItem(); i++) {
					getGameEnvironment().purchase(listShop.getSelectedValue().get(0));
				}
				lblGold.setText("Gold: " + getGameEnvironment().getGoldBalance());
				if (shopSize != getGameEnvironment().getShop().size()) {
					listShop.clearSelection();
					listShopModel.removeAllElements();
					listShopModel.addAll(getGameEnvironment().getShop());
				}
				getParentComponent().repaint();
				
		});
		btnBuy.setEnabled(false);
		btnBuy.setBounds(419, 358, 105, 42);
		container.add(btnBuy);
		
		JButton btnSellItem = new JButton("Item");
		btnSellItem.addActionListener(e -> getGameEnvironment().transitionScreen("INVENTORY", "SHOP", true));
		btnSellItem.setBounds(419, 11, 105, 42);
		container.add(btnSellItem);
		
		JButton btnSellMonster = new JButton("Monster");
		btnSellMonster.addActionListener(e -> getGameEnvironment().transitionScreen("PARTY", "SHOP", true));
		btnSellMonster.setBounds(304, 11, 105, 42);
		container.add(btnSellMonster);
	}
	
	private void addComboBox(Container container) {
		comboBoxNumItems = new JComboBox<Integer>();
		comboBoxNumItems.setVisible(false);
		comboBoxNumItems.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBoxNumItems.getSelectedItem()!=null) {
					int totalPrice = listShop.getSelectedValue().get(0).getBuyPrice() * (int)comboBoxNumItems.getSelectedItem();
					lblPrice.setText("Price: " + totalPrice);
					if (getGameEnvironment().getGoldBalance() < totalPrice) {
						btnBuy.setEnabled(false);
					} else {
						btnBuy.setEnabled(true);
					}
				}
			}
		});
	
		comboBoxNumItems.setBounds(337, 328, 56, 22);
		container.add(comboBoxNumItems);
		
	}
	
	private void addListShop(Container container) {

		listShopModel = new DefaultListModel<ArrayList<Purchasable>>();
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
				checkCanBuy();
			}
		});

		listShop.setBounds(10, 96, 514, 216);
		container.add(listShop);
		
	}
		
	private void checkCanBuy() {
		
		comboBoxNumItems.setVisible(listShop.getSelectedValue()!=null);
		if (listShop.getSelectedValue()!=null) {
			comboBoxNumItems.removeAllItems();
			for (int i = 1; i <= listShop.getSelectedValue().size(); i++) {
				comboBoxNumItems.addItem(i);
			}
			comboBoxNumItems.setSelectedIndex(0);
			lblPrice.setText("Price: " + listShop.getSelectedValue().get(0).getBuyPrice());
		}
		lblPrice.setVisible(listShop.getSelectedValue()!=null);
		lblBuyAmount.setVisible(listShop.getSelectedValue()!=null);
		btnBuy.setEnabled(listShop.getSelectedValue()!=null && getGameEnvironment().getGoldBalance() >= listShop.getSelectedValue().get(0).getBuyPrice());
	}




}
