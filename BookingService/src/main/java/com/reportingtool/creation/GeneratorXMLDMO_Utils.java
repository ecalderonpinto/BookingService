package com.reportingtool.creation;

import java.math.BigDecimal;
import java.util.List;

import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportExecution;
import com.reportingtool.utilities.ReportUtilities;
import com.reportingtool.xml.dmo.CABECERAOP;
import com.reportingtool.xml.dmo.CABECERAOP.Caracteristicas;
import com.reportingtool.xml.dmo.DOCIDENTIFPF;
import com.reportingtool.xml.dmo.DOCIDENTIFPJ;
import com.reportingtool.xml.dmo.DOMICILIO;
import com.reportingtool.xml.dmo.PERSONAFIS;
import com.reportingtool.xml.dmo.PERSONAJUR;

/**
 * Class with utilities to generate DMO.XML files
 * 
 * @author alberto.olivan
 * 
 */
public class GeneratorXMLDMO_Utils {

	/***************************************************************/
	/** NOTES: all ObjectFactoryDMO factories created from .XSD */
	/** has to implements ObjectFactoryDMOAdapter to work properly */
	/***************************************************************/

	/**
	 * Enter a empty CABECERAOP and reportExecution and fill it.
	 * 
	 * @param objectFactory
	 * @param cabeceraop
	 * @param block
	 * @param reportExecution
	 * @param n
	 *            : number of field which start to count
	 * @return CABECERAOP
	 */
	public static CABECERAOP makeCabeceraopDMO(
			ObjectFactoryDMOAdapter objectFactory, CABECERAOP cabeceraop,
			String block, ReportExecution reportExecution, Integer n) {

		List<ReportData> reportDatas = reportExecution.getReportDatas();

		// (9+n) <NumOperacion>
		if (ReportUtilities.searchData(reportDatas, "NumOperacion",
				Integer.toString(9 + n), block) != null)
			try {
				cabeceraop.setNumOperacion(new Integer(ReportUtilities
						.searchData(reportDatas, "NumOperacion",
								Integer.toString(9 + n), block)));
			} catch (NumberFormatException e) {
			}

		// (10+n) <TipoOperacion>
		if (ReportUtilities.searchData(reportDatas, "TipoOperacion",
				Integer.toString(10 + n), block) != null)
			try {
				cabeceraop.setTipoOperacion(new Integer(ReportUtilities
						.searchData(reportDatas, "TipoOperacion",
								Integer.toString(10 + n), block)));
			} catch (NumberFormatException e) {
			}

		// (11+n) <Caracteristica>
		Caracteristicas caracteristicas = objectFactory
				.createCABECERAOPCaracteristicas();
		List<Integer> caracteristicasList = caracteristicas.getCaracteristica();
		// consider only one occurrence here
		if (ReportUtilities.searchData(reportDatas, "Caracteristica",
				Integer.toString(11 + n), block) != null)
			try {
				caracteristicasList.add(new Integer(ReportUtilities.searchData(
						reportDatas, "Caracteristica",
						Integer.toString(11 + n), block)));
			} catch (NumberFormatException e) {
			}
		cabeceraop.setCaracteristicas(caracteristicas);

		// (12+n) <FechaOperacion>
		if (ReportUtilities.searchData(reportDatas, "FechaOperacion",
				Integer.toString(12 + n), block) != null)
			cabeceraop.setFechaOperacion(ReportUtilities.searchData(
					reportDatas, "FechaOperacion", Integer.toString(12 + n),
					block));

		// (13+n) <HoraOperacion>
		if (ReportUtilities.searchData(reportDatas, "HoraOperacion",
				Integer.toString(13 + n), block) != null)
			cabeceraop.setHoraOperacion(ReportUtilities.searchData(reportDatas,
					"HoraOperacion", Integer.toString(13 + n), block));

		// (14+n) <Importe>
		if (ReportUtilities.searchData(reportDatas, "Importe",
				Integer.toString(14 + n), block) != null)
			try {
				cabeceraop.setImporte(new BigDecimal(ReportUtilities
						.searchData(reportDatas, "Importe",
								Integer.toString(14 + n), block)));
			} catch (NumberFormatException e) {
			}

		// (15+n) <Divisa>
		if (ReportUtilities.searchData(reportDatas, "Divisa",
				Integer.toString(15 + n), block) != null)
			try {
				cabeceraop
						.setDivisa(new Integer(ReportUtilities.searchData(
								reportDatas, "Divisa",
								Integer.toString(15 + n), block)));
			} catch (NumberFormatException e) {
			}

		// (16+n) <ImporteEnEuros>
		if (ReportUtilities.searchData(reportDatas, "ImporteEnEuros",
				Integer.toString(16 + n), block) != null)
			try {
				cabeceraop.setImporteEnEuros(new BigDecimal(ReportUtilities
						.searchData(reportDatas, "ImporteEnEuros",
								Integer.toString(16 + n), block)));
			} catch (NumberFormatException e) {
			}

		// (17+n) <PaisOrigen>
		if (ReportUtilities.searchData(reportDatas, "PaisOrigen",
				Integer.toString(17 + n), block) != null)
			cabeceraop.setPaisOrigen(ReportUtilities.searchData(reportDatas,
					"PaisOrigen", Integer.toString(17 + n), block));

		// (18+n) <ProvinciaOrigen>
		if (ReportUtilities.searchData(reportDatas, "ProvinciaOrigen",
				Integer.toString(18 + n), block) != null)
			try {
				cabeceraop.setProvinciaOrigen(new Integer(ReportUtilities
						.searchData(reportDatas, "ProvinciaOrigen",
								Integer.toString(18 + n), block)));
			} catch (NumberFormatException e) {
			}

		// (19+n) <ProvinciaOrigenEx>
		if (cabeceraop.getProvinciaOrigen() == null)
			if (ReportUtilities.searchData(reportDatas, "ProvinciaOrigenEx",
					Integer.toString(19 + n), block) != null)
				cabeceraop.setProvinciaOrigenEx(ReportUtilities.searchData(
						reportDatas, "ProvinciaOrigenEx",
						Integer.toString(19 + n), block));

		// (20+n) <MunicipioOrigen>
		if (ReportUtilities.searchData(reportDatas, "MunicipioOrigen",
				Integer.toString(20 + n), block) != null)
			cabeceraop.setMunicipioOrigen(ReportUtilities.searchData(
					reportDatas, "MunicipioOrigen", Integer.toString(20 + n),
					block));

		// (21+n) <MunicipioOrigenEx>
		if (cabeceraop.getMunicipioOrigen() == null)
			if (ReportUtilities.searchData(reportDatas, "MunicipioOrigenEx",
					Integer.toString(21 + n), block) != null)
				cabeceraop.setMunicipioOrigenEx(ReportUtilities.searchData(
						reportDatas, "MunicipioOrigenEx",
						Integer.toString(21 + n), block));

		// (22+n) <PaisDestino>
		if (ReportUtilities.searchData(reportDatas, "PaisDestino",
				Integer.toString(22 + n), block) != null)
			cabeceraop.setPaisDestino(ReportUtilities.searchData(reportDatas,
					"PaisDestino", Integer.toString(22 + n), block));

		// (23+n) <ProvinciaDestino>
		if (ReportUtilities.searchData(reportDatas, "ProvinciaDestino",
				Integer.toString(23 + n), block) != null)
			try {
				cabeceraop.setProvinciaDestino(new Integer(ReportUtilities
						.searchData(reportDatas, "ProvinciaDestino",
								Integer.toString(23 + n), block)));
			} catch (NumberFormatException e) {
			}

		// (24+n) <ProvinciaDestinoEx>
		if (cabeceraop.getProvinciaDestino() == null)
			if (ReportUtilities.searchData(reportDatas, "ProvinciaDestinoEx",
					Integer.toString(24 + n), block) != null)
				cabeceraop.setProvinciaDestinoEx(ReportUtilities.searchData(
						reportDatas, "ProvinciaDestinoEx",
						Integer.toString(24 + n), block));

		// (25+n) <MunicipioDestino>
		if (ReportUtilities.searchData(reportDatas, "MunicipioDestino",
				Integer.toString(25 + n), block) != null)
			cabeceraop.setMunicipioDestino(ReportUtilities.searchData(
					reportDatas, "MunicipioDestino", Integer.toString(25 + n),
					block));

		// (26+n) <MunicipioDestinoEx>
		if (cabeceraop.getMunicipioDestino() == null)
			if (ReportUtilities.searchData(reportDatas, "MunicipioDestinoEx",
					Integer.toString(26 + n), block) != null)
				cabeceraop.setMunicipioDestinoEx(ReportUtilities.searchData(
						reportDatas, "MunicipioDestinoEx",
						Integer.toString(26 + n), block));

		// (27+n) <Oficina>
		if (ReportUtilities.searchData(reportDatas, "Oficina",
				Integer.toString(27 + n), block) != null)
			cabeceraop.setOficina(ReportUtilities.searchData(reportDatas,
					"Oficina", Integer.toString(27 + n), block));

		// (28+n) <NumeroCuenta>
		if (ReportUtilities.searchData(reportDatas, "NumeroCuenta",
				Integer.toString(28 + n), block) != null)
			cabeceraop.setNumeroCuenta(ReportUtilities.searchData(reportDatas,
					"NumeroCuenta", Integer.toString(28 + n), block));

		if (isEmptyCABECERAOP(cabeceraop))
			cabeceraop = null;

		return cabeceraop;
	}

	/**
	 * Enter a empty PERSONAFIS and reportExecution and fill it.
	 * 
	 * @param objectFactory
	 * @param personafis
	 * @param block
	 * @param reportExecution
	 * @param n
	 *            : number of field which start to count
	 * @param prefix
	 *            : prefix of fieldName
	 * @return PERSONAFIS
	 */
	public static PERSONAFIS makePersonaFisDMO(
			ObjectFactoryDMOAdapter objectFactory, PERSONAFIS personafis,
			String block, ReportExecution reportExecution, Integer n,
			String prefix) {

		List<ReportData> reportDatas = reportExecution.getReportDatas();

		// (1+n) <prefix_CDPERSONA>
		if (ReportUtilities.searchData(reportDatas, prefix + "CDPERSONA",
				Integer.toString(1 + n), block) != null)
			personafis.setCDPERSONA(ReportUtilities.searchData(reportDatas,
					prefix + "CDPERSONA", Integer.toString(1 + n), block));

		// (2+n) <prefix_Nombre>
		if (ReportUtilities.searchData(reportDatas, prefix + "Nombre",
				Integer.toString(2 + n), block) != null)
			personafis.setNombre(ReportUtilities.searchData(reportDatas, prefix
					+ "Nombre", Integer.toString(2 + n), block));

		// (3+n) <prefix_PrimerApellido>
		if (ReportUtilities.searchData(reportDatas, prefix + "PrimerApellido",
				Integer.toString(3 + n), block) != null)
			personafis.setPrimerApellido(ReportUtilities.searchData(
					reportDatas, prefix + "PrimerApellido",
					Integer.toString(3 + n), block));

		// (4+n) <prefix_SegundoApellido>
		if (ReportUtilities.searchData(reportDatas, prefix + "SegundoApellido",
				Integer.toString(4 + n), block) != null)
			personafis.setSegundoApellido(ReportUtilities.searchData(
					reportDatas, prefix + "SegundoApellido",
					Integer.toString(4 + n), block));

		// (5+n) <prefix_Pais>
		if (ReportUtilities.searchData(reportDatas, prefix + "Pais",
				Integer.toString(5 + n), block) != null)
			personafis.setPais(ReportUtilities.searchData(reportDatas, prefix
					+ "Pais", Integer.toString(5 + n), block));

		// PERSONAFIS.DocIndetifsPF
		PERSONAFIS.DocIdentifsPF docIndetifsPF = objectFactory
				.createPERSONAFISDocIdentifsPF();
		List<PERSONAFIS.DocIdentifsPF.DocIdentifPF> docIndetifsPFList = docIndetifsPF
				.getDocIdentifPF();
		PERSONAFIS.DocIdentifsPF.DocIdentifPF docIdentifPF = objectFactory
				.createPERSONAFISDocIdentifsPFDocIdentifPF();
		// consider only one occurrence

		// (6+n) <prefix_PaisExpedicion>
		if (ReportUtilities.searchData(reportDatas, prefix + "PaisExpedicion",
				Integer.toString(6 + n), block) != null)
			docIdentifPF.setPaisExpedicion(ReportUtilities.searchData(
					reportDatas, prefix + "PaisExpedicion",
					Integer.toString(6 + n), block));

		// (7+n) <prefix_TipoDocIdentif>
		if (ReportUtilities.searchData(reportDatas, prefix + "TipoDocIdentif",
				Integer.toString(7 + n), block) != null)
			try {
				docIdentifPF.setTipoDocIdentif(new Integer(ReportUtilities
						.searchData(reportDatas, prefix + "TipoDocIdentif",
								Integer.toString(7 + n), block)));
			} catch (NumberFormatException e) {
			}

		// (8+n) <prefix_NumDocIdentif>
		if (ReportUtilities.searchData(reportDatas, prefix + "NumDocIdentif",
				Integer.toString(8 + n), block) != null)
			docIdentifPF.setNumDocIdentif(ReportUtilities.searchData(
					reportDatas, prefix + "NumDocIdentif",
					Integer.toString(8 + n), block));

		if (!isEmptyDocIdentifPF(docIdentifPF))
			docIndetifsPFList.add(docIdentifPF);
		if (docIndetifsPFList.size() > 0)
			personafis.setDocIdentifsPF(docIndetifsPF);
		else
			personafis.setDocIdentifsPF(objectFactory
					.createPERSONAFISDocIdentifsPF());

		// PERSONAFIS.DomiciliosPF
		PERSONAFIS.DomiciliosPF domiciliosPF = objectFactory
				.createPERSONAFISDomiciliosPF();
		List<DOMICILIO> domiciliosPFList = domiciliosPF.getDomicilioPF();
		DOMICILIO domicilio = objectFactory.createDOMICILIO();
		// consider only one occurrence

		// (9+n) <prefix_TipoVia>
		if (ReportUtilities.searchData(reportDatas, prefix + "TipoVia",
				Integer.toString(9 + n), block) != null)
			try {
				domicilio.setTipoVia(new Integer(ReportUtilities.searchData(
						reportDatas, prefix + "TipoVia",
						Integer.toString(9 + n), block)));
			} catch (NumberFormatException e) {
			}

		// (10+n) <prefix_NombreVia>
		if (ReportUtilities.searchData(reportDatas, prefix + "NombreVia",
				Integer.toString(10 + n), block) != null)
			domicilio.setNombreVia(ReportUtilities.searchData(reportDatas,
					prefix + "NombreVia", Integer.toString(10 + n), block));

		// (11+n) <prefix_NumeroVia>
		if (ReportUtilities.searchData(reportDatas, prefix + "NumeroVia",
				Integer.toString(11 + n), block) != null)
			domicilio.setNumeroVia(ReportUtilities.searchData(reportDatas,
					prefix + "NumeroVia", Integer.toString(11 + n), block));

		// (12+n) <prefix_OtrosDatos>
		if (ReportUtilities.searchData(reportDatas, prefix + "OtrosDatos",
				Integer.toString(12 + n), block) != null)
			domicilio.setOtrosDatos(ReportUtilities.searchData(reportDatas,
					prefix + "OtrosDatos", Integer.toString(12 + n), block));

		// (13+n) <prefix_Pais>
		if (ReportUtilities.searchData(reportDatas, prefix + "Pais",
				Integer.toString(13 + n), block) != null)
			domicilio.setPais(ReportUtilities.searchData(reportDatas, prefix
					+ "Pais", Integer.toString(13 + n), block));

		// (14+n) <prefix_Provincia>
		if (ReportUtilities.searchData(reportDatas, prefix + "Provincia",
				Integer.toString(14 + n), block) != null)
			try {
				domicilio.setProvincia(new Integer(ReportUtilities.searchData(
						reportDatas, prefix + "Provincia",
						Integer.toString(14 + n), block)));
			} catch (NumberFormatException e) {
			}

		// (15+n) <prefix_ProvinciaExtranjera>
		if (ReportUtilities.searchData(reportDatas, prefix
				+ "ProvinciaExtranjera", Integer.toString(15 + n), block) != null)
			domicilio.setProvinciaExtranjera(ReportUtilities.searchData(
					reportDatas, prefix + "ProvinciaExtranjera",
					Integer.toString(15 + n), block));

		// (16+n) <prefix_Municipio>
		if (ReportUtilities.searchData(reportDatas, prefix + "Municipio",
				Integer.toString(16 + n), block) != null)
			domicilio.setMunicipio(ReportUtilities.searchData(reportDatas,
					prefix + "Municipio", Integer.toString(16 + n), block));

		// (17+n) <prefix_MunicipioExtranjero>
		if (ReportUtilities.searchData(reportDatas, prefix
				+ "MunicipioExtranjero", Integer.toString(17 + n), block) != null)
			domicilio.setMunicipioExtranjero(ReportUtilities.searchData(
					reportDatas, prefix + "MunicipioExtranjero",
					Integer.toString(17 + n), block));

		// (18+n) <prefix_CodPostal>
		if (ReportUtilities.searchData(reportDatas, prefix + "CodPostal",
				Integer.toString(18 + n), block) != null)
			domicilio.setCodPostal(ReportUtilities.searchData(reportDatas,
					prefix + "CodPostal", Integer.toString(18 + n), block));

		// (19+n) <prefix_CodPostalExtrajero>
		if (ReportUtilities.searchData(reportDatas, prefix
				+ "CodPostalExtrajero", Integer.toString(19 + n), block) != null)
			domicilio.setCodPostalExtrajero(ReportUtilities.searchData(
					reportDatas, prefix + "CodPostalExtrajero",
					Integer.toString(19 + n), block));

		// (20+n) <prefix_NumApartado>
		if (ReportUtilities.searchData(reportDatas, prefix + "NumApartado",
				Integer.toString(20 + n), block) != null)
			domicilio.setNumApartado(ReportUtilities.searchData(reportDatas,
					prefix + "NumApartado", Integer.toString(20 + n), block));

		// (21+n) <prefix_Localidad>
		if (ReportUtilities.searchData(reportDatas, prefix + "Localidad",
				Integer.toString(21 + n), block) != null)
			domicilio.setLocalidad(ReportUtilities.searchData(reportDatas,
					prefix + "Localidad", Integer.toString(21 + n), block));

		if (!isEmptyDOMICILIO(domicilio))
			domiciliosPFList.add(domicilio);
		if (domiciliosPFList.size() > 0)
			personafis.setDomiciliosPF(domiciliosPF);
		else
			personafis.setDomiciliosPF(objectFactory
					.createPERSONAFISDomiciliosPF());

		// PERSONAFIS.Telefonos
		PERSONAFIS.Telefonos telefonos = objectFactory
				.createPERSONAFISTelefonos();
		List<PERSONAFIS.Telefonos.Telefono> telefonosList = telefonos
				.getTelefono();
		PERSONAFIS.Telefonos.Telefono telefono = objectFactory
				.createPERSONAFISTelefonosTelefono();

		// (22+n) <prefix_Telefono>
		if (ReportUtilities.searchData(reportDatas, prefix + "Telefono",
				Integer.toString(22 + n), block) != null)
			telefono.setTelefono(ReportUtilities.searchData(reportDatas, prefix
					+ "Telefono", Integer.toString(22 + n), block));

		if (telefono.getTelefono() != null)
			telefonosList.add(telefono);
		if (telefonosList.size() > 0)
			personafis.setTelefonos(telefonos);
		else
			personafis.setTelefonos(objectFactory.createPERSONAFISTelefonos());

		// (23+n) <prefix_Relacion>
		if (ReportUtilities.searchData(reportDatas, prefix + "Relacion",
				Integer.toString(23 + n), block) != null)
			try {
				personafis.setRelacion(new Integer(ReportUtilities.searchData(
						reportDatas, prefix + "Relacion",
						Integer.toString(23 + n), block)));
			} catch (NumberFormatException e) {
			}

		if (isEmptyPERSONAFIS(personafis))
			personafis = null;

		return personafis;
	}

	/**
	 * Enter a empty PERSONAJUR and reportExecution and fill it.
	 * 
	 * @param objectFactory
	 * @param personajur
	 * @param block
	 * @param reportExecution
	 * @param n
	 *            : number of field which start to count
	 * @param prefix
	 *            : prefix of fieldName
	 * @return PERSONAJUR
	 */
	public static PERSONAJUR makePersonaJurDMO(
			ObjectFactoryDMOAdapter objectFactory, PERSONAJUR personajur,
			String block, ReportExecution reportExecution, Integer n,
			String prefix) {

		List<ReportData> reportDatas = reportExecution.getReportDatas();

		// (1+n) <prefix_CDPERSONA>
		if (ReportUtilities.searchData(reportDatas, prefix + "CDPERSONA",
				Integer.toString(1 + n), block) != null)
			personajur.setCDPERSONA(ReportUtilities.searchData(reportDatas,
					prefix + "CDPERSONA", Integer.toString(1 + n), block));

		// (2+n) <prefix_RazonSocial>
		if (ReportUtilities.searchData(reportDatas, prefix + "RazonSocial",
				Integer.toString(2 + n), block) != null)
			personajur.setRazonSocial(ReportUtilities.searchData(reportDatas,
					prefix + "RazonSocial", Integer.toString(2 + n), block));

		// (3+n) <prefix_PaisNacionalidad>
		if (ReportUtilities.searchData(reportDatas,
				prefix + "PaisNacionalidad", Integer.toString(5 + n), block) != null)
			personajur.setPaisNacionalidad(ReportUtilities.searchData(
					reportDatas, prefix + "PaisNacionalidad",
					Integer.toString(5 + n), block));

		// PERSONAJUR.DocIndetifsPJ
		PERSONAJUR.DocIdentifsPJ docIndetifsPJ = objectFactory
				.createPERSONAJURDocIdentifsPJ();
		List<PERSONAJUR.DocIdentifsPJ.DocIdentifPJ> docIndetifsPJList = docIndetifsPJ
				.getDocIdentifPJ();
		PERSONAJUR.DocIdentifsPJ.DocIdentifPJ docIdentifPJ = objectFactory
				.createPERSONAJURDocIdentifsPJDocIdentifPJ();
		// consider only one occurrence

		// (4+n) <prefix_TipoDocIdentif>
		if (ReportUtilities.searchData(reportDatas, prefix + "TipoDocIdentif",
				Integer.toString(4 + n), block) != null)
			try {
				docIdentifPJ.setTipoDocIdentif(new Integer(ReportUtilities
						.searchData(reportDatas, prefix + "TipoDocIdentif",
								Integer.toString(4 + n), block)));
			} catch (NumberFormatException e) {
			}

		// (5+n) <prefix_PaisExpedicion>
		if (ReportUtilities.searchData(reportDatas, prefix + "PaisExpedicion",
				Integer.toString(5 + n), block) != null)
			docIdentifPJ.setPaisExpedicion(ReportUtilities.searchData(
					reportDatas, prefix + "PaisExpedicion",
					Integer.toString(5 + n), block));

		// (6+n) <prefix_NumDocIdentif>
		if (ReportUtilities.searchData(reportDatas, prefix + "NumDocIdentif",
				Integer.toString(6 + n), block) != null)
			docIdentifPJ.setNumDocIdentif(ReportUtilities.searchData(
					reportDatas, prefix + "NumDocIdentif",
					Integer.toString(6 + n), block));

		if (!isEmptyDocIdentifPJ(docIdentifPJ))
			docIndetifsPJList.add(docIdentifPJ);
		if (docIndetifsPJList.size() > 0)
			personajur.setDocIdentifsPJ(docIndetifsPJ);
		else
			personajur.setDocIdentifsPJ(objectFactory
					.createPERSONAJURDocIdentifsPJ());

		// PERSONAJUR.DomiciliosPJ
		PERSONAJUR.DomiciliosPJ domiciliosPJ = objectFactory
				.createPERSONAJURDomiciliosPJ();
		List<DOMICILIO> domiciliosPJList = domiciliosPJ.getDomicilioPJ();
		DOMICILIO domicilio = objectFactory.createDOMICILIO();
		// consider only one occurrence

		// (7+n) <prefix_TipoVia>
		if (ReportUtilities.searchData(reportDatas, prefix + "TipoVia",
				Integer.toString(7 + n), block) != null)
			try {
				domicilio.setTipoVia(new Integer(ReportUtilities.searchData(
						reportDatas, prefix + "TipoVia",
						Integer.toString(7 + n), block)));
			} catch (NumberFormatException e) {
			}

		// (8+n) <prefix_NombreVia>
		if (ReportUtilities.searchData(reportDatas, prefix + "NombreVia",
				Integer.toString(8 + n), block) != null)
			domicilio.setNombreVia(ReportUtilities.searchData(reportDatas,
					prefix + "NombreVia", Integer.toString(8 + n), block));

		// (9+n) <prefix_NumeroVia>
		if (ReportUtilities.searchData(reportDatas, prefix + "NumeroVia",
				Integer.toString(9 + n), block) != null)
			domicilio.setNumeroVia(ReportUtilities.searchData(reportDatas,
					prefix + "NumeroVia", Integer.toString(9 + n), block));

		// (10+n) <prefix_OtrosDatos>
		if (ReportUtilities.searchData(reportDatas, prefix + "OtrosDatos",
				Integer.toString(10 + n), block) != null)
			domicilio.setOtrosDatos(ReportUtilities.searchData(reportDatas,
					prefix + "OtrosDatos", Integer.toString(10 + n), block));

		// (11+n) <prefix_Pais>
		if (ReportUtilities.searchData(reportDatas, prefix + "Pais",
				Integer.toString(11 + n), block) != null)
			domicilio.setPais(ReportUtilities.searchData(reportDatas, prefix
					+ "Pais", Integer.toString(11 + n), block));

		// (12+n) <prefix_Provincia>
		if (ReportUtilities.searchData(reportDatas, prefix + "Provincia",
				Integer.toString(12 + n), block) != null)
			try {
				domicilio.setProvincia(new Integer(ReportUtilities.searchData(
						reportDatas, prefix + "Provincia",
						Integer.toString(12 + n), block)));
			} catch (NumberFormatException e) {
			}

		// (13+n) <prefix_ProvinciaExtranjera>
		if (ReportUtilities.searchData(reportDatas, prefix
				+ "ProvinciaExtranjera", Integer.toString(13 + n), block) != null)
			domicilio.setProvinciaExtranjera(ReportUtilities.searchData(
					reportDatas, prefix + "ProvinciaExtranjera",
					Integer.toString(13 + n), block));

		// (14+n) <prefix_Municipio>
		if (ReportUtilities.searchData(reportDatas, prefix + "Municipio",
				Integer.toString(14 + n), block) != null)
			domicilio.setMunicipio(ReportUtilities.searchData(reportDatas,
					prefix + "Municipio", Integer.toString(14 + n), block));

		// (15+n) <prefix_MunicipioExtranjero>
		if (ReportUtilities.searchData(reportDatas, prefix
				+ "MunicipioExtranjero", Integer.toString(15 + n), block) != null)
			domicilio.setMunicipioExtranjero(ReportUtilities.searchData(
					reportDatas, prefix + "MunicipioExtranjero",
					Integer.toString(15 + n), block));

		// (16+n) <prefix_CodPostal>
		if (ReportUtilities.searchData(reportDatas, prefix + "CodPostal",
				Integer.toString(16 + n), block) != null)
			domicilio.setCodPostal(ReportUtilities.searchData(reportDatas,
					prefix + "CodPostal", Integer.toString(16 + n), block));

		// (17+n) <prefix_CodPostalExtrajero>
		if (ReportUtilities.searchData(reportDatas, prefix
				+ "CodPostalExtrajero", Integer.toString(17 + n), block) != null)
			domicilio.setCodPostalExtrajero(ReportUtilities.searchData(
					reportDatas, prefix + "CodPostalExtrajero",
					Integer.toString(17 + n), block));

		// (18+n) <prefix_NumApartado>
		if (ReportUtilities.searchData(reportDatas, prefix + "NumApartado",
				Integer.toString(18 + n), block) != null)
			domicilio.setNumApartado(ReportUtilities.searchData(reportDatas,
					prefix + "NumApartado", Integer.toString(18 + n), block));

		// (19+n) <prefix_Localidad>
		if (ReportUtilities.searchData(reportDatas, prefix + "Localidad",
				Integer.toString(19 + n), block) != null)
			domicilio.setLocalidad(ReportUtilities.searchData(reportDatas,
					prefix + "Localidad", Integer.toString(19 + n), block));

		if (!isEmptyDOMICILIO(domicilio))
			domiciliosPJList.add(domicilio);
		if (domiciliosPJList.size() > 0)
			personajur.setDomiciliosPJ(domiciliosPJ);
		else
			personajur.setDomiciliosPJ(objectFactory
					.createPERSONAJURDomiciliosPJ());

		// PERSONAJUR.Telefonos
		PERSONAJUR.Telefonos telefonos = objectFactory
				.createPERSONAJURTelefonos();
		List<PERSONAJUR.Telefonos.Telefono> telefonosList = telefonos
				.getTelefono();
		PERSONAJUR.Telefonos.Telefono telefono = objectFactory
				.createPERSONAJURTelefonosTelefono();

		// (20+n) <prefix_Telefono>
		if (ReportUtilities.searchData(reportDatas, prefix + "Telefono",
				Integer.toString(20 + n), block) != null)
			telefono.setTelefono(ReportUtilities.searchData(reportDatas, prefix
					+ "Telefono", Integer.toString(20 + n), block));

		if (telefono.getTelefono() != null)
			telefonosList.add(telefono);
		if (telefonosList.size() > 0)
			personajur.setTelefonos(telefonos);
		else
			personajur.setTelefonos(objectFactory.createPERSONAJURTelefonos());

		// (21+n) <prefix_Relacion>
		if (ReportUtilities.searchData(reportDatas, prefix + "Relacion",
				Integer.toString(21 + n), block) != null)
			try {
				personajur.setRelacion(new Integer(ReportUtilities.searchData(
						reportDatas, prefix + "Relacion",
						Integer.toString(21 + n), block)));
			} catch (NumberFormatException e) {
			}

		if (isEmptyPERSONAJUR(personajur))
			personajur = null;

		return personajur;
	}

	/**
	 * Check if is empty PERSONAFIS.
	 * 
	 * @param personafis
	 * @return boolean
	 */
	public static boolean isEmptyPERSONAFIS(PERSONAFIS personafis) {
		boolean result = true;

		if (personafis.getCDPERSONA() != null)
			result = false;
		// if (personafis.getDocIdentifsPF() != null)
		// result = false;
		// if (personafis.getDomiciliosPF() != null)
		// result = false;
		if (personafis.getNombre() != null)
			result = false;
		if (personafis.getPais() != null)
			result = false;
		if (personafis.getPrimerApellido() != null)
			result = false;
		if (personafis.getRelacion() != null)
			result = false;
		if (personafis.getSegundoApellido() != null)
			result = false;
		// if (personafis.getTelefonos() != null)
		// result = false;

		return result;
	}

	/**
	 * Check if is empty DocIdentifPF.
	 * 
	 * @param docIdentifpf
	 * @return boolean
	 */
	public static boolean isEmptyDocIdentifPF(DOCIDENTIFPF docIdentifpf) {
		boolean result = true;

		if (docIdentifpf.getNumDocIdentif() != null)
			result = false;
		if (docIdentifpf.getPaisExpedicion() != null)
			result = false;
		// if (docIdentifpf.getTipoDocIdentif() != null)
		// result = false;

		return result;
	}

	/**
	 * Check if is empty DocIdentifPJ.
	 * 
	 * @param docIdentifpj
	 * @return boolean
	 */
	public static boolean isEmptyDocIdentifPJ(DOCIDENTIFPJ docIdentifpj) {
		boolean result = true;

		if (docIdentifpj.getNumDocIdentif() != null)
			result = false;
		if (docIdentifpj.getPaisExpedicion() != null)
			result = false;
		// if (docIdentifpf.getTipoDocIdentif() != null)
		// result = false;

		return result;
	}

	/**
	 * Check if is empty DOMICILIO.
	 * 
	 * @param domicilio
	 * @return boolean
	 */
	public static boolean isEmptyDOMICILIO(DOMICILIO domicilio) {
		boolean result = true;

		if (domicilio.getCodPostal() != null)
			result = false;
		if (domicilio.getCodPostalExtrajero() != null)
			result = false;
		if (domicilio.getLocalidad() != null)
			result = false;
		if (domicilio.getMunicipio() != null)
			result = false;
		if (domicilio.getMunicipioExtranjero() != null)
			result = false;
		if (domicilio.getNombreVia() != null)
			result = false;
		if (domicilio.getNumApartado() != null)
			result = false;
		if (domicilio.getNumeroVia() != null)
			result = false;
		if (domicilio.getOtrosDatos() != null)
			result = false;
		if (domicilio.getPais() != null)
			result = false;
		if (domicilio.getProvincia() != null)
			result = false;
		if (domicilio.getProvinciaExtranjera() != null)
			result = false;
		if (domicilio.getTipoVia() != null)
			result = false;

		return result;
	}

	/**
	 * Function read all CABECERAOP fields and if are empty, return true.
	 * 
	 * @param cabeceraop
	 * @return true isEmpty, false otherwise
	 */
	public static boolean isEmptyCABECERAOP(CABECERAOP cabeceraop) {
		boolean result = true;

		if (cabeceraop.getCaracteristicas() != null)
			result = false;
		if (cabeceraop.getDivisa() != null)
			result = false;
		if (cabeceraop.getFechaOperacion() != null)
			result = false;
		if (cabeceraop.getHoraOperacion() != null)
			result = false;
		if (cabeceraop.getImporte() != null)
			result = false;
		if (cabeceraop.getImporteEnEuros() != null)
			result = false;
		if (cabeceraop.getMunicipioDestino() != null)
			result = false;
		if (cabeceraop.getMunicipioDestinoEx() != null)
			result = false;
		if (cabeceraop.getMunicipioOrigen() != null)
			result = false;
		if (cabeceraop.getMunicipioOrigenEx() != null)
			result = false;
		if (cabeceraop.getNumeroCuenta() != null)
			result = false;
		// if (cabeceraop.getNumOperacion() != null)
		// result = false;
		if (cabeceraop.getOficina() != null)
			result = false;
		if (cabeceraop.getPaisDestino() != null)
			result = false;
		if (cabeceraop.getPaisOrigen() != null)
			result = false;
		if (cabeceraop.getProvinciaDestino() != null)
			result = false;
		if (cabeceraop.getProvinciaDestinoEx() != null)
			result = false;
		if (cabeceraop.getProvinciaOrigen() != null)
			result = false;
		if (cabeceraop.getProvinciaOrigenEx() != null)
			result = false;
		// if (cabeceraop.getTipoOperacion() != null)
		// result = false;

		return result;
	}

	/**
	 * Check if is empty PERSONAJUR.
	 * 
	 * @param personajur
	 * @return boolean
	 */
	public static boolean isEmptyPERSONAJUR(PERSONAJUR personajur) {
		boolean result = true;

		if (personajur.getCDPERSONA() != null)
			result = false;
		// if (personajur.getDocIdentifsPJ() != null)
		// result = false;
		// if (personajur.getDomiciliosPJ() != null)
		// result = false;
		if (personajur.getRazonSocial() != null)
			result = false;
		if (personajur.getPaisNacionalidad() != null)
			result = false;
		if (personajur.getRelacion() != null)
			result = false;
		// if (personajur.getTelefonos() != null)
		// result = false;

		return result;
	}

}
