package monsterfighter.ui.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

public class BattleScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BattleScreen window = new BattleScreen();
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
	public BattleScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("MF Battle Screen");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(490, 675);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JPanel opponentPanel = new JPanel();
		opponentPanel.setBounds(229, 30, 235, 95);
		frame.getContentPane().add(opponentPanel);
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
		frame.getContentPane().add(userPanel);
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
		frame.getContentPane().add(battleMessagePanel);
		battleMessagePanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 11, 434, 102);
		battleMessagePanel.add(lblNewLabel);
		
		JButton btnAttack = new JButton("Attack");
		btnAttack.setBounds(20, 565, 105, 42);
		frame.getContentPane().add(btnAttack);
		
		JButton btnUseItem = new JButton("Use Item");
		btnUseItem.setBounds(184, 565, 105, 42);
		frame.getContentPane().add(btnUseItem);
		
		JButton btnSwitchMonster = new JButton("Switch Monster");
		btnSwitchMonster.setBounds(348, 565, 105, 42);
		frame.getContentPane().add(btnSwitchMonster);
	}
}
