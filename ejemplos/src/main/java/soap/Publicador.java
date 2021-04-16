package soap;

import javax.xml.ws.Endpoint;

public class Publicador {

	public static void main(String[] args) {
		
		Endpoint.publish(
				"http://localhost:9999/ws/saludo", 
				new SaludoImpl());
		
		System.out.println("Servicio saludo funcionando");
	}
}
