package monsterfighter.ui.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

import monsterfighter.core.GameEnvironment;
import monsterfighter.core.Monster;

public class PartyScreen extends Screen{
	
	private String backButtonRoute;
	private String txtMonsterOne = "";
	private String txtMonsterTwo = "";
	private String txtMonsterThree = "";
	private String txtMonsterFour = "";
	private ButtonGroup buttonsMonsterOptions;
	
	protected PartyScreen(GameEnvironment gameEnvironment, String back) {
		super("Monster Fighter Party", gameEnvironment);
		backButtonRoute = back;
	}
	
	@Override
	protected void initialise(Container container) {
		container.setSize(550, 450);
		
		addlabels(container);
		addMonsterBtns(container);
		addOptionBtns(container);
	}

	private void addlabels(Container container) {
		
		JLabel partyLabel = new JLabel("Party");
		partyLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		partyLabel.setBounds(21, 11, 168, 43);
		container.add(partyLabel);
			
		JLabel lblSlotFourOne = new JLabel("Slot 1");
		lblSlotFourOne.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSlotFourOne.setBounds(79, 58, 46, 14);
		container.add(lblSlotFourOne);
		
		JLabel lblSlotFourTwo = new JLabel("Slot 2");
		lblSlotFourTwo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSlotFourTwo.setBounds(296, 58, 46, 14);
		container.add(lblSlotFourTwo);
		
		JLabel lblSlotFourThree = new JLabel("Slot 3");
		lblSlotFourThree.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSlotFourThree.setBounds(79, 204, 46, 14);
		container.add(lblSlotFourThree);
		
		JLabel lblSlotFour = new JLabel("Slot 4");
		lblSlotFour.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSlotFour.setBounds(296, 204, 46, 14);
		container.add(lblSlotFour);
	}
	

	private void addMonsterBtns(Container container) {

		if (getGameEnvironment().getParty().size()>=1) {
			txtMonsterOne = getGameEnvironment().getParty().get(0).getNickname();
		}
		JToggleButton btnMonsterOne = new JToggleButton(txtMonsterOne);
		if (txtMonsterOne==null) {
			btnMonsterOne.setEnabled(false);
		}
		btnMonsterOne.setBounds(69, 83, 182, 106);
		container.add(btnMonsterOne);
		
		if (getGameEnvironment().getParty().size()>=2) {
			txtMonsterTwo = getGameEnvironment().getParty().get(1).getNickname();
		}
		JToggleButton btnMonsterTwo = new JToggleButton(txtMonsterTwo);
		if (txtMonsterTwo==null) {
			btnMonsterTwo.setEnabled(false);
		}
		btnMonsterTwo.setBounds(282, 83, 182, 106);
		container.add(btnMonsterTwo);
		
		if (getGameEnvironment().getParty().size()>=3) {
			txtMonsterThree = getGameEnvironment().getParty().get(2).getNickname();
		}
		JToggleButton btnMonsterThree = new JToggleButton(txtMonsterThree);
		if (txtMonsterThree==null) {
			btnMonsterThree.setEnabled(false);
		}
		btnMonsterThree.setBounds(69, 229, 182, 106);
		container.add(btnMonsterThree);
		
		if (getGameEnvironment().getParty().size()==4) {
			txtMonsterFour = getGameEnvironment().getParty().get(3).getNickname();
		}
		JToggleButton btnMonsterFour = new JToggleButton(txtMonsterFour);
		if (txtMonsterFour==null) {
			btnMonsterFour.setEnabled(false);
		}
		btnMonsterFour.setBounds(282, 229, 182, 106);
		container.add(btnMonsterFour);
	}
	
	private void addOptionBtns(Container container) {
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> getGameEnvironment().transitionScreen(backButtonRoute, "PARTY_SCREEN"));
		btnBack.setBounds(10, 358, 105, 42);
		container.add(btnBack);
		
		JButton btnNickname = new JButton("Nickname");
		btnNickname.setBounds(304, 358, 105, 42);
		container.add(btnNickname);
		
		JButton btnUseItem = new JButton("Use Item");
		btnUseItem.setBounds(189, 358, 105, 42);
		container.add(btnUseItem);
		
		JButton btnSwitchMonsters = new JButton("Switch ");
		btnSwitchMonsters.setBounds(419, 358, 105, 42);
		container.add(btnSwitchMonsters);
	}
	
	private void buttonEnable() {

	}

}
