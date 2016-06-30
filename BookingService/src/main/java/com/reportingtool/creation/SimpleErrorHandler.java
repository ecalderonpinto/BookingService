package com.reportingtool.creation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.reportingtool.controllers.DownloadController;

/**
 * Class from http://www.edankert.com/validate.html Used in
 * GeneratorXML.validateXMLWellFormed()
 * 
 * @author alberto.olivan
 *
 */
public class SimpleErrorHandler implements ErrorHandler {

	private static final Logger logger = LoggerFactory
			.getLogger(DownloadController.class);
	
	public void warning(SAXParseException e) throws SAXException {
		logger.warn(e.getMessage());
	}

	public void error(SAXParseException e) throws SAXException {
		logger.error(e.getMessage());
	}

	public void fatalError(SAXParseException e) throws SAXException {
		logger.error(e.getMessage());
	}

}
