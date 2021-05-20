package bookle.rest;

import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import bookle.rest.Listado.ResumenExtendido;
import bookle.rest.seguridad.AvailableRoles;
import bookle.rest.seguridad.Secured;
import bookle.servicio.ActividadResumen;
import bookle.servicio.ServicioBookle;
import bookle.servicio.ServicioBookleException;
import es.um.bookle.Actividad;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

@Api
@Path("actividades")
public class BookleControladorRest {

	private ServicioBookle servicio = ServicioBookle.getInstancia();
	
	@Context
	private SecurityContext securityContext;
	
	
	@Context
	private UriInfo uriInfo;
	
		
	// http://localhost:8080/api/actividades/1
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	@ApiOperation(value = "Consulta una actividad",
		notes = "Retorna una actividad utilizando su id",
		response = Actividad.class)
	@ApiResponses(value = {
		@ApiResponse(code = HttpServletResponse.SC_OK, message =""),
		@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, 	message = "Actividad no encontrada")})
	public Response getActividad(
		@ApiParam(value = "id de la actividad", required = true) 
		@PathParam("id") String id)  throws Exception {
			
		return Response.status(Response.Status.OK)
				.entity(
						servicio.getActividad(id)
				).build();
	}
	
	// 	void update(Actividad actividad) throws RepositorioException, EntidadNoEncontrada;

	
	// PUT http://localhost:8080/api/actividades/1
	
	// curl -i -X PUT -H "Content-type: application/xml" -d @test-files/1.xml http://localhost:8080/api/actividades/1

	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_XML)
	public Response update(@PathParam("id") String id, Actividad actividad) throws Exception {
		
		if (! id.equals(actividad.getId()))
			throw new IllegalArgumentException("El identificador no coincide: " + id);
					
		servicio.update(actividad);
		
		return Response.status(Response.Status.NO_CONTENT).build();
		
		
	}
	
	
	// void removeActividad(String id)  throws RepositorioException, EntidadNoEncontrada;
	
	// curl -i -X DELETE http://localhost:8080/api/actividades/1

	
	@DELETE
	@Path("/{id}")
	public Response removeActividad(@PathParam("id") String id) throws Exception {
		
		servicio.removeActividad(id);
		
		return Response.status(Response.Status.NO_CONTENT).build();
		
	}
	
	
	// String create(Actividad actividad) throws ServicioBookleException;
	
	
	// curl -i -X POST -H "Content-type: application/xml" -d @test-files/1.xml http://localhost:8080/api/actividades/

	
	// No hay que agregar ningún fragmento al path
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)	
	public Response create(Actividad actividad) throws Exception {

		String id = servicio.create(actividad);

        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(id);
        URI nuevaURL = builder.build();

        return Response.created(nuevaURL).build();
	}
	
	
	// List<ActividadResumen> getListadoActividades() throws RepositorioException;
	
	// http://localhost:8080/api/actividades

	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Secured(AvailableRoles.PROFESOR)
	public Response getListadoActividades() throws Exception {
		
		System.out.println(this.securityContext.getUserPrincipal().getName());
		
		
		List<ActividadResumen> resultado = servicio.getListadoActividades();
		
		LinkedList<ResumenExtendido> extendido = new LinkedList<Listado.ResumenExtendido>();
		
		for (ActividadResumen actividadResumen : resultado) {
			
			ResumenExtendido resumenExtendido = new ResumenExtendido();
			
			resumenExtendido.setResumen(actividadResumen);
			
			// URL
			
			String id = actividadResumen.getId();
			UriBuilder builder = uriInfo.getAbsolutePathBuilder();
	        builder.path(id);
	        URI nuevaURL = builder.build();
			
	        resumenExtendido.setUrl(nuevaURL.toString()); // string
	        
	        extendido.add(resumenExtendido);
			
		}
		
		// Una lista no es un documento XML
		
		// Creamos un documento XML con un envoltorio
		
		Listado listado = new Listado();
		
		listado.setActividad(extendido);
		
		return Response.ok(listado).build();
		
	}
	
	
	
	// boolean reservar(String idActividad, Date fecha, int indice, String alumno, String email) throws RepositorioException, EntidadNoEncontrada;
	
	// /actividades/{id}/agenda/{fecha}/turnos/{indice}/reserva

	// curl -i -X POST --data "alumno=Pepe&email=pepe@um.es" http://localhost:8080/api/actividades/1/agenda/2021-04-08/turno/1/reserva

	// query: http://localhost:8080/api/actividades/1/agenda/2021-04-08/turno/1/reserva?alumno=Pepe&email=pepe@um.es
	
	@POST
	@Path("/{id}/agenda/{fecha}/turnos/{indice}/reserva")
	public Response reservar(@PathParam("id") String id, 
					@PathParam("fecha") String fecha, 
					@PathParam("indice") int indice,
					@FormParam("alumno") String alumno,
					@FormParam("email") String email) throws Exception {
		
		  DateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");

	      Date fechaDate = null;
	      try {
	         fechaDate = formateador.parse(fecha);
	      } catch (ParseException e) {
	         throw new IllegalArgumentException(e);
	      }
		
		servicio.reservar(id, fechaDate, indice, alumno, email);
		
		return Response.status(Response.Status.NO_CONTENT).build();
		
	}
	
	
}
