package jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class Programa2 {

	public static void main(String[] args) throws Exception {
		// Construir el contexto JAXB para las clases anotadas
		
		JAXBContext contexto = 
			JAXBContext.newInstance(Persona.class);
				
		Persona persona = new Persona();
		persona.setNombre("Juan");
		persona.setApellidos("González");
				
		// Empaquetado en un documento XML (marshalling)
				
		Marshaller marshaller = contexto.createMarshaller();
						
		marshaller.setProperty("jaxb.formatted.output", true);
				
		marshaller.marshal(persona, new File("xml/persona2.xml"));
		
		System.out.println("fin.");
	}
}
