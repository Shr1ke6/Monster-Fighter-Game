package monsterfighter.ui.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;

import monsterfighter.core.GameEnvironment;
import monsterfighter.core.Monster;

public class PartyScreen extends Screen{


	protected PartyScreen(GameEnvironment gameEnvironment) {
		super("Monster Fighter Party", gameEnvironment);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void initialise(Container container) {
		// TODO Auto-generated method stub
		initialize(container);
	}

	/**
	 * Initialize the contents of the container.
	 */
	private void initialize(Container container) {
		container.setSize(550, 450);

		JLabel partyLabel = new JLabel("Party");
		partyLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		partyLabel.setBounds(21, 11, 168, 43);
		container.add(partyLabel);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBack.setBounds(10, 358, 105, 42);
		container.add(btnBack);
		
		JButton btnUseItem = new JButton("Nickname");
		btnUseItem.setBounds(419, 358, 105, 42);
		container.add(btnUseItem);
		
		JButton btnMonsterOne = new JButton("Monster1");
		btnMonsterOne.setBounds(69, 83, 182, 106);
		container.add(btnMonsterOne);
		
		JButton btnMonsterTwo = new JButton("Monster2");
		btnMonsterTwo.setBounds(282, 83, 182, 106);
		container.add(btnMonsterTwo);
		
		JButton btnMonsterThree = new JButton("Monster3");
		btnMonsterThree.setBounds(282, 229, 182, 106);
		container.add(btnMonsterThree);
		
		JButton btnMonsterFour = new JButton("Monster4");
		btnMonsterFour.setBounds(69, 229, 182, 106);
		container.add(btnMonsterFour);
		
		JLabel slot1Label = new JLabel("Slot 1");
		slot1Label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		slot1Label.setBounds(79, 58, 46, 14);
		container.add(slot1Label);
		
		JLabel lblSlot_1 = new JLabel("Slot 2");
		lblSlot_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSlot_1.setBounds(296, 58, 46, 14);
		container.add(lblSlot_1);
		
		JLabel slot2Label = new JLabel("Slot 3");
		slot2Label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		slot2Label.setBounds(79, 204, 46, 14);
		container.add(slot2Label);
		
		JLabel lblSlot = new JLabel("Slot 4");
		lblSlot.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSlot.setBounds(296, 204, 46, 14);
		container.add(lblSlot);
		
		JButton btnUseItem_1 = new JButton("Use Item");
		btnUseItem_1.setBounds(304, 358, 105, 42);
		container.add(btnUseItem_1);
		
		JButton btnSwitchMonsters = new JButton("Switch ");
		btnSwitchMonsters.setBounds(189, 358, 105, 42);
		container.add(btnSwitchMonsters);
	}


}
