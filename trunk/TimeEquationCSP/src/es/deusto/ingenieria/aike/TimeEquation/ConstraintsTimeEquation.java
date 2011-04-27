package es.deusto.ingenieria.aike.TimeEquation;

import java.awt.Point;
import java.util.List;

import es.deusto.ingenieria.aike.csp.formulation.Constraint;
import es.deusto.ingenieria.aike.csp.formulation.Variable;


//We have subclassed Constraint binding its parameter to Integer
//This means that the values the variables of the Queens Problem will take are Integers
public class ConstraintsTimeEquation extends Constraint<Integer> {
	
	public ConstraintsTimeEquation(List<Variable<Integer>> variables, String name){
		super(name, variables);
	}

		
	public boolean isSatisfied(Variable<Integer> variable, Integer value) {
		if (this.getName().equals("unary"))
		{ }
		if (this.getName().equals("binary"))
				{}
		if (this.getName().equals("global"))
			{}
			return true;}
	
}

				
				
					
			/*if (this.getName().equals("binary")){
				int  d=this.addNumber();
				while ((d*.getMultiplier())%10!=envi.getConstant())
					 d=envi.addNumber();
				System.out.println("d"+d+"multip "+envi.getMultiplier()+"modulo"+envi.getMultiplier()%10);
				int x1=d*envi.getMultiplier()/10;
				System.out.println(x1+" lalal");}
					
				/*•	D x M = Constant + 10 x X1
				•	A x M = E + X3
				•	FE <= Max Minutes
				*/
			//}
			//else if (this.getName().equals("global")){
				/*•	C x M = G + X1 + 10 x X2
				•	B x M = F + X2 + 10 x X3
				•	AB <= Max Minutes / Multiplier + X2
				}*/
			
//QUEENS
//--------------------------------------------------------------------------------

//public boolean isSatisfied(Variable<Integer> variable, Integer value) {
	//we associate a Point with the current queen variable (passed in the variable parameter)
	//this point is made out of the column and row values of the queen variable
	//and will be used to check whether other queens are on the same diagonal as the current queen
	//Point currentQueen = new Point(Integer.valueOf(variable.getName()), value);
	//Point auxQueen = null;
	
	//For each of the queen variables involved in the constraint
	//for (Variable<Integer> varAux : this.getVariables()) {
		//If the variable is a different queen from the current one and has a value assigned
		//if (!varAux.equals(variable) && varAux.hasValue()) {
			//associate a Point with such queen variable 
			//the point is made out of the column and row values of the queen
			//auxQueen = new Point(Integer.valueOf(varAux.getName()), varAux.getValue());
			//If this queen has the same row value (i.e. y value) as the current queen,
			//then the constraint is NOT satisfied
			//If the Point associated to this queen is on the  same diagonal as 
			//the Point associated to the current queen,
			//then the constraint is NOT satisfied either
			//if (currentQueen.y == auxQueen.y ||
			   // Math.abs(currentQueen.x - auxQueen.x) == Math.abs(currentQueen.y - auxQueen.y)) {
				//return false;
			//}
		//}
	//}
	
	//return true;
//---------------------------------------------------------------------------------------
	