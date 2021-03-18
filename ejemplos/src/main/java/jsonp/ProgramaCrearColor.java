package jsonp;

import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.stream.JsonGenerator;

public class ProgramaCrearColor {

	public static void main(String[] args) throws Exception {
		
		/* Objetivo
		 
		 {
	      "color": "white",
	      "category": "value",
	      "code": {
	        "rgba": [0,0,0,1],
	        "hex": "#FFF"
	      }
		 }

		 */
		
		
		// Patrón builder
		
	    JsonObject color = Json.createObjectBuilder()
	             .add("color", "white")
	             .add("category", "value")
	             .add("code", 
	                     Json.createObjectBuilder()
	                        .add("rgba", 
					Json.createArrayBuilder(Arrays.asList(0, 0, 0, 1)).build())
	                        .add("hex", "#FFF")
	                        .build()
	            ).build();

	    System.out.println("Color: " + color.toString());
	    
	    // Almacenamiento en disco
	    
	    OutputStreamWriter writer = new FileWriter("json/color.json");
        JsonGenerator generador = Json.createGenerator(writer);
        
        generador.write(color);
        generador.close();

	    System.out.println("fin.");
		
	}
}
