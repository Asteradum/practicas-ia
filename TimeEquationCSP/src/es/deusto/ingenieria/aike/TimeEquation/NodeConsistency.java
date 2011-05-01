package es.deusto.ingenieria.aike.TimeEquation;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.aike.csp.formulation.Constraint;
import es.deusto.ingenieria.aike.csp.formulation.Variable;

public class NodeConsistency implements ConstraintPropagation {

	@Override
	public void makeConsistent(TimeEquationProblem problem) {
		// TODO Auto-generated method stub
		//tengo todas las variables con todos sus dominios
		List<Variable<Integer>> lista;
		List<Constraint<Integer>> listConst;
		int varSize = problem.getVariables().size();
		int varConst;
		UnaryConstraint cons;
		lista=problem.getVariables();
		for (int i=0;i<varSize;i++){
			//hago una lista con las constraints de cada variable
			listConst=lista.get(i).getConstraints();
			varConst = listConst.size();
			System.out.println("INICIAL "+lista.get(i).getName()+" "+lista.get(i).getDomain());
			for (int j=0;j<varConst;j++){
				if (listConst.get(j) instanceof UnaryConstraint){
					cons = (UnaryConstraint) listConst.get(j);
					if (cons.getName().equals("DistinctFrom")){
						int value=cons.getValue();
						lista.get(i).getDomain().remove((Object)value);
						//System.out.println(lista.get(i).getName()+" "+lista.get(i).getDomain());
						
					}
					else if (cons.getName().equals("EqualTo")){
						int value=cons.getValue();
						List<Integer> domain = new ArrayList<Integer>(1);		
						domain.add(value);
						lista.get(i).setDomain(domain);
						//System.out.println(lista.get(i).getName()+" "+lista.get(i).getDomain());
						/*int dom=1;
						while (dom!=value&& dom<10)
							{lista.get(i).getDomain().remove((Object)dom);
							dom++;}*/
					}
					else {
						int value=cons.getValue();
						List<Integer> domain = new ArrayList<Integer>(6);		
						for (int dom=1; dom<value; dom++)
							domain.add(dom);
						lista.get(i).setDomain(domain);
						//System.out.println(lista.get(i).getName()+" "+lista.get(i).getDomain());
						
					}
				}
			}
			System.out.println("FINAL"+lista.get(i).getName()+" "+lista.get(i).getDomain());		
		}
		
	}

}
