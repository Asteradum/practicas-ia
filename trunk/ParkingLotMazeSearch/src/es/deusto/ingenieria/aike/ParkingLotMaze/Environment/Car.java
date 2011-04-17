package es.deusto.ingenieria.aike.ParkingLotMaze.Environment;


public class Car{
	
	/**
	 * @uml.property  name="position"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Cell position;
	/**
	 * @uml.property  name="direction"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Data.Direction direction;
	
	public Car( Cell c, Data.Direction d){
		position = c;
		direction = d;
	}
	
	/**
	 * @return
	 * @uml.property  name="position"
	 */
	public Cell getPosition() {
		return position;
	}
	/**
	 * @param cellPosition
	 * @uml.property  name="position"
	 */
	public void setPosition(Cell cellPosition) {
		this.position = cellPosition;
	}
	/**
	 * @return
	 * @uml.property  name="direction"
	 */
	public Data.Direction getDirection() {
		return direction;
	}
	/**
	 * @param direction
	 * @uml.property  name="direction"
	 */
	public void setDirection(Data.Direction direction) {
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
