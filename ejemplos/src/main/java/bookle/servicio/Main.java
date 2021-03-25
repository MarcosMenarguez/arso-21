package bookle.servicio;

import java.util.Date;

import es.um.bookle.Actividad;
import es.um.bookle.DiaActividad;
import es.um.bookle.Turno;


public class Main {

	public static void main(String[] args) throws Exception {
		
				
		IServicioBookle servicio = ServicioBookle.getInstancia();
		
		// 1. Creación de una actividad
		
		Actividad actividad = new Actividad();
		actividad.setTitulo("Entrevistas de prácticas");
		actividad.setDescripcion("Enlace Zoom: ...");
		actividad.setProfesor("Marcos");
		actividad.setEmail("marcos@um.es");
		
		// Día 1
		
		DiaActividad dia1 = new DiaActividad();
		Date fecha1 = Utils.dateFromString("12-04-2021");
		dia1.setFecha(Utils.createFecha(fecha1));
		
		for (int hora = 10; hora <= 14; hora++) {
		
			Turno turno = new Turno();
			turno.setHorario(hora + ":00h");
			dia1.getTurno().add(turno);
		}
		
		actividad.getAgenda().add(dia1);
		
		// Día 2
		
		DiaActividad dia2 = new DiaActividad();
		Date fecha2 = Utils.dateFromString("13-04-2021");
		dia2.setFecha(Utils.createFecha(fecha2));
		
		for (int hora = 17; hora <= 19; hora++) {		
			Turno turno = new Turno();
			turno.setHorario(hora + ":00h");
			dia2.getTurno().add(turno);
		}
		
		actividad.getAgenda().add(dia2);
		
		String id = servicio.create(actividad);
		
		// 2. Actualización
		
		actividad = servicio.getActividad(id);
		
		actividad.setDescripcion("Enlace Zoom");
		
		servicio.update(actividad);
		
		// 3. Reserva
		
		Date fechaReserva = Utils.dateFromString("12-04-2021");
		
		boolean resultado = servicio.reservar(id, fechaReserva, 1, "Juan", "juan@um.es");
		
		System.out.println(resultado);
		
		// 4. Listado de resúmenes
		
		System.out.println("Listado:");
		for (ActividadResumen resumen : servicio.getListadoActividades())
			System.out.println("\t" + resumen);
		
		
		System.out.println("Fin.");
	}
}
