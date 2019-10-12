package DS_Sem2Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class DS_Home extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DS_Home frame = new DS_Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DS_Home() {
		setTitle("Boggle - Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 625, 514);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel(new ImageIcon("images/boggle_img.png"));
		label.setLocation(145, 61);
		label.setSize(330, 137);
		contentPane.add(label);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DS_Game g = new DS_Game();
				g.setVisible(true);
				dispose();
			}
		});
		btnPlay.setBounds(221, 225, 177, 68);
		contentPane.add(btnPlay);
		
		JButton btnRules = new JButton("Rules");
		btnRules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DS_Rules r = new DS_Rules();
				r.setVisible(true);
				dispose();
			}
		});
		btnRules.setBounds(220, 292, 178, 68);
		contentPane.add(btnRules);
		
		JButton btnLeaderboard = new JButton("Leaderboard");
		btnLeaderboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DS_Leaderboard l;
				dispose();
				l = new DS_Leaderboard();
				l.setVisible(true);
			
			}
		});
		btnLeaderboard.setBounds(221, 358, 177, 68);
		contentPane.add(btnLeaderboard);
	}
}
