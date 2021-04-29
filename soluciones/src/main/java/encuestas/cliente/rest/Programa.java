package encuestas.cliente.rest;

import java.io.FileInputStream;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class Programa {

	private static final String URL_BASE = "http://localhost:8080/api/encuestas";
	
	public static void main(String[] args) throws Exception {

		// Listado

		final String urlListado = URL_BASE;

		JsonReader readerListado = Json.createReader(new URL(urlListado).openStream());

		JsonArray listado = readerListado.readObject().getJsonArray("encuestas");

		for (JsonObject encuestaResumen : listado.getValuesAs(JsonObject.class)) {
			
			final String urlEncuesta = URL_BASE + "/" + encuestaResumen.getString("id");
			
			JsonReader readerEncuesta = Json.createReader(new URL(urlEncuesta).openStream());
			
			JsonObject encuesta = readerEncuesta.readObject();
			
			System.out.println("Titulo: " + encuesta.getString("titulo"));
			System.out.println("Numero de opciones: " + encuesta.getInt("numeroOpciones"));
		}

		System.out.println("fin.");
		
	}
}
