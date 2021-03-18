package jaxb;

import java.io.File;
import java.util.Calendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;

import es.um.acta.Acta;
import es.um.acta.TipoDiligencia;


public class ProgramaDiligencias {

	// Por legibilidad, se dejan escapar las excepciones
		
	public static void main(String[] args) throws Exception {
		
		final String documento = "xml/acta.xml";
		
		// 1. Construir el contexto JAXB
		
		JAXBContext contexto = JAXBContext.newInstance("es.um.acta");
		
		// 2. Obtener el árbol de contenido de un documento
		
		Unmarshaller unmarshaller = contexto.createUnmarshaller();
		
		Acta acta =  (Acta) unmarshaller.unmarshal(new File(documento));
		
		// 3. Contar el  número de diligencias en los últimos 60 días
		
		int contador = 0;
		
		for (TipoDiligencia diligencia : acta.getDiligencia()) {
			
			XMLGregorianCalendar fechaXML = diligencia.getFecha();
			
			Calendar haceUnMes = Calendar.getInstance();
			haceUnMes.add(Calendar.DAY_OF_MONTH, -60);
			
			if (fechaXML.toGregorianCalendar().after(haceUnMes))
				contador++;
			
		}
		
		System.out.println("Número de diligencias: " + contador);
		
		System.out.println("Fin.");
	}
}
