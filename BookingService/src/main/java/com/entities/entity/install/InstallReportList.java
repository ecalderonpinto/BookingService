package com.entities.entity.install;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.entities.dao.reportingtool.ReportFieldListDAO;
import com.entities.entity.reportingtool.ReportFieldList;
import com.entities.utilities.hibernate.VersionAuditor;

public class InstallReportList implements InstallAdapter{

	private ApplicationContext applicationContext;
	
	private static final Logger logger = LoggerFactory
			.getLogger(InstallReportList.class);

	public InstallReportList(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * Process to install in database reportFieldList for all AIFMD reports
	 * 
	 * @param applicationContext
	 */
	public void install() {
		
		try {

		List<ReportFieldList> reportFieldList = new ArrayList<ReportFieldList>();

		VersionAuditor versionAdmin = new VersionAuditor("admin");

		ReportFieldListDAO reportFieldListDAO = (ReportFieldListDAO) applicationContext
				.getBean("reportFieldListDAO");
		//reportFieldListDAO.deleteAll();

		reportFieldList.add(new ReportFieldList("VERSION", "1.2", "1.2",
				versionAdmin));

		 reportFieldList.add(new ReportFieldList("BOOLEAN", "true", "true",
		 versionAdmin));
		 reportFieldList.add(new ReportFieldList("BOOLEAN", "false", "false",
		 versionAdmin));

		reportFieldList.add(new ReportFieldList("ThreeRankingType", "1", "1",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("ThreeRankingType", "2", "2",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("ThreeRankingType", "3", "3",
				versionAdmin));

		reportFieldList.add(new ReportFieldList("FiveRankingType", "1", "1",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("FiveRankingType", "2", "2",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("FiveRankingType", "3", "3",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("FiveRankingType", "4", "4",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("FiveRankingType", "5", "5",
				versionAdmin));

		reportFieldList.add(new ReportFieldList("TenRankingType", "1", "1",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("TenRankingType", "2", "2",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("TenRankingType", "3", "3",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("TenRankingType", "4", "4",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("TenRankingType", "5", "5",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("TenRankingType", "6", "6",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("TenRankingType", "7", "7",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("TenRankingType", "8", "8",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("TenRankingType", "9", "9",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("TenRankingType", "10", "10",
				versionAdmin));

		reportFieldList.add(new ReportFieldList("AIFMasterFeederStatusType",
				"MASTER", "MASTER", versionAdmin));
		reportFieldList.add(new ReportFieldList("AIFMasterFeederStatusType",
				"FEEDER", "FEEDER", versionAdmin));
		reportFieldList.add(new ReportFieldList("AIFMasterFeederStatusType",
				"NONE", "NONE", versionAdmin));

		reportFieldList.add(new ReportFieldList("AIFMContentTypeType", "1",
				"1", versionAdmin));
		reportFieldList.add(new ReportFieldList("AIFMContentTypeType", "2",
				"2", versionAdmin));
		reportFieldList.add(new ReportFieldList("AIFMContentTypeType", "3",
				"3", versionAdmin));

		reportFieldList.add(new ReportFieldList("AIFMReportingCodeType", "1",
				"1", versionAdmin));
		reportFieldList.add(new ReportFieldList("AIFMReportingCodeType", "2",
				"2", versionAdmin));
		reportFieldList.add(new ReportFieldList("AIFMReportingCodeType", "3",
				"3", versionAdmin));
		reportFieldList.add(new ReportFieldList("AIFMReportingCodeType", "4",
				"4", versionAdmin));
		reportFieldList.add(new ReportFieldList("AIFMReportingCodeType", "5",
				"5", versionAdmin));
		reportFieldList.add(new ReportFieldList("AIFMReportingCodeType", "6",
				"6", versionAdmin));
		reportFieldList.add(new ReportFieldList("AIFMReportingCodeType", "7",
				"7", versionAdmin));
		reportFieldList.add(new ReportFieldList("AIFMReportingCodeType", "8",
				"8", versionAdmin));
		reportFieldList.add(new ReportFieldList("AIFMReportingCodeType", "9",
				"9", versionAdmin));

		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "1", "1",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "2", "2",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "3", "3",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "4", "4",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "5", "5",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "6", "6",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "7", "7",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "8", "8",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "9", "9",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "10", "10",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "11", "11",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "12", "12",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "13", "13",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "14", "14",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "15", "15",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "16", "16",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "17", "17",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "18", "18",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "19", "19",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "20", "20",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "21", "21",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "22", "22",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "23", "23",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "24", "24",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "25", "25",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "26", "26",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "27", "27",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "28", "28",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "29", "29",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "30", "30",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "31", "31",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "32", "32",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "33", "33",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "34", "34",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "35", "35",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "36", "36",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "37", "37",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "38", "38",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "39", "39",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "40", "40",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "41", "41",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("QUESTION_AIFM", "42", "42",
				versionAdmin));

		reportFieldList.add(new ReportFieldList("AIFTypeType", "HFND", "HFND",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("AIFTypeType", "PEQF", "PEQF",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("AIFTypeType", "REST", "REST",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("AIFTypeType", "FOFS", "FOFS",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("AIFTypeType", "OTHR", "OTHR",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("AIFTypeType", "NONE", "NONE",
				versionAdmin));

		reportFieldList.add(new ReportFieldList("AIIDerivativeTypeType", "O",
				"O", versionAdmin));
		reportFieldList.add(new ReportFieldList("AIIDerivativeTypeType", "F",
				"F", versionAdmin));

		reportFieldList.add(new ReportFieldList("AIIPutCallIdentifierType",
				"P", "P", versionAdmin));
		reportFieldList.add(new ReportFieldList("AIIPutCallIdentifierType",
				"C", "C", versionAdmin));
		reportFieldList.add(new ReportFieldList("AIIPutCallIdentifierType",
				"F", "F", versionAdmin));

		reportFieldList.add(new ReportFieldList("AlternateTextType", "NA",
				"NA", versionAdmin));

		reportFieldList.add(new ReportFieldList("AssetMacroTypeType", "SEC",
				"SEC", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetMacroTypeType", "DER",
				"DER", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetMacroTypeType", "CIU",
				"CIU", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetMacroTypeType", "PHY",
				"PHY", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetMacroTypeType", "OTH",
				"OTH", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetMacroTypeType", "NTA",
				"NTA", versionAdmin));

		reportFieldList.add(new ReportFieldList("AssetTypeType", "SEC_CSH",
				"SEC_CSH", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "SEC_LEQ",
				"SEC_LEQ", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "SEC_CPN",
				"SEC_CPN", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "SEC_CPI",
				"SEC_CPI", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "SEC_SBD",
				"SEC_SBD", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "SEC_MBN",
				"SEC_MBN", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "SEC_CBN",
				"SEC_CBN", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "SEC_CBI",
				"SEC_CBI", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "SEC_LON",
				"SEC_LON", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "SEC_SSP",
				"SEC_SSP", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "SEC_UEQ",
				"SEC_UEQ", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "DER_EQD",
				"DER_EQD", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "DER_FID",
				"DER_FID", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "DER_CDS",
				"DER_CDS", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "DER_FEX",
				"DER_FEX", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "DER_IRD",
				"DER_IRD", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "DER_CTY",
				"DER_CTY", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "DER_OTH",
				"DER_OTH", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "PHY_RES",
				"PHY_RES", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "PHY_CTY",
				"PHY_CTY", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "PHY_TIM",
				"PHY_TIM", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "PHY_ART",
				" ", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "PHY_TPT",
				"PHY_TPT", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "PHY_OTH",
				"PHY_OTH", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "CIU_OAM",
				"CIU_OAM", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "CIU_NAM",
				"CIU_NAM", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "OTH_OTH",
				"OTH_OTH", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "NTA_NTA",
				"NTA_NTA", versionAdmin));

		reportFieldList.add(new ReportFieldList("CancelledRecordFlagType", "C",
				"C", versionAdmin));

		reportFieldList.add(new ReportFieldList("FilingTypeType", "AMND",
				"AMND", versionAdmin));
		reportFieldList.add(new ReportFieldList("FilingTypeType", "INIT",
				"INIT", versionAdmin));

		reportFieldList.add(new ReportFieldList("FundOfFundsStrategyTypeType",
				"FOFS_FHFS", "FOFS_FHFS", versionAdmin));
		reportFieldList.add(new ReportFieldList("FundOfFundsStrategyTypeType",
				"FOFS_PRIV", "FOFS_PRIV", versionAdmin));
		reportFieldList.add(new ReportFieldList("FundOfFundsStrategyTypeType",
				"OTHR_FOFS", "OTHR_FOFS", versionAdmin));

		reportFieldList.add(new ReportFieldList("FXEURReferenceRateTypeType",
				"ECB", "ECB", versionAdmin));
		reportFieldList.add(new ReportFieldList("FXEURReferenceRateTypeType",
				"OTH", "OTH", versionAdmin));

		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"NONE", "NONE", versionAdmin));

		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"EQTY_LGBS", "EQTY_LGBS", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"EQTY_LGST", "EQTY_LGST", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"EQTY_MTNL", "EQTY_MTNL", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"EQTY_STBS", "EQTY_STBS", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"RELV_FXIA", "RELV_FXIA", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"RELV_CBAR", "RELV_CBAR", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"RELV_VLAR", "RELV_VLAR", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"EVDR_DSRS", "EVDR_DSRS", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"EVDR_RAMA", "EVDR_RAMA", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"EVDR_EYSS", "EVDR_EYSS", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"CRED_LGST", "CRED_LGST", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"CRED_ABLG", "CRED_ABLG", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"MACR_MACR", "MACR_MACR", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"MANF_CTAF", "MANF_CTAF", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"MANF_CTAQ", "MANF_CTAQ", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"MULT_HFND", "MULT_HFND", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"OTHR_HFND", "OTHR_HFND", versionAdmin));

		reportFieldList.add(new ReportFieldList("InstrumentCodeTypeType",
				"ISIN", "ISIN", versionAdmin));
		reportFieldList.add(new ReportFieldList("InstrumentCodeTypeType",
				"AII", "AII", versionAdmin));
		reportFieldList.add(new ReportFieldList("InstrumentCodeTypeType",
				"NONE", "NONE", versionAdmin));

		reportFieldList.add(new ReportFieldList("InvestorGroupTypeType",
				"NFCO", "NFCO", versionAdmin));
		reportFieldList.add(new ReportFieldList("InvestorGroupTypeType",
				"BANK", "BANK", versionAdmin));
		reportFieldList.add(new ReportFieldList("InvestorGroupTypeType",
				"INSC", "INSC", versionAdmin));
		reportFieldList.add(new ReportFieldList("InvestorGroupTypeType",
				"OFIN", "OFIN", versionAdmin));
		reportFieldList.add(new ReportFieldList("InvestorGroupTypeType",
				"PFND", "PFND", versionAdmin));
		reportFieldList.add(new ReportFieldList("InvestorGroupTypeType",
				"GENG", "GENG", versionAdmin));
		reportFieldList.add(new ReportFieldList("InvestorGroupTypeType",
				"OCIU", "OCIU", versionAdmin));
		reportFieldList.add(new ReportFieldList("InvestorGroupTypeType",
				"HHLD", "HHLD", versionAdmin));
		reportFieldList.add(new ReportFieldList("InvestorGroupTypeType",
				"UNKN", "UNKN", versionAdmin));
		reportFieldList.add(new ReportFieldList("InvestorGroupTypeType",
				"NONE", "NONE", versionAdmin));

		reportFieldList
				.add(new ReportFieldList("InvestorRedemptionFrequencyType",
						"NONE", "NONE", versionAdmin));

		reportFieldList.add(new ReportFieldList(
				"InvestorRedemptionFrequencyType", "D", "D", versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"InvestorRedemptionFrequencyType", "W", "W", versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"InvestorRedemptionFrequencyType", "F", "F", versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"InvestorRedemptionFrequencyType", "M", "M", versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"InvestorRedemptionFrequencyType", "Q", "Q", versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"InvestorRedemptionFrequencyType", "H", "H", versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"InvestorRedemptionFrequencyType", "Y", "Y", versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"InvestorRedemptionFrequencyType", "O", "O", versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"InvestorRedemptionFrequencyType", "N", "N", versionAdmin));

		reportFieldList.add(new ReportFieldList("MarketCodeTypeWithNOTType",
				"NOT", "NOT", versionAdmin));
		reportFieldList.add(new ReportFieldList("MarketCodeTypeWithNOTType",
				"MIC", "MIC", versionAdmin));
		reportFieldList.add(new ReportFieldList("MarketCodeTypeWithNOTType",
				"OTC", "OTC", versionAdmin));
		reportFieldList.add(new ReportFieldList("MarketCodeTypeWithNOTType",
				"XXX", "XXX", versionAdmin));

		reportFieldList.add(new ReportFieldList("MarketCodeTypeWithoutNOTType",
				"MIC", "MIC", versionAdmin));
		reportFieldList.add(new ReportFieldList("MarketCodeTypeWithoutNOTType",
				"OTC", "OTC", versionAdmin));
		reportFieldList.add(new ReportFieldList("MarketCodeTypeWithoutNOTType",
				"XXX", "XXX", versionAdmin));

		reportFieldList.add(new ReportFieldList("OtherFundStrategyTypeType",
				"OTHR_COMF", "OTHR_COMF", versionAdmin));
		reportFieldList.add(new ReportFieldList("OtherFundStrategyTypeType",
				"OTHR_EQYF", "OTHR_EQYF", versionAdmin));
		reportFieldList.add(new ReportFieldList("OtherFundStrategyTypeType",
				"OTHR_FXIF", "OTHR_FXIF", versionAdmin));
		reportFieldList.add(new ReportFieldList("OtherFundStrategyTypeType",
				"OTHR_INFF", "OTHR_INFF", versionAdmin));
		reportFieldList.add(new ReportFieldList("OtherFundStrategyTypeType",
				"OTHR_OTHF", "OTHR_OTHF", versionAdmin));

		reportFieldList.add(new ReportFieldList("PositionTypeType", "L", "L",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("PositionTypeType", "S", "S",
				versionAdmin));

		reportFieldList.add(new ReportFieldList(
				"PrivateEquityFundStrategyTypeType", "VENT_CAPL", "VENT_CAPL",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"PrivateEquityFundStrategyTypeType", "GRTH_CAPL", "GRTH_CAPL",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"PrivateEquityFundStrategyTypeType", "MZNE_CAPL", "MZNE_CAPL",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"PrivateEquityFundStrategyTypeType", "MULT_PEQF", "MULT_PEQF",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"PrivateEquityFundStrategyTypeType", "OTHR_PEQF", "OTHR_PEQF",
				versionAdmin));

		reportFieldList.add(new ReportFieldList(
				"RealEstateFundStrategyTypeType", "RESL_REST", "RESL_REST",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"RealEstateFundStrategyTypeType", "COML_REST", "COML_REST",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"RealEstateFundStrategyTypeType", "INDL_REST", "INDL_REST",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"RealEstateFundStrategyTypeType", "MULT_REST", "MULT_REST",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"RealEstateFundStrategyTypeType", "OTHR_REST", "OTHR_REST",
				versionAdmin));

		reportFieldList.add(new ReportFieldList(
				"ReportingObligationChangeFrequencyCodeType", "YH", "YH",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"ReportingObligationChangeFrequencyCodeType", "YQ", "YQ",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"ReportingObligationChangeFrequencyCodeType", "HY", "HY",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"ReportingObligationChangeFrequencyCodeType", "HQ", "HQ",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"ReportingObligationChangeFrequencyCodeType", "QY", "QY",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"ReportingObligationChangeFrequencyCodeType", "QH", "QH",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"ReportingObligationChangeFrequencyCodeType", "NQ", "NQ",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"ReportingObligationChangeFrequencyCodeType", "NH", "NH",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"ReportingObligationChangeFrequencyCodeType", "NY", "NY",
				versionAdmin));

		reportFieldList.add(new ReportFieldList(
				"ReportingObligationChangeQuarterType", "Q1", "Q1",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"ReportingObligationChangeQuarterType", "Q2", "Q2",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"ReportingObligationChangeQuarterType", "Q3", "Q3",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"ReportingObligationChangeQuarterType", "Q4", "Q4",
				versionAdmin));

		reportFieldList.add(new ReportFieldList("ReportingPeriodTypeType",
				"Q1", "Q1", versionAdmin));
		reportFieldList.add(new ReportFieldList("ReportingPeriodTypeType",
				"Q2", "Q2", versionAdmin));
		reportFieldList.add(new ReportFieldList("ReportingPeriodTypeType",
				"Q3", "Q3", versionAdmin));
		reportFieldList.add(new ReportFieldList("ReportingPeriodTypeType",
				"Q4", "Q4", versionAdmin));
		reportFieldList.add(new ReportFieldList("ReportingPeriodTypeType",
				"H1", "H1", versionAdmin));
		reportFieldList.add(new ReportFieldList("ReportingPeriodTypeType",
				"H2", "H2", versionAdmin));
		reportFieldList.add(new ReportFieldList("ReportingPeriodTypeType",
				"Y1", "Y1", versionAdmin));
		reportFieldList.add(new ReportFieldList("ReportingPeriodTypeType",
				"X1", "X1", versionAdmin));
		reportFieldList.add(new ReportFieldList("ReportingPeriodTypeType",
				"X2", "X2", versionAdmin));

		reportFieldList.add(new ReportFieldList("RiskMeasureTypeType",
				"NET_EQTY_DELTA", "NET_EQTY_DELTA", versionAdmin));
		reportFieldList.add(new ReportFieldList("RiskMeasureTypeType",
				"NET_DV01", "NET_DV01", versionAdmin));
		reportFieldList.add(new ReportFieldList("RiskMeasureTypeType",
				"NET_CS01", "NET_CS01", versionAdmin));
		reportFieldList.add(new ReportFieldList("RiskMeasureTypeType", "VAR",
				"VAR", versionAdmin));
		reportFieldList.add(new ReportFieldList("RiskMeasureTypeType",
				"VEGA_EXPO", "VEGA_EXPO", versionAdmin));
		reportFieldList.add(new ReportFieldList("RiskMeasureTypeType",
				"NET_FX_DELTA", "NET_FX_DELTA", versionAdmin));
		reportFieldList.add(new ReportFieldList("RiskMeasureTypeType",
				"NET_CTY_DELTA", "NET_CTY_DELTA", versionAdmin));

		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CSH_CODP", "SEC_CSH_CODP", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CSH_COMP", "SEC_CSH_COMP", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CSH_OTHD", "SEC_CSH_OTHD", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CSH_OTHC", "SEC_CSH_OTHC", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_LEQ_IFIN", "SEC_LEQ_IFIN", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_LEQ_OTHR", "SEC_LEQ_OTHR", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_UEQ_UEQY", "SEC_UEQ_UEQY", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CPN_INVG", "SEC_CPN_INVG", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CPN_NIVG", "SEC_CPN_NIVG", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CPI_INVG", "SEC_CPI_INVG", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CPI_NIVG", "SEC_CPI_NIVG", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SBD_EUBY", "SEC_SBD_EUBY", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SBD_EUBM", "SEC_SBD_EUBM", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SBD_NOGY", "SEC_SBD_NOGY", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SBD_NOGM", "SEC_SBD_NOGM", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SBD_EUGY", "SEC_SBD_EUGY", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SBD_EUGM", "SEC_SBD_EUGM", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_MBN_MNPL", "SEC_MBN_MNPL", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CBN_INVG", "SEC_CBN_INVG", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CBN_NIVG", "SEC_CBN_NIVG", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CBI_INVG", "SEC_CBI_INVG", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CBI_NIVG", "SEC_CBI_NIVG", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_LON_LEVL", "SEC_LON_LEVL", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_LON_OTHL", "SEC_LON_OTHL", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SSP_SABS", "SEC_SSP_SABS", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SSP_RMBS", "SEC_SSP_RMBS", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SSP_CMBS", "SEC_SSP_CMBS", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SSP_AMBS", "SEC_SSP_AMBS", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SSP_ABCP", "SEC_SSP_ABCP", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SSP_CDOC", "SEC_SSP_CDOC", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SSP_STRC", "SEC_SSP_STRC", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SSP_SETP", "SEC_SSP_SETP", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SSP_OTHS", "SEC_SSP_OTHS", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_EQD_FINI", "DER_EQD_FINI", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_EQD_OTHD", "DER_EQD_OTHD", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_FID_FIXI", "DER_FID_FIXI", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CDS_SNFI", "DER_CDS_SNFI", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CDS_SNSO", "DER_CDS_SNSO", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CDS_SNOT", "DER_CDS_SNOT", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CDS_INDX", "DER_CDS_INDX", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CDS_EXOT", "DER_CDS_EXOT", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CDS_OTHR", "DER_CDS_OTHR", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_FEX_INVT", "DER_FEX_INVT", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_FEX_HEDG", "DER_FEX_HEDG", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_IRD_INTR", "DER_IRD_INTR", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_ECOL", "DER_CTY_ECOL", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_ENNG", "DER_CTY_ENNG", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_ENPW", "DER_CTY_ENPW", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_ENOT", "DER_CTY_ENOT", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_PMGD", "DER_CTY_PMGD", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_PMOT", "DER_CTY_PMOT", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_OTIM", "DER_CTY_OTIM", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_OTLS", "DER_CTY_OTLS", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_OTAP", "DER_CTY_OTAP", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_OTHR", "DER_CTY_OTHR", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_OTH_OTHR", "DER_OTH_OTHR", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"PHY_RES_RESL", "PHY_RES_RESL", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"PHY_RES_COML", "PHY_RES_COML", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"PHY_RES_OTHR", "PHY_RES_OTHR", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"PHY_CTY_PCTY", "PHY_CTY_PCTY", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"PHY_TIM_PTIM", "PHY_TIM_PTIM", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"PHY_ART_PART", "PHY_ART_PART", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"PHY_TPT_PTPT", "PHY_TPT_PTPT", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"PHY_OTH_OTHR", "PHY_OTH_OTHR", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"CIU_OAM_MMFC", "CIU_OAM_MMFC", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"CIU_OAM_AETF", "CIU_OAM_AETF", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"CIU_OAM_OTHR", "CIU_OAM_OTHR", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"CIU_NAM_MMFC", "CIU_NAM_MMFC", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"CIU_NAM_AETF", "CIU_NAM_AETF", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"CIU_NAM_OTHR", "CIU_NAM_OTHR", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"OTH_OTH_OTHR", "OTH_OTH_OTHR", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"NTA_NTA_NOTA", "NTA_NTA_NOTA", versionAdmin));

		reportFieldList.add(new ReportFieldList("TransactionTypeType", "ACAP",
				"ACAP", versionAdmin));
		reportFieldList.add(new ReportFieldList("TransactionTypeType", "BOUT",
				"BOUT", versionAdmin));
		reportFieldList.add(new ReportFieldList("TransactionTypeType", "CONS",
				"CONS", versionAdmin));
		reportFieldList.add(new ReportFieldList("TransactionTypeType", "CDIV",
				"CDIV", versionAdmin));
		reportFieldList.add(new ReportFieldList("TransactionTypeType", "ESOP",
				"ESOP", versionAdmin));
		reportFieldList.add(new ReportFieldList("TransactionTypeType", "GCAP",
				"GCAP", versionAdmin));
		reportFieldList.add(new ReportFieldList("TransactionTypeType", "RCAP",
				"RCAP", versionAdmin));
		reportFieldList.add(new ReportFieldList("TransactionTypeType", "SLIQ",
				"SLIQ", versionAdmin));
		reportFieldList.add(new ReportFieldList("TransactionTypeType", "TURN",
				"TURN", versionAdmin));
		reportFieldList.add(new ReportFieldList("TransactionTypeType", "OTHR",
				"OTHR", versionAdmin));

		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_CSH_CSH", "SEC_CSH_CSH", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_LEQ_LEQ", "SEC_LEQ_LEQ", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_UEQ_UEQ", "SEC_UEQ_UEQ", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_CPN_IVG", "SEC_CPN_IVG", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_CPN_NIG", "SEC_CPN_NIG", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_CPI_CPI", "SEC_CPI_CPI", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_SBD_EUB", "SEC_SBD_EUB", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_SBD_NEU", "SEC_SBD_NEU", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_MUN_MUN", "SEC_MUN_MUN", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_CBD_CBD", "SEC_CBD_CBD", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_LON_LON", "SEC_LON_LON", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_SSP_SSP", "SEC_SSP_SSP", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"DER_EQD_EQD", "DER_EQD_EQD", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"DER_FID_FID", "DER_FID_FID", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"DER_CDS_CDS", "DER_CDS_CDS", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"DER_FEX_INV", "DER_FEX_INV", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"DER_FEX_HED", "DER_FEX_HED", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"DER_IRD_IRD", "DER_IRD_IRD", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"DER_CTY_CTY", "DER_CTY_CTY", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"DER_OTH_OTH", "DER_OTH_OTH", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"PHY_RES_RES", "PHY_RES_RES", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"PHY_CTY_CTY", "PHY_CTY_CTY", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"PHY_TIM_TIM", "PHY_TIM_TIM", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"PHY_ART_ART", "PHY_ART_ART", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"PHY_TPT_TPT", "PHY_TPT_TPT", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"PHY_OTH_OTH", "PHY_OTH_OTH", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"CIU_CIU_CIU", "CIU_CIU_CIU", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"OTH_OTH_OTH", "OTH_OTH_OTH", versionAdmin));

		reportFieldList.add(new ReportFieldList("TypicalPositionSizeType",
				"V_SMALL", "V_SMALL", versionAdmin));
		reportFieldList.add(new ReportFieldList("TypicalPositionSizeType",
				"SMALL", "SMALL", versionAdmin));
		reportFieldList.add(new ReportFieldList("TypicalPositionSizeType",
				"LOW_MID_MKT", "LOW_MID_MKT", versionAdmin));
		reportFieldList.add(new ReportFieldList("TypicalPositionSizeType",
				"UP_MID_MKT", "UP_MID_MKT", versionAdmin));
		reportFieldList.add(new ReportFieldList("TypicalPositionSizeType",
				"L_CAP", "L_CAP", versionAdmin));
		reportFieldList.add(new ReportFieldList("TypicalPositionSizeType",
				"M_CAP", "M_CAP", versionAdmin));

		reportFieldList.add(new ReportFieldList(
				"VARCalculationMethodCodeTypeType", "HISTO", "HISTO",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"VARCalculationMethodCodeTypeType", "CARLO", "CARLO",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"VARCalculationMethodCodeTypeType", "PARAM", "PARAM",
				versionAdmin));

		// MIC

		reportFieldList.add(new ReportFieldList("MICCodeType", "ASEX", "ASEX",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "BMEX", "BMEX",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "BOVM", "BOVM",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "BVCA", "BVCA",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "BVMF", "BVMF",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "DIFX", "DIFX",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "EUCH", "EUCH",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "FNSE", "FNSE",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "FRAA", "FRAA",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "HKME", "HKME",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "IFED", "IFED",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "MERF", "MERF",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "MUNC", "MUNC",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "NURD", "NURD",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "OOTC", "OOTC",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "PLDX", "PLDX",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "RTSL", "RTSL",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "SHSC", "SHSC",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "WDER", "WDER",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XAEX", "XAEX",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XAMM", "XAMM",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XAMS", "XAMS",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XASE", "XASE",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XASX", "XASX",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XBAR", "XBAR",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XBCL", "XBCL",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XBER", "XBER",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XBIL", "XBIL",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XBKK", "XBKK",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XBOG", "XBOG",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XBOM", "XBOM",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XBRU", "XBRU",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XBUD", "XBUD",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XBUE", "XBUE",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XCAI", "XCAI",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XCAS", "XCAS",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XCBO", "XCBO",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XCBT", "XCBT",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XCME", "XCME",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XCOL", "XCOL",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XCSE", "XCSE",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XDMI", "XDMI",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XDPA", "XDPA",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XDRF", "XDRF",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XDUB", "XDUB",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XDUS", "XDUS",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XETA", "XETA",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XEUR", "XEUR",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XFRA", "XFRA",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XHAM", "XHAM",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XHEL", "XHEL",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XHKF", "XHKF",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XICE", "XICE",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XIDX", "XIDX",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XIMM", "XIMM",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XIOM", "XIOM",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XIST", "XIST",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XISX", "XISX",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XJAS", "XJAS",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XJSE", "XJSE",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XKAR", "XKAR",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XKAZ", "XKAZ",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XKFE", "XKFE",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XKLS", "XKLS",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XKOS", "XKOS",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XKRX", "XKRX",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XLAT", "XLAT",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XLDX", "XLDX",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XLIM", "XLIM",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XLIS", "XLIS",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XLJU", "XLJU",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XLON", "XLON",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XLUX", "XLUX",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XMAD", "XMAD",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XMCE", "XMCE",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XMEV", "XMEV",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XMEX", "XMEX",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XMIL", "XMIL",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XMLI", "XMLI",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XMOD", "XMOD",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XMON", "XMON",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XMOS", "XMOS",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XMRV", "XMRV",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XNDX", "XNDX",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XNGO", "XNGO",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XNGS", "XNGS",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XNLI", "XNLI",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XNMS", "XNMS",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XNSE", "XNSE",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XNYF", "XNYF",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XNYS", "XNYS",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XNZE", "XNZE",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XOSE", "XOSE",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XOSL", "XOSL",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XOTC", "XOTC",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XPAR", "XPAR",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XPHL", "XPHL",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XPHS", "XPHS",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XPRA", "XPRA",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XSCO", "XSCO",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XSES", "XSES",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XSGO", "XSGO",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XSHE", "XSHE",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XSHG", "XSHG",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XSIM", "XSIM",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XSTU", "XSTU",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XSWX", "XSWX",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XTAE", "XTAE",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XTAI", "XTAI",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XTAL", "XTAL",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XTFF", "XTFF",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XTKS", "XTKS",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XTSE", "XTSE",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XTSX", "XTSX",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XVAL", "XVAL",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XVTX", "XVTX",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XWAR", "XWAR",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XWBO", "XWBO",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XNAS", "XNAS",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("MICCodeType", "XLIF", "XLIF",
				versionAdmin));

		// country codes
		reportFieldList.add(new ReportFieldList("CountryCodeType", "AF",
				"Afghanistan", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "AX",
				"Åland Islands", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "AL",
				"Albania", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "DZ",
				"Algeria", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "AS",
				"American Samoa", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "AD",
				"Andorra", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "AO",
				"Angola", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "AI",
				"Anguilla", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "AQ",
				"Antarctica", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "AG",
				"Antigua and Barbuda", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "AR",
				"Argentina", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "AM",
				"Armenia", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "AW",
				"Aruba", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "AU",
				"Australia", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "AT",
				"Austria", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "AZ",
				"Azerbaijan", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "BS",
				"Bahamas", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "BH",
				"Bahrain", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "BD",
				"Bangladesh", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "BB",
				"Barbados", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "BY",
				"Belarus", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "BE",
				"Belgium", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "BZ",
				"Belize", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "BJ",
				"Benin", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "BM",
				"Bermuda", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "BT",
				"Bhutan", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "BO",
				"Bolivia (Plurinational State of)", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "BQ",
				"Bonaire, Sint Eustatius and Saba", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "BA",
				"Bosnia and Herzegovina", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "BW",
				"Botswana", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "BV",
				"Bouvet Island", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "BR",
				"Brazil", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "IO",
				"British Indian Ocean Territory", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "BN",
				"Brunei Darussalam", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "BG",
				"Bulgaria", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "BF",
				"Burkina Faso", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "BI",
				"Burundi", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "KH",
				"Cambodia", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "CM",
				"Cameroon", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "CA",
				"Canada", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "CV",
				"Cabo Verde", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "KY",
				"Cayman Islands", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "CF",
				"Central African Republic", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "TD",
				"Chad", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "CL",
				"Chile", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "CN",
				"China", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "CX",
				"Christmas Island", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "CC",
				"Cocos (Keeling) Islands", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "CO",
				"Colombia", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "KM",
				"Comoros", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "CG",
				"Congo", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "CD",
				"Congo (Democratic Republic of the)", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "CK",
				"Cook Islands", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "CR",
				"Costa Rica", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "CI",
				"Côte d'Ivoire", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "HR",
				"Croatia", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "CU",
				"Cuba", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "CW",
				"Curaçao", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "CY",
				"Cyprus", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "CZ",
				"Czech Republic", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "DK",
				"Denmark", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "DJ",
				"Djibouti", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "DM",
				"Dominica", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "DO",
				"Dominican Republic", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "EC",
				"Ecuador", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "EG",
				"Egypt", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "SV",
				"El Salvador", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "GQ",
				"Equatorial Guinea", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "ER",
				"Eritrea", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "EE",
				"Estonia", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "ET",
				"Ethiopia", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "FK",
				"Falkland Islands (Malvinas)", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "FO",
				"Faroe Islands", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "FJ",
				"Fiji", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "FI",
				"Finland", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "FR",
				"France", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "GF",
				"French Guiana", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "PF",
				"French Polynesia", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "TF",
				"French Southern Territories", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "GA",
				"Gabon", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "GM",
				"Gambia", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "GE",
				"Georgia", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "DE",
				"Germany", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "GH",
				"Ghana", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "GI",
				"Gibraltar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "GR",
				"Greece", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "GL",
				"Greenland", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "GD",
				"Grenada", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "GP",
				"Guadeloupe", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "GU",
				"Guam", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "GT",
				"Guatemala", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "GG",
				"Guernsey", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "GN",
				"Guinea", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "GW",
				"Guinea-Bissau", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "GY",
				"Guyana", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "HT",
				"Haiti", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "HM",
				"Heard Island and McDonald Islands", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "VA",
				"Holy See", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "HN",
				"Honduras", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "HK",
				"Hong Kong", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "HU",
				"Hungary", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "IS",
				"Iceland", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "IN",
				"India", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "ID",
				"Indonesia", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "IR",
				"Iran (Islamic Republic of)", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "IQ",
				"Iraq", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "IE",
				"Ireland", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "IM",
				"Isle of Man", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "IL",
				"Israel", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "IT",
				"Italy", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "JM",
				"Jamaica", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "JP",
				"Japan", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "JE",
				"Jersey", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "JO",
				"Jordan", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "KZ",
				"Kazakhstan", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "KE",
				"Kenya", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "KI",
				"Kiribati", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "KP",
				"Korea (Democratic People's Republic of)", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "KR",
				"Korea (Republic of)", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "KW",
				"Kuwait", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "KG",
				"Kyrgyzstan", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "LA",
				"Lao People's Democratic Republic", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "LV",
				"Latvia", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "LB",
				"Lebanon", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "LS",
				"Lesotho", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "LR",
				"Liberia", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "LY",
				"Libya", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "LI",
				"Liechtenstein", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "LT",
				"Lithuania", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "LU",
				"Luxembourg", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "MO",
				"Macao", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "MK",
				"Macedonia (the former Yugoslav Republic of)", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "MG",
				"Madagascar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "MW",
				"Malawi", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "MY",
				"Malaysia", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "MV",
				"Maldives", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "ML",
				"Mali", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "MT",
				"Malta", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "MH",
				"Marshall Islands", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "MQ",
				"Martinique", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "MR",
				"Mauritania", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "MU",
				"Mauritius", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "YT",
				"Mayotte", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "MX",
				"Mexico", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "FM",
				"Micronesia (Federated States of)", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "MD",
				"Moldova (Republic of)", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "MC",
				"Monaco", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "MN",
				"Mongolia", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "ME",
				"Montenegro", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "MS",
				"Montserrat", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "MA",
				"Morocco", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "MZ",
				"Mozambique", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "MM",
				"Myanmar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "NA",
				"Namibia", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "NR",
				"Nauru", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "NP",
				"Nepal", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "NL",
				"Netherlands", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "NC",
				"New Caledonia", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "NZ",
				"New Zealand", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "NI",
				"Nicaragua", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "NE",
				"Niger", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "NG",
				"Nigeria", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "NU",
				"Niue", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "NF",
				"Norfolk Island", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "MP",
				"Northern Mariana Islands", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "NO",
				"Norway", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "OM",
				"Oman", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "PK",
				"Pakistan", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "PW",
				"Palau", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "PS",
				"Palestine, State of", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "PA",
				"Panama", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "PG",
				"Papua New Guinea", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "PY",
				"Paraguay", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "PE",
				"Peru", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "PH",
				"Philippines", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "PN",
				"Pitcairn", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "PL",
				"Poland", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "PT",
				"Portugal", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "PR",
				"Puerto Rico", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "QA",
				"Qatar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "RE",
				"Réunion", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "RO",
				"Romania", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "RU",
				"Russian Federation", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "RW",
				"Rwanda", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "BL",
				"Saint Barthélemy", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "SH",
				"Saint Helena, Ascension and Tristan da Cunha", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "KN",
				"Saint Kitts and Nevis", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "LC",
				"Saint Lucia", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "MF",
				"Saint Martin (French part)", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "PM",
				"Saint Pierre and Miquelon", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "VC",
				"Saint Vincent and the Grenadines", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "WS",
				"Samoa", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "SM",
				"San Marino", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "ST",
				"Sao Tome and Principe", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "SA",
				"Saudi Arabia", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "SN",
				"Senegal", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "RS",
				"Serbia", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "SC",
				"Seychelles", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "SL",
				"Sierra Leone", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "SG",
				"Singapore", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "SX",
				"Sint Maarten (Dutch part)", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "SK",
				"Slovakia", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "SI",
				"Slovenia", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "SB",
				"Solomon Islands", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "SO",
				"Somalia", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "ZA",
				"South Africa", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "GS",
				"South Georgia and the South Sandwich Islands", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "SS",
				"South Sudan", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "ES",
				"Spain", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "LK",
				"Sri Lanka", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "SD",
				"Sudan", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "SR",
				"Suriname", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "SJ",
				"Svalbard and Jan Mayen", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "SZ",
				"Swaziland", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "SE",
				"Sweden", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "CH",
				"Switzerland", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "SY",
				"Syrian Arab Republic", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "TW",
				"Taiwan, Province of China", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "TJ",
				"Tajikistan", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "TZ",
				"Tanzania, United Republic of", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "TH",
				"Thailand", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "TL",
				"Timor-Leste", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "TG",
				"Togo", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "TK",
				"Tokelau", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "TO",
				"Tonga", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "TT",
				"Trinidad and Tobago", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "TN",
				"Tunisia", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "TR",
				"Turkey", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "TM",
				"Turkmenistan", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "TC",
				"Turks and Caicos Islands", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "TV",
				"Tuvalu", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "UG",
				"Uganda", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "UA",
				"Ukraine", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "AE",
				"United Arab Emirates", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "GB",
				"United Kingdom of Great Britain and Northern Ireland",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "US",
				"United States of America", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "UM",
				"United States Minor Outlying Islands", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "UY",
				"Uruguay", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "UZ",
				"Uzbekistan", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "VU",
				"Vanuatu", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "VE",
				"Venezuela (Bolivarian Republic of)", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "VN",
				"Viet Nam", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "VG",
				"Virgin Islands (British)", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "VI",
				"Virgin Islands (U.S.)", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "WF",
				"Wallis and Futuna", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "EH",
				"Western Sahara", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "YE",
				"Yemen", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "ZM",
				"Zambia", versionAdmin));
		reportFieldList.add(new ReportFieldList("CountryCodeType", "ZW",
				"Zimbabwe", versionAdmin));

		// CurrencyCodeType
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "AED",
				"UAE Dirham", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "AFN",
				"Afghani", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "ALL",
				"Lek", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "AMD",
				"Armenian Dram", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "ANG",
				"Netherlands Antillean Guilder", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "AOA",
				"Kwanza", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "ARS",
				"Argentine Peso", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "AUD",
				"Australian Dollar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "AWG",
				"Aruban Florin", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "AZN",
				"Azerbaijanian Manat", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "BAM",
				"Convertible Mark", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "BBD",
				"Barbados Dollar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "BDT",
				"Taka", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "BGN",
				"Bulgarian Lev", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "BHD",
				"Bahraini Dinar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "BIF",
				"Burundi Franc", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "BMD",
				"Bermudian Dollar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "BND",
				"Brunei Dollar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "BOB",
				"Boliviano", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "BOV",
				"Mvdol", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "BRL",
				"Brazilian Real", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "BSD",
				"Bahamian Dollar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "BTN",
				"Ngultrum", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "BWP",
				"Pula", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "BYR",
				"Belarussian Ruble", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "BZD",
				"Belize Dollar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "CAD",
				"Canadian Dollar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "CDF",
				"Congolese Franc", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "CHE",
				"WIR Euro", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "CHF",
				"Swiss Franc", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "CHW",
				"WIR Franc", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "CLF",
				"Unidad de Fomento", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "CLP",
				"Chilean Peso", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "CNY",
				"Yuan Renminbi", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "COP",
				"Colombian Peso", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "COU",
				"Unidad de Valor Real", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "CRC",
				"Costa Rican Colon", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "CUC",
				"Peso Convertible", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "CUP",
				"Cuban Peso", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "CVE",
				"Cabo Verde Escudo", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "CZK",
				"Czech Koruna", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "DJF",
				"Djibouti Franc", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "DKK",
				"Danish Krone", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "DOP",
				"Dominican Peso", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "DZD",
				"Algerian Dinar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "EGP",
				"Egyptian Pound", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "ERN",
				"Nakfa", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "ETB",
				"Ethiopian Birr", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "EUR",
				"Euro", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "FJD",
				"Fiji Dollar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "FKP",
				"Falkland Islands Pound", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "GBP",
				"Pound Sterling", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "GEL",
				"Lari", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "GHS",
				"Ghana Cedi", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "GIP",
				"Gibraltar Pound", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "GMD",
				"Dalasi", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "GNF",
				"Guinea Franc", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "GTQ",
				"Quetzal", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "GYD",
				"Guyana Dollar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "HKD",
				"Hong Kong Dollar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "HNL",
				"Lempira", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "HRK",
				"Croatian Kuna", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "HTG",
				"Gourde", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "HUF",
				"Forint", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "IDR",
				"Rupiah", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "ILS",
				"New Israeli Sheqel", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "INR",
				"Indian Rupee", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "IQD",
				"Iraqi Dinar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "IRR",
				"Iranian Rial", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "ISK",
				"Iceland Krona", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "JMD",
				"Jamaican Dollar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "JOD",
				"Jordanian Dinar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "JPY",
				"Yen", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "KES",
				"Kenyan Shilling", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "KGS",
				"Som", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "KHR",
				"Riel", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "KMF",
				"Comoro Franc", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "KPW",
				"North Korean Won", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "KRW",
				"Won", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "KWD",
				"Kuwaiti Dinar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "KYD",
				"Cayman Islands Dollar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "KZT",
				"Tenge", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "LAK",
				"Kip", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "LBP",
				"Lebanese Pound", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "LKR",
				"Sri Lanka Rupee", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "LRD",
				"Liberian Dollar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "LSL",
				"Loti", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "LYD",
				"Libyan Dinar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "MAD",
				"Moroccan Dirham", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "MDL",
				"Moldovan Leu", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "MGA",
				"Malagasy Ariary", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "MKD",
				"Denar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "MMK",
				"Kyat", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "MNT",
				"Tugrik", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "MOP",
				"Pataca", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "MRO",
				"Ouguiya", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "MUR",
				"Mauritius Rupee", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "MVR",
				"Rufiyaa", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "MWK",
				"Kwacha", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "MXN",
				"Mexican Peso", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "MXV",
				"Mexican Unidad de Inversion (UDI)", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "MYR",
				"Malaysian Ringgit", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "MZN",
				"Mozambique Metical", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "NAD",
				"Namibia Dollar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "NGN",
				"Naira", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "NIO",
				"Cordoba Oro", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "NOK",
				"Norwegian Krone", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "NPR",
				"Nepalese Rupee", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "NZD",
				"New Zealand Dollar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "OMR",
				"Rial Omani", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "PAB",
				"Balboa", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "PEN",
				"Nuevo Sol", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "PGK",
				"Kina", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "PHP",
				"Philippine Peso", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "PKR",
				"Pakistan Rupee", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "PLN",
				"Zloty", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "PYG",
				"Guarani", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "QAR",
				"Qatari Rial", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "RON",
				"New Romanian Leu", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "RSD",
				"Serbian Dinar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "RUB",
				"Russian Ruble", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "RWF",
				"Rwanda Franc", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "SAR",
				"Saudi Riyal", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "SBD",
				"Solomon Islands Dollar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "SCR",
				"Seychelles Rupee", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "SDG",
				"Sudanese Pound", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "SEK",
				"Swedish Krona", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "SGD",
				"Singapore Dollar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "SHP",
				"Saint Helena Pound", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "SLL",
				"Leone", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "SOS",
				"Somali Shilling", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "SRD",
				"Surinam Dollar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "SSP",
				"South Sudanese Pound", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "STD",
				"Dobra", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "SVC",
				"El Salvador Colon", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "SYP",
				"Syrian Pound", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "SZL",
				"Lilangeni", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "THB",
				"Baht", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "TJS",
				"Somoni", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "TMT",
				"Turkmenistan New Manat", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "TND",
				"Tunisian Dinar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "TOP",
				"Pa’anga", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "TRY",
				"Turkish Lira", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "TTD",
				"Trinidad and Tobago Dollar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "TWD",
				"New Taiwan Dollar", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "TZS",
				"Tanzanian Shilling", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "UAH",
				"Hryvnia", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "UGX",
				"Uganda Shilling", versionAdmin));
		reportFieldList.add(new ReportFieldList("CurrencyCodeType", "USD",
				"US Dollar", versionAdmin));

		for (ReportFieldList reportFieldListExample : reportFieldList) {
			reportFieldListDAO.create(reportFieldListExample);
		}

		
		} catch (Exception e) {
			logger.error("Error when installing : " + e.getMessage());
		}
	}
}
