package es.deusto.ingenieria.aike.ParkingLotMaze;

import es.deusto.ingenieria.aike.search.heuristic.EvaluationFunction;
import es.deusto.ingenieria.ingenieria.search.Node;

public class ManhattanDistance extends EvaluationFunction {

	public double calculateG(Node nodo) {
		return 0;
	}

	public double calculateH(Node nodo) {
		Board b = (Board) nodo.getState().getInformation();
		
		int distance = 0;		
		
		distance = (int) this.getManhattanDistance(b.getFlag().getPosition(), b.getCar().getPosition());
		
		return distance;
	}
	
	private double getManhattanDistance(Cell f, Cell c) {
		return Math.abs(f.getX() - c.getX()) + Math.abs(f.getY() - c.getY());
	}
}