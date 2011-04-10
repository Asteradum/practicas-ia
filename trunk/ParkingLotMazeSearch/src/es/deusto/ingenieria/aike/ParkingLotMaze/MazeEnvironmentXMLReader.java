package es.deusto.ingenieria.aike.ParkingLotMaze;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import es.deusto.ingenieria.aike.ParkingLotMaze.Cell.TypeCell;
import es.deusto.ingenieria.aike.ParkingLotMaze.Flag.Direction;
import es.deusto.ingenieria.aike.xml.InformationXMLReader;


public class MazeEnvironmentXMLReader extends InformationXMLReader{
	private int totalRow;
	private int totalColumn;
	
	private int row;
	private int column;
	
	private boolean top;
	private boolean bottom;
	private boolean left;
	private boolean right;
	
	private Cell[][] cells;
	private Car car;
	private Flag flag;
	
	
	public MazeEnvironmentXMLReader(String initialState) {
		super(initialState);
		}

	public Object getInformation() {
		return new Board( cells, this.totalRow, this.totalColumn, car, flag);
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
						cells [i][j]=new Cell (TypeCell.CIRCLE, i, j);
			}	
			else if (qName.equals("aike:car")) {
				this.row = Integer.valueOf(attributes.getValue("row"));
				this.column = Integer.valueOf(attributes.getValue("column"));
				Direction d = null;
				if (this.row == 0) d = Direction.SOUTH;
				else if (this.row == totalRow) d = Direction.NORTH;
				else if (this.column == 0 ) d = Direction.EAST;
				else if (this.column == totalColumn) d = Direction.WEST;
				
				//The starting position of the car will have a 0 in the row/column 
				//or the totalRow/totalColumn value in the row/column because
				// TotalRow or TotalColumn are the length of the array (from 0 to totalRow/TotalColumn-1 )
				car = new Car(new Cell(TypeCell.CIRCLE, this.row, this.column), d);
				System.out.println(car.toString());
				
			} else if (qName.equals("aike:flag")) {
				this.row = Integer.valueOf(attributes.getValue("row"));
				this.column = Integer.valueOf(attributes.getValue("column"));
				
				this.top = attributes.getValue("top-wall").equalsIgnoreCase("yes");
				this.bottom = attributes.getValue("bottom-wall").equalsIgnoreCase("yes");
				this.left = attributes.getValue("left-wall").equalsIgnoreCase("yes");
				this.right = attributes.getValue("right-wall").equalsIgnoreCase("yes");
				
				Cell c = new Cell(TypeCell.CIRCLE, this.row, this.column);
				Direction e = null;
				if (this.top) e= Direction.NORTH;
				else if (this.bottom) e= Direction.SOUTH;
				else if (this.right) e= Direction.EAST;
				else e= Direction.WEST;
				
				flag = new Flag(c, e);
				System.out.println(flag.toString());
				
			} else if (qName.equals("aike:crosses"));
			  else if (qName.equals("aike:cross"))
			  {
				this.row = Integer.valueOf(attributes.getValue("row"));
				this.column = Integer.valueOf(attributes.getValue("column"));
				cells[row][column].setType(TypeCell.CROSS);
			  }
				 
			}catch (Exception ex) {
			System.out.println(this.getClass().getName() + ".startElement(): " + ex);
		}
		
	}
	

}
