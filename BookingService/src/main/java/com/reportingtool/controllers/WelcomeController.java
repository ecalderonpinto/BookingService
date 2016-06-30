/**
 * If you really care for the license, look for the LICENSE.txt at the project root. Frankly, I 
 * really don't care.
 **/
package com.reportingtool.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entities.dao.reportingtool.CompanyDAO;
import com.entities.dao.reportingtool.ReportCatalogDAO;
import com.entities.entity.reportingtool.Company;
import com.entities.entity.reportingtool.ReportCatalog;
import com.entities.entity.usermanager.User;
import com.reportingtool.controllers.forms.AlertToView;
import com.reportingtool.service.UserService;

/**
 * This controller facilitates actions related to general user welcome and admin
 * welcome pages.
 * 
 * @author Roshan Amadoru
 **/
@Controller
public class WelcomeController {

	private static Logger logger = LoggerFactory
			.getLogger(WelcomeController.class);

	@Autowired
	UserService userDetailsService;

	@Autowired
	ApplicationContext applicationContext;

	/**
	 * Handles requests to the /welcome.do page
	 **/
	@RequestMapping(method = RequestMethod.GET, value = { "welcome" })
	public String showWelcomePage(Model model) {

		logger.info("Welcome User: /welcome.do");

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			// datamanager.jsp
			List<Company> companies = new ArrayList<Company>();

			CompanyDAO companyDao = (CompanyDAO) applicationContext
					.getBean("companyDAO");
			Company company = user.getCompany();
			if (company == null) {
				// if user has no company, show all
				companies = companyDao.findAll();
			} else {
				// if user has company, show only this
				companies.add(company);
			}

			model.addAttribute("companies", companies);

			return "datamanager";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

	/**
	 * Handles requests to the /admin/welcome.do page
	 **/
	@RequestMapping(method = RequestMethod.GET, value = "admin/welcome.do")
	public String showAdminWelcomePage(Model model) {

		logger.info("Welcome admin: /admin/welcome.do");

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			// admin/admin.jsp
			ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) applicationContext
					.getBean("reportCatalogDAO");
			List<ReportCatalog> reportCatalogs = reportCatalogDAO.findAll();

			model.addAttribute("reportcatalogs", reportCatalogs);
			model.addAttribute("alerts", false);

			return "admin";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}
}