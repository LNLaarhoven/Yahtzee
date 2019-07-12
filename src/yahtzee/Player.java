package yahtzee;

import java.util.ArrayList;

/**
 * Class that represent a player in the Yahtzee game. This class holds the dice throw history of a player.
 * @author Luca Laarhoven
 *
 */
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
	
	/*
	 * Method to print the dice throw history of a player
	 */
	public void printThrowHistory() {
		for (Throw diceThrow: this.throwHistory) {
			diceThrow.printDiceThrow();
		}
	}
}
