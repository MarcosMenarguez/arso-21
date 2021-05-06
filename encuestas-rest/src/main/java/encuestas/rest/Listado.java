package encuestas.rest;

import java.util.LinkedList;

import encuestas.ListadoEncuestas.EncuestaResumen;

public class Listado {

	public static class ResumenExtendido {
		
		private EncuestaResumen resumen;		
		private String url;
		
		public EncuestaResumen getResumen() {
			return resumen;
		}
		
		public void setResumen(EncuestaResumen resumen) {
			this.resumen = resumen;
		}
		
		public String getUrl() {
			return url;
		}
		
		public void setUrl(String url) {
			this.url = url;
		}
	}
	
	private LinkedList<ResumenExtendido> encuestas = new LinkedList<>();
	
	public LinkedList<ResumenExtendido> getEncuestas() {
		return encuestas;
	}
	
	public void setEncuestas(LinkedList<ResumenExtendido> encuesta) {
		this.encuestas = encuesta;
	}
	
}
