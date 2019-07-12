package yahtzee;

import java.util.*;

/**
 * Class that holds the Yahtzee game and its objects together.
 * @author Luca Laarhoven
 *
 */
public class YahtzeeGame {
	ArrayList<Die> dice;
	boolean[] holdDice;
	Player[] players;
	int amountOfPlayers, amountOfDice;

	public YahtzeeGame() {
		/* Initialize and instantiate all variables for the dice. The amount of Dice needs to be in the interval [1,9] */
		this.amountOfDice = 5;
		this.dice = new ArrayList<>(this.amountOfDice);
		this.holdDice = new boolean[this.amountOfDice];
		for (int i = 0; i < this.amountOfDice; i++) {
			this.dice.add(new Die());
			this.holdDice[i] = false;
		}
		
		/* Initialize and instantiate all variables for the players. */
		this.amountOfPlayers = 2;
		this.players = new Player[this.amountOfPlayers];
		for (int i = 0; i < this.amountOfPlayers; i++) {
			this.players[i] = new Player();
		}
		
		/* Start the game */
		this.playGame();
	}

	/* 
	 * Method to play the game. This method keeps the game alive and once this is done running the game and the application stop.
	 */
	public void playGame() {
		System.out.println("Please type p to play yahtzee and q to quit");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();

		/* Loop that keeps the game running until the players type in q to stop */
		while (!input.equals("q")) {
			/* Check for valid input */
			if (input.equals("p")) {
				/* Loop through the players */
				for (int turn = 0; turn < this.amountOfPlayers; turn++) {
					System.out.println("Player " + (turn + 1) + ", you are up!");
					
					/* Loop for the first 2 dice throws */
					for (int i = 0; i < 2; i++) {
						/* Throw all the dice and decide which ones to keep */
						System.out.println("Throw " + (i + 1) + ":");
						this.throwAndPrint();
						
						System.out.println("");
						System.out.println("Which dice do you want to keep?");
						this.hold(scanner);
					}
					/* After the last dice throw you have to keep all the dice and the dice throw is added to the history */
					System.out.println("Throw 3:");
					this.throwAndPrint();
					this.players[turn].getThrowHistory().add(new Throw(this.dice));
					System.out.println(" , Added the throw to your throw history");
					
					/* Reset the boolean array that indicates which dice to hold */
					for (int i = 0; i < this.holdDice.length; i++) {
						this.holdDice[i] = false;
					}
				}
			}
			/* Read the next line of input */
			System.out.println("Please type p for a game or q to quit and hit return");
			input = scanner.nextLine();
		}
	}

	/*
	 * Method to determine which dice to keep after a dice throw. The method also checks for invalid input.
	 */
	private void hold(Scanner scanner) {
		/* Reset the boolean array to determine which dice to keep */
		for (int i = 0; i < this.amountOfDice; i++) {
			this.holdDice[i] = false;
		}

		/* Read the input on which dice to keep */
		String input = scanner.nextLine();
		
		/* Check if the input is more than the amount of dice we have or an empty string */
		while (input.length() > this.amountOfDice || input.length() < 1) {
			System.out.println("Please type a valid input for which dice you want to hold.");
			input = scanner.nextLine();
		}
		
		/* Check if the input just contains a 0 and nothing else, with only 0 we hold no dice */
		if (!(input.length() == 1 && input.charAt(0) == '0')) {
			/* Loop through the String */
			for (int i = 0; i < input.length(); i++) {
				/* Try to parse the input, it catches a NumberFormatException in case we encounter something that is not a digit */
				try {
					
					/* Check if the number typed in is of a higher value than the amount of dice that we have
					 * or that there is a 0 used in combination with other numbers */
					if (Integer.parseInt(input.substring(i, i+1)) > this.amountOfDice) {
						System.out.println("I don't understand which die you want to keep with " + input.charAt(i)
								+ ", ignoring it.");
					} else if (Integer.parseInt(input.substring(i, i+1)) == 0) {
						System.out.println("Can't use 0 in combination with other numbers, ignoring it");
					} else {
						/* Input looks to be correct. Using the digit to set the boolean array at the right index to true */
						this.holdDice[(Integer.parseInt(input.substring(i, i+1))) - 1] = true;
					}

				} catch (NumberFormatException ex) {
					/* We received something that isn't a number */
					System.out.println(input.charAt(i) + " isn't a number, ignoring it");
				}
			}
		}
	}
	
	/*
	 * Method that does a new dice throw and prints it on the screen.
	 */
	private void throwAndPrint() {
		for (Die die : this.dice) {
			if (!this.holdDice[this.dice.indexOf(die)]) {
				die.setThrownValue(die.throwDie());
			}
			System.out.print(die.getThrownValue() + " ");
		}
	}
}
