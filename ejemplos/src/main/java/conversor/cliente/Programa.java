package conversor.cliente;

import es.um.arso.conversor.ConversorTextoNumeroImplService;
import es.um.arso.conversor.ConversionException;
import es.um.arso.conversor.ConversorTextoNumero;


public class Programa {

	public static void main(String[] args) {
		
		ConversorTextoNumeroImplService servicio = new ConversorTextoNumeroImplService();
		
		ConversorTextoNumero puerto = servicio.getConversorTextoNumeroImplPort();
		
		int resultado;
		try {
			resultado = puerto.parse("abd");
			System.out.println(resultado);
		} catch (ConversionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		System.out.println("fin.");
	}
}
