package monsterfighter.ui.gui;

import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.Arrays;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;

import monsterfighter.core.Battle;
import monsterfighter.core.GameEnvironment;
import monsterfighter.core.Monster;
import java.awt.Color;
import java.awt.Container;

import javax.swing.border.EtchedBorder;
import javax.swing.ListSelectionModel;

public class BattleSelectionScreen extends Screen{
	
	private JList<Battle> listWildBattles;
	private JList<Battle> listTrainerBattles ;
	

	protected BattleSelectionScreen(GameEnvironment gameEnvironment) {
		super("Battle Selection Screen", gameEnvironment);
	}
	
	@Override
	protected void initialise(Container container) {
		container.setSize(490, 675);
		
		addLabels(container);
		addBtns(container);
		addListWildBattles(container);
		addListTrainerBattles(container);
	}


	private void addLabels(Container container) {

		JLabel lblBattleHeader = new JLabel("Battles");
		lblBattleHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBattleHeader.setBounds(28, 11, 109, 44);
		container.add(lblBattleHeader);
		
		JLabel lblBattleIntructions_1 = new JLabel("Win fights to gain rewards and strengthen your team");
		lblBattleIntructions_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBattleIntructions_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblBattleIntructions_1.setBounds(28, 54, 480, 28);
		container.add(lblBattleIntructions_1);
		
		JLabel lblBattleIntructions_2 = new JLabel("Each opponent may be fought only once");
		lblBattleIntructions_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblBattleIntructions_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBattleIntructions_2.setBounds(28, 81, 480, 28);
		container.add(lblBattleIntructions_2);
		
		JLabel lblBattleIntructions_3 = new JLabel("New opponents arrive each day");
		lblBattleIntructions_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblBattleIntructions_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBattleIntructions_3.setBounds(28, 109, 480, 28);
		container.add(lblBattleIntructions_3);
		
		JLabel lblBattleIntructions_4 = new JLabel("Choose a battle from either list and press battle to begin");
		lblBattleIntructions_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblBattleIntructions_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBattleIntructions_4.setBounds(28, 137, 480, 28);
		container.add(lblBattleIntructions_4);
		
		JLabel lblWildBattle = new JLabel("Wild Monsters");
		lblWildBattle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblWildBattle.setBounds(28, 180, 188, 44);
		container.add(lblWildBattle);
		
		JLabel lblTrainerBattle = new JLabel("Trainers");
		lblTrainerBattle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTrainerBattle.setBounds(28, 332, 188, 44);
		container.add(lblTrainerBattle);
		
	}

	private void addBtns(Container container) {
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 583, 105, 42);
		btnBack.addActionListener(e -> getGameEnvironment().transitionScreen("MAIN_MENU"));
		container.add(btnBack);
		
		JButton btnBattle = new JButton("Battle");
		btnBattle.setBounds(359, 583, 105, 42);
		btnBattle.addActionListener(e -> getGameEnvironment().transitionScreen("BATTLE"));
		container.add(btnBattle);
		
	}

	private void addListWildBattles(Container container) {
		
		// Create a ListModel to store the items in the JList
		DefaultListModel<Battle> wildBattlesListModel = new DefaultListModel<Battle>();
		// Add the existing difficulties to the ListModel
		wildBattlesListModel.addAll(getGameEnvironment().getWildBattles());
		
		listWildBattles = new JList<Battle>(wildBattlesListModel);
		listWildBattles.setVisibleRowCount(-1);
		listWildBattles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listWildBattles.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listWildBattles.setBackground(Color.WHITE);
		listWildBattles.setBounds(28, 220, 420, 68);
		container.add(listWildBattles);
	}

	private void addListTrainerBattles(Container container) {
		
		// Create a ListModel to store the items in the JList
		DefaultListModel<Battle> trainerBattleListModel = new DefaultListModel<Battle>();
		// Add the existing difficulties to the ListModel
		trainerBattleListModel.addAll(getGameEnvironment().getTrainerBattles());
		
		listTrainerBattles = new JList<Battle>(trainerBattleListModel);
		listTrainerBattles.setVisibleRowCount(-1);
		listTrainerBattles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listTrainerBattles.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listTrainerBattles.setBackground(Color.WHITE);
		listTrainerBattles.setBounds(28, 370, 420, 99);
		container.add(listTrainerBattles);
		
	}


}
