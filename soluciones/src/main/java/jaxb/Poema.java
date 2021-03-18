package jaxb;

import java.util.LinkedList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Poema {

	@XmlAttribute(required=true)
	private String fecha;
	
	@XmlAttribute(required=true)
	private String lugar;
	
	@XmlElement(required=true)
	private String titulo;
	
	@XmlElement(required=true)
	private LinkedList<String> verso = new LinkedList<>();
	
	
	public String getFecha() {
		return fecha;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public LinkedList<String> getVerso() {
		return verso;
	}
	
	public void setVerso(LinkedList<String> verso) {
		this.verso = verso;
	}
}
