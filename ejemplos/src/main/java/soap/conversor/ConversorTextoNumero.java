package soap.conversor;

import javax.jws.WebService;

@WebService(targetNamespace = "http://um.es/arso/conversor")
public interface ConversorTextoNumero {

	int parse(String texto) throws ConversionException;
}
