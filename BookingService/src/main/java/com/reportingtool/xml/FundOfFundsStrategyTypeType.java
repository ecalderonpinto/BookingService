//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-520 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.19 at 04:23:50 PM CET 
//


package com.reportingtool.xml;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FundOfFundsStrategyTypeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FundOfFundsStrategyTypeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="FOFS_FHFS"/>
 *     &lt;enumeration value="FOFS_PRIV"/>
 *     &lt;enumeration value="OTHR_FOFS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FundOfFundsStrategyTypeType")
@XmlEnum
public enum FundOfFundsStrategyTypeType {

    FOFS_FHFS,
    FOFS_PRIV,
    OTHR_FOFS;

    public String value() {
        return name();
    }

    public static FundOfFundsStrategyTypeType fromValue(String v) {
        return valueOf(v);
    }

}
