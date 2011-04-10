package es.deusto.ingenieria.aike.ParkingLotMaze.Environment;

import es.deusto.ingenieria.aike.ParkingLotMaze.Environment.Flag.Direction;


public class Car{
	
	private Cell position;
	private Direction direction;
	
	public Car( Cell c, Direction d){
		position = c;
		direction = d;
	}
	
	public Cell getPosition() {
		return position;
	}
	public void setPosition(Cell cellPosition) {
		this.position = cellPosition;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public Object clone() {
		return new Car((Cell) position.clone(), direction);
	}
	
	public String toString() {
		return "Car [" + this.getPosition().toString() + ", Car direction: " + this.getDirection()+ "]";
	}
	public boolean equals(Object obj){
		if (obj != null	&& obj instanceof Car) {
			Car c = (Car)obj;
			
			if (c.getPosition().equals(this.position) && c.getDirection().equals(this.direction))
				return true;
			else return false;
			
		} else {
			return false;
		}
	}
}
