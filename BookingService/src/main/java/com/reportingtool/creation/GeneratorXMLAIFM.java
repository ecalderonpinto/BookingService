package com.reportingtool.creation;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.reportingtool.xml.AIFMReportingInfo;
import com.reportingtool.xml.CancelledRecordFlagType;
import com.reportingtool.xml.ComplexAIFMCompleteDescriptionType;
import com.reportingtool.xml.ComplexAIFMIdentifierType;
import com.reportingtool.xml.ComplexAIFMNationalIdentifierType;
import com.reportingtool.xml.ComplexAIFMPrincipalInstrumentsType;
import com.reportingtool.xml.ComplexAIFMPrincipalMarketsType;
import com.reportingtool.xml.ComplexAIFMRecordInfoType;
import com.reportingtool.xml.ComplexAssumptionType;
import com.reportingtool.xml.ComplexAssumptionsType;
import com.reportingtool.xml.ComplexBaseCurrencyDescriptionType;
import com.reportingtool.xml.ComplexCancellationAIFMRecordInfoType;
import com.reportingtool.xml.ComplexFivePrincipalMarketType;
import com.reportingtool.xml.ComplexMarketIdentificationWithNOTType;
import com.reportingtool.xml.ComplexPrincipalInstrumentType;
import com.reportingtool.xml.FXEURReferenceRateTypeType;
import com.reportingtool.xml.FilingTypeType;
import com.reportingtool.xml.MarketCodeTypeWithNOTType;
import com.reportingtool.xml.ObjectFactoryAIFM;
import com.reportingtool.xml.ReportingObligationChangeFrequencyCodeType;
import com.reportingtool.xml.ReportingObligationChangeQuarterType;
import com.reportingtool.xml.ReportingPeriodTypeType;
import com.reportingtool.xml.SubAssetTypeType;

/**
 * Class to generate XML string from a reportExecution type AIFM
 * 
 * @author Alberto
 * 
 */
public class GeneratorXMLAIFM {

	/**
	 * Function generate a aifmXML from a reportExecution with XSD classes and
	 * validate it. Create reportErrores
	 * 
	 * @param reportExecution
	 * @return aifmXML string
	 */
	public static GenerateXMLForm generateXMLAIFM(
			ReportExecution reportExecution,
			ApplicationContext applicationContext, Logger logger,
			String aifmXSDResource) {

		logger.info("GeneratorXML: starting XML generation ");

		// all dataFields
		List<ReportData> reportDatas = new ArrayList<ReportData>(
				reportExecution.getReportDatas());

		for (ReportData reportData : reportDatas) {
			logger.info(reportData.getReportField().getReportFieldName() + "("
					+ reportData.getReportField().getReportFieldNum() + "): "
					+ reportData.getReportDataText());
		}

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
			ObjectFactoryAIFM objectFactoryAIFM = new ObjectFactoryAIFM();

			AIFMReportingInfo aifmReportingInfo = objectFactoryAIFM
					.createAIFMReportingInfo();

			ComplexCancellationAIFMRecordInfoType complexCancellationAIFMRecordInfoType = objectFactoryAIFM
					.createComplexCancellationAIFMRecordInfoType();

			ComplexAIFMRecordInfoType complexAIFMRecordInfoType = objectFactoryAIFM
					.createComplexAIFMRecordInfoType();

			ComplexAIFMCompleteDescriptionType complexAIFMCompleteDescriptionType = objectFactoryAIFM
					.createComplexAIFMCompleteDescriptionType();

			ComplexBaseCurrencyDescriptionType complexBaseCurrencyDescriptionType = objectFactoryAIFM
					.createComplexBaseCurrencyDescriptionType();

			ComplexAIFMIdentifierType complexAIFMIdentifierType = objectFactoryAIFM
					.createComplexAIFMIdentifierType();

			ComplexAIFMNationalIdentifierType complexAIFMNationalIdentifierType = objectFactoryAIFM
					.createComplexAIFMNationalIdentifierType();

			ComplexAIFMPrincipalInstrumentsType complexAIFMPrincipalInstrumentsType = objectFactoryAIFM
					.createComplexAIFMPrincipalInstrumentsType();

			ComplexAIFMPrincipalMarketsType complexAIFMPrincipalMarketsType = objectFactoryAIFM
					.createComplexAIFMPrincipalMarketsType();

			ComplexAssumptionsType complexAssumptionsType = objectFactoryAIFM
					.createComplexAssumptionsType();

			// /////////////////////////////////////////////////////////////////
			// <AifmReportingInfo>

			// (1) <ReportingMemberState>
			if (ReportUtilities.searchData(reportDatas, "ReportingMemberState",
					"1", null) != null)
				aifmReportingInfo.setReportingMemberState(ReportUtilities
						.searchData(reportDatas, "ReportingMemberState", "1",
								null));

			// (2) <Version>
			if (ReportUtilities.searchData(reportDatas, "Version", "2", null) != null)
				aifmReportingInfo.setVersion(ReportUtilities.searchData(
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
					aifmReportingInfo
							.setCreationDateAndTime(creationDateAndTimeXML);
				} catch (ParseException pe) {
					logger.error("Error in formatDateTime to field (3) <CreationDateAndTime>: "
							+ pe.getMessage());
				}
			}

			// /////////////////////////////////////////////////////////////////
			// <AIFMRecordInfo>

			// (4) <FilingType>
			if (ReportUtilities
					.searchData(reportDatas, "FilingType", "4", null) != null) {
				FilingTypeType filingTypeType = FilingTypeType
						.valueOf(ReportUtilities.searchData(reportDatas,
								"FilingType", "4", null));
				complexAIFMRecordInfoType.setFilingType(filingTypeType);
			}

			// (5) <AIFMContentType>
			if (ReportUtilities.searchData(reportDatas, "AIFMContentType", "5",
					null) != null)
				complexAIFMRecordInfoType.setAIFMContentType(ReportUtilities
						.searchData(reportDatas, "AIFMContentType", "5", null));

			// (6) <ReportingPeriodStartDate>
			if (ReportUtilities.searchData(reportDatas,
					"ReportingPeriodStartDate", "6", null) != null) {
				try {
					Date reportingPeriodStartDate = formatDate
							.parse(ReportUtilities.searchData(reportDatas,
									"ReportingPeriodStartDate", "6", null));
					XMLGregorianCalendar reportingPeriodStartDateXML = XMLGregorianCalendarConverter
							.asXMLGregorianCalendarDate(reportingPeriodStartDate);
					complexAIFMRecordInfoType
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
					Date reportingPerdiodEndDate = formatDate
							.parse(ReportUtilities.searchData(reportDatas,
									"ReportingPeriodEndDate", "7", null));
					XMLGregorianCalendar reportingPerdiodEndDateXML = XMLGregorianCalendarConverter
							.asXMLGregorianCalendarDate(reportingPerdiodEndDate);
					complexAIFMRecordInfoType
							.setReportingPeriodEndDate(reportingPerdiodEndDateXML);
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
				complexAIFMRecordInfoType
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
					complexAIFMRecordInfoType
							.setReportingPeriodYear(reportingPeriodYearXML);
				} catch (ParseException pe) {
					logger.error("Error in formatDateTime to field (9) <ReportingPeriodYear>: "
							+ pe.getMessage());
				}
			}

			// (10) <AIFMReportingObligationChangeFrequencyCode>
			if (ReportUtilities.searchData(reportDatas,
					"AIFMReportingObligationChangeFrequencyCode", "10", null) != null) {
				ReportingObligationChangeFrequencyCodeType reportingObligationChangeFrequencyCode = ReportingObligationChangeFrequencyCodeType
						.fromValue(ReportUtilities.searchData(reportDatas,
								"AIFMReportingObligationChangeFrequencyCode",
								"10", null));
				complexAIFMRecordInfoType
						.setAIFMReportingObligationChangeFrequencyCode(reportingObligationChangeFrequencyCode);
			}

			// (11) <AIFMReportingObligationChangeContentsCode>
			if (ReportUtilities.searchData(reportDatas,
					"AIFMReportingObligationChangeContentsCode", "11", null) != null)
				complexAIFMRecordInfoType
						.setAIFMReportingObligationChangeContentsCode(new BigInteger(
								ReportUtilities
										.searchData(
												reportDatas,
												"AIFMReportingObligationChangeContentsCode",
												"11", null)));

			// (12) <AIFMReportingObligationChangeQuarter>
			if (ReportUtilities.searchData(reportDatas,
					"AIFMReportingObligationChangeQuarter", "12", null) != null) {

				ReportingObligationChangeQuarterType reportingObligationChangeQuarterType = ReportingObligationChangeQuarterType
						.fromValue(ReportUtilities.searchData(reportDatas,
								"AIFMReportingObligationChangeQuarter", "12",
								null));
				complexAIFMRecordInfoType
						.setAIFMReportingObligationChangeQuarter(reportingObligationChangeQuarterType);
			}

			// (13) <LastReportingFlag>
			if (ReportUtilities.searchData(reportDatas, "LastReportingFlag",
					"13", null) != null)
				complexAIFMRecordInfoType.setLastReportingFlag(Boolean
						.parseBoolean(ReportUtilities.searchData(reportDatas,
								"LastReportingFlag", "13", null)));

			// /////////////////////////////////////////////////////////////////
			// <Assumptions>
			List<ComplexAssumptionType> complexAssumptionTypeList = complexAssumptionsType
					.getAssumption();
			List<Integer> assumptionCounts = new ArrayList<Integer>();
			// first store in List all dataBlock
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals("AssumptionDescription"))
					assumptionCounts.add(Integer.parseInt(reportData
							.getReportDataBlock()));
			}
			for (Integer i : assumptionCounts) {
				BigInteger questionNumber = new BigInteger("0");
				String assumptionDescription = "";
				// <Assumption>
				ComplexAssumptionType complexAssumptionType = objectFactoryAIFM
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

				complexAssumptionTypeList.add(complexAssumptionType);
				logger.debug("assumption i " + i + " " + questionNumber + " "
						+ assumptionDescription);
			}
			complexAIFMRecordInfoType.setAssumptions(complexAssumptionsType);

			// /////////////////////////////////////////////////////////////////

			// (16) <AIFMReportingCode>
			if (ReportUtilities.searchData(reportDatas, "AIFMReportingCode",
					"16", null) != null)
				complexAIFMRecordInfoType.setAIFMReportingCode(new BigInteger(
						ReportUtilities.searchData(reportDatas,
								"AIFMReportingCode", "16", null)));

			// (17) <AIFMJurisdiction>
			if (ReportUtilities.searchData(reportDatas, "AIFMJurisdiction",
					"17", null) != null)
				complexAIFMRecordInfoType
						.setAIFMJurisdiction(ReportUtilities.searchData(
								reportDatas, "AIFMJurisdiction", "17", null));

			// (18) <AIFMNationalCode>
			if (ReportUtilities.searchData(reportDatas, "AIFMNationalCode",
					"18", null) != null)
				complexAIFMRecordInfoType
						.setAIFMNationalCode(ReportUtilities.searchData(
								reportDatas, "AIFMNationalCode", "18", null));

			// (19) <AIFMName>
			if (ReportUtilities.searchData(reportDatas, "AIFMName", "19", null) != null)
				complexAIFMRecordInfoType.setAIFMName(ReportUtilities
						.searchData(reportDatas, "AIFMName", "19", null));

			// (20) <AIFMEEAFlag>
			if (ReportUtilities.searchData(reportDatas, "AIFMEEAFlag", "20",
					null) != null)
				complexAIFMRecordInfoType.setAIFMEEAFlag(Boolean
						.parseBoolean(ReportUtilities.searchData(reportDatas,
								"AIFMEEAFlag", "20", null)));

			// (21) <AIFMNoReportingFlag>
			if (ReportUtilities.searchData(reportDatas, "AIFMNoReportingFlag",
					"21", null) != null)
				complexAIFMRecordInfoType.setAIFMNoReportingFlag(Boolean
						.parseBoolean(ReportUtilities.searchData(reportDatas,
								"AIFMNoReportingFlag", "21", null)));

			// /////////////////////////////////////////////////////////////////
			// <AIFMPrincipalMarkets>

			List<ComplexFivePrincipalMarketType> complexFivePrincipalMarketTypeList = complexAIFMPrincipalMarketsType
					.getAIFMFivePrincipalMarket();

			for (int i = 1; i < 6; i++) {
				if (ReportUtilities.searchData(reportDatas, "Ranking", "26",
						Integer.toString(i)) == null) {
					continue;
				} else {

					ComplexFivePrincipalMarketType complexFivePrincipalMarketType = objectFactoryAIFM
							.createComplexFivePrincipalMarketType();

					ComplexMarketIdentificationWithNOTType complexMarketIdentificationWithNOTType = objectFactoryAIFM
							.createComplexMarketIdentificationWithNOTType();

					// (26) <Ranking>
					if (ReportUtilities.searchData(reportDatas, "Ranking",
							"26", Integer.toString(i)) != null)
						complexFivePrincipalMarketType
								.setRanking(new BigInteger(ReportUtilities
										.searchData(reportDatas, "Ranking",
												"26", Integer.toString(i))));

					// (27) <MarketCodeType>
					if (ReportUtilities.searchData(reportDatas,
							"MarketCodeType", "27", Integer.toString(i)) != null) {
						MarketCodeTypeWithNOTType marketCodeTypeWithNOTType = MarketCodeTypeWithNOTType
								.fromValue(ReportUtilities.searchData(
										reportDatas, "MarketCodeType", "27",
										Integer.toString(i)));
						complexMarketIdentificationWithNOTType
								.setMarketCodeType(marketCodeTypeWithNOTType);

						// (28) <MarketCode>
						if (ReportUtilities.searchData(reportDatas,
								"MarketCode", "28", Integer.toString(i)) != null) {
							complexMarketIdentificationWithNOTType
									.setMarketCode(ReportUtilities.searchData(
											reportDatas, "MarketCode", "28",
											Integer.toString(i)));
						}

						complexFivePrincipalMarketType
								.setMarketIdentification(complexMarketIdentificationWithNOTType);
					}

					// (29) <AggregatedValueAmount>
					if (ReportUtilities.searchData(reportDatas,
							"AggregatedValueAmount", "29", Integer.toString(i)) != null)
						complexFivePrincipalMarketType
								.setAggregatedValueAmount(new BigInteger(
										ReportUtilities.searchData(reportDatas,
												"AggregatedValueAmount", "29",
												Integer.toString(i))));

					complexFivePrincipalMarketTypeList
							.add(complexFivePrincipalMarketType);
				}
			}

			// /////////////////////////////////////////////////////////////////
			// <AIFMPrincipalInstruments>

			List<ComplexPrincipalInstrumentType> complexPrincipalInstrumentTypeList = complexAIFMPrincipalInstrumentsType
					.getAIFMPrincipalInstrument();

			for (int i = 1; i < 6; i++) {
				if (ReportUtilities.searchData(reportDatas, "Ranking", "30",
						Integer.toString(i)) == null) {
					continue;
				} else {
					ComplexPrincipalInstrumentType complexPrincipalInstrumentType = objectFactoryAIFM
							.createComplexPrincipalInstrumentType();

					// (30) <Ranking>
					if (ReportUtilities.searchData(reportDatas, "Ranking",
							"30", Integer.toString(i)) != null)
						complexPrincipalInstrumentType
								.setRanking(new BigInteger(ReportUtilities
										.searchData(reportDatas, "Ranking",
												"30", Integer.toString(i))));
					// (31) <SubAssetType>
					if (ReportUtilities.searchData(reportDatas, "SubAssetType",
							"31", Integer.toString(i)) != null) {
						SubAssetTypeType subAssetTypeType = SubAssetTypeType
								.fromValue(ReportUtilities.searchData(
										reportDatas, "SubAssetType", "31",
										Integer.toString(i)));
						complexPrincipalInstrumentType
								.setSubAssetType(subAssetTypeType);
					}

					// (32) <AggregatedValueAmount>
					if (ReportUtilities.searchData(reportDatas,
							"AggregatedValueAmount", "32", Integer.toString(i)) != null)
						complexPrincipalInstrumentType
								.setAggregatedValueAmount(new BigInteger(
										ReportUtilities.searchData(reportDatas,
												"AggregatedValueAmount", "32",
												Integer.toString(i))));

					complexPrincipalInstrumentTypeList
							.add(complexPrincipalInstrumentType);
				}
			}

			// /////////////////////////////////////////////////////////////////
			// <AIFMCompleteDescription>

			// (22) <AIFMIdentifierLEI>
			if (ReportUtilities.searchData(reportDatas, "AIFMIdentifierLEI",
					"22", null) != null)
				complexAIFMIdentifierType.setAIFMIdentifierLEI(ReportUtilities
						.searchData(reportDatas, "AIFMIdentifierLEI", "22",
								null));

			// (23) <AIFMIdentifierBIC>
			if (ReportUtilities.searchData(reportDatas, "AIFMIdentifierBIC",
					"23", null) != null)
				complexAIFMIdentifierType.setAIFMIdentifierBIC(ReportUtilities
						.searchData(reportDatas, "AIFMIdentifierBIC", "23",
								null));

			// (24) <Old_ReportingMemberState>
			if (ReportUtilities.searchData(reportDatas,
					"Old_ReportingMemberState", "24", null) != null)
				complexAIFMNationalIdentifierType
						.setReportingMemberState(ReportUtilities.searchData(
								reportDatas, "Old_ReportingMemberState", "24",
								null));

			// (25) <Old_AIFMNationalCode>
			if (ReportUtilities.searchData(reportDatas, "Old_AIFMNationalCode",
					"25", null) != null)
				complexAIFMNationalIdentifierType
						.setAIFMNationalCode(ReportUtilities
								.searchData(reportDatas,
										"Old_AIFMNationalCode", "25", null));

			// (33) <AUMAmountInEuro>
			if (ReportUtilities.searchData(reportDatas, "AUMAmountInEuro",
					"33", null) != null)
				complexAIFMCompleteDescriptionType
						.setAUMAmountInEuro(new BigInteger(ReportUtilities
								.searchData(reportDatas, "AUMAmountInEuro",
										"33", null)));

			// (34) <AUMAmountInBaseCurrency>
			if (ReportUtilities.searchData(reportDatas,
					"AUMAmountInBaseCurrency", "34", null) != null)
				complexBaseCurrencyDescriptionType
						.setAUMAmountInBaseCurrency(new BigInteger(
								ReportUtilities.searchData(reportDatas,
										"AUMAmountInBaseCurrency", "34", null)));

			// (35) <BaseCurrency>
			if (ReportUtilities.searchData(reportDatas, "BaseCurrency", "35",
					null) != null)
				complexBaseCurrencyDescriptionType
						.setBaseCurrency(ReportUtilities.searchData(
								reportDatas, "BaseCurrency", "35", null));

			// (36) <FXEURReferenceRateType>
			if (ReportUtilities.searchData(reportDatas,
					"FXEURReferenceRateType", "36", null) != null) {
				FXEURReferenceRateTypeType fxEURReferenceRateTypeType = FXEURReferenceRateTypeType
						.fromValue(ReportUtilities.searchData(reportDatas,
								"FXEURReferenceRateType", "36", null));
				complexBaseCurrencyDescriptionType
						.setFXEURReferenceRateType(fxEURReferenceRateTypeType);
			}

			// (37) <FXEURRate>
			if (ReportUtilities
					.searchData(reportDatas, "FXEURRate", "37", null) != null)
				complexBaseCurrencyDescriptionType.setFXEURRate(new BigDecimal(
						ReportUtilities.searchData(reportDatas, "FXEURRate",
								"37", null)));

			// (38) <FXEUROtherReferenceRateDescription>
			if (ReportUtilities.searchData(reportDatas,
					"FXEUROtherReferenceRateDescription", "38", null) != null)
				complexBaseCurrencyDescriptionType
						.setFXEUROtherReferenceRateDescription(ReportUtilities
								.searchData(reportDatas,
										"FXEUROtherReferenceRateDescription",
										"38", null));

			// (24) (25)
			if (ReportUtilities.searchData(reportDatas,
					"Old_ReportingMemberState", "24", null) != null
					&& ReportUtilities.searchData(reportDatas,
							"Old_AIFMNationalCode", "25", null) != null)
				complexAIFMIdentifierType
						.setOldAIFMIdentifierNCA(complexAIFMNationalIdentifierType);

			complexAIFMCompleteDescriptionType
					.setAIFMIdentifier(complexAIFMIdentifierType);
			complexAIFMCompleteDescriptionType
					.setAIFMBaseCurrencyDescription(complexBaseCurrencyDescriptionType);
			complexAIFMCompleteDescriptionType
					.setAIFMPrincipalInstruments(complexAIFMPrincipalInstrumentsType);
			complexAIFMCompleteDescriptionType
					.setAIFMPrincipalMarkets(complexAIFMPrincipalMarketsType);

			complexAIFMRecordInfoType
					.setAIFMCompleteDescription(complexAIFMCompleteDescriptionType);

			// /////////////////////////////////////////////////////////////////
			// <CancellationAIFMRecordInfo>

			// (39) <CancelledAIFMNationalCode>
			if (ReportUtilities.searchData(reportDatas,
					"CancelledAIFMNationalCode", "39", null) != null)
				complexCancellationAIFMRecordInfoType
						.setCancelledAIFMNationalCode(ReportUtilities
								.searchData(reportDatas,
										"CancelledAIFMNationalCode", "39", null));

			// (40) <CancelledReportingPeriodType>
			if (ReportUtilities.searchData(reportDatas,
					"CancelledReportingPeriodType", "40", null) != null) {
				ReportingPeriodTypeType cancelledReportingPeriodTypeType = ReportingPeriodTypeType
						.fromValue(ReportUtilities.searchData(reportDatas,
								"CancelledReportingPeriodType", "40", null));
				complexCancellationAIFMRecordInfoType
						.setCancelledReportingPeriodType(cancelledReportingPeriodTypeType);
			}

			// (41) <CancelledReportingPeriodYear>
			if (ReportUtilities.searchData(reportDatas,
					"CancelledReportingPeriodYear", "41", null) != null) {
				try {
					Date cancelledReportingPeriodYear = formatYear
							.parse(ReportUtilities.searchData(reportDatas,
									"CancelledReportingPeriodYear", "41", null));
					XMLGregorianCalendar cancelledReportingPeriodYearXML = XMLGregorianCalendarConverter
							.asXMLGregorianCalendarDate(cancelledReportingPeriodYear);
					complexCancellationAIFMRecordInfoType
							.setCancelledReportingPeriodYear(cancelledReportingPeriodYearXML);
				} catch (ParseException pe) {
					logger.error("Error in formatDateTime to field (41) <CancelledReportingPeriodYear>: "
							+ pe.getMessage());
				}
			}

			// (42) <CancelledRecordFlag>
			if (ReportUtilities.searchData(reportDatas, "CancelledRecordFlag",
					"42", null) != null) {
				CancelledRecordFlagType cancelledRecordFlagXML = CancelledRecordFlagType
						.fromValue(ReportUtilities.searchData(reportDatas,
								"CancelledRecordFlag", "42", null));
				complexCancellationAIFMRecordInfoType
						.setCancelledRecordFlag(cancelledRecordFlagXML);
			}

			// /////////////////////////////////////////////////////////////////
			// create list of root elements:
			// AIFMRecordInfo and CancellationAIFMRecordInfo
			List<Object> listAIFMReporting = aifmReportingInfo
					.getCancellationAIFMRecordInfoOrAIFMRecordInfo();
			listAIFMReporting.add(complexAIFMRecordInfoType);
			listAIFMReporting.add(complexCancellationAIFMRecordInfoType);

			// GENERATING XML WITH JAXB

			// http://www.tutorialspoint.com/java/xml/javax_xml_bind_jaxbelement.htm

			// create JAXBElement of type AIFMReportingInfo
			JAXBElement<AIFMReportingInfo> jaxbElement = new JAXBElement(
					new QName(AIFMReportingInfo.class.getSimpleName()),
					AIFMReportingInfo.class, aifmReportingInfo);

			// create JAXBContext which will be used to update writer
			JAXBContext context = JAXBContext
					.newInstance(AIFMReportingInfo.class);

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
					reportExecution, aifmXSDResource, applicationContext,
					logger));

			return result;

			// // need a styler.xsl to transform XML to HTML
			// StringReader reader = new StringReader(st.toString());
			// StringWriter writer = new StringWriter();
			// TransformerFactory tFactory = TransformerFactory.newInstance();
			// Transformer transformer = tFactory.newTransformer(
			// new javax.xml.transform.stream.StreamSource("styler.xsl"));
			// transformer.transform(
			// new javax.xml.transform.stream.StreamSource(reader),
			// new javax.xml.transform.stream.StreamResult(writer));
			// String result = writer.toString();
			// return result;

		} catch (JAXBException e) {
			logger.error("Error in JAXB XML: " + e.getMessage());
			ReportingErrorManager.createReportError(applicationContext,
					ErrorTypeEnum.GENERATION.getErrorType(), reportExecution,
					"FAIL", "Error in JAXB XML: " + e.getMessage());
//		} catch (ParseException e) {
//			logger.error("Error when parsing XML: " + e.getMessage());
//			ReportingErrorManager.createReportError(applicationContext,
//					ErrorTypeEnum.GENERATION.getErrorType(), reportExecution,
//					"FAIL", "Error when parsing XML: " + e.getMessage());
		} catch (NumberFormatException e) {
			logger.error("Number format error in XML process: "
					+ e.getMessage());
			ReportingErrorManager.createReportError(applicationContext,
					ErrorTypeEnum.GENERATION.getErrorType(), reportExecution,
					"FAIL",
					"Number format error in XML process: " + e.getMessage());
		} catch (Exception e) {
			logger.error("General error in XML process: " + e.getMessage());
			ReportingErrorManager.createReportError(applicationContext,
					ErrorTypeEnum.GENERATION.getErrorType(), reportExecution,
					"FAIL", "General error in XML process: " + e.getMessage());
		}

		return null;
	}
}
