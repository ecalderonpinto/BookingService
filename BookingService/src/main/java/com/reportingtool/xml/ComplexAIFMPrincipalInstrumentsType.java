//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-520 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.19 at 04:23:50 PM CET 
//


package com.reportingtool.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ComplexAIFMPrincipalInstrumentsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComplexAIFMPrincipalInstrumentsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AIFMPrincipalInstrument" type="{}ComplexPrincipalInstrumentType" maxOccurs="5" minOccurs="5"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplexAIFMPrincipalInstrumentsType", propOrder = {
    "aifmPrincipalInstrument"
})
public class ComplexAIFMPrincipalInstrumentsType {

    @XmlElement(name = "AIFMPrincipalInstrument", required = true)
    protected List<ComplexPrincipalInstrumentType> aifmPrincipalInstrument;

    /**
     * Gets the value of the aifmPrincipalInstrument property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aifmPrincipalInstrument property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAIFMPrincipalInstrument().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComplexPrincipalInstrumentType }
     * 
     * 
     */
    public List<ComplexPrincipalInstrumentType> getAIFMPrincipalInstrument() {
        if (aifmPrincipalInstrument == null) {
            aifmPrincipalInstrument = new ArrayList<ComplexPrincipalInstrumentType>();
        }
        return this.aifmPrincipalInstrument;
    }

}
