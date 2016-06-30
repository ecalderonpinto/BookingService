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

import com.entities.dao.reportingtool.DepartmentDAO;
import com.entities.entity.reportingtool.Department;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.entity.usermanager.User;
import com.reportingtool.controllers.forms.AlertToView;
import com.reportingtool.service.UserService;
import com.reportingtool.utilities.ReportingErrorManager;

@Controller
@SessionAttributes("department")
public class DepartmentReportsController {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	UserService userDetailsService;

	private static final Logger logger = LoggerFactory
			.getLogger(DepartmentReportsController.class);

	/**
	 * TODO:RT Falta implementar filtros;
	 */
	@RequestMapping(value = "/departmentReports.do", method = RequestMethod.GET)
	public String DataManagerControllerPre(@RequestParam("id") String id,
			Model model) {

		logger.info("Department Reports Controller - Company=" + id);

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			DepartmentDAO departmentDAO = (DepartmentDAO) applicationContext
					.getBean("departmentDAO");
			Department department = departmentDAO.findById(Long.parseLong(id));

			// check some issues in every reportExecution before been showed
			for (ReportExecution reportExecution : department
					.getReportExecutions()) {
				// set reportExecution.hasErrors and reportData.hasErrors
				// true/false
				// to show link error/view XML
				ReportingErrorManager
						.checkReportExecutionHasErrors(reportExecution);
			}

			model.addAttribute("department", department);

			return "departmentreports";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

}
