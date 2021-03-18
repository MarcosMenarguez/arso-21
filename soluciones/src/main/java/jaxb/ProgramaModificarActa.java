package jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import es.um.acta.Acta;
import es.um.acta.TipoCalificacion;
import es.um.acta.TipoDiligencia;


public class ProgramaModificarActa {

	// Por legibilidad, se dejan escapar las excepciones
		
	public static void main(String[] args) throws Exception {
		
		final String documento = "xml/acta.xml";
		final String salida = "xml/acta-modificado-jaxb.xml";
				
		// 1. Construir el contexto JAXB
		
		JAXBContext contexto = JAXBContext.newInstance("es.um.acta");
		
		// 2. Obtener el árbol de contenido de un documento
		
		Unmarshaller unmarshaller = contexto.createUnmarshaller();
		
		Acta acta =  (Acta) unmarshaller.unmarshal(new File(documento));
		
		// 3.1 Incrementa la nota de las calificaciones
		
		for (TipoCalificacion calificacion : acta.getCalificacion()) {
			
			double nota = Math.min(10, calificacion.getNota() + 0.5);
			calificacion.setNota(nota);			
		}
		
		// 3.2 Incrementa la nota de las diligencias

		for (TipoDiligencia diligencia : acta.getDiligencia()) {

			double nota = Math.min(10, diligencia.getNota() + 0.5);
			diligencia.setNota(nota);
		}
		
		// 4. Empaquetado en un documento XML (marshalling)

		Marshaller marshaller = contexto.createMarshaller();

		marshaller.setProperty("jaxb.formatted.output", true);

		// Opcional enlazar con esquema
		
		marshaller.setProperty("jaxb.schemaLocation",
				"http://www.um.es/acta acta.xsd");

		marshaller.marshal(acta, new File(salida));
		
		System.out.println("Fin.");
	}
}
