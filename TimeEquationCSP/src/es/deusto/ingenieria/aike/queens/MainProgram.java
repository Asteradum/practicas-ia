package es.deusto.ingenieria.aike.queens;

import es.deusto.ingenieria.aike.csp.algorithm.BackTracking;
import es.deusto.ingenieria.aike.formulation.State;
import es.deusto.ingenieria.aike.xml.InformationXMLReader;

public class MainProgram {

	public static void main(String[] args) {
		try {						
			/*
			InformationXMLReader entornoSAXParser = new MazeEnvironmentXMLReader("data/parkinglotmaze1.xml"); 	
			//ParkingLotMazeProblem problem= new ParkingLotMazeProblem((Board)entornoSAXParser.getInformation());
			ParkingLotMazeProblem problem= new ParkingLotMazeProblem();
			State initialState = new State((Board)entornoSAXParser.getInformation());
			 * */
			InformationXMLReader SAXParserEnvironment = new TimeEquationXMLReader("data/equationMinSec-4.xml");
			
			/*
			int size = 17;
			NQueenProblem problem = new NQueenProblem(size);						
			System.out.println("* CSP " + size + "-queen problem");
			
			BackTracking<Integer> backTracking = new BackTracking<Integer>();			
			problem.solve(backTracking);			
			*/
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}