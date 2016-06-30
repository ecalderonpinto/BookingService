package com.entities.entity.install;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.entities.dao.common.ErrorDAO;
import com.entities.dao.loader.FileColumDAO;
import com.entities.dao.loader.FileColumListDAO;
import com.entities.dao.loader.FileConfigDAO;
import com.entities.dao.loader.LoadErrorDAO;
import com.entities.dao.loader.LoadFileDAO;
import com.entities.dao.loader.LoadRawDAO;
import com.entities.dao.loader.LoadRawDataDAO;
import com.entities.dao.reportingtool.CompanyDAO;
import com.entities.dao.reportingtool.DepartmentDAO;
import com.entities.dao.reportingtool.FundDAO;
import com.entities.dao.reportingtool.FundGroupDAO;
import com.entities.dao.reportingtool.ReportCatalogDAO;
import com.entities.dao.reportingtool.ReportDataDAO;
import com.entities.dao.reportingtool.ReportDataErrorDAO;
import com.entities.dao.reportingtool.ReportErrorDAO;
import com.entities.dao.reportingtool.ReportExecutionDAO;
import com.entities.dao.reportingtool.ReportFieldDAO;
import com.entities.dao.reportingtool.ReportFieldListDAO;
import com.entities.dao.reportingtool.ReportSemanticDAO;
import com.entities.dao.usermanager.UserControlDAO;
import com.entities.dao.usermanager.UserDAO;
import com.entities.dao.usermanager.UserRolDAO;

/**
 * Class to install configuration in database
 * 
 * @author alberto.olivan
 * 
 */
public class InstallManager {

	private ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(InstallManager.class);

	public static final String typeAll = "ALL";
	public static final String typeAIFMD = "AIFMD";
	public static final String typeDMO = "DMO";

	public InstallManager(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * 
	 * @param type
	 *            : ALL, AIMDF, DMO
	 */
	public void installAll(String type) {

		// user
		InstallUser installUser = new InstallUser(applicationContext);
		installUser.install();
		logger.info("InstallUser completed.");

		// error
		InstallError installError = new InstallError(applicationContext);
		installError.install();
		logger.info("InstallError completed.");

		if (type.equals(typeAll) || type.equals(typeAIFMD)) {

			// Companies and departments
			InstallCompanyUser installCompanies = new InstallCompanyUser(
					applicationContext);
			installCompanies.install();
			logger.info("InstallCompanyUser completed.");

			// reportCatalog and reportFields from AIFM
			InstallAIFM installAIFM = new InstallAIFM(applicationContext);
			installAIFM.install();
			logger.info("InstallAIFM completed.");

			// reportCatalog and reportFields from AIFM
			InstallAIF installAIF = new InstallAIF(applicationContext);
			installAIF.install();
			logger.info("InstallAIF completed.");

			// reportFieldList
			InstallReportList installReportList = new InstallReportList(
					applicationContext);
			installReportList.install();
			logger.info("InstallReportList completed.");
		}

		if (type.equals(typeAll) || type.equals(typeDMO)) {

			// DMO Companies and departments
			InstallCompanyUserDMO installCompanyUserDMO = new InstallCompanyUserDMO(
					applicationContext);
			installCompanyUserDMO.install();
			logger.info("InstallCompanyUserDMO completed.");

			// DMO report
			InstallDMO_DM installDMO_DM = new InstallDMO_DM(applicationContext);
			installDMO_DM.install();
			logger.info("InstallDMO_DM completed.");

			InstallDMO_DN installDMO_DN = new InstallDMO_DN(applicationContext);
			installDMO_DN.install();
			logger.info("InstallDMO_DN completed.");

			InstallDMO_DP installDMO_DP = new InstallDMO_DP(applicationContext);
			installDMO_DP.install();
			logger.info("InstallDMO_DP completed.");

			InstallDMO_FR installDMO_FR = new InstallDMO_FR(applicationContext);
			installDMO_FR.install();
			logger.info("InstallDMO_FR completed.");

			InstallDMO_FRI installDMO_FRI = new InstallDMO_FRI(
					applicationContext);
			installDMO_FRI.install();
			logger.info("InstallDMO_FRI completed.");

			InstallDMO_OI installDMO_OI = new InstallDMO_OI(applicationContext);
			installDMO_OI.install();
			logger.info("InstallDMO_OI completed.");

			// DMO field list
			InstallReportListDMO installReportListDMO = new InstallReportListDMO(
					applicationContext);
			installReportListDMO.install();
			logger.info("InstallReportListDMO completed.");
		}

	}

	/**
	 * Process to delete all data from database
	 * 
	 * @param applicationContext
	 */
	public void deleteAll() {

		UserControlDAO userControlDAO = (UserControlDAO) applicationContext
				.getBean("userControlDAO");
		userControlDAO.deleteAll();

		// spring security intercepts User, this DAO has override function
		UserDAO userDAO = (UserDAO) applicationContext.getBean("userDAO");
		userDAO.deleteAll();

		// spring security intercepts Role, this DAO has override function
		UserRolDAO userRolDAO = (UserRolDAO) applicationContext
				.getBean("userRolDAO");
		userRolDAO.deleteAll();

		ReportSemanticDAO reportSemanticDAO = (ReportSemanticDAO) applicationContext
				.getBean("reportSemanticDAO");
		reportSemanticDAO.deleteAll();

		FileColumListDAO fileColumListDAO = (FileColumListDAO) applicationContext
				.getBean("fileColumListDAO");
		fileColumListDAO.deleteAll();

		ReportErrorDAO reportErrorDAO = (ReportErrorDAO) applicationContext
				.getBean("reportErrorDAO");
		reportErrorDAO.deleteAll();

		ReportDataErrorDAO reportDataErrorDAO = (ReportDataErrorDAO) applicationContext
				.getBean("reportDataErrorDAO");
		reportDataErrorDAO.deleteAll();

		LoadErrorDAO loadErrorDAO = (LoadErrorDAO) applicationContext
				.getBean("loadErrorDAO");
		loadErrorDAO.deleteAll();

		ErrorDAO errorDAO = (ErrorDAO) applicationContext.getBean("errorDAO");
		errorDAO.deleteAll();

		LoadRawDataDAO loadRawDataDAO = (LoadRawDataDAO) applicationContext
				.getBean("loadRawDataDAO");
		loadRawDataDAO.deleteAll();

		LoadRawDAO loadRawDAO = (LoadRawDAO) applicationContext
				.getBean("loadRawDAO");
		loadRawDAO.deleteAll();

		ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
				.getBean("reportDataDAO");
		reportDataDAO.deleteAll();

		FileColumDAO fileColumDAO = (FileColumDAO) applicationContext
				.getBean("fileColumDAO");
		fileColumDAO.deleteAll();

		ReportFieldListDAO reportFieldListDAO = (ReportFieldListDAO) applicationContext
				.getBean("reportFieldListDAO");
		reportFieldListDAO.deleteAll();

		ReportFieldDAO reportFieldDAO = (ReportFieldDAO) applicationContext
				.getBean("reportFieldDAO");
		reportFieldDAO.deleteAll();

		ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
				.getBean("reportExecutionDAO");
		reportExecutionDAO.deleteAll();

		LoadFileDAO loadFileDAO = (LoadFileDAO) applicationContext
				.getBean("loadFileDAO");
		loadFileDAO.deleteAll();

		FileConfigDAO fileConfigDAO = (FileConfigDAO) applicationContext
				.getBean("fileConfigDAO");
		fileConfigDAO.deleteAll();

		ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) applicationContext
				.getBean("reportCatalogDAO");
		reportCatalogDAO.deleteAll();

		FundGroupDAO fundGroupDAO = (FundGroupDAO) applicationContext
				.getBean("fundGroupDAO");
		fundGroupDAO.deleteAll();

		FundDAO fundDAO = (FundDAO) applicationContext.getBean("fundDAO");
		fundDAO.deleteAll();

		DepartmentDAO departmentDAO = (DepartmentDAO) applicationContext
				.getBean("departmentDAO");
		departmentDAO.deleteAll();

		CompanyDAO companyDAO = (CompanyDAO) applicationContext
				.getBean("companyDAO");
		companyDAO.deleteAll();

	}

	/**
	 * Function used after generating database schema, to reset all ids.
	 */
	public void resetId() {

		// load properties file to know what type of database uses
		try {

			InputStream input = null;

			// FIXME no funciona la ruta del WEB-INF/db.properties

			Properties prop = new Properties();
			// String filename = "/src/main/webapp/WEB-INF/db.properties";
			String filename = "/WEB-INF/db.properties";
			input = InstallManager.class.getClassLoader().getResourceAsStream(
					filename);
			if (input == null) {
				logger.error("Database config file unable to find: " + filename);
				return;
			}
			// load a properties file from class path, inside static method
			prop.load(input);
			String className = prop.getProperty("db.driverClassName");
			logger.info("className: " + className);

			// MYSQL -> AUTO_INCREMENT
			if (className.contains("mysql22")) {
				List<String> alters = new ArrayList<String>();
				alters.add("ALTER TABLE T_COMPANY AUTO_INCREMENT  = 1 ");
				alters.add("ALTER TABLE T_DEPARTMENT AUTO_INCREMENT  = 1 ");
				alters.add("ALTER TABLE T_ERROR AUTO_INCREMENT  = 1 ");
				alters.add("ALTER TABLE T_FILE_ASSIG_EXECUTION AUTO_INCREMENT = 1 ");
				alters.add("ALTER TABLE T_FILE_COLUM AUTO_INCREMENT  = 1 ");
				alters.add("ALTER TABLE T_FILE_COLUM_LIST AUTO_INCREMENT  = 1 ");
				alters.add("ALTER TABLE T_FILE_CONFIG AUTO_INCREMENT  = 1 ");
				alters.add("ALTER TABLE T_FUND AUTO_INCREMENT  = 1 ");
				alters.add("ALTER TABLE T_FUND_GROUP AUTO_INCREMENT = 1 ");
				alters.add("ALTER TABLE T_LOAD_ERROR AUTO_INCREMENT = 1 ");
				alters.add("ALTER TABLE T_LOAD_FILE AUTO_INCREMENT = 1 ");
				alters.add("ALTER TABLE T_LOAD_RAW AUTO_INCREMENT = 1 ");
				alters.add("ALTER TABLE T_LOAD_RAW_DATA AUTO_INCREMENT  = 1 ");
				alters.add("ALTER TABLE T_REPORT_CATALOG AUTO_INCREMENT  = 1 ");
				alters.add("ALTER TABLE T_REPORT_CUSTOM AUTO_INCREMENT = 1 ");
				alters.add("ALTER TABLE T_REPORT_DATA AUTO_INCREMENT  = 1 ");
				alters.add("ALTER TABLE T_REPORT_DATA_ERROR AUTO_INCREMENT = 1 ");
				alters.add("ALTER TABLE T_REPORT_DATA_LONG AUTO_INCREMENT = 1 ");
				alters.add("ALTER TABLE T_REPORT_ERROR AUTO_INCREMENT = 1 ");
				alters.add("ALTER TABLE T_REPORT_EXECUTION AUTO_INCREMENT = 1 ");
				alters.add("ALTER TABLE T_REPORT_FIELD AUTO_INCREMENT = 1 ");
				alters.add("ALTER TABLE T_REPORT_FIELD_LIST AUTO_INCREMENT = 1 ");
				alters.add("ALTER TABLE T_REPORT_GROUP AUTO_INCREMENT = 1 ");
				alters.add("ALTER TABLE T_REPORT_SEMANTIC AUTO_INCREMENT = 1 ");
				alters.add("ALTER TABLE T_USER_CONTROL AUTO_INCREMENT = 1 ");
				// ALTER TABLE T_ROLE AUTO_INCREMENT = 1;
				// ALTER TABLE T_USER AUTO_INCREMENT = 1;
				// ALTER TABLE T_USER_ROLE AUTO_INCREMENT = 1;

				UserRolDAO userRolDAO = (UserRolDAO) applicationContext
						.getBean("userRolDAO");
				// send a list of SQL to executed, careful with this method
				userRolDAO.insertUserRoles(alters);

			}

			// ORACLE -> SEQUENCES
			if (className.contains("oracle")) {
				// not necessary, sequences are objects witch are destroyed
			}

		} catch (Exception e) {
			logger.error("Error in resetId " + e.getMessage());
		}
	}
}
