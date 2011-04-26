package es.deusto.ingenieria.aike.queens;

import es.deusto.ingenieria.aike.csp.algorithm.BackTracking;
import es.deusto.ingenieria.aike.xml.InformationXMLReader;

public class MainProgram {

	public static void main(String[] args) {
		try {						
			InformationXMLReader SAXParserEnvironment = new TimeEquationXMLReader("data/equationMinSec-4.xml");
			System.out.println("* CSP -TimeEquationProblem");
			Environment envi=(Environment) SAXParserEnvironment.getInformation();
			TimeEquationProblem problem = new TimeEquationProblem(envi);	
			BackTracking<Integer> backTracking = new BackTracking<Integer>();			
			problem.solve(backTracking);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}