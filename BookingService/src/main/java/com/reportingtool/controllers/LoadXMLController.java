package com.reportingtool.controllers;

import java.io.File;
import java.io.FileOutputStream;

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

import com.entities.dao.reportingtool.ReportExecutionDAO;
import com.entities.dictionary.ReportCatalogLevelEnum;
import com.entities.dictionary.UserControlTypeEnum;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.entity.usermanager.User;
import com.reportingtool.controllers.forms.AlertToView;
import com.reportingtool.controllers.forms.LoadXMLFileForm;
import com.reportingtool.creation.GeneratorXMLUtils;
import com.reportingtool.creation.LoadXML;
import com.reportingtool.service.UserService;
import com.reportingtool.utilities.ReportUtilities;
import com.reportingtool.validator.Semantic;
import com.reportingtool.validator.Status;
import com.reportingtool.validator.Syntactic;

@Controller
@RequestMapping(value = "/loadXML.do")
@SessionAttributes("loadXML")
public class LoadXMLController {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	UserService userDetailsService;

	private static final Logger logger = LoggerFactory
			.getLogger(LoadXMLController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String DataManagerControllerPre(@RequestParam("id") String id,
			Model model) {

		logger.info("XML Form controller");

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
					.getBean("reportExecutionDAO");
			ReportExecution reportExecution = reportExecutionDAO.findById(Long
					.parseLong(id));

			LoadXMLFileForm loadXMLFileForm = new LoadXMLFileForm();
			loadXMLFileForm.setReportExecution(reportExecution);

			model.addAttribute("alerts", false);
			model.addAttribute("loadXML", loadXMLFileForm);

			return "loadxml";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			@ModelAttribute("loadXML") LoadXMLFileForm loadXMLFileForm,
			BindingResult result, Model model, SessionStatus status,
			HttpSession session) {

		logger.info("Submit XML Form controller");

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			AlertToView alert = new AlertToView(false, "All were success");

			File file = null;

			boolean update = false;

			try {

				file = new File(loadXMLFileForm.getInputFile()
						.getOriginalFilename());
				file.createNewFile();
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(loadXMLFileForm.getInputFile().getBytes());
				fos.close();

				// before load file, check if XML isValid
				// GeneratorXML generatorXML = new
				// GeneratorXML(applicationContext);

				// String text = new Scanner(file).useDelimiter("\\A").next();
				// with XSD, is not going to load if is incomplete
				// boolean isValid = generatorXML.validateSchemaXSD(text, null,
				// generatorXML.aifmXSDResource);

				boolean isValid = GeneratorXMLUtils.validateXMLWellFormed(file,
						logger);

				if (isValid) {
					logger.info("Fichero xml " + file.getPath());

					LoadXML loadXML = new LoadXML(applicationContext);

					ReportExecution reportExecution = loadXMLFileForm
							.getReportExecution();

					// mode update or only insert
					update = loadXMLFileForm.isUpdate();

					if (reportExecution.getReportCatalog().getReportLevel()
							.contains(ReportCatalogLevelEnum.FUND.getReportLevel())) {
						// load AIF report
						reportExecution = loadXML.loadAIFFile(file,
								reportExecution, update);
					}

					if (reportExecution.getReportCatalog().getReportLevel()
							.contains(ReportCatalogLevelEnum.COMPANY.getReportLevel())) {
						// load AIFMD report
						reportExecution = loadXML.loadAIFMFile(file,
								reportExecution, update);
					}

					// AUDIT
					ReportUtilities.createUserControl(applicationContext, user,
							UserControlTypeEnum.LOADXML.getDesc()
									+ reportExecution.getId(), false,
							UserControlTypeEnum.LOADXML.getType());

					ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
							.getBean("reportExecutionDAO");

					// Syntactic analysis
					Syntactic syntactic = new Syntactic(applicationContext);
					syntactic.validReportExecution(reportExecution);

					// Semantic analysis
					Semantic semantic = new Semantic(applicationContext);
					semantic.checkSemantic(reportExecution);

					// Reporting Status
					Status reportingStatus = new Status(applicationContext);
					reportExecution = reportingStatus
							.checkStatus(reportExecution);

					// save reportExecution
					reportExecutionDAO.edit(reportExecution);
					logger.info("saved changes of reportExecution "
							+ reportExecution.getReportExecutionName());
				} else {
					alert.setError(true);
					// alert.setMessage("XML File has failed XSD validation. It is not loaded.");
					alert.setMessage("File " + file.getPath()
							+ " entered is not XML well formed, it is ignored.");
				}

			} catch (Exception e) {
				// TODO clean
				e.printStackTrace();
				logger.error("Error: " + e.getMessage());
				alert.setError(true);
				alert.setMessage("Error: " + e.getMessage());
			}

			model.addAttribute("loadXML", loadXMLFileForm);

			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);

			return "loadxml";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}
}
