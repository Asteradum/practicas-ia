package es.deusto.ingenieria.aike.ParkingLotMaze;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.aike.ParkingLotMaze.Flag.Direction;
import es.deusto.ingenieria.aike.formulation.Operator;
import es.deusto.ingenieria.aike.formulation.Problem;
import es.deusto.ingenieria.aike.formulation.State;
import es.deusto.ingenieria.aike.search.blind.BreadthFSwithLog;
import es.deusto.ingenieria.ingenieria.search.Node;
import es.deusto.ingenieria.ingenieria.search.SearchMethod;

public class ParkingLotMazeProblem extends Problem {
	
	public ParkingLotMazeProblem() {
		
	}

	public ParkingLotMazeProblem(Object information) {
		super();
		//this.createFinalStates(information);
		//this.createOperators(); Super() makes it;
	}

	// Generate finalState of the Car 
	// ¿It is necessary to create a new "environment" (Board) just for checking the car position?
	/*private void createFinalStates(Object information) {
		Board b = (Board) information;
		Direction d = null;
		Direction flagEn = b.getFlag().getEntrance();
		if (flagEn == Direction.SOUTH) d= Direction.NORTH;
		else if (flagEn == Direction.NORTH) d= Direction.SOUTH;
		else if (flagEn == Direction.EAST) d= Direction.WEST;
		else d= Direction.EAST;
		Car car = new Car(b.getFlag().getPosition(), d );
		State state = new State(new Board(b.getCells(), b.getTotalRows(), b.getTotalColumns(), car, b.getFlag()));
		this.addFinalState(state);
	}
	*/
	
	public boolean isFinalState(State state) {
		if (state != null) {
			Board b = (Board) state.getInformation(); 
			return b.getCar().getPosition().equals(b.getFlag().getPosition());
		} else {
			return false;
		}
	}

	protected void createOperators() {
		Operator operator = new MoveStraightOperator();
		this.addOperator(operator);
		operator = new TurnLeftOperator();
		this.addOperator(operator);
		operator = new TurnRightOperator();
		this.addOperator(operator);
	}
	
	public void solve(SearchMethod searchMethod) {
		 Node finalNode = searchMethod.search(this, this.getInitialStates().get(0));
		 
		 if (finalNode != null) {
				System.out.println("Solution found!!");
				List<String> operators = new ArrayList<String>();
				searchMethod.solutionPath(finalNode, operators);
				searchMethod.createSolutionLog(operators);
		 } 
		 else System.out.println("Unable to find the solution!");
	}

	

}
