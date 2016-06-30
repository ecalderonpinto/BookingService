package com.reportingtool.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//import com.entities.entity.install.InstallManager;
import com.reportingtool.test.TestValidator;

@Controller
@RequestMapping(value = "/")
public class TestController {

	//////////////
	// OBSOLETE //
	//////////////
	
	@Autowired
	ApplicationContext applicationContext;

	@RequestMapping(value = "test.do", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {

		// TO DO
		TestValidator testValidator = new TestValidator();
		testValidator.process(applicationContext);

		System.out.println("Test");
		return "test";
	}

	@RequestMapping(value = "install.do", method = RequestMethod.GET)
	public String install(Locale locale, Model model) {

//		// TO DO
//		InstallManager installLoader = new InstallManager();
//		// installLoader.installTest(applicationContext);
//		installLoader.deleteEntities(applicationContext);
//		installLoader.installEntitiesFull(applicationContext);
//		installLoader.installAIF(applicationContext);

		System.out.println("Installed");
		return "install";
	}

	@RequestMapping(value = "loader.do", method = RequestMethod.GET)
	public String loader(Locale locale, Model model) {

			/*			
			if (sfb.isRunning())
				System.out.println("Running");
			else
				System.out.println("No");
				*/

		return "install";
	}
}
