package encuestas;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

public interface IServicioEncuestas {

	String create(Encuesta encuesta) throws RepositorioException;
	
	boolean haVotado(String id, String usuario) throws RepositorioException, EntidadNoEncontrada;
	
	void votar(String id, int opcion, String usuario) throws RepositorioException, EntidadNoEncontrada;
	
	Encuesta getById(String id) throws RepositorioException, EntidadNoEncontrada;
	
	void remove(String id) throws RepositorioException, EntidadNoEncontrada;
	
	ListadoEncuestas getListadoResumen() throws RepositorioException, EntidadNoEncontrada;
}
