package jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import es.um.acta.Acta;

public class Programa1 {

	public static void main(String[] args) throws Exception {
		
		JAXBContext contexto = JAXBContext.newInstance("es.um.acta");
		
		Unmarshaller unmarshaller = contexto.createUnmarshaller();
		
		Acta acta = (Acta) unmarshaller.unmarshal(new File("xml/acta.xml"));
		
		System.out.println(acta.getAsignatura());
		
		acta.setConvocatoria("junio");
		
		Marshaller marshaller = contexto.createMarshaller();
		marshaller.setProperty("jaxb.formatted.output", true);
		
		marshaller.marshal(acta, new File("xml/acta-jaxb.xml"));
		
		
		System.out.println("fin.");
		
	}
}
