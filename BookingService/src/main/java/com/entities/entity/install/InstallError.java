package com.entities.entity.install;

import org.springframework.context.ApplicationContext;

import com.entities.dao.common.ErrorDAO;
import com.entities.dictionary.ErrorTypeEnum;
import com.entities.entity.common.Error;
import com.entities.utilities.hibernate.VersionAuditor;

public class InstallError implements InstallAdapter {

	private ApplicationContext applicationContext;

	public InstallError(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void install() {
		VersionAuditor versionAdmin = new VersionAuditor("admin");

		Error error1 = new Error(ErrorTypeEnum.LOADER.getErrorType(),
				"Error load", "1", "Error in load file", "Reload file",
				versionAdmin);
		Error error2 = new Error(ErrorTypeEnum.CONTROLLER.getErrorType(),
				"Error controller", "1", "Error in servlet", "Reload page",
				versionAdmin);
		Error error3 = new Error(ErrorTypeEnum.VALIDATOR.getErrorType(),
				"Error validator", "1", "Error in validation", "Check fields",
				versionAdmin);
		Error error4 = new Error(ErrorTypeEnum.FORMAT.getErrorType(),
				"Error normalizer", "1", "Error in normalize process",
				"Reload file", versionAdmin);
		Error error5 = new Error(ErrorTypeEnum.GENERATION.getErrorType(),
				"Error creation", "1", "Error in creation", "Check conditions",
				versionAdmin);
		Error error6 = new Error(ErrorTypeEnum.SYNTAXIS.getErrorType(),
				"Error syntaxis", "1", "Error in syntaxis", "Check format",
				versionAdmin);
		Error error7 = new Error(ErrorTypeEnum.SEMANTIC.getErrorType(),
				"Error semantic", "1", "Error in semantic", "Check rules",
				versionAdmin);
		Error error8 = new Error(ErrorTypeEnum.TRANSLATE.getErrorType(),
				"Error translate", "1", "Error in load file", "Reload file",
				versionAdmin);
		Error error9 = new Error(ErrorTypeEnum.STATUS.getErrorType(),
				"Error status", "1", "Error in status checker", "Check report",
				versionAdmin);
		Error error10 = new Error(ErrorTypeEnum.REPORTING.getErrorType(),
				"Error report", "1", "Error in report", "Check report",
				versionAdmin);

		ErrorDAO errorDAO = (ErrorDAO) applicationContext.getBean("errorDAO");
		errorDAO.create(error1);
		errorDAO.create(error2);
		errorDAO.create(error3);
		errorDAO.create(error4);
		errorDAO.create(error5);
		errorDAO.create(error6);
		errorDAO.create(error7);
		errorDAO.create(error8);
		errorDAO.create(error9);
		errorDAO.create(error10);
	}
}
