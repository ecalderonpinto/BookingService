package com.reportingtool.controllers;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entities.entity.usermanager.User;
import com.reportingtool.controllers.forms.AlertToView;
import com.reportingtool.service.UserService;

@Controller
public class UserController {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	UserService userDetailsService;

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@RequestMapping(value = "/user.do", method = RequestMethod.GET)
	public String userListController(Locale locale, Model model) {

		logger.info("User Controller");

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			model.addAttribute("alerts", false);

			return "user";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

}
