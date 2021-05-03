package bookle.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class TratamientoIllegalArgumentException implements ExceptionMapper<IllegalArgumentException> {
	
	@Override
	public Response toResponse(IllegalArgumentException arg0) {
		
		return Response.status(Response.Status.BAD_REQUEST).entity(arg0.getMessage()).build();
	}
}