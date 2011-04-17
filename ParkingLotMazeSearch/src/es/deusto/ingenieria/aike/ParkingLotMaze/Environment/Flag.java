package es.deusto.ingenieria.aike.ParkingLotMaze.Environment;



public class Flag{
	
	/**
	 * @uml.property  name="cellPosition"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Cell cellPosition;
	/**
	 * @uml.property  name="entrance"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Data.Direction entrance;
	
	public Flag( Cell c, Data.Direction e){
		cellPosition = c;
		entrance = e;
	}
	
	public Cell getPosition() {
		return cellPosition;
	}
	public void setPosition(Cell cellPosition) {
		this.cellPosition = cellPosition;
	}
	/**
	 * @return
	 * @uml.property  name="entrance"
	 */
	public Data.Direction getEntrance() {
		return entrance;
	}
	/**
	 * @param direction
	 * @uml.property  name="entrance"
	 */
	public void setEntrance(Data.Direction direction) {
		this.entrance = direction;
	}
	public Object clone() {
		return new Flag((Cell) cellPosition.clone(), entrance);
	}
	
	public String toString() {
		return "Flag [" + this.getPosition().toString() + ", Flag entrance: " + this.getEntrance()+ "]";
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
