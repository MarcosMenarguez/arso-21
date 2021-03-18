package jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class ProgramaPoema {

	public static void main(String[] args) throws Exception {

		JAXBContext contexto = JAXBContext.newInstance(Poema.class);

		Poema poema = new Poema();
		poema.setFecha("Abril de 1915");
		poema.setLugar("Granada");
		poema.setTitulo("Alba");
		poema.getVerso().add("Mi corazón oprimido");
		poema.getVerso().add("siente junto a la alborada");
		poema.getVerso().add("el dolor de sus amores");
		poema.getVerso().add("y el sue?o de las distancias.");

		Marshaller marshaller = contexto.createMarshaller();

		marshaller.setProperty("jaxb.formatted.output", true);

		marshaller.marshal(poema, new File("xml/poema-jaxb.xml"));

		System.out.println("Fin.");

	}
}
