package soap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(targetNamespace = "http://um.es/arso")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Saludo {
	@WebMethod
	String getSaludo(String nombre);
}