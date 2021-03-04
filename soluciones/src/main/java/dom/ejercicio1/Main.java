package dom.ejercicio1;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Main {

	// Para no complicar el código del ejercicio, no tratamos las excepciones
	
	public static void main(String[] args) throws Exception {

		final String documento = "xml/acta.xml";
		
		// Construye un analizador DOM
		
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factoria.newDocumentBuilder();

		Document doc = builder.parse(documento);

		double acumulado = 0; 
		int notas = 0;
		
				
		NodeList nodos = doc.getElementsByTagName("nota");
		for (int i = 0; i < nodos.getLength(); i++) {
			
			Element nota = (Element) nodos.item(i);
			
			// Comprueba si es una calificación
			
			if (nota.getParentNode().getNodeName().equals("calificacion")) {
				acumulado += Double.parseDouble(nota.getTextContent());
				notas++;
			}
		}
		
		if (notas > 0)
			System.out.println("Nota media DOM: " + acumulado / notas);
		else
			System.out.println("El documento no contiene notas");

		System.out.println("Fin.");
	}

}
