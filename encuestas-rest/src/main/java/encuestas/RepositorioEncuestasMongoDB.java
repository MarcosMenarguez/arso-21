package encuestas;

import java.util.LinkedList;
import java.util.List;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

public class RepositorioEncuestasMongoDB implements RepositorioEncuestas {

	
	private MongoCollection<Encuesta> encuestas;

	
	public RepositorioEncuestasMongoDB() {
		
		// TODO: la cadena de conexión debería obtenerse de una propiedad
		
		String uriString = "mongodb://arso:Ux1WkJEbALErSIm2@cluster0-shard-00-00.mp1o0.azure.mongodb.net:27017,cluster0-shard-00-01.mp1o0.azure.mongodb.net:27017,cluster0-shard-00-02.mp1o0.azure.mongodb.net:27017/myFirstDatabase?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority";

		ConnectionString connectionString = new ConnectionString(uriString);

		CodecRegistry pojoCodecRegistry = CodecRegistries
				.fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				pojoCodecRegistry);
		MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.codecRegistry(codecRegistry).build();

		MongoClient mongoClient = MongoClients.create(clientSettings);
		
		MongoDatabase db = mongoClient.getDatabase("test");
		
		this.encuestas = db.getCollection("encuestas", Encuesta.class);
		
	}

	/** Métodos de apoyo **/

	protected boolean checkDocument(ObjectId id) {

		return encuestas.find(Filters.eq("_id", id)).first() != null;
	}
	

	/** fin métodos de apoyo **/

	@Override
	public String add(Encuesta enlace) throws RepositorioException {

		try {
			
			enlace.setId(new ObjectId());
			
			encuestas.insertOne(enlace);

			return enlace.getId().toString();
		} catch (Exception e) {
			throw new RepositorioException("No se ha podido insertar la entidad", e);
		}
	}

	@Override
	public void update(Encuesta enlace) throws RepositorioException, EntidadNoEncontrada {
			
		if (! checkDocument(enlace.getId()))
			throw new EntidadNoEncontrada("No existe la entidad con id:" + enlace.getId());
		

		try {
		
			encuestas.replaceOne(Filters.eq("_id", enlace.getId()), enlace);
			
		} catch (Exception e) {
			throw new RepositorioException("No se ha podido actualizar la entidad, id:" + enlace.getId(), e);
		}

	}

	@Override
	public void delete(Encuesta enlace) throws RepositorioException, EntidadNoEncontrada {
		
		if (! checkDocument(enlace.getId()))
			throw new EntidadNoEncontrada("No existe la entidad con id:" + enlace.getId());
		
		try {
			encuestas.deleteOne(Filters.eq("_id", enlace.getId()));
		} catch (Exception e) {
			throw new RepositorioException("No se ha podido borrar la entidad, id:" + enlace.getId(), e);
		}

	}

	@Override
	public Encuesta getById(String id) throws RepositorioException, EntidadNoEncontrada {

		Encuesta encuesta = encuestas.find(Filters.eq("_id", new ObjectId(id))).first();
		
		if (encuesta == null)
			throw new EntidadNoEncontrada("No existe la entidad con id:" + id);
		
		return encuesta;
	}

	@Override
	public List<Encuesta> getAll() throws RepositorioException {
		
		LinkedList<Encuesta> allEncuestas = new LinkedList<>();

		encuestas.find().into(allEncuestas);
		
		return allEncuestas;
	}

	@Override
	public List<String> getIds() {
		
		LinkedList<String> allIds = new LinkedList<String>();
		
		encuestas.find().map(e -> e.getId().toString()).into(allIds);
		
		return allIds;
	}
}