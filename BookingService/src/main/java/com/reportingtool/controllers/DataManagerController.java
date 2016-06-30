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

import com.entities.dao.reportingtool.CompanyDAO;
import com.entities.entity.reportingtool.Company;
import com.entities.entity.usermanager.User;
import com.reportingtool.controllers.forms.AlertToView;
import com.reportingtool.service.UserService;

@Controller
public class DataManagerController {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	UserService userDetailsService;

	private static final Logger logger = LoggerFactory
			.getLogger(DataManagerController.class);

	/**
	 * TODO:RT Considerar incorporar más info a esta pantalla;
	 */
	@RequestMapping(value = "/dataManager.do", method = RequestMethod.GET)
	public String DataManagerControllerPre(Locale locale, Model model) {

		logger.info("DataManager Controller");

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

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
}
