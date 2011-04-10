package es.deusto.ingenieria.aike.ParkingLotMaze;

import es.deusto.ingenieria.aike.ParkingLotMaze.Cell.TypeCell;
import es.deusto.ingenieria.aike.ParkingLotMaze.Flag.Direction;
import es.deusto.ingenieria.aike.formulation.Operator;
import es.deusto.ingenieria.aike.formulation.State;

public class TurnRightOperator extends Operator {

	private Cell followingCarPos;
	private Direction followingCarDir;

	public TurnRightOperator() {
		super("Turns right", 1d);
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
				if (car.getPosition().getColumn() !=  board.getTotalColumns() - 1 ){
					followingCarPos = board.getCell( car.getPosition().getRow(), car.getPosition().getColumn() + 1 );
					followingCarDir = Direction.EAST;
				}
				else{
					System.out.println("Turn Right is not applicable because direction is NORTH and it cannot move right");
					return false;
				}
			else if ( car.getDirection().equals(Direction.SOUTH) )
				if ( car.getPosition().getColumn() !=1 ){
					followingCarPos = board.getCell( car.getPosition().getRow(), car.getPosition().getColumn() - 1 );
					followingCarDir = Direction.WEST;
				}	
				else{
					System.out.println("Turn Right is not applicable because direction is SOUTH and it cannot move left");
					return false;
				}
			else if ( car.getDirection().equals(Direction.WEST) )
				if ( car.getPosition().getRow() != 1 ){
					followingCarPos = board.getCell( car.getPosition().getRow() - 1, car.getPosition().getColumn() );
					followingCarDir = Direction.NORTH;
				}
				else{
					System.out.println("Turn Right is not applicable because direction is WEST and it cannot move up");
					return false;
				}
			else if ( car.getPosition().getRow() !=  board.getTotalRows() - 1 ){
				followingCarPos = board.getCell( car.getPosition().getRow() + 1, car.getPosition().getColumn() );
				followingCarDir = Direction.SOUTH;
			}
			else{
				System.out.println("Turn Right is not applicable because direction is SOUTH and it cannot move down");
				return false;
			}
			
			//Evaluating if the next position is the flag and then if it is possible to move in
			if ( flag.getPosition().equals(followingCarPos) )
				if ( !( ( flag.getEntrance().equals(Direction.NORTH) &&  car.getDirection().equals(Direction.EAST)) ||
					 ( flag.getEntrance().equals(Direction.SOUTH) &&  car.getDirection().equals(Direction.WEST)) ||
					 ( flag.getEntrance().equals(Direction.WEST) &&  car.getDirection().equals(Direction.NORTH)) ||
					 ( flag.getEntrance().equals(Direction.EAST) &&  car.getDirection().equals(Direction.SOUTH))
					) )
				{
					System.out.println("Turn Right is not applicable because there is a wall of the flag position");
					return true;
				}
				else{
					System.out.println("Turn Right is applicable, the next position is the flag and is: " + followingCarPos.toString() + " with a direction of: " + followingCarDir);
					return true;
				}
			else{
				System.out.println("Turn Right is applicable and the next position is: " + followingCarPos.toString() + " with a direction of: " + followingCarDir);
				return true;
			}
		}
		else{
			System.out.println("Turn Right is not applicable because the car position is not an X, is a " + carPositionType);
			return false;
		}
	}

	//It only changes the position of the car
	protected State effect(State state) {
		
		Board b = (Board) state.getInformation();
		Car car = new Car (followingCarPos, followingCarDir);
		
		System.out.println("Turn Right is going to be applied moving the car to: " + car.toString() + " and changing the car direction to " + car.getDirection());
		return new State( new Board(b.getCells(), b.getTotalRows(), b.getTotalColumns(), car ,b.getFlag() ) );

	}
}