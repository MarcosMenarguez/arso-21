package bookle.servicio;

import javax.xml.ws.Endpoint;

public class Publicador {

	public static void main(String[] args) {
		
		Endpoint.publish(
				"http://localhost:9995/ws/bookle", 
				ServicioBookle.getInstancia());
		
		System.out.println("Servicio bookle funcionando");
	}
}
