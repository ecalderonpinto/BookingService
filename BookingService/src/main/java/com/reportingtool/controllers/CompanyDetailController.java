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

import com.entities.dao.reportingtool.CompanyDAO;
import com.entities.dictionary.ReportCatalogLevelEnum;
import com.entities.entity.reportingtool.Company;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.entity.usermanager.User;
import com.reportingtool.controllers.forms.AlertToView;
import com.reportingtool.service.UserService;

@Controller
@RequestMapping(value = "/companyDetail.do")
@SessionAttributes("company")
public class CompanyDetailController {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	UserService userDetailsService;

	private static final Logger logger = LoggerFactory
			.getLogger(CompanyDetailController.class);

	/**
	 * TODO:RT A nivel de DATA MANAGER no se considera la edición. Faltan
	 * consideraciones estéticas a nivel de pantalla;
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String DataManagerControllerPre(@RequestParam("id") String id,
			Model model) {

		logger.info("CompanyDetail Controller - id=" + id);

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			CompanyDAO companyDao = (CompanyDAO) applicationContext
					.getBean("companyDAO");
			Company company = companyDao.findById(Long.parseLong(id));
			
			int contCompany = 0;
			int contDepartment = 0;
			int contFund = 0;
			for (ReportExecution report : company.getReportExecutions()) {
				if (report.getReportCatalog().getReportLevel().equals(ReportCatalogLevelEnum.COMPANY.getReportLevel()))
					contCompany++;
				if (report.getReportCatalog().getReportLevel().equals(ReportCatalogLevelEnum.DEPARTMENT.getReportLevel()))
					contDepartment++;
				if (report.getReportCatalog().getReportLevel().equals(ReportCatalogLevelEnum.FUND.getReportLevel()))
					contFund++;
			}

			model.addAttribute("contCompany", contCompany);
			model.addAttribute("contDepartment", contDepartment);
			model.addAttribute("contFund", contFund);
			model.addAttribute("company", company);

			return "companydetail";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}
}
