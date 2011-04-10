package es.deusto.ingenieria.aike.ParkingLotMaze;

import java.awt.Point;



public class Cell extends Point {
		
	private static final long serialVersionUID = 1L;
	public static enum TypeCell {
		CROSS,
		CIRCLE
	}
	
	private TypeCell type;
	
		
	public Cell(TypeCell type, int row, int col) {
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


	public TypeCell getType() {
		return type;
	}

	public String getTypeString() {
		if (type.equals(TypeCell.CIRCLE))
				return "O";
		else return "X";
	}

	public void setType(TypeCell type) {
		this.type = type;
	}

	public Object clone() {
		return new Cell(type, this.getRow(), this.getColumn());
	}

		
}
