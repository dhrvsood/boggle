package DS_Sem2Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DS_Leaderboard extends JFrame implements Serializable {

	private JPanel contentPane;
	public ArrayList<DS_Player> playerList = new ArrayList<DS_Player>();

	/**
	 * Create the frame.
	 */
	public DS_Leaderboard() {
		setTitle("Boggle - Leaderboard");
		playerList = readFileToList();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 625, 514);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLeaderboard = new JLabel("Leaderboard");
		lblLeaderboard.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblLeaderboard.setHorizontalAlignment(SwingConstants.CENTER);
		lblLeaderboard.setBounds(222, 6, 184, 59);
		contentPane.add(lblLeaderboard);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(29, 68, 564, 396);
		contentPane.add(textArea);
		
		JButton button = new JButton("←");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				DS_Home h = new DS_Home();
				h.setVisible(true);
			}
		});
		button.setBounds(16, 16, 45, 29);
		contentPane.add(button);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				DS_Leaderboard l = new DS_Leaderboard();
				l.setVisible(true);
			}
		});
		btnRefresh.setBounds(520, 16, 81, 29);
		contentPane.add(btnRefresh);
		
		Collections.sort(playerList);
		
		for (int i=0; i<playerList.size(); i++) {
			textArea.setText(textArea.getText() + playerList.get(i).getName() + " – " + playerList.get(i).getScore()  + "\n");
		}
		saveListToFile(playerList);
	}
	/**
     * method to save file to list
     */
    public void saveListToFile(ArrayList list) {
        // save student list to file
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("pList.dat"));
            out.writeObject(list);
            out.close();
        } catch(IOException e) { e.printStackTrace(); }
    }
	
    /**
     * method to read file and create list
     */
	public ArrayList readFileToList() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("pList.dat"));
            playerList = (ArrayList) in.readObject();
            in.close();
        } catch(IOException e) { e.printStackTrace(); }
        catch(ClassNotFoundException c) { }
        return playerList;
    }
}
