package DS_Sem2Project;

import java.util.*;
import java.io.*;

import java.awt.BorderLayout;
import java.util.ArrayList;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.Timer;
import java.awt.event.ActionEvent;

public class DS_Game extends JFrame implements Serializable {

	private JPanel contentPane, boggle_panel;
	private int defaultTime = 180, timeLeft, minutes, seconds, score;
	private String timeString, fileName = "dictionary.txt";
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	private ArrayList<String> wordsFound = new ArrayList<String>();
	static ArrayList<DS_Player> playerList = new ArrayList<DS_Player>();
	private Timer t;

	/**
	 * Create the frame.
	 */
	public DS_Game() {
		playerList = readFileToList();
		timeLeft = defaultTime;

		setTitle("Boggle - Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 625, 514);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel header = new JPanel();
		header.setBorder(new EmptyBorder(0, 0, 0, 0));
		header.setBackground(new Color(100, 149, 237));
		header.setBounds(17, 17, 590, 51);
		contentPane.add(header);
		header.setLayout(null);

		// method to pause game
		JButton btnPause = new JButton("Pause");
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.stop();
				JOptionPane.showMessageDialog(header, "Game Paused.");
				t.start();
			}
		});
		btnPause.setBounds(504, 5, 80, 29);
		header.add(btnPause);

		JButton btnBack = new JButton("←");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					timeLeft = defaultTime;
					t.stop();
					dispose();
					DS_Home h = new DS_Home();
					h.setVisible(true);
				}
			});
		btnBack.setBounds(6, 5, 48, 29);
		header.add(btnBack);

		JLabel lblBoggle = new JLabel("Boggle!");
		lblBoggle.setBounds(241, 6, 106, 34);
		header.add(lblBoggle);
		lblBoggle.setFont(new Font("Lucida Grande", Font.BOLD, 28));
		lblBoggle.setHorizontalAlignment(SwingConstants.CENTER);

		/**
		 * bottom panel of screen
		 */
		JPanel bottom_panel = new JPanel();
		bottom_panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		bottom_panel.setBackground(new Color(100, 149, 237));
		bottom_panel.setBounds(17, 393, 590, 72);
		contentPane.add(bottom_panel);
		bottom_panel.setLayout(null);

		/**
		 * score panel
		 */
		JPanel score_panel = new JPanel();
		score_panel.setBackground(new Color(100, 149, 237));
		score_panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Score", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		score_panel.setBounds(411, 14, 179, 52);
		bottom_panel.add(score_panel);

		JLabel lblInt = new JLabel();
		lblInt.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblInt.setText(Integer.toString(score));
		score_panel.add(lblInt);

		/**
		 * words found panel
		 */
		JPanel words_found = new JPanel();
		words_found.setBackground(new Color(100, 149, 237));
		words_found.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Words Found", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		words_found.setBounds(378, 80, 229, 192);
		contentPane.add(words_found);
		words_found.setLayout(null);

		JLabel lblIndicator = new JLabel();
		lblIndicator.setHorizontalAlignment(SwingConstants.CENTER);
		lblIndicator.setBounds(6, 158, 217, 28);
		words_found.add(lblIndicator);
		lblIndicator.setBackground(new Color(0, 0, 128));
		lblIndicator.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		lblIndicator.setVisible(false);

		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBounds(6, 21, 217, 125);
		words_found.add(textArea);

		/**
		 * current word panel
		 */
		JPanel current_word = new JPanel();
		current_word.setBackground(new Color(100, 149, 237));
		current_word.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Current Word", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		current_word.setBounds(0, 14, 286, 52);

		JLabel lblWord = new JLabel();
		lblWord.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		current_word.add(lblWord);
		bottom_panel.add(current_word);


		/**
		 * board panel
		 */
		boggle_panel = new JPanel();
		boggle_panel.setBackground(new Color(100, 149, 237));
		boggle_panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Board", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		boggle_panel.setBounds(17, 80, 358, 307);
		contentPane.add(boggle_panel);
		boggle_panel.setLayout(new GridLayout(4, 4, 0, 0));

		// create boggle board
		createBoard();

		// grid buttons action handler
		for (int i=0; i<buttons.size(); i++) {
			int n = buttons.indexOf(buttons.get(i));
			buttons.get(n).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// set text of jlabel
					lblWord.setText(lblWord.getText() + (buttons.get(n).getText()));

					// disable buttons
					for (int j=0; j<buttons.size(); j++) {
						buttons.get(j).setEnabled(false);
					}
					lblIndicator.setVisible(false);

					/**
					 * enable buttons
					 */
					// centers
					if ((n == 5 || n == 6) || (n == 9 || n == 10)) {
						buttons.get(n-5).setEnabled(true);
						buttons.get(n-4).setEnabled(true);
						buttons.get(n-3).setEnabled(true);
						buttons.get(n-1).setEnabled(true);
						buttons.get(n+1).setEnabled(true);
						buttons.get(n+3).setEnabled(true);
						buttons.get(n+4).setEnabled(true);
						buttons.get(n+5).setEnabled(true);
					}
					// corners
					if (n == 0) {
						buttons.get(n+1).setEnabled(true);
						buttons.get(n+4).setEnabled(true);
						buttons.get(n+5).setEnabled(true);
					}
					if (n == 15) {
						buttons.get(n-1).setEnabled(true);
						buttons.get(n-4).setEnabled(true);
						buttons.get(n-5).setEnabled(true);
					}
					if (n == 3) {
						buttons.get(n-1).setEnabled(true);
						buttons.get(n+3).setEnabled(true);
						buttons.get(n+4).setEnabled(true);
					}
					if (n == 12) {
						buttons.get(n+1).setEnabled(true);
						buttons.get(n-3).setEnabled(true);
						buttons.get(n-4).setEnabled(true);
					}
					// edges
					if (n == 1 || n == 2) {
						buttons.get(n-1).setEnabled(true);
						buttons.get(n+1).setEnabled(true);
						buttons.get(n+3).setEnabled(true);
						buttons.get(n+4).setEnabled(true);
						buttons.get(n+5).setEnabled(true);
					}
					if (n == 13 || n == 14) {
						buttons.get(n-1).setEnabled(true);
						buttons.get(n+1).setEnabled(true);
						buttons.get(n-3).setEnabled(true);
						buttons.get(n-4).setEnabled(true);
						buttons.get(n-5).setEnabled(true);
					}
					if (n == 4 || n == 8) {
						buttons.get(n+1).setEnabled(true);
						buttons.get(n-3).setEnabled(true);
						buttons.get(n-4).setEnabled(true);
						buttons.get(n+4).setEnabled(true);
						buttons.get(n+5).setEnabled(true);
					}
					if (n == 7 || n == 11) {
						buttons.get(n-1).setEnabled(true);
						buttons.get(n+3).setEnabled(true);
						buttons.get(n+4).setEnabled(true);
						buttons.get(n-4).setEnabled(true);
						buttons.get(n-5).setEnabled(true);
					}

					// create red border around already selected buttons
					buttons.get(n).setEnabled(false);
					buttons.get(n).setBorder(BorderFactory.createLineBorder(Color.RED, 5));

					// making sure that words with a red button cannot be selected again (one box can only be used once)
					for (int i=0; i<buttons.size(); i++) {
						if (((LineBorder) buttons.get(i).getBorder()).getLineColor() == Color.RED) {
							buttons.get(i).setEnabled(false);
						}
					}
				}
			});
		}

		// submit word button
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userWord = lblWord.getText().toLowerCase();
				try {
					clearBoard();
					lblWord.setText("");
					lblIndicator.setVisible(true);
					// if word length is less than 3
					if (userWord.length() < 3) {
						lblIndicator.setText("Incorrect - Word too short!");
						lblIndicator.setForeground(new Color(255, 0, 0));
					}
					// if word does not exist in dictionary
					else if (!linearSearch(fileName, userWord)) {
						lblIndicator.setText("Incorrect - Word not found!");
						lblIndicator.setForeground(new Color(255, 0, 0));
					}
					// if word has already been input
					else if (wordsFound.contains(userWord)) {
						lblIndicator.setText("Incorrect - Word already used!");
						lblIndicator.setForeground(new Color(255, 0, 0));
					}
					// if word meets all valid conditions
					else if (linearSearch(fileName, userWord)) {
						score += getScore(userWord.length());
						lblInt.setText(Integer.toString(score));
						lblIndicator.setText("Correct!");
						lblIndicator.setForeground(new Color(0, 128, 0));
						wordsFound.add(userWord);
						String wordsFoundString = String.join(",", wordsFound);
						textArea.setText(wordsFoundString);
					}
				} catch (IOException e1) {
					System.out.println(e1);
				}
			}
		});
		btnSubmit.setBounds(286, 14, 59, 52);
		bottom_panel.add(btnSubmit);

		// clear word button
		JButton btnClearWord = new JButton("Clear");
		btnClearWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblWord.setText("");
				clearBoard();
				lblIndicator.setVisible(false);
			}
		});
		btnClearWord.setBounds(347, 14, 59, 52);
		bottom_panel.add(btnClearWord);

		/**
		 * time panel
		 */
		JPanel time_panel = new JPanel();
		time_panel.setBackground(new Color(100, 149, 237));
		time_panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Time", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		time_panel.setBounds(378, 275, 229, 112);
		contentPane.add(time_panel);
		time_panel.setLayout(null);

		JLabel label = new JLabel();
		label.setText(formatTime(timeLeft));
		label.setBounds(75, 20, 89, 52);
		time_panel.add(label);
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 29));


		// showing count down timer
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// formatting time and updating label
				label.setText(formatTime(timeLeft));
				if (timeLeft == 0) {
					String name = JOptionPane.showInputDialog("Game Over! Your score was: " + score + ". Enter your name here.");
					dispose();
					playerList.add(new DS_Player(name, score));

					DS_Leaderboard l = new DS_Leaderboard();
					l.setVisible(true);
					t.stop();
					saveListToFile(playerList);
				}
				else  { timeLeft--; }
			}
		};
		t = new Timer(1000, al);
		t.start();

		// Restart button
		JButton btnRestart = new JButton("Restart");
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					timeLeft = defaultTime;
					t.stop();
					dispose();
					DS_Game g = new DS_Game();
					g.setVisible(true);
				}
			});
		btnRestart.setBounds(6, 77, 217, 29);
		time_panel.add(btnRestart);

		}

	// method to create board array
	public void createBoard() {
		DS_Board b = new DS_Board();
		String[] letterList = b.createBoard();
		for (int a=0; a<16; a++) {
			JButton button = new JButton(letterList[a]);
			button.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			boggle_panel.add(button);
			buttons.add(button);
			buttons.get(a).setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		}
	}


	// method to clear grid (enable all buttons)
	public void clearBoard() {
		for (int j=0; j<buttons.size(); j++) {
			buttons.get(j).setEnabled(true);
			buttons.get(j).setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		}
	}

	// format time
	public String formatTime(int secondsLeft) {
		minutes = (secondsLeft % 3600) / 60;
		seconds = secondsLeft % 60;
		timeString = String.format("%02d:%02d", minutes, seconds);
		return timeString;
	}

	// linear search method for txt file (http://p2p.wrox.com/pro-java/46886-binary-search-text-file.html)
	@SuppressWarnings("resource")
	public boolean linearSearch(String fileName, String target) throws IOException {
        BufferedReader br = null;
        br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String line;
        while ((line = br.readLine()) != null) {
        		if (line.equals(target)) { return true; }
        }
		return false;
  }

	// method to calculate the score of a word based on its length
	public int getScore(int wordLength) {
		int score = 0;
		if (wordLength == 3 || wordLength == 4) { score = 10; }
		if (wordLength == 5) { score = 20; }
		if (wordLength == 6) { score = 30; }
		if (wordLength == 7) { score = 50; }
		if (wordLength >= 8) { score = 80; }
		return score;
	}
	/**
     * method to save file to list
     */
    public void saveListToFile(ArrayList<DS_Player> list) {
        // save student list to file
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("pList.dat"));
            out.writeObject(list);
            out.close();
        } catch(IOException e) { e.printStackTrace(); }
    }

    /**
     * method to read file to list
     */
    public ArrayList readFileToList() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("pList.dat"));
            playerList = (ArrayList<DS_Player>) in.readObject();
            in.close();
        } catch(IOException e) { e.printStackTrace(); }
        catch(ClassNotFoundException c) { }
        return playerList;
    }
}
