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
 *         &lt;element ref="{http://www.sepblac.es/DMO}OperacionGeneral" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.sepblac.es/DMO}Operacion8_20" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.sepblac.es/DMO}Operacion23_25" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.sepblac.es/DMO}Operacion26y27" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.sepblac.es/DMO}Operacion28y29" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.sepblac.es/DMO}Operacion30_35" maxOccurs="unbounded" minOccurs="0"/>
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
    "operacionGeneral",
    "operacion820",
    "operacion2325",
    "operacion26Y27",
    "operacion28Y29",
    "operacion3035"
})
@XmlRootElement(name = "OperacionesImport")
public class OperacionesImport {

    @XmlElement(name = "OperacionGeneral", namespace = "http://www.sepblac.es/DMO")
    protected List<OperacionGeneral> operacionGeneral;
    @XmlElement(name = "Operacion8_20", namespace = "http://www.sepblac.es/DMO")
    protected List<Operacion820> operacion820;
    @XmlElement(name = "Operacion23_25", namespace = "http://www.sepblac.es/DMO")
    protected List<Operacion2325> operacion2325;
    @XmlElement(name = "Operacion26y27", namespace = "http://www.sepblac.es/DMO")
    protected List<Operacion26Y27> operacion26Y27;
    @XmlElement(name = "Operacion28y29", namespace = "http://www.sepblac.es/DMO")
    protected List<Operacion28Y29> operacion28Y29;
    @XmlElement(name = "Operacion30_35", namespace = "http://www.sepblac.es/DMO")
    protected List<Operacion3035> operacion3035;

    /**
     * Gets the value of the operacionGeneral property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the operacionGeneral property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOperacionGeneral().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OperacionGeneral }
     * 
     * 
     */
    public List<OperacionGeneral> getOperacionGeneral() {
        if (operacionGeneral == null) {
            operacionGeneral = new ArrayList<OperacionGeneral>();
        }
        return this.operacionGeneral;
    }

    /**
     * Gets the value of the operacion820 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the operacion820 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOperacion820().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Operacion820 }
     * 
     * 
     */
    public List<Operacion820> getOperacion820() {
        if (operacion820 == null) {
            operacion820 = new ArrayList<Operacion820>();
        }
        return this.operacion820;
    }

    /**
     * Gets the value of the operacion2325 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the operacion2325 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOperacion2325().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Operacion2325 }
     * 
     * 
     */
    public List<Operacion2325> getOperacion2325() {
        if (operacion2325 == null) {
            operacion2325 = new ArrayList<Operacion2325>();
        }
        return this.operacion2325;
    }

    /**
     * Gets the value of the operacion26Y27 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the operacion26Y27 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOperacion26Y27().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Operacion26Y27 }
     * 
     * 
     */
    public List<Operacion26Y27> getOperacion26Y27() {
        if (operacion26Y27 == null) {
            operacion26Y27 = new ArrayList<Operacion26Y27>();
        }
        return this.operacion26Y27;
    }

    /**
     * Gets the value of the operacion28Y29 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the operacion28Y29 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOperacion28Y29().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Operacion28Y29 }
     * 
     * 
     */
    public List<Operacion28Y29> getOperacion28Y29() {
        if (operacion28Y29 == null) {
            operacion28Y29 = new ArrayList<Operacion28Y29>();
        }
        return this.operacion28Y29;
    }

    /**
     * Gets the value of the operacion3035 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the operacion3035 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOperacion3035().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Operacion3035 }
     * 
     * 
     */
    public List<Operacion3035> getOperacion3035() {
        if (operacion3035 == null) {
            operacion3035 = new ArrayList<Operacion3035>();
        }
        return this.operacion3035;
    }

}
