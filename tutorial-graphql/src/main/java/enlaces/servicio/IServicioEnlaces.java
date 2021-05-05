package enlaces.servicio;

import java.util.List;

import enlaces.modelo.Enlace;
import repositorio.RepositorioException;

public interface IServicioEnlaces {

	Enlace crear(String url, String descripcion, String emailUsuario) throws RepositorioException;

	List<Enlace> findAll() throws RepositorioException;
	
}
