package es.deusto.ingenieria.aike.queens;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import es.deusto.ingenieria.aike.xml.InformationXMLReader;


public class TimeEquationXMLReader extends InformationXMLReader{
	
	private int multiplier;
	private int constant;
	private int maxMinutes;
	
	public TimeEquationXMLReader(String initialState) {
		super(initialState);
	}

	@Override
	public Object getInformation() {
		return new Environment(multiplier, constant, maxMinutes);
	}	
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		try {
			if (qName.equals("aike:equation")) {
				this.multiplier = Integer.valueOf(attributes.getValue("multiplier"));
				this.constant = Integer.valueOf(attributes.getValue("constant"));
				this.maxMinutes = Integer.valueOf(attributes.getValue("maxMinutes"));	
				
				
				
				System.out.println("Mutiplier: " + this.multiplier + ", constant: " + this.constant + ", maxMinutes: " + this.maxMinutes);
			}
				 
		}catch (Exception ex) {
			System.out.println(this.getClass().getName() + ".startElement(): " + ex);
		}
		
	}
	

}
