package es.deusto.ingenieria.aike.ParkingLotMaze;

import es.deusto.ingenieria.aike.formulation.State;
import es.deusto.ingenieria.aike.ParkingLotMaze.ManhattanDistance;
import es.deusto.ingenieria.aike.ParkingLotMaze.Environment.Board;
import es.deusto.ingenieria.aike.search.blind.BreadthFSwithLog;
import es.deusto.ingenieria.aike.search.blind.DepthFSwithLog;
import es.deusto.ingenieria.aike.search.heuristic.BestFSwithLog;


import es.deusto.ingenieria.aike.xml.InformationXMLReader;
import es.deusto.ingenieria.ingenieria.search.SearchMethod;

public class MainProgram {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			
			InformationXMLReader entornoSAXParser = new MazeEnvironmentXMLReader("data/parkinglotmaze1.xml"); 	
			//Constructor of ParkingLotMazeProblem needs Board in order to know where is the flag so it can add a FinalState
			ParkingLotMazeProblem problem= new ParkingLotMazeProblem((Board)entornoSAXParser.getInformation());
			State initialState = new State((Board)entornoSAXParser.getInformation());
			problem.addInitialState(initialState);		
			//problem.solve(DepthFSwithLog.getInstance());	
			SearchMethod search = new BestFSwithLog(new ManhattanDistance());
			problem.solve(search);
		} catch (Exception ex) {
			System.err.println("% [Main Program] Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

}
