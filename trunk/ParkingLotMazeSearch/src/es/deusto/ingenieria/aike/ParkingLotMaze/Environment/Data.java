package es.deusto.ingenieria.aike.ParkingLotMaze.Environment;

public final class Data {

	/**
	 * @author   Aster
	 */
	public static enum Direction {
		/**
		 * @uml.property  name="nORTH"
		 * @uml.associationEnd  
		 */
		NORTH,
		/**
		 * @uml.property  name="sOUTH"
		 * @uml.associationEnd  
		 */
		SOUTH,
		/**
		 * @uml.property  name="wEST"
		 * @uml.associationEnd  
		 */
		WEST,
		/**
		 * @uml.property  name="eAST"
		 * @uml.associationEnd  
		 */
		EAST
	}

	/**
	 * @author   Aster
	 */
	public static enum TypeCell {
		/**
		 * @uml.property  name="cROSS"
		 * @uml.associationEnd  
		 */
		CROSS,
		/**
		 * @uml.property  name="cIRCLE"
		 * @uml.associationEnd  
		 */
		CIRCLE
	}

}
