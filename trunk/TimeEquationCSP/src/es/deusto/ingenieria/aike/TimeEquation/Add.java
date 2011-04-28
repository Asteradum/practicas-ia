package es.deusto.ingenieria.aike.TimeEquation;

import java.util.List;

import es.deusto.ingenieria.aike.csp.formulation.Constraint;
import es.deusto.ingenieria.aike.csp.formulation.Variable;

public class Add extends Constraint<Integer> {
	
	private int addType;
	private static int multiplier = 0;
	private static int constant = 0;
	
	public Add(List<Variable<Integer>> variables, String name){
		super(name, variables);
		
		addType = variables.size();		
	}

	public boolean isSatisfied(Variable<Integer> variable, Integer value) {		
		
		if ( ( variable!= null )&& ( this.getVariables().contains(variable) ) )
			
			switch (addType) {
				case 2: // D x Multiplier = Constant + 10 x X1
					
					if ( this.getVariables().get(0).getValue() * multiplier == constant + 10 * this.getVariables().get(1).getValue() )
						return true;
					else return false;
					
				case 3: // A x Multiplier = E + X3 
					
					if ( this.getVariables().get(0).getValue() * multiplier == this.getVariables().get(1).getValue() + this.getVariables().get(2).getValue() )
						return true;
					else return false;
					
				case 4: // B x Multiplier = F + X2 + 10 x X3 OR C x Multiplier = G + X1 + 10 x X2
					
					if ( this.getVariables().get(0).getValue() * multiplier == this.getVariables().get(1).getValue() + this.getVariables().get(2).getValue() + 10 * this.getVariables().get(3).getValue() )
						return true;
					else return false;
					
				default: System.out.println("Error, the add constraint does not have a correct type");
					return false;
			}
		else return false;
		
	}

	public void setMultiplier(int multiplier) {
		Add.multiplier = multiplier;
	}

	public int getMultiplier() {
		return multiplier;
	}

	public void setConstant(int constant) {
		Add.constant = constant;
	}

	public int getConstant() {
		return constant;
	}
}