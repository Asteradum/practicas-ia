package es.deusto.ingenieria.aike.queens;

import java.util.List;

import es.deusto.ingenieria.aike.csp.formulation.Variable;

//We have subclassed Variable binding its parameter to Integer
//This means that the values the variables of the Queens Problem will take are Integers
public class Queen extends Variable<Integer> {
	
	public Queen(int column, List<Integer> domainValues) {
		//Each queen is fixed to a different column. So the column names the queen
		super(String.valueOf(column), domainValues);
	}
	
	/**
	 * Returns a string describing the queen variable.
	 * The string specifies: "Queen Name = row value/?"
	 * 
	 * @return String, describing the variable.
	 */	
	public String toString() {
		String result = "Queen " + this.getName() + " = ";

		if (this.getValue() != null) {
			result += "row " + this.getValue();
		} else {
			result += "?";
		}

		return result;
	}
}