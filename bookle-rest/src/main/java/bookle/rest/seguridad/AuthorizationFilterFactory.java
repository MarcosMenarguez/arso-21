package bookle.rest.seguridad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.jersey.api.model.AbstractMethod;
import com.sun.jersey.spi.container.ResourceFilter;
import com.sun.jersey.spi.container.ResourceFilterFactory;

public class AuthorizationFilterFactory implements ResourceFilterFactory {

    @Override
    public List<ResourceFilter> create(AbstractMethod abstractMethod) {    	
    	
    	// Aplica el filtro si el método tiene la anotación @Secured
    	
    	if(abstractMethod.isAnnotationPresent(Secured.class)) 
    		return Arrays.asList(new AuthorizationFilter(abstractMethod));    	
    	else 
    		return new ArrayList<ResourceFilter>();    	
    }
}
