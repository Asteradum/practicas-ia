package es.deusto.ingenieria.aike.TimeEquation;

import java.awt.Point;
import java.util.List;

import es.deusto.ingenieria.aike.csp.formulation.Constraint;
import es.deusto.ingenieria.aike.csp.formulation.Variable;


//We have subclassed Constraint binding its parameter to Integer
//This means that the values the variables of the Queens Problem will take are Integers
public class LowerThan extends Constraint<Integer> {
	
	final int VALUE = 6;

	public LowerThan(List<Variable<Integer>> variables, String name){
		super(name, variables);
	}

	public boolean isSatisfied(Variable<Integer> variable, Integer value) {
		
		if ( ( variable!= null )&& ( this.getVariables().contains(variable) ) && ( value < VALUE ) )
			return true;
		else return false;
		
	}
}