package jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import es.um.acta.Acta;
import es.um.acta.TipoCalificacion;
import es.um.acta.TipoDiligencia;

public class ProgramaCrearActa {

	// Por legibilidad, se dejan escapar las excepciones
	
	public static void main(String[] args) throws Exception {
		
		final String documento = "xml/acta-jaxb.xml";
		
		// 1. Construir el contexto JAXB
		
		JAXBContext contexto = JAXBContext.newInstance("es.um.acta");
		
		// 2. Construye el documento
		
		// Raíz del documento
		
		Acta calificaciones = new Acta();
		calificaciones.setConvocatoria("febrero");
		
		XMLGregorianCalendar curso = 
				DatatypeFactory.newInstance().newXMLGregorianCalendar();	
		
		curso.setYear(2017);	
		calificaciones.setCurso(curso);	
		
		calificaciones.setAsignatura("1092");
		
		// Primera calificación
		
		TipoCalificacion calificacion1 = new TipoCalificacion();
		calificacion1.setNif("23322156M");
		calificacion1.setNota(10);
		
		// Segunda calificación
		
		TipoCalificacion calificacion2 = new TipoCalificacion();
		calificacion2.setNif("13322156M");
		calificacion2.setNombre("Pepe");
		calificacion2.setNota(8);
		
		// Se a?aden al acta
		
		calificaciones.getCalificacion().add(calificacion1);
		calificaciones.getCalificacion().add(calificacion2);
		
		// Diligencia
		
		TipoDiligencia diligencia = new TipoDiligencia();
		diligencia.setNif("13322156M");
		diligencia.setNota(9);
		
		XMLGregorianCalendar fechaDiligencia = 
				DatatypeFactory.newInstance().newXMLGregorianCalendar();		
		
		fechaDiligencia.setYear(2021);
		fechaDiligencia.setMonth(2);
		fechaDiligencia.setDay(12);		
		
		diligencia.setFecha(fechaDiligencia);
		
		// Se añade al acta
		
		calificaciones.getDiligencia().add(diligencia);
		
		// Almacenamos el documento
		
		Marshaller marshaller = contexto.createMarshaller();
		
		marshaller.setProperty("jaxb.formatted.output", true);

		// Opcional enlazar con esquema
		
		marshaller.setProperty("jaxb.schemaLocation",
				"http://www.um.es/acta acta.xsd");
		
		marshaller.marshal(calificaciones, new File(documento));
		
		System.out.println("Fin.");
	}
}
