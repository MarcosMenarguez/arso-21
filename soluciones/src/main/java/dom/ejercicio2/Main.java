package dom.ejercicio2;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Main {

	// Para no complicar el código del ejercicio, no tratamos las excepciones
	
	public static void main(String[] args) throws Exception {

		final String documento = "xml/acta.xml";
		final String salida = "xml/acta-modificado.xml";
		
		// Construye un analizador DOM
		
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factoria.newDocumentBuilder();

		Document doc = builder.parse(documento);
		
		// Actualizamos las notas
		
		NodeList nodos = doc.getElementsByTagName("nota");
		
		for (int i = 0; i < nodos.getLength(); i++) {
			
			Element nota = (Element) nodos.item(i);
			
			// Actualizamos la nota
			
			double valor = Double.parseDouble(nota.getTextContent());
			valor = Math.min(10, valor + 0.5);
			
			nota.setTextContent(Double.toString(valor));			
		}

		
		// Almacenamos el árbol DOM en un nuevo documento
		
		TransformerFactory tFactoria = TransformerFactory.newInstance();
		Transformer transformacion = tFactoria.newTransformer();
		
		Source input = new DOMSource(doc);
		Result output = new StreamResult(salida);
		transformacion.transform(input, output);

		System.out.println("Fin.");
	}
}
