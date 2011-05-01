package es.deusto.ingenieria.aike.TimeEquation.ConstraintPropagation;

import java.util.List;

import es.deusto.ingenieria.aike.TimeEquation.TimeEquationProblem;
import es.deusto.ingenieria.aike.csp.formulation.Variable;

public interface ConstraintPropagation {
	
	
	public boolean makeConsistent(TimeEquationProblem problem);
}
