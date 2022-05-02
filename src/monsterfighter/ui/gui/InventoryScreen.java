package monsterfighter.ui.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;

import monsterfighter.core.Item;
import monsterfighter.core.Monster;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.ListSelectionModel;

public class InventoryScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryScreen window = new InventoryScreen();
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
	public InventoryScreen() {
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
		
		JLabel inventroyLabel = new JLabel("Inventory");
		inventroyLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		inventroyLabel.setBounds(21, 11, 168, 43);
		frame.getContentPane().add(inventroyLabel);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBack.setBounds(10, 358, 105, 42);
		frame.getContentPane().add(btnBack);
		
		JButton btnUseItem = new JButton("Use Item");
		btnUseItem.setBounds(419, 358, 105, 42);
		frame.getContentPane().add(btnUseItem);
		
		JList<ArrayList<Item>> inventoryList = new JList<ArrayList<Item>>();
		inventoryList.setVisibleRowCount(-1);
		inventoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		inventoryList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		inventoryList.setBackground(Color.WHITE);
		inventoryList.setBounds(10, 65, 514, 282);
		frame.getContentPane().add(inventoryList);
	}

}
