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
		
	private List<Integer> xmlData;
	
	public TimeEquationProblem(List<Integer> list) {
		this.xmlData = list;
		this.createDigits();
		this.createConstraints();
	}
	
	private void createDigits() {	
		List<Integer> d = this.createDomain();
		
		//Crear una enumeracion
		/* 0 = A
		 * 1 = B
		 * 2 = C
		 * 3 = D
		 * 4 = M
		 * 5 = E
		 * 6 = F
		 * 7 = G
		 * 8 = H (Constant)
		 * 9 = X1
		 * 10 = x2
		 * 11 = X3
		 */
		
		for (char c='A'; c<='G'; c++)
			this.addVariable(new Digit(c, d));
	}
	
	private void createConstraints() {
		//LowerThan lower = new LowerThan(); 
		//lower = new LowerThan();
		
		
		// Dos LowerThan
		// 2 EqualTo
		// 14 distinct from (7 para la constante y 7 para el multiplier); comprobar si m y c son iguales, meterle el valor con un set
		// Sustituir estas 3 clases por un UnaryConstraint que dependiendo de lo que se le pase, haga un menor un mayor o un igual
		
		// Posiblemente en el environment haya que cambair los int por digit para que cumplan las constraint especificadas en la docu
		// 2 MaxMinutes, meterle el maxMinutes con un set
		// 2 Suma1
		// 2 Suma2
		
		
		ConstraintsTimeEquation cons1 = new ConstraintsTimeEquation(this.getVariables(), "unary");
		ConstraintsTimeEquation cons2 = new ConstraintsTimeEquation(this.getVariables(), "binary");
		ConstraintsTimeEquation cons3 = new ConstraintsTimeEquation(this.getVariables(), "global");
		Variable <Integer>a=this.getVariables().get(0);
		Variable <Integer>b=this.getVariables().get(1);
		Variable <Integer>c=this.getVariables().get(2);
		Variable <Integer>d=this.getVariables().get(3);
		Variable <Integer>e=this.getVariables().get(4);
		Variable <Integer>f=this.getVariables().get(5);
		Variable <Integer>g=this.getVariables().get(6);
		a.addConstraint(cons2);
		a.addConstraint(cons3);
		//----
		d.addConstraint(cons1);
							
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
		result+="    :     x     "+this.xmlData.get(0)+"      =     :      "+this.xmlData.get(1);
		result += "\n";
		result += "Final Values: ";
		result += "\n";
		result+=this.getVariables().get(0).getValue()+" "+this.getVariables().get(1).getValue()+" : "+this.getVariables().get(2).getValue()+" "+this.getVariables().get(3).getValue()+" x    " +
				" "+this.xmlData.get(0)+"      = "+this.getVariables().get(4).getValue()+" "+this.getVariables().get(5).getValue()+" : "+this.getVariables().get(6).getValue()+"    "+this.xmlData.get(1);
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