package es.deusto.ingenieria.aike.TimeEquation.Constraints;

import java.util.List;

import es.deusto.ingenieria.aike.csp.formulation.Constraint;
import es.deusto.ingenieria.aike.csp.formulation.Variable;


public class UnaryConstraint extends Constraint<Integer> {

	// value can be constant, multiplier or the limitation of the seconds
	private int value;
	// 1 = LowerThan; 2 = EqualTo; 3 = DistinctFrom
	private int constraintType;
	
	public UnaryConstraint(List<Variable<Integer>> variables, String name){
		super(name, variables);
		
		if ( name.equals("LowerThan") )	constraintType = 1;
		else if ( name.equals("EqualTo") )	constraintType = 2;
		else if ( name.equals("DistinctFrom") )	constraintType = 3;
		else constraintType = 0;
	}

	public boolean isSatisfied(Variable<Integer> variable, Integer value) {
		
		if ( ( variable!= null ) && ( this.getVariables().contains(variable) ) ) {
			
			switch (constraintType) {
				case 1: // LowerThan
					
					if ( value < this.value ) 
						return true;
					else return false;
					
				case 2: //EqualTo 
					
					if ( value == this.value ) 
						return true;
					else return false;
					
				case 3: // DistinctFrom
					
					if ( value != this.value ) 
						return true;
					else return false;
					
				default: System.out.println("Error, the unary constraint does not have a correct type");
					return false;
			}
		}
		else return false;
		
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}