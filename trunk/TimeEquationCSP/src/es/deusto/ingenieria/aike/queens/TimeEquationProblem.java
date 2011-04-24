package es.deusto.ingenieria.aike.queens;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import es.deusto.ingenieria.aike.csp.algorithm.CSPAlgorithm;
import es.deusto.ingenieria.aike.csp.formulation.CSPproblem;
import es.deusto.ingenieria.aike.csp.formulation.Variable;

//We have subclassed CSPproblem, binding its parameter Type to Integer
//This means that the values the variables of the Queens Problem will take are Integers
public class TimeEquationProblem extends CSPproblem<Integer> {
		
	public TimeEquationProblem(int queens) {
		this.createQueens(queens);
		this.createConstraints();
	}
	
	private void createQueens(int queens) {		
		for (int i=1; i<=queens; i++) {
			//The value domain is the same one for every queen variable			
			this.addVariable(new Queen(i, this.createDomain(queens)));
		}
	}
	
	private void createConstraints() {
		//There is a single constraint involving every queen variable. 
		Threat threat = new Threat(this.getVariables(), "Threat");
		//so this constraint must be associated to each queen variable
		for (Variable<Integer> queen : this.getVariables()) {
			queen.addConstraint(threat);			
		}
	}
	
	private List<Integer> createDomain(int queens) {
		//Every queen variable may have a row value from 1 to 8. 
		List<Integer> rowValues = new ArrayList<Integer>(queens);		
		
		for (int i=1; i<=queens; i++) {
			rowValues.add(i);
		}

		return rowValues;
	}
	
	public String toString() {
		String result = "   ";
		String row;
		
		for (int i=0; i<this.getVariables().size(); i++) {
			result += (i<9) ? "   " + (i+1) : "  " + (i+1);
		}
		
		for (int i=0; i<this.getVariables().size(); i++) {
			row = " ";
			
			for (int j=0; j<this.getVariables().size(); j++) {
				if (j==0) {
					row += (i<9) ? " " + (i+1) + " |" : (i+1) + " |";
				}
				
				if (this.getVariables().get(j).getValue() == i) {
					row += " Q |";
				} else {
					row += " - |";
				}
			}
			
			result += "\n";
			result += row;
		}
		
		return result;
	}
	
	public void solve(CSPAlgorithm<Integer> algorithm) {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.S");
		Date beginDate = GregorianCalendar.getInstance().getTime();
		System.out.println("\n* Begin '" + algorithm.getClass().getName() + "' (" + formatter.format(beginDate) + ")");		
		boolean solutionFound = algorithm.solve(this);
		Date endDate = GregorianCalendar.getInstance().getTime();		
		System.out.println("* End   '" + algorithm.getClass().getName() + "' (" + formatter.format(endDate) + ")");
		
		long miliseconds = (int) Math.abs(beginDate.getTime() - endDate.getTime());
		long seconds = miliseconds / 1000;
		miliseconds %= 1000;		
		long minutes = seconds / 60;
		seconds %= 60;
		long hours = minutes / 60;
		minutes %= 60;
		
		String time = "\n* Serach lasts: ";
		time += (hours > 0) ? hours + " h " : " ";
		time += (minutes > 0) ? minutes + " m " : " ";
		time += (seconds > 0) ? seconds + "s " : " ";
		time += (miliseconds > 0) ? miliseconds + "ms " : " ";
		
		System.out.println(time);
		
		if (solutionFound) {
			System.out.println("\n* Solution found!!");			
			System.out.println("\n" + this);
		} else {
			System.out.println("\n* Solution not found :-(");
		}
	}
	
}