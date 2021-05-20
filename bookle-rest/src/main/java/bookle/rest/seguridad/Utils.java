package bookle.rest.seguridad;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

public class Utils {

	@XmlRootElement
	@XmlAccessorType(XmlAccessType.FIELD)
	protected static class RespuestaError {
		
		private String error;
		private String descripcion;
		private String causa;
		private String mensaje;
		
		public RespuestaError() {
			
		}
		
		public RespuestaError(String error, String descripcion, String causa, String mensaje) {
			
			this.error = error;
			this.descripcion = descripcion;
			this.causa = causa;
			this.mensaje = mensaje;
		}
		
		
		public RespuestaError(String error, String descripcion) {
			super();
			this.error = error;
			this.descripcion = descripcion;			
		}

		public String getError() {
			return error;
		}
		public String getDescripcion() {
			return descripcion;
		}
		public String getCausa() {
			return causa;
		}
		public String getMensaje() {
			return mensaje;
		}
		
	}
	
	protected static Response buildErrorXML(Status httpCode, RespuestaError error) {
	
		return Response.status(httpCode).entity(error).type(MediaType.APPLICATION_XML).build();

	}

	public static Response buildUnauthorizedResponse(String descripcion, String causa, String mensaje) {
		
		RespuestaError respuestaXML = new RespuestaError(Status.UNAUTHORIZED.name(), descripcion, causa, mensaje);
		
		return buildErrorXML(Status.UNAUTHORIZED, respuestaXML);
	}
	
	public static Response buildForbiddenResponse(String rol, String peticion) {
		
		RespuestaError respuestaXML = new RespuestaError(Status.FORBIDDEN.name(), "rol " + rol + " no autorizado para la peticion: " + peticion);
				
		return buildErrorXML(Status.FORBIDDEN, respuestaXML);
	}
}
