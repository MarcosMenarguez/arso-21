package pasarela.zuul.seguridad;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	private static final String TOKEN_PREFIX = "Bearer ";
	
	private static final String ROLE_KEY = "rol";
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		// Intenta recuperar la cabecera "Authorization" en busca del token JWT
		
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if (authHeader != null && authHeader.startsWith(TOKEN_PREFIX)) {			
			final String jwt = authHeader.substring(TOKEN_PREFIX.length());

			try {
				
				// Valida el token JWT. Los fallos de validación se notifican con excepción
				Claims claims = JwtUtils.validateJWT(jwt);
				
				// Recupera el usuario y el rol del JWT y lo establece en el contexto de seguridad de Spring
				
				ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				authorities.add(new SimpleGrantedAuthority(claims.get(ROLE_KEY).toString()));
								
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);

				// Establecemos la autenticación en el contexto de seguridad
				// Se interpreta como que el usuario ha superado la autenticación
				
				SecurityContextHolder.getContext().setAuthentication(auth);					
			
			
			} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
				
				Utils.writeResponseJSON(response, HttpServletResponse.SC_UNAUTHORIZED, 
						Utils.buildAuthorizationErrorJSON(e.getClass().getSimpleName(), e.getMessage()));
				
				return;
			}			
		}
		
		chain.doFilter(request, response);
	}
	
	
}