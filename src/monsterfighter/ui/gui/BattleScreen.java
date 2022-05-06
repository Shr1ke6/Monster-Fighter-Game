package monsterfighter.ui.gui;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import monsterfighter.core.GameEnvironment;

import javax.swing.JDesktopPane;
import javax.swing.JButton;
import javax.swing.JLabel;

public class BattleScreen extends Screen{



	protected BattleScreen(GameEnvironment gameEnvironment, String back) {
		super("Monster Fighter Battle", gameEnvironment);
	}
	
	@Override
	protected void initialise(Container container) {
		initialize(container);
		
	}

	/**
	 * Initialize the contents of the container.
	 */
	private void initialize(Container container) {
		container.setSize(490, 675);
		
		JPanel opponentPanel = new JPanel();
		opponentPanel.setBounds(229, 30, 235, 95);
		container.add(opponentPanel);
		opponentPanel.setLayout(null);
		
		JPanel oppHealthPanel = new JPanel();
		oppHealthPanel.setBounds(10, 64, 133, 20);
		opponentPanel.add(oppHealthPanel);
		
		JLabel oppMonsterNameLabel = new JLabel("New label");
		oppMonsterNameLabel.setBounds(10, 11, 150, 42);
		opponentPanel.add(oppMonsterNameLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(170, 11, 55, 42);
		opponentPanel.add(lblNewLabel_1);
		
		JLabel oppTypeLabel = new JLabel("New label");
		oppTypeLabel.setBounds(170, 64, 54, 20);
		opponentPanel.add(oppTypeLabel);
		
		JPanel userPanel = new JPanel();
		userPanel.setBounds(10, 291, 235, 95);
		container.add(userPanel);
		userPanel.setLayout(null);
		
		JLabel userMonsterNameLabel = new JLabel("New label");
		userMonsterNameLabel.setBounds(10, 11, 150, 42);
		userPanel.add(userMonsterNameLabel);
		
		JPanel userHealthPanel = new JPanel();
		userHealthPanel.setBounds(10, 64, 133, 20);
		userPanel.add(userHealthPanel);
		
		JLabel userTypeLabel = new JLabel("New label");
		userTypeLabel.setBounds(171, 64, 54, 20);
		userPanel.add(userTypeLabel);
		
		JPanel battleMessagePanel = new JPanel();
		battleMessagePanel.setBounds(10, 420, 454, 124);
		container.add(battleMessagePanel);
		battleMessagePanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 11, 434, 102);
		battleMessagePanel.add(lblNewLabel);
		
		JButton btnAttack = new JButton("Attack");
		btnAttack.setBounds(20, 565, 105, 42);
		container.add(btnAttack);
		
		JButton btnUseItem = new JButton("Use Item");
		btnUseItem.setBounds(184, 565, 105, 42);
		container.add(btnUseItem);
		
		JButton btnSwitchMonster = new JButton("Switch Monster");
		btnSwitchMonster.setBounds(348, 565, 105, 42);
		container.add(btnSwitchMonster);
	}


}
