package yahtzee;

import java.util.ArrayList;

public class Player {
	private ArrayList<Throw> throwHistory;
	
	public Player() {
		this.throwHistory = new ArrayList<>();
	}
	
	public void setThrowHistory(ArrayList<Throw> throwHistory) {
		this.throwHistory = throwHistory;
	}
	
	public ArrayList<Throw> getThrowHistory() {
		return this.throwHistory;
	}
}
