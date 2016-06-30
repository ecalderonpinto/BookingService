package com.reportingtool.creation;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.entities.dao.reportingtool.ReportExecutionDAO;
import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.utilities.hibernate.VersionAuditor;
import com.reportingtool.utilities.ReportUtilities;
import com.reportingtool.utilities.XMLGregorianCalendarConverter;
import com.reportingtool.xml.AIFMReportingInfo;
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

/**
 * Class to load a XML file and convert to reportExecution type AIFM
 * 
 * @author alberto.olivan
 * 
 */
public class LoadXMLAIFM {

	private ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(LoadXMLAIFM.class);

	public LoadXMLAIFM(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	// http://java.dzone.com/articles/using-jaxb-generate-java

	/**
	 * Function receive a File and AIFM reportExecution, read this File to add
	 * reportDatas from XML file
	 * 
	 * @param file
	 * @param reportExecution
	 * @param update
	 *            : true -> update or insert / false -> only insert
	 * @return reportExecution with reportDatas from file
	 */
	public ReportExecution loadAIFMFile(File file,
			ReportExecution reportExecution, boolean update) throws Exception {

		logger.info("LoadXMLAIFM: starting loading " + file.getAbsolutePath());

		// 1. We need to create JAXContext instance
		JAXBContext jaxbContext = JAXBContext
				.newInstance(AIFMReportingInfo.class);

		// 2. Use JAXBContext instance to create the Unmarshaller.
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		// 3. Use the Unmarshaller to unmarshal the XML document to get an
		// instance of JAXBElement.
		JAXBElement<AIFMReportingInfo> root = (JAXBElement<AIFMReportingInfo>) unmarshaller
				.unmarshal(new StreamSource(file), AIFMReportingInfo.class);

		// 4. Get the instance of the required JAXB Root Class from the
		AIFMReportingInfo aifmReportingInfo = root.getValue();

		List<Object> listAIFMReporting = aifmReportingInfo
				.getCancellationAIFMRecordInfoOrAIFMRecordInfo();

		ComplexAIFMRecordInfoType complexAIFMRecordInfoType = null;
		ComplexCancellationAIFMRecordInfoType complexCancellationAIFMRecordInfoType = null;

		for (Object object : listAIFMReporting) {
			if (object instanceof ComplexAIFMRecordInfoType) {
				complexAIFMRecordInfoType = (ComplexAIFMRecordInfoType) object;
			}
			if (object instanceof ComplexCancellationAIFMRecordInfoType) {
				complexCancellationAIFMRecordInfoType = (ComplexCancellationAIFMRecordInfoType) object;
			}

		}

		// Process inverse of GenerateXML, the class aifmReportingInfo contains
		// all objects inside and we unmarshal creating reportDatas, searching
		// of every reportFields and add to reportExecution if not exists

		VersionAuditor user = new VersionAuditor("loader");

		ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
				.getBean("reportExecutionDAO");

		reportExecution = reportExecutionDAO.findById(reportExecution.getId());

		List<ReportData> reportDatas = reportExecution.getReportDatas();

		// if true, this load try to update or insert info, if false only insert
		//update = false;

		String temp = null;
		String block = null;
		int count = 0;

		// (1) <ReportingMemberState>
		if (aifmReportingInfo.getReportingMemberState() != null) {
			temp = aifmReportingInfo.getReportingMemberState();
			ReportUtilities.saveData(temp, "ReportingMemberState", "1", null,
					reportExecution, user, update);
		}

		// (2) <Version>
		if (aifmReportingInfo.getVersion() != null) {
			temp = aifmReportingInfo.getVersion();
			ReportUtilities.saveData(temp, "Version", "2", null,
					reportExecution, user, update);
		}

		// (3) <CreationDateAndTime>
		if (aifmReportingInfo.getCreationDateAndTime() != null) {
			temp = new SimpleDateFormat(ReportUtilities.dateTimePattern)
					.format(XMLGregorianCalendarConverter
							.asDate(aifmReportingInfo.getCreationDateAndTime()));
			ReportUtilities.saveData(temp, "CreationDateAndTime", "3", null,
					reportExecution, user, update);
		}

		if (complexAIFMRecordInfoType != null) {

			// (4) <FilingType>
			if (complexAIFMRecordInfoType.getFilingType() != null) {
				temp = complexAIFMRecordInfoType.getFilingType().value();
				ReportUtilities.saveData(temp, "FilingType", "4", null,
						reportExecution, user, update);
			}

			// (5) <AIFMContentType>
			if (complexAIFMRecordInfoType.getAIFMContentType() != null) {
				temp = complexAIFMRecordInfoType.getAIFMContentType();
				ReportUtilities.saveData(temp, "AIFMContentType", "5", null,
						reportExecution, user, update);
			}

			// (6) <ReportingPeriodStartDate>
			if (complexAIFMRecordInfoType.getReportingPeriodStartDate() != null) {
				temp = new SimpleDateFormat(ReportUtilities.datePattern)
						.format(XMLGregorianCalendarConverter
								.asDate(complexAIFMRecordInfoType
										.getReportingPeriodStartDate()));
				ReportUtilities.saveData(temp, "ReportingPeriodStartDate", "6",
						null, reportExecution, user, update);
			}

			// (7) <ReportingPeriodEndDate>
			if (complexAIFMRecordInfoType.getReportingPeriodEndDate() != null) {
				temp = new SimpleDateFormat(ReportUtilities.datePattern)
						.format(XMLGregorianCalendarConverter
								.asDate(complexAIFMRecordInfoType
										.getReportingPeriodEndDate()));
				ReportUtilities.saveData(temp, "ReportingPeriodEndDate", "7",
						null, reportExecution, user, update);
			}

			// (8) <ReportingPeriodType>
			if (complexAIFMRecordInfoType.getReportingPeriodType() != null) {
				temp = complexAIFMRecordInfoType.getReportingPeriodType()
						.value();
				ReportUtilities.saveData(temp, "ReportingPeriodType", "8",
						null, reportExecution, user, update);
			}

			// (9) <ReportingPeriodYear>
			if (complexAIFMRecordInfoType.getReportingPeriodYear() != null) {
				temp = new SimpleDateFormat(ReportUtilities.yearPattern)
						.format(XMLGregorianCalendarConverter
								.asDate(complexAIFMRecordInfoType
										.getReportingPeriodYear()));
				ReportUtilities.saveData(temp, "ReportingPeriodYear", "9",
						null, reportExecution, user, update);
			}

			// (10) <AIFMReportingObligationChangeFrequencyCode>
			if (complexAIFMRecordInfoType
					.getAIFMReportingObligationChangeFrequencyCode() != null) {
				temp = complexAIFMRecordInfoType
						.getAIFMReportingObligationChangeFrequencyCode()
						.value();
				ReportUtilities.saveData(temp,
						"AIFMReportingObligationChangeFrequencyCode", "10",
						null, reportExecution, user, update);
			}

			// (11) <AIFMReportingObligationChangeContentsCode>
			if (complexAIFMRecordInfoType
					.getAIFMReportingObligationChangeContentsCode() != null) {
				temp = complexAIFMRecordInfoType
						.getAIFMReportingObligationChangeContentsCode()
						.toString();
				ReportUtilities.saveData(temp,
						"AIFMReportingObligationChangeContentsCode", "11",
						null, reportExecution, user, update);
			}

			// (12) <AIFMReportingObligationChangeQuarter>
			if (complexAIFMRecordInfoType
					.getAIFMReportingObligationChangeQuarter() != null) {
				temp = complexAIFMRecordInfoType
						.getAIFMReportingObligationChangeQuarter().value();
				ReportUtilities.saveData(temp,
						"AIFMReportingObligationChangeQuarter", "12", null,
						reportExecution, user, update);
			}

			// (13) <LastReportingFlag>
			temp = Boolean.toString(complexAIFMRecordInfoType
					.isLastReportingFlag());
			ReportUtilities.saveData(temp, "LastReportingFlag", "13", null,
					reportExecution, user, update);

			// /////////////////////////////////////////////////////////////////
			// <Assumptions>

			ComplexAssumptionsType complexAssumptionsType = complexAIFMRecordInfoType
					.getAssumptions();

			if (complexAssumptionsType != null) {

				List<ComplexAssumptionType> complexAssumptionTypeList = complexAssumptionsType
						.getAssumption();

				count = 0;
				for (ComplexAssumptionType complexAssumptionType : complexAssumptionTypeList) {

					count++;
					block = Integer.toString(count);

					// (14) <QuestionNumber>
					if (complexAssumptionType.getQuestionNumber() != null) {
						temp = complexAssumptionType.getQuestionNumber()
								.toString();
						ReportUtilities.saveData(temp, "QuestionNumber", "14",
								block, reportExecution, user, update);
					}

					// (15) <AssumptionDescription>
					if (complexAssumptionType.getAssumptionDescription() != null) {
						temp = complexAssumptionType.getAssumptionDescription();

						ReportUtilities.saveData(temp, "AssumptionDescription",
								"15", block, reportExecution, user, update);
					}

				}

			} // end complexAssumptionsType

			// (16) <AIFMReportingCode>
			if (complexAIFMRecordInfoType.getAIFMReportingCode() != null) {
				temp = complexAIFMRecordInfoType.getAIFMReportingCode()
						.toString();
				ReportUtilities.saveData(temp, "AIFMReportingCode", "16", null,
						reportExecution, user, update);
			}

			// (17) <AIFMJurisdiction>
			if (complexAIFMRecordInfoType.getAIFMJurisdiction() != null) {
				temp = complexAIFMRecordInfoType.getAIFMJurisdiction();
				ReportUtilities.saveData(temp, "AIFMJurisdiction", "17", null,
						reportExecution, user, update);
			}

			// (18) <AIFMNationalCode>
			if (complexAIFMRecordInfoType.getAIFMNationalCode() != null) {
				temp = complexAIFMRecordInfoType.getAIFMNationalCode();
				ReportUtilities.saveData(temp, "AIFMNationalCode", "18", null,
						reportExecution, user, update);
			}

			// (19) <AIFMName>
			if (complexAIFMRecordInfoType.getAIFMName() != null) {
				temp = complexAIFMRecordInfoType.getAIFMName();
				ReportUtilities.saveData(temp, "AIFMName", "19", null,
						reportExecution, user, update);
			}

			// (20) <AIFMEEAFlag>
			temp = Boolean.toString(complexAIFMRecordInfoType.isAIFMEEAFlag());
			ReportUtilities.saveData(temp, "AIFMEEAFlag", "20", null,
					reportExecution, user, update);

			// (21) <AIFMNoReportingFlag>
			temp = Boolean.toString(complexAIFMRecordInfoType
					.isAIFMNoReportingFlag());
			ReportUtilities.saveData(temp, "AIFMNoReportingFlag", "21", null,
					reportExecution, user, update);

			ComplexAIFMCompleteDescriptionType complexAIFMCompleteDescriptionType = complexAIFMRecordInfoType
					.getAIFMCompleteDescription();

			if (complexAIFMCompleteDescriptionType != null) {

				// /////////////////////////////////////////////////////////////////
				// <AIFMPrincipalMarkets>

				ComplexAIFMPrincipalMarketsType complexAIFMPrincipalMarketsType = complexAIFMCompleteDescriptionType
						.getAIFMPrincipalMarkets();

				if (complexAIFMPrincipalMarketsType != null) {
					List<ComplexFivePrincipalMarketType> complexFivePrincipalMarketTypeList = complexAIFMPrincipalMarketsType
							.getAIFMFivePrincipalMarket();

					count = 0;
					for (ComplexFivePrincipalMarketType complexFivePrincipalMarketType : complexFivePrincipalMarketTypeList) {

						count++;
						block = Integer.toString(count);

						// (26) <Ranking>
						if (complexFivePrincipalMarketType.getRanking() != null) {
							temp = complexFivePrincipalMarketType.getRanking()
									.toString();
							ReportUtilities.saveData(temp, "Ranking", "26",
									block, reportExecution, user, update);
						}

						ComplexMarketIdentificationWithNOTType complexMarketIdentificationWithNOTType = complexFivePrincipalMarketType
								.getMarketIdentification();

						logger.debug("** complexFivePrincipalMarketType: "
								+ " ranking "
								+ complexFivePrincipalMarketType.getRanking()
								+ " value "
								+ complexFivePrincipalMarketType
										.getAggregatedValueAmount()
								+ " market_code "
								+ complexMarketIdentificationWithNOTType
										.getMarketCode()
								+ " market_type "
								+ complexMarketIdentificationWithNOTType
										.getMarketCodeType() + " block "
								+ block);

						// (27) <MarketCodeType>
						if (complexMarketIdentificationWithNOTType
								.getMarketCodeType() != null) {
							temp = complexMarketIdentificationWithNOTType
									.getMarketCodeType().value();
							ReportUtilities.saveData(temp, "MarketCodeType",
									"27", block, reportExecution, user, update);
						}

						// (28) <MarketCode>
						if (complexMarketIdentificationWithNOTType
								.getMarketCode() != null) {
							temp = complexMarketIdentificationWithNOTType
									.getMarketCode();
							ReportUtilities.saveData(temp, "MarketCode", "28",
									block, reportExecution, user, update);
						}

						// (29) <AggregatedValueAmount>
						if (complexFivePrincipalMarketType
								.getAggregatedValueAmount() != null) {
							temp = complexFivePrincipalMarketType
									.getAggregatedValueAmount().toString();
							ReportUtilities.saveData(temp,
									"AggregatedValueAmount", "29", block,
									reportExecution, user, update);
						}

					}

				} // end complexAIFMPrincipalMarketsType

				// /////////////////////////////////////////////////////////////////
				// <AIFMPrincipalInstruments>

				ComplexAIFMPrincipalInstrumentsType complexAIFMPrincipalInstrumentsType = complexAIFMCompleteDescriptionType
						.getAIFMPrincipalInstruments();

				if (complexAIFMPrincipalInstrumentsType != null) {

					List<ComplexPrincipalInstrumentType> complexPrincipalInstrumentTypeList = complexAIFMPrincipalInstrumentsType
							.getAIFMPrincipalInstrument();

					count = 0;
					for (ComplexPrincipalInstrumentType complexPrincipalInstrumentType : complexPrincipalInstrumentTypeList) {

						count++;
						block = Integer.toString(count);

						// (30) <Ranking>
						if (complexPrincipalInstrumentType.getRanking() != null) {
							temp = complexPrincipalInstrumentType.getRanking()
									.toString();
							ReportUtilities.saveData(temp, "Ranking", "30",
									block, reportExecution, user, update);
						}

						// (31) <SubAssetType>
						if (complexPrincipalInstrumentType.getSubAssetType() != null) {
							temp = complexPrincipalInstrumentType
									.getSubAssetType().value();
							ReportUtilities.saveData(temp, "SubAssetType",
									"31", block, reportExecution, user, update);
						}

						// (32) <AggregatedValueAmount>
						if (complexPrincipalInstrumentType
								.getAggregatedValueAmount() != null) {
							temp = complexPrincipalInstrumentType
									.getAggregatedValueAmount().toString();
							ReportUtilities.saveData(temp,
									"AggregatedValueAmount", "32", block,
									reportExecution, user, update);
						}
					}

				} // end complexAIFMPrincipalInstrumentsType

				// /////////////////////////////////////////////////////////////////
				// <AIFMCompleteDescription>

				ComplexAIFMIdentifierType complexAIFMIdentifierType = complexAIFMCompleteDescriptionType
						.getAIFMIdentifier();

				if (complexAIFMIdentifierType != null) {

					// (22) <AIFMIdentifierLEI>
					if (complexAIFMIdentifierType.getAIFMIdentifierLEI() != null) {
						temp = complexAIFMIdentifierType.getAIFMIdentifierLEI();
						ReportUtilities.saveData(temp, "AIFMIdentifierLEI",
								"22", null, reportExecution, user, update);
					}

					// (23) <AIFMIdentifierBIC>
					if (complexAIFMIdentifierType.getAIFMIdentifierBIC() != null) {
						temp = complexAIFMIdentifierType.getAIFMIdentifierBIC();
						ReportUtilities.saveData(temp, "AIFMIdentifierBIC",
								"23", null, reportExecution, user, update);
					}

					ComplexAIFMNationalIdentifierType complexAIFMNationalIdentifierType = complexAIFMIdentifierType
							.getOldAIFMIdentifierNCA();

					if (complexAIFMNationalIdentifierType != null) {

						// (24) <Old_ReportingMemberState>
						if (complexAIFMNationalIdentifierType
								.getReportingMemberState() != null) {
							temp = complexAIFMNationalIdentifierType
									.getReportingMemberState();
							ReportUtilities.saveData(temp,
									"Old_ReportingMemberState", "24", null,
									reportExecution, user, update);
						}

						// (25) <Old_AIFMNationalCode>
						if (complexAIFMNationalIdentifierType
								.getAIFMNationalCode() != null) {
							temp = complexAIFMNationalIdentifierType
									.getAIFMNationalCode();
							ReportUtilities.saveData(temp,
									"Old_AIFMNationalCode", "25", null,
									reportExecution, user, update);
						}
					}

				} // end complexAIFMIdentifierType

				// (33) <AUMAmountInEuro>
				if (complexAIFMCompleteDescriptionType.getAUMAmountInEuro() != null) {
					temp = complexAIFMCompleteDescriptionType
							.getAUMAmountInEuro().toString();
					ReportUtilities.saveData(temp, "AUMAmountInEuro", "33",
							null, reportExecution, user, update);
				}

				ComplexBaseCurrencyDescriptionType complexBaseCurrencyDescriptionType = complexAIFMCompleteDescriptionType
						.getAIFMBaseCurrencyDescription();

				if (complexBaseCurrencyDescriptionType != null) {

					// (34) <AUMAmountInBaseCurrency>
					if (complexBaseCurrencyDescriptionType
							.getAUMAmountInBaseCurrency() != null) {
						temp = complexBaseCurrencyDescriptionType
								.getAUMAmountInBaseCurrency().toString();
						ReportUtilities.saveData(temp,
								"AUMAmountInBaseCurrency", "34", null,
								reportExecution, user, update);
					}

					// (35) <BaseCurrency>
					if (complexBaseCurrencyDescriptionType.getBaseCurrency() != null) {
						temp = complexBaseCurrencyDescriptionType
								.getBaseCurrency();
						ReportUtilities.saveData(temp, "BaseCurrency", "35",
								null, reportExecution, user, update);
					}

					// (36) <FXEURReferenceRateType>
					if (complexBaseCurrencyDescriptionType
							.getFXEURReferenceRateType() != null) {
						temp = complexBaseCurrencyDescriptionType
								.getFXEURReferenceRateType().value();
						ReportUtilities.saveData(temp,
								"FXEURReferenceRateType", "36", null,
								reportExecution, user, update);
					}

					// (37) <FXEURRate>
					if (complexBaseCurrencyDescriptionType.getFXEURRate() != null) {
						temp = complexBaseCurrencyDescriptionType
								.getFXEURRate().toString();
						ReportUtilities.saveData(temp, "FXEURRate", "37", null,
								reportExecution, user, update);
					}

					// (38) <FXEUROtherReferenceRateDescription>
					if (complexBaseCurrencyDescriptionType
							.getFXEUROtherReferenceRateDescription() != null) {
						temp = complexBaseCurrencyDescriptionType
								.getFXEUROtherReferenceRateDescription();
						ReportUtilities.saveData(temp,
								"FXEUROtherReferenceRateDescription", "38",
								null, reportExecution, user, update);
					}

				} // end complexBaseCurrencyDescriptionType

			} // end complexAIFMCompleteDescriptionType

		} // end complexAIFMRecordInfoType

		// /////////////////////////////////////////////////////////////////
		// <CancellationAIFMRecordInfo>

		if (complexCancellationAIFMRecordInfoType != null) {
			// (39) <CancelledAIFMNationalCode>
			if (complexCancellationAIFMRecordInfoType
					.getCancelledAIFMNationalCode() != null) {
				temp = complexCancellationAIFMRecordInfoType
						.getCancelledAIFMNationalCode();
				ReportUtilities.saveData(temp, "CancelledAIFMNationalCode",
						"39", null, reportExecution, user, update);
			}

			// (40) <CancelledReportingPeriodType>
			if (complexCancellationAIFMRecordInfoType
					.getCancelledReportingPeriodType() != null) {
				temp = complexCancellationAIFMRecordInfoType
						.getCancelledReportingPeriodType().value();
				ReportUtilities.saveData(temp, "CancelledReportingPeriodType",
						"40", null, reportExecution, user, update);
			}

			// (41) <CancelledReportingPeriodYear>
			if (complexCancellationAIFMRecordInfoType
					.getCancelledReportingPeriodYear() != null) {
				temp = new SimpleDateFormat(ReportUtilities.yearPattern)
						.format(XMLGregorianCalendarConverter
								.asDate(complexCancellationAIFMRecordInfoType
										.getCancelledReportingPeriodYear()));
				ReportUtilities.saveData(temp, "CancelledReportingPeriodYear",
						"41", null, reportExecution, user, update);
			}

			// (42) <CancelledRecordFlag>
			if (complexCancellationAIFMRecordInfoType.getCancelledRecordFlag() != null) {
				temp = complexCancellationAIFMRecordInfoType
						.getCancelledRecordFlag().value();
				ReportUtilities.saveData(temp, "CancelledRecordFlag", "42",
						null, reportExecution, user, update);
			}

		} // end complexCancellationAIFMRecordInfoType

		// /////////////////////////////////////////////////////////////////

		logger.debug("Datos finales de XML: ");
		for (ReportData reportData1 : reportDatas) {
			logger.debug(reportData1.getReportField().getReportFieldName()
					+ " (" + reportData1.getReportField().getReportFieldNum()
					+ ") " + reportData1.getReportDataText());
		}

		reportExecution.setReportDatas(reportDatas);

		return reportExecution;
	}
}
