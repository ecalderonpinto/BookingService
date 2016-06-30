package com.reportingtool.utilities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.entities.dao.reportingtool.ReportDataDAO;
import com.entities.dao.reportingtool.ReportExecutionDAO;
import com.entities.dao.reportingtool.ReportFieldDAO;
import com.entities.dao.usermanager.UserControlDAO;
import com.entities.dictionary.ReportCatalogLevelEnum;
import com.entities.entity.reportingtool.Company;
import com.entities.entity.reportingtool.ReportCatalog;
import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportDataError;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.entity.reportingtool.ReportField;
import com.entities.entity.usermanager.User;
import com.entities.entity.usermanager.UserControl;
import com.entities.utilities.hibernate.VersionAuditor;

/**
 * Class that contains utilities related with Reports
 * 
 * @author alberto.olivan
 * 
 */
public class ReportUtilities {

	// Define what to set in FileColum.columBlock if is repeatable
	public static final String fileColumBlockRepe = "n";

	public static final String dateTimePattern = "yyyy-MM-dd HH:mm:ss";
	public static final String datePattern = "yyyy-MM-dd";
	public static final String yearPattern = "yyyy";
	public static final String monthPattern = "mm/yyyy";

	public static final String dateXMLGregorianPattern = "EEE MMM dd zzz HH:mm:ss yyyy";

	public static final String emptySelect = "--SELECT--";

	public static final String reportVersion = "1.2";
	public static final String reportDMOVersion = "2.0";

	public static final String reportCatalogDMO_DM_Name = "DMO Declaracion Movimientos "
			+ reportDMOVersion;
	public static final String reportCatalogDMO_DN_Name = "DMO Declaracion Negativa "
			+ reportDMOVersion;
	public static final String reportCatalogDMO_DP_Name = "DMO Declaracion Positiva "
			+ reportDMOVersion;
	public static final String reportCatalogDMO_FR_Name = "DMO Fraccionamiento "
			+ reportDMOVersion;
	public static final String reportCatalogDMO_FRI_Name = "DMO Fraccionamiento Importacion "
			+ reportDMOVersion;
	public static final String reportCatalogDMO_OI_Name = "DMO Operaciones Import"
			+ reportDMOVersion;

	private static final Logger logger = LoggerFactory
			.getLogger(ReportUtilities.class);

	/**
	 * Function to clean objects from reportExecution which are disabled
	 * 
	 * @param reportExecution
	 * @return reportExecution cleaned of disable objects
	 */
	public ReportExecution reportExecutionNotDisabled(
			ReportExecution reportExecution) {

		// TODO:RT make a function to delete from object all reportData,
		// reportError and reportDataError which are disabled (isDelete=1)

		for (ReportData reportData : reportExecution.getReportDatas()) {
			if (reportData.getVersionAuditor().isDeleted()) {
				// ?
			}
		}

		return reportExecution;
	}

	/**
	 * Generate default reportData of this reportFields: CreationDateAndTime,
	 * Version and FillingType if does not exists
	 * 
	 * @param applicationContext
	 * @param reportExecution
	 * @param versionNum
	 */
	public static void generateDefaultReportDatas(
			ApplicationContext applicationContext,
			ReportExecution reportExecution, String versionNum,
			String reportingPeriodYear) {

		// all dataFields
		List<ReportData> reportDatas = new ArrayList<ReportData>(
				reportExecution.getReportDatas());

		logger.info("Creating default fields of catalog "
				+ reportExecution.getReportCatalog().getReportCatalogName());

		ReportFieldDAO reportFieldDAO = (ReportFieldDAO) applicationContext
				.getBean("reportFieldDAO");

		if (!reportExecution.getReportCatalog().getReportLevel()
				.contains("DEPARTMENT")) {

			// (2) <Version>
			if (searchData(reportDatas, "Version", "2", null) == null) {

				ReportField reportField = new ReportField();

				reportField = reportFieldDAO.findAllByProperty(
						findReportFieldDAO("Version", "2",
								reportExecution.getReportCatalog())).get(0);

				ReportData reportData = new ReportData(null, reportField,
						reportExecution, null, null, versionNum, null, null,
						new VersionAuditor("utilities"));

				// save new reportData
				ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
						.getBean("reportDataDAO");
				reportDataDAO.create(reportData);

				reportExecution.getReportDatas().add(reportData);

				logger.info("creating <Version> " + versionNum);
			}

			// (3) <CreationDateAndTime>
			if (searchData(reportDatas, "CreationDateAndTime", "3", null) == null) {

				ReportField reportField = new ReportField();

				reportField = reportFieldDAO.findAllByProperty(
						findReportFieldDAO("CreationDateAndTime", "3",
								reportExecution.getReportCatalog())).get(0);

				DateFormat dateFormat = new SimpleDateFormat(
						ReportUtilities.dateTimePattern);
				// Get the date today using Calendar object.
				Date today = Calendar.getInstance().getTime();
				// Using DateFormat format method we can create a string
				String creationDateTime = dateFormat.format(today);

				ReportData reportData = new ReportData(null, reportField,
						reportExecution, null, null, creationDateTime, null,
						null, new VersionAuditor("utilities"));

				// save new reportData
				ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
						.getBean("reportDataDAO");
				reportDataDAO.create(reportData);

				reportExecution.getReportDatas().add(reportData);

				logger.info("creating <CreationDateAndTime> "
						+ creationDateTime);
			}

			// (4) <FillingType>
			if (searchData(reportDatas, "FilingType", "4", null) == null) {

				ReportField reportField = new ReportField();

				reportField = reportFieldDAO.findAllByProperty(
						findReportFieldDAO("FilingType", "4",
								reportExecution.getReportCatalog())).get(0);

				String fillingType = "INIT";
				// if the reportExecution is new in this year/period -> INIT
				// if already exists other reportExecution -> AMND

				String year = reportExecution.getReportPeriodYear();
				String period = reportExecution.getReportPeriodType();

				ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
						.getBean("reportExecutionDAO");

				List<ReportExecution> reportExecutionList = reportExecutionDAO
						.findAllByProperty(findReportExecutionDAO(period, year,
								reportExecution.getReportCatalog(),
								reportExecution.getCompany()));

				if (reportExecutionList.size() > 1) {
					fillingType = "AMND";
				}

				logger.info("creating <FillingType> " + fillingType);

				ReportData reportData = new ReportData(null, reportField,
						reportExecution, null, null, fillingType, null, null,
						new VersionAuditor("utilities"));

				// save new reportData
				ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
						.getBean("reportDataDAO");
				reportDataDAO.create(reportData);

				reportExecution.getReportDatas().add(reportData);
			}

			// (9) <ReportingPeriodYear>
			if (searchData(reportDatas, "ReportingPeriodYear", "9", null) == null) {

				ReportField reportField = new ReportField();

				reportField = reportFieldDAO.findAllByProperty(
						findReportFieldDAO("ReportingPeriodYear", "9",
								reportExecution.getReportCatalog())).get(0);

				ReportData reportData = new ReportData(null, reportField,
						reportExecution, null, null, reportingPeriodYear, null,
						null, new VersionAuditor("utilities"));

				// save new reportData
				ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
						.getBean("reportDataDAO");
				reportDataDAO.create(reportData);

				reportExecution.getReportDatas().add(reportData);

				logger.info("creating <ReportingPeriodYear> "
						+ reportingPeriodYear);
			}

		}

		if (reportExecution.getReportCatalog().getReportLevel()
				.contains(ReportCatalogLevelEnum.COMPANY.getReportLevel())) {

			// (26) <Ranking> [1]
			if (searchData(reportDatas, "Ranking", "26", "1") == null) {

				ReportField reportField = new ReportField();

				reportField = reportFieldDAO.findAllByProperty(
						findReportFieldDAO("Ranking", "26",
								reportExecution.getReportCatalog())).get(0);

				ReportData reportData = new ReportData(null, reportField,
						reportExecution, null, null, "1", "1", null,
						new VersionAuditor("utilities"));

				// save new reportData
				ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
						.getBean("reportDataDAO");
				reportDataDAO.create(reportData);

				reportExecution.getReportDatas().add(reportData);

				logger.info("creating (26) <Ranking> [1]");
			}

			// (26) <Ranking> [2]
			if (searchData(reportDatas, "Ranking", "26", "2") == null) {

				ReportField reportField = new ReportField();

				reportField = reportFieldDAO.findAllByProperty(
						findReportFieldDAO("Ranking", "26",
								reportExecution.getReportCatalog())).get(0);

				ReportData reportData = new ReportData(null, reportField,
						reportExecution, null, null, "2", "2", null,
						new VersionAuditor("utilities"));

				// save new reportData
				ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
						.getBean("reportDataDAO");
				reportDataDAO.create(reportData);

				reportExecution.getReportDatas().add(reportData);

				logger.info("creating (26) <Ranking> [2]");
			}

			// (26) <Ranking> [3]
			if (searchData(reportDatas, "Ranking", "26", "3") == null) {

				ReportField reportField = new ReportField();

				reportField = reportFieldDAO.findAllByProperty(
						findReportFieldDAO("Ranking", "26",
								reportExecution.getReportCatalog())).get(0);

				ReportData reportData = new ReportData(null, reportField,
						reportExecution, null, null, "3", "3", null,
						new VersionAuditor("utilities"));

				// save new reportData
				ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
						.getBean("reportDataDAO");
				reportDataDAO.create(reportData);

				reportExecution.getReportDatas().add(reportData);

				logger.info("creating (26) <Ranking> [3]");
			}

			// (26) <Ranking> [4]
			if (searchData(reportDatas, "Ranking", "26", "4") == null) {

				ReportField reportField = new ReportField();

				reportField = reportFieldDAO.findAllByProperty(
						findReportFieldDAO("Ranking", "26",
								reportExecution.getReportCatalog())).get(0);

				ReportData reportData = new ReportData(null, reportField,
						reportExecution, null, null, "4", "4", null,
						new VersionAuditor("utilities"));

				// save new reportData
				ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
						.getBean("reportDataDAO");
				reportDataDAO.create(reportData);

				reportExecution.getReportDatas().add(reportData);

				logger.info("creating (26) <Ranking> [4]");
			}

			// (26) <Ranking> [5]
			if (searchData(reportDatas, "Ranking", "26", "5") == null) {

				ReportField reportField = new ReportField();

				reportField = reportFieldDAO.findAllByProperty(
						findReportFieldDAO("Ranking", "26",
								reportExecution.getReportCatalog())).get(0);

				ReportData reportData = new ReportData(null, reportField,
						reportExecution, null, null, "5", "5", null,
						new VersionAuditor("utilities"));

				// save new reportData
				ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
						.getBean("reportDataDAO");
				reportDataDAO.create(reportData);

				reportExecution.getReportDatas().add(reportData);

				logger.info("creating (26) <Ranking> [5]");
			}

			// (30) <Ranking> [1]
			if (searchData(reportDatas, "Ranking", "30", "1") == null) {

				ReportField reportField = new ReportField();

				reportField = reportFieldDAO.findAllByProperty(
						findReportFieldDAO("Ranking", "30",
								reportExecution.getReportCatalog())).get(0);

				ReportData reportData = new ReportData(null, reportField,
						reportExecution, null, null, "1", "1", null,
						new VersionAuditor("utilities"));

				// save new reportData
				ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
						.getBean("reportDataDAO");
				reportDataDAO.create(reportData);

				reportExecution.getReportDatas().add(reportData);

				logger.info("creating (30) <Ranking> [1]");
			}

			// (30) <Ranking> [2]
			if (searchData(reportDatas, "Ranking", "30", "2") == null) {

				ReportField reportField = new ReportField();

				reportField = reportFieldDAO.findAllByProperty(
						findReportFieldDAO("Ranking", "30",
								reportExecution.getReportCatalog())).get(0);

				ReportData reportData = new ReportData(null, reportField,
						reportExecution, null, null, "2", "2", null,
						new VersionAuditor("utilities"));

				// save new reportData
				ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
						.getBean("reportDataDAO");
				reportDataDAO.create(reportData);

				reportExecution.getReportDatas().add(reportData);

				logger.info("creating (30) <Ranking> [2]");
			}

			// (30) <Ranking> [3]
			if (searchData(reportDatas, "Ranking", "30", "3") == null) {

				ReportField reportField = new ReportField();

				reportField = reportFieldDAO.findAllByProperty(
						findReportFieldDAO("Ranking", "30",
								reportExecution.getReportCatalog())).get(0);

				ReportData reportData = new ReportData(null, reportField,
						reportExecution, null, null, "3", "3", null,
						new VersionAuditor("utilities"));

				// save new reportData
				ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
						.getBean("reportDataDAO");
				reportDataDAO.create(reportData);

				reportExecution.getReportDatas().add(reportData);

				logger.info("creating (30) <Ranking> [3]");
			}

			// (30) <Ranking> [4]
			if (searchData(reportDatas, "Ranking", "30", "4") == null) {

				ReportField reportField = new ReportField();

				reportField = reportFieldDAO.findAllByProperty(
						findReportFieldDAO("Ranking", "30",
								reportExecution.getReportCatalog())).get(0);

				ReportData reportData = new ReportData(null, reportField,
						reportExecution, null, null, "4", "4", null,
						new VersionAuditor("utilities"));

				// save new reportData
				ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
						.getBean("reportDataDAO");
				reportDataDAO.create(reportData);

				reportExecution.getReportDatas().add(reportData);

				logger.info("creating (30) <Ranking> [4]");
			}

			// (30) <Ranking> [5]
			if (searchData(reportDatas, "Ranking", "30", "5") == null) {

				ReportField reportField = new ReportField();

				reportField = reportFieldDAO.findAllByProperty(
						findReportFieldDAO("Ranking", "30",
								reportExecution.getReportCatalog())).get(0);

				ReportData reportData = new ReportData(null, reportField,
						reportExecution, null, null, "5", "5", null,
						new VersionAuditor("utilities"));

				// save new reportData
				ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
						.getBean("reportDataDAO");
				reportDataDAO.create(reportData);

				reportExecution.getReportDatas().add(reportData);

				logger.info("creating (30) <Ranking> [5]");
			}
		}

	}

	/**
	 * Function used to find a reportField like findByExample using
	 * reportCatalog, because hibernate does not consider it.
	 * 
	 * @param reportFieldName
	 * @param reportFieldNum
	 * @param reportCatalog
	 * @return List to used in reportExecutionDAO.findByProperty(list)
	 */
	public static List<Map<String, Object>> findReportFieldDAO(
			String reportFieldName, String reportFieldNum,
			ReportCatalog reportCatalog) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		HashMap<String, Object> mMap = new HashMap<String, Object>();
		mMap.put("propertyName", "reportFieldName");
		mMap.put("value", reportFieldName);
		list.add(mMap);
		HashMap<String, Object> mMap2 = new HashMap<String, Object>();
		mMap2.put("propertyName", "reportFieldNum");
		mMap2.put("value", new BigInteger(reportFieldNum));
		list.add(mMap2);
		HashMap<String, Object> mMap3 = new HashMap<String, Object>();
		mMap3.put("propertyName", "reportCatalog");
		mMap3.put("value", reportCatalog);
		list.add(mMap3);

		return list;
	}

	public static List<Map<String, Object>> findReportExecutionDAO(
			String reportPeriodType, String reportPeriodYear,
			ReportCatalog reportCatalog, Company company) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		HashMap<String, Object> mMap = new HashMap<String, Object>();
		mMap.put("propertyName", "reportPeriodType");
		mMap.put("value", reportPeriodType);
		list.add(mMap);
		HashMap<String, Object> mMap2 = new HashMap<String, Object>();
		mMap2.put("propertyName", "reportPeriodYear");
		mMap2.put("value", reportPeriodYear);
		list.add(mMap2);
		HashMap<String, Object> mMap3 = new HashMap<String, Object>();
		mMap3.put("propertyName", "reportCatalog");
		mMap3.put("value", reportCatalog);
		list.add(mMap3);
		HashMap<String, Object> mMap4 = new HashMap<String, Object>();
		mMap4.put("propertyName", "company");
		mMap4.put("value", company);
		list.add(mMap4);

		return list;
	}

	/**
	 * Function used to find a user like findByExample
	 * 
	 * @param userName
	 * @param userPassword
	 * @return List to used in userDAO.findByProperty(list)
	 */
	public static List<Map<String, Object>> findUserDAO(String userName,
			String userPass) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		HashMap<String, Object> mMap = new HashMap<String, Object>();
		mMap.put("propertyName", "userName");
		mMap.put("value", userName);
		list.add(mMap);
		HashMap<String, Object> mMap2 = new HashMap<String, Object>();
		mMap2.put("propertyName", "userPass");
		mMap2.put("value", userPass);
		list.add(mMap2);

		return list;
	}

	/**
	 * Function to search a ReportField from List<ReportField> with
	 * reportFieldName and reportFieldNumber
	 * 
	 * @param reportFields
	 * @param reportFieldName
	 * @param reportFieldNum
	 * @return ReportField
	 */
	public static ReportField searchField(List<ReportField> reportFields,
			String reportFieldName, String reportFieldNum) {

		ReportField result = null;

		for (ReportField reportField : reportFields) {
			if (reportField.getReportFieldName().equals(reportFieldName)
					&& reportField.getReportFieldNum().equals(
							new BigInteger(reportFieldNum))) {
				result = reportField;
				break;
			}
		}

		if (result == null) {
			logger.error("ERROR ReportUtilities: field does not exists "
					+ reportFieldName + " (" + reportFieldNum + ")");
		}

		return result;
	}

	/**
	 * Function search a reportDataText with a reportFieldName and reporFieldNum
	 * 
	 * @param reportDatas
	 * @param reportFieldName
	 * @param reportFieldNum
	 * @param reportDataBlock
	 * @return reportData.getReportDataText() / null
	 */
	public static String searchData(List<ReportData> reportDatas,
			String reportFieldName, String reportFieldNum,
			String reportDataBlock) {
		String result = null;

		if (reportDataBlock != null) {
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportDataBlock() != null) {
					if (reportData.getReportField().getReportFieldName()
							.equals(reportFieldName)
							&& reportData.getReportField().getReportFieldNum()
									.equals(new BigInteger(reportFieldNum))
							&& reportData.getReportDataBlock().equals(
									reportDataBlock)) {
						result = reportData.getReportDataText();
						break;
					}
				}
			}
		} else {
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals(reportFieldName)
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger(reportFieldNum))) {
					result = reportData.getReportDataText();
					break;
				}
			}
		}

		logger.debug("searchData(" + reportFieldName + " " + reportFieldNum
				+ " " + reportDataBlock + ") -> " + result);

		return result;
	}

	/**
	 * Function search a reportDataText with only reporFieldNum
	 * 
	 * @param reportDatas
	 * @param reportFieldNum
	 * @param reportDataBlock
	 * @return reportData.getReportDataText() / null
	 */
	public static String searchDataNum(List<ReportData> reportDatas,
			String reportFieldNum, String reportDataBlock) {
		String result = null;

		if (reportDataBlock != null) {
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportDataBlock() != null) {
					if (reportData.getReportField().getReportFieldNum()
							.equals(new BigInteger(reportFieldNum))
							&& reportData.getReportDataBlock().equals(
									reportDataBlock)) {
						result = reportData.getReportDataText();
						break;
					}
				}
			}
		} else {
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldNum()
						.equals(new BigInteger(reportFieldNum))) {
					result = reportData.getReportDataText();
					break;
				}
			}
		}

		logger.debug("searchDataNum( " + reportFieldNum + " " + reportDataBlock
				+ ") -> " + result);

		return result;
	}

	/**
	 * Similar to searchData, but returns this reportData.
	 * 
	 * @param reportDatas
	 * @param reportFieldName
	 * @param reportFieldNum
	 * @param reportDataBlock
	 * @return ReportData
	 */
	public static ReportData findData(List<ReportData> reportDatas,
			String reportFieldName, String reportFieldNum,
			String reportDataBlock) {

		ReportData result = null;

		if (reportDataBlock != null) {
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportDataBlock() != null) {
					if (reportData.getReportField().getReportFieldName()
							.equals(reportFieldName)
							&& reportData.getReportField().getReportFieldNum()
									.equals(new BigInteger(reportFieldNum))
							&& reportData.getReportDataBlock().equals(
									reportDataBlock)) {
						result = reportData;
						break;
					}
				}
			}
		} else {
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals(reportFieldName)
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger(reportFieldNum))) {
					result = reportData;
					break;
				}
			}
		}

		logger.debug("findData(" + reportFieldName + " " + reportFieldNum + " "
				+ reportDataBlock + ") -> " + result);

		return result;
	}

	/**
	 * Function search a reportDataText with a reportFieldName and reporFieldNum
	 * only when don not have reportDataError
	 * 
	 * @param reportDatas
	 * @param reportFieldName
	 * @param reportFieldNum
	 * @param reportDataBlock
	 * @return reportData.getReportDataText() / null
	 */
	public static String searchDataClean(List<ReportData> reportDatas,
			String reportFieldName, String reportFieldNum,
			String reportDataBlock) {
		String result = null;

		if (reportDataBlock != null) {
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportDataBlock() != null) {
					if (reportData.getReportField().getReportFieldName()
							.equals(reportFieldName)
							&& reportData.getReportField().getReportFieldNum()
									.equals(new BigInteger(reportFieldNum))
							&& reportData.getReportDataBlock().equals(
									reportDataBlock)) {
						boolean hasError = false;
						List<ReportDataError> reportDataErrorList = reportData
								.getReportDataErrors();
						for (ReportDataError reportDataError : reportDataErrorList) {
							if (reportDataError.getVersionAuditor().isDeleted() == false)
								hasError = true;
						}
						if (!hasError) {
							result = reportData.getReportDataText();
							break;
						}
					}
				}
			}
		} else {
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldName()
						.equals(reportFieldName)
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger(reportFieldNum))) {
					boolean hasError = false;
					List<ReportDataError> reportDataErrorList = reportData
							.getReportDataErrors();
					for (ReportDataError reportDataError : reportDataErrorList) {
						if (reportDataError.getVersionAuditor().isDeleted() == false)
							hasError = true;
					}
					if (!hasError) {
						result = reportData.getReportDataText();
						break;
					}
				}
			}
		}

		logger.debug("searchDataClean(" + reportFieldName + " "
				+ reportFieldNum + " " + reportDataBlock + ") -> " + result);

		return result;
	}

	/**
	 * Function return make a List<String> of reportData.reportDataBlock from
	 * field and number (similar searchData).
	 * 
	 * @param reportDatas
	 * @param reportFieldName
	 * @param reportFieldNum
	 * @return List<String> of reportData.Block from field
	 */
	public static List<String> searchBlockList(List<ReportData> reportDatas,
			String reportFieldName, String reportFieldNum) {
		List<String> result = new ArrayList<String>();

		for (ReportData reportData : reportDatas) {
			if (reportData.getReportDataBlock() != null) {
				if (reportData.getReportField().getReportFieldName()
						.equals(reportFieldName)
						&& reportData.getReportField().getReportFieldNum()
								.equals(new BigInteger(reportFieldNum))) {
					if (!result.contains(reportData.getReportDataBlock()))
						result.add(reportData.getReportDataBlock());
				}
			}
		}

		return result;
	}

	/**
	 * Function return make a List<String> of reportData.reportDataBlock from
	 * only number (similar searchData).
	 * 
	 * @param reportDatas
	 * @param reportFieldNum
	 * @return List<String> of reportData.Block from field
	 */
	public static List<String> searchBlockListNum(List<ReportData> reportDatas,
			String reportFieldNum) {
		List<String> result = new ArrayList<String>();

		for (ReportData reportData : reportDatas) {
			if (reportData.getReportDataBlock() != null) {
				if (reportData.getReportField().getReportFieldNum()
						.equals(new BigInteger(reportFieldNum))) {
					if (!result.contains(reportData.getReportDataBlock()))
						result.add(reportData.getReportDataBlock());
				}
			}
		}

		return result;
	}

	/**
	 * Function receive List<String> from searchBlockList() and return max
	 * number of reportData.Block
	 * 
	 * @param blockList
	 * @return
	 */
	public static String maxBlockFromList(List<String> blockList) {

		String maxBlock = "1";

		for (String block : blockList) {
			if (block.compareTo(maxBlock) > 0) {
				int temp = Integer.parseInt(block);
				temp++;
				maxBlock = Integer.toString(temp);
			}
		}

		return maxBlock;
	}

	/**
	 * Function return true if reportFieldRepe[0] = 1
	 * 
	 * @param reportField
	 * @return boolean
	 */
	public static boolean reportFieldIsMandatory(ReportField reportField) {
		boolean result = false;

		try {
			String[] parts = reportField.getReportFieldRepe().split(",");
			String part0 = parts[0]; // 0 Optional, 1 Mandatory
			String part1 = parts[1]; // number of repetitions 1,5,10,n...

			if (part0.equals("1"))
				result = true;

		} catch (Exception e) {
			logger.error("ERROR reportField.isMandatory() : " + e.getMessage());
		}

		return result;
	}

	/**
	 * Function return true if reportFieldRepe[1] is > than 1 or n
	 * 
	 * @param reportField
	 * @return boolean
	 */
	public static boolean reportFieldIsRepe(ReportField reportField) {
		boolean result = false;

		try {
			String[] parts = reportField.getReportFieldRepe().split(",");
			String part0 = parts[0]; // 0 Optional, 1 Mandatory
			String part1 = parts[1]; // number of repetitions 1,5,10,n...

			if (part1.equals("n")) {
				result = true;
			} else {
				if (Integer.parseInt(part1) > 1)
					result = true;
			}

		} catch (Exception e) {
			logger.error("ERROR reportField.isRepe() : " + e.getMessage());
		}

		return result;
	}

	/**
	 * Function to create in database a UserControl
	 * 
	 * @param applicationContext
	 * @param user
	 * @param message
	 * @param alert
	 */
	public static void createUserControl(ApplicationContext applicationContext,
			User user, String message, boolean alert, String type) {
		try {
			UserControlDAO userControlDAO = (UserControlDAO) applicationContext
					.getBean("userControlDAO");
			UserControl userControl = new UserControl(user, message, alert,
					type, new VersionAuditor("utility"));
			userControlDAO.create(userControl);

		} catch (Exception e) {
			logger.error("Error when creating UserControl: " + e.getMessage());
		}
	}

	/**
	 * Function reportFieldRepe[1] convert to int : 1,5,10,n->99
	 * 
	 * @param reportField
	 * @return boolean
	 */
	public static int reportFieldNumberRepe(ReportField reportField) {
		int result = 1;

		try {
			String[] parts = reportField.getReportFieldRepe().split(",");
			String part0 = parts[0]; // 0 Optional, 1 Mandatory
			String part1 = parts[1]; // number of repetitions 1,5,10,n...

			if (part1.equals("n"))
				result = 99;
			else
				result = Integer.parseInt(part1);

		} catch (Exception e) {
			logger.error("ERROR reportField.numberRepe() : " + e.getMessage());
		}

		return result;
	}

	/**
	 * Enter a reportField (?,n) repeatable and find how many blocks has to add
	 * to reportExecution, witch is max block of his section.
	 * 
	 * @param reportField
	 * @param reportExecution
	 * @return (int) blocks to add
	 */
	public static int reportFieldRepeSection(ReportField reportField,
			ReportExecution reportExecution) {
		int result = 1;

		String section = reportField.getReportFieldSection();

		if (section != null && !section.isEmpty()) {

			List<ReportField> reportFields = searchFieldSection(section,
					reportExecution);

			// find in repeatable fields, witch has max num [block].
			for (ReportField field : reportFields) {
				if (reportFieldNumberRepe(field) > 1) {
					String block = maxBlockFromList(searchBlockList(
							reportExecution.getReportDatas(),
							field.getReportFieldName(), field
									.getReportFieldNum().toString()));
					if (Integer.parseInt(block) > result)
						result = Integer.parseInt(block);
				}
			}

			result++;

		} else {
			result = 0;
		}
		logger.debug("section " + section + "  max: [" + result + "] -> "
				+ reportField.getReportFieldName() + " ("
				+ reportField.getReportFieldNum().toString() + ") "
				+ reportField.getReportFieldSection());

		return result;
	}

	/**
	 * Enter a String section and return a List<ReportField> witch have same
	 * section.
	 * 
	 * @param section
	 * @param reportExecution
	 * @return List<ReportField>
	 */
	public static List<ReportField> searchFieldSection(String section,
			ReportExecution reportExecution) {
		List<ReportField> result = new ArrayList<ReportField>();

		for (ReportField reportField : reportExecution.getReportCatalog()
				.getReportFields()) {
			if (reportField.getReportFieldSection() != null
					&& reportField.getReportFieldSection().equals(section))
				result.add(reportField);
		}

		return result;
	}

	/**
	 * Populate ReportExecution with input, creating a reportData with
	 * reportFieldName, reportFieldNum, reportDataBlock parameters.
	 * 
	 * @param input
	 * @param reportFieldName
	 * @param reportFieldNum
	 * @param reportDataBlock
	 * @param reportExecution
	 * @param user
	 * @param update
	 *            : true update, false only insert
	 */
	public static void saveData(String input, String reportFieldName,
			String reportFieldNum, String reportDataBlock,
			ReportExecution reportExecution, VersionAuditor user, boolean update) {

		ReportField reportField = ReportUtilities.searchField(reportExecution
				.getReportCatalog().getReportFields(), reportFieldName,
				reportFieldNum);

		if (reportField != null && input != null && !input.isEmpty()) {
			// update or insert
			if (update) {
				// insert
				if (ReportUtilities.searchData(
						reportExecution.getReportDatas(), reportFieldName,
						reportFieldNum, reportDataBlock) == null) {
					reportExecution.getReportDatas().add(
							new ReportData(null, reportField, reportExecution,
									null, null, input, reportDataBlock, null,
									user));
				} else {
					// update
					ReportData reportData = findData(
							reportExecution.getReportDatas(), reportFieldName,
							reportFieldNum, reportDataBlock);
					reportData.setReportDataText(input);
					reportData.setVersionAuditor(user);
					reportData.setVersion(reportData.getVersion() + 1);
				}

			} else {
				// only insert
				if (ReportUtilities.searchData(
						reportExecution.getReportDatas(), reportFieldName,
						reportFieldNum, reportDataBlock) == null)
					reportExecution.getReportDatas().add(
							new ReportData(null, reportField, reportExecution,
									null, null, input, reportDataBlock, null,
									user));
			}
		}
	}

	/**
	 * Function counts how many reportDatas with same fieldName/number exists
	 * and return List<Integer> with blocks inside.
	 * 
	 * @param reportDatas
	 * @param reportFieldName
	 * @param reportFieldNum
	 * @return List<Integer>
	 */
	public static List<Integer> listBlockDatas(List<ReportData> reportDatas,
			String reportFieldName, String reportFieldNum) {

		List<Integer> result = new ArrayList<Integer>();

		for (ReportData reportData : reportDatas) {
			if (reportData.getReportField().getReportFieldName()
					.equals(reportFieldName)
					&& reportData.getReportField().getReportFieldNum()
							.equals(new BigInteger(reportFieldNum)))
				result.add(Integer.parseInt(reportData.getReportDataBlock()));
		}

		return result;
	}

	/**
	 * Return true if String is a number, false otherwise
	 * 
	 * @param string
	 * @return boolean
	 */
	public static boolean isInteger(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		if (length == 0) {
			return false;
		}
		int i = 0;
		if (str.charAt(0) == '-') {
			if (length == 1) {
				return false;
			}
			i = 1;
		}
		for (; i < length; i++) {
			char c = str.charAt(i);
			if (c <= '/' || c >= ':') {
				return false;
			}
		}
		return true;
	}

	/**
	 * Function receive two repeatable fields and check comparing blocks, check
	 * if in second has content, the first exists in all cases. See semantic
	 * rules of AIFM field(14).
	 * 
	 * @param reportDatas
	 * @param reportFieldName1
	 * @param reportFieldNum1
	 * @param reportFieldName2
	 * @param reportFieldNum2
	 * @return null / problem String
	 */
	public static String dependencyRepeData(List<ReportData> reportDatas,
			String reportFieldName1, String reportFieldNum1,
			String reportFieldName2, String reportFieldNum2) {

		String result = null;

		// Example: field2(14) != null, field1(15) has to have content.
		// We find all field2(14) matching block number with field1(15) and
		// check if this rule works

		List<String> listField2 = searchBlockList(reportDatas,
				reportFieldName2, reportFieldNum2);

		for (String blockNum : listField2) {
			if (searchData(reportDatas, reportFieldName2, reportFieldNum2,
					blockNum) != null) {
				// field2 has the content expected, we find in field1 is
				// exists
				if (searchData(reportDatas, reportFieldName1, reportFieldNum1,
						blockNum) == null) {
					// if one fail, the rule is not satisfied
					result = " Check [" + blockNum + "], should not be empty.";
				}
			}
		}
		if (listField2.size() == 0
				&& searchBlockList(reportDatas, reportFieldName1,
						reportFieldNum1).size() > 0) {
			result = "Check field(" + reportFieldNum2 + ") is empty and ("
					+ reportFieldNum1 + ") should be empty.";
		}

		return result;
	}

	/**
	 * Function receive two repeatable fields and check comparing blocks, check
	 * if second field is empty, first field is forbidden. See semantic rules of
	 * AIF field(101).
	 * 
	 * @param reportDatas
	 * @param reportFieldName1
	 * @param reportFieldNum1
	 * @param reportFieldName2
	 * @param reportFieldNum2
	 * @return null / problem String
	 */
	public static String dependencyRepeDataForb(List<ReportData> reportDatas,
			String reportFieldName1, String reportFieldNum1,
			String reportFieldName2, String reportFieldNum2) {

		String result = null;

		// Example: field2(100) == null, field1(101) has to be empty.
		// if field2(100) != null, field1(101) is optional

		List<String> listField1 = searchBlockList(reportDatas,
				reportFieldName1, reportFieldNum1);
		for (String blockNum : listField1) {
			if (searchData(reportDatas, reportFieldName2, reportFieldNum2,
					blockNum) == null) {
				// if one fail, the rule is not satisfied
				result = " Check [" + blockNum + "], should be empty.";
			}
		}

		return result;
	}

	/**
	 * Function receive two repeatable fields and check comparing blocks, check
	 * if in second has reportDataText2, the first exists in all cases. See
	 * semantic rules of AIFM field(28). Similar to dependencyRepeData()
	 * 
	 * @param reportDatas
	 * @param reportFieldName1
	 * @param reportFieldNum1
	 * @param reportFieldName2
	 * @param reportFieldNum2
	 * @param reportDataText2
	 * @return null if ok, block number if fails
	 */
	public static String dependencyRepeDataExist(List<ReportData> reportDatas,
			String reportFieldName1, String reportFieldNum1,
			String reportFieldName2, String reportFieldNum2,
			String reportDataText2) {

		String result = null;

		// Example: field2(27) = MIC, field1(28) has to have content.
		// We find all field2(27) matching block number with field1(28) and
		// check if this rule works

		List<String> listField2 = searchBlockList(reportDatas,
				reportFieldName2, reportFieldNum2);
		// if (listField2.size() == 0) {
		// result = "Check field(" + reportFieldNum2 + ") is empty.";
		// } else {
		for (String blockNum : listField2) {
			if (searchData(reportDatas, reportFieldName2, reportFieldNum2,
					blockNum).equals(reportDataText2)) {
				// field2 has the content expected, we find in field1 is
				// exists
				if (searchData(reportDatas, reportFieldName1, reportFieldNum1,
						blockNum) == null) {
					// if one fail, the rule is not satisfied
					result = " Check [" + blockNum + "], should not be empty.";
				}
			}
		}
		// }

		return result;
	}

	/**
	 * Function receive two repeatable fields and check comparing blocks, check
	 * if in second has any of list reportDataText2, the first is forbidden in
	 * all cases. See semantic rules of AIFM field(123). Similar to
	 * dependencyRepeDataExist()
	 * 
	 * @param reportDatas
	 * @param reportFieldName1
	 * @param reportFieldNum1
	 * @param reportFieldName2
	 * @param reportFieldNum2
	 * @param reportDataText2
	 * @return null if ok, block number if fails
	 */
	public static String dependencyRepeDataDiffForbList(
			List<ReportData> reportDatas, String reportFieldName1,
			String reportFieldNum1, String reportFieldName2,
			String reportFieldNum2, String[] reportDataText2) {

		String result = null;

		// Example: field2(121) = DER_FEX_INVT or DER_FEX_HEDG" or DER_IRD_INTR,
		// field1(123) has to have content.

		List<String> reportDataTextList2 = new ArrayList<String>(
				Arrays.asList(reportDataText2));

		List<String> listField2 = searchBlockList(reportDatas,
				reportFieldName2, reportFieldNum2);
		for (String blockNum : listField2) {
			if (reportDataTextList2.contains(searchData(reportDatas,
					reportFieldName2, reportFieldNum2, blockNum))) {
				// field2 has the content expected, we find in field1 is
				// exists
				if (searchData(reportDatas, reportFieldName1, reportFieldNum1,
						blockNum) != null) {
					// if one fail, the rule is not satisfied
					result = " Check [" + blockNum + "], should be empty.";
				}
			}
		}

		return result;
	}

	/**
	 * Function receive field1 repeatable and unique field2, check if in second
	 * has reportDataText2, the first exists in all cases. See semantic rules of
	 * AIF field(42). Similar to dependencyRepeData()
	 * 
	 * @param reportDatas
	 * @param reportFieldName1
	 * @param reportFieldNum1
	 * @param reportFieldName2
	 * @param reportFieldNum2
	 * @param reportDataText2
	 * @return null if ok, block number if fails
	 */
	public static String dependencyFieldRepeDataExist(
			List<ReportData> reportDatas, String reportFieldName1,
			String reportFieldNum1, String reportFieldName2,
			String reportFieldNum2, String reportDataText2) {

		String result = null;
		boolean field2Exists = false;

		// Example: field2(41) = FEEDER, field1(42) has to have content.

		// search if field2 has content
		if (searchData(reportDatas, reportFieldName2, reportFieldNum2, null) != null
				&& searchData(reportDatas, reportFieldName2, reportFieldNum2,
						null).equals(reportDataText2)) {
			field2Exists = true;
		}

		List<String> listField1 = searchBlockList(reportDatas,
				reportFieldName1, reportFieldNum1);

		for (String blockNum : listField1) {
			if (field2Exists) {
				// field2 has the content expected, we find in field1 is
				// exists
				if (searchData(reportDatas, reportFieldName1, reportFieldNum1,
						blockNum) == null) {
					// if one fail, the rule is not satisfied
					result = " Check [" + blockNum + "], should not be empty.";
				}
			}
		}
		if (field2Exists && listField1.size() == 0) {
			result = "Check field(" + reportFieldNum1 + ") is empty.";
		}

		return result;
	}

	/**
	 * Function receive two repeatable fields and check comparing blocks, check
	 * if in second has different reportDataText2, the first is empty in all
	 * cases. See semantic rules of AIFM field(28). Similar to
	 * dependencyRepeData()
	 * 
	 * @param reportDatas
	 * @param reportFieldName1
	 * @param reportFieldNum1
	 * @param reportFieldName2
	 * @param reportFieldNum2
	 * @param reportDataText2
	 * @return null if ok, block number if fails
	 */
	public static String dependencyRepeDataDiffEmpty(
			List<ReportData> reportDatas, String reportFieldName1,
			String reportFieldNum1, String reportFieldName2,
			String reportFieldNum2, String reportDataText2) {

		String result = null;

		// Example: field2(27) <> MIC, field1(28) has to be empty.
		// We find all field2(27) matching block number with field1(28) and
		// check if this rule works

		List<String> listField2 = searchBlockList(reportDatas,
				reportFieldName2, reportFieldNum2);
		// if (listField2.size() == 0) {
		// result = "Check field(" + reportFieldNum2 + ") is empty.";
		// } else {
		for (String blockNum : listField2) {
			if (!searchData(reportDatas, reportFieldName2, reportFieldNum2,
					blockNum).equals(reportDataText2)) {
				// field2 has the content expected, we find in field1 is
				// exists
				if (searchData(reportDatas, reportFieldName1, reportFieldNum1,
						blockNum) != null) {
					// if one fail, the rule is not satisfied
					result = " Check [" + blockNum + "], should be empty.";
				}
			}
		}
		// }

		return result;
	}

	/**
	 * Function receive a repeatable field2 and unique field1, check if in
	 * second has different reportDataText2, the first is empty in all cases.
	 * See semantic rules of AIF field(42). Similar to
	 * dependencyRepeDataDiffEmpty()
	 * 
	 * @param reportDatas
	 * @param reportFieldName1
	 * @param reportFieldNum1
	 * @param reportFieldName2
	 * @param reportFieldNum2
	 * @param reportDataText2
	 * @return null if ok, block number if fails
	 */
	public static String dependencyFieldRepeDataDiffEmpty(
			List<ReportData> reportDatas, String reportFieldName1,
			String reportFieldNum1, String reportFieldName2,
			String reportFieldNum2, String reportDataText2) {

		String result = null;

		// Example: field2(41) <> FEEDER, field1(42) has to be empty.

		List<String> listField1 = searchBlockList(reportDatas,
				reportFieldName1, reportFieldNum1);
		// if (listField2.size() == 0) {
		// result = "Check field(" + reportFieldNum2 + ") is empty.";
		// } else {
		for (String blockNum : listField1) {
			if (!searchData(reportDatas, reportFieldName2, reportFieldNum2,
					null).equals(reportDataText2)) {
				// field2 has the content expected, we find in field1 is
				// exists
				if (searchData(reportDatas, reportFieldName1, reportFieldNum1,
						blockNum) != null) {
					// if one fail, the rule is not satisfied
					result = " Check [" + blockNum + "], should be empty.";
				}
			}
		}
		// }

		return result;
	}

	/**
	 * Function receive two repeatable fields and check comparing blocks, check
	 * if in second has different reportDataText2, the first exists in all
	 * cases. See semantic rules of AIF field(127). Similar to
	 * dependencyRepeData()
	 * 
	 * @param reportDatas
	 * @param reportFieldName1
	 * @param reportFieldNum1
	 * @param reportFieldName2
	 * @param reportFieldNum2
	 * @param reportDataText2
	 * @return null if ok, block number if fails
	 */
	public static String dependencyRepeDataDiffExists(
			List<ReportData> reportDatas, String reportFieldName1,
			String reportFieldNum1, String reportFieldName2,
			String reportFieldNum2, String reportDataText2) {

		String result = null;

		// Example: field2(27) <> MIC, field1(28) has to be empty.
		// We find all field2(27) matching block number with field1(28) and
		// check if this rule works

		List<String> listField2 = searchBlockList(reportDatas,
				reportFieldName2, reportFieldNum2);
		// if (listField2.size() == 0) {
		// result = "Check field(" + reportFieldNum2 + ") is empty.";
		// } else {
		for (String blockNum : listField2) {
			if (searchData(reportDatas, reportFieldName2, reportFieldNum2,
					blockNum).equals(reportDataText2)) {
				// field2 has the content expected, we find in field1 is
				// exists
				if (searchData(reportDatas, reportFieldName1, reportFieldNum1,
						blockNum) == null) {
					// if one fail, the rule is not satisfied
					result = " Check [" + blockNum + "], should be empty.";
				}
			}
		}
		// }

		return result;
	}

	/**
	 * Function receive two repeatable fields and check comparing blocks, check
	 * if in second has reportDataText2, the first is empty in all cases. See
	 * semantic rules of AIFM field(28). Similar to dependencyRepeData()
	 * 
	 * @param reportDatas
	 * @param reportFieldName1
	 * @param reportFieldNum1
	 * @param reportFieldName2
	 * @param reportFieldNum2
	 * @param reportDataText2
	 * @return null if ok, block number if fails
	 */
	public static String dependencyRepeDataEmpty(List<ReportData> reportDatas,
			String reportFieldName1, String reportFieldNum1,
			String reportFieldName2, String reportFieldNum2,
			String reportDataText2) {

		String result = null;

		// Example: field2(27) == NOT, field1(28) has to be empty.
		// We find all field2(27) matching block number with field1(28) and
		// check if this rule works

		List<String> listField2 = searchBlockList(reportDatas,
				reportFieldName2, reportFieldNum2);
		// if (listField2.size() == 0) {
		// result = "Check field(" + reportFieldNum2 + ") is empty.";
		// } else {
		for (String blockNum : listField2) {
			if (searchData(reportDatas, reportFieldName2, reportFieldNum2,
					blockNum).equals(reportDataText2)) {
				// field2 has the content expected, we find in field1 is
				// exists
				if (searchData(reportDatas, reportFieldName1, reportFieldNum1,
						blockNum) != null) {
					// if one fail, the rule is not satisfied
					result = " Check [" + blockNum + "], should be empty.";
				}
			}
		}
		// }

		return result;
	}

	/**
	 * Function receive a field1 repeatable and other field2 unique, check if in
	 * second has reportDataText2, the first is empty in all cases. See semantic
	 * rules of AIF field(34). Similar to dependencyRepeDataEmpty()
	 * 
	 * @param reportDatas
	 * @param reportFieldName1
	 * @param reportFieldNum1
	 * @param reportFieldName2
	 * @param reportFieldNum2
	 * @param reportDataText2
	 * @return null if ok, block number if fails
	 */
	public static String dependencyFieldRepeDataEmpty(
			List<ReportData> reportDatas, String reportFieldName1,
			String reportFieldNum1, String reportFieldName2,
			String reportFieldNum2, String reportDataText2) {

		String result = null;

		// Example: field2(33) == false, any field1(34) has to be empty.
		// only repeated is field1, field2 is only one

		List<String> listField1 = searchBlockList(reportDatas,
				reportFieldName1, reportFieldNum1);
		for (String blockNum : listField1) {
			if (searchData(reportDatas, reportFieldName2, reportFieldNum2, null)
					.equals(reportDataText2)) {
				// field2 has the content expected, we find in field1 is exists
				if (searchData(reportDatas, reportFieldName1, reportFieldNum1,
						blockNum) != null) {
					// if one fail, the rule is not satisfied
					result = " Check [" + blockNum + "], should be empty.";
				}
			}
		}

		return result;
	}

	/**
	 * Function receive two repeatable fields and check comparing blocks, check
	 * if in second not has reportDataText2, the first exists in all cases. See
	 * semantic rules of AIFM field(29). Similar to dependencyRepeDataExist()
	 * 
	 * @param reportDatas
	 * @param reportFieldName1
	 * @param reportFieldNum1
	 * @param reportFieldName2
	 * @param reportFieldNum2
	 * @param reportDataText2
	 * @return null if ok, block number if fails
	 */
	public static String dependencyRepeDataNot(List<ReportData> reportDatas,
			String reportFieldName1, String reportFieldNum1,
			String reportFieldName2, String reportFieldNum2,
			String reportDataText2) {

		String result = null;

		// Example: field2(27) <> NOT, field1(29) has to have content.
		// We find all field2(27) matching block number with field1(28) and
		// check if this rule works

		List<String> listField2 = searchBlockList(reportDatas,
				reportFieldName2, reportFieldNum2);
		for (String blockNum : listField2) {
			if (!searchData(reportDatas, reportFieldName2, reportFieldNum2,
					blockNum).equals(reportDataText2)) {
				// field2 has the content expected, we find in field1 is exists
				if (searchData(reportDatas, reportFieldName1, reportFieldNum1,
						blockNum) == null) {
					// if one fail, the rule is not satisfied
					result = " Check [" + blockNum + "], should not be empty.";
				}
			}
		}

		return result;
	}

	/**
	 * Function receive two repeatable fields and check comparing blocks, check
	 * if in second not start with reportDataText2, first field is forbidden.
	 * See semantic rules of AIF field(127).
	 * 
	 * @param reportDatas
	 * @param reportFieldName1
	 * @param reportFieldNum1
	 * @param reportFieldName2
	 * @param reportFieldNum2
	 * @param reportDataText2
	 * @return null if ok, block number if fails
	 */
	public static String dependencyRepeDataForbChar(
			List<ReportData> reportDatas, String reportFieldName1,
			String reportFieldNum1, String reportFieldName2,
			String reportFieldNum2, String reportDataText2) {

		String result = null;

		// Example: field2(125)[0] != 'D', field1(127)[0] is forbidden.

		List<String> listField2 = searchBlockList(reportDatas,
				reportFieldName2, reportFieldNum2);
		for (String blockNum : listField2) {
			if (!searchData(reportDatas, reportFieldName2, reportFieldNum2,
					blockNum).startsWith(reportDataText2)) {
				// field2 has the content expected, we find in field1 is exists
				if (searchData(reportDatas, reportFieldName1, reportFieldNum1,
						blockNum) != null) {
					// if one fail, the rule is not satisfied
					result = " Check [" + blockNum + "], should be empty.";
				}
			}
		}

		return result;
	}

	/**
	 * Function receive two repeatable fields and check comparing blocks, check
	 * if in second start with reportDataText2, first field is mandatory. See
	 * semantic rules of AIF field(61_O).
	 * 
	 * @param reportDatas
	 * @param reportFieldName1
	 * @param reportFieldNum1
	 * @param reportFieldName2
	 * @param reportFieldNum2
	 * @param reportDataText2
	 * @return null if ok, block number if fails
	 */
	public static String dependencyRepeDataExistChar(
			List<ReportData> reportDatas, String reportFieldName1,
			String reportFieldNum1, String reportFieldName2,
			String reportFieldNum2, String reportDataText2) {

		String result = null;

		// Example: field2(58)[0] == 'O', field1(61)[0] exists.

		List<String> listField2 = searchBlockList(reportDatas,
				reportFieldName2, reportFieldNum2);
		for (String blockNum : listField2) {
			if (searchData(reportDatas, reportFieldName2, reportFieldNum2,
					blockNum).startsWith(reportDataText2)) {
				// field2 has the content expected, we find in field1 is exists
				if (searchData(reportDatas, reportFieldName1, reportFieldNum1,
						blockNum) == null) {
					// if one fail, the rule is not satisfied
					result = " Check [" + blockNum + "], should not be empty.";
				}
			}
		}

		return result;
	}

	/**
	 * With a range of reportFieldNum (178,184) it compares if numeric sums is
	 * equals to total. See semantic rule AIF (178).
	 * 
	 * @param reportDatas
	 * @param reportFieldNum1
	 * @param reportFieldNum2
	 * @param total
	 * @return boolean
	 */
	public static boolean percentageRange(List<ReportData> reportDatas,
			String reportFieldNum1, String reportFieldNum2, String totalS) {

		boolean result = false;

		double total = 1;
		double subTotal = 0;
		String sub = "";
		try {
			total = Double.parseDouble(totalS);
			int min = Integer.parseInt(reportFieldNum1);
			int max = Integer.parseInt(reportFieldNum2);
			int i = min;
			List<Integer> range = new ArrayList<Integer>();
			while (i <= max) {
				range.add(i);
				i++;
			}
			for (Integer r : range) {
				sub = searchDataNum(reportDatas, r.toString(), null);
				if (sub != null && !sub.isEmpty())
					subTotal += Double.parseDouble(sub);
			}
		} catch (Exception e) {
			logger.error("Error in percentageRange(" + reportFieldNum1 + ","
					+ reportFieldNum2 + ")");
		}

		if (round(subTotal, 2) == total)
			result = true;

		return result;
	}

	/**
	 * Function to round a double with n decimals.
	 * 
	 * @param value
	 * @param places
	 * @return double
	 */
	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	/**
	 * With a range of reportFieldNum (178,184) if someone has content return
	 * true, false otherwise. See semantic rule AIF (178).
	 * 
	 * @param reportDatas
	 * @param reportFieldNum1
	 * @param reportFieldNum2
	 * @return boolean
	 */
	public static boolean hasContentRange(List<ReportData> reportDatas,
			String reportFieldNum1, String reportFieldNum2) {

		boolean result = false;

		try {
			int min = Integer.parseInt(reportFieldNum1);
			int max = Integer.parseInt(reportFieldNum2);
			int i = min;
			List<Integer> range = new ArrayList<Integer>();
			while (i <= max) {
				range.add(i);
				i++;
			}
			for (Integer r : range) {
				if (searchDataNum(reportDatas, r.toString(), null) != null) {
					result = true;
					break;
				}
			}
		} catch (Exception e) {
			logger.error("Error in hasContentRange(" + reportFieldNum1 + ","
					+ reportFieldNum2 + ")");
		}

		return result;
	}

	/**
	 * With a fieldName/fieldNum repeatable it compares if numeric sums is
	 * equals to total. See semantic rule AIF (209).
	 * 
	 * @param reportDatas
	 * @param reportFieldName1
	 * @param reportFieldNum1
	 * @param totalS
	 * @return boolean
	 */
	public static boolean percentageField(List<ReportData> reportDatas,
			String reportFieldName1, String reportFieldNum1, String totalS) {

		boolean result = false;

		double total = 1;
		double subTotal = 0;
		String sub = "";
		try {
			total = Double.parseDouble(totalS);
			List<String> listField1 = searchBlockList(reportDatas,
					reportFieldName1, reportFieldNum1);
			for (String block : listField1) {
				sub = searchData(reportDatas, reportFieldName1,
						reportFieldNum1, block);
				if (sub != null && !sub.isEmpty())
					subTotal += Double.parseDouble(sub);
			}
		} catch (Exception e) {
			logger.error("Error in percentageField(" + reportFieldName1 + ","
					+ reportFieldNum1 + ")");
		}

		if (round(subTotal, 2) == total)
			result = true;

		return result;
	}

	/**
	 * With a fieldNum repeatable it compares if numeric sums is equals to
	 * total. See semantic rule AIF (69).
	 * 
	 * @param reportDatas
	 * @param reportFieldNum1
	 * @param totalS
	 * @return boolean
	 */
	public static boolean percentageFieldNum(List<ReportData> reportDatas,
			String reportFieldNum1, String totalS) {
		boolean result = false;

		double total = 1;
		double subTotal = 0;
		String sub = "";
		try {
			total = Double.parseDouble(totalS);
			for (ReportData reportData : reportDatas) {
				if (reportData.getReportField().getReportFieldNum()
						.equals(new BigInteger(reportFieldNum1))) {
					sub = reportData.getReportDataText();
					if (sub != null && !sub.isEmpty())
						subTotal += Double.parseDouble(sub);
				}
			}
		} catch (Exception e) {
			logger.error("Error in percentageField(" + reportFieldNum1 + ")");
		}

		if (round(subTotal, 2) == total)
			result = true;

		return result;
	}

	/**
	 * Function enter a number of months (max 12) and return Date[2] with two
	 * dates between a period os months.
	 * 
	 * @param periodicity
	 * @return Date[2]
	 */
	public static Date[] getDatesFromPeriod(int periodicity, int monthLess) {

		// solo 12 meses maximo
		if (periodicity > 12)
			periodicity = 12;

		// solo 12 meses maximo
		if (monthLess > 12)
			monthLess = 12;
		if (monthLess < 0)
			monthLess = 0;

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		Date[] dates = new Date[2];
		int monthInt = calendar.get(Calendar.MONTH);
		int yearInt = calendar.get(Calendar.YEAR);

		if (monthLess > 0) {
			for (int i = 0; i < monthLess; i++) {
				monthInt--;
				// si el mes es 0, se resta tambien year
				if (monthInt < 0) {
					yearInt = yearInt - 1;
					monthInt = 11;
				}
			}
		}

		for (int i = 0; i < periodicity; i++) {
			monthInt--;
			// si el mes es 0, se resta tambien year
			if (monthInt < 0) {
				yearInt = yearInt - 1;
				monthInt = 11;
			}
		}

		Date initDate = getDateToDB(yearInt, monthInt, 1);
		monthInt = monthInt + periodicity;
		if (monthInt > 11) {
			monthInt = monthInt - 12;
			yearInt = yearInt + 1;
		}
		Date finishDate = getDateToDB(yearInt, monthInt, 1);

		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(finishDate);
		calendar2.add(Calendar.DAY_OF_YEAR, -1);

		finishDate = new Date(calendar2.getTimeInMillis());

		dates = new Date[] { initDate, finishDate };

		return dates;
	}

	/**
	 * Return Date from String year, month, day.
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return Date
	 */
	public static Date getDateToDB(String year, String month, String day) {

		return getDateToDB(Integer.parseInt(year), Integer.parseInt(month),
				Integer.parseInt(day));
	}

	/**
	 * Return Date from int year, month, day.
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return Date
	 */
	public static Date getDateToDB(int year, int month, int day) {

		GregorianCalendar calendar = new GregorianCalendar(year, month, day);

		return new Date(calendar.getTimeInMillis());
	}

	/**
	 * Return the parsed date as a String, with the format (both initial and
	 * end) as parameters.
	 * 
	 * 01/02/2015 = formatDate ("2015-02-01","yyyy-MM-dd","dd/MM/yyyy");
	 * 
	 * @param date
	 * @param initDateFormat
	 * @param endDateFormat
	 * @return String date
	 * @throws ParseException
	 */
	public static String formatDate(String date, String initDateFormat,
			String endDateFormat) throws ParseException {

		String parsedDate = null;

		if (date != null && !date.isEmpty() && initDateFormat != null
				&& !initDateFormat.isEmpty() && endDateFormat != null
				&& !endDateFormat.isEmpty()) {

			Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
			SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
			parsedDate = formatter.format(initDate);
		}

		return parsedDate;
	}

	/**
	 * Function to calculate DC from full account number of 18 chars.
	 * http://www.luciano.es/utiles/ccc.htm
	 * In: 1111 2222 33333333 
	 * Out: 1111 2222 DC 33333333 
	 * 
	 * @param fullAccount
	 * @return String 20 chars
	 */
	public static String calculateDCFromFullAccount(String fullAccount) {
		String result = "";
		// assuming is 18 chars: 1111 2222 ?? 3333333333

		if (fullAccount != null && fullAccount.length() == 18) {
			result = calculateDCFromAccount(fullAccount.substring(0, 4),
					fullAccount.substring(4, 8), fullAccount.substring(8, 18));
		} else {
			result = fullAccount;
			logger.warn("calculateDCFromFullAccount with account: "
					+ fullAccount);
		}

		return result;
	}

	/**
	 * Function to calculate DC from entity, office and account.
	 * http://www.luciano.es/utiles/ccc.htm
	 * In: 1111 2222 33333333 
	 * Out: 1111 2222 DC 33333333 
	 * 
	 * @param entidad
	 * @param sucursal
	 * @param nCuenta
	 * @return String DC
	 */
	public static String calculateDCFromAccount(String bank, String office,
			String account) {
		String result = "";
		int[] tableBank = { 4, 8, 5, 10 };
		int[] tableOffice = { 9, 7, 3, 6 };
		int[] tableAccount = { 1, 2, 4, 8, 5, 10, 9, 7, 3, 6 };

		if (bank != null && bank.length() == 4 && office != null
				&& office.length() == 4 && account != null
				&& account.length() == 10) {

			try {

				int dc1 = 0;
				for (int i = 0; i < bank.length(); i++) {
					dc1 += Character.getNumericValue(bank.charAt(i))
							* tableBank[i];
				}
				for (int i = 0; i < office.length(); i++) {
					dc1 += Character.getNumericValue(office.charAt(i))
							* tableOffice[i];
				}
				dc1 = dc1 % 11;
				dc1 = 11 - dc1;
				if (dc1 == 10)
					dc1 = 1;

				int dc2 = 0;
				for (int i = 0; i < account.length(); i++) {
					dc2 += Character.getNumericValue(account.charAt(i))
							* tableAccount[i];
				}
				dc2 = dc2 % 11;
				dc2 = 11 - dc2;
				if (dc2 == 10)
					dc2 = 1;

				result = bank + office + Integer.toString(dc1)
						+ Integer.toString(dc2) + account;

			} catch (Exception e) {
				e.printStackTrace();

				logger.warn("Could not calculate DC with params: bank " + bank
						+ " office: " + office + " account: " + account);
				result = bank + office + account;
			}
		} else {
			result = bank + office + account;
			logger.warn("calculateDCFromAccount with params: bank " + bank
					+ " office: " + office + " account: " + account);
		}

		return result;
	}

}
