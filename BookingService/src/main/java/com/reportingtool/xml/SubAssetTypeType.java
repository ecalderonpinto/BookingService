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
 * <p>Java class for SubAssetTypeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SubAssetTypeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SEC_CSH_CODP"/>
 *     &lt;enumeration value="SEC_CSH_COMP"/>
 *     &lt;enumeration value="SEC_CSH_OTHD"/>
 *     &lt;enumeration value="SEC_CSH_OTHC"/>
 *     &lt;enumeration value="SEC_LEQ_IFIN"/>
 *     &lt;enumeration value="SEC_LEQ_OTHR"/>
 *     &lt;enumeration value="SEC_UEQ_UEQY"/>
 *     &lt;enumeration value="SEC_CPN_INVG"/>
 *     &lt;enumeration value="SEC_CPN_NIVG"/>
 *     &lt;enumeration value="SEC_CPI_INVG"/>
 *     &lt;enumeration value="SEC_CPI_NIVG"/>
 *     &lt;enumeration value="SEC_SBD_EUBY"/>
 *     &lt;enumeration value="SEC_SBD_EUBM"/>
 *     &lt;enumeration value="SEC_SBD_NOGY"/>
 *     &lt;enumeration value="SEC_SBD_NOGM"/>
 *     &lt;enumeration value="SEC_SBD_EUGY"/>
 *     &lt;enumeration value="SEC_SBD_EUGM"/>
 *     &lt;enumeration value="SEC_MBN_MNPL"/>
 *     &lt;enumeration value="SEC_CBN_INVG"/>
 *     &lt;enumeration value="SEC_CBN_NIVG"/>
 *     &lt;enumeration value="SEC_CBI_INVG"/>
 *     &lt;enumeration value="SEC_CBI_NIVG"/>
 *     &lt;enumeration value="SEC_LON_LEVL"/>
 *     &lt;enumeration value="SEC_LON_OTHL"/>
 *     &lt;enumeration value="SEC_SSP_SABS"/>
 *     &lt;enumeration value="SEC_SSP_RMBS"/>
 *     &lt;enumeration value="SEC_SSP_CMBS"/>
 *     &lt;enumeration value="SEC_SSP_AMBS"/>
 *     &lt;enumeration value="SEC_SSP_ABCP"/>
 *     &lt;enumeration value="SEC_SSP_CDOC"/>
 *     &lt;enumeration value="SEC_SSP_STRC"/>
 *     &lt;enumeration value="SEC_SSP_SETP"/>
 *     &lt;enumeration value="SEC_SSP_OTHS"/>
 *     &lt;enumeration value="DER_EQD_FINI"/>
 *     &lt;enumeration value="DER_EQD_OTHD"/>
 *     &lt;enumeration value="DER_FID_FIXI"/>
 *     &lt;enumeration value="DER_CDS_SNFI"/>
 *     &lt;enumeration value="DER_CDS_SNSO"/>
 *     &lt;enumeration value="DER_CDS_SNOT"/>
 *     &lt;enumeration value="DER_CDS_INDX"/>
 *     &lt;enumeration value="DER_CDS_EXOT"/>
 *     &lt;enumeration value="DER_CDS_OTHR"/>
 *     &lt;enumeration value="DER_FEX_INVT"/>
 *     &lt;enumeration value="DER_FEX_HEDG"/>
 *     &lt;enumeration value="DER_IRD_INTR"/>
 *     &lt;enumeration value="DER_CTY_ECOL"/>
 *     &lt;enumeration value="DER_CTY_ENNG"/>
 *     &lt;enumeration value="DER_CTY_ENPW"/>
 *     &lt;enumeration value="DER_CTY_ENOT"/>
 *     &lt;enumeration value="DER_CTY_PMGD"/>
 *     &lt;enumeration value="DER_CTY_PMOT"/>
 *     &lt;enumeration value="DER_CTY_OTIM"/>
 *     &lt;enumeration value="DER_CTY_OTLS"/>
 *     &lt;enumeration value="DER_CTY_OTAP"/>
 *     &lt;enumeration value="DER_CTY_OTHR"/>
 *     &lt;enumeration value="DER_OTH_OTHR"/>
 *     &lt;enumeration value="PHY_RES_RESL"/>
 *     &lt;enumeration value="PHY_RES_COML"/>
 *     &lt;enumeration value="PHY_RES_OTHR"/>
 *     &lt;enumeration value="PHY_CTY_PCTY"/>
 *     &lt;enumeration value="PHY_TIM_PTIM"/>
 *     &lt;enumeration value="PHY_ART_PART"/>
 *     &lt;enumeration value="PHY_TPT_PTPT"/>
 *     &lt;enumeration value="PHY_OTH_OTHR"/>
 *     &lt;enumeration value="CIU_OAM_MMFC"/>
 *     &lt;enumeration value="CIU_OAM_AETF"/>
 *     &lt;enumeration value="CIU_OAM_OTHR"/>
 *     &lt;enumeration value="CIU_NAM_MMFC"/>
 *     &lt;enumeration value="CIU_NAM_AETF"/>
 *     &lt;enumeration value="CIU_NAM_OTHR"/>
 *     &lt;enumeration value="OTH_OTH_OTHR"/>
 *     &lt;enumeration value="NTA_NTA_NOTA"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SubAssetTypeType")
@XmlEnum
public enum SubAssetTypeType {

    SEC_CSH_CODP,
    SEC_CSH_COMP,
    SEC_CSH_OTHD,
    SEC_CSH_OTHC,
    SEC_LEQ_IFIN,
    SEC_LEQ_OTHR,
    SEC_UEQ_UEQY,
    SEC_CPN_INVG,
    SEC_CPN_NIVG,
    SEC_CPI_INVG,
    SEC_CPI_NIVG,
    SEC_SBD_EUBY,
    SEC_SBD_EUBM,
    SEC_SBD_NOGY,
    SEC_SBD_NOGM,
    SEC_SBD_EUGY,
    SEC_SBD_EUGM,
    SEC_MBN_MNPL,
    SEC_CBN_INVG,
    SEC_CBN_NIVG,
    SEC_CBI_INVG,
    SEC_CBI_NIVG,
    SEC_LON_LEVL,
    SEC_LON_OTHL,
    SEC_SSP_SABS,
    SEC_SSP_RMBS,
    SEC_SSP_CMBS,
    SEC_SSP_AMBS,
    SEC_SSP_ABCP,
    SEC_SSP_CDOC,
    SEC_SSP_STRC,
    SEC_SSP_SETP,
    SEC_SSP_OTHS,
    DER_EQD_FINI,
    DER_EQD_OTHD,
    DER_FID_FIXI,
    DER_CDS_SNFI,
    DER_CDS_SNSO,
    DER_CDS_SNOT,
    DER_CDS_INDX,
    DER_CDS_EXOT,
    DER_CDS_OTHR,
    DER_FEX_INVT,
    DER_FEX_HEDG,
    DER_IRD_INTR,
    DER_CTY_ECOL,
    DER_CTY_ENNG,
    DER_CTY_ENPW,
    DER_CTY_ENOT,
    DER_CTY_PMGD,
    DER_CTY_PMOT,
    DER_CTY_OTIM,
    DER_CTY_OTLS,
    DER_CTY_OTAP,
    DER_CTY_OTHR,
    DER_OTH_OTHR,
    PHY_RES_RESL,
    PHY_RES_COML,
    PHY_RES_OTHR,
    PHY_CTY_PCTY,
    PHY_TIM_PTIM,
    PHY_ART_PART,
    PHY_TPT_PTPT,
    PHY_OTH_OTHR,
    CIU_OAM_MMFC,
    CIU_OAM_AETF,
    CIU_OAM_OTHR,
    CIU_NAM_MMFC,
    CIU_NAM_AETF,
    CIU_NAM_OTHR,
    OTH_OTH_OTHR,
    NTA_NTA_NOTA;

    public String value() {
        return name();
    }

    public static SubAssetTypeType fromValue(String v) {
        return valueOf(v);
    }

}
