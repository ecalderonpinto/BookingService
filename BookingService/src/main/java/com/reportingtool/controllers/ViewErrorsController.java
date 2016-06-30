package com.reportingtool.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.entities.dao.loader.LoadErrorDAO;
import com.entities.dao.loader.LoadFileDAO;
import com.entities.dao.reportingtool.ReportDataErrorDAO;
import com.entities.dao.reportingtool.ReportErrorDAO;
import com.entities.dao.reportingtool.ReportExecutionDAO;
import com.entities.entity.loader.LoadError;
import com.entities.entity.loader.LoadFile;
import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportDataError;
import com.entities.entity.reportingtool.ReportError;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.entity.usermanager.User;
import com.reportingtool.controllers.forms.AlertToView;
import com.reportingtool.service.UserService;

@Controller
public class ViewErrorsController {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	UserService userDetailsService;

	private static final Logger logger = LoggerFactory
			.getLogger(ViewErrorsController.class);

	@RequestMapping(value = "/viewErrors.do", method = RequestMethod.GET)
	public String loadErrorDetailController(@RequestParam("id") String id,
			Model model) {

		logger.info("ViewErrors Controller - LoadId=" + id);

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			LoadFileDAO loadFileDAO = (LoadFileDAO) applicationContext
					.getBean("loadFileDAO");
			LoadFile loadFile = loadFileDAO.findById(Long.parseLong(id));

			model.addAttribute("load", loadFile);

			return "viewerrors";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

	@RequestMapping(value = "/loadError.do", method = RequestMethod.GET)
	public String loadErrorController(Locale locale, Model model) {

		logger.info("Load Error Controller");

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			LoadErrorDAO loadErrorDAO = (LoadErrorDAO) applicationContext
					.getBean("loadErrorDAO");
			List<LoadError> loadErrors = loadErrorDAO.findAll();

			model.addAttribute("loaderrors", loadErrors);

			return "loaderror";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

	@RequestMapping(value = "/reportError.do", method = RequestMethod.GET)
	public String reportErrorController(@RequestParam("id") String id,
			Locale locale, Model model) {

		logger.info("Report Error Controller id=" + id);

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			ReportErrorDAO reportErrorDAO = (ReportErrorDAO) applicationContext
					.getBean("reportErrorDAO");

			ReportDataErrorDAO reportDataErrorDAO = (ReportDataErrorDAO) applicationContext
					.getBean("reportDataErrorDAO");

			ReportExecution reportExecution = null;

			List<ReportError> reportErrors;

			List<ReportDataError> reportDataErrors;

			// sandra:Variable que controla si el bt back vuelve a index.do o a
			// un
			// reporte ya creado
			boolean backindex = true;

			if (id != null && !id.isEmpty()) {

				ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
						.getBean("reportExecutionDAO");
				reportExecution = reportExecutionDAO.findById(Long
						.parseLong(id));
				reportErrors = reportExecution.getReportErrors();

				reportDataErrors = new ArrayList<ReportDataError>();
				for (ReportData reportData : reportExecution.getReportDatas()) {
					reportDataErrors.addAll(reportData.getReportDataErrors());
				}

				backindex = false;

			} else {

				reportErrors = reportErrorDAO.findAll();

				reportDataErrors = reportDataErrorDAO.findAll();

				backindex = true;
			}

			model.addAttribute("reportExecution", reportExecution);
			model.addAttribute("reporterrors", reportErrors);
			model.addAttribute("reportdataerrors", reportDataErrors);
			model.addAttribute("backindex", backindex);

			return "reporterror";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

	@RequestMapping(value = "/reportDataError.do", method = RequestMethod.GET)
	public String reportdataErrorController(Locale locale, Model model) {

		logger.info("Report Data Error Controller");

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			ReportDataErrorDAO reportDataErrorDAO = (ReportDataErrorDAO) applicationContext
					.getBean("reportDataErrorDAO");
			List<ReportDataError> reportDataErrors = reportDataErrorDAO
					.findAll();

			model.addAttribute("reportdataerrors", reportDataErrors);

			return "reportdataerror";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

}
