package es.deusto.ingenieria.aike.queens;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import es.deusto.ingenieria.aike.csp.algorithm.CSPAlgorithm;
import es.deusto.ingenieria.aike.csp.formulation.CSPproblem;
import es.deusto.ingenieria.aike.csp.formulation.Variable;

public class TimeEquationProblem extends CSPproblem<Integer> {
	
	Environment envi;
	
	public TimeEquationProblem(Environment e) {
		this.createDigits();
		this.createConstraints();
		envi=e;
		
			
	}
	
	private void createDigits() {
		this.addVariable(new Digit('A', this.createDomain()));
		this.addVariable(new Digit('B', this.createDomain()));
		this.addVariable(new Digit('C', this.createDomain()));
		this.addVariable(new Digit('D', this.createDomain()));
		this.addVariable(new Digit('E', this.createDomain()));
		this.addVariable(new Digit('F', this.createDomain()));
		this.addVariable(new Digit('G', this.createDomain()));
		
	}
	
	private void createConstraints() {
		Constraints cons1 = new Constraints(this.getVariables(), "unary");
		Constraints cons2 = new Constraints(this.getVariables(), "binary");
		Constraints cons3 = new Constraints(this.getVariables(), "global");
		Variable <Integer>d=envi.getDigits()[4];
		d.addConstraint(cons2);
		//envi.getDigits()[4].addConstraint(cons2);
		/*for (Variable<Integer> queen : this.getVariables()) 
			queen.addConstraint(threat);*/			
		
	}
	
	private List<Integer> createDomain() {
		List<Integer> numberValues = new ArrayList<Integer>();		
		for (int i=0; i<=9; i++) 
			numberValues.add(i);
		return numberValues;
	}
	
	@Override
	public String toString() {
		String result = "   ";
		result="A B : C D x Multiplier = E F : G Constant";
		result += "\n";
		result+="    :     x     "+envi.getMultiplier()+"      =     :      "+envi.getConstant();
		result += "\n";
		result += "Final Values: ";
		result += "\n";
		//result+=envi.getDigits()[1]+" "+ envi.getDigits()[2]+" : "+envi.getDigits()[3]+" "+envi.getDigits()[4]+" x "+envi.getMultiplier()+" = "+envi.getDigits()[5]+" "+envi.getDigits()[6]+" :"+envi.getDigits()[7]+" "+envi.getConstant();
		return result;
	}
	
	public void solve(CSPAlgorithm<Integer> algorithm) {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.S");
		Date beginDate = Calendar.getInstance().getTime();
		System.out.println("\n* Begin '" + algorithm.getClass().getName() + "' (" + formatter.format(beginDate) + ")");		
		boolean solutionFound = algorithm.solve(this);
		Date endDate = Calendar.getInstance().getTime();		
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