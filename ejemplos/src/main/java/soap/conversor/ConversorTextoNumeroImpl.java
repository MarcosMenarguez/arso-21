package soap.conversor;

import javax.jws.WebService;

@WebService(endpointInterface = "soap.conversor.ConversorTextoNumero", 
	targetNamespace = "http://um.es/arso/conversor")
public class ConversorTextoNumeroImpl implements ConversorTextoNumero {

	@Override
	public int parse(String texto) throws ConversionException {
		
		try {
			return Integer.parseInt(texto);
		}
		catch(Exception e) {
			
			throw new ConversionException(e.getMessage());
		}
		

	}

}
