package saludo.cliente;

import es.um.arso.Saludo;
import es.um.arso.SaludoImplService;

public class Programa {

	public static void main(String[] args) {
		
		SaludoImplService servicio = new SaludoImplService();
		
		Saludo puerto = servicio.getSaludoImplPort();
		
		String respuesta = puerto.getSaludo("Pepe");
		
		System.out.println(respuesta);
		
		System.out.println("fin.");
	}
}
