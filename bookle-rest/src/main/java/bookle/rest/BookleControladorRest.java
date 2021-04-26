package bookle.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bookle.servicio.ServicioBookle;

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
}
