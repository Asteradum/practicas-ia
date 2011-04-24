package es.deusto.ingenieria.aike.queens;

import java.util.List;

import es.deusto.ingenieria.aike.csp.formulation.Variable;

//We have subclassed Variable binding its parameter to Integer
//This means that the values the variables of the Queens Problem will take are Integers
public class Digit extends Variable<Integer> {

	
	public Digit(char box, List<Integer> domainValues) {
		//Each digit is fixed to a different box (A, B, C...). So the box names the digit
		super(String.valueOf(box), domainValues);
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