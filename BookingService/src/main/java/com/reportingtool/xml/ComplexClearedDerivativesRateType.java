//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-520 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.19 at 04:23:50 PM CET 
//


package com.reportingtool.xml;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ComplexClearedDerivativesRateType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComplexClearedDerivativesRateType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CCPRate" type="{}UnsignedPercentType" minOccurs="0"/>
 *         &lt;element name="BilateralClearingRate" type="{}UnsignedPercentType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplexClearedDerivativesRateType", propOrder = {
    "ccpRate",
    "bilateralClearingRate"
})
public class ComplexClearedDerivativesRateType {

    @XmlElement(name = "CCPRate")
    protected BigDecimal ccpRate;
    @XmlElement(name = "BilateralClearingRate")
    protected BigDecimal bilateralClearingRate;

    /**
     * Gets the value of the ccpRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCCPRate() {
        return ccpRate;
    }

    /**
     * Sets the value of the ccpRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCCPRate(BigDecimal value) {
        this.ccpRate = value;
    }

    /**
     * Gets the value of the bilateralClearingRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBilateralClearingRate() {
        return bilateralClearingRate;
    }

    /**
     * Sets the value of the bilateralClearingRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBilateralClearingRate(BigDecimal value) {
        this.bilateralClearingRate = value;
    }

}
