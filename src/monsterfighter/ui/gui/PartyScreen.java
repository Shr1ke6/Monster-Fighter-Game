package monsterfighter.ui.gui;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import monsterfighter.core.GameEnvironment;
import monsterfighter.core.Item;
import monsterfighter.core.Monster;
import monsterfighter.ui.GameEnvironmentUi;

public class PartyScreen extends Screen{
	
	private JButton btnNickname;
	private JButton btnUseItem;
	private JToggleButton btnSwitchMonsters;
	private JButton btnSellMonster;
	private JToggleButton btnMonsterOne;
	private JToggleButton btnMonsterTwo;
	private JToggleButton btnMonsterThree;
	private JToggleButton btnMonsterFour;
	private JToggleButton btnSelectedMonster;
	private Monster selectedMonster;
	private Monster selectedMonsterSwitch;
	private Item selectedItem;
	private List<AbstractButton> listOptionButtons;
	private ButtonGroup buttonGroupPartyMonsters;
	private JLabel lblGold;
	private JLabel lblSellPrice;
	
	
	protected PartyScreen(GameEnvironment gameEnvironment, String backButtonRoute) {
		super("Monster Fighter Party", gameEnvironment, backButtonRoute);
	}
	
	@Override
	protected void initialise(Container container) {
		container.setSize(550, 450);
		
		if (getBackButtonRoute().equals("INVENTORY")) {
			selectedItem = (Item)getGameEnvironment().getSelectedObject();
		}
		
		addlabels(container);
		addMonsterBtns(container);
		addOptionBtns(container);
		
		if (getBackButtonRoute().equals("SHOP")) {
			addLabelsSell(container);
		}
	}

	private void addLabelsSell(Container container) {
		
		lblGold = new JLabel("Gold: " + getGameEnvironment().getPlayer().getGoldBalance());
		lblGold.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGold.setBounds(411, 11, 113, 43);
		container.add(lblGold);	
		
		lblSellPrice = new JLabel("Sell Price:");
		lblSellPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSellPrice.setVisible(false);
		lblSellPrice.setBounds(198, 356, 137, 43);
		container.add(lblSellPrice);
		
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
		
		buttonGroupPartyMonsters = new ButtonGroup();
		ActionListener listenerPartyMonsters = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (AbstractButton button: listOptionButtons) {
					button.setEnabled(buttonGroupPartyMonsters.getSelection()!=null);
				}
				btnSelectedMonster = (JToggleButton) e.getSource();
				if (e.getSource().equals(btnMonsterOne)) {
					selectedMonster = getGameEnvironment().getPlayer().getParty().get(0);
				} else if (e.getSource().equals(btnMonsterTwo)){
					selectedMonster = getGameEnvironment().getPlayer().getParty().get(1);
				} else if (e.getSource().equals(btnMonsterThree)) {
					selectedMonster = getGameEnvironment().getPlayer().getParty().get(2);
				} else {
					selectedMonster = getGameEnvironment().getPlayer().getParty().get(3);
				}
				if (getBackButtonRoute().equals("SHOP")) {
					lblSellPrice.setVisible(true);
					lblSellPrice.setText("Sell Price: " + selectedMonster.getSellPrice());
				}
				if (btnSwitchMonsters!=null && btnSwitchMonsters.isSelected() && !getGameEnvironment().getBattleRunning()) {
					getGameEnvironment().switchMonsters(selectedMonsterSwitch, selectedMonster);
					paintBtnsMonsters();
				}
			}
		};


		btnMonsterOne = new JToggleButton();
		btnMonsterOne.addActionListener(listenerPartyMonsters);
		btnMonsterOne.setBounds(69, 83, 182, 106);
		container.add(btnMonsterOne);
		

		btnMonsterTwo = new JToggleButton();
		btnMonsterTwo.addActionListener(listenerPartyMonsters);
		btnMonsterTwo.setBounds(282, 83, 182, 106);
		container.add(btnMonsterTwo);
		

		btnMonsterThree = new JToggleButton();
		btnMonsterThree.addActionListener(listenerPartyMonsters);
		btnMonsterThree.setBounds(69, 229, 182, 106);
		container.add(btnMonsterThree);
		

		btnMonsterFour = new JToggleButton();
		btnMonsterFour.addActionListener(listenerPartyMonsters);
		btnMonsterFour.setBounds(282, 229, 182, 106);
		container.add(btnMonsterFour);
		
		paintBtnsMonsters();
		
		buttonGroupPartyMonsters.add(btnMonsterOne);
		buttonGroupPartyMonsters.add(btnMonsterTwo);
		buttonGroupPartyMonsters.add(btnMonsterThree);
		buttonGroupPartyMonsters.add(btnMonsterFour);
			
	}
	
	private void paintBtnsMonsters() {
		
		if (getGameEnvironment().getPlayer().getParty().size()>=1) {
			btnMonsterOne.setText(getGameEnvironment().getPlayer().getParty().get(0).getNickname());
			btnMonsterOne.setToolTipText(getGameEnvironment().getPlayer().getParty().get(0).toolTipText());
		} else {
			btnMonsterOne.setEnabled(false);
			btnMonsterOne.setText(null);
		}
		
		if (getGameEnvironment().getPlayer().getParty().size()>=2) {
			btnMonsterTwo.setText(getGameEnvironment().getPlayer().getParty().get(1).getNickname());
			btnMonsterTwo.setToolTipText(getGameEnvironment().getPlayer().getParty().get(1).toolTipText());
		} else {
			btnMonsterTwo.setEnabled(false);
			btnMonsterTwo.setText(null);
		}
		
		if (getGameEnvironment().getPlayer().getParty().size()>=3) {
			btnMonsterThree.setText(getGameEnvironment().getPlayer().getParty().get(2).getNickname());
			btnMonsterThree.setToolTipText(getGameEnvironment().getPlayer().getParty().get(2).toolTipText());
		} else {
			btnMonsterThree.setEnabled(false);
			btnMonsterThree.setText(null);
		}
		
		if (getGameEnvironment().getPlayer().getParty().size()==4) {
			btnMonsterFour.setText(getGameEnvironment().getPlayer().getParty().get(3).getNickname());
			btnMonsterFour.setToolTipText(getGameEnvironment().getPlayer().getParty().get(3).toolTipText());
		} else {
			btnMonsterFour.setEnabled(false);
			btnMonsterFour.setText(null);
		}
	}
	
	private void addOptionBtns(Container container) {
		
		listOptionButtons = new ArrayList<AbstractButton>();
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> {
			if (getBackButtonRoute().equals("MAIN_MENU")) {
				getGameEnvironment().transitionScreen(getBackButtonRoute(), "PARTY", true);
			} else {
				getGameEnvironment().transitionScreen(getBackButtonRoute(), "MAIN_MENU", true);
			}
		});
		btnBack.setBounds(10, 358, 105, 42);
		container.add(btnBack);
	
		if (getBackButtonRoute().equals("MAIN_MENU")) {
			btnNickname = new JButton("Nickname");
			btnNickname.setEnabled(false);
			btnNickname.addActionListener(e -> {
				optionPaneNickname();
				paintBtnsMonsters();
			});
			btnNickname.setBounds(189, 358, 105, 42);
			container.add(btnNickname);
			listOptionButtons.add(btnNickname);
			
			btnUseItem = new JButton("Use Item");
			btnUseItem.setEnabled(false);
			btnUseItem.addActionListener(e -> {
				getGameEnvironment().setSelectedObject(selectedMonster);
				getGameEnvironment().transitionScreen("INVENTORY", "PARTY", false);
			});
			btnUseItem.setBounds(304, 358, 105, 42);
			container.add(btnUseItem);
			listOptionButtons.add(btnUseItem);
			
			btnSwitchMonsters = new JToggleButton("Switch");
			btnSwitchMonsters.setEnabled(false);
			btnSwitchMonsters.addActionListener(e -> { 
				selectedMonsterSwitch = selectedMonster;
				btnSelectedMonster.setPressedIcon(null);
			});
			btnSwitchMonsters.setBounds(419, 358, 105, 42);
			container.add(btnSwitchMonsters);
			listOptionButtons.add(btnSwitchMonsters);
			
		} else if (getBackButtonRoute().equals("SHOP")) {
			btnSellMonster = new JButton("Sell");
			btnSellMonster.setEnabled(false);
			btnSellMonster.addActionListener(e -> {
				if (getGameEnvironment().getPlayer().getGoldBalance() + selectedMonster.getSellPrice() < 200) {
					 optionPaneEndGame();
				} else {
					sellMonster();
				}
			});
			btnSellMonster.setBounds(419, 358, 105, 42);
			container.add(btnSellMonster);
			listOptionButtons.add(btnSellMonster);
			
		} else if (getBackButtonRoute().equals("INVENTORY")) {
			btnUseItem = new JButton("Use Item");
			btnUseItem.setEnabled(false);
			btnUseItem.addActionListener(e -> {
				getGameEnvironment().useItem(selectedMonster, selectedItem);
				getGameEnvironment().transitionScreen(getBackButtonRoute(), "MAIN_MENU", true);
			});
			btnUseItem.setBounds(419, 358, 105, 42);
			container.add(btnUseItem);
			listOptionButtons.add(btnUseItem);
		}

	}
	
	private void optionPaneNickname() {
		final JButton btnCancel = new JButton("Cancel");
		final JButton btnAccept = new JButton("Accept");
		final JTextField fieldNickname = new JTextField();
		JLabel lblNickname = new JLabel("Change Nickname");
		
		btnAccept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedMonster.setNickname(fieldNickname.getText());
				JOptionPane.getRootFrame().dispose();   
			}
			
		});
		
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.getRootFrame().dispose();   
			}
			
		});
		
		fieldNickname.setColumns(50);
		fieldNickname.getDocument().addDocumentListener(new DocumentListener() {
	        protected void update() {
	        	boolean validMonsterNickname = fieldNickname.getText().matches(GameEnvironmentUi.NAME_REGEX) || fieldNickname.getText().isEmpty();
	            btnAccept.setEnabled(validMonsterNickname);
	            lblNickname.setText(validMonsterNickname ? "Change Nickname" : "<html>Change Nickname <font color='red'>(" + GameEnvironmentUi.MONSTER_NAME_REQUIREMENTS + ")</font></html>");
	        }

	        @Override
	        public void insertUpdate(DocumentEvent e) {
	            update();
	        }

	        @Override
	        public void removeUpdate(DocumentEvent e) {
	            update();
	        }

	        @Override
	        public void changedUpdate(DocumentEvent e) {
	            update();
	        }
		});
		
        JOptionPane.showOptionDialog(null,  new Object[] {lblNickname, fieldNickname}, "Set Nickname", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, new JButton[]
        {btnCancel, btnAccept}, btnCancel);
	}
	
	private void optionPaneEndGame() {
		final JButton btnCancel = new JButton("Cancel");
		final JButton btnSellAnyway = new JButton("Sell Anyway");
		final JButton btnEndGame = new JButton("End Game");
		final JLabel lblMessage = new JLabel("<html><font color='red'>WARNING:</font> Selling " + selectedMonster.getNickname() + " will "
				+ "leave you without monsters and not enough gold to buy another.<br> "
				+ "You can choose to cancel the sale, sell and carry on the game or sell and end the game here.");
		
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.getRootFrame().dispose();   
			}
			
		});
		
		btnSellAnyway.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sellMonster();
				JOptionPane.getRootFrame().dispose();
			}
			
		});
		
		btnEndGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sellMonster();
				JOptionPane.getRootFrame().dispose();
				getGameEnvironment().transitionScreen("GAME_OVER", "PARTY", true);
			}
		});
		
		JOptionPane.showOptionDialog(null,  new Object[] {lblMessage}, "Set Nickname", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, new JButton[]
		        {btnCancel, btnSellAnyway, btnEndGame}, btnCancel);
	}
	
	private void sellMonster() {
		getGameEnvironment().sellMonster(selectedMonster);
		buttonGroupPartyMonsters.clearSelection();
		lblSellPrice.setVisible(false);
		lblGold.setText("Gold: " + getGameEnvironment().getPlayer().getGoldBalance());
		btnSellMonster.setEnabled(false);
		selectedMonster=null;
		paintBtnsMonsters();
	}
	
}