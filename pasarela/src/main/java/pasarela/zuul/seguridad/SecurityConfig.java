package pasarela.zuul.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {	

	@Autowired
	private SecuritySuccessHandler successHandler;
	
	@Autowired
	private JwtRequestFilter authenticationRequestFilter;
	
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		
		
		httpSecurity.csrf().disable()
			.httpBasic().disable()
			.authorizeRequests()
			.anyRequest().authenticated()
			.and()
			.oauth2Login().successHandler(successHandler)
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		// Establece el filtro de autenticación en la cadena de filtros de seguridad
		
		httpSecurity.addFilterBefore(authenticationRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
		
	}	
}
