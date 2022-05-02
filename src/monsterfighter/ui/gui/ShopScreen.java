package monsterfighter.ui.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;

import monsterfighter.core.Item;
import javax.swing.JComboBox;

public class ShopScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopScreen window = new ShopScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ShopScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("MF Inventory Screen");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(550, 450);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel shopLabel = new JLabel("Shop");
		shopLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		shopLabel.setBounds(21, 11, 168, 43);
		frame.getContentPane().add(shopLabel);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBack.setBounds(10, 358, 105, 42);
		frame.getContentPane().add(btnBack);
		
		JButton btnBuy = new JButton("Buy");
		btnBuy.setBounds(419, 358, 105, 42);
		frame.getContentPane().add(btnBuy);
		
		JList<ArrayList<Item>> shopList = new JList<ArrayList<Item>>();
		shopList.setVisibleRowCount(-1);
		shopList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		shopList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		shopList.setBackground(Color.WHITE);
		shopList.setBounds(10, 65, 514, 282);
		frame.getContentPane().add(shopList);
		
		JLabel buyAmounLabel = new JLabel("How many would you like to buy?");
		buyAmounLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buyAmounLabel.setBounds(142, 371, 186, 14);
		frame.getContentPane().add(buyAmounLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("");
		comboBox.setBounds(338, 368, 56, 22);
		frame.getContentPane().add(comboBox);
	}

}
