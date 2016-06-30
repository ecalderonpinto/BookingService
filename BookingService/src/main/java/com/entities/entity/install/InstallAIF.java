package com.entities.entity.install;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.entities.dao.reportingtool.ReportCatalogDAO;
import com.entities.dao.reportingtool.ReportFieldDAO;
import com.entities.dao.reportingtool.ReportSemanticDAO;
import com.entities.dictionary.ReportCatalogLevelEnum;
import com.entities.entity.reportingtool.ReportCatalog;
import com.entities.entity.reportingtool.ReportField;
import com.entities.entity.reportingtool.ReportSemantic;
import com.entities.utilities.hibernate.VersionAuditor;
import com.reportingtool.utilities.ReportUtilities;

public class InstallAIF implements InstallAdapter {

	private ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(InstallAIF.class);

	public InstallAIF(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * Process to install in database reportCatalog and reportFields from AIF
	 * report
	 * 
	 * @param applicationContext
	 */
	public void install() {

		// TODO:RT not inserting company/department by default in the future

		try {
			VersionAuditor versionAdmin = new VersionAuditor("admin");

			String versionField = ReportUtilities.reportVersion;

			ReportCatalog reportCatalog = new ReportCatalog(versionField,
					ReportCatalogLevelEnum.FUND.getReportLevel(), "AIF 1.2", "", null, null, null, versionAdmin);

			ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) applicationContext
					.getBean("reportCatalogDAO");
			reportCatalogDAO.create(reportCatalog);

			ReportFieldDAO reportFieldDAO = (ReportFieldDAO) applicationContext
					.getBean("reportFieldDAO");

			ReportField reportFieldx1 = new ReportField(reportCatalog, null,
					"X", "AIFReportingInfo", new BigInteger("0"), null, "",
					null, null, "01.000", "1,1", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportFieldx1);

			// (1) <ReportingMemberState>
			ReportField reportField1 = new ReportField(reportCatalog,
					reportFieldx1, "A", "ReportingMemberState", new BigInteger(
							"1"), ".{2}", "", "General Info",
					"CountryCodeType", "01.001", "1,1", versionField, null,
					null, null, versionAdmin);
			reportField1.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField1);

			// (2) <Version>
			ReportField reportField2 = new ReportField(reportCatalog,
					reportFieldx1, "A", "Version", new BigInteger("2"),
					"([0-9])+\\.([0-9])+", "", "General Info", "VERSION",
					"01.002", "1,1", versionField, null, null, null,
					versionAdmin);
			reportField2.setReportFieldEditable(true);
			reportField2.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField2);

			// (3) <CreationDateAndTime>
			ReportField reportField3 = new ReportField(reportCatalog,
					reportFieldx1, "D", "CreationDateAndTime", new BigInteger(
							"3"),
					"[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}",
					"", "General Info", "DATETIME", "01.003", "1,1",
					versionField, null, null, null, versionAdmin);
			reportField3.setReportFieldEditable(true);
			reportField3.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField3);

			ReportField reportFieldx2 = new ReportField(reportCatalog,
					reportFieldx1, "X", "AIFRecordInfo", new BigInteger("0"),
					null, "", null, null, "01.1", "1,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx2);

			// (4) <FilingType>
			ReportField reportField4 = new ReportField(reportCatalog,
					reportFieldx2, "A", "FilingType", new BigInteger("4"),
					".{4}", "", "General Info", "FilingTypeType", "01.004",
					"1,1", versionField, null, null, null, versionAdmin);
			reportField4.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField4);

			// (5) <AIFContentType>
			ReportField reportField5 = new ReportField(reportCatalog,
					reportFieldx2, "N", "AIFContentType", new BigInteger("5"),
					"[0-9]{1}", "", "General Info", "AIFContentTypeType",
					"01.005", "1,1", versionField, null, null, null,
					versionAdmin);
			reportField5.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField5);

			// (6) <ReportingPeriodStartDate>
			ReportField reportField6 = new ReportField(reportCatalog,
					reportFieldx2, "D", "ReportingPeriodStartDate",
					new BigInteger("6"), "[0-9]{4}-[0-9]{2}-[0-9]{2}", "",
					"General Info", "DATE", "01.006", "1,1", versionField,
					null, null, null, versionAdmin);
			reportField6.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField6);

			// (7) <ReportingPeriodEndDate>
			ReportField reportField7 = new ReportField(reportCatalog,
					reportFieldx2, "D", "ReportingPeriodEndDate",
					new BigInteger("7"), "[0-9]{4}-[0-9]{2}-[0-9]{2}", "",
					"General Info", "DATE", "01.007", "1,1", versionField,
					null, null, null, versionAdmin);
			reportField7.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField7);

			// (8) <ReportingPeriodType>
			ReportField reportField8 = new ReportField(reportCatalog,
					reportFieldx2, "A", "ReportingPeriodType", new BigInteger(
							"8"), ".{2}", "", "General Info",
					"ReportingPeriodTypeType", "01.008", "1,1", versionField,
					null, null, null, versionAdmin);
			reportField8.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField8);

			// (9) <ReportingPeriodYear>
			ReportField reportField9 = new ReportField(reportCatalog,
					reportFieldx2, "N", "ReportingPeriodYear", new BigInteger(
							"9"), "[0-9]{4}", "", "General Info", "YEAR",
					"01.009", "1,1", versionField, null, null, null,
					versionAdmin);
			reportField9.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField9);

			// (10) <AIFReportingObligationChangeFrequencyCode>
			ReportField reportField10 = new ReportField(reportCatalog,
					reportFieldx2, "A",
					"AIFReportingObligationChangeFrequencyCode",
					new BigInteger("10"), ".{2}", "", "General Info",
					"ReportingObligationChangeFrequencyCodeType", "01.010",
					"0,1", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField10);

			// (11) <AIFReportingObligationChangeContentsCode>
			ReportField reportField11 = new ReportField(reportCatalog,
					reportFieldx2, "N",
					"AIFReportingObligationChangeContentsCode", new BigInteger(
							"11"), "[0-9]{1}", "", "General Info",
					"AIFReportingObligationChangeContentsCodeType", "01.011",
					"0,1", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField11);

			// (12) <AIFReportingObligationChangeQuarter>
			ReportField reportField12 = new ReportField(reportCatalog,
					reportFieldx2, "A", "AIFReportingObligationChangeQuarter",
					new BigInteger("12"), ".{2}", "", "General Info",
					"ReportingObligationChangeQuarterType", "01.012", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField12);

			// (13) <LastReportingFlag>
			ReportField reportField13 = new ReportField(reportCatalog,
					reportFieldx2, "B", "LastReportingFlag", new BigInteger(
							"13"), "true|false", "", "General Info", "BOOLEAN",
					"01.013", "1,1", versionField, null, null, null,
					versionAdmin);
			reportField13.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField13);

			ReportField reportFieldx3 = new ReportField(reportCatalog,
					reportFieldx2, "X", "Assumptions", new BigInteger("0"),
					null, "", null, null, "02.1", "0,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx3);
			ReportField reportFieldx4 = new ReportField(reportCatalog,
					reportFieldx3, "X", "Assumption", new BigInteger("0"),
					null, "", null, null, "02.1", "0,n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx4);

			// (14) <QuestionNumber>
			ReportField reportField14 = new ReportField(reportCatalog,
					reportFieldx4, "N", "QuestionNumber", new BigInteger("14"),
					"[0-9]{1,3}", "", "Assumptions", "QUESTION", "02.014",
					"0,n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField14);

			// (15) <AssumptionDescription>
			ReportField reportField15 = new ReportField(reportCatalog,
					reportFieldx4, "Z", "AssumptionDescription",
					new BigInteger("15"), ".{1,300}", "", "Assumptions",
					"DESCRIPTION", "02.015", "0,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField15);

			// (16) <AIFMNationalCode>
			ReportField reportField16 = new ReportField(reportCatalog,
					reportFieldx2, "Z", "AIFMNationalCode",
					new BigInteger("16"), ".{1,30}", "", "General Info",
					"AIFMNationalCodeType", "01.016", "1,1", versionField,
					null, null, null, versionAdmin);
			reportField16.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField16);

			// (17) <AIFNationalCode>
			ReportField reportField17 = new ReportField(reportCatalog,
					reportFieldx2, "Z", "AIFNationalCode",
					new BigInteger("17"), ".{1,30}", "", "General Info",
					"AIFNationalCodeType", "01.017", "1,1", versionField, null,
					null, null, versionAdmin);
			reportField17.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField17);

			// (18) <AIFName>
			ReportField reportField18 = new ReportField(reportCatalog,
					reportFieldx2, "Z", "AIFName", new BigInteger("18"),
					".{1,300}", "", "General Info", "AIF_NAME", "01.018",
					"1,1", versionField, null, null, null, versionAdmin);
			reportField18.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField18);

			// (19) <AIFEEAFlag>
			ReportField reportField19 = new ReportField(reportCatalog,
					reportFieldx2, "B", "AIFEEAFlag", new BigInteger("19"),
					"true|false", "", "General Info", "BOOLEAN", "01.019",
					"1,1", versionField, null, null, null, versionAdmin);
			reportField19.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField19);

			// (20) <AIFReportingCode>
			ReportField reportField20 = new ReportField(reportCatalog,
					reportFieldx2, "A", "AIFReportingCode",
					new BigInteger("20"), ".{2}", "", "General Info",
					"AIFReportingCodeType", "01.020", "1,1", versionField,
					null, null, null, versionAdmin);
			reportField20.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField20);

			// (21) <AIFDomicile>
			ReportField reportField21 = new ReportField(reportCatalog,
					reportFieldx2, "A", "AIFDomicile", new BigInteger("21"),
					".{2}", "", "General Info", "CountryCodeType", "01.021",
					"1,1", versionField, null, null, null, versionAdmin);
			reportField21.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField21);

			// (22) <InceptionDate>
			ReportField reportField22 = new ReportField(reportCatalog,
					reportFieldx2, "D", "InceptionDate", new BigInteger("22"),
					"[0-9]{4}-[0-9]{2}-[0-9]{2}", "", "General Info", "DATE",
					"01.022", "1,1", versionField, null, null, null,
					versionAdmin);
			reportField22.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField22);

			// (23) <AIFNoReportingFlag>
			ReportField reportField23 = new ReportField(reportCatalog,
					reportFieldx2, "B", "AIFNoReportingFlag", new BigInteger(
							"23"), "true|false", "", "General Info", "BOOLEAN",
					"01.023", "1,1", versionField, null, null, null,
					versionAdmin);
			reportField23.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField23);

			// /////////////////////////////////////////////////////////////

			ReportField reportFieldx5 = new ReportField(reportCatalog,
					reportFieldx2, "X", "AIFCompleteDescription",
					new BigInteger("0"), null, "", null, null, "03.1", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx5);

			ReportField reportFieldx6 = new ReportField(reportCatalog,
					reportFieldx5, "X", "AIFPrincipalInfo",
					new BigInteger("0"), null, "", null, null, "03.1", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx6);
			ReportField reportFieldx7 = new ReportField(reportCatalog,
					reportFieldx6, "X", "AIFIdentification",
					new BigInteger("0"), null, "", null, null, "03.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx7);

			// (24) <AIFIdentifierLEI>
			ReportField reportField24 = new ReportField(reportCatalog,
					reportFieldx7, "A", "AIFIdentifierLEI",
					new BigInteger("24"), "[0-9a-zA-Z]{18}[0-9]{2}", "",
					"Complete Description", "AIFIdentification", "03.024",
					"0,1", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField24);

			// (25) <AIFIdentifierISIN>
			ReportField reportField25 = new ReportField(reportCatalog,
					reportFieldx7, "A", "AIFIdentifierISIN", new BigInteger(
							"25"), "[A-Z]{2}([A-Z]|[0-9]){9}[0-9]", "",
					"Complete Description", "ISINInstrumentIdentificationType",
					"03.025", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField25);

			// (26) <AIFIdentifierCUSIP>
			ReportField reportField26 = new ReportField(reportCatalog,
					reportFieldx7, "A", "AIFIdentifierCUSIP", new BigInteger(
							"26"), ".{9}", "", "Complete Description",
					"CusipCodeType", "03.026", "0,1", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField26);

			// (27) <AIFIdentifierSEDOL>
			ReportField reportField27 = new ReportField(reportCatalog,
					reportFieldx7, "A", "AIFIdentifierSEDOL", new BigInteger(
							"27"), ".{7}", "", "Complete Description",
					"SedolCodeType", "03.027", "0,1", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField27);

			// (28) <AIFIdentifierTicker>
			ReportField reportField28 = new ReportField(reportCatalog,
					reportFieldx7, "A", "AIFIdentifierTicker", new BigInteger(
							"28"), ".{20}", "", "Complete Description",
					"TickerCodeType", "03.028", "0,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField28);

			// (29) <AIFIdentifierRIC>
			ReportField reportField29 = new ReportField(reportCatalog,
					reportFieldx7, "A", "AIFIdentifierRIC",
					new BigInteger("29"), ".{20}", "", "Complete Description",
					"RICCodeType", "03.029", "0,1", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField29);

			// (30) <AIFIdentifierECB>
			ReportField reportField30 = new ReportField(reportCatalog,
					reportFieldx7, "A", "AIFIdentifierECB",
					new BigInteger("30"), ".{20}", "", "Complete Description",
					"ECBCodeType", "03.030", "0,1", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField30);

			// (31) <Old_ReportingMemberState>
			ReportField reportField31 = new ReportField(reportCatalog,
					reportFieldx7, "A", "Old_ReportingMemberState",
					new BigInteger("31"), ".{2}", "", "Complete Description",
					"CountryCodeType", "03.031", "0,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField31);

			// (32) <Old_AIFNationalCode>
			ReportField reportField32 = new ReportField(reportCatalog,
					reportFieldx7, "A", "Old_AIFNationalCode", new BigInteger(
							"32"), ".{30}", "", "Complete Description",
					"AIFMNationalCodeType", "03.032", "0,1", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField32);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx8 = new ReportField(reportCatalog,
					reportFieldx7, "X", "ShareClassIdentifier", new BigInteger(
							"0"), null, "", null, null, "04.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx8);

			// (33) <ShareClassFlag>
			ReportField reportField33 = new ReportField(reportCatalog,
					reportFieldx8, "B", "ShareClassFlag", new BigInteger("33"),
					"true|false", "", "Share Class Identifier", "BOOLEAN",
					"04.0.033", "1,1", versionField, null, null, null,
					versionAdmin);
			reportField33.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField33);

			// (34) <ShareClassNationalCode>
			ReportField reportField34 = new ReportField(reportCatalog,
					reportFieldx8, "Z", "ShareClassNationalCode",
					new BigInteger("34"), ".{1,30}", "",
					"Share Class Identifier", "ShareClassNationalCodeType",
					"04.034", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField34);

			// (35) <ShareClassIdentifierISIN>
			ReportField reportField35 = new ReportField(reportCatalog,
					reportFieldx8, "A", "ShareClassIdentifierISIN",
					new BigInteger("35"), "[A-Z]{2}([A-Z]|[0-9]){9}[0-9]", "",
					"Share Class Identifier",
					"ISINInstrumentIdentificationType", "04.035", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField35);

			// (36) <ShareClassIdentifierSEDOL>
			ReportField reportField36 = new ReportField(reportCatalog,
					reportFieldx8, "A", "ShareClassIdentifierSEDOL",
					new BigInteger("36"), ".{7}", "", "Share Class Identifier",
					"SedolCodeType", "04.036", "0,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField36);

			// (37) <ShareClassIdentifierCUSIP>
			ReportField reportField37 = new ReportField(reportCatalog,
					reportFieldx8, "A", "ShareClassIdentifierCUSIP",
					new BigInteger("37"), ".{9}", "", "Share Class Identifier",
					"CusipCodeType", "04.037", "0,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField37);

			// (38) <ShareClassIdentifierTicker>
			ReportField reportField38 = new ReportField(reportCatalog,
					reportFieldx8, "A", "ShareClassIdentifierTicker",
					new BigInteger("38"), ".{20}", "",
					"Share Class Identifier", "TickerCodeType", "04.038",
					"0,n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField38);

			// (39) <ShareClassIdentifierRIC>
			ReportField reportField39 = new ReportField(reportCatalog,
					reportFieldx8, "A", "ShareClassIdentifierRIC",
					new BigInteger("39"), ".{20}", "",
					"Share Class Identifier", "RICCodeType", "04.039", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField39);

			// (40) <ShareClassName>
			ReportField reportField40 = new ReportField(reportCatalog,
					reportFieldx8, "Z", "ShareClassName", new BigInteger("40"),
					".{1,300}", "", "Share Class Identifier", "SHARECLASS",
					"04.040", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField40);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx9 = new ReportField(reportCatalog,
					reportFieldx6, "X", "AIFDescription", new BigInteger("0"),
					null, "", null, null, "05.1", "1,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx9);

			// (41) <AIFMasterFeederStatus>
			ReportField reportField41 = new ReportField(reportCatalog,
					reportFieldx9, "A", "AIFMasterFeederStatus",
					new BigInteger("41"), ".{4,6}", "", "AIF Description",
					"AIFMasterFeederStatusType", "05.0.041", "1,1",
					versionField, null, null, null, versionAdmin);
			reportField41.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField41);

			ReportField reportFieldx10 = new ReportField(reportCatalog,
					reportFieldx9, "X", "MasterAIFsIdentification",
					new BigInteger("0"), null, "", null, null, "05.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx10);
			ReportField reportFieldx11 = new ReportField(reportCatalog,
					reportFieldx10, "X", "MasterAIFIdentification",
					new BigInteger("0"), null, "", null, null, "05.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx11);

			// (42) <AIFName>
			ReportField reportField42 = new ReportField(reportCatalog,
					reportFieldx11, "Z", "AIFName", new BigInteger("42"),
					".{1,300}", "", "AIF Description", "AIF_NAME", "05.042",
					"0,n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField42);

			// (43) <ReportingMemberState>
			ReportField reportField43 = new ReportField(reportCatalog,
					reportFieldx11, "A", "ReportingMemberState",
					new BigInteger("43"), ".{2}", "", "AIF Description",
					"CountryCodeType", "05.043", "0,n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField43);

			// (44) <AIFNationalCode>
			ReportField reportField44 = new ReportField(reportCatalog,
					reportFieldx11, "Z", "AIFNationalCode",
					new BigInteger("44"), ".{1,30}", "", "AIF Description",
					"AIFNationalCodeType", "05.044", "0,n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField44);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx12 = new ReportField(reportCatalog,
					reportFieldx9, "X", "PrimeBrokers", new BigInteger("0"),
					null, "", null, null, "06.1", "0,n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx12);
			ReportField reportFieldx13 = new ReportField(reportCatalog,
					reportFieldx12, "X", "PrimeBrokerIdentification",
					new BigInteger("0"), null, "", null, null, "06.001", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx13);

			// (45) <EntityName>
			ReportField reportField45 = new ReportField(reportCatalog,
					reportFieldx13, "Z", "EntityName", new BigInteger("45"),
					".{1,300}", "", "Prime Brokers", "NAME", "06.045", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField45);

			// (46) <EntityIdentificationLEI>
			ReportField reportField46 = new ReportField(reportCatalog,
					reportFieldx13, "A", "EntityIdentificationLEI",
					new BigInteger("46"), "[0-9a-zA-Z]{18}[0-9]{2}", "",
					"Prime Brokers", "LEICodeType", "06.046", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField46);

			// (47) <EntityIdentificationBIC>
			ReportField reportField47 = new ReportField(reportCatalog,
					reportFieldx13, "A", "EntityIdentificationBIC",
					new BigInteger("47"), ".{11}", "", "Prime Brokers",
					"BICCodeType", "06.047", "0,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField47);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx14 = new ReportField(reportCatalog,
					reportFieldx9, "X", "AIFBaseCurrencyDescription",
					new BigInteger("0"), null, "", null, null, "07.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx14);

			// (48) <AUMAmountInBaseCurrency>
			ReportField reportField48 = new ReportField(reportCatalog,
					reportFieldx14, "N", "AUMAmountInBaseCurrency",
					new BigInteger("48"), "[0-9]{1,15}?", "",
					"AIF Base Currency Description", "NUMBER", "07.048", "1,1",
					versionField, null, null, null, versionAdmin);
			reportField48.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField48);

			// (49) <BaseCurrency>
			ReportField reportField49 = new ReportField(reportCatalog,
					reportFieldx14, "A", "BaseCurrency", new BigInteger("49"),
					".{3}", "", "AIF Base Currency Description",
					"CurrencyCodeType", "07.049", "1,1", versionField, null,
					null, null, versionAdmin);
			reportField49.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField49);

			// (50) <FXEURRate>
			ReportField reportField50 = new ReportField(reportCatalog,
					reportFieldx14, "N", "FXEURRate", new BigInteger("50"),
					"[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"AIF Base Currency Description", "DECIMAL", "07.050",
					"0,1", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField50);

			// (51) <FXEURReferenceRateType>
			ReportField reportField51 = new ReportField(reportCatalog,
					reportFieldx14, "A", "FXEURReferenceRateType",
					new BigInteger("51"), ".{3}", "",
					"AIF Base Currency Description",
					"FXEURReferenceRateTypeType", "07.051", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField51);

			// (52) <FXEUROtherReferenceRateDescription>
			ReportField reportField52 = new ReportField(reportCatalog,
					reportFieldx14, "Z", "FXEUROtherReferenceRateDescription",
					new BigInteger("52"), ".{1,300}", "",
					"AIF Base Currency Description", "DESCRIPTION", "07.052",
					"0,1", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField52);

			// (53) <AIFNetAssetValue>
			ReportField reportField53 = new ReportField(reportCatalog,
					reportFieldx9, "N", "AIFNetAssetValue",
					new BigInteger("53"), "[+|-]?[0-9]{1,15}", "",
					"AIF Base Currency Description", "NUMBER", "07.053", "1,1",
					versionField, null, null, null, versionAdmin);
			reportField53.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField53);

			// //////////////////////////////////////////////////////////////

			// (54) <FirstFundingSourceCountry>
			ReportField reportField54 = new ReportField(reportCatalog,
					reportFieldx9, "A", "FirstFundingSourceCountry",
					new BigInteger("54"), ".{2}", "", "Jurisdiction",
					"CountryCodeType", "08.054", "0,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField54);

			// (55) <SecondFundingSourceCountry>
			ReportField reportField55 = new ReportField(reportCatalog,
					reportFieldx9, "A", "SecondFundingSourceCountry",
					new BigInteger("55"), ".{2}", "", "Jurisdiction",
					"CountryCodeType", "08.055", "0,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField55);

			// (56) <ThirdFundingSourceCountry>
			ReportField reportField56 = new ReportField(reportCatalog,
					reportFieldx9, "A", "ThirdFundingSourceCountry",
					new BigInteger("56"), ".{2}", "", "Jurisdiction",
					"CountryCodeType", "08.056", "0,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField56);

			// //////////////////////////////////////////////////////////////

			// (57) <PredominantAIFType>
			ReportField reportField57 = new ReportField(reportCatalog,
					reportFieldx9, "A", "PredominantAIFType", new BigInteger(
							"57"), ".{4}", "", "AIF Type", "AIFTypeType",
					"09.057", "1,1", versionField, null, null, null,
					versionAdmin);
			reportField57.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField57);

			// //////////////////////////////////////////////////////////////
			// <HedgeFundInvestmentStrategies>

			ReportField reportFieldx15 = new ReportField(reportCatalog,
					reportFieldx9, "X", "HedgeFundInvestmentStrategies",
					new BigInteger("0"), null, "", null, null, "10.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx15);
			ReportField reportFieldx16 = new ReportField(reportCatalog,
					reportFieldx15, "X", "HedgeFundStrategy", new BigInteger(
							"0"), null, "", null, null, "10.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx16);

			// (58) <HedgeFundStrategyType>
			ReportField reportField58_H = new ReportField(reportCatalog,
					reportFieldx16, "A", "HedgeFundStrategyType",
					new BigInteger("58"), ".{9}", "", "Hedge Fund Strategy",
					"HedgeFundStrategyTypeType", "10.058", "0,n", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField58_H);

			// (59) <H_PrimaryStrategyFlag>
			ReportField reportField59_H = new ReportField(reportCatalog,
					reportFieldx16, "B", "H_PrimaryStrategyFlag",
					new BigInteger("59"), "true|false", "",
					"Hedge Fund Strategy", "BOOLEAN", "10.059", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField59_H);

			// (60) <H_StrategyNAVRate>
			ReportField reportField60_H = new ReportField(reportCatalog,
					reportFieldx16, "N", "H_StrategyNAVRate", new BigInteger(
							"60"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Hedge Fund Strategy", "DECIMAL", "10.060", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField60_H);

			// (61) <H_StrategyTypeOtherDescription>
			ReportField reportField61_H = new ReportField(reportCatalog,
					reportFieldx16, "Z", "H_StrategyTypeOtherDescription",
					new BigInteger("61"), ".{1,300}", "",
					"Hedge Fund Strategy", "DESCRIPTION", "10.061", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField61_H);

			ReportField reportFieldx17 = new ReportField(reportCatalog,
					reportFieldx9, "X",
					"PrivateEquityFundInvestmentStrategies",
					new BigInteger("0"), null, "", null, null, "11.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx17);
			ReportField reportFieldx18 = new ReportField(reportCatalog,
					reportFieldx17, "X", "PrivateEquityFundInvestmentStrategy",
					new BigInteger("0"), null, "", null, null, "11.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx18);

			// //////////////////////////////////////////////////////////////
			// <PrivateEquityFundInvestmentStrategies>

			// (58) <PrivateEquityFundStrategyType>
			ReportField reportField58_P = new ReportField(reportCatalog,
					reportFieldx18, "A", "PrivateEquityFundStrategyType",
					new BigInteger("58"), ".{9}", "",
					"Private Equity Fund Strategy",
					"PrivateEquityFundStrategyTypeType", "11.058", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField58_P);

			// (59) <P_PrimaryStrategyFlag>
			ReportField reportField59_P = new ReportField(reportCatalog,
					reportFieldx18, "B", "P_PrimaryStrategyFlag",
					new BigInteger("59"), "true|false", "",
					"Private Equity Fund Strategy", "BOOLEAN", "11.059", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField59_P);

			// (60) <P_StrategyNAVRate>
			ReportField reportField60_P = new ReportField(reportCatalog,
					reportFieldx18, "N", "P_StrategyNAVRate", new BigInteger(
							"60"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Private Equity Fund Strategy", "DECIMAL", "11.060", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField60_P);

			// (61) <P_StrategyTypeOtherDescription>
			ReportField reportField61_P = new ReportField(reportCatalog,
					reportFieldx18, "Z", "P_StrategyTypeOtherDescription",
					new BigInteger("61"), ".{1,300}", "",
					"Private Equity Fund Strategy", "DESCRIPTION", "11.061",
					"0,n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField61_P);

			// //////////////////////////////////////////////////////////////
			// <FundOfFundsInvestmentStrategies>

			ReportField reportFieldx19 = new ReportField(reportCatalog,
					reportFieldx9, "X", "FundOfFundsInvestmentStrategies",
					new BigInteger("0"), null, "", null, null, "12.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx19);
			ReportField reportFieldx20 = new ReportField(reportCatalog,
					reportFieldx19, "X", "FundOfFundsStrategy", new BigInteger(
							"0"), null, "", null, null, "12.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx20);

			// (58) <FundOfFundsStrategyType>
			ReportField reportField58_F = new ReportField(reportCatalog,
					reportFieldx20, "A", "FundOfFundsStrategyType",
					new BigInteger("58"), ".{9}", "", "Fund Of Funds Strategy",
					"FundOfFundsStrategyTypeType", "12.058", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField58_F);

			// (59) <F_PrimaryStrategyFlag>
			ReportField reportField59_F = new ReportField(reportCatalog,
					reportFieldx20, "B", "F_PrimaryStrategyFlag",
					new BigInteger("59"), "true|false", "",
					"Fund Of Funds Strategy", "BOOLEAN", "12.059", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField59_F);

			// (60) <F_StrategyNAVRate>
			ReportField reportField60_F = new ReportField(reportCatalog,
					reportFieldx20, "N", "F_StrategyNAVRate", new BigInteger(
							"60"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Fund Of Funds Strategy", "DECIMAL", "12.060", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField60_F);

			// (61) <F_StrategyTypeOtherDescription>
			ReportField reportField61_F = new ReportField(reportCatalog,
					reportFieldx20, "Z", "F_StrategyTypeOtherDescription",
					new BigInteger("61"), ".{1,300}", "",
					"Fund Of Funds Strategy", "DESCRIPTION", "12.061", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField61_F);

			// //////////////////////////////////////////////////////////////
			// <OtherFundInvestmentStrategies>

			ReportField reportFieldx21 = new ReportField(reportCatalog,
					reportFieldx9, "X", "OtherFundInvestmentStrategies",
					new BigInteger("0"), null, "", null, null, "10.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx21);
			ReportField reportFieldx22 = new ReportField(reportCatalog,
					reportFieldx21, "X", "OtherFundStrategy", new BigInteger(
							"0"), null, "", null, null, "13.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx22);

			// (58) <OtherFundStrategyType>
			ReportField reportField58_O = new ReportField(reportCatalog,
					reportFieldx22, "A", "OtherFundStrategyType",
					new BigInteger("58"), ".{9}", "", "Other Fund Strategy",
					"OtherFundStrategyTypeType", "13.058", "0,n", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField58_O);

			// (59) <O_PrimaryStrategyFlag>
			ReportField reportField59_O = new ReportField(reportCatalog,
					reportFieldx22, "B", "O_PrimaryStrategyFlag",
					new BigInteger("59"), "true|false", "",
					"Other Fund Strategy", "BOOLEAN", "13.059", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField59_O);

			// (60) <O_StrategyNAVRate>
			ReportField reportField60_O = new ReportField(reportCatalog,
					reportFieldx22, "N", "O_StrategyNAVRate", new BigInteger(
							"60"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Other Fund Strategy", "DECIMAL", "13.060", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField60_O);

			// (61) <O_StrategyTypeOtherDescription>
			ReportField reportField61_O = new ReportField(reportCatalog,
					reportFieldx22, "Z", "O_StrategyTypeOtherDescription",
					new BigInteger("61"), ".{1,300}", "",
					"Other Fund Strategy", "DESCRIPTION", "13.061", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField61_O);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx23 = new ReportField(reportCatalog,
					reportFieldx9, "X", "RealEstateFundInvestmentStrategies",
					new BigInteger("0"), null, "", null, null, "14.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx23);
			ReportField reportFieldx24 = new ReportField(reportCatalog,
					reportFieldx23, "X", "RealEstateFundStrategy",
					new BigInteger("0"), null, "", null, null, "14.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx24);

			// (58) <RealEstateFundStrategyType>
			ReportField reportField58_R = new ReportField(reportCatalog,
					reportFieldx24, "A", "RealEstateFundStrategyType",
					new BigInteger("58"), ".{9}", "",
					"Real Estate Fund Strategy",
					"RealEstateFundStrategyTypeType", "14.058", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField58_R);

			// (59) <R_PrimaryStrategyFlag>
			ReportField reportField59_R = new ReportField(reportCatalog,
					reportFieldx24, "B", "R_PrimaryStrategyFlag",
					new BigInteger("59"), "true|false", "",
					"Real Estate Fund Strategy", "BOOLEAN", "14.059", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField59_R);

			// (60) <R_StrategyNAVRate>
			ReportField reportField60_R = new ReportField(reportCatalog,
					reportFieldx24, "N", "R_StrategyNAVRate", new BigInteger(
							"60"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Real Estate Fund Strategy", "DECIMAL", "14.060", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField60_R);

			// (61) <R_StrategyTypeOtherDescription>
			ReportField reportField61_R = new ReportField(reportCatalog,
					reportFieldx24, "Z", "R_StrategyTypeOtherDescription",
					new BigInteger("61"), ".{1,300}", "",
					"Real Estate Fund Strategy", "DESCRIPTION", "14.061",
					"0,n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField61_R);

			// //////////////////////////////////////////////////////////////

			// (62) <HFTTransactionNumber>
			ReportField reportField62 = new ReportField(reportCatalog,
					reportFieldx9, "N", "HFTTransactionNumber", new BigInteger(
							"62"), "[0-9]{1,15}?", "", "Transactions",
					"NUMBER", "15.062", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField62);

			// (63) <HFTBuySellMarketValue>
			ReportField reportField63 = new ReportField(reportCatalog,
					reportFieldx9, "N", "HFTBuySellMarketValue",
					new BigInteger("63"), "[0-9]{1,15}?", "", "Transactions",
					"NUMBER", "15.063", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField63);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx25 = new ReportField(reportCatalog,
					reportFieldx6, "X", "MainInstrumentsTraded",
					new BigInteger("0"), null, "", null, null, "16.1", "0,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx25);
			ReportField reportFieldx26 = new ReportField(reportCatalog,
					reportFieldx25, "X", "MainInstrumentTraded",
					new BigInteger("0"), null, "", null, null, "16.1", "0,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx26);

			// (64) <Ranking>
			ReportField reportField64 = new ReportField(reportCatalog,
					reportFieldx26, "N", "Ranking", new BigInteger("64"),
					"[0-9]{1}", "", "Main Instruments Traded",
					"FiveRankingType", "16.064", "5,5", versionField, null,
					null, null, versionAdmin);
			reportField64.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField64);

			// (65) <SubAssetType>
			ReportField reportField65 = new ReportField(reportCatalog,
					reportFieldx26, "Z", "SubAssetType", new BigInteger("65"),
					".{12}", "", "Main Instruments Traded", "SubAssetTypeType",
					"16.065", "5,5", versionField, null, null, null,
					versionAdmin);
			reportField65.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField65);

			// (66) <InstrumentCodeType>
			ReportField reportField66 = new ReportField(reportCatalog,
					reportFieldx26, "A", "InstrumentCodeType", new BigInteger(
							"66"), ".{3,4}", "", "Main Instruments Traded",
					"InstrumentCodeTypeType", "16.066", "0,5", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField66);

			// (67) <InstrumentName>
			ReportField reportField67 = new ReportField(reportCatalog,
					reportFieldx26, "Z", "InstrumentName",
					new BigInteger("67"), ".{1,300}", "",
					"Main Instruments Traded", "INSTR_NAME", "16.067", "0,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField67);

			// (68) <ISINInstrumentIdentification>
			ReportField reportField68 = new ReportField(reportCatalog,
					reportFieldx26, "A", "ISINInstrumentIdentification",
					new BigInteger("68"), "[A-Z]{2}([A-Z]|[0-9]){9}[0-9]", "",
					"Main Instruments Traded",
					"ISINInstrumentIdentificationType", "16.068", "0,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField68);

			ReportField reportFieldx27 = new ReportField(reportCatalog,
					reportFieldx26, "X", "AIIInstrumentIdentification",
					new BigInteger("0"), null, "", null, null, "17.1", "0,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx27);

			// (69) <AIIExchangeCode>
			ReportField reportField69 = new ReportField(reportCatalog,
					reportFieldx27, "A", "AIIExchangeCode",
					new BigInteger("69"), ".{4}", "",
					"Main Instruments Traded", "MICCodeType", "17.069", "0,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField69);

			// (70) <AIIProductCode>
			ReportField reportField70 = new ReportField(reportCatalog,
					reportFieldx27, "A", "AIIProductCode",
					new BigInteger("70"), ".{1,12}", "",
					"Main Instruments Traded", "AIIProductCodeType", "17.070",
					"0,5", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField70);

			// (71) <AIIDerivativeType>
			ReportField reportField71 = new ReportField(reportCatalog,
					reportFieldx27, "A", "AIIDerivativeType", new BigInteger(
							"71"), ".{1}", "", "Main Instruments Traded",
					"AIIDerivativeTypeType", "17.071", "0,5", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField71);

			// (72) <AIIPutCallIdentifier>
			ReportField reportField72 = new ReportField(reportCatalog,
					reportFieldx27, "A", "AIIPutCallIdentifier",
					new BigInteger("72"), ".{1}", "",
					"Main Instruments Traded", "AIIPutCallIdentifierType",
					"17.072", "0,5", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField72);

			// (73) <AIIExpiryDate>
			ReportField reportField73 = new ReportField(reportCatalog,
					reportFieldx27, "D", "AIIExpiryDate", new BigInteger("73"),
					"[0-9]{4}-[0-9]{2}-[0-9]{2}", "",
					"Main Instruments Traded", "DATE", "17.073", "0,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField73);

			// (74) <AIIStrikePrice>
			ReportField reportField74 = new ReportField(reportCatalog,
					reportFieldx27, "N", "AIIStrikePrice",
					new BigInteger("74"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Main Instruments Traded", "DECIMAL", "17.074", "0,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField74);

			// (75) <PositionType>
			ReportField reportField75 = new ReportField(reportCatalog,
					reportFieldx26, "A", "PositionType", new BigInteger("75"),
					".{1}", "", "Main Instruments Traded", "PositionTypeType",
					"17.075", "0,5", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField75);

			// (76) <PositionValue>
			ReportField reportField76 = new ReportField(reportCatalog,
					reportFieldx26, "N", "PositionValue", new BigInteger("76"),
					"[0-9]{1,15}?", "", "Main Instruments Traded", "NUMBER",
					"17.076", "0,5", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField76);

			// (77) <ShortPositionHedgingRate>
			ReportField reportField77 = new ReportField(reportCatalog,
					reportFieldx26, "N", "ShortPositionHedgingRate",
					new BigInteger("77"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Main Instruments Traded", "DECIMAL", "17.077", "0,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField77);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx28 = new ReportField(reportCatalog,
					reportFieldx6, "X", "NAVGeographicalFocus", new BigInteger(
							"0"), null, "", null, null, "18.1", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx28);

			// (78) <AfricaNAVRate>
			ReportField reportField78 = new ReportField(reportCatalog,
					reportFieldx28, "N", "AfricaNAVRate", new BigInteger("78"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"NAV Geographical Focus", "DECIMAL", "18.078", "1,1",
					versionField, null, null, null, versionAdmin);
			reportField78.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField78);

			// (79) <AsiaPacificNAVRate>
			ReportField reportField79 = new ReportField(reportCatalog,
					reportFieldx28, "N", "AsiaPacificNAVRate", new BigInteger(
							"79"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"NAV Geographical Focus", "DECIMAL", "18.079", "1,1",
					versionField, null, null, null, versionAdmin);
			reportField79.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField79);

			// (80) <EuropeNAVRate>
			ReportField reportField80 = new ReportField(reportCatalog,
					reportFieldx28, "N", "EuropeNAVRate", new BigInteger("80"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"NAV Geographical Focus", "DECIMAL", "18.080", "1,1",
					versionField, null, null, null, versionAdmin);
			reportField80.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField80);

			// (81) <EEANAVRate>
			ReportField reportField81 = new ReportField(reportCatalog,
					reportFieldx28, "N", "EEANAVRate", new BigInteger("81"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"NAV Geographical Focus", "DECIMAL", "18.081", "1,1",
					versionField, null, null, null, versionAdmin);
			reportField81.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField81);

			// (82) <MiddleEastNAVRate>
			ReportField reportField82 = new ReportField(reportCatalog,
					reportFieldx28, "N", "MiddleEastNAVRate", new BigInteger(
							"82"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"NAV Geographical Focus", "DECIMAL", "18.082", "1,1",
					versionField, null, null, null, versionAdmin);
			reportField82.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField82);

			// (83) <NorthAmericaNAVRate>
			ReportField reportField83 = new ReportField(reportCatalog,
					reportFieldx28, "N", "NorthAmericaNAVRate", new BigInteger(
							"83"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"NAV Geographical Focus", "DECIMAL", "18.083", "1,1",
					versionField, null, null, null, versionAdmin);
			reportField83.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField83);

			// (84) <SouthAmericaNAVRate>
			ReportField reportField84 = new ReportField(reportCatalog,
					reportFieldx28, "N", "SouthAmericaNAVRate", new BigInteger(
							"84"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"NAV Geographical Focus", "DECIMAL", "18.084", "1,1",
					versionField, null, null, null, versionAdmin);
			reportField84.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField84);

			// (85) <SupraNationalNAVRate>
			ReportField reportField85 = new ReportField(reportCatalog,
					reportFieldx28, "N", "SupraNationalNAVRate",
					new BigInteger("85"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "NAV Geographical Focus", "DECIMAL", "18.085", "1,1",
					versionField, null, null, null, versionAdmin);
			reportField85.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField85);

			ReportField reportFieldx29 = new ReportField(reportCatalog,
					reportFieldx6, "X", "AUMGeographicalFocus", new BigInteger(
							"0"), null, "", null, null, "18.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx29);

			// (86) <AfricaAUMRate>
			ReportField reportField86 = new ReportField(reportCatalog,
					reportFieldx29, "N", "AfricaAUMRate", new BigInteger("86"),
					"[0-9]{1,3}(\\.[0-9]{1,2})?", "", "NAV Geographical Focus",
					"PERCENT", "18.086", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField86);

			// (87) <AsiaPacificAUMRate>
			ReportField reportField87 = new ReportField(reportCatalog,
					reportFieldx29, "N", "AsiaPacificAUMRate", new BigInteger(
							"87"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"NAV Geographical Focus", "PERCENT", "18.087", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField87);

			// (88) <EuropeAUMRate>
			ReportField reportField88 = new ReportField(reportCatalog,
					reportFieldx29, "N", "EuropeAUMRate", new BigInteger("88"),
					"[0-9]{1,3}(\\.[0-9]{1,2})?", "", "NAV Geographical Focus",
					"PERCENT", "18.088", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField88);

			// (89) <EEAAUMRate>
			ReportField reportField89 = new ReportField(reportCatalog,
					reportFieldx29, "N", "EEAAUMRate", new BigInteger("89"),
					"[0-9]{1,3}(\\.[0-9]{1,2})?", "", "NAV Geographical Focus",
					"PERCENT", "18.089", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField89);

			// (90) <MiddleEastAUMRate>
			ReportField reportField90 = new ReportField(reportCatalog,
					reportFieldx29, "N", "MiddleEastAUMRate", new BigInteger(
							"90"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"NAV Geographical Focus", "PERCENT", "18.090", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField90);

			// (91) <NorthAmericaAUMRate>
			ReportField reportField91 = new ReportField(reportCatalog,
					reportFieldx29, "N", "NorthAmericaAUMRate", new BigInteger(
							"91"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"NAV Geographical Focus", "PERCENT", "18.091", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField91);

			// (92) <SouthAmericaAUMRate>
			ReportField reportField92 = new ReportField(reportCatalog,
					reportFieldx29, "N", "SouthAmericaAUMRate", new BigInteger(
							"92"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"NAV Geographical Focus", "PERCENT", "18.092", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField92);

			// (93) <SupraNationalAUMRate>
			ReportField reportField93 = new ReportField(reportCatalog,
					reportFieldx29, "N", "SupraNationalAUMRate",
					new BigInteger("93"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"NAV Geographical Focus", "PERCENT", "18.093", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField93);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx30 = new ReportField(reportCatalog,
					reportFieldx6, "X", "PrincipalExposures", new BigInteger(
							"0"), null, "", null, null, "19.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx30);
			ReportField reportFieldx31 = new ReportField(reportCatalog,
					reportFieldx30, "X", "PrincipalExposure", new BigInteger(
							"0"), null, "", null, null, "19.1", "10,10",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx31);

			// (94) <Ranking>
			ReportField reportField94 = new ReportField(reportCatalog,
					reportFieldx31, "N", "Ranking", new BigInteger("94"),
					"[0-9]{1,2}", "", "Principal Exposures", "TenRankingType",
					"19.094", "10,10", versionField, null, null, null,
					versionAdmin);
			reportField94.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField94);

			// (95) <AssetMacroType>
			ReportField reportField95 = new ReportField(reportCatalog,
					reportFieldx31, "A", "AssetMacroType",
					new BigInteger("95"), ".{3}", "", "Principal Exposures",
					"AssetMacroTypeType", "19.095", "10,10", versionField,
					null, null, null, versionAdmin);
			reportField95.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField95);

			// (96) <SubAssetType>
			ReportField reportField96 = new ReportField(reportCatalog,
					reportFieldx31, "Z", "SubAssetType", new BigInteger("96"),
					".{12}", "", "Principal Exposures", "SubAssetTypeType",
					"19.096", "0,10", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField96);

			// (97) <PositionType>
			ReportField reportField97 = new ReportField(reportCatalog,
					reportFieldx31, "A", "PositionType", new BigInteger("97"),
					".{1}", "", "Principal Exposures", "PositionTypeType",
					"19.097", "0,10", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField97);

			// (98) <AggregatedValueAmount>
			ReportField reportField98 = new ReportField(reportCatalog,
					reportFieldx31, "N", "AggregatedValueAmount",
					new BigInteger("98"), "[0-9]{1,15}?", "",
					"Principal Exposures", "NUMBER", "19.098", "0,10",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField98);

			// (99) <AggregatedValueRate>
			ReportField reportField99 = new ReportField(reportCatalog,
					reportFieldx31, "N", "AggregatedValueRate", new BigInteger(
							"99"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Principal Exposures", "PERCENT", "19.099", "0,10",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField99);

			// (100) <EntityName>
			ReportField reportField100 = new ReportField(reportCatalog,
					reportFieldx31, "Z", "EntityName", new BigInteger("100"),
					".{1,300}", "", "Principal Exposures", "NAME", "19.100",
					"0,10", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField100);

			// (101) <EntityIdentificationLEI>
			ReportField reportField101 = new ReportField(reportCatalog,
					reportFieldx31, "A", "EntityIdentificationLEI",
					new BigInteger("101"), "[0-9a-zA-Z]{18}[0-9]{2}", "",
					"Principal Exposures", "LEICodeType", "19.101", "0,10",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField101);

			// (102) <EntityIdentificationBIC>
			ReportField reportField102 = new ReportField(reportCatalog,
					reportFieldx31, "A", "EntityIdentificationBIC",
					new BigInteger("102"), ".{11}", "", "Principal Exposures",
					"BICCodeType", "19.102", "0,10", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField102);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx32 = new ReportField(reportCatalog,
					reportFieldx6, "X", "MostImportantConcentration",
					new BigInteger("0"), null, "", null, null, "20.1", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx32);
			ReportField reportFieldx33 = new ReportField(reportCatalog,
					reportFieldx32, "X", "PortfolioConcentrations",
					new BigInteger("0"), null, "", null, null, "20.1", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx33);
			ReportField reportFieldx34 = new ReportField(reportCatalog,
					reportFieldx33, "X", "PortfolioConcentration",
					new BigInteger("0"), null, "", null, null, "20.1", "5,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx34);

			// (103) <Ranking>
			ReportField reportField103 = new ReportField(reportCatalog,
					reportFieldx34, "N", "Ranking", new BigInteger("103"),
					"[0-9]{1}", "", "Portfolio Concentration",
					"FiveRankingType", "20.103", "5,5", versionField, null,
					null, null, versionAdmin);
			reportField103.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField103);

			// (104) <AssetType>
			ReportField reportField104 = new ReportField(reportCatalog,
					reportFieldx34, "A", "AssetType", new BigInteger("104"),
					".{7}", "", "Portfolio Concentration", "AssetTypeType",
					"20.104", "5,5", versionField, null, null, null,
					versionAdmin);
			reportField104.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField104);

			// (105) <PositionType>
			ReportField reportField105 = new ReportField(reportCatalog,
					reportFieldx34, "A", "PositionType", new BigInteger("105"),
					".{1}", "", "Portfolio Concentration", "PositionTypeType",
					"20.105", "0,5", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField105);

			ReportField reportFieldx35 = new ReportField(reportCatalog,
					reportFieldx34, "X", "MarketIdentification",
					new BigInteger("0"), null, "", null, null, "20.1", "5,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx35);

			// (106) <MarketCodeType>
			ReportField reportField106 = new ReportField(reportCatalog,
					reportFieldx35, "A", "MarketCodeType",
					new BigInteger("106"), ".{3}", "",
					"Portfolio Concentration", "MarketCodeTypeWithoutNOTType",
					"20.106", "0,5", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField106);

			// (107) <MarketCode>
			ReportField reportField107 = new ReportField(reportCatalog,
					reportFieldx35, "A", "MarketCode", new BigInteger("107"),
					".{4}", "", "Portfolio Concentration", "MICCodeType",
					"20.107", "0,5", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField107);

			// (108) <AggregatedValueAmount>
			ReportField reportField108 = new ReportField(reportCatalog,
					reportFieldx34, "N", "AggregatedValueAmount",
					new BigInteger("108"), "[0-9]{1,15}?", "",
					"Portfolio Concentration", "NUMBER", "20.108", "0,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField108);

			// (109) <AggregatedValueRate>
			ReportField reportField109 = new ReportField(reportCatalog,
					reportFieldx34, "N", "AggregatedValueRate", new BigInteger(
							"109"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Portfolio Concentration", "PERCENT", "20.109", "0,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField109);

			ReportField reportFieldx36 = new ReportField(reportCatalog,
					reportFieldx35, "X", "CounterpartyIdentification",
					new BigInteger("0"), null, "", null, null, "20.1", "0,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx36);

			// (110) <EntityName>
			ReportField reportField110 = new ReportField(reportCatalog,
					reportFieldx36, "Z", "EntityName", new BigInteger("110"),
					".{1,300}", "", "Portfolio Concentration", "NAME",
					"20.110", "0,5", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField110);

			// (111) <EntityIdentificationLEI>
			ReportField reportField111 = new ReportField(reportCatalog,
					reportFieldx36, "A", "EntityIdentificationLEI",
					new BigInteger("111"), "[0-9a-zA-Z]{18}[0-9]{2}", "",
					"Portfolio Concentration", "LEICodeType", "20.111", "0,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField111);

			// (112) <EntityIdentificationBIC>
			ReportField reportField112 = new ReportField(reportCatalog,
					reportFieldx36, "A", "EntityIdentificationBIC",
					new BigInteger("112"), ".{11}", "",
					"Portfolio Concentration", "BICCodeType", "20.112", "0,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField112);

			// //////////////////////////////////////////////////////////////

			// (113) <TypicalPositionSize>
			ReportField reportField113 = new ReportField(reportCatalog,
					reportFieldx32, "A", "TypicalPositionSize", new BigInteger(
							"113"), ".{5,11}", "", "Typical Position Size",
					"TypicalPositionSizeType", "21.113", "0,1", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField113);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx37 = new ReportField(reportCatalog,
					reportFieldx32, "X", "AIFPrincipalMarkets", new BigInteger(
							"0"), null, "", null, null, "22.1", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx37);
			ReportField reportFieldx38 = new ReportField(reportCatalog,
					reportFieldx37, "X", "AIFPrincipalMarket", new BigInteger(
							"0"), null, "", null, null, "22.1", "3,3",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx38);

			// (114) <Ranking>
			ReportField reportField114 = new ReportField(reportCatalog,
					reportFieldx38, "N", "Ranking", new BigInteger("114"),
					"[0-9]{1}", "", "AIF Principal Markets",
					"ThreeRankingType", "22.114", "3,3", versionField, null,
					null, null, versionAdmin);
			reportField114.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField114);

			ReportField reportFieldx39 = new ReportField(reportCatalog,
					reportFieldx38, "X", "MarketIdentification",
					new BigInteger("0"), null, "", null, null, "22.1", "3,3",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx39);

			// (115) <MarketCodeType>
			ReportField reportField115 = new ReportField(reportCatalog,
					reportFieldx39, "A", "MarketCodeType",
					new BigInteger("115"), ".{3}", "", "AIF Principal Markets",
					"MarketCodeTypeWithNOTType", "22.115", "3,3", versionField,
					null, null, null, versionAdmin);
			reportField115.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField115);

			// (116) <MarketCode>
			ReportField reportField116 = new ReportField(reportCatalog,
					reportFieldx39, "A", "MarketCode", new BigInteger("116"),
					".{4}", "", "AIF Principal Markets", "MICCodeType",
					"22.116", "0,3", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField116);

			// (117) <AggregatedValueAmount>
			ReportField reportField117 = new ReportField(reportCatalog,
					reportFieldx38, "N", "AggregatedValueAmount",
					new BigInteger("117"), "[0-9]{1,15}?", "",
					"AIF Principal Markets", "NUMBER", "22.117", "0,3",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField117);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx40 = new ReportField(reportCatalog,
					reportFieldx32, "X", "InvestorConcentration",
					new BigInteger("0"), null, "", null, null, "23.1", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx40);

			// (118) <MainBeneficialOwnersRate>
			ReportField reportField118 = new ReportField(reportCatalog,
					reportFieldx40, "N", "MainBeneficialOwnersRate",
					new BigInteger("118"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Investor Concentration", "PERCENT", "23.118", "1,1",
					versionField, null, null, null, versionAdmin);
			reportField118.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField118);

			// (119) <ProfessionalInvestorConcentrationRate>
			ReportField reportField119 = new ReportField(reportCatalog,
					reportFieldx40, "N",
					"ProfessionalInvestorConcentrationRate", new BigInteger(
							"119"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Investor Concentration", "PERCENT", "23.119", "1,1",
					versionField, null, null, null, versionAdmin);
			reportField119.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField119);

			// (120) <RetailInvestorConcentrationRate>
			ReportField reportField120 = new ReportField(reportCatalog,
					reportFieldx40, "N", "RetailInvestorConcentrationRate",
					new BigInteger("120"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Investor Concentration", "PERCENT", "23.120", "1,1",
					versionField, null, null, null, versionAdmin);
			reportField120.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField120);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx41 = new ReportField(reportCatalog,
					reportFieldx5, "X", "AIFIndividualInfo",
					new BigInteger("0"), null, "", null, null, "24.1", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx41);

			ReportField reportFieldx42 = new ReportField(reportCatalog,
					reportFieldx41, "X", "IndividualExposure", new BigInteger(
							"0"), null, "", null, null, "24.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx42);
			ReportField reportFieldx43 = new ReportField(reportCatalog,
					reportFieldx42, "X", "AssetTypeExposures", new BigInteger(
							"0"), null, "", null, null, "24.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx43);
			ReportField reportFieldx44 = new ReportField(reportCatalog,
					reportFieldx43, "X", "AssetTypeExposure", new BigInteger(
							"0"), null, "", null, null, "24.1", "1,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx44);

			// (121) <SubAssetType>
			ReportField reportField121 = new ReportField(reportCatalog,
					reportFieldx44, "A", "SubAssetType", new BigInteger("121"),
					".{1,12}", "", "Individual Exposure", "SubAssetTypeType",
					"24.121", "1,n", versionField, null, null, null,
					versionAdmin);
			reportField121.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField121);

			// (122) <GrossValue>
			ReportField reportField122 = new ReportField(reportCatalog,
					reportFieldx44, "N", "GrossValue", new BigInteger("122"),
					"[0-9]{1,15}?", "", "Individual Exposure", "NUMBER",
					"24.122", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField122);

			// (123) <LongValue>
			ReportField reportField123 = new ReportField(reportCatalog,
					reportFieldx44, "N", "LongValue", new BigInteger("123"),
					"[0-9]{1,15}?", "", "Individual Exposure", "NUMBER",
					"24.123", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField123);

			// (124) <ShortValue>
			ReportField reportField124 = new ReportField(reportCatalog,
					reportFieldx44, "N", "ShortValue", new BigInteger("124"),
					"[0-9]{1,15}?", "", "Individual Exposure", "NUMBER",
					"24.124", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField124);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx45 = new ReportField(reportCatalog,
					reportFieldx42, "X", "AssetTypeTurnovers", new BigInteger(
							"0"), null, "", null, null, "25.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx45);
			ReportField reportFieldx46 = new ReportField(reportCatalog,
					reportFieldx45, "X", "AssetTypeTurnover", new BigInteger(
							"0"), null, "", null, null, "25.1", "1,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx46);

			// (125) <TurnoverSubAssetType>
			ReportField reportField125 = new ReportField(reportCatalog,
					reportFieldx46, "A", "TurnoverSubAssetType",
					new BigInteger("125"), ".{1,12}", "",
					"Asset Type Turnovers", "TurnoverSubAssetTypeType",
					"25.125", "1,n", versionField, null, null, null,
					versionAdmin);
			reportField125.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField125);

			// (126) <MarketValue>
			ReportField reportField126 = new ReportField(reportCatalog,
					reportFieldx46, "N", "MarketValue", new BigInteger("126"),
					"[0-9]{1,15}?", "", "Asset Type Turnovers", "NUMBER",
					"25.126", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField126);

			// (127) <NotionalValue>
			ReportField reportField127 = new ReportField(reportCatalog,
					reportFieldx46, "N", "NotionalValue",
					new BigInteger("127"), "[0-9]{1,15}?", "",
					"Asset Type Turnovers", "NUMBER", "25.127", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField127);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx47 = new ReportField(reportCatalog,
					reportFieldx42, "X", "CurrencyExposures", new BigInteger(
							"0"), null, "", null, null, "26.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx47);
			ReportField reportFieldx48 = new ReportField(reportCatalog,
					reportFieldx47, "X", "CurrencyExposure",
					new BigInteger("0"), null, "", null, null, "26.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx48);

			// (128) <ExposureCurrency>
			ReportField reportField128 = new ReportField(reportCatalog,
					reportFieldx48, "A", "ExposureCurrency", new BigInteger(
							"128"), ".{3}", "", "Currency Exposures",
					"CurrencyCodeType", "26.128", "0,n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField128);

			// (129) <LongPositionValue>
			ReportField reportField129 = new ReportField(reportCatalog,
					reportFieldx48, "N", "LongPositionValue", new BigInteger(
							"129"), "[0-9]{1,15}?", "", "Currency Exposures",
					"NUMBER", "26.129", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField129);

			// (130) <ShortPositionValue>
			ReportField reportField130 = new ReportField(reportCatalog,
					reportFieldx48, "N", "ShortPositionValue", new BigInteger(
							"130"), "[0-9]{1,15}?", "", "Currency Exposures",
					"NUMBER", "26.130", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField130);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx49 = new ReportField(reportCatalog,
					reportFieldx42, "X", "CompaniesDominantInfluence",
					new BigInteger("0"), null, "", null, null, "27.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx49);
			ReportField reportFieldx50 = new ReportField(reportCatalog,
					reportFieldx49, "X", "CompanyDominantInfluence",
					new BigInteger("0"), null, "", null, null, "27.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx50);
			ReportField reportFieldx51 = new ReportField(reportCatalog,
					reportFieldx50, "X", "CompanyIdentification",
					new BigInteger("0"), null, "", null, null, "27.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx51);

			// (131) <EntityName>
			ReportField reportField131 = new ReportField(reportCatalog,
					reportFieldx51, "Z", "EntityName", new BigInteger("131"),
					".{1,300}", "", "Companies Dominant Influence", "NAME",
					"27.131", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField131);

			// (132) <EntityIdentificationLEI>
			ReportField reportField132 = new ReportField(reportCatalog,
					reportFieldx51, "A", "EntityIdentificationLEI",
					new BigInteger("132"), "[0-9a-zA-Z]{18}[0-9]{2}", "",
					"Companies Dominant Influence", "LEICodeType", "27.132",
					"0,n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField132);

			// (133) <EntityIdentificationBIC>
			ReportField reportField133 = new ReportField(reportCatalog,
					reportFieldx51, "A", "EntityIdentificationBIC",
					new BigInteger("133"), ".{11}", "",
					"Companies Dominant Influence", "BICCodeType", "27.133",
					"0,n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField133);

			// (134) <TransactionType>
			ReportField reportField134 = new ReportField(reportCatalog,
					reportFieldx50, "A", "TransactionType", new BigInteger(
							"134"), ".{4}", "", "Companies Dominant Influence",
					"TransactionTypeType", "27.134", "0,n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField134);

			// (135) <OtherTransactionTypeDescription>
			ReportField reportField135 = new ReportField(reportCatalog,
					reportFieldx50, "Z", "OtherTransactionTypeDescription",
					new BigInteger("135"), ".{1,300}", "",
					"Companies Dominant Influence", "NAME", "27.135", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField135);

			// (136) <VotingRightsRate>
			ReportField reportField136 = new ReportField(reportCatalog,
					reportFieldx50, "N", "VotingRightsRate", new BigInteger(
							"136"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Companies Dominant Influence", "PERCENT", "27.136", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField136);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx52 = new ReportField(reportCatalog,
					reportFieldx41, "X", "RiskProfile", new BigInteger("0"),
					null, "", null, null, "28.1", "0,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx52);

			ReportField reportFieldx53 = new ReportField(reportCatalog,
					reportFieldx52, "X", "MarketRiskProfile", new BigInteger(
							"0"), null, "", null, null, "28.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx53);

			// (137) <AnnualInvestmentReturnRate>
			ReportField reportField137 = new ReportField(reportCatalog,
					reportFieldx53, "N", "AnnualInvestmentReturnRate",
					new BigInteger("137"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?|NA", "", "Risk Profile",
					"DECIMAL", "28.0.137", "1,1", versionField, null, null,
					null, versionAdmin);
			reportField137.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField137);

			ReportField reportFieldx54 = new ReportField(reportCatalog,
					reportFieldx53, "X", "MarketRiskMeasures", new BigInteger(
							"0"), null, "", null, null, "28.1", "1,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx54);
			ReportField reportFieldx55 = new ReportField(reportCatalog,
					reportFieldx54, "X", "MarketRiskMeasure", new BigInteger(
							"0"), null, "", null, null, "28.1", "1,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx55);

			// (138) <RiskMeasureType>
			ReportField reportField138 = new ReportField(reportCatalog,
					reportFieldx55, "Z", "RiskMeasureType", new BigInteger(
							"138"), ".{3,14}", "", "Risk Profile",
					"RiskMeasureTypeType", "28.138", "0,n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField138);

			// (139) <RiskMeasureValue>
			ReportField reportField139 = new ReportField(reportCatalog,
					reportFieldx55, "N", "RiskMeasureValue", new BigInteger(
							"139"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Risk Profile", "DECIMAL", "28.139", "0,n", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField139);

			ReportField reportFieldx56 = new ReportField(reportCatalog,
					reportFieldx55, "X", "BucketRiskMeasureValues",
					new BigInteger("0"), null, "", null, null, "28.1", "1,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx56);

			// (140) <LessFiveYearsRiskMeasureValue>
			ReportField reportField140 = new ReportField(reportCatalog,
					reportFieldx56, "N", "LessFiveYearsRiskMeasureValue",
					new BigInteger("140"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "Risk Profile", "DECIMAL", "28.140", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField140);

			// (141) <FifthteenYearsRiskMeasureValue>
			ReportField reportField141 = new ReportField(reportCatalog,
					reportFieldx56, "N", "FifthteenYearsRiskMeasureValue",
					new BigInteger("141"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "Risk Profile", "DECIMAL", "28.141", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField141);

			// (142) <MoreFifthteenYearsRiskMeasureValue>
			ReportField reportField142 = new ReportField(reportCatalog,
					reportFieldx56, "N", "MoreFifthteenYearsRiskMeasureValue",
					new BigInteger("142"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "Risk Profile", "DECIMAL", "28.142", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField142);

			ReportField reportFieldx57 = new ReportField(reportCatalog,
					reportFieldx55, "X", "VegaRiskMeasureValues",
					new BigInteger("0"), null, "", null, null, "28.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx57);

			// (143) <CurrentMarketRiskMeasureValue>
			ReportField reportField143 = new ReportField(reportCatalog,
					reportFieldx57, "N", "CurrentMarketRiskMeasureValue",
					new BigInteger("143"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "Risk Profile", "DECIMAL", "28.143", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField143);

			// (144) <LowerMarketRiskMeasureValue>
			ReportField reportField144 = new ReportField(reportCatalog,
					reportFieldx57, "N", "LowerMarketRiskMeasureValue",
					new BigInteger("144"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "Risk Profile", "DECIMAL", "28.144", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField144);

			// (145) <HigherMarketRiskMeasureValue>
			ReportField reportField145 = new ReportField(reportCatalog,
					reportFieldx57, "N", "HigherMarketRiskMeasureValue",
					new BigInteger("145"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "Risk Profile", "DECIMAL", "28.145", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField145);

			ReportField reportFieldx58 = new ReportField(reportCatalog,
					reportFieldx55, "X", "VARRiskMeasureValues",
					new BigInteger("0"), null, "", null, null, "28.1", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx58);

			// (302) <VARValue>
			ReportField reportField302 = new ReportField(reportCatalog,
					reportFieldx58, "N", "VARValue", new BigInteger("302"),
					"[0-9]{1,3}(\\.[0-9]{1,2})?", "", "Risk Profile",
					"PERCENT", "28.302", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField302);

			// (146) <VARCalculationMethodCodeType>
			ReportField reportField146 = new ReportField(reportCatalog,
					reportFieldx58, "A", "VARCalculationMethodCodeType",
					new BigInteger("146"), ".{5}", "", "Risk Profile",
					"VARCalculationMethodCodeTypeType", "28.146", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField146);

			// (147) <RiskMeasureDescription>
			ReportField reportField147 = new ReportField(reportCatalog,
					reportFieldx55, "Z", "RiskMeasureDescription",
					new BigInteger("147"), ".{1,300}", "", "Risk Profile",
					"DESCRIPTION", "28.147", "0,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField147);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx59 = new ReportField(reportCatalog,
					reportFieldx52, "X", "CounterpartyRiskProfile",
					new BigInteger("0"), null, "", null, null, "29.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx59);

			ReportField reportFieldx60 = new ReportField(reportCatalog,
					reportFieldx59, "X", "TradingClearingMechanism",
					new BigInteger("0"), null, "", null, null, "29.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx60);

			ReportField reportFieldx61 = new ReportField(reportCatalog,
					reportFieldx60, "X", "TradedSecurities",
					new BigInteger("0"), null, "", null, null, "29.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx61);

			// (148) <RegulatedMarketRate>
			ReportField reportField148 = new ReportField(reportCatalog,
					reportFieldx61, "N", "RegulatedMarketRate", new BigInteger(
							"148"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Counterparty Risk Profile", "PERCENT", "29.148", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField148);

			// (149) <OTCRate>
			ReportField reportField149 = new ReportField(reportCatalog,
					reportFieldx61, "N", "OTCRate", new BigInteger("149"),
					"[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Counterparty Risk Profile", "PERCENT", "29.149", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField149);

			ReportField reportFieldx62 = new ReportField(reportCatalog,
					reportFieldx60, "X", "TradedDerivatives", new BigInteger(
							"0"), null, "", null, null, "29.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx62);

			// (150) <RegulatedMarketRate>
			ReportField reportField150 = new ReportField(reportCatalog,
					reportFieldx62, "N", "RegulatedMarketRate", new BigInteger(
							"150"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Counterparty Risk Profile", "PERCENT", "29.150", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField150);

			// (151) <OTCRate>
			ReportField reportField151 = new ReportField(reportCatalog,
					reportFieldx62, "N", "OTCRate", new BigInteger("151"),
					"[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Counterparty Risk Profile", "PERCENT", "29.151", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField151);

			ReportField reportFieldx63 = new ReportField(reportCatalog,
					reportFieldx59, "X", "ClearedDerivativesRate",
					new BigInteger("0"), null, "", null, null, "29.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx63);

			// (152) <CCPRate>
			ReportField reportField152 = new ReportField(reportCatalog,
					reportFieldx63, "N", "CCPRate", new BigInteger("152"),
					"[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Counterparty Risk Profile", "PERCENT", "29.152", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField152);

			// (153) <BilateralClearingRate>
			ReportField reportField153 = new ReportField(reportCatalog,
					reportFieldx63, "N", "BilateralClearingRate",
					new BigInteger("153"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Counterparty Risk Profile", "PERCENT", "29.153", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField153);

			ReportField reportFieldx64 = new ReportField(reportCatalog,
					reportFieldx59, "X", "ClearedReposRate",
					new BigInteger("0"), null, "", null, null, "29.154", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx64);

			// (154) <CCPRate>
			ReportField reportField154 = new ReportField(reportCatalog,
					reportFieldx64, "N", "CCPRate", new BigInteger("154"),
					"[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Counterparty Risk Profile", "PERCENT", "29.154", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField154);

			// (155) <BilateralClearingRate>
			ReportField reportField155 = new ReportField(reportCatalog,
					reportFieldx64, "N", "BilateralClearingRate",
					new BigInteger("155"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Counterparty Risk Profile", "PERCENT", "29.155", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField155);

			// (156) <TriPartyRepoClearingRate>
			ReportField reportField156 = new ReportField(reportCatalog,
					reportFieldx64, "N", "TriPartyRepoClearingRate",
					new BigInteger("156"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Counterparty Risk Profile", "PERCENT", "29.156", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField156);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx65 = new ReportField(reportCatalog,
					reportFieldx59, "X", "AllCounterpartyCollateral",
					new BigInteger("0"), null, "", null, null, "30.1", "1,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx65);

			// (157) <AllCounterpartyCollateralCash>
			ReportField reportField157 = new ReportField(reportCatalog,
					reportFieldx65, "N", "AllCounterpartyCollateralCash",
					new BigInteger("157"), "[0-9]{1,15}?", "",
					"All Counterparty Collateral", "NUMBER", "30.157", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField157);

			// (158) <AllCounterpartyCollateralSecurities>
			ReportField reportField158 = new ReportField(reportCatalog,
					reportFieldx65, "N", "AllCounterpartyCollateralSecurities",
					new BigInteger("158"), "[0-9]{1,15}?", "",
					"All Counterparty Collateral", "NUMBER", "30.158", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField158);

			// (159) <AllCounterpartyOtherCollateralPosted>
			ReportField reportField159 = new ReportField(reportCatalog,
					reportFieldx65, "N",
					"AllCounterpartyOtherCollateralPosted", new BigInteger(
							"159"), "[0-9]{1,15}?", "",
					"All Counterparty Collateral", "NUMBER", "30.159", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField159);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx66 = new ReportField(reportCatalog,
					reportFieldx59, "X", "FundToCounterpartyExposures",
					new BigInteger("0"), null, "", null, null, "31.1", "1,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx66);
			ReportField reportFieldx67 = new ReportField(reportCatalog,
					reportFieldx66, "X", "FundToCounterpartyExposure",
					new BigInteger("0"), null, "", null, null, "31.1", "1,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx67);

			// (160) <Ranking>
			ReportField reportField160 = new ReportField(reportCatalog,
					reportFieldx67, "N", "Ranking", new BigInteger("160"),
					"[0-9]{1}", "", "Fund To Counterparty Exposures",
					"FiveRankingType", "31.160", "5,5", versionField, null,
					null, null, versionAdmin);
			reportField160.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField160);

			// (161) <CounterpartyExposureFlag>
			ReportField reportField161 = new ReportField(reportCatalog,
					reportFieldx67, "B", "CounterpartyExposureFlag",
					new BigInteger("161"), "true|false", "",
					"Fund To Counterparty Exposures", "BOOLEAN", "31.161",
					"5,5", versionField, null, null, null, versionAdmin);
			reportField161.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField161);

			ReportField reportFieldx68 = new ReportField(reportCatalog,
					reportFieldx67, "X", "CounterpartyIdentification",
					new BigInteger("0"), null, "", null, null, "31.1", "1,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx68);

			// (162) <EntityName>
			ReportField reportField162 = new ReportField(reportCatalog,
					reportFieldx68, "Z", "EntityName", new BigInteger("162"),
					".{1,300}", "", "Fund To Counterparty Exposures", "NAME",
					"31.162", "0,5", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField162);

			// (163) <EntityIdentificationLEI>
			ReportField reportField163 = new ReportField(reportCatalog,
					reportFieldx68, "A", "EntityIdentificationLEI",
					new BigInteger("163"), "[0-9a-zA-Z]{18}[0-9]{2}", "",
					"Fund To Counterparty Exposures", "LEICodeType", "31.163",
					"0,5", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField163);

			// (164) <EntityIdentificationBIC>
			ReportField reportField164 = new ReportField(reportCatalog,
					reportFieldx68, "A", "EntityIdentificationBIC",
					new BigInteger("164"), ".{11}", "",
					"Fund To Counterparty Exposures", "BICCodeType", "31.164",
					"0,5", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField164);

			// (165) <CounterpartyTotalExposureRate>
			ReportField reportField165 = new ReportField(reportCatalog,
					reportFieldx67, "N", "CounterpartyTotalExposureRate",
					new BigInteger("165"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Fund To Counterparty Exposures", "PERCENT", "31.165",
					"0,5", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField165);

			ReportField reportFieldx69 = new ReportField(reportCatalog,
					reportFieldx59, "X", "CounterpartyToFundExposures",
					new BigInteger("0"), null, "", null, null, "31.1", "1,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx69);
			ReportField reportFieldx70 = new ReportField(reportCatalog,
					reportFieldx69, "X", "CounterpartyToFundExposure",
					new BigInteger("0"), null, "", null, null, "31.1", "1,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx70);

			// (166) <Ranking>
			ReportField reportField166 = new ReportField(reportCatalog,
					reportFieldx70, "N", "Ranking", new BigInteger("166"),
					"[0-9]{1}", "", "Fund To Counterparty Exposures",
					"FiveRankingType", "31.166", "5,5", versionField, null,
					null, null, versionAdmin);
			reportField166.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField166);

			// (167) <CounterpartyExposureFlag>
			ReportField reportField167 = new ReportField(reportCatalog,
					reportFieldx70, "B", "CounterpartyExposureFlag",
					new BigInteger("167"), "true|false", "",
					"Fund To Counterparty Exposures", "BOOLEAN", "31.167",
					"5,5", versionField, null, null, null, versionAdmin);
			reportField167.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField167);

			ReportField reportFieldx71 = new ReportField(reportCatalog,
					reportFieldx70, "X", "CounterpartyIdentification",
					new BigInteger("0"), null, "", null, null, "31.1", "1,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx71);

			// (168) <EntityName>
			ReportField reportField168 = new ReportField(reportCatalog,
					reportFieldx71, "Z", "EntityName", new BigInteger("168"),
					".{1,300}", "", "Fund To Counterparty Exposures", "NAME",
					"31.168", "0,5", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField168);

			// (169) <EntityIdentificationLEI>
			ReportField reportField169 = new ReportField(reportCatalog,
					reportFieldx71, "A", "EntityIdentificationLEI",
					new BigInteger("169"), "[0-9a-zA-Z]{18}[0-9]{2}", "",
					"Fund To Counterparty Exposures", "LEICodeType", "31.169",
					"0,5", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField169);

			// (170) <EntityIdentificationBIC>
			ReportField reportField170 = new ReportField(reportCatalog,
					reportFieldx71, "A", "EntityIdentificationBIC",
					new BigInteger("170"), ".{11}", "",
					"Fund To Counterparty Exposures", "BICCodeType", "31.170",
					"0,5", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField170);

			// (171) <CounterpartyTotalExposureRate>
			ReportField reportField171 = new ReportField(reportCatalog,
					reportFieldx70, "N", "CounterpartyTotalExposureRate",
					new BigInteger("171"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Fund To Counterparty Exposures", "PERCENT", "31.171",
					"0,5", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField171);

			// //////////////////////////////////////////////////////////////

			// (172) <ClearTransactionsThroughCCPFlag>
			ReportField reportField172 = new ReportField(reportCatalog,
					reportFieldx59, "B", "ClearTransactionsThroughCCPFlag",
					new BigInteger("172"), "true|false", "", "CCP Exposures",
					"BOOLEAN", "32.0.172", "1,1", versionField, null, null,
					null, versionAdmin);
			reportField172.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField172);

			ReportField reportFieldx72 = new ReportField(reportCatalog,
					reportFieldx59, "X", "CCPExposures", new BigInteger("0"),
					null, "", null, null, "32.1", "1,n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx72);
			ReportField reportFieldx73 = new ReportField(reportCatalog,
					reportFieldx72, "X", "CCPExposure", new BigInteger("0"),
					null, "", null, null, "32.1", "1,n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx73);

			// (173) <Ranking>
			ReportField reportField173 = new ReportField(reportCatalog,
					reportFieldx73, "N", "Ranking", new BigInteger("173"),
					"[0-9]{1}", "", "CCP Exposures", "ThreeRankingType",
					"32.173", "0,3", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField173);

			ReportField reportFieldx74 = new ReportField(reportCatalog,
					reportFieldx73, "X", "CCPIdentification", new BigInteger(
							"0"), null, "", null, null, "32.1", "1,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx74);

			// (174) <EntityName>
			ReportField reportField174 = new ReportField(reportCatalog,
					reportFieldx74, "Z", "EntityName", new BigInteger("174"),
					".{1,300}", "", "CCP Exposures", "NAME", "32.174", "0,3",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField174);

			// (175) <EntityIdentificationLEI>
			ReportField reportField175 = new ReportField(reportCatalog,
					reportFieldx74, "A", "EntityIdentificationLEI",
					new BigInteger("175"), "[0-9a-zA-Z]{18}[0-9]{2}", "",
					"CCP Exposures", "LEICodeType", "32.175", "0,3",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField175);

			// (176) <EntityIdentificationBIC>
			ReportField reportField176 = new ReportField(reportCatalog,
					reportFieldx74, "A", "EntityIdentificationBIC",
					new BigInteger("176"), ".{11}", "", "CCP Exposures",
					"BICCodeType", "32.176", "0,3", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField176);

			// (177) <CCPExposureValue>
			ReportField reportField177 = new ReportField(reportCatalog,
					reportFieldx73, "N", "CCPExposureValue", new BigInteger(
							"177"), "[0-9]{1,15}?", "", "CCP Exposures",
					"NUMBER", "32.177", "0,3", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField177);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx75 = new ReportField(reportCatalog,
					reportFieldx52, "X", "LiquidityRiskProfile",
					new BigInteger("0"), null, "", null, null, "33.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx75);

			ReportField reportFieldx76 = new ReportField(reportCatalog,
					reportFieldx75, "X", "PortfolioLiquidityProfile",
					new BigInteger("0"), null, "", null, null, "33.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx76);

			// (178) <PortfolioLiquidityInDays0to1Rate>
			ReportField reportField178 = new ReportField(reportCatalog,
					reportFieldx76, "N", "PortfolioLiquidityInDays0to1Rate",
					new BigInteger("178"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Portfolio Liquidity Profile", "DECIMAL", "33.178", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField178);

			// (179) <PortfolioLiquidityInDays2to7Rate>
			ReportField reportField179 = new ReportField(reportCatalog,
					reportFieldx76, "N", "PortfolioLiquidityInDays2to7Rate",
					new BigInteger("179"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Portfolio Liquidity Profile", "DECIMAL", "33.179", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField179);

			// (180) <PortfolioLiquidityInDays8to30Rate>
			ReportField reportField180 = new ReportField(reportCatalog,
					reportFieldx76, "N", "PortfolioLiquidityInDays8to30Rate",
					new BigInteger("180"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Portfolio Liquidity Profile", "DECIMAL", "33.180", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField180);

			// (181) <PortfolioLiquidityInDays31to90Rate>
			ReportField reportField181 = new ReportField(reportCatalog,
					reportFieldx76, "N", "PortfolioLiquidityInDays31to90Rate",
					new BigInteger("181"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Portfolio Liquidity Profile", "DECIMAL", "33.181", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField181);

			// (182) <PortfolioLiquidityInDays91to180Rate>
			ReportField reportField182 = new ReportField(reportCatalog,
					reportFieldx76, "N", "PortfolioLiquidityInDays91to180Rate",
					new BigInteger("182"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Portfolio Liquidity Profile", "DECIMAL", "33.182", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField182);

			// (183) <PortfolioLiquidityInDays181to365Rate>
			ReportField reportField183 = new ReportField(reportCatalog,
					reportFieldx76, "N",
					"PortfolioLiquidityInDays181to365Rate", new BigInteger(
							"183"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Portfolio Liquidity Profile", "DECIMAL", "33.183", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField183);

			// (184) <PortfolioLiquidityInDays365MoreRate>
			ReportField reportField184 = new ReportField(reportCatalog,
					reportFieldx76, "N", "PortfolioLiquidityInDays365MoreRate",
					new BigInteger("184"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Portfolio Liquidity Profile", "DECIMAL", "33.184", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField184);

			// (185) <UnencumberedCash>
			ReportField reportField185 = new ReportField(reportCatalog,
					reportFieldx76, "N", "UnencumberedCash", new BigInteger(
							"185"), "[0-9]{1,15}?", "",
					"Portfolio Liquidity Profile", "NUMBER", "33.185", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField185);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx77 = new ReportField(reportCatalog,
					reportFieldx75, "X", "InvestorLiquidityProfile",
					new BigInteger("0"), null, "", null, null, "34.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx77);

			// (186) <InvestorLiquidityInDays0to1Rate>
			ReportField reportField186 = new ReportField(reportCatalog,
					reportFieldx77, "N", "InvestorLiquidityInDays0to1Rate",
					new BigInteger("186"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Investor Liquidity Profile", "DECIMAL", "34.186", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField186);

			// (187) <InvestorLiquidityInDays2to7Rate>
			ReportField reportField187 = new ReportField(reportCatalog,
					reportFieldx77, "N", "InvestorLiquidityInDays2to7Rate",
					new BigInteger("187"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Investor Liquidity Profile", "DECIMAL", "34.187", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField187);

			// (188) <InvestorLiquidityInDays8to30Rate>
			ReportField reportField188 = new ReportField(reportCatalog,
					reportFieldx77, "N", "InvestorLiquidityInDays8to30Rate",
					new BigInteger("188"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Investor Liquidity Profile", "DECIMAL", "34.188", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField188);

			// (189) <InvestorLiquidityInDays31to90Rate>
			ReportField reportField189 = new ReportField(reportCatalog,
					reportFieldx77, "N", "InvestorLiquidityInDays31to90Rate",
					new BigInteger("189"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Investor Liquidity Profile", "DECIMAL", "34.189", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField189);

			// (190) <InvestorLiquidityInDays91to180Rate>
			ReportField reportField190 = new ReportField(reportCatalog,
					reportFieldx77, "N", "InvestorLiquidityInDays91to180Rate",
					new BigInteger("190"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Investor Liquidity Profile", "DECIMAL", "34.190", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField190);

			// (191) <InvestorLiquidityInDays181to365Rate>
			ReportField reportField191 = new ReportField(reportCatalog,
					reportFieldx77, "N", "InvestorLiquidityInDays181to365Rate",
					new BigInteger("191"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Investor Liquidity Profile", "DECIMAL", "34.191", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField191);

			// (192) <InvestorLiquidityInDays365MoreRate>
			ReportField reportField192 = new ReportField(reportCatalog,
					reportFieldx77, "N", "InvestorLiquidityInDays365MoreRate",
					new BigInteger("192"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Investor Liquidity Profile", "DECIMAL", "34.192", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField192);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx78 = new ReportField(reportCatalog,
					reportFieldx75, "X", "InvestorRedemption", new BigInteger(
							"0"), null, "", null, null, "35.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx78);

			// (193) <ProvideWithdrawalRightsFlag>
			ReportField reportField193 = new ReportField(reportCatalog,
					reportFieldx78, "B", "ProvideWithdrawalRightsFlag",
					new BigInteger("193"), "true|false", "",
					"Investor Redemption", "BOOLEAN", "35.193", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField193);

			// (194) <InvestorRedemptionFrequency>
			ReportField reportField194 = new ReportField(reportCatalog,
					reportFieldx78, "A", "InvestorRedemptionFrequency",
					new BigInteger("194"), ".{1}", "", "Investor Redemption",
					"InvestorRedemptionFrequencyType", "35.194", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField194);

			// (195) <InvestorRedemptionNoticePeriod>
			ReportField reportField195 = new ReportField(reportCatalog,
					reportFieldx78, "N", "InvestorRedemptionNoticePeriod",
					new BigInteger("195"), "[0-9]{1,4}?", "",
					"Investor Redemption", "NUMBER", "35.195", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField195);

			// (196) <InvestorRedemptionLockUpPeriod>
			ReportField reportField196 = new ReportField(reportCatalog,
					reportFieldx78, "N", "InvestorRedemptionLockUpPeriod",
					new BigInteger("196"), "[0-9]{1,4}?", "",
					"Investor Redemption", "NUMBER", "35.196", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField196);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx79 = new ReportField(reportCatalog,
					reportFieldx75, "X", "InvestorArrangement", new BigInteger(
							"0"), null, "", null, null, "361.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx79);

			ReportField reportFieldx80 = new ReportField(reportCatalog,
					reportFieldx79, "X", "InvestorIlliquidAssetArrangement",
					new BigInteger("0"), null, "", null, null, "36.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx80);

			// (197) <SidePocketRate>
			ReportField reportField197 = new ReportField(reportCatalog,
					reportFieldx80, "N", "SidePocketRate",
					new BigInteger("197"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Investor Arrangement", "PERCENT", "36.197", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField197);

			// (198) <GatesRate>
			ReportField reportField198 = new ReportField(reportCatalog,
					reportFieldx80, "N", "GatesRate", new BigInteger("198"),
					"[0-9]{1,3}(\\.[0-9]{1,2})?", "", "Investor Arrangement",
					"PERCENT", "36.198", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField198);

			// (199) <DealingSuspensionRate>
			ReportField reportField199 = new ReportField(reportCatalog,
					reportFieldx80, "N", "DealingSuspensionRate",
					new BigInteger("199"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Investor Arrangement", "PERCENT", "36.199", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField199);

			ReportField reportFieldx81 = new ReportField(reportCatalog,
					reportFieldx80, "X", "OtherArrangement",
					new BigInteger("0"), null, "", null, null, "36.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx81);

			// (200) <OtherArrangementType>
			ReportField reportField200 = new ReportField(reportCatalog,
					reportFieldx81, "Z", "OtherArrangementType",
					new BigInteger("200"), ".{1,300}", "",
					"Investor Arrangement", "DESCRIPTION", "36.200", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField200);

			// (201) <OtherArrangementRate>
			ReportField reportField201 = new ReportField(reportCatalog,
					reportFieldx81, "N", "OtherArrangementRate",
					new BigInteger("201"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Investor Arrangement", "PERCENT", "36.201", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField201);

			// (202) <TotalArrangementRate>
			ReportField reportField202 = new ReportField(reportCatalog,
					reportFieldx80, "N", "TotalArrangementRate",
					new BigInteger("202"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Investor Arrangement", "PERCENT", "36.202", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField202);

			ReportField reportFieldx82 = new ReportField(reportCatalog,
					reportFieldx79, "X", "InvestorPreferentialTreatment",
					new BigInteger("0"), null, "", null, null, "36.2", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx82);

			// (203) <InvestorPreferentialTreatmentFlag>
			ReportField reportField203 = new ReportField(reportCatalog,
					reportFieldx82, "B", "InvestorPreferentialTreatmentFlag",
					new BigInteger("203"), "true|false", "",
					"Investor Arrangement", "BOOLEAN", "36.203", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField203);

			// (204) <DisclosureTermsPreferentialTreatmentFlag>
			ReportField reportField204 = new ReportField(reportCatalog,
					reportFieldx82, "B",
					"DisclosureTermsPreferentialTreatmentFlag", new BigInteger(
							"204"), "true|false", "", "Investor Arrangement",
					"BOOLEAN", "36.204", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField204);

			// (205) <LiquidityTermsPreferentialTreatmentFlag>
			ReportField reportField205 = new ReportField(reportCatalog,
					reportFieldx82, "B",
					"LiquidityTermsPreferentialTreatmentFlag", new BigInteger(
							"205"), "true|false", "", "Investor Arrangement",
					"BOOLEAN", "36.205", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField205);

			// (206) <FeeTermsPreferentialTreatmentFlag>
			ReportField reportField206 = new ReportField(reportCatalog,
					reportFieldx82, "B", "FeeTermsPreferentialTreatmentFlag",
					new BigInteger("206"), "true|false", "",
					"Investor Arrangement", "BOOLEAN", "36.206", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField206);

			// (207) <OtherTermsPreferentialTreatmentFlag>
			ReportField reportField207 = new ReportField(reportCatalog,
					reportFieldx82, "B", "OtherTermsPreferentialTreatmentFlag",
					new BigInteger("207"), "true|false", "",
					"Investor Arrangement", "BOOLEAN", "36.207", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField207);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx83 = new ReportField(reportCatalog,
					reportFieldx75, "X", "InvestorGroups", new BigInteger("0"),
					null, "", null, null, "37.1", "0,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx83);
			ReportField reportFieldx84 = new ReportField(reportCatalog,
					reportFieldx83, "X", "InvestorGroup", new BigInteger("0"),
					null, "", null, null, "37.1", "0,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx84);

			// (208) <InvestorGroupType>
			ReportField reportField208 = new ReportField(reportCatalog,
					reportFieldx84, "A", "InvestorGroupType", new BigInteger(
							"208"), ".{4}", "", "Investor Groups",
					"InvestorGroupTypeType", "37.208", "1,n", versionField,
					null, null, null, versionAdmin);
			reportField208.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField208);

			// (209) <InvestorGroupRate>
			ReportField reportField209 = new ReportField(reportCatalog,
					reportFieldx84, "N", "InvestorGroupRate", new BigInteger(
							"209"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"Investor Groups", "PERCENT", "37.209", "1,n",
					versionField, null, null, null, versionAdmin);
			reportField209.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField209);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx85 = new ReportField(reportCatalog,
					reportFieldx75, "X", "FinancingLiquidityProfile",
					new BigInteger("0"), null, "", null, null, "38.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx85);

			// (210) <TotalFinancingAmount>
			ReportField reportField210 = new ReportField(reportCatalog,
					reportFieldx85, "N", "TotalFinancingAmount",
					new BigInteger("210"), "[0-9]{1,15}?", "",
					"Financing Liquidity Profile", "NUMBER", "38.210", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField210);

			// (211) <TotalFinancingInDays0to1Rate>
			ReportField reportField211 = new ReportField(reportCatalog,
					reportFieldx85, "N", "TotalFinancingInDays0to1Rate",
					new BigInteger("211"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Financing Liquidity Profile", "DECIMAL", "38.211", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField211);

			// (212) <TotalFinancingInDays2to7Rate>
			ReportField reportField212 = new ReportField(reportCatalog,
					reportFieldx85, "N", "TotalFinancingInDays2to7Rate",
					new BigInteger("212"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Financing Liquidity Profile", "DECIMAL", "38.212", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField212);

			// (213) <TotalFinancingInDays8to30Rate>
			ReportField reportField213 = new ReportField(reportCatalog,
					reportFieldx85, "N", "TotalFinancingInDays8to30Rate",
					new BigInteger("213"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Financing Liquidity Profile", "DECIMAL", "38.213", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField213);

			// (214) <TotalFinancingInDays31to90Rate>
			ReportField reportField214 = new ReportField(reportCatalog,
					reportFieldx85, "N", "TotalFinancingInDays31to90Rate",
					new BigInteger("214"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Financing Liquidity Profile", "DECIMAL", "38.214", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField214);

			// (215) <TotalFinancingInDays91to180Rate>
			ReportField reportField215 = new ReportField(reportCatalog,
					reportFieldx85, "N", "TotalFinancingInDays91to180Rate",
					new BigInteger("215"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Financing Liquidity Profile", "DECIMAL", "38.215", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField215);

			// (216) <TotalFinancingInDays181to365Rate>
			ReportField reportField216 = new ReportField(reportCatalog,
					reportFieldx85, "N", "TotalFinancingInDays181to365Rate",
					new BigInteger("216"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Financing Liquidity Profile", "DECIMAL", "38.216", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField216);

			// (217) <TotalFinancingInDays365MoreRate>
			ReportField reportField217 = new ReportField(reportCatalog,
					reportFieldx85, "N", "TotalFinancingInDays365MoreRate",
					new BigInteger("217"), "[0-9]{1,15}(\\.[0-9]{1,4})?", "",
					"Financing Liquidity Profile", "DECIMAL", "38.217", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField217);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx86 = new ReportField(reportCatalog,
					reportFieldx52, "X", "OperationalRisk",
					new BigInteger("0"), null, "", null, null, "39.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx86);

			// (218) <TotalOpenPositions>
			ReportField reportField218 = new ReportField(reportCatalog,
					reportFieldx86, "N", "TotalOpenPositions", new BigInteger(
							"218"), "[0-9]{1,15}?", "", "Operational Risk",
					"NUMBER", "39.218", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField218);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx87 = new ReportField(reportCatalog,
					reportFieldx86, "X", "HistoricalRiskProfile",
					new BigInteger("0"), null, "", null, null, "40.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx87);

			ReportField reportFieldx88 = new ReportField(reportCatalog,
					reportFieldx87, "X", "GrossInvestmentReturnsRate",
					new BigInteger("0"), null, "", null, null, "40.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx88);

			// (219) <RateJanuary>
			ReportField reportField219 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateJanuary", new BigInteger("219"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.219", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField219);

			// (220) <RateFebruary>
			ReportField reportField220 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateFebruary", new BigInteger("220"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.220", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField220);

			// (221) <RateMarch>
			ReportField reportField221 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateMarch", new BigInteger("221"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.221", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField221);

			// (222) <RateApril>
			ReportField reportField222 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateApril", new BigInteger("222"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.222", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField222);

			// (223) <RateMay>
			ReportField reportField223 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateMay", new BigInteger("223"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.223", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField223);

			// (224) <RateJune>
			ReportField reportField224 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateJune", new BigInteger("224"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.224", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField224);

			// (225) <RateJuly>
			ReportField reportField225 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateJuly", new BigInteger("225"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.225", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField225);

			// (226) <RateAugust>
			ReportField reportField226 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateAugust", new BigInteger("226"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.226", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField226);

			// (227) <RateSeptember>
			ReportField reportField227 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateSeptember",
					new BigInteger("227"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "Historical Risk Profile", "DECIMAL", "40.227", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField227);

			// (228) <RateOctober>
			ReportField reportField228 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateOctober", new BigInteger("228"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.228", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField228);

			// (229) <RateNovember>
			ReportField reportField229 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateNovember", new BigInteger("229"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.229", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField229);

			// (230) <RateDecember>
			ReportField reportField230 = new ReportField(reportCatalog,
					reportFieldx88, "N", "RateDecember", new BigInteger("230"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.230", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField230);

			ReportField reportFieldx89 = new ReportField(reportCatalog,
					reportFieldx87, "X", "NetInvestmentReturnsRate",
					new BigInteger("0"), null, "", null, null, "40.2", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx89);

			// (231) <RateJanuary>
			ReportField reportField231 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateJanuary", new BigInteger("231"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.231", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField231);

			// (232) <RateFebruary>
			ReportField reportField232 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateFebruary", new BigInteger("232"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.232", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField232);

			// (233) <RateMarch>
			ReportField reportField233 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateMarch", new BigInteger("233"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.233", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField233);

			// (234) <RateApril>
			ReportField reportField234 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateApril", new BigInteger("234"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.234", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField234);

			// (235) <RateMay>
			ReportField reportField235 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateMay", new BigInteger("235"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.235", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField235);

			// (236) <RateJune>
			ReportField reportField236 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateJune", new BigInteger("236"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.236", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField236);

			// (237) <RateJuly>
			ReportField reportField237 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateJuly", new BigInteger("237"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.237", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField237);

			// (238) <RateAugust>
			ReportField reportField238 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateAugust", new BigInteger("238"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.238", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField238);

			// (239) <RateSeptember>
			ReportField reportField239 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateSeptember",
					new BigInteger("239"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "Historical Risk Profile", "DECIMAL", "40.239", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField239);

			// (240) <RateOctober>
			ReportField reportField240 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateOctober", new BigInteger("240"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.240", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField240);

			// (241) <RateNovember>
			ReportField reportField241 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateNovember", new BigInteger("241"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.241", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField241);

			// (242) <RateDecember>
			ReportField reportField242 = new ReportField(reportCatalog,
					reportFieldx89, "N", "RateDecember", new BigInteger("242"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.242", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField242);

			ReportField reportFieldx90 = new ReportField(reportCatalog,
					reportFieldx87, "X", "NAVChangeRate", new BigInteger("0"),
					null, "", null, null, "40.2", "0,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx90);

			// (243) <RateJanuary>
			ReportField reportField243 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateJanuary", new BigInteger("243"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.243", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField243);

			// (244) <RateFebruary>
			ReportField reportField244 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateFebruary", new BigInteger("244"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.244", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField244);

			// (245) <RateMarch>
			ReportField reportField245 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateMarch", new BigInteger("245"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.245", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField245);

			// (246) <RateApril>
			ReportField reportField246 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateApril", new BigInteger("246"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.246", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField246);

			// (247) <RateMay>
			ReportField reportField247 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateMay", new BigInteger("247"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.247", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField247);

			// (248) <RateJune>
			ReportField reportField248 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateJune", new BigInteger("248"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.248", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField248);

			// (249) <RateJuly>
			ReportField reportField249 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateJuly", new BigInteger("249"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.249", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField249);

			// (250) <RateAugust>
			ReportField reportField250 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateAugust", new BigInteger("250"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.250", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField250);

			// (251) <RateSeptember>
			ReportField reportField251 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateSeptember",
					new BigInteger("251"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "Historical Risk Profile", "DECIMAL", "40.251", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField251);

			// (252) <RateOctober>
			ReportField reportField252 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateOctober", new BigInteger("252"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.252", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField252);

			// (253) <RateNovember>
			ReportField reportField253 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateNovember", new BigInteger("253"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.253", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField253);

			// (254) <RateDecember>
			ReportField reportField254 = new ReportField(reportCatalog,
					reportFieldx90, "N", "RateDecember", new BigInteger("254"),
					"[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Historical Risk Profile", "DECIMAL", "40.254", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField254);

			ReportField reportFieldx91 = new ReportField(reportCatalog,
					reportFieldx87, "X", "Subscription", new BigInteger("0"),
					null, "", null, null, "40.2", "0,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx91);

			// (255) <QuantityJanuary>
			ReportField reportField255 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityJanuary", new BigInteger(
							"255"), "[0-9]{1,15}?", "",
					"Historical Risk Profile", "NUMBER", "40.255", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField255);

			// (256) <QuantityFebruary>
			ReportField reportField256 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityFebruary", new BigInteger(
							"256"), "[0-9]{1,15}?", "",
					"Historical Risk Profile", "NUMBER", "40.256", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField256);

			// (257) <QuantityMarch>
			ReportField reportField257 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityMarch",
					new BigInteger("257"), "[0-9]{1,15}?", "",
					"Historical Risk Profile", "NUMBER", "40.257", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField257);

			// (258) <QuantityApril>
			ReportField reportField258 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityApril",
					new BigInteger("258"), "[0-9]{1,15}?", "",
					"Historical Risk Profile", "NUMBER", "40.258", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField258);

			// (259) <QuantityMay>
			ReportField reportField259 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityMay", new BigInteger("259"),
					"[0-9]{1,15}?", "", "Historical Risk Profile", "NUMBER",
					"40.259", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField259);

			// (260) <QuantityJune>
			ReportField reportField260 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityJune", new BigInteger("260"),
					"[0-9]{1,15}?", "", "Historical Risk Profile", "NUMBER",
					"40.260", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField260);

			// (261) <QuantityJuly>
			ReportField reportField261 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityJuly", new BigInteger("261"),
					"[0-9]{1,15}?", "", "Historical Risk Profile", "NUMBER",
					"40.261", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField261);

			// (262) <QuantityAugust>
			ReportField reportField262 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityAugust",
					new BigInteger("262"), "[0-9]{1,15}?", "",
					"Historical Risk Profile", "NUMBER", "40.262", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField262);

			// (263) <QuantitySeptember>
			ReportField reportField263 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantitySeptember", new BigInteger(
							"263"), "[0-9]{1,15}?", "",
					"Historical Risk Profile", "NUMBER", "40.263", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField263);

			// (264) <QuantityOctober>
			ReportField reportField264 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityOctober", new BigInteger(
							"264"), "[0-9]{1,15}?", "",
					"Historical Risk Profile", "NUMBER", "40.264", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField264);

			// (265) <QuantityNovember>
			ReportField reportField265 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityNovember", new BigInteger(
							"265"), "[0-9]{1,15}?", "",
					"Historical Risk Profile", "NUMBER", "40.265", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField265);

			// (266) <QuantityDecember>
			ReportField reportField266 = new ReportField(reportCatalog,
					reportFieldx91, "N", "QuantityDecember", new BigInteger(
							"266"), "[0-9]{1,15}?", "",
					"Historical Risk Profile", "NUMBER", "40.266", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField266);

			ReportField reportFieldx92 = new ReportField(reportCatalog,
					reportFieldx87, "X", "Redemption", new BigInteger("0"),
					null, "", null, null, "40.1", "0,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx92);

			// (267) <QuantityJanuary>
			ReportField reportField267 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityJanuary", new BigInteger(
							"267"), "[0-9]{1,15}?", "",
					"Historical Risk Profile", "NUMBER", "40.267", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField267);

			// (268) <QuantityFebruary>
			ReportField reportField268 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityFebruary", new BigInteger(
							"268"), "[0-9]{1,15}?", "",
					"Historical Risk Profile", "NUMBER", "40.268", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField268);

			// (269) <QuantityMarch>
			ReportField reportField269 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityMarch",
					new BigInteger("269"), "[0-9]{1,15}?", "",
					"Historical Risk Profile", "NUMBER", "40.269", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField269);

			// (270) <QuantityApril>
			ReportField reportField270 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityApril",
					new BigInteger("270"), "[0-9]{1,15}?", "",
					"Historical Risk Profile", "NUMBER", "40.270", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField270);

			// (271) <QuantityMay>
			ReportField reportField271 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityMay", new BigInteger("271"),
					"[0-9]{1,15}?", "", "Historical Risk Profile", "NUMBER",
					"40.271", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField271);

			// (272) <QuantityJune>
			ReportField reportField272 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityJune", new BigInteger("272"),
					"[0-9]{1,15}?", "", "Historical Risk Profile", "NUMBER",
					"40.272", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField272);

			// (273) <QuantityJuly>
			ReportField reportField273 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityJuly", new BigInteger("273"),
					"[0-9]{1,15}?", "", "Historical Risk Profile", "NUMBER",
					"40.273", "0,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField273);

			// (274) <QuantityAugust>
			ReportField reportField274 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityAugust",
					new BigInteger("274"), "[0-9]{1,15}?", "",
					"Historical Risk Profile", "NUMBER", "40.274", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField274);

			// (275) <QuantitySeptember>
			ReportField reportField275 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantitySeptember", new BigInteger(
							"275"), "[0-9]{1,15}?", "",
					"Historical Risk Profile", "NUMBER", "40.275", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField275);

			// (276) <QuantityOctober>
			ReportField reportField276 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityOctober", new BigInteger(
							"276"), "[0-9]{1,15}?", "",
					"Historical Risk Profile", "NUMBER", "40.276", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField276);

			// (277) <QuantityNovember>
			ReportField reportField277 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityNovember", new BigInteger(
							"277"), "[0-9]{1,15}?", "",
					"Historical Risk Profile", "NUMBER", "40.277", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField277);

			// (278) <QuantityDecember>
			ReportField reportField278 = new ReportField(reportCatalog,
					reportFieldx92, "N", "QuantityDecember", new BigInteger(
							"278"), "[0-9]{1,15}?", "",
					"Historical Risk Profile", "NUMBER", "40.278", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField278);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx93 = new ReportField(reportCatalog,
					reportFieldx41, "X", "StressTests", new BigInteger("0"),
					null, "", null, null, "41.1", "1,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx93);

			// (279) <StressTestsResultArticle15>
			ReportField reportField279 = new ReportField(reportCatalog,
					reportFieldx93, "Z", "StressTestsResultArticle15",
					new BigInteger("279"), ".{1,32000}", "", "Stress Tests",
					"StressTestsResultType", "41.279", "1,1", versionField,
					null, null, null, versionAdmin);
			reportField279.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField279);

			// (280) <StressTestsResultArticle16>
			ReportField reportField280 = new ReportField(reportCatalog,
					reportFieldx93, "Z", "StressTestsResultArticle16",
					new BigInteger("280"), ".{1,32000}", "", "Stress Tests",
					"StressTestsResultType", "41.280", "1,1", versionField,
					null, null, null, versionAdmin);
			reportField280.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField280);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx94 = new ReportField(reportCatalog,
					reportFieldx5, "X", "AIFLeverageInfo", new BigInteger("0"),
					null, "", null, null, "42.1", "1,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx94);

			ReportField reportFieldx95 = new ReportField(reportCatalog,
					reportFieldx94, "X", "AIFLeverageArticle24-2",
					new BigInteger("0"), null, "", null, null, "42.1", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx95);

			// (281) <AllCounterpartyCollateralRehypothecationFlag>
			ReportField reportField281 = new ReportField(reportCatalog,
					reportFieldx95, "B",
					"AllCounterpartyCollateralRehypothecationFlag",
					new BigInteger("281"), "true|false", "",
					"AIF Leverage Info", "BOOLEAN", "42.281", "1,1",
					versionField, null, null, null, versionAdmin);
			reportField281.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField281);

			// (282) <AllCounterpartyCollateralRehypothecatedRate>
			ReportField reportField282 = new ReportField(reportCatalog,
					reportFieldx95, "N",
					"AllCounterpartyCollateralRehypothecatedRate",
					new BigInteger("282"), "[0-9]{1,3}(\\.[0-9]{1,2})?", "",
					"AIF Leverage Info", "PERCENT", "42.282", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField282);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx96 = new ReportField(reportCatalog,
					reportFieldx95, "X", "SecuritiesCashBorrowing",
					new BigInteger("0"), null, "", null, null, "43.1", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx96);

			// (283) <UnsecuredBorrowingAmount>
			ReportField reportField283 = new ReportField(reportCatalog,
					reportFieldx96, "N", "UnsecuredBorrowingAmount",
					new BigInteger("283"), "[0-9]{1,15}?", "",
					"Securities Cash Borrowing", "NUMBER", "43.283", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField283);

			// (284) <SecuredBorrowingPrimeBrokerageAmount>
			ReportField reportField284 = new ReportField(reportCatalog,
					reportFieldx96, "N",
					"SecuredBorrowingPrimeBrokerageAmount", new BigInteger(
							"284"), "[0-9]{1,15}?", "",
					"Securities Cash Borrowing", "NUMBER", "43.284", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField284);

			// (285) <SecuredBorrowingReverseRepoAmount>
			ReportField reportField285 = new ReportField(reportCatalog,
					reportFieldx96, "N", "SecuredBorrowingReverseRepoAmount",
					new BigInteger("285"), "[0-9]{1,15}?", "",
					"Securities Cash Borrowing", "NUMBER", "43.285", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField285);

			// (286) <SecuredBorrowingOtherAmount>
			ReportField reportField286 = new ReportField(reportCatalog,
					reportFieldx96, "N", "SecuredBorrowingOtherAmount",
					new BigInteger("286"), "[0-9]{1,15}?", "",
					"Securities Cash Borrowing", "NUMBER", "43.286", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField286);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx97 = new ReportField(reportCatalog,
					reportFieldx95, "X", "FinancialInstrumentBorrowing",
					new BigInteger("0"), null, "", null, null, "44.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx97);

			// (287) <ExchangedTradedDerivativesExposureValue>
			ReportField reportField287 = new ReportField(reportCatalog,
					reportFieldx97, "N",
					"ExchangedTradedDerivativesExposureValue", new BigInteger(
							"287"), "[0-9]{1,15}?", "",
					"Financial Instrument Borrowing", "NUMBER", "44.287",
					"0,1", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField287);

			// (288) <OTCDerivativesAmount>
			ReportField reportField288 = new ReportField(reportCatalog,
					reportFieldx97, "N", "OTCDerivativesAmount",
					new BigInteger("288"), "[0-9]{1,15}?", "",
					"Financial Instrument Borrowing", "NUMBER", "44.288",
					"0,1", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField288);

			// (289) <ShortPositionBorrowedSecuritiesValue>
			ReportField reportField289 = new ReportField(reportCatalog,
					reportFieldx95, "N",
					"ShortPositionBorrowedSecuritiesValue", new BigInteger(
							"289"), "[0-9]{1,15}?", "",
					"Financial Instrument Borrowing", "NUMBER", "44.289",
					"0,1", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField289);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx98 = new ReportField(reportCatalog,
					reportFieldx95, "X", "ControlledStructures",
					new BigInteger("0"), null, "", null, null, "45.1", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx98);
			ReportField reportFieldx99 = new ReportField(reportCatalog,
					reportFieldx98, "X", "ControlledStructure", new BigInteger(
							"0"), null, "", null, null, "45.1", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx99);
			ReportField reportFieldx100 = new ReportField(reportCatalog,
					reportFieldx99, "X", "ControlledStructureIdentification",
					new BigInteger("0"), null, "", null, null, "45.1", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx100);

			// (290) <EntityName>
			ReportField reportField290 = new ReportField(reportCatalog,
					reportFieldx100, "Z", "EntityName", new BigInteger("290"),
					".{1,300}", "", "Controlled Structures", "NAME", "45.290",
					"0,n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField290);

			// (291) <EntityIdentificationLEI>
			ReportField reportField291 = new ReportField(reportCatalog,
					reportFieldx100, "A", "EntityIdentificationLEI",
					new BigInteger("291"), "[0-9a-zA-Z]{18}[0-9]{2}", "",
					"Controlled Structures", "LEICodeType", "45.291", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField291);

			// (292) <EntityIdentificationBIC>
			ReportField reportField292 = new ReportField(reportCatalog,
					reportFieldx100, "A", "EntityIdentificationBIC",
					new BigInteger("292"), ".{11}", "",
					"Controlled Structures", "BICCodeType", "45.292", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField292);

			// (293) <ControlledStructureExposureValue>
			ReportField reportField293 = new ReportField(reportCatalog,
					reportFieldx99, "N", "ControlledStructureExposureValue",
					new BigInteger("293"), "[0-9]{1,15}?", "",
					"Controlled Structures", "NUMBER", "45.293", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField293);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx101 = new ReportField(reportCatalog,
					reportFieldx95, "X", "LeverageAIF", new BigInteger("0"),
					null, "", null, null, "46.1", "1,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx101);

			// (294) <GrossMethodRate>
			ReportField reportField294 = new ReportField(reportCatalog,
					reportFieldx101, "N", "GrossMethodRate", new BigInteger(
							"294"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?", "",
					"Leverage AIF", "DECIMAL", "46.294", "1,1", versionField,
					null, null, null, versionAdmin);
			reportField294.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField294);

			// (295) <CommitmentMethodRate>
			ReportField reportField295 = new ReportField(reportCatalog,
					reportFieldx101, "N", "CommitmentMethodRate",
					new BigInteger("295"), "[+|-]?[0-9]{1,15}(\\.[0-9]{1,2})?",
					"", "Leverage AIF", "DECIMAL", "46.295", "1,1",
					versionField, null, null, null, versionAdmin);
			reportField295.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField295);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx102 = new ReportField(reportCatalog,
					reportFieldx94, "X", "AIFLeverageArticle24-4",
					new BigInteger("0"), null, "", null, null, "47.1", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx102);

			ReportField reportFieldx103 = new ReportField(reportCatalog,
					reportFieldx102, "X", "BorrowingSource",
					new BigInteger("0"), null, "", null, null, "47.1", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx103);

			// (296) <Ranking>
			ReportField reportField296 = new ReportField(reportCatalog,
					reportFieldx103, "N", "Ranking", new BigInteger("296"),
					"[0-9]{1}", "", "Borrowing Source", "FiveRankingType",
					"47.296", "5,5", versionField, null, null, null,
					versionAdmin);
			reportField296.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField296);

			// (297) <BorrowingSourceFlag>
			ReportField reportField297 = new ReportField(reportCatalog,
					reportFieldx103, "B", "BorrowingSourceFlag",
					new BigInteger("297"), "true|false", "",
					"Borrowing Source", "BOOLEAN", "47.297", "5,5",
					versionField, null, null, null, versionAdmin);
			reportField297.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField297);

			ReportField reportFieldx104 = new ReportField(reportCatalog,
					reportFieldx103, "X", "SourceIdentification",
					new BigInteger("0"), null, "", null, null, "47.2", "1,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx104);

			// (298) <EntityName>
			ReportField reportField298 = new ReportField(reportCatalog,
					reportFieldx104, "Z", "EntityName", new BigInteger("298"),
					".{1,300}", "", "Borrowing Source", "NAME", "47.298",
					"0,5", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField298);

			// (299) <EntityIdentificationLEI>
			ReportField reportField299 = new ReportField(reportCatalog,
					reportFieldx104, "A", "EntityIdentificationLEI",
					new BigInteger("299"), "[0-9a-zA-Z]{18}[0-9]{2}", "",
					"Borrowing Source", "LEICodeType", "47.299", "0,5",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField299);

			// (300) <EntityIdentificationBIC>
			ReportField reportField300 = new ReportField(reportCatalog,
					reportFieldx104, "A", "EntityIdentificationBIC",
					new BigInteger("300"), ".{11}", "", "Borrowing Source",
					"BICCodeType", "47.300", "0,5", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField300);

			// (301) <LeverageAmount>
			ReportField reportField301 = new ReportField(reportCatalog,
					reportFieldx103, "N", "LeverageAmount", new BigInteger(
							"301"), "[0-9]{1,15}?", "", "Borrowing Source",
					"NUMBER", "47.301", "0,5", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField301);

			// //////////////////////////////////////////////////////////////

			ReportField reportFieldx105 = new ReportField(reportCatalog,
					reportFieldx1, "X", "CancellationAIFRecordInfo",
					new BigInteger("0"), null, "", null, null, "48.1", "0,1",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportFieldx105);

			// (303) <CancelledAIFNationalCode>
			ReportField reportField303 = new ReportField(reportCatalog,
					reportFieldx105, "Z", "CancelledAIFNationalCode",
					new BigInteger("303"), ".{1,30}", "", "Cancellation Info",
					"AIFNationalCodeType", "48.303", "0,1", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField303);

			// (304) <CancelledAIFMNationalCode>
			ReportField reportField304 = new ReportField(reportCatalog,
					reportFieldx105, "Z", "CancelledAIFMNationalCode",
					new BigInteger("304"), ".{1,30}", "", "Cancellation Info",
					"AIFMNationalCodeType", "48.304", "0,1", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField304);

			// (305) <CancelledReportingPeriodType>
			ReportField reportField305 = new ReportField(reportCatalog,
					reportFieldx105, "A", "CancelledReportingPeriodType",
					new BigInteger("305"), ".{2}", "", "Cancellation Info",
					"ReportingPeriodTypeType", "48.305", "0,1", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField305);

			// (306) <CancelledReportingPeriodYear>
			ReportField reportField306 = new ReportField(reportCatalog,
					reportFieldx105, "D", "CancelledReportingPeriodYear",
					new BigInteger("306"), "[0-9]{4}?", "",
					"Cancellation Info", "YEAR", "48.306", "0,1", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField306);

			// (307) <CancelledRecordFlag>
			ReportField reportField307 = new ReportField(reportCatalog,
					reportFieldx105, "A", "CancelledRecordFlag",
					new BigInteger("307"), ".{1}", "", "Cancellation Info",
					"CancelledRecordFlagType", "48.307", "0,1", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField307);

			// /////////////////////////////////////////////////////////////////
			// SEMANTIC RULES

			List<ReportSemantic> reportSemantics = new ArrayList<ReportSemantic>();

			// (1) <ReportingMemberState>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField1,
							"(" + reportField1.getReportFieldNum().toString()
									+ ") " + reportField1.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"ReportingMemberState\", \"1\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(1).", versionAdmin));

			// (2) <Version>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField2,
							"(" + reportField2.getReportFieldNum().toString()
									+ ") " + reportField2.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"Version\", \"2\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(2).", versionAdmin));

			// (3) <CreationDateAndTime>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField3,
							"(" + reportField3.getReportFieldNum().toString()
									+ ") " + reportField3.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"CreationDateAndTime\", \"3\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(3).", versionAdmin));

			// (4) <FilingType>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField4,
							"(" + reportField4.getReportFieldNum().toString()
									+ ") " + reportField4.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"FilingType\", \"4\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(4).", versionAdmin));

			// (5) <AIFContentType>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField5,
							"(" + reportField5.getReportFieldNum().toString()
									+ ") " + reportField5.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"AIFContentType\", \"5\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(5).", versionAdmin));

			// (6) <ReportingPeriodStartDate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField6,
							"(" + reportField6.getReportFieldNum().toString()
									+ ") " + reportField6.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"ReportingPeriodStartDate\", \"6\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(6).", versionAdmin));

			// (7) <ReportingPeriodEndDate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField7,
							"(" + reportField7.getReportFieldNum().toString()
									+ ") " + reportField7.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"ReportingPeriodEndDate\", \"7\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(7).", versionAdmin));

			// (8) <ReportingPeriodType>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField8,
							"(" + reportField8.getReportFieldNum().toString()
									+ ") " + reportField8.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"ReportingPeriodType\", \"8\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(8).", versionAdmin));

			// (9) <ReportingPeriodYear>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField9,
							"(" + reportField9.getReportFieldNum().toString()
									+ ") " + reportField9.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"ReportingPeriodYear\", \"9\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(9).", versionAdmin));

			// (10) <AIFReportingObligationChangeFrequencyCode>
			// (11) <AIFReportingObligationChangeContentsCode>

			// (12) <AIFReportingObligationChangeQuarter>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField12,
							"(" + reportField12.getReportFieldNum().toString()
									+ ") " + reportField12.getReportFieldName(),
							"if( "
									+ "\n ( ( ReportUtilities.searchData(reportDatas, \"AIFReportingObligationChangeFrequencyCode\", \"10\", null) != null "
									+ "\n || ReportUtilities.searchData(reportDatas, \"AIFReportingObligationChangeContentsCode\", \"11\", null) != null "
									+ "\n ) && ReportUtilities.searchData(reportDatas, \"AIFReportingObligationChangeQuarter\", \"12\", null) != null )"
									+ "\n || (ReportUtilities.searchData(reportDatas, \"AIFReportingObligationChangeFrequencyCode\", \"10\", null) == null "
									+ "\n && ReportUtilities.searchData(reportDatas, \"AIFReportingObligationChangeContentsCode\", \"11\", null) == null "
									+ "\n &&  ReportUtilities.searchData(reportDatas, \"AIFReportingObligationChangeQuarter\", \"12\", null) == null)"
									+ ")" + "\n result = \"ok\"",
							null,
							"Fill field(12) only when field(10) or field(11) have content.",
							versionAdmin));

			// (13) <LastReportingFlag>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField13,
							"(" + reportField13.getReportFieldNum().toString()
									+ ") " + reportField13.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"LastReportingFlag\", \"13\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(13).", versionAdmin));

			// (14) <QuestionNumber>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField14,
							"(" + reportField14.getReportFieldNum().toString()
									+ ") " + reportField14.getReportFieldName(),
							"if (ReportUtilities.dependencyRepeData(reportDatas, \"QuestionNumber\", \"14\", \"AssumptionDescription\", \"15\") == null) "
									+ "\n result = \"ok\"; \n else {"
									+ "\n problem = ReportUtilities.dependencyRepeData(reportDatas, \"QuestionNumber\", \"14\", \"AssumptionDescription\", \"15\");"
									+ "}",
							null,
							"Field(14) is mandatory when field(15) has content.",
							versionAdmin));

			// (15) <AssumptionDescription>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField15,
							"(" + reportField15.getReportFieldNum().toString()
									+ ") " + reportField15.getReportFieldName(),
							"if (ReportUtilities.dependencyRepeData(reportDatas, \"AssumptionDescription\", \"15\", \"QuestionNumber\", \"14\") == null) "
									+ "\n result = \"ok\"; \n else {"
									+ "\n problem = ReportUtilities.dependencyRepeData(reportDatas, \"AssumptionDescription\", \"15\", \"QuestionNumber\", \"14\");"
									+ "}",
							null,
							"Field(15) is mandatory when field(14) has content.",
							versionAdmin));

			// (16) <AIFMNationalCode>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField16,
							"(" + reportField16.getReportFieldNum().toString()
									+ ") " + reportField16.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"AIFMNationalCode\", \"16\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(16).", versionAdmin));

			// (17) <AIFNationalCode>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField17,
							"(" + reportField17.getReportFieldNum().toString()
									+ ") " + reportField17.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"AIFNationalCode\", \"17\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(17).", versionAdmin));

			// (18) <AIFName>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField18,
							"(" + reportField18.getReportFieldNum().toString()
									+ ") " + reportField18.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"AIFName\", \"18\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(18).", versionAdmin));

			// (19) <AIFEEAFlag>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField19,
							"(" + reportField19.getReportFieldNum().toString()
									+ ") " + reportField19.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"AIFEEAFlag\", \"19\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(19).", versionAdmin));

			// (20) <AIFReportingCode>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField20,
							"(" + reportField20.getReportFieldNum().toString()
									+ ") " + reportField20.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"AIFReportingCode\", \"20\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(20).", versionAdmin));

			// (21) <AIFDomicile>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField21,
							"(" + reportField21.getReportFieldNum().toString()
									+ ") " + reportField21.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"AIFDomicile\", \"21\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(21).", versionAdmin));

			// (22) <InceptionDate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField22,
							"(" + reportField22.getReportFieldNum().toString()
									+ ") " + reportField22.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"InceptionDate\", \"22\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(22).", versionAdmin));

			// (23) <AIFNoReportingFlag>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField23,
							"(" + reportField23.getReportFieldNum().toString()
									+ ") " + reportField23.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"AIFNoReportingFlag\", \"23\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(23).", versionAdmin));

			// (24) <AIFIdentifierLEI>
			// (25) <AIFIdentifierISIN>
			// (26) <AIFIdentifierCUSIP>
			// (27) <AIFIdentifierSEDOL>
			// (28) <AIFIdentifierTicker>
			// (29) <AIFIdentifierRIC>
			// (30) <AIFIdentifierECB>

			// (31) <Old_ReportingMemberState>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField31,
							"(" + reportField31.getReportFieldNum().toString()
									+ ") " + reportField31.getReportFieldName(),
							"if ( "
									+ "(ReportUtilities.searchData(reportDatas, \"Old_AIFNationalCode\", \"32\", null) != null"
									+ " && ReportUtilities.searchData(reportDatas, \"Old_ReportingMemberState\", \"31\", null) != null)"
									+ " || ReportUtilities.searchData(reportDatas, \"Old_AIFNationalCode\", \"32\", null) == null) "
									+ "\n result = \"ok\"", null,
							"Fill field(31) if field(32) has content",
							versionAdmin));

			// (32) <Old_AIFNationalCode>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField32,
							"(" + reportField32.getReportFieldNum().toString()
									+ ") " + reportField32.getReportFieldName(),
							"if ( "
									+ "(ReportUtilities.searchData(reportDatas, \"Old_ReportingMemberState\", \"31\", null) != null"
									+ " && ReportUtilities.searchData(reportDatas, \"Old_AIFNationalCode\", \"32\", null) != null)"
									+ " || ReportUtilities.searchData(reportDatas, \"Old_ReportingMemberState\", \"31\", null) == null) "
									+ "\n result = \"ok\"", null,
							"Fill field(32) if field(31) has content",
							versionAdmin));

			// (33) <ShareClassFlag>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField33,
							"(" + reportField33.getReportFieldNum().toString()
									+ ") " + reportField33.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"ShareClassFlag\", \"33\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(33).", versionAdmin));

			// (34) <ShareClassNationalCode>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField34,
							"(" + reportField34.getReportFieldNum().toString()
									+ ") " + reportField34.getReportFieldName(),
							"if ( ReportUtilities.dependencyFieldRepeDataEmpty(reportDatas, \"ShareClassNationalCode\", \"34\", \"ShareClassFlag\", \"33\", \"false\") == null) "
									+ "\n result = \"ok\"; \n else {"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataEmpty(reportDatas, \"ShareClassNationalCode\", \"34\", \"ShareClassFlag\", \"33\", \"false\");"
									+ "}", null,
							"Field(34) is forbidden when field(33) is false.",
							versionAdmin));

			// (35) <ShareClassIdentifierISIN>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField35,
							"(" + reportField35.getReportFieldNum().toString()
									+ ") " + reportField35.getReportFieldName(),
							"if ( ReportUtilities.dependencyFieldRepeDataEmpty(reportDatas, \"ShareClassIdentifierISIN\", \"35\", \"ShareClassFlag\", \"33\", \"false\") == null) "
									+ "\n result = \"ok\"; \n else {"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataEmpty(reportDatas, \"ShareClassIdentifierISIN\", \"35\", \"ShareClassFlag\", \"33\", \"false\");"
									+ "}", null,
							"Field(35) is forbidden when field(33) is false.",
							versionAdmin));

			// (36) <ShareClassIdentifierSEDOL>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField36,
							"(" + reportField36.getReportFieldNum().toString()
									+ ") " + reportField36.getReportFieldName(),
							"if ( ReportUtilities.dependencyFieldRepeDataEmpty(reportDatas, \"ShareClassIdentifierSEDOL\", \"36\", \"ShareClassFlag\", \"33\", \"false\") == null) "
									+ "\n result = \"ok\"; \n else {"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataEmpty(reportDatas, \"ShareClassIdentifierSEDOL\", \"36\", \"ShareClassFlag\", \"33\", \"false\");"
									+ "}", null,
							"Field(36) is forbidden when field(33) is false.",
							versionAdmin));

			// (37) <ShareClassIdentifierCUSIP>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField37,
							"(" + reportField37.getReportFieldNum().toString()
									+ ") " + reportField37.getReportFieldName(),
							"if ( ReportUtilities.dependencyFieldRepeDataEmpty(reportDatas, \"ShareClassIdentifierCUSIP\", \"37\", \"ShareClassFlag\", \"33\", \"false\") == null) "
									+ "\n result = \"ok\"; \n else {"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataEmpty(reportDatas, \"ShareClassIdentifierCUSIP\", \"37\", \"ShareClassFlag\", \"33\", \"false\");"
									+ "}", null,
							"Field(37) is forbidden when field(33) is false.",
							versionAdmin));

			// (38) <ShareClassIdentifierTicker>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField38,
							"(" + reportField38.getReportFieldNum().toString()
									+ ") " + reportField38.getReportFieldName(),
							"if ( ReportUtilities.dependencyFieldRepeDataEmpty(reportDatas, \"ShareClassIdentifierTicker\", \"38\", \"ShareClassFlag\", \"33\", \"false\") == null) "
									+ "\n result = \"ok\"; \n else {"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataEmpty(reportDatas, \"ShareClassIdentifierTicker\", \"38\", \"ShareClassFlag\", \"33\", \"false\");"
									+ "}", null,
							"Field(38) is forbidden when field(33) is false.",
							versionAdmin));

			// (39) <ShareClassIdentifierRIC>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField39,
							"(" + reportField39.getReportFieldNum().toString()
									+ ") " + reportField39.getReportFieldName(),
							"if ( ReportUtilities.dependencyFieldRepeDataEmpty(reportDatas, \"ShareClassIdentifierRIC\", \"39\", \"ShareClassFlag\", \"33\", \"false\") == null) "
									+ "\n result = \"ok\"; \n else {"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataEmpty(reportDatas, \"ShareClassIdentifierRIC\", \"39\", \"ShareClassFlag\", \"33\", \"false\");"
									+ "}", null,
							"Field(39) is forbidden when field(33) is false.",
							versionAdmin));

			// (40) <ShareClassName>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField40,
							"(" + reportField40.getReportFieldNum().toString()
									+ ") " + reportField40.getReportFieldName(),
							"if ( ReportUtilities.dependencyFieldRepeDataEmpty(reportDatas, \"ShareClassName\", \"40\", \"ShareClassFlag\", \"33\", \"false\") == null) "
									+ "\n result = \"ok\"; \n else {"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataEmpty(reportDatas, \"ShareClassName\", \"40\", \"ShareClassFlag\", \"33\", \"false\");"
									+ "}", null,
							"Field(40) is forbidden when field(33) is false.",
							versionAdmin));

			// (41) <AIFMasterFeederStatus>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField41,
							"(" + reportField41.getReportFieldNum().toString()
									+ ") " + reportField41.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"AIFMasterFeederStatus\", \"41\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(41).", versionAdmin));

			// (42) <AIFName>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField42,
							"(" + reportField42.getReportFieldNum().toString()
									+ ") " + reportField42.getReportFieldName(),
							"if ( (ReportUtilities.searchData(reportDatas, \"AIFMasterFeederStatus\", \"41\", null) != null "
									+ " && ReportUtilities.searchData(reportDatas, \"AIFMasterFeederStatus\", \"41\", null).equals(\"FEEDER\") "
									+ " && ReportUtilities.searchBlockList(reportDatas, \"AIFName\", \"42\").size() > 0 )"
									+ " || ( "
									+ " ( (ReportUtilities.searchData(reportDatas, \"AIFMasterFeederStatus\", \"41\", null) != null "
									+ " && !ReportUtilities.searchData(reportDatas, \"AIFMasterFeederStatus\", \"41\", null).equals(\"FEEDER\") ) "
									+ " || ReportUtilities.searchData(reportDatas, \"AIFMasterFeederStatus\", \"41\", null) == null )"
									+ " && ReportUtilities.searchBlockList(reportDatas, \"AIFName\", \"42\").size() == 0 "
									+ ") ) " + "\n result = \"ok\"",
							null,
							"Field(42) are mandatory when field(41) = FEEDER, forbidden otherwise.",
							versionAdmin));

			// (43) <ReportingMemberState>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField43,
							"(" + reportField43.getReportFieldNum().toString()
									+ ") " + reportField43.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"AIFMasterFeederStatus\", \"41\", null) != null "
									+ " && ReportUtilities.searchData(reportDatas, \"AIFMasterFeederStatus\", \"41\", null).equals(\"FEEDER\") "
									+ " || ReportUtilities.searchBlockList(reportDatas, \"ReportingMemberState\", \"43\").size() == 0 "
									+ ") " + "\n result = \"ok\"",
							null,
							"Field(43) are forbidden when field(41) different to FEEDER.",
							versionAdmin));

			// (44) <AIFNationalCode>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField44,
							"(" + reportField44.getReportFieldNum().toString()
									+ ") " + reportField44.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeData(reportDatas, \"AIFNationalCode\", \"44\", \"ReportingMemberState\", \"43\") == null )"
									+ "\n result = \"ok\"; \n else {"
									+ "\n problem = ReportUtilities.dependencyRepeData(reportDatas, \"AIFNationalCode\", \"44\", \"ReportingMemberState\", \"43\");"
									+ "}",
							null,
							"Field(44) mandatory when field(43) has content and forbidden otherwise.",
							versionAdmin));

			// (45) <EntityName>
			// (46) <EntityIdentificationLEI>
			// (47) <EntityIdentificationBIC>

			// (48) <AUMAmountInBaseCurrency>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField48,
							"(" + reportField48.getReportFieldNum().toString()
									+ ") " + reportField48.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"AUMAmountInBaseCurrency\", \"48\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(48).", versionAdmin));

			// (49) <BaseCurrency>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField49,
							"(" + reportField49.getReportFieldNum().toString()
									+ ") " + reportField49.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"BaseCurrency\", \"49\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(49).", versionAdmin));

			// (50) <FXEURRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField50,
							"(" + reportField50.getReportFieldNum().toString()
									+ ") " + reportField50.getReportFieldName(),
							"if (ReportUtilities.searchData(reportDatas, \"BaseCurrency\", \"49\", null) == null) {"
									+ "  result = \"ok\"; "
									+ " } else {"
									+ " if (ReportUtilities.searchData(reportDatas, \"BaseCurrency\", \"49\", null) != null "
									+ "&& ReportUtilities.searchData(reportDatas, \"BaseCurrency\", \"49\", null).equals(\"EUR\")"
									+ " &&  ReportUtilities.searchData(reportDatas, \"FXEURRate\", \"50\", null) == null) {"
									+ " result = \"ok\"; "
									+ " } else {"
									+ "  if (ReportUtilities.searchData(reportDatas, \"BaseCurrency\", \"49\", null) != null "
									+ "&& !ReportUtilities.searchData(reportDatas, \"BaseCurrency\", \"49\", null).equals(\"EUR\")"
									+ " && ReportUtilities.searchData(reportDatas, \"FXEURRate\", \"50\", null) != null)"
									+ " result = \"ok\"; " + " }" + "}", null,
							"Fill field(50) only when field(49) not EUR.",
							versionAdmin));

			// (51) <FXEURReferenceRateType>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField51,
							"(" + reportField51.getReportFieldNum().toString()
									+ ") " + reportField51.getReportFieldName(),
							"if (ReportUtilities.searchData(reportDatas, \"BaseCurrency\", \"49\", null) == null) {"
									+ "  result = \"ok\"; "
									+ "} else {"
									+ " if (ReportUtilities.searchData(reportDatas, \"BaseCurrency\", \"49\", null) != null "
									+ " && ReportUtilities.searchData(reportDatas, \"BaseCurrency\", \"49\", null).equals(\"EUR\")"
									+ " && ReportUtilities.searchData(reportDatas, \"FXEURReferenceRateType\", \"51\", null) == null) {"
									+ " result = \"ok\"; "
									+ " } else {"
									+ "  if (ReportUtilities.searchData(reportDatas, \"BaseCurrency\", \"49\", null) != null "
									+ " && !ReportUtilities.searchData(reportDatas, \"BaseCurrency\", \"49\", null).equals(\"EUR\")"
									+ " && ReportUtilities.searchData(reportDatas, \"FXEURReferenceRateType\", \"51\", null) != null)"
									+ " result = \"ok\"; " + " }" + "}", null,
							"Fill field(51) only when field(49) not EUR.",
							versionAdmin));

			// (52) <FXEUROtherReferenceRateDescription>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField52,
							"(" + reportField52.getReportFieldNum().toString()
									+ ") " + reportField52.getReportFieldName(),
							"if (ReportUtilities.searchData(reportDatas, \"FXEURReferenceRateType\", \"51\", null) == null) {"
									+ "  result = \"ok\"; "
									+ "} else {"
									+ " if (ReportUtilities.searchData(reportDatas, \"FXEURReferenceRateType\", \"51\", null) != null "
									+ " && ReportUtilities.searchData(reportDatas, \"FXEURReferenceRateType\", \"51\", null).equals(\"OTH\")"
									+ " && ReportUtilities.searchData(reportDatas, \"FXEUROtherReferenceRateDescription\", \"52\", null) != null) {"
									+ " result = \"ok\"; "
									+ " } else {"
									+ "  if (ReportUtilities.searchData(reportDatas, \"FXEURReferenceRateType\", \"51\", null) != null "
									+ " && !ReportUtilities.searchData(reportDatas, \"FXEURReferenceRateType\", \"51\", null).equals(\"OTH\")"
									+ " && ReportUtilities.searchData(reportDatas, \"FXEUROtherReferenceRateDescription\", \"52\", null) == null)"
									+ " result = \"ok\"; " + " }" + "}", null,
							"Fill field(52) only when field(51) is OTH.",
							versionAdmin));

			// (53) <AIFNetAssetValue>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField53,
							"(" + reportField53.getReportFieldNum().toString()
									+ ") " + reportField53.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"AIFNetAssetValue\", \"53\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(53).", versionAdmin));

			// (54) <FirstFundingSourceCountry>
			// (55) <SecondFundingSourceCountry>
			// (56) <ThirdFundingSourceCountry>

			// (57) <PredominantAIFType>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField57,
							"(" + reportField57.getReportFieldNum().toString()
									+ ") " + reportField57.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"PredominantAIFType\", \"57\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(57).", versionAdmin));

			// (58-61) are optional but at least one is mandatory
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField58_H,
							"Investment Strategies (58-61)",
							"if (ReportUtilities.listBlockDatas(reportDatas, \"HedgeFundStrategyType\", \"58\").size() + "
									+ "ReportUtilities.listBlockDatas(reportDatas, \"PrivateEquityFundStrategyType\", \"58\").size() + "
									+ "ReportUtilities.listBlockDatas(reportDatas, \"FundOfFundsStrategyType\", \"58\").size() + "
									+ "ReportUtilities.listBlockDatas(reportDatas, \"OtherFundStrategyType\", \"58\").size() + "
									+ "ReportUtilities.listBlockDatas(reportDatas, \"RealEstateFundStrategyType\", \"58\").size() > 0 "
									+ "&& (ReportUtilities.searchData(reportDatas, \"PredominantAIFType\", \"57\", null) != null "
									+ "&& !ReportUtilities.searchData(reportDatas, \"PredominantAIFType\", \"57\", null).equals(\"NONE\") )"
									+ ") " + "\n result = \"ok\"; ",

							null,
							"Investment strategies (Fields 58-61) has to have at least one section filled when (57) different to NONE.",
							versionAdmin));

			// (58) <HedgeFundStrategyType>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField58_H,
							"("
									+ reportField58_H.getReportFieldNum()
											.toString() + ") "
									+ reportField58_H.getReportFieldName(),
							"if ( (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"HedgeFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"HFND\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"HedgeFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"PEQF\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"HedgeFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"REST\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"HedgeFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"FOFS\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"HedgeFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"OTHR\") == null "
									+ ") || (ReportUtilities.listBlockDatas(reportDatas, \"HedgeFundStrategyType\", \"58\").size() == 0) "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"HedgeFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"HFND\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"HedgeFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"HFND\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"HedgeFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"PEQF\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"HedgeFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"PEQF\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"HedgeFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"REST\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"HedgeFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"REST\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"HedgeFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"FOFS\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"HedgeFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"FOFS\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"HedgeFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"OTHR\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"HedgeFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"OTHR\");"
									+ "}",
							null,
							"Field(58_H) is mandatory when field(57) is HFND , PEQF, REST,  FOFS  OTHR.",
							versionAdmin));

			// (59) <H_PrimaryStrategyFlag>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField59_H,
							"("
									+ reportField59_H.getReportFieldNum()
											.toString() + ") "
									+ reportField59_H.getReportFieldName(),
							"if ( (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"H_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"HFND\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"H_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"PEQF\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"H_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"REST\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"H_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"FOFS\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"H_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"OTHR\") == null "
									+ ") || (ReportUtilities.listBlockDatas(reportDatas, \"H_PrimaryStrategyFlag\", \"59\").size() == 0) "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"H_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"HFND\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"H_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"HFND\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"H_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"PEQF\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"H_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"PEQF\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"H_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"REST\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"H_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"REST\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"H_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"FOFS\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"H_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"FOFS\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"H_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"OTHR\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"H_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"OTHR\");"
									+ "}",
							null,
							"Field(59_H) is mandatory when field(57) is HFND , PEQF, REST,  FOFS  OTHR.",
							versionAdmin));

			// (60) <H_StrategyNAVRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField60_H,
							"("
									+ reportField60_H.getReportFieldNum()
											.toString() + ") "
									+ reportField60_H.getReportFieldName(),
							"if ( (ReportUtilities.dependencyRepeDataDiffExists(reportDatas, \"H_StrategyNAVRate\", \"60\", \"HedgeFundStrategyType\", \"58\", \"MULT_HFND \") == null "
									+ ") || (ReportUtilities.listBlockDatas(reportDatas, \"H_StrategyNAVRate\", \"60\").size() == 0) "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataDiffExists(reportDatas, \"H_StrategyNAVRate\", \"60\", \"HedgeFundStrategyType\", \"58\", \"MULT_HFND \") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataDiffExists(reportDatas, \"H_StrategyNAVRate\", \"60\", \"HedgeFundStrategyType\", \"58\", \"MULT_HFND \");"
									+ "}",
							null,
							"Field(60_H) is mandatory when field(58) is different MULT_HFND .",
							versionAdmin));

			// has to sum 100 with all (60) fields
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField60_H,
							"("
									+ reportField60_H.getReportFieldNum()
											.toString() + ") "
									// + reportField60_H.getReportFieldName()
									+ " Sum 100",
							"if ( (ReportUtilities.searchBlockListNum(reportDatas, \"60\").size() > 0 "
									+ " &&  ReportUtilities.percentageFieldNum(reportDatas, \"60\", \"100\") )"
									+ " || ReportUtilities.searchBlockListNum(reportDatas, \"60\").size() == 0"
									+ " ) " + "\n result = \"ok\"", null,
							"Fields (60) has to sum 100 if there are values.",
							versionAdmin));

			// (61) <H_StrategyTypeOtherDescription>

			// (58) <PrivateEquityFundStrategyType>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField58_P,
							"("
									+ reportField58_P.getReportFieldNum()
											.toString() + ") "
									+ reportField58_P.getReportFieldName(),
							"if ( (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"PrivateEquityFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"HFND\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"PrivateEquityFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"PEQF\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"PrivateEquityFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"REST\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"PrivateEquityFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"FOFS\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"PrivateEquityFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"OTHR\") == null "
									+ ") || (ReportUtilities.listBlockDatas(reportDatas, \"PrivateEquityFundStrategyType\", \"58\").size() == 0) "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"PrivateEquityFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"HFND\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"PrivateEquityFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"HFND\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"PrivateEquityFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"PEQF\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"PrivateEquityFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"PEQF\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"PrivateEquityFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"REST\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"PrivateEquityFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"REST\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"PrivateEquityFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"FOFS\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"PrivateEquityFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"FOFS\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"PrivateEquityFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"OTHR\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"PrivateEquityFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"OTHR\");"
									+ "}",
							null,
							"Field(58_P) is mandatory when field(57) is HFND , PEQF, REST,  FOFS  OTHR.",
							versionAdmin));

			// (59) <P_PrimaryStrategyFlag>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField59_P,
							"("
									+ reportField59_P.getReportFieldNum()
											.toString() + ") "
									+ reportField59_P.getReportFieldName(),
							"if ( (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"P_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"HFND\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"P_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"PEQF\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"P_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"REST\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"P_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"FOFS\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"P_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"OTHR\") == null "
									+ ") || (ReportUtilities.listBlockDatas(reportDatas, \"P_PrimaryStrategyFlag\", \"59\").size() == 0) "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"P_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"HFND\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"P_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"HFND\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"P_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"PEQF\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"P_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"PEQF\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"P_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"REST\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"P_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"REST\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"P_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"FOFS\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"P_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"FOFS\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"P_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"OTHR\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"P_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"OTHR\");"
									+ "}",
							null,
							"Field(59_P) is mandatory when field(57) is HFND , PEQF, REST,  FOFS  OTHR.",
							versionAdmin));

			// (60) <P_StrategyNAVRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField60_P,
							"("
									+ reportField60_P.getReportFieldNum()
											.toString() + ") "
									+ reportField60_P.getReportFieldName(),
							"if ( (ReportUtilities.dependencyRepeDataDiffExists(reportDatas, \"P_StrategyNAVRate\", \"60\", \"PrivateEquityFundStrategyType\", \"58\", \"MULT_PEQF \") == null "
									+ ") || (ReportUtilities.listBlockDatas(reportDatas, \"P_StrategyNAVRate\", \"60\").size() == 0) "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataDiffExists(reportDatas, \"P_StrategyNAVRate\", \"60\", \"PrivateEquityFundStrategyType\", \"58\", \"MULT_PEQF \") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataDiffExists(reportDatas, \"P_StrategyNAVRate\", \"60\", \"PrivateEquityFundStrategyType\", \"58\", \"MULT_PEQF \");"
									+ "}",
							null,
							"Field(60_P) is mandatory when field(58) is different MULT_PEQF .",
							versionAdmin));

			// (61) <P_StrategyTypeOtherDescription>

			// (58) <FundOfFundsStrategyType>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField58_F,
							"("
									+ reportField58_F.getReportFieldNum()
											.toString() + ") "
									+ reportField58_F.getReportFieldName(),
							"if ( (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"FundOfFundsStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"HFND\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"FundOfFundsStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"PEQF\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"FundOfFundsStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"REST\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"FundOfFundsStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"FOFS\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"FundOfFundsStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"OTHR\") == null "
									+ ") || (ReportUtilities.listBlockDatas(reportDatas, \"FundOfFundsStrategyType\", \"58\").size() == 0) "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"FundOfFundsStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"HFND\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"FundOfFundsStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"HFND\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"FundOfFundsStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"PEQF\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"FundOfFundsStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"PEQF\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"FundOfFundsStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"REST\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"FundOfFundsStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"REST\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"FundOfFundsStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"FOFS\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"FundOfFundsStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"FOFS\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"FundOfFundsStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"OTHR\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"FundOfFundsStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"OTHR\");"
									+ "}",
							null,
							"Field(58_F) is mandatory when field(57) is HFND , PEQF, REST,  FOFS  OTHR.",
							versionAdmin));

			// (59) <F_PrimaryStrategyFlag>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField59_F,
							"("
									+ reportField59_F.getReportFieldNum()
											.toString() + ") "
									+ reportField59_F.getReportFieldName(),
							"if ( (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"F_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"HFND\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"F_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"PEQF\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"F_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"REST\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"F_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"FOFS\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"F_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"OTHR\") == null "
									+ ") || (ReportUtilities.listBlockDatas(reportDatas, \"F_PrimaryStrategyFlag\", \"59\").size() == 0) "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"F_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"HFND\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"F_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"HFND\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"F_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"PEQF\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"F_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"PEQF\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"F_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"REST\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"F_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"REST\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"F_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"FOFS\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"F_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"FOFS\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"F_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"OTHR\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"F_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"OTHR\");"
									+ "}",
							null,
							"Field(59_F) is mandatory when field(57) is HFND , PEQF, REST,  FOFS  OTHR.",
							versionAdmin));

			// (60) <F_StrategyNAVRate>

			// (61) <F_StrategyTypeOtherDescription>

			// (58) <OtherFundStrategyType>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField58_O,
							"("
									+ reportField58_O.getReportFieldNum()
											.toString() + ") "
									+ reportField58_O.getReportFieldName(),
							"if ( (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"OtherFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"HFND\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"OtherFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"PEQF\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"OtherFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"REST\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"OtherFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"FOFS\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"OtherFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"OTHR\") == null "
									+ ") || (ReportUtilities.listBlockDatas(reportDatas, \"OtherFundStrategyType\", \"58\").size() == 0) "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"OtherFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"HFND\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"OtherFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"HFND\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"OtherFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"PEQF\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"OtherFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"PEQF\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"OtherFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"REST\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"OtherFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"REST\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"OtherFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"FOFS\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"OtherFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"FOFS\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"OtherFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"OTHR\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"OtherFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"OTHR\");"
									+ "}",
							null,
							"Field(58_O) is mandatory when field(57) is HFND , PEQF, REST,  FOFS  OTHR.",
							versionAdmin));

			// (59) <O_PrimaryStrategyFlag>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField59_O,
							"("
									+ reportField59_O.getReportFieldNum()
											.toString() + ") "
									+ reportField59_O.getReportFieldName(),
							"if ( (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"O_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"HFND\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"O_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"PEQF\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"O_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"REST\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"O_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"FOFS\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"O_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"OTHR\") == null "
									+ ") || (ReportUtilities.listBlockDatas(reportDatas, \"O_PrimaryStrategyFlag\", \"59\").size() == 0) "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"O_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"HFND\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"O_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"HFND\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"O_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"PEQF\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"O_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"PEQF\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"O_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"REST\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"O_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"REST\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"O_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"FOFS\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"O_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"FOFS\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"O_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"OTHR\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"O_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"OTHR\");"
									+ "}",
							null,
							"Field(59_O) is mandatory when field(57) is HFND , PEQF, REST,  FOFS  OTHR.",
							versionAdmin));

			// (60) <O_StrategyNAVRate>

			// (61) <O_StrategyTypeOtherDescription>

			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField61_O,
							"("
									+ reportField61_O.getReportFieldNum()
											.toString() + ") "
									+ reportField61_O.getReportFieldName(),
							"if ( (ReportUtilities.dependencyRepeDataExist(reportDatas, \"O_StrategyTypeOtherDescription\", \"61\", \"OtherFundStrategyType\", \"58\", \"OTHR_HFND\") == null "
									+ " && ReportUtilities.dependencyRepeDataExist(reportDatas, \"O_StrategyTypeOtherDescription\", \"61\", \"OtherFundStrategyType\", \"58\", \"OTHR_PEQF\") == null "
									+ " && ReportUtilities.dependencyRepeDataExist(reportDatas, \"O_StrategyTypeOtherDescription\", \"61\", \"OtherFundStrategyType\", \"58\", \"OTHR_REST\") == null "
									+ " && ReportUtilities.dependencyRepeDataExist(reportDatas, \"O_StrategyTypeOtherDescription\", \"61\", \"OtherFundStrategyType\", \"58\", \"OTHR_FOFS\") == null "
									+ " && ReportUtilities.dependencyRepeDataExist(reportDatas, \"O_StrategyTypeOtherDescription\", \"61\", \"OtherFundStrategyType\", \"58\", \"OTHR_OTHF\") == null "
									+ ") || (ReportUtilities.listBlockDatas(reportDatas, \"O_StrategyTypeOtherDescription\", \"61\").size() == 0) "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataExist(reportDatas, \"O_StrategyTypeOtherDescription\", \"61\", \"OtherFundStrategyType\", \"58\", \"OTHR_HFND\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataExist(reportDatas, \"O_StrategyTypeOtherDescription\", \"61\", \"OtherFundStrategyType\", \"58\", \"OTHR_HFND\");"
									+ "\n if (ReportUtilities.dependencyRepeDataExist(reportDatas, \"O_StrategyTypeOtherDescription\", \"61\", \"OtherFundStrategyType\", \"58\", \"OTHR_PEQF\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataExist(reportDatas, \"O_StrategyTypeOtherDescription\", \"61\", \"OtherFundStrategyType\", \"58\", \"OTHR_PEQF\");"
									+ "\n if (ReportUtilities.dependencyRepeDataExist(reportDatas, \"O_StrategyTypeOtherDescription\", \"61\", \"OtherFundStrategyType\", \"58\", \"OTHR_REST\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataExist(reportDatas, \"O_StrategyTypeOtherDescription\", \"61\", \"OtherFundStrategyType\", \"58\", \"OTHR_REST\");"
									+ "\n if (ReportUtilities.dependencyRepeDataExist(reportDatas, \"O_StrategyTypeOtherDescription\", \"61\", \"OtherFundStrategyType\", \"58\", \"OTHR_FOFS\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataExist(reportDatas, \"O_StrategyTypeOtherDescription\", \"61\", \"OtherFundStrategyType\", \"58\", \"OTHR_FOFS\");"
									+ "\n if (ReportUtilities.dependencyRepeDataExist(reportDatas, \"O_StrategyTypeOtherDescription\", \"61\", \"OtherFundStrategyType\", \"58\", \"OTHR_OTHF\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataExist(reportDatas, \"O_StrategyTypeOtherDescription\", \"61\", \"OtherFundStrategyType\", \"58\", \"OTHR_OTHF\");"
									+ "}",
							null,
							"Field(61_O) is mandatory when field(58) is OTHR_HFND, OTHR_PEQF, OTHR_REST, OTHR_FOFS, OTHR_OTHF.",
							versionAdmin));

			// reportSemantics
			// .add(new ReportSemantic(
			// reportCatalog,
			// reportField61_O,
			// "("
			// + reportField61_O.getReportFieldNum()
			// .toString() + ") "
			// + reportField61_O.getReportFieldName(),
			// "if ( ReportUtilities.dependencyRepeDataExistChar(reportDatas, \"O_StrategyTypeOtherDescription\", \"61\", \"OtherFundStrategyType\", \"58\", \"O\") == null "
			// + ") "
			// + "\n result = \"ok\"; \n else {"
			// +
			// "\n if (ReportUtilities.dependencyRepeDataExistChar(reportDatas, \"O_StrategyTypeOtherDescription\", \"61\", \"OtherFundStrategyType\", \"58\", \"O\") != null)"
			// +
			// "\n problem = ReportUtilities.dependencyRepeDataExistChar(reportDatas, \"O_StrategyTypeOtherDescription\", \"61\", \"OtherFundStrategyType\", \"58\", \"O\");"
			// + "}",
			// null,
			// "Field(61_O) is mandatory when field(58) is OTHR_HFND, OTHR_PEQF, OTHR_REST, OTHR_FOFS, OTHR_OTHF.",
			// versionAdmin));

			// (58) <RealEstateFundStrategyType>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField58_R,
							"("
									+ reportField58_R.getReportFieldNum()
											.toString() + ") "
									+ reportField58_R.getReportFieldName(),
							"if ( (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"RealEstateFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"HFND\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"RealEstateFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"PEQF\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"RealEstateFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"REST\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"RealEstateFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"FOFS\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"RealEstateFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"OTHR\") == null "
									+ ") || (ReportUtilities.listBlockDatas(reportDatas, \"RealEstateFundStrategyType\", \"58\").size() == 0) "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"RealEstateFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"HFND\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"RealEstateFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"HFND\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"RealEstateFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"PEQF\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"RealEstateFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"PEQF\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"RealEstateFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"REST\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"RealEstateFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"REST\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"RealEstateFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"FOFS\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"RealEstateFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"FOFS\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"RealEstateFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"OTHR\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"RealEstateFundStrategyType\", \"58\", \"PredominantAIFType\", \"57\", \"OTHR\");"
									+ "}",
							null,
							"Field(58_R) is mandatory when field(57) is HFND , PEQF, REST,  FOFS  OTHR.",
							versionAdmin));

			// (59) <R_PrimaryStrategyFlag>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField59_R,
							"("
									+ reportField59_R.getReportFieldNum()
											.toString() + ") "
									+ reportField59_R.getReportFieldName(),
							"if ( (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"R_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"HFND\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"R_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"PEQF\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"R_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"REST\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"R_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"FOFS\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"R_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"OTHR\") == null "
									+ ") || (ReportUtilities.listBlockDatas(reportDatas, \"R_PrimaryStrategyFlag\", \"59\").size() == 0) "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"R_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"HFND\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"R_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"HFND\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"R_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"PEQF\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"R_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"PEQF\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"R_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"REST\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"R_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"REST\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"R_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"FOFS\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"R_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"FOFS\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"R_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"OTHR\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"R_PrimaryStrategyFlag\", \"59\", \"PredominantAIFType\", \"57\", \"OTHR\");"
									+ "}",
							null,
							"Field(59_R) is mandatory when field(57) is HFND , PEQF, REST,  FOFS  OTHR.",
							versionAdmin));

			// (60) <R_StrategyNAVRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField60_R,
							"("
									+ reportField60_R.getReportFieldNum()
											.toString() + ") "
									+ reportField60_R.getReportFieldName(),
							"if ( (ReportUtilities.dependencyRepeDataDiffExists(reportDatas, \"R_StrategyNAVRate\", \"60\", \"RealEstateFundStrategyType\", \"58\", \"MULT_REST \") == null "
									+ ") || (ReportUtilities.listBlockDatas(reportDatas, \"R_StrategyNAVRate\", \"60\").size() == 0) "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataDiffExists(reportDatas, \"R_StrategyNAVRate\", \"60\", \"RealEstateFundStrategyType\", \"58\", \"MULT_REST \") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataDiffExists(reportDatas, \"R_StrategyNAVRate\", \"60\", \"RealEstateFundStrategyType\", \"58\", \"MULT_REST \");"
									+ "}",
							null,
							"Field(60_R) is mandatory when field(58) is different MULT_PEQF .",
							versionAdmin));

			// (61) <R_StrategyTypeOtherDescription>

			// (62) <HFTTransactionNumber>

			// (63) <HFTBuySellMarketValue>

			// (64) <Ranking>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField64,
							"(" + reportField64.getReportFieldNum().toString()
									+ ") " + reportField64.getReportFieldName(),
							"if (ReportUtilities.searchBlockList(reportDatas, \"Ranking\", \"64\").size() == 5) "
									+ "\n result = \"ok\"", null,
							"Field(64) has 5 occurences.", versionAdmin));

			// (65) <SubAssetType>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField65,
							"(" + reportField65.getReportFieldNum().toString()
									+ ") " + reportField65.getReportFieldName(),
							"if (ReportUtilities.searchBlockList(reportDatas, \"SubAssetType\", \"65\").size() == 5) "
									+ "\n result = \"ok\"", null,
							"Field(65) has 5 occurences.", versionAdmin));

			// (66) <InstrumentCodeType>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField66,
							"(" + reportField66.getReportFieldNum().toString()
									+ ") " + reportField66.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"InstrumentCodeType\", \"66\", \"SubAssetType\", \"65\", \"NTA_NTA_NOTA\") == null "
									+ "&& ReportUtilities.dependencyRepeDataNot(reportDatas, \"InstrumentCodeType\", \"66\", \"SubAssetType\", \"65\", \"NTA_NTA_NOTA\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"InstrumentCodeType\", \"66\", \"SubAssetType\", \"65\", \"NTA_NTA_NOTA\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"InstrumentCodeType\", \"66\", \"SubAssetType\", \"65\", \"NTA_NTA_NOTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataNot(reportDatas, \"InstrumentCodeType\", \"66\", \"SubAssetType\", \"65\", \"NTA_NTA_NOTA\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataNot(reportDatas, \"InstrumentCodeType\", \"66\", \"SubAssetType\", \"65\", \"NTA_NTA_NOTA\");"
									+ "}",
							null,
							"Field(66) is mandatory when field(65) different NTA_NTA_NOTA, forbidden otherwise.",
							versionAdmin));

			// (67) <InstrumentName>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField67,
							"(" + reportField67.getReportFieldNum().toString()
									+ ") " + reportField67.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"InstrumentName\", \"67\", \"SubAssetType\", \"65\", \"NTA_NTA_NOTA\") == null "
									+ "&& ReportUtilities.dependencyRepeDataNot(reportDatas, \"InstrumentName\", \"67\", \"SubAssetType\", \"65\", \"NTA_NTA_NOTA\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"InstrumentName\", \"67\", \"SubAssetType\", \"65\", \"NTA_NTA_NOTA\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"InstrumentName\", \"67\", \"SubAssetType\", \"65\", \"NTA_NTA_NOTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataNot(reportDatas, \"InstrumentName\", \"67\", \"SubAssetType\", \"65\", \"NTA_NTA_NOTA\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataNot(reportDatas, \"InstrumentName\", \"67\", \"SubAssetType\", \"65\", \"NTA_NTA_NOTA\");"
									+ "}",
							null,
							"Field(67) is mandatory when field(65) different NTA_NTA_NOTA, forbidden otherwise.",
							versionAdmin));

			// (68) <ISINInstrumentIdentification>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField68,
							"(" + reportField68.getReportFieldNum().toString()
									+ ") " + reportField68.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataExist(reportDatas, \"ISINInstrumentIdentification\", \"68\", \"InstrumentCodeType\", \"66\", \"ISIN\") == null "
									+ "&& ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"ISINInstrumentIdentification\", \"68\", \"InstrumentCodeType\", \"66\", \"ISIN\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataExist(reportDatas, \"ISINInstrumentIdentification\", \"68\", \"InstrumentCodeType\", \"66\", \"ISIN\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataExist(reportDatas, \"ISINInstrumentIdentification\", \"68\", \"InstrumentCodeType\", \"66\", \"ISIN\");"
									+ "\n if (ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"ISINInstrumentIdentification\", \"68\", \"InstrumentCodeType\", \"66\", \"ISIN\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"ISINInstrumentIdentification\", \"68\", \"InstrumentCodeType\", \"66\", \"ISIN\");"
									+ "}",
							null,
							"Field(68) is mandatory when field(66) is ISIN, forbidden otherwise.",
							versionAdmin));

			// (69) <AIIExchangeCode>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField69,
							"(" + reportField69.getReportFieldNum().toString()
									+ ") " + reportField69.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataExist(reportDatas, \"AIIExchangeCode\", \"69\", \"InstrumentCodeType\", \"66\", \"AII\") == null "
									+ "&& ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"AIIExchangeCode\", \"69\", \"InstrumentCodeType\", \"66\", \"AII\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataExist(reportDatas, \"AIIExchangeCode\", \"69\", \"InstrumentCodeType\", \"66\", \"AII\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataExist(reportDatas, \"AIIExchangeCode\", \"69\", \"InstrumentCodeType\", \"66\", \"AII\");"
									+ "\n if (ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"AIIExchangeCode\", \"69\", \"InstrumentCodeType\", \"66\", \"AII\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"AIIExchangeCode\", \"69\", \"InstrumentCodeType\", \"66\", \"AII\");"
									+ "}",
							null,
							"Field(69) is mandatory when field(66) is AII, forbidden otherwise.",
							versionAdmin));

			// (70) <AIIProductCode>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField70,
							"(" + reportField70.getReportFieldNum().toString()
									+ ") " + reportField70.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataExist(reportDatas, \"AIIProductCode\", \"70\", \"InstrumentCodeType\", \"66\", \"AII\") == null "
									+ "&& ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"AIIProductCode\", \"70\", \"InstrumentCodeType\", \"66\", \"AII\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataExist(reportDatas, \"AIIProductCode\", \"70\", \"InstrumentCodeType\", \"66\", \"AII\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataExist(reportDatas, \"AIIProductCode\", \"70\", \"InstrumentCodeType\", \"66\", \"AII\");"
									+ "\n if (ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"AIIProductCode\", \"70\", \"InstrumentCodeType\", \"66\", \"AII\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"AIIProductCode\", \"70\", \"InstrumentCodeType\", \"66\", \"AII\");"
									+ "}",
							null,
							"Field(70) is mandatory when field(66) is AII, forbidden otherwise.",
							versionAdmin));

			// (71) <AIIDerivativeType>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField71,
							"(" + reportField71.getReportFieldNum().toString()
									+ ") " + reportField71.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataExist(reportDatas, \"AIIDerivativeType\", \"71\", \"InstrumentCodeType\", \"66\", \"AII\") == null "
									+ "&& ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"AIIDerivativeType\", \"71\", \"InstrumentCodeType\", \"66\", \"AII\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataExist(reportDatas, \"AIIDerivativeType\", \"71\", \"InstrumentCodeType\", \"66\", \"AII\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataExist(reportDatas, \"AIIDerivativeType\", \"71\", \"InstrumentCodeType\", \"66\", \"AII\");"
									+ "\n if (ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"AIIDerivativeType\", \"71\", \"InstrumentCodeType\", \"66\", \"AII\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"AIIDerivativeType\", \"71\", \"InstrumentCodeType\", \"66\", \"AII\");"
									+ "}",
							null,
							"Field(71) is mandatory when field(66) is AII, forbidden otherwise.",
							versionAdmin));

			// (72) <AIIPutCallIdentifier>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField72,
							"(" + reportField72.getReportFieldNum().toString()
									+ ") " + reportField72.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataExist(reportDatas, \"AIIPutCallIdentifier\", \"72\", \"InstrumentCodeType\", \"66\", \"AII\") == null "
									+ "&& ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"AIIPutCallIdentifier\", \"72\", \"InstrumentCodeType\", \"66\", \"AII\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataExist(reportDatas, \"AIIPutCallIdentifier\", \"72\", \"InstrumentCodeType\", \"66\", \"AII\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataExist(reportDatas, \"AIIPutCallIdentifier\", \"72\", \"InstrumentCodeType\", \"66\", \"AII\");"
									+ "\n if (ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"AIIPutCallIdentifier\", \"72\", \"InstrumentCodeType\", \"66\", \"AII\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"AIIPutCallIdentifier\", \"72\", \"InstrumentCodeType\", \"66\", \"AII\");"
									+ "}",
							null,
							"Field(72) is mandatory when field(66) is AII, forbidden otherwise.",
							versionAdmin));

			// (73) <AIIExpiryDate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField73,
							"(" + reportField73.getReportFieldNum().toString()
									+ ") " + reportField73.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataExist(reportDatas, \"AIIExpiryDate\", \"73\", \"InstrumentCodeType\", \"66\", \"AII\") == null "
									+ "&& ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"AIIExpiryDate\", \"73\", \"InstrumentCodeType\", \"66\", \"AII\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataExist(reportDatas, \"AIIExpiryDate\", \"73\", \"InstrumentCodeType\", \"66\", \"AII\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataExist(reportDatas, \"AIIExpiryDate\", \"73\", \"InstrumentCodeType\", \"66\", \"AII\");"
									+ "\n if (ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"AIIExpiryDate\", \"73\", \"InstrumentCodeType\", \"66\", \"AII\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"AIIExpiryDate\", \"73\", \"InstrumentCodeType\", \"66\", \"AII\");"
									+ "}",
							null,
							"Field(73) is mandatory when field(66) is AII, forbidden otherwise.",
							versionAdmin));

			// (74) <AIIStrikePrice>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField74,
							"(" + reportField74.getReportFieldNum().toString()
									+ ") " + reportField74.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataExist(reportDatas, \"AIIStrikePrice\", \"74\", \"InstrumentCodeType\", \"66\", \"AII\") == null "
									+ "&& ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"AIIStrikePrice\", \"74\", \"InstrumentCodeType\", \"66\", \"AII\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataExist(reportDatas, \"AIIStrikePrice\", \"74\", \"InstrumentCodeType\", \"66\", \"AII\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataExist(reportDatas, \"AIIStrikePrice\", \"74\", \"InstrumentCodeType\", \"66\", \"AII\");"
									+ "\n if (ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"AIIStrikePrice\", \"74\", \"InstrumentCodeType\", \"66\", \"AII\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"AIIStrikePrice\", \"74\", \"InstrumentCodeType\", \"66\", \"AII\");"
									+ "}",
							null,
							"Field(74) is mandatory when field(66) is AII, forbidden otherwise.",
							versionAdmin));

			// (75) <PositionType>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField75,
							"(" + reportField75.getReportFieldNum().toString()
									+ ") " + reportField75.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"PositionType\", \"75\", \"SubAssetType\", \"65\", \"NTA_NTA_NOTA\") == null "
									+ "&& ReportUtilities.dependencyRepeDataNot(reportDatas, \"PositionType\", \"75\", \"SubAssetType\", \"65\", \"NTA_NTA_NOTA\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"PositionType\", \"75\", \"SubAssetType\", \"65\", \"NTA_NTA_NOTA\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"PositionType\", \"75\", \"SubAssetType\", \"65\", \"NTA_NTA_NOTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataNot(reportDatas, \"PositionType\", \"75\", \"SubAssetType\", \"65\", \"NTA_NTA_NOTA\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataNot(reportDatas, \"PositionType\", \"75\", \"SubAssetType\", \"65\", \"NTA_NTA_NOTA\");"
									+ "}",
							null,
							"Field(75) is mandatory when field(65) different NTA_NTA_NOTA, forbidden otherwise.",
							versionAdmin));

			// (76) <PositionValue>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField76,
							"(" + reportField76.getReportFieldNum().toString()
									+ ") " + reportField76.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"PositionValue\", \"76\", \"SubAssetType\", \"65\", \"NTA_NTA_NOTA\") == null "
									+ "&& ReportUtilities.dependencyRepeDataNot(reportDatas, \"PositionValue\", \"76\", \"SubAssetType\", \"65\", \"NTA_NTA_NOTA\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"PositionValue\", \"76\", \"SubAssetType\", \"65\", \"NTA_NTA_NOTA\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"PositionValue\", \"76\", \"SubAssetType\", \"65\", \"NTA_NTA_NOTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataNot(reportDatas, \"PositionValue\", \"76\", \"SubAssetType\", \"65\", \"NTA_NTA_NOTA\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataNot(reportDatas, \"PositionValue\", \"76\", \"SubAssetType\", \"65\", \"NTA_NTA_NOTA\");"
									+ "}",
							null,
							"Field(76) is mandatory when field(65) different NTA_NTA_NOTA, forbidden otherwise.",
							versionAdmin));

			// (77) <ShortPositionHedgingRate>

			// (78) <AfricaNAVRate>
			reportSemantics.add(new ReportSemantic(
					reportCatalog,
					reportField78,
					// "("
					// + reportField78.getReportFieldNum().toString()
					// + ") " + reportField78.getReportFieldName(),
					"(78)-(85) sum 100",
					"if ( ReportUtilities.percentageRange(reportDatas, \"78\", \"85\", \"100\") ) "
							+ "\n result = \"ok\"", null,
					"Fields (78) to (85) has to sum 100.", versionAdmin));

			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField78,
							"(" + reportField78.getReportFieldNum().toString()
									+ ") " + reportField78.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"AfricaNAVRate\", \"78\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(78).", versionAdmin));

			// (79) <AsiaPacificNAVRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField79,
							"(" + reportField79.getReportFieldNum().toString()
									+ ") " + reportField79.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"AsiaPacificNAVRate\", \"79\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(79).", versionAdmin));

			// (80) <EuropeNAVRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField80,
							"(" + reportField80.getReportFieldNum().toString()
									+ ") " + reportField80.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"EuropeNAVRate\", \"80\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(80).", versionAdmin));

			// (81) <EEANAVRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField81,
							"(" + reportField81.getReportFieldNum().toString()
									+ ") " + reportField81.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"EEANAVRate\", \"81\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(81).", versionAdmin));

			// (82) <MiddleEastNAVRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField82,
							"(" + reportField82.getReportFieldNum().toString()
									+ ") " + reportField82.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"MiddleEastNAVRate\", \"82\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(82).", versionAdmin));

			// (83) <NorthAmericaNAVRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField83,
							"(" + reportField83.getReportFieldNum().toString()
									+ ") " + reportField83.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"NorthAmericaNAVRate\", \"83\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(83).", versionAdmin));

			// (84) <SouthAmericaNAVRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField84,
							"(" + reportField84.getReportFieldNum().toString()
									+ ") " + reportField84.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"SouthAmericaNAVRate\", \"84\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(84).", versionAdmin));

			// (85) <SupraNationalNAVRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField85,
							"(" + reportField85.getReportFieldNum().toString()
									+ ") " + reportField85.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"SupraNationalNAVRate\", \"85\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(85).", versionAdmin));

			// (86) <AfricaAUMRate>
			// (87) <AsiaPacificAUMRate>
			// (88) <EuropeAUMRate>
			// (89) <EEAAUMRate>
			// (90) <MiddleEastAUMRate>
			// (91) <NorthAmericaAUMRate>
			// (92) <SouthAmericaAUMRate>
			// (93) <SupraNationalAUMRate>

			// (94) <Ranking>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField94,
							"(" + reportField94.getReportFieldNum().toString()
									+ ") " + reportField94.getReportFieldName(),
							"if (ReportUtilities.searchBlockList(reportDatas, \"Ranking\", \"94\").size() == 10) "
									+ "\n result = \"ok\"", null,
							"Field(94) has 10 occurences.", versionAdmin));

			// (95) <AssetMacroType>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField95,
							"(" + reportField95.getReportFieldNum().toString()
									+ ") " + reportField95.getReportFieldName(),
							"if (ReportUtilities.searchBlockList(reportDatas, \"AssetMacroType\", \"95\").size() == 10) "
									+ "\n result = \"ok\"", null,
							"Field(95) has 10 occurences.", versionAdmin));

			// (96) <SubAssetType>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField96,
							"(" + reportField96.getReportFieldNum().toString()
									+ ") " + reportField96.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"SubAssetType\", \"96\", \"AssetMacroType\", \"95\", \"NTA\") == null "
									+ "&& ReportUtilities.dependencyRepeDataNot(reportDatas, \"SubAssetType\", \"96\", \"AssetMacroType\", \"95\", \"NTA\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"SubAssetType\", \"96\", \"AssetMacroType\", \"95\", \"NTA\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"SubAssetType\", \"96\", \"AssetMacroType\", \"95\", \"NTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataNot(reportDatas, \"SubAssetType\", \"96\", \"AssetMacroType\", \"95\", \"NTA\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataNot(reportDatas, \"SubAssetType\", \"96\", \"AssetMacroType\", \"95\", \"NTA\");"
									+ "}",
							null,
							"Field(96) is mandatory when field(95) different NTA, forbidden otherwise.",
							versionAdmin));

			// (97) <PositionType>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField97,
							"(" + reportField97.getReportFieldNum().toString()
									+ ") " + reportField97.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"PositionType\", \"97\", \"AssetMacroType\", \"95\", \"NTA\") == null "
									+ "&& ReportUtilities.dependencyRepeDataNot(reportDatas, \"PositionType\", \"97\", \"AssetMacroType\", \"95\", \"NTA\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"PositionType\", \"97\", \"AssetMacroType\", \"95\", \"NTA\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"PositionType\", \"97\", \"AssetMacroType\", \"95\", \"NTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataNot(reportDatas, \"PositionType\", \"97\", \"AssetMacroType\", \"95\", \"NTA\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataNot(reportDatas, \"PositionType\", \"97\", \"AssetMacroType\", \"95\", \"NTA\");"
									+ "}",
							null,
							"Field(97) is mandatory when field(95) different NTA, forbidden otherwise.",
							versionAdmin));

			// (98) <AggregatedValueAmount>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField98,
							"(" + reportField98.getReportFieldNum().toString()
									+ ") " + reportField98.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"AggregatedValueAmount\", \"98\", \"AssetMacroType\", \"95\", \"NTA\") == null "
									+ "&& ReportUtilities.dependencyRepeDataNot(reportDatas, \"AggregatedValueAmount\", \"98\", \"AssetMacroType\", \"95\", \"NTA\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"AggregatedValueAmount\", \"98\", \"AssetMacroType\", \"95\", \"NTA\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"AggregatedValueAmount\", \"98\", \"AssetMacroType\", \"95\", \"NTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataNot(reportDatas, \"AggregatedValueAmount\", \"98\", \"AssetMacroType\", \"95\", \"NTA\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataNot(reportDatas, \"AggregatedValueAmount\", \"98\", \"AssetMacroType\", \"95\", \"NTA\");"
									+ "}",
							null,
							"Field(98) is mandatory when field(95) different NTA, forbidden otherwise.",
							versionAdmin));

			// (99) <AggregatedValueRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField99,
							"(" + reportField99.getReportFieldNum().toString()
									+ ") " + reportField99.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"AggregatedValueRate\", \"99\", \"AssetMacroType\", \"95\", \"NTA\") == null "
									+ "&& ReportUtilities.dependencyRepeDataNot(reportDatas, \"AggregatedValueRate\", \"99\", \"AssetMacroType\", \"95\", \"NTA\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"AggregatedValueRate\", \"99\", \"AssetMacroType\", \"95\", \"NTA\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"AggregatedValueRate\", \"99\", \"AssetMacroType\", \"95\", \"NTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataNot(reportDatas, \"AggregatedValueRate\", \"99\", \"AssetMacroType\", \"95\", \"NTA\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataNot(reportDatas, \"AggregatedValueRate\", \"99\", \"AssetMacroType\", \"95\", \"NTA\");"
									+ "}",
							null,
							"Field(99) is mandatory when field(95) different NTA, forbidden otherwise.",
							versionAdmin));

			// (100) <EntityName>

			// (101) <EntityIdentificationLEI>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField101,
							"(" + reportField101.getReportFieldNum().toString()
									+ ") "
									+ reportField101.getReportFieldName(),
							"if (ReportUtilities.dependencyRepeDataForb(reportDatas, \"EntityIdentificationLEI\", \"101\", \"EntityName\", \"100\") == null) "
									+ "\n result = \"ok\"; else {"
									+ "\n problem = ReportUtilities.dependencyRepeDataForb(reportDatas, \"EntityIdentificationLEI\", \"101\", \"EntityName\", \"100\");"
									+ "}",
							null,
							"Field(101) is forbidden when field(100) is empty.",
							versionAdmin));

			// (102) <EntityIdentificationBIC>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField102,
							"(" + reportField102.getReportFieldNum().toString()
									+ ") "
									+ reportField102.getReportFieldName(),
							"if (ReportUtilities.dependencyRepeDataForb(reportDatas, \"EntityIdentificationBIC\", \"102\", \"EntityName\", \"100\") == null) "
									+ "\n result = \"ok\"; else {"
									+ "\n problem = ReportUtilities.dependencyRepeDataForb(reportDatas, \"EntityIdentificationBIC\", \"102\", \"EntityName\", \"100\");"
									+ "}",
							null,
							"Field(102) is forbidden when field(100) is empty.",
							versionAdmin));

			// (103) <Ranking>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField103,
							"(" + reportField103.getReportFieldNum().toString()
									+ ") "
									+ reportField103.getReportFieldName(),
							"if (ReportUtilities.searchBlockList(reportDatas, \"Ranking\", \"103\").size() == 5) "
									+ "\n result = \"ok\"", null,
							"Field(103) has 5 occurences.", versionAdmin));

			// (104) <AssetType>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField104,
							"(" + reportField104.getReportFieldNum().toString()
									+ ") "
									+ reportField104.getReportFieldName(),
							"if (ReportUtilities.searchBlockList(reportDatas, \"AssetType\", \"104\").size() == 5) "
									+ "\n result = \"ok\"", null,
							"Field(104) has 5 occurences.", versionAdmin));

			// (105) <PositionType>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField105,
							"(" + reportField105.getReportFieldNum().toString()
									+ ") "
									+ reportField105.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"PositionType\", \"105\", \"AssetType\", \"104\", \"NTA_NTA\") == null "
									+ "&& ReportUtilities.dependencyRepeDataNot(reportDatas, \"PositionType\", \"105\", \"AssetType\", \"104\", \"NTA_NTA\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"PositionType\", \"105\", \"AssetType\", \"104\", \"NTA_NTA\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"PositionType\", \"105\", \"AssetType\", \"104\", \"NTA_NTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataNot(reportDatas, \"PositionType\", \"105\", \"AssetType\", \"104\", \"NTA_NTA\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataNot(reportDatas, \"PositionType\", \"105\", \"AssetType\", \"104\", \"NTA_NTA\");"
									+ "}",
							null,
							"Field(105) is mandatory when field(104) different NTA_NTA, forbidden otherwise.",
							versionAdmin));

			// (106) <MarketCodeType>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField106,
							"(" + reportField106.getReportFieldNum().toString()
									+ ") "
									+ reportField106.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"MarketCodeType\", \"106\", \"AssetType\", \"104\", \"NTA_NTA\") == null "
									+ "&& ReportUtilities.dependencyRepeDataNot(reportDatas, \"MarketCodeType\", \"106\", \"AssetType\", \"104\", \"NTA_NTA\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"MarketCodeType\", \"106\", \"AssetType\", \"104\", \"NTA_NTA\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"MarketCodeType\", \"106\", \"AssetType\", \"104\", \"NTA_NTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataNot(reportDatas, \"MarketCodeType\", \"106\", \"AssetType\", \"104\", \"NTA_NTA\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataNot(reportDatas, \"MarketCodeType\", \"106\", \"AssetType\", \"104\", \"NTA_NTA\");"
									+ "}",
							null,
							"Field(106) is mandatory when field(104) different NTA_NTA, forbidden otherwise.",
							versionAdmin));

			// (107) <MarketCode>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField107,
							"(" + reportField107.getReportFieldNum().toString()
									+ ") "
									+ reportField107.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataExist(reportDatas, \"MarketCode\", \"107\", \"MarketCodeType\", \"106\", \"MIC\") == null "
									+ "&& ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"MarketCode\", \"107\", \"MarketCodeType\", \"106\", \"MIC\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataExist(reportDatas, \"MarketCode\", \"107\", \"MarketCodeType\", \"106\", \"MIC\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataExist(reportDatas, \"MarketCode\", \"107\", \"MarketCodeType\", \"106\", \"MIC\");"
									+ "\n if (ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"MarketCode\", \"107\", \"MarketCodeType\", \"106\", \"MIC\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"MarketCode\", \"107\", \"MarketCodeType\", \"106\", \"MIC\");"
									+ "}",
							null,
							"Field(107) is mandatory when field(106) is MIC, forbidden otherwise.",
							versionAdmin));

			// (108) <AggregatedValueAmount>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField108,
							"(" + reportField108.getReportFieldNum().toString()
									+ ") "
									+ reportField108.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"AggregatedValueAmount\", \"108\", \"AssetType\", \"104\", \"NTA_NTA\") == null "
									+ "&& ReportUtilities.dependencyRepeDataNot(reportDatas, \"AggregatedValueAmount\", \"108\", \"AssetType\", \"104\", \"NTA_NTA\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"AggregatedValueAmount\", \"108\", \"AssetType\", \"104\", \"NTA_NTA\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"AggregatedValueAmount\", \"108\", \"AssetType\", \"104\", \"NTA_NTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataNot(reportDatas, \"AggregatedValueAmount\", \"108\", \"AssetType\", \"104\", \"NTA_NTA\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataNot(reportDatas, \"AggregatedValueAmount\", \"108\", \"AssetType\", \"104\", \"NTA_NTA\");"
									+ "}",
							null,
							"Field(108) is mandatory when field(104) different NTA_NTA, forbidden otherwise.",
							versionAdmin));

			// (109) <AggregatedValueRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField109,
							"(" + reportField109.getReportFieldNum().toString()
									+ ") "
									+ reportField109.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"AggregatedValueRate\", \"109\", \"AssetType\", \"104\", \"NTA_NTA\") == null "
									+ "&& ReportUtilities.dependencyRepeDataNot(reportDatas, \"AggregatedValueRate\", \"109\", \"AssetType\", \"104\", \"NTA_NTA\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"AggregatedValueRate\", \"109\", \"AssetType\", \"104\", \"NTA_NTA\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"AggregatedValueRate\", \"109\", \"AssetType\", \"104\", \"NTA_NTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataNot(reportDatas, \"AggregatedValueRate\", \"109\", \"AssetType\", \"104\", \"NTA_NTA\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataNot(reportDatas, \"AggregatedValueRate\", \"109\", \"AssetType\", \"104\", \"NTA_NTA\");"
									+ "}",
							null,
							"Field(109) is mandatory when field(104) different NTA_NTA, forbidden otherwise.",
							versionAdmin));

			// (110) <EntityName>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField110,
							"(" + reportField110.getReportFieldNum().toString()
									+ ") "
									+ reportField110.getReportFieldName(),
							"if (ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"EntityName\", \"110\", \"MarketCodeType\", \"106\", \"OTC\") == null) "
									+ "\n result = \"ok\"; \n else {"
									+ "\n problem = ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"EntityName\", \"110\", \"MarketCodeType\", \"106\", \"OTC\");"
									+ "}",
							null,
							"Field(110) is forbidden when field(106) is different to OTC.",
							versionAdmin));

			// (111) <EntityIdentificationLEI>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField111,
							"(" + reportField111.getReportFieldNum().toString()
									+ ") "
									+ reportField111.getReportFieldName(),
							"if ( (ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"EntityIdentificationLEI\", \"111\", \"MarketCodeType\", \"106\", \"OTC\") == null) "
									+ " && (ReportUtilities.dependencyRepeDataForb(reportDatas, \"EntityIdentificationLEI\", \"111\", \"EntityName\", \"110\") == null) )"
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"EntityIdentificationLEI\", \"111\", \"MarketCodeType\", \"106\", \"OTC\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"EntityIdentificationLEI\", \"111\", \"MarketCodeType\", \"106\", \"OTC\");"
									+ "\n if (ReportUtilities.dependencyRepeDataForb(reportDatas, \"EntityIdentificationLEI\", \"111\", \"EntityName\", \"110\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataForb(reportDatas, \"EntityIdentificationLEI\", \"111\", \"EntityName\", \"110\");"
									+ "}",
							null,
							"Field(111) is forbidden when field(106) is different to OTC or field(110) is empty.",
							versionAdmin));

			// (112) <EntityIdentificationBIC>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField112,
							"(" + reportField112.getReportFieldNum().toString()
									+ ") "
									+ reportField112.getReportFieldName(),
							"if ( (ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"EntityIdentificationBIC\", \"112\", \"MarketCodeType\", \"106\", \"OTC\") == null) "
									+ " || (ReportUtilities.dependencyRepeData(reportDatas, \"EntityIdentificationBIC\", \"112\", \"EntityName\", \"110\") == null) )"
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"EntityIdentificationBIC\", \"112\", \"MarketCodeType\", \"106\", \"OTC\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"EntityIdentificationBIC\", \"112\", \"MarketCodeType\", \"106\", \"OTC\");"
									+ "\n if (ReportUtilities.dependencyRepeData(reportDatas, \"EntityIdentificationBIC\", \"112\", \"MarketCodeType\", \"106\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeData(reportDatas, \"EntityIdentificationBIC\", \"112\", \"MarketCodeType\", \"106\");"
									+ "}",
							null,
							"Field(112) is forbidden when field(106) is different to OTC or field(110) is empty.",
							versionAdmin));

			// (113) <TypicalPositionSize>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField113,
							"(" + reportField113.getReportFieldNum().toString()
									+ ") "
									+ reportField113.getReportFieldName(),
							"if ( (ReportUtilities.searchData(reportDatas, \"TypicalPositionSize\", \"113\", null) != null "
									+ "&& ReportUtilities.searchData(reportDatas, \"PredominantAIFType\", \"57\", null) != null "
									+ "&& ReportUtilities.searchData(reportDatas, \"PredominantAIFType\", \"57\", null).equals(\"PEQF\") )"
									+ " || (ReportUtilities.searchData(reportDatas, \"TypicalPositionSize\", \"113\", null) == null "
									+ "&& (ReportUtilities.searchData(reportDatas, \"PredominantAIFType\", \"57\", null) == null "
									+ "|| !ReportUtilities.searchData(reportDatas, \"PredominantAIFType\", \"57\", null).equals(\"PEQF\") )"
									+ ")" + ") " + "\n result = \"ok\"",
							null,
							"Field(113) is mandatory when field(57) is PEQF, forbidden otherwise.",
							versionAdmin));

			// (114) <Ranking>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField114,
							"(" + reportField114.getReportFieldNum().toString()
									+ ") "
									+ reportField114.getReportFieldName(),
							"if (ReportUtilities.searchBlockList(reportDatas, \"Ranking\", \"114\").size() == 3) "
									+ "\n result = \"ok\"", null,
							"Field(114) has 3 occurences.", versionAdmin));

			// (115) <MarketCodeType>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField115,
							"(" + reportField115.getReportFieldNum().toString()
									+ ") "
									+ reportField115.getReportFieldName(),
							"if (ReportUtilities.searchBlockList(reportDatas, \"MarketCodeType\", \"115\").size() == 3) "
									+ "\n result = \"ok\"", null,
							"Field(115) has 3 occurences.", versionAdmin));

			// (116) <MarketCode>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField116,
							"(" + reportField116.getReportFieldNum().toString()
									+ ") "
									+ reportField116.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataExist(reportDatas, \"MarketCode\", \"116\", \"MarketCodeType\", \"115\", \"MIC\") == null "
									+ "&& ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"MarketCode\", \"116\", \"MarketCodeType\", \"115\", \"MIC\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataExist(reportDatas, \"MarketCode\", \"116\", \"MarketCodeType\", \"115\", \"MIC\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataExist(reportDatas, \"MarketCode\", \"116\", \"MarketCodeType\", \"115\", \"MIC\");"
									+ "\n if (ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"MarketCode\", \"116\", \"MarketCodeType\", \"115\", \"MIC\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"MarketCode\", \"116\", \"MarketCodeType\", \"115\", \"MIC\");"
									+ "}",
							null,
							"Field(116) is mandatory when field(115) is MIC, forbidden otherwise.",
							versionAdmin));

			// (117) <AggregatedValueAmount>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField117,
							"(" + reportField117.getReportFieldNum().toString()
									+ ") "
									+ reportField117.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"AggregatedValueAmount\", \"117\", \"MarketCodeType\", \"115\", \"NOT\") == null "
									+ "&& ReportUtilities.dependencyRepeDataNot(reportDatas, \"AggregatedValueAmount\", \"117\", \"MarketCodeType\", \"115\", \"NOT\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"AggregatedValueAmount\", \"117\", \"MarketCodeType\", \"115\", \"NOT\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"AggregatedValueAmount\", \"117\", \"MarketCodeType\", \"115\", \"NOT\");"
									+ "\n if (ReportUtilities.dependencyRepeDataNot(reportDatas, \"AggregatedValueAmount\", \"117\", \"MarketCodeType\", \"115\", \"NOT\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataNot(reportDatas, \"AggregatedValueAmount\", \"117\", \"MarketCodeType\", \"115\", \"NOT\");"
									+ "}",
							null,
							"Field(117) is mandatory when field(115) different NOT, forbidden otherwise.",
							versionAdmin));

			// (118) <MainBeneficialOwnersRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField118,
							"(" + reportField118.getReportFieldNum().toString()
									+ ") "
									+ reportField118.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"MainBeneficialOwnersRate\", \"118\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(118).", versionAdmin));

			// (119) <ProfessionalInvestorConcentrationRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField119,
							"(" + reportField119.getReportFieldNum().toString()
									+ ") "
									+ reportField119.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"ProfessionalInvestorConcentrationRate\", \"119\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(119).", versionAdmin));

			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField119,
							// "(" +
							// reportField119.getReportFieldNum().toString()
							// + ") "
							// + reportField119.getReportFieldName(),
							"(119)-(120) sum 100",
							"if ( ReportUtilities.percentageRange(reportDatas, \"119\", \"120\", \"100\") ) "
									+ "\n result = \"ok\"", null,
							"Fields (119) to (120) has to sum 100",
							versionAdmin));

			// (120) <RetailInvestorConcentrationRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField120,
							"(" + reportField120.getReportFieldNum().toString()
									+ ") "
									+ reportField120.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"RetailInvestorConcentrationRate\", \"120\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(120).", versionAdmin));

			// (121) <SubAssetType>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField121,
							"(" + reportField121.getReportFieldNum().toString()
									+ ") "
									+ reportField121.getReportFieldName(),
							"if (ReportUtilities.searchBlockList(reportDatas, \"SubAssetType\", \"121\").size() >= 1) "
									+ "\n result = \"ok\"", null,
							"Field(121) has at least 1 occurence.",
							versionAdmin));

			// (122) <GrossValue>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField122,
							"(" + reportField122.getReportFieldNum().toString()
									+ ") "
									+ reportField122.getReportFieldName(),
							"if (ReportUtilities.dependencyRepeDataForbChar(reportDatas, \"GrossValue\", \"122\", \"SubAssetType\", \"121\", \"D\") == null"
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataForbChar(reportDatas, \"GrossValue\", \"122\", \"SubAssetType\", \"121\", \"D\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataForbChar(reportDatas, \"GrossValue\", \"122\", \"SubAssetType\", \"121\", \"D\");"
									+ "}",
							null,
							"Field(122) is forbidden when field(121) is different to DER_FEX_INVT, DER_FEX_HEDG, DER_IRD_INTR.",
							versionAdmin));

			// (123) <LongValue>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField123,
							"(" + reportField123.getReportFieldNum().toString()
									+ ") "
									+ reportField123.getReportFieldName(),
							"if (ReportUtilities.dependencyRepeDataDiffForbList(reportDatas, \"LongValue\", \"123\", \"SubAssetType\", \"121\", new String[] {\"DER_FEX_INVT\", \"DER_FEX_HEDG\", \"DER_IRD_INTR\"}) == null) "
									+ "\n result = \"ok\"; \n else {"
									+ "\n problem = ReportUtilities.dependencyRepeDataDiffForbList(reportDatas, \"LongValue\", \"123\", \"SubAssetType\", \"121\", new String[] {\"DER_FEX_INVT\", \"DER_FEX_HEDG\", \"DER_IRD_INTR\"});"
									+ "}",
							null,
							"Field(123) is forbidden when field(121) is different to DER_FEX_INVT, DER_FEX_HEDG, DER_IRD_INTR.",
							versionAdmin));

			// (124) <ShortValue>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField124,
							"(" + reportField124.getReportFieldNum().toString()
									+ ") "
									+ reportField124.getReportFieldName(),
							"if (ReportUtilities.dependencyRepeDataDiffForbList(reportDatas, \"ShortValue\", \"124\", \"SubAssetType\", \"121\", new String[] {\"DER_FEX_INVT\", \"DER_FEX_HEDG\", \"DER_IRD_INTR\"}) == null) "
									+ "\n result = \"ok\"; \n else {"
									+ "\n problem = ReportUtilities.dependencyRepeDataDiffForbList(reportDatas, \"ShortValue\", \"124\", \"SubAssetType\", \"121\", new String[] {\"DER_FEX_INVT\", \"DER_FEX_HEDG\", \"DER_IRD_INTR\"});"
									+ "}",
							null,
							"Field(124) is forbidden when field(121) is different to DER_FEX_INVT, DER_FEX_HEDG, DER_IRD_INTR.",
							versionAdmin));

			// (125) <TurnoverSubAssetType>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField125,
							"(" + reportField125.getReportFieldNum().toString()
									+ ") "
									+ reportField125.getReportFieldName(),
							"if (ReportUtilities.searchBlockList(reportDatas, \"TurnoverSubAssetType\", \"125\").size() >= 1) "
									+ "\n result = \"ok\"", null,
							"Field(125) has at least 1 occurence.",
							versionAdmin));

			// (126) <MarketValue>

			// (127) <NotionalValue>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField127,
							"(" + reportField127.getReportFieldNum().toString()
									+ ") "
									+ reportField127.getReportFieldName(),
							"if (ReportUtilities.dependencyRepeDataForbChar(reportDatas, \"NotionalValue\", \"127\", \"TurnoverSubAssetType\", \"125\", \"D\") == null"
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataForbChar(reportDatas, \"NotionalValue\", \"127\", \"TurnoverSubAssetType\", \"125\", \"D\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataForbChar(reportDatas, \"NotionalValue\", \"127\", \"TurnoverSubAssetType\", \"125\", \"D\");"
									+ "}",
							null,
							"Field(127) is forbidden when field(125) is different to DER_EQD_EQD, DER_FID_FID, DER_CDS_CDS, DER_FEX_INV, DER_FEX_HED, DER_IRD_IRD, DER_CTY_CTY, DER_OTH_OTH.",
							versionAdmin));

			// (128) <ExposureCurrency>

			// (129) <LongPositionValue>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField129,
							"(" + reportField129.getReportFieldNum().toString()
									+ ") "
									+ reportField129.getReportFieldName(),
							"if (ReportUtilities.dependencyRepeDataForb(reportDatas, \"LongPositionValue\", \"129\", \"ExposureCurrency\", \"128\") == null) "
									+ "\n result = \"ok\"; \n else {"
									+ "\n problem = ReportUtilities.dependencyRepeDataForb(reportDatas, \"LongPositionValue\", \"129\", \"ExposureCurrency\", \"128\");"
									+ "}",
							null,
							"Field(129) is forbidden when field(128) is empty.",
							versionAdmin));

			// (130) <ShortPositionValue>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField130,
							"(" + reportField130.getReportFieldNum().toString()
									+ ") "
									+ reportField130.getReportFieldName(),
							"if (ReportUtilities.dependencyRepeDataForb(reportDatas, \"ShortPositionValue\", \"130\", \"ExposureCurrency\", \"128\") == null) "
									+ "\n result = \"ok\"; \n else {"
									+ "\n problem = ReportUtilities.dependencyRepeDataForb(reportDatas, \"ShortPositionValue\", \"130\", \"ExposureCurrency\", \"128\");"
									+ "}",
							null,
							"Fill field(130) is forbidden when field(128) is empty.",
							versionAdmin));

			// (131) <EntityName>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField131,
							"(" + reportField131.getReportFieldNum().toString()
									+ ") "
									+ reportField131.getReportFieldName(),
							"if ( ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"EntityName\", \"131\", \"PredominantAIFType\", \"57\", \"PEQF\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"EntityName\", \"131\", \"PredominantAIFType\", \"57\", \"PEQF\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"EntityName\", \"131\", \"PredominantAIFType\", \"57\", \"PEQF\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"EntityName\", \"131\", \"PredominantAIFType\", \"57\", \"PEQF\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"EntityName\", \"131\", \"PredominantAIFType\", \"57\", \"PEQF\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"EntityName\", \"131\", \"PredominantAIFType\", \"57\", \"PEQF\");"
									+ "}",
							null,
							"Field(131) is mandatory when field(57) is PEQF, forbidden otherwise.",
							versionAdmin));

			// (132) <EntityIdentificationLEI>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField132,
							"(" + reportField132.getReportFieldNum().toString()
									+ ") "
									+ reportField132.getReportFieldName(),
							"if ( ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"EntityIdentificationLEI\", \"132\", \"PredominantAIFType\", \"57\", \"PEQF\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"EntityIdentificationLEI\", \"132\", \"PredominantAIFType\", \"57\", \"PEQF\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"EntityIdentificationLEI\", \"132\", \"PredominantAIFType\", \"57\", \"PEQF\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"EntityIdentificationLEI\", \"132\", \"PredominantAIFType\", \"57\", \"PEQF\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"EntityIdentificationLEI\", \"132\", \"PredominantAIFType\", \"57\", \"PEQF\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"EntityIdentificationLEI\", \"132\", \"PredominantAIFType\", \"57\", \"PEQF\");"
									+ "}",
							null,
							"Field(132) is mandatory when field(57) is PEQF, forbidden otherwise.",
							versionAdmin));

			// (133) <EntityIdentificationBIC>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField133,
							"(" + reportField133.getReportFieldNum().toString()
									+ ") "
									+ reportField133.getReportFieldName(),
							"if ( ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"EntityIdentificationBIC\", \"133\", \"PredominantAIFType\", \"57\", \"PEQF\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"EntityIdentificationBIC\", \"133\", \"PredominantAIFType\", \"57\", \"PEQF\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"EntityIdentificationBIC\", \"133\", \"PredominantAIFType\", \"57\", \"PEQF\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"EntityIdentificationBIC\", \"133\", \"PredominantAIFType\", \"57\", \"PEQF\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"EntityIdentificationBIC\", \"133\", \"PredominantAIFType\", \"57\", \"PEQF\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"EntityIdentificationBIC\", \"133\", \"PredominantAIFType\", \"57\", \"PEQF\");"
									+ "}",
							null,
							"Field(133) is mandatory when field(57) is PEQF, forbidden otherwise.",
							versionAdmin));

			// (134) <TransactionType>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField134,
							"(" + reportField134.getReportFieldNum().toString()
									+ ") "
									+ reportField134.getReportFieldName(),
							"if ( ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"TransactionType\", \"134\", \"PredominantAIFType\", \"57\", \"PEQF\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"TransactionType\", \"134\", \"PredominantAIFType\", \"57\", \"PEQF\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"TransactionType\", \"134\", \"PredominantAIFType\", \"57\", \"PEQF\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"TransactionType\", \"134\", \"PredominantAIFType\", \"57\", \"PEQF\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"TransactionType\", \"134\", \"PredominantAIFType\", \"57\", \"PEQF\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"TransactionType\", \"134\", \"PredominantAIFType\", \"57\", \"PEQF\");"
									+ "}",
							null,
							"Field(134) is mandatory when field(57) is PEQF, forbidden otherwise.",
							versionAdmin));

			// (135) <OtherTransactionTypeDescription>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField135,
							"(" + reportField135.getReportFieldNum().toString()
									+ ") "
									+ reportField135.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataExist(reportDatas, \"OtherTransactionTypeDescription\", \"135\", \"TransactionType\", \"134\", \"OTHR\") == null "
									+ "&& ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"OtherTransactionTypeDescription\", \"135\", \"TransactionType\", \"134\", \"OTHR\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataExist(reportDatas, \"OtherTransactionTypeDescription\", \"135\", \"TransactionType\", \"134\", \"OTHR\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataExist(reportDatas, \"OtherTransactionTypeDescription\", \"135\", \"TransactionType\", \"134\", \"OTHR\");"
									+ "\n if (ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"OtherTransactionTypeDescription\", \"135\", \"TransactionType\", \"134\", \"OTHR\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"OtherTransactionTypeDescription\", \"135\", \"TransactionType\", \"134\", \"OTHR\");"
									+ "}",
							null,
							"Field(135) is mandatory when field(134) is OTHR, forbidden otherwise.",
							versionAdmin));

			// (136) <VotingRightsRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField136,
							"(" + reportField136.getReportFieldNum().toString()
									+ ") "
									+ reportField136.getReportFieldName(),
							"if ( ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"VotingRightsRate\", \"136\", \"PredominantAIFType\", \"57\", \"PEQF\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"VotingRightsRate\", \"136\", \"PredominantAIFType\", \"57\", \"PEQF\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"VotingRightsRate\", \"136\", \"PredominantAIFType\", \"57\", \"PEQF\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"VotingRightsRate\", \"136\", \"PredominantAIFType\", \"57\", \"PEQF\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"VotingRightsRate\", \"136\", \"PredominantAIFType\", \"57\", \"PEQF\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"VotingRightsRate\", \"136\", \"PredominantAIFType\", \"57\", \"PEQF\");"
									+ "}",
							null,
							"Field(136) is mandatory when field(57) is PEQF, forbidden otherwise.",
							versionAdmin));

			// (137) <AnnualInvestmentReturnRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField137,
							"(" + reportField137.getReportFieldNum().toString()
									+ ") "
									+ reportField137.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"AnnualInvestmentReturnRate\", \"137\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(137).", versionAdmin));

			// (138) <RiskMeasureType>

			// (139) <RiskMeasureValue>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField139,
							"(" + reportField139.getReportFieldNum().toString()
									+ ") "
									+ reportField139.getReportFieldName(),
							"if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"RiskMeasureValue\", \"139\", \"RiskMeasureType\", \"138\", \"NET_DV01\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"RiskMeasureValue\", \"139\", \"RiskMeasureType\", \"138\", \"NET_CS01\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"RiskMeasureValue\", \"139\", \"RiskMeasureType\", \"138\", \"VAR\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"RiskMeasureValue\", \"139\", \"RiskMeasureType\", \"138\", \"VEGA_EXPO\") == null"
									+ " ) "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"RiskMeasureValue\", \"139\", \"RiskMeasureType\", \"138\", \"NET_DV01\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"RiskMeasureValue\", \"139\", \"RiskMeasureType\", \"138\", \"NET_DV01\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"RiskMeasureValue\", \"139\", \"RiskMeasureType\", \"138\", \"NET_CS01\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"RiskMeasureValue\", \"139\", \"RiskMeasureType\", \"138\", \"NET_CS01\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"RiskMeasureValue\", \"139\", \"RiskMeasureType\", \"138\", \"VAR\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"RiskMeasureValue\", \"139\", \"RiskMeasureType\", \"138\", \"VAR\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"RiskMeasureValue\", \"139\", \"RiskMeasureType\", \"138\", \"VEGA_EXPO\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"RiskMeasureValue\", \"139\", \"RiskMeasureType\", \"138\", \"VEGA_EXPO\");"
									+ "}",
							null,
							"Field(139) is forbidden when field(138) is NET_DV01, NET_CS01, VAR, VEGA_EXPO.",
							versionAdmin));

			// (140) <LessFiveYearsRiskMeasureValue>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField140,
							"(" + reportField140.getReportFieldNum().toString()
									+ ") "
									+ reportField140.getReportFieldName(),
							"if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LessFiveYearsRiskMeasureValue\", \"140\", \"RiskMeasureType\", \"138\", \"NET_EQTY_DELTA\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LessFiveYearsRiskMeasureValue\", \"140\", \"RiskMeasureType\", \"138\", \"NET_FX_DELTA\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LessFiveYearsRiskMeasureValue\", \"140\", \"RiskMeasureType\", \"138\", \"NET_CTY_DELTA\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LessFiveYearsRiskMeasureValue\", \"140\", \"RiskMeasureType\", \"138\", \"VAR\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LessFiveYearsRiskMeasureValue\", \"140\", \"RiskMeasureType\", \"138\", \"VEGA_EXPO\") == null"
									+ " ) "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LessFiveYearsRiskMeasureValue\", \"140\", \"RiskMeasureType\", \"138\", \"NET_EQTY_DELTA\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LessFiveYearsRiskMeasureValue\", \"140\", \"RiskMeasureType\", \"138\", \"NET_EQTY_DELTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LessFiveYearsRiskMeasureValue\", \"140\", \"RiskMeasureType\", \"138\", \"NET_FX_DELTA\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LessFiveYearsRiskMeasureValue\", \"140\", \"RiskMeasureType\", \"138\", \"NET_FX_DELTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LessFiveYearsRiskMeasureValue\", \"140\", \"RiskMeasureType\", \"138\", \"NET_CTY_DELTA\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LessFiveYearsRiskMeasureValue\", \"140\", \"RiskMeasureType\", \"138\", \"NET_CTY_DELTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LessFiveYearsRiskMeasureValue\", \"140\", \"RiskMeasureType\", \"138\", \"VAR\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LessFiveYearsRiskMeasureValue\", \"140\", \"RiskMeasureType\", \"138\", \"VAR\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LessFiveYearsRiskMeasureValue\", \"140\", \"RiskMeasureType\", \"138\", \"VEGA_EXPO\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LessFiveYearsRiskMeasureValue\", \"140\", \"RiskMeasureType\", \"138\", \"VEGA_EXPO\");"
									+ "}",
							null,
							"Field(140) is forbidden when field(138) is NET_EQTY_DELTA, NET_FX_DELTA, NET_CTY_DELTA, VAR, VEGA_EXPO.",
							versionAdmin));

			// (141) <FifthteenYearsRiskMeasureValue>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField141,
							"(" + reportField141.getReportFieldNum().toString()
									+ ") "
									+ reportField141.getReportFieldName(),
							"if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"FifthteenYearsRiskMeasureValue\", \"141\", \"RiskMeasureType\", \"138\", \"NET_EQTY_DELTA\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"FifthteenYearsRiskMeasureValue\", \"141\", \"RiskMeasureType\", \"138\", \"NET_FX_DELTA\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"FifthteenYearsRiskMeasureValue\", \"141\", \"RiskMeasureType\", \"138\", \"NET_CTY_DELTA\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"FifthteenYearsRiskMeasureValue\", \"141\", \"RiskMeasureType\", \"138\", \"VAR\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"FifthteenYearsRiskMeasureValue\", \"141\", \"RiskMeasureType\", \"138\", \"VEGA_EXPO\") == null"
									+ " ) "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"FifthteenYearsRiskMeasureValue\", \"141\", \"RiskMeasureType\", \"138\", \"NET_EQTY_DELTA\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"FifthteenYearsRiskMeasureValue\", \"141\", \"RiskMeasureType\", \"138\", \"NET_EQTY_DELTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"FifthteenYearsRiskMeasureValue\", \"141\", \"RiskMeasureType\", \"138\", \"NET_FX_DELTA\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"FifthteenYearsRiskMeasureValue\", \"141\", \"RiskMeasureType\", \"138\", \"NET_FX_DELTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"FifthteenYearsRiskMeasureValue\", \"141\", \"RiskMeasureType\", \"138\", \"NET_CTY_DELTA\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"FifthteenYearsRiskMeasureValue\", \"141\", \"RiskMeasureType\", \"138\", \"NET_CTY_DELTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"FifthteenYearsRiskMeasureValue\", \"141\", \"RiskMeasureType\", \"138\", \"VAR\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"FifthteenYearsRiskMeasureValue\", \"141\", \"RiskMeasureType\", \"138\", \"VAR\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"FifthteenYearsRiskMeasureValue\", \"141\", \"RiskMeasureType\", \"138\", \"VEGA_EXPO\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"FifthteenYearsRiskMeasureValue\", \"141\", \"RiskMeasureType\", \"138\", \"VEGA_EXPO\");"
									+ "}",
							null,
							"Field(141) is forbidden when field(138) is NET_EQTY_DELTA, NET_FX_DELTA, NET_CTY_DELTA, VAR, VEGA_EXPO.",
							versionAdmin));

			// (142) <MoreFifthteenYearsRiskMeasureValue>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField142,
							"(" + reportField142.getReportFieldNum().toString()
									+ ") "
									+ reportField142.getReportFieldName(),
							"if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"MoreFifthteenYearsRiskMeasureValue\", \"142\", \"RiskMeasureType\", \"138\", \"NET_EQTY_DELTA\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"MoreFifthteenYearsRiskMeasureValue\", \"142\", \"RiskMeasureType\", \"138\", \"NET_FX_DELTA\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"MoreFifthteenYearsRiskMeasureValue\", \"142\", \"RiskMeasureType\", \"138\", \"NET_CTY_DELTA\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"MoreFifthteenYearsRiskMeasureValue\", \"142\", \"RiskMeasureType\", \"138\", \"VAR\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"MoreFifthteenYearsRiskMeasureValue\", \"142\", \"RiskMeasureType\", \"138\", \"VEGA_EXPO\") == null"
									+ " ) "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"MoreFifthteenYearsRiskMeasureValue\", \"142\", \"RiskMeasureType\", \"138\", \"NET_EQTY_DELTA\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"MoreFifthteenYearsRiskMeasureValue\", \"142\", \"RiskMeasureType\", \"138\", \"NET_EQTY_DELTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"MoreFifthteenYearsRiskMeasureValue\", \"142\", \"RiskMeasureType\", \"138\", \"NET_FX_DELTA\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"MoreFifthteenYearsRiskMeasureValue\", \"142\", \"RiskMeasureType\", \"138\", \"NET_FX_DELTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"MoreFifthteenYearsRiskMeasureValue\", \"142\", \"RiskMeasureType\", \"138\", \"NET_CTY_DELTA\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"MoreFifthteenYearsRiskMeasureValue\", \"142\", \"RiskMeasureType\", \"138\", \"NET_CTY_DELTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"MoreFifthteenYearsRiskMeasureValue\", \"142\", \"RiskMeasureType\", \"138\", \"VAR\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"MoreFifthteenYearsRiskMeasureValue\", \"142\", \"RiskMeasureType\", \"138\", \"VAR\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"MoreFifthteenYearsRiskMeasureValue\", \"142\", \"RiskMeasureType\", \"138\", \"VEGA_EXPO\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"MoreFifthteenYearsRiskMeasureValue\", \"142\", \"RiskMeasureType\", \"138\", \"VEGA_EXPO\");"
									+ "}",
							null,
							"Field(142) is forbidden when field(138) is NET_EQTY_DELTA, NET_FX_DELTA, NET_CTY_DELTA, VAR, VEGA_EXPO.",
							versionAdmin));

			// (143) <CurrentMarketRiskMeasureValue>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField143,
							"(" + reportField143.getReportFieldNum().toString()
									+ ") "
									+ reportField143.getReportFieldName(),
							"if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"CurrentMarketRiskMeasureValue\", \"143\", \"RiskMeasureType\", \"138\", \"NET_EQTY_DELTA\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"CurrentMarketRiskMeasureValue\", \"143\", \"RiskMeasureType\", \"138\", \"NET_FX_DELTA\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"CurrentMarketRiskMeasureValue\", \"143\", \"RiskMeasureType\", \"138\", \"NET_CTY_DELTA\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"CurrentMarketRiskMeasureValue\", \"143\", \"RiskMeasureType\", \"138\", \"VAR\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"CurrentMarketRiskMeasureValue\", \"143\", \"RiskMeasureType\", \"138\", \"NET_DV01\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"CurrentMarketRiskMeasureValue\", \"143\", \"RiskMeasureType\", \"138\", \"NET_CS01\") == null"
									+ " ) "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"CurrentMarketRiskMeasureValue\", \"143\", \"RiskMeasureType\", \"138\", \"NET_EQTY_DELTA\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"CurrentMarketRiskMeasureValue\", \"143\", \"RiskMeasureType\", \"138\", \"NET_EQTY_DELTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"CurrentMarketRiskMeasureValue\", \"143\", \"RiskMeasureType\", \"138\", \"NET_FX_DELTA\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"CurrentMarketRiskMeasureValue\", \"143\", \"RiskMeasureType\", \"138\", \"NET_FX_DELTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"CurrentMarketRiskMeasureValue\", \"143\", \"RiskMeasureType\", \"138\", \"NET_CTY_DELTA\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"CurrentMarketRiskMeasureValue\", \"143\", \"RiskMeasureType\", \"138\", \"NET_CTY_DELTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"CurrentMarketRiskMeasureValue\", \"143\", \"RiskMeasureType\", \"138\", \"VAR\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"CurrentMarketRiskMeasureValue\", \"143\", \"RiskMeasureType\", \"138\", \"VAR\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"CurrentMarketRiskMeasureValue\", \"143\", \"RiskMeasureType\", \"138\", \"NET_DV01\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"CurrentMarketRiskMeasureValue\", \"143\", \"RiskMeasureType\", \"138\", \"NET_DV01\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"CurrentMarketRiskMeasureValue\", \"143\", \"RiskMeasureType\", \"138\", \"NET_CS01\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"CurrentMarketRiskMeasureValue\", \"143\", \"RiskMeasureType\", \"138\", \"NET_CS01\");"
									+ "}",
							null,
							"Field(143) is forbidden when field(138) is NET_EQTY_DELTA, NET_FX_DELTA, NET_CTY_DELTA, VAR, NET_DV01, NET_CS01.",
							versionAdmin));

			// (144) <LowerMarketRiskMeasureValue>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField144,
							"(" + reportField144.getReportFieldNum().toString()
									+ ") "
									+ reportField144.getReportFieldName(),
							"if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LowerMarketRiskMeasureValue\", \"144\", \"RiskMeasureType\", \"138\", \"NET_EQTY_DELTA\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LowerMarketRiskMeasureValue\", \"144\", \"RiskMeasureType\", \"138\", \"NET_FX_DELTA\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LowerMarketRiskMeasureValue\", \"144\", \"RiskMeasureType\", \"138\", \"NET_CTY_DELTA\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LowerMarketRiskMeasureValue\", \"144\", \"RiskMeasureType\", \"138\", \"VAR\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LowerMarketRiskMeasureValue\", \"144\", \"RiskMeasureType\", \"138\", \"NET_DV01\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LowerMarketRiskMeasureValue\", \"144\", \"RiskMeasureType\", \"138\", \"NET_CS01\") == null"
									+ " ) "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LowerMarketRiskMeasureValue\", \"144\", \"RiskMeasureType\", \"138\", \"NET_EQTY_DELTA\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LowerMarketRiskMeasureValue\", \"144\", \"RiskMeasureType\", \"138\", \"NET_EQTY_DELTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LowerMarketRiskMeasureValue\", \"144\", \"RiskMeasureType\", \"138\", \"NET_FX_DELTA\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LowerMarketRiskMeasureValue\", \"144\", \"RiskMeasureType\", \"138\", \"NET_FX_DELTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LowerMarketRiskMeasureValue\", \"144\", \"RiskMeasureType\", \"138\", \"NET_CTY_DELTA\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LowerMarketRiskMeasureValue\", \"144\", \"RiskMeasureType\", \"138\", \"NET_CTY_DELTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LowerMarketRiskMeasureValue\", \"144\", \"RiskMeasureType\", \"138\", \"VAR\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LowerMarketRiskMeasureValue\", \"144\", \"RiskMeasureType\", \"138\", \"VAR\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LowerMarketRiskMeasureValue\", \"144\", \"RiskMeasureType\", \"138\", \"NET_DV01\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LowerMarketRiskMeasureValue\", \"144\", \"RiskMeasureType\", \"138\", \"NET_DV01\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LowerMarketRiskMeasureValue\", \"144\", \"RiskMeasureType\", \"138\", \"NET_CS01\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"LowerMarketRiskMeasureValue\", \"144\", \"RiskMeasureType\", \"138\", \"NET_CS01\");"
									+ "}",
							null,
							"Field(144) is forbidden when field(138) is NET_EQTY_DELTA, NET_FX_DELTA, NET_CTY_DELTA, VAR, NET_DV01, NET_CS01.",
							versionAdmin));

			// (145) <HigherMarketRiskMeasureValue>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField145,
							"(" + reportField145.getReportFieldNum().toString()
									+ ") "
									+ reportField145.getReportFieldName(),
							"if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"HigherMarketRiskMeasureValue\", \"145\", \"RiskMeasureType\", \"138\", \"NET_EQTY_DELTA\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"HigherMarketRiskMeasureValue\", \"145\", \"RiskMeasureType\", \"138\", \"NET_FX_DELTA\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"HigherMarketRiskMeasureValue\", \"145\", \"RiskMeasureType\", \"138\", \"NET_CTY_DELTA\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"HigherMarketRiskMeasureValue\", \"145\", \"RiskMeasureType\", \"138\", \"VAR\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"HigherMarketRiskMeasureValue\", \"145\", \"RiskMeasureType\", \"138\", \"NET_DV01\") == null"
									+ " && ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"HigherMarketRiskMeasureValue\", \"145\", \"RiskMeasureType\", \"138\", \"NET_CS01\") == null"
									+ " ) "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"HigherMarketRiskMeasureValue\", \"145\", \"RiskMeasureType\", \"138\", \"NET_EQTY_DELTA\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"HigherMarketRiskMeasureValue\", \"145\", \"RiskMeasureType\", \"138\", \"NET_EQTY_DELTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"HigherMarketRiskMeasureValue\", \"145\", \"RiskMeasureType\", \"138\", \"NET_FX_DELTA\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"HigherMarketRiskMeasureValue\", \"145\", \"RiskMeasureType\", \"138\", \"NET_FX_DELTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"HigherMarketRiskMeasureValue\", \"145\", \"RiskMeasureType\", \"138\", \"NET_CTY_DELTA\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"HigherMarketRiskMeasureValue\", \"145\", \"RiskMeasureType\", \"138\", \"NET_CTY_DELTA\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"HigherMarketRiskMeasureValue\", \"145\", \"RiskMeasureType\", \"138\", \"VAR\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"HigherMarketRiskMeasureValue\", \"145\", \"RiskMeasureType\", \"138\", \"VAR\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"HigherMarketRiskMeasureValue\", \"145\", \"RiskMeasureType\", \"138\", \"NET_DV01\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"HigherMarketRiskMeasureValue\", \"145\", \"RiskMeasureType\", \"138\", \"NET_DV01\");"
									+ "\n if (ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"HigherMarketRiskMeasureValue\", \"145\", \"RiskMeasureType\", \"138\", \"NET_CS01\") != null) "
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"HigherMarketRiskMeasureValue\", \"145\", \"RiskMeasureType\", \"138\", \"NET_CS01\");"
									+ "}",
							null,
							"Field(145) is forbidden when field(138) is NET_EQTY_DELTA, NET_FX_DELTA, NET_CTY_DELTA, VAR, NET_DV01, NET_CS01.",
							versionAdmin));

			// (302) <VARValue>
			reportSemantics
			.add(new ReportSemantic(
					reportCatalog,
					reportField302,
					"(" + reportField302.getReportFieldNum().toString()
							+ ") "
							+ reportField302.getReportFieldName(),
					"if ( ReportUtilities.dependencyRepeDataExist(reportDatas, \"VARValue\", \"302\", \"RiskMeasureType\", \"138\", \"VAR\") == null "
							+ "&& ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"VARValue\", \"302\", \"RiskMeasureType\", \"138\", \"VAR\") == null "
							+ ") "
							+ "\n result = \"ok\"; \n else {"
							+ "\n if (ReportUtilities.dependencyRepeDataExist(reportDatas, \"VARValue\", \"302\", \"RiskMeasureType\", \"138\", \"VAR\") != null)"
							+ "\n problem = ReportUtilities.dependencyRepeDataExist(reportDatas, \"VARValue\", \"302\", \"RiskMeasureType\", \"138\", \"VAR\");"
							+ "\n if (ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"VARValue\", \"302\", \"RiskMeasureType\", \"138\", \"VAR\") != null)"
							+ "\n problem = ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"VARValue\", \"302\", \"RiskMeasureType\", \"138\", \"VAR\");"
							+ "}",
					null,
					"Field(302) is mandatory when field(138) is VAR, forbidden otherwise.",
					versionAdmin));

			// (146) <VARCalculationMethodCodeType>
			reportSemantics
			.add(new ReportSemantic(
					reportCatalog,
					reportField146,
					"(" + reportField146.getReportFieldNum().toString()
							+ ") "
							+ reportField146.getReportFieldName(),
					"if ( ReportUtilities.dependencyRepeDataExist(reportDatas, \"VARCalculationMethodCodeType\", \"146\", \"RiskMeasureType\", \"138\", \"VAR\") == null "
							+ "&& ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"VARCalculationMethodCodeType\", \"146\", \"RiskMeasureType\", \"138\", \"VAR\") == null "
							+ ") "
							+ "\n result = \"ok\"; \n else {"
							+ "\n if (ReportUtilities.dependencyRepeDataExist(reportDatas, \"VARCalculationMethodCodeType\", \"146\", \"RiskMeasureType\", \"138\", \"VAR\") != null)"
							+ "\n problem = ReportUtilities.dependencyRepeDataExist(reportDatas, \"VARCalculationMethodCodeType\", \"146\", \"RiskMeasureType\", \"138\", \"VAR\");"
							+ "\n if (ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"VARCalculationMethodCodeType\", \"146\", \"RiskMeasureType\", \"138\", \"VAR\") != null)"
							+ "\n problem = ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"VARCalculationMethodCodeType\", \"146\", \"RiskMeasureType\", \"138\", \"VAR\");"
							+ "}",
					null,
					"Field(146) is mandatory when field(138) is VAR, forbidden otherwise.",
					versionAdmin));

			// (147) <RiskMeasureDescription>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField147,
							"(" + reportField147.getReportFieldNum().toString()
									+ ") "
									+ reportField147.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeData(reportDatas, \"RiskMeasureDescription\", \"147\", \"RiskMeasureValue\", \"139\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeData(reportDatas, \"RiskMeasureDescription\", \"147\", \"RiskMeasureValue\", \"139\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeData(reportDatas, \"RiskMeasureDescription\", \"147\", \"RiskMeasureValue\", \"139\");"
									+ "}",
							null,
							"Field(147) is mandatory when field(139) has content.",
							versionAdmin));

			// (148) <RegulatedMarketRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField148,
							// "(" +
							// reportField148.getReportFieldNum().toString()
							// + ") "
							// + reportField148.getReportFieldName(),
							"(148)-(149) sum 100",
							"if ( !ReportUtilities.hasContentRange(reportDatas, \"148\", \"149\") || ReportUtilities.percentageRange(reportDatas, \"148\", \"149\", \"100\") ) "
									+ "\n result = \"ok\"",
							null,
							"Fields (148) to (149) has to sum 100, if there are values.",
							versionAdmin));

			// (149) <OTCRate>

			// (150) <RegulatedMarketRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField150,
							// "(" +
							// reportField150.getReportFieldNum().toString()
							// + ") "
							// + reportField150.getReportFieldName(),
							"(150)-(151) sum 100",
							"if ( !ReportUtilities.hasContentRange(reportDatas, \"150\", \"151\") || ReportUtilities.percentageRange(reportDatas, \"150\", \"151\", \"100\") ) "
									+ "\n result = \"ok\"",
							null,
							"Fields (150) to (151) has to sum 100, if there are values.",
							versionAdmin));

			// (151) <OTCRate>

			// (152) <CCPRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField152,
							// "(" +
							// reportField152.getReportFieldNum().toString()
							// + ") "
							// + reportField152.getReportFieldName(),
							"(152)-(153) sum 100",
							"if ( !ReportUtilities.hasContentRange(reportDatas, \"152\", \"153\") || ReportUtilities.percentageRange(reportDatas, \"152\", \"153\", \"100\") ) "
									+ "\n result = \"ok\"",
							null,
							"Fields (152) to (153) has to sum 100, if there are values.",
							versionAdmin));

			// (153) <BilateralClearingRate>
			// (154) <CCPRate>

			// (155) <BilateralClearingRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField155,
							// "(" +
							// reportField155.getReportFieldNum().toString()
							// + ") "
							// + reportField155.getReportFieldName(),
							"(154)-(156) sum 100",
							"if ( !ReportUtilities.hasContentRange(reportDatas, \"154\", \"156\") || ReportUtilities.percentageRange(reportDatas, \"154\", \"156\", \"100\") ) "
									+ "\n result = \"ok\"",
							null,
							"Fields (154) to (156) has to sum 100, if there are values.",
							versionAdmin));

			// (156) <TriPartyRepoClearingRate>
			// (157) <AllCounterpartyCollateralCash>
			// (158) <AllCounterpartyCollateralSecurities>
			// (159) <AllCounterpartyOtherCollateralPosted>

			// (160) <Ranking>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField160,
							"(" + reportField160.getReportFieldNum().toString()
									+ ") "
									+ reportField160.getReportFieldName(),
							"if (ReportUtilities.searchBlockList(reportDatas, \"Ranking\", \"160\").size() == 5) "
									+ "\n result = \"ok\"", null,
							"Field(160) has 5 occurences.", versionAdmin));

			// (161) <CounterpartyExposureFlag>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField161,
							"(" + reportField161.getReportFieldNum().toString()
									+ ") "
									+ reportField161.getReportFieldName(),
							"if (ReportUtilities.searchBlockList(reportDatas, \"CounterpartyExposureFlag\", \"161\").size() == 5) "
									+ "\n result = \"ok\"", null,
							"Field(161) has 5 occurences.", versionAdmin));

			// (162) <EntityName>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField162,
							"(" + reportField162.getReportFieldNum().toString()
									+ ") "
									+ reportField162.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataExist(reportDatas, \"EntityName\", \"162\", \"CounterpartyExposureFlag\", \"161\", \"true\") == null "
									+ "&& ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"EntityName\", \"162\", \"CounterpartyExposureFlag\", \"161\", \"true\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataExist(reportDatas, \"EntityName\", \"162\", \"CounterpartyExposureFlag\", \"161\", \"true\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataExist(reportDatas, \"EntityName\", \"162\", \"CounterpartyExposureFlag\", \"161\", \"true\");"
									+ "\n if (ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"EntityName\", \"162\", \"CounterpartyExposureFlag\", \"161\", \"true\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"EntityName\", \"162\", \"CounterpartyExposureFlag\", \"161\", \"true\");"
									+ "}",
							null,
							"Field(162) is mandatory when field(161) is true, forbidden otherwise.",
							versionAdmin));

			// (163) <EntityIdentificationLEI>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField163,
							"(" + reportField163.getReportFieldNum().toString()
									+ ") "
									+ reportField163.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataForb(reportDatas, \"EntityIdentificationLEI\", \"163\", \"EntityName\", \"162\") == null) "
									+ "\n result = \"ok\"; \n else {"
									+ "\n problem = ReportUtilities.dependencyRepeDataForb(reportDatas, \"EntityIdentificationLEI\", \"163\", \"EntityName\", \"162\");"
									+ "}",
							null,
							"Field(163) is forbidden when field(162) is empty.",
							versionAdmin));

			// (164) <EntityIdentificationBIC>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField164,
							"(" + reportField164.getReportFieldNum().toString()
									+ ") "
									+ reportField164.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataForb(reportDatas, \"EntityIdentificationBIC\", \"164\", \"EntityName\", \"162\") == null) "
									+ "\n result = \"ok\"; \n else {"
									+ "\n problem = ReportUtilities.dependencyRepeDataForb(reportDatas, \"EntityIdentificationBIC\", \"164\", \"EntityName\", \"162\");"
									+ "}",
							null,
							"Field(164) is forbidden when field(162) is empty.",
							versionAdmin));

			// (165) <CounterpartyTotalExposureRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField165,
							"(" + reportField165.getReportFieldNum().toString()
									+ ") "
									+ reportField165.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataExist(reportDatas, \"CounterpartyTotalExposureRate\", \"165\", \"CounterpartyExposureFlag\", \"161\", \"true\") == null "
									+ "&& ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"CounterpartyTotalExposureRate\", \"165\", \"CounterpartyExposureFlag\", \"161\", \"true\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataExist(reportDatas, \"CounterpartyTotalExposureRate\", \"165\", \"CounterpartyExposureFlag\", \"161\", \"true\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataExist(reportDatas, \"CounterpartyTotalExposureRate\", \"165\", \"CounterpartyExposureFlag\", \"161\", \"true\");"
									+ "\n if (ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"CounterpartyTotalExposureRate\", \"165\", \"CounterpartyExposureFlag\", \"161\", \"true\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"CounterpartyTotalExposureRate\", \"165\", \"CounterpartyExposureFlag\", \"161\", \"true\");"
									+ "}",
							null,
							"Field(165) is mandatory when field(161) is true, forbidden otherwise.",
							versionAdmin));

			// (166) <Ranking>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField166,
							"(" + reportField166.getReportFieldNum().toString()
									+ ") "
									+ reportField166.getReportFieldName(),
							"if (ReportUtilities.searchBlockList(reportDatas, \"Ranking\", \"166\").size() == 5) "
									+ "\n result = \"ok\"", null,
							"Field(166) has 5 occurences.", versionAdmin));

			// (167) <CounterpartyExposureFlag>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField167,
							"(" + reportField167.getReportFieldNum().toString()
									+ ") "
									+ reportField167.getReportFieldName(),
							"if (ReportUtilities.searchBlockList(reportDatas, \"CounterpartyExposureFlag\", \"167\").size() == 5) "
									+ "\n result = \"ok\"", null,
							"Field(167) has 5 occurences.", versionAdmin));

			// (168) <EntityName>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField168,
							"(" + reportField168.getReportFieldNum().toString()
									+ ") "
									+ reportField168.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataExist(reportDatas, \"EntityName\", \"168\", \"CounterpartyExposureFlag\", \"167\", \"true\") == null "
									+ "&& ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"EntityName\", \"168\", \"CounterpartyExposureFlag\", \"167\", \"true\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataExist(reportDatas, \"EntityName\", \"168\", \"CounterpartyExposureFlag\", \"167\", \"true\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataExist(reportDatas, \"EntityName\", \"168\", \"CounterpartyExposureFlag\", \"167\", \"true\");"
									+ "\n if (ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"EntityName\", \"168\", \"CounterpartyExposureFlag\", \"167\", \"true\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"EntityName\", \"168\", \"CounterpartyExposureFlag\", \"167\", \"true\");"
									+ "}",
							null,
							"Field(168) is mandatory when field(167) is true, forbidden otherwise.",
							versionAdmin));

			// (169) <EntityIdentificationLEI>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField169,
							"(" + reportField169.getReportFieldNum().toString()
									+ ") "
									+ reportField169.getReportFieldName(),
							"if (ReportUtilities.dependencyRepeDataForb(reportDatas, \"EntityIdentificationLEI\", \"169\", \"EntityName\", \"168\") == null) "
									+ "\n result = \"ok\"; else {"
									+ "\n problem = ReportUtilities.dependencyRepeDataForb(reportDatas, \"EntityIdentificationLEI\", \"169\", \"EntityName\", \"168\");"
									+ "}",
							null,
							"Field(169) is forbidden when field(168) is empty.",
							versionAdmin));

			// (170) <EntityIdentificationBIC>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField170,
							"(" + reportField170.getReportFieldNum().toString()
									+ ") "
									+ reportField170.getReportFieldName(),
							"if (ReportUtilities.dependencyRepeDataForb(reportDatas, \"EntityIdentificationBIC\", \"170\", \"EntityName\", \"168\") == null) "
									+ "\n result = \"ok\"; else {"
									+ "\n problem = ReportUtilities.dependencyRepeDataForb(reportDatas, \"EntityIdentificationBIC\", \"170\", \"EntityName\", \"168\");"
									+ "}",
							null,
							"Field(170) is forbidden when field(168) is empty.",
							versionAdmin));

			// (171) <CounterpartyTotalExposureRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField171,
							"(" + reportField171.getReportFieldNum().toString()
									+ ") "
									+ reportField171.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataExist(reportDatas, \"CounterpartyTotalExposureRate\", \"171\", \"CounterpartyExposureFlag\", \"167\", \"true\") == null "
									+ "&& ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"CounterpartyTotalExposureRate\", \"171\", \"CounterpartyExposureFlag\", \"167\", \"true\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataExist(reportDatas, \"CounterpartyTotalExposureRate\", \"171\", \"CounterpartyExposureFlag\", \"167\", \"true\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataExist(reportDatas, \"CounterpartyTotalExposureRate\", \"171\", \"CounterpartyExposureFlag\", \"167\", \"true\");"
									+ "\n if (ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"CounterpartyTotalExposureRate\", \"171\", \"CounterpartyExposureFlag\", \"167\", \"true\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"CounterpartyTotalExposureRate\", \"171\", \"CounterpartyExposureFlag\", \"167\", \"true\");"
									+ "}",
							null,
							"Field(171) is mandatory when field(167) is true, forbidden otherwise.",
							versionAdmin));

			// (172) <ClearTransactionsThroughCCPFlag>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField172,
							"(" + reportField172.getReportFieldNum().toString()
									+ ") "
									+ reportField172.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"ClearTransactionsThroughCCPFlag\", \"172\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(172).", versionAdmin));

			// (173) <Ranking>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField173,
							"(" + reportField173.getReportFieldNum().toString()
									+ ") "
									+ reportField173.getReportFieldName(),
							"if ( ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"Ranking\", \"173\", \"ClearTransactionsThroughCCPFlag\", \"172\", \"true\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"Ranking\", \"173\", \"ClearTransactionsThroughCCPFlag\", \"172\", \"true\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"Ranking\", \"173\", \"ClearTransactionsThroughCCPFlag\", \"172\", \"true\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"Ranking\", \"173\", \"ClearTransactionsThroughCCPFlag\", \"172\", \"true\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"Ranking\", \"173\", \"ClearTransactionsThroughCCPFlag\", \"172\", \"true\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"Ranking\", \"173\", \"ClearTransactionsThroughCCPFlag\", \"172\", \"true\");"
									+ "}",
							null,
							"Field(173) is mandatory when field(172) is true, forbidden otherwise.",
							versionAdmin));

			// (174) <EntityName>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField174,
							"(" + reportField174.getReportFieldNum().toString()
									+ ") "
									+ reportField174.getReportFieldName(),
							"if ( ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"EntityName\", \"174\", \"ClearTransactionsThroughCCPFlag\", \"172\", \"true\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"EntityName\", \"174\", \"ClearTransactionsThroughCCPFlag\", \"172\", \"true\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"EntityName\", \"174\", \"ClearTransactionsThroughCCPFlag\", \"172\", \"true\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"EntityName\", \"174\", \"ClearTransactionsThroughCCPFlag\", \"172\", \"true\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"EntityName\", \"174\", \"ClearTransactionsThroughCCPFlag\", \"172\", \"true\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"EntityName\", \"174\", \"ClearTransactionsThroughCCPFlag\", \"172\", \"true\");"
									+ "}",
							null,
							"Field(174) is mandatory when field(172) is true, forbidden otherwise.",
							versionAdmin));

			// (175) <EntityIdentificationLEI>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField175,
							"(" + reportField175.getReportFieldNum().toString()
									+ ") "
									+ reportField175.getReportFieldName(),
							"if ( ReportUtilities.dependencyFieldRepeDataEmpty(reportDatas, \"EntityIdentificationLEI\", \"175\", \"ClearTransactionsThroughCCPFlag\", \"172\", \"false\") == null) "
									+ "\n result = \"ok\"; \n else {"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataEmpty(reportDatas, \"EntityIdentificationLEI\", \"175\", \"ClearTransactionsThroughCCPFlag\", \"172\", \"false\");"
									+ "}",
							null,
							"Field(175) is forbidden when field(172) is false.",
							versionAdmin));

			// (176) <EntityIdentificationBIC>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField176,
							"(" + reportField176.getReportFieldNum().toString()
									+ ") "
									+ reportField176.getReportFieldName(),
							"if ( ReportUtilities.dependencyFieldRepeDataEmpty(reportDatas, \"EntityIdentificationBIC\", \"176\", \"ClearTransactionsThroughCCPFlag\", \"172\", \"false\") == null) "
									+ "\n result = \"ok\"; \n else {"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataEmpty(reportDatas, \"EntityIdentificationBIC\", \"176\", \"ClearTransactionsThroughCCPFlag\", \"172\", \"false\");"
									+ "}",
							null,
							"Field(176) is forbidden when field(172) is false.",
							versionAdmin));

			// (177) <CCPExposureValue>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField177,
							"(" + reportField177.getReportFieldNum().toString()
									+ ") "
									+ reportField177.getReportFieldName(),
							"if ( ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"CCPExposureValue\", \"177\", \"ClearTransactionsThroughCCPFlag\", \"172\", \"true\") == null "
									+ "&& ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"CCPExposureValue\", \"177\", \"ClearTransactionsThroughCCPFlag\", \"172\", \"true\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"CCPExposureValue\", \"177\", \"ClearTransactionsThroughCCPFlag\", \"172\", \"true\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataExist(reportDatas, \"CCPExposureValue\", \"177\", \"ClearTransactionsThroughCCPFlag\", \"172\", \"true\");"
									+ "\n if (ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"CCPExposureValue\", \"177\", \"ClearTransactionsThroughCCPFlag\", \"172\", \"true\") != null)"
									+ "\n problem = ReportUtilities.dependencyFieldRepeDataDiffEmpty(reportDatas, \"CCPExposureValue\", \"177\", \"ClearTransactionsThroughCCPFlag\", \"172\", \"true\");"
									+ "}",
							null,
							"Field(177) is mandatory when field(172) is true, forbidden otherwise.",
							versionAdmin));

			// (178) <PortfolioLiquidityInDays0to1Rate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField178,
							// "(" +
							// reportField178.getReportFieldNum().toString()
							// + ") "
							// + reportField178.getReportFieldName(),
							"(178)-(184) sum 100",
							"if ( !ReportUtilities.hasContentRange(reportDatas, \"178\", \"184\") || ReportUtilities.percentageRange(reportDatas, \"178\", \"184\", \"100\") ) "
									+ "\n result = \"ok\"",
							null,
							"Fields (178) to (184) has to sum 100 if there are values.",
							versionAdmin));

			// (179) <PortfolioLiquidityInDays2to7Rate>
			// (180) <PortfolioLiquidityInDays8to30Rate>
			// (181) <PortfolioLiquidityInDays31to90Rate>
			// (182) <PortfolioLiquidityInDays91to180Rate>
			// (183) <PortfolioLiquidityInDays181to365Rate>
			// (184) <PortfolioLiquidityInDays365MoreRate>
			// (185) <UnencumberedCash>

			// (186) <InvestorLiquidityInDays0to1Rate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField186,
							// "(" +
							// reportField186.getReportFieldNum().toString()
							// + ") "
							// + reportField186.getReportFieldName(),
							"(186)-(192) sum 100",
							"if ( !ReportUtilities.hasContentRange(reportDatas, \"186\", \"192\") || ReportUtilities.percentageRange(reportDatas, \"186\", \"192\", \"100\") ) "
									+ "\n result = \"ok\"", null,
							"Fields (186) to (192) has to sum 100.",
							versionAdmin));

			// (187) <InvestorLiquidityInDays2to7Rate>
			// (188) <InvestorLiquidityInDays8to30Rate>
			// (189) <InvestorLiquidityInDays31to90Rate>
			// (190) <InvestorLiquidityInDays91to180Rate>
			// (191) <InvestorLiquidityInDays181to365Rate>
			// (192) <InvestorLiquidityInDays365MoreRate>
			// (193) <ProvideWithdrawalRightsFlag>

			// (194) <InvestorRedemptionFrequency>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField194,
							"(" + reportField194.getReportFieldNum().toString()
									+ ") "
									+ reportField194.getReportFieldName(),
							"if ( (ReportUtilities.searchData(reportDatas, \"ProvideWithdrawalRightsFlag\", \"193\", null) == null) "
									+ " || (ReportUtilities.searchData(reportDatas, \"ProvideWithdrawalRightsFlag\", \"193\", null) != null"
									+ " && ReportUtilities.searchData(reportDatas, \"ProvideWithdrawalRightsFlag\", \"193\", null).equals(\"true\") )"
									+ " || (ReportUtilities.searchData(reportDatas, \"ProvideWithdrawalRightsFlag\", \"193\", null) != null "
									+ " && ReportUtilities.searchData(reportDatas, \"ProvideWithdrawalRightsFlag\", \"193\", null).equals(\"false\") "
									+ " && ReportUtilities.searchData(reportDatas, \"InvestorRedemptionFrequency\", \"194\", null) == null ) "
									+ ") " + "\n result = \"ok\"",
							null,
							"Field(194) is forbidden when field(193) is false.",
							versionAdmin));

			// (195) <InvestorRedemptionNoticePeriod>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField195,
							"(" + reportField195.getReportFieldNum().toString()
									+ ") "
									+ reportField195.getReportFieldName(),
							"if ( (ReportUtilities.searchData(reportDatas, \"ProvideWithdrawalRightsFlag\", \"193\", null) == null) "
									+ " || (ReportUtilities.searchData(reportDatas, \"ProvideWithdrawalRightsFlag\", \"193\", null) != null"
									+ " && ReportUtilities.searchData(reportDatas, \"ProvideWithdrawalRightsFlag\", \"193\", null).equals(\"true\") )"
									+ " || (ReportUtilities.searchData(reportDatas, \"ProvideWithdrawalRightsFlag\", \"193\", null) != null "
									+ " && ReportUtilities.searchData(reportDatas, \"ProvideWithdrawalRightsFlag\", \"193\", null).equals(\"false\") "
									+ " && ReportUtilities.searchData(reportDatas, \"InvestorRedemptionNoticePeriod\", \"195\", null) == null ) "
									+ ") " + "\n result = \"ok\"",
							null,
							"Field(195) is forbidden when field(193) is false.",
							versionAdmin));

			// (196) <InvestorRedemptionLockUpPeriod>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField196,
							"(" + reportField196.getReportFieldNum().toString()
									+ ") "
									+ reportField196.getReportFieldName(),
							"if ( (ReportUtilities.searchData(reportDatas, \"ProvideWithdrawalRightsFlag\", \"193\", null) == null) "
									+ " || (ReportUtilities.searchData(reportDatas, \"ProvideWithdrawalRightsFlag\", \"193\", null) != null"
									+ " && ReportUtilities.searchData(reportDatas, \"ProvideWithdrawalRightsFlag\", \"193\", null).equals(\"true\") )"
									+ " || (ReportUtilities.searchData(reportDatas, \"ProvideWithdrawalRightsFlag\", \"193\", null) != null "
									+ " && ReportUtilities.searchData(reportDatas, \"ProvideWithdrawalRightsFlag\", \"193\", null).equals(\"false\") "
									+ " && ReportUtilities.searchData(reportDatas, \"InvestorRedemptionLockUpPeriod\", \"196\", null) == null ) "
									+ ") " + "\n result = \"ok\"",
							null,
							"Field(196) is forbidden when field(193) is false.",
							versionAdmin));

			// (197) <SidePocketRate>
			// (198) <GatesRate>
			// (199) <DealingSuspensionRate>

			// (200) <OtherArrangementType>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField200,
							"(" + reportField200.getReportFieldNum().toString()
									+ ") "
									+ reportField200.getReportFieldName(),
							"if ( (ReportUtilities.searchData(reportDatas, \"OtherArrangementType\", \"200\", null) != null"
									+ " && ReportUtilities.searchData(reportDatas, \"OtherArrangementRate\", \"201\", null) != null )"
									+ " || (ReportUtilities.searchData(reportDatas, \"OtherArrangementType\", \"200\", null) == null"
									+ " && ReportUtilities.searchData(reportDatas, \"OtherArrangementRate\", \"201\", null) == null)"
									+ ") " + "\n result = \"ok\"",
							null,
							"Field(200) is mandatory when field(201) has content.",
							versionAdmin));

			// (201) <OtherArrangementRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField201,
							"(" + reportField201.getReportFieldNum().toString()
									+ ") "
									+ reportField201.getReportFieldName(),
							"if ( (ReportUtilities.searchData(reportDatas, \"OtherArrangementType\", \"200\", null) != null"
									+ " && ReportUtilities.searchData(reportDatas, \"OtherArrangementRate\", \"201\", null) != null )"
									+ " || (ReportUtilities.searchData(reportDatas, \"OtherArrangementType\", \"200\", null) == null"
									+ " && ReportUtilities.searchData(reportDatas, \"OtherArrangementRate\", \"201\", null) == null)"
									+ ") " + "\n result = \"ok\"",
							null,
							"Field(201) is mandatory when field(200) has content.",
							versionAdmin));

			// (202) <TotalArrangementRate>
			// (203) <InvestorPreferentialTreatmentFlag>
			// (204) <DisclosureTermsPreferentialTreatmentFlag>
			// (205) <LiquidityTermsPreferentialTreatmentFlag>
			// (206) <FeeTermsPreferentialTreatmentFlag>
			// (207) <OtherTermsPreferentialTreatmentFlag>

			// (208) <InvestorGroupType>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField208,
							"(" + reportField208.getReportFieldNum().toString()
									+ ") "
									+ reportField208.getReportFieldName(),
							"if (ReportUtilities.searchBlockList(reportDatas, \"InvestorGroupType\", \"208\").size() >= 1) "
									+ "\n result = \"ok\"", null,
							"Field(208) has at least 1 occurence.",
							versionAdmin));

			// (209) <InvestorGroupRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField209,
							"(" + reportField209.getReportFieldNum().toString()
									+ ") "
									+ reportField209.getReportFieldName(),
							"if (ReportUtilities.searchBlockList(reportDatas, \"InvestorGroupRate\", \"209\").size() >= 1) "
									+ "\n result = \"ok\"", null,
							"Field(209) has at least 1 occurence.",
							versionAdmin));

			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField209,
							"(" + reportField209.getReportFieldNum().toString()
									+ ") "
									+ reportField209.getReportFieldName()
									+ " sum 100",
							"if ( ReportUtilities.percentageField(reportDatas, \"InvestorGroupRate\", \"209\", \"100\") ) "
									+ "\n result = \"ok\"", null,
							"Fields (209) has to sum 100.", versionAdmin));

			// (210) <TotalFinancingAmount>

			// (211) <TotalFinancingInDays0to1Rate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField211,
							// "(" +
							// reportField211.getReportFieldNum().toString()
							// + ") "
							// + reportField211.getReportFieldName(),
							"(211)-(217) sum 100",
							"if ( !ReportUtilities.hasContentRange(reportDatas, \"211\", \"217\") || ReportUtilities.percentageRange(reportDatas, \"211\", \"217\", \"100\") ) "
									+ "\n result = \"ok\"",
							null,
							"Fields (211) to (217) has to sum 100 , if there are values.",
							versionAdmin));

			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField211,
							"(" + reportField211.getReportFieldNum().toString()
									+ ") "
									+ reportField211.getReportFieldName(),
							"if ( (ReportUtilities.searchData(reportDatas, \"TotalFinancingAmount\", \"210\", null) != null "
									+ "&& ReportUtilities.searchData(reportDatas, \"TotalFinancingInDays0to1Rate\", \"211\", null) != null ) "
									+ " || ( ReportUtilities.searchData(reportDatas, \"TotalFinancingAmount\", \"210\", null) == null"
									+ " && ReportUtilities.searchData(reportDatas, \"TotalFinancingInDays0to1Rate\", \"211\", null) == null )"
									+ ") " + "\n result = \"ok\"",
							null,
							"Field (211) is mandatory when field(210) has content, forbidden otherwise.",
							versionAdmin));

			// (212) <TotalFinancingInDays2to7Rate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField212,
							"(" + reportField212.getReportFieldNum().toString()
									+ ") "
									+ reportField212.getReportFieldName(),
							"if ( (ReportUtilities.searchData(reportDatas, \"TotalFinancingAmount\", \"210\", null) != null "
									+ "&& ReportUtilities.searchData(reportDatas, \"TotalFinancingInDays2to7Rate\", \"212\", null) != null ) "
									+ " || ( ReportUtilities.searchData(reportDatas, \"TotalFinancingAmount\", \"210\", null) == null"
									+ " && ReportUtilities.searchData(reportDatas, \"TotalFinancingInDays2to7Rate\", \"212\", null) == null )"
									+ ") " + "\n result = \"ok\"",
							null,
							"Field (212) is mandatory when field(210) has content, forbidden otherwise.",
							versionAdmin));

			// (213) <TotalFinancingInDays8to30Rate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField213,
							"(" + reportField213.getReportFieldNum().toString()
									+ ") "
									+ reportField213.getReportFieldName(),
							"if ( (ReportUtilities.searchData(reportDatas, \"TotalFinancingAmount\", \"210\", null) != null "
									+ "&& ReportUtilities.searchData(reportDatas, \"TotalFinancingInDays8to30Rate\", \"213\", null) != null ) "
									+ " || ( ReportUtilities.searchData(reportDatas, \"TotalFinancingAmount\", \"210\", null) == null"
									+ " && ReportUtilities.searchData(reportDatas, \"TotalFinancingInDays8to30Rate\", \"213\", null) == null )"
									+ ") " + "\n result = \"ok\"",
							null,
							"Field (213) is mandatory when field(210) has content, forbidden otherwise.",
							versionAdmin));

			// (214) <TotalFinancingInDays31to90Rate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField214,
							"(" + reportField214.getReportFieldNum().toString()
									+ ") "
									+ reportField214.getReportFieldName(),
							"if ( (ReportUtilities.searchData(reportDatas, \"TotalFinancingAmount\", \"210\", null) != null "
									+ "&& ReportUtilities.searchData(reportDatas, \"TotalFinancingInDays31to90Rate\", \"214\", null) != null ) "
									+ " || ( ReportUtilities.searchData(reportDatas, \"TotalFinancingAmount\", \"210\", null) == null"
									+ " && ReportUtilities.searchData(reportDatas, \"TotalFinancingInDays31to90Rate\", \"214\", null) == null )"
									+ ") " + "\n result = \"ok\"",
							null,
							"Field (214) is mandatory when field(210) has content, forbidden otherwise.",
							versionAdmin));

			// (215) <TotalFinancingInDays91to180Rate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField215,
							"(" + reportField215.getReportFieldNum().toString()
									+ ") "
									+ reportField215.getReportFieldName(),
							"if ( (ReportUtilities.searchData(reportDatas, \"TotalFinancingAmount\", \"210\", null) != null "
									+ "&& ReportUtilities.searchData(reportDatas, \"TotalFinancingInDays91to180Rate\", \"215\", null) != null ) "
									+ " || ( ReportUtilities.searchData(reportDatas, \"TotalFinancingAmount\", \"210\", null) == null"
									+ " && ReportUtilities.searchData(reportDatas, \"TotalFinancingInDays91to180Rate\", \"215\", null) == null )"
									+ ") " + "\n result = \"ok\"",
							null,
							"Field (215) is mandatory when field(210) has content, forbidden otherwise.",
							versionAdmin));

			// (216) <TotalFinancingInDays181to365Rate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField216,
							"(" + reportField216.getReportFieldNum().toString()
									+ ") "
									+ reportField216.getReportFieldName(),
							"if ( (ReportUtilities.searchData(reportDatas, \"TotalFinancingAmount\", \"210\", null) != null "
									+ "&& ReportUtilities.searchData(reportDatas, \"TotalFinancingInDays181to365Rate\", \"216\", null) != null ) "
									+ " || ( ReportUtilities.searchData(reportDatas, \"TotalFinancingAmount\", \"210\", null) == null"
									+ " && ReportUtilities.searchData(reportDatas, \"TotalFinancingInDays181to365Rate\", \"216\", null) == null )"
									+ ") " + "\n result = \"ok\"",
							null,
							"Field (216) is mandatory when field(210) has content, forbidden otherwise.",
							versionAdmin));

			// (217) <TotalFinancingInDays365MoreRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField217,
							"(" + reportField217.getReportFieldNum().toString()
									+ ") "
									+ reportField217.getReportFieldName(),
							"if ( (ReportUtilities.searchData(reportDatas, \"TotalFinancingAmount\", \"210\", null) != null "
									+ "&& ReportUtilities.searchData(reportDatas, \"TotalFinancingInDays365MoreRate\", \"217\", null) != null ) "
									+ " || ( ReportUtilities.searchData(reportDatas, \"TotalFinancingAmount\", \"210\", null) == null"
									+ " && ReportUtilities.searchData(reportDatas, \"TotalFinancingInDays365MoreRate\", \"217\", null) == null )"
									+ ") " + "\n result = \"ok\"",
							null,
							"Field (217) is mandatory when field(210) has content, forbidden otherwise.",
							versionAdmin));

			// (218) <TotalOpenPositions>
			// (219) <RateJanuary>
			// (220) <RateFebruary>
			// (221) <RateMarch>
			// (222) <RateApril>
			// (223) <RateMay>
			// (224) <RateJune>
			// (225) <RateJuly>
			// (226) <RateAugust>
			// (227) <RateSeptember>
			// (228) <RateOctober>
			// (229) <RateNovember>
			// (230) <RateDecember>
			// (231) <RateJanuary>
			// (232) <RateFebruary>
			// (233) <RateMarch>
			// (234) <RateApril>
			// (235) <RateMay>
			// (236) <RateJune>
			// (237) <RateJuly>
			// (238) <RateAugust>
			// (239) <RateSeptember>
			// (240) <RateOctober>
			// (241) <RateNovember>
			// (242) <RateDecember>
			// (243) <RateJanuary>
			// (244) <RateFebruary>
			// (245) <RateMarch>
			// (246) <RateApril>
			// (247) <RateMay>
			// (248) <RateJune>
			// (249) <RateJuly>
			// (250) <RateAugust>
			// (251) <RateSeptember>
			// (252) <RateOctober>
			// (253) <RateNovember>
			// (254) <RateDecember>
			// (255) <QuantityJanuary>
			// (256) <QuantityFebruary>
			// (257) <QuantityMarch>
			// (258) <QuantityApril>
			// (259) <QuantityMay>
			// (260) <QuantityJune>
			// (261) <QuantityJuly>
			// (262) <QuantityAugust>
			// (263) <QuantitySeptember>
			// (264) <QuantityOctober>
			// (265) <QuantityNovember>
			// (266) <QuantityDecember>
			// (267) <QuantityJanuary>
			// (268) <QuantityFebruary>
			// (269) <QuantityMarch>
			// (270) <QuantityApril>
			// (271) <QuantityMay>
			// (272) <QuantityJune>
			// (273) <QuantityJuly>
			// (274) <QuantityAugust>
			// (275) <QuantitySeptember>
			// (276) <QuantityOctober>
			// (277) <QuantityNovember>
			// (278) <QuantityDecember>

			// (279) <StressTestsResultArticle15>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField279,
							"(" + reportField279.getReportFieldNum().toString()
									+ ") "
									+ reportField279.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"StressTestsResultArticle15\", \"279\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(279).", versionAdmin));

			// (280) <StressTestsResultArticle16>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField280,
							"(" + reportField280.getReportFieldNum().toString()
									+ ") "
									+ reportField280.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"StressTestsResultArticle16\", \"280\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(280).", versionAdmin));

			// (281) <AllCounterpartyCollateralRehypothecationFlag>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField281,
							"(" + reportField281.getReportFieldNum().toString()
									+ ") "
									+ reportField281.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"AllCounterpartyCollateralRehypothecationFlag\", \"281\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(281).", versionAdmin));

			// (282) <AllCounterpartyCollateralRehypothecatedRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField282,
							"(" + reportField282.getReportFieldNum().toString()
									+ ") "
									+ reportField282.getReportFieldName(),
							"if ( (ReportUtilities.searchData(reportDatas, \"AllCounterpartyCollateralRehypothecationFlag\", \"281\", null) == null) "
									+ " || (ReportUtilities.searchData(reportDatas, \"AllCounterpartyCollateralRehypothecationFlag\", \"281\", null) != null"
									+ " && ReportUtilities.searchData(reportDatas, \"AllCounterpartyCollateralRehypothecationFlag\", \"281\", null).equals(\"true\") )"
									+ " || (ReportUtilities.searchData(reportDatas, \"AllCounterpartyCollateralRehypothecationFlag\", \"281\", null) != null "
									+ " && ReportUtilities.searchData(reportDatas, \"AllCounterpartyCollateralRehypothecationFlag\", \"281\", null).equals(\"false\") "
									+ " && ReportUtilities.searchData(reportDatas, \"AllCounterpartyCollateralRehypothecatedRate\", \"282\", null) == null ) "
									+ ") " + "\n result = \"ok\"",
							null,
							"Field(282) is forbidden when field(281) is false.",
							versionAdmin));

			// (283) <UnsecuredBorrowingAmount>
			// (284) <SecuredBorrowingPrimeBrokerageAmount>
			// (285) <SecuredBorrowingReverseRepoAmount>
			// (286) <SecuredBorrowingOtherAmount>
			// (287) <ExchangedTradedDerivativesExposureValue>
			// (288) <OTCDerivativesAmount>
			// (289) <ShortPositionBorrowedSecuritiesValue>
			// (290) <EntityName>
			// (291) <EntityIdentificationLEI>
			// (292) <EntityIdentificationBIC>

			// (293) <ControlledStructureExposureValue>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField293,
							"(" + reportField293.getReportFieldNum().toString()
									+ ") "
									+ reportField293.getReportFieldName(),
							"if (ReportUtilities.dependencyRepeData(reportDatas, \"ControlledStructureExposureValue\", \"293\", \"EntityName\", \"290\") == null) "
									+ "\n result = \"ok\"; \n else {"
									+ "\n problem = ReportUtilities.dependencyRepeData(reportDatas, \"ControlledStructureExposureValue\", \"293\", \"EntityName\", \"290\");"
									+ "}",
							null,
							"Field(293) is mandatory when field(290) has content.",
							versionAdmin));

			// (294) <GrossMethodRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField294,
							"(" + reportField294.getReportFieldNum().toString()
									+ ") "
									+ reportField294.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"GrossMethodRate\", \"294\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(294).", versionAdmin));

			// (295) <CommitmentMethodRate>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField295,
							"(" + reportField295.getReportFieldNum().toString()
									+ ") "
									+ reportField295.getReportFieldName(),
							"if ( ReportUtilities.searchData(reportDatas, \"CommitmentMethodRate\", \"295\", null) != null) "
									+ "\n result = \"ok\"", null,
							"Fill field(295).", versionAdmin));

			// (296) <Ranking>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField296,
							"(" + reportField296.getReportFieldNum().toString()
									+ ") "
									+ reportField296.getReportFieldName(),
							"if (ReportUtilities.searchBlockList(reportDatas, \"Ranking\", \"296\").size() == 5) "
									+ "\n result = \"ok\"", null,
							"Field(296) has 5 occurences.", versionAdmin));

			// (297) <BorrowingSourceFlag>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField297,
							"(" + reportField297.getReportFieldNum().toString()
									+ ") "
									+ reportField297.getReportFieldName(),
							"if (ReportUtilities.searchBlockList(reportDatas, \"BorrowingSourceFlag\", \"297\").size() == 5) "
									+ "\n result = \"ok\"", null,
							"Field(297) has 5 occurences.", versionAdmin));

			// (298) <EntityName>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField298,
							"(" + reportField298.getReportFieldNum().toString()
									+ ") "
									+ reportField298.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataExist(reportDatas, \"EntityName\", \"298\", \"BorrowingSourceFlag\", \"297\", \"true\") == null "
									+ "&& ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"EntityName\", \"298\", \"BorrowingSourceFlag\", \"297\", \"true\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataExist(reportDatas, \"EntityName\", \"298\", \"BorrowingSourceFlag\", \"297\", \"true\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataExist(reportDatas, \"EntityName\", \"298\", \"BorrowingSourceFlag\", \"297\", \"true\");"
									+ "\n if (ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"EntityName\", \"298\", \"BorrowingSourceFlag\", \"297\", \"true\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"EntityName\", \"298\", \"BorrowingSourceFlag\", \"297\", \"true\");"
									+ "}",
							null,
							"Field(298) is mandatory when field(297) is true, forbidden otherwise.",
							versionAdmin));

			// (299) <EntityIdentificationLEI>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField299,
							"(" + reportField299.getReportFieldNum().toString()
									+ ") "
									+ reportField299.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"EntityIdentificationLEI\", \"299\", \"BorrowingSourceFlag\", \"297\", \"false\") == null) "
									+ "\n result = \"ok\"; \n else {"
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"EntityIdentificationLEI\", \"299\", \"BorrowingSourceFlag\", \"297\", \"false\");"
									+ "}",
							null,
							"Field(299) is forbidden when field(297) is false.",
							versionAdmin));

			// (300) <EntityIdentificationBIC>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField300,
							"(" + reportField300.getReportFieldNum().toString()
									+ ") "
									+ reportField300.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"EntityIdentificationBIC\", \"300\", \"BorrowingSourceFlag\", \"297\", \"false\") == null) "
									+ "\n result = \"ok\"; \n else {"
									+ "\n problem = ReportUtilities.dependencyRepeDataEmpty(reportDatas, \"EntityIdentificationBIC\", \"300\", \"BorrowingSourceFlag\", \"297\", \"false\");"
									+ "}",
							null,
							"Field(300) is forbidden when field(297) is false.",
							versionAdmin));

			// (301) <LeverageAmount>
			reportSemantics
					.add(new ReportSemantic(
							reportCatalog,
							reportField301,
							"(" + reportField301.getReportFieldNum().toString()
									+ ") "
									+ reportField301.getReportFieldName(),
							"if ( ReportUtilities.dependencyRepeDataExist(reportDatas, \"LeverageAmount\", \"301\", \"BorrowingSourceFlag\", \"297\", \"true\") == null "
									+ "&& ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"LeverageAmount\", \"301\", \"BorrowingSourceFlag\", \"297\", \"true\") == null "
									+ ") "
									+ "\n result = \"ok\"; \n else {"
									+ "\n if (ReportUtilities.dependencyRepeDataExist(reportDatas, \"LeverageAmount\", \"301\", \"BorrowingSourceFlag\", \"297\", \"true\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataExist(reportDatas, \"LeverageAmount\", \"301\", \"BorrowingSourceFlag\", \"297\", \"true\");"
									+ "\n if (ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"LeverageAmount\", \"301\", \"BorrowingSourceFlag\", \"297\", \"true\") != null)"
									+ "\n problem = ReportUtilities.dependencyRepeDataDiffEmpty(reportDatas, \"LeverageAmount\", \"301\", \"BorrowingSourceFlag\", \"297\", \"true\");"
									+ "}",
							null,
							"Fill field(301) is mandatory when field(297) is true, forbidden otherwise.",
							versionAdmin));

			// (303) <CancelledAIFNationalCode>
			// (304) <CancelledAIFMNationalCode>
			// (305) <CancelledReportingPeriodType>
			// (306) <CancelledReportingPeriodYear>
			// (307) <CancelledRecordFlag>

			// /////////////////////////////////////////////////////////////////
			// DAO

			ReportSemanticDAO reportSemanticDAO = (ReportSemanticDAO) applicationContext
					.getBean("reportSemanticDAO");

			for (ReportSemantic reportSemanticExample : reportSemantics) {
				reportSemanticDAO.create(reportSemanticExample);
			}

		} catch (Exception e) {
			logger.error("Error in installation AIF: " + e.getMessage());
		}
	}
}
