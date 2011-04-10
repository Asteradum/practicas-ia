package es.deusto.ingenieria.aike.ParkingLotMaze;

import es.deusto.ingenieria.aike.ParkingLotMaze.Cell.TypeCell;


public class Flag{
	
	private Cell cellPosition;
	public static enum Direction {
		NORTH,
		SOUTH,
		WEST,
		EAST
	}
	private Direction entrance;
	
	public Flag( Cell c, Direction e){
		cellPosition = c;
		entrance = e;
	}
	
	public Cell getPosition() {
		return cellPosition;
	}
	public void setPosition(Cell cellPosition) {
		this.cellPosition = cellPosition;
	}
	public Direction getEntrance() {
		return entrance;
	}
	public void setEntrance(Direction direction) {
		this.entrance = direction;
	}
	public Object clone() {
		return new Flag((Cell) cellPosition.clone(), entrance);
	}
	
	public String toString() {
		return "Flag [" + this.getPosition().toString() + ", Flag direction: " + this.getEntrance()+ "]";
	}
	public boolean equals(Object obj){
		if (obj != null	&& obj instanceof Flag) {
			Flag f = (Flag)obj;
			
			if (f.getPosition().equals(this.cellPosition) && f.getEntrance().equals(this.entrance))
				return true;
			else return false;
			
		} else {
			return false;
		}
	}
}
