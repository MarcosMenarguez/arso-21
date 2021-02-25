package sax.ejercicio1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Manejador extends DefaultHandler {

	private double sumaNotas;
	private int contadorNotas;
	
	// Atributos para llevar el estado del procesamiento
	
	private boolean dentroCalificacion;
	private boolean dentroNota;
	
	public int getContadorNotas() {
		return contadorNotas;
	}
	
	public double getMedia() {		
		
		if (contadorNotas > 0)		
			return sumaNotas / contadorNotas;
		else
			throw new IllegalStateException("El documento no tiene notas");
	}

	@Override
	public void startDocument() throws SAXException {
		
		sumaNotas = 0;
		contadorNotas = 0;
				
		dentroCalificacion = false;
		dentroNota = false;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		if (qName.equals("calificacion")) {
			
			dentroCalificacion = true;		
		}
		else if (qName.equals("nota") && dentroCalificacion) {
			
			dentroNota = true;
		}			
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		
		if (qName.equals("calificacion")) {
			
			dentroCalificacion = false;		
		}
		else if (qName.equals("nota") && dentroCalificacion) {
			
			dentroNota = false;
		}	
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		
		if (dentroNota) {
			
			String cadena = new String(ch, start, length);

			Double nota = null;

			try {
				nota = Double.parseDouble(cadena);
			} catch (NumberFormatException e) {
				throw new SAXException(e);
			}

			this.sumaNotas += nota;
			
			this.contadorNotas++;			
		}
	}
	
}
