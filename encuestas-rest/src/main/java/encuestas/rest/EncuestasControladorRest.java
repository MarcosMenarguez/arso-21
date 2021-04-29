package encuestas.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import encuestas.IServicioEncuestas;
import encuestas.ServicioEncuestas;

@Path("encuestas")
public class EncuestasControladorRest {

	private IServicioEncuestas servicio = ServicioEncuestas.getInstancia();

	// http://localhost:8080/api/encuestas

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listado() throws Exception {

		return Response.ok().entity(
				servicio.getListadoResumen()
				).build();
	}

	// http://localhost:8080/api/encuestas/6086ec9f2723fe04cc41014b

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEncuesta(@PathParam("id") String id) throws Exception {

		return Response.ok().entity(
				servicio.getById(id)
				).build();
	}

}
