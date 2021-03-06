//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-520 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.13 at 01:16:38 PM CET 
//


package com.reportingtool.xml.dmo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Persona Juridica (Sociedad) gen�rica
 * 
 * <p>Java class for PERSONAJUR complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PERSONAJUR">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CDPERSONA">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="RazonSocial" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="80"/>
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="PaisNacionalidad" type="{http://www.sepblac.es/DMO}PAIS_ENUM" minOccurs="0"/>
 *         &lt;element name="DocIdentifsPJ">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="DocIdentifPJ" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;extension base="{http://www.sepblac.es/DMO}DOCIDENTIF_PJ">
 *                         &lt;/extension>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="DomiciliosPJ">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="DomicilioPJ" type="{http://www.sepblac.es/DMO}DOMICILIO" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Telefonos">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Telefono" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;extension base="{http://www.sepblac.es/DMO}TELEFONO">
 *                         &lt;/extension>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Relacion" type="{http://www.sepblac.es/DMO}REL_PER_CUENTA_ENUM" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PERSONAJUR", propOrder = {
    "cdpersona",
    "razonSocial",
    "paisNacionalidad",
    "docIdentifsPJ",
    "domiciliosPJ",
    "telefonos",
    "relacion"
})
public class PERSONAJUR {

    @XmlElement(name = "CDPERSONA", required = true)
    protected String cdpersona;
    @XmlElement(name = "RazonSocial")
    protected String razonSocial;
    @XmlElement(name = "PaisNacionalidad")
    protected String paisNacionalidad;
    @XmlElement(name = "DocIdentifsPJ", required = true)
    protected PERSONAJUR.DocIdentifsPJ docIdentifsPJ;
    @XmlElement(name = "DomiciliosPJ", required = true)
    protected PERSONAJUR.DomiciliosPJ domiciliosPJ;
    @XmlElement(name = "Telefonos", required = true)
    protected PERSONAJUR.Telefonos telefonos;
    @XmlElement(name = "Relacion")
    protected Integer relacion;

    /**
     * Gets the value of the cdpersona property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCDPERSONA() {
        return cdpersona;
    }

    /**
     * Sets the value of the cdpersona property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCDPERSONA(String value) {
        this.cdpersona = value;
    }

    /**
     * Gets the value of the razonSocial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * Sets the value of the razonSocial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazonSocial(String value) {
        this.razonSocial = value;
    }

    /**
     * Gets the value of the paisNacionalidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaisNacionalidad() {
        return paisNacionalidad;
    }

    /**
     * Sets the value of the paisNacionalidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaisNacionalidad(String value) {
        this.paisNacionalidad = value;
    }

    /**
     * Gets the value of the docIdentifsPJ property.
     * 
     * @return
     *     possible object is
     *     {@link PERSONAJUR.DocIdentifsPJ }
     *     
     */
    public PERSONAJUR.DocIdentifsPJ getDocIdentifsPJ() {
        return docIdentifsPJ;
    }

    /**
     * Sets the value of the docIdentifsPJ property.
     * 
     * @param value
     *     allowed object is
     *     {@link PERSONAJUR.DocIdentifsPJ }
     *     
     */
    public void setDocIdentifsPJ(PERSONAJUR.DocIdentifsPJ value) {
        this.docIdentifsPJ = value;
    }

    /**
     * Gets the value of the domiciliosPJ property.
     * 
     * @return
     *     possible object is
     *     {@link PERSONAJUR.DomiciliosPJ }
     *     
     */
    public PERSONAJUR.DomiciliosPJ getDomiciliosPJ() {
        return domiciliosPJ;
    }

    /**
     * Sets the value of the domiciliosPJ property.
     * 
     * @param value
     *     allowed object is
     *     {@link PERSONAJUR.DomiciliosPJ }
     *     
     */
    public void setDomiciliosPJ(PERSONAJUR.DomiciliosPJ value) {
        this.domiciliosPJ = value;
    }

    /**
     * Gets the value of the telefonos property.
     * 
     * @return
     *     possible object is
     *     {@link PERSONAJUR.Telefonos }
     *     
     */
    public PERSONAJUR.Telefonos getTelefonos() {
        return telefonos;
    }

    /**
     * Sets the value of the telefonos property.
     * 
     * @param value
     *     allowed object is
     *     {@link PERSONAJUR.Telefonos }
     *     
     */
    public void setTelefonos(PERSONAJUR.Telefonos value) {
        this.telefonos = value;
    }

    /**
     * Gets the value of the relacion property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRelacion() {
        return relacion;
    }

    /**
     * Sets the value of the relacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRelacion(Integer value) {
        this.relacion = value;
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
     *         &lt;element name="DocIdentifPJ" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;extension base="{http://www.sepblac.es/DMO}DOCIDENTIF_PJ">
     *               &lt;/extension>
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
        "docIdentifPJ"
    })
    public static class DocIdentifsPJ {

        @XmlElement(name = "DocIdentifPJ")
        protected List<PERSONAJUR.DocIdentifsPJ.DocIdentifPJ> docIdentifPJ;

        /**
         * Gets the value of the docIdentifPJ property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the docIdentifPJ property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDocIdentifPJ().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PERSONAJUR.DocIdentifsPJ.DocIdentifPJ }
         * 
         * 
         */
        public List<PERSONAJUR.DocIdentifsPJ.DocIdentifPJ> getDocIdentifPJ() {
            if (docIdentifPJ == null) {
                docIdentifPJ = new ArrayList<PERSONAJUR.DocIdentifsPJ.DocIdentifPJ>();
            }
            return this.docIdentifPJ;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;extension base="{http://www.sepblac.es/DMO}DOCIDENTIF_PJ">
         *     &lt;/extension>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class DocIdentifPJ
            extends DOCIDENTIFPJ
        {


        }

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
     *         &lt;element name="DomicilioPJ" type="{http://www.sepblac.es/DMO}DOMICILIO" maxOccurs="unbounded" minOccurs="0"/>
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
        "domicilioPJ"
    })
    public static class DomiciliosPJ {

        @XmlElement(name = "DomicilioPJ")
        protected List<DOMICILIO> domicilioPJ;

        /**
         * Gets the value of the domicilioPJ property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the domicilioPJ property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDomicilioPJ().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DOMICILIO }
         * 
         * 
         */
        public List<DOMICILIO> getDomicilioPJ() {
            if (domicilioPJ == null) {
                domicilioPJ = new ArrayList<DOMICILIO>();
            }
            return this.domicilioPJ;
        }

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
     *         &lt;element name="Telefono" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;extension base="{http://www.sepblac.es/DMO}TELEFONO">
     *               &lt;/extension>
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
        "telefono"
    })
    public static class Telefonos {

        @XmlElement(name = "Telefono")
        protected List<PERSONAJUR.Telefonos.Telefono> telefono;

        /**
         * Gets the value of the telefono property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the telefono property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTelefono().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PERSONAJUR.Telefonos.Telefono }
         * 
         * 
         */
        public List<PERSONAJUR.Telefonos.Telefono> getTelefono() {
            if (telefono == null) {
                telefono = new ArrayList<PERSONAJUR.Telefonos.Telefono>();
            }
            return this.telefono;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;extension base="{http://www.sepblac.es/DMO}TELEFONO">
         *     &lt;/extension>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Telefono
            extends TELEFONO
        {


        }

    }

}
