package encuestas;

import java.util.LinkedList;

import org.bson.codecs.pojo.annotations.BsonIgnore;

public class Opcion {

	private String texto;
	private LinkedList<String> votos = new LinkedList<>();
	
		public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LinkedList<String> getVotos() {
		return votos;
	}

	public void setVotos(LinkedList<String> votos) {
		this.votos = votos;
	}

	@BsonIgnore
	public int getNumeroVotos() {
		return votos.size();
	}
}
