package DS_Sem2Project;
import java.io.Serializable;

public class DS_Player implements Serializable, Comparable<DS_Player> {
	String name;
	int score;
	public DS_Player(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	// https://www.mkyong.com/java/java-object-sorting-example-comparable-and-comparator/
	public int compareTo(DS_Player player) {
		/**int compareScore = ((DS_Player) comparePlayer).getScore();
		return compareScore - this.score;**/
		if (score == player.getScore()) { return 0; }
		else if (score > player.getScore()) { return -1; }
		else { return 1; }
	}
	
}
