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
 * <p>Java class for OtherFundStrategyTypeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="OtherFundStrategyTypeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="OTHR_COMF"/>
 *     &lt;enumeration value="OTHR_EQYF"/>
 *     &lt;enumeration value="OTHR_FXIF"/>
 *     &lt;enumeration value="OTHR_INFF"/>
 *     &lt;enumeration value="OTHR_OTHF"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "OtherFundStrategyTypeType")
@XmlEnum
public enum OtherFundStrategyTypeType {

    OTHR_COMF,
    OTHR_EQYF,
    OTHR_FXIF,
    OTHR_INFF,
    OTHR_OTHF;

    public String value() {
        return name();
    }

    public static OtherFundStrategyTypeType fromValue(String v) {
        return valueOf(v);
    }

}
