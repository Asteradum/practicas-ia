package es.deusto.ingenieria.aike.TimeEquation;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.aike.csp.formulation.Variable;

public class NodeConsistency implements ConstraintPropagation {

	@Override
	public TimeEquationProblem makeConsistent(TimeEquationProblem problem) {
		// TODO Auto-generated method stub
		
		for (int i=0;i<problem.getVariables().size();i++)
		{ List<Variable<Integer>> lista=problem.getVariables();
			
		if ((lista.get(i).getName().equals("D"))&& (lista.get(i).getName().equals("G")))
				{System.out.println(lista.get(i).getName());
				List<Integer> domain = new ArrayList<Integer>(6);		
				for (int j=1; j<=5; j++) 
					domain.add(j);
				lista.get(i).setDomain(domain);
				}
		
		}
		return problem;
		//Para checkear si es una constraint unaria solo hace falta preguntar si la constraint asociada a la variable es de tipo UnaryConstraint
		
	
	}

}
