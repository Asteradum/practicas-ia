package es.deusto.ingenieria.aike.ParkingLotMaze.Environment;

import java.awt.Point;



public class Cell extends Point {
		
	private static final long serialVersionUID = 1L;
	/**
	 * @uml.property  name="type"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Data.TypeCell type;
	
		
	public Cell(Data.TypeCell type, int row, int col) {
		super();
		this.type = type;
		setRow(row);
		setColumn(col);
	}
	

	public void setRow(int row) {
		this.y = row;
	}
		
	public int getRow() {
		return this.y;
	}
	
	public void setColumn(int column) {
		this.x = column;
	}
	
	public int getColumn() {
		return this.x;
	}
	
	public Point getPosition() {
		return this;
	}


	public String toString() {
		return "Cell Type: " + this.getType() + ", Row: " + this.getRow() + ", Column: " + this.getColumn();
	}


	/**
	 * @return
	 * @uml.property  name="type"
	 */
	public Data.TypeCell getType() {
		return type;
	}

	public String getTypeString() {
		if (type.equals(Data.TypeCell.CIRCLE))
				return "O";
		else return "X";
	}

	/**
	 * @param type
	 * @uml.property  name="type"
	 */
	public void setType(Data.TypeCell type) {
		this.type = type;
	}

	public Object clone() {
		return new Cell(type, this.getRow(), this.getColumn());
	}

		
}
