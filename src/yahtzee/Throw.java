package yahtzee;

import java.util.ArrayList;

public class Throw {
	private int[] diceThrow;
	
	public Throw() {
		this.diceThrow = new int[5];
	}
	
	public Throw(int[] diceThrow) {
		this.diceThrow = diceThrow;
	}
	
	public Throw(ArrayList<Die> dice) {
		this.diceThrow = new int[5];
		for (Die die: dice) {
			this.diceThrow[dice.indexOf(die)] = die.getThrownValue();
		}
	}
	
	public void printDiceThrow() {
		System.out.print("[" + this.diceThrow[0]);
		for (int i = 1; i < 5; i++) {
			System.out.print(", " + this.diceThrow[i]);
		}
		System.out.println("]");
	}
	
	public void setDiceThrow(int[] diceThrow) {
		this.diceThrow = diceThrow;
	}
	
	public int[] getDiceThrow() {
		return this.diceThrow;
	}
}
