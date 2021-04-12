package mongodb.enlaces.pojo;

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

public class RepositorioEnlacesMongoDB implements RepositorioEnlaces {
	
	private MongoCollection<Enlace> enlaces;

	
	public RepositorioEnlacesMongoDB() {
		
		// TODO: la cadena de conexión debería obtenerse de una propiedad
		
		String uriString = "mongodb+srv://arso:arso-20@cluster0.mp1o0.azure.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";

		ConnectionString connectionString = new ConnectionString(uriString);

		CodecRegistry pojoCodecRegistry = CodecRegistries
				.fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				pojoCodecRegistry);
		MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.codecRegistry(codecRegistry).build();

		MongoClient mongoClient = MongoClients.create(clientSettings);
		
		MongoDatabase db = mongoClient.getDatabase("test");
		
		this.enlaces = db.getCollection("enlaces", Enlace.class);
		
	}

	/** Métodos de apoyo **/

	protected boolean checkDocument(ObjectId id) {

		return enlaces.find(Filters.eq("_id", id)).first() != null;
	}

	
	/** fin métodos de apoyo **/

	@Override
	public String add(Enlace enlace) throws RepositorioException {

		try {
			enlace.setId(new ObjectId());
			
			enlaces.insertOne(enlace);

			return enlace.getIdentificador();
		} catch (Exception e) {
			throw new RepositorioException("No se ha podido insertar la entidad", e);
		}
	}

	@Override
	public void update(Enlace enlace) throws RepositorioException, EntidadNoEncontrada {
			
		if (! checkDocument(enlace.getId()))
			throw new EntidadNoEncontrada("No existe la entidad con id:" + enlace.getId());
		

		try {
			
			enlaces.replaceOne(Filters.eq("_id", enlace.getId()), enlace);
			
		} catch (Exception e) {
			throw new RepositorioException("No se ha podido actualizar la entidad, id:" + enlace.getId(), e);
		}

	}

	@Override
	public void delete(Enlace enlace) throws RepositorioException, EntidadNoEncontrada {
		
		if (! checkDocument(enlace.getId()))
			throw new EntidadNoEncontrada("No existe la entidad con id:" + enlace.getId());
		
		try {
			enlaces.deleteOne(Filters.eq("_id", enlace.getId()));
		} catch (Exception e) {
			throw new RepositorioException("No se ha podido borrar la entidad, id:" + enlace.getId(), e);
		}

	}

	@Override
	public Enlace getById(String id) throws RepositorioException, EntidadNoEncontrada {

		Enlace enlace = enlaces.find(Filters.eq("_id", new ObjectId(id))).first();
		
		if (enlace == null)
			throw new EntidadNoEncontrada("No existe la entidad con id:" + id);
		
		return enlace;
	}

	@Override
	public List<Enlace> getAll() throws RepositorioException {
		
		LinkedList<Enlace> allEnlaces = new LinkedList<>();

		enlaces.find().into(allEnlaces);
		
		return allEnlaces;
	}

	@Override
	public List<String> getIds() {
		
		LinkedList<String> allIds = new LinkedList<String>();
		
		enlaces.find().map(e -> e.getId().toString()).into(allIds);
		
		return allIds;
	}
}