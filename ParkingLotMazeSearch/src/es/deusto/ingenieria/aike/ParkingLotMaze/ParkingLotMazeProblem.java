package es.deusto.ingenieria.aike.ParkingLotMaze;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.aike.ParkingLotMaze.Environment.Board;
import es.deusto.ingenieria.aike.ParkingLotMaze.Operators.MoveStraightOperator;
import es.deusto.ingenieria.aike.ParkingLotMaze.Operators.TurnLeftOperator;
import es.deusto.ingenieria.aike.ParkingLotMaze.Operators.TurnRightOperator;
import es.deusto.ingenieria.aike.formulation.Operator;
import es.deusto.ingenieria.aike.formulation.Problem;
import es.deusto.ingenieria.aike.formulation.State;
import es.deusto.ingenieria.ingenieria.search.Node;
import es.deusto.ingenieria.ingenieria.search.SearchMethod;

public class ParkingLotMazeProblem extends Problem {
	
	public ParkingLotMazeProblem() {
		super();
	}

	public ParkingLotMazeProblem(Object information) {
		super();
		//this.createFinalStates(information);
		//this.createOperators(); Super() makes it;
	}

	// Generate finalState of the Car 
	/*private void createFinalStates(Object information) {
		Board b = (Board) information;
		Direction carDir;
		Direction flagEn = b.getFlag().getEntrance();
		
		if (flagEn == Direction.SOUTH) carDir= Direction.NORTH;
		else if (flagEn == Direction.NORTH) carDir= Direction.SOUTH;
		else if (flagEn == Direction.EAST) carDir= Direction.WEST;
		else carDir= Direction.EAST;
		Car car = new Car(b.getFlag().getPosition(), carDir );
		
		Board newBoard = (Board) b.clone();
		newBoard.setCar(car);
		State state = new State(newBoard );
		this.addFinalState(state);
	}*/
	
	
	public boolean isFinalState(State state) {
		if (state != null) {
			Board b = (Board) state.getInformation(); 
			System.out.println("Final state: Car " + b.getCar().toString() + ", Flag " + b.getFlag().getPosition().toString() + "; Evaluation: " +  b.getCar().getPosition().equals(b.getFlag().getPosition()));
			return b.getCar().getPosition().equals(b.getFlag().getPosition());
		} else {

			return false;
		}
	}

	protected void createOperators() {
		Operator operator = new MoveStraightOperator();
		this.addOperator(operator);
		operator = new TurnRightOperator();
		this.addOperator(operator);
		operator = new TurnLeftOperator();
		this.addOperator(operator);
	}
	
	public void solve(SearchMethod searchMethod) {
		 Node finalNode = searchMethod.search(this, this.getInitialStates().get(0));
		 
		 if (finalNode != null) {
				System.out.println("Solution found!!");
				List<String> operators = new ArrayList<String>();
				searchMethod.solutionPath(finalNode, operators);
				
				Board board=(Board) finalNode.getState().getInformation();
				System.out.println("Total Cost: " +board.getTotalDistance());
				
				searchMethod.createSolutionLog(operators);
		 } 
		 else System.out.println("Unable to find the solution!");
	}

	

}
