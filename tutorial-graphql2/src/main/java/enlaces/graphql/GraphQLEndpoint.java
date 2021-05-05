package enlaces.graphql;

import javax.servlet.annotation.WebServlet;

import enlaces.servicio.ServicioEnlaces;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;
import io.leangen.graphql.GraphQLSchemaGenerator;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

	public GraphQLEndpoint() {
        super(buildSchema());
    }

    private static GraphQLSchema buildSchema() {
      
    	 return new GraphQLSchemaGenerator()
    		        .withOperationsFromSingletons(
    		            ServicioEnlaces.getInstancia()
    		            )
    		        .generate();
    }
}