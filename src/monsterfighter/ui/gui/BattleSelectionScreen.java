package monsterfighter.ui.gui;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;

import monsterfighter.core.Battle;
import monsterfighter.core.GameEnvironment;
import java.awt.Color;
import java.awt.Container;

import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ListSelectionModel;

public class BattleSelectionScreen extends Screen{
	
	private JList<Battle> listWildBattles;
	private JList<Battle> listTrainerBattles;
	private JButton btnBattle;
	

	protected BattleSelectionScreen(GameEnvironment gameEnvironment, String backButtonRoute) {
		super("Battle Selection Screen", gameEnvironment, backButtonRoute);
	}
	
	@Override
	protected void initialise(Container container) {
		container.setSize(490, 517);
		
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
		
		JLabel lblBattleIntructions = new JLabel("<html>Win fights to gain rewards and strengthen your team<p style='margin-top:10'>"
				+ "Each opponent may be fought only once<p style='margin-top:10'>"
				+ "New opponents arrive each day<p style='margin-top:10'>"
				+ "Choose a battle from either list and press battle to begin</html>");
		lblBattleIntructions.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBattleIntructions.setHorizontalAlignment(SwingConstants.LEFT);
		lblBattleIntructions.setBounds(28, 47, 436, 125);
		container.add(lblBattleIntructions);
				
		JLabel lblWildBattle = new JLabel("Wild Monsters");
		lblWildBattle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblWildBattle.setBounds(28, 165, 188, 44);
		container.add(lblWildBattle);
		
		JLabel lblTrainerBattle = new JLabel("Trainers");
		lblTrainerBattle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTrainerBattle.setBounds(28, 275, 188, 44);
		container.add(lblTrainerBattle);
		
	}

	private void addBtns(Container container) {
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 426, 105, 42);
		btnBack.addActionListener(e -> {
			getGameEnvironment().setSelectedObject(null);
			getGameEnvironment().transitionScreen(getBackButtonRoute(), "BATTLE_SELECT", true);
		});
		container.add(btnBack);
		
		btnBattle = new JButton("Battle");
		btnBattle.setEnabled(false);
		btnBattle.setBounds(359, 426, 105, 42);
		btnBattle.addActionListener(e -> {
			getGameEnvironment().startBattle();
			if (getGameEnvironment().getBattleRunning()) {
				getGameEnvironment().transitionScreen("BATTLE", "BATTLE_SELECT", true);
			}
		});
		container.add(btnBattle);
		
	}

	private void addListWildBattles(Container container) {
		
		// Create a ListModel to store the items in the JList
		DefaultListModel<Battle> wildBattlesListModel = new DefaultListModel<Battle>();
		// Add the existing difficulties to the ListModel
		wildBattlesListModel.addAll(getGameEnvironment().getBattles().getWildBattles());
		
		listWildBattles = new JList<Battle>(wildBattlesListModel);
		listWildBattles.setVisibleRowCount(-1);
		listWildBattles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listWildBattles.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listWildBattles.setBackground(Color.WHITE);
		listWildBattles.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!listWildBattles.isSelectionEmpty()) {
					listTrainerBattles.clearSelection();
				}
				getGameEnvironment().getBattles().setCurrentBattle(listWildBattles.getSelectedValue());
				btnBattle.setEnabled(true);
			}
			
		});
		listWildBattles.setBounds(28, 220, 420, 44);
		container.add(listWildBattles);
	}

	private void addListTrainerBattles(Container container) {
		
		// Create a ListModel to store the items in the JList
		DefaultListModel<Battle> trainerBattleListModel = new DefaultListModel<Battle>();
		// Add the existing difficulties to the ListModel
		trainerBattleListModel.addAll(getGameEnvironment().getBattles().getTrainerBattles());
		
		listTrainerBattles = new JList<Battle>(trainerBattleListModel);
		listTrainerBattles.setVisibleRowCount(-1);
		listTrainerBattles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listTrainerBattles.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listTrainerBattles.setBackground(Color.WHITE);
		listTrainerBattles.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!listTrainerBattles.isSelectionEmpty()) {
					listWildBattles.clearSelection();
				}
				getGameEnvironment().getBattles().setCurrentBattle(listTrainerBattles.getSelectedValue());
				btnBattle.setEnabled(true);
			}
			
		});
		listTrainerBattles.setBounds(28, 330, 420, 62);
		container.add(listTrainerBattles);
		
	}


}
