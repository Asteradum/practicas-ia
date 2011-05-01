package es.deusto.ingenieria.aike.TimeEquation;

import java.util.List;

import es.deusto.ingenieria.aike.TimeEquation.ConstraintPropagation.NodeConsistency;
import es.deusto.ingenieria.aike.csp.algorithm.BackTracking;
import es.deusto.ingenieria.aike.xml.InformationXMLReader;

public class MainProgram {

	public static void main(String[] args) {
		try {						
			InformationXMLReader SAXParserEnvironment = new TimeEquationXMLReader("data/equationMinSec-3.xml");
			TimeEquationProblem problem = new TimeEquationProblem((List<Integer>)SAXParserEnvironment.getInformation());
			NodeConsistency consist=new NodeConsistency();
			BackTracking<Integer> backTracking = new BackTracking<Integer>();		
			if ( consist.makeConsistent(problem) )
			 	problem.solve(backTracking);		
			else System.out.println("Problem is unsolvable, there are variabels with domain zero");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}