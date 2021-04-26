package bookle.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class Utils {
	
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
		} 
		catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String createId() {
		
		return UUID.randomUUID().toString();
	}
}
