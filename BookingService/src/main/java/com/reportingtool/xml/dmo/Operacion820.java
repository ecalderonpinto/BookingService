//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-520 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.13 at 01:15:20 PM CET 
//


package com.reportingtool.xml.dmo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Operacion" type="{http://www.sepblac.es/DMO}CABECERAOP"/>
 *         &lt;element name="Intervinientes">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PersonaFisica" type="{http://www.sepblac.es/DMO}PERSONAFIS" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="Sociedad" type="{http://www.sepblac.es/DMO}PERSONAJUR" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "operacion",
    "intervinientes"
})
@XmlRootElement(name = "Operacion8_20")
public class Operacion820 {

    @XmlElement(name = "Operacion", required = true)
    protected CABECERAOP operacion;
    @XmlElement(name = "Intervinientes", required = true)
    protected Operacion820 .Intervinientes intervinientes;

    /**
     * Gets the value of the operacion property.
     * 
     * @return
     *     possible object is
     *     {@link CABECERAOP }
     *     
     */
    public CABECERAOP getOperacion() {
        return operacion;
    }

    /**
     * Sets the value of the operacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link CABECERAOP }
     *     
     */
    public void setOperacion(CABECERAOP value) {
        this.operacion = value;
    }

    /**
     * Gets the value of the intervinientes property.
     * 
     * @return
     *     possible object is
     *     {@link Operacion820 .Intervinientes }
     *     
     */
    public Operacion820 .Intervinientes getIntervinientes() {
        return intervinientes;
    }

    /**
     * Sets the value of the intervinientes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Operacion820 .Intervinientes }
     *     
     */
    public void setIntervinientes(Operacion820 .Intervinientes value) {
        this.intervinientes = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="PersonaFisica" type="{http://www.sepblac.es/DMO}PERSONAFIS" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="Sociedad" type="{http://www.sepblac.es/DMO}PERSONAJUR" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "personaFisica",
        "sociedad"
    })
    public static class Intervinientes {

        @XmlElement(name = "PersonaFisica")
        protected List<PERSONAFIS> personaFisica;
        @XmlElement(name = "Sociedad")
        protected List<PERSONAJUR> sociedad;

        /**
         * Gets the value of the personaFisica property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the personaFisica property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPersonaFisica().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PERSONAFIS }
         * 
         * 
         */
        public List<PERSONAFIS> getPersonaFisica() {
            if (personaFisica == null) {
                personaFisica = new ArrayList<PERSONAFIS>();
            }
            return this.personaFisica;
        }

        /**
         * Gets the value of the sociedad property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the sociedad property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSociedad().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PERSONAJUR }
         * 
         * 
         */
        public List<PERSONAJUR> getSociedad() {
            if (sociedad == null) {
                sociedad = new ArrayList<PERSONAJUR>();
            }
            return this.sociedad;
        }

    }

}