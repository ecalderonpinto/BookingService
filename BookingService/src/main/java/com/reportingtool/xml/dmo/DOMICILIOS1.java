//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-520 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.13 at 01:16:38 PM CET 
//


package com.reportingtool.xml.dmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Clase de domicilio para operaciones S1 (Sin apartado de correos)
 * 
 * <p>Java class for DOMICILIO_S1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DOMICILIO_S1">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TipoVia" type="{http://www.sepblac.es/DMO}TIPVIA_ENUM" minOccurs="0"/>
 *         &lt;element name="NombreVia">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="150"/>
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="NumeroVia" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="5"/>
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="OtrosDatos" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Pais">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.sepblac.es/DMO}PAIS_ENUM">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="CodPostal">
 *             &lt;simpleType>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                 &lt;length value="5"/>
 *               &lt;/restriction>
 *             &lt;/simpleType>
 *           &lt;/element>
 *           &lt;element name="CodPostalExtrajero">
 *             &lt;simpleType>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                 &lt;maxLength value="8"/>
 *                 &lt;minLength value="1"/>
 *               &lt;/restriction>
 *             &lt;/simpleType>
 *           &lt;/element>
 *         &lt;/choice>
 *         &lt;element name="Localidad">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
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
@XmlType(name = "DOMICILIO_S1", propOrder = {
    "tipoVia",
    "nombreVia",
    "numeroVia",
    "otrosDatos",
    "pais",
    "codPostal",
    "codPostalExtrajero",
    "localidad"
})
public class DOMICILIOS1 {

    @XmlElement(name = "TipoVia")
    protected Integer tipoVia;
    @XmlElement(name = "NombreVia", required = true)
    protected String nombreVia;
    @XmlElement(name = "NumeroVia")
    protected String numeroVia;
    @XmlElement(name = "OtrosDatos")
    protected String otrosDatos;
    @XmlElement(name = "Pais", required = true)
    protected String pais;
    @XmlElement(name = "CodPostal")
    protected String codPostal;
    @XmlElement(name = "CodPostalExtrajero")
    protected String codPostalExtrajero;
    @XmlElement(name = "Localidad", required = true)
    protected String localidad;

    /**
     * Gets the value of the tipoVia property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTipoVia() {
        return tipoVia;
    }

    /**
     * Sets the value of the tipoVia property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTipoVia(Integer value) {
        this.tipoVia = value;
    }

    /**
     * Gets the value of the nombreVia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreVia() {
        return nombreVia;
    }

    /**
     * Sets the value of the nombreVia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreVia(String value) {
        this.nombreVia = value;
    }

    /**
     * Gets the value of the numeroVia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroVia() {
        return numeroVia;
    }

    /**
     * Sets the value of the numeroVia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroVia(String value) {
        this.numeroVia = value;
    }

    /**
     * Gets the value of the otrosDatos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtrosDatos() {
        return otrosDatos;
    }

    /**
     * Sets the value of the otrosDatos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtrosDatos(String value) {
        this.otrosDatos = value;
    }

    /**
     * Gets the value of the pais property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPais() {
        return pais;
    }

    /**
     * Sets the value of the pais property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPais(String value) {
        this.pais = value;
    }

    /**
     * Gets the value of the codPostal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodPostal() {
        return codPostal;
    }

    /**
     * Sets the value of the codPostal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodPostal(String value) {
        this.codPostal = value;
    }

    /**
     * Gets the value of the codPostalExtrajero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodPostalExtrajero() {
        return codPostalExtrajero;
    }

    /**
     * Sets the value of the codPostalExtrajero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodPostalExtrajero(String value) {
        this.codPostalExtrajero = value;
    }

    /**
     * Gets the value of the localidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * Sets the value of the localidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalidad(String value) {
        this.localidad = value;
    }

}
