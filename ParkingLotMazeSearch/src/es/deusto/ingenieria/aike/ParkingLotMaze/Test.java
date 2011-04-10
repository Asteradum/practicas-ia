package es.deusto.ingenieria.aike.ParkingLotMaze;

import es.deusto.ingenieria.aike.formulation.State;
import es.deusto.ingenieria.aike.xml.InformationXMLReader;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		InformationXMLReader entornoSAXParser = new MazeEnvironmentXMLReader("data/parkinglotmaze1.xml"); 	
		//Constructor of ParkingLotMazeProblem needs Board in order to know where is the flag so it can add a FinalState
		State initialState = new State((Board)entornoSAXParser.getInformation());
		//MoveStraight funciona NORTH, WEST, EAST
		//Turnleft funcion NORTH, WEST, SOUTH
		//TurnRight funcion NORTH, WEST
		MoveStraightOperator moverRecto = new MoveStraightOperator();
		
		// NORTH true
		if ( moverRecto.isApplicable(initialState) )
			initialState = moverRecto.effect(initialState);
		System.out.println();
		//NORTH false
		if ( moverRecto.isApplicable(initialState) )
			initialState = moverRecto.effect(initialState);
		
		TurnLeftOperator tLeft = new TurnLeftOperator();
		//NORTH true
		System.out.println();
		if ( tLeft.isApplicable(initialState) )
			initialState = tLeft.effect(initialState);
		//WEST false
		System.out.println();
		if ( tLeft.isApplicable(initialState) )
			initialState = tLeft.effect(initialState);
		
		//WEST true
		System.out.println();
		if ( moverRecto.isApplicable(initialState) )
			initialState = moverRecto.effect(initialState);
		
		//WEST false
		System.out.println();
		if ( tLeft.isApplicable(initialState) )
			initialState = tLeft.effect(initialState);
		
		TurnRightOperator tRight = new TurnRightOperator();
		
		//WEST true
		System.out.println();
		if ( tRight.isApplicable(initialState) )
			initialState = tRight.effect(initialState);
		
		// NORTH false
		System.out.println();
		if ( tRight.isApplicable(initialState) )
			initialState = tRight.effect(initialState);
		
		//NORTH true
		System.out.println();
		if ( moverRecto.isApplicable(initialState) )
			initialState = moverRecto.effect(initialState);

		// NORTH true
		System.out.println();
		if ( tLeft.isApplicable(initialState) )
			initialState = tLeft.effect(initialState);
		
		// WEST true
		System.out.println();
		if ( tRight.isApplicable(initialState) )
			initialState = tRight.effect(initialState);
		
		// NORTH true
		System.out.println();
		if ( tRight.isApplicable(initialState) )
			initialState = tRight.effect(initialState);
		
		//EAST true
		System.out.println();
		if ( moverRecto.isApplicable(initialState) )
			initialState = moverRecto.effect(initialState);
		
		//EAST true
		System.out.println();
		if ( moverRecto.isApplicable(initialState) )
			initialState = moverRecto.effect(initialState);
		//EAST true
		System.out.println();
		if ( moverRecto.isApplicable(initialState) )
			initialState = moverRecto.effect(initialState);
		//EAST true
		System.out.println();
		if ( moverRecto.isApplicable(initialState) )
			initialState = moverRecto.effect(initialState);
		//EAST true
		System.out.println();
		if ( moverRecto.isApplicable(initialState) )
			initialState = moverRecto.effect(initialState);
		//EAST true
		System.out.println();
		if ( moverRecto.isApplicable(initialState) )
			initialState = moverRecto.effect(initialState);
		
		
		
	}

}
