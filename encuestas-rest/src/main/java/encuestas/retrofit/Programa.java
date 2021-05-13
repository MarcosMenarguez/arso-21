package encuestas.retrofit;

import encuestas.Encuesta;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class Programa {

	public static void main(String[] args) throws Exception {

		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://localhost:8080/api/")
				.addConverterFactory(JacksonConverterFactory.create())
				.build();

		EncuestasRestCliente servicio = retrofit.create(EncuestasRestCliente.class);
		
		Encuesta encuesta = servicio.getEncuesta("609c0ee29cc8b71b2850d36d").execute().body();
		
		System.out.println(encuesta.getIdentificador());
		
		System.out.println("fin.");
		
	}
}
