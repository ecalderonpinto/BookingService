package com.reportingtool.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.entities.dao.loader.FileConfigDAO;
import com.entities.dao.loader.LoadFileDAO;
import com.entities.dao.reportingtool.ReportCatalogDAO;
import com.entities.dictionary.ErrorTypeEnum;
import com.entities.dictionary.ReportCatalogLevelEnum;
import com.entities.dictionary.UserControlTypeEnum;
import com.entities.entity.loader.FileConfig;
import com.entities.entity.loader.LoadError;
import com.entities.entity.loader.LoadFile;
import com.entities.entity.loader.LoadRaw;
import com.entities.entity.loader.LoadRawData;
import com.entities.entity.reportingtool.ReportCatalog;
import com.entities.entity.usermanager.User;
import com.reportingtool.controllers.forms.AlertToView;
import com.reportingtool.controllers.forms.GenerateDMOForm;
import com.reportingtool.controllers.forms.LoadFileForm;
import com.reportingtool.normalizer.Format;
import com.reportingtool.normalizer.Translate;
import com.reportingtool.scheduler.DMOLoader;
import com.reportingtool.scheduler.FileLoader;
import com.reportingtool.service.UserService;
import com.reportingtool.utilities.ReportUtilities;
import com.reportingtool.utilities.ReportingErrorManager;

@Controller
@SessionAttributes({ "loadFile", "generateDMO" })
public class LoadsController {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	UserService userDetailsService;

	private static final Logger logger = LoggerFactory
			.getLogger(LoadsController.class);

	@RequestMapping(value = "/loads.do", method = RequestMethod.GET)
	public String DataManagerControllerPre(Locale locale, Model model) {

		logger.info("Loads Controller");

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			LoadFileDAO loadFileDao = (LoadFileDAO) applicationContext
					.getBean("loadFileDAO");
			List<LoadFile> loads = loadFileDao.findAll();

			LoadFileForm form = new LoadFileForm();
			GenerateDMOForm dmoForm = new GenerateDMOForm();

			// ////////////////////////////////////////////
			// if there are reportCatalog type DMO
			// we show a button to generate files from
			// AML schema to load here
			boolean dmoExists = false;
			ReportCatalog reportCatalog = new ReportCatalog();
			reportCatalog.setReportLevel(ReportCatalogLevelEnum.DEPARTMENT
					.getReportLevel());
			ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) applicationContext
					.getBean("reportCatalogDAO");
			List<ReportCatalog> reportCatalogs = reportCatalogDAO
					.findByExample(reportCatalog);
			if (reportCatalogs.size() > 0)
				dmoExists = true;

			model.addAttribute("dmoExists", dmoExists);
			// ///////////////////////////////////////////

			model.addAttribute("loads", loads);
			model.addAttribute("loadFile", form);
			model.addAttribute("alerts", false);

			model.addAttribute("generateDMO", dmoForm);

			return "loads";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

	@RequestMapping(value = "/loads.do", method = RequestMethod.POST)
	public String processSubmit(
			@ModelAttribute("loadFile") LoadFileForm loadFileForm,
			@ModelAttribute("generateDMO") GenerateDMOForm generateDMOForm,
			Model model) {

		logger.info("Loads Controller id="
				+ loadFileForm.getSelectFileConfigs());
		logger.info("Loads Controller id=" + generateDMOForm.toString());

		String out = "loads";

		// in this POST there are two forms: loadFile & generateDMO
		if (loadFileForm.getInputFile() == null) {
			// if there are not file, call generateDMO
			out = dmoLoadProcessSubmit(generateDMOForm, model);

		} else {
			// else there are file in form to load

			try {

				User user = userDetailsService.getCurrentUser();
				model.addAttribute("user", user);

				AlertToView alert = new AlertToView(false, "All were success");

				try {
					InputStream inputStream = loadFileForm.getInputFile()
							.getInputStream();
					String fileName = loadFileForm.getInputFile()
							.getOriginalFilename();

					FileConfigDAO fileConfigDAO = (FileConfigDAO) applicationContext
							.getBean("fileConfigDAO");
					FileConfig fileConfig = fileConfigDAO.findById(Long
							.parseLong(loadFileForm.getSelectFileConfigs()));

					LoadFile loadFile = getLoadFile(applicationContext, alert,
							fileConfig, inputStream, fileName);

					// AUDIT
					ReportUtilities.createUserControl(
							applicationContext,
							user,
							UserControlTypeEnum.LOADFILE.getDesc()
									+ loadFile.getLoadFileName(), false,
							UserControlTypeEnum.LOADFILE.getType());

				} catch (IOException e) {
					alert.setError(true);
					alert.setMessage("IO Problem: " + e.getMessage());
					logger.error("IO Problem: " + e.getMessage());
				} catch (SerialException e) {
					alert.setError(true);
					alert.setMessage("Error parsing the file: "
							+ e.getMessage());
					logger.error("Error parsing the file: " + e.getMessage());
				} catch (SQLException e) {
					alert.setError(true);
					alert.setMessage("Error inserting in database: "
							+ e.getMessage());
					logger.error("Error inserting in database: "
							+ e.getMessage());
				}

				model.addAttribute("alerts", true);
				model.addAttribute("alert", alert);

				// refresh content of loads to display
				LoadFileDAO loadFileDao = (LoadFileDAO) applicationContext
						.getBean("loadFileDAO");
				List<LoadFile> loads = loadFileDao.findAll();

				model.addAttribute("loads", loads);

				// ////////////////////////////////////////////
				// if there are reportCatalog type DMO
				// we show a button to generate files from
				// AML schema to load here
				boolean dmoExists = false;
				ReportCatalog reportCatalog = new ReportCatalog();
				reportCatalog.setReportLevel(ReportCatalogLevelEnum.DEPARTMENT
						.getReportLevel());
				ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) applicationContext
						.getBean("reportCatalogDAO");
				List<ReportCatalog> reportCatalogs = reportCatalogDAO
						.findByExample(reportCatalog);
				if (reportCatalogs.size() > 0)
					dmoExists = true;

				model.addAttribute("dmoExists", dmoExists);
				// ///////////////////////////////////////////

			} catch (Exception e) {
				AlertToView alert = new AlertToView(true, e.getMessage());
				model.addAttribute("alerts", true);
				model.addAttribute("alert", alert);
				out = "error";
			}
		}

		return out;
	}

	@ModelAttribute("selectFileConfigs")
	public Map<String, String> populateFileConfigSelect() {

		logger.info("populate fileConfig Select");

		FileConfigDAO fileConfigDao = (FileConfigDAO) applicationContext
				.getBean("fileConfigDAO");
		List<FileConfig> configs = fileConfigDao.findAll();

		Map<String, String> selectFileConfigs = new HashMap<String, String>();
		for (FileConfig config : configs) {
			selectFileConfigs.put(Long.toString(config.getId()),
					config.getFileConfigName());
		}

		return selectFileConfigs;
	}

	/**
	 * From submit of DMO Data file generator, call DMOLoader class to generate
	 * LoadFiles from AML database schema.
	 * 
	 * @param generateDMOForm
	 * @param model
	 * @return tiles "loads" / "error"
	 */
	private String dmoLoadProcessSubmit(GenerateDMOForm generateDMOForm,
			Model model) {

		logger.info("DMO Load Action");

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			AlertToView alert = new AlertToView(false, "All were success");

			// Call to DMO loader and generate files
			Integer period = new Integer(generateDMOForm.getPeriod());
			Integer monthLess = new Integer(generateDMOForm.getMonthLess());
			Integer amount = null;
			try {
				amount = new Integer(generateDMOForm.getAmount());
			} catch (Exception e) {
			}

			// default amount is 30000 by law
			if (amount == null) {
				amount = 30000;
			}

			logger.info("Period: " + period + " / Amount: " + amount + " monthLess " + monthLess);

			DMOLoader dmoLoader = new DMOLoader(period, amount, monthLess);
			// method with all logic and return results
			alert = dmoLoader.loadFiles(applicationContext);

			// AUDIT
			ReportUtilities.createUserControl(applicationContext, user,
					UserControlTypeEnum.DMO.getDesc() + " period "
							+ generateDMOForm.getPeriod(), false,
					UserControlTypeEnum.DMO.getType());

			// refresh content of loads to display
			LoadFileDAO loadFileDao = (LoadFileDAO) applicationContext
					.getBean("loadFileDAO");
			List<LoadFile> loads = loadFileDao.findAll();

			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			model.addAttribute("loads", loads);

			// ////////////////////////////////////////////
			// if there are reportCatalog type DMO
			// we show a button to generate files from
			// AML schema to load here
			boolean dmoExists = false;
			ReportCatalog reportCatalog = new ReportCatalog();
			reportCatalog.setReportLevel(ReportCatalogLevelEnum.DEPARTMENT
					.getReportLevel());
			ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) applicationContext
					.getBean("reportCatalogDAO");
			List<ReportCatalog> reportCatalogs = reportCatalogDAO
					.findByExample(reportCatalog);
			if (reportCatalogs.size() > 0)
				dmoExists = true;

			model.addAttribute("dmoExists", dmoExists);
			// ///////////////////////////////////////////F

			return "loads";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

	/**
	 * Function create a LoadFile from a InputStream, fileName and FileConfig,
	 * populate AlertToView and return it.
	 * 
	 * @param applicationContext
	 * @param alert
	 * @param fileConfig
	 * @param inputStream
	 * @param fileName
	 * @return LoadFile
	 * @throws IOException
	 * @throws SerialException
	 * @throws SQLException
	 */
	public static LoadFile getLoadFile(ApplicationContext applicationContext,
			AlertToView alert, FileConfig fileConfig, InputStream inputStream,
			String fileName) throws IOException, SerialException, SQLException {

		FileLoader fileLoader = new FileLoader(fileConfig, inputStream,
				fileName);

		LoadFile loadFile = fileLoader.run(applicationContext);
		if (loadFile != null) {
			logger.info(loadFile.getLoadFileName() + " is going to be loader");
		} else {
			alert.setError(true);
			alert.setMessage("Error loading file");
			ReportingErrorManager.createLoadError(applicationContext,
					ErrorTypeEnum.LOADER.getErrorType(), loadFile, "PARSING",
					"Error parsing " + fileName + " file");
		}

		// Save LoadFile in DataBase;
		LoadFileDAO loadFileDAO = (LoadFileDAO) applicationContext
				.getBean("loadFileDAO");
		loadFileDAO.create(loadFile);

		// Validate Proccess;
		for (LoadRaw loadRaw : loadFile.getLoadRaws()) {
			for (LoadRawData loadRawData : loadRaw.getLoadRawDatas()) {
				Translate translate = new Translate(applicationContext);
				translate.translateRaw(loadRawData);

				Format format = new Format(applicationContext);
				format.formatRaw(loadRawData);
			}
		}

		// search for errors and add to alert message
		List<LoadError> loadErrorList = loadFile.getLoadErrors();
		if (loadErrorList.size() > 0) {
			alert.setError(true);
			String temp = "";
			for (LoadError loadError : loadErrorList) {
				temp = temp.concat(" " + loadError.getLoadErrorText());
			}
			alert.setMessage("Error: " + temp);
		}

		return loadFile;
	}
}
