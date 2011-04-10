package es.deusto.ingenieria.aike.ParkingLotMaze;



public class Board {

private Cell[][] cells;
private int totalRows;
private int totalColumns;
private Car car;
private Flag flag; 

	public Board(Cell[][] c, int rows, int columns, Car car, Flag f){
		this.cells = c;
		this.totalRows = rows;
		this.totalColumns = columns;
		this.car = car;
		this.flag = f;
	}

	public Board(Cell[][] tiles) {
		this.cells = tiles;
	}
	
	public Board() {
		// TODO Auto-generated constructor stub
	}

	public Cell[][] getCells(){
		return this.cells;
	}
	
	
	public Cell getCells(int row, int column){
		return this.cells[row][column];		
	}	
	
	public void setCells(int row, int column,Cell element){
		 this.cells[row][column]=element;		
	}


	public Car getCar() {
		return car;
	}


	public void setCar(Car car) {
		this.car = car;
	}


	public Flag getFlag() {
		return flag;
	}


	public void setFlag(Flag flag) {
		this.flag = flag;
	}


	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}


	public int getTotalRows() {
		return totalRows;
	}


	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}


	public int getTotalColumns() {
		return totalColumns;
	}


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
	}
	
	public boolean equals(Object obj) {
		if (obj != null	&& obj instanceof Board) {
			Board b = (Board)obj;
			if (b.getTotalColumns() == this.getTotalColumns() && b.getTotalRows() == this.getTotalRows()){
			
				for(int i=1; i<totalRows; i++) {
					for (int j=1; i<totalColumns; j++)
						if (!cells[i][j].equals(b.getCell(i, j)))
							return false;
				}
				
				if (!flag.equals(b.getFlag()))
					return false;
				
				if (!car.equals(b.getCar()))
					return false;
				
				return true;
			}
			else return false;
		} else {
			return false;
		}
	}
	*/
	
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
		
		for(int i=1; i<this.totalRows; i++) {
			for(int j=1; j<this.totalColumns; j++) {
				newBoard.setCell(i, j, (Cell) cells[i][j].clone());				
			}
		}
		return newBoard;
	}

}
