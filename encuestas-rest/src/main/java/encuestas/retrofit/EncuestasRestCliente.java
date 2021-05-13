package encuestas.retrofit;

import encuestas.Encuesta;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EncuestasRestCliente {

	@GET("encuestas/{id}")
	Call<Encuesta> getEncuesta(@Path("id") String id);
}
