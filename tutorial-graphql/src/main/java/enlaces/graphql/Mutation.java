package enlaces.graphql;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

import enlaces.modelo.Enlace;
import enlaces.servicio.ServicioEnlaces;
import repositorio.RepositorioException;

public class Mutation implements GraphQLRootResolver {
    
    public Enlace crearEnlace(String url, String descripcion, String emailUsuario) throws RepositorioException {
        
    	return ServicioEnlaces.getInstancia().crear(url, descripcion, emailUsuario);
    }
}
