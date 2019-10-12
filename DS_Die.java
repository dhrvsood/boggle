package DS_Sem2Project;

import java.util.Random;

public class DS_Die {
	// instantiating variables
	private String sides[];
	private int sideUp;
	public static Random r = new Random();
	
	// constructor
	public DS_Die(String side1, String side2, String side3, String side4, String side5, String side6) {
		sides = new String[] {side1, side2, side3, side4, side5, side6};
		sideUp = r.nextInt(6);
	}
	
	// get a random letter from
	public String getLetter() {
		return sides[sideUp];
	}	
}
