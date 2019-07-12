package yahtzee;

import java.util.ArrayList;

/**
 * Class that represents a dice throw. It holds the value of the dice in an int array.
 * @author Luca Laarhoven
 *
 */
public class Throw {
	private int[] diceThrow;
	
	/* The following 4 constructors are all valid ways to instantiate a Throw object */
	public Throw() {
		this.diceThrow = new int[5];
	}
	
	public Throw(int amountOfDice) {
		this.diceThrow = new int[amountOfDice];
	}
	
	public Throw(int[] diceThrow) {
		this.diceThrow = diceThrow;
	}
	
	public Throw(ArrayList<Die> dice) {
		this.diceThrow = new int[dice.size()];
		for (Die die: dice) {
			this.diceThrow[dice.indexOf(die)] = die.getThrownValue();
		}
	}
	
	/*
	 * Method to print this dice throw
	 */
	public void printDiceThrow() {
		System.out.print("[" + this.diceThrow[0]);
		for (int i = 1; i < this.diceThrow.length; i++) {
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
