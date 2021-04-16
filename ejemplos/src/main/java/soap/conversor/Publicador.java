package soap.conversor;

import javax.xml.ws.Endpoint;

public class Publicador {

	public static void main(String[] args) {
		
		Endpoint.publish(
				"http://localhost:9998/ws/conversor", 
				new ConversorTextoNumeroImpl());
		
		System.out.println("Servicio conversor funcionando");
	}
}
