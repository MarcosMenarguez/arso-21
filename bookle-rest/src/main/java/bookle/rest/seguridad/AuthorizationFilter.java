package bookle.rest.seguridad;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;


import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import com.sun.jersey.api.model.AbstractMethod;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Provider
public class AuthorizationFilter implements ContainerRequestFilter, ResourceFilter {
	
	private static final String BEARER_PREFIX = "Bearer ";
	
	private static final String ROLE_KEY = "rol";
	
	private static final String USER_KEY = "usuario";

		
	private final AbstractMethod method;
	
		
	@Context
	private SecurityContext securityContext;
		
	public AuthorizationFilter(AbstractMethod method) {
		this.method = method;
	}

	@Override
	public ContainerRequest filter(ContainerRequest request) {		
	
		// Obtiene la cabecera "Authorization" y comprueba si tiene el token JWT
        String authorizationHeader = request.getHeaderValue(HttpHeaders.AUTHORIZATION);
		
		if (authorizationHeader == null || !authorizationHeader.startsWith(BEARER_PREFIX)) {
			throw new WebApplicationException(Utils.buildUnauthorizedResponse("Petición no autorizada",
					"no tiene token JWT", "La petición no tiene el token JWT"));

		} else {
			
			String jwt = authorizationHeader.substring(BEARER_PREFIX.length());

			try {
				// Valida el token, los errores son notificados con excepciones
				
				Claims claims = JwtUtils.validateJWT(jwt);
				
				// Comprueba si el rol del usuario es uno de los tokens permitidos en el método
				
				String userRole = claims.get(ROLE_KEY).toString();
				List<AvailableRoles> roles = Arrays.asList(method.getAnnotation(Secured.class).value());
				
				if(!roles.contains(AvailableRoles.valueOf(userRole))) {
					throw new WebApplicationException(Utils.buildForbiddenResponse(userRole, method.getResource().getPath().getValue()));
				}
				
				// Establece un nuevo contexto de seguridad que será inyectado en el servicio REST
				
				final SecurityContext currentSecurityContext = request.getSecurityContext();
				request.setSecurityContext(new SecurityContext() {

				   @Override
				   public Principal getUserPrincipal() {
				            return () -> claims.get(USER_KEY).toString();
				   }

				    @Override
				    public boolean isUserInRole(String role) {
				        return true;
				    }

				    @Override
				    public boolean isSecure() {
				        return currentSecurityContext.isSecure();
				    }

				    @Override
				    public String getAuthenticationScheme() {
				        return "Bearer";
				    }
				});
			
			
			} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
				throw new WebApplicationException(Utils.buildUnauthorizedResponse("El token de acceso no es valido", e.getClass().getSimpleName(), e.getMessage()));
			}		
		}
		
		return request;		
	}

	@Override
	public ContainerRequestFilter getRequestFilter() {
		return this;
	}

	@Override
	public ContainerResponseFilter getResponseFilter() {
		return null;
	}
	
}
