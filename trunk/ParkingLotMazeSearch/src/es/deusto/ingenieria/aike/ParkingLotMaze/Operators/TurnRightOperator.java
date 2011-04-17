package es.deusto.ingenieria.aike.ParkingLotMaze.Operators;

import es.deusto.ingenieria.aike.ParkingLotMaze.Environment.Board;
import es.deusto.ingenieria.aike.ParkingLotMaze.Environment.Car;
import es.deusto.ingenieria.aike.ParkingLotMaze.Environment.Cell;
import es.deusto.ingenieria.aike.ParkingLotMaze.Environment.Data;
import es.deusto.ingenieria.aike.ParkingLotMaze.Environment.Flag;
import es.deusto.ingenieria.aike.formulation.Operator;
import es.deusto.ingenieria.aike.formulation.State;

public class TurnRightOperator extends Operator {

	/**
	 * @uml.property  name="followingCarPos"
	 * @uml.associationEnd  
	 */
	private Cell followingCarPos;
	/**
	 * @uml.property  name="followingCarDir"
	 * @uml.associationEnd  
	 */
	private Data.Direction followingCarDir;
	
	public TurnRightOperator() {
		super("Turns right", 1d);
		System.out.println(this.toString());
	}

	protected boolean isApplicable(State state) {
		
		Board board = (Board) state.getInformation();
		Data.TypeCell carPositionType = board.getCar().getPosition().getType();
		Flag flag = board.getFlag();
		Car car = board.getCar();
		followingCarPos = null;
		followingCarDir = null;
		
		
		if ( carPositionType.equals(Data.TypeCell.CROSS) ){
			
			//Evaluating if the next position in the Maze is valid
			if ( car.getDirection().equals(Data.Direction.NORTH) )
			{
				if (car.getPosition().getColumn() !=  board.getTotalColumns() - 1 ){
					followingCarPos = board.getCell( car.getPosition().getRow(), car.getPosition().getColumn() + 1 );
					followingCarDir = Data.Direction.EAST;
				}
				else return false;
			}
			else if ( car.getDirection().equals(Data.Direction.SOUTH) )
			{
				if ( car.getPosition().getColumn() !=1 ){
					followingCarPos = board.getCell( car.getPosition().getRow(), car.getPosition().getColumn() - 1 );
					followingCarDir = Data.Direction.WEST;
				}	
				else return false;
			}
			else if ( car.getDirection().equals(Data.Direction.WEST) )
			{
				if ( car.getPosition().getRow() != 1 ){
					followingCarPos = board.getCell( car.getPosition().getRow() - 1, car.getPosition().getColumn() );
					followingCarDir = Data.Direction.NORTH;
				}
				else return false;
			}
			else if ( car.getDirection().equals(Data.Direction.WEST) )
			{
				if ( car.getPosition().getRow() !=  board.getTotalRows() - 1 ){
					followingCarPos = board.getCell( car.getPosition().getRow() + 1, car.getPosition().getColumn() );
					followingCarDir = Data.Direction.SOUTH;
				}
				else return false;
			}
			else return false;
			
			//Evaluating if the next position is the flag and then if it is possible to move in
			if ( flag.getPosition().equals(followingCarPos) )
			{
				if ( ( flag.getEntrance().equals(Data.Direction.NORTH) &&  car.getDirection().equals(Data.Direction.EAST)) ||
					 ( flag.getEntrance().equals(Data.Direction.SOUTH) &&  car.getDirection().equals(Data.Direction.WEST)) ||
					 ( flag.getEntrance().equals(Data.Direction.WEST) &&  car.getDirection().equals(Data.Direction.NORTH)) ||
					 ( flag.getEntrance().equals(Data.Direction.EAST) &&  car.getDirection().equals(Data.Direction.SOUTH))
					) 
					return true;
				else return false;
			}
			else return true;
		}
		// It is not a X, is a O
		else return false;
	}

	//It only changes the position of the car
	protected State effect(State state) {
		
		Board b = (Board) state.getInformation();
		Car car = new Car (this.followingCarPos, this.followingCarDir);
		
		double dist=b.getTotalDistance()+this.getCost();
		
		Board newBoard = (Board) b.clone();
		newBoard.setTotalDistance(dist);
		newBoard.setCar(car);
		
		System.out.println("Turn Right is going to be applied moving the car to: " + car.toString() + " and changing the car direction to " + car.getDirection());
		return new State( newBoard );

	}
}