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
		
		if ( ( variable!= null )&& ( this.getVariables().contains(variable) ) ){
			
			for (Variable<Integer> digit : getVariables() ){
				if ( (!digit.equals(variable) ) && ( !digit.hasValue() ) )  return true;
			}
			
			switch (addType) {
				case 2: // D x Multiplier = Constant + 10 x X1
					
					if ( variable.getName().equals("D") ) {
						if ( value * multiplier == constant + 10 * this.getVariables().get(1).getValue() )
							return true;
						else return false;
					} 
					else
						if ( this.getVariables().get(0).getValue() * multiplier == constant + 10 * value )
							return true;
						else return false;
					
				case 3: // A x Multiplier = E + X3 
					
					if ( variable.getName().equals("A") ) {
						if ( value * multiplier == this.getVariables().get(1).getValue() + this.getVariables().get(2).getValue() )
							return true;
						else return false;
					} 
					else if ( variable.getName().equals("E") ) {
						if ( this.getVariables().get(0).getValue() * multiplier == value + this.getVariables().get(2).getValue() )
							return true;
						else return false;
					} 
					else
						if ( this.getVariables().get(0).getValue() * multiplier == this.getVariables().get(1).getValue() + value )
							return true;
						else return false;
						
				case 4: // B x Multiplier = F + X2 + 10 x X3 OR C x Multiplier = G + X1 + 10 x X2
					
					if ( ( variable.getName().equals("B") ) || ( variable.getName().equals("C") )  ) {
						if ( value * multiplier == this.getVariables().get(1).getValue() + this.getVariables().get(2).getValue() + 10 * this.getVariables().get(3).getValue() )
							return true;
						else return false;
					} 
					else if ( ( variable.getName().equals("F") ) || ( variable.getName().equals("G") )  ) {
						if ( this.getVariables().get(0).getValue() * multiplier == value + this.getVariables().get(2).getValue() + 10 * this.getVariables().get(3).getValue() )
							return true;
						else return false;
					} 
					else if  ( variable.getName().equals("X1") ) {
						if ( this.getVariables().get(0).getValue() * multiplier == this.getVariables().get(1).getValue() + value + 10 * this.getVariables().get(3).getValue() )
							return true;
						else return false;
					} 
					else //if  ( variable.getName().equals("X2") ) { 
						if ( this.getVariables().get(2).equals(variable) ) //X2 is in the second position
							if ( this.getVariables().get(0).getValue() * multiplier == this.getVariables().get(1).getValue() + value + 10 * this.getVariables().get(3).getValue() )
								return true;
							else return false;
						else //X2 is in the third position or it is X3
							if ( this.getVariables().get(0).getValue() * multiplier == this.getVariables().get(1).getValue() + this.getVariables().get(2).getValue() + 10 * value )
								return true;
							else return false;
					/*} 
					else
						if ( this.getVariables().get(0).getValue() * multiplier == this.getVariables().get(1).getValue() + this.getVariables().get(2).getValue() + 10 * value )
							return true;
						else return false;
					*/
				default: System.out.println("Error, the add constraint does not have a correct type");
					return false;
			}
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