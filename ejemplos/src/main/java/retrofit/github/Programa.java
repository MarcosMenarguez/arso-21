package retrofit.github;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class Programa {

	public static void main(String[] args) throws Exception {

		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://api.github.com/")
				.addConverterFactory(JacksonConverterFactory.create())
				.build();

		GitHubService service = retrofit.create(GitHubService.class);
		
		Call<List<Repo>> repos = service.listRepos("octocat");
		
		Response<List<Repo>> respuesta = repos.execute();
		
		List<Repo> lista = respuesta.body();
		
		System.out.println(lista);
		
		System.out.println("fin.");
		
	}
}
