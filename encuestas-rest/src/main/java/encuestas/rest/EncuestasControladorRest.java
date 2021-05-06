package encuestas.rest;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import encuestas.Encuesta;
import encuestas.IServicioEncuestas;
import encuestas.ListadoEncuestas;
import encuestas.ListadoEncuestas.EncuestaResumen;
import encuestas.ServicioEncuestas;
import encuestas.rest.Listado.ResumenExtendido;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

@Path("encuestas")
public class EncuestasControladorRest {

	private IServicioEncuestas servicio = ServicioEncuestas.getInstancia();

	@Context
	private UriInfo uriInfo;
	
	
	// http://localhost:8080/api/encuestas

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listado() throws Exception {

		ListadoEncuestas resultado = servicio.getListadoResumen();
		
		LinkedList<ResumenExtendido> extendido = new LinkedList<ResumenExtendido>();
		
		for (EncuestaResumen encuestaResumen : resultado.getEncuestas()) {
			
			ResumenExtendido resumenExtendido = new ResumenExtendido();
			
			resumenExtendido.setResumen(encuestaResumen);
			
			// URL
			
			String id = encuestaResumen.getId();
			UriBuilder builder = uriInfo.getAbsolutePathBuilder();
	        builder.path(id);
	        URI nuevaURL = builder.build();
			
	        resumenExtendido.setUrl(nuevaURL.toString()); // string
	        
	        extendido.add(resumenExtendido);
			
		}
		
		
		Listado listado = new Listado();
		
		listado.setEncuestas(extendido);
		
		return Response.ok(listado).build();
		
		
	}

	// http://localhost:8080/api/encuestas/608cee8461c58a080436054a

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEncuesta(@PathParam("id") String id) throws Exception {

		return Response.ok().entity(
				servicio.getById(id)
				).build();
	}
	
	// curl -i -X POST --data "usuario=pepe@um.es" http://localhost:8080/api/encuestas/608cee8461c58a080436054a/opcion/1
	
	@POST
	@Path("{id}/opcion/{opcion}")
	public Response votar(@PathParam("id") String id, 
			@PathParam("opcion") int opcion, @FormParam("usuario") String usuario) throws Exception {
		
		servicio.votar(id, opcion, usuario);
		
		return Response.noContent().build();
		
	}
	
	// curl -i -X POST -H "Content-type: application/json" -d @test-files/encuesta1.json http://localhost:8080/api/encuestas/

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Encuesta encuesta) throws Exception {
		
		String id = servicio.create(encuesta);

        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(id);
        URI nuevaURL = builder.build();

        return Response.created(nuevaURL).build();
	}
	
	
	
}
