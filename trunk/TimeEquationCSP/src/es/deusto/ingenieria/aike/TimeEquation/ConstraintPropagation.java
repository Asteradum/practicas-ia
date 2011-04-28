package es.deusto.ingenieria.aike.TimeEquation;

import java.util.List;

import es.deusto.ingenieria.aike.csp.formulation.Variable;

public interface ConstraintPropagation {
	
	
	public TimeEquationProblem makeConsistent(TimeEquationProblem problem);
}
