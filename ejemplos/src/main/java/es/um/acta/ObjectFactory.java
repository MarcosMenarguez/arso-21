//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.03.15 a las 09:19:58 AM CET 
//


package es.um.acta;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.um.acta package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.um.acta
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Acta }
     * 
     */
    public Acta createActa() {
        return new Acta();
    }

    /**
     * Create an instance of {@link TipoCalificacion }
     * 
     */
    public TipoCalificacion createTipoCalificacion() {
        return new TipoCalificacion();
    }

    /**
     * Create an instance of {@link TipoDiligencia }
     * 
     */
    public TipoDiligencia createTipoDiligencia() {
        return new TipoDiligencia();
    }

}
