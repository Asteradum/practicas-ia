package es.deusto.ingenieria.aike.TimeEquation;

import java.util.List;

import es.deusto.ingenieria.aike.csp.formulation.Constraint;
import es.deusto.ingenieria.aike.csp.formulation.Variable;


//We have subclassed Constraint binding its parameter to Integer
//This means that the values the variables of the Queens Problem will take are Integers
public class ConstraintsTimeEquation extends Constraint<Integer> {
	
	private List<Integer> xmlData=(List<Integer>)(new TimeEquationXMLReader("data/equationMinSec-1.xml")).getInformation();
	
	
	public ConstraintsTimeEquation(List<Variable<Integer>> variables, String name){
		super(name, variables);
	}

		
	public boolean isSatisfied(Variable<Integer> variable, Integer value) {
		if (this.getName().equals("unary"))
		{  if ((value<6) )
			return true;
		else return false;
			/*•	C<6
			•	G<6
			*/

		}
		if (this.getName().equals("binary"))
				{System.out.println("este valor:"+value*this.xmlData.get(0)%10);
				System.out.println(this.xmlData.get(1));
				if ((value*this.xmlData.get(0))%10==this.xmlData.get(1))
				{ 
					int x1=value*this.xmlData.get(0)/10;
					System.out.println(x1+" lalal");
					return true;}
				else return false;
			
			/*•	D x M = Constant + 10 x X1
			•	A x M = E + X3
			•	FE <= Max Minutes
			*/}
		if (this.getName().equals("global"))
			{
			/*•	C x M = G + X1 + 10 x X2
			•	B x M = F + X2 + 10 x X3
			•	AB <= Max Minutes / Multiplier + X2
			}*/
			}
		return true;}
	
}

