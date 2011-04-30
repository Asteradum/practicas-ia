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
		for (int i=0;i<varSize;i++){
			lista=problem.getVariables();
			//hago una lista con las constraints de cada variable
			listConst=lista.get(i).getConstraints();
			varConst = listConst.size();
			for (int j=0;j<varConst;j++){
				if (listConst.get(j) instanceof UnaryConstraint){
					cons = (UnaryConstraint) listConst.get(j);
					if (cons.getName().equals("DistinctFrom")){
						//System.out.println(listaConst.get(j).getValue());
						//System.out.println(lista.get(i).getValue());
						// Mira tu sysout y comparalo con el mio, 
						// estabas recogiendo valores de variables, no de constraints
						List<Integer> domain = new ArrayList<Integer>(9);
						int multiplier=lista.get(4).getValue();
						int constant=lista.get(8).getValue();
						int d=1;
						while (d<10){
							if ((d!=multiplier) &&(d!=constant))
								domain.add(d);
							d++;
						}
						lista.get(i).setDomain(domain);
					}
					else if (cons.getName().equals("EqualTo")){
						
					}
					else {
						List<Integer> domain = new ArrayList<Integer>(6);		
						for (int dom=1; dom<=5; dom++) 
							domain.add(dom);
						lista.get(i).setDomain(domain);
					}
				}
			}
					
		}
		
	}

}
