package com.reportingtool.creation;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.entities.entity.reportingtool.ReportExecution;

/**
 * Class to load a XML file and convert to reportExecution
 * 
 * @author alberto.olivan
 * 
 */
public class LoadXML {

	private ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory.getLogger(LoadXML.class);

	public LoadXML(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	// http://java.dzone.com/articles/using-jaxb-generate-java

	/**
	 * Function receive a File and AIF reportExecution, read this File to add
	 * reportDatas from XML file
	 * 
	 * @param file
	 * @param reportExecution
	 * @param update
	 *            : true -> update or insert / false -> only insert
	 * @return reportExecution with reportDatas from file
	 */
	public ReportExecution loadAIFFile(File file,
			ReportExecution reportExecution, boolean update) throws Exception {

		LoadXMLAIF load = new LoadXMLAIF(applicationContext);

		return load.loadAIFFile(file, reportExecution, update);
	}

	/**
	 * Function receive a File and AIFM reportExecution, read this File to add
	 * reportDatas from XML file
	 * 
	 * @param file
	 * @param reportExecution
	 * @param update
	 *            : true -> update or insert / false -> only insert
	 * @return reportExecution with reportDatas from file
	 */
	public ReportExecution loadAIFMFile(File file,
			ReportExecution reportExecution, boolean update) throws Exception {

		LoadXMLAIFM load = new LoadXMLAIFM(applicationContext);

		return load.loadAIFMFile(file, reportExecution, update);
	}
}
