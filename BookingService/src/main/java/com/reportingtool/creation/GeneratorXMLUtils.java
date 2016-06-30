package com.reportingtool.creation;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.xml.sax.SAXException;

import com.entities.dictionary.ErrorTypeEnum;
import com.entities.entity.reportingtool.ReportExecution;
import com.reportingtool.utilities.ReportingErrorManager;

/**
 * Class with utilities for XML files
 * 
 * @author Alberto
 * 
 */
public class GeneratorXMLUtils {
	
	/**
	 * Validate a aifmdXML string with his XSD schema, create reportErrors in
	 * reportExecution. Return true if XSD is valid, false otherwise
	 * 
	 * @param aifmdXML
	 * @param reportExecution
	 * @param xsdResource
	 * @return boolean
	 */
	public static boolean validateSchemaXSD(String aifmdXML,
			ReportExecution reportExecution, String xsdResource,
			ApplicationContext applicationContext, Logger logger) {

		boolean result = false;

		// http://stackoverflow.com/questions/15732/whats-the-best-way-to-validate-an-xml-file-against-an-xsd-file

		Source xmlFile = new StreamSource(new StringReader(aifmdXML));

		try {
			ClassLoader classLoader = GeneratorXMLUtils.class.getClassLoader();
			File schemaFile = new File(classLoader.getResource(xsdResource)
					.getFile());

			// first disable old errors
			ReportingErrorManager.disableReportError(applicationContext,
					ErrorTypeEnum.GENERATION.getErrorType(), reportExecution,
					"FAIL");
			ReportingErrorManager.disableReportError(applicationContext,
					ErrorTypeEnum.GENERATION.getErrorType(), reportExecution,
					"XML Incomplete");

			SchemaFactory schemaFactory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			
			Schema schema = schemaFactory.newSchema(schemaFile);
			Validator validator = schema.newValidator();

			validator.validate(xmlFile);

			logger.info("CREATION - XML is valid.");
			result = true;

			ReportingErrorManager.disableReportError(applicationContext,
					ErrorTypeEnum.GENERATION.getErrorType(), reportExecution,
					"FAIL");
			ReportingErrorManager.disableReportError(applicationContext,
					ErrorTypeEnum.GENERATION.getErrorType(), reportExecution,
					"XML Incomplete");

		} catch (SAXException e) {
			logger.info("CREATION - XML NOT is valid: "
					+ e.getLocalizedMessage());

			ReportingErrorManager.createReportError(applicationContext,
					ErrorTypeEnum.GENERATION.getErrorType(), reportExecution,
					"XML Incomplete", "Validating process detect some issues: "
							+ e.getLocalizedMessage());
		} catch (Exception e) {
			logger.error("Error validating XML: " + e.getLocalizedMessage());

			ReportingErrorManager.createReportError(applicationContext,
					ErrorTypeEnum.GENERATION.getErrorType(), reportExecution,
					"FAIL", "Error validating XML " + e.getLocalizedMessage());
		}

		return result;
	}

	/**
	 * Validate a aifmdXML string if is a XML well formed format. Return true if
	 * it is XML, false otherwise.
	 * 
	 * @param aifmdXML
	 * @return boolean
	 */
	public static boolean validateXMLWellFormed(File file, Logger logger) {
		boolean result = false;

		// http://www.edankert.com/validate.html

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setValidating(false);
			factory.setNamespaceAware(true);

			DocumentBuilder builder = factory.newDocumentBuilder();
			builder.setErrorHandler(new SimpleErrorHandler());

			builder.parse(file);

			// is well formed
			result = true;

		} catch (ParserConfigurationException e) {
			logger.info("Parser error when validating XML is not well formed. "
					+ e.getMessage());

		} catch (SAXException e) {
			logger.info("SAX error when validating XML is not well formed. "
					+ e.getMessage());

		} catch (IOException e) {
			logger.error("IO error when validating XML is not well formed. "
					+ e.getMessage());

		}

		return result;
	}
}
