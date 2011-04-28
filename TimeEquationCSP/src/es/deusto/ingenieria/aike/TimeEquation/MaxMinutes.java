package es.deusto.ingenieria.aike.TimeEquation;

import java.awt.Point;
import java.util.List;

import es.deusto.ingenieria.aike.csp.formulation.Constraint;
import es.deusto.ingenieria.aike.csp.formulation.Variable;


//We have subclassed Constraint binding its parameter to Integer
//This means that the values the variables of the Queens Problem will take are Integers
public class MaxMinutes extends Constraint<Integer> {
	
	private int maxMinutes;
	private int ten;
	private int unit;

	public MaxMinutes(List<Variable<Integer>> variables, String name){
		// 0 = Ten
		// 1 = Unit
		super(name, variables);
	}

	public boolean isSatisfied(Variable<Integer> variable, Integer value) {
		
		if ( ( variable!= null )&& ( this.getVariables().contains(variable) ) ){
			// Y si la unidad tiene un valor superior al permitido en unit, pero el ten digit en el momento de asignar ese valor no tenia un valor superior al ten?  
			// Check if the variable is a ten or a unit Digit
			if ( (variable.getName().equals("A") || variable.getName().equals("E") ) ){
				 if ( value <= ten )
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
		this.maxMinutes = maxMinutes;
		this.ten = maxMinutes / 10;
		this.unit = maxMinutes % 10;
	}
}