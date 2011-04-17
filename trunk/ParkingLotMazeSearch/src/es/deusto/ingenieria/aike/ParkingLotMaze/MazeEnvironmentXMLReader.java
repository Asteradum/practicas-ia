package es.deusto.ingenieria.aike.ParkingLotMaze;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import es.deusto.ingenieria.aike.ParkingLotMaze.Environment.Board;
import es.deusto.ingenieria.aike.ParkingLotMaze.Environment.Car;
import es.deusto.ingenieria.aike.ParkingLotMaze.Environment.Cell;
import es.deusto.ingenieria.aike.ParkingLotMaze.Environment.Data;
import es.deusto.ingenieria.aike.ParkingLotMaze.Environment.Flag;
import es.deusto.ingenieria.aike.xml.InformationXMLReader;


public class MazeEnvironmentXMLReader extends InformationXMLReader{
	/**
	 * @uml.property  name="totalRow"
	 */
	private int totalRow;
	/**
	 * @uml.property  name="totalColumn"
	 */
	private int totalColumn;
	
	/**
	 * @uml.property  name="row"
	 */
	private int row;
	/**
	 * @uml.property  name="column"
	 */
	private int column;
	
	/**
	 * @uml.property  name="top"
	 */
	private boolean top;
	/**
	 * @uml.property  name="bottom"
	 */
	private boolean bottom;
	/**
	 * @uml.property  name="left"
	 */

	/**
	 * @uml.property  name="right"
	 */
	private boolean right;
	
	/**
	 * @uml.property  name="cells" multiplicity="(0 -1)" dimension="2"
	 */
	private Cell[][] cells;
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
	
	
	public MazeEnvironmentXMLReader(String initialState) {
		super(initialState);
		}

	public Object getInformation() {
		return new Board( cells, this.totalRow, this.totalColumn, car, flag,0);
	}	
	
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		try {
			if (qName.equals("aike:maze")) {
				this.totalRow = Integer.valueOf(attributes.getValue("rows")) + 1;
				this.totalColumn = Integer.valueOf(attributes.getValue("columns")) + 1 ;				
				cells = new Cell[totalRow][totalColumn];
				
				// i=0 or j=0 are not valid because the xml file starts at position (1,1)
				System.out.println("TotalRows: " + totalRow);
				System.out.println("TotalColumns " + totalColumn);
				System.out.println("Maze rows: " + (totalRow -1) );
				System.out.println("Maze columns: " + (totalColumn -1) );
				System.out.println();
				
				
				for(int i=1; i<totalRow; i++) 
					for(int j=1; j<totalColumn; j++) 
						cells [i][j]=new Cell (Data.TypeCell.CIRCLE, i, j);
			}	
			else if (qName.equals("aike:car")) {
				this.row = Integer.valueOf(attributes.getValue("row"));
				this.column = Integer.valueOf(attributes.getValue("column"));
				Data.Direction d = null;
				if (this.row == 0) d = Data.Direction.SOUTH;
				else if (this.row == totalRow) d = Data.Direction.NORTH;
				else if (this.column == 0 ) d = Data.Direction.EAST;
				else if (this.column == totalColumn) d = Data.Direction.WEST;
				
				//The starting position of the car will have a 0 in the row/column 
				//or the totalRow/totalColumn value in the row/column because
				// TotalRow or TotalColumn are the length of the array (from 0 to totalRow/TotalColumn-1 )
				car = new Car(new Cell(Data.TypeCell.CIRCLE, this.row, this.column), d);
				System.out.println(car.toString());
				
			} else if (qName.equals("aike:flag")) {
				this.row = Integer.valueOf(attributes.getValue("row"));
				this.column = Integer.valueOf(attributes.getValue("column"));
				
				this.top = attributes.getValue("top-wall").equalsIgnoreCase("yes");
				this.bottom = attributes.getValue("bottom-wall").equalsIgnoreCase("yes");
				this.right = attributes.getValue("right-wall").equalsIgnoreCase("yes");
				
				Cell c = new Cell(Data.TypeCell.CIRCLE, this.row, this.column);
				Data.Direction e = null;
				if (!this.top) e= Data.Direction.NORTH;
				else if (!this.bottom) e= Data.Direction.SOUTH;
				else if (!this.right) e= Data.Direction.EAST;
				else e= Data.Direction.WEST;
				
				
				flag = new Flag(c, e);
				System.out.println(flag.toString());
				
			} else if (qName.equals("aike:crosses"));
			  else if (qName.equals("aike:cross"))
			  {
				this.row = Integer.valueOf(attributes.getValue("row"));
				this.column = Integer.valueOf(attributes.getValue("column"));
				cells[row][column].setType(Data.TypeCell.CROSS);
			  }
				 
			}catch (Exception ex) {
			System.out.println(this.getClass().getName() + ".startElement(): " + ex);
		}
		
	}
	

}
