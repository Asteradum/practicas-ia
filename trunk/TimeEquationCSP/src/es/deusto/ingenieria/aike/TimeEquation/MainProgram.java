package es.deusto.ingenieria.aike.TimeEquation;

import java.util.List;

import es.deusto.ingenieria.aike.csp.algorithm.BackTracking;
import es.deusto.ingenieria.aike.formulation.State;
import es.deusto.ingenieria.aike.xml.InformationXMLReader;

public class MainProgram {

	public static void main(String[] args) {
		try {						
			
			InformationXMLReader SAXParserEnvironment = new TimeEquationXMLReader("data/equationMinSec-1.xml");
			
			TimeEquationProblem problem = new TimeEquationProblem((List<Integer>)SAXParserEnvironment.getInformation());
			/*
			int size = 17;
			NQueenProblem problem = new NQueenProblem(size);						
			System.out.println("* CSP " + size + "-queen problem");
			*/
			BackTracking<Integer> backTracking = new BackTracking<Integer>();			
			problem.solve(backTracking);			
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}