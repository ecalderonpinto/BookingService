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
 * Tipo de dato Provincia 40 (provincia + coleccion de municipios)
 * 
 * <p>Java class for PROV40 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PROV40">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CDPROVINCIA" type="{http://www.sepblac.es/DMO}PROV_ENUM"/>
 *         &lt;element name="MUNICIPIOS" type="{http://www.sepblac.es/DMO}DSMUNICIP40"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PROV40", propOrder = {
    "cdprovincia",
    "municipios"
})
public class PROV40 {

    @XmlElement(name = "CDPROVINCIA")
    protected int cdprovincia;
    @XmlElement(name = "MUNICIPIOS", required = true)
    protected String municipios;

    /**
     * Gets the value of the cdprovincia property.
     * 
     */
    public int getCDPROVINCIA() {
        return cdprovincia;
    }

    /**
     * Sets the value of the cdprovincia property.
     * 
     */
    public void setCDPROVINCIA(int value) {
        this.cdprovincia = value;
    }

    /**
     * Gets the value of the municipios property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMUNICIPIOS() {
        return municipios;
    }

    /**
     * Sets the value of the municipios property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMUNICIPIOS(String value) {
        this.municipios = value;
    }

}
