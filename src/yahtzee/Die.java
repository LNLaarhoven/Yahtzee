package yahtzee;

import java.util.Random;

/**
 * Class that represent a die
 * @author Luca Laarhoven
 *
 */
public class Die {
	private int thrownValue;

	public int getThrownValue() {
		return thrownValue;
	}

	public void setThrownValue(int thrownValue) {
		this.thrownValue = thrownValue;
	}

	/* 
	 * Throws this die and return the value. The die is always a 6-sided die.
	 */
	public int throwDie(){
		Random rnd = new Random();
		return rnd.nextInt(6) + 1;
	}
}
