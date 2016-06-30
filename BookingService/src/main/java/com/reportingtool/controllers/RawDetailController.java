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

import com.entities.dao.loader.LoadRawDAO;
import com.entities.entity.loader.LoadRaw;
import com.entities.entity.usermanager.User;
import com.reportingtool.controllers.forms.AlertToView;
import com.reportingtool.service.UserService;

@Controller
@RequestMapping(value = "/rawDetail.do")
public class RawDetailController {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	UserService userDetailsService;

	private static final Logger logger = LoggerFactory
			.getLogger(RawDetailController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String DataManagerControllerPre(@RequestParam("id") String id,
			Model model) {

		logger.info("RawDetail Controller - id=" + id);

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			LoadRawDAO loadRawDao = (LoadRawDAO) applicationContext
					.getBean("loadRawDAO");
			LoadRaw loadRaw = loadRawDao.findById(Long.parseLong(id));

			model.addAttribute("loadRaw", loadRaw);

			return "rawdetail";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}
}
