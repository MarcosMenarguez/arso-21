package ip.cliente;

import com.cdyne.ws.IP2Geo;
import com.cdyne.ws.IP2GeoSoap;
import com.cdyne.ws.IPInformation;

public class Programa {

	public static void main(String[] args) {
		
		IP2Geo servicio = new IP2Geo();
		
		IP2GeoSoap puerto = servicio.getIP2GeoSoap();
		
		IPInformation resultado = puerto.resolveIP("155.54.1.1", "0");
		
		System.out.println(resultado.getCity());
		
		System.out.println("fin.");
		
	}
}
