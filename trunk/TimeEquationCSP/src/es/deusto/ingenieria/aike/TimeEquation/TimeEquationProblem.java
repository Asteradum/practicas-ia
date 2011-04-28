package es.deusto.ingenieria.aike.TimeEquation;

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
		
	//This constants are used to take the variable from the list
	private final int A = 0;
	private final int B = 1;
	private final int C = 2;
	private final int D = 3;
	private final int M = 4;
	private final int E = 5;
	private final int F = 6;
	private final int G = 7;
	private final int H = 8;
	private final int X1 = 9;
	private final int X2 = 10;
	private final int X3 = 11;
	
	private int multiplier;
	private int constant;
	private int maxMinutes;
	
	public TimeEquationProblem(List<Integer> list) {
		this.multiplier = list.get(0);
		this.constant = list.get(1);
		this.maxMinutes = list.get(2);
		this.createDigits();
		this.createConstraints();
	}
	
	private void createDigits() {	
		List<Integer> d = this.createDomain();
		Digit digit;
		
		char name='A';
		for (int i=0; name<='D';i++){
			this.addVariable(new Digit(String.valueOf(name), d));
			name++;
		}
		
		//The multiplier
		digit = new Digit("M", d);
		digit.setValue(multiplier);
		this.addVariable(digit);
		
		name='E';
		for (int i=0; name<='H';i++){
			if ( name=='H'){ //The constant
				digit = new Digit(String.valueOf(name),d);
				digit.setValue(constant);
				this.addVariable(digit);
			}	
			else this.addVariable(new Digit(String.valueOf(name), d));
			name++;
		}
		
		//The auxiliary
		this.addVariable(new Digit("X1", d));
		this.addVariable(new Digit("X2", d));
		this.addVariable(new Digit("X3", d));
	}
	
	private void createConstraints() {
		
		
		//Comprobar si poseen valores nulos en las constraints?
		
		//LowerThan for C and G
		UnaryConstraint unary = new UnaryConstraint(this.getVariables().subList(C, D), "LowerThan");
		this.getVariables().get(C).addConstraint(unary);
		
		unary = new UnaryConstraint(this.getVariables().subList(G, H), "LowerThan");
		this.getVariables().get(G).addConstraint(unary);
		
		//EqualTo Constant and Multiplier
		unary = new UnaryConstraint(this.getVariables().subList(M, E), "EqualTo");
		unary.setValue(multiplier);
		this.getVariables().get(M).addConstraint(unary);
		
		unary = new UnaryConstraint(this.getVariables().subList(H, X1), "EqualTo");
		unary.setValue(constant);
		this.getVariables().get(H).addConstraint(unary);
		
		//Distinct from constant and multiplier
		List<Variable<Integer>> sub = new ArrayList<Variable<Integer>>(this.getVariables().subList(A, H));
		sub.remove(M);
		unary = new UnaryConstraint(sub, "DistinctFrom");
		unary.setValue(multiplier);
		
		for (Variable<Integer> digit : sub ) 
			digit.addConstraint(unary);
		
		//If constant == multiplier create this constraint is unnecessary
		if (multiplier != constant){
			unary = new UnaryConstraint(sub, "DistinctFrom");
			unary.setValue(constant);
			
			for (Variable<Integer> digit : sub ) 
				digit.addConstraint(unary);		
		}
		
		//MaxMinutes
		MaxMinutes max = new MaxMinutes(this.getVariables().subList(A, C), "MaxMinutes");
		max.setMaxMinutes(maxMinutes);
		this.getVariables().get(A).addConstraint(max);
		this.getVariables().get(B).addConstraint(max);
		
		max = new MaxMinutes(this.getVariables().subList(E, G), "MaxMinutes");
		this.getVariables().get(E).addConstraint(max);
		this.getVariables().get(F).addConstraint(max);
		
		//Adds
		sub.clear();
		sub.add(this.getVariables().get(D));
		sub.add(this.getVariables().get(X1));
		Add add = new Add(sub, "Add");
		add.setConstant(constant);
		add.setMultiplier(multiplier);
		this.getVariables().get(D).addConstraint(add);
		this.getVariables().get(X1).addConstraint(add);
		
		sub.clear();
		sub.add(this.getVariables().get(A));
		sub.add(this.getVariables().get(E));
		sub.add(this.getVariables().get(X3));
		add = new Add(sub, "Add");
		this.getVariables().get(A).addConstraint(add);
		this.getVariables().get(E).addConstraint(add);
		this.getVariables().get(X3).addConstraint(add);
		
		sub.clear();
		sub.add(this.getVariables().get(B));
		sub.add(this.getVariables().get(F));
		sub.add(this.getVariables().get(X2));
		sub.add(this.getVariables().get(X3));
		add = new Add(sub, "Add");
		this.getVariables().get(B).addConstraint(add);
		this.getVariables().get(F).addConstraint(add);
		this.getVariables().get(X2).addConstraint(add);
		this.getVariables().get(X3).addConstraint(add);
		
		sub.clear();
		sub.add(this.getVariables().get(C));
		sub.add(this.getVariables().get(G));
		sub.add(this.getVariables().get(X1));
		sub.add(this.getVariables().get(X2));
		add = new Add(sub, "Add");
		this.getVariables().get(C).addConstraint(add);
		this.getVariables().get(G).addConstraint(add);
		this.getVariables().get(X1).addConstraint(add);
		this.getVariables().get(X2).addConstraint(add);	
	
	}
	
	
	private List<Integer> createDomain() {
		//A digit is between 0 and 9
		List<Integer> domain = new ArrayList<Integer>(10);		
		
		for (int i=0; i<=9; i++) {
			domain.add(i);
		}

		return domain;
	}
	
	public String toString() {
		String result = "   ";
		result="A B : C D x Multiplier = E F : G Constant";
		result += "\n";
		result+="    :     x     "+ multiplier +"      =     :      "+constant;
		result += "\n";
		result += "Final Values: ";
		result += "\n";
		result+=this.getVariables().get(0).getValue()+" "+this.getVariables().get(1).getValue()+" : "+this.getVariables().get(2).getValue()+" "+this.getVariables().get(3).getValue()+" x    " +
				" "+ multiplier +"      = "+this.getVariables().get(4).getValue()+" "+this.getVariables().get(5).getValue()+" : "+this.getVariables().get(6).getValue()+"    "+constant;
		return result;
	}
	
	public void solve(CSPAlgorithm<Integer> algorithm) {
		//Insertar el NodeConsistency
		
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
		
		String time = "\n* Search lasts: ";
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