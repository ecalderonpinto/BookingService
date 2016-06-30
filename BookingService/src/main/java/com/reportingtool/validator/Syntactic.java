package com.reportingtool.validator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.entities.dao.reportingtool.ReportFieldListDAO;
import com.entities.dictionary.ErrorTypeEnum;
import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.entity.reportingtool.ReportField;
import com.entities.entity.reportingtool.ReportFieldList;
import com.reportingtool.utilities.ReportingErrorManager;

/**
 * Class to check syntatic rules of reportExecution
 * 
 * @author alberto.olivan
 *
 */
public class Syntactic {

	private ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(Syntactic.class);

	/**
	 * Constructor of Syntactic with an applicationContext
	 * 
	 * @param applicationContext
	 */
	public Syntactic(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * Main function to check syntactic rules of a reportExecution, create
	 * reportDataError
	 * 
	 * @param reportExecution
	 */
	public void validReportExecution(ReportExecution reportExecution) {

		logger.info("SYNTAXIS: starting process "
				+ reportExecution.getReportExecutionName());

		for (ReportData reportData : reportExecution.getReportDatas()) {
			this.validInValueList(reportData);
			this.validRegex(reportData);
		}
	}

	/**
	 * Function to check if reportData.getReportDataText exists in
	 * reportFieldList of his reportField
	 * 
	 * @param reportData
	 */
	private void validInValueList(ReportData reportData) {

		ReportField reportField = reportData.getReportField();

		ReportFieldListDAO reportFieldListDAO = (ReportFieldListDAO) applicationContext
				.getBean("reportFieldListDAO");

		ReportFieldList reportFieldListExample = new ReportFieldList();
		reportFieldListExample.setReportFieldListType(reportField
				.getReportFieldMask());

		List<ReportFieldList> reportFieldLists = reportFieldListDAO
				.findByExample(reportFieldListExample);

		Boolean hasFieldValue = true;
		for (ReportFieldList reportFieldList : reportFieldLists) {
			hasFieldValue = false;
			logger.debug("SYNTAXIS: value "
					+ reportFieldList.getReportFieldListValue() + " -> "
					+ reportData.getReportDataText());
			if (reportFieldList.getReportFieldListValue().equals(
					reportData.getReportDataText())) {
				hasFieldValue = true;
				break;
			}

		}

		if (hasFieldValue) {
			// ok

			logger.debug("SYNTAXIS: ("
					+ reportField.getReportFieldNum()
					+ ") hasFieldValue ok, check if error exists "
					+ reportData.getReportDataText() + " -> "
					+ reportField.getReportFieldFormat());

			// find and disable if there are a REGEX error
			ReportingErrorManager.disableReportDataError(applicationContext,
					ErrorTypeEnum.SYNTAXIS.getErrorType(), reportData, "VALUE");
		} else {
			// not valid

			// create a message of error
			ReportingErrorManager.createReportDataError(
					applicationContext,
					ErrorTypeEnum.SYNTAXIS.getErrorType(),
					reportData,
					"VALUE",
					"Value not present in list: "
							+ reportData.getReportDataText());
		}
	}

	/**
	 * Function to check regular expression of reportField.getReportFieldFormat
	 * is correct in reportData
	 * 
	 * @param reportData
	 */
	private void validRegex(ReportData reportData) {

		ReportField reportField = reportData.getReportField();

		if (reportData.getReportDataText() != null
				&& !reportData.getReportDataText().isEmpty()) {
			// ok, is not empty

			// Check Regex patters
			// http://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
			// http://www.ocpsoft.org/tutorials/regular-expressions/java-visual-regex-tester/

			logger.debug("SYNTAXIS regex: ("
					+ reportField.getReportFieldNum() + ")"
					+ reportData.getReportDataText() + " -> "
					+ reportField.getReportFieldFormat());

			Pattern pattern = Pattern.compile(reportField
					.getReportFieldFormat());
			Matcher matcher = pattern.matcher(reportData.getReportDataText());
			boolean matchVersion = matcher.matches();
			if (matchVersion) {
				// ok Regex

				logger.debug("SYNTAXIS: ("
						+ reportField.getReportFieldNum()
						+ ") regex ok, check if error exists "
						+ reportData.getReportDataText() + " -> "
						+ reportField.getReportFieldFormat() + " ");

				// find and disable if there are a REGEX error
				ReportingErrorManager.disableReportDataError(
						applicationContext,
						ErrorTypeEnum.SYNTAXIS.getErrorType(), reportData,
						"REGEX");

			} else {
				// not valid, bad Regex

				ReportingErrorManager.createReportDataError(applicationContext,
						ErrorTypeEnum.SYNTAXIS.getErrorType(), reportData,
						"REGEX",
						"Syntactic fail: "
								+ reportData.getReportDataText()
								+ " <> "
								+ reportData.getReportField()
										.getReportFieldFormat());
			}
		} else {
			// not valid, is empty
			ReportingErrorManager.createReportDataError(applicationContext,
					ErrorTypeEnum.SYNTAXIS.getErrorType(), reportData, "EMPTY",
					"EMPTY FIELD");
		}

	}
}
