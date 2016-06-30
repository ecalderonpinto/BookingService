package com.reportingtool.controllers;

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

import com.entities.dao.reportingtool.FundDAO;
import com.entities.dao.reportingtool.ReportExecutionDAO;
import com.entities.entity.reportingtool.Fund;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.entity.usermanager.User;
import com.reportingtool.controllers.forms.AlertToView;
import com.reportingtool.service.UserService;
import com.reportingtool.utilities.ReportingErrorManager;
import com.reportingtool.validator.Status;

@Controller
@SessionAttributes("fund")
public class FundReportsController {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	UserService userDetailsService;

	private static final Logger logger = LoggerFactory
			.getLogger(FundReportsController.class);

	/**
	 * TODO:RT falta implementar los filtros y acciones de la botonera ACTIONS
	 */
	@RequestMapping(value = "/fundReports.do", method = RequestMethod.GET)
	public String DataManagerControllerPre(@RequestParam("id") String id,
			Model model) {

		logger.info("funds Reports Controller - Fund=" + id);

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			FundDAO fundDao = (FundDAO) applicationContext.getBean("fundDAO");
			Fund fund = fundDao.findById(Long.parseLong(id));

			// check some issues in every reportExecution before been showed
			for (ReportExecution reportExecution : fund.getReportExecutions()) {
				// set reportExecution.hasErrors and reportData.hasErrors
				// true/false
				// to show link error/view XML
				ReportingErrorManager
						.checkReportExecutionHasErrors(reportExecution);
			}

			model.addAttribute("fund", fund);

			return "fundreports";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

	@RequestMapping(value = "/reportFundSend.do", method = RequestMethod.GET)
	public String reportExecutionSendController(@RequestParam("id") String id,
			Model model) {

		logger.info("ReportExecution Send Controller - id=" + id);

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
					.getBean("reportExecutionDAO");

			// load reportExecution from id to have all this child entities
			ReportExecution reportExecution = reportExecutionDAO.findById(Long
					.parseLong(id));

			Status reportingStatus = new Status(applicationContext);
			reportingStatus.statusSend(reportExecution);

			// reload company to return
			long idFund = reportExecution.getFund().getId();

			return "redirect:/fundReports.do?id=" + idFund;

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}
}
