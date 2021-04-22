package bookle.servicio;

import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import es.um.bookle.Actividad;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

@WebService(targetNamespace = "http://um.es/arso/bookle/soap")
public interface IServicioBookle {

	/** 
	 * Metodo de alta de una actividad.
	 * 
	 * @param actividad debe ser valida respecto al modelo de dominio
	 * @return identificador de la actividad
	 */
	String create(Actividad actividad) throws ServicioBookleException;
	
	/**
	 * Actualiza una actividad.
	 * @param actividad debe ser valida respecto al modelo de dominio
	 */
	void update(Actividad actividad) throws RepositorioException, EntidadNoEncontrada;
	
	/**
	 * Recupera una actividad utilizando el identificador. 	
	 */
	Actividad getActividad(String id)  throws RepositorioException, EntidadNoEncontrada;
	
	/**
	 * Elimina una actividad utilizando el identificador.
	 */
	void removeActividad(String id)  throws RepositorioException, EntidadNoEncontrada;
	
		
	/**
	 * Realiza la reserva de un turno de la actividad si no está ocupado.
	 * @rerturn indica si se ha podido efectuar la reserva
	 */
	boolean reservar(String idActividad, Date fecha, int indice, String alumno, String email) throws RepositorioException, EntidadNoEncontrada;
	
	
	/**
	 * Retorna un resumen de todas las actividades de todas las actividades.	
	 */
	List<ActividadResumen> getListadoActividades() throws RepositorioException;
}
