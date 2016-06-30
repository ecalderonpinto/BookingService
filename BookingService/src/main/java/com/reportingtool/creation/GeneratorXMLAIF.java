package com.reportingtool.creation;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;

import com.entities.dictionary.ErrorTypeEnum;
import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportExecution;
import com.reportingtool.controllers.forms.GenerateXMLForm;
import com.reportingtool.utilities.ReportUtilities;
import com.reportingtool.utilities.ReportingErrorManager;
import com.reportingtool.utilities.XMLGregorianCalendarConverter;
import com.reportingtool.xml.*;

/**
 * Class to generate XML string from a reportExecution type AIF
 * 
 * @author Alberto
 * 
 */
public class GeneratorXMLAIF {

	/**
	 * Function generate a aifXML from a reportExecution with XSD classes and
	 * validate it. Create reportErrores
	 * 
	 * @param reportExecution
	 * @return aifXML string
	 */
	public static GenerateXMLForm generateXMLAIF(
			ReportExecution reportExecution,
			ApplicationContext applicationContext, Logger logger,
			String aifXSDResource) {

		logger.info("GeneratorXML: starting XML generation ");

		// all dataFields
		List<ReportData> reportDatas = new ArrayList<ReportData>(
				reportExecution.getReportDatas());

		// show all reportData to make sure the content
		// for (ReportData reportData : reportDatas) {
		// logger.info(reportData.getReportField().getReportFieldName() + "("
		// + reportData.getReportField().getReportFieldNum() + "): "
		// + reportData.getReportDataText());
		// }

		// ///////////////////////////////////////////////////////////
		// TODO:RT ONLY STATUS = PENDING WILL CREATE XML REPORTS
		// if (aifmdReportResult.getAifmdReportResultStat().equals("PENDING")) {
		// }

		try {
			DateFormat formatDate = new SimpleDateFormat(
					ReportUtilities.datePattern);
			DateFormat formatYear = new SimpleDateFormat(
					ReportUtilities.yearPattern);
			DateFormat formatDateTime = new SimpleDateFormat(
					ReportUtilities.dateTimePattern);

			// ObjectFactory to make every class to AIFM report
			ObjectFactoryAIF objectFactoryAIF = new ObjectFactoryAIF();

			AIFReportingInfo aifReportingInfo = objectFactoryAIF
					.createAIFReportingInfo();

			ComplexCancellationAIFRecordInfoType complexCancellationAIFRecordInfoType = objectFactoryAIF
					.createComplexCancellationAIFRecordInfoType();

			ComplexAIFRecordInfoType complexAIFRecordInfoType = objectFactoryAIF
					.createComplexAIFRecordInfoType();

			ComplexAIFCompleteDescriptionType complexAIFCompleteDescriptionType = objectFactoryAIF
					.createComplexAIFCompleteDescriptionType();

			ComplexAIFIndividualInfoType complexAIFIndividualInfoType = objectFactoryAIF
					.createComplexAIFIndividualInfoType();
			ComplexAIFLeverageInfoType complexAIFLeverageInfoType = objectFactoryAIF
					.createComplexAIFLeverageInfoType();
			ComplexAIFPrincipalInfoType complexAIFPrincipalInfoType = objectFactoryAIF
					.createComplexAIFPrincipalInfoType();

			ComplexAssumptionsType complexAssumptionsType = objectFactoryAIF
					.createComplexAssumptionsType();

			ComplexIndividualExposureType complexIndividualExposureType = objectFactoryAIF
					.createComplexIndividualExposureType();

			ComplexAssetTypeExposuresType complexAssetTypeExposuresType = objectFactoryAIF
					.createComplexAssetTypeExposuresType();

			ComplexAssetTypeTurnoversType complexAssetTypeTurnoversType = objectFactoryAIF
					.createComplexAssetTypeTurnoversType();

			ComplexCompaniesDominantInfluenceType complexCompaniesDominantInfluenceType = objectFactoryAIF
					.createComplexCompaniesDominantInfluenceType();

			ComplexCurrencyExposuresType complexCurrencyExposuresType = objectFactoryAIF
					.createComplexCurrencyExposuresType();

			ComplexRiskProfileType complexRiskProfileType = objectFactoryAIF
					.createComplexRiskProfileType();

			ComplexCounterpartyRiskProfileType complexCounterpartyRiskProfileType = objectFactoryAIF
					.createComplexCounterpartyRiskProfileType();

			ComplexLiquidityRiskProfileType complexLiquidityRiskProfileType = objectFactoryAIF
					.createComplexLiquidityRiskProfileType();

			ComplexMarketRiskProfileType complexMarketRiskProfileType = objectFactoryAIF
					.createComplexMarketRiskProfileType();

			ComplexOperationalRiskType complexOperationalRiskType = objectFactoryAIF
					.createComplexOperationalRiskType();

			ComplexStressTestsType complexStressTestsType = objectFactoryAIF
					.createComplexStressTestsType();

			ComplexAIFLeverageArticle242Type complexAIFLeverageArticle242Type = objectFactoryAIF
					.createComplexAIFLeverageArticle242Type();

			ComplexControlledStructuresType complexControlledStructuresType = objectFactoryAIF
					.createComplexControlledStructuresType();

			ComplexFinancialInstrumentBorrowingType complexFinancialInstrumentBorrowingType = objectFactoryAIF
					.createComplexFinancialInstrumentBorrowingType();

			ComplexLeverageAIFType complexLeverageAIFType = objectFactoryAIF
					.createComplexLeverageAIFType();

			ComplexSecuritiesCashBorrowingType complexSecuritiesCashBorrowingType = objectFactoryAIF
					.createComplexSecuritiesCashBorrowingType();

			ComplexAIFLeverageArticle244Type complexAIFLeverageArticle244Type = objectFactoryAIF
					.createComplexAIFLeverageArticle244Type();

			ComplexAIFDescriptionType complexAIFDescriptionType = objectFactoryAIF
					.createComplexAIFDescriptionType();

			ComplexBaseCurrencyDescriptionType complexBaseCurrencyDescriptionType = objectFactoryAIF
					.createComplexBaseCurrencyDescriptionType();

			ComplexFundOfFundsInvestmentStrategiesType complexFundOfFundsInvestmentStrategiesType = objectFactoryAIF
					.createComplexFundOfFundsInvestmentStrategiesType();

			ComplexHedgeFundInvestmentStrategiesType complexHedgeFundInvestmentStrategiesType = objectFactoryAIF
					.createComplexHedgeFundInvestmentStrategiesType();

			ComplexMasterAIFsIdentificationType complexMasterAIFsIdentificationType = objectFactoryAIF
					.createComplexMasterAIFsIdentificationType();

			ComplexOtherFundInvestmentStrategiesType complexOtherFundInvestmentStrategiesType = objectFactoryAIF
					.createComplexOtherFundInvestmentStrategiesType();

			ComplexPrimeBrokersType complexPrimeBrokersType = objectFactoryAIF
					.createComplexPrimeBrokersType();

			ComplexPrivateEquityFundInvestmentStrategiesType complexPrivateEquityFundInvestmentStrategiesType = objectFactoryAIF
					.createComplexPrivateEquityFundInvestmentStrategiesType();

			ComplexRealEstateFundInvestmentStrategiesType complexRealEstateFundInvestmentStrategiesType = objectFactoryAIF
					.createComplexRealEstateFundInvestmentStrategiesType();

			ComplexAIFIdentifierType complexAIFIdentifierType = objectFactoryAIF
					.createComplexAIFIdentifierType();

			ComplexAIFNationalIdentifierType complexAIFNationalIdentifierType = objectFactoryAIF
					.createComplexAIFNationalIdentifierType();

			ComplexAUMGeographicalFocusType complexAUMGeographicalFocusType = objectFactoryAIF
					.createComplexAUMGeographicalFocusType();

			ComplexMainInstrumentsTradedType complexMainInstrumentsTradedType = objectFactoryAIF
					.createComplexMainInstrumentsTradedType();

			ComplexMostImportantConcentrationType complexMostImportantConcentrationType = objectFactoryAIF
					.createComplexMostImportantConcentrationType();

			ComplexAIFPrincipalMarketsType complexAIFPrincipalMarketsType = objectFactoryAIF
					.createComplexAIFPrincipalMarketsType();

			ComplexInvestorConcentrationType complexInvestorConcentrationType = objectFactoryAIF
					.createComplexInvestorConcentrationType();

			ComplexPortfolioConcentrationsType complexPortfolioConcentrationsType = objectFactoryAIF
					.createComplexPortfolioConcentrationsType();

			ComplexNAVGeographicalFocusType complexNAVGeographicalFocusType = objectFactoryAIF
					.createComplexNAVGeographicalFocusType();

			ComplexPrincipalExposuresType complexPrincipalExposuresType = objectFactoryAIF
					.createComplexPrincipalExposuresType();

			ComplexShareClassIdentificationType complexShareClassIdentificationType = objectFactoryAIF
					.createComplexShareClassIdentificationType();

			ComplexAllCounterpartyCollateralType complexAllCounterpartyCollateralType = objectFactoryAIF
					.createComplexAllCounterpartyCollateralType();

			// /////////////////////////////////////////////////////////////////
			// <AifReportingInfo>

			// (1) <ReportingMemberState>
			if (ReportUtilities.searchData(reportDatas, "ReportingMemberState",
					"1", null) != null)
				aifReportingInfo.setReportingMemberState(ReportUtilities
						.searchData(reportDatas, "ReportingMemberState", "1",
								null));

			// (2) <Version>
			if (ReportUtilities.searchData(reportDatas, "Version", "2", null) != null)
				aifReportingInfo.setVersion(ReportUtilities.searchData(
						reportDatas, "Version", "2", null));

			// (3) <CreationDateAndTime>
			if (ReportUtilities.searchData(reportDatas, "CreationDateAndTime",
					"3", null) != null) {
				try {
					Date creationDateAndTime = formatDateTime
							.parse(ReportUtilities.searchData(reportDatas,
									"CreationDateAndTime", "3", null));
					XMLGregorianCalendar creationDateAndTimeXML = XMLGregorianCalendarConverter
							.asXMLGregorianCalendarDateTime(creationDateAndTime);
					aifReportingInfo
							.setCreationDateAndTime(creationDateAndTimeXML);
				} catch (ParseException pe) {
					logger.error("Error in formatDateTime to field (3) <CreationDateAndTime>: "
							+ pe.getMessage());
				}
			}

			// /////////////////////////////////////////////////////////////////
			// <AIFRecordInfo>

			// (4) <FilingType>
			if (ReportUtilities
					.searchData(reportDatas, "FilingType", "4", null) != null) {
				FilingTypeType filingTypeType = FilingTypeType
						.valueOf(ReportUtilities.searchData(reportDatas,
								"FilingType", "4", null));
				complexAIFRecordInfoType.setFilingType(filingTypeType);
			}

			// (5) <AIFContentType>
			if (ReportUtilities.searchData(reportDatas, "AIFContentType", "5",
					null) != null)
				complexAIFRecordInfoType.setAIFContentType(ReportUtilities
						.searchData(reportDatas, "AIFContentType", "5", null));

			// (6) <ReportingPeriodStartDate>
			if (ReportUtilities.searchData(reportDatas,
					"ReportingPeriodStartDate", "6", null) != null) {
				try {
					Date reportingPeriodStartDate = formatDate
							.parse(ReportUtilities.searchData(reportDatas,
									"ReportingPeriodStartDate", "6", null));
					XMLGregorianCalendar reportingPeriodStartDateXML = XMLGregorianCalendarConverter
							.asXMLGregorianCalendarDate(reportingPeriodStartDate);
					complexAIFRecordInfoType
							.setReportingPeriodStartDate(reportingPeriodStartDateXML);
				} catch (ParseException pe) {
					logger.error("Error in formatDateTime to field (6) <ReportingPeriodStartDate>: "
							+ pe.getMessage());
				}
			}

			// (7) <ReportingPeriodEndDate>
			if (ReportUtilities.searchData(reportDatas,
					"ReportingPeriodEndDate", "7", null) != null) {
				try {
					Date reportingPeriodEndDate = formatDate
							.parse(ReportUtilities.searchData(reportDatas,
									"ReportingPeriodEndDate", "7", null));
					XMLGregorianCalendar reportingPeriodEndDateXML = XMLGregorianCalendarConverter
							.asXMLGregorianCalendarDate(reportingPeriodEndDate);
					complexAIFRecordInfoType
							.setReportingPeriodEndDate(reportingPeriodEndDateXML);
				} catch (ParseException pe) {
					logger.error("Error in formatDateTime to field (7) <ReportingPeriodEndDate>: "
							+ pe.getMessage());
				}
			}

			// (8) <ReportingPeriodType>
			if (ReportUtilities.searchData(reportDatas, "ReportingPeriodType",
					"8", null) != null) {
				ReportingPeriodTypeType reportingPeriodTypeType = ReportingPeriodTypeType
						.fromValue(ReportUtilities.searchData(reportDatas,
								"ReportingPeriodType", "8", null));
				complexAIFRecordInfoType
						.setReportingPeriodType(reportingPeriodTypeType);
			}

			// (9) <ReportingPeriodYear>
			if (ReportUtilities.searchData(reportDatas, "ReportingPeriodYear",
					"9", null) != null) {
				try {
					Date reportingPeriodYear = formatYear.parse(ReportUtilities
							.searchData(reportDatas, "ReportingPeriodYear",
									"9", null));
					XMLGregorianCalendar reportingPeriodYearXML = XMLGregorianCalendarConverter
							.asXMLGregorianCalendarDate(reportingPeriodYear);
					complexAIFRecordInfoType
							.setReportingPeriodYear(reportingPeriodYearXML);
				} catch (ParseException pe) {
					logger.error("Error in formatDateTime to field (9) <ReportingPeriodYear>: "
							+ pe.getMessage());
				}
			}

			// (10) <AIFReportingObligationChangeFrequencyCode>
			if (ReportUtilities.searchData(reportDatas,
					"AIFReportingObligationChangeFrequencyCode", "10", null) != null) {
				ReportingObligationChangeFrequencyCodeType reportingObligationChangeFrequencyCodeType = ReportingObligationChangeFrequencyCodeType
						.fromValue(ReportUtilities.searchData(reportDatas,
								"AIFReportingObligationChangeFrequencyCode",
								"10", null));
				complexAIFRecordInfoType
						.setAIFReportingObligationChangeFrequencyCode(reportingObligationChangeFrequencyCodeType);
			}

			// (11) <AIFReportingObligationChangeContentsCode>
			if (ReportUtilities.searchData(reportDatas,
					"AIFReportingObligationChangeQuarter", "11", null) != null)
				complexAIFRecordInfoType
						.setAIFReportingObligationChangeContentsCode(new BigInteger(
								ReportUtilities.searchData(reportDatas,
										"AIFReportingObligationChangeQuarter",
										"11", null)));

			// (12) <AIFReportingObligationChangeQuarter>
			if (ReportUtilities.searchData(reportDatas,
					"AIFReportingObligationChangeQuarter", "12", null) != null) {
				ReportingObligationChangeQuarterType reportingObligationChangeQuarterType = ReportingObligationChangeQuarterType
						.fromValue(ReportUtilities.searchData(reportDatas,
								"AIFReportingObligationChangeQuarter", "12",
								null));
				complexAIFRecordInfoType
						.setAIFReportingObligationChangeQuarter(reportingObligationChangeQuarterType);
			}

			// (13) <LastReportingFlag>
			if (ReportUtilities.searchData(reportDatas, "LastReportingFlag",
					"13", null) != null)
				complexAIFRecordInfoType.setLastReportingFlag(Boolean
						.parseBoolean(ReportUtilities.searchData(reportDatas,
								"LastReportingFlag", "13", null)));

			// /////////////////////////////////////////////////////////////////
			// <Assumptions>

			List<ComplexAssumptionType> complexAssumptionTypeList = complexAssumptionsType
					.getAssumption();
			List<Integer> assumptionCounts = new ArrayList<Integer>();
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AssumptionDescription"))
					assumptionCounts.add(Integer.parseInt(reportData
							.getReportDataBlock()));
			}
			for (Integer i : assumptionCounts) {

				// <Assumption>
				ComplexAssumptionType complexAssumptionType = objectFactoryAIF
						.createComplexAssumptionType();

				// (14) <QuestionNumber>
				if (ReportUtilities.searchData(reportDatas, "QuestionNumber",
						"14", Integer.toString(i)) != null)
					complexAssumptionType
							.setQuestionNumber(new BigInteger(ReportUtilities
									.searchData(reportDatas, "QuestionNumber",
											"14", Integer.toString(i))));

				// (15) <AssumptionDescription>
				if (ReportUtilities.searchData(reportDatas,
						"AssumptionDescription", "15", Integer.toString(i)) != null)
					complexAssumptionType
							.setAssumptionDescription(ReportUtilities
									.searchData(reportDatas,
											"AssumptionDescription", "15",
											Integer.toString(i)));

				// < / Assumption>
				complexAssumptionTypeList.add(complexAssumptionType);
			}

			// < / Assumptions>
			complexAIFRecordInfoType.setAssumptions(complexAssumptionsType);

			// /////////////////////////////////////////////////////////////////

			// (16) <AIFMNationalCode>
			if (ReportUtilities.searchData(reportDatas, "AIFMNationalCode",
					"16", null) != null)
				complexAIFRecordInfoType
						.setAIFMNationalCode(ReportUtilities.searchData(
								reportDatas, "AIFMNationalCode", "16", null));

			// (17) <AIFNationalCode>
			if (ReportUtilities.searchData(reportDatas, "AIFNationalCode",
					"17", null) != null)
				complexAIFRecordInfoType
						.setAIFNationalCode(ReportUtilities.searchData(
								reportDatas, "AIFNationalCode", "17", null));

			// (18) <AIFName>
			if (ReportUtilities.searchData(reportDatas, "AIFName", "18", null) != null)
				complexAIFRecordInfoType.setAIFName(ReportUtilities.searchData(
						reportDatas, "AIFName", "18", null));

			// (19) <AIFEEAFlag>
			if (ReportUtilities.searchData(reportDatas, "AIFEEAFlag", "19",
					null) != null)
				complexAIFRecordInfoType.setAIFEEAFlag(Boolean
						.parseBoolean(ReportUtilities.searchData(reportDatas,
								"AIFEEAFlag", "19", null)));

			// (20) <AIFReportingCode>
			if (ReportUtilities.searchData(reportDatas, "AIFReportingCode",
					"20", null) != null)
				complexAIFRecordInfoType.setAIFReportingCode(new BigInteger(
						ReportUtilities.searchData(reportDatas,
								"AIFReportingCode", "20", null)));

			// (21) <AIFDomicile>
			if (ReportUtilities.searchData(reportDatas, "AIFDomicile", "21",
					null) != null)
				complexAIFRecordInfoType.setAIFDomicile(ReportUtilities
						.searchData(reportDatas, "AIFDomicile", "21", null));

			// (22) <InceptionDate>
			if (ReportUtilities.searchData(reportDatas, "InceptionDate", "22",
					null) != null) {
				try {
					Date inceptionDate = formatYear.parse(ReportUtilities
							.searchData(reportDatas, "InceptionDate", "22",
									null));
					XMLGregorianCalendar inceptionDateXML = XMLGregorianCalendarConverter
							.asXMLGregorianCalendarDate(inceptionDate);
					complexAIFRecordInfoType.setInceptionDate(inceptionDateXML);
				} catch (ParseException pe) {
					logger.error("Error in formatDateTime to field (22) <InceptionDate>: "
							+ pe.getMessage());
				}
			}

			// (23) <AIFNoReportingFlag>
			if (ReportUtilities.searchData(reportDatas, "AIFNoReportingFlag",
					"23", null) != null)
				complexAIFRecordInfoType.setAIFNoReportingFlag(Boolean
						.parseBoolean(ReportUtilities.searchData(reportDatas,
								"AIFNoReportingFlag", "23", null)));

			// /////////////////////////////////////////////////////////////////
			// <AIFPrincipalInfo><AIFIdentifier>

			// (24) <AIFIdentifierLEI>
			if (ReportUtilities.searchData(reportDatas, "AIFIdentifierLEI",
					"24", null) != null)
				complexAIFIdentifierType
						.setAIFIdentifierLEI(ReportUtilities.searchData(
								reportDatas, "AIFIdentifierLEI", "24", null));

			// (25) <AIFIdentifierISIN>
			if (ReportUtilities.searchData(reportDatas, "AIFIdentifierISIN",
					"25", null) != null)
				complexAIFIdentifierType.setAIFIdentifierISIN(ReportUtilities
						.searchData(reportDatas, "AIFIdentifierISIN", "25",
								null));

			// (26) <AIFIdentifierCUSIP>
			if (ReportUtilities.searchData(reportDatas, "AIFIdentifierCUSIP",
					"26", null) != null)
				complexAIFIdentifierType.setAIFIdentifierCUSIP(ReportUtilities
						.searchData(reportDatas, "AIFIdentifierCUSIP", "26",
								null));

			// (27) <AIFIdentifierSEDOL>
			if (ReportUtilities.searchData(reportDatas, "AIFIdentifierSEDOL",
					"27", null) != null)
				complexAIFIdentifierType.setAIFIdentifierSEDOL(ReportUtilities
						.searchData(reportDatas, "AIFIdentifierSEDOL", "27",
								null));

			// (28) <AIFIdentifierTicker>
			if (ReportUtilities.searchData(reportDatas, "AIFIdentifierTicker",
					"28", null) != null)
				complexAIFIdentifierType.setAIFIdentifierTicker(ReportUtilities
						.searchData(reportDatas, "AIFIdentifierTicker", "28",
								null));

			// (29) <AIFIdentifierRIC>
			if (ReportUtilities.searchData(reportDatas, "AIFIdentifierRIC",
					"29", null) != null)
				complexAIFIdentifierType
						.setAIFIdentifierRIC(ReportUtilities.searchData(
								reportDatas, "AIFIdentifierRIC", "29", null));

			// (30) <AIFIdentifierECB>
			if (ReportUtilities.searchData(reportDatas, "AIFIdentifierECB",
					"30", null) != null)
				complexAIFIdentifierType
						.setAIFIdentifierECB(ReportUtilities.searchData(
								reportDatas, "AIFIdentifierECB", "30", null));

			// (31) <Old_ReportingMemberState>
			if (ReportUtilities.searchData(reportDatas,
					"Old_ReportingMemberState", "31", null) != null)
				complexAIFNationalIdentifierType
						.setReportingMemberState(ReportUtilities.searchData(
								reportDatas, "Old_ReportingMemberState", "31",
								null));

			// (32) <Old_AIFNationalCode>
			if (ReportUtilities.searchData(reportDatas, "Old_AIFNationalCode",
					"32", null) != null)
				complexAIFNationalIdentifierType
						.setAIFNationalCode(ReportUtilities.searchData(
								reportDatas, "Old_AIFNationalCode", "32", null));

			if (ReportUtilities.searchData(reportDatas,
					"Old_ReportingMemberState", "31", null) != null
					&& ReportUtilities.searchData(reportDatas,
							"Old_AIFNationalCode", "32", null) != null)
				complexAIFIdentifierType
						.setOldAIFIdentifierNCA(complexAIFNationalIdentifierType);

			// /////////////////////////////////////////////////////////////////
			// <ShareClassIdentification>

			List<ComplexShareClassIdentifierType> complexShareClassIdentifierTypeList = complexShareClassIdentificationType
					.getShareClassIdentifier();

			// (33) <ShareClassFlag>
			if (ReportUtilities.searchData(reportDatas, "ShareClassFlag", "33",
					null) != null)
				complexAIFPrincipalInfoType.setShareClassFlag(Boolean
						.parseBoolean(ReportUtilities.searchData(reportDatas,
								"ShareClassFlag", "33", null)));

			List<Integer> complexShareClassIdentifierTypeCounts = new ArrayList<Integer>();
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("ShareClassNationalCode")
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger("34")))
					complexShareClassIdentifierTypeCounts.add(Integer
							.parseInt(reportData.getReportDataBlock()));
			}
			for (Integer i : complexShareClassIdentifierTypeCounts) {

				// <ShareClassIdentifier>
				ComplexShareClassIdentifierType complexShareClassIdentifierType = objectFactoryAIF
						.createComplexShareClassIdentifierType();

				// (34) <ShareClassNationalCode>
				if (ReportUtilities.searchData(reportDatas,
						"ShareClassNationalCode", "34", Integer.toString(i)) != null) {
					complexShareClassIdentifierType
							.setShareClassNationalCode(ReportUtilities
									.searchData(reportDatas,
											"ShareClassNationalCode", "34",
											Integer.toString(i)));
				}

				// (35) <ShareClassIdentifierISIN>
				if (ReportUtilities.searchData(reportDatas,
						"ShareClassIdentifierISIN", "35", Integer.toString(i)) != null) {
					complexShareClassIdentifierType
							.setShareClassIdentifierISIN(ReportUtilities
									.searchData(reportDatas,
											"ShareClassIdentifierISIN", "35",
											Integer.toString(i)));
				}

				// (36) <ShareClassIdentifierSEDOL>
				if (ReportUtilities.searchData(reportDatas,
						"ShareClassIdentifierSEDOL", "36", Integer.toString(i)) != null) {
					complexShareClassIdentifierType
							.setShareClassIdentifierSEDOL(ReportUtilities
									.searchData(reportDatas,
											"ShareClassIdentifierSEDOL", "36",
											Integer.toString(i)));
				}

				// (37) <ShareClassIdentifierCUSIP>
				if (ReportUtilities.searchData(reportDatas,
						"ShareClassIdentifierCUSIP", "37", Integer.toString(i)) != null) {
					complexShareClassIdentifierType
							.setShareClassIdentifierCUSIP(ReportUtilities
									.searchData(reportDatas,
											"ShareClassIdentifierCUSIP", "37",
											Integer.toString(i)));
				}

				// (38) <ShareClassIdentifierTicker>
				if (ReportUtilities
						.searchData(reportDatas, "ShareClassIdentifierTicker",
								"38", Integer.toString(i)) != null) {
					complexShareClassIdentifierType
							.setShareClassIdentifierTicker(ReportUtilities
									.searchData(reportDatas,
											"ShareClassIdentifierTicker", "38",
											Integer.toString(i)));
				}

				// (39) <ShareClassIdentifierRIC>
				if (ReportUtilities.searchData(reportDatas,
						"ShareClassIdentifierRIC", "39", Integer.toString(i)) != null) {
					complexShareClassIdentifierType
							.setShareClassIdentifierRIC(ReportUtilities
									.searchData(reportDatas,
											"ShareClassIdentifierRIC", "39",
											Integer.toString(i)));
				}

				// (40) <ShareClassName>
				if (ReportUtilities.searchData(reportDatas, "ShareClassName",
						"40", Integer.toString(i)) != null) {
					complexShareClassIdentifierType
							.setShareClassName(ReportUtilities.searchData(
									reportDatas, "ShareClassName", "40",
									Integer.toString(i)));
				}

				// < / ShareClassIdentifier>
				complexShareClassIdentifierTypeList
						.add(complexShareClassIdentifierType);
			}

			// <ShareClassIdentification>
			complexAIFPrincipalInfoType
					.setShareClassIdentification(complexShareClassIdentificationType);

			// /////////////////////////////////////////////////////////////////
			// <AIFDescription>

			// (41) <AIFMasterFeederStatus>
			if (ReportUtilities.searchData(reportDatas,
					"AIFMasterFeederStatus", "41", null) != null) {
				AIFMasterFeederStatusType aifMasterFeederStatusType = AIFMasterFeederStatusType
						.fromValue(ReportUtilities.searchData(reportDatas,
								"AIFMasterFeederStatus", "41", null));
				complexAIFDescriptionType
						.setAIFMasterFeederStatus(aifMasterFeederStatusType);
			}

			// <MasterAIFsIdentification>

			List<ComplexMasterAIFIdentificationType> complexMasterAIFIdentificationTypeList = complexMasterAIFsIdentificationType
					.getMasterAIFIdentification();

			List<Integer> complexMasterAIFIdentificationTypeCounts = new ArrayList<Integer>();
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AIFName")
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger("42")))
					complexMasterAIFIdentificationTypeCounts.add(Integer
							.parseInt(reportData.getReportDataBlock()));
			}
			for (Integer i : complexMasterAIFIdentificationTypeCounts) {

				// <MasterAIFIdentification>
				ComplexMasterAIFIdentificationType complexMasterAIFIdentificationType = objectFactoryAIF
						.createComplexMasterAIFIdentificationType();

				// (42) <AIFName>
				if (ReportUtilities.searchData(reportDatas, "AIFName", "42",
						Integer.toString(i)) != null) {
					complexMasterAIFIdentificationType
							.setAIFName(ReportUtilities.searchData(reportDatas,
									"AIFName", "42", Integer.toString(i)));

					// <AIFIdentifierNCA>
					ComplexAIFNationalIdentifierType aifIdentifierNCA = objectFactoryAIF
							.createComplexAIFNationalIdentifierType();

					// (43) <ReportingMemberState>
					if (ReportUtilities.searchData(reportDatas,
							"ReportingMemberState", "43", Integer.toString(i)) != null) {
						aifIdentifierNCA
								.setReportingMemberState(ReportUtilities
										.searchData(reportDatas,
												"ReportingMemberState", "43",
												Integer.toString(i)));
					}
					// (44) <AIFNationalCode>
					if (ReportUtilities.searchData(reportDatas,
							"AIFNationalCode", "44", Integer.toString(i)) != null) {
						aifIdentifierNCA.setAIFNationalCode(ReportUtilities
								.searchData(reportDatas, "AIFNationalCode",
										"44", Integer.toString(i)));
					}

					// < / AIFIdentifierNCA>
					complexMasterAIFIdentificationType
							.setAIFIdentifierNCA(aifIdentifierNCA);

				}

				// < / MasterAIFIdentification>
				complexMasterAIFIdentificationTypeList
						.add(complexMasterAIFIdentificationType);
			}

			// < / MasterAIFsIdentification>
			complexAIFDescriptionType
					.setMasterAIFsIdentification(complexMasterAIFsIdentificationType);

			// /////////////////////////////////////////////////////////////////
			// <PrimeBrokers>

			List<ComplexEntityIdentificationType> complexEntityIdentificationTypeList = complexPrimeBrokersType
					.getPrimeBrokerIdentification();

			List<Integer> complexEntityIdentificationTypeCounts = new ArrayList<Integer>();
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("EntityName")
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger("45")))
					complexEntityIdentificationTypeCounts.add(Integer
							.parseInt(reportData.getReportDataBlock()));
			}
			for (Integer i : complexEntityIdentificationTypeCounts) {

				// <PrimeBrokerIdentification>
				ComplexEntityIdentificationType complexEntityIdentificationType = objectFactoryAIF
						.createComplexEntityIdentificationType();

				// (45) <EntityName>
				if (ReportUtilities.searchData(reportDatas, "EntityName", "45",
						Integer.toString(i)) != null) {
					complexEntityIdentificationType
							.setEntityName(ReportUtilities.searchData(
									reportDatas, "EntityName", "45",
									Integer.toString(i)));
				}

				// (46) <EntityIdentificationLEI>
				if (ReportUtilities.searchData(reportDatas,
						"EntityIdentificationLEI", "46", Integer.toString(i)) != null) {
					complexEntityIdentificationType
							.setEntityIdentificationLEI(ReportUtilities
									.searchData(reportDatas,
											"EntityIdentificationLEI", "46",
											Integer.toString(i)));
				}

				// (47) <EntityIdentificationBIC>
				if (ReportUtilities.searchData(reportDatas,
						"EntityIdentificationBIC", "47", Integer.toString(i)) != null) {
					complexEntityIdentificationType
							.setEntityIdentificationBIC(ReportUtilities
									.searchData(reportDatas,
											"EntityIdentificationBIC", "47",
											Integer.toString(i)));
				}

				// < / PrimeBrokerIdentification>
				complexEntityIdentificationTypeList
						.add(complexEntityIdentificationType);
			}

			// < / PrimeBrokers>
			complexAIFDescriptionType.setPrimeBrokers(complexPrimeBrokersType);

			// /////////////////////////////////////////////////////////////////
			// <AIFBaseCurrencyDescription>

			// (48) <AUMAmountInBaseCurrency>
			if (ReportUtilities.searchData(reportDatas,
					"AUMAmountInBaseCurrency", "48", null) != null)
				complexBaseCurrencyDescriptionType
						.setAUMAmountInBaseCurrency(new BigInteger(
								ReportUtilities.searchData(reportDatas,
										"AUMAmountInBaseCurrency", "48", null)));

			// (49) <BaseCurrency>
			if (ReportUtilities.searchData(reportDatas, "BaseCurrency", "49",
					null) != null)
				complexBaseCurrencyDescriptionType
						.setBaseCurrency(ReportUtilities.searchData(
								reportDatas, "BaseCurrency", "49", null));

			// (50) <FXEURRate>
			if (ReportUtilities
					.searchData(reportDatas, "FXEURRate", "50", null) != null)
				complexBaseCurrencyDescriptionType.setFXEURRate(new BigDecimal(
						ReportUtilities.searchData(reportDatas, "FXEURRate",
								"50", null)));

			// (51) <FXEURReferenceRateType>
			if (ReportUtilities.searchData(reportDatas,
					"FXEURReferenceRateType", "51", null) != null) {
				FXEURReferenceRateTypeType fxEURReferenceRateTypeType = FXEURReferenceRateTypeType
						.fromValue(ReportUtilities.searchData(reportDatas,
								"FXEURReferenceRateType", "51", null));
				complexBaseCurrencyDescriptionType
						.setFXEURReferenceRateType(fxEURReferenceRateTypeType);
			}

			// (52) <FXEUROtherReferenceRateDescription>
			if (ReportUtilities.searchData(reportDatas,
					"FXEUROtherReferenceRateDescription", "52", null) != null)
				complexBaseCurrencyDescriptionType
						.setFXEUROtherReferenceRateDescription(ReportUtilities
								.searchData(reportDatas,
										"FXEUROtherReferenceRateDescription",
										"52", null));

			// (53) <AIFNetAssetValue>
			if (ReportUtilities.searchData(reportDatas, "AIFNetAssetValue",
					"53", null) != null)
				complexAIFDescriptionType.setAIFNetAssetValue(Long
						.parseLong(ReportUtilities.searchData(reportDatas,
								"AIFNetAssetValue", "53", null)));

			// < / AIFBaseCurrencyDescription>
			complexAIFDescriptionType
					.setAIFBaseCurrencyDescription(complexBaseCurrencyDescriptionType);

			// (54) <FirstFundingSourceCountry>
			if (ReportUtilities.searchData(reportDatas,
					"FirstFundingSourceCountry", "54", null) != null)
				complexAIFDescriptionType
						.setFirstFundingSourceCountry(ReportUtilities
								.searchData(reportDatas,
										"FirstFundingSourceCountry", "54", null));

			// (55) <SecondFundingSourceCountry>
			if (ReportUtilities.searchData(reportDatas,
					"SecondFundingSourceCountry", "55", null) != null)
				complexAIFDescriptionType
						.setSecondFundingSourceCountry(ReportUtilities
								.searchData(reportDatas,
										"SecondFundingSourceCountry", "55",
										null));

			// (56) <ThirdFundingSourceCountry>
			if (ReportUtilities.searchData(reportDatas,
					"ThirdFundingSourceCountry", "56", null) != null)
				complexAIFDescriptionType
						.setThirdFundingSourceCountry(ReportUtilities
								.searchData(reportDatas,
										"ThirdFundingSourceCountry", "56", null));

			// (57) <PredominantAIFType>
			if (ReportUtilities.searchData(reportDatas, "PredominantAIFType",
					"57", null) != null) {
				AIFTypeType predominantAIFType = AIFTypeType
						.fromValue(ReportUtilities.searchData(reportDatas,
								"PredominantAIFType", "57", null));
				complexAIFDescriptionType
						.setPredominantAIFType(predominantAIFType);
			}

			// /////////////////////////////////////////////////////////////////
			// <HedgeFundInvestmentStrategies>

			List<ComplexHedgeFundStrategyType> complexHedgeFundStrategyTypeList = complexHedgeFundInvestmentStrategiesType
					.getHedgeFundStrategy();

			List<Integer> complexHedgeFundStrategyTypeCounts = new ArrayList<Integer>();
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("HedgeFundStrategyType")
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger("58")))
					complexHedgeFundStrategyTypeCounts.add(Integer
							.parseInt(reportData.getReportDataBlock()));
			}
			for (Integer i : complexHedgeFundStrategyTypeCounts) {

				// <HedgeFundStrategy>
				ComplexHedgeFundStrategyType complexHedgeFundStrategyType = objectFactoryAIF
						.createComplexHedgeFundStrategyType();

				// (58) <HedgeFundStrategyType>
				if (ReportUtilities.searchData(reportDatas,
						"HedgeFundStrategyType", "58", Integer.toString(i)) != null) {
					HedgeFundStrategyTypeType hedgeFundStrategyTypeType = HedgeFundStrategyTypeType
							.valueOf(ReportUtilities.searchData(reportDatas,
									"HedgeFundStrategyType", "58",
									Integer.toString(i)));
					complexHedgeFundStrategyType
							.setHedgeFundStrategyType(hedgeFundStrategyTypeType);
				}

				// (59) <H_PrimaryStrategyFlag>
				if (ReportUtilities.searchData(reportDatas,
						"H_PrimaryStrategyFlag", "59", Integer.toString(i)) != null) {
					complexHedgeFundStrategyType.setPrimaryStrategyFlag(Boolean
							.parseBoolean(ReportUtilities.searchData(
									reportDatas, "H_PrimarystrategyFlag", "59",
									Integer.toString(i))));
				}

				// (60) <H_StrategyNAVRate>
				if (ReportUtilities.searchData(reportDatas,
						"H_StrategyNAVRate", "60", Integer.toString(i)) != null) {
					complexHedgeFundStrategyType
							.setStrategyNAVRate(new BigDecimal(ReportUtilities
									.searchData(reportDatas,
											"H_StrategyNAVRate", "60",
											Integer.toString(i))));
				}

				// (61) <H_StrategyTypeOtherDescription>
				if (ReportUtilities.searchData(reportDatas,
						"H_StrategyTypeOtherDescription", "61",
						Integer.toString(i)) != null) {
					complexHedgeFundStrategyType
							.setStrategyTypeOtherDescription(ReportUtilities
									.searchData(reportDatas,
											"H_StrategyTypeOtherDescription",
											"61", Integer.toString(i)));
				}

				// < / HedgeFundStrategy>
				complexHedgeFundStrategyTypeList
						.add(complexHedgeFundStrategyType);
			}

			// < / HedgeFundInvestmentStrategies>
			complexAIFDescriptionType
					.setHedgeFundInvestmentStrategies(complexHedgeFundInvestmentStrategiesType);

			// /////////////////////////////////////////////////////////////////
			// <FundOfFundsInvestmentStrategies>

			List<ComplexFundOfFundsStrategyType> complexFundOfFundsStrategyTypeList = complexFundOfFundsInvestmentStrategiesType
					.getFundOfFundsStrategy();

			List<Integer> complexFundOfFundsStrategyTypeCounts = new ArrayList<Integer>();
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("FundOfFundsStrategyType")
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger("58")))
					complexFundOfFundsStrategyTypeCounts.add(Integer
							.parseInt(reportData.getReportDataBlock()));
			}
			for (Integer i : complexFundOfFundsStrategyTypeCounts) {

				// <FundOfFundsStrategy>
				ComplexFundOfFundsStrategyType complexFundOfFundsStrategyType = objectFactoryAIF
						.createComplexFundOfFundsStrategyType();

				// (58) <FundOfFundsStrategyType>
				if (ReportUtilities.searchData(reportDatas,
						"FundOfFundsStrategyType", "58", Integer.toString(i)) != null) {
					FundOfFundsStrategyTypeType fundOfFundsStrategyTypeType = FundOfFundsStrategyTypeType
							.valueOf(ReportUtilities.searchData(reportDatas,
									"FundOfFundsStrategyType", "58",
									Integer.toString(i)));
					complexFundOfFundsStrategyType
							.setFundOfFundsStrategyType(fundOfFundsStrategyTypeType);
				}

				// (59) <F_PrimaryStrategyFlag>
				if (ReportUtilities.searchData(reportDatas,
						"F_PrimaryStrategyFlag", "59", Integer.toString(i)) != null) {
					complexFundOfFundsStrategyType
							.setPrimaryStrategyFlag(Boolean
									.parseBoolean(ReportUtilities.searchData(
											reportDatas,
											"F_PrimarystrategyFlag", "59",
											Integer.toString(i))));
				}

				// (60) <F_StrategyNAVRate>
				if (ReportUtilities.searchData(reportDatas,
						"F_StrategyNAVRate", "60", Integer.toString(i)) != null) {
					complexFundOfFundsStrategyType
							.setStrategyNAVRate(new BigDecimal(ReportUtilities
									.searchData(reportDatas,
											"F_StrategyNAVRate", "60",
											Integer.toString(i))));
				}

				// (61) <F_StrategyTypeOtherDescription>
				if (ReportUtilities.searchData(reportDatas,
						"F_StrategyTypeOtherDescription", "61",
						Integer.toString(i)) != null) {
					complexFundOfFundsStrategyType
							.setStrategyTypeOtherDescription(ReportUtilities
									.searchData(reportDatas,
											"F_StrategyTypeOtherDescription",
											"61", Integer.toString(i)));
				}

				// < / FundOfFundsStrategy>
				complexFundOfFundsStrategyTypeList
						.add(complexFundOfFundsStrategyType);
			}

			// < / FundOfFundsInvestmentStrategies>
			complexAIFDescriptionType
					.setFundOfFundsInvestmentStrategies(complexFundOfFundsInvestmentStrategiesType);

			// /////////////////////////////////////////////////////////////////
			// <OtherFundInvestmentStrategies>

			List<ComplexOtherFundStrategyType> complexOtherFundStrategyTypeList = complexOtherFundInvestmentStrategiesType
					.getOtherFundStrategy();

			List<Integer> complexOtherFundStrategyTypeCounts = new ArrayList<Integer>();
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("OtherFundStrategyType")
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger("58")))
					complexOtherFundStrategyTypeCounts.add(Integer
							.parseInt(reportData.getReportDataBlock()));
			}
			for (Integer i : complexOtherFundStrategyTypeCounts) {

				// <OtherFundStrategy>
				ComplexOtherFundStrategyType complexOtherFundStrategyType = objectFactoryAIF
						.createComplexOtherFundStrategyType();

				// (58) <OtherFundStrategyType>
				if (ReportUtilities.searchData(reportDatas,
						"OtherFundStrategyType", "58", Integer.toString(i)) != null) {
					OtherFundStrategyTypeType otherFundStrategyTypeType = OtherFundStrategyTypeType
							.valueOf(ReportUtilities.searchData(reportDatas,
									"OtherFundStrategyType", "58",
									Integer.toString(i)));
					complexOtherFundStrategyType
							.setOtherFundStrategyType(otherFundStrategyTypeType);
				}

				// (59) <O_PrimaryStrategyFlag>
				if (ReportUtilities.searchData(reportDatas,
						"O_PrimaryStrategyFlag", "59", Integer.toString(i)) != null) {
					complexOtherFundStrategyType.setPrimaryStrategyFlag(Boolean
							.parseBoolean(ReportUtilities.searchData(
									reportDatas, "O_PrimarystrategyFlag", "59",
									Integer.toString(i))));
				}

				// (60) <O_StrategyNAVRate>
				if (ReportUtilities.searchData(reportDatas,
						"O_StrategyNAVRate", "60", Integer.toString(i)) != null) {
					complexOtherFundStrategyType
							.setStrategyNAVRate(new BigDecimal(ReportUtilities
									.searchData(reportDatas,
											"O_StrategyNAVRate", "60",
											Integer.toString(i))));
				}

				// (61) <O_StrategyTypeOtherDescription>
				if (ReportUtilities.searchData(reportDatas,
						"O_StrategyTypeOtherDescription", "61",
						Integer.toString(i)) != null) {
					complexOtherFundStrategyType
							.setStrategyTypeOtherDescription(ReportUtilities
									.searchData(reportDatas,
											"O_StrategyTypeOtherDescription",
											"61", Integer.toString(i)));
				}

				// < / OtherFundStrategy>
				complexOtherFundStrategyTypeList
						.add(complexOtherFundStrategyType);
			}

			// < / OtherFundInvestmentStrategies>
			complexAIFDescriptionType
					.setOtherFundInvestmentStrategies(complexOtherFundInvestmentStrategiesType);

			// /////////////////////////////////////////////////////////////////
			// <PrivateEquityFundInvestmentStrategies>

			List<ComplexPrivateEquityFundStrategyType> complexPrivateEquityFundStrategyTypeList = complexPrivateEquityFundInvestmentStrategiesType
					.getPrivateEquityFundInvestmentStrategy();

			List<Integer> complexPrivateEquityFundStrategyTypeCounts = new ArrayList<Integer>();
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("PrivateEquityFundStrategyType")
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger("58")))
					complexPrivateEquityFundStrategyTypeCounts.add(Integer
							.parseInt(reportData.getReportDataBlock()));
			}
			for (Integer i : complexPrivateEquityFundStrategyTypeCounts) {

				// <PrivateEquityFundInvestmentStrategy>
				ComplexPrivateEquityFundStrategyType complexPrivateEquityFundStrategyType = objectFactoryAIF
						.createComplexPrivateEquityFundStrategyType();

				// (58) <PrivateEquityFundStrategyType>
				if (ReportUtilities.searchData(reportDatas,
						"PrivateEquityFundStrategyType", "58",
						Integer.toString(i)) != null) {
					PrivateEquityFundStrategyTypeType privateEquityFundStrategyTypeType = PrivateEquityFundStrategyTypeType
							.valueOf(ReportUtilities.searchData(reportDatas,
									"PrivateEquityFundStrategyType", "58",
									Integer.toString(i)));
					complexPrivateEquityFundStrategyType
							.setPrivateEquityFundStrategyType(privateEquityFundStrategyTypeType);
				}

				// (59) <P_PrimaryStrategyFlag>
				if (ReportUtilities.searchData(reportDatas,
						"P_PrimaryStrategyFlag", "59", Integer.toString(i)) != null) {
					complexPrivateEquityFundStrategyType
							.setPrimaryStrategyFlag(Boolean
									.parseBoolean(ReportUtilities.searchData(
											reportDatas,
											"P_PrimarystrategyFlag", "59",
											Integer.toString(i))));
				}

				// (60) <P_StrategyNAVRate>
				if (ReportUtilities.searchData(reportDatas,
						"P_StrategyNAVRate", "60", Integer.toString(i)) != null) {
					complexPrivateEquityFundStrategyType
							.setStrategyNAVRate(new BigDecimal(ReportUtilities
									.searchData(reportDatas,
											"P_StrategyNAVRate", "60",
											Integer.toString(i))));
				}

				// (61) <P_StrategyTypeOtherDescription>
				if (ReportUtilities.searchData(reportDatas,
						"P_StrategyTypeOtherDescription", "61",
						Integer.toString(i)) != null) {
					complexPrivateEquityFundStrategyType
							.setStrategyTypeOtherDescription(ReportUtilities
									.searchData(reportDatas,
											"P_StrategyTypeOtherDescription",
											"61", Integer.toString(i)));
				}

				// </ PrivateEquityFundInvestmentStrategy>
				complexPrivateEquityFundStrategyTypeList
						.add(complexPrivateEquityFundStrategyType);
			}

			// < / PrivateEquityFundInvestmentStrategies>
			complexAIFDescriptionType
					.setPrivateEquityFundInvestmentStrategies(complexPrivateEquityFundInvestmentStrategiesType);

			// /////////////////////////////////////////////////////////////////
			// <RealEstateFundStrategies>

			List<ComplexRealEstateFundStrategyType> complexRealEstateFundStrategyTypeList = complexRealEstateFundInvestmentStrategiesType
					.getRealEstateFundStrategy();

			List<Integer> complexRealEstateFundStrategyTypeCounts = new ArrayList<Integer>();
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("RealEstateFundStrategyType")
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger("58")))
					complexRealEstateFundStrategyTypeCounts.add(Integer
							.parseInt(reportData.getReportDataBlock()));
			}
			for (Integer i : complexRealEstateFundStrategyTypeCounts) {

				// <RealEstateFundStrategy>
				ComplexRealEstateFundStrategyType complexRealEstateFundStrategyType = objectFactoryAIF
						.createComplexRealEstateFundStrategyType();

				// (58) <RealEstateFundStrategyType>
				if (ReportUtilities
						.searchData(reportDatas, "RealEstateFundStrategyType",
								"58", Integer.toString(i)) != null) {
					RealEstateFundStrategyTypeType realEstateFundStrategyTypeType = RealEstateFundStrategyTypeType
							.valueOf(ReportUtilities.searchData(reportDatas,
									"RealEstateFundStrategyType", "58",
									Integer.toString(i)));
					complexRealEstateFundStrategyType
							.setRealEstateFundStrategyType(realEstateFundStrategyTypeType);
				}

				// (59) <R_PrimaryStrategyFlag>
				if (ReportUtilities.searchData(reportDatas,
						"R_PrimaryStrategyFlag", "59", Integer.toString(i)) != null) {
					complexRealEstateFundStrategyType
							.setPrimaryStrategyFlag(Boolean
									.parseBoolean(ReportUtilities.searchData(
											reportDatas,
											"R_PrimarystrategyFlag", "59",
											Integer.toString(i))));
				}

				// (60) <R_StrategyNAVRate>
				if (ReportUtilities.searchData(reportDatas,
						"R_StrategyNAVRate", "60", Integer.toString(i)) != null) {
					complexRealEstateFundStrategyType
							.setStrategyNAVRate(new BigDecimal(ReportUtilities
									.searchData(reportDatas,
											"R_StrategyNAVRate", "60",
											Integer.toString(i))));
				}

				// (61) <R_StrategyTypeOtherDescription>
				if (ReportUtilities.searchData(reportDatas,
						"R_StrategyTypeOtherDescription", "61",
						Integer.toString(i)) != null) {
					complexRealEstateFundStrategyType
							.setStrategyTypeOtherDescription(ReportUtilities
									.searchData(reportDatas,
											"R_StrategyTypeOtherDescription",
											"61", Integer.toString(i)));
				}

				// < / RealEstateFundStrategy>
				complexRealEstateFundStrategyTypeList
						.add(complexRealEstateFundStrategyType);
			}

			// <RealEstateFundStrategies>
			complexAIFDescriptionType
					.setRealEstateFundInvestmentStrategies(complexRealEstateFundInvestmentStrategiesType);

			// (62) <HFTTransactionNumber>
			if (ReportUtilities.searchData(reportDatas, "HFTTransactionNumber",
					"62", null) != null)
				complexAIFDescriptionType
						.setHFTTransactionNumber(new BigInteger(ReportUtilities
								.searchData(reportDatas,
										"HFTTransactionNumber", "62", null)));

			// (63) <HFTBuySellMarketValue>
			if (ReportUtilities.searchData(reportDatas,
					"HFTBuySellMarketValue", "63", null) != null)
				complexAIFDescriptionType
						.setHFTBuySellMarketValue(new BigInteger(
								ReportUtilities.searchData(reportDatas,
										"HFTBuySellMarketValue", "63", null)));

			// < / AIFDescription>

			// /////////////////////////////////////////////////////////////////
			// <MainInstrumentsTraded>

			List<ComplexMainInstrumentTradedType> complexMainInstrumentTradedTypeList = complexMainInstrumentsTradedType
					.getMainInstrumentTraded();

			List<Integer> complexMainInstrumentTradedTypeCounts = new ArrayList<Integer>();
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("Ranking")
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger("64")))
					complexMainInstrumentTradedTypeCounts.add(Integer
							.parseInt(reportData.getReportDataBlock()));
			}
			Collections.sort(complexMainInstrumentTradedTypeCounts);
			for (Integer i : complexMainInstrumentTradedTypeCounts) {

				// <MainInstrumentTraded>
				ComplexMainInstrumentTradedType complexMainInstrumentTradedType = objectFactoryAIF
						.createComplexMainInstrumentTradedType();

				// (64) <Ranking>
				if (ReportUtilities.searchData(reportDatas, "Ranking", "64",
						Integer.toString(i)) != null) {
					complexMainInstrumentTradedType.setRanking(new BigInteger(
							ReportUtilities.searchData(reportDatas, "Ranking",
									"64", Integer.toString(i))));
				}

				// (65) <SubAssetType>
				if (ReportUtilities.searchData(reportDatas, "SubAssetType",
						"65", Integer.toString(i)) != null) {
					SubAssetTypeType subAssetTypeType = SubAssetTypeType
							.valueOf(ReportUtilities.searchData(reportDatas,
									"SubAssetType", "65", Integer.toString(i)));
					complexMainInstrumentTradedType
							.setSubAssetType(subAssetTypeType);
				}

				// (66) <InstrumentCodeType>
				if (ReportUtilities.searchData(reportDatas,
						"InstrumentCodeType", "66", Integer.toString(i)) != null) {
					InstrumentCodeTypeType instrumentCodeTypeType = InstrumentCodeTypeType
							.valueOf(ReportUtilities.searchData(reportDatas,
									"InstrumentCodeType", "66",
									Integer.toString(i)));
					complexMainInstrumentTradedType
							.setInstrumentCodeType(instrumentCodeTypeType);
				}

				// (67) <InstrumentName>
				if (ReportUtilities.searchData(reportDatas, "InstrumentName",
						"67", Integer.toString(i)) != null) {
					complexMainInstrumentTradedType
							.setInstrumentName(ReportUtilities.searchData(
									reportDatas, "InstrumentName", "67",
									Integer.toString(i)));
				}

				// (68) <ISINInstrumentIdentification>
				if (ReportUtilities.searchData(reportDatas,
						"ISINInstrumentIdentification", "68",
						Integer.toString(i)) != null) {
					complexMainInstrumentTradedType
							.setISINInstrumentIdentification(ReportUtilities
									.searchData(reportDatas,
											"ISINInstrumentIdentification",
											"68", Integer.toString(i)));
				}

				// <AIIInstrumentIdentification>
				ComplexAIIInstrumentIdentificationType complexAIIInstrumentIdentificationType = objectFactoryAIF
						.createComplexAIIInstrumentIdentificationType();

				// (69) <AIIExchangeCode>
				if (ReportUtilities.searchData(reportDatas, "AIIExchangeCode",
						"69", Integer.toString(i)) != null) {
					complexAIIInstrumentIdentificationType
							.setAIIExchangeCode(ReportUtilities.searchData(
									reportDatas, "AIIExchangeCode", "69",
									Integer.toString(i)));
				}

				// (70) <AIIProductCode>
				if (ReportUtilities.searchData(reportDatas, "AIIProductCode",
						"70", Integer.toString(i)) != null) {
					complexAIIInstrumentIdentificationType
							.setAIIProductCode(ReportUtilities.searchData(
									reportDatas, "AIIProductCode", "70",
									Integer.toString(i)));
				}

				// (71) <AIIDerivativeType>
				if (ReportUtilities.searchData(reportDatas,
						"AIIDerivativeType", "71", Integer.toString(i)) != null) {
					AIIDerivativeTypeType aIIDerivativeTypeType = AIIDerivativeTypeType
							.valueOf(ReportUtilities.searchData(reportDatas,
									"AIIDerivativeType", "71",
									Integer.toString(i)));
					complexAIIInstrumentIdentificationType
							.setAIIDerivativeType(aIIDerivativeTypeType);
				}

				// (72) <AIIPutCallIdentifier>
				if (ReportUtilities.searchData(reportDatas,
						"AIIPutCallIdentifier", "72", Integer.toString(i)) != null) {
					AIIPutCallIdentifierType aIIPutCallIdentifierType = AIIPutCallIdentifierType
							.valueOf(ReportUtilities.searchData(reportDatas,
									"AIIPutCallIdentifier", "72",
									Integer.toString(i)));
					complexAIIInstrumentIdentificationType
							.setAIIPutCallIdentifier(aIIPutCallIdentifierType);
				}

				// (73) <AIIExpiryDate>
				if (ReportUtilities.searchData(reportDatas, "AIIExpiryDate",
						"73", Integer.toString(i)) != null) {
					try {
						Date aIIExpiryDate = formatDate.parse(ReportUtilities
								.searchData(reportDatas, "AIIExpiryDate", "73",
										Integer.toString(i)));
						XMLGregorianCalendar aIIExpiryDateXML = XMLGregorianCalendarConverter
								.asXMLGregorianCalendarDate(aIIExpiryDate);
						complexAIIInstrumentIdentificationType
								.setAIIExpiryDate(aIIExpiryDateXML);
					} catch (ParseException pe) {
						logger.error("Error in formatDateTime to field (73) <AIIExpiryDate>: "
								+ pe.getMessage());
					}
				}

				// (74) <AIIStrikePrice>
				if (ReportUtilities.searchData(reportDatas, "AIIStrikePrice",
						"74", Integer.toString(i)) != null) {
					complexAIIInstrumentIdentificationType
							.setAIIStrikePrice(new BigDecimal(ReportUtilities
									.searchData(reportDatas, "AIIStrikePrice",
											"74", Integer.toString(i))));
				}

				// < / AIIInstrumentIdentification>
				complexMainInstrumentTradedType
						.setAIIInstrumentIdentification(complexAIIInstrumentIdentificationType);

				// (75) <PositionType>
				if (ReportUtilities.searchData(reportDatas, "PositionType",
						"75", Integer.toString(i)) != null) {
					PositionTypeType positionTypeType = PositionTypeType
							.valueOf(ReportUtilities.searchData(reportDatas,
									"PositionType", "75", Integer.toString(i)));
					complexMainInstrumentTradedType
							.setPositionType(positionTypeType);
				}

				// (76) <PositionValue>
				if (ReportUtilities.searchData(reportDatas, "PositionValue",
						"76", Integer.toString(i)) != null) {
					complexMainInstrumentTradedType
							.setPositionValue(new BigInteger(ReportUtilities
									.searchData(reportDatas, "PositionValue",
											"76", Integer.toString(i))));
				}

				// (77) <ShortPositionHedgingRate>
				if (ReportUtilities.searchData(reportDatas,
						"ShortPositionHedgingRate", "77", Integer.toString(i)) != null) {
					complexMainInstrumentTradedType
							.setShortPositionHedgingRate(new BigDecimal(
									ReportUtilities.searchData(reportDatas,
											"ShortPositionHedgingRate", "77",
											Integer.toString(i))));
				}

				// < / MainInstrumentTraded>
				complexMainInstrumentTradedTypeList
						.add(complexMainInstrumentTradedType);
			}

			// /////////////////////////////////////////////////////////////////
			// <NAVGeographicalFocus>

			// (78) <AfricaNAVRate>
			if (ReportUtilities.searchData(reportDatas, "AfricaNAVRate", "78",
					null) != null)
				complexNAVGeographicalFocusType
						.setAfricaNAVRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "AfricaNAVRate", "78",
										null)));

			// (79) <AsiaPacificNAVRate>
			if (ReportUtilities.searchData(reportDatas, "AsiaPacificNAVRate",
					"79", null) != null)
				complexNAVGeographicalFocusType
						.setAsiaPacificNAVRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "AsiaPacificNAVRate",
										"79", null)));

			// (80) <EuropeNAVRate>
			if (ReportUtilities.searchData(reportDatas, "EuropeNAVRate", "80",
					null) != null)
				complexNAVGeographicalFocusType
						.setEuropeNAVRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "EuropeNAVRate", "80",
										null)));

			// (81) <EEANAVRate>
			if (ReportUtilities.searchData(reportDatas, "EEANAVRate", "81",
					null) != null)
				complexNAVGeographicalFocusType.setEEANAVRate(new BigDecimal(
						ReportUtilities.searchData(reportDatas, "EEANAVRate",
								"81", null)));

			// (82) <MiddleEastNAVRate>
			if (ReportUtilities.searchData(reportDatas, "MiddleEastNAVRate",
					"82", null) != null)
				complexNAVGeographicalFocusType
						.setMiddleEastNAVRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "MiddleEastNAVRate",
										"82", null)));

			// (83) <NorthAmericaNAVRate>
			if (ReportUtilities.searchData(reportDatas, "NorthAmericaNAVRate",
					"83", null) != null)
				complexNAVGeographicalFocusType
						.setNorthAmericaNAVRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "NorthAmericaNAVRate",
										"83", null)));

			// (84) <SouthAmericaNAVRate>
			if (ReportUtilities.searchData(reportDatas, "SouthAmericaNAVRate",
					"84", null) != null)
				complexNAVGeographicalFocusType
						.setSouthAmericaNAVRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "SouthAmericaNAVRate",
										"84", null)));

			// (85) <SupraNationalNAVRate>
			if (ReportUtilities.searchData(reportDatas, "SupraNationalNAVRate",
					"85", null) != null)
				complexNAVGeographicalFocusType
						.setSupraNationalNAVRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas,
										"SupraNationalNAVRate", "85", null)));

			// /////////////////////////////////////////////////////////////////
			// <AUMGeographicalFocus>

			// (86) <AfricaAUMRate>
			if (ReportUtilities.searchData(reportDatas, "AfricaAUMRate", "86",
					null) != null)
				complexAUMGeographicalFocusType
						.setAfricaAUMRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "AfricaAUMRate", "86",
										null)));

			// (87) <AsiaPacificAUMRate>
			if (ReportUtilities.searchData(reportDatas, "AsiaPacificAUMRate",
					"87", null) != null)
				complexAUMGeographicalFocusType
						.setAsiaPacificAUMRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "AsiaPacificAUMRate",
										"87", null)));

			// (88) <EuropeAUMRate>
			if (ReportUtilities.searchData(reportDatas, "EuropeAUMRate", "88",
					null) != null)
				complexAUMGeographicalFocusType
						.setEuropeAUMRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "EuropeAUMRate", "88",
										null)));

			// (89) <EEAAUMRate>
			if (ReportUtilities.searchData(reportDatas, "EEAAUMRate", "89",
					null) != null)
				complexAUMGeographicalFocusType.setEEAAUMRate(new BigDecimal(
						ReportUtilities.searchData(reportDatas, "EEAAUMRate",
								"89", null)));

			// (90) <MiddleEastAUMRate>
			if (ReportUtilities.searchData(reportDatas, "MiddleEastAUMRate",
					"90", null) != null)
				complexAUMGeographicalFocusType
						.setMiddleEastAUMRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "MiddleEastAUMRate",
										"90", null)));

			// (91) <NorthAmericaAUMRate>
			if (ReportUtilities.searchData(reportDatas, "NorthAmericaAUMRate",
					"91", null) != null)
				complexAUMGeographicalFocusType
						.setNorthAmericaAUMRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "NorthAmericaAUMRate",
										"91", null)));

			// (92) <SouthAmericaAUMRate>
			if (ReportUtilities.searchData(reportDatas, "SouthAmericaAUMRate",
					"92", null) != null)
				complexAUMGeographicalFocusType
						.setSouthAmericaAUMRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "SouthAmericaAUMRate",
										"92", null)));

			// (93) <SupraNationalAUMRate>
			if (ReportUtilities.searchData(reportDatas, "SupraNationalAUMRate",
					"93", null) != null)
				complexAUMGeographicalFocusType
						.setSupraNationalAUMRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas,
										"SupraNationalAUMRate", "93", null)));

			// /////////////////////////////////////////////////////////////////
			// <PrincipalExposures>

			List<ComplexPrincipalExposureType> complexPrincipalExposureTypeList = complexPrincipalExposuresType
					.getPrincipalExposure();

			List<Integer> complexPrincipalExposureTypeCounts = new ArrayList<Integer>();
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("Ranking")
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger("94")))
					complexPrincipalExposureTypeCounts.add(Integer
							.parseInt(reportData.getReportDataBlock()));
			}
			Collections.sort(complexPrincipalExposureTypeCounts);
			for (Integer i : complexPrincipalExposureTypeCounts) {

				// <PrincipalExposure>
				ComplexPrincipalExposureType complexPrincipalExposureType = objectFactoryAIF
						.createComplexPrincipalExposureType();

				// (94) <Ranking>
				if (ReportUtilities.searchData(reportDatas, "Ranking", "94",
						Integer.toString(i)) != null) {
					complexPrincipalExposureType.setRanking(new BigInteger(
							ReportUtilities.searchData(reportDatas, "Ranking",
									"94", Integer.toString(i))));
				}

				// (95) <AssetMacroType>
				if (ReportUtilities.searchData(reportDatas, "AssetMacroType",
						"95", Integer.toString(i)) != null) {
					AssetMacroTypeType assetMacroTypeType = AssetMacroTypeType
							.valueOf(ReportUtilities.searchData(reportDatas,
									"AssetMacroType", "95", Integer.toString(i)));
					complexPrincipalExposureType
							.setAssetMacroType(assetMacroTypeType);
				}

				// (96) <SubAssetType>
				if (ReportUtilities.searchData(reportDatas, "SubAssetType",
						"96", Integer.toString(i)) != null) {
					SubAssetTypeType subAssetTypeType = SubAssetTypeType
							.valueOf(ReportUtilities.searchData(reportDatas,
									"SubAssetType", "96", Integer.toString(i)));
					complexPrincipalExposureType
							.setSubAssetType(subAssetTypeType);
				}

				// (97) <PositionType>
				if (ReportUtilities.searchData(reportDatas, "PositionType",
						"97", Integer.toString(i)) != null) {
					PositionTypeType positionTypeType = PositionTypeType
							.valueOf(ReportUtilities.searchData(reportDatas,
									"PositionType", "97", Integer.toString(i)));
					complexPrincipalExposureType
							.setPositionType(positionTypeType);
				}

				// (98) <AggregatedValueAmount>
				if (ReportUtilities.searchData(reportDatas,
						"AggregatedValueAmount", "98", Integer.toString(i)) != null) {
					complexPrincipalExposureType
							.setAggregatedValueAmount(new BigInteger(
									ReportUtilities.searchData(reportDatas,
											"AggregatedValueAmount", "98",
											Integer.toString(i))));
				}

				// (99) <AggregatedValueRate>
				if (ReportUtilities.searchData(reportDatas,
						"AggregatedValueRate", "99", Integer.toString(i)) != null) {
					complexPrincipalExposureType
							.setAggregatedValueRate(new BigDecimal(
									ReportUtilities.searchData(reportDatas,
											"AggregatedValueRate", "99",
											Integer.toString(i))));
				}

				// <CounterpartyIdentification>
				ComplexEntityIdentificationType complexEntityIdentificationType = objectFactoryAIF
						.createComplexEntityIdentificationType();

				// (100) <EntityName>
				if (ReportUtilities.searchData(reportDatas, "EntityName",
						"100", Integer.toString(i)) != null) {
					complexEntityIdentificationType
							.setEntityName(ReportUtilities.searchData(
									reportDatas, "EntityName", "100",
									Integer.toString(i)));
				}

				// (101) <EntityIdentificationLEI>
				if (ReportUtilities.searchData(reportDatas,
						"EntityIdentificationLEI", "101", Integer.toString(i)) != null) {
					complexEntityIdentificationType
							.setEntityIdentificationLEI(ReportUtilities
									.searchData(reportDatas,
											"EntityIdentificationLEI", "101",
											Integer.toString(i)));
				}

				// (102) <EntityIdentificationBIC>
				if (ReportUtilities.searchData(reportDatas,
						"EntityIdentificationBIC", "102", Integer.toString(i)) != null) {
					complexEntityIdentificationType
							.setEntityIdentificationBIC(ReportUtilities
									.searchData(reportDatas,
											"EntityIdentificationBIC", "102",
											Integer.toString(i)));
				}

				// < / CounterpartyIdentification>
				complexPrincipalExposureType
						.setCounterpartyIdentification(complexEntityIdentificationType);

				// < / PrincipalExposure>
				complexPrincipalExposureTypeList
						.add(complexPrincipalExposureType);
			}

			// /////////////////////////////////////////////////////////////////
			// <PortfolioConcentrations>

			List<ComplexPortfolioConcentrationType> complexPortfolioConcentrationTypeList = complexPortfolioConcentrationsType
					.getPortfolioConcentration();

			List<Integer> complexPortfolioConcentrationTypeCounts = new ArrayList<Integer>();
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("Ranking")
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger("103")))
					complexPortfolioConcentrationTypeCounts.add(Integer
							.parseInt(reportData.getReportDataBlock()));
			}
			Collections.sort(complexPortfolioConcentrationTypeCounts);
			for (Integer i : complexPortfolioConcentrationTypeCounts) {

				// <PortfolioConcentration>
				ComplexPortfolioConcentrationType complexPortfolioConcentrationType = objectFactoryAIF
						.createComplexPortfolioConcentrationType();

				// (103) <Ranking>
				if (ReportUtilities.searchData(reportDatas, "Ranking", "103",
						Integer.toString(i)) != null) {
					complexPortfolioConcentrationType
							.setRanking(new BigInteger(ReportUtilities
									.searchData(reportDatas, "Ranking", "103",
											Integer.toString(i))));
				}

				// (104) <AssetType>
				if (ReportUtilities.searchData(reportDatas, "AssetType", "104",
						Integer.toString(i)) != null) {
					AssetTypeType assetTypeType = AssetTypeType
							.valueOf(ReportUtilities.searchData(reportDatas,
									"AssetType", "104", Integer.toString(i)));
					complexPortfolioConcentrationType
							.setAssetType(assetTypeType);
				}

				// (105) <PositionType>
				if (ReportUtilities.searchData(reportDatas, "PositionType",
						"105", Integer.toString(i)) != null) {
					PositionTypeType positionTypeType = PositionTypeType
							.valueOf(ReportUtilities.searchData(reportDatas,
									"PositionType", "105", Integer.toString(i)));
					complexPortfolioConcentrationType
							.setPositionType(positionTypeType);
				}

				// <MarketIdentification>
				ComplexMarketIdentificationWithoutNOTType complexMarketIdentificationWithoutNOTType = objectFactoryAIF
						.createComplexMarketIdentificationWithoutNOTType();

				// (106) <MarketCodeType>
				if (ReportUtilities.searchData(reportDatas, "MarketCodeType",
						"106", Integer.toString(i)) != null) {
					MarketCodeTypeWithoutNOTType marketCodeTypeWithoutNOTType = MarketCodeTypeWithoutNOTType
							.valueOf(ReportUtilities.searchData(reportDatas,
									"MarketCodeType", "106",
									Integer.toString(i)));
					complexMarketIdentificationWithoutNOTType
							.setMarketCodeType(marketCodeTypeWithoutNOTType);
				}

				// (107) <MarketCode>
				if (ReportUtilities.searchData(reportDatas, "MarketCode",
						"107", Integer.toString(i)) != null) {
					complexMarketIdentificationWithoutNOTType
							.setMarketCode(ReportUtilities.searchData(
									reportDatas, "MarketCode", "107",
									Integer.toString(i)));
				}

				// < / MarketIdentification>
				complexPortfolioConcentrationType
						.setMarketIdentification(complexMarketIdentificationWithoutNOTType);

				// (108) <AggregatedValueAmount>
				if (ReportUtilities.searchData(reportDatas,
						"AggregatedValueAmount", "108", Integer.toString(i)) != null) {
					complexPortfolioConcentrationType
							.setAggregatedValueAmount(new BigInteger(
									ReportUtilities.searchData(reportDatas,
											"AggregatedValueAmount", "108",
											Integer.toString(i))));
				}

				// (109) <AggregatedValueRate>
				if (ReportUtilities.searchData(reportDatas,
						"AggregatedValueRate", "109", Integer.toString(i)) != null) {
					complexPortfolioConcentrationType
							.setAggregatedValueRate(new BigDecimal(
									ReportUtilities.searchData(reportDatas,
											"AggregatedValueRate", "109",
											Integer.toString(i))));
				}

				// <CounterpartyIdentification>
				ComplexEntityIdentificationType complexEntityIdentificationType = objectFactoryAIF
						.createComplexEntityIdentificationType();

				// (110) <EntityName>
				if (ReportUtilities.searchData(reportDatas, "EntityName",
						"110", Integer.toString(i)) != null) {
					complexEntityIdentificationType
							.setEntityName(ReportUtilities.searchData(
									reportDatas, "EntityName", "110",
									Integer.toString(i)));
				}

				// (111) <EntityIdentificationLEI>
				if (ReportUtilities.searchData(reportDatas,
						"EntityIdentificationLEI", "111", Integer.toString(i)) != null) {
					complexEntityIdentificationType
							.setEntityIdentificationLEI(ReportUtilities
									.searchData(reportDatas,
											"EntityIdentificationLEI", "111",
											Integer.toString(i)));
				}

				// (112) <EntityIdentificationBIC>
				if (ReportUtilities.searchData(reportDatas,
						"EntityIdentificationBIC", "112", Integer.toString(i)) != null) {
					complexEntityIdentificationType
							.setEntityIdentificationBIC(ReportUtilities
									.searchData(reportDatas,
											"EntityIdentificationBIC", "112",
											Integer.toString(i)));
				}

				// < / CounterpartyIdentification>
				complexPortfolioConcentrationType
						.setCounterpartyIdentification(complexEntityIdentificationType);

				// < / PortfolioConcentration>
				complexPortfolioConcentrationTypeList
						.add(complexPortfolioConcentrationType);
			}

			// < / PortfolioConcentrations>
			complexMostImportantConcentrationType
					.setPortfolioConcentrations(complexPortfolioConcentrationsType);

			// /////////////////////////////////////////////////////////////////
			// <Typical Position Size>

			// (113) <TypicalPositionSize>
			if (ReportUtilities.searchData(reportDatas, "TypicalPositionSize",
					"113", null) != null) {
				TypicalPositionSizeType typicalPositionSizeType = TypicalPositionSizeType
						.fromValue(ReportUtilities.searchData(reportDatas,
								"TypicalPositionSize", "113", null));
				complexMostImportantConcentrationType
						.setTypicalPositionSize(typicalPositionSizeType);
			}

			// /////////////////////////////////////////////////////////////////
			// <AIFPrincipalMarkets>

			List<ComplexThreePrincipalMarketType> complexThreePrincipalMarketTypeList = complexAIFPrincipalMarketsType
					.getAIFPrincipalMarket();

			List<Integer> complexThreePrincipalMarketTypeCounts = new ArrayList<Integer>();
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("Ranking")
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger("114")))
					complexThreePrincipalMarketTypeCounts.add(Integer
							.parseInt(reportData.getReportDataBlock()));
			}
			Collections.sort(complexThreePrincipalMarketTypeCounts);
			for (Integer i : complexThreePrincipalMarketTypeCounts) {

				// <AIFPrincipalMarket>
				ComplexThreePrincipalMarketType complexThreePrincipalMarketType = objectFactoryAIF
						.createComplexThreePrincipalMarketType();

				// (114) <Ranking>
				if (ReportUtilities.searchData(reportDatas, "Ranking", "114",
						Integer.toString(i)) != null)
					complexThreePrincipalMarketType.setRanking(new BigInteger(
							ReportUtilities.searchData(reportDatas, "Ranking",
									"114", Integer.toString(i))));

				// <MarketIdentification>
				ComplexMarketIdentificationWithNOTType complexMarketIdentificationWithNOTType = objectFactoryAIF
						.createComplexMarketIdentificationWithNOTType();

				// (115) <MarketCodeType>
				if (ReportUtilities.searchData(reportDatas, "MarketCodeType",
						"115", Integer.toString(i)) != null) {
					MarketCodeTypeWithNOTType marketCodeTypeWithNOTType = MarketCodeTypeWithNOTType
							.valueOf(ReportUtilities.searchData(reportDatas,
									"MarketCodeType", "115",
									Integer.toString(i)));
					complexMarketIdentificationWithNOTType
							.setMarketCodeType(marketCodeTypeWithNOTType);
				}

				// (116) <MarketCode>
				if (ReportUtilities.searchData(reportDatas, "MarketCode",
						"116", Integer.toString(i)) != null)
					complexMarketIdentificationWithNOTType
							.setMarketCode(ReportUtilities.searchData(
									reportDatas, "MarketCode", "116",
									Integer.toString(i)));

				// < / MarketIdentification>
				complexThreePrincipalMarketType
						.setMarketIdentification(complexMarketIdentificationWithNOTType);

				// (117) <AggregatedValueAmount>
				if (ReportUtilities.searchData(reportDatas,
						"AggregatedValueAmount", "117", Integer.toString(i)) != null)
					complexThreePrincipalMarketType
							.setAggregatedValueAmount(new BigInteger(
									ReportUtilities.searchData(reportDatas,
											"AggregatedValueAmount", "117",
											Integer.toString(i))));

				// < / AIFPrincipalMarket>
				complexThreePrincipalMarketTypeList
						.add(complexThreePrincipalMarketType);
			}

			// < / AIFPrincipalMarkets>
			complexMostImportantConcentrationType
					.setAIFPrincipalMarkets(complexAIFPrincipalMarketsType);

			// /////////////////////////////////////////////////////////////////
			// <InvestorConcentration>

			// (118) <MainBeneficialOwnersRate>
			if (ReportUtilities.searchData(reportDatas,
					"MainBeneficialOwnersRate", "118", null) != null)
				complexInvestorConcentrationType
						.setMainBeneficialOwnersRate(new BigDecimal(
								ReportUtilities
										.searchData(reportDatas,
												"MainBeneficialOwnersRate",
												"118", null)));

			// (119) <ProfessionalInvestorConcentrationRate>
			if (ReportUtilities.searchData(reportDatas,
					"ProfessionalInvestorConcentrationRate", "119", null) != null)
				complexInvestorConcentrationType
						.setProfessionalInvestorConcentrationRate(new BigDecimal(
								ReportUtilities
										.searchData(
												reportDatas,
												"ProfessionalInvestorConcentrationRate",
												"119", null)));

			// (120) <RetailInvestorConcentrationRate>
			if (ReportUtilities.searchData(reportDatas,
					"RetailInvestorConcentrationRate", "120", null) != null)
				complexInvestorConcentrationType
						.setRetailInvestorConcentrationRate(new BigDecimal(
								ReportUtilities.searchData(reportDatas,
										"RetailInvestorConcentrationRate",
										"120", null)));

			// < / InvestorConcentration>
			complexMostImportantConcentrationType
					.setInvestorConcentration(complexInvestorConcentrationType);

			// /////////////////////////////////////////////////////////////////
			// <IndividualExposure>

			List<ComplexAssetTypeExposureType> complexAssetTypeExposureTypeList = complexAssetTypeExposuresType
					.getAssetTypeExposure();

			List<Integer> complexAssetTypeExposureTypeCounts = new ArrayList<Integer>();
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("SubAssetType")
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger("121")))
					complexAssetTypeExposureTypeCounts.add(Integer
							.parseInt(reportData.getReportDataBlock()));
			}
			for (Integer i : complexAssetTypeExposureTypeCounts) {

				// <AssetTypeExposures>
				ComplexAssetTypeExposureType complexAssetTypeExposureType = objectFactoryAIF
						.createComplexAssetTypeExposureType();

				// (121) <SubAssetType>
				if (ReportUtilities.searchData(reportDatas, "SubAssetType",
						"121", Integer.toString(i)) != null) {
					SubAssetTypeType subAssetTypeType = SubAssetTypeType
							.valueOf(ReportUtilities.searchData(reportDatas,
									"SubAssetType", "121", Integer.toString(i)));
					complexAssetTypeExposureType
							.setSubAssetType(subAssetTypeType);
				}

				// (122) <GrossValue>
				if (ReportUtilities.searchData(reportDatas, "GrossValue",
						"122", Integer.toString(i)) != null)
					complexAssetTypeExposureType.setGrossValue(new BigInteger(
							ReportUtilities.searchData(reportDatas,
									"GrossValue", "122", Integer.toString(i))));

				// (123) <LongValue>
				if (ReportUtilities.searchData(reportDatas, "LongValue", "123",
						Integer.toString(i)) != null)
					complexAssetTypeExposureType.setLongValue(new BigInteger(
							ReportUtilities.searchData(reportDatas,
									"LongValue", "123", Integer.toString(i))));

				// (124) <ShortValue>
				if (ReportUtilities.searchData(reportDatas, "ShortValue",
						"124", Integer.toString(i)) != null)
					complexAssetTypeExposureType.setShortValue(new BigInteger(
							ReportUtilities.searchData(reportDatas,
									"ShortValue", "124", Integer.toString(i))));

				// < / AssetTypeExposures>
				complexAssetTypeExposureTypeList
						.add(complexAssetTypeExposureType);
			}

			// < / IndividualExposure>
			complexIndividualExposureType
					.setAssetTypeExposures(complexAssetTypeExposuresType);

			// /////////////////////////////////////////////////////////////////
			// <AssetTypeTurnovers>

			List<ComplexAssetTypeTurnoverType> complexAssetTypeTurnoverTypeList = complexAssetTypeTurnoversType
					.getAssetTypeTurnover();

			List<Integer> complexAssetTypeTurnoverTypeCounts = new ArrayList<Integer>();
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("TurnoverSubAssetType")
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger("125")))
					complexAssetTypeTurnoverTypeCounts.add(Integer
							.parseInt(reportData.getReportDataBlock()));
			}
			for (Integer i : complexAssetTypeTurnoverTypeCounts) {

				// <AssetTypeTurnover>
				ComplexAssetTypeTurnoverType complexAssetTypeTurnoverType = objectFactoryAIF
						.createComplexAssetTypeTurnoverType();

				// (125) <TurnoverSubAssetType>
				if (ReportUtilities.searchData(reportDatas,
						"TurnoverSubAssetType", "125", Integer.toString(i)) != null) {
					TurnoverSubAssetTypeType turnoverSubAssetTypeType = TurnoverSubAssetTypeType
							.valueOf(ReportUtilities.searchData(reportDatas,
									"TurnoverSubAssetType", "125",
									Integer.toString(i)));
					complexAssetTypeTurnoverType
							.setTurnoverSubAssetType(turnoverSubAssetTypeType);
				}

				// (126) <MarketValue>
				if (ReportUtilities.searchData(reportDatas, "MarketValue",
						"126", Integer.toString(i)) != null)
					complexAssetTypeTurnoverType
							.setMarketValue(new BigInteger(ReportUtilities
									.searchData(reportDatas, "MarketValue",
											"126", Integer.toString(i))));

				// (127) <NotionalValue>
				if (ReportUtilities.searchData(reportDatas, "NotionalValue",
						"127", Integer.toString(i)) != null)
					complexAssetTypeTurnoverType
							.setNotionalValue(new BigInteger(ReportUtilities
									.searchData(reportDatas, "NotionalValue",
											"127", Integer.toString(i))));

				// < / AssetTypeTurnover>
				complexAssetTypeTurnoverTypeList
						.add(complexAssetTypeTurnoverType);
			}

			// < / AssetTypeTurnovers>
			complexIndividualExposureType
					.setAssetTypeTurnovers(complexAssetTypeTurnoversType);

			// /////////////////////////////////////////////////////////////////
			// <CurrencyExposures>

			List<ComplexCurrencyExposureType> complexCurrencyExposureTypeList = complexCurrencyExposuresType
					.getCurrencyExposure();

			List<Integer> complexCurrencyExposureTypeCounts = new ArrayList<Integer>();
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("ExposureCurrency")
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger("128")))
					complexCurrencyExposureTypeCounts.add(Integer
							.parseInt(reportData.getReportDataBlock()));
			}
			for (Integer i : complexCurrencyExposureTypeCounts) {

				// <CurrencyExposure>
				ComplexCurrencyExposureType complexCurrencyExposureType = objectFactoryAIF
						.createComplexCurrencyExposureType();

				// (128) <ExposureCurrency>
				if (ReportUtilities.searchData(reportDatas, "ExposureCurrency",
						"128", Integer.toString(i)) != null)
					complexCurrencyExposureType
							.setExposureCurrency(ReportUtilities.searchData(
									reportDatas, "ExposureCurrency", "128",
									Integer.toString(i)));

				// (129) <LongPositionValue>
				if (ReportUtilities.searchData(reportDatas,
						"LongPositionValue", "129", Integer.toString(i)) != null)
					complexCurrencyExposureType
							.setLongPositionValue(new BigInteger(
									ReportUtilities.searchData(reportDatas,
											"LongPositionValue", "129",
											Integer.toString(i))));

				// (130) <ShortPositionValue>
				if (ReportUtilities.searchData(reportDatas,
						"ShortPositionValue", "130", Integer.toString(i)) != null)
					complexCurrencyExposureType
							.setShortPositionValue(new BigInteger(
									ReportUtilities.searchData(reportDatas,
											"ShortPositionValue", "130",
											Integer.toString(i))));

				// < / CurrencyExposure>
				complexCurrencyExposureTypeList
						.add(complexCurrencyExposureType);
			}

			// < / CurrencyExposures>
			complexIndividualExposureType
					.setCurrencyExposures(complexCurrencyExposuresType);

			// /////////////////////////////////////////////////////////////////
			// <CompanyDominantInfluence>

			List<ComplexCompanyDominantInfluenceType> complexCompanyDominantInfluenceTypeList = complexCompaniesDominantInfluenceType
					.getCompanyDominantInfluence();

			List<Integer> complexCompanyDominantInfluenceTypeCounts = new ArrayList<Integer>();
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("EntityName")
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger("131")))
					complexCompanyDominantInfluenceTypeCounts.add(Integer
							.parseInt(reportData.getReportDataBlock()));
			}
			for (Integer i : complexCompanyDominantInfluenceTypeCounts) {

				// <CompanyDominantInfluence>
				ComplexCompanyDominantInfluenceType complexCompanyDominantInfluenceType = objectFactoryAIF
						.createComplexCompanyDominantInfluenceType();

				// <CompanyIdentification>
				ComplexEntityIdentificationType complexEntityIdentificationType = objectFactoryAIF
						.createComplexEntityIdentificationType();

				// (131) <EntityName>
				if (ReportUtilities.searchData(reportDatas, "EntityName",
						"131", Integer.toString(i)) != null)
					complexEntityIdentificationType
							.setEntityName(ReportUtilities.searchData(
									reportDatas, "EntityName", "131",
									Integer.toString(i)));

				// (132) <EntityIdentificationLEI>
				if (ReportUtilities.searchData(reportDatas,
						"EntityIdentificationLEI", "132", Integer.toString(i)) != null)
					complexEntityIdentificationType
							.setEntityIdentificationLEI(ReportUtilities
									.searchData(reportDatas,
											"EntityIdentificationLEI", "132",
											Integer.toString(i)));

				// (133) <EntityIdentificationBIC>
				if (ReportUtilities.searchData(reportDatas,
						"EntityIdentificationBIC", "133", Integer.toString(i)) != null)
					complexEntityIdentificationType
							.setEntityIdentificationBIC(ReportUtilities
									.searchData(reportDatas,
											"EntityIdentificationBIC", "133",
											Integer.toString(i)));

				// < / CompanyIdentification>
				complexCompanyDominantInfluenceType
						.setCompanyIdentification(complexEntityIdentificationType);

				// (134) <TransactionType>
				if (ReportUtilities.searchData(reportDatas, "TransactionType",
						"134", Integer.toString(i)) != null) {
					TransactionTypeType transactionTypeType = TransactionTypeType
							.valueOf(ReportUtilities.searchData(reportDatas,
									"TransactionType", "134",
									Integer.toString(i)));
					complexCompanyDominantInfluenceType
							.setTransactionType(transactionTypeType);
				}

				// (135) <OtherTransactionTypeDescription>
				if (ReportUtilities.searchData(reportDatas,
						"OtherTransactionTypeDescription", "135",
						Integer.toString(i)) != null)
					complexCompanyDominantInfluenceType
							.setOtherTransactionTypeDescription(ReportUtilities
									.searchData(reportDatas,
											"OtherTransactionTypeDescription",
											"135", Integer.toString(i)));

				// (136) <VotingRightsRate>
				if (ReportUtilities.searchData(reportDatas, "VotingRightsRate",
						"136", Integer.toString(i)) != null)
					complexCompanyDominantInfluenceType
							.setVotingRightsRate(new BigDecimal(ReportUtilities
									.searchData(reportDatas,
											"VotingRightsRate", "136",
											Integer.toString(i))));

				// < / CompanyDominantInfluence>
				complexCompanyDominantInfluenceTypeList
						.add(complexCompanyDominantInfluenceType);
			}

			// < / CompanyDominantInfluence>
			complexIndividualExposureType
					.setCompaniesDominantInfluence(complexCompaniesDominantInfluenceType);

			// /////////////////////////////////////////////////////////////////
			// <RiskProfile>

			// <MarketRiskProfile>
			ComplexMarketRiskMeasuresType complexMarketRiskMeasuresType = objectFactoryAIF
					.createComplexMarketRiskMeasuresType();

			List<ComplexMarketRiskMeasureType> complexMarketRiskMeasureTypeList = complexMarketRiskMeasuresType
					.getMarketRiskMeasure();

			// (137) <AnnualInvestmentReturnRate>
			if (ReportUtilities.searchData(reportDatas,
					"AnnualInvestmentReturnRate", "137", null) != null)
				complexMarketRiskProfileType
						.setAnnualInvestmentReturnRate(ReportUtilities
								.searchData(reportDatas,
										"AnnualInvestmentReturnRate", "137",
										null));

			List<Integer> complexMarketRiskMeasureTypeCounts = new ArrayList<Integer>();
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("RiskMeasureType")
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger("138")))
					complexMarketRiskMeasureTypeCounts.add(Integer
							.parseInt(reportData.getReportDataBlock()));
			}
			for (Integer i : complexMarketRiskMeasureTypeCounts) {

				// <MarketRiskMeasures>
				ComplexMarketRiskMeasureType complexMarketRiskMeasureType = objectFactoryAIF
						.createComplexMarketRiskMeasureType();

				// (138) <RiskMeasureType>
				if (ReportUtilities.searchData(reportDatas, "RiskMeasureType",
						"138", Integer.toString(i)) != null) {
					RiskMeasureTypeType riskMeasureTypeType = RiskMeasureTypeType
							.fromValue(ReportUtilities.searchData(reportDatas,
									"RiskMeasureType", "138",
									Integer.toString(i)));
					complexMarketRiskMeasureType
							.setRiskMeasureType(riskMeasureTypeType);
				}

				// (139) <RiskMeasureValue>
				if (ReportUtilities.searchData(reportDatas, "RiskMeasureValue",
						"139", Integer.toString(i)) != null)
					complexMarketRiskMeasureType
							.setRiskMeasureValue(new BigDecimal(ReportUtilities
									.searchData(reportDatas,
											"RiskMeasureValue", "139",
											Integer.toString(i))));

				// <BucketRiskMeasureValues>
				ComplexBucketRiskMeasureValuesType complexBucketRiskMeasureValuesType = objectFactoryAIF
						.createComplexBucketRiskMeasureValuesType();

				// (140) <LessFiveYearsRiskMeasureValue>
				if (ReportUtilities.searchData(reportDatas,
						"LessFiveYearsRiskMeasureValue", "140",
						Integer.toString(i)) != null)
					complexBucketRiskMeasureValuesType
							.setLessFiveYearsRiskMeasureValue(new BigDecimal(
									ReportUtilities.searchData(reportDatas,
											"LessFiveYearsRiskMeasureValue",
											"140", Integer.toString(i))));

				// (141) <FifthteenYearsRiskMeasureValue>
				if (ReportUtilities.searchData(reportDatas,
						"FifthteenYearsRiskMeasureValue", "141",
						Integer.toString(i)) != null)
					complexBucketRiskMeasureValuesType
							.setFifthteenYearsRiskMeasureValue(new BigDecimal(
									ReportUtilities.searchData(reportDatas,
											"FifthteenYearsRiskMeasureValue",
											"141", Integer.toString(i))));

				// (142) <MoreFifthteenYearsRiskMeasureValue>
				if (ReportUtilities.searchData(reportDatas,
						"MoreFifthteenYearsRiskMeasureValue", "142",
						Integer.toString(i)) != null)
					complexBucketRiskMeasureValuesType
							.setMoreFifthteenYearsRiskMeasureValue(new BigDecimal(
									ReportUtilities
											.searchData(
													reportDatas,
													"MoreFifthteenYearsRiskMeasureValue",
													"142", Integer.toString(i))));

				// < / BucketRiskMeasureValues>
				complexMarketRiskMeasureType
						.setBucketRiskMeasureValues(complexBucketRiskMeasureValuesType);

				// <VegaRiskMeasureValues>
				ComplexVegaRiskMeasureValuesType complexVegaRiskMeasureValuesType = objectFactoryAIF
						.createComplexVegaRiskMeasureValuesType();

				// (143) <CurrentMarketRiskMeasureValue>
				if (ReportUtilities.searchData(reportDatas,
						"CurrentMarketRiskMeasureValue", "143",
						Integer.toString(i)) != null)
					complexVegaRiskMeasureValuesType
							.setCurrentMarketRiskMeasureValue(new BigDecimal(
									ReportUtilities.searchData(reportDatas,
											"CurrentMarketRiskMeasureValue",
											"143", Integer.toString(i))));

				// (144) <LowerMarketRiskMeasureValue>
				if (ReportUtilities.searchData(reportDatas,
						"LowerMarketRiskMeasureValue", "144",
						Integer.toString(i)) != null)
					complexVegaRiskMeasureValuesType
							.setLowerMarketRiskMeasureValue(new BigDecimal(
									ReportUtilities.searchData(reportDatas,
											"LowerMarketRiskMeasureValue",
											"144", Integer.toString(i))));

				// (145) <HigherMarketRiskMeasureValue>
				if (ReportUtilities.searchData(reportDatas,
						"HigherMarketRiskMeasureValue", "145",
						Integer.toString(i)) != null)
					complexVegaRiskMeasureValuesType
							.setHigherMarketRiskMeasureValue(new BigDecimal(
									ReportUtilities.searchData(reportDatas,
											"HigherMarketRiskMeasureValue",
											"145", Integer.toString(i))));

				// < / VegaRiskMeasureValues>
				complexMarketRiskMeasureType
						.setVegaRiskMeasureValues(complexVegaRiskMeasureValuesType);

				// <VARRiskMeasureValues>
				ComplexVARRiskMeasureValuesType complexVARRiskMeasureValuesType = objectFactoryAIF
						.createComplexVARRiskMeasureValuesType();

				// (146) <VARCalculationMethodCodeType>
				if (ReportUtilities.searchData(reportDatas,
						"VARCalculationMethodCodeType", "146",
						Integer.toString(i)) != null) {
					VARCalculationMethodCodeTypeType vARCalculationMethodCodeTypeType = VARCalculationMethodCodeTypeType
							.valueOf(ReportUtilities.searchData(reportDatas,
									"VARCalculationMethodCodeType", "146",
									Integer.toString(i)));
					complexVARRiskMeasureValuesType
							.setVARCalculationMethodCodeType(vARCalculationMethodCodeTypeType);
				}

				// (147) <RiskMeasureDescription>
				if (ReportUtilities.searchData(reportDatas,
						"RiskMeasureDescription", "147", Integer.toString(i)) != null)
					complexMarketRiskMeasureType
							.setRiskMeasureDescription(ReportUtilities
									.searchData(reportDatas,
											"RiskMeasureDescription", "147",
											Integer.toString(i)));

				// (302) <VARValue>
				if (ReportUtilities.searchData(reportDatas, "VARValue", "302",
						Integer.toString(i)) != null)
					complexVARRiskMeasureValuesType.setVARValue(new BigDecimal(
							ReportUtilities.searchData(reportDatas, "VARValue",
									"302", Integer.toString(i))));

				// < / VARRiskMeasureValues>
				complexMarketRiskMeasureType
						.setVARRiskMeasureValues(complexVARRiskMeasureValuesType);

				// < / MarketRiskMeasures>
				complexMarketRiskMeasureTypeList
						.add(complexMarketRiskMeasureType);
			}

			// < / MarketRiskProfile>
			complexRiskProfileType
					.setMarketRiskProfile(complexMarketRiskProfileType);

			// < / RiskProfile>
			complexMarketRiskProfileType
					.setMarketRiskMeasures(complexMarketRiskMeasuresType);

			// /////////////////////////////////////////////////////////////////
			// <CounterpartyRiskProfile>

			// /////////////////////////////////////////////////////////////////
			// <TradingClearingMechanism>

			ComplexTradingClearingMechanismType complexTradingClearingMechanismType = objectFactoryAIF
					.createComplexTradingClearingMechanismType();

			// <TradedSecurities>
			ComplexMarketRate3P2Type complexTradedSecurities = objectFactoryAIF
					.createComplexMarketRate3P2Type();

			// (148) <RegulatedMarketRate>
			if (ReportUtilities.searchData(reportDatas, "RegulatedMarketRate",
					"148", null) != null)
				complexTradedSecurities.setRegulatedMarketRate(new BigDecimal(
						ReportUtilities.searchData(reportDatas,
								"RegulatedMarketRate", "148", null)));

			// (149) <OTCRate>
			if (ReportUtilities.searchData(reportDatas, "OTCRate", "149", null) != null)
				complexTradedSecurities.setOTCRate(new BigDecimal(
						ReportUtilities.searchData(reportDatas, "OTCRate",
								"149", null)));

			// < / TradedSecurities>
			complexTradingClearingMechanismType
					.setTradedSecurities(complexTradedSecurities);

			// <TradedDerivatives>
			ComplexMarketRate3P2Type complexMarketRate3P2Type = objectFactoryAIF
					.createComplexMarketRate3P2Type();

			// (150) <RegulatedMarketRate>
			if (ReportUtilities.searchData(reportDatas, "RegulatedMarketRate",
					"150", null) != null)
				complexMarketRate3P2Type.setRegulatedMarketRate(new BigDecimal(
						ReportUtilities.searchData(reportDatas,
								"RegulatedMarketRate", "150", null)));

			// (151) <OTCRate>
			if (ReportUtilities.searchData(reportDatas, "OTCRate", "151", null) != null)
				complexMarketRate3P2Type.setOTCRate(new BigDecimal(
						ReportUtilities.searchData(reportDatas, "OTCRate",
								"151", null)));

			// < / TradedDerivatives>
			complexTradingClearingMechanismType
					.setTradedDerivatives(complexMarketRate3P2Type);

			// <ClearedDerivativesRate>
			ComplexClearedDerivativesRateType complexClearedDerivativesRateType = objectFactoryAIF
					.createComplexClearedDerivativesRateType();

			// (152) <CCPRate>
			if (ReportUtilities.searchData(reportDatas, "CCPRate", "152", null) != null)
				complexClearedDerivativesRateType.setCCPRate(new BigDecimal(
						ReportUtilities.searchData(reportDatas, "CCPRate",
								"152", null)));

			// (153) <BilateralClearingRate>
			if (ReportUtilities.searchData(reportDatas,
					"BilateralClearingRate", "153", null) != null)
				complexClearedDerivativesRateType
						.setBilateralClearingRate(new BigDecimal(
								ReportUtilities.searchData(reportDatas,
										"BilateralClearingRate", "153", null)));

			// < / ClearedDerivativesRate>
			complexTradingClearingMechanismType
					.setClearedDerivativesRate(complexClearedDerivativesRateType);

			// <ClearedReposRate>
			ComplexClearedReposRateType complexClearedReposRateType = objectFactoryAIF
					.createComplexClearedReposRateType();

			// (154) <CCPRate>
			if (ReportUtilities.searchData(reportDatas, "CCPRate", "154", null) != null)
				complexClearedReposRateType.setCCPRate(new BigDecimal(
						ReportUtilities.searchData(reportDatas, "CCPRate",
								"154", null)));

			// (155) <BilateralClearingRate>
			if (ReportUtilities.searchData(reportDatas,
					"BilateralClearingRate", "155", null) != null)
				complexClearedReposRateType
						.setBilateralClearingRate(new BigDecimal(
								ReportUtilities.searchData(reportDatas,
										"BilateralClearingRate", "155", null)));

			// (156) <TriPartyRepoClearingRate>
			if (ReportUtilities.searchData(reportDatas,
					"TriPartyRepoClearingRate", "156", null) != null)
				complexClearedReposRateType
						.setTriPartyRepoClearingRate(new BigDecimal(
								ReportUtilities
										.searchData(reportDatas,
												"TriPartyRepoClearingRate",
												"156", null)));

			// < / ClearedReposRate>
			complexTradingClearingMechanismType
					.setClearedReposRate(complexClearedReposRateType);

			// < / TradingClearingMechanism>
			complexCounterpartyRiskProfileType
					.setTradingClearingMechanism(complexTradingClearingMechanismType);

			// /////////////////////////////////////////////////////////////////
			// <AllCounterpartyCollateral>

			// (157) <AllCounterpartyCollateralCash>
			if (ReportUtilities.searchData(reportDatas,
					"AllCounterpartyCollateralCash", "157", null) != null)
				complexAllCounterpartyCollateralType
						.setAllCounterpartyCollateralCash(new BigInteger(
								ReportUtilities.searchData(reportDatas,
										"AllCounterpartyCollateralCash", "157",
										null)));

			// (158) <AllCounterpartyCollateralSecurities>
			if (ReportUtilities.searchData(reportDatas,
					"AllCounterpartyCollateralSecurities", "158", null) != null)
				complexAllCounterpartyCollateralType
						.setAllCounterpartyCollateralSecurities(new BigInteger(
								ReportUtilities.searchData(reportDatas,
										"AllCounterpartyCollateralSecurities",
										"158", null)));

			// (159) <AllCounterpartyOtherCollateralPosted>
			if (ReportUtilities.searchData(reportDatas,
					"AllCounterpartyOtherCollateralPosted", "159", null) != null)
				complexAllCounterpartyCollateralType
						.setAllCounterpartyOtherCollateralPosted(new BigInteger(
								ReportUtilities.searchData(reportDatas,
										"AllCounterpartyOtherCollateralPosted",
										"159", null)));

			// < / AllCounterpartyCollateral>
			complexCounterpartyRiskProfileType
					.setAllCounterpartyCollateral(complexAllCounterpartyCollateralType);

			// /////////////////////////////////////////////////////////////////
			// <FundToCounterpartyExposures>
			ComplexFundToCounterpartyExposuresType complexFundToCounterpartyExposuresType = objectFactoryAIF
					.createComplexFundToCounterpartyExposuresType();

			List<ComplexCounterpartyExposureType> complexFundCounterpartyExposureTypeList = complexFundToCounterpartyExposuresType
					.getFundToCounterpartyExposure();

			List<Integer> complexFundCounterpartyExposureTypeCounts = new ArrayList<Integer>();
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("Ranking")
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger("160")))
					complexFundCounterpartyExposureTypeCounts.add(Integer
							.parseInt(reportData.getReportDataBlock()));
			}
			Collections.sort(complexFundCounterpartyExposureTypeCounts);
			for (Integer i : complexFundCounterpartyExposureTypeCounts) {

				// <FundToCounterpartyExposure>
				ComplexCounterpartyExposureType complexCounterpartyExposureType = objectFactoryAIF
						.createComplexCounterpartyExposureType();

				// (160) <Ranking>
				if (ReportUtilities.searchData(reportDatas, "Ranking", "160",
						Integer.toString(i)) != null)
					complexCounterpartyExposureType.setRanking(new BigInteger(
							ReportUtilities.searchData(reportDatas, "Ranking",
									"160", Integer.toString(i))));

				// (161) <CounterpartyExposureFlag>
				if (ReportUtilities.searchData(reportDatas,
						"CounterpartyExposureFlag", "161", Integer.toString(i)) != null)
					complexCounterpartyExposureType
							.setCounterpartyExposureFlag(Boolean
									.parseBoolean(ReportUtilities.searchData(
											reportDatas,
											"CounterpartyExposureFlag", "161",
											Integer.toString(i))));

				// <CounterpartyIdentification>
				ComplexEntityIdentificationType complexEntityIdentificationType = objectFactoryAIF
						.createComplexEntityIdentificationType();

				// (162) <EntityName>
				if (ReportUtilities.searchData(reportDatas, "EntityName",
						"162", Integer.toString(i)) != null)
					complexEntityIdentificationType
							.setEntityName(ReportUtilities.searchData(
									reportDatas, "EntityName", "162",
									Integer.toString(i)));

				// (163) <EntityIdentificationLEI>
				if (ReportUtilities.searchData(reportDatas,
						"EntityIdentificationLEI", "163", Integer.toString(i)) != null)
					complexEntityIdentificationType
							.setEntityIdentificationLEI(ReportUtilities
									.searchData(reportDatas,
											"EntityIdentificationLEI", "163",
											Integer.toString(i)));

				// (164) <EntityIdentificationBIC>
				if (ReportUtilities.searchData(reportDatas,
						"EntityIdentificationBIC", "164", Integer.toString(i)) != null)
					complexEntityIdentificationType
							.setEntityIdentificationBIC(ReportUtilities
									.searchData(reportDatas,
											"EntityIdentificationBIC", "164",
											Integer.toString(i)));

				// < / CounterpartyIdentification>
				complexCounterpartyExposureType
						.setCounterpartyIdentification(complexEntityIdentificationType);

				// (165) <CounterpartyTotalExposureRate>
				if (ReportUtilities.searchData(reportDatas,
						"CounterpartyTotalExposureRate", "165",
						Integer.toString(i)) != null)
					complexCounterpartyExposureType
							.setCounterpartyTotalExposureRate(new BigDecimal(
									ReportUtilities.searchData(reportDatas,
											"CounterpartyTotalExposureRate",
											"165", Integer.toString(i))));

				// < / FundToCounterpartyExposure>
				complexFundCounterpartyExposureTypeList
						.add(complexCounterpartyExposureType);
			}

			// < / FundToCounterpartyExposures>
			complexCounterpartyRiskProfileType
					.setFundToCounterpartyExposures(complexFundToCounterpartyExposuresType);

			// /////////////////////////////////////////////////////////////////
			// <CounterpartyToFundExposures>
			ComplexCounterpartyToFundExposuresType complexCounterpartyToFundExposuresType = objectFactoryAIF
					.createComplexCounterpartyToFundExposuresType();

			List<ComplexCounterpartyExposureType> complexCounterpartyExposureTypeList = complexCounterpartyToFundExposuresType
					.getCounterpartyToFundExposure();

			List<Integer> complexCounterpartyExposureTypeCounts = new ArrayList<Integer>();
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("Ranking")
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger("166")))
					complexCounterpartyExposureTypeCounts.add(Integer
							.parseInt(reportData.getReportDataBlock()));
			}
			Collections.sort(complexCounterpartyExposureTypeCounts);
			for (Integer i : complexCounterpartyExposureTypeCounts) {

				// <CounterpartyToFundExposure>
				ComplexCounterpartyExposureType complexCounterpartyExposureType = objectFactoryAIF
						.createComplexCounterpartyExposureType();

				// (166) <Ranking>
				if (ReportUtilities.searchData(reportDatas, "Ranking", "166",
						Integer.toString(i)) != null)
					complexCounterpartyExposureType.setRanking(new BigInteger(
							ReportUtilities.searchData(reportDatas, "Ranking",
									"166", Integer.toString(i))));

				// (167) <CounterpartyExposureFlag>
				if (ReportUtilities.searchData(reportDatas,
						"CounterpartyExposureFlag", "167", Integer.toString(i)) != null)
					complexCounterpartyExposureType
							.setCounterpartyExposureFlag(Boolean
									.parseBoolean(ReportUtilities.searchData(
											reportDatas,
											"CounterpartyExposureFlag", "167",
											Integer.toString(i))));

				// <CounterpartyIdentification>
				ComplexEntityIdentificationType complexEntityIdentificationType = objectFactoryAIF
						.createComplexEntityIdentificationType();

				// (168) <EntityName>
				if (ReportUtilities.searchData(reportDatas, "EntityName",
						"168", Integer.toString(i)) != null)
					complexEntityIdentificationType
							.setEntityName(ReportUtilities.searchData(
									reportDatas, "EntityName", "168",
									Integer.toString(i)));

				// (169) <EntityIdentificationLEI>
				if (ReportUtilities.searchData(reportDatas,
						"EntityIdentificationLEI", "169", Integer.toString(i)) != null)
					complexEntityIdentificationType
							.setEntityIdentificationLEI(ReportUtilities
									.searchData(reportDatas,
											"EntityIdentificationLEI", "169",
											Integer.toString(i)));

				// (170) <EntityIdentificationBIC>
				if (ReportUtilities.searchData(reportDatas,
						"EntityIdentificationBIC", "170", Integer.toString(i)) != null)
					complexEntityIdentificationType
							.setEntityIdentificationBIC(ReportUtilities
									.searchData(reportDatas,
											"EntityIdentificationBIC", "170",
											Integer.toString(i)));

				// < / CounterpartyIdentification>
				complexCounterpartyExposureType
						.setCounterpartyIdentification(complexEntityIdentificationType);

				// (171) <CounterpartyTotalExposureRate>
				if (ReportUtilities.searchData(reportDatas,
						"CounterpartyTotalExposureRate", "171",
						Integer.toString(i)) != null)
					complexCounterpartyExposureType
							.setCounterpartyTotalExposureRate(new BigDecimal(
									ReportUtilities.searchData(reportDatas,
											"CounterpartyTotalExposureRate",
											"171", Integer.toString(i))));

				// < / CounterpartyToFundExposure>
				complexCounterpartyExposureTypeList
						.add(complexCounterpartyExposureType);
			}

			// < / CounterpartyToFundExposures>
			complexCounterpartyRiskProfileType
					.setCounterpartyToFundExposures(complexCounterpartyToFundExposuresType);

			// (172) <ClearTransactionsThroughCCPFlag>
			if (ReportUtilities.searchData(reportDatas,
					"ClearTransactionsThroughCCPFlag", "172", null) != null)
				complexCounterpartyRiskProfileType
						.setClearTransactionsThroughCCPFlag(Boolean
								.parseBoolean(ReportUtilities.searchData(
										reportDatas,
										"ClearTransactionsThroughCCPFlag",
										"172", null)));

			// /////////////////////////////////////////////////////////////////
			// <CCPExposures>

			ComplexCCPExposuresType complexCCPExposuresType = objectFactoryAIF
					.createComplexCCPExposuresType();

			List<ComplexCCPExposureType> complexCCPExposureTypeList = complexCCPExposuresType
					.getCCPExposure();

			List<Integer> complexCCPExposureTypeCounts = new ArrayList<Integer>();
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("Ranking")
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger("173")))
					complexCCPExposureTypeCounts.add(Integer
							.parseInt(reportData.getReportDataBlock()));
			}
			Collections.sort(complexCCPExposureTypeCounts);
			for (Integer i : complexCCPExposureTypeCounts) {

				// <CCPExposure>
				ComplexCCPExposureType complexCCPExposureType = objectFactoryAIF
						.createComplexCCPExposureType();

				// (173) <Ranking>
				if (ReportUtilities.searchData(reportDatas, "Ranking", "173",
						Integer.toString(i)) != null)
					complexCCPExposureType.setRanking(new BigInteger(
							ReportUtilities.searchData(reportDatas, "Ranking",
									"173", Integer.toString(i))));

				// <CCPIdentification>
				ComplexEntityIdentificationType complexEntityIdentificationType = objectFactoryAIF
						.createComplexEntityIdentificationType();

				// (174) <EntityName>
				if (ReportUtilities.searchData(reportDatas, "EntityName",
						"174", Integer.toString(i)) != null)
					complexEntityIdentificationType
							.setEntityName(ReportUtilities.searchData(
									reportDatas, "EntityName", "174",
									Integer.toString(i)));

				// (175) <EntityIdentificationLEI>
				if (ReportUtilities.searchData(reportDatas,
						"EntityIdentificationLEI", "175", Integer.toString(i)) != null)
					complexEntityIdentificationType
							.setEntityIdentificationLEI(ReportUtilities
									.searchData(reportDatas,
											"EntityIdentificationLEI", "175",
											Integer.toString(i)));

				// (176) <EntityIdentificationBIC>
				if (ReportUtilities.searchData(reportDatas,
						"EntityIdentificationBIC", "176", Integer.toString(i)) != null)
					complexEntityIdentificationType
							.setEntityIdentificationBIC(ReportUtilities
									.searchData(reportDatas,
											"EntityIdentificationBIC", "176",
											Integer.toString(i)));

				// < / CCPIdentification>
				complexCCPExposureType
						.setCCPIdentification(complexEntityIdentificationType);

				// (177) <CCPExposureValue>
				if (ReportUtilities.searchData(reportDatas, "CCPExposureValue",
						"177", Integer.toString(i)) != null)
					complexCCPExposureType.setCCPExposureValue(new BigInteger(
							ReportUtilities.searchData(reportDatas,
									"CCPExposureValue", "177",
									Integer.toString(i))));

				// < / CCPExposure>
				complexCCPExposureTypeList.add(complexCCPExposureType);
			}

			// < / CCPExposures>
			complexCounterpartyRiskProfileType
					.setCCPExposures(complexCCPExposuresType);

			// < / CounterpartyRiskProfile>
			complexRiskProfileType
					.setCounterpartyRiskProfile(complexCounterpartyRiskProfileType);

			// /////////////////////////////////////////////////////////////////
			// <LiquidityRiskProfile>

			// <PortfolioLiquidityProfile>
			ComplexPortfolioLiquidityProfileType complexPortfolioLiquidityProfileType = objectFactoryAIF
					.createComplexPortfolioLiquidityProfileType();

			// (178) <PortfolioLiquidityInDays0to1Rate>
			if (ReportUtilities.searchData(reportDatas,
					"PortfolioLiquidityInDays0to1Rate", "178", null) != null)
				complexPortfolioLiquidityProfileType
						.setPortfolioLiquidityInDays0To1Rate(new BigDecimal(
								ReportUtilities.searchData(reportDatas,
										"PortfolioLiquidityInDays0to1Rate",
										"178", null)));

			// (179) <PortfolioLiquidityInDays2to7Rate>
			if (ReportUtilities.searchData(reportDatas,
					"PortfolioLiquidityInDays2to7Rate", "179", null) != null)
				complexPortfolioLiquidityProfileType
						.setPortfolioLiquidityInDays2To7Rate(new BigDecimal(
								ReportUtilities.searchData(reportDatas,
										"PortfolioLiquidityInDays2to7Rate",
										"179", null)));

			// (180) <PortfolioLiquidityInDays8to30Rate>
			if (ReportUtilities.searchData(reportDatas,
					"PortfolioLiquidityInDays8to30Rate", "180", null) != null)
				complexPortfolioLiquidityProfileType
						.setPortfolioLiquidityInDays8To30Rate(new BigDecimal(
								ReportUtilities.searchData(reportDatas,
										"PortfolioLiquidityInDays8to30Rate",
										"180", null)));

			// (181) <PortfolioLiquidityInDays31to90Rate>
			if (ReportUtilities.searchData(reportDatas,
					"PortfolioLiquidityInDays31to90Rate", "181", null) != null)
				complexPortfolioLiquidityProfileType
						.setPortfolioLiquidityInDays31To90Rate(new BigDecimal(
								ReportUtilities.searchData(reportDatas,
										"PortfolioLiquidityInDays31to90Rate",
										"181", null)));

			// (182) <PortfolioLiquidityInDays91to180Rate>
			if (ReportUtilities.searchData(reportDatas,
					"PortfolioLiquidityInDays91to180Rate", "182", null) != null)
				complexPortfolioLiquidityProfileType
						.setPortfolioLiquidityInDays91To180Rate(new BigDecimal(
								ReportUtilities.searchData(reportDatas,
										"PortfolioLiquidityInDays91to180Rate",
										"182", null)));

			// (183) <PortfolioLiquidityInDays181to365Rate>
			if (ReportUtilities.searchData(reportDatas,
					"PortfolioLiquidityInDays181to365Rate", "183", null) != null)
				complexPortfolioLiquidityProfileType
						.setPortfolioLiquidityInDays181To365Rate(new BigDecimal(
								ReportUtilities.searchData(reportDatas,
										"PortfolioLiquidityInDays181to365Rate",
										"183", null)));

			// (184) <PortfolioLiquidityInDays365MoreRate>
			if (ReportUtilities.searchData(reportDatas,
					"PortfolioLiquidityInDays365MoreRate", "184", null) != null)
				complexPortfolioLiquidityProfileType
						.setPortfolioLiquidityInDays365MoreRate(new BigDecimal(
								ReportUtilities.searchData(reportDatas,
										"PortfolioLiquidityInDays365MoreRate",
										"184", null)));

			// (185) <UnencumberedCash>
			if (ReportUtilities.searchData(reportDatas, "UnencumberedCash",
					"185", null) != null)
				complexPortfolioLiquidityProfileType
						.setUnencumberedCash(new BigInteger(ReportUtilities
								.searchData(reportDatas, "UnencumberedCash",
										"185", null)));

			// < / PortfolioLiquidityProfile>
			complexLiquidityRiskProfileType
					.setPortfolioLiquidityProfile(complexPortfolioLiquidityProfileType);

			// <InvestorLiquidityProfile>
			ComplexInvestorLiquidityProfileType complexInvestorLiquidityProfileType = objectFactoryAIF
					.createComplexInvestorLiquidityProfileType();

			// (186) <InvestorLiquidityInDays0to1Rate>
			if (ReportUtilities.searchData(reportDatas,
					"InvestorLiquidityInDays0to1Rate", "186", null) != null)
				complexInvestorLiquidityProfileType
						.setInvestorLiquidityInDays0To1Rate(new BigDecimal(
								ReportUtilities.searchData(reportDatas,
										"InvestorLiquidityInDays0to1Rate",
										"186", null)));

			// (187) <InvestorLiquidityInDays2to7Rate>
			if (ReportUtilities.searchData(reportDatas,
					"InvestorLiquidityInDays2to7Rate", "187", null) != null)
				complexInvestorLiquidityProfileType
						.setInvestorLiquidityInDays2To7Rate(new BigDecimal(
								ReportUtilities.searchData(reportDatas,
										"InvestorLiquidityInDays2to7Rate",
										"187", null)));

			// (188) <InvestorLiquidityInDays8to30Rate>
			if (ReportUtilities.searchData(reportDatas,
					"InvestorLiquidityInDays8to30Rate", "188", null) != null)
				complexInvestorLiquidityProfileType
						.setInvestorLiquidityInDays8To30Rate(new BigDecimal(
								ReportUtilities.searchData(reportDatas,
										"InvestorLiquidityInDays8to30Rate",
										"188", null)));

			// (189) <InvestorLiquidityInDays31to90Rate>
			if (ReportUtilities.searchData(reportDatas,
					"InvestorLiquidityInDays31to90Rate", "189", null) != null)
				complexInvestorLiquidityProfileType
						.setInvestorLiquidityInDays31To90Rate(new BigDecimal(
								ReportUtilities.searchData(reportDatas,
										"InvestorLiquidityInDays31to90Rate",
										"189", null)));

			// (190) <InvestorLiquidityInDays91to180Rate>
			if (ReportUtilities.searchData(reportDatas,
					"InvestorLiquidityInDays91to180Rate", "190", null) != null)
				complexInvestorLiquidityProfileType
						.setInvestorLiquidityInDays91To180Rate(new BigDecimal(
								ReportUtilities.searchData(reportDatas,
										"InvestorLiquidityInDays91to180Rate",
										"190", null)));

			// (191) <InvestorLiquidityInDays181to365Rate>
			if (ReportUtilities.searchData(reportDatas,
					"InvestorLiquidityInDays181to365Rate", "191", null) != null)
				complexInvestorLiquidityProfileType
						.setInvestorLiquidityInDays181To365Rate(new BigDecimal(
								ReportUtilities.searchData(reportDatas,
										"InvestorLiquidityInDays181to365Rate",
										"191", null)));

			// (192) <InvestorLiquidityInDays365MoreRate>
			if (ReportUtilities.searchData(reportDatas,
					"InvestorLiquidityInDays365MoreRate", "192", null) != null)
				complexInvestorLiquidityProfileType
						.setInvestorLiquidityInDays365MoreRate(new BigDecimal(
								ReportUtilities.searchData(reportDatas,
										"InvestorLiquidityInDays365MoreRate",
										"192", null)));

			// < / InvestorLiquidityProfile>
			complexLiquidityRiskProfileType
					.setInvestorLiquidityProfile(complexInvestorLiquidityProfileType);

			// <InvestorRedemption>
			ComplexInvestorRedemptionType complexInvestorRedemptionType = objectFactoryAIF
					.createComplexInvestorRedemptionType();

			// (193) <ProvideWithdrawalRightsFlag>
			if (ReportUtilities.searchData(reportDatas,
					"ProvideWithdrawalRightsFlag", "193", null) != null)
				complexInvestorRedemptionType
						.setProvideWithdrawalRightsFlag(Boolean
								.parseBoolean(ReportUtilities.searchData(
										reportDatas,
										"ProvideWithdrawalRightsFlag", "193",
										null)));

			// (194) <InvestorRedemptionFrequency>
			if (ReportUtilities.searchData(reportDatas,
					"InvestorRedemptionFrequency", "194", null) != null) {
				InvestorRedemptionFrequencyType investorRedemptionFrequencyType = InvestorRedemptionFrequencyType
						.valueOf(ReportUtilities.searchData(reportDatas,
								"InvestorRedemptionFrequency", "194", null));
				complexInvestorRedemptionType
						.setInvestorRedemptionFrequency(investorRedemptionFrequencyType);
			}

			// (195) <InvestorRedemptionNoticePeriod>
			if (ReportUtilities.searchData(reportDatas,
					"InvestorRedemptionNoticePeriod", "195", null) != null)
				complexInvestorRedemptionType
						.setInvestorRedemptionNoticePeriod(new BigInteger(
								ReportUtilities.searchData(reportDatas,
										"InvestorRedemptionNoticePeriod",
										"195", null)));

			// (196) <InvestorRedemptionLockUpPeriod>
			if (ReportUtilities.searchData(reportDatas,
					"InvestorRedemptionLockUpPeriod", "196", null) != null)
				complexInvestorRedemptionType
						.setInvestorRedemptionLockUpPeriod(new BigInteger(
								ReportUtilities.searchData(reportDatas,
										"InvestorRedemptionLockUpPeriod",
										"196", null)));

			// < / InvestorRedemption>
			complexLiquidityRiskProfileType
					.setInvestorRedemption(complexInvestorRedemptionType);

			// <InvestorArrangement>
			ComplexInvestorArrangementType complexInvestorArrangementType = objectFactoryAIF
					.createComplexInvestorArrangementType();

			// <InvestorIlliquidAssetArrangement>
			ComplexInvestorIlliquidAssetArrangementType complexInvestorIlliquidAssetArrangementType = objectFactoryAIF
					.createComplexInvestorIlliquidAssetArrangementType();

			// (197) <SidePocketRate>
			if (ReportUtilities.searchData(reportDatas, "SidePocketRate",
					"197", null) != null)
				complexInvestorIlliquidAssetArrangementType
						.setSidePocketRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "SidePocketRate",
										"197", null)));

			// (198) <GatesRate>
			if (ReportUtilities.searchData(reportDatas, "GatesRate", "198",
					null) != null)
				complexInvestorIlliquidAssetArrangementType
						.setGatesRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "GatesRate", "198",
										null)));

			// (199) <DealingSuspensionRate>
			if (ReportUtilities.searchData(reportDatas,
					"DealingSuspensionRate", "199", null) != null)
				complexInvestorIlliquidAssetArrangementType
						.setDealingSuspensionRate(new BigDecimal(
								ReportUtilities.searchData(reportDatas,
										"DealingSuspensionRate", "199", null)));

			// <OtherArrangement>
			ComplexOtherArrangementType complexOtherArrangementType = objectFactoryAIF
					.createComplexOtherArrangementType();

			// (200) <OtherArrangementType>
			if (ReportUtilities.searchData(reportDatas, "OtherArrangementType",
					"200", null) != null)
				complexOtherArrangementType
						.setOtherArrangementType(ReportUtilities.searchData(
								reportDatas, "OtherArrangementType", "200",
								null));

			// (201) <OtherArrangementRate>
			if (ReportUtilities.searchData(reportDatas, "OtherArrangementRate",
					"201", null) != null)
				complexOtherArrangementType
						.setOtherArrangementRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas,
										"OtherArrangementRate", "201", null)));

			// < / OtherArrangement>
			complexInvestorIlliquidAssetArrangementType
					.setOtherArrangement(complexOtherArrangementType);

			// (202) <TotalArrangementRate>
			if (ReportUtilities.searchData(reportDatas, "TotalArrangementRate",
					"202", null) != null)
				complexInvestorIlliquidAssetArrangementType
						.setTotalArrangementRate(new BigDecimal(ReportUtilities
								.searchData(reportDatas,
										"TotalArrangementRate", "202", null)));

			// < / InvestorIlliquidAssetArrangement>
			complexInvestorArrangementType
					.setInvestorIlliquidAssetArrangement(complexInvestorIlliquidAssetArrangementType);

			// <InvestorPreferentialTreatment>
			ComplexInvestorPreferentialTreatmentType complexInvestorPreferentialTreatmentType = objectFactoryAIF
					.createComplexInvestorPreferentialTreatmentType();

			// (203) <InvestorPreferentialTreatmentFlag>
			if (ReportUtilities.searchData(reportDatas,
					"InvestorPreferentialTreatmentFlag", "203", null) != null)
				complexInvestorPreferentialTreatmentType
						.setInvestorPreferentialTreatmentFlag(Boolean
								.parseBoolean(ReportUtilities.searchData(
										reportDatas,
										"InvestorPreferentialTreatmentFlag",
										"203", null)));

			// (204) <DisclosureTermsPreferentialTreatmentFlag>
			if (ReportUtilities.searchData(reportDatas,
					"DisclosureTermsPreferentialTreatmentFlag", "204", null) != null)
				complexInvestorPreferentialTreatmentType
						.setDisclosureTermsPreferentialTreatmentFlag(Boolean.parseBoolean(ReportUtilities
								.searchData(
										reportDatas,
										"DisclosureTermsPreferentialTreatmentFlag",
										"204", null)));

			// (205) <LiquidityTermsPreferentialTreatmentFlag>
			if (ReportUtilities.searchData(reportDatas,
					"LiquidityTermsPreferentialTreatmentFlag", "205", null) != null)
				complexInvestorPreferentialTreatmentType
						.setLiquidityTermsPreferentialTreatmentFlag(Boolean.parseBoolean(ReportUtilities
								.searchData(
										reportDatas,
										"LiquidityTermsPreferentialTreatmentFlag",
										"205", null)));

			// (206) <FeeTermsPreferentialTreatmentFlag>
			if (ReportUtilities.searchData(reportDatas,
					"FeeTermsPreferentialTreatmentFlag", "206", null) != null)
				complexInvestorPreferentialTreatmentType
						.setFeeTermsPreferentialTreatmentFlag(Boolean
								.parseBoolean(ReportUtilities.searchData(
										reportDatas,
										"FeeTermsPreferentialTreatmentFlag",
										"206", null)));

			// (207) <OtherTermsPreferentialTreatmentFlag>
			if (ReportUtilities.searchData(reportDatas,
					"OtherTermsPreferentialTreatmentFlag", "207", null) != null)
				complexInvestorPreferentialTreatmentType
						.setOtherTermsPreferentialTreatmentFlag(Boolean
								.parseBoolean(ReportUtilities.searchData(
										reportDatas,
										"OtherTermsPreferentialTreatmentFlag",
										"207", null)));

			// < / InvestorPreferentialTreatment>
			complexInvestorArrangementType
					.setInvestorPreferentialTreatment(complexInvestorPreferentialTreatmentType);

			// < / InvestorArrangement>
			complexLiquidityRiskProfileType
					.setInvestorArrangement(complexInvestorArrangementType);

			// <InvestorGroups>
			ComplexInvestorGroupsType complexInvestorGroupsType = objectFactoryAIF
					.createComplexInvestorGroupsType();

			List<ComplexInvestorGroupType> complexInvestorGroupTypeList = complexInvestorGroupsType
					.getInvestorGroup();

			List<Integer> complexInvestorGroupTypeCounts = new ArrayList<Integer>();
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("InvestorGroupType")
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger("208")))
					complexInvestorGroupTypeCounts.add(Integer
							.parseInt(reportData.getReportDataBlock()));
			}
			for (Integer i : complexInvestorGroupTypeCounts) {

				// <InvestorGroup>
				ComplexInvestorGroupType complexInvestorGroupType = objectFactoryAIF
						.createComplexInvestorGroupType();

				// (208) <InvestorGroupType>
				if (ReportUtilities.searchData(reportDatas,
						"InvestorGroupType", "208", Integer.toString(i)) != null) {
					InvestorGroupTypeType investorGroupTypeType = InvestorGroupTypeType
							.valueOf(ReportUtilities.searchData(reportDatas,
									"InvestorGroupType", "208",
									Integer.toString(i)));
					complexInvestorGroupType
							.setInvestorGroupType(investorGroupTypeType);
				}

				// (209) <InvestorGroupRate>
				if (ReportUtilities.searchData(reportDatas,
						"InvestorGroupRate", "209", Integer.toString(i)) != null)
					complexInvestorGroupType
							.setInvestorGroupRate(new BigDecimal(
									ReportUtilities.searchData(reportDatas,
											"InvestorGroupRate", "209",
											Integer.toString(i))));

				// < / InvestorGroup>
				complexInvestorGroupTypeList.add(complexInvestorGroupType);
			}

			// < / InvestorGroups>
			complexLiquidityRiskProfileType
					.setInvestorGroups(complexInvestorGroupsType);

			// <FinancingLiquidityProfile>
			ComplexFinancingLiquidityProfileType complexFinancingLiquidityProfileType = objectFactoryAIF
					.createComplexFinancingLiquidityProfileType();

			// (210) <TotalFinancingAmount>
			if (ReportUtilities.searchData(reportDatas, "TotalFinancingAmount",
					"210", null) != null)
				complexFinancingLiquidityProfileType
						.setTotalFinancingAmount(new BigInteger(ReportUtilities
								.searchData(reportDatas,
										"TotalFinancingAmount", "210", null)));

			// (211) <TotalFinancingInDays0to1Rate>
			if (ReportUtilities.searchData(reportDatas,
					"TotalFinancingInDays0to1Rate", "211", null) != null)
				complexFinancingLiquidityProfileType
						.setTotalFinancingInDays0To1Rate(new BigDecimal(
								ReportUtilities.searchData(reportDatas,
										"TotalFinancingInDays0to1Rate", "211",
										null)));

			// (212) <TotalFinancingInDays2to7Rate>
			if (ReportUtilities.searchData(reportDatas,
					"TotalFinancingInDays2to7Rate", "212", null) != null)
				complexFinancingLiquidityProfileType
						.setTotalFinancingInDays2To7Rate(new BigDecimal(
								ReportUtilities.searchData(reportDatas,
										"TotalFinancingInDays2to7Rate", "212",
										null)));

			// (213) <TotalFinancingInDays8to30Rate>
			if (ReportUtilities.searchData(reportDatas,
					"TotalFinancingInDays8to30Rate", "213", null) != null)
				complexFinancingLiquidityProfileType
						.setTotalFinancingInDays8To30Rate(new BigDecimal(
								ReportUtilities.searchData(reportDatas,
										"TotalFinancingInDays8to30Rate", "213",
										null)));

			// (214) <TotalFinancingInDays31to90Rate>
			if (ReportUtilities.searchData(reportDatas,
					"TotalFinancingInDays31to90Rate", "214", null) != null)
				complexFinancingLiquidityProfileType
						.setTotalFinancingInDays31To90Rate(new BigDecimal(
								ReportUtilities.searchData(reportDatas,
										"TotalFinancingInDays31to90Rate",
										"214", null)));

			// (215) <TotalFinancingInDays91to180Rate>
			if (ReportUtilities.searchData(reportDatas,
					"TotalFinancingInDays91to180Rate", "215", null) != null)
				complexFinancingLiquidityProfileType
						.setTotalFinancingInDays91To180Rate(new BigDecimal(
								ReportUtilities.searchData(reportDatas,
										"TotalFinancingInDays91to180Rate",
										"215", null)));

			// (216) <TotalFinancingInDays181to365Rate>
			if (ReportUtilities.searchData(reportDatas,
					"TotalFinancingInDays181to365Rate", "216", null) != null)
				complexFinancingLiquidityProfileType
						.setTotalFinancingInDays181To365Rate(new BigDecimal(
								ReportUtilities.searchData(reportDatas,
										"TotalFinancingInDays181to365Rate",
										"216", null)));

			// (217) <TotalFinancingInDays365MoreRate>
			if (ReportUtilities.searchData(reportDatas,
					"TotalFinancingInDays365MoreRate", "217", null) != null)
				complexFinancingLiquidityProfileType
						.setTotalFinancingInDays365MoreRate(new BigDecimal(
								ReportUtilities.searchData(reportDatas,
										"TotalFinancingInDays365MoreRate",
										"217", null)));

			// < / FinancingLiquidityProfile>
			complexLiquidityRiskProfileType
					.setFinancingLiquidityProfile(complexFinancingLiquidityProfileType);

			// < / LiquidityRiskProfile>
			complexRiskProfileType
					.setLiquidityRiskProfile(complexLiquidityRiskProfileType);

			// /////////////////////////////////////////////////////////////////
			// <OperationalRisk>

			// (218) <TotalOpenPositions>
			if (ReportUtilities.searchData(reportDatas, "TotalOpenPositions",
					"218", null) != null)
				complexOperationalRiskType
						.setTotalOpenPositions(new BigInteger(ReportUtilities
								.searchData(reportDatas, "TotalOpenPositions",
										"218", null)));

			// /////////////////////////////////////////////////////////////////
			// <HistoricalRiskProfile>
			ComplexHistoricalRiskProfileType complexHistoricalRiskProfileType = objectFactoryAIF
					.createComplexHistoricalRiskProfileType();

			// /////////////////////////////////////////////////////////////////
			// <GrossInvestmentReturnsRate>
			ComplexSignedRateMonthPeriodType complexGrossSignedRateMonthPeriodType = objectFactoryAIF
					.createComplexSignedRateMonthPeriodType();

			// (219) <RateJanuary>
			if (ReportUtilities.searchData(reportDatas, "RateJanuary", "219",
					null) != null)
				complexGrossSignedRateMonthPeriodType
						.setRateJanuary(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "RateJanuary", "219",
										null)));

			// (220) <RateFebruary>
			if (ReportUtilities.searchData(reportDatas, "RateFebruary", "220",
					null) != null)
				complexGrossSignedRateMonthPeriodType
						.setRateFebruary(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "RateFebruary", "220",
										null)));

			// (221) <RateMarch>
			if (ReportUtilities.searchData(reportDatas, "RateMarch", "221",
					null) != null)
				complexGrossSignedRateMonthPeriodType
						.setRateMarch(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "RateMarch", "221",
										null)));

			// (222) <RateApril>
			if (ReportUtilities.searchData(reportDatas, "RateApril", "222",
					null) != null)
				complexGrossSignedRateMonthPeriodType
						.setRateApril(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "RateApril", "222",
										null)));

			// (223) <RateMay>
			if (ReportUtilities.searchData(reportDatas, "RateMay", "223", null) != null)
				complexGrossSignedRateMonthPeriodType
						.setRateMay(new BigDecimal(ReportUtilities.searchData(
								reportDatas, "RateMay", "223", null)));

			// (224) <RateJune>
			if (ReportUtilities
					.searchData(reportDatas, "RateJune", "224", null) != null)
				complexGrossSignedRateMonthPeriodType
						.setRateJune(new BigDecimal(ReportUtilities.searchData(
								reportDatas, "RateJune", "224", null)));

			// (225) <RateJuly>
			if (ReportUtilities
					.searchData(reportDatas, "RateJuly", "225", null) != null)
				complexGrossSignedRateMonthPeriodType
						.setRateJuly(new BigDecimal(ReportUtilities.searchData(
								reportDatas, "RateJuly", "225", null)));

			// (226) <RateAugust>
			if (ReportUtilities.searchData(reportDatas, "RateAugust", "226",
					null) != null)
				complexGrossSignedRateMonthPeriodType
						.setRateAugust(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "RateAugust", "226",
										null)));

			// (227) <RateSeptember>
			if (ReportUtilities.searchData(reportDatas, "RateSeptember", "227",
					null) != null)
				complexGrossSignedRateMonthPeriodType
						.setRateSeptember(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "RateSeptember",
										"227", null)));

			// (228) <RateOctober>
			if (ReportUtilities.searchData(reportDatas, "RateOctober", "228",
					null) != null)
				complexGrossSignedRateMonthPeriodType
						.setRateOctober(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "RateOctober", "228",
										null)));

			// (229) <RateNovember>
			if (ReportUtilities.searchData(reportDatas, "RateNovember", "229",
					null) != null)
				complexGrossSignedRateMonthPeriodType
						.setRateNovember(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "RateNovember", "229",
										null)));

			// (230) <RateDecember>
			if (ReportUtilities.searchData(reportDatas, "RateDecember", "230",
					null) != null)
				complexGrossSignedRateMonthPeriodType
						.setRateDecember(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "RateDecember", "230",
										null)));

			// < / GrossInvestmentReturnsRate>
			complexHistoricalRiskProfileType
					.setGrossInvestmentReturnsRate(complexGrossSignedRateMonthPeriodType);

			// /////////////////////////////////////////////////////////////////
			// <NetInvestmentReturnsRate>
			ComplexSignedRateMonthPeriodType complexNetSignedRateMonthPeriodType = objectFactoryAIF
					.createComplexSignedRateMonthPeriodType();

			// (231) <RateJanuary>
			if (ReportUtilities.searchData(reportDatas, "RateJanuary", "231",
					null) != null)
				complexNetSignedRateMonthPeriodType
						.setRateJanuary(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "RateJanuary", "231",
										null)));

			// (232) <RateFebruary>
			if (ReportUtilities.searchData(reportDatas, "RateFebruary", "232",
					null) != null)
				complexNetSignedRateMonthPeriodType
						.setRateFebruary(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "RateFebruary", "232",
										null)));

			// (233) <RateMarch>
			if (ReportUtilities.searchData(reportDatas, "RateMarch", "233",
					null) != null)
				complexNetSignedRateMonthPeriodType
						.setRateMarch(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "RateMarch", "233",
										null)));

			// (234) <RateApril>
			if (ReportUtilities.searchData(reportDatas, "RateApril", "234",
					null) != null)
				complexNetSignedRateMonthPeriodType
						.setRateApril(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "RateApril", "234",
										null)));

			// (235) <RateMay>
			if (ReportUtilities.searchData(reportDatas, "RateMay", "235", null) != null)
				complexNetSignedRateMonthPeriodType.setRateMay(new BigDecimal(
						ReportUtilities.searchData(reportDatas, "RateMay",
								"235", null)));

			// (236) <RateJune>
			if (ReportUtilities
					.searchData(reportDatas, "RateJune", "236", null) != null)
				complexNetSignedRateMonthPeriodType.setRateJune(new BigDecimal(
						ReportUtilities.searchData(reportDatas, "RateJune",
								"236", null)));

			// (237) <RateJuly>
			if (ReportUtilities
					.searchData(reportDatas, "RateJuly", "237", null) != null)
				complexNetSignedRateMonthPeriodType.setRateJuly(new BigDecimal(
						ReportUtilities.searchData(reportDatas, "RateJuly",
								"237", null)));

			// (238) <RateAugust>
			if (ReportUtilities.searchData(reportDatas, "RateAugust", "238",
					null) != null)
				complexNetSignedRateMonthPeriodType
						.setRateAugust(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "RateAugust", "238",
										null)));

			// (239) <RateSeptember>
			if (ReportUtilities.searchData(reportDatas, "RateSeptember", "239",
					null) != null)
				complexNetSignedRateMonthPeriodType
						.setRateSeptember(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "RateSeptember",
										"239", null)));

			// (240) <RateOctober>
			if (ReportUtilities.searchData(reportDatas, "RateOctober", "240",
					null) != null)
				complexNetSignedRateMonthPeriodType
						.setRateOctober(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "RateOctober", "240",
										null)));

			// (241) <RateNovember>
			if (ReportUtilities.searchData(reportDatas, "RateNovember", "241",
					null) != null)
				complexNetSignedRateMonthPeriodType
						.setRateNovember(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "RateNovember", "241",
										null)));

			// (242) <RateDecember>
			if (ReportUtilities.searchData(reportDatas, "RateDecember", "242",
					null) != null)
				complexNetSignedRateMonthPeriodType
						.setRateDecember(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "RateDecember", "242",
										null)));

			// < / NetInvestmentReturnsRate>
			complexHistoricalRiskProfileType
					.setNetInvestmentReturnsRate(complexNetSignedRateMonthPeriodType);

			// /////////////////////////////////////////////////////////////////
			// <NAVChangeRate>
			ComplexSignedRateMonthPeriodType complexNAVSignedRateMonthPeriodType = objectFactoryAIF
					.createComplexSignedRateMonthPeriodType();

			// (243) <RateJanuary>
			if (ReportUtilities.searchData(reportDatas, "RateJanuary", "243",
					null) != null)
				complexNAVSignedRateMonthPeriodType
						.setRateJanuary(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "RateJanuary", "243",
										null)));

			// (244) <RateFebruary>
			if (ReportUtilities.searchData(reportDatas, "RateFebruary", "244",
					null) != null)
				complexNAVSignedRateMonthPeriodType
						.setRateFebruary(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "RateFebruary", "244",
										null)));

			// (245) <RateMarch>
			if (ReportUtilities.searchData(reportDatas, "RateMarch", "245",
					null) != null)
				complexNAVSignedRateMonthPeriodType
						.setRateMarch(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "RateMarch", "245",
										null)));

			// (246) <RateApril>
			if (ReportUtilities.searchData(reportDatas, "RateApril", "246",
					null) != null)
				complexNAVSignedRateMonthPeriodType
						.setRateApril(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "RateApril", "246",
										null)));

			// (247) <RateMay>
			if (ReportUtilities.searchData(reportDatas, "RateMay", "247", null) != null)
				complexNAVSignedRateMonthPeriodType.setRateMay(new BigDecimal(
						ReportUtilities.searchData(reportDatas, "RateMay",
								"247", null)));

			// (248) <RateJune>
			if (ReportUtilities
					.searchData(reportDatas, "RateJune", "248", null) != null)
				complexNAVSignedRateMonthPeriodType.setRateJune(new BigDecimal(
						ReportUtilities.searchData(reportDatas, "RateJune",
								"248", null)));

			// (249) <RateJuly>
			if (ReportUtilities
					.searchData(reportDatas, "RateJuly", "249", null) != null)
				complexNAVSignedRateMonthPeriodType.setRateJuly(new BigDecimal(
						ReportUtilities.searchData(reportDatas, "RateJuly",
								"249", null)));

			// (250) <RateAugust>
			if (ReportUtilities.searchData(reportDatas, "RateAugust", "250",
					null) != null)
				complexNAVSignedRateMonthPeriodType
						.setRateAugust(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "RateAugust", "250",
										null)));

			// (251) <RateSeptember>
			if (ReportUtilities.searchData(reportDatas, "RateSeptember", "251",
					null) != null)
				complexNAVSignedRateMonthPeriodType
						.setRateSeptember(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "RateSeptember",
										"251", null)));

			// (252) <RateOctober>
			if (ReportUtilities.searchData(reportDatas, "RateOctober", "252",
					null) != null)
				complexNAVSignedRateMonthPeriodType
						.setRateOctober(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "RateOctober", "252",
										null)));

			// (253) <RateNovember>
			if (ReportUtilities.searchData(reportDatas, "RateNovember", "253",
					null) != null)
				complexNAVSignedRateMonthPeriodType
						.setRateNovember(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "RateNovember", "253",
										null)));

			// (254) <RateDecember>
			if (ReportUtilities.searchData(reportDatas, "RateDecember", "254",
					null) != null)
				complexNAVSignedRateMonthPeriodType
						.setRateDecember(new BigDecimal(ReportUtilities
								.searchData(reportDatas, "RateDecember", "254",
										null)));

			// < / NAVChangeRate>
			complexHistoricalRiskProfileType
					.setNAVChangeRate(complexNAVSignedRateMonthPeriodType);

			// /////////////////////////////////////////////////////////////////
			// <Subscription>
			ComplexQuantityMonthPeriodType complexSubscriptionQuantityMonthPeriodType = objectFactoryAIF
					.createComplexQuantityMonthPeriodType();

			// (255) <QuantityJanuary>
			if (ReportUtilities.searchData(reportDatas, "QuantityJanuary",
					"255", null) != null)
				complexSubscriptionQuantityMonthPeriodType
						.setQuantityJanuary(new BigInteger(ReportUtilities
								.searchData(reportDatas, "QuantityJanuary",
										"255", null)));

			// (256) <QuantityFebruary>
			if (ReportUtilities.searchData(reportDatas, "QuantityFebruary",
					"256", null) != null)
				complexSubscriptionQuantityMonthPeriodType
						.setQuantityFebruary(new BigInteger(ReportUtilities
								.searchData(reportDatas, "QuantityFebruary",
										"256", null)));

			// (257) <QuantityMarch>
			if (ReportUtilities.searchData(reportDatas, "QuantityMarch", "257",
					null) != null)
				complexSubscriptionQuantityMonthPeriodType
						.setQuantityMarch(new BigInteger(ReportUtilities
								.searchData(reportDatas, "QuantityMarch",
										"257", null)));

			// (258) <QuantityApril>
			if (ReportUtilities.searchData(reportDatas, "QuantityApril", "258",
					null) != null)
				complexSubscriptionQuantityMonthPeriodType
						.setQuantityApril(new BigInteger(ReportUtilities
								.searchData(reportDatas, "QuantityApril",
										"258", null)));

			// (259) <QuantityMay>
			if (ReportUtilities.searchData(reportDatas, "QuantityMay", "259",
					null) != null)
				complexSubscriptionQuantityMonthPeriodType
						.setQuantityMay(new BigInteger(ReportUtilities
								.searchData(reportDatas, "QuantityMay", "259",
										null)));

			// (260) <QuantityJune>
			if (ReportUtilities.searchData(reportDatas, "QuantityJune", "260",
					null) != null)
				complexSubscriptionQuantityMonthPeriodType
						.setQuantityJune(new BigInteger(ReportUtilities
								.searchData(reportDatas, "QuantityJune", "260",
										null)));

			// (261) <QuantityJuly>
			if (ReportUtilities.searchData(reportDatas, "QuantityJuly", "261",
					null) != null)
				complexSubscriptionQuantityMonthPeriodType
						.setQuantityJuly(new BigInteger(ReportUtilities
								.searchData(reportDatas, "QuantityJuly", "261",
										null)));

			// (262) <QuantityAugust>
			if (ReportUtilities.searchData(reportDatas, "QuantityAugust",
					"262", null) != null)
				complexSubscriptionQuantityMonthPeriodType
						.setQuantityAugust(new BigInteger(ReportUtilities
								.searchData(reportDatas, "QuantityAugust",
										"262", null)));

			// (263) <QuantitySeptember>
			if (ReportUtilities.searchData(reportDatas, "QuantitySeptember",
					"263", null) != null)
				complexSubscriptionQuantityMonthPeriodType
						.setQuantitySeptember(new BigInteger(ReportUtilities
								.searchData(reportDatas, "QuantitySeptember",
										"263", null)));

			// (264) <QuantityOctober>
			if (ReportUtilities.searchData(reportDatas, "QuantityOctober",
					"264", null) != null)
				complexSubscriptionQuantityMonthPeriodType
						.setQuantityOctober(new BigInteger(ReportUtilities
								.searchData(reportDatas, "QuantityOctober",
										"264", null)));

			// (265) <QuantityNovember>
			if (ReportUtilities.searchData(reportDatas, "QuantityNovember",
					"265", null) != null)
				complexSubscriptionQuantityMonthPeriodType
						.setQuantityNovember(new BigInteger(ReportUtilities
								.searchData(reportDatas, "QuantityNovember",
										"265", null)));

			// (266) <QuantityDecember>
			if (ReportUtilities.searchData(reportDatas, "QuantityDecember",
					"266", null) != null)
				complexSubscriptionQuantityMonthPeriodType
						.setQuantityDecember(new BigInteger(ReportUtilities
								.searchData(reportDatas, "QuantityDecember",
										"266", null)));

			// < / Subscription>
			complexHistoricalRiskProfileType
					.setSubscription(complexSubscriptionQuantityMonthPeriodType);

			// /////////////////////////////////////////////////////////////////
			// <Redemption>
			ComplexQuantityMonthPeriodType complexRedemptionQuantityMonthPeriodType = objectFactoryAIF
					.createComplexQuantityMonthPeriodType();

			// (267) <QuantityJanuary>
			if (ReportUtilities.searchData(reportDatas, "QuantityJanuary",
					"267", null) != null)
				complexRedemptionQuantityMonthPeriodType
						.setQuantityJanuary(new BigInteger(ReportUtilities
								.searchData(reportDatas, "QuantityJanuary",
										"267", null)));

			// (268) <QuantityFebruary>
			if (ReportUtilities.searchData(reportDatas, "QuantityFebruary",
					"268", null) != null)
				complexRedemptionQuantityMonthPeriodType
						.setQuantityFebruary(new BigInteger(ReportUtilities
								.searchData(reportDatas, "QuantityFebruary",
										"268", null)));

			// (269) <QuantityMarch>
			if (ReportUtilities.searchData(reportDatas, "QuantityMarch", "269",
					null) != null)
				complexRedemptionQuantityMonthPeriodType
						.setQuantityMarch(new BigInteger(ReportUtilities
								.searchData(reportDatas, "QuantityMarch",
										"269", null)));

			// (270) <QuantityApril>
			if (ReportUtilities.searchData(reportDatas, "QuantityApril", "270",
					null) != null)
				complexRedemptionQuantityMonthPeriodType
						.setQuantityApril(new BigInteger(ReportUtilities
								.searchData(reportDatas, "QuantityApril",
										"270", null)));

			// (271) <QuantityMay>
			if (ReportUtilities.searchData(reportDatas, "QuantityMay", "271",
					null) != null)
				complexRedemptionQuantityMonthPeriodType
						.setQuantityMay(new BigInteger(ReportUtilities
								.searchData(reportDatas, "QuantityMay", "271",
										null)));

			// (272) <QuantityJune>
			if (ReportUtilities.searchData(reportDatas, "QuantityJune", "272",
					null) != null)
				complexRedemptionQuantityMonthPeriodType
						.setQuantityJune(new BigInteger(ReportUtilities
								.searchData(reportDatas, "QuantityJune", "272",
										null)));

			// (273) <QuantityJuly>
			if (ReportUtilities.searchData(reportDatas, "QuantityJuly", "273",
					null) != null)
				complexRedemptionQuantityMonthPeriodType
						.setQuantityJuly(new BigInteger(ReportUtilities
								.searchData(reportDatas, "QuantityJuly", "273",
										null)));

			// (274) <QuantityAugust>
			if (ReportUtilities.searchData(reportDatas, "QuantityAugust",
					"274", null) != null)
				complexRedemptionQuantityMonthPeriodType
						.setQuantityAugust(new BigInteger(ReportUtilities
								.searchData(reportDatas, "QuantityAugust",
										"274", null)));

			// (275) <QuantitySeptember>
			if (ReportUtilities.searchData(reportDatas, "QuantitySeptember",
					"275", null) != null)
				complexRedemptionQuantityMonthPeriodType
						.setQuantitySeptember(new BigInteger(ReportUtilities
								.searchData(reportDatas, "QuantitySeptember",
										"275", null)));

			// (276) <QuantityOctober>
			if (ReportUtilities.searchData(reportDatas, "QuantityOctober",
					"276", null) != null)
				complexRedemptionQuantityMonthPeriodType
						.setQuantityOctober(new BigInteger(ReportUtilities
								.searchData(reportDatas, "QuantityOctober",
										"276", null)));

			// (277) <QuantityNovember>
			if (ReportUtilities.searchData(reportDatas, "QuantityNovember",
					"277", null) != null)
				complexRedemptionQuantityMonthPeriodType
						.setQuantityNovember(new BigInteger(ReportUtilities
								.searchData(reportDatas, "QuantityNovember",
										"277", null)));

			// (278) <QuantityDecember>
			if (ReportUtilities.searchData(reportDatas, "QuantityDecember",
					"278", null) != null)
				complexRedemptionQuantityMonthPeriodType
						.setQuantityDecember(new BigInteger(ReportUtilities
								.searchData(reportDatas, "QuantityDecember",
										"278", null)));

			// < / Redemption>
			complexHistoricalRiskProfileType
					.setRedemption(complexRedemptionQuantityMonthPeriodType);

			// < / HistoricalRiskProfile>
			complexOperationalRiskType
					.setHistoricalRiskProfile(complexHistoricalRiskProfileType);

			// < / OperationalRisk>
			complexRiskProfileType
					.setOperationalRisk(complexOperationalRiskType);

			// /////////////////////////////////////////////////////////////////
			// <StressTests>

			// (279) <StressTestsResultArticle15>
			if (ReportUtilities.searchData(reportDatas,
					"StressTestsResultArticle15", "279", null) != null)
				complexStressTestsType
						.setStressTestsResultArticle15(ReportUtilities
								.searchData(reportDatas,
										"StressTestsResultArticle15", "279",
										null));

			// (280) <StressTestsResultArticle16>
			if (ReportUtilities.searchData(reportDatas,
					"StressTestsResultArticle16", "280", null) != null)
				complexStressTestsType
						.setStressTestsResultArticle16(ReportUtilities
								.searchData(reportDatas,
										"StressTestsResultArticle16", "280",
										null));

			// /////////////////////////////////////////////////////////////////
			// <AIFLeverageArticle24-2>

			// (281) <AllCounterpartyCollateralRehypothecationFlag>
			if (ReportUtilities
					.searchData(reportDatas,
							"AllCounterpartyCollateralRehypothecationFlag",
							"281", null) != null)
				complexAIFLeverageArticle242Type
						.setAllCounterpartyCollateralRehypothecationFlag(Boolean.parseBoolean(ReportUtilities
								.searchData(
										reportDatas,
										"AllCounterpartyCollateralRehypothecationFlag",
										"281", null)));

			// (282) <AllCounterpartyCollateralRehypothecatedRate>
			if (ReportUtilities.searchData(reportDatas,
					"AllCounterpartyCollateralRehypothecatedRate", "282", null) != null)
				complexAIFLeverageArticle242Type
						.setAllCounterpartyCollateralRehypothecatedRate(new BigDecimal(
								ReportUtilities
										.searchData(
												reportDatas,
												"AllCounterpartyCollateralRehypothecatedRate",
												"282", null)));

			// /////////////////////////////////////////////////////////////////
			// <SecuritiesCashBorrowing>

			// (283) <UnsecuredBorrowingAmount>
			if (ReportUtilities.searchData(reportDatas,
					"UnsecuredBorrowingAmount", "283", null) != null)
				complexSecuritiesCashBorrowingType
						.setUnsecuredBorrowingAmount(new BigInteger(
								ReportUtilities
										.searchData(reportDatas,
												"UnsecuredBorrowingAmount",
												"283", null)));

			// (284) <SecuredBorrowingPrimeBrokerageAmount>
			if (ReportUtilities.searchData(reportDatas,
					"SecuredBorrowingPrimeBrokerageAmount", "284", null) != null)
				complexSecuritiesCashBorrowingType
						.setSecuredBorrowingPrimeBrokerageAmount(new BigInteger(
								ReportUtilities.searchData(reportDatas,
										"SecuredBorrowingPrimeBrokerageAmount",
										"284", null)));

			// (285) <SecuredBorrowingReverseRepoAmount>
			if (ReportUtilities.searchData(reportDatas,
					"SecuredBorrowingReverseRepoAmount", "285", null) != null)
				complexSecuritiesCashBorrowingType
						.setSecuredBorrowingReverseRepoAmount(new BigInteger(
								ReportUtilities.searchData(reportDatas,
										"SecuredBorrowingReverseRepoAmount",
										"285", null)));

			// (286) <SecuredBorrowingOtherAmount>
			if (ReportUtilities.searchData(reportDatas,
					"SecuredBorrowingOtherAmount", "286", null) != null)
				complexSecuritiesCashBorrowingType
						.setSecuredBorrowingOtherAmount(new BigInteger(
								ReportUtilities.searchData(reportDatas,
										"SecuredBorrowingOtherAmount", "286",
										null)));

			// < / SecuritiesCashBorrowing>
			complexAIFLeverageArticle242Type
					.setSecuritiesCashBorrowing(complexSecuritiesCashBorrowingType);

			// /////////////////////////////////////////////////////////////////
			// <FinancialInstrumentBorrowing>

			// (287) <ExchangedTradedDerivativesExposureValue>
			if (ReportUtilities.searchData(reportDatas,
					"ExchangedTradedDerivativesExposureValue", "287", null) != null)
				complexFinancialInstrumentBorrowingType
						.setExchangedTradedDerivativesExposureValue(new BigInteger(
								ReportUtilities
										.searchData(
												reportDatas,
												"ExchangedTradedDerivativesExposureValue",
												"287", null)));

			// (288) <OTCDerivativesAmount>
			if (ReportUtilities.searchData(reportDatas, "OTCDerivativesAmount",
					"288", null) != null)
				complexFinancialInstrumentBorrowingType
						.setExchangedTradedDerivativesExposureValue(new BigInteger(
								ReportUtilities.searchData(reportDatas,
										"OTCDerivativesAmount", "288", null)));

			// < / FinancialInstrumentBorrowing>
			complexAIFLeverageArticle242Type
					.setFinancialInstrumentBorrowing(complexFinancialInstrumentBorrowingType);

			// (289) <ShortPositionBorrowedSecuritiesValue>
			if (ReportUtilities.searchData(reportDatas,
					"ShortPositionBorrowedSecuritiesValue", "289", null) != null)
				complexAIFLeverageArticle242Type
						.setShortPositionBorrowedSecuritiesValue(new BigInteger(
								ReportUtilities.searchData(reportDatas,
										"ShortPositionBorrowedSecuritiesValue",
										"289", null)));

			// /////////////////////////////////////////////////////////////////
			// <ControlledStructures>

			List<ComplexControlledStructureType> complexControlledStructureTypeList = complexControlledStructuresType
					.getControlledStructure();

			List<Integer> complexControlledStructureTypeCounts = new ArrayList<Integer>();
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("EntityName")
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger("290")))
					complexControlledStructureTypeCounts.add(Integer
							.parseInt(reportData.getReportDataBlock()));
			}
			for (Integer i : complexControlledStructureTypeCounts) {

				// <ControlledStructure>
				ComplexControlledStructureType complexControlledStructureType = objectFactoryAIF
						.createComplexControlledStructureType();

				// <ControlledStructureIdentification>
				ComplexEntityIdentificationType complexEntityIdentificationType = objectFactoryAIF
						.createComplexEntityIdentificationType();

				// (290) <EntityName>
				if (ReportUtilities.searchData(reportDatas, "EntityName",
						"290", Integer.toString(i)) != null)
					complexEntityIdentificationType
							.setEntityName(ReportUtilities.searchData(
									reportDatas, "EntityName", "290",
									Integer.toString(i)));

				// (291) <EntityIdentificationLEI>
				if (ReportUtilities.searchData(reportDatas,
						"EntityIdentificationLEI", "291", Integer.toString(i)) != null)
					complexEntityIdentificationType
							.setEntityIdentificationLEI(ReportUtilities
									.searchData(reportDatas,
											"EntityIdentificationLEI", "291",
											Integer.toString(i)));

				// (292) <EntityIdentificationBIC>
				if (ReportUtilities.searchData(reportDatas,
						"EntityIdentificationBIC", "292", Integer.toString(i)) != null)
					complexEntityIdentificationType
							.setEntityIdentificationBIC(ReportUtilities
									.searchData(reportDatas,
											"EntityIdentificationBIC", "292",
											Integer.toString(i)));

				// (293) <ControlledStructureExposureValue>
				if (ReportUtilities.searchData(reportDatas,
						"ControlledStructureExposureValue", "293",
						Integer.toString(i)) != null)
					complexControlledStructureType
							.setControlledStructureExposureValue(new BigInteger(
									ReportUtilities.searchData(reportDatas,
											"ControlledStructureExposureValue",
											"293", Integer.toString(i))));

				// < / ControlledStructureIdentification>
				complexControlledStructureType
						.setControlledStructureIdentification(complexEntityIdentificationType);

				// < / ControlledStructure>
				complexControlledStructureTypeList
						.add(complexControlledStructureType);
			}

			// < / ControlledStructures>
			complexAIFLeverageArticle242Type
					.setControlledStructures(complexControlledStructuresType);

			// /////////////////////////////////////////////////////////////////
			// <LeverageAIF>

			// (294) <GrossMethodRate>
			if (ReportUtilities.searchData(reportDatas, "GrossMethodRate",
					"294", null) != null)
				complexLeverageAIFType.setGrossMethodRate(new BigDecimal(
						ReportUtilities.searchData(reportDatas,
								"GrossMethodRate", "294", null)));

			// (295) <CommitmentMethodRate>
			if (ReportUtilities.searchData(reportDatas, "CommitmentMethodRate",
					"295", null) != null)
				complexLeverageAIFType.setCommitmentMethodRate(new BigDecimal(
						ReportUtilities.searchData(reportDatas,
								"CommitmentMethodRate", "295", null)));

			// < / LeverageAIF>
			complexAIFLeverageArticle242Type
					.setLeverageAIF(complexLeverageAIFType);

			// < / AIFLeverageArticle24-2>

			// /////////////////////////////////////////////////////////////////
			// <AIFLeverageArticle24-4>

			List<ComplexBorrowingSourceType> complexBorrowingSourceTypeList = complexAIFLeverageArticle244Type
					.getBorrowingSource();

			List<Integer> complexBorrowingSourceTypeCounts = new ArrayList<Integer>();
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("Ranking")
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger("296")))
					complexBorrowingSourceTypeCounts.add(Integer
							.parseInt(reportData.getReportDataBlock()));
			}
			Collections.sort(complexBorrowingSourceTypeCounts);
			for (Integer i : complexBorrowingSourceTypeCounts) {

				// <BorrowingSource>
				ComplexBorrowingSourceType complexBorrowingSourceType = objectFactoryAIF
						.createComplexBorrowingSourceType();

				// (296) <Ranking>
				if (ReportUtilities.searchData(reportDatas, "Ranking", "296",
						Integer.toString(i)) != null)
					complexBorrowingSourceType.setRanking(new BigInteger(
							ReportUtilities.searchData(reportDatas, "Ranking",
									"296", Integer.toString(i))));

				// (297) <BorrowingSourceFlag>
				if (ReportUtilities.searchData(reportDatas,
						"BorrowingSourceFlag", "297", Integer.toString(i)) != null)
					complexBorrowingSourceType.setBorrowingSourceFlag(Boolean
							.parseBoolean(ReportUtilities.searchData(
									reportDatas, "BorrowingSourceFlag", "297",
									Integer.toString(i))));

				// <SourceIdentification>
				ComplexEntityIdentificationType complexEntityIdentificationType = objectFactoryAIF
						.createComplexEntityIdentificationType();

				// (298) <EntityName>
				if (ReportUtilities.searchData(reportDatas, "EntityName",
						"298", Integer.toString(i)) != null)
					complexEntityIdentificationType
							.setEntityName(ReportUtilities.searchData(
									reportDatas, "EntityName", "298",
									Integer.toString(i)));

				// (299) <EntityIdentificationLEI>
				if (ReportUtilities.searchData(reportDatas,
						"EntityIdentificationLEI", "299", Integer.toString(i)) != null)
					complexEntityIdentificationType
							.setEntityIdentificationLEI(ReportUtilities
									.searchData(reportDatas,
											"EntityIdentificationLEI", "299",
											Integer.toString(i)));

				// (300) <EntityIdentificationBIC>
				if (ReportUtilities.searchData(reportDatas,
						"EntityIdentificationBIC", "300", Integer.toString(i)) != null)
					complexEntityIdentificationType
							.setEntityIdentificationBIC(ReportUtilities
									.searchData(reportDatas,
											"EntityIdentificationBIC", "300",
											Integer.toString(i)));

				// < / SourceIdentification>
				complexBorrowingSourceType
						.setSourceIdentification(complexEntityIdentificationType);

				// (301) <LeverageAmount>
				if (ReportUtilities.searchData(reportDatas, "LeverageAmount",
						"301", Integer.toString(i)) != null)
					complexBorrowingSourceType
							.setLeverageAmount(new BigInteger(ReportUtilities
									.searchData(reportDatas, "LeverageAmount",
											"301", Integer.toString(i))));

				// < / BorrowingSource>
				complexBorrowingSourceTypeList.add(complexBorrowingSourceType);
			}

			// < / AIFLeverageArticle24-4>

			// /////////////////////////////////////////////////////////////////
			// <CancellationAIFMRecordInfo>

			// (303) <CancelledAIFNationalCode>
			if (ReportUtilities.searchData(reportDatas,
					"CancelledAIFNationalCode", "303", null) != null)
				complexCancellationAIFRecordInfoType
						.setCancelledAIFNationalCode(ReportUtilities
								.searchData(reportDatas,
										"CancelledAIFNationalCode", "303", null));

			// (304) <CancelledAIFMNationalCode>
			if (ReportUtilities.searchData(reportDatas,
					"CancelledAIFMNationalCode", "304", null) != null)
				complexCancellationAIFRecordInfoType
						.setCancelledAIFMNationalCode(ReportUtilities
								.searchData(reportDatas,
										"CancelledAIFMNationalCode", "304",
										null));

			// (305) <CancelledReportingPeriodType>
			if (ReportUtilities.searchData(reportDatas,
					"CancelledReportingPeriodType", "305", null) != null) {
				ReportingPeriodTypeType cancelledReportingPeriodTypeType = ReportingPeriodTypeType
						.fromValue(ReportUtilities.searchData(reportDatas,
								"CancelledReportingPeriodType", "305", null));
				complexCancellationAIFRecordInfoType
						.setCancelledReportingPeriodType(cancelledReportingPeriodTypeType);
			}

			// (306) <CancelledReportingPeriodYear>
			if (ReportUtilities.searchData(reportDatas,
					"CancelledReportingPeriodYear", "306", null) != null) {
				try {
					Date cancelledReportingPeriodYear = formatYear
							.parse(ReportUtilities
									.searchData(reportDatas,
											"CancelledReportingPeriodYear",
											"306", null));
					XMLGregorianCalendar cancelledReportingPeriodYearXML = XMLGregorianCalendarConverter
							.asXMLGregorianCalendarDate(cancelledReportingPeriodYear);
					complexCancellationAIFRecordInfoType
							.setCancelledReportingPeriodYear(cancelledReportingPeriodYearXML);
				} catch (ParseException pe) {
					logger.error("Error in formatDateTime to field (306) <CancelledReportingPeriodYear>: "
							+ pe.getMessage());
				}
			}

			// (307) <CancelledRecordFlag>
			if (ReportUtilities.searchData(reportDatas, "CancelledRecordFlag",
					"307", null) != null) {
				CancelledRecordFlagType cancelledRecordFlagXML = CancelledRecordFlagType
						.fromValue(ReportUtilities.searchData(reportDatas,
								"CancelledRecordFlag", "307", null));
				complexCancellationAIFRecordInfoType
						.setCancelledRecordFlag(cancelledRecordFlagXML);
			}

			// /////////////////////////////////////////////////////////////////
			// MAIN PART
			// /////////////////////////////////////////////////////////////////

			// /////////////////////////////////////////////////////////////////
			// <AIFCompleteDescription><AIFPrincipalInfo>

			complexAIFIndividualInfoType
					.setIndividualExposure(complexIndividualExposureType);
			complexAIFIndividualInfoType.setRiskProfile(complexRiskProfileType);
			complexAIFIndividualInfoType.setStressTests(complexStressTestsType);

			// /////////////////////////////////////////////////////////////////
			// <AIFCompleteDescription><AIFLeverageInfo>

			complexAIFLeverageInfoType
					.setAIFLeverageArticle242(complexAIFLeverageArticle242Type);
			complexAIFLeverageInfoType
					.setAIFLeverageArticle244(complexAIFLeverageArticle244Type);

			// /////////////////////////////////////////////////////////////////
			// <AIFCompleteDescription><AIFPrincipalInfo>

			complexAIFPrincipalInfoType
					.setAIFDescription(complexAIFDescriptionType);
			complexAIFPrincipalInfoType
					.setAIFIdentification(complexAIFIdentifierType);
			complexAIFPrincipalInfoType
					.setAUMGeographicalFocus(complexAUMGeographicalFocusType);
			complexAIFPrincipalInfoType
					.setMainInstrumentsTraded(complexMainInstrumentsTradedType);

			complexAIFPrincipalInfoType
					.setMostImportantConcentration(complexMostImportantConcentrationType);

			complexAIFPrincipalInfoType
					.setNAVGeographicalFocus(complexNAVGeographicalFocusType);
			complexAIFPrincipalInfoType
					.setPrincipalExposures(complexPrincipalExposuresType);

			// /////////////////////////////////////////////////////////////////
			// <AIFRecordInfo><AIFCompleteDescription>

			complexAIFCompleteDescriptionType
					.setAIFIndividualInfo(complexAIFIndividualInfoType);
			complexAIFCompleteDescriptionType
					.setAIFLeverageInfo(complexAIFLeverageInfoType);
			complexAIFCompleteDescriptionType
					.setAIFPrincipalInfo(complexAIFPrincipalInfoType);

			complexAIFRecordInfoType
					.setAIFCompleteDescription(complexAIFCompleteDescriptionType);

			// create list of root elements: AIFMRecordInfo and
			// CancellationAIFMRecordInfo
			List<Object> listAIFRepoting = aifReportingInfo
					.getCancellationAIFRecordInfoOrAIFRecordInfo();
			listAIFRepoting.add(complexAIFRecordInfoType);
			listAIFRepoting.add(complexCancellationAIFRecordInfoType);

			// GENERATING XML WITH JAXB

			// http://www.tutorialspoint.com/java/xml/javax_xml_bind_jaxbelement.htm

			// create JAXBElement of type AIFMReportingInfo
			JAXBElement<AIFReportingInfo> jaxbElement = new JAXBElement(
					new QName(AIFReportingInfo.class.getSimpleName()),
					AIFReportingInfo.class, aifReportingInfo);

			// create JAXBContext which will be used to update writer
			JAXBContext context = JAXBContext
					.newInstance(AIFReportingInfo.class);

			// Create a String writer object which will be
			// used to write jaxbElment XML to string
			// StringWriter writer = new StringWriter();
			// marshall or convert jaxbElement
			// context.createMarshaller().marshal(jaxbElement, writer);
			// print XML string
			// logger.debug(writer.toString());

			// http://blog.sanaulla.info/2010/08/29/using-jaxb-to-generate-xml-from-the-java-xsd/
			// print nice in XML
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
			marshaller.marshal(jaxbElement, System.out);
			StringWriter st = new StringWriter();
			marshaller.marshal(jaxbElement, st);

			GenerateXMLForm result = new GenerateXMLForm();
			result.setOutputXML(st.toString());
			result.setReportExecution(reportExecution);
			result.setValid(GeneratorXMLUtils.validateSchemaXSD(st.toString(),
					reportExecution, aifXSDResource, applicationContext, logger));

			return result;

		} catch (JAXBException e) {
			logger.error("Error in JAXB XML: " + e.getMessage());
			ReportingErrorManager.createReportError(applicationContext,
					ErrorTypeEnum.GENERATION.getErrorType(), reportExecution,
					"FAIL", "Error in JAXB XML: " + e.getMessage());
			// TODO clean
			e.printStackTrace();

			// } catch (ParseException e) {
			// logger.error("Error when parsing XML: " + e.getMessage());
			// ReportingErrorManager.createReportError(applicationContext,
			// ErrorTypeEnum.GENERATION.getErrorType(), reportExecution,
			// "FAIL", "Error when parsing XML: " + e.getMessage());
			//
			// // TODO clean
			// e.printStackTrace();

		} catch (NumberFormatException e) {
			logger.error("Number format error in XML process: "
					+ e.getMessage());
			ReportingErrorManager.createReportError(applicationContext,
					ErrorTypeEnum.GENERATION.getErrorType(), reportExecution,
					"FAIL",
					"Number format error in XML process: " + e.getMessage());

			// TODO clean
			e.printStackTrace();

		} catch (Exception e) {
			logger.error("General error in XML process: " + e.getMessage());
			ReportingErrorManager.createReportError(applicationContext,
					ErrorTypeEnum.GENERATION.getErrorType(), reportExecution,
					"FAIL", "General error in XML process: " + e.getMessage());

			// TODO clean
			e.printStackTrace();
		}

		return null;
	}
}
