package com.reportingtool.controllers;

import java.util.List;
import java.util.Locale;

import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.entities.dao.reportingtool.ReportCatalogDAO;
import com.entities.dao.reportingtool.ReportFieldDAO;
import com.entities.dao.reportingtool.ReportFieldListDAO;
import com.entities.entity.install.InstallManager;
import com.entities.entity.reportingtool.ReportCatalog;
import com.entities.entity.reportingtool.ReportField;
import com.entities.entity.reportingtool.ReportFieldList;
import com.entities.entity.usermanager.User;
import com.reportingtool.controllers.forms.AlertToView;
import com.reportingtool.service.UserService;

@Controller
public class AdminController {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	UserService userDetailsService;

	private static final Logger logger = LoggerFactory
			.getLogger(AdminController.class);

	/**
	 * TODO:RT Considerar UI y ver necesidad de incorporar más funcionalidad.
	 */
	@RequestMapping(value = "/reportCatalog.do", method = RequestMethod.GET)
	public String reportCatalogController(Locale locale, Model model) {

		logger.info("Admin - Report Catalog Controller");

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) applicationContext
					.getBean("reportCatalogDAO");
			List<ReportCatalog> reportCatalogs = reportCatalogDAO.findAll();

			model.addAttribute("reportcatalogs", reportCatalogs);
			model.addAttribute("alerts", false);

			return "reportcatalog";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

	@RequestMapping(value = "/reportCatalogDetail.do", method = RequestMethod.GET)
	public String reportCatalogDetailController(@RequestParam("id") String id,
			Model model) {

		logger.info("Admin - Report Catalog Detail Controller - id=" + id);

		try {

			ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) applicationContext
					.getBean("reportCatalogDAO");
			ReportCatalog reportCatalog = reportCatalogDAO.findById(Long
					.parseLong(id));

			model.addAttribute("reportcatalog", reportCatalog);
			model.addAttribute("reportfields", reportCatalog.getReportFields());

			return "reportcatalogdetail";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

	/**
	 * TODO:RT Falta editar/guardar información;
	 */
	@RequestMapping(value = "/reportFieldDetail.do", method = RequestMethod.GET)
	public String reportFieldDetailController(@RequestParam("id") String id,
			Model model) {

		logger.info("Admin - Report Field Detail Controller - id=" + id);

		try {

			ReportFieldDAO reportFieldDAO = (ReportFieldDAO) applicationContext
					.getBean("reportFieldDAO");
			ReportField reportField = reportFieldDAO.findById(Long
					.parseLong(id));

			model.addAttribute("reportfield", reportField);
			model.addAttribute("reportcatalog", reportField.getReportCatalog());

			return "reportfielddetail";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

	@RequestMapping(value = "/reportFieldList.do", method = RequestMethod.GET)
	public String reportFieldListController(Locale locale, Model model) {

		logger.info("Admin Report Field List Controller");

		try {

			ReportFieldListDAO reportFieldListDAO = (ReportFieldListDAO) applicationContext
					.getBean("reportFieldListDAO");
			List<ReportFieldList> reportFieldList = reportFieldListDAO
					.findAll();

			model.addAttribute("reportfieldlist", reportFieldList);

			return "reportfieldlist";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}
	
	@RequestMapping(value = "/admin/admin.do", method = RequestMethod.GET)
	public String adminController(Locale locale, Model model) {

		logger.info("Admin - Report Catalog Controller");

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			// it is only showed admin buttons

			return "admin";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

	@RequestMapping(value = "/admin/generateDB.do")
	public String generateDataBase(Model model) {

		logger.info("Admin generateDB");

		try {

			LocalSessionFactoryBean sessionFactory = this.applicationContext
					.getBean("&sessionFactory", LocalSessionFactoryBean.class);

			SchemaExport export = new SchemaExport(
					sessionFactory.getConfiguration());
			export.drop(false, true);
			export.create(false, true);
			
			// TODO finish this
			//InstallManager installManager = new InstallManager(applicationContext);
			//installManager.resetId();

			logger.info("Database schema generated.");

			AlertToView alert = new AlertToView(false,
					"Generation of DB complete");
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);

			return "admin";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

	@RequestMapping(value = "/admin/installAIFMDData.do")
	public String installAIFDMData(Model model) {

		logger.info("Admin - installAIFDMData");

		try {

			InstallManager installManager = new InstallManager(
					applicationContext);
			installManager.deleteAll();
			installManager.installAll(InstallManager.typeAIFMD);

			logger.info("Database clean and reinstalled.");

			// to refresh content, load al data similar to "admin"
			ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) applicationContext
					.getBean("reportCatalogDAO");
			List<ReportCatalog> reportCatalogs = reportCatalogDAO.findAll();

			AlertToView alert = new AlertToView(false, "Install AIFMD correct");

			model.addAttribute("reportcatalogs", reportCatalogs);
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);

			return "admin";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}
	
	@RequestMapping(value = "/admin/installDMOData.do")
	public String installDMOData(Model model) {

		logger.info("Admin - installDMOData");

		try {

			InstallManager installManager = new InstallManager(
					applicationContext);
			installManager.deleteAll();
			installManager.installAll(InstallManager.typeDMO);

			logger.info("Database clean and reinstalled.");

			// to refresh content, load al data similar to "admin"
			ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) applicationContext
					.getBean("reportCatalogDAO");
			List<ReportCatalog> reportCatalogs = reportCatalogDAO.findAll();

			AlertToView alert = new AlertToView(false, "Install DMO correct");

			model.addAttribute("reportcatalogs", reportCatalogs);
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);

			return "admin";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

	@RequestMapping(value = "/admin/cleanData.do")
	public String cleanData(Model model) {

		logger.info("Admin - cleanData");

		try {

			InstallManager installManager = new InstallManager(
					applicationContext);
			installManager.deleteAll();

			logger.info("Database clean.");

			// to refresh content, load al data similar to "admin"
			ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) applicationContext
					.getBean("reportCatalogDAO");
			List<ReportCatalog> reportCatalogs = reportCatalogDAO.findAll();

			AlertToView alert = new AlertToView(false, "Delete all data");

			model.addAttribute("reportcatalogs", reportCatalogs);
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}

		return "admin";
	}

}
