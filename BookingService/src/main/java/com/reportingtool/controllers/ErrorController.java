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
import com.reportingtool.service.UserService;

@Controller
public class ErrorController {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	UserService userDetailsService;

	private static final Logger logger = LoggerFactory
			.getLogger(ErrorController.class);

	/**
	 * TODO:RT Considerar UI y ver necesidad de incorporar más funcionalidad.
	 */
	@RequestMapping(value = "/error.do", method = RequestMethod.GET)
	public String reportCatalogController(Locale locale, Model model) {

		logger.info("Error Page - Controller");

		User user = userDetailsService.getCurrentUser();
		model.addAttribute("user", user);

		return "error";
	}
}
