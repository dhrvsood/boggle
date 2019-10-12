package DS_Sem2Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class DS_Rules extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public DS_Rules() {
		setTitle("Boggle - Rules");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 625, 514);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRules = new JLabel("Rules");
		lblRules.setBackground(Color.BLUE);
		lblRules.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblRules.setBounds(272, 16, 79, 64);
		contentPane.add(lblRules);
		
		JButton btnNewButton = new JButton("←");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DS_Home h = new DS_Home();
				h.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(20, 16, 48, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/Users/19dsood/Desktop/Dhruv/Google Drive/AES Grade 11/IB HL Comp Sci/DhruvProject/SemesterProject/src/DS_Sem2Project/images/points.png"));
		lblNewLabel.setBounds(477, 50, 128, 331);
		contentPane.add(lblNewLabel);
		
		JTextArea txtrWelcomeToBoggle = new JTextArea();
		txtrWelcomeToBoggle.setForeground(new Color(0, 0, 0));
		txtrWelcomeToBoggle.setBackground(new Color(135, 206, 235));
		txtrWelcomeToBoggle.setEditable(false);
		txtrWelcomeToBoggle.setLineWrap(true);
		txtrWelcomeToBoggle.setText("Welcome to Boggle!\n\nThe computer will generate a grid of letters in a 4x4 grid. It is your \njob to find as many words as possible that fit the following \nguidelines:\n\n- The letters must be a part of a chain (the letters must be adjacent horizontally, vertically, or diagonally)\n- Words must be at least 3 letters long\n- A letter can only be used once\n- The word must be a valid word in the english dictionary \n\nYou will be awarded points based on the length of your word! Be \nquick and find as many words as you can in a span of 3 minutes.\n\nGood luck!");
		txtrWelcomeToBoggle.setBounds(20, 77, 445, 344);
		txtrWelcomeToBoggle.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(txtrWelcomeToBoggle);
		
		JButton btnNewButton_1 = new JButton("Play Now!");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DS_Game g = new DS_Game();
				g.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBackground(Color.CYAN);
		btnNewButton_1.setBounds(477, 354, 128, 73);
		contentPane.add(btnNewButton_1);
	}
}
