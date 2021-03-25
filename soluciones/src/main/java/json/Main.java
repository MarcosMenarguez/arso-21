package json;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;

public class Main {

	public static void main(String[] args) throws Exception {

		JsonReader jsonReader = Json.createReader(new FileInputStream("json/acta.json"));
		JsonObject acta = jsonReader.readObject();
				
		// 1. Nota media
		
		JsonArray calificaciones = acta.getJsonArray("calificaciones");
		
		double notaAcumulada = 0;
		for (JsonObject calificacion : calificaciones.getValuesAs(JsonObject.class)) { 
			
			notaAcumulada += calificacion.getJsonNumber("nota").doubleValue();
		}

		System.out.println("Nota media: " + (notaAcumulada/calificaciones.size()));
		
		// 2. Contar diligencias
		
		JsonArray diligencias = acta.getJsonArray("diligencias");
		
		int numeroDilicencias = 0;
		for (JsonObject diligencia : diligencias.getValuesAs(JsonObject.class)) { 
			
			String fechaString = diligencia.getString("fecha");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");			
			Date fecha = null;;
			
			try {
				fecha = dateFormat.parse(fechaString);
			} catch (ParseException e) {
				throw new IllegalStateException("Fecha incorrecta. El documento no ha sido validado");
			}
			
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -30);
			
			if (fecha.after(calendar.getTime())) {
				numeroDilicencias++;
			}
		}

		System.out.println("Numero de diligencias: " + numeroDilicencias);
		
		// Crear acta
		
		JsonObject nuevaActa = Json.createObjectBuilder()
				.add("convocatoria", "febrero")
				.add("curso", 2020)
				.add("asignatura", 1902)
				.add("calificaciones", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
							.add("nif", "23322156M")
							.add("nota", 10))
						.add(Json.createObjectBuilder()
								.add("nif", "13322156M")
								.add("nombre", "Pepe")
								.add("nota", 8)))							
				.add("diligencias", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
							.add("nif", "13322156M")
							.add("nota", 9)
							.add("fecha", "2021-02-12")))
				.build();
		
		
		// Opción 1: el documento queda en una sola línea
		
        // JsonGenerator generador = Json.createGenerator(new FileWriter("json/acta-json-p.json"));
        
		// Opción 2: configuración del formato de la salida
		
        HashMap<String, Boolean> config = new HashMap<String, Boolean>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);

        JsonGeneratorFactory factoriaGeneradores = Json.createGeneratorFactory(config);
		
        JsonGenerator generador = factoriaGeneradores.createGenerator(
        		new FileWriter("json/acta-json-p.json"));
        
        generador.write(nuevaActa);
        generador.close();
		
		System.out.println("fin.");

	}
}
