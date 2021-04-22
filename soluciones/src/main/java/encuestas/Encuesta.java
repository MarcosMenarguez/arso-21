package encuestas;

import java.time.LocalDateTime;
import java.util.LinkedList;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.types.ObjectId;


public class Encuesta {

	@BsonId
	private ObjectId id;
	
	private String titulo;
	private String instrucciones;
	private LocalDateTime fechaApertura;
	private LocalDateTime fechaCierre;
	private LinkedList<Opcion> opciones = new LinkedList<>();
			
	@BsonIgnore
	public String getIdentificador() {
		return id.toString();
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getInstrucciones() {
		return instrucciones;
	}

	public void setInstrucciones(String instrucciones) {
		this.instrucciones = instrucciones;
	}

	public LocalDateTime getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(LocalDateTime fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public LocalDateTime getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(LocalDateTime fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	
	public LinkedList<Opcion> getOpciones() {
		return opciones;
	}

	public void setOpciones(LinkedList<Opcion> opciones) {
		this.opciones = opciones;
	}

	@BsonIgnore
	public int getNumeroOpciones() {
		
		return this.opciones.size();
	}
	
	@Override
	public String toString() {
		return "Encuesta [id=" + id + ", titulo=" + titulo + ", instrucciones=" + instrucciones + ", fechaApertura="
				+ fechaApertura + ", fechaCierre=" + fechaCierre + ", opciones=" + opciones + "]";
	}
	
	
	
}
