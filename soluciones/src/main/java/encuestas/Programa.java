package encuestas;

import java.time.LocalDateTime;
import java.util.Collections;

import encuestas.ListadoEncuestas.EncuestaResumen;

public class Programa {

	public static void main(String[] args) throws Exception {

		
		ServicioEncuestas servicio = ServicioEncuestas.getInstancia();
		
		// Configura la encuesta
		
		Encuesta encuesta = new Encuesta();
		
		encuesta.setTitulo("Fecha del parcial");
		encuesta.setFechaApertura(LocalDateTime.now());
		encuesta.setFechaCierre(LocalDateTime.now().plusDays(1));
				
		Opcion opcion1 = new Opcion();
		opcion1.setTexto("Jueves");
		Opcion opcion2 = new Opcion();
		opcion2.setTexto("Viernes");
		
		Collections.addAll(encuesta.getOpciones(), opcion1, opcion2);
		
		// Alta de la encuesta
		
		String id = servicio.create(encuesta);
		
		// Voto
		
		servicio.votar(id, 1, "juan@um.es");
		
		System.out.println("¿Ha votado juan? " + servicio.haVotado(id, "juan@um.es"));
		System.out.println("¿Ha votado jose? " + servicio.haVotado(id, "jose@um.es"));
						
		for (EncuestaResumen resumen : servicio.getListadoResumen().getEncuestas()) {
			System.out.println(resumen);
			
			if (! resumen.getId().equals(id))
				servicio.remove(resumen.getId());
		}
		
		System.out.println("fin.");

	}
}
