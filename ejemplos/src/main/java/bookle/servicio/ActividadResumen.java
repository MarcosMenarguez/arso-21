package bookle.servicio;

public class ActividadResumen {

	private String id;
	private String titulo;
	private String profesor;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getProfesor() {
		return profesor;
	}
	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}
	
	@Override
	public String toString() {
		return "ActividadResumen [id=" + id + ", titulo=" + titulo + ", profesor=" + profesor + "]";
	}
	
	
}
