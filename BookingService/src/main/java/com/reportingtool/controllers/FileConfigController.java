package com.reportingtool.controllers;

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

import com.entities.dao.loader.FileColumDAO;
import com.entities.dao.loader.FileColumListDAO;
import com.entities.dao.loader.FileConfigDAO;
import com.entities.entity.loader.FileColum;
import com.entities.entity.loader.FileColumList;
import com.entities.entity.loader.FileConfig;
import com.entities.entity.usermanager.User;
import com.reportingtool.controllers.forms.AlertToView;
import com.reportingtool.service.UserService;

@Controller
public class FileConfigController {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	UserService userDetailsService;

	private static final Logger logger = LoggerFactory
			.getLogger(AdminController.class);

	/**
	 * TODO:RT Falta implementar edición; Filtros??
	 */
	@RequestMapping(value = "/fileConfig.do", method = RequestMethod.GET)
	public String fileConfigController(Locale locale, Model model) {

		logger.info("File Config Controller");

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			FileConfigDAO fileConfigDAO = (FileConfigDAO) applicationContext
					.getBean("fileConfigDAO");
			List<FileConfig> fileConfigs = fileConfigDAO.findAll();

			model.addAttribute("fileconfigs", fileConfigs);

			return "fileconfig";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

	/**
	 * TODO:RT Edición; Filtros??
	 */
	@RequestMapping(value = "/fileConfigDetail.do", method = RequestMethod.GET)
	public String fileConfigDetailController(@RequestParam("id") String id,
			Model model) {

		logger.info("FileConfigDetail Controller - id=" + id);

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			FileConfigDAO fileConfigDAO = (FileConfigDAO) applicationContext
					.getBean("fileConfigDAO");
			FileConfig fileConfig = fileConfigDAO.findById(Long.parseLong(id));

			model.addAttribute("fileconfig", fileConfig);
			model.addAttribute("filecolums", fileConfig.getFileColums());

			return "fileconfigdetail";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

	@RequestMapping(value = "/fileColumDetail.do", method = RequestMethod.GET)
	public String fileColumDetailController(@RequestParam("id") String id,
			Model model) {

		logger.info("File Colum Detail Controller - id=" + id);
		
		try {

		User user = userDetailsService.getCurrentUser();
		model.addAttribute("user", user);

		FileColumDAO fileColumDAO = (FileColumDAO) applicationContext
				.getBean("fileColumDAO");
		FileColum fileColum = fileColumDAO.findById(Long.parseLong(id));

		model.addAttribute("filecolum", fileColum);
		model.addAttribute("fileconfig", fileColum.getFileConfig());

		return "filecolumdetail";
		
		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

	@RequestMapping(value = "/fileColumList.do", method = RequestMethod.GET)
	public String fileColumListController(Locale locale, Model model) {

		logger.info("File Colum List Controller");

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			FileColumListDAO fileColumListDAO = (FileColumListDAO) applicationContext
					.getBean("fileColumListDAO");
			List<FileColumList> fileColumList = fileColumListDAO.findAll();

			model.addAttribute("filecolumlist", fileColumList);

			return "filecolumlist";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}
}
