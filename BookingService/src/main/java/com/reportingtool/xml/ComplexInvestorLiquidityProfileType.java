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
 * <p>Java class for ComplexInvestorLiquidityProfileType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComplexInvestorLiquidityProfileType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InvestorLiquidityInDays0to1Rate" type="{}UnsignedDecimal15p4Type"/>
 *         &lt;element name="InvestorLiquidityInDays2to7Rate" type="{}UnsignedDecimal15p4Type"/>
 *         &lt;element name="InvestorLiquidityInDays8to30Rate" type="{}UnsignedDecimal15p4Type"/>
 *         &lt;element name="InvestorLiquidityInDays31to90Rate" type="{}UnsignedDecimal15p4Type"/>
 *         &lt;element name="InvestorLiquidityInDays91to180Rate" type="{}UnsignedDecimal15p4Type"/>
 *         &lt;element name="InvestorLiquidityInDays181to365Rate" type="{}UnsignedDecimal15p4Type"/>
 *         &lt;element name="InvestorLiquidityInDays365MoreRate" type="{}UnsignedDecimal15p4Type"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplexInvestorLiquidityProfileType", propOrder = {
    "investorLiquidityInDays0To1Rate",
    "investorLiquidityInDays2To7Rate",
    "investorLiquidityInDays8To30Rate",
    "investorLiquidityInDays31To90Rate",
    "investorLiquidityInDays91To180Rate",
    "investorLiquidityInDays181To365Rate",
    "investorLiquidityInDays365MoreRate"
})
public class ComplexInvestorLiquidityProfileType {

    @XmlElement(name = "InvestorLiquidityInDays0to1Rate", required = true)
    protected BigDecimal investorLiquidityInDays0To1Rate;
    @XmlElement(name = "InvestorLiquidityInDays2to7Rate", required = true)
    protected BigDecimal investorLiquidityInDays2To7Rate;
    @XmlElement(name = "InvestorLiquidityInDays8to30Rate", required = true)
    protected BigDecimal investorLiquidityInDays8To30Rate;
    @XmlElement(name = "InvestorLiquidityInDays31to90Rate", required = true)
    protected BigDecimal investorLiquidityInDays31To90Rate;
    @XmlElement(name = "InvestorLiquidityInDays91to180Rate", required = true)
    protected BigDecimal investorLiquidityInDays91To180Rate;
    @XmlElement(name = "InvestorLiquidityInDays181to365Rate", required = true)
    protected BigDecimal investorLiquidityInDays181To365Rate;
    @XmlElement(name = "InvestorLiquidityInDays365MoreRate", required = true)
    protected BigDecimal investorLiquidityInDays365MoreRate;

    /**
     * Gets the value of the investorLiquidityInDays0To1Rate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInvestorLiquidityInDays0To1Rate() {
        return investorLiquidityInDays0To1Rate;
    }

    /**
     * Sets the value of the investorLiquidityInDays0To1Rate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInvestorLiquidityInDays0To1Rate(BigDecimal value) {
        this.investorLiquidityInDays0To1Rate = value;
    }

    /**
     * Gets the value of the investorLiquidityInDays2To7Rate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInvestorLiquidityInDays2To7Rate() {
        return investorLiquidityInDays2To7Rate;
    }

    /**
     * Sets the value of the investorLiquidityInDays2To7Rate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInvestorLiquidityInDays2To7Rate(BigDecimal value) {
        this.investorLiquidityInDays2To7Rate = value;
    }

    /**
     * Gets the value of the investorLiquidityInDays8To30Rate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInvestorLiquidityInDays8To30Rate() {
        return investorLiquidityInDays8To30Rate;
    }

    /**
     * Sets the value of the investorLiquidityInDays8To30Rate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInvestorLiquidityInDays8To30Rate(BigDecimal value) {
        this.investorLiquidityInDays8To30Rate = value;
    }

    /**
     * Gets the value of the investorLiquidityInDays31To90Rate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInvestorLiquidityInDays31To90Rate() {
        return investorLiquidityInDays31To90Rate;
    }

    /**
     * Sets the value of the investorLiquidityInDays31To90Rate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInvestorLiquidityInDays31To90Rate(BigDecimal value) {
        this.investorLiquidityInDays31To90Rate = value;
    }

    /**
     * Gets the value of the investorLiquidityInDays91To180Rate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInvestorLiquidityInDays91To180Rate() {
        return investorLiquidityInDays91To180Rate;
    }

    /**
     * Sets the value of the investorLiquidityInDays91To180Rate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInvestorLiquidityInDays91To180Rate(BigDecimal value) {
        this.investorLiquidityInDays91To180Rate = value;
    }

    /**
     * Gets the value of the investorLiquidityInDays181To365Rate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInvestorLiquidityInDays181To365Rate() {
        return investorLiquidityInDays181To365Rate;
    }

    /**
     * Sets the value of the investorLiquidityInDays181To365Rate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInvestorLiquidityInDays181To365Rate(BigDecimal value) {
        this.investorLiquidityInDays181To365Rate = value;
    }

    /**
     * Gets the value of the investorLiquidityInDays365MoreRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInvestorLiquidityInDays365MoreRate() {
        return investorLiquidityInDays365MoreRate;
    }

    /**
     * Sets the value of the investorLiquidityInDays365MoreRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInvestorLiquidityInDays365MoreRate(BigDecimal value) {
        this.investorLiquidityInDays365MoreRate = value;
    }

}
