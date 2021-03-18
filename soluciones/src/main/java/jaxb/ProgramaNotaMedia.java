package jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import es.um.acta.Acta;
import es.um.acta.TipoCalificacion;

public class ProgramaNotaMedia {

	// Por legibilidad, se dejan escapar las excepciones
	
	public static void main(String[] args) throws Exception {
		
		final String documento = "xml/acta.xml";
		
		// 1. Construir el contexto JAXB
				
		JAXBContext contexto = JAXBContext.newInstance("es.um.acta");
		
		// 2. Obtener el ?rbol de contenido de un documento
		
		Unmarshaller unmarshaller = contexto.createUnmarshaller();
		
		Acta acta =  (Acta) unmarshaller.unmarshal(new File(documento));
		
		// 3. Calcular la nota media
		
		int contadorNotas = 0;
		double notaAcumulada = 0;
		
		for (TipoCalificacion calificacion : acta.getCalificacion()) {
			
			contadorNotas++;
			notaAcumulada += calificacion.getNota();
			
		}
		
		if (contadorNotas > 0)
			System.out.println("Nota media: " + (notaAcumulada/contadorNotas));
		else
			System.out.println("No hay notas");
		
		System.out.println("Fin.");
	}
}
