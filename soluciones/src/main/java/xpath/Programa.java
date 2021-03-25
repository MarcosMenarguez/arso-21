package xpath;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Programa {

	public static void main(String[] args) throws Exception {

		final String documento = "xml/actividad.xml";

		// Construye un analizador DOM
		
		DocumentBuilderFactory factoriaDOM = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factoriaDOM.newDocumentBuilder();

		Document doc = builder.parse(documento);
		
		// 1. Obtener la factoria

		XPathFactory factoria = XPathFactory.newInstance();

		// 2. Construir el evaluador XPath

		XPath xpath = factoria.newXPath();

		// 3. Realizar una consulta

		// consulta 1: /actividad/agenda/fecha
		// 2: /actividad/agenda[fecha = '2021-04-13']/turno/horario
		// 3: /actividad/agenda/turno[reserva]/horario
		// 4: /actividad/agenda/turno/reserva/email
		// 5: /actividad/agenda/fecha[starts-with(.,'2021-04')]
		// 6: /actividad/agenda/turno[1]
		// 7: /actividad/agenda/turno[last()]
		

		XPathExpression consulta = xpath.compile("/actividad/agenda/turno[last()]");

		// Importante: hay que ajustar la evaluacion y el tipo de retorno segun
		// el dato que se espere

		NodeList resultado = (NodeList) consulta.evaluate(doc, XPathConstants.NODESET);
		for (int i = 0; i < resultado.getLength(); i++) {

			Node nodo = resultado.item(i);

			// Se muestra informacion general sobre el resultado

			System.out.println("Nombre: " + nodo.getNodeName());

			// getTextContent muestra todos los nodos textuales descendientes

			System.out.println("Contenido: " + nodo.getTextContent());

		}

		System.out.println("Fin.");

	}
}
