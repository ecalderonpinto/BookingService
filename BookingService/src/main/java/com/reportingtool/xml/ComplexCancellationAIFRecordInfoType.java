//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-520 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.19 at 04:23:36 PM CET 
//


package com.reportingtool.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ComplexCancellationAIFRecordInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComplexCancellationAIFRecordInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CancelledAIFNationalCode" type="{}AIFNationalCodeType"/>
 *         &lt;element name="CancelledAIFMNationalCode" type="{}AIFMNationalCodeType"/>
 *         &lt;element name="CancelledReportingPeriodType" type="{}ReportingPeriodTypeType"/>
 *         &lt;element name="CancelledReportingPeriodYear" type="{http://www.w3.org/2001/XMLSchema}gYear"/>
 *         &lt;element name="CancelledRecordFlag" type="{}CancelledRecordFlagType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplexCancellationAIFRecordInfoType", propOrder = {
    "cancelledAIFNationalCode",
    "cancelledAIFMNationalCode",
    "cancelledReportingPeriodType",
    "cancelledReportingPeriodYear",
    "cancelledRecordFlag"
})
public class ComplexCancellationAIFRecordInfoType {

    @XmlElement(name = "CancelledAIFNationalCode", required = true)
    protected String cancelledAIFNationalCode;
    @XmlElement(name = "CancelledAIFMNationalCode", required = true)
    protected String cancelledAIFMNationalCode;
    @XmlElement(name = "CancelledReportingPeriodType", required = true)
    protected ReportingPeriodTypeType cancelledReportingPeriodType;
    @XmlElement(name = "CancelledReportingPeriodYear", required = true)
    @XmlSchemaType(name = "gYear")
    protected XMLGregorianCalendar cancelledReportingPeriodYear;
    @XmlElement(name = "CancelledRecordFlag", required = true)
    protected CancelledRecordFlagType cancelledRecordFlag;

    /**
     * Gets the value of the cancelledAIFNationalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCancelledAIFNationalCode() {
        return cancelledAIFNationalCode;
    }

    /**
     * Sets the value of the cancelledAIFNationalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCancelledAIFNationalCode(String value) {
        this.cancelledAIFNationalCode = value;
    }

    /**
     * Gets the value of the cancelledAIFMNationalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCancelledAIFMNationalCode() {
        return cancelledAIFMNationalCode;
    }

    /**
     * Sets the value of the cancelledAIFMNationalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCancelledAIFMNationalCode(String value) {
        this.cancelledAIFMNationalCode = value;
    }

    /**
     * Gets the value of the cancelledReportingPeriodType property.
     * 
     * @return
     *     possible object is
     *     {@link ReportingPeriodTypeType }
     *     
     */
    public ReportingPeriodTypeType getCancelledReportingPeriodType() {
        return cancelledReportingPeriodType;
    }

    /**
     * Sets the value of the cancelledReportingPeriodType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReportingPeriodTypeType }
     *     
     */
    public void setCancelledReportingPeriodType(ReportingPeriodTypeType value) {
        this.cancelledReportingPeriodType = value;
    }

    /**
     * Gets the value of the cancelledReportingPeriodYear property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCancelledReportingPeriodYear() {
        return cancelledReportingPeriodYear;
    }

    /**
     * Sets the value of the cancelledReportingPeriodYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCancelledReportingPeriodYear(XMLGregorianCalendar value) {
        this.cancelledReportingPeriodYear = value;
    }

    /**
     * Gets the value of the cancelledRecordFlag property.
     * 
     * @return
     *     possible object is
     *     {@link CancelledRecordFlagType }
     *     
     */
    public CancelledRecordFlagType getCancelledRecordFlag() {
        return cancelledRecordFlag;
    }

    /**
     * Sets the value of the cancelledRecordFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link CancelledRecordFlagType }
     *     
     */
    public void setCancelledRecordFlag(CancelledRecordFlagType value) {
        this.cancelledRecordFlag = value;
    }

}
