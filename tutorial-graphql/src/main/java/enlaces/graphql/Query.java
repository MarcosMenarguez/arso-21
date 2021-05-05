package enlaces.graphql;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

import enlaces.modelo.Enlace;
import enlaces.servicio.ServicioEnlaces;
import repositorio.RepositorioException;

public class Query implements GraphQLRootResolver {
    
    public List<Enlace> findAll() throws RepositorioException {
        return ServicioEnlaces.getInstancia().findAll();
    }
}