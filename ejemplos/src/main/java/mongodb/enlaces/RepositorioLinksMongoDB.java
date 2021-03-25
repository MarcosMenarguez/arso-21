package mongodb.enlaces;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

public class RepositorioLinksMongoDB implements RepositorioEnlaces {

	// Constantes para identificadores de las propiedades
	
	private static final String URL = "url";
	private static final String DESCRIPCION = "descripcion";
	private static final String EMAIL_USUARIO = "email_usuario";
	
	
	
	private MongoCollection<Document> enlaces;

	
	public RepositorioLinksMongoDB() {
		
		// TODO: la cadena de conexión debería obtenerse de una propiedad
		
		String uriString = "url";

		ConnectionString connectionString = new ConnectionString(uriString);

		MongoClient mongoClient = MongoClients.create(connectionString);

		MongoDatabase db = mongoClient.getDatabase("test");
		
		this.enlaces = db.getCollection("enlaces");
		
	}

	/** Métodos de apoyo **/

	protected boolean checkDocument(String id) {

		return enlaces.find(Filters.eq("_id", new ObjectId(id))).first() != null;
	}

	protected Document toDocument(Enlace enlace) {

		// Proporciona un id si no lo tiene
		if (enlace.getId() == null) {
			ObjectId id = new ObjectId();
			enlace.setId(id.toString());
		}

		Document doc = new Document();
		
		doc.append("_id", new ObjectId(enlace.getId()));
		doc.append(URL, enlace.getUrl());
		doc.append(DESCRIPCION, enlace.getDescripcion());
		doc.append(EMAIL_USUARIO, enlace.getEmailUsuario());

		return doc;
	}

	protected Enlace fromDocument(Document doc) {
		
		Enlace enlace = new Enlace();
		enlace.setId(doc.get("_id").toString());
		enlace.setUrl(doc.getString(URL));
		enlace.setDescripcion(doc.getString(DESCRIPCION));
		enlace.setEmailUsuario(doc.getString(EMAIL_USUARIO));
		
		return enlace;
	}

	/** fin métodos de apoyo **/

	@Override
	public String add(Enlace enlace) throws RepositorioException {

		try {
			Document doc = toDocument(enlace);
			enlaces.insertOne(doc);

			return doc.get("_id").toString();
		} catch (Exception e) {
			throw new RepositorioException("No se ha podido insertar la entidad", e);
		}
	}

	@Override
	public void update(Enlace enlace) throws RepositorioException, EntidadNoEncontrada {
			
		if (! checkDocument(enlace.getId()))
			throw new EntidadNoEncontrada("No existe la entidad con id:" + enlace.getId());
		

		try {

			Document doc = toDocument(enlace);
		
			enlaces.replaceOne(Filters.eq("_id", doc.get("_id")), doc);
			
		} catch (Exception e) {
			throw new RepositorioException("No se ha podido actualizar la entidad, id:" + enlace.getId(), e);
		}

	}

	@Override
	public void delete(Enlace enlace) throws RepositorioException, EntidadNoEncontrada {
		
		if (! checkDocument(enlace.getId()))
			throw new EntidadNoEncontrada("No existe la entidad con id:" + enlace.getId());
		
		try {
			enlaces.deleteOne(Filters.eq("_id", new ObjectId(enlace.getId())));
		} catch (Exception e) {
			throw new RepositorioException("No se ha podido borrar la entidad, id:" + enlace.getId(), e);
		}

	}

	@Override
	public Enlace getById(String id) throws RepositorioException, EntidadNoEncontrada {

		Document doc = enlaces.find(Filters.eq("_id", new ObjectId(id))).first();
		
		if (doc == null)
			throw new EntidadNoEncontrada("No existe la entidad con id:" + id);
		
		return fromDocument(doc);
	}

	@Override
	public List<Enlace> getAll() throws RepositorioException {
		
		LinkedList<Enlace> allEnlaces = new LinkedList<>();

		for (Document doc : enlaces.find()) {
			allEnlaces.add(fromDocument(doc));
		}
		return allEnlaces;
	}

	@Override
	public List<String> getIds() {
		
		LinkedList<String> allIds = new LinkedList<String>();
		
		enlaces.find().map(doc -> doc.get("_id").toString()).into(allIds);
		
		return allIds;
	}
}