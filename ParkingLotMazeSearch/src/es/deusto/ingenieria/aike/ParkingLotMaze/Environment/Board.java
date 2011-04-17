package es.deusto.ingenieria.aike.ParkingLotMaze.Environment;



public class Board {

/**
 * @uml.property  name="cells"
 */
private Cell[][] cells;
/**
 * @uml.property  name="totalRows"
 */
private int totalRows;
/**
 * @uml.property  name="totalColumns"
 */
private int totalColumns;
/**
 * @uml.property  name="car"
 * @uml.associationEnd  
 */
private Car car;
/**
 * @uml.property  name="flag"
 * @uml.associationEnd  
 */
private Flag flag; 
private double totalDistance=0;

	public Board(Cell[][] c, int rows, int columns, Car car, Flag f,double d){
		this.cells = c;
		this.totalRows = rows;
		this.totalColumns = columns;
		this.car = car;
		this.flag = f;
		this.totalDistance=d;
	}

	public Board(Cell[][] cells) {
		this.cells = cells;
	}
	
	public Board() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return
	 * @uml.property  name="cells"
	 */
	public Cell[][] getCells(){
		return this.cells;
	}
	
	public Cell getCells(int row, int column){
		return this.cells[row][column];		
	}	
	
	public void setCells(int row, int column,Cell element){
		 this.cells[row][column]=element;		
	}


	/**
	 * @return
	 * @uml.property  name="car"
	 */
	public Car getCar() {
		return car;
	}


	/**
	 * @param car
	 * @uml.property  name="car"
	 */
	public void setCar(Car car) {
		this.car = car;
	}


	/**
	 * @return
	 * @uml.property  name="flag"
	 */
	public Flag getFlag() {
		return flag;
	}


	/**
	 * @param flag
	 * @uml.property  name="flag"
	 */
	public void setFlag(Flag flag) {
		this.flag = flag;
	}


	/**
	 * @param cells
	 * @uml.property  name="cells"
	 */
	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}


	/**
	 * @return
	 * @uml.property  name="totalRows"
	 */
	public int getTotalRows() {
		return totalRows;
	}


	/**
	 * @param totalRows
	 * @uml.property  name="totalRows"
	 */
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}


	/**
	 * @return
	 * @uml.property  name="totalColumns"
	 */
	public int getTotalColumns() {
		return totalColumns;
	}


	/**
	 * @param totalColumns
	 * @uml.property  name="totalColumns"
	 */
	public void setTotalColumns(int totalColumns) {
		this.totalColumns = totalColumns;
	}
	/*
	public String toString() {
		String str = "|";		
		
		for(int j=1; j<totalColumns; j++) 
			str += "----"; 
		str += "|" + '\n';
		for(int i=1; i<totalRows; i++) {
			for(int j=1; j<totalColumns; j++) 
				if (flag.getPosition().equals(cells[i][j]))
					str += " | "+ "F"; 
				else str += " | "+cells[i][j].getTypeString(); 
			str += " |" + '\n' + "|";
			for(int j=1; j<totalColumns; j++) 
				str += "----"; 
			str += "|" + '\n';
		}
		
		return str;
	}*/
	
	/* This method is used for checking final state, the board is static, so it will not change
	 * The same for the flag
	 * The only object that changes in the game is the car, so the method checks it
	 */
	public boolean equals(Object obj) {
		if (obj != null	&& obj instanceof Board) {
			Board b = (Board)obj;
			if ( car.equals(b.getCar()) )
				return true;
			else return false;
		} else {
			return false;
		}
	}
	
	public String toString() {
		String str = car.toString();		
		return str;
	}
	
	public Cell getCell(int r, int c){
		if (cells[r][c] != null)
			return cells[r][c];
		else return null;
	}
	
	public void setCell(int r, int c, Cell cell){
		cells[r][c] = cell; 
	}
	
	public Object clone() {
		Board newBoard = new Board();
		
		newBoard.setCar((Car) car.clone());
		newBoard.setFlag((Flag) flag.clone());
		newBoard.setTotalColumns(totalColumns);
		newBoard.setTotalRows(totalRows);
		newBoard.setTotalDistance(totalDistance);
		Cell[][] newCells = new Cell[totalRows][totalColumns];
		
		for(int i=1; i<this.totalRows; i++) {
			for(int j=1; j<this.totalColumns; j++) {
				newCells[i][j] = (Cell) cells[i][j].clone();				
			}
		}
		newBoard.setCells(newCells);
		return newBoard;
	}

	public double getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(double totalDistance) {
		this.totalDistance = totalDistance;
	}

}
