package monsterfighter.ui.gui;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import monsterfighter.core.GameEnvironment;
import monsterfighter.core.Monster;
import monsterfighter.ui.GameEnvironmentUi;


import java.util.Arrays;
import javax.swing.SwingConstants;
import java.awt.Font;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SetupScreen extends Screen{
	
	private JTextField fieldName;
	private JTextField fieldMonsterNickname;
	private JButton btnAccept;
	private JComboBox<Integer> comboBoxDay;
	private JList<GameEnvironment.Difficulty> listDifficulty;
	private JList<Monster> listStartingMonster;
	private JLabel lblNameReq;
	private JLabel lblNicknameReq;
	
	public SetupScreen(GameEnvironment incomingGameEnvironment) {
		super("Monster Fighter Set Up Screen", incomingGameEnvironment, null);
	}

	private void setupComplete() {
		getGameEnvironment().onSetupFinished(fieldName.getText(), comboBoxDay.getSelectedIndex() + 5, 
				listStartingMonster.getSelectedValue(), fieldMonsterNickname.getText(), listDifficulty.getSelectedValue());
	}
	
	@Override
	protected void initialise(Container container) {
        
		container.setSize(490, 675);
        
		addLabels(container);
		addFieldName(container);
		addFieldMonsterNickname(container);
		addListDifficulty(container);
		addComboBoxDay(container);
		addListStartingMonsters(container);
		addAcceptButton(container);
		
	}
	
	private void addLabels(Container container) {
		JLabel lblTitle = new JLabel("Welcome to Monster Fighter!");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(99, 11, 276, 50);
		container.add(lblTitle);
		
		JLabel lblAuthors = new JLabel("By sco161 & qzh78");
		lblAuthors.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAuthors.setBounds(184, 72, 105, 14);
		container.add(lblAuthors);
		
		JLabel lblNamePrompt = new JLabel("What is your name?");
		lblNamePrompt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNamePrompt.setBounds(10, 120, 109, 27);
		container.add(lblNamePrompt);
		
		JLabel lblSelectDays = new JLabel("Select no. of days");
		lblSelectDays.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSelectDays.setBounds(10, 192, 109, 27);
		container.add(lblSelectDays);
		
		lblNameReq = new JLabel(GameEnvironmentUi.NAME_REQUIREMENTS);
		lblNameReq.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNameReq.setForeground(Color.RED);
		lblNameReq.setBounds(135, 155, 316, 14);
		container.add(lblNameReq);
		
		JLabel lblDifficulty = new JLabel("Select difficulty");
		lblDifficulty.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDifficulty.setBounds(10, 247, 109, 27);
		container.add(lblDifficulty);
		
		JLabel lblNicknamePrompt = new JLabel("Give your monster a nickname or leave blank for default name");
		lblNicknamePrompt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNicknamePrompt.setBounds(10, 448, 454, 34);
		container.add(lblNicknamePrompt);
		
		JLabel lblStartingMonster = new JLabel("Select starting monster");
		lblStartingMonster.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStartingMonster.setBounds(10, 337, 156, 27);
		container.add(lblStartingMonster);
		
		lblNicknameReq = new JLabel("");
		lblNicknameReq.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNicknameReq.setForeground(Color.RED);
		lblNicknameReq.setBounds(67, 524, 339, 14);
		container.add(lblNicknameReq);
		
	}
	
	private void addFieldName(Container container) {
		fieldName = new JTextField();
		fieldName.setBounds(125, 124, 339, 20);
		container.add(fieldName);
		fieldName.setColumns(10);
		
		fieldName.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				checkCanContinue();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				checkCanContinue();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				checkCanContinue();
			}
		});
	}
	
	private void addFieldMonsterNickname(Container container) {
		fieldMonsterNickname = new JTextField();
		fieldMonsterNickname.setBounds(10, 493, 441, 20);
		container.add(fieldMonsterNickname);
		fieldMonsterNickname.setColumns(10);
		
		fieldMonsterNickname.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				checkCanContinue();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				checkCanContinue();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				checkCanContinue();
			}
		});
	}
	
	private void addComboBoxDay(Container container) {
		comboBoxDay = new JComboBox<Integer>();
		comboBoxDay.setMaximumRowCount(10);
		comboBoxDay.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}));
		comboBoxDay.setSelectedIndex(0);
		
		comboBoxDay.setBounds(117, 195, 49, 22);
		container.add(comboBoxDay);
	}
	
	private void addAcceptButton(Container container) {
		btnAccept = new JButton("Accept");
		btnAccept.setEnabled(false);
		btnAccept.addActionListener(e -> setupComplete());
		
		btnAccept.setBounds(359, 583, 105, 42);
		container.add(btnAccept);
	}
	
	private void addListDifficulty(Container container) {
		
		// Create a ListModel to store the items in the JList
		DefaultListModel<GameEnvironment.Difficulty> difficultyListModel = new DefaultListModel<GameEnvironment.Difficulty>();
		// Add the existing difficulties to the ListModel
		difficultyListModel.addAll(Arrays.asList(GameEnvironment.Difficulty.values()));
		
		listDifficulty = new JList<GameEnvironment.Difficulty>(difficultyListModel);
		listDifficulty.setBackground(Color.WHITE);
		listDifficulty.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listDifficulty.setVisibleRowCount(-1);
		listDifficulty.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		listDifficulty.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				checkCanContinue();
			}
		});
		listDifficulty.setBounds(99, 251, 365, 62);
		container.add(listDifficulty);
		
	}
	
	private void addListStartingMonsters(Container container) {
		
		DefaultListModel<Monster> startingMonsterListModel = new DefaultListModel<Monster>();
		// Add the existing startingMonsters to the ListModel
		startingMonsterListModel.addAll(getGameEnvironment().getStartingMonsters());
		
		listStartingMonster = new JList<Monster>(startingMonsterListModel);
		listStartingMonster.setCellRenderer(new StartingMonstersRenderer());
		listStartingMonster.setVisibleRowCount(-1);
		listStartingMonster.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listStartingMonster.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listStartingMonster.setBackground(Color.WHITE);
		
		listStartingMonster.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				checkCanContinue();
			}
		});
		
		listStartingMonster.setBounds(10, 375, 454, 62);
		container.add(listStartingMonster);
	}
	
	private void checkCanContinue() {
		boolean validName = fieldName.getText().matches(GameEnvironmentUi.NAME_REGEX);
		boolean validMonsterNickname = fieldMonsterNickname.getText().matches(GameEnvironmentUi.NAME_REGEX) || fieldMonsterNickname.getText().isEmpty();

		// Hide the name requirements text if the input is valid
		lblNameReq.setText(validName ? null : GameEnvironmentUi.NAME_REQUIREMENTS);
		
		lblNicknameReq.setText(validMonsterNickname ? null : GameEnvironmentUi.MONSTER_NAME_REQUIREMENTS);

		btnAccept.setEnabled(validName && validMonsterNickname && !listDifficulty.isSelectionEmpty() && !listStartingMonster.isSelectionEmpty());
	}


}
