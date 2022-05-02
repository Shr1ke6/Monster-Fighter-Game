package monsterfighter.ui.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JList;

import monsterfighter.core.Battle;
import monsterfighter.core.Monster;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.ListSelectionModel;

public class BattleSelectionScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BattleSelectionScreen window = new BattleSelectionScreen();
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
	public BattleSelectionScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("MF Battle Select Screen");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(490, 675);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel battleIntructionsLabel_1 = new JLabel("Win fights to gain rewards and strengthen your team");
		battleIntructionsLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		battleIntructionsLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		battleIntructionsLabel_1.setBounds(28, 54, 480, 28);
		frame.getContentPane().add(battleIntructionsLabel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Battles");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(28, 11, 109, 44);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel wildBattleLabel = new JLabel("Wild Monsters");
		wildBattleLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		wildBattleLabel.setBounds(28, 180, 188, 44);
		frame.getContentPane().add(wildBattleLabel);
		
		JLabel battleIntructionsLabel_2 = new JLabel("Each opponent may be fought only once");
		battleIntructionsLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		battleIntructionsLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		battleIntructionsLabel_2.setBounds(28, 81, 480, 28);
		frame.getContentPane().add(battleIntructionsLabel_2);
		
		JLabel battleIntructionsLabel_3 = new JLabel("New opponents arrive each day");
		battleIntructionsLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		battleIntructionsLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		battleIntructionsLabel_3.setBounds(28, 109, 480, 28);
		frame.getContentPane().add(battleIntructionsLabel_3);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 583, 105, 42);
		frame.getContentPane().add(btnBack);
		
		JButton btnBattle = new JButton("Battle");
		btnBattle.setBounds(359, 583, 105, 42);
		frame.getContentPane().add(btnBattle);
		
		JList<Battle> wildBattlesList = new JList<Battle>();
		wildBattlesList.setVisibleRowCount(-1);
		wildBattlesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		wildBattlesList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		wildBattlesList.setBackground(Color.WHITE);
		wildBattlesList.setBounds(28, 220, 420, 68);
		frame.getContentPane().add(wildBattlesList);
		
		JLabel trainerBattleLabel = new JLabel("Trainers");
		trainerBattleLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		trainerBattleLabel.setBounds(28, 332, 188, 44);
		frame.getContentPane().add(trainerBattleLabel);
		
		JList<Battle> wildBattlesList_1 = new JList<Battle>();
		wildBattlesList_1.setVisibleRowCount(-1);
		wildBattlesList_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		wildBattlesList_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		wildBattlesList_1.setBackground(Color.WHITE);
		wildBattlesList_1.setBounds(28, 370, 420, 99);
		frame.getContentPane().add(wildBattlesList_1);
		
		JLabel battleIntructionsLabel_3_1 = new JLabel("Choose a battle from either list and press battle to begin");
		battleIntructionsLabel_3_1.setHorizontalAlignment(SwingConstants.LEFT);
		battleIntructionsLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		battleIntructionsLabel_3_1.setBounds(28, 137, 480, 28);
		frame.getContentPane().add(battleIntructionsLabel_3_1);
		
	}

}
