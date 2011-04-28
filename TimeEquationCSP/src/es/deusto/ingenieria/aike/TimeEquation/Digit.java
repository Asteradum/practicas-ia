package es.deusto.ingenieria.aike.TimeEquation;

import java.util.List;

import es.deusto.ingenieria.aike.csp.formulation.Variable;

public class Digit extends Variable<Integer> {

	
	public Digit(String string, List<Integer> domainValues) {
		//Each digit is fixed to a different box (A, B, C...). So the box names the digit
		super(string, domainValues);
	}
	
	/**
	 * Returns a string describing the queen variable.
	 * The string specifies: "Queen Name = row value/?"
	 * 
	 * @return String, describing the variable.
	 */	
	public String toString() {
		String result = "Digit " + this.getName() + " = ";

		if (this.getValue() != null) {
			result += + this.getValue();
		} else {
			result += "?";
		}

		return result;
	}
}