package es.deusto.ingenieria.aike.TimeEquation.Constraints;

import java.util.List;

import es.deusto.ingenieria.aike.csp.formulation.Constraint;
import es.deusto.ingenieria.aike.csp.formulation.Variable;

public class MaxMinutes extends Constraint<Integer> {
	
	private static int maxMinutes;
	private static int ten;
	private static int unit;

	public MaxMinutes(List<Variable<Integer>> variables, String name){
		// 0 = Ten
		// 1 = Unit
		super(name, variables);
	}

	public boolean isSatisfied(Variable<Integer> variable, Integer value) {
		
		if ( ( variable!= null )&& ( this.getVariables().contains(variable) ) ){
			
			for (Variable<Integer> digit : getVariables() ){
				if ( (!digit.equals(variable) ) && ( !digit.hasValue() ) )  return true;
			}
			  
			// Check if the variable is a ten or a unit Digit
			if ( (variable.getName().equals("A") || variable.getName().equals("E") ) ){
				 if ( value <= ten  && this.getVariables().get(1).getValue() <= unit )
					 return true;
				 else return false;
			}
			else //is a unit
				//Check if the tens have a maximum value
				if ( this.getVariables().get(0).getValue() < ten )
					return true;
				else if ( value <= unit )
					return true;
				else return false;
		}
		else return false;
		
	}

	public int getMaxMinutes() {
		return maxMinutes;
	}

	public void setMaxMinutes(int maxMinutes) {
		MaxMinutes.maxMinutes = maxMinutes;
		MaxMinutes.ten = maxMinutes / 10;
		MaxMinutes.unit = maxMinutes % 10;
	}
}