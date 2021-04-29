package bookle.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bookle.servicio.ServicioBookle;
import bookle.servicio.ServicioBookleException;
import es.um.bookle.Actividad;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

@Path("actividades")
public class BookleControladorRest {

	private ServicioBookle servicio = ServicioBookle.getInstancia();
	
	// http://localhost:8080/api/actividades

	@GET
	public Response test() {
			
		return Response.ok().entity("hola").build();
	}

	// http://localhost:8080/api/actividades/1
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getActividadBookle(@PathParam("id") String id) throws Exception {
		
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
		
		// TODO: comprobar que el parámetro id coincide con el campo id de la actividad
		
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
		
		String url = "http://localhost:8080/api/actividades/" + id;
		
		return Response.status(Response.Status.CREATED).header("Location", url).build();
		
		// return Response.created(uri).build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
