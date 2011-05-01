package es.deusto.ingenieria.aike.TimeEquation.ConstraintPropagation;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.aike.TimeEquation.TimeEquationProblem;
import es.deusto.ingenieria.aike.TimeEquation.Constraints.UnaryConstraint;
import es.deusto.ingenieria.aike.csp.formulation.Constraint;
import es.deusto.ingenieria.aike.csp.formulation.Variable;

public class NodeConsistency implements ConstraintPropagation {
	
	@Override
	public boolean makeConsistent(TimeEquationProblem problem) {
		// TODO Auto-generated method stub
		
		List<Variable<Integer>> list;
		List<Constraint<Integer>> listConst;
		int varSize = problem.getVariables().size();
		int constSize;
		int domSize;
		int value;
		UnaryConstraint cons;
		
		list=problem.getVariables();
		for (int i=0;i<varSize;i++){
			listConst = list.get(i).getConstraints();
			constSize = listConst.size();
			for (int j=0;j<constSize;j++){
				if (listConst.get(j) instanceof UnaryConstraint){
					
					cons = (UnaryConstraint) listConst.get(j);
					if (cons.getName().equals("DistinctFrom")){
						list.get(i).getDomain().remove( (Object)cons.getValue() );
						if ( list.get(i).getDomain().isEmpty() )
							return false;
					}
					else if (cons.getName().equals("EqualTo")){
						value = cons.getValue();						
						domSize = list.get(i).getDomain().size();
						for (int dom = 1; dom <= domSize ; dom++)
							if (dom != value)
								list.get(i).getDomain().remove( (Object)dom );
						if ( list.get(i).getDomain().isEmpty() )
							return false;
					}
					else { // LowerThan 	
						domSize=list.get(i).getDomain().size();
						for (int dom = cons.getValue(); dom <= domSize ; dom++)
							list.get(i).getDomain().remove( (Object)dom );
						if ( list.get(i).getDomain().isEmpty() )
							return false;
					}
					
				}
			}
			System.out.println( "FINAL DOMAIN OF " + list.get(i).getName() + " " + list.get(i).getDomain() );		
		}
		return true;
	}

}
