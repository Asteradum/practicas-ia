package es.deusto.ingenieria.aike.ParkingLotMaze;

import es.deusto.ingenieria.aike.ParkingLotMaze.Cell.TypeCell;
import es.deusto.ingenieria.aike.ParkingLotMaze.Flag.Direction;
import es.deusto.ingenieria.aike.formulation.Operator;
import es.deusto.ingenieria.aike.formulation.State;

public class MoveStraightOperator extends Operator {

	private Cell followingCarPos;
	// first movement;

	public MoveStraightOperator() {
		super("Moves Straight" , 1d);
		System.out.println(this.toString());
	}

	protected boolean isApplicable(State state) {
		
		Board board = (Board) state.getInformation();
		TypeCell carPositionType = board.getCar().getPosition().getType();
		Flag flag = board.getFlag();
		Car car = board.getCar();
		followingCarPos = null;
		

		
		if ( carPositionType.equals(TypeCell.CIRCLE) ){
			
			//Evaluating if it is the first move of the car
			if (car.getPosition().getRow() == board.getTotalRows() ){
				followingCarPos = board.getCell( car.getPosition().getRow() - 1, car.getPosition().getColumn() );
				return true;
			}
			else if (car.getPosition().getRow() == 0  ){
				followingCarPos = board.getCell( car.getPosition().getRow() + 1, car.getPosition().getColumn() );
				return true;
			}
			else if (car.getPosition().getColumn() == board.getTotalColumns()){ 
				followingCarPos = board.getCell( car.getPosition().getRow(), car.getPosition().getColumn() - 1 );
				return true;
			}
			else if (car.getPosition().getColumn() == 0  ){
				followingCarPos = board.getCell( car.getPosition().getRow(), car.getPosition().getColumn() + 1 );
				return true;
			}
			//Evaluating if the next position in the Maze is valid
			else if ( car.getDirection().equals(Direction.NORTH) ) 
			{

				if (car.getPosition().getRow()!=1 )
					followingCarPos = board.getCell( car.getPosition().getRow() - 1, car.getPosition().getColumn() );
				else return false;
			}
			else if ( car.getDirection().equals(Direction.SOUTH) )
			{
				if ( car.getPosition().getRow() != board.getTotalRows()-1 )
					followingCarPos = board.getCell( car.getPosition().getRow() + 1, car.getPosition().getColumn() );
				else return false;
			}
			else if ( car.getDirection().equals(Direction.WEST) )
			{
				if ( car.getPosition().getColumn() != 1 )
					followingCarPos = board.getCell( car.getPosition().getRow(), car.getPosition().getColumn() - 1 );
				else return false;
			}
			else if ( car.getDirection().equals(Direction.EAST) )
			{
				if ( car.getPosition().getColumn() != board.getTotalColumns() - 1 )
					followingCarPos = board.getCell( car.getPosition().getRow(), car.getPosition().getColumn() + 1 );
				else return false;
			}
			else return false;
			
			//Evaluating if the next position is the flag and then if it is possible to move in
			if ( flag.getPosition().equals(followingCarPos) )
			{				
				if ( ( flag.getEntrance().equals(Direction.NORTH) &&  car.getDirection().equals(Direction.SOUTH)) ||
					 ( flag.getEntrance().equals(Direction.SOUTH) &&  car.getDirection().equals(Direction.NORTH)) ||
					 ( flag.getEntrance().equals(Direction.WEST) &&  car.getDirection().equals(Direction.EAST)) ||
					 ( flag.getEntrance().equals(Direction.EAST) &&  car.getDirection().equals(Direction.WEST))
					)
					return true;
				else return false;
			}
			else return true;
		}
		// It is not a O, is a X
		else return false;	
	}

	//It only changes the position of the car
	protected State effect(State state) {
		
		Board b = (Board) state.getInformation();
		Car car = new Car (followingCarPos, b.getCar().getDirection());
		
		System.out.println("Move Straight is going to be applied moving the car to: " + car.toString());
		return new State( new Board(b.getCells(), b.getTotalRows(), b.getTotalColumns(), car ,b.getFlag() ) );

	}
}