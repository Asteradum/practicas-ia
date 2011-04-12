package es.deusto.ingenieria.aike.ParkingLotMaze.Operators;

import es.deusto.ingenieria.aike.ParkingLotMaze.Environment.Board;
import es.deusto.ingenieria.aike.ParkingLotMaze.Environment.Car;
import es.deusto.ingenieria.aike.ParkingLotMaze.Environment.Cell;
import es.deusto.ingenieria.aike.ParkingLotMaze.Environment.Flag;
import es.deusto.ingenieria.aike.ParkingLotMaze.Environment.Cell.TypeCell;
import es.deusto.ingenieria.aike.ParkingLotMaze.Environment.Flag.Direction;
import es.deusto.ingenieria.aike.formulation.Operator;
import es.deusto.ingenieria.aike.formulation.State;

public class TurnLeftOperator extends Operator {

	private Cell followingCarPos;
	private Direction followingCarDir;

	public TurnLeftOperator() {
		super("Turns left", 1d);
		System.out.println(this.toString());
	}

	protected boolean isApplicable(State state) {
		
		Board board = (Board) state.getInformation();
		TypeCell carPositionType = board.getCar().getPosition().getType();
		Flag flag = board.getFlag();
		Car car = board.getCar();
		followingCarPos = null;
		followingCarDir = null;
		
		if ( carPositionType.equals(TypeCell.CROSS) ){
			
			//Evaluating if the next position in the Maze is valid
			if ( car.getDirection().equals(Direction.NORTH) ) 
			{
				if (car.getPosition().getColumn() != 1 ){
					followingCarPos = board.getCell( car.getPosition().getRow(), car.getPosition().getColumn() - 1 );
					followingCarDir = Direction.WEST;
				}
				else return false;
			}
			else if ( car.getDirection().equals(Direction.SOUTH) )
			{
				if ( car.getPosition().getColumn() != board.getTotalColumns() - 1 ){
					followingCarPos = board.getCell( car.getPosition().getRow(), car.getPosition().getColumn() + 1 );
					followingCarDir = Direction.EAST;
				}	
				else return false;
			}
			else if ( car.getDirection().equals(Direction.WEST) )
			{
				if ( car.getPosition().getRow() != board.getTotalRows() - 1 ){
					followingCarPos = board.getCell( car.getPosition().getRow() + 1, car.getPosition().getColumn() );
					followingCarDir = Direction.SOUTH;
				}
				else return false;
			}
			else if ( car.getPosition().getRow() != 1 )
			{
				followingCarPos = board.getCell( car.getPosition().getRow() - 1, car.getPosition().getColumn() );
				followingCarDir = Direction.NORTH;
			}
			else return false;
			
			//Evaluating if the next position is the flag and then if it is possible to move in
			if ( flag.getPosition().equals(followingCarPos) )
			{
				if ( ( flag.getEntrance().equals(Direction.NORTH) &&  car.getDirection().equals(Direction.WEST)) ||
					 ( flag.getEntrance().equals(Direction.SOUTH) &&  car.getDirection().equals(Direction.EAST)) ||
					 ( flag.getEntrance().equals(Direction.WEST) &&  car.getDirection().equals(Direction.SOUTH)) ||
					 ( flag.getEntrance().equals(Direction.EAST) &&  car.getDirection().equals(Direction.NORTH))
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
		Car car = new Car (followingCarPos, followingCarDir);
		
		System.out.println("Turn Left is going to be applied moving the car to: " + car.toString() + " and changing the car direction to " + car.getDirection());
		b.addDistance();
		System.out.println(b.getTotalDistance());
		return new State( new Board(b.getCells(), b.getTotalRows(), b.getTotalColumns(), car ,b.getFlag() ) );

	}
}