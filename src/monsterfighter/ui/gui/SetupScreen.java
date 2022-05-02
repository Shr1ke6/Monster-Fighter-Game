package monsterfighter.ui.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import monsterfighter.core.GameEnvironment;
import monsterfighter.core.GameEnvironment.Difficulty;
import monsterfighter.core.Monster;
import monsterfighter.ui.GameEnvironmentUi;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.ListModel;

public class SetupScreen{

	private JFrame frame;
	// The input field for the name of the player
	private JTextField textField;
	
	private ArrayList<GameEnvironment.Difficulty> difficulties = new ArrayList<>();
	
	private ArrayList<Monster> startingMonsters = new ArrayList<>();
	
	private GameEnvironment gameEnvironment;
	
	private JTextField textField_1;
	private JTextField textField_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetupScreen window = new SetupScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*
	@Override
	protected void initialise(Container container) {
		// TODO Auto-generated method stub
		
	}
	*/

	/**
	 * Create the application.
	 */
	public SetupScreen() {
		for (Difficulty difficulty : Difficulty.values()) {
			difficulties.add(difficulty);
		}
		/*
		for (Monster monster : gameEnvironment.getStartingMonsters()) {
			startingMonsters.add(monster);
		}
		*/
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("MF Setup Screen");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(490, 675);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("Welcome to Monster Fighter!");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(99, 11, 276, 50);
		frame.getContentPane().add(titleLabel);
		
		JLabel authorsLabel = new JLabel("By sco161 & qzh78");
		authorsLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		authorsLabel.setBounds(184, 72, 105, 14);
		frame.getContentPane().add(authorsLabel);
		
		JLabel lblNewLabel_2 = new JLabel("What is your name?");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 120, 109, 27);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(125, 124, 339, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel nameReqLabel = new JLabel(GameEnvironmentUi.NAME_REQUIREMENTS);
		nameReqLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		nameReqLabel.setForeground(Color.RED);
		nameReqLabel.setBounds(135, 155, 316, 14);
		frame.getContentPane().add(nameReqLabel);
		
		JButton btnNewButton = new JButton("Accept");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(359, 583, 105, 42);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_2_1 = new JLabel("Select no. of days");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_1.setBounds(10, 192, 109, 27);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setMaximumRowCount(10);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(117, 195, 49, 22);
		frame.getContentPane().add(comboBox);
		
		JLabel difficultyLabel = new JLabel("Select difficulty");
		difficultyLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		difficultyLabel.setBounds(10, 247, 109, 27);
		frame.getContentPane().add(difficultyLabel);
		
		// Create a ListModel to store the items in the JList
		DefaultListModel<GameEnvironment.Difficulty> difficultyListModel = new DefaultListModel<GameEnvironment.Difficulty>();
		// Add the existing difficulties to the ListModel
		difficultyListModel.addAll(difficulties);
		
		JList<GameEnvironment.Difficulty> difficultyList = new JList<GameEnvironment.Difficulty>(difficultyListModel);
		difficultyList.setBackground(Color.WHITE);
		difficultyList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		difficultyList .setVisibleRowCount(-1);
		difficultyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		difficultyList.setBounds(99, 251, 365, 62);
		frame.getContentPane().add(difficultyList);
		
		JLabel startingMonsterLabel = new JLabel("Select starting monster");
		startingMonsterLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		startingMonsterLabel.setBounds(10, 337, 156, 27);
		frame.getContentPane().add(startingMonsterLabel);
		
		/*
		DefaultListModel<Monster> startingMonsterListModel = new DefaultListModel<Monster>();
		// Add the existing difficulties to the ListModel
		startingMonsterListModel.addAll(startingMonsters);
		*/
		
		JList<Monster> startingMonsterList = new JList<Monster>();
		startingMonsterList.setVisibleRowCount(-1);
		startingMonsterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		startingMonsterList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		startingMonsterList.setBackground(Color.WHITE);
		startingMonsterList.setBounds(10, 375, 454, 62);
		frame.getContentPane().add(startingMonsterList);
		
		JLabel lblNewLabel = new JLabel("Give your monster a nickname or leave blank for default name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 448, 454, 34);
		frame.getContentPane().add(lblNewLabel);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 493, 441, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel nicknameReqLabel = new JLabel(GameEnvironmentUi.MONSTER_NAME_REQUIREMENTS);
		nicknameReqLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		nicknameReqLabel.setForeground(Color.RED);
		nicknameReqLabel.setBounds(67, 524, 339, 14);
		frame.getContentPane().add(nicknameReqLabel);
	}


}
