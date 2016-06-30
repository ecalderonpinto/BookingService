package com.reportingtool.validator;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.entities.dao.reportingtool.ReportExecutionDAO;
import com.entities.dictionary.ReportCatalogLevelEnum;
import com.entities.dictionary.ReportExecutionStatusEnum;
import com.entities.entity.reportingtool.ReportCatalog;
import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.entity.reportingtool.ReportField;

/**
 * Class to check reportExecution.reportStatus if is ready to be generated
 * 
 * @author alberto.olivan
 * 
 */
public class Status {

	private ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory.getLogger(Status.class);

	/**
	 * Constructor if Status with an applicationContext
	 * 
	 * @param applicationContext
	 */
	public Status(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * Main function to check Status of a reportExecution
	 * 
	 * @param reportExecution
	 * @return reportExecution
	 */
	public ReportExecution checkStatus(ReportExecution reportExecution) {

		logger.info("Status function");

		// if report sent, is not analyzed
		if (!reportExecution.getReportStatus().equals(
				ReportExecutionStatusEnum.SENT.getReportExecutionStatus())) {

			if (reportExecution.getReportCatalog().getReportLevel()
					.contains(ReportCatalogLevelEnum.FUND.getReportLevel())) {
				// check AIF report
				reportExecution = checkAIFMDStatus(reportExecution);
			}
			if (reportExecution.getReportCatalog().getReportLevel()
					.contains(ReportCatalogLevelEnum.COMPANY.getReportLevel())) {
				// check AIFMD report
				reportExecution = checkAIFMDStatus(reportExecution);
			}
			if (reportExecution
					.getReportCatalog()
					.getReportLevel()
					.contains(
							ReportCatalogLevelEnum.DEPARTMENT.getReportLevel())) {
				// check DMO report
				reportExecution = checkDMOStatus(reportExecution);
			}

		}

		return reportExecution;
	}

	/**
	 * Function change status to SENT of reportingExecution.
	 * 
	 * @param reportExecution
	 * @return reportExecution
	 */
	public ReportExecution statusSend(ReportExecution reportExecution) {

		logger.info("Status send");

		// change to SENT
		reportExecution.setReportStatus(ReportExecutionStatusEnum.SENT
				.getReportExecutionStatus());

		// save
		ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
				.getBean("reportExecutionDAO");
		reportExecutionDAO.edit(reportExecution);

		return reportExecution;
	}

	/**
	 * Function to check status of AIFM report
	 * 
	 * @param reportExecution
	 * @return reportExecution
	 */
	private ReportExecution checkAIFMDStatus(ReportExecution reportExecution) {

		// reportCatalog
		ReportCatalog reportCatalog = reportExecution.getReportCatalog();

		// reportFields
		List<ReportField> reportFields = new ArrayList<ReportField>(
				reportCatalog.getReportFields());

		// dataFields
		List<ReportData> reportDatas = new ArrayList<ReportData>(
				reportExecution.getReportDatas());

		boolean finalStatus = true;
		boolean hasData = false;
		int countFields = 0;
		int countDatas = 0;

		// for each field, semantic has to be checked (mandatory)
		for (ReportField reportField : reportFields) {

			boolean oblg = fieldMandatory(reportField, reportExecution);
			if (oblg) {
				// fieldValid says field is mandatory, it has to appear in
				// dataFields

				countFields++;
				hasData = false;
				// search in dataField if this mandatory field exists
				for (ReportData reportData : reportDatas) {
					if (reportData.getReportField() == reportField) {
						hasData = true;
					}
				}
				if (hasData) {
					// ok: field mandatory is in dataField
					logger.debug("Status ok field "
							+ reportField.getReportFieldName());

					countDatas++;
				} else {
					// not ok
					logger.debug("Status not ok "
							+ reportField.getReportFieldName());

					finalStatus = false;
					// set status to in creation, it has at least one field
				}
			}

		}
		// all fields has to be correct to change status to prepared
		if (finalStatus) {
			reportExecution.setReportStatus(ReportExecutionStatusEnum.READY
					.getReportExecutionStatus());
		} else {
			reportExecution.setReportStatus(ReportExecutionStatusEnum.CREATION
					.getReportExecutionStatus());
		}
		// literal: reportDatas / reportFields mandatory
		reportExecution.setReportStatusCount(countDatas + " / " + countFields);

		logger.info("Save report " + reportExecution.getId() + " with status "
				+ finalStatus);

		ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
				.getBean("reportExecutionDAO");
		reportExecutionDAO.edit(reportExecution);

		return reportExecution;
	}

	/**
	 * Function to check status of DMO report
	 * 
	 * @param reportExecution
	 * @return ReportExecution
	 */
	private ReportExecution checkDMOStatus(ReportExecution reportExecution) {
		
		boolean finalStatus = !reportExecution.isHasErrors();
		
		// only finalStatus is false when has errors
		if (finalStatus) {
			reportExecution.setReportStatus(ReportExecutionStatusEnum.READY
					.getReportExecutionStatus());
		} else {
			reportExecution.setReportStatus(ReportExecutionStatusEnum.CREATION
					.getReportExecutionStatus());
		}
		// literal: reportDatas.size fields
		reportExecution.setReportStatusCount(reportExecution.getReportDatas().size() + " fields");

		logger.info("Save report " + reportExecution.getId() + " with status "
				+ finalStatus);

		ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
				.getBean("reportExecutionDAO");
		reportExecutionDAO.edit(reportExecution);

		return reportExecution;
	}

	/**
	 * Function to check if reportField is mandatory or not in this
	 * reportExecution
	 * 
	 * @param reportField
	 * @param reportExecution
	 * @return true if reportField is mandatory in this reportExecution
	 */
	public static boolean fieldMandatory(ReportField reportField,
			ReportExecution reportExecution) {
		boolean result = false;

		// fields num 0 are sections, not mandatory
		if (reportField.getReportFieldNum().intValue() > 0) {

			String[] parts = reportField.getReportFieldRepe().split(",");
			// first part of a reportFieldRepe [1,1]
			String part1 = parts[0];
			int rep = Integer.parseInt(part1);

			if (rep > 0)
				result = true;

		}

		return result;
	}
}
