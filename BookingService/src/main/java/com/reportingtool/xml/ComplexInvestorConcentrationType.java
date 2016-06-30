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
 * <p>Java class for ComplexInvestorConcentrationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComplexInvestorConcentrationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MainBeneficialOwnersRate" type="{}UnsignedPercentType"/>
 *         &lt;element name="ProfessionalInvestorConcentrationRate" type="{}UnsignedPercentType"/>
 *         &lt;element name="RetailInvestorConcentrationRate" type="{}UnsignedPercentType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplexInvestorConcentrationType", propOrder = {
    "mainBeneficialOwnersRate",
    "professionalInvestorConcentrationRate",
    "retailInvestorConcentrationRate"
})
public class ComplexInvestorConcentrationType {

    @XmlElement(name = "MainBeneficialOwnersRate", required = true)
    protected BigDecimal mainBeneficialOwnersRate;
    @XmlElement(name = "ProfessionalInvestorConcentrationRate", required = true)
    protected BigDecimal professionalInvestorConcentrationRate;
    @XmlElement(name = "RetailInvestorConcentrationRate", required = true)
    protected BigDecimal retailInvestorConcentrationRate;

    /**
     * Gets the value of the mainBeneficialOwnersRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMainBeneficialOwnersRate() {
        return mainBeneficialOwnersRate;
    }

    /**
     * Sets the value of the mainBeneficialOwnersRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMainBeneficialOwnersRate(BigDecimal value) {
        this.mainBeneficialOwnersRate = value;
    }

    /**
     * Gets the value of the professionalInvestorConcentrationRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getProfessionalInvestorConcentrationRate() {
        return professionalInvestorConcentrationRate;
    }

    /**
     * Sets the value of the professionalInvestorConcentrationRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setProfessionalInvestorConcentrationRate(BigDecimal value) {
        this.professionalInvestorConcentrationRate = value;
    }

    /**
     * Gets the value of the retailInvestorConcentrationRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRetailInvestorConcentrationRate() {
        return retailInvestorConcentrationRate;
    }

    /**
     * Sets the value of the retailInvestorConcentrationRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRetailInvestorConcentrationRate(BigDecimal value) {
        this.retailInvestorConcentrationRate = value;
    }

}