package com.reportingtool.controllers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.entities.dao.reportingtool.ReportDataDAO;
import com.entities.dao.reportingtool.ReportDataErrorDAO;
import com.entities.dao.reportingtool.ReportExecutionDAO;
import com.entities.dao.reportingtool.ReportFieldListDAO;
import com.entities.dictionary.UserControlTypeEnum;
import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportDataError;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.entity.reportingtool.ReportField;
import com.entities.entity.reportingtool.ReportFieldList;
import com.entities.entity.usermanager.User;
import com.entities.utilities.hibernate.VersionAuditor;
import com.reportingtool.controllers.forms.AlertToView;
import com.reportingtool.controllers.forms.ReportSectionForm;
import com.reportingtool.service.UserService;
import com.reportingtool.utilities.ReportUtilities;
import com.reportingtool.utilities.ReportingErrorManager;
import com.reportingtool.validator.Semantic;
import com.reportingtool.validator.Status;
import com.reportingtool.validator.Syntactic;

@Controller
@RequestMapping(value = "/reportExecution.do")
@SessionAttributes("reportExecution")
public class ReportExecutionController {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	UserService userDetailsService;

	private static final Logger logger = LoggerFactory
			.getLogger(ReportExecutionController.class);

	/**
	 * Controller to show reportExecution
	 * 
	 * @param id
	 * @param model
	 * @return reportexecution -> tiles.xml
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String ReportControllerPre(@RequestParam("id") String id, Model model) {

		logger.info("ReportExecution Controller id=" + id);

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
					.getBean("reportExecutionDAO");

			// load reportExecution from id to have all this child entities
			ReportExecution reportExecution = reportExecutionDAO.findById(Long
					.parseLong(id));

			// set reportExecution.hasErrors and reportData.hasErrors true/false
			// to show only active
			ReportingErrorManager
					.checkReportExecutionHasErrors(reportExecution);

			// logger.info("GET just enter");
			// showReportDatas(reportExecution);

			// adapt DATE to FECHA_GEN only DMO
//			if (reportExecution.getReportCatalog().getReportLevel()
//					.equals("DEPARTMENT"))
				//reportExecution = adaptDATEtoFECHA_GEN(reportExecution, "GET");

			// list of fields with dropdowns
			List<String> fieldList = getReportFieldListTypeString();
			model.addAttribute("fieldlist", fieldList);

			// Map of data to populate dropdowns
			Map<String, String> fieldListMap = getReportFieldListDropdown();
			model.addAttribute("fieldlistmap", fieldListMap);

			// add all reportDatas empty when are not populated yet
			reportExecution = addReportDatas(reportExecution);

			// logger.info("GET after addReportDatas");
			// showReportDatas(reportExecution);

			// get all visual sections of the report
			List<String> sections = getSections(reportExecution);

			// showReportDatas(reportExecution);

			// order reportDatas to display correctly
			reportExecution = reportExecutionOrder(reportExecution);

			// logger.info("GET after reportExecutionOrder");
			// showReportDatas(reportExecution);
			
			// ///////////////////////////////////////////////////////////////////
			// TEST SEMANTIC RULEZ
			//List<ReportData> reportDatas = reportExecution.getReportDatas();
			// /////////////////////////////////////////////////////////////////

			model.addAttribute("reportExecution", reportExecution);
			model.addAttribute("sections", sections);

			// // new about sections
			// List<ReportSectionForm> reportSectionForms =
			// getReportSections(reportExecution);
			// for(ReportSectionForm reportSectionForm : reportSectionForms) {
			// logger.debug("section : " + reportSectionForm.getSectionName()
			// + " - " + reportSectionForm.isHasBlock());
			// }
			// model.addAttribute("reportsections", reportSectionForms);

			return "reportexecution";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

	/**
	 * Controller to save reportDatas from reportExecution UI
	 * 
	 * @param reportExecution
	 * @param result
	 * @param model
	 * @param status
	 * @param session
	 * @return reportexecution -> tiles.xml
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			@ModelAttribute("reportExecution") ReportExecution reportExecution,
			BindingResult result, Model model, SessionStatus status,
			HttpSession session) {

		logger.info("Submit - ReportExecution id=" + reportExecution.getId());

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			// logger.info("POST just enter");
			// showReportDatas(reportExecution);

			// delete reportDatas with id and null in database
			reportExecution = deleteReportDatas(reportExecution);

			// logger.info("POST after deleteReportDatas");
			// showReportDatas(reportExecution);

			// clean all empty reportData to avoid been saved
			reportExecution = cleanReportDatas(reportExecution);

			// logger.info("POST after cleanReportDatas");
			// showReportDatas(reportExecution);

			ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
					.getBean("reportExecutionDAO");

			// adapt DATE to FECHA_GEN only DMO
//			if (reportExecution.getReportCatalog().getReportLevel()
//					.equals("DEPARTMENT"))
//				reportExecution = adaptDATEtoFECHA_GEN(reportExecution, "POST");

			// save changes of reportExecution
			reportExecutionDAO.edit(reportExecution);

			// logger.info("POST after edit");
			// showReportDatas(reportExecution);

			// load reportExecution from id to have all this child entities
			reportExecution = reportExecutionDAO.findById(reportExecution
					.getId());

			// Syntactic analysis
			Syntactic syntactic = new Syntactic(applicationContext);
			syntactic.validReportExecution(reportExecution);

			// Semantic analysis
			Semantic semantic = new Semantic(applicationContext);
			semantic.checkSemantic(reportExecution);

			// Status of report
			Status reportingStatus = new Status(applicationContext);
			reportExecution = reportingStatus.checkStatus(reportExecution);

			// save changes
			reportExecutionDAO.edit(reportExecution);

			// logger.info("POST after semantic");
			// showReportDatas(reportExecution);

			// AUDIT
			ReportUtilities.createUserControl(
					applicationContext,
					user,
					UserControlTypeEnum.EDITREPORT.getDesc()
							+ reportExecution.getId(), false,
					UserControlTypeEnum.EDITREPORT.getType());

			// return "reportexecution";
			// with URL uses GET
			return "redirect:/reportExecution.do?id=" + reportExecution.getId();

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

	/**
	 * Show reportData content in console
	 * 
	 * @param reportExecution
	 */
	public void showReportDatas(ReportExecution reportExecution) {
		int count = 0;

		BigInteger b57 = new BigInteger("171");
		BigInteger b64 = new BigInteger("178");

		logger.info(" *** showReportDatas *** ");
		for (ReportData reportData : reportExecution.getReportDatas()) {
			if (reportData.getReportField().getReportFieldNum().compareTo(b57) > 0
					&& reportData.getReportField().getReportFieldNum()
							.compareTo(b64) < 0)

				logger.info(count + " id " + reportData.getId() + " : "
						+ reportData.getReportField().getReportFieldName()
						+ "(" + reportData.getReportField().getReportFieldNum()
						+ ") -> '" + reportData.getReportDataText() + "' ["
						+ reportData.getReportDataBlock() + "]-"
						+ reportData.getReportDataOrder());
			count++;
		}
	}

	/**
	 * Function delete empty reportDatas with id
	 * 
	 * @param reportExecution
	 * @return reportExecution
	 */
	private ReportExecution deleteReportDatas(ReportExecution reportExecution) {

		ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
				.getBean("reportDataDAO");

		ReportDataErrorDAO reportDataErrorDAO = (ReportDataErrorDAO) applicationContext
				.getBean("reportDataErrorDAO");

		for (ReportData reportData : reportExecution.getReportDatas()) {
			if ((reportData.getReportDataText().equals(
					ReportUtilities.emptySelect)
					|| reportData.getReportDataText() == null || reportData
					.getReportDataText().isEmpty()) && reportData.getId() > 0) {
				logger.info("deleting "
						+ reportData.getReportField().getReportFieldName());

				List<ReportDataError> reportDataErrorList = reportData
						.getReportDataErrors();

				for (ReportDataError reportDataError : reportDataErrorList) {
					reportDataErrorDAO.delete(reportDataError);
				}

				reportDataDAO.delete(reportData);
			}
		}

		return reportExecution;
	}

	/**
	 * Function that build a List<String> with sections to display
	 * 
	 * @param reportExecution
	 * @return List<String> of sections to display
	 */
	private List<String> getSections(ReportExecution reportExecution) {

		List<String> result = new ArrayList<String>();

		for (ReportData reportData : reportExecution.getReportDatas()) {
			if (reportData.getReportField().getReportFieldSection() != null
					&& !result.contains(reportData.getReportField()
							.getReportFieldSection()))
				result.add(reportData.getReportField().getReportFieldSection());
		}

		return result;
	}

	/**
	 * Function to sort a reportExecution.reportDatas in order to display
	 * 
	 * @param reportExecution
	 * @return reportExecution sorted
	 */
	private ReportExecution reportExecutionOrder(ReportExecution reportExecution) {

		List<ReportData> reportDatas = new ArrayList<ReportData>(
				reportExecution.getReportDatas());

		List<ReportData> reportDataResult = new ArrayList<ReportData>();

		// temp List with reportDataOrder
		List<String> orderList = new ArrayList<String>();

		String reportDataOrder = "";

		// first for to create reportDataOrder
		for (ReportData reportData : reportDatas) {
			if (reportData.getReportField().getReportFieldSection() != null) {
				if (reportData.getReportDataBlock() != null) {
					String[] order = reportData.getReportField()
							.getReportFieldOrder().split("\\.");
					logger.debug("split: " + Arrays.toString(order));

					// block 01,02,03, ...10
					String blockOrder = reportData.getReportDataBlock();
					if (blockOrder.length() == 1)
						blockOrder = "0" + blockOrder;
					// reportDataOrder is section.block.fieldNumber
					reportDataOrder = order[0] + "." + blockOrder + "."
							+ order[1];
					reportData.setReportDataOrder(reportDataOrder);
				} else {
					// reportDataOrder is section.fieldNumber
					reportData.setReportDataOrder(reportData.getReportField()
							.getReportFieldOrder());
				}
				orderList.add(reportData.getReportDataOrder());
			}
			logger.debug("reportData1 " + reportData.getReportDataOrder() + " "
					+ reportData.getReportField().getReportFieldName());
		}

		logger.debug("orderList " + orderList.toString());

		Collections.sort(orderList);

		// second for to create reportDataResult with orderList
		for (String order : orderList) {
			logger.debug("order: " + order);
			for (ReportData reportData : reportDatas) {
				logger.debug("order: " + order + " -> "
						+ reportData.getReportDataOrder());
				if (reportData.getReportDataOrder().equals(order))
					reportDataResult.add(reportData);
			}
		}

		reportExecution.setReportDatas(reportDataResult);

		return reportExecution;
	}

	/**
	 * Function that add all reportData possible, UI can populate them
	 * 
	 * @param reportExecution
	 * @return reportExecution with all reportDatas to fill
	 */
	private ReportExecution addReportDatas(ReportExecution reportExecution) {

		List<ReportData> reportDatas = new ArrayList<ReportData>();
		List<ReportField> reportFields = new ArrayList<ReportField>(
				reportExecution.getReportCatalog().getReportFields());

		boolean flagField = false;
		boolean repeAdded = false;
		for (ReportField reportField : reportFields) {
			// is field without content
			if (reportField.getReportFieldSection() == null) {
				continue;
			}
			logger.debug("reportField -> " + reportField.getReportFieldName());
			for (ReportData reportData : reportExecution.getReportDatas()) {
				if (reportData.getReportField().equals(reportField)) {
					// reportData already exists
					logger.debug("yes reportField, adding reportData of "
							+ reportField.getReportFieldName() + "-"
							+ reportField.getReportFieldNum().toString()
							+ " content: " + reportData.getReportDataText());
					reportDatas.add(reportData);
					flagField = true;
				}
			}
			if (flagField == false) {
				// add reportData

				ReportData reportDataTemp = new ReportData(null, reportField,
						reportExecution, null, null, "", null, null,
						new VersionAuditor("generated"));

				// if is a field repeated and doesn't exist, we set block = "1"
				if (ReportUtilities.reportFieldIsRepe(reportField)) {
					reportDataTemp.setReportDataBlock("1");
					repeAdded = true;
				}

				logger.debug("no reportField, adding reportData of "
						+ reportField.getReportFieldName() + "-"
						+ reportField.getReportFieldNum().toString());
				reportDatas.add(reportDataTemp);

			}

			// if is a field repeated and exists, we find last number block
			// we add next number block until is full (5,10) or one more (n)
			if (ReportUtilities.reportFieldIsRepe(reportField)) {
				int count = ReportUtilities.reportFieldNumberRepe(reportField);

				logger.debug("exists field and is repeated "
						+ reportField.getReportFieldName() + " "
						+ reportField.getReportFieldRepe());

				// (0,n) need always same reportDatas as their max block os
				// section
				if (count == 99) {
					// find max block of his section to add this number of
					// blocks, no less
					count = ReportUtilities.reportFieldRepeSection(reportField,
							reportExecution);
				}

				String block = "1";

				for (int i = 1; i <= count; i++) {
					block = Integer.toString(i);
					// if this field + block doesn't exist, we add it
					if (ReportUtilities.searchData(reportDatas, reportField
							.getReportFieldName(), reportField
							.getReportFieldNum().toString(), block) == null) {

						ReportData reportDataTemp = new ReportData(null,
								reportField, reportExecution, null, null, "",
								null, null, new VersionAuditor("generated"));

						reportDataTemp.setReportDataBlock(block);

						logger.debug("yes reportField and repeated, adding reportData of "
								+ reportField.getReportFieldName()
								+ "-"
								+ reportField.getReportFieldNum().toString()
								+ " with Block " + block);

						// n repe already added, we skip
						if (count == 99 && repeAdded) {
							break;
						}

						reportDatas.add(reportDataTemp);

						// n repes only one added
						if (count == 99) {
							break;
						}

					}
				}
			}

			flagField = false;
			repeAdded = false;
		}

		reportExecution.setReportDatas(reportDatas);

		return reportExecution;
	}

	/**
	 * Function to clean all empty reportDatas which are not populated by UI
	 * 
	 * @param reportExecution
	 * @return reportExecution cleaned of empty reportDatas
	 */
	private ReportExecution cleanReportDatas(ReportExecution reportExecution) {

		List<ReportData> reportDatas = new ArrayList<ReportData>();

		logger.info("clean data, size entering: "
				+ reportExecution.getReportDatas().size());

		for (ReportData reportData : reportExecution.getReportDatas()) {
			if (reportData.getReportDataText() != null
					&& !reportData.getReportDataText().isEmpty()
					&& !reportData.getReportDataText().equals(
							ReportUtilities.emptySelect)) {
				reportDatas.add(reportData);
				logger.debug("adding reportData "
						+ reportData.getReportDataText() + " from "
						+ reportData.getReportField().getReportFieldName());
			}
		}

		logger.debug("clean data, size ending: " + reportDatas.size());

		reportExecution.setReportDatas(reportDatas);

		return reportExecution;
	}

	/**
	 * Function create Map<reportFieldType, reportFieldValues> to make dropdowns
	 * 
	 * @return Map<String, String> of dropdown content
	 */
	private Map<String, String> getReportFieldListDropdown() {

		Map<String, String> result = new HashMap<String, String>();

		ReportFieldListDAO reportFieldListDAO = (ReportFieldListDAO) applicationContext
				.getBean("reportFieldListDAO");

		List<ReportFieldList> reportFieldLists = reportFieldListDAO.findAll();

		// list of all fieldType, the relation between
		// reportField.reportFieldMask and reportFieldList
		List<String> filedTypeList = getReportFieldListTypeString();

		// populate result with dropdowns and their values
		for (String fieldType : filedTypeList) {
			String fieldListValues = "";
			for (ReportFieldList reportFieldList : reportFieldLists) {
				if (reportFieldList.getReportFieldListType().equals(fieldType))
					fieldListValues = fieldListValues + ","
							+ reportFieldList.getReportFieldListValue();
			}
			// this value represent no populate dropdown, it is necessary to
			// clean before save
			// fieldListValues = fieldListValues + ",<empty>";
			logger.debug("dropdown: " + fieldType + "-> " + fieldListValues);

			result.put(fieldType, fieldListValues);
		}

		// TODO dropdown of country code are unsorted

		return result;
	}

	/**
	 * Function load list of reportFieldListTypes
	 * 
	 * @return List<String> of dropdown types
	 */
	private List<String> getReportFieldListTypeString() {

		ReportFieldListDAO reportFieldListDAO = (ReportFieldListDAO) applicationContext
				.getBean("reportFieldListDAO");

		List<ReportFieldList> reportFieldLists = reportFieldListDAO.findAll();

		// list of all fieldType, the relation between
		// reportField.reportFieldMask and reportFieldList
		List<String> filedTypeList = new ArrayList<String>();

		// create List of all dropdowns
		for (ReportFieldList reportFieldList : reportFieldLists) {
			if (!filedTypeList.contains(reportFieldList
					.getReportFieldListType())) {
				filedTypeList.add(reportFieldList.getReportFieldListType());
				logger.debug("list: "
						+ reportFieldList.getReportFieldListType());
			}

		}

		return filedTypeList;
	}

	/**
	 * Function to adapt reportData.text when reportField with mask FECHA_GEN to
	 * DATE, replacing '-' for '/' depending if is GET or POST type.
	 * 
	 * @param reportExecution
	 * @param type
	 *            : GET || POST
	 * @return ReportExecution
	 */
	@Deprecated
	private ReportExecution adaptDATEtoFECHA_GEN(
			ReportExecution reportExecution, String type) {

		for (ReportData reportData : reportExecution.getReportDatas()) {
			if (reportData.getReportField().getReportFieldMask()
					.equals("FECHA_GEN")) {
				System.out.println(type + " antes: " + reportData.getReportDataText());
				
				if (type.equals("GET"))
					// change '/' for ''
					reportData.setReportDataText(reportData.getReportDataText()
							.replace('/', '-'));
				else
					// change '-' for '/'
					reportData.setReportDataText(reportData.getReportDataText()
							.replace('-', '/'));
				
				System.out.println(type + " despues: " + reportData.getReportDataText());
			}
		}

		return reportExecution;
	}

	/**
	 * Function generate a List of ReportSectionForm which can be used to make
	 * sections in UI
	 * 
	 * @param reportExecution
	 * @return List<ReportSectionForm>
	 */
	private List<ReportSectionForm> getReportSections(
			ReportExecution reportExecution) {

		List<ReportSectionForm> result = new ArrayList<ReportSectionForm>();

		String section = "";
		boolean hasBlock = false;

		for (ReportData reportData : reportExecution.getReportDatas()) {
			if (reportData.getReportDataErrors().size() > 0) {
				logger.debug("Data tiene errores...");
				for (ReportDataError error : reportData.getReportDataErrors())
					logger.debug("Error: " + error.getReportDataErrorText());
			}
			if (reportData.getReportField().getReportFieldSection() != null) {
				section = reportData.getReportField().getReportFieldSection();
				if (reportData.getReportDataBlock() != null) {
					hasBlock = true;
				} else {
					hasBlock = false;
				}
				ReportSectionForm reportSectionForm = new ReportSectionForm(
						section, hasBlock);
				if (!result.contains(reportSectionForm))
					result.add(reportSectionForm);
			}
		}

		return result;
	}

}
