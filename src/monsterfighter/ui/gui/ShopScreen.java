package monsterfighter.ui.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;

import monsterfighter.core.GameEnvironment;
import monsterfighter.core.Item;
import javax.swing.JComboBox;

public class ShopScreen extends Screen{





	protected ShopScreen(GameEnvironment gameEnvironment) {
		super("Monster Fighter Shop", gameEnvironment);
		// TODO Auto-generated constructor stub
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
		container.setLayout(null);
		
		JLabel shopLabel = new JLabel("Shop");
		shopLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		shopLabel.setBounds(21, 11, 168, 43);
		container.add(shopLabel);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBack.setBounds(10, 358, 105, 42);
		container.add(btnBack);
		
		JButton btnBuy = new JButton("Buy");
		btnBuy.setBounds(419, 358, 105, 42);
		container.add(btnBuy);
		
		JList<ArrayList<Item>> shopList = new JList<ArrayList<Item>>();
		shopList.setVisibleRowCount(-1);
		shopList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		shopList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		shopList.setBackground(Color.WHITE);
		shopList.setBounds(10, 65, 514, 282);
		container.add(shopList);
		
		JLabel buyAmounLabel = new JLabel("How many would you like to buy?");
		buyAmounLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buyAmounLabel.setBounds(142, 371, 186, 14);
		container.add(buyAmounLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("");
		comboBox.setBounds(338, 368, 56, 22);
		container.add(comboBox);
	}



}
