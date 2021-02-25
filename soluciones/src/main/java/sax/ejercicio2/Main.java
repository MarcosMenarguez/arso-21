package sax.ejercicio2;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) throws ParserConfigurationException, SAXException {
		
		
		// Establecer el nombre del fichero

		final String documento = "xml/acta.xml";

		Manejador manejador = new Manejador();

		// 1. Obtener una factoría

		SAXParserFactory factoria = SAXParserFactory.newInstance();
		
		// 2. Pedir a la factoría la construcción del analizador

		// Dejamos escapar las excepciones de construcción (throws en el main)

		SAXParser analizador = factoria.newSAXParser();
		
		// 3. Analizar el documento

		try {
			analizador.parse(documento, manejador);

			System.out.println("Diligencias: " + manejador.getDiligencias());

		} catch (SAXException e) {

			System.out.println("El procesamiento se ha detenido por errores sintácticos");

		} catch (IOException e) {

			System.out.println("No se ha podido abrir el documento: " + documento);
		}

		System.out.println("Fin.");
	}
}
