//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.03.17 a las 10:23:12 AM CET 
//


package es.um.acta;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="calificacion" type="{http://www.um.es/acta}tipoCalificacion" maxOccurs="unbounded"/&gt;
 *         &lt;element name="diligencia" type="{http://www.um.es/acta}tipoDiligencia" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="asignatura" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" /&gt;
 *       &lt;attribute name="convocatoria" use="required"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;enumeration value="febrero"/&gt;
 *             &lt;enumeration value="junio"/&gt;
 *             &lt;enumeration value="julio"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="curso" type="{http://www.w3.org/2001/XMLSchema}gYear" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "calificacion",
    "diligencia"
})
@XmlRootElement(name = "acta")
public class Acta {

    @XmlElement(required = true)
    protected List<TipoCalificacion> calificacion;
    protected List<TipoDiligencia> diligencia;
    @XmlAttribute(name = "asignatura", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String asignatura;
    @XmlAttribute(name = "convocatoria", required = true)
    protected String convocatoria;
    @XmlAttribute(name = "curso")
    @XmlSchemaType(name = "gYear")
    protected XMLGregorianCalendar curso;

    /**
     * Gets the value of the calificacion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the calificacion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCalificacion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TipoCalificacion }
     * 
     * 
     */
    public List<TipoCalificacion> getCalificacion() {
        if (calificacion == null) {
            calificacion = new ArrayList<TipoCalificacion>();
        }
        return this.calificacion;
    }

    /**
     * Gets the value of the diligencia property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the diligencia property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDiligencia().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TipoDiligencia }
     * 
     * 
     */
    public List<TipoDiligencia> getDiligencia() {
        if (diligencia == null) {
            diligencia = new ArrayList<TipoDiligencia>();
        }
        return this.diligencia;
    }

    /**
     * Obtiene el valor de la propiedad asignatura.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsignatura() {
        return asignatura;
    }

    /**
     * Define el valor de la propiedad asignatura.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsignatura(String value) {
        this.asignatura = value;
    }

    /**
     * Obtiene el valor de la propiedad convocatoria.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConvocatoria() {
        return convocatoria;
    }

    /**
     * Define el valor de la propiedad convocatoria.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConvocatoria(String value) {
        this.convocatoria = value;
    }

    /**
     * Obtiene el valor de la propiedad curso.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCurso() {
        return curso;
    }

    /**
     * Define el valor de la propiedad curso.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCurso(XMLGregorianCalendar value) {
        this.curso = value;
    }

}
