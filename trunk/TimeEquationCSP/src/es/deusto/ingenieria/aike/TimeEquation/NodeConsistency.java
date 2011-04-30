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
		for (int i=0;i<problem.getVariables().size();i++){
			List<Variable<Integer>> lista=problem.getVariables();
			//hago una lista con las constraints de cada variable
			List<Constraint<Integer>> listConst=lista.get(i).getConstraints();
			for (int j=0;j<listConst.size();j++){
				if (listConst.get(j).getName().equals("DistinctFrom")){
					//System.out.println(lista.get(i).getValue());
					//solo estan asignados los valores del multiplicador y de la constante
					//no se que valor quieres q quite del dominio, no se puede acceder a Value
					//xq todavia no esta asignado.Aparte no hay un RemoveValue asiq el dominio
					//hay q volver a crearlo borrando el anterior
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
				if (listConst.get(j).getName().equals("EqualTo")){
					
				}
				if (listConst.get(j).getName().equals("LowerThan")){
					List<Integer> domain = new ArrayList<Integer>(6);		
					for (int dom=1; dom<=5; dom++) 
						domain.add(dom);
					lista.get(i).setDomain(domain);
				}
			}
					
		}
		
	}

}
