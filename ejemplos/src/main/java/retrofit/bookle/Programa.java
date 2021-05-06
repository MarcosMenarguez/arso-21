package retrofit.bookle;

import es.um.bookle.Actividad;
import retrofit.bookle.Listado.ResumenExtendido;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jaxb.JaxbConverterFactory;

public class Programa {

	public static void main(String[] args) throws Exception {

		Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:8080/api/")
				.addConverterFactory(JaxbConverterFactory.create()).build();

		BookleRestClient service = retrofit.create(BookleRestClient.class);

		// Creación

		// Recupera la actividad "1" como punto de partida

		Actividad actividadBase = service.getActividad("1").execute().body();

		// Crea una nueva actividad con la misma información

		Response<Void> resultado = service.createActividad(actividadBase).execute();

		String url1 = resultado.headers().get("Location");

		String id1 = url1.substring(url1.lastIndexOf("/") + 1);

		System.out.println("Actividad creada: " + url1);
		System.out.println("Id: " + id1);


		// Recuperación

		Actividad actividad = service.getActividad(id1).execute().body();

		System.out.println("Actividad: " + actividad.getTitulo() + " - " + actividad.getDescripcion());

		// Actualización

		actividad.setDescripcion("descripción actualizada");

		service.updateActividad(id1, actividad).execute();

		System.out.println("Actividad actualizada");

		
		// Reserva

		Response<Void> respuesta = service.reservar(id1, "2021-04-12", 2, "Antonio", "antonio@um.es").execute();

		System.out.println("Reserva realizada sobre actividad: " + id1);
		System.out.println("Código de respuesta: " + respuesta.message());

		
		// Listado

		Listado listado = service.getListado().execute().body();

		System.out.println("Listado:");
		for (ResumenExtendido actividadResumen : listado.getActividad()) {
			System.out.println("\t" + actividadResumen.getResumen().getTitulo());
			System.out.println("\t" + actividadResumen.getUrl());
		}

		System.out.println("fin.");

	}
}
