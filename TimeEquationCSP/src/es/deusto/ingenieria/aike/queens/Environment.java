package es.deusto.ingenieria.aike.queens;

public class Environment {
	
	private Digit[] digits;
	private int multiplier;
	private int constant;
	private int maxMinutes;
	
	public Environment( int mult, int c, int min){
		this.multiplier = mult;
		this.constant = c;
		this.maxMinutes = min;
	}
	
	public Digit[] getDigits() {
		return digits;
	}
	public void setDigits(Digit[] digits) {
		this.digits = digits;
	}
	public int getMultiplier() {
		return multiplier;
	}
	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}
	public int getConstant() {
		return constant;
	}
	public void setConstant(int constant) {
		this.constant = constant;
	}

	public void setMaxMinutes(int maxMinutes) {
		this.maxMinutes = maxMinutes;
	}

	public int getMaxMinutes() {
		return maxMinutes;
	}

}
