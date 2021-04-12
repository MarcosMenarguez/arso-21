package mongodb.enlaces.pojo;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.types.ObjectId;

public class Enlace {

	@BsonId
	private ObjectId id; 
	
    private String url;
    private String descripcion;
    private String emailUsuario;

    /** Identificador **/
    
	public ObjectId getId() {
		return id;
	}
	
	public void setId(ObjectId id) {
		this.id = id;
	}

	@BsonIgnore
	public String getIdentificador() {
		
		return id.toString();
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getEmailUsuario() {
		return emailUsuario;
	}
	
    public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	@Override
	public String toString() {
		return "Enlace [id=" + id + ", url=" + url + ", descripcion=" + descripcion + ", emailUsuario=" + emailUsuario
				+ "]";
	}

	
}
