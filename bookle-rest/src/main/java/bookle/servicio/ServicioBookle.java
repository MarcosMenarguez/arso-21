package bookle.servicio;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.xml.datatype.XMLGregorianCalendar;

import org.xml.sax.SAXParseException;

import bookle.repositorio.FactoriaRepositorioActividades;
import bookle.repositorio.RepositorioActividades;
import es.um.bookle.Actividad;
import es.um.bookle.DiaActividad;
import es.um.bookle.Reserva;
import es.um.bookle.Turno;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

public class ServicioBookle implements IServicioBookle {

	private RepositorioActividades repositorio = FactoriaRepositorioActividades.getRepositorio();

	// patrón singleton

	private static ServicioBookle instancia;

	public static ServicioBookle getInstancia() {

		if (instancia == null)
			instancia = new ServicioBookle();

		return instancia;
	}

	/** Métodos de apoyo **/

	protected void validar(Actividad actividad) throws IllegalArgumentException {

		List<SAXParseException> resultadoValidacion = Utils.validar(actividad);

		if (!resultadoValidacion.isEmpty())
			// FIXME simplificación: Se incluye en la información el mensaje de la primera excepción
			
			throw new IllegalArgumentException(
					"La actividad no cumple el esquema: " + resultadoValidacion.get(0).getMessage());

	}

	@Override
	public String create(Actividad actividad) throws ServicioBookleException {

		
		// Según el esquema, el id es obligatorio
		// Se establece uno provisional, el repositorio aportará el definitivo
		actividad.setId(" ");

		try {
			// validar(actividad);

			return repositorio.add(actividad);
		}
		catch(RepositorioException e) {
			throw new ServicioBookleException("No se ha podido crear la actividad", e);
		}
	}

	@Override
	public void update(Actividad actividad) throws RepositorioException, EntidadNoEncontrada {

		// validar(actividad);

		repositorio.update(actividad);
	}

	@Override
	public Actividad getActividad(String id) throws RepositorioException, EntidadNoEncontrada {

		return repositorio.getById(id);
	}

	@Override
	public void removeActividad(String id) throws RepositorioException, EntidadNoEncontrada {

		Actividad actividad = repositorio.getById(id);

		repositorio.delete(actividad);
	}

	@Override
	public boolean reservar(String id, Date fecha, int indice, String alumno, String email)
			throws RepositorioException, EntidadNoEncontrada {

		if (fecha == null)
			throw new IllegalArgumentException("La fecha debe establecerse");

		if (indice < 1)
			throw new IllegalArgumentException("El primer turno tiene índice 1");

		if (alumno == null || alumno.isBlank())
			throw new IllegalArgumentException("El nombre del alumno no debe ser vacío");

		// email es opcional

		Actividad actividad = repositorio.getById(id);

		DiaActividad diaActividad = null;

		XMLGregorianCalendar fechaXML = Utils.createFecha(fecha);

		Optional<DiaActividad> resultado = actividad.getAgenda().stream()
				.filter(dia -> dia.getFecha().equals(fechaXML))
				.findFirst();

		if (resultado.isEmpty())
			throw new IllegalArgumentException("La fecha no esta en la agenda: " + fecha);
		else
			diaActividad = resultado.get();

		if (indice > diaActividad.getTurno().size())
			throw new IllegalArgumentException("No existe el turno " + indice + " para la fecha " + fecha);

		Turno turno = diaActividad.getTurno().get(indice - 1);

		if (turno.getReserva() != null)
			return false;

		Reserva reserva = new Reserva();
		reserva.setAlumno(alumno);
		reserva.setEmail(email);

		turno.setReserva(reserva);

		repositorio.update(actividad);

		return true;
	}

	@Override
	public List<ActividadResumen> getListadoActividades() throws RepositorioException {

		LinkedList<ActividadResumen> resultado = new LinkedList<>();

		for (String id : repositorio.getIds()) {

			try {
				Actividad actividad = getActividad(id);
				ActividadResumen resumen = new ActividadResumen();
				resumen.setId(actividad.getId());
				resumen.setTitulo(actividad.getTitulo());
				resumen.setProfesor(actividad.getProfesor());
				resultado.add(resumen);
			} catch (EntidadNoEncontrada e) {
				// No debe suceder
				e.printStackTrace(); // para depurar
			}
		}

		return resultado;
	}

}
