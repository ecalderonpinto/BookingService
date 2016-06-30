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
import com.reportingtool.xml.AIFReportingInfo;
import com.reportingtool.xml.ComplexAIFCompleteDescriptionType;
import com.reportingtool.xml.ComplexAIFDescriptionType;
import com.reportingtool.xml.ComplexAIFIdentifierType;
import com.reportingtool.xml.ComplexAIFIndividualInfoType;
import com.reportingtool.xml.ComplexAIFLeverageArticle242Type;
import com.reportingtool.xml.ComplexAIFLeverageArticle244Type;
import com.reportingtool.xml.ComplexAIFLeverageInfoType;
import com.reportingtool.xml.ComplexAIFNationalIdentifierType;
import com.reportingtool.xml.ComplexAIFPrincipalInfoType;
import com.reportingtool.xml.ComplexAIFPrincipalMarketsType;
import com.reportingtool.xml.ComplexAIFRecordInfoType;
import com.reportingtool.xml.ComplexAIIInstrumentIdentificationType;
import com.reportingtool.xml.ComplexAUMGeographicalFocusType;
import com.reportingtool.xml.ComplexAllCounterpartyCollateralType;
import com.reportingtool.xml.ComplexAssetTypeExposureType;
import com.reportingtool.xml.ComplexAssetTypeExposuresType;
import com.reportingtool.xml.ComplexAssetTypeTurnoverType;
import com.reportingtool.xml.ComplexAssetTypeTurnoversType;
import com.reportingtool.xml.ComplexAssumptionType;
import com.reportingtool.xml.ComplexAssumptionsType;
import com.reportingtool.xml.ComplexBaseCurrencyDescriptionType;
import com.reportingtool.xml.ComplexBorrowingSourceType;
import com.reportingtool.xml.ComplexBucketRiskMeasureValuesType;
import com.reportingtool.xml.ComplexCCPExposureType;
import com.reportingtool.xml.ComplexCCPExposuresType;
import com.reportingtool.xml.ComplexCancellationAIFRecordInfoType;
import com.reportingtool.xml.ComplexClearedDerivativesRateType;
import com.reportingtool.xml.ComplexClearedReposRateType;
import com.reportingtool.xml.ComplexCompaniesDominantInfluenceType;
import com.reportingtool.xml.ComplexCompanyDominantInfluenceType;
import com.reportingtool.xml.ComplexControlledStructureType;
import com.reportingtool.xml.ComplexControlledStructuresType;
import com.reportingtool.xml.ComplexCounterpartyExposureType;
import com.reportingtool.xml.ComplexCounterpartyRiskProfileType;
import com.reportingtool.xml.ComplexCounterpartyToFundExposuresType;
import com.reportingtool.xml.ComplexCurrencyExposureType;
import com.reportingtool.xml.ComplexCurrencyExposuresType;
import com.reportingtool.xml.ComplexEntityIdentificationType;
import com.reportingtool.xml.ComplexFinancialInstrumentBorrowingType;
import com.reportingtool.xml.ComplexFinancingLiquidityProfileType;
import com.reportingtool.xml.ComplexFundOfFundsInvestmentStrategiesType;
import com.reportingtool.xml.ComplexFundOfFundsStrategyType;
import com.reportingtool.xml.ComplexFundToCounterpartyExposuresType;
import com.reportingtool.xml.ComplexHedgeFundInvestmentStrategiesType;
import com.reportingtool.xml.ComplexHedgeFundStrategyType;
import com.reportingtool.xml.ComplexHistoricalRiskProfileType;
import com.reportingtool.xml.ComplexIndividualExposureType;
import com.reportingtool.xml.ComplexInvestorArrangementType;
import com.reportingtool.xml.ComplexInvestorConcentrationType;
import com.reportingtool.xml.ComplexInvestorGroupType;
import com.reportingtool.xml.ComplexInvestorGroupsType;
import com.reportingtool.xml.ComplexInvestorIlliquidAssetArrangementType;
import com.reportingtool.xml.ComplexInvestorLiquidityProfileType;
import com.reportingtool.xml.ComplexInvestorPreferentialTreatmentType;
import com.reportingtool.xml.ComplexInvestorRedemptionType;
import com.reportingtool.xml.ComplexLeverageAIFType;
import com.reportingtool.xml.ComplexLiquidityRiskProfileType;
import com.reportingtool.xml.ComplexMainInstrumentTradedType;
import com.reportingtool.xml.ComplexMainInstrumentsTradedType;
import com.reportingtool.xml.ComplexMarketIdentificationWithNOTType;
import com.reportingtool.xml.ComplexMarketIdentificationWithoutNOTType;
import com.reportingtool.xml.ComplexMarketRate3P2Type;
import com.reportingtool.xml.ComplexMarketRiskMeasureType;
import com.reportingtool.xml.ComplexMarketRiskMeasuresType;
import com.reportingtool.xml.ComplexMarketRiskProfileType;
import com.reportingtool.xml.ComplexMasterAIFIdentificationType;
import com.reportingtool.xml.ComplexMasterAIFsIdentificationType;
import com.reportingtool.xml.ComplexMostImportantConcentrationType;
import com.reportingtool.xml.ComplexNAVGeographicalFocusType;
import com.reportingtool.xml.ComplexOperationalRiskType;
import com.reportingtool.xml.ComplexOtherArrangementType;
import com.reportingtool.xml.ComplexOtherFundInvestmentStrategiesType;
import com.reportingtool.xml.ComplexOtherFundStrategyType;
import com.reportingtool.xml.ComplexPortfolioConcentrationType;
import com.reportingtool.xml.ComplexPortfolioConcentrationsType;
import com.reportingtool.xml.ComplexPortfolioLiquidityProfileType;
import com.reportingtool.xml.ComplexPrimeBrokersType;
import com.reportingtool.xml.ComplexPrincipalExposureType;
import com.reportingtool.xml.ComplexPrincipalExposuresType;
import com.reportingtool.xml.ComplexPrivateEquityFundInvestmentStrategiesType;
import com.reportingtool.xml.ComplexPrivateEquityFundStrategyType;
import com.reportingtool.xml.ComplexQuantityMonthPeriodType;
import com.reportingtool.xml.ComplexRealEstateFundInvestmentStrategiesType;
import com.reportingtool.xml.ComplexRealEstateFundStrategyType;
import com.reportingtool.xml.ComplexRiskProfileType;
import com.reportingtool.xml.ComplexSecuritiesCashBorrowingType;
import com.reportingtool.xml.ComplexShareClassIdentificationType;
import com.reportingtool.xml.ComplexShareClassIdentifierType;
import com.reportingtool.xml.ComplexSignedRateMonthPeriodType;
import com.reportingtool.xml.ComplexStressTestsType;
import com.reportingtool.xml.ComplexThreePrincipalMarketType;
import com.reportingtool.xml.ComplexTradingClearingMechanismType;
import com.reportingtool.xml.ComplexVARRiskMeasureValuesType;
import com.reportingtool.xml.ComplexVegaRiskMeasureValuesType;

/**
 * Class to load a XML file and convert to reportExecution type AIF
 * 
 * @author alberto.olivan
 * 
 */
public class LoadXMLAIF {

	private ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(LoadXMLAIF.class);

	public LoadXMLAIF(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	// http://java.dzone.com/articles/using-jaxb-generate-java

	/**
	 * Function receive a File and AIF reportExecution, read this File to add
	 * reportDatas from XML file
	 * 
	 * @param file
	 * @param reportExecution
	 * @param update
	 *            : true -> update or insert / false -> only insert
	 * @return reportExecution with reportDatas from file
	 */
	public ReportExecution loadAIFFile(File file,
			ReportExecution reportExecution, boolean update) throws Exception {

		logger.info("LoadXMLAIF: starting loading " + file.getAbsolutePath());

		// 1. We need to create JAXContext instance
		JAXBContext jaxbContext = JAXBContext
				.newInstance(AIFReportingInfo.class);

		// 2. Use JAXBContext instance to create the Unmarshaller.
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		// 3. Use the Unmarshaller to unmarshal the XML document to get an
		// instance of JAXBElement.
		JAXBElement<AIFReportingInfo> root = (JAXBElement<AIFReportingInfo>) unmarshaller
				.unmarshal(new StreamSource(file), AIFReportingInfo.class);

		// 4. Get the instance of the required JAXB Root Class from the
		AIFReportingInfo aifReportingInfo = root.getValue();

		List<Object> listAIFReporting = aifReportingInfo
				.getCancellationAIFRecordInfoOrAIFRecordInfo();

		ComplexAIFRecordInfoType complexAIFRecordInfoType = null;
		ComplexCancellationAIFRecordInfoType complexCancellationAIFRecordInfoType = null;

		for (Object object : listAIFReporting) {
			if (object instanceof ComplexAIFRecordInfoType) {
				complexAIFRecordInfoType = (ComplexAIFRecordInfoType) object;
			}
			if (object instanceof ComplexCancellationAIFRecordInfoType) {
				complexCancellationAIFRecordInfoType = (ComplexCancellationAIFRecordInfoType) object;
			}

		}

		// Process inverse of GenerateXML, the class aifmReportingInfo contains
		// all objects inside and we unmarshal creating reportDatas, searching
		// of every reportFields and add to reportExecution if not exists

		VersionAuditor user = new VersionAuditor("loader");

		ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
				.getBean("reportExecutionDAO");

		reportExecution = reportExecutionDAO.findById(reportExecution.getId());

		// if true, this load try to update or insert info, if false only insert
		// update = false;

		String temp = null;
		String block = null;
		int count = 0;

		// /////////////////////////////////////////////////////////////////
		// <AifReportingInfo>

		// (1) <ReportingMemberState>
		if (aifReportingInfo.getReportingMemberState() != null) {
			temp = aifReportingInfo.getReportingMemberState();
			ReportUtilities.saveData(temp, "ReportingMemberState", "1", null,
					reportExecution, user, update);
		}

		// (2) <Version>
		if (aifReportingInfo.getVersion() != null) {
			temp = aifReportingInfo.getVersion();
			ReportUtilities.saveData(temp, "Version", "2", null,
					reportExecution, user, update);
		}

		// (3) <CreationDateAndTime>
		if (aifReportingInfo.getCreationDateAndTime() != null) {
			temp = new SimpleDateFormat(ReportUtilities.dateTimePattern)
					.format(XMLGregorianCalendarConverter
							.asDate(aifReportingInfo.getCreationDateAndTime()));
			ReportUtilities.saveData(temp, "CreationDateAndTime", "3", null,
					reportExecution, user, update);
		}

		if (complexAIFRecordInfoType != null) {

			// /////////////////////////////////////////////////////////////////
			// <AIFRecordInfo>

			// (4) <FilingType>
			if (complexAIFRecordInfoType.getFilingType() != null) {
				temp = complexAIFRecordInfoType.getFilingType().value();
				ReportUtilities.saveData(temp, "FilingType", "4", null,
						reportExecution, user, update);
			}

			// (5) <AIFContentType>
			if (complexAIFRecordInfoType.getAIFContentType() != null) {
				temp = complexAIFRecordInfoType.getAIFContentType();
				ReportUtilities.saveData(temp, "AIFContentType", "5", null,
						reportExecution, user, update);
			}

			// (6) <ReportingPeriodStartDate>
			if (complexAIFRecordInfoType.getReportingPeriodStartDate() != null) {
				temp = new SimpleDateFormat(ReportUtilities.datePattern)
						.format(XMLGregorianCalendarConverter
								.asDate(complexAIFRecordInfoType
										.getReportingPeriodStartDate()));
				ReportUtilities.saveData(temp, "ReportingPeriodStartDate", "6",
						null, reportExecution, user, update);
			}

			// (7) <ReportingPeriodEndDate>
			if (complexAIFRecordInfoType.getReportingPeriodEndDate() != null) {
				temp = new SimpleDateFormat(ReportUtilities.datePattern)
						.format(XMLGregorianCalendarConverter
								.asDate(complexAIFRecordInfoType
										.getReportingPeriodEndDate()));
				ReportUtilities.saveData(temp, "ReportingPeriodEndDate", "7",
						null, reportExecution, user, update);
			}

			// (8) <ReportingPeriodType>
			if (complexAIFRecordInfoType.getReportingPeriodType() != null) {
				temp = complexAIFRecordInfoType.getReportingPeriodType()
						.value();
				ReportUtilities.saveData(temp, "ReportingPeriodType", "8",
						null, reportExecution, user, update);
			}

			// (9) <ReportingPeriodYear>
			if (complexAIFRecordInfoType.getReportingPeriodYear() != null) {
				temp = new SimpleDateFormat(ReportUtilities.yearPattern)
						.format(XMLGregorianCalendarConverter
								.asDate(complexAIFRecordInfoType
										.getReportingPeriodYear()));
				ReportUtilities.saveData(temp, "ReportingPeriodYear", "9",
						null, reportExecution, user, update);
			}

			// (10) <AIFReportingObligationChangeFrequencyCode>
			if (complexAIFRecordInfoType
					.getAIFReportingObligationChangeFrequencyCode() != null) {
				temp = complexAIFRecordInfoType
						.getAIFReportingObligationChangeFrequencyCode().value();
				ReportUtilities.saveData(temp,
						"AIFReportingObligationChangeFrequencyCode", "10",
						null, reportExecution, user, update);
			}

			// (11) <AIFReportingObligationChangeContentsCode>
			if (complexAIFRecordInfoType
					.getAIFReportingObligationChangeContentsCode() != null) {
				temp = complexAIFRecordInfoType
						.getAIFReportingObligationChangeContentsCode()
						.toString();
				ReportUtilities.saveData(temp,
						"AIFReportingObligationChangeContentsCode", "11", null,
						reportExecution, user, update);
			}

			// (12) <AIFReportingObligationChangeQuarter>
			if (complexAIFRecordInfoType
					.getAIFReportingObligationChangeQuarter() != null) {
				temp = complexAIFRecordInfoType
						.getAIFReportingObligationChangeQuarter().value();
				ReportUtilities.saveData(temp,
						"AIFReportingObligationChangeQuarter", "12", null,
						reportExecution, user, update);
			}

			// (13) <LastReportingFlag>
			temp = Boolean.toString(complexAIFRecordInfoType
					.isLastReportingFlag());
			ReportUtilities.saveData(temp, "LastReportingFlag", "13", null,
					reportExecution, user, update);

			// /////////////////////////////////////////////////////////////////
			// <Assumptions>

			ComplexAssumptionsType complexAssumptionsType = complexAIFRecordInfoType
					.getAssumptions();

			if (complexAssumptionsType != null) {

				List<ComplexAssumptionType> complexAssumptionTypeList = complexAssumptionsType
						.getAssumption();

				count = 0;
				// <Assumption>
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

				// < / Assumptions>
			} // end complexAssumptionsType

			// /////////////////////////////////////////////////////////////////

			// (16) <AIFMNationalCode>
			if (complexAIFRecordInfoType.getAIFMNationalCode() != null) {
				temp = complexAIFRecordInfoType.getAIFMNationalCode()
						.toString();
				ReportUtilities.saveData(temp, "AIFMNationalCode", "16", null,
						reportExecution, user, update);
			}

			// (17) <AIFNationalCode>
			if (complexAIFRecordInfoType.getAIFNationalCode() != null) {
				temp = complexAIFRecordInfoType.getAIFNationalCode().toString();
				ReportUtilities.saveData(temp, "AIFNationalCode", "17", null,
						reportExecution, user, update);
			}

			// (18) <AIFName>
			if (complexAIFRecordInfoType.getAIFName() != null) {
				temp = complexAIFRecordInfoType.getAIFName().toString();
				ReportUtilities.saveData(temp, "AIFName", "18", null,
						reportExecution, user, update);
			}

			// (19) <AIFEEAFlag>
			temp = Boolean.toString(complexAIFRecordInfoType.isAIFEEAFlag());
			ReportUtilities.saveData(temp, "AIFEEAFlag", "19", null,
					reportExecution, user, update);

			// (20) <AIFReportingCode>
			if (complexAIFRecordInfoType.getAIFReportingCode() != null) {
				temp = complexAIFRecordInfoType.getAIFReportingCode()
						.toString();
				ReportUtilities.saveData(temp, "AIFReportingCode", "20", null,
						reportExecution, user, update);
			}

			// (21) <AIFDomicile>
			if (complexAIFRecordInfoType.getAIFDomicile() != null) {
				temp = complexAIFRecordInfoType.getAIFDomicile().toString();
				ReportUtilities.saveData(temp, "AIFDomicile", "21", null,
						reportExecution, user, update);
			}

			// (22) <InceptionDate>
			if (complexAIFRecordInfoType.getInceptionDate() != null) {
				temp = new SimpleDateFormat(ReportUtilities.datePattern)
						.format(XMLGregorianCalendarConverter
								.asDate(complexAIFRecordInfoType
										.getInceptionDate()));
				ReportUtilities.saveData(temp, "InceptionDate", "22", null,
						reportExecution, user, update);
			}

			// (23) <AIFNoReportingFlag>
			temp = Boolean.toString(complexAIFRecordInfoType
					.isAIFNoReportingFlag());
			ReportUtilities.saveData(temp, "AIFNoReportingFlag", "23", null,
					reportExecution, user, update);

			// /////////////////////////////////////////////////////////////////
			// <AIFCompleteDescription>
			ComplexAIFCompleteDescriptionType complexAIFCompleteDescriptionType = complexAIFRecordInfoType
					.getAIFCompleteDescription();
			if (complexAIFCompleteDescriptionType != null) {
				ComplexAIFPrincipalInfoType complexAIFPrincipalInfoType = complexAIFCompleteDescriptionType
						.getAIFPrincipalInfo();

				// <AIFPrincipalInfo>
				if (complexAIFPrincipalInfoType != null) {

					// <AIFIdentification>
					ComplexAIFIdentifierType complexAIFIdentifierType = complexAIFPrincipalInfoType
							.getAIFIdentification();
					if (complexAIFIdentifierType != null) {

						// (24) <AIFIdentifierLEI>
						if (complexAIFIdentifierType.getAIFIdentifierLEI() != null) {
							temp = complexAIFIdentifierType
									.getAIFIdentifierLEI().toString();
							ReportUtilities.saveData(temp, "AIFIdentifierLEI",
									"24", null, reportExecution, user, update);
						}

						// (25) <AIFIdentifierISIN>
						if (complexAIFIdentifierType.getAIFIdentifierISIN() != null) {
							temp = complexAIFIdentifierType
									.getAIFIdentifierISIN().toString();
							ReportUtilities.saveData(temp, "AIFIdentifierISIN",
									"25", null, reportExecution, user, update);
						}

						// (26) <AIFIdentifierCUSIP>
						if (complexAIFIdentifierType.getAIFIdentifierCUSIP() != null) {
							temp = complexAIFIdentifierType
									.getAIFIdentifierCUSIP().toString();
							ReportUtilities.saveData(temp,
									"AIFIdentifierCUSIP", "26", null,
									reportExecution, user, update);
						}

						// (27) <AIFIdentifierSEDOL>
						if (complexAIFIdentifierType.getAIFIdentifierSEDOL() != null) {
							temp = complexAIFIdentifierType
									.getAIFIdentifierSEDOL().toString();
							ReportUtilities.saveData(temp,
									"AIFIdentifierSEDOL", "27", null,
									reportExecution, user, update);
						}

						// (28) <AIFIdentifierTicker>
						if (complexAIFIdentifierType.getAIFIdentifierTicker() != null) {
							temp = complexAIFIdentifierType
									.getAIFIdentifierTicker().toString();
							ReportUtilities.saveData(temp,
									"AIFIdentifierTicker", "28", null,
									reportExecution, user, update);
						}

						// (29) <AIFIdentifierRIC>
						if (complexAIFIdentifierType.getAIFIdentifierRIC() != null) {
							temp = complexAIFIdentifierType
									.getAIFIdentifierRIC().toString();
							ReportUtilities.saveData(temp, "AIFIdentifierRIC",
									"29", null, reportExecution, user, update);
						}

						// (30) <AIFIdentifierECB>
						if (complexAIFIdentifierType.getAIFIdentifierECB() != null) {
							temp = complexAIFIdentifierType
									.getAIFIdentifierRIC().toString();
							ReportUtilities.saveData(temp, "AIFIdentifierECB",
									"30", null, reportExecution, user, update);
						}

						// <AIFIdentifierNCA>
						ComplexAIFNationalIdentifierType complexAIFNationalIdentifierType = complexAIFIdentifierType
								.getOldAIFIdentifierNCA();
						if (complexAIFNationalIdentifierType != null) {

							// (31) <Old_ReportingMemberState>
							if (complexAIFNationalIdentifierType
									.getReportingMemberState() != null) {
								temp = complexAIFNationalIdentifierType
										.getReportingMemberState().toString();
								ReportUtilities.saveData(temp,
										"Old_ReportingMemberState", "31", null,
										reportExecution, user, update);
							}

							// (32) <Old_AIFNationalCode>
							if (complexAIFNationalIdentifierType
									.getAIFNationalCode() != null) {
								temp = complexAIFNationalIdentifierType
										.getAIFNationalCode().toString();
								ReportUtilities.saveData(temp,
										"Old_AIFNationalCode", "32", null,
										reportExecution, user, update);
							}

						} // end complexAIFNationalIdentifierType

					} // end complexAIFIdentifierType

					// /////////////////////////////////////////////////////////////////
					// <ShareClassIdentification>
					ComplexShareClassIdentificationType complexShareClassIdentificationType = complexAIFPrincipalInfoType
							.getShareClassIdentification();

					// (33) <ShareClassFlag>
					temp = Boolean.toString(complexAIFPrincipalInfoType
							.isShareClassFlag());
					ReportUtilities.saveData(temp, "ShareClassFlag", "33",
							null, reportExecution, user, update);

					if (complexShareClassIdentificationType != null) {

						List<ComplexShareClassIdentifierType> complexShareClassIdentifierTypeList = complexShareClassIdentificationType
								.getShareClassIdentifier();

						count = 0;
						// <ShareClassIdentifier>
						for (ComplexShareClassIdentifierType complexShareClassIdentifierType : complexShareClassIdentifierTypeList) {
							count++;
							block = Integer.toString(count);

							// (34) <ShareClassNationalCode>
							if (complexShareClassIdentifierType
									.getShareClassNationalCode() != null) {
								temp = complexShareClassIdentifierType
										.getShareClassNationalCode().toString();
								ReportUtilities.saveData(temp,
										"ShareClassNationalCode", "34", block,
										reportExecution, user, update);
							}

							// (35) <ShareClassIdentifierISIN>
							if (complexShareClassIdentifierType
									.getShareClassIdentifierISIN() != null) {
								temp = complexShareClassIdentifierType
										.getShareClassIdentifierISIN()
										.toString();
								ReportUtilities.saveData(temp,
										"ShareClassIdentifierISIN", "35",
										block, reportExecution, user, update);
							}

							// (36) <ShareClassIdentifierSEDOL>
							if (complexShareClassIdentifierType
									.getShareClassIdentifierSEDOL() != null) {
								temp = complexShareClassIdentifierType
										.getShareClassIdentifierSEDOL()
										.toString();
								ReportUtilities.saveData(temp,
										"ShareClassIdentifierSEDOL", "36",
										block, reportExecution, user, update);
							}

							// (37) <ShareClassIdentifierCUSIP>
							if (complexShareClassIdentifierType
									.getShareClassIdentifierCUSIP() != null) {
								temp = complexShareClassIdentifierType
										.getShareClassIdentifierCUSIP()
										.toString();
								ReportUtilities.saveData(temp,
										"ShareClassIdentifierCUSIP", "37",
										block, reportExecution, user, update);
							}

							// (38) <ShareClassIdentifierTicker>
							if (complexShareClassIdentifierType
									.getShareClassIdentifierTicker() != null) {
								temp = complexShareClassIdentifierType
										.getShareClassIdentifierTicker()
										.toString();
								ReportUtilities.saveData(temp,
										"ShareClassIdentifierTicker", "38",
										block, reportExecution, user, update);
							}

							// (39) <ShareClassIdentifierRIC>
							if (complexShareClassIdentifierType
									.getShareClassIdentifierRIC() != null) {
								temp = complexShareClassIdentifierType
										.getShareClassIdentifierRIC()
										.toString();
								ReportUtilities.saveData(temp,
										"ShareClassIdentifierRIC", "39", block,
										reportExecution, user, update);
							}

							// (40) <ShareClassName>
							if (complexShareClassIdentifierType
									.getShareClassName() != null) {
								temp = complexShareClassIdentifierType
										.getShareClassName().toString();
								ReportUtilities.saveData(temp,
										"ShareClassName", "40", block,
										reportExecution, user, update);
							}

						} // < / ShareClassIdentifier>

					} // end complexShareClassIdentificationType

					// /////////////////////////////////////////////////////////////////
					// <AIFDescription>
					ComplexAIFDescriptionType complexAIFDescriptionType = complexAIFPrincipalInfoType
							.getAIFDescription();
					if (complexAIFDescriptionType != null) {

						// (41) <AIFMasterFeederStatus>
						if (complexAIFDescriptionType
								.getAIFMasterFeederStatus() != null) {
							temp = complexAIFDescriptionType
									.getAIFMasterFeederStatus().toString();
							ReportUtilities.saveData(temp,
									"AIFMasterFeederStatus", "41", null,
									reportExecution, user, update);
						}

						// <MasterAIFsIdentification>
						ComplexMasterAIFsIdentificationType complexMasterAIFsIdentificationType = complexAIFDescriptionType
								.getMasterAIFsIdentification();
						if (complexMasterAIFsIdentificationType != null) {

							List<ComplexMasterAIFIdentificationType> complexMasterAIFIdentificationTypeList = complexMasterAIFsIdentificationType
									.getMasterAIFIdentification();

							count = 0;
							// <MasterAIFIdentification>
							for (ComplexMasterAIFIdentificationType complexMasterAIFIdentificationType : complexMasterAIFIdentificationTypeList) {
								count++;
								block = Integer.toString(count);

								// (42) <AIFName>
								if (complexMasterAIFIdentificationType
										.getAIFName() != null) {
									temp = complexMasterAIFIdentificationType
											.getAIFName().toString();
									ReportUtilities.saveData(temp, "AIFName",
											"42", block, reportExecution, user,
											update);
								}

								// <AIFIdentifierNCA>
								ComplexAIFNationalIdentifierType aifIdentifierNCA = complexMasterAIFIdentificationType
										.getAIFIdentifierNCA();

								if (aifIdentifierNCA != null) {

									// (43) <ReportingMemberState>
									if (aifIdentifierNCA
											.getReportingMemberState() != null) {
										temp = aifIdentifierNCA
												.getReportingMemberState();
										ReportUtilities.saveData(temp,
												"ReportingMemberState", "43",
												block, reportExecution, user,
												update);
									}

									// (44) <AIFNationalCode>
									if (aifIdentifierNCA.getAIFNationalCode() != null) {
										temp = aifIdentifierNCA
												.getAIFNationalCode();
										ReportUtilities.saveData(temp,
												"AIFNationalCode", "44", block,
												reportExecution, user, update);
									}

								} // < / AIFIdentifierNCA>

							} // < / MasterAIFIdentification>

						} // end complexMasterAIFsIdentificationType

					} // end complexAIFDescriptionType

					// < / MasterAIFsIdentification>

					// /////////////////////////////////////////////////////////////////
					// <PrimeBrokers>
					ComplexPrimeBrokersType complexPrimeBrokersType = complexAIFDescriptionType
							.getPrimeBrokers();
					if (complexPrimeBrokersType != null) {

						List<ComplexEntityIdentificationType> complexEntityIdentificationTypeList = complexPrimeBrokersType
								.getPrimeBrokerIdentification();

						count = 0;
						// <PrimeBrokerIdentification>
						for (ComplexEntityIdentificationType complexEntityIdentificationType : complexEntityIdentificationTypeList) {
							count++;
							block = Integer.toString(count);

							// (45) <EntityName>
							if (complexEntityIdentificationType.getEntityName() != null) {
								temp = complexEntityIdentificationType
										.getEntityName().toString();
								ReportUtilities.saveData(temp, "EntityName",
										"45", block, reportExecution, user,
										update);
							}

							// (46) <EntityIdentificationLEI>
							if (complexEntityIdentificationType
									.getEntityIdentificationLEI() != null) {
								temp = complexEntityIdentificationType
										.getEntityIdentificationLEI()
										.toString();
								ReportUtilities.saveData(temp,
										"EntityIdentificationLEI", "46", block,
										reportExecution, user, update);
							}

							// (47) <EntityIdentificationBIC>
							if (complexEntityIdentificationType
									.getEntityIdentificationBIC() != null) {
								temp = complexEntityIdentificationType
										.getEntityIdentificationBIC()
										.toString();
								ReportUtilities.saveData(temp,
										"EntityIdentificationBIC", "47", block,
										reportExecution, user, update);
							}

						} // < / PrimeBrokerIdentification>

					} // end complexPrimeBrokersType

					// < / PrimeBrokers>

					// /////////////////////////////////////////////////////////////////
					// <AIFBaseCurrencyDescription>
					ComplexBaseCurrencyDescriptionType complexBaseCurrencyDescriptionType = complexAIFDescriptionType
							.getAIFBaseCurrencyDescription();
					if (complexBaseCurrencyDescriptionType != null) {

						// (48) <AUMAmountInBaseCurrency>
						if (complexBaseCurrencyDescriptionType
								.getAUMAmountInBaseCurrency() != null) {
							temp = complexBaseCurrencyDescriptionType
									.getAUMAmountInBaseCurrency().toString();
							ReportUtilities.saveData(temp,
									"AUMAmountInBaseCurrency", "48", null,
									reportExecution, user, update);
						}

						// (49) <BaseCurrency>
						if (complexBaseCurrencyDescriptionType
								.getBaseCurrency() != null) {
							temp = complexBaseCurrencyDescriptionType
									.getBaseCurrency().toString();
							ReportUtilities.saveData(temp, "BaseCurrency",
									"49", null, reportExecution, user, update);
						}

						// (50) <FXEURRate>
						if (complexBaseCurrencyDescriptionType.getFXEURRate() != null) {
							temp = complexBaseCurrencyDescriptionType
									.getFXEURRate().toString();
							ReportUtilities.saveData(temp, "FXEURRate", "50",
									null, reportExecution, user, update);
						}

						// (51) <FXEURReferenceRateType>
						if (complexBaseCurrencyDescriptionType
								.getFXEURReferenceRateType() != null) {
							temp = complexBaseCurrencyDescriptionType
									.getFXEURReferenceRateType().value();
							ReportUtilities.saveData(temp,
									"FXEURReferenceRateType", "51", null,
									reportExecution, user, update);
						}

						// (52) <FXEUROtherReferenceRateDescription>
						if (complexBaseCurrencyDescriptionType
								.getFXEUROtherReferenceRateDescription() != null) {
							temp = complexBaseCurrencyDescriptionType
									.getFXEUROtherReferenceRateDescription()
									.toString();
							ReportUtilities.saveData(temp,
									"FXEUROtherReferenceRateDescription", "52",
									null, reportExecution, user, update);
						}

						// (53) <AIFNetAssetValue>
						temp = Long.toString(complexAIFDescriptionType
								.getAIFNetAssetValue());
						ReportUtilities.saveData(temp, "AIFNetAssetValue",
								"53", null, reportExecution, user, update);

						// < / AIFBaseCurrencyDescription>

						// (54) <FirstFundingSourceCountry>
						if (complexAIFDescriptionType
								.getFirstFundingSourceCountry() != null) {
							temp = complexAIFDescriptionType
									.getFirstFundingSourceCountry().toString();
							ReportUtilities.saveData(temp,
									"FirstFundingSourceCountry", "54", null,
									reportExecution, user, update);
						}

						// (55) <SecondFundingSourceCountry>
						if (complexAIFDescriptionType
								.getSecondFundingSourceCountry() != null) {
							temp = complexAIFDescriptionType
									.getSecondFundingSourceCountry().toString();
							ReportUtilities.saveData(temp,
									"SecondFundingSourceCountry", "55", null,
									reportExecution, user, update);
						}

						// (56) <ThirdFundingSourceCountry>
						if (complexAIFDescriptionType
								.getThirdFundingSourceCountry() != null) {
							temp = complexAIFDescriptionType
									.getThirdFundingSourceCountry().toString();
							ReportUtilities.saveData(temp,
									"ThirdFundingSourceCountry", "56", null,
									reportExecution, user, update);
						}

						// (57) <PredominantAIFType>
						if (complexAIFDescriptionType.getPredominantAIFType() != null) {
							temp = complexAIFDescriptionType
									.getPredominantAIFType().value();
							ReportUtilities.saveData(temp,
									"PredominantAIFType", "57", null,
									reportExecution, user, update);
						}

					} // end complexBaseCurrencyDescriptionType

					// /////////////////////////////////////////////////////////////////
					// <HedgeFundInvestmentStrategies>
					ComplexHedgeFundInvestmentStrategiesType complexHedgeFundInvestmentStrategiesType = complexAIFDescriptionType
							.getHedgeFundInvestmentStrategies();
					if (complexHedgeFundInvestmentStrategiesType != null) {

						List<ComplexHedgeFundStrategyType> complexHedgeFundStrategyTypeList = complexHedgeFundInvestmentStrategiesType
								.getHedgeFundStrategy();

						count = 0;
						// <HedgeFundStrategy>
						for (ComplexHedgeFundStrategyType complexHedgeFundStrategyType : complexHedgeFundStrategyTypeList) {
							count++;
							block = Integer.toString(count);

							// (58) <HedgeFundStrategyType>
							if (complexHedgeFundStrategyType
									.getHedgeFundStrategyType() != null) {
								temp = complexHedgeFundStrategyType
										.getHedgeFundStrategyType().value();
								ReportUtilities.saveData(temp,
										"HedgeFundStrategyType", "58", block,
										reportExecution, user, update);
							}

							// (59) <H_PrimaryStrategyFlag>
							temp = Boolean
									.toString(complexHedgeFundStrategyType
											.isPrimaryStrategyFlag());
							ReportUtilities.saveData(temp,
									"H_PrimaryStrategyFlag", "59", block,
									reportExecution, user, update);

							// (60) <H_StrategyNAVRate>
							if (complexHedgeFundStrategyType
									.getStrategyNAVRate() != null) {
								temp = complexHedgeFundStrategyType
										.getStrategyNAVRate().toString();
								ReportUtilities.saveData(temp,
										"H_StrategyNAVRate", "60", block,
										reportExecution, user, update);
							}

							// (61) <H_StrategyTypeOtherDescription>
							if (complexHedgeFundStrategyType
									.getStrategyTypeOtherDescription() != null) {
								temp = complexHedgeFundStrategyType
										.getStrategyTypeOtherDescription()
										.toString();
								ReportUtilities.saveData(temp,
										"H_StrategyTypeOtherDescription", "61",
										block, reportExecution, user, update);
							}

						} // < / HedgeFundStrategy>

					} // end complexHedgeFundInvestmentStrategiesType

					// < / HedgeFundInvestmentStrategies>

					// /////////////////////////////////////////////////////////////////
					// <FundOfFundsInvestmentStrategies>
					ComplexFundOfFundsInvestmentStrategiesType complexFundOfFundsInvestmentStrategiesType = complexAIFDescriptionType
							.getFundOfFundsInvestmentStrategies();
					if (complexFundOfFundsInvestmentStrategiesType != null) {

						List<ComplexFundOfFundsStrategyType> complexFundOfFundsStrategyTypeList = complexFundOfFundsInvestmentStrategiesType
								.getFundOfFundsStrategy();

						count = 0;
						// <FundOfFundsStrategy>
						for (ComplexFundOfFundsStrategyType complexFundOfFundsStrategyType : complexFundOfFundsStrategyTypeList) {
							count++;
							block = Integer.toString(count);

							// (58) <FundOfFundsStrategyType>
							if (complexFundOfFundsStrategyType
									.getFundOfFundsStrategyType() != null) {
								temp = complexFundOfFundsStrategyType
										.getFundOfFundsStrategyType().value();
								ReportUtilities.saveData(temp,
										"FundOfFundsStrategyType", "58", block,
										reportExecution, user, update);
							}

							// (59) <F_PrimaryStrategyFlag>
							temp = Boolean
									.toString(complexFundOfFundsStrategyType
											.isPrimaryStrategyFlag());
							ReportUtilities.saveData(temp,
									"F_PrimaryStrategyFlag", "59", block,
									reportExecution, user, update);

							// (60) <F_StrategyNAVRate>
							if (complexFundOfFundsStrategyType
									.getStrategyNAVRate() != null) {
								temp = complexFundOfFundsStrategyType
										.getStrategyNAVRate().toString();
								ReportUtilities.saveData(temp,
										"F_StrategyNAVRate", "60", block,
										reportExecution, user, update);
							}

							// (61) <F_StrategyTypeOtherDescription>
							if (complexFundOfFundsStrategyType
									.getStrategyTypeOtherDescription() != null) {
								temp = complexFundOfFundsStrategyType
										.getStrategyTypeOtherDescription()
										.toString();
								ReportUtilities.saveData(temp,
										"F_StrategyTypeOtherDescription", "61",
										block, reportExecution, user, update);
							}

						} // < / FundOfFundsStrategy>

					} // end complexFundOfFundsInvestmentStrategiesType

					// < / FundOfFundsInvestmentStrategies>

					// /////////////////////////////////////////////////////////////////
					// <OtherFundInvestmentStrategies>
					ComplexOtherFundInvestmentStrategiesType complexOtherFundInvestmentStrategiesType = complexAIFDescriptionType
							.getOtherFundInvestmentStrategies();
					if (complexOtherFundInvestmentStrategiesType != null) {

						List<ComplexOtherFundStrategyType> complexOtherFundStrategyTypeList = complexOtherFundInvestmentStrategiesType
								.getOtherFundStrategy();

						count = 0;
						// <OtherFundStrategy>
						for (ComplexOtherFundStrategyType complexOtherFundStrategyType : complexOtherFundStrategyTypeList) {
							count++;
							block = Integer.toString(count);

							// (58) <OtherFundStrategyType>
							if (complexOtherFundStrategyType
									.getOtherFundStrategyType() != null) {
								temp = complexOtherFundStrategyType
										.getOtherFundStrategyType().value();
								ReportUtilities.saveData(temp,
										"OtherFundStrategyType", "58", block,
										reportExecution, user, update);
							}

							// (59) <O_PrimaryStrategyFlag>
							temp = Boolean
									.toString(complexOtherFundStrategyType
											.isPrimaryStrategyFlag());
							ReportUtilities.saveData(temp,
									"O_PrimaryStrategyFlag", "59", block,
									reportExecution, user, update);

							// (60) <O_StrategyNAVRate>
							if (complexOtherFundStrategyType
									.getStrategyNAVRate() != null) {
								temp = complexOtherFundStrategyType
										.getStrategyNAVRate().toString();
								ReportUtilities.saveData(temp,
										"O_StrategyNAVRate", "60", block,
										reportExecution, user, update);
							}

							// (61) <O_StrategyTypeOtherDescription>
							if (complexOtherFundStrategyType
									.getStrategyTypeOtherDescription() != null) {
								temp = complexOtherFundStrategyType
										.getStrategyTypeOtherDescription()
										.toString();
								ReportUtilities.saveData(temp,
										"O_StrategyTypeOtherDescription", "61",
										block, reportExecution, user, update);
							}

						} // < / OtherFundStrategy>

					} // end complexOtherFundStrategyType

					// < / OtherFundInvestmentStrategies>

					// /////////////////////////////////////////////////////////////////
					// <PrivateEquityFundInvestmentStrategies>
					ComplexPrivateEquityFundInvestmentStrategiesType complexPrivateEquityFundInvestmentStrategiesType = complexAIFDescriptionType
							.getPrivateEquityFundInvestmentStrategies();
					if (complexPrivateEquityFundInvestmentStrategiesType != null) {

						List<ComplexPrivateEquityFundStrategyType> complexPrivateEquityFundStrategyTypeList = complexPrivateEquityFundInvestmentStrategiesType
								.getPrivateEquityFundInvestmentStrategy();

						count = 0;
						// <PrivateEquityFundInvestmentStrategy>
						for (ComplexPrivateEquityFundStrategyType complexPrivateEquityFundStrategyType : complexPrivateEquityFundStrategyTypeList) {
							count++;
							block = Integer.toString(count);

							// (58) <PrivateEquityFundStrategyType>
							if (complexPrivateEquityFundStrategyType
									.getPrivateEquityFundStrategyType() != null) {
								temp = complexPrivateEquityFundStrategyType
										.getPrivateEquityFundStrategyType()
										.value();
								ReportUtilities.saveData(temp,
										"PrivateEquityFundStrategyType", "58",
										block, reportExecution, user, update);
							}

							// (59) <P_PrimaryStrategyFlag>
							temp = Boolean
									.toString(complexPrivateEquityFundStrategyType
											.isPrimaryStrategyFlag());
							ReportUtilities.saveData(temp,
									"P_PrimaryStrategyFlag", "59", block,
									reportExecution, user, update);

							// (60) <P_StrategyNAVRate>
							if (complexPrivateEquityFundStrategyType
									.getStrategyNAVRate() != null) {
								temp = complexPrivateEquityFundStrategyType
										.getStrategyNAVRate().toString();
								ReportUtilities.saveData(temp,
										"P_StrategyNAVRate", "60", block,
										reportExecution, user, update);
							}

							// (61) <P_StrategyTypeOtherDescription>
							if (complexPrivateEquityFundStrategyType
									.getStrategyTypeOtherDescription() != null) {
								temp = complexPrivateEquityFundStrategyType
										.getStrategyTypeOtherDescription()
										.toString();
								ReportUtilities.saveData(temp,
										"P_StrategyTypeOtherDescription", "61",
										block, reportExecution, user, update);
							}

						} // </ PrivateEquityFundInvestmentStrategy>

					} // end complexPrivateEquityFundStrategyType

					// < / PrivateEquityFundInvestmentStrategies>

					// /////////////////////////////////////////////////////////////////
					// <RealEstateFundStrategies>
					ComplexRealEstateFundInvestmentStrategiesType complexRealEstateFundInvestmentStrategiesType = complexAIFDescriptionType
							.getRealEstateFundInvestmentStrategies();
					if (complexRealEstateFundInvestmentStrategiesType != null) {

						List<ComplexRealEstateFundStrategyType> complexRealEstateFundStrategyTypeList = complexRealEstateFundInvestmentStrategiesType
								.getRealEstateFundStrategy();

						count = 0;
						// <RealEstateFundStrategy>
						for (ComplexRealEstateFundStrategyType complexRealEstateFundStrategyType : complexRealEstateFundStrategyTypeList) {
							count++;
							block = Integer.toString(count);

							// (58) <RealEstateFundStrategyType>
							if (complexRealEstateFundStrategyType
									.getRealEstateFundStrategyType() != null) {
								temp = complexRealEstateFundStrategyType
										.getRealEstateFundStrategyType()
										.value();
								ReportUtilities.saveData(temp,
										"RealEstateFundStrategyType", "58",
										block, reportExecution, user, update);
							}

							// (59) <R_PrimaryStrategyFlag>
							temp = Boolean
									.toString(complexRealEstateFundStrategyType
											.isPrimaryStrategyFlag());
							ReportUtilities.saveData(temp,
									"R_PrimaryStrategyFlag", "59", block,
									reportExecution, user, update);

							// (60) <R_StrategyNAVRate>
							if (complexRealEstateFundStrategyType
									.getStrategyNAVRate() != null) {
								temp = complexRealEstateFundStrategyType
										.getStrategyNAVRate().toString();
								ReportUtilities.saveData(temp,
										"R_StrategyNAVRate", "60", block,
										reportExecution, user, update);
							}

							// (61) <R_StrategyTypeOtherDescription>
							if (complexRealEstateFundStrategyType
									.getStrategyTypeOtherDescription() != null) {
								temp = complexRealEstateFundStrategyType
										.getStrategyTypeOtherDescription()
										.toString();
								ReportUtilities.saveData(temp,
										"R_StrategyTypeOtherDescription", "61",
										block, reportExecution, user, update);
							}

						} // < / RealEstateFundStrategy>

					} // end complexRealEstateFundStrategyType

					// < / RealEstateFundStrategies>

					// (62) <HFTTransactionNumber>
					if (complexAIFDescriptionType.getHFTTransactionNumber() != null) {
						temp = complexAIFDescriptionType
								.getHFTTransactionNumber().toString();
						ReportUtilities.saveData(temp, "HFTTransactionNumber",
								"62", null, reportExecution, user, update);
					}

					// (63) <HFTBuySellMarketValue>
					if (complexAIFDescriptionType.getHFTBuySellMarketValue() != null) {
						temp = complexAIFDescriptionType
								.getHFTBuySellMarketValue().toString();
						ReportUtilities.saveData(temp, "HFTBuySellMarketValue",
								"63", null, reportExecution, user, update);
					}

				} // end complexAIFDescriptionType

				// < / AIFDescription>

				// /////////////////////////////////////////////////////////////////
				// <MainInstrumentsTraded>
				ComplexMainInstrumentsTradedType complexMainInstrumentsTradedType = complexAIFPrincipalInfoType
						.getMainInstrumentsTraded();
				if (complexMainInstrumentsTradedType != null) {

					List<ComplexMainInstrumentTradedType> complexMainInstrumentTradedTypeList = complexMainInstrumentsTradedType
							.getMainInstrumentTraded();

					count = 0;
					// <MainInstrumentTraded>
					for (ComplexMainInstrumentTradedType complexMainInstrumentTradedType : complexMainInstrumentTradedTypeList) {
						count++;
						block = Integer.toString(count);

						// (64) <Ranking>
						if (complexMainInstrumentTradedType.getRanking() != null) {
							temp = complexMainInstrumentTradedType.getRanking()
									.toString();
							ReportUtilities.saveData(temp, "Ranking", "64",
									block, reportExecution, user, update);
						}

						// (65) <SubAssetType>
						if (complexMainInstrumentTradedType.getSubAssetType() != null) {
							temp = complexMainInstrumentTradedType
									.getSubAssetType().value();
							ReportUtilities.saveData(temp, "SubAssetType",
									"65", block, reportExecution, user, update);
						}

						// (66) <InstrumentCodeType>
						if (complexMainInstrumentTradedType
								.getInstrumentCodeType() != null) {
							temp = complexMainInstrumentTradedType
									.getInstrumentCodeType().value();
							ReportUtilities.saveData(temp,
									"InstrumentCodeType", "66", block,
									reportExecution, user, update);
						}

						// (67) <InstrumentName>
						if (complexMainInstrumentTradedType.getInstrumentName() != null) {
							temp = complexMainInstrumentTradedType
									.getInstrumentName().toString();
							ReportUtilities.saveData(temp, "InstrumentName",
									"67", block, reportExecution, user, update);
						}

						// (68) <ISINInstrumentIdentification>
						if (complexMainInstrumentTradedType
								.getISINInstrumentIdentification() != null) {
							temp = complexMainInstrumentTradedType
									.getISINInstrumentIdentification()
									.toString();
							ReportUtilities.saveData(temp,
									"ISINInstrumentIdentification", "68",
									block, reportExecution, user, update);
						}

						// <AIIInstrumentIdentification>
						ComplexAIIInstrumentIdentificationType complexAIIInstrumentIdentificationType = complexMainInstrumentTradedType
								.getAIIInstrumentIdentification();
						if (complexAIIInstrumentIdentificationType != null) {

							// (69) <AIIExchangeCode>
							if (complexAIIInstrumentIdentificationType
									.getAIIExchangeCode() != null) {
								temp = complexAIIInstrumentIdentificationType
										.getAIIExchangeCode().toString();
								ReportUtilities.saveData(temp,
										"AIIExchangeCode", "69", block,
										reportExecution, user, update);
							}

							// (70) <AIIProductCode>
							if (complexAIIInstrumentIdentificationType
									.getAIIProductCode() != null) {
								temp = complexAIIInstrumentIdentificationType
										.getAIIProductCode().toString();
								ReportUtilities.saveData(temp,
										"AIIProductCode", "70", block,
										reportExecution, user, update);
							}

							// (71) <AIIDerivativeType>
							if (complexAIIInstrumentIdentificationType
									.getAIIDerivativeType() != null) {
								temp = complexAIIInstrumentIdentificationType
										.getAIIDerivativeType().value();
								ReportUtilities.saveData(temp,
										"AIIDerivativeType", "71", block,
										reportExecution, user, update);
							}

							// (72) <AIIPutCallIdentifier>
							if (complexAIIInstrumentIdentificationType
									.getAIIPutCallIdentifier() != null) {
								temp = complexAIIInstrumentIdentificationType
										.getAIIPutCallIdentifier().toString();
								ReportUtilities.saveData(temp,
										"AIIPutCallIdentifier", "72", block,
										reportExecution, user, update);
							}

							// (73) <AIIExpiryDate>
							if (complexAIIInstrumentIdentificationType
									.getAIIExpiryDate() != null) {
								temp = new SimpleDateFormat(
										ReportUtilities.datePattern)
										.format(XMLGregorianCalendarConverter
												.asDate(complexAIIInstrumentIdentificationType
														.getAIIExpiryDate()));
								ReportUtilities.saveData(temp, "AIIExpiryDate",
										"73", block, reportExecution, user,
										update);
							}

							// (74) <AIIStrikePrice>
							if (complexAIIInstrumentIdentificationType
									.getAIIStrikePrice() != null) {
								temp = complexAIIInstrumentIdentificationType
										.getAIIStrikePrice().toString();
								ReportUtilities.saveData(temp,
										"AIIStrikePrice", "74", block,
										reportExecution, user, update);
							}

						} // end complexAIIInstrumentIdentificationType

						// < / AIIInstrumentIdentification>

						// (75) <PositionType>
						if (complexMainInstrumentTradedType.getPositionType() != null) {
							temp = complexMainInstrumentTradedType
									.getPositionType().value();
							ReportUtilities.saveData(temp, "PositionType",
									"75", block, reportExecution, user, update);
						}

						// (76) <PositionValue>
						if (complexMainInstrumentTradedType.getPositionValue() != null) {
							temp = complexMainInstrumentTradedType
									.getPositionValue().toString();
							ReportUtilities.saveData(temp, "PositionValue",
									"76", block, reportExecution, user, update);
						}

						// (77) <ShortPositionHedgingRate>
						if (complexMainInstrumentTradedType
								.getShortPositionHedgingRate() != null) {
							temp = complexMainInstrumentTradedType
									.getShortPositionHedgingRate().toString();
							ReportUtilities.saveData(temp,
									"ShortPositionHedgingRate", "77", block,
									reportExecution, user, update);
						}

					} // < / MainInstrumentTraded>

				} // end complexMainInstrumentsTradedType

				// /////////////////////////////////////////////////////////////////
				// <NAVGeographicalFocus>
				ComplexNAVGeographicalFocusType complexNAVGeographicalFocusType = complexAIFPrincipalInfoType
						.getNAVGeographicalFocus();
				if (complexNAVGeographicalFocusType != null) {

					// (78) <AfricaNAVRate>
					if (complexNAVGeographicalFocusType.getAfricaNAVRate() != null) {
						temp = complexNAVGeographicalFocusType
								.getAfricaNAVRate().toString();
						ReportUtilities.saveData(temp, "AfricaNAVRate", "78",
								null, reportExecution, user, update);
					}

					// (79) <AsiaPacificNAVRate>
					if (complexNAVGeographicalFocusType.getAsiaPacificNAVRate() != null) {
						temp = complexNAVGeographicalFocusType
								.getAsiaPacificNAVRate().toString();
						ReportUtilities.saveData(temp, "AsiaPacificNAVRate",
								"79", null, reportExecution, user, update);
					}

					// (80) <EuropeNAVRate>
					if (complexNAVGeographicalFocusType.getEuropeNAVRate() != null) {
						temp = complexNAVGeographicalFocusType
								.getEuropeNAVRate().toString();
						ReportUtilities.saveData(temp, "EuropeNAVRate", "80",
								null, reportExecution, user, update);
					}

					// (81) <EEANAVRate>
					if (complexNAVGeographicalFocusType.getEEANAVRate() != null) {
						temp = complexNAVGeographicalFocusType.getEEANAVRate()
								.toString();
						ReportUtilities.saveData(temp, "EEANAVRate", "81",
								null, reportExecution, user, update);
					}

					// (82) <MiddleEastNAVRate>
					if (complexNAVGeographicalFocusType.getMiddleEastNAVRate() != null) {
						temp = complexNAVGeographicalFocusType
								.getMiddleEastNAVRate().toString();
						ReportUtilities.saveData(temp, "MiddleEastNAVRate",
								"82", null, reportExecution, user, update);
					}

					// (83) <NorthAmericaNAVRate>
					if (complexNAVGeographicalFocusType
							.getNorthAmericaNAVRate() != null) {
						temp = complexNAVGeographicalFocusType
								.getNorthAmericaNAVRate().toString();
						ReportUtilities.saveData(temp, "NorthAmericaNAVRate",
								"83", null, reportExecution, user, update);
					}

					// (84) <SouthAmericaNAVRate>
					if (complexNAVGeographicalFocusType
							.getSouthAmericaNAVRate() != null) {
						temp = complexNAVGeographicalFocusType
								.getSouthAmericaNAVRate().toString();
						ReportUtilities.saveData(temp, "SouthAmericaNAVRate",
								"84", null, reportExecution, user, update);
					}

					// (85) <SupraNationalNAVRate>
					if (complexNAVGeographicalFocusType
							.getSupraNationalNAVRate() != null) {
						temp = complexNAVGeographicalFocusType
								.getSupraNationalNAVRate().toString();
						ReportUtilities.saveData(temp, "SupraNationalNAVRate",
								"85", null, reportExecution, user, update);
					}

				} // end complexNAVGeographicalFocusType

				// /////////////////////////////////////////////////////////////////
				// <AUMGeographicalFocus>
				ComplexAUMGeographicalFocusType complexAUMGeographicalFocusType = complexAIFPrincipalInfoType
						.getAUMGeographicalFocus();
				if (complexAUMGeographicalFocusType != null) {

					// (86) <AfricaAUMRate>
					if (complexAUMGeographicalFocusType.getAfricaAUMRate() != null) {
						temp = complexAUMGeographicalFocusType
								.getAfricaAUMRate().toString();
						ReportUtilities.saveData(temp, "AfricaAUMRate", "86",
								null, reportExecution, user, update);
					}

					// (87) <AsiaPacificAUMRate>
					if (complexAUMGeographicalFocusType.getAsiaPacificAUMRate() != null) {
						temp = complexAUMGeographicalFocusType
								.getAsiaPacificAUMRate().toString();
						ReportUtilities.saveData(temp, "AsiaPacificAUMRate",
								"87", null, reportExecution, user, update);
					}

					// (88) <EuropeAUMRate>
					if (complexAUMGeographicalFocusType.getEuropeAUMRate() != null) {
						temp = complexAUMGeographicalFocusType
								.getEuropeAUMRate().toString();
						ReportUtilities.saveData(temp, "EuropeAUMRate", "88",
								null, reportExecution, user, update);
					}

					// (89) <EEAAUMRate>
					if (complexAUMGeographicalFocusType.getEEAAUMRate() != null) {
						temp = complexAUMGeographicalFocusType.getEEAAUMRate()
								.toString();
						ReportUtilities.saveData(temp, "EEAAUMRate", "89",
								null, reportExecution, user, update);
					}

					// (90) <MiddleEastAUMRate>
					if (complexAUMGeographicalFocusType.getMiddleEastAUMRate() != null) {
						temp = complexAUMGeographicalFocusType
								.getMiddleEastAUMRate().toString();
						ReportUtilities.saveData(temp, "MiddleEastAUMRate",
								"90", null, reportExecution, user, update);
					}

					// (91) <NorthAmericaAUMRate>
					if (complexAUMGeographicalFocusType
							.getNorthAmericaAUMRate() != null) {
						temp = complexAUMGeographicalFocusType
								.getNorthAmericaAUMRate().toString();
						ReportUtilities.saveData(temp, "NorthAmericaAUMRate",
								"91", null, reportExecution, user, update);
					}

					// (92) <SouthAmericaAUMRate>
					if (complexAUMGeographicalFocusType
							.getSouthAmericaAUMRate() != null) {
						temp = complexAUMGeographicalFocusType
								.getSouthAmericaAUMRate().toString();
						ReportUtilities.saveData(temp, "SouthAmericaAUMRate",
								"92", null, reportExecution, user, update);
					}

					// (93) <SupraNationalAUMRate>
					if (complexAUMGeographicalFocusType
							.getSupraNationalAUMRate() != null) {
						temp = complexAUMGeographicalFocusType
								.getSupraNationalAUMRate().toString();
						ReportUtilities.saveData(temp, "SupraNationalAUMRate",
								"93", null, reportExecution, user, update);
					}

				} // end complexAUMGeographicalFocusType

				// /////////////////////////////////////////////////////////////////
				// <PrincipalExposures>
				ComplexPrincipalExposuresType complexPrincipalExposuresType = complexAIFPrincipalInfoType
						.getPrincipalExposures();
				if (complexPrincipalExposuresType != null) {

					List<ComplexPrincipalExposureType> complexPrincipalExposureTypeList = complexPrincipalExposuresType
							.getPrincipalExposure();

					count = 0;
					// <PrincipalExposure>
					for (ComplexPrincipalExposureType complexPrincipalExposureType : complexPrincipalExposureTypeList) {
						count++;
						block = Integer.toString(count);

						// (94) <Ranking>
						if (complexPrincipalExposureType.getRanking() != null) {
							temp = complexPrincipalExposureType.getRanking()
									.toString();
							ReportUtilities.saveData(temp, "Ranking", "94",
									block, reportExecution, user, update);
						}

						// (95) <AssetMacroType>
						if (complexPrincipalExposureType.getAssetMacroType() != null) {
							temp = complexPrincipalExposureType
									.getAssetMacroType().value();
							ReportUtilities.saveData(temp, "AssetMacroType",
									"95", block, reportExecution, user, update);
						}

						// (96) <SubAssetType>
						if (complexPrincipalExposureType.getSubAssetType() != null) {
							temp = complexPrincipalExposureType
									.getSubAssetType().value();
							ReportUtilities.saveData(temp, "SubAssetType",
									"96", block, reportExecution, user, update);
						}

						// (97) <PositionType>
						if (complexPrincipalExposureType.getPositionType() != null) {
							temp = complexPrincipalExposureType
									.getPositionType().value();
							ReportUtilities.saveData(temp, "PositionType",
									"97", block, reportExecution, user, update);
						}

						// (98) <AggregatedValueAmount>
						if (complexPrincipalExposureType
								.getAggregatedValueAmount() != null) {
							temp = complexPrincipalExposureType
									.getAggregatedValueAmount().toString();
							ReportUtilities.saveData(temp,
									"AggregatedValueAmount", "98", block,
									reportExecution, user, update);
						}

						// (99) <AggregatedValueRate>
						if (complexPrincipalExposureType
								.getAggregatedValueRate() != null) {
							temp = complexPrincipalExposureType
									.getAggregatedValueRate().toString();
							ReportUtilities.saveData(temp,
									"AggregatedValueRate", "99", block,
									reportExecution, user, update);
						}

						// <CounterpartyIdentification>
						ComplexEntityIdentificationType complexEntityIdentificationType = complexPrincipalExposureType
								.getCounterpartyIdentification();
						if (complexEntityIdentificationType != null) {

							// (100) <EntityName>
							if (complexEntityIdentificationType.getEntityName() != null) {
								temp = complexEntityIdentificationType
										.getEntityName().toString();
								ReportUtilities.saveData(temp, "EntityName",
										"100", block, reportExecution, user,
										update);
							}

							// (101) <EntityIdentificationLEI>
							if (complexEntityIdentificationType
									.getEntityIdentificationLEI() != null) {
								temp = complexEntityIdentificationType
										.getEntityIdentificationLEI()
										.toString();
								ReportUtilities.saveData(temp,
										"EntityIdentificationLEI", "101",
										block, reportExecution, user, update);
							}

							// (102) <EntityIdentificationBIC>
							if (complexEntityIdentificationType
									.getEntityIdentificationBIC() != null) {
								temp = complexEntityIdentificationType
										.getEntityIdentificationBIC()
										.toString();
								ReportUtilities.saveData(temp,
										"EntityIdentificationBIC", "102",
										block, reportExecution, user, update);
							}

						} // end complexEntityIdentificationType

					} // < / CounterpartyIdentification>

				} // end complexPrincipalExposuresType

				// < / PrincipalExposure>

				// /////////////////////////////////////////////////////////////////
				// <MostImportantConcentration>
				ComplexMostImportantConcentrationType complexMostImportantConcentrationType = complexAIFPrincipalInfoType
						.getMostImportantConcentration();
				if (complexMostImportantConcentrationType != null) {

					// /////////////////////////////////////////////////////////////////
					// <PortfolioConcentrations>
					ComplexPortfolioConcentrationsType complexPortfolioConcentrationsType = complexMostImportantConcentrationType
							.getPortfolioConcentrations();
					if (complexPortfolioConcentrationsType != null) {

						List<ComplexPortfolioConcentrationType> complexPortfolioConcentrationTypeList = complexPortfolioConcentrationsType
								.getPortfolioConcentration();

						count = 0;
						// <PortfolioConcentration>
						for (ComplexPortfolioConcentrationType complexPortfolioConcentrationType : complexPortfolioConcentrationTypeList) {
							count++;
							block = Integer.toString(count);

							// (103) <Ranking>
							if (complexPortfolioConcentrationType.getRanking() != null) {
								temp = complexPortfolioConcentrationType
										.getRanking().toString();
								ReportUtilities.saveData(temp, "Ranking",
										"103", block, reportExecution, user,
										update);
							}

							// (104) <AssetType>
							if (complexPortfolioConcentrationType
									.getAssetType() != null) {
								temp = complexPortfolioConcentrationType
										.getAssetType().value();
								ReportUtilities.saveData(temp, "AssetType",
										"104", block, reportExecution, user,
										update);
							}

							// (105) <PositionType>
							if (complexPortfolioConcentrationType
									.getPositionType() != null) {
								temp = complexPortfolioConcentrationType
										.getPositionType().value();
								ReportUtilities.saveData(temp, "PositionType",
										"105", block, reportExecution, user,
										update);
							}

							// <MarketIdentification>
							ComplexMarketIdentificationWithoutNOTType complexMarketIdentificationWithoutNOTType = complexPortfolioConcentrationType
									.getMarketIdentification();
							if (complexMarketIdentificationWithoutNOTType != null) {

								// (106) <MarketCodeType>
								if (complexMarketIdentificationWithoutNOTType
										.getMarketCodeType() != null) {
									temp = complexMarketIdentificationWithoutNOTType
											.getMarketCodeType().toString();
									ReportUtilities.saveData(temp,
											"MarketCodeType", "106", block,
											reportExecution, user, update);
								}

								// (107) <MarketCode>
								if (complexMarketIdentificationWithoutNOTType
										.getMarketCode() != null) {
									temp = complexMarketIdentificationWithoutNOTType
											.getMarketCode().toString();
									ReportUtilities.saveData(temp,
											"MarketCode", "107", block,
											reportExecution, user, update);
								}

							} // end complexMarketIdentificationWithoutNOTType

							// < / MarketIdentification>

							// (108) <AggregatedValueAmount>
							if (complexPortfolioConcentrationType
									.getAggregatedValueAmount() != null) {
								temp = complexPortfolioConcentrationType
										.getAggregatedValueAmount().toString();
								ReportUtilities.saveData(temp,
										"AggregatedValueAmount", "108", block,
										reportExecution, user, update);
							}

							// (109) <AggregatedValueRate>
							if (complexPortfolioConcentrationType
									.getAggregatedValueRate() != null) {
								temp = complexPortfolioConcentrationType
										.getAggregatedValueRate().toString();
								ReportUtilities.saveData(temp,
										"AggregatedValueRate", "109", block,
										reportExecution, user, update);
							}

							// <CounterpartyIdentification>
							ComplexEntityIdentificationType complexEntityIdentificationType = complexPortfolioConcentrationType
									.getCounterpartyIdentification();
							if (complexEntityIdentificationType != null) {

								// (110) <EntityName>
								if (complexEntityIdentificationType
										.getEntityName() != null) {
									temp = complexEntityIdentificationType
											.getEntityName().toString();
									ReportUtilities.saveData(temp,
											"EntityName", "110", block,
											reportExecution, user, update);
								}

								// (111) <EntityIdentificationLEI>
								if (complexEntityIdentificationType
										.getEntityIdentificationLEI() != null) {
									temp = complexEntityIdentificationType
											.getEntityIdentificationLEI()
											.toString();
									ReportUtilities.saveData(temp,
											"EntityIdentificationLEI", "111",
											block, reportExecution, user,
											update);
								}

								// (112) <EntityIdentificationBIC>
								if (complexEntityIdentificationType
										.getEntityIdentificationBIC() != null) {
									temp = complexEntityIdentificationType
											.getEntityIdentificationBIC()
											.toString();
									ReportUtilities.saveData(temp,
											"EntityIdentificationBIC", "112",
											block, reportExecution, user,
											update);
								}

							} // end complexEntityIdentificationType

							// < / CounterpartyIdentification>

						} // end complexPortfolioConcentrationType

						// < / PortfolioConcentration>

					} // end complexPortfolioConcentrationsType

					// < / PortfolioConcentrations>

					// /////////////////////////////////////////////////////////////////
					// <TypicalPositionSize>

					// (113) <TypicalPositionSize>
					if (complexMostImportantConcentrationType
							.getTypicalPositionSize() != null) {
						temp = complexMostImportantConcentrationType
								.getTypicalPositionSize().toString();
						ReportUtilities.saveData(temp, "TypicalPositionSize",
								"113", null, reportExecution, user, update);
					}

					// /////////////////////////////////////////////////////////////////
					// <AIFPrincipalMarkets>
					ComplexAIFPrincipalMarketsType complexAIFPrincipalMarketsType = complexMostImportantConcentrationType
							.getAIFPrincipalMarkets();
					if (complexAIFPrincipalMarketsType != null) {

						List<ComplexThreePrincipalMarketType> complexThreePrincipalMarketTypeList = complexAIFPrincipalMarketsType
								.getAIFPrincipalMarket();

						count = 0;
						// <AIFPrincipalMarket>
						for (ComplexThreePrincipalMarketType complexThreePrincipalMarketType : complexThreePrincipalMarketTypeList) {
							count++;
							block = Integer.toString(count);

							// (114) <Ranking>
							if (complexThreePrincipalMarketType.getRanking() != null) {
								temp = complexThreePrincipalMarketType
										.getRanking().toString();
								ReportUtilities.saveData(temp, "Ranking",
										"114", block, reportExecution, user,
										update);
							}

							// <MarketIdentification>
							ComplexMarketIdentificationWithNOTType complexMarketIdentificationWithNOTType = complexThreePrincipalMarketType
									.getMarketIdentification();
							if (complexMarketIdentificationWithNOTType != null) {

								// (115) <MarketCodeType>
								if (complexMarketIdentificationWithNOTType
										.getMarketCodeType() != null) {
									temp = complexMarketIdentificationWithNOTType
											.getMarketCodeType().toString();
									ReportUtilities.saveData(temp,
											"MarketCodeType", "115", block,
											reportExecution, user, update);
								}

								// (116) <MarketCode>
								if (complexMarketIdentificationWithNOTType
										.getMarketCode() != null) {
									temp = complexMarketIdentificationWithNOTType
											.getMarketCode().toString();
									ReportUtilities.saveData(temp,
											"MarketCode", "116", block,
											reportExecution, user, update);
								}

							} // end complexMarketIdentificationWithNOTType

							// < / MarketIdentification>

							// (117) <AggregatedValueAmount>
							if (complexThreePrincipalMarketType
									.getAggregatedValueAmount() != null) {
								temp = complexThreePrincipalMarketType
										.getAggregatedValueAmount().toString();
								ReportUtilities.saveData(temp,
										"AggregatedValueAmount", "117", block,
										reportExecution, user, update);
							}

						} // < / AIFPrincipalMarket>

					} // end complexAIFPrincipalMarketsType

					// < / AIFPrincipalMarkets>

					// /////////////////////////////////////////////////////////////////
					// <InvestorConcentration>
					ComplexInvestorConcentrationType complexInvestorConcentrationType = complexMostImportantConcentrationType
							.getInvestorConcentration();
					if (complexMostImportantConcentrationType != null) {

						// (118) <MainBeneficialOwnersRate>
						if (complexInvestorConcentrationType
								.getMainBeneficialOwnersRate() != null) {
							temp = complexInvestorConcentrationType
									.getMainBeneficialOwnersRate().toString();
							ReportUtilities.saveData(temp,
									"MainBeneficialOwnersRate", "118", null,
									reportExecution, user, update);
						}

						// (119) <ProfessionalInvestorConcentrationRate>
						if (complexInvestorConcentrationType
								.getProfessionalInvestorConcentrationRate() != null) {
							temp = complexInvestorConcentrationType
									.getProfessionalInvestorConcentrationRate()
									.toString();
							ReportUtilities.saveData(temp,
									"ProfessionalInvestorConcentrationRate",
									"119", null, reportExecution, user, update);
						}

						// (120) <RetailInvestorConcentrationRate>
						if (complexInvestorConcentrationType
								.getRetailInvestorConcentrationRate() != null) {
							temp = complexInvestorConcentrationType
									.getRetailInvestorConcentrationRate()
									.toString();
							ReportUtilities.saveData(temp,
									"RetailInvestorConcentrationRate", "120",
									null, reportExecution, user, update);
						}

					} // end complexInvestorConcentrationType

					// < / InvestorConcentration>

				} // end complexMostImportantConcentrationType

				// </MostImportantConcentration>

			} // end complexAIFPrincipalInfoType

			// < / AIFPrincipalInfo>

			// /////////////////////////////////////////////////////////////////
			// <AIFIndividualInfo>
			ComplexAIFIndividualInfoType complexAIFIndividualInfoType = complexAIFCompleteDescriptionType
					.getAIFIndividualInfo();
			if (complexAIFIndividualInfoType != null) {

				ComplexIndividualExposureType complexIndividualExposureType = complexAIFIndividualInfoType
						.getIndividualExposure();
				// <IndividualExposure>
				if (complexIndividualExposureType != null) {

					// <AssetTypeExposures>
					ComplexAssetTypeExposuresType complexAssetTypeExposuresType = complexIndividualExposureType
							.getAssetTypeExposures();
					if (complexAssetTypeExposuresType != null) {

						List<ComplexAssetTypeExposureType> complexAssetTypeExposureTypeList = complexAssetTypeExposuresType
								.getAssetTypeExposure();

						count = 0;
						// <AssetTypeExposures>
						for (ComplexAssetTypeExposureType complexAssetTypeExposureType : complexAssetTypeExposureTypeList) {
							count++;
							block = Integer.toString(count);

							// (121) <SubAssetType>
							if (complexAssetTypeExposureType.getSubAssetType() != null) {
								temp = complexAssetTypeExposureType
										.getSubAssetType().value();
								ReportUtilities.saveData(temp, "SubAssetType",
										"121", block, reportExecution, user,
										update);
							}

							// (122) <GrossValue>
							if (complexAssetTypeExposureType.getGrossValue() != null) {
								temp = complexAssetTypeExposureType
										.getGrossValue().toString();
								ReportUtilities.saveData(temp, "GrossValue",
										"122", block, reportExecution, user,
										update);
							}

							// (123) <LongValue>
							if (complexAssetTypeExposureType.getLongValue() != null) {
								temp = complexAssetTypeExposureType
										.getLongValue().toString();
								ReportUtilities.saveData(temp, "LongValue",
										"123", block, reportExecution, user,
										update);
							}

							// (124) <ShortValue>
							if (complexAssetTypeExposureType.getShortValue() != null) {
								temp = complexAssetTypeExposureType
										.getShortValue().toString();
								ReportUtilities.saveData(temp, "ShortValue",
										"124", block, reportExecution, user,
										update);
							}

						} // < / AssetTypeExposures>

						// < / IndividualExposure>

					} // end complexAssetTypeExposuresType

					// <AssetTypeTurnovers>
					ComplexAssetTypeTurnoversType complexAssetTypeTurnoversType = complexIndividualExposureType
							.getAssetTypeTurnovers();
					if (complexAssetTypeTurnoversType != null) {

						List<ComplexAssetTypeTurnoverType> complexAssetTypeTurnoverTypeList = complexAssetTypeTurnoversType
								.getAssetTypeTurnover();

						count = 0;
						// <AssetTypeTurnover>
						for (ComplexAssetTypeTurnoverType complexAssetTypeTurnoverType : complexAssetTypeTurnoverTypeList) {
							count++;
							block = Integer.toString(count);

							// (125) <TurnoverSubAssetType>
							if (complexAssetTypeTurnoverType
									.getTurnoverSubAssetType() != null) {
								temp = complexAssetTypeTurnoverType
										.getTurnoverSubAssetType().value();
								ReportUtilities.saveData(temp,
										"TurnoverSubAssetType", "125", block,
										reportExecution, user, update);
							}

							// (126) <MarketValue>
							if (complexAssetTypeTurnoverType.getMarketValue() != null) {
								temp = complexAssetTypeTurnoverType
										.getMarketValue().toString();
								ReportUtilities.saveData(temp, "MarketValue",
										"126", block, reportExecution, user,
										update);
							}

							// (127) <NotionalValue>
							if (complexAssetTypeTurnoverType.getNotionalValue() != null) {
								temp = complexAssetTypeTurnoverType
										.getNotionalValue().toString();
								ReportUtilities.saveData(temp, "NotionalValue",
										"127", block, reportExecution, user,
										update);
							}

						} // < / AssetTypeTurnover>

						// < / AssetTypeTurnovers>

					} // end complexAssetTypeTurnoversType

					// <CurrencyExposures>
					ComplexCurrencyExposuresType complexCurrencyExposuresType = complexIndividualExposureType
							.getCurrencyExposures();
					if (complexCurrencyExposuresType != null) {

						List<ComplexCurrencyExposureType> complexCurrencyExposureTypeList = complexCurrencyExposuresType
								.getCurrencyExposure();

						count = 0;
						// <CurrencyExposure>
						for (ComplexCurrencyExposureType complexCurrencyExposureType : complexCurrencyExposureTypeList) {
							count++;
							block = Integer.toString(count);

							// (128) <ExposureCurrency>
							if (complexCurrencyExposureType
									.getExposureCurrency() != null) {
								temp = complexCurrencyExposureType
										.getExposureCurrency().toString();
								ReportUtilities.saveData(temp,
										"ExposureCurrency", "128", block,
										reportExecution, user, update);
							}

							// (129) <LongPositionValue>
							if (complexCurrencyExposureType
									.getLongPositionValue() != null) {
								temp = complexCurrencyExposureType
										.getLongPositionValue().toString();
								ReportUtilities.saveData(temp,
										"LongPositionValue", "129", block,
										reportExecution, user, update);
							}

							// (130) <ShortPositionValue>
							if (complexCurrencyExposureType
									.getShortPositionValue() != null) {
								temp = complexCurrencyExposureType
										.getShortPositionValue().toString();
								ReportUtilities.saveData(temp,
										"ShortPositionValue", "130", block,
										reportExecution, user, update);
							}

						} // < / CurrencyExposure>

						// < / CurrencyExposures>

					} // end complexCurrencyExposuresType

					// <CompaniesDominantInfluence>
					ComplexCompaniesDominantInfluenceType complexCompaniesDominantInfluenceType = complexIndividualExposureType
							.getCompaniesDominantInfluence();
					if (complexCompaniesDominantInfluenceType != null) {

						List<ComplexCompanyDominantInfluenceType> complexCompanyDominantInfluenceTypeList = complexCompaniesDominantInfluenceType
								.getCompanyDominantInfluence();

						count = 0;
						// <CompanyDominantInfluence>
						for (ComplexCompanyDominantInfluenceType complexCompanyDominantInfluenceType : complexCompanyDominantInfluenceTypeList) {
							count++;
							block = Integer.toString(count);

							// <CompanyIdentification>
							ComplexEntityIdentificationType complexEntityIdentificationType = complexCompanyDominantInfluenceType
									.getCompanyIdentification();
							if (complexEntityIdentificationType != null) {

								// (131) <EntityName>
								if (complexEntityIdentificationType
										.getEntityName() != null) {
									temp = complexEntityIdentificationType
											.getEntityName().toString();
									ReportUtilities.saveData(temp,
											"EntityName", "131", block,
											reportExecution, user, update);
								}

								// (132) <EntityIdentificationLEI>
								if (complexEntityIdentificationType
										.getEntityIdentificationLEI() != null) {
									temp = complexEntityIdentificationType
											.getEntityIdentificationLEI()
											.toString();
									ReportUtilities.saveData(temp,
											"EntityIdentificationLEI", "132",
											block, reportExecution, user,
											update);
								}

								// (133) <EntityIdentificationBIC>
								if (complexEntityIdentificationType
										.getEntityIdentificationBIC() != null) {
									temp = complexEntityIdentificationType
											.getEntityIdentificationBIC()
											.toString();
									ReportUtilities.saveData(temp,
											"EntityIdentificationBIC", "133",
											block, reportExecution, user,
											update);
								}

							} // end complexEntityIdentificationType

							// < / CompanyIdentification>

							// (134) <TransactionType>
							if (complexCompanyDominantInfluenceType
									.getTransactionType() != null) {
								temp = complexCompanyDominantInfluenceType
										.getTransactionType().value();
								ReportUtilities.saveData(temp,
										"TransactionType", "134", block,
										reportExecution, user, update);
							}

							// (135) <OtherTransactionTypeDescription>
							if (complexCompanyDominantInfluenceType
									.getOtherTransactionTypeDescription() != null) {
								temp = complexCompanyDominantInfluenceType
										.getOtherTransactionTypeDescription()
										.toString();
								ReportUtilities.saveData(temp,
										"OtherTransactionTypeDescription",
										"135", block, reportExecution, user,
										update);
							}

							// (136) <VotingRightsRate>
							if (complexCompanyDominantInfluenceType
									.getVotingRightsRate() != null) {
								temp = complexCompanyDominantInfluenceType
										.getVotingRightsRate().toString();
								ReportUtilities.saveData(temp,
										"VotingRightsRate", "136", block,
										reportExecution, user, update);
							}

							// < / CompanyDominantInfluence>
						}

						// < / CompanyDominantInfluence>

					} // end complexCompaniesDominantInfluenceType

				} // end complexIndividualExposureType

				// <RiskProfile>
				ComplexRiskProfileType complexRiskProfileType = complexAIFIndividualInfoType
						.getRiskProfile();
				if (complexRiskProfileType != null) {

					// <MarketRiskProfile>
					ComplexMarketRiskProfileType complexMarketRiskProfileType = complexRiskProfileType
							.getMarketRiskProfile();
					if (complexMarketRiskProfileType != null) {

						// <MarketRiskProfile>
						ComplexMarketRiskMeasuresType complexMarketRiskMeasuresType = complexMarketRiskProfileType
								.getMarketRiskMeasures();

						// (137) <AnnualInvestmentReturnRate>
						if (complexMarketRiskProfileType
								.getAnnualInvestmentReturnRate() != null) {
							temp = complexMarketRiskProfileType
									.getAnnualInvestmentReturnRate().toString();
							ReportUtilities.saveData(temp,
									"AnnualInvestmentReturnRate", "137", null,
									reportExecution, user, update);
						}

						if (complexMarketRiskMeasuresType != null) {

							List<ComplexMarketRiskMeasureType> complexMarketRiskMeasureTypeList = complexMarketRiskMeasuresType
									.getMarketRiskMeasure();

							count = 0;
							// <MarketRiskMeasures>
							for (ComplexMarketRiskMeasureType complexMarketRiskMeasureType : complexMarketRiskMeasureTypeList) {
								count++;
								block = Integer.toString(count);

								// (138) <RiskMeasureType>
								if (complexMarketRiskMeasureType
										.getRiskMeasureType() != null) {
									temp = complexMarketRiskMeasureType
											.getRiskMeasureType().value();
									ReportUtilities.saveData(temp,
											"RiskMeasureType", "138", block,
											reportExecution, user, update);
								}

								// (139) <RiskMeasureValue>
								if (complexMarketRiskMeasureType
										.getRiskMeasureValue() != null) {
									temp = complexMarketRiskMeasureType
											.getRiskMeasureValue().toString();
									ReportUtilities.saveData(temp,
											"RiskMeasureValue", "139", block,
											reportExecution, user, update);
								}

								// <BucketRiskMeasureValues>
								ComplexBucketRiskMeasureValuesType complexBucketRiskMeasureValuesType = complexMarketRiskMeasureType
										.getBucketRiskMeasureValues();
								if (complexBucketRiskMeasureValuesType != null) {

									// (140) <LessFiveYearsRiskMeasureValue>
									if (complexBucketRiskMeasureValuesType
											.getLessFiveYearsRiskMeasureValue() != null) {
										temp = complexBucketRiskMeasureValuesType
												.getLessFiveYearsRiskMeasureValue()
												.toString();
										ReportUtilities
												.saveData(
														temp,
														"LessFiveYearsRiskMeasureValue",
														"140", block,
														reportExecution, user,
														update);
									}

									// (141) <FifthteenYearsRiskMeasureValue>
									if (complexBucketRiskMeasureValuesType
											.getFifthteenYearsRiskMeasureValue() != null) {
										temp = complexBucketRiskMeasureValuesType
												.getFifthteenYearsRiskMeasureValue()
												.toString();
										ReportUtilities
												.saveData(
														temp,
														"FifthteenYearsRiskMeasureValue",
														"141", block,
														reportExecution, user,
														update);
									}

									// (142)
									// <MoreFifthteenYearsRiskMeasureValue>
									if (complexBucketRiskMeasureValuesType
											.getMoreFifthteenYearsRiskMeasureValue() != null) {
										temp = complexBucketRiskMeasureValuesType
												.getMoreFifthteenYearsRiskMeasureValue()
												.toString();
										ReportUtilities
												.saveData(
														temp,
														"MoreFifthteenYearsRiskMeasureValue",
														"142", block,
														reportExecution, user,
														update);
									}

								} // end complexBucketRiskMeasureValuesType
									// < / BucketRiskMeasureValues>

								// <VegaRiskMeasureValues>
								ComplexVegaRiskMeasureValuesType complexVegaRiskMeasureValuesType = complexMarketRiskMeasureType
										.getVegaRiskMeasureValues();
								if (complexVegaRiskMeasureValuesType != null) {

									// (143) <CurrentMarketRiskMeasureValue>
									if (complexVegaRiskMeasureValuesType
											.getCurrentMarketRiskMeasureValue() != null) {
										temp = complexVegaRiskMeasureValuesType
												.getCurrentMarketRiskMeasureValue()
												.toString();
										ReportUtilities
												.saveData(
														temp,
														"CurrentMarketRiskMeasureValue",
														"143", block,
														reportExecution, user,
														update);
									}

									// (144) <LowerMarketRiskMeasureValue>
									if (complexVegaRiskMeasureValuesType
											.getLowerMarketRiskMeasureValue() != null) {
										temp = complexVegaRiskMeasureValuesType
												.getLowerMarketRiskMeasureValue()
												.toString();
										ReportUtilities.saveData(temp,
												"LowerMarketRiskMeasureValue",
												"144", block, reportExecution,
												user, update);
									}

									// (145) <HigherMarketRiskMeasureValue>
									if (complexVegaRiskMeasureValuesType
											.getHigherMarketRiskMeasureValue() != null) {
										temp = complexVegaRiskMeasureValuesType
												.getHigherMarketRiskMeasureValue()
												.toString();
										ReportUtilities.saveData(temp,
												"HigherMarketRiskMeasureValue",
												"145", block, reportExecution,
												user, update);
									}

								} // end complexVegaRiskMeasureValuesType
									// < / VegaRiskMeasureValues>

								// <VARRiskMeasureValues>
								ComplexVARRiskMeasureValuesType complexVARRiskMeasureValuesType = complexMarketRiskMeasureType
										.getVARRiskMeasureValues();
								if (complexVARRiskMeasureValuesType != null) {

									// (146) <VARCalculationMethodCodeType>
									if (complexVARRiskMeasureValuesType
											.getVARCalculationMethodCodeType() != null) {
										temp = complexVARRiskMeasureValuesType
												.getVARCalculationMethodCodeType()
												.value();
										ReportUtilities.saveData(temp,
												"VARCalculationMethodCodeType",
												"146", block, reportExecution,
												user, update);
									}

									// (302) <VARValue>
									if (complexVARRiskMeasureValuesType
											.getVARValue() != null) {
										temp = complexVARRiskMeasureValuesType
												.getVARValue().toString();
										ReportUtilities.saveData(temp,
												"VARValue", "302", block,
												reportExecution, user, update);
									}

								} // end complexVARRiskMeasureValuesType

								// < / VARRiskMeasureValues>

								// (147) <RiskMeasureDescription>
								if (complexMarketRiskMeasureType
										.getRiskMeasureDescription() != null) {
									temp = complexMarketRiskMeasureType
											.getRiskMeasureDescription()
											.toString();
									ReportUtilities.saveData(temp,
											"RiskMeasureDescription", "147",
											block, reportExecution, user,
											update);
								}

							} // < / MarketRiskMeasures>

						} // end complexMarketRiskMeasuresType

						// < / MarketRiskProfile>

					} // end complexMarketRiskProfileType

					// /////////////////////////////////////////////////////////////////
					// <CounterpartyRiskProfile>
					ComplexCounterpartyRiskProfileType complexCounterpartyRiskProfileType = complexRiskProfileType
							.getCounterpartyRiskProfile();
					if (complexCounterpartyRiskProfileType != null) {

						// /////////////////////////////////////////////////////////////////
						// <TradingClearingMechanism>
						ComplexTradingClearingMechanismType complexTradingClearingMechanismType = complexCounterpartyRiskProfileType
								.getTradingClearingMechanism();
						if (complexTradingClearingMechanismType != null) {

							// <TradedSecurities>
							ComplexMarketRate3P2Type complexTradedSecurities = complexTradingClearingMechanismType
									.getTradedSecurities();
							if (complexTradedSecurities != null) {

								// (148) <RegulatedMarketRate>
								if (complexTradedSecurities
										.getRegulatedMarketRate() != null) {
									temp = complexTradedSecurities
											.getRegulatedMarketRate()
											.toString();
									ReportUtilities.saveData(temp,
											"RegulatedMarketRate", "148", null,
											reportExecution, user, update);
								}

								// (149) <OTCRate>
								if (complexTradedSecurities.getOTCRate() != null) {
									temp = complexTradedSecurities.getOTCRate()
											.toString();
									ReportUtilities.saveData(temp, "OTCRate",
											"149", null, reportExecution, user,
											update);
								}

							} // end complexTradedSecurities
								// < / TradedSecurities>

							// <TradedDerivatives>
							ComplexMarketRate3P2Type complexMarketRate3P2Type = complexTradingClearingMechanismType
									.getTradedDerivatives();
							if (complexMarketRate3P2Type != null) {

								// (150) <RegulatedMarketRate>
								if (complexMarketRate3P2Type
										.getRegulatedMarketRate() != null) {
									temp = complexMarketRate3P2Type
											.getRegulatedMarketRate()
											.toString();
									ReportUtilities.saveData(temp,
											"RegulatedMarketRate", "150", null,
											reportExecution, user, update);
								}

								// (151) <OTCRate>
								if (complexMarketRate3P2Type.getOTCRate() != null) {
									temp = complexMarketRate3P2Type
											.getOTCRate().toString();
									ReportUtilities.saveData(temp, "OTCRate",
											"151", null, reportExecution, user,
											update);
								}

							} // end complexMarketRate3P2Type
								// < / TradedDerivatives>

							// <ClearedDerivativesRate>
							ComplexClearedDerivativesRateType complexClearedDerivativesRateType = complexTradingClearingMechanismType
									.getClearedDerivativesRate();
							if (complexClearedDerivativesRateType != null) {

								// (152) <CCPRate>
								if (complexClearedDerivativesRateType
										.getCCPRate() != null) {
									temp = complexClearedDerivativesRateType
											.getCCPRate().toString();
									ReportUtilities.saveData(temp, "CCPRate",
											"152", null, reportExecution, user,
											update);
								}

								// (153) <BilateralClearingRate>
								if (complexClearedDerivativesRateType
										.getBilateralClearingRate() != null) {
									temp = complexClearedDerivativesRateType
											.getBilateralClearingRate()
											.toString();
									ReportUtilities
											.saveData(temp,
													"BilateralClearingRate",
													"153", null,
													reportExecution, user,
													update);
								}

							} // end complexClearedDerivativesRateType
								// < / ClearedDerivativesRate>

							// <ClearedReposRate>
							ComplexClearedReposRateType complexClearedReposRateType = complexTradingClearingMechanismType
									.getClearedReposRate();
							if (complexClearedReposRateType != null) {

								// (154) <CCPRate>
								if (complexClearedReposRateType.getCCPRate() != null) {
									temp = complexClearedReposRateType
											.getCCPRate().toString();
									ReportUtilities.saveData(temp, "CCPRate",
											"154", null, reportExecution, user,
											update);
								}

								// (155) <BilateralClearingRate>
								if (complexClearedReposRateType
										.getBilateralClearingRate() != null) {
									temp = complexClearedReposRateType
											.getBilateralClearingRate()
											.toString();
									ReportUtilities
											.saveData(temp,
													"BilateralClearingRate",
													"155", null,
													reportExecution, user,
													update);
								}

								// (156) <TriPartyRepoClearingRate>
								if (complexClearedReposRateType
										.getTriPartyRepoClearingRate() != null) {
									temp = complexClearedReposRateType
											.getTriPartyRepoClearingRate()
											.toString();
									ReportUtilities
											.saveData(temp,
													"TriPartyRepoClearingRate",
													"156", null,
													reportExecution, user,
													update);
								}

							} // end complexClearedReposRateType
								// < / ClearedReposRate>

						} // end complexTradingClearingMechanismType

						// < / TradingClearingMechanism>

						// /////////////////////////////////////////////////////////////////
						// <AllCounterpartyCollateral>
						ComplexAllCounterpartyCollateralType complexAllCounterpartyCollateralType = complexCounterpartyRiskProfileType
								.getAllCounterpartyCollateral();
						if (complexAllCounterpartyCollateralType != null) {

							// (157) <AllCounterpartyCollateralCash>
							if (complexAllCounterpartyCollateralType
									.getAllCounterpartyCollateralCash() != null) {
								temp = complexAllCounterpartyCollateralType
										.getAllCounterpartyCollateralCash()
										.toString();
								ReportUtilities.saveData(temp,
										"AllCounterpartyCollateralCash", "157",
										null, reportExecution, user, update);
							}

							// (158) <AllCounterpartyCollateralSecurities>
							if (complexAllCounterpartyCollateralType
									.getAllCounterpartyCollateralSecurities() != null) {
								temp = complexAllCounterpartyCollateralType
										.getAllCounterpartyCollateralSecurities()
										.toString();
								ReportUtilities.saveData(temp,
										"AllCounterpartyCollateralSecurities",
										"158", null, reportExecution, user,
										update);
							}

							// (159) <AllCounterpartyOtherCollateralPosted>
							if (complexAllCounterpartyCollateralType
									.getAllCounterpartyOtherCollateralPosted() != null) {
								temp = complexAllCounterpartyCollateralType
										.getAllCounterpartyOtherCollateralPosted()
										.toString();
								ReportUtilities.saveData(temp,
										"AllCounterpartyOtherCollateralPosted",
										"159", null, reportExecution, user,
										update);
							}

						} // end complexAllCounterpartyCollateralType
							// < / AllCounterpartyCollateral>

						// /////////////////////////////////////////////////////////////////
						// <FundToCounterpartyExposures>
						ComplexFundToCounterpartyExposuresType complexFundToCounterpartyExposuresType = complexCounterpartyRiskProfileType
								.getFundToCounterpartyExposures();
						if (complexFundToCounterpartyExposuresType != null) {

							List<ComplexCounterpartyExposureType> complexFundCounterpartyExposureTypeList = complexFundToCounterpartyExposuresType
									.getFundToCounterpartyExposure();

							count = 0;
							// <FundToCounterpartyExposure>
							for (ComplexCounterpartyExposureType complexCounterpartyExposureType : complexFundCounterpartyExposureTypeList) {
								count++;
								block = Integer.toString(count);

								// (160) <Ranking>
								if (complexCounterpartyExposureType
										.getRanking() != null) {
									temp = complexCounterpartyExposureType
											.getRanking().toString();
									ReportUtilities.saveData(temp, "Ranking",
											"160", block, reportExecution,
											user, update);
								}

								// (161) <CounterpartyExposureFlag>
								temp = Boolean
										.toString(complexCounterpartyExposureType
												.isCounterpartyExposureFlag());
								ReportUtilities.saveData(temp,
										"CounterpartyExposureFlag", "161",
										block, reportExecution, user, update);

								// <CounterpartyIdentification>
								ComplexEntityIdentificationType complexEntityIdentificationType = complexCounterpartyExposureType
										.getCounterpartyIdentification();
								if (complexEntityIdentificationType != null) {

									// (162) <EntityName>
									if (complexEntityIdentificationType
											.getEntityName() != null) {
										temp = complexEntityIdentificationType
												.getEntityName().toString();
										ReportUtilities.saveData(temp,
												"EntityName", "162", block,
												reportExecution, user, update);
									}

									// (163) <EntityIdentificationLEI>
									if (complexEntityIdentificationType
											.getEntityIdentificationLEI() != null) {
										temp = complexEntityIdentificationType
												.getEntityIdentificationLEI()
												.toString();
										ReportUtilities.saveData(temp,
												"EntityIdentificationLEI",
												"163", block, reportExecution,
												user, update);
									}

									// (164) <EntityIdentificationBIC>
									if (complexEntityIdentificationType
											.getEntityIdentificationBIC() != null) {
										temp = complexEntityIdentificationType
												.getEntityIdentificationBIC()
												.toString();
										ReportUtilities.saveData(temp,
												"EntityIdentificationBIC",
												"164", block, reportExecution,
												user, update);
									}

								} // end complexEntityIdentificationType
									// < / CounterpartyIdentification>

								// (165) <CounterpartyTotalExposureRate>
								if (complexCounterpartyExposureType
										.getCounterpartyTotalExposureRate() != null) {
									temp = complexCounterpartyExposureType
											.getCounterpartyTotalExposureRate()
											.toString();
									ReportUtilities.saveData(temp,
											"CounterpartyTotalExposureRate",
											"165", block, reportExecution,
											user, update);
								}

							} // < / FundToCounterpartyExposure>

						} // end complexFundToCounterpartyExposuresType

						// < / FundToCounterpartyExposures>

						// /////////////////////////////////////////////////////////////////
						// <CounterpartyToFundExposures>
						ComplexCounterpartyToFundExposuresType complexCounterpartyToFundExposuresType = complexCounterpartyRiskProfileType
								.getCounterpartyToFundExposures();
						if (complexCounterpartyToFundExposuresType != null) {

							List<ComplexCounterpartyExposureType> complexCounterpartyExposureTypeList = complexCounterpartyToFundExposuresType
									.getCounterpartyToFundExposure();

							count = 0;
							// <CounterpartyToFundExposure>
							for (ComplexCounterpartyExposureType complexCounterpartyExposureType : complexCounterpartyExposureTypeList) {
								count++;
								block = Integer.toString(count);

								// (166) <Ranking>
								if (complexCounterpartyExposureType
										.getRanking() != null) {
									temp = complexCounterpartyExposureType
											.getRanking().toString();
									ReportUtilities.saveData(temp, "Ranking",
											"166", block, reportExecution,
											user, update);
								}

								// (167) <CounterpartyExposureFlag>
								temp = Boolean
										.toString(complexCounterpartyExposureType
												.isCounterpartyExposureFlag());
								ReportUtilities.saveData(temp,
										"CounterpartyExposureFlag", "167",
										block, reportExecution, user, update);

								// <CounterpartyIdentification>
								ComplexEntityIdentificationType complexEntityIdentificationType = complexCounterpartyExposureType
										.getCounterpartyIdentification();
								if (complexEntityIdentificationType != null) {

									// (168) <EntityName>
									if (complexEntityIdentificationType
											.getEntityName() != null) {
										temp = complexEntityIdentificationType
												.getEntityName().toString();
										ReportUtilities.saveData(temp,
												"EntityName", "168", block,
												reportExecution, user, update);
									}

									// (169) <EntityIdentificationLEI>
									if (complexEntityIdentificationType
											.getEntityIdentificationLEI() != null) {
										temp = complexEntityIdentificationType
												.getEntityIdentificationLEI()
												.toString();
										ReportUtilities.saveData(temp,
												"EntityIdentificationLEI",
												"169", block, reportExecution,
												user, update);
									}

									// (170) <EntityIdentificationBIC>
									if (complexEntityIdentificationType
											.getEntityIdentificationBIC() != null) {
										temp = complexEntityIdentificationType
												.getEntityIdentificationBIC()
												.toString();
										ReportUtilities.saveData(temp,
												"EntityIdentificationBIC",
												"170", block, reportExecution,
												user, update);
									}

								} // end complexEntityIdentificationType
									// < / CounterpartyIdentification>

								// (171) <CounterpartyTotalExposureRate>
								if (complexCounterpartyExposureType
										.getCounterpartyTotalExposureRate() != null) {
									temp = complexCounterpartyExposureType
											.getCounterpartyTotalExposureRate()
											.toString();
									ReportUtilities.saveData(temp,
											"CounterpartyTotalExposureRate",
											"171", block, reportExecution,
											user, update);
								}

							} // < / CounterpartyToFundExposure>

						} // end complexCounterpartyToFundExposuresType

						// < / CounterpartyToFundExposures>

						// (172) <ClearTransactionsThroughCCPFlag>
						temp = Boolean
								.toString(complexCounterpartyRiskProfileType
										.isClearTransactionsThroughCCPFlag());
						ReportUtilities.saveData(temp,
								"ClearTransactionsThroughCCPFlag", "172", null,
								reportExecution, user, update);

						// /////////////////////////////////////////////////////////////////
						// <CCPExposures>
						ComplexCCPExposuresType complexCCPExposuresType = complexCounterpartyRiskProfileType
								.getCCPExposures();
						if (complexCCPExposuresType != null) {

							List<ComplexCCPExposureType> complexCCPExposureTypeList = complexCCPExposuresType
									.getCCPExposure();

							count = 0;
							// <CCPExposure>
							for (ComplexCCPExposureType complexCCPExposureType : complexCCPExposureTypeList) {
								count++;
								block = Integer.toString(count);

								// (173) <Ranking>
								if (complexCCPExposureType.getRanking() != null) {
									temp = complexCCPExposureType.getRanking()
											.toString();
									ReportUtilities.saveData(temp, "Ranking",
											"173", block, reportExecution,
											user, update);
								}

								// <CCPIdentification>
								ComplexEntityIdentificationType complexEntityIdentificationType = complexCCPExposureType
										.getCCPIdentification();
								if (complexEntityIdentificationType != null) {

									// (174) <EntityName>
									if (complexEntityIdentificationType
											.getEntityName() != null) {
										temp = complexEntityIdentificationType
												.getEntityName().toString();
										ReportUtilities.saveData(temp,
												"EntityName", "174", block,
												reportExecution, user, update);
									}

									// (175) <EntityIdentificationLEI>
									if (complexEntityIdentificationType
											.getEntityIdentificationLEI() != null) {
										temp = complexEntityIdentificationType
												.getEntityIdentificationLEI()
												.toString();
										ReportUtilities.saveData(temp,
												"EntityIdentificationLEI",
												"175", block, reportExecution,
												user, update);
									}

									// (176) <EntityIdentificationBIC>
									if (complexEntityIdentificationType
											.getEntityIdentificationBIC() != null) {
										temp = complexEntityIdentificationType
												.getEntityIdentificationBIC()
												.toString();
										ReportUtilities.saveData(temp,
												"EntityIdentificationBIC",
												"176", block, reportExecution,
												user, update);
									}

								} // end complexEntityIdentificationType
									// < / CCPIdentification>

								// (177) <CCPExposureValue>
								if (complexCCPExposureType
										.getCCPExposureValue() != null) {
									temp = complexCCPExposureType
											.getCCPExposureValue().toString();
									ReportUtilities.saveData(temp,
											"CCPExposureValue", "177", block,
											reportExecution, user, update);
								}

							} // < / CCPExposure>

						} // end complexCCPExposuresType

						// < / CCPExposures>

					} // end complexCounterpartyRiskProfileType

					// < / CounterpartyRiskProfile>

					// /////////////////////////////////////////////////////////////////
					// <LiquidityRiskProfile>
					ComplexLiquidityRiskProfileType complexLiquidityRiskProfileType = complexRiskProfileType
							.getLiquidityRiskProfile();
					if (complexLiquidityRiskProfileType != null) {

						// <PortfolioLiquidityProfile>
						ComplexPortfolioLiquidityProfileType complexPortfolioLiquidityProfileType = complexLiquidityRiskProfileType
								.getPortfolioLiquidityProfile();
						if (complexPortfolioLiquidityProfileType != null) {

							// (178) <PortfolioLiquidityInDays0to1Rate>
							if (complexPortfolioLiquidityProfileType
									.getPortfolioLiquidityInDays0To1Rate() != null) {
								temp = complexPortfolioLiquidityProfileType
										.getPortfolioLiquidityInDays0To1Rate()
										.toString();
								ReportUtilities.saveData(temp,
										"PortfolioLiquidityInDays0to1Rate",
										"178", null, reportExecution, user,
										update);
							}

							// (179) <PortfolioLiquidityInDays2to7Rate>
							if (complexPortfolioLiquidityProfileType
									.getPortfolioLiquidityInDays2To7Rate() != null) {
								temp = complexPortfolioLiquidityProfileType
										.getPortfolioLiquidityInDays2To7Rate()
										.toString();
								ReportUtilities.saveData(temp,
										"PortfolioLiquidityInDays2to7Rate",
										"179", null, reportExecution, user,
										update);
							}

							// (180) <PortfolioLiquidityInDays8to30Rate>
							if (complexPortfolioLiquidityProfileType
									.getPortfolioLiquidityInDays8To30Rate() != null) {
								temp = complexPortfolioLiquidityProfileType
										.getPortfolioLiquidityInDays8To30Rate()
										.toString();
								ReportUtilities.saveData(temp,
										"PortfolioLiquidityInDays8to30Rate",
										"180", null, reportExecution, user,
										update);
							}

							// (181) <PortfolioLiquidityInDays31to90Rate>
							if (complexPortfolioLiquidityProfileType
									.getPortfolioLiquidityInDays31To90Rate() != null) {
								temp = complexPortfolioLiquidityProfileType
										.getPortfolioLiquidityInDays31To90Rate()
										.toString();
								ReportUtilities.saveData(temp,
										"PortfolioLiquidityInDays31to90Rate",
										"181", null, reportExecution, user,
										update);
							}

							// (182) <PortfolioLiquidityInDays91to180Rate>
							if (complexPortfolioLiquidityProfileType
									.getPortfolioLiquidityInDays91To180Rate() != null) {
								temp = complexPortfolioLiquidityProfileType
										.getPortfolioLiquidityInDays91To180Rate()
										.toString();
								ReportUtilities.saveData(temp,
										"PortfolioLiquidityInDays91to180Rate",
										"182", null, reportExecution, user,
										update);
							}

							// (183) <PortfolioLiquidityInDays181to365Rate>
							if (complexPortfolioLiquidityProfileType
									.getPortfolioLiquidityInDays181To365Rate() != null) {
								temp = complexPortfolioLiquidityProfileType
										.getPortfolioLiquidityInDays181To365Rate()
										.toString();
								ReportUtilities.saveData(temp,
										"PortfolioLiquidityInDays181to365Rate",
										"183", null, reportExecution, user,
										update);
							}

							// (184) <PortfolioLiquidityInDays365MoreRate>
							if (complexPortfolioLiquidityProfileType
									.getPortfolioLiquidityInDays365MoreRate() != null) {
								temp = complexPortfolioLiquidityProfileType
										.getPortfolioLiquidityInDays365MoreRate()
										.toString();
								ReportUtilities.saveData(temp,
										"PortfolioLiquidityInDays365MoreRate",
										"184", null, reportExecution, user,
										update);
							}

							// (185) <UnencumberedCash>
							if (complexPortfolioLiquidityProfileType
									.getUnencumberedCash() != null) {
								temp = complexPortfolioLiquidityProfileType
										.getUnencumberedCash().toString();
								ReportUtilities.saveData(temp,
										"UnencumberedCash", "185", null,
										reportExecution, user, update);
							}

						} // end complexPortfolioLiquidityProfileType
							// < / PortfolioLiquidityProfile>

						// <InvestorLiquidityProfile>
						ComplexInvestorLiquidityProfileType complexInvestorLiquidityProfileType = complexLiquidityRiskProfileType
								.getInvestorLiquidityProfile();
						if (complexInvestorLiquidityProfileType != null) {

							// (186) <InvestorLiquidityInDays0to1Rate>
							if (complexInvestorLiquidityProfileType
									.getInvestorLiquidityInDays0To1Rate() != null) {
								temp = complexInvestorLiquidityProfileType
										.getInvestorLiquidityInDays0To1Rate()
										.toString();
								ReportUtilities.saveData(temp,
										"InvestorLiquidityInDays0to1Rate",
										"186", null, reportExecution, user,
										update);
							}

							// (187) <InvestorLiquidityInDays2to7Rate>
							if (complexInvestorLiquidityProfileType
									.getInvestorLiquidityInDays2To7Rate() != null) {
								temp = complexInvestorLiquidityProfileType
										.getInvestorLiquidityInDays2To7Rate()
										.toString();
								ReportUtilities.saveData(temp,
										"InvestorLiquidityInDays2to7Rate",
										"187", null, reportExecution, user,
										update);
							}

							// (188) <InvestorLiquidityInDays8to30Rate>
							if (complexInvestorLiquidityProfileType
									.getInvestorLiquidityInDays8To30Rate() != null) {
								temp = complexInvestorLiquidityProfileType
										.getInvestorLiquidityInDays8To30Rate()
										.toString();
								ReportUtilities.saveData(temp,
										"InvestorLiquidityInDays8to30Rate",
										"188", null, reportExecution, user,
										update);
							}

							// (189) <InvestorLiquidityInDays31to90Rate>
							if (complexInvestorLiquidityProfileType
									.getInvestorLiquidityInDays31To90Rate() != null) {
								temp = complexInvestorLiquidityProfileType
										.getInvestorLiquidityInDays31To90Rate()
										.toString();
								ReportUtilities.saveData(temp,
										"InvestorLiquidityInDays31to90Rate",
										"189", null, reportExecution, user,
										update);
							}

							// (190) <InvestorLiquidityInDays91to180Rate>
							if (complexInvestorLiquidityProfileType
									.getInvestorLiquidityInDays91To180Rate() != null) {
								temp = complexInvestorLiquidityProfileType
										.getInvestorLiquidityInDays91To180Rate()
										.toString();
								ReportUtilities.saveData(temp,
										"InvestorLiquidityInDays91to180Rate",
										"190", null, reportExecution, user,
										update);
							}

							// (191) <InvestorLiquidityInDays181to365Rate>
							if (complexInvestorLiquidityProfileType
									.getInvestorLiquidityInDays181To365Rate() != null) {
								temp = complexInvestorLiquidityProfileType
										.getInvestorLiquidityInDays181To365Rate()
										.toString();
								ReportUtilities.saveData(temp,
										"InvestorLiquidityInDays181to365Rate",
										"191", null, reportExecution, user,
										update);
							}

							// (192) <InvestorLiquidityInDays365MoreRate>
							if (complexInvestorLiquidityProfileType
									.getInvestorLiquidityInDays365MoreRate() != null) {
								temp = complexInvestorLiquidityProfileType
										.getInvestorLiquidityInDays365MoreRate()
										.toString();
								ReportUtilities.saveData(temp,
										"InvestorLiquidityInDays365MoreRate",
										"192", null, reportExecution, user,
										update);
							}

						} // end complexInvestorLiquidityProfileType
							// < / InvestorLiquidityProfile>

						// <InvestorRedemption>
						ComplexInvestorRedemptionType complexInvestorRedemptionType = complexLiquidityRiskProfileType
								.getInvestorRedemption();
						if (complexInvestorRedemptionType != null) {

							// (193) <ProvideWithdrawalRightsFlag>
							temp = Boolean
									.toString(complexInvestorRedemptionType
											.isProvideWithdrawalRightsFlag());
							ReportUtilities.saveData(temp,
									"ProvideWithdrawalRightsFlag", "193", null,
									reportExecution, user, update);

							// (194) <InvestorRedemptionFrequency>
							if (complexInvestorRedemptionType
									.getInvestorRedemptionFrequency() != null) {
								temp = complexInvestorRedemptionType
										.getInvestorRedemptionFrequency()
										.toString();
								ReportUtilities.saveData(temp,
										"InvestorRedemptionFrequency", "194",
										null, reportExecution, user, update);
							}

							// (195) <InvestorRedemptionNoticePeriod>
							if (complexInvestorRedemptionType
									.getInvestorRedemptionNoticePeriod() != null) {
								temp = complexInvestorRedemptionType
										.getInvestorRedemptionNoticePeriod()
										.toString();
								ReportUtilities.saveData(temp,
										"InvestorRedemptionNoticePeriod",
										"195", null, reportExecution, user,
										update);
							}

							// (196) <InvestorRedemptionLockUpPeriod>
							if (complexInvestorRedemptionType
									.getInvestorRedemptionLockUpPeriod() != null) {
								temp = complexInvestorRedemptionType
										.getInvestorRedemptionLockUpPeriod()
										.toString();
								ReportUtilities.saveData(temp,
										"InvestorRedemptionLockUpPeriod",
										"196", null, reportExecution, user,
										update);
							}

						} // end complexInvestorRedemptionType
							// < / InvestorRedemption>

						// <InvestorArrangement>
						ComplexInvestorArrangementType complexInvestorArrangementType = complexLiquidityRiskProfileType
								.getInvestorArrangement();
						if (complexInvestorArrangementType != null) {

							// <InvestorIlliquidAssetArrangement>
							ComplexInvestorIlliquidAssetArrangementType complexInvestorIlliquidAssetArrangementType = complexInvestorArrangementType
									.getInvestorIlliquidAssetArrangement();
							if (complexInvestorIlliquidAssetArrangementType != null) {

								// (197) <SidePocketRate>
								if (complexInvestorIlliquidAssetArrangementType
										.getSidePocketRate() != null) {
									temp = complexInvestorIlliquidAssetArrangementType
											.getSidePocketRate().toString();
									ReportUtilities.saveData(temp,
											"SidePocketRate", "197", null,
											reportExecution, user, update);
								}

								// (198) <GatesRate>
								if (complexInvestorIlliquidAssetArrangementType
										.getGatesRate() != null) {
									temp = complexInvestorIlliquidAssetArrangementType
											.getGatesRate().toString();
									ReportUtilities.saveData(temp, "GatesRate",
											"198", null, reportExecution, user,
											update);
								}

								// (199) <DealingSuspensionRate>
								if (complexInvestorIlliquidAssetArrangementType
										.getDealingSuspensionRate() != null) {
									temp = complexInvestorIlliquidAssetArrangementType
											.getDealingSuspensionRate()
											.toString();
									ReportUtilities
											.saveData(temp,
													"DealingSuspensionRate",
													"199", null,
													reportExecution, user,
													update);
								}

								// <OtherArrangement>
								ComplexOtherArrangementType complexOtherArrangementType = complexInvestorIlliquidAssetArrangementType
										.getOtherArrangement();
								if (complexOtherArrangementType != null) {

									// (200) <OtherArrangementType>
									if (complexOtherArrangementType
											.getOtherArrangementType() != null) {
										temp = complexOtherArrangementType
												.getOtherArrangementType()
												.toString();
										ReportUtilities.saveData(temp,
												"OtherArrangementType", "200",
												null, reportExecution, user,
												update);
									}

									// (201) <OtherArrangementRate>
									if (complexOtherArrangementType
											.getOtherArrangementRate() != null) {
										temp = complexOtherArrangementType
												.getOtherArrangementRate()
												.toString();
										ReportUtilities.saveData(temp,
												"OtherArrangementRate", "201",
												null, reportExecution, user,
												update);
									}

								} // complexOtherArrangementType

								// < / OtherArrangement>

								// (202) <TotalArrangementRate>
								if (complexInvestorIlliquidAssetArrangementType
										.getTotalArrangementRate() != null) {
									temp = complexInvestorIlliquidAssetArrangementType
											.getTotalArrangementRate()
											.toString();
									ReportUtilities
											.saveData(temp,
													"TotalArrangementRate",
													"202", null,
													reportExecution, user,
													update);
								}

							} // end complexInvestorIlliquidAssetArrangementType
								// < / InvestorIlliquidAssetArrangement>

							// <InvestorPreferentialTreatment>
							ComplexInvestorPreferentialTreatmentType complexInvestorPreferentialTreatmentType = complexInvestorArrangementType
									.getInvestorPreferentialTreatment();
							if (complexInvestorPreferentialTreatmentType != null) {

								// (203) <InvestorPreferentialTreatmentFlag>
								temp = Boolean
										.toString(complexInvestorPreferentialTreatmentType
												.isInvestorPreferentialTreatmentFlag());
								ReportUtilities.saveData(temp,
										"InvestorPreferentialTreatmentFlag",
										"203", null, reportExecution, user,
										update);

								// (204)
								// <DisclosureTermsPreferentialTreatmentFlag>
								if (complexInvestorPreferentialTreatmentType
										.isDisclosureTermsPreferentialTreatmentFlag() != null) {
									temp = Boolean
											.toString(complexInvestorPreferentialTreatmentType
													.isDisclosureTermsPreferentialTreatmentFlag());
									ReportUtilities
											.saveData(
													temp,
													"DisclosureTermsPreferentialTreatmentFlag",
													"204", null,
													reportExecution, user,
													update);
								}

								// (205)
								// <LiquidityTermsPreferentialTreatmentFlag>
								if (complexInvestorPreferentialTreatmentType
										.isLiquidityTermsPreferentialTreatmentFlag() != null) {
									temp = Boolean
											.toString(complexInvestorPreferentialTreatmentType
													.isLiquidityTermsPreferentialTreatmentFlag());
									ReportUtilities
											.saveData(
													temp,
													"LiquidityTermsPreferentialTreatmentFlag",
													"205", null,
													reportExecution, user,
													update);
								}

								// (206) <FeeTermsPreferentialTreatmentFlag>
								if (complexInvestorPreferentialTreatmentType
										.isFeeTermsPreferentialTreatmentFlag() != null) {
									temp = Boolean
											.toString(complexInvestorPreferentialTreatmentType
													.isFeeTermsPreferentialTreatmentFlag());
									ReportUtilities
											.saveData(
													temp,
													"FeeTermsPreferentialTreatmentFlag",
													"206", null,
													reportExecution, user,
													update);
								}

								// (207) <OtherTermsPreferentialTreatmentFlag>
								if (complexInvestorPreferentialTreatmentType
										.isOtherTermsPreferentialTreatmentFlag() != null) {
									temp = Boolean
											.toString(complexInvestorPreferentialTreatmentType
													.isOtherTermsPreferentialTreatmentFlag());
									ReportUtilities
											.saveData(
													temp,
													"OtherTermsPreferentialTreatmentFlag",
													"207", null,
													reportExecution, user,
													update);
								}

							} // end complexInvestorPreferentialTreatmentType
								// < / InvestorPreferentialTreatment>

						} // end complexInvestorArrangementType
							// < / InvestorArrangement>

						// /////////////////////////////////////////////////////////////////
						// <InvestorGroups>
						ComplexInvestorGroupsType complexInvestorGroupsType = complexLiquidityRiskProfileType
								.getInvestorGroups();
						if (complexInvestorGroupsType != null) {

							List<ComplexInvestorGroupType> complexInvestorGroupTypeList = complexInvestorGroupsType
									.getInvestorGroup();

							count = 0;
							// <InvestorGroup>
							for (ComplexInvestorGroupType complexInvestorGroupType : complexInvestorGroupTypeList) {
								count++;
								block = Integer.toString(count);

								// (208) <InvestorGroupType>
								if (complexInvestorGroupType
										.getInvestorGroupType() != null) {
									temp = complexInvestorGroupType
											.getInvestorGroupType().value();
									ReportUtilities.saveData(temp,
											"InvestorGroupType", "208", block,
											reportExecution, user, update);
								}

								// (209) <InvestorGroupRate>
								if (complexInvestorGroupType
										.getInvestorGroupRate() != null) {
									temp = complexInvestorGroupType
											.getInvestorGroupRate().toString();
									ReportUtilities.saveData(temp,
											"InvestorGroupRate", "209", block,
											reportExecution, user, update);
								}

							} // < / InvestorGroup>

						} // end complexInvestorGroupsType

						// < / InvestorGroups>

						// /////////////////////////////////////////////////////////////////
						// <FinancingLiquidityProfile>
						ComplexFinancingLiquidityProfileType complexFinancingLiquidityProfileType = complexLiquidityRiskProfileType
								.getFinancingLiquidityProfile();
						if (complexFinancingLiquidityProfileType != null) {

							// (210) <TotalFinancingAmount>
							if (complexFinancingLiquidityProfileType
									.getTotalFinancingAmount() != null) {
								temp = complexFinancingLiquidityProfileType
										.getTotalFinancingAmount().toString();
								ReportUtilities.saveData(temp,
										"TotalFinancingAmount", "210", null,
										reportExecution, user, update);
							}

							// (211) <TotalFinancingInDays0to1Rate>
							if (complexFinancingLiquidityProfileType
									.getTotalFinancingInDays0To1Rate() != null) {
								temp = complexFinancingLiquidityProfileType
										.getTotalFinancingInDays0To1Rate()
										.toString();
								ReportUtilities.saveData(temp,
										"TotalFinancingInDays0to1Rate", "211",
										null, reportExecution, user, update);
							}

							// (212) <TotalFinancingInDays2to7Rate>
							if (complexFinancingLiquidityProfileType
									.getTotalFinancingInDays2To7Rate() != null) {
								temp = complexFinancingLiquidityProfileType
										.getTotalFinancingInDays2To7Rate()
										.toString();
								ReportUtilities.saveData(temp,
										"TotalFinancingInDays2to7Rate", "212",
										null, reportExecution, user, update);
							}

							// (213) <TotalFinancingInDays8to30Rate>
							if (complexFinancingLiquidityProfileType
									.getTotalFinancingInDays8To30Rate() != null) {
								temp = complexFinancingLiquidityProfileType
										.getTotalFinancingInDays8To30Rate()
										.toString();
								ReportUtilities.saveData(temp,
										"TotalFinancingInDays8to30Rate", "213",
										null, reportExecution, user, update);
							}

							// (214) <TotalFinancingInDays31to90Rate>
							if (complexFinancingLiquidityProfileType
									.getTotalFinancingInDays31To90Rate() != null) {
								temp = complexFinancingLiquidityProfileType
										.getTotalFinancingInDays31To90Rate()
										.toString();
								ReportUtilities.saveData(temp,
										"TotalFinancingInDays31to90Rate",
										"214", null, reportExecution, user,
										update);
							}

							// (215) <TotalFinancingInDays91to180Rate>
							if (complexFinancingLiquidityProfileType
									.getTotalFinancingInDays91To180Rate() != null) {
								temp = complexFinancingLiquidityProfileType
										.getTotalFinancingInDays91To180Rate()
										.toString();
								ReportUtilities.saveData(temp,
										"TotalFinancingInDays91to180Rate",
										"215", null, reportExecution, user,
										update);
							}

							// (216) <TotalFinancingInDays181to365Rate>
							if (complexFinancingLiquidityProfileType
									.getTotalFinancingInDays181To365Rate() != null) {
								temp = complexFinancingLiquidityProfileType
										.getTotalFinancingInDays181To365Rate()
										.toString();
								ReportUtilities.saveData(temp,
										"TotalFinancingInDays181to365Rate",
										"216", null, reportExecution, user,
										update);
							}

							// (217) <TotalFinancingInDays365MoreRate>
							if (complexFinancingLiquidityProfileType
									.getTotalFinancingInDays365MoreRate() != null) {
								temp = complexFinancingLiquidityProfileType
										.getTotalFinancingInDays365MoreRate()
										.toString();
								ReportUtilities.saveData(temp,
										"TotalFinancingInDays365MoreRate",
										"217", null, reportExecution, user,
										update);
							}

							// < / FinancingLiquidityProfile>
						} // end complexFinancingLiquidityProfileType
							// < / LiquidityRiskProfile>

					} // end complexLiquidityRiskProfileType

					// /////////////////////////////////////////////////////////////////
					// <OperationalRisk>
					ComplexOperationalRiskType complexOperationalRiskType = complexRiskProfileType
							.getOperationalRisk();
					if (complexOperationalRiskType != null) {

						// (218) <TotalOpenPositions>
						if (complexOperationalRiskType.getTotalOpenPositions() != null) {
							temp = complexOperationalRiskType
									.getTotalOpenPositions().toString();
							ReportUtilities.saveData(temp,
									"TotalOpenPositions", "218", null,
									reportExecution, user, update);
						}

						// /////////////////////////////////////////////////////////////////
						// <HistoricalRiskProfile>
						ComplexHistoricalRiskProfileType complexHistoricalRiskProfileType = complexOperationalRiskType
								.getHistoricalRiskProfile();
						if (complexHistoricalRiskProfileType != null) {

							// /////////////////////////////////////////////////////////////////
							// <GrossInvestmentReturnsRate>
							ComplexSignedRateMonthPeriodType complexGrossSignedRateMonthPeriodType = complexHistoricalRiskProfileType
									.getGrossInvestmentReturnsRate();
							if (complexGrossSignedRateMonthPeriodType != null) {

								// (219) <RateJanuary>
								if (complexGrossSignedRateMonthPeriodType
										.getRateJanuary() != null) {
									temp = complexGrossSignedRateMonthPeriodType
											.getRateJanuary().toString();
									ReportUtilities.saveData(temp,
											"RateJanuary", "219", null,
											reportExecution, user, update);
								}

								// (220) <RateFebruary>
								if (complexGrossSignedRateMonthPeriodType
										.getRateFebruary() != null) {
									temp = complexGrossSignedRateMonthPeriodType
											.getRateFebruary().toString();
									ReportUtilities.saveData(temp,
											"RateFebruary", "220", null,
											reportExecution, user, update);
								}

								// (221) <RateMarch>
								if (complexGrossSignedRateMonthPeriodType
										.getRateMarch() != null) {
									temp = complexGrossSignedRateMonthPeriodType
											.getRateMarch().toString();
									ReportUtilities.saveData(temp, "RateMarch",
											"221", null, reportExecution, user,
											update);
								}

								// (222) <RateApril>
								if (complexGrossSignedRateMonthPeriodType
										.getRateApril() != null) {
									temp = complexGrossSignedRateMonthPeriodType
											.getRateApril().toString();
									ReportUtilities.saveData(temp, "RateApril",
											"222", null, reportExecution, user,
											update);
								}

								// (223) <RateMay>
								if (complexGrossSignedRateMonthPeriodType
										.getRateMay() != null) {
									temp = complexGrossSignedRateMonthPeriodType
											.getRateMay().toString();
									ReportUtilities.saveData(temp, "RateMay",
											"223", null, reportExecution, user,
											update);
								}

								// (224) <RateJune>
								if (complexGrossSignedRateMonthPeriodType
										.getRateJune() != null) {
									temp = complexGrossSignedRateMonthPeriodType
											.getRateJune().toString();
									ReportUtilities.saveData(temp, "RateJune",
											"224", null, reportExecution, user,
											update);
								}

								// (225) <RateJuly>
								if (complexGrossSignedRateMonthPeriodType
										.getRateJuly() != null) {
									temp = complexGrossSignedRateMonthPeriodType
											.getRateJuly().toString();
									ReportUtilities.saveData(temp, "RateJuly",
											"225", null, reportExecution, user,
											update);
								}

								// (226) <RateAugust>
								if (complexGrossSignedRateMonthPeriodType
										.getRateAugust() != null) {
									temp = complexGrossSignedRateMonthPeriodType
											.getRateAugust().toString();
									ReportUtilities.saveData(temp,
											"RateAugust", "226", null,
											reportExecution, user, update);
								}

								// (227) <RateSeptember>
								if (complexGrossSignedRateMonthPeriodType
										.getRateSeptember() != null) {
									temp = complexGrossSignedRateMonthPeriodType
											.getRateSeptember().toString();
									ReportUtilities.saveData(temp,
											"RateSeptember", "227", null,
											reportExecution, user, update);
								}

								// (228) <RateOctober>
								if (complexGrossSignedRateMonthPeriodType
										.getRateOctober() != null) {
									temp = complexGrossSignedRateMonthPeriodType
											.getRateOctober().toString();
									ReportUtilities.saveData(temp,
											"RateOctober", "228", null,
											reportExecution, user, update);
								}

								// (229) <RateNovember>
								if (complexGrossSignedRateMonthPeriodType
										.getRateNovember() != null) {
									temp = complexGrossSignedRateMonthPeriodType
											.getRateNovember().toString();
									ReportUtilities.saveData(temp,
											"RateNovember", "229", null,
											reportExecution, user, update);
								}

								// (230) <RateDecember>
								if (complexGrossSignedRateMonthPeriodType
										.getRateDecember() != null) {
									temp = complexGrossSignedRateMonthPeriodType
											.getRateDecember().toString();
									ReportUtilities.saveData(temp,
											"RateDecember", "230", null,
											reportExecution, user, update);
								}

							} // end complexGrossSignedRateMonthPeriodType
								// < / GrossInvestmentReturnsRate>

							// /////////////////////////////////////////////////////////////////
							// <NetInvestmentReturnsRate>
							ComplexSignedRateMonthPeriodType complexNetSignedRateMonthPeriodType = complexHistoricalRiskProfileType
									.getNetInvestmentReturnsRate();
							if (complexNetSignedRateMonthPeriodType != null) {

								// (231) <RateJanuary>
								if (complexNetSignedRateMonthPeriodType
										.getRateJanuary() != null) {
									temp = complexNetSignedRateMonthPeriodType
											.getRateJanuary().toString();
									ReportUtilities.saveData(temp,
											"RateJanuary", "231", null,
											reportExecution, user, update);
								}

								// (232) <RateFebruary>
								if (complexNetSignedRateMonthPeriodType
										.getRateFebruary() != null) {
									temp = complexNetSignedRateMonthPeriodType
											.getRateFebruary().toString();
									ReportUtilities.saveData(temp,
											"RateFebruary", "232", null,
											reportExecution, user, update);
								}

								// (233) <RateMarch>
								if (complexNetSignedRateMonthPeriodType
										.getRateMarch() != null) {
									temp = complexNetSignedRateMonthPeriodType
											.getRateMarch().toString();
									ReportUtilities.saveData(temp, "RateMarch",
											"233", null, reportExecution, user,
											update);
								}

								// (234) <RateApril>
								if (complexNetSignedRateMonthPeriodType
										.getRateApril() != null) {
									temp = complexNetSignedRateMonthPeriodType
											.getRateApril().toString();
									ReportUtilities.saveData(temp, "RateApril",
											"234", null, reportExecution, user,
											update);
								}

								// (235) <RateMay>
								if (complexNetSignedRateMonthPeriodType
										.getRateMay() != null) {
									temp = complexNetSignedRateMonthPeriodType
											.getRateMay().toString();
									ReportUtilities.saveData(temp, "RateMay",
											"235", null, reportExecution, user,
											update);
								}

								// (236) <RateJune>
								if (complexNetSignedRateMonthPeriodType
										.getRateJune() != null) {
									temp = complexNetSignedRateMonthPeriodType
											.getRateJune().toString();
									ReportUtilities.saveData(temp, "RateJune",
											"236", null, reportExecution, user,
											update);
								}

								// (237) <RateJuly>
								if (complexNetSignedRateMonthPeriodType
										.getRateJuly() != null) {
									temp = complexNetSignedRateMonthPeriodType
											.getRateJuly().toString();
									ReportUtilities.saveData(temp, "RateJuly",
											"237", null, reportExecution, user,
											update);
								}

								// (238) <RateAugust>
								if (complexNetSignedRateMonthPeriodType
										.getRateAugust() != null) {
									temp = complexNetSignedRateMonthPeriodType
											.getRateAugust().toString();
									ReportUtilities.saveData(temp,
											"RateAugust", "238", null,
											reportExecution, user, update);
								}

								// (239) <RateSeptember>
								if (complexNetSignedRateMonthPeriodType
										.getRateSeptember() != null) {
									temp = complexNetSignedRateMonthPeriodType
											.getRateSeptember().toString();
									ReportUtilities.saveData(temp,
											"RateSeptember", "239", null,
											reportExecution, user, update);
								}

								// (240) <RateOctober>
								if (complexNetSignedRateMonthPeriodType
										.getRateOctober() != null) {
									temp = complexNetSignedRateMonthPeriodType
											.getRateOctober().toString();
									ReportUtilities.saveData(temp,
											"RateOctober", "240", null,
											reportExecution, user, update);
								}

								// (241) <RateNovember>
								if (complexNetSignedRateMonthPeriodType
										.getRateNovember() != null) {
									temp = complexNetSignedRateMonthPeriodType
											.getRateNovember().toString();
									ReportUtilities.saveData(temp,
											"RateNovember", "241", null,
											reportExecution, user, update);
								}

								// (242) <RateDecember>
								if (complexNetSignedRateMonthPeriodType
										.getRateDecember() != null) {
									temp = complexNetSignedRateMonthPeriodType
											.getRateDecember().toString();
									ReportUtilities.saveData(temp,
											"RateDecember", "242", null,
											reportExecution, user, update);
								}

							} // end complexNetSignedRateMonthPeriodType
								// < / NetInvestmentReturnsRate>

							// /////////////////////////////////////////////////////////////////
							// <NAVChangeRate>
							ComplexSignedRateMonthPeriodType complexNAVSignedRateMonthPeriodType = complexHistoricalRiskProfileType
									.getNAVChangeRate();
							if (complexNAVSignedRateMonthPeriodType != null) {

								// (243) <RateJanuary>
								if (complexNAVSignedRateMonthPeriodType
										.getRateJanuary() != null) {
									temp = complexNAVSignedRateMonthPeriodType
											.getRateJanuary().toString();
									ReportUtilities.saveData(temp,
											"RateJanuary", "243", null,
											reportExecution, user, update);
								}

								// (244) <RateFebruary>
								if (complexNAVSignedRateMonthPeriodType
										.getRateFebruary() != null) {
									temp = complexNAVSignedRateMonthPeriodType
											.getRateFebruary().toString();
									ReportUtilities.saveData(temp,
											"RateFebruary", "244", null,
											reportExecution, user, update);
								}

								// (245) <RateMarch>
								if (complexNAVSignedRateMonthPeriodType
										.getRateMarch() != null) {
									temp = complexNAVSignedRateMonthPeriodType
											.getRateMarch().toString();
									ReportUtilities.saveData(temp, "RateMarch",
											"245", null, reportExecution, user,
											update);
								}

								// (246) <RateApril>
								if (complexNAVSignedRateMonthPeriodType
										.getRateApril() != null) {
									temp = complexNAVSignedRateMonthPeriodType
											.getRateApril().toString();
									ReportUtilities.saveData(temp, "RateApril",
											"246", null, reportExecution, user,
											update);
								}

								// (247) <RateMay>
								if (complexNAVSignedRateMonthPeriodType
										.getRateMay() != null) {
									temp = complexNAVSignedRateMonthPeriodType
											.getRateMay().toString();
									ReportUtilities.saveData(temp, "RateMay",
											"247", null, reportExecution, user,
											update);
								}

								// (248) <RateJune>
								if (complexNAVSignedRateMonthPeriodType
										.getRateJune() != null) {
									temp = complexNAVSignedRateMonthPeriodType
											.getRateJune().toString();
									ReportUtilities.saveData(temp, "RateJune",
											"248", null, reportExecution, user,
											update);
								}

								// (249) <RateJuly>
								if (complexNAVSignedRateMonthPeriodType
										.getRateJuly() != null) {
									temp = complexNAVSignedRateMonthPeriodType
											.getRateJuly().toString();
									ReportUtilities.saveData(temp, "RateJuly",
											"249", null, reportExecution, user,
											update);
								}

								// (250) <RateAugust>
								if (complexNAVSignedRateMonthPeriodType
										.getRateAugust() != null) {
									temp = complexNAVSignedRateMonthPeriodType
											.getRateAugust().toString();
									ReportUtilities.saveData(temp,
											"RateAugust", "250", null,
											reportExecution, user, update);
								}

								// (251) <RateSeptember>
								if (complexNAVSignedRateMonthPeriodType
										.getRateSeptember() != null) {
									temp = complexNAVSignedRateMonthPeriodType
											.getRateSeptember().toString();
									ReportUtilities.saveData(temp,
											"RateSeptember", "251", null,
											reportExecution, user, update);
								}

								// (252) <RateOctober>
								if (complexNAVSignedRateMonthPeriodType
										.getRateOctober() != null) {
									temp = complexNAVSignedRateMonthPeriodType
											.getRateOctober().toString();
									ReportUtilities.saveData(temp,
											"RateOctober", "252", null,
											reportExecution, user, update);
								}

								// (253) <RateNovember>
								if (complexNAVSignedRateMonthPeriodType
										.getRateNovember() != null) {
									temp = complexNAVSignedRateMonthPeriodType
											.getRateNovember().toString();
									ReportUtilities.saveData(temp,
											"RateNovember", "253", null,
											reportExecution, user, update);
								}

								// (254) <RateDecember>
								if (complexNAVSignedRateMonthPeriodType
										.getRateDecember() != null) {
									temp = complexNAVSignedRateMonthPeriodType
											.getRateDecember().toString();
									ReportUtilities.saveData(temp,
											"RateDecember", "254", null,
											reportExecution, user, update);
								}

							} // end complexNAVSignedRateMonthPeriodType
								// < / NAVChangeRate>

							// /////////////////////////////////////////////////////////////////
							// <Subscription>
							ComplexQuantityMonthPeriodType complexSubscriptionQuantityMonthPeriodType = complexHistoricalRiskProfileType
									.getSubscription();
							if (complexSubscriptionQuantityMonthPeriodType != null) {

								// (255) <QuantityJanuary>
								if (complexSubscriptionQuantityMonthPeriodType
										.getQuantityJanuary() != null) {
									temp = complexSubscriptionQuantityMonthPeriodType
											.getQuantityJanuary().toString();
									ReportUtilities.saveData(temp,
											"QuantityJanuary", "255", null,
											reportExecution, user, update);
								}

								// (256) <QuantityFebruary>
								if (complexSubscriptionQuantityMonthPeriodType
										.getQuantityFebruary() != null) {
									temp = complexSubscriptionQuantityMonthPeriodType
											.getQuantityFebruary().toString();
									ReportUtilities.saveData(temp,
											"QuantityFebruary", "256", null,
											reportExecution, user, update);
								}

								// (257) <QuantityMarch>
								if (complexSubscriptionQuantityMonthPeriodType
										.getQuantityMarch() != null) {
									temp = complexSubscriptionQuantityMonthPeriodType
											.getQuantityMarch().toString();
									ReportUtilities.saveData(temp,
											"QuantityMarch", "257", null,
											reportExecution, user, update);
								}

								// (258) <QuantityApril>
								if (complexSubscriptionQuantityMonthPeriodType
										.getQuantityApril() != null) {
									temp = complexSubscriptionQuantityMonthPeriodType
											.getQuantityApril().toString();
									ReportUtilities.saveData(temp,
											"QuantityApril", "258", null,
											reportExecution, user, update);
								}

								// (259) <QuantityMay>
								if (complexSubscriptionQuantityMonthPeriodType
										.getQuantityMay() != null) {
									temp = complexSubscriptionQuantityMonthPeriodType
											.getQuantityMay().toString();
									ReportUtilities.saveData(temp,
											"QuantityMay", "259", null,
											reportExecution, user, update);
								}

								// (260) <QuantityJune>
								if (complexSubscriptionQuantityMonthPeriodType
										.getQuantityJune() != null) {
									temp = complexSubscriptionQuantityMonthPeriodType
											.getQuantityJune().toString();
									ReportUtilities.saveData(temp,
											"QuantityJune", "260", null,
											reportExecution, user, update);
								}

								// (261) <QuantityJuly>
								if (complexSubscriptionQuantityMonthPeriodType
										.getQuantityJuly() != null) {
									temp = complexSubscriptionQuantityMonthPeriodType
											.getQuantityJuly().toString();
									ReportUtilities.saveData(temp,
											"QuantityJuly", "261", null,
											reportExecution, user, update);
								}

								// (262) <QuantityAugust>
								if (complexSubscriptionQuantityMonthPeriodType
										.getQuantityAugust() != null) {
									temp = complexSubscriptionQuantityMonthPeriodType
											.getQuantityAugust().toString();
									ReportUtilities.saveData(temp,
											"QuantityAugust", "262", null,
											reportExecution, user, update);
								}

								// (263) <QuantitySeptember>
								if (complexSubscriptionQuantityMonthPeriodType
										.getQuantitySeptember() != null) {
									temp = complexSubscriptionQuantityMonthPeriodType
											.getQuantitySeptember().toString();
									ReportUtilities.saveData(temp,
											"QuantitySeptember", "263", null,
											reportExecution, user, update);
								}

								// (264) <QuantityOctober>
								if (complexSubscriptionQuantityMonthPeriodType
										.getQuantityOctober() != null) {
									temp = complexSubscriptionQuantityMonthPeriodType
											.getQuantityOctober().toString();
									ReportUtilities.saveData(temp,
											"QuantityOctober", "264", null,
											reportExecution, user, update);
								}

								// (265) <QuantityNovember>
								if (complexSubscriptionQuantityMonthPeriodType
										.getQuantityNovember() != null) {
									temp = complexSubscriptionQuantityMonthPeriodType
											.getQuantityNovember().toString();
									ReportUtilities.saveData(temp,
											"QuantityNovember", "265", null,
											reportExecution, user, update);
								}

								// (266) <QuantityDecember>
								if (complexSubscriptionQuantityMonthPeriodType
										.getQuantityDecember() != null) {
									temp = complexSubscriptionQuantityMonthPeriodType
											.getQuantityDecember().toString();
									ReportUtilities.saveData(temp,
											"QuantityDecember", "266", null,
											reportExecution, user, update);
								}
							} // end complexSubscriptionQuantityMonthPeriodType
								// < / Subscription>

							// /////////////////////////////////////////////////////////////////
							// <Redemption>
							ComplexQuantityMonthPeriodType complexRedemptionQuantityMonthPeriodType = complexHistoricalRiskProfileType
									.getRedemption();
							if (complexRedemptionQuantityMonthPeriodType != null) {

								// (267) <QuantityJanuary>
								if (complexRedemptionQuantityMonthPeriodType
										.getQuantityJanuary() != null) {
									temp = complexRedemptionQuantityMonthPeriodType
											.getQuantityJanuary().toString();
									ReportUtilities.saveData(temp,
											"QuantityJanuary", "267", null,
											reportExecution, user, update);
								}

								// (268) <QuantityFebruary>
								if (complexRedemptionQuantityMonthPeriodType
										.getQuantityFebruary() != null) {
									temp = complexRedemptionQuantityMonthPeriodType
											.getQuantityFebruary().toString();
									ReportUtilities.saveData(temp,
											"QuantityFebruary", "268", null,
											reportExecution, user, update);
								}

								// (269) <QuantityMarch>
								if (complexRedemptionQuantityMonthPeriodType
										.getQuantityMarch() != null) {
									temp = complexRedemptionQuantityMonthPeriodType
											.getQuantityMarch().toString();
									ReportUtilities.saveData(temp,
											"QuantityMarch", "269", null,
											reportExecution, user, update);
								}

								// (270) <QuantityApril>
								if (complexRedemptionQuantityMonthPeriodType
										.getQuantityApril() != null) {
									temp = complexRedemptionQuantityMonthPeriodType
											.getQuantityApril().toString();
									ReportUtilities.saveData(temp,
											"QuantityApril", "270", null,
											reportExecution, user, update);
								}

								// (271) <QuantityMay>
								if (complexRedemptionQuantityMonthPeriodType
										.getQuantityMay() != null) {
									temp = complexRedemptionQuantityMonthPeriodType
											.getQuantityMay().toString();
									ReportUtilities.saveData(temp,
											"QuantityMay", "271", null,
											reportExecution, user, update);
								}

								// (272) <QuantityJune>
								if (complexRedemptionQuantityMonthPeriodType
										.getQuantityJune() != null) {
									temp = complexRedemptionQuantityMonthPeriodType
											.getQuantityJune().toString();
									ReportUtilities.saveData(temp,
											"QuantityJune", "272", null,
											reportExecution, user, update);
								}

								// (273) <QuantityJuly>
								if (complexRedemptionQuantityMonthPeriodType
										.getQuantityJuly() != null) {
									temp = complexRedemptionQuantityMonthPeriodType
											.getQuantityJuly().toString();
									ReportUtilities.saveData(temp,
											"QuantityJuly", "273", null,
											reportExecution, user, update);
								}

								// (274) <QuantityAugust>
								if (complexRedemptionQuantityMonthPeriodType
										.getQuantityAugust() != null) {
									temp = complexRedemptionQuantityMonthPeriodType
											.getQuantityAugust().toString();
									ReportUtilities.saveData(temp,
											"QuantityAugust", "274", null,
											reportExecution, user, update);
								}

								// (275) <QuantitySeptember>
								if (complexRedemptionQuantityMonthPeriodType
										.getQuantitySeptember() != null) {
									temp = complexRedemptionQuantityMonthPeriodType
											.getQuantitySeptember().toString();
									ReportUtilities.saveData(temp,
											"QuantitySeptember", "275", null,
											reportExecution, user, update);
								}

								// (276) <QuantityOctober>
								if (complexRedemptionQuantityMonthPeriodType
										.getQuantityOctober() != null) {
									temp = complexRedemptionQuantityMonthPeriodType
											.getQuantityOctober().toString();
									ReportUtilities.saveData(temp,
											"QuantityOctober", "276", null,
											reportExecution, user, update);
								}

								// (277) <QuantityNovember>
								if (complexRedemptionQuantityMonthPeriodType
										.getQuantityNovember() != null) {
									temp = complexRedemptionQuantityMonthPeriodType
											.getQuantityNovember().toString();
									ReportUtilities.saveData(temp,
											"QuantityNovember", "277", null,
											reportExecution, user, update);
								}

								// (278) <QuantityDecember>
								if (complexRedemptionQuantityMonthPeriodType
										.getQuantityDecember() != null) {
									temp = complexRedemptionQuantityMonthPeriodType
											.getQuantityDecember().toString();
									ReportUtilities.saveData(temp,
											"QuantityDecember", "278", null,
											reportExecution, user, update);
								}

							} // end complexRedemptionQuantityMonthPeriodType
								// < / Redemption>

						} // end complexHistoricalRiskProfileType

						// < / HistoricalRiskProfile>
						// < / OperationalRisk>

					} // end complexOperationalRiskType

				} // end complexRiskProfileType

				ComplexStressTestsType complexStressTestsType = complexAIFIndividualInfoType
						.getStressTests();
				// <StressTests>
				if (complexStressTestsType != null) {

					// (279) <StressTestsResultArticle15>
					if (complexStressTestsType.getStressTestsResultArticle15() != null) {
						temp = complexStressTestsType
								.getStressTestsResultArticle15().toString();
						ReportUtilities.saveData(temp,
								"StressTestsResultArticle15", "279", null,
								reportExecution, user, update);
					}

					// (280) <StressTestsResultArticle16>
					if (complexStressTestsType.getStressTestsResultArticle16() != null) {
						temp = complexStressTestsType
								.getStressTestsResultArticle16().toString();
						ReportUtilities.saveData(temp,
								"StressTestsResultArticle16", "280", null,
								reportExecution, user, update);
					}

				} // end complexStressTestsType

			} // end complexAIFIndividualInfoType

			// < / AIFIndividualInfo>

			// /////////////////////////////////////////////////////////////////
			// <AIFLeverageInfo>
			ComplexAIFLeverageInfoType complexAIFLeverageInfoType = complexAIFCompleteDescriptionType
					.getAIFLeverageInfo();
			if (complexAIFLeverageInfoType != null) {

				// <AIFLeverageArticle24-2>
				ComplexAIFLeverageArticle242Type complexAIFLeverageArticle242Type = complexAIFLeverageInfoType
						.getAIFLeverageArticle242();
				if (complexAIFLeverageArticle242Type != null) {

					// (281) <AllCounterpartyCollateralRehypothecationFlag>
					temp = Boolean.toString(complexAIFLeverageArticle242Type
							.isAllCounterpartyCollateralRehypothecationFlag());
					ReportUtilities.saveData(temp,
							"AllCounterpartyCollateralRehypothecationFlag",
							"281", null, reportExecution, user, update);

					// (282) <AllCounterpartyCollateralRehypothecatedRate>
					if (complexAIFLeverageArticle242Type
							.getAllCounterpartyCollateralRehypothecatedRate() != null) {
						temp = complexAIFLeverageArticle242Type
								.getAllCounterpartyCollateralRehypothecatedRate()
								.toString();
						ReportUtilities.saveData(temp,
								"AllCounterpartyCollateralRehypothecatedRate",
								"282", null, reportExecution, user, update);
					}

					// /////////////////////////////////////////////////////////////////
					// <SecuritiesCashBorrowing>
					ComplexSecuritiesCashBorrowingType complexSecuritiesCashBorrowingType = complexAIFLeverageArticle242Type
							.getSecuritiesCashBorrowing();
					if (complexSecuritiesCashBorrowingType != null) {

						// (283) <UnsecuredBorrowingAmount>
						if (complexSecuritiesCashBorrowingType
								.getUnsecuredBorrowingAmount() != null) {
							temp = complexSecuritiesCashBorrowingType
									.getUnsecuredBorrowingAmount().toString();
							ReportUtilities.saveData(temp,
									"UnsecuredBorrowingAmount", "283", null,
									reportExecution, user, update);
						}

						// (284) <SecuredBorrowingPrimeBrokerageAmount>
						if (complexSecuritiesCashBorrowingType
								.getSecuredBorrowingPrimeBrokerageAmount() != null) {
							temp = complexSecuritiesCashBorrowingType
									.getSecuredBorrowingPrimeBrokerageAmount()
									.toString();
							ReportUtilities.saveData(temp,
									"SecuredBorrowingPrimeBrokerageAmount",
									"284", null, reportExecution, user, update);
						}

						// (285) <SecuredBorrowingReverseRepoAmount>
						if (complexSecuritiesCashBorrowingType
								.getSecuredBorrowingReverseRepoAmount() != null) {
							temp = complexSecuritiesCashBorrowingType
									.getSecuredBorrowingReverseRepoAmount()
									.toString();
							ReportUtilities.saveData(temp,
									"SecuredBorrowingReverseRepoAmount", "285",
									null, reportExecution, user, update);
						}

						// (286) <SecuredBorrowingOtherAmount>
						if (complexSecuritiesCashBorrowingType
								.getSecuredBorrowingOtherAmount() != null) {
							temp = complexSecuritiesCashBorrowingType
									.getSecuredBorrowingOtherAmount()
									.toString();
							ReportUtilities.saveData(temp,
									"SecuredBorrowingOtherAmount", "286", null,
									reportExecution, user, update);
						}
					} // end complexSecuritiesCashBorrowingType
						// < / SecuritiesCashBorrowing>

					// /////////////////////////////////////////////////////////////////
					// <FinancialInstrumentBorrowing>
					ComplexFinancialInstrumentBorrowingType complexFinancialInstrumentBorrowingType = complexAIFLeverageArticle242Type
							.getFinancialInstrumentBorrowing();
					if (complexFinancialInstrumentBorrowingType != null) {

						// (287) <ExchangedTradedDerivativesExposureValue>
						if (complexFinancialInstrumentBorrowingType
								.getExchangedTradedDerivativesExposureValue() != null) {
							temp = complexFinancialInstrumentBorrowingType
									.getExchangedTradedDerivativesExposureValue()
									.toString();
							ReportUtilities.saveData(temp,
									"ExchangedTradedDerivativesExposureValue",
									"287", null, reportExecution, user, update);
						}

						// (288) <OTCDerivativesAmount>
						if (complexFinancialInstrumentBorrowingType
								.getExchangedTradedDerivativesExposureValue() != null) {
							temp = complexFinancialInstrumentBorrowingType
									.getExchangedTradedDerivativesExposureValue()
									.toString();
							ReportUtilities.saveData(temp,
									"OTCDerivativesAmount", "288", null,
									reportExecution, user, update);
						}

					} // end complexFinancialInstrumentBorrowingType
						// < / FinancialInstrumentBorrowing>

					// (289) <ShortPositionBorrowedSecuritiesValue>
					if (complexAIFLeverageArticle242Type
							.getShortPositionBorrowedSecuritiesValue() != null) {
						temp = complexAIFLeverageArticle242Type
								.getShortPositionBorrowedSecuritiesValue()
								.toString();
						ReportUtilities.saveData(temp,
								"ShortPositionBorrowedSecuritiesValue", "289",
								null, reportExecution, user, update);
					}

					// /////////////////////////////////////////////////////////////////
					// <ControlledStructures>
					ComplexControlledStructuresType complexControlledStructuresType = complexAIFLeverageArticle242Type
							.getControlledStructures();
					if (complexControlledStructuresType != null) {

						List<ComplexControlledStructureType> complexControlledStructureTypeList = complexControlledStructuresType
								.getControlledStructure();

						count = 0;
						// <ControlledStructure>
						for (ComplexControlledStructureType complexControlledStructureType : complexControlledStructureTypeList) {
							count++;
							block = Integer.toString(count);

							// <ControlledStructureIdentification>
							ComplexEntityIdentificationType complexEntityIdentificationType = complexControlledStructureType
									.getControlledStructureIdentification();
							if (complexEntityIdentificationType != null) {

								// (290) <EntityName>
								if (complexEntityIdentificationType
										.getEntityName() != null) {
									temp = complexEntityIdentificationType
											.getEntityName().toString();
									ReportUtilities.saveData(temp,
											"EntityName", "290", block,
											reportExecution, user, update);
								}

								// (291) <EntityIdentificationLEI>
								if (complexEntityIdentificationType
										.getEntityIdentificationLEI() != null) {
									temp = complexEntityIdentificationType
											.getEntityIdentificationLEI()
											.toString();
									ReportUtilities.saveData(temp,
											"EntityIdentificationLEI", "291",
											block, reportExecution, user,
											update);
								}

								// (292) <EntityIdentificationBIC>
								if (complexEntityIdentificationType
										.getEntityIdentificationBIC() != null) {
									temp = complexEntityIdentificationType
											.getEntityIdentificationBIC()
											.toString();
									ReportUtilities.saveData(temp,
											"EntityIdentificationBIC", "292",
											block, reportExecution, user,
											update);
								}

							} // end complexEntityIdentificationType

							// (293) <ControlledStructureExposureValue>
							if (complexControlledStructureType
									.getControlledStructureExposureValue() != null) {
								temp = complexControlledStructureType
										.getControlledStructureExposureValue()
										.toString();
								ReportUtilities.saveData(temp,
										"ControlledStructureExposureValue",
										"293", block, reportExecution, user,
										update);
							}

							// < / ControlledStructureIdentification>

						} // < / ControlledStructure>

					} // end complexControlledStructuresType

					// < / ControlledStructures>

					// /////////////////////////////////////////////////////////////////
					// <LeverageAIF>
					ComplexLeverageAIFType complexLeverageAIFType = complexAIFLeverageArticle242Type
							.getLeverageAIF();
					if (complexLeverageAIFType != null) {

						// (294) <GrossMethodRate>
						if (complexLeverageAIFType.getGrossMethodRate() != null) {
							temp = complexLeverageAIFType.getGrossMethodRate()
									.toString();
							ReportUtilities.saveData(temp, "GrossMethodRate",
									"294", null, reportExecution, user, update);
						}

						// (295) <CommitmentMethodRate>
						if (complexLeverageAIFType.getCommitmentMethodRate() != null) {
							temp = complexLeverageAIFType
									.getCommitmentMethodRate().toString();
							ReportUtilities.saveData(temp,
									"CommitmentMethodRate", "295", null,
									reportExecution, user, update);
						}

						// < / LeverageAIF>
					} // end complexLeverageAIFType

					// < / AIFLeverageArticle24-2>

				} // end complexAIFLeverageArticle242Type

				// <AIFLeverageArticle24-4>
				ComplexAIFLeverageArticle244Type complexAIFLeverageArticle244Type = complexAIFLeverageInfoType
						.getAIFLeverageArticle244();
				if (complexAIFLeverageArticle244Type != null) {

					List<ComplexBorrowingSourceType> complexBorrowingSourceTypeList = complexAIFLeverageArticle244Type
							.getBorrowingSource();

					count = 0;
					// <BorrowingSource>
					for (ComplexBorrowingSourceType complexBorrowingSourceType : complexBorrowingSourceTypeList) {
						count++;
						block = Integer.toString(count);

						// (296) <Ranking>
						if (complexBorrowingSourceType.getRanking() != null) {
							temp = complexBorrowingSourceType.getRanking()
									.toString();
							ReportUtilities.saveData(temp, "Ranking", "296",
									block, reportExecution, user, update);
						}

						// (297) <BorrowingSourceFlag>
						temp = Boolean.toString(complexBorrowingSourceType
								.isBorrowingSourceFlag());
						ReportUtilities.saveData(temp, "BorrowingSourceFlag",
								"297", block, reportExecution, user, update);

						// <SourceIdentification>
						ComplexEntityIdentificationType complexEntityIdentificationType = complexBorrowingSourceType
								.getSourceIdentification();
						if (complexEntityIdentificationType != null) {

							// (298) <EntityName>
							if (complexEntityIdentificationType.getEntityName() != null) {
								temp = complexEntityIdentificationType
										.getEntityName().toString();
								ReportUtilities.saveData(temp, "EntityName",
										"298", block, reportExecution, user,
										update);
							}

							// (299) <EntityIdentificationLEI>
							if (complexEntityIdentificationType
									.getEntityIdentificationLEI() != null) {
								temp = complexEntityIdentificationType
										.getEntityIdentificationLEI()
										.toString();
								ReportUtilities.saveData(temp,
										"EntityIdentificationLEI", "299",
										block, reportExecution, user, update);
							}

							// (300) <EntityIdentificationBIC>
							if (complexEntityIdentificationType
									.getEntityIdentificationBIC() != null) {
								temp = complexEntityIdentificationType
										.getEntityIdentificationBIC()
										.toString();
								ReportUtilities.saveData(temp,
										"EntityIdentificationBIC", "300",
										block, reportExecution, user, update);
							}

						} // end complexEntityIdentificationType
							// < / SourceIdentification>

						// (301) <LeverageAmount>
						if (complexBorrowingSourceType.getLeverageAmount() != null) {
							temp = complexBorrowingSourceType
									.getLeverageAmount().toString();
							ReportUtilities
									.saveData(temp, "LeverageAmount", "301",
											block, reportExecution, user,
											update);
						}

					} // < / BorrowingSource>

					// < / AIFLeverageArticle24-4>

				} // end complexAIFLeverageArticle244Type

			} // end complexAIFLeverageInfoType

			// <AIFLeverageInfo>

		} // end complexAIFCompleteDescriptionType

		// /////////////////////////////////////////////////////////////////
		// <CancellationAIFMRecordInfo>
		if (complexCancellationAIFRecordInfoType != null) {

			// (303) <CancelledAIFNationalCode>
			if (complexCancellationAIFRecordInfoType
					.getCancelledAIFNationalCode() != null) {
				temp = complexCancellationAIFRecordInfoType
						.getCancelledAIFNationalCode().toString();
				ReportUtilities.saveData(temp, "CancelledAIFNationalCode",
						"303", null, reportExecution, user, update);
			}

			// (304) <CancelledAIFMNationalCode>
			if (complexCancellationAIFRecordInfoType
					.getCancelledAIFMNationalCode() != null) {
				temp = complexCancellationAIFRecordInfoType
						.getCancelledAIFMNationalCode().toString();
				ReportUtilities.saveData(temp, "CancelledAIFMNationalCode",
						"304", null, reportExecution, user, update);
			}

			// (305) <CancelledReportingPeriodType>
			if (complexCancellationAIFRecordInfoType
					.getCancelledReportingPeriodType() != null) {
				temp = complexCancellationAIFRecordInfoType
						.getCancelledReportingPeriodType().value();
				ReportUtilities.saveData(temp, "CancelledReportingPeriodType",
						"305", null, reportExecution, user, update);
			}

			// (306) <CancelledReportingPeriodYear>
			if (complexCancellationAIFRecordInfoType
					.getCancelledReportingPeriodYear() != null) {
				temp = complexCancellationAIFRecordInfoType
						.getCancelledReportingPeriodYear().toString();
				ReportUtilities.saveData(temp, "CancelledReportingPeriodYear",
						"306", null, reportExecution, user, update);
			}

			// (307) <CancelledRecordFlag>
			if (complexCancellationAIFRecordInfoType.getCancelledRecordFlag() != null) {
				temp = complexCancellationAIFRecordInfoType
						.getCancelledRecordFlag().toString();
				ReportUtilities.saveData(temp, "CancelledRecordFlag", "307",
						null, reportExecution, user, update);
			}

		} // end complexCancellationAIFRecordInfoType
			// < / CancellationAIFMRecordInfo>

		// /////////////////////////////////////////////////////////////////

		logger.debug("Datos finales de XML: ");
		for (ReportData reportData1 : reportExecution.getReportDatas()) {
			logger.debug(reportData1.getReportField().getReportFieldName()
					+ " (" + reportData1.getReportField().getReportFieldNum()
					+ ") " + reportData1.getReportDataText());
		}

		// reportExecution.setReportDatas(reportDatas);

		return reportExecution;
	}
}