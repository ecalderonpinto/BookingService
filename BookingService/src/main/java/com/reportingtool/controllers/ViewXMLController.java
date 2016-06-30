package com.reportingtool.controllers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.entities.dao.reportingtool.ReportExecutionDAO;
import com.entities.dictionary.ErrorTypeEnum;
import com.entities.dictionary.ReportCatalogLevelEnum;
import com.entities.dictionary.UserControlTypeEnum;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.entity.usermanager.User;
import com.reportingtool.controllers.forms.AlertToView;
import com.reportingtool.controllers.forms.GenerateXMLForm;
import com.reportingtool.creation.GeneratorXML;
import com.reportingtool.scheduler.DMOLoader;
import com.reportingtool.service.UserService;
import com.reportingtool.utilities.ReportUtilities;
import com.reportingtool.utilities.ReportingErrorManager;
import com.reportingtool.validator.Status;

@Controller
@RequestMapping(value = "/viewXML.do")
@SessionAttributes("generateXML")
public class ViewXMLController {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	UserService userDetailsService;

	// variable with reportExecution and outputXML, filled in GET, used in
	// POST to generate file.xml
	private GenerateXMLForm generateXMLForm = null;

	private static final Logger logger = LoggerFactory
			.getLogger(ViewXMLController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String DataManagerControllerPre(@RequestParam("id") String id,
			Model model) {

		logger.info("View XML controller id=" + id);

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
					.getBean("reportExecutionDAO");

			ReportExecution reportExecution = reportExecutionDAO.findById(Long
					.parseLong(id));

			GeneratorXML generatorXML = new GeneratorXML(applicationContext);

			try {
				generateXMLForm = generatorXML.generateXML(reportExecution);

				// this form has reportExecution with new potential errors to
				// check
				reportExecution = generateXMLForm.getReportExecution();
				ReportingErrorManager
						.checkReportExecutionHasErrors(reportExecution);
				
				// Reporting Status
				Status reportingStatus = new Status(applicationContext);
				reportExecution = reportingStatus.checkStatus(reportExecution);

			} catch (Exception e) {
				logger.error("XML Generator, Error when generating XML: "
						+ e.getMessage());
			}

			// can be null if XML has errors
			if (generateXMLForm == null) {
				generateXMLForm = new GenerateXMLForm();
				generateXMLForm.setHasErrors(true);
				generateXMLForm.setOutputXML("");
				generateXMLForm.setReportExecution(reportExecution);
				generateXMLForm.setValid(false);
			}

			model.addAttribute("generateXML", generateXMLForm);

			// AUDIT
			ReportUtilities.createUserControl(
					applicationContext,
					user,
					UserControlTypeEnum.VIEWREPORT.getDesc()
							+ reportExecution.getId(), false,
					UserControlTypeEnum.VIEWREPORT.getType());

			return "viewxml";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	protected void exportXML(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		logger.info("Download XML report");

		DateFormat dateFormat = new SimpleDateFormat(
				ReportUtilities.datePattern);
		Date today = Calendar.getInstance().getTime();
		String creationDate = dateFormat.format(today);

		String reportName = generateXMLForm.getReportExecution()
				.getReportExecutionName() + "_" + creationDate + ".xml";

		// firefox does not recognize format of file if there are spaces in the
		// name: 'name report DATE.xml' -> is replaced 'name_report_DATE.xml'
		reportName = reportName.replace(" ", "_");

		// tell browser program going to return an application file
		// instead of html page
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ reportName);

		try {

			ServletOutputStream out = response.getOutputStream();

			StringBuffer sb = new StringBuffer(generateXMLForm.getOutputXML());
			InputStream in = new ByteArrayInputStream(sb.toString().getBytes(
					"UTF-8"));

			// copy input to servlet, download by web
			IOUtils.copy(in, out);
			in.close();
			out.close();

			// send string to download locally if has DMO
			if (generateXMLForm.getReportExecution().getReportCatalog()
					.getReportLevel()
					.equals(ReportCatalogLevelEnum.DEPARTMENT.getReportLevel()))
				writeXMLFileLocal(sb.toString(), reportName);

		} catch (Exception e) {
			logger.error("Error in download XML " + e.getMessage());
			ReportingErrorManager.createReportError(applicationContext,
					ErrorTypeEnum.GENERATION.getErrorType(),
					generateXMLForm.getReportExecution(), "FAIL",
					"Error when download XML " + e.getMessage());
		}

	}

	/**
	 * Receive a string to write and a fileName and generate a local file in
	 * path DMOLoader.configName.fileOut property.
	 * 
	 * @param stringOut
	 * @param fileName
	 */
	private void writeXMLFileLocal(String stringOut, String fileName) {

		String fileOut = null;
		InputStream input = null;

		try {

			Properties prop = new Properties();
			String filename = DMOLoader.configName;
			input = ViewXMLController.class.getClassLoader()
					.getResourceAsStream(filename);
			if (input == null) {
				logger.error("File configName not found: "
						+ DMOLoader.configName);
			} else {
				// load a properties file from class path, inside static method
				prop.load(input);
				fileOut = prop.getProperty("fileOut");

				// we expect a valid folder in this property
				File folder = new File(fileOut);
				if (folder != null && folder.exists() && folder.isDirectory()) {
					// create file in local folder
					FileUtils.writeStringToFile(new File(fileOut + fileName),
							stringOut);
				} else {
					logger.info("Folder not valid: " + fileOut);
				}
			}
		} catch (Exception e) {
			logger.error("Error when writting local file: " + e.getMessage());
		}
	}
}
