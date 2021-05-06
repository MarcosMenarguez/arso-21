package retrofit.bookle;

import es.um.bookle.Actividad;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BookleRestClient {

	@POST("actividades")
	Call<Void> createActividad(@Body Actividad actividad);
	
	@GET("actividades/{id}")
	Call<Actividad> getActividad(@Path("id") String id);
	
	@PUT("actividades/{id}")
	Call<Void> updateActividad(@Path("id") String id, @Body Actividad actividad);

	@DELETE("actividades/{id}")
	Call<Void> removeActividad(@Path("id") String id);

	@FormUrlEncoded
	@POST("actividades/{id}/agenda/{fecha}/turnos/{indice}/reserva")
	Call<Void> reservar(@Path("id") String id, @Path("fecha") String fecha, 
			@Path("indice") int indice, @Field("alumno") String alumno, @Field("email") String email);
	
	@GET("actividades")
	Call<Listado> getListado();
	
}
