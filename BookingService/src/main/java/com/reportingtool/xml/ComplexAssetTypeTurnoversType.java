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
 * <p>Java class for ComplexAssetTypeTurnoversType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComplexAssetTypeTurnoversType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AssetTypeTurnover" type="{}ComplexAssetTypeTurnoverType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplexAssetTypeTurnoversType", propOrder = {
    "assetTypeTurnover"
})
public class ComplexAssetTypeTurnoversType {

    @XmlElement(name = "AssetTypeTurnover", required = true)
    protected List<ComplexAssetTypeTurnoverType> assetTypeTurnover;

    /**
     * Gets the value of the assetTypeTurnover property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assetTypeTurnover property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssetTypeTurnover().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComplexAssetTypeTurnoverType }
     * 
     * 
     */
    public List<ComplexAssetTypeTurnoverType> getAssetTypeTurnover() {
        if (assetTypeTurnover == null) {
            assetTypeTurnover = new ArrayList<ComplexAssetTypeTurnoverType>();
        }
        return this.assetTypeTurnover;
    }

}
