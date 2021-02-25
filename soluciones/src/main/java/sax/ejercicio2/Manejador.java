package sax.ejercicio2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Manejador extends DefaultHandler {

	private int diligencias;

	public int getDiligencias() {
		return diligencias;
	}

	@Override
	public void startDocument() throws SAXException {
		
		// El manejador podría utilizarse con varios documentos.
		// Con este método se resetea el contador.
		
		diligencias = 0;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (qName.equals("diligencia")) {
			
			String fechaString = attributes.getValue("fecha");
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");			
			Date fecha = null;;
			
			try {
				fecha = dateFormat.parse(fechaString);
			} catch (ParseException e) {
				throw new SAXException("Fecha incorrecta. El documento no ha sido validado");
			}
			
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -30);
			
			if (fecha.after(calendar.getTime())) {
				diligencias++;
			}
			
		}
			
	}

}
