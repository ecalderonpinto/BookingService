package com.reportingtool.controllers;

import java.util.Collections;
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

import com.entities.dao.usermanager.UserControlDAO;
import com.entities.dao.usermanager.UserDAO;
import com.entities.entity.usermanager.User;
import com.entities.entity.usermanager.UserControl;
import com.reportingtool.controllers.forms.AlertToView;
import com.reportingtool.service.UserService;

@Controller
public class UserManagerController {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	UserService userDetailsService;

	private static final Logger logger = LoggerFactory
			.getLogger(UserManagerController.class);

	@RequestMapping(value = "/admin/userManager.do", method = RequestMethod.GET)
	public String userManagerListController(Locale locale, Model model) {

		logger.info("User Manager Controller");

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			UserDAO userDAO = (UserDAO) applicationContext.getBean("userDAO");
			List<User> users = userDAO.findAll();

			UserControlDAO userControlDAO = (UserControlDAO) applicationContext
					.getBean("userControlDAO");
			List<UserControl> userControls = userControlDAO.findAll();
			
			// sort to show recent controls
			Collections.sort(userControls);
			// limit 25 elements to show 
			int n = 25;
			if (userControls.size() < n)
				n = userControls.size();
			List<UserControl> userControls2 = userControls.subList(0, n);

			model.addAttribute("users", users);
			model.addAttribute("usercontrols", userControls2);
			model.addAttribute("alerts", false);

			return "usermanager";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

}
