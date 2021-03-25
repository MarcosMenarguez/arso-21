package bookle.servicio;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXParseException;

import es.um.bookle.Actividad;

public class Utils {

	public static final String ESQUEMA = "xml/bookle.xsd";

	public static List<SAXParseException> validar(Actividad actividad) {

		try {
			SchemaFactory factoriaSchema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

			// Construye el esquema
			Schema esquema = factoriaSchema.newSchema(new File(ESQUEMA));

			// Solicita al esquema la construcción de un validador
			Validator validador = esquema.newValidator();

			// Registra el manejador de eventos de error
			Validador miValidador = new Validador();
			validador.setErrorHandler(miValidador);

			// Solicita la validación de los objetos JAXB
			JAXBContext contexto = JAXBContext.newInstance("es.um.bookle");
			validador.validate(new JAXBSource(contexto, actividad));
			
			return miValidador.getErrores();
		} catch (Exception e) {

			return null; // No se ha realizado la validación 
		}

	}

	public static XMLGregorianCalendar createFecha(Date fecha) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);

		XMLGregorianCalendar fechaXML = null;

		try {
			fechaXML = DatatypeFactory.newInstance().newXMLGregorianCalendar();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		fechaXML.setDay(calendar.get(Calendar.DAY_OF_MONTH));
		fechaXML.setMonth(calendar.get(Calendar.MONTH) + 1);
		fechaXML.setYear(calendar.get(Calendar.YEAR));

		return fechaXML;
	}

	public static String formatoFecha(Calendar fecha) {

		DateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");

		return formateador.format(fecha.getTime());
	}

	public static Date dateFromString(String fechaString) {

		DateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");

		try {
			return formateador.parse(fechaString);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static String createId() {

		return UUID.randomUUID().toString();
	}
}
