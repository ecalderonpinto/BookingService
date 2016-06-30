package com.reportingtool.creation;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;

import com.entities.dictionary.ErrorTypeEnum;
import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportExecution;
import com.reportingtool.controllers.forms.GenerateXMLForm;
import com.reportingtool.utilities.ReportingErrorManager;
import com.reportingtool.xml.dmo.Declaracion;
import com.reportingtool.xml.dmo.DeclaracionMovimientos;
import com.reportingtool.xml.dmo.ObjectFactoryDMO;
import com.reportingtool.xml.dmo.Operacion2325;
import com.reportingtool.xml.dmo.Operacion26Y27;
import com.reportingtool.xml.dmo.Operacion28Y29;
import com.reportingtool.xml.dmo.Operacion3035;
import com.reportingtool.xml.dmo.TIPODECLENUM;

/**
 * Class to generate XML string from a reportExecution type DMO_DM
 * 
 * @author Alberto
 * 
 */
public class GeneratorXMLDMO_DM {

	/**
	 * Function generate a DMO_XML from a reportExecution with XSD classes and
	 * validate it. Create reportErrores
	 * 
	 * @param reportExecution
	 * @return DMO_XML string
	 */
	public static GenerateXMLForm generateXMLDMO(
			ReportExecution reportExecution,
			ApplicationContext applicationContext, Logger logger,
			String dmoXSDResource) {

		logger.info("GeneratorXML: starting XML generation ");

		// all dataFields
		List<ReportData> reportDatas = new ArrayList<ReportData>(
				reportExecution.getReportDatas());

		for (ReportData reportData : reportDatas) {
			logger.info(reportData.getReportField().getReportFieldName() + "("
					+ reportData.getReportField().getReportFieldNum() + "): "
					+ reportData.getReportDataText());
		}

		// ///////////////////////////////////////////////////////////
		// TODO:RT ONLY STATUS = PENDING WILL CREATE XML REPORTS
		// if (aifmdReportResult.getAifmdReportResultStat().equals("PENDING")) {
		// }

		try {

			// ObjectFactory to make every class to DMO report
			ObjectFactoryDMO objectFactory = new ObjectFactoryDMO();

			// main object of DMO report
			Declaracion declaracion = objectFactory.createDeclaracion();

			DeclaracionMovimientos declaracionMovimientos = objectFactory
					.createDeclaracionMovimientos();

			String id = "1";

			// /////////////////////////////////////////////////////////////////
			// DeclaracionMovimientos

			DeclaracionMovimientos.DatosEntrada datosEntrada = objectFactory
					.createDeclaracionMovimientosDatosEntrada();

			datosEntrada.setCIFNIFEntidad("");
			datosEntrada.setFechaDeclaracion("");
			datosEntrada.setMesDeclaracion("");
			datosEntrada.setNombreEntidad("");
			datosEntrada.setRepresentante("");

			// TODO fill datosEntrada

			DeclaracionMovimientos.Operaciones operaciones = objectFactory
					.createDeclaracionMovimientosOperaciones();

			List<Operacion2325> operacion2325List = operaciones
					.getOperacion2325();

			List<Operacion26Y27> operacion26Y27List = operaciones
					.getOperacion26Y27();

			List<Operacion28Y29> operacion28Y29List = operaciones
					.getOperacion28Y29();

			List<Operacion3035> operacion3035List = operaciones
					.getOperacion3035();

			// TODO fill operaciones

			// setters of DeclaracionMovimientos
			declaracionMovimientos.setDatosEntrada(datosEntrada);
			declaracionMovimientos.setID(id);
			declaracionMovimientos.setOperaciones(operaciones);
			declaracionMovimientos.setTYPE(TIPODECLENUM.MOVIMIENTO);

			// final set to Declaracion
			declaracion.setDeclaracionMovimientos(declaracionMovimientos);

			// GENERATING XML WITH JAXB

			// http://www.tutorialspoint.com/java/xml/javax_xml_bind_jaxbelement.htm

			// create JAXBElement of type AIFMReportingInfo
			JAXBElement<Declaracion> jaxbElement = new JAXBElement(new QName(
					Declaracion.class.getSimpleName()), Declaracion.class,
					declaracion);

			// create JAXBContext which will be used to update writer
			JAXBContext context = JAXBContext
					.newInstance(Declaracion.class);

			// Create a String writer object which will be
			// used to write jaxbElment XML to string
			// StringWriter writer = new StringWriter();
			// marshall or convert jaxbElement
			// context.createMarshaller().marshal(jaxbElement, writer);
			// print XML string
			// logger.debug(writer.toString());

			// http://blog.sanaulla.info/2010/08/29/using-jaxb-to-generate-xml-from-the-java-xsd/
			// print nice in XML
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
			marshaller.marshal(jaxbElement, System.out);

			StringWriter st = new StringWriter();
			marshaller.marshal(jaxbElement, st);

			GenerateXMLForm result = new GenerateXMLForm();
			result.setOutputXML(st.toString());
			result.setReportExecution(reportExecution);
			result.setValid(GeneratorXMLUtils.validateSchemaXSD(st.toString(),
					reportExecution, dmoXSDResource, applicationContext,
					logger));

			return result;

			// // need a styler.xsl to transform XML to HTML
			// StringReader reader = new StringReader(st.toString());
			// StringWriter writer = new StringWriter();
			// TransformerFactory tFactory = TransformerFactory.newInstance();
			// Transformer transformer = tFactory.newTransformer(
			// new javax.xml.transform.stream.StreamSource("styler.xsl"));
			// transformer.transform(
			// new javax.xml.transform.stream.StreamSource(reader),
			// new javax.xml.transform.stream.StreamResult(writer));
			// String result = writer.toString();
			// return result;

		} catch (JAXBException e) {
			logger.error("Error in JAXB XML: " + e.getMessage());
			ReportingErrorManager.createReportError(applicationContext,
					ErrorTypeEnum.GENERATION.getErrorType(), reportExecution,
					"FAIL", "Error in JAXB XML: " + e.getMessage());
			// } catch (ParseException e) {
			// logger.error("Error when parsing XML: " + e.getMessage());
			// ReportingErrorManager.createReportError(applicationContext,
			// ErrorTypeEnum.GENERATION.getErrorType(), reportExecution,
			// "FAIL", "Error when parsing XML: " + e.getMessage());
		} catch (NumberFormatException e) {
			logger.error("Number format error in XML process: "
					+ e.getMessage());
			ReportingErrorManager.createReportError(applicationContext,
					ErrorTypeEnum.GENERATION.getErrorType(), reportExecution,
					"FAIL",
					"Number format error in XML process: " + e.getMessage());
		} catch (Exception e) {
			logger.error("General error in XML process: " + e.getMessage());
			ReportingErrorManager.createReportError(applicationContext,
					ErrorTypeEnum.GENERATION.getErrorType(), reportExecution,
					"FAIL", "General error in XML process: " + e.getMessage());
		}

		return null;
	}

}
