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
import com.reportingtool.utilities.ReportUtilities;
import com.reportingtool.utilities.ReportingErrorManager;
import com.reportingtool.xml.dmo.Declaracion;
import com.reportingtool.xml.dmo.DeclaracionNegativa;
import com.reportingtool.xml.dmo.ObjectFactoryDMO;
import com.reportingtool.xml.dmo.TIPODECLENUM;

/**
 * Class to generate XML string from a reportExecution type DMO_DN
 * 
 * @author Alberto
 * 
 */
public class GeneratorXMLDMO_DN {

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

			DeclaracionNegativa declaracionNegativa = objectFactory
					.createDeclaracionNegativa();

			// (1) <ID>
			if (ReportUtilities.searchData(reportDatas, "ID", "1", null) != null)
				declaracionNegativa.setID(ReportUtilities.searchData(
						reportDatas, "ID", "1", null));

			// /////////////////////////////////////////////////////////////////
			// DeclaracionNegativa

			DeclaracionNegativa.DatosEntrada datosEntrada = objectFactory
					.createDeclaracionNegativaDatosEntrada();

			// (2) <NombreEntidad>
			if (ReportUtilities.searchData(reportDatas, "NombreEntidad", "2",
					null) != null)
				datosEntrada.setNombreEntidad(ReportUtilities.searchData(
						reportDatas, "NombreEntidad", "2", null));

			// (3) <CIFNIFEntidad>
			if (ReportUtilities.searchData(reportDatas, "CIFNIFEntidad", "3",
					null) != null)
				datosEntrada.setCIFNIFEntidad(ReportUtilities.searchData(
						reportDatas, "CIFNIFEntidad", "3", null));

			// (5) <Representante>
			if (ReportUtilities.searchData(reportDatas, "Representante", "5",
					null) != null)
				datosEntrada.setRepresentante(ReportUtilities.searchData(
						reportDatas, "Representante", "5", null));

			// (6) <FechaDeclaracion>
			if (ReportUtilities.searchData(reportDatas, "FechaDeclaracion",
					"6", null) != null)
				datosEntrada.setFechaDeclaracion(ReportUtilities.searchData(
						reportDatas, "FechaDeclaracion", "6", null));

			DeclaracionNegativa.Periodo periodo = objectFactory
					.createDeclaracionNegativaPeriodo();

			// (7) <FechaDesde>
			if (ReportUtilities
					.searchData(reportDatas, "FechaDesde", "7", null) != null)
				periodo.setFechaDesde(ReportUtilities.searchData(reportDatas,
						"FechaDesde", "7", null));

			// (8) <FechaHasta>
			if (ReportUtilities
					.searchData(reportDatas, "FechaHasta", "8", null) != null)
				periodo.setFechaHasta(ReportUtilities.searchData(reportDatas,
						"FechaHasta", "8", null));

			// setters of DeclaracionNegativa
			declaracionNegativa.setDatosEntrada(datosEntrada);
			declaracionNegativa.setOperaciones(null);
			declaracionNegativa.setPeriodo(periodo);
			declaracionNegativa.setTYPE(TIPODECLENUM.NEGATIVA);

			// final set to Declaracion
			declaracion.setDeclaracionNegativa(declaracionNegativa);

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
