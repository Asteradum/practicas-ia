package es.deusto.ingenieria.aike.TimeEquation;

import java.awt.Point;
import java.util.List;

import es.deusto.ingenieria.aike.csp.formulation.Constraint;
import es.deusto.ingenieria.aike.csp.formulation.Variable;


//We have subclassed Constraint binding its parameter to Integer
//This means that the values the variables of the Queens Problem will take are Integers
public class EqualTo extends Constraint<Integer> {
	
	private int value; //the value of the Constant or the Multiplier

	public EqualTo(List<Variable<Integer>> variables, String name){
		super(name, variables);
	}

	public boolean isSatisfied(Variable<Integer> variable, Integer value) {
		
		if ( ( variable!= null )&& ( this.getVariables().contains(variable) ) && ( value == this.value ) )
			return true;
		else return false;
		
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}