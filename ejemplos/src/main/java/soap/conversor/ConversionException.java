package soap.conversor;

import javax.xml.ws.WebFault;

@WebFault
public class ConversionException extends Exception {

	private String info;
	
	public ConversionException(String msg) {
		super(msg);
		this.info = msg;
		
	}	
	
	public String getFaultInfo() {
		
		return info;
	}
}
