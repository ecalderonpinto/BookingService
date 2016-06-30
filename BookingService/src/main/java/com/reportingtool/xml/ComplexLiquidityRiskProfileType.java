//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-520 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.19 at 04:23:50 PM CET 
//


package com.reportingtool.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ComplexLiquidityRiskProfileType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComplexLiquidityRiskProfileType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PortfolioLiquidityProfile" type="{}ComplexPortfolioLiquidityProfileType" minOccurs="0"/>
 *         &lt;element name="InvestorLiquidityProfile" type="{}ComplexInvestorLiquidityProfileType" minOccurs="0"/>
 *         &lt;element name="InvestorRedemption" type="{}ComplexInvestorRedemptionType" minOccurs="0"/>
 *         &lt;element name="InvestorArrangement" type="{}ComplexInvestorArrangementType" minOccurs="0"/>
 *         &lt;element name="InvestorGroups" type="{}ComplexInvestorGroupsType"/>
 *         &lt;element name="FinancingLiquidityProfile" type="{}ComplexFinancingLiquidityProfileType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplexLiquidityRiskProfileType", propOrder = {
    "portfolioLiquidityProfile",
    "investorLiquidityProfile",
    "investorRedemption",
    "investorArrangement",
    "investorGroups",
    "financingLiquidityProfile"
})
public class ComplexLiquidityRiskProfileType {

    @XmlElement(name = "PortfolioLiquidityProfile")
    protected ComplexPortfolioLiquidityProfileType portfolioLiquidityProfile;
    @XmlElement(name = "InvestorLiquidityProfile")
    protected ComplexInvestorLiquidityProfileType investorLiquidityProfile;
    @XmlElement(name = "InvestorRedemption")
    protected ComplexInvestorRedemptionType investorRedemption;
    @XmlElement(name = "InvestorArrangement")
    protected ComplexInvestorArrangementType investorArrangement;
    @XmlElement(name = "InvestorGroups", required = true)
    protected ComplexInvestorGroupsType investorGroups;
    @XmlElement(name = "FinancingLiquidityProfile")
    protected ComplexFinancingLiquidityProfileType financingLiquidityProfile;

    /**
     * Gets the value of the portfolioLiquidityProfile property.
     * 
     * @return
     *     possible object is
     *     {@link ComplexPortfolioLiquidityProfileType }
     *     
     */
    public ComplexPortfolioLiquidityProfileType getPortfolioLiquidityProfile() {
        return portfolioLiquidityProfile;
    }

    /**
     * Sets the value of the portfolioLiquidityProfile property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexPortfolioLiquidityProfileType }
     *     
     */
    public void setPortfolioLiquidityProfile(ComplexPortfolioLiquidityProfileType value) {
        this.portfolioLiquidityProfile = value;
    }

    /**
     * Gets the value of the investorLiquidityProfile property.
     * 
     * @return
     *     possible object is
     *     {@link ComplexInvestorLiquidityProfileType }
     *     
     */
    public ComplexInvestorLiquidityProfileType getInvestorLiquidityProfile() {
        return investorLiquidityProfile;
    }

    /**
     * Sets the value of the investorLiquidityProfile property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexInvestorLiquidityProfileType }
     *     
     */
    public void setInvestorLiquidityProfile(ComplexInvestorLiquidityProfileType value) {
        this.investorLiquidityProfile = value;
    }

    /**
     * Gets the value of the investorRedemption property.
     * 
     * @return
     *     possible object is
     *     {@link ComplexInvestorRedemptionType }
     *     
     */
    public ComplexInvestorRedemptionType getInvestorRedemption() {
        return investorRedemption;
    }

    /**
     * Sets the value of the investorRedemption property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexInvestorRedemptionType }
     *     
     */
    public void setInvestorRedemption(ComplexInvestorRedemptionType value) {
        this.investorRedemption = value;
    }

    /**
     * Gets the value of the investorArrangement property.
     * 
     * @return
     *     possible object is
     *     {@link ComplexInvestorArrangementType }
     *     
     */
    public ComplexInvestorArrangementType getInvestorArrangement() {
        return investorArrangement;
    }

    /**
     * Sets the value of the investorArrangement property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexInvestorArrangementType }
     *     
     */
    public void setInvestorArrangement(ComplexInvestorArrangementType value) {
        this.investorArrangement = value;
    }

    /**
     * Gets the value of the investorGroups property.
     * 
     * @return
     *     possible object is
     *     {@link ComplexInvestorGroupsType }
     *     
     */
    public ComplexInvestorGroupsType getInvestorGroups() {
        return investorGroups;
    }

    /**
     * Sets the value of the investorGroups property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexInvestorGroupsType }
     *     
     */
    public void setInvestorGroups(ComplexInvestorGroupsType value) {
        this.investorGroups = value;
    }

    /**
     * Gets the value of the financingLiquidityProfile property.
     * 
     * @return
     *     possible object is
     *     {@link ComplexFinancingLiquidityProfileType }
     *     
     */
    public ComplexFinancingLiquidityProfileType getFinancingLiquidityProfile() {
        return financingLiquidityProfile;
    }

    /**
     * Sets the value of the financingLiquidityProfile property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexFinancingLiquidityProfileType }
     *     
     */
    public void setFinancingLiquidityProfile(ComplexFinancingLiquidityProfileType value) {
        this.financingLiquidityProfile = value;
    }

}
