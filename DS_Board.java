package DS_Sem2Project;

import java.util.*;

public class DS_Board {
	private static ArrayList<DS_Die> dice;
	private String[] letterList;
	
	
	public DS_Board() {
		initDice();
	}
	
	private void initDice() {
        dice = new ArrayList<DS_Die>();
        dice.add(new DS_Die("F", "O", "R", "I", "X", "B"));
        dice.add(new DS_Die("M", "O", "QU", "A", "B", "J"));
        dice.add(new DS_Die("G", "U", "R", "I", "L", "W"));
        dice.add(new DS_Die("S", "E", "T", "U", "P", "L"));
        dice.add(new DS_Die("C", "M", "P", "D", "A", "E"));
        dice.add(new DS_Die("A", "C", "I", "T", "A", "O"));
        dice.add(new DS_Die("S", "L", "C", "R", "A", "E"));
        dice.add(new DS_Die("R", "O", "M", "A", "S", "H"));
        dice.add(new DS_Die("N", "O", "D", "E", "S", "W"));
        dice.add(new DS_Die("H", "E", "F", "I", "Y", "E"));
        dice.add(new DS_Die("O", "N", "U", "D", "T", "K"));
        dice.add(new DS_Die("T", "E", "V", "I", "G", "N"));
        dice.add(new DS_Die("A", "N", "E", "D", "V", "Z"));
        dice.add(new DS_Die("P", "I", "N", "E", "S", "H"));
        dice.add(new DS_Die("A", "B", "I", "L", "Y", "T"));
        dice.add(new DS_Die("G", "K", "Y", "L", "E", "U"));
      }
	
    /**
     * Return an array of Strings showing the sequence of faces on
     * a randomly generated board.
     */
    public String[] createBoard()  {
      String[] letterList = new String[16];
      Collections.shuffle(dice);
      for (int i = 0; i < 16; i++) {
        	DS_Die d = (DS_Die) dice.get(i);
        	letterList[i] = d.getLetter();
      }
      return letterList;
    }
}
