package yahtzee;

import java.util.ArrayList;
import java.util.Scanner;

public class YahtzeeGame {
	ArrayList<Die> dice;
	boolean[] holdDice;
	Player[] players;
	int amountOfPlayers, amountOfDice;

	public YahtzeeGame() {
		this.amountOfDice = 5;
		this.dice = new ArrayList<>(this.amountOfDice);
		this.holdDice = new boolean[this.amountOfDice];
		for (int i = 0; i < this.amountOfDice; i++) {
			this.dice.add(new Die());
			this.holdDice[i] = false;
		}
		
		this.amountOfPlayers = 2;
		this.players = new Player[this.amountOfPlayers];
		for (int i = 0; i < this.amountOfPlayers; i++) {
			this.players[i] = new Player();
		}
		this.playGame();
	}

	public void playGame() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Please type p to play yahtzee and q to quit");
		String input = scanner.nextLine();

		while (!input.equals("q")) {
			if (input.equals("p")) {
				for (int turn = 0; turn < this.amountOfPlayers; turn++) {
					System.out.println("Player " + (turn + 1) + ", you are up!");
					for (int i = 0; i < 2; i++) {
						System.out.println("Throw " + (i + 1) + ":");
						this.throwAndPrint();
						
						System.out.println("");
						this.hold(scanner);
					}
					System.out.println("Throw 3:");
					this.throwAndPrint();

					this.players[turn].getThrowHistory().add(new Throw(this.dice));
					System.out.println(" , Added the throw to your throw history");
					for (int i = 0; i < this.holdDice.length; i++) {
						this.holdDice[i] = false;
					}
				}

			} else {
				System.out.println("Please either type p or q and hit return.");
			}
			System.out.println("Please type p for another game or q to quit and hit return");
			input = scanner.nextLine();
		}
	}

	private void hold(Scanner scanner) {
		for (int i = 0; i < this.amountOfDice; i++) {
			this.holdDice[i] = false;
		}

		String input = scanner.nextLine();
		while (input.length() > this.amountOfDice || input.length() < 1) {
			System.out.println("Please type a valid input for which dice you want to hold.");
			input = scanner.nextLine();
		}

		char[] splitInput = new char[input.length()];

		for (int i = 0; i < input.length(); i++) {
			splitInput[i] = input.charAt(i);
		}

		if (!(splitInput.length == 1 && splitInput[0] == '0')) {
			for (int i = 0; i < input.length(); i++) {
				try {

					if (Integer.parseInt(splitInput[i] + "") > this.amountOfDice) {
						System.out.println("I don't understand which die you want to keep with " + splitInput[i]
								+ ", ignoring it.");
					} else if (Integer.parseInt(splitInput[i] + "") == 0) {
						System.out.println("Can't use 0 in combination with other numbers, ignoring it");
					} else {
						this.holdDice[(Integer.parseInt(splitInput[i] + "")) - 1] = true;
					}

				} catch (NumberFormatException ex) {
					System.out.println(splitInput[i] + " isn't a number, ignoring it");
				}
			}
		}
	}
	
	private void throwAndPrint() {
		for (Die die : this.dice) {
			if (!this.holdDice[this.dice.indexOf(die)]) {
				die.setThrownValue(die.throwDie());
			}
			System.out.print(die.getThrownValue() + " ");
		}
	}
}
