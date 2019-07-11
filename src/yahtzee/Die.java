package yahtzee;

import java.util.Random;

public class Die {
	private int thrownValue;

	public int getThrownValue() {
		return thrownValue;
	}

	public void setThrownValue(int thrownValue) {
		this.thrownValue = thrownValue;
	}

	public int throwDie(){
		Random rnd = new Random();
		return rnd.nextInt(6) + 1;
	}
}
