package com.reportingtool.creation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.entities.entity.reportingtool.ReportExecution;

/**
 * Class to generate PDF files from reportExecution
 * 
 * @author alberto.olivan
 *
 */
public class GeneratorPDF {

	private ApplicationContext applicationContext;
	
	private static final Logger logger = LoggerFactory
			.getLogger(GeneratorPDF.class);

	/**
	 * Constructor of GeneratorPDF with an applicationContext
	 * 
	 * @param applicationContext
	 */
	public GeneratorPDF(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * Main function that generate a PDF report with a reportExecution
	 * 
	 * @param reportExecution
	 * @return PDF format of reportExecution
	 */
	public String generatePDF(ReportExecution reportExecution) {

		String result = "";

		// TODO:RT function empty, need iText libraries from maven
		// and see this example
		// http://stackoverflow.com/questions/6283336/generate-pdf-in-java-from-jsp

		return result;
	}
}
