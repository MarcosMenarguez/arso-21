package enlaces.servicio;

import java.util.List;

import enlaces.modelo.Enlace;
import enlaces.repositorio.FactoriaRepositorioEnlaces;
import enlaces.repositorio.RepositorioEnlaces;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import repositorio.RepositorioException;

public class ServicioEnlaces implements IServicioEnlaces {

	private RepositorioEnlaces repositorio = FactoriaRepositorioEnlaces.getRepositorio();
	
	/** Patrón Singleton **/
	
	private static ServicioEnlaces instancia;

	private ServicioEnlaces() {
		
	}
	
	public static ServicioEnlaces getInstancia() {

		if (instancia == null)
			instancia = new ServicioEnlaces();

		return instancia;
	}

	@GraphQLMutation
	@Override
	public Enlace crear(String url, String descripcion, String emailUsuario) throws RepositorioException {
		
		Enlace enlace = new Enlace();
		enlace.setUrl(url);
		enlace.setDescripcion(descripcion);
		enlace.setEmailUsuario(emailUsuario);
		
		String id = repositorio.add(enlace);
		
		enlace.setId(id);
		
		return enlace;
	}

	@GraphQLQuery
	@Override
	public List<Enlace> findAll() throws RepositorioException {
		
		return repositorio.getAll();
	}
	
}
