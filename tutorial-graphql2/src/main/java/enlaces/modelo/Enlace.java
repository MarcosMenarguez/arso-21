package enlaces.modelo;

public class Enlace {

	private String id; // gestionado por el repositorio
	
    private String url;
    private String descripcion;
    private String emailUsuario;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
