package com.entities.entity.install;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.entities.dao.loader.FileColumDAO;
import com.entities.dao.loader.FileConfigDAO;
import com.entities.dao.reportingtool.DepartmentDAO;
import com.entities.dao.reportingtool.ReportCatalogDAO;
import com.entities.dao.reportingtool.ReportFieldDAO;
import com.entities.dictionary.ReportCatalogLevelEnum;
import com.entities.entity.loader.FileColum;
import com.entities.entity.loader.FileConfig;
import com.entities.entity.reportingtool.Department;
import com.entities.entity.reportingtool.ReportCatalog;
import com.entities.entity.reportingtool.ReportField;
import com.entities.utilities.hibernate.VersionAuditor;
import com.reportingtool.utilities.ReportUtilities;

public class InstallDMO_DP implements InstallAdapter {

	private ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(InstallDMO_DP.class);

	public static final String nameDatosEntrada = "DMO_DM_2.0 DatosEntrada";
	public static final String nameOperacionGeneral = "DMO_DM_2.0 OperacionGeneral";
	public static final String nameOperacion820 = "DMO_DM_2.0 Operacion8_20";

	public InstallDMO_DP(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * Process to install in database reportCatalog and reportFields from AIFM
	 * report, also an example of load file.
	 * 
	 * @param applicationContext
	 */
	public void install() {

		try {

			VersionAuditor versionAdmin = new VersionAuditor("admin");

			String versionField = ReportUtilities.reportDMOVersion;

			ReportCatalog reportCatalog = new ReportCatalog(versionField,
					ReportCatalogLevelEnum.DEPARTMENT.getReportLevel(), ReportUtilities.reportCatalogDMO_DP_Name, "",
					null, null, null, versionAdmin);

			ReportFieldDAO reportFieldDAO = (ReportFieldDAO) applicationContext
					.getBean("reportFieldDAO");

			ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) applicationContext
					.getBean("reportCatalogDAO");
			reportCatalogDAO.create(reportCatalog);

			ReportField reportFieldx1 = new ReportField(reportCatalog, null,
					"X", "DeclaracionPositiva", new BigInteger("0"), null, "",
					null, null, "1.0", "1,1", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportFieldx1);

			ReportField reportFieldx2 = new ReportField(reportCatalog,
					reportFieldx1, "X", "Operacion", new BigInteger("0"), null,
					"", null, null, "1.0", "1,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportFieldx2);

			// (1) <ID>
			ReportField reportField1 = new ReportField(reportCatalog,
					reportFieldx2, "A", "ID", new BigInteger("1"), ".{1,5}",
					"DatosEntrada", "DatosEntrada", "xs:string", "1.01", "1,1",
					versionField, null, null, null, versionAdmin);
			reportField1.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField1);

			// (2) <NombreEntidad>
			ReportField reportField2 = new ReportField(reportCatalog,
					reportFieldx2, "A", "NombreEntidad", new BigInteger("2"),
					".{1,80}", "DatosEntrada", "DatosEntrada", "xs:string",
					"1.02", "1,1", versionField, null, null, null, versionAdmin);
			reportField2.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField2);

			// (3) <CIFNIFEntidad>
			ReportField reportField3 = new ReportField(reportCatalog,
					reportFieldx2, "A", "CIFNIFEntidad", new BigInteger("3"),
					".{1,15}", "DatosEntrada", "DatosEntrada", "xs:string",
					"1.03", "1,1", versionField, null, null, null, versionAdmin);
			reportField3.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField3);

			// (4) <MesDeclaracion>
			ReportField reportField4 = new ReportField(reportCatalog,
					reportFieldx2, "D", "MesDeclaracion", new BigInteger("4"),
					"(19[0-9]{2}|20[0-9]{2})\\/([0][1-9]|[1][0-2])",
					"DatosEntrada", "DatosEntrada", "yyyy/mm", "1.04", "1,1",
					versionField, null, null, null, versionAdmin);
			reportField4.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField4);

			// (5) <Representante>
			ReportField reportField5 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Representante", new BigInteger("5"),
					".{1,160}", "DatosEntrada", "DatosEntrada", "xs:string",
					"1.05", "1,1", versionField, null, null, null, versionAdmin);
			reportField5.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField5);

			// (6) <FechaDeclaracion>
			ReportField reportField6 = new ReportField(
					reportCatalog,
					reportFieldx2,
					"D",
					"FechaDeclaracion",
					new BigInteger("6"),
					"(10|20|[0-2][1-9]|[3][0-1])\\/([0][1-9]|[1][0-2])\\/(19[0-9]{2}|20[0-9]{2})",
					"DatosEntrada", "DatosEntrada", "FECHA_GEN", "1.06", "1,1",
					versionField, null, null, null, versionAdmin);
			reportField6.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField6);

			// //////////////////////////////////

			// // (7) <FechaDesde>
			// ReportField reportField7 = new ReportField(
			// reportCatalog,
			// reportFieldx2,
			// "D",
			// "FechaDesde",
			// new BigInteger("7"),
			// "(10|20|[0-2][1-9]|[3][0-1])\\/([0][1-9]|[1][0-2])\\/(19[0-9]{2}|20[0-9]{2})",
			// "Periodo", "Periodo", "FECHA_GEN", "2.08", "0,n",
			// versionField, null, null, null, versionAdmin);
			// reportFieldDAO.create(reportField7);
			//
			// // (8) <FechaHasta>
			// ReportField reportField8 = new ReportField(
			// reportCatalog,
			// reportFieldx2,
			// "D",
			// "FechaHasta",
			// new BigInteger("8"),
			// "(10|20|[0-2][1-9]|[3][0-1])\\/([0][1-9]|[1][0-2])\\/(19[0-9]{2}|20[0-9]{2})",
			// "Periodo", "Periodo", "FECHA_GEN", "2.08", "0,n",
			// versionField, null, null, null, versionAdmin);
			// reportFieldDAO.create(reportField8);

			// //////////////////////////////////

			// (9) <NumOperacion>
			ReportField reportField9 = new ReportField(reportCatalog,
					reportFieldx2, "N", "NumOperacion", new BigInteger("9"),
					"[0-9]{1,5}", "CABECERAOP", "OperacionGeneral", "xs:int",
					"3.09", "1,n", versionField, null, null, null, versionAdmin);
			reportField9.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField9);

			// (10) <TipoOperacion>
			ReportField reportField10 = new ReportField(reportCatalog,
					reportFieldx2, "N", "TipoOperacion", new BigInteger("10"),
					"[0-9]{1,5}", "CABECERAOP", "OperacionGeneral", "xs:int",
					"3.10", "1,n", versionField, null, null, null, versionAdmin);
			reportField10.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField10);

			// (11) <Caracteristica>
			ReportField reportField11 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Caracteristica", new BigInteger("11"),
					".{1}", "CABECERAOP", "OperacionGeneral",
					"CARACTERISTICAS_ENUM", "3.11", "1,n", versionField, null,
					null, null, versionAdmin);
			reportField11.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField11);

			// (12) <FechaOperacion>
			ReportField reportField12 = new ReportField(
					reportCatalog,
					reportFieldx2,
					"D",
					"FechaOperacion",
					new BigInteger("12"),
					"(10|20|[0-2][1-9]|[3][0-1])\\/([0][1-9]|[1][0-2])\\/(19[0-9]{2}|20[0-9]{2})",
					"CABECERAOP", "OperacionGeneral", "FECHA_GEN", "3.12",
					"1,n", versionField, null, null, null, versionAdmin);
			reportField12.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField12);

			// (13) <HoraOperacion>
			ReportField reportField13 = new ReportField(reportCatalog,
					reportFieldx2, "D", "HoraOperacion", new BigInteger("13"),
					"([0-1][0-9]|[2][0-3]):([0-5][0-9]):([0-5][0-9])",
					"CABECERAOP", "OperacionGeneral", "HORA", "3.13", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField13);

			// (14) <Importe>
			ReportField reportField14 = new ReportField(reportCatalog,
					reportFieldx2, "N", "Importe", new BigInteger("14"),
					"[0-9]{1,16}(\\.[0-9]{1,2})?", "CABECERAOP",
					"OperacionGeneral", "xs:decimal", "3.14", "1,n",
					versionField, null, null, null, versionAdmin);
			reportField14.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField14);

			// (15) <Divisa>
			ReportField reportField15 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Divisa", new BigInteger("15"),
					".{1,3}", "CABECERAOP", "OperacionGeneral", "DIVISA_ENUM",
					"3.15", "1,n", versionField, null, null, null, versionAdmin);
			reportField15.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField15);

			// (16) <ImporteEnEuros>
			ReportField reportField16 = new ReportField(reportCatalog,
					reportFieldx2, "N", "ImporteEnEuros", new BigInteger("16"),
					"[0-9]{1,16}(\\.[0-9]{1,2})?", "CABECERAOP",
					"OperacionGeneral", "xs:decimal", "3.16", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField16);

			// (17) <PaisOrigen>
			ReportField reportField17 = new ReportField(reportCatalog,
					reportFieldx2, "A", "PaisOrigen", new BigInteger("17"),
					".{3}", "CABECERAOP", "OperacionGeneral", "PAIS_ENUM",
					"3.17", "0,n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField17);

			// (18) <ProvinciaOrigen>
			ReportField reportField18 = new ReportField(reportCatalog,
					reportFieldx2, "A", "ProvinciaOrigen",
					new BigInteger("18"), ".{1,2}", "CABECERAOP",
					"OperacionGeneral", "PROV_ENUM", "3.18", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField18);

			// (19) <ProvinciaOrigenEx>
			ReportField reportField19 = new ReportField(reportCatalog,
					reportFieldx2, "A", "ProvinciaOrigenEx", new BigInteger(
							"19"), ".{1,80}", "CABECERAOP", "OperacionGeneral",
					"xs:string", "3.19", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField19);

			// (20) <MunicipioOrigen>
			ReportField reportField20 = new ReportField(reportCatalog,
					reportFieldx2, "A", "MunicipioOrigen",
					new BigInteger("20"), ".{1,3}", "CABECERAOP",
					"OperacionGeneral", "MUN_ENUM", "3.20", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField20);

			// (21) <MunicipioOrigenEx>
			ReportField reportField21 = new ReportField(reportCatalog,
					reportFieldx2, "A", "MunicipioOrigenEx", new BigInteger(
							"21"), ".{1,80}", "CABECERAOP", "OperacionGeneral",
					"xs:string", "3.21", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField21);

			// (22) <PaisDestino>
			ReportField reportField22 = new ReportField(reportCatalog,
					reportFieldx2, "A", "PaisDestino", new BigInteger("22"),
					".{3}", "CABECERAOP", "OperacionGeneral", "PAIS_ENUM",
					"3.22", "0,n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField22);

			// (23) <ProvinciaDestino>
			ReportField reportField23 = new ReportField(reportCatalog,
					reportFieldx2, "A", "ProvinciaDestino",
					new BigInteger("23"), ".{1,2}", "CABECERAOP",
					"OperacionGeneral", "PROV_ENUM", "3.23", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField23);

			// (24) <ProvinciaDestinoEx>
			ReportField reportField24 = new ReportField(reportCatalog,
					reportFieldx2, "A", "ProvinciaDestinoEx", new BigInteger(
							"24"), ".{1,80}", "CABECERAOP", "OperacionGeneral",
					"xs:string", "3.24", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField24);

			// (25) <MunicipioDestino>
			ReportField reportField25 = new ReportField(reportCatalog,
					reportFieldx2, "A", "MunicipioDestino",
					new BigInteger("25"), ".{1,3}", "CABECERAOP",
					"OperacionGeneral", "MUN_ENUM", "3.25", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField25);

			// (26) <MunicipioDestinoEx>
			ReportField reportField26 = new ReportField(reportCatalog,
					reportFieldx2, "A", "MunicipioDestinoEx", new BigInteger(
							"26"), ".{1,80}", "CABECERAOP", "OperacionGeneral",
					"xs:string", "3.26", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField26);

			// (27) <Oficina>
			ReportField reportField27 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Oficina", new BigInteger("27"),
					"[0-9]{4}", "CABECERAOP", "OperacionGeneral", "xs:string",
					"3.27", "0,n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField27);

			// (28) <NumeroCuenta>
			ReportField reportField28 = new ReportField(reportCatalog,
					reportFieldx2, "A", "NumeroCuenta", new BigInteger("28"),
					"[0-9]{20}", "CABECERAOP", "OperacionGeneral", "xs:string",
					"3.28", "0,n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField28);

			// //////////////////////////////////
			// Origen

			// (29) <Origen_CDPERSONA>
			ReportField reportField29 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_CDPERSONA",
					new BigInteger("29"), ".{1,10}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "4.29", "1,n",
					versionField, null, null, null, versionAdmin);
			reportField29.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField29);

			// (30) <Origen_Nombre>
			ReportField reportField30 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_Nombre", new BigInteger("30"),
					".{1,80}", "PERSONAFIS", "OperacionGeneral", "xs:string",
					"4.30", "0,n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField30);

			// (31) <Origen_PrimerApellido>
			ReportField reportField31 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_PrimerApellido",
					new BigInteger("31"), ".{1,40}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "4.31", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField31);

			// (32) <Origen_SegundoApellido>
			ReportField reportField32 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_SegundoApellido",
					new BigInteger("32"), ".{1,40}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "4.32", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField32);

			// (33) <Origen_Pais>
			ReportField reportField33 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_Pais", new BigInteger("33"),
					".{3}", "PERSONAFIS", "OperacionGeneral", "PAIS_ENUM",
					"4.33", "0,n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField33);

			// (34) <Origen_PaisExpedicion>
			ReportField reportField34 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_PaisExpedicion",
					new BigInteger("34"), ".{3}", "PERSONAFIS",
					"OperacionGeneral", "PAIS_ENUM", "4.34", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField34);

			// (35) <Origen_TipoDocIdentif>
			ReportField reportField35 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_TipoDocIdentif",
					new BigInteger("35"), ".{1}", "PERSONAFIS",
					"OperacionGeneral", "TIPDIPF_ENUM", "4.35", "1,n",
					versionField, null, null, null, versionAdmin);
			reportField35.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField35);

			// (36) <Origen_NumDocIdentif>
			ReportField reportField36 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_NumDocIdentif", new BigInteger(
							"36"), ".{1,15}", "PERSONAFIS", "OperacionGeneral",
					"xs:string", "4.36", "1,n", versionField, null, null, null,
					versionAdmin);
			reportField36.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField36);

			// (37) <Origen_TipoVia>
			ReportField reportField37 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_TipoVia", new BigInteger("37"),
					".{1,2}", "PERSONAFIS", "OperacionGeneral", "TIPVIA_ENUM",
					"4.37", "0,n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField37);

			// (38) <Origen_NombreVia>
			ReportField reportField38 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_NombreVia",
					new BigInteger("38"), ".{1,150}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "4.38", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField38);

			// (39) <Origen_NumeroVia>
			ReportField reportField39 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_NumeroVia",
					new BigInteger("39"), ".{1,5}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "4.39", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField39);

			// (40) <Origen_OtrosDatos>
			ReportField reportField40 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_OtrosDatos", new BigInteger(
							"40"), ".{1,50}", "PERSONAFIS", "OperacionGeneral",
					"xs:string", "4.40", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField40);

			// (41) <Origen_Pais>
			ReportField reportField41 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_Pais", new BigInteger("41"),
					".{3}", "PERSONAFIS", "OperacionGeneral", "PAIS_ENUM",
					"4.41", "1,n", versionField, null, null, null, versionAdmin);
			reportField41.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField41);

			// (42) <Origen_Provincia>
			ReportField reportField42 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_Provincia",
					new BigInteger("42"), ".{1,2}", "PERSONAFIS",
					"OperacionGeneral", "PROV_ENUM", "4.42", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField42);

			// (43) <Origen_ProvinciaExtranjera>
			ReportField reportField43 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_ProvinciaExtranjera",
					new BigInteger("43"), ".{1,80}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "4.43", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField43);

			// (44) <Origen_Municipio>
			ReportField reportField44 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_Municipio",
					new BigInteger("44"), ".{3}", "PERSONAFIS",
					"OperacionGeneral", "MUN_ENUM", "4.44", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField44);

			// (45) <Origen_MunicipioExtranjero>
			ReportField reportField45 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_MunicipioExtranjero",
					new BigInteger("45"), ".{1,80}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "4.45", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField45);

			// (46) <Origen_CodPostal>
			ReportField reportField46 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_CodPostal",
					new BigInteger("46"), ".{1,5}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "4.46", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField46);

			// (47) <Origen_CodPostalExtrajero>
			ReportField reportField47 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_CodPostalExtrajero",
					new BigInteger("47"), ".{1,8}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "4.47", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField47);

			// (48) <Origen_NumApartado>
			ReportField reportField48 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_NumApartado", new BigInteger(
							"48"), ".{1,12}", "PERSONAFIS", "OperacionGeneral",
					"xs:string", "4.48", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField48);

			// (49) <Origen_Localidad>
			ReportField reportField49 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_Localidad",
					new BigInteger("49"), ".{1,80}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "4.49", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField49);

			// (50) <Origen_Telefono>
			ReportField reportField50 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_Telefono",
					new BigInteger("50"), ".{1,20}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "4.50", "1,n",
					versionField, null, null, null, versionAdmin);
			reportField50.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField50);

			// (51) <Origen_Relacion>
			ReportField reportField51 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_Relacion",
					new BigInteger("51"), ".{1}", "PERSONAFIS",
					"OperacionGeneral", "REL_PER_CUENTA_ENUM", "4.51", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField51);

			// ////////////////////////////////////////////////////////

			// (52) <Origen_CDPERSONA>
			ReportField reportField52 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_CDPERSONA",
					new BigInteger("52"), ".{1,10}", "PERSONAJUR",
					"OperacionGeneral", "xs:string", "5.52", "1,n",
					versionField, null, null, null, versionAdmin);
			reportField52.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField52);

			// (53) <Origen_RazonSocial>
			ReportField reportField53 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_RazonSocial", new BigInteger(
							"53"), ".{1,80}", "PERSONAJUR", "OperacionGeneral",
					"xs:string", "5.53", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField53);

			// (54) <Origen_PaisNacionalidad>
			ReportField reportField54 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_PaisNacionalidad",
					new BigInteger("54"), ".{3}", "PERSONAJUR",
					"OperacionGeneral", "PAIS_ENUM", "5.54", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField54);

			// (55) <Origen_TipoDocIdentif>
			ReportField reportField55 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_TipoDocIdentif",
					new BigInteger("55"), ".{2}", "PERSONAJUR",
					"OperacionGeneral", "TIPDIPJ_ENUM", "5.55", "1,n",
					versionField, null, null, null, versionAdmin);
			reportField55.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField55);

			// (56) <Origen_PaisExpedicion>
			ReportField reportField56 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_PaisExpedicion",
					new BigInteger("56"), ".{3}", "PERSONAJUR",
					"OperacionGeneral", "PAIS_ENUM", "5.56", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField56);

			// (57) <Origen_NumDocIdentif>
			ReportField reportField57 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_NumDocIdentif", new BigInteger(
							"57"), ".{1,15}", "PERSONAJUR", "OperacionGeneral",
					"xs:string", "5.57", "1,n", versionField, null, null, null,
					versionAdmin);
			reportField57.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField57);

			// (58) <Origen_TipoVia>
			ReportField reportField58 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_TipoVia", new BigInteger("58"),
					".{1,2}", "PERSONAJUR", "OperacionGeneral", "TIPVIA_ENUM",
					"5.58", "0,n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField58);

			// (59) <Origen_NombreVia>
			ReportField reportField59 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_NombreVia",
					new BigInteger("59"), ".{1,150}", "PERSONAJUR",
					"OperacionGeneral", "xs:string", "5.59", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField59);

			// (60) <Origen_NumeroVia>
			ReportField reportField60 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_NumeroVia",
					new BigInteger("60"), ".{1,5}", "PERSONAJUR",
					"OperacionGeneral", "xs:string", "5.60", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField60);

			// (61) <Origen_OtrosDatos>
			ReportField reportField61 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_OtrosDatos", new BigInteger(
							"61"), ".{1,50}", "PERSONAJUR", "OperacionGeneral",
					"xs:string", "5.61", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField61);

			// (62) <Origen_Pais>
			ReportField reportField62 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_Pais", new BigInteger("62"),
					".{3}", "PERSONAJUR", "OperacionGeneral", "PAIS_ENUM",
					"5.62", "1,n", versionField, null, null, null, versionAdmin);
			reportField62.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField62);

			// (63) <Origen_Provincia>
			ReportField reportField63 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_Provincia",
					new BigInteger("63"), ".{3}", "PERSONAFIS",
					"OperacionGeneral", "PROV_ENUM", "5.63", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField63);

			// (64) <Origen_ProvinciaExtranjera>
			ReportField reportField64 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_ProvinciaExtranjera",
					new BigInteger("64"), ".{1,80}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "5.64", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField64);

			// (65) <Origen_Municipio>
			ReportField reportField65 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_Municipio",
					new BigInteger("65"), ".{3}", "PERSONAFIS",
					"OperacionGeneral", "MUN_ENUM", "5.65", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField65);

			// (66) <Origen_MunicipioExtranjero>
			ReportField reportField66 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_MunicipioExtranjero",
					new BigInteger("66"), ".{1,80}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "5.66", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField66);

			// (67) <Origen_CodPostal>
			ReportField reportField67 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_CodPostal",
					new BigInteger("67"), ".{1,5}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "5.67", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField67);

			// (68) <Origen_CodPostalExtrajero>
			ReportField reportField68 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_CodPostalExtrajero",
					new BigInteger("68"), ".{1,8}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "5.68", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField68);

			// (69) <Origen_NumApartado>
			ReportField reportField69 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_NumApartado", new BigInteger(
							"69"), ".{1,12}", "PERSONAFIS", "OperacionGeneral",
					"xs:string", "5.69", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField69);

			// (70) <Origen_Localidad>
			ReportField reportField70 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_Localidad",
					new BigInteger("70"), ".{1,80}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "5.70", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField70);

			// (71) <Origen_Telefono>
			ReportField reportField71 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_Telefono",
					new BigInteger("71"), ".{1,20}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "5.71", "1,n",
					versionField, null, null, null, versionAdmin);
			reportField71.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField71);

			// (72) <Origen_Relacion>
			ReportField reportField72 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Origen_Relacion",
					new BigInteger("72"), ".{1}", "PERSONAFIS",
					"OperacionGeneral", "REL_PER_CUENTA_ENUM", "5.72", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField72);

			// //////////////////////////////////
			// Destino

			// (73) <Destino_CDPERSONA>
			ReportField reportField73 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_CDPERSONA", new BigInteger(
							"73"), ".{1,10}", "PERSONAFIS", "OperacionGeneral",
					"xs:string", "6.73", "1,n", versionField, null, null, null,
					versionAdmin);
			reportField73.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField73);

			// (74) <Destino_Nombre>
			ReportField reportField74 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_Nombre", new BigInteger("74"),
					".{1,80}", "PERSONAFIS", "OperacionGeneral", "xs:string",
					"6.74", "0,n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField74);

			// (75) <Destino_PrimerApellido>
			ReportField reportField75 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_PrimerApellido",
					new BigInteger("75"), ".{1,40}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "6.75", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField75);

			// (76) <Destino_SegundoApellido>
			ReportField reportField76 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_SegundoApellido",
					new BigInteger("76"), ".{1,40}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "6.76", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField76);

			// (77) <Destino_Pais>
			ReportField reportField77 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_Pais", new BigInteger("77"),
					".{3}", "PERSONAFIS", "OperacionGeneral", "PAIS_ENUM",
					"6.77", "0,n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField77);

			// (78) <Destino_PaisExpedicion>
			ReportField reportField78 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_PaisExpedicion",
					new BigInteger("78"), ".{3}", "PERSONAFIS",
					"OperacionGeneral", "PAIS_ENUM", "6.78", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField78);

			// (79) <Destino_TipoDocIdentif>
			ReportField reportField79 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_TipoDocIdentif",
					new BigInteger("79"), ".{1}", "PERSONAFIS",
					"OperacionGeneral", "TIPDIPF_ENUM", "6.79", "1,n",
					versionField, null, null, null, versionAdmin);
			reportField79.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField79);

			// (80) <Destino_NumDocIdentif>
			ReportField reportField80 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_NumDocIdentif",
					new BigInteger("80"), ".{1,15}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "6.80", "1,n",
					versionField, null, null, null, versionAdmin);
			reportField80.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField80);

			// (81) <Destino_TipoVia>
			ReportField reportField81 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_TipoVia",
					new BigInteger("81"), ".{1,2}", "PERSONAFIS",
					"OperacionGeneral", "TIPVIA_ENUM", "6.81", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField81);

			// (82) <Destino_NombreVia>
			ReportField reportField82 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_NombreVia", new BigInteger(
							"82"), ".{1,150}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "6.82", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField82);

			// (83) <Destino_NumeroVia>
			ReportField reportField83 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_NumeroVia", new BigInteger(
							"83"), ".{1,5}", "PERSONAFIS", "OperacionGeneral",
					"xs:string", "6.83", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField83);

			// (84) <Destino_OtrosDatos>
			ReportField reportField84 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_OtrosDatos", new BigInteger(
							"84"), ".{1,50}", "PERSONAFIS", "OperacionGeneral",
					"xs:string", "6.84", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField84);

			// (85) <Destino_Pais>
			ReportField reportField85 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_Pais", new BigInteger("85"),
					".{3}", "PERSONAFIS", "OperacionGeneral", "PAIS_ENUM",
					"6.85", "1,n", versionField, null, null, null, versionAdmin);
			reportField85.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField85);

			// (86) <Destino_Provincia>
			ReportField reportField86 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_Provincia", new BigInteger(
							"86"), ".{1,2}", "PERSONAFIS", "OperacionGeneral",
					"PROV_ENUM", "6.86", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField86);

			// (87) <Destino_ProvinciaExtranjera>
			ReportField reportField87 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_ProvinciaExtranjera",
					new BigInteger("87"), ".{1,80}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "6.87", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField87);

			// (88) <Destino_Municipio>
			ReportField reportField88 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_Municipio", new BigInteger(
							"88"), ".{3}", "PERSONAFIS", "OperacionGeneral",
					"MUN_ENUM", "6.88", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField88);

			// (89) <Destino_MunicipioExtranjero>
			ReportField reportField89 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_MunicipioExtranjero",
					new BigInteger("89"), ".{1,80}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "6.89", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField89);

			// (90) <Destino_CodPostal>
			ReportField reportField90 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_CodPostal", new BigInteger(
							"90"), ".{1,5}", "PERSONAFIS", "OperacionGeneral",
					"xs:string", "6.90", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField90);

			// (91) <Destino_CodPostalExtrajero>
			ReportField reportField91 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_CodPostalExtrajero",
					new BigInteger("91"), ".{1,8}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "6.91", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField91);

			// (92) <Destino_NumApartado>
			ReportField reportField92 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_NumApartado", new BigInteger(
							"92"), ".{1,12}", "PERSONAFIS", "OperacionGeneral",
					"xs:string", "6.92", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField92);

			// (93) <Destino_Localidad>
			ReportField reportField93 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_Localidad", new BigInteger(
							"93"), ".{1,80}", "PERSONAFIS", "OperacionGeneral",
					"xs:string", "6.93", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField93);

			// (94) <Destino_Telefono>
			ReportField reportField94 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_Telefono",
					new BigInteger("94"), ".{1,20}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "6.94", "1,n",
					versionField, null, null, null, versionAdmin);
			reportField94.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField94);

			// (95) <Destino_Relacion>
			ReportField reportField95 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_Relacion",
					new BigInteger("95"), ".{1}", "PERSONAFIS",
					"OperacionGeneral", "REL_PER_CUENTA_ENUM", "6.95", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField95);

			// ////////////////////////////////////////////////////////

			// (96) <Destino_CDPERSONA>
			ReportField reportField96 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_CDPERSONA", new BigInteger(
							"96"), ".{1,10}", "PERSONAJUR", "OperacionGeneral",
					"xs:string", "7.96", "1,n", versionField, null, null, null,
					versionAdmin);
			reportField96.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField96);

			// (97) <Destino_RazonSocial>
			ReportField reportField97 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_RazonSocial", new BigInteger(
							"97"), ".{1,80}", "PERSONAJUR", "OperacionGeneral",
					"xs:string", "7.97", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField97);

			// (98) <Destino_PaisNacionalidad>
			ReportField reportField98 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_PaisNacionalidad",
					new BigInteger("98"), ".{3}", "PERSONAJUR",
					"OperacionGeneral", "PAIS_ENUM", "7.98", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField98);

			// (99) <Destino_TipoDocIdentif>
			ReportField reportField99 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_TipoDocIdentif",
					new BigInteger("99"), ".{2}", "PERSONAJUR",
					"OperacionGeneral", "TIPDIPJ_ENUM", "7.99", "1,n",
					versionField, null, null, null, versionAdmin);
			reportField99.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField99);

			// (100) <Destino_PaisExpedicion>
			ReportField reportField100 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_PaisExpedicion",
					new BigInteger("100"), ".{3}", "PERSONAJUR",
					"OperacionGeneral", "PAIS_ENUM", "7.100", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField100);

			// (101) <Destino_NumDocIdentif>
			ReportField reportField101 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_NumDocIdentif",
					new BigInteger("101"), ".{1,15}", "PERSONAJUR",
					"OperacionGeneral", "xs:string", "7.101", "1,n",
					versionField, null, null, null, versionAdmin);
			reportField101.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField101);

			// (102) <Destino_TipoVia>
			ReportField reportField102 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_TipoVia",
					new BigInteger("102"), ".{1,2}", "PERSONAJUR",
					"OperacionGeneral", "TIPVIA_ENUM", "7.102", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField102);

			// (103) <Destino_NombreVia>
			ReportField reportField103 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_NombreVia", new BigInteger(
							"103"), ".{1,150}", "PERSONAJUR",
					"OperacionGeneral", "xs:string", "7.103", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField103);

			// (104) <Destino_NumeroVia>
			ReportField reportField104 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_NumeroVia", new BigInteger(
							"104"), ".{1,5}", "PERSONAJUR", "OperacionGeneral",
					"xs:string", "7.104", "0,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField104);

			// (105) <Destino_OtrosDatos>
			ReportField reportField105 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_OtrosDatos", new BigInteger(
							"105"), ".{1,50}", "PERSONAJUR",
					"OperacionGeneral", "xs:string", "7.105", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField105);

			// (106) <Destino_Pais>
			ReportField reportField106 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_Pais", new BigInteger("106"),
					".{3}", "PERSONAJUR", "OperacionGeneral", "PAIS_ENUM",
					"7.106", "1,n", versionField, null, null, null,
					versionAdmin);
			reportField106.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField106);

			// (107) <Destino_Provincia>
			ReportField reportField107 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_Provincia", new BigInteger(
							"107"), ".{3}", "PERSONAFIS", "OperacionGeneral",
					"PROV_ENUM", "7.107", "0,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField107);

			// (108) <Destino_ProvinciaExtranjera>
			ReportField reportField108 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_ProvinciaExtranjera",
					new BigInteger("108"), ".{1,80}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "7.108", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField108);

			// (109) <Destino_Municipio>
			ReportField reportField109 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_Municipio", new BigInteger(
							"109"), ".{3}", "PERSONAFIS", "OperacionGeneral",
					"MUN_ENUM", "7.109", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField109);

			// (110) <Destino_MunicipioExtranjero>
			ReportField reportField110 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_MunicipioExtranjero",
					new BigInteger("110"), ".{1,80}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "7.110", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField110);

			// (111) <Destino_CodPostal>
			ReportField reportField111 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_CodPostal", new BigInteger(
							"111"), ".{1,5}", "PERSONAFIS", "OperacionGeneral",
					"xs:string", "7.111", "0,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField111);

			// (112) <Destino_CodPostalExtrajero>
			ReportField reportField112 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_CodPostalExtrajero",
					new BigInteger("112"), ".{1,8}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "7.112", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField112);

			// (113) <Destino_NumApartado>
			ReportField reportField113 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_NumApartado", new BigInteger(
							"113"), ".{1,12}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "7.113", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField113);

			// (114) <Destino_Localidad>
			ReportField reportField114 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_Localidad", new BigInteger(
							"114"), ".{1,80}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "7.114", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField114);

			// (115) <Destino_Telefono>
			ReportField reportField115 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_Telefono", new BigInteger(
							"115"), ".{1,20}", "PERSONAFIS",
					"OperacionGeneral", "xs:string", "7.115", "1,n",
					versionField, null, null, null, versionAdmin);
			reportField115.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField115);

			// (116) <Destino_Relacion>
			ReportField reportField116 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Destino_Relacion", new BigInteger(
							"116"), ".{1}", "PERSONAFIS", "OperacionGeneral",
					"REL_PER_CUENTA_ENUM", "7.116", "0,n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField116);

			// TODO middle

			// //////////////////////////////////
			// Operacion8_20

			// (117) <NumOperacion>
			ReportField reportField117 = new ReportField(reportCatalog,
					reportFieldx2, "N", "NumOperacion", new BigInteger("117"),
					"[0-9]{1,5}", "CABECERAOP", "Operacion8_20", "xs:int",
					"8.117", "1,n", versionField, null, null, null,
					versionAdmin);
			reportField117.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField117);

			// (118) <TipoOperacion>
			ReportField reportField118 = new ReportField(reportCatalog,
					reportFieldx2, "N", "TipoOperacion", new BigInteger("118"),
					"[0-9]{1,5}", "CABECERAOP", "Operacion8_20", "xs:int",
					"8.118", "1,n", versionField, null, null, null,
					versionAdmin);
			reportField118.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField118);

			// (119) <Caracteristica>
			ReportField reportField119 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Caracteristica",
					new BigInteger("119"), ".{1}", "CABECERAOP",
					"Operacion8_20", "CARACTERISTICAS_ENUM", "8.119", "1,n",
					versionField, null, null, null, versionAdmin);
			reportField119.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField119);

			// (120) <FechaOperacion>
			ReportField reportField120 = new ReportField(
					reportCatalog,
					reportFieldx2,
					"D",
					"FechaOperacion",
					new BigInteger("120"),
					"(10|20|[0-2][1-9]|[3][0-1])\\/([0][1-9]|[1][0-2])\\/(19[0-9]{2}|20[0-9]{2})",
					"CABECERAOP", "Operacion8_20", "FECHA_GEN", "8.120", "1,n",
					versionField, null, null, null, versionAdmin);
			reportField120.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField120);

			// (121) <HoraOperacion>
			ReportField reportField121 = new ReportField(reportCatalog,
					reportFieldx2, "D", "HoraOperacion", new BigInteger("121"),
					"([0-1][0-9]|[2][0-3]):([0-5][0-9]):([0-5][0-9])",
					"CABECERAOP", "Operacion8_20", "HORA", "8.121", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField121);

			// (122) <Importe>
			ReportField reportField122 = new ReportField(reportCatalog,
					reportFieldx2, "N", "Importe", new BigInteger("122"),
					"[0-9]{1,16}(\\.[0-9]{1,2})?", "CABECERAOP",
					"Operacion8_20", "xs:decimal", "8.122", "1,n",
					versionField, null, null, null, versionAdmin);
			reportField122.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField122);

			// (123) <Divisa>
			ReportField reportField123 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Divisa", new BigInteger("123"),
					".{1,3}", "CABECERAOP", "Operacion8_20", "DIVISA_ENUM",
					"8.123", "1,n", versionField, null, null, null,
					versionAdmin);
			reportField123.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField123);

			// (124) <ImporteEnEuros>
			ReportField reportField124 = new ReportField(reportCatalog,
					reportFieldx2, "N", "ImporteEnEuros",
					new BigInteger("124"), "[0-9]{1,16}(\\.[0-9]{1,2})?",
					"CABECERAOP", "Operacion8_20", "xs:decimal", "8.124",
					"0,n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField124);

			// (125) <PaisOrigen>
			ReportField reportField125 = new ReportField(reportCatalog,
					reportFieldx2, "A", "PaisOrigen", new BigInteger("125"),
					".{3}", "CABECERAOP", "Operacion8_20", "PAIS_ENUM",
					"8.125", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField125);

			// (126) <ProvinciaOrigen>
			ReportField reportField126 = new ReportField(reportCatalog,
					reportFieldx2, "A", "ProvinciaOrigen",
					new BigInteger("126"), ".{1,2}", "CABECERAOP",
					"Operacion8_20", "PROV_ENUM", "8.126", "0,n", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField126);

			// (127) <ProvinciaOrigenEx>
			ReportField reportField127 = new ReportField(reportCatalog,
					reportFieldx2, "A", "ProvinciaOrigenEx", new BigInteger(
							"127"), ".{1,80}", "CABECERAOP", "Operacion8_20",
					"xs:string", "8.127", "0,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField127);

			// (128) <MunicipioOrigen>
			ReportField reportField128 = new ReportField(reportCatalog,
					reportFieldx2, "A", "MunicipioOrigen",
					new BigInteger("128"), ".{1,3}", "CABECERAOP",
					"Operacion8_20", "MUN_ENUM", "8.128", "0,n", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField128);

			// (129) <MunicipioOrigenEx>
			ReportField reportField129 = new ReportField(reportCatalog,
					reportFieldx2, "A", "MunicipioOrigenEx", new BigInteger(
							"129"), ".{1,80}", "CABECERAOP", "Operacion8_20",
					"xs:string", "8.129", "0,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField129);

			// (130) <PaisDestino>
			ReportField reportField130 = new ReportField(reportCatalog,
					reportFieldx2, "A", "PaisDestino", new BigInteger("130"),
					".{3}", "CABECERAOP", "Operacion8_20", "PAIS_ENUM",
					"8.130", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField130);

			// (131) <ProvinciaDestino>
			ReportField reportField131 = new ReportField(reportCatalog,
					reportFieldx2, "A", "ProvinciaDestino", new BigInteger(
							"131"), ".{1,2}", "CABECERAOP", "Operacion8_20",
					"PROV_ENUM", "8.131", "0,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField131);

			// (132) <ProvinciaDestinoEx>
			ReportField reportField132 = new ReportField(reportCatalog,
					reportFieldx2, "A", "ProvinciaDestinoEx", new BigInteger(
							"132"), ".{1,80}", "CABECERAOP", "Operacion8_20",
					"xs:string", "8.132", "0,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField132);

			// (133) <MunicipioDestino>
			ReportField reportField133 = new ReportField(reportCatalog,
					reportFieldx2, "A", "MunicipioDestino", new BigInteger(
							"133"), ".{1,3}", "CABECERAOP", "Operacion8_20",
					"MUN_ENUM", "8.133", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField133);

			// (134) <MunicipioDestinoEx>
			ReportField reportField134 = new ReportField(reportCatalog,
					reportFieldx2, "A", "MunicipioDestinoEx", new BigInteger(
							"134"), ".{1,80}", "CABECERAOP", "Operacion8_20",
					"xs:string", "8.134", "0,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField134);

			// (135) <Oficina>
			ReportField reportField135 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Oficina", new BigInteger("135"),
					"[0-9]{4}", "CABECERAOP", "Operacion8_20", "xs:string",
					"8.135", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField135);

			// (136) <NumeroCuenta>
			ReportField reportField136 = new ReportField(reportCatalog,
					reportFieldx2, "A", "NumeroCuenta", new BigInteger("136"),
					"[0-9]{20}", "CABECERAOP", "Operacion8_20", "xs:string",
					"8.136", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField136);

			// //////////////////////////////////

			// (137) <CDPERSONA>
			ReportField reportField137 = new ReportField(reportCatalog,
					reportFieldx2, "A", "CDPERSONA", new BigInteger("137"),
					".{1,10}", "PERSONAFIS", "Operacion8_20", "xs:string",
					"9.137", "1,n", versionField, null, null, null,
					versionAdmin);
			reportField137.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField137);

			// (138) <Nombre>
			ReportField reportField138 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Nombre", new BigInteger("138"),
					".{1,80}", "PERSONAFIS", "Operacion8_20", "xs:string",
					"9.138", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField138);

			// (139) <PrimerApellido>
			ReportField reportField139 = new ReportField(reportCatalog,
					reportFieldx2, "A", "PrimerApellido",
					new BigInteger("139"), ".{1,40}", "PERSONAFIS",
					"Operacion8_20", "xs:string", "9.139", "0,n", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField139);

			// (140) <SegundoApellido>
			ReportField reportField140 = new ReportField(reportCatalog,
					reportFieldx2, "A", "SegundoApellido",
					new BigInteger("140"), ".{1,40}", "PERSONAFIS",
					"Operacion8_20", "xs:string", "9.140", "0,n", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField140);

			// (141) <Pais>
			ReportField reportField141 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Pais", new BigInteger("141"), ".{3}",
					"PERSONAFIS", "Operacion8_20", "PAIS_ENUM", "9.141", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField141);

			// (142) <PaisExpedicion>
			ReportField reportField142 = new ReportField(reportCatalog,
					reportFieldx2, "A", "PaisExpedicion",
					new BigInteger("142"), ".{3}", "PERSONAFIS",
					"Operacion8_20", "PAIS_ENUM", "9.142", "0,n", versionField,
					null, null, null, versionAdmin);
			reportFieldDAO.create(reportField142);

			// (143) <TipoDocIdentif>
			ReportField reportField143 = new ReportField(reportCatalog,
					reportFieldx2, "A", "TipoDocIdentif",
					new BigInteger("143"), ".{1}", "PERSONAFIS",
					"Operacion8_20", "TIPDIPF_ENUM", "9.143", "1,n",
					versionField, null, null, null, versionAdmin);
			reportField143.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField143);

			// (144) <NumDocIdentif>
			ReportField reportField144 = new ReportField(reportCatalog,
					reportFieldx2, "A", "NumDocIdentif", new BigInteger("144"),
					".{1,15}", "PERSONAFIS", "Operacion8_20", "xs:string",
					"9.144", "1,n", versionField, null, null, null,
					versionAdmin);
			reportField144.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField144);

			// (145) <TipoVia>
			ReportField reportField145 = new ReportField(reportCatalog,
					reportFieldx2, "A", "TipoVia", new BigInteger("145"),
					".{1,2}", "PERSONAFIS", "Operacion8_20", "TIPVIA_ENUM",
					"9.145", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField145);

			// (146) <NombreVia>
			ReportField reportField146 = new ReportField(reportCatalog,
					reportFieldx2, "A", "NombreVia", new BigInteger("146"),
					".{1,150}", "PERSONAFIS", "Operacion8_20", "xs:string",
					"9.146", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField146);

			// (147) <NumeroVia>
			ReportField reportField147 = new ReportField(reportCatalog,
					reportFieldx2, "A", "NumeroVia", new BigInteger("147"),
					".{1,5}", "PERSONAFIS", "Operacion8_20", "xs:string",
					"9.147", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField147);

			// (148) <OtrosDatos>
			ReportField reportField148 = new ReportField(reportCatalog,
					reportFieldx2, "A", "OtrosDatos", new BigInteger("148"),
					".{1,50}", "PERSONAFIS", "Operacion8_20", "xs:string",
					"9.148", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField148);

			// (149) <Pais>
			ReportField reportField149 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Pais", new BigInteger("149"), ".{3}",
					"PERSONAFIS", "Operacion8_20", "PAIS_ENUM", "9.149", "1,n",
					versionField, null, null, null, versionAdmin);
			reportField149.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField149);

			// (150) <Provincia>
			ReportField reportField150 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Provincia", new BigInteger("150"),
					".{1,2}", "PERSONAFIS", "Operacion8_20", "PROV_ENUM",
					"9.150", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField150);

			// (151) <ProvinciaExtranjera>
			ReportField reportField151 = new ReportField(reportCatalog,
					reportFieldx2, "A", "ProvinciaExtranjera", new BigInteger(
							"151"), ".{1,80}", "PERSONAFIS", "Operacion8_20",
					"xs:string", "9.151", "0,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField151);

			// (152) <Municipio>
			ReportField reportField152 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Municipio", new BigInteger("152"),
					".{3}", "PERSONAFIS", "Operacion8_20", "MUN_ENUM", "9.152",
					"0,n", versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField152);

			// (153) <MunicipioExtranjero>
			ReportField reportField153 = new ReportField(reportCatalog,
					reportFieldx2, "A", "MunicipioExtranjero", new BigInteger(
							"153"), ".{1,80}", "PERSONAFIS", "Operacion8_20",
					"xs:string", "9.153", "0,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField153);

			// (154) <CodPostal>
			ReportField reportField154 = new ReportField(reportCatalog,
					reportFieldx2, "A", "CodPostal", new BigInteger("154"),
					".{1,5}", "PERSONAFIS", "Operacion8_20", "xs:string",
					"9.154", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField154);

			// (155) <CodPostalExtrajero>
			ReportField reportField155 = new ReportField(reportCatalog,
					reportFieldx2, "A", "CodPostalExtrajero", new BigInteger(
							"155"), ".{1,8}", "PERSONAFIS", "Operacion8_20",
					"xs:string", "9.155", "0,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField155);

			// (156) <NumApartado>
			ReportField reportField156 = new ReportField(reportCatalog,
					reportFieldx2, "A", "NumApartado", new BigInteger("156"),
					".{1,12}", "PERSONAFIS", "Operacion8_20", "xs:string",
					"9.156", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField156);

			// (157) <Localidad>
			ReportField reportField157 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Localidad", new BigInteger("157"),
					".{1,80}", "PERSONAFIS", "Operacion8_20", "xs:string",
					"9.157", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField157);

			// (158) <Telefono>
			ReportField reportField158 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Telefono", new BigInteger("158"),
					".{1,20}", "PERSONAFIS", "Operacion8_20", "xs:string",
					"9.158", "1,n", versionField, null, null, null,
					versionAdmin);
			reportField158.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField158);

			// (159) <Relacion>
			ReportField reportField159 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Relacion", new BigInteger("159"),
					".{1}", "PERSONAFIS", "Operacion8_20",
					"REL_PER_CUENTA_ENUM", "9.159", "0,n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField159);

			// ////////////////////////////////////////////////////////

			// (160) <CDPERSONA>
			ReportField reportField160 = new ReportField(reportCatalog,
					reportFieldx2, "A", "CDPERSONA", new BigInteger("160"),
					".{1,10}", "PERSONAJUR", "Operacion8_20", "xs:string",
					"10.160", "1,n", versionField, null, null, null,
					versionAdmin);
			reportField160.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField160);

			// (161) <RazonSocial>
			ReportField reportField161 = new ReportField(reportCatalog,
					reportFieldx2, "A", "RazonSocial", new BigInteger("161"),
					".{1,80}", "PERSONAJUR", "Operacion8_20", "xs:string",
					"10.161", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField161);

			// (162) <PaisNacionalidad>
			ReportField reportField162 = new ReportField(reportCatalog,
					reportFieldx2, "A", "PaisNacionalidad", new BigInteger(
							"162"), ".{3}", "PERSONAJUR", "Operacion8_20",
					"PAIS_ENUM", "10.162", "0,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField162);

			// (163) <TipoDocIdentif>
			ReportField reportField163 = new ReportField(reportCatalog,
					reportFieldx2, "A", "TipoDocIdentif",
					new BigInteger("163"), ".{2}", "PERSONAJUR",
					"Operacion8_20", "TIPDIPJ_ENUM", "10.163", "1,n",
					versionField, null, null, null, versionAdmin);
			reportField163.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField163);

			// (164) <PaisExpedicion>
			ReportField reportField164 = new ReportField(reportCatalog,
					reportFieldx2, "A", "PaisExpedicion",
					new BigInteger("164"), ".{3}", "PERSONAJUR",
					"Operacion8_20", "PAIS_ENUM", "10.164", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField164);

			// (165) <NumDocIdentif>
			ReportField reportField165 = new ReportField(reportCatalog,
					reportFieldx2, "A", "NumDocIdentif", new BigInteger("165"),
					".{1,15}", "PERSONAJUR", "Operacion8_20", "xs:string",
					"10.165", "1,n", versionField, null, null, null,
					versionAdmin);
			reportField165.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField165);

			// (166) <TipoVia>
			ReportField reportField166 = new ReportField(reportCatalog,
					reportFieldx2, "A", "TipoVia", new BigInteger("166"),
					".{1,2}", "PERSONAJUR", "Operacion8_20", "TIPVIA_ENUM",
					"10.166", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField166);

			// (167) <NombreVia>
			ReportField reportField167 = new ReportField(reportCatalog,
					reportFieldx2, "A", "NombreVia", new BigInteger("167"),
					".{1,150}", "PERSONAJUR", "Operacion8_20", "xs:string",
					"10.167", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField167);

			// (168) <NumeroVia>
			ReportField reportField168 = new ReportField(reportCatalog,
					reportFieldx2, "A", "NumeroVia", new BigInteger("168"),
					".{1,5}", "PERSONAJUR", "Operacion8_20", "xs:string",
					"10.168", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField168);

			// (169) <OtrosDatos>
			ReportField reportField169 = new ReportField(reportCatalog,
					reportFieldx2, "A", "OtrosDatos", new BigInteger("169"),
					".{1,50}", "PERSONAJUR", "Operacion8_20", "xs:string",
					"10.169", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField169);

			// (170) <Pais>
			ReportField reportField170 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Pais", new BigInteger("170"), ".{3}",
					"PERSONAJUR", "Operacion8_20", "PAIS_ENUM", "10.170",
					"1,n", versionField, null, null, null, versionAdmin);
			reportField170.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField170);

			// (171) <Provincia>
			ReportField reportField171 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Provincia", new BigInteger("171"),
					".{1,2}", "PERSONAFIS", "Operacion8_20", "PROV_ENUM",
					"10.171", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField171);

			// (172) <ProvinciaExtranjera>
			ReportField reportField172 = new ReportField(reportCatalog,
					reportFieldx2, "A", "ProvinciaExtranjera", new BigInteger(
							"172"), ".{1,80}", "PERSONAFIS", "Operacion8_20",
					"xs:string", "10.172", "0,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField172);

			// (173) <Municipio>
			ReportField reportField173 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Municipio", new BigInteger("173"),
					".{3}", "PERSONAFIS", "Operacion8_20", "MUN_ENUM",
					"10.173", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField173);

			// (174) <MunicipioExtranjero>
			ReportField reportField174 = new ReportField(reportCatalog,
					reportFieldx2, "A", "MunicipioExtranjero", new BigInteger(
							"174"), ".{1,80}", "PERSONAFIS", "Operacion8_20",
					"xs:string", "10.174", "0,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField174);

			// (175) <CodPostal>
			ReportField reportField175 = new ReportField(reportCatalog,
					reportFieldx2, "A", "CodPostal", new BigInteger("175"),
					".{1,5}", "PERSONAFIS", "Operacion8_20", "xs:string",
					"10.175", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField175);

			// (176) <CodPostalExtrajero>
			ReportField reportField176 = new ReportField(reportCatalog,
					reportFieldx2, "A", "CodPostalExtrajero", new BigInteger(
							"176"), ".{1,8}", "PERSONAFIS", "Operacion8_20",
					"xs:string", "10.176", "0,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportField176);

			// (177) <NumApartado>
			ReportField reportField177 = new ReportField(reportCatalog,
					reportFieldx2, "A", "NumApartado", new BigInteger("177"),
					".{1,12}", "PERSONAFIS", "Operacion8_20", "xs:string",
					"10.177", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField177);

			// (178) <Localidad>
			ReportField reportField178 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Localidad", new BigInteger("178"),
					".{1,80}", "PERSONAFIS", "Operacion8_20", "xs:string",
					"10.178", "0,n", versionField, null, null, null,
					versionAdmin);
			reportFieldDAO.create(reportField178);

			// (179) <Telefono>
			ReportField reportField179 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Telefono", new BigInteger("179"),
					".{1,20}", "PERSONAFIS", "Operacion8_20", "xs:string",
					"10.179", "1,n", versionField, null, null, null,
					versionAdmin);
			reportField179.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField179);

			// (180) <Relacion>
			ReportField reportField180 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Relacion", new BigInteger("180"),
					".{1}", "PERSONAFIS", "Operacion8_20",
					"REL_PER_CUENTA_ENUM", "10.180", "0,n", versionField, null,
					null, null, versionAdmin);
			reportFieldDAO.create(reportField180);

			// //////////////////////////////////////////////////

			// TODO fileConfig

			// fileConfig DMO DP

			// NEED department to link of a fileConfig

			DepartmentDAO departmentDAO = (DepartmentDAO) applicationContext
					.getBean("departmentDAO");

			Department department1 = new Department();
			department1.setDepartmentCode("AML");
			List<Department> departments = departmentDAO
					.findByExample(department1);
			if (departments.size() > 0) {
				department1 = departments.get(0);
			}

			FileConfigDAO fileConfigDAO = (FileConfigDAO) applicationContext
					.getBean("fileConfigDAO");

			FileColumDAO fileColumDAO = (FileColumDAO) applicationContext
					.getBean("fileColumDAO");

			// //////////////////////////////////////////////////
			// DatosEntrada

			FileConfig fileConfig0 = new FileConfig(department1, "DMO",
					nameDatosEntrada, ";", "SIMPLE", "*", null, false, null,
					null, versionAdmin);

			List<FileColum> fileColums0 = new ArrayList<FileColum>();

			fileColums0.add(new FileColum(reportField1, fileConfig0, "type",
					new BigDecimal(0), "ID", "", "format", null, versionAdmin));
			fileColums0.add(new FileColum(reportField2, fileConfig0, "type",
					new BigDecimal(1), "NombreEntidad", "", "format", null,
					versionAdmin));
			fileColums0.add(new FileColum(reportField3, fileConfig0, "type",
					new BigDecimal(2), "CIFNIFEntidad", "", "format", null,
					versionAdmin));
			fileColums0.add(new FileColum(reportField4, fileConfig0, "type",
					new BigDecimal(3), "MesDeclaracion", "", "format", null,
					versionAdmin));
			fileColums0.add(new FileColum(reportField5, fileConfig0, "type",
					new BigDecimal(4), "Representante", "", "format", null,
					versionAdmin));
			fileColums0.add(new FileColum(reportField6, fileConfig0, "type",
					new BigDecimal(5), "FechaDeclaracion", "", "format", null,
					versionAdmin));

			fileConfigDAO.create(fileConfig0);

			for (FileColum fileColumExample : fileColums0) {
				fileColumDAO.create(fileColumExample);
			}

			// //////////////////////////////////////////////////
			// OperacionGeneral

			FileConfig fileConfig1 = new FileConfig(department1, "DMO",
					nameOperacionGeneral, ";", "SIMPLE", "*", null, false,
					null, null, versionAdmin);

			List<FileColum> fileColums1 = new ArrayList<FileColum>();

			// CABECERAOP
			fileColums1.add(new FileColum(reportField9, fileConfig1, "type",
					new BigDecimal(0), "NumOperacion", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField10, fileConfig1, "type",
					new BigDecimal(1), "TipoOperacion", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField11, fileConfig1, "type",
					new BigDecimal(2), "Caracteristica", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField12, fileConfig1, "type",
					new BigDecimal(3), "FechaOperacion", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField13, fileConfig1, "type",
					new BigDecimal(4), "HoraOperacion", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField14, fileConfig1, "type",
					new BigDecimal(5), "Importe", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField15, fileConfig1, "type",
					new BigDecimal(6), "Divisa", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField16, fileConfig1, "type",
					new BigDecimal(7), "ImporteEnEuros", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField17, fileConfig1, "type",
					new BigDecimal(8), "PaisOrigen", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField18, fileConfig1, "type",
					new BigDecimal(9), "ProvinciaOrigen", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField19, fileConfig1, "type",
					new BigDecimal(10), "ProvinciaOrigenEx", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField20, fileConfig1, "type",
					new BigDecimal(11), "MunicipioOrigen", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField21, fileConfig1, "type",
					new BigDecimal(12), "MunicipioOrigenEx", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField22, fileConfig1, "type",
					new BigDecimal(13), "PaisDestino", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField23, fileConfig1, "type",
					new BigDecimal(14), "ProvinciaDestino", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField24, fileConfig1, "type",
					new BigDecimal(15), "ProvinciaDestinoEx", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField25, fileConfig1, "type",
					new BigDecimal(16), "MunicipioDestino", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField26, fileConfig1, "type",
					new BigDecimal(17), "MunicipioDestinoEx", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField27, fileConfig1, "type",
					new BigDecimal(18), "Oficina", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField28, fileConfig1, "type",
					new BigDecimal(19), "NumeroCuenta", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));

			// Origen

			// Origen.PERSONAFIS
			fileColums1.add(new FileColum(reportField29, fileConfig1, "type",
					new BigDecimal(20), "CDPERSONA", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField30, fileConfig1, "type",
					new BigDecimal(21), "Nombre", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField31, fileConfig1, "type",
					new BigDecimal(22), "PrimerApellido", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField32, fileConfig1, "type",
					new BigDecimal(23), "SegundoApellido", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField33, fileConfig1, "type",
					new BigDecimal(24), "Pais", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField34, fileConfig1, "type",
					new BigDecimal(25), "PaisExpedicion", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField35, fileConfig1, "type",
					new BigDecimal(26), "TipoDocIdentif", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField36, fileConfig1, "type",
					new BigDecimal(27), "NumDocIdentif", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField37, fileConfig1, "type",
					new BigDecimal(28), "TipoVia", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField38, fileConfig1, "type",
					new BigDecimal(29), "NombreVia", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField39, fileConfig1, "type",
					new BigDecimal(30), "NumeroVia", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField40, fileConfig1, "type",
					new BigDecimal(31), "OtrosDatos", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField41, fileConfig1, "type",
					new BigDecimal(32), "Pais", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField42, fileConfig1, "type",
					new BigDecimal(33), "Provincia", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField43, fileConfig1, "type",
					new BigDecimal(34), "ProvinciaExtranjera", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField44, fileConfig1, "type",
					new BigDecimal(35), "Municipio", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField45, fileConfig1, "type",
					new BigDecimal(36), "MunicipioExtranjero", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField46, fileConfig1, "type",
					new BigDecimal(37), "CodPostal", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField47, fileConfig1, "type",
					new BigDecimal(38), "CodPostalExtrajero", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField48, fileConfig1, "type",
					new BigDecimal(39), "NumApartado", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField49, fileConfig1, "type",
					new BigDecimal(40), "Localidad", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField50, fileConfig1, "type",
					new BigDecimal(41), "Telefono", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField51, fileConfig1, "type",
					new BigDecimal(42), "Relacion", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));

			// Origen.PERSONAJUR
			fileColums1.add(new FileColum(reportField52, fileConfig1, "type",
					new BigDecimal(43), "CDPERSONA", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField53, fileConfig1, "type",
					new BigDecimal(44), "RazonSocial", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField54, fileConfig1, "type",
					new BigDecimal(45), "PaisNacionalidad", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField55, fileConfig1, "type",
					new BigDecimal(46), "TipoDocIdentif", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField56, fileConfig1, "type",
					new BigDecimal(47), "PaisExpedicion", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField57, fileConfig1, "type",
					new BigDecimal(48), "NumDocIdentif", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField58, fileConfig1, "type",
					new BigDecimal(49), "TipoVia", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField59, fileConfig1, "type",
					new BigDecimal(50), "NombreVia", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField60, fileConfig1, "type",
					new BigDecimal(51), "NumeroVia", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField61, fileConfig1, "type",
					new BigDecimal(52), "OtrosDatos", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField62, fileConfig1, "type",
					new BigDecimal(53), "Pais", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField63, fileConfig1, "type",
					new BigDecimal(54), "Provincia", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField64, fileConfig1, "type",
					new BigDecimal(55), "ProvinciaExtranjera", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField65, fileConfig1, "type",
					new BigDecimal(56), "Municipio", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField66, fileConfig1, "type",
					new BigDecimal(57), "MunicipioExtranjero", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField67, fileConfig1, "type",
					new BigDecimal(58), "CodPostal", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField68, fileConfig1, "type",
					new BigDecimal(59), "CodPostalExtrajero", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField69, fileConfig1, "type",
					new BigDecimal(60), "NumApartado", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField70, fileConfig1, "type",
					new BigDecimal(61), "Localidad", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField71, fileConfig1, "type",
					new BigDecimal(62), "Telefono", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField72, fileConfig1, "type",
					new BigDecimal(63), "Relacion", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));

			// Destino

			// Destino.PERSONAFIS
			fileColums1.add(new FileColum(reportField73, fileConfig1, "type",
					new BigDecimal(64), "CDPERSONA", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField74, fileConfig1, "type",
					new BigDecimal(65), "Nombre", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField75, fileConfig1, "type",
					new BigDecimal(66), "PrimerApellido", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField76, fileConfig1, "type",
					new BigDecimal(67), "SegundoApellido", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField77, fileConfig1, "type",
					new BigDecimal(68), "Pais", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField78, fileConfig1, "type",
					new BigDecimal(69), "PaisExpedicion", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField79, fileConfig1, "type",
					new BigDecimal(70), "TipoDocIdentif", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField80, fileConfig1, "type",
					new BigDecimal(71), "NumDocIdentif", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField81, fileConfig1, "type",
					new BigDecimal(72), "TipoVia", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField82, fileConfig1, "type",
					new BigDecimal(73), "NombreVia", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField83, fileConfig1, "type",
					new BigDecimal(74), "NumeroVia", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField84, fileConfig1, "type",
					new BigDecimal(75), "OtrosDatos", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField85, fileConfig1, "type",
					new BigDecimal(76), "Pais", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField86, fileConfig1, "type",
					new BigDecimal(77), "Provincia", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField87, fileConfig1, "type",
					new BigDecimal(78), "ProvinciaExtranjera", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField88, fileConfig1, "type",
					new BigDecimal(79), "Municipio", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField89, fileConfig1, "type",
					new BigDecimal(80), "MunicipioExtranjero", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField90, fileConfig1, "type",
					new BigDecimal(81), "CodPostal", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField91, fileConfig1, "type",
					new BigDecimal(82), "CodPostalExtrajero", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField92, fileConfig1, "type",
					new BigDecimal(83), "NumApartado", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField93, fileConfig1, "type",
					new BigDecimal(84), "Localidad", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField94, fileConfig1, "type",
					new BigDecimal(85), "Telefono", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField95, fileConfig1, "type",
					new BigDecimal(86), "Relacion", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));

			// Destino.PERSONAJUR
			fileColums1.add(new FileColum(reportField96, fileConfig1, "type",
					new BigDecimal(87), "CDPERSONA", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField97, fileConfig1, "type",
					new BigDecimal(88), "RazonSocial", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField98, fileConfig1, "type",
					new BigDecimal(89), "PaisNacionalidad", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField99, fileConfig1, "type",
					new BigDecimal(90), "TipoDocIdentif", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField100, fileConfig1, "type",
					new BigDecimal(91), "PaisExpedicion", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField101, fileConfig1, "type",
					new BigDecimal(92), "NumDocIdentif", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField102, fileConfig1, "type",
					new BigDecimal(93), "TipoVia", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField103, fileConfig1, "type",
					new BigDecimal(94), "NombreVia", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField104, fileConfig1, "type",
					new BigDecimal(95), "NumeroVia", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField105, fileConfig1, "type",
					new BigDecimal(96), "OtrosDatos", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField106, fileConfig1, "type",
					new BigDecimal(97), "Pais", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField107, fileConfig1, "type",
					new BigDecimal(98), "Provincia", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField108, fileConfig1, "type",
					new BigDecimal(99), "ProvinciaExtranjera", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField109, fileConfig1, "type",
					new BigDecimal(100), "Municipio", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField110, fileConfig1, "type",
					new BigDecimal(101), "MunicipioExtranjero", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField111, fileConfig1, "type",
					new BigDecimal(102), "CodPostal", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField112, fileConfig1, "type",
					new BigDecimal(103), "CodPostalExtrajero", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField113, fileConfig1, "type",
					new BigDecimal(104), "NumApartado", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField114, fileConfig1, "type",
					new BigDecimal(105), "Localidad", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField115, fileConfig1, "type",
					new BigDecimal(106), "Telefono", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums1.add(new FileColum(reportField116, fileConfig1, "type",
					new BigDecimal(107), "Relacion", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));

			fileConfigDAO.create(fileConfig1);

			for (FileColum fileColumExample : fileColums1) {
				fileColumDAO.create(fileColumExample);
			}

			// //////////////////////////////////////////////////
			// Operacion8_20

			FileConfig fileConfig2 = new FileConfig(department1, "DMO",
					nameOperacion820, ";", "SIMPLE", "*", null, false, null,
					null, versionAdmin);

			List<FileColum> fileColums2 = new ArrayList<FileColum>();

			// CABECERAOP
			fileColums2.add(new FileColum(reportField117, fileConfig2, "type",
					new BigDecimal(0), "NumOperacion", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField118, fileConfig2, "type",
					new BigDecimal(1), "TipoOperacion", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField119, fileConfig2, "type",
					new BigDecimal(2), "Caracteristica", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField120, fileConfig2, "type",
					new BigDecimal(3), "FechaOperacion", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField121, fileConfig2, "type",
					new BigDecimal(4), "HoraOperacion", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField122, fileConfig2, "type",
					new BigDecimal(5), "Importe", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField123, fileConfig2, "type",
					new BigDecimal(6), "Divisa", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField124, fileConfig2, "type",
					new BigDecimal(7), "ImporteEnEuros", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField125, fileConfig2, "type",
					new BigDecimal(8), "PaisOrigen", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField126, fileConfig2, "type",
					new BigDecimal(9), "ProvinciaOrigen", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField127, fileConfig2, "type",
					new BigDecimal(10), "ProvinciaOrigenEx", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField128, fileConfig2, "type",
					new BigDecimal(11), "MunicipioOrigen", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField129, fileConfig2, "type",
					new BigDecimal(12), "MunicipioOrigenEx", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField130, fileConfig2, "type",
					new BigDecimal(13), "PaisDestino", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField131, fileConfig2, "type",
					new BigDecimal(14), "ProvinciaDestino", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField132, fileConfig2, "type",
					new BigDecimal(15), "ProvinciaDestinoEx", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField133, fileConfig2, "type",
					new BigDecimal(16), "MunicipioDestino", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField134, fileConfig2, "type",
					new BigDecimal(17), "MunicipioDestinoEx", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField135, fileConfig2, "type",
					new BigDecimal(18), "Oficina", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField136, fileConfig2, "type",
					new BigDecimal(19), "NumeroCuenta", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));

			// PERSONAFIS
			fileColums2.add(new FileColum(reportField137, fileConfig2, "type",
					new BigDecimal(20), "CDPERSONA", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField138, fileConfig2, "type",
					new BigDecimal(21), "Nombre", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField139, fileConfig2, "type",
					new BigDecimal(22), "PrimerApellido", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField140, fileConfig2, "type",
					new BigDecimal(23), "SegundoApellido", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField141, fileConfig2, "type",
					new BigDecimal(24), "Pais", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField142, fileConfig2, "type",
					new BigDecimal(25), "PaisExpedicion", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField143, fileConfig2, "type",
					new BigDecimal(26), "TipoDocIdentif", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField144, fileConfig2, "type",
					new BigDecimal(27), "NumDocIdentif", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField145, fileConfig2, "type",
					new BigDecimal(28), "TipoVia", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField146, fileConfig2, "type",
					new BigDecimal(29), "NombreVia", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField147, fileConfig2, "type",
					new BigDecimal(30), "NumeroVia", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField148, fileConfig2, "type",
					new BigDecimal(31), "OtrosDatos", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField149, fileConfig2, "type",
					new BigDecimal(32), "Pais", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField150, fileConfig2, "type",
					new BigDecimal(33), "Provincia", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField151, fileConfig2, "type",
					new BigDecimal(34), "ProvinciaExtranjera", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField152, fileConfig2, "type",
					new BigDecimal(35), "Municipio", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField153, fileConfig2, "type",
					new BigDecimal(36), "MunicipioExtranjero", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField154, fileConfig2, "type",
					new BigDecimal(37), "CodPostal", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField155, fileConfig2, "type",
					new BigDecimal(38), "CodPostalExtrajero", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField156, fileConfig2, "type",
					new BigDecimal(39), "NumApartado", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField157, fileConfig2, "type",
					new BigDecimal(40), "Localidad", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField158, fileConfig2, "type",
					new BigDecimal(41), "Telefono", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField159, fileConfig2, "type",
					new BigDecimal(42), "Relacion", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));

			// PERSONAJUR
			fileColums2.add(new FileColum(reportField160, fileConfig2, "type",
					new BigDecimal(43), "CDPERSONA", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField161, fileConfig2, "type",
					new BigDecimal(44), "RazonSocial", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField162, fileConfig2, "type",
					new BigDecimal(45), "PaisNacionalidad", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField163, fileConfig2, "type",
					new BigDecimal(46), "TipoDocIdentif", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField164, fileConfig2, "type",
					new BigDecimal(47), "PaisExpedicion", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField165, fileConfig2, "type",
					new BigDecimal(48), "NumDocIdentif", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField166, fileConfig2, "type",
					new BigDecimal(49), "TipoVia", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField167, fileConfig2, "type",
					new BigDecimal(50), "NombreVia", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField168, fileConfig2, "type",
					new BigDecimal(51), "NumeroVia", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField169, fileConfig2, "type",
					new BigDecimal(52), "OtrosDatos", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField170, fileConfig2, "type",
					new BigDecimal(53), "Pais", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField171, fileConfig2, "type",
					new BigDecimal(54), "Provincia", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField172, fileConfig2, "type",
					new BigDecimal(55), "ProvinciaExtranjera", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField173, fileConfig2, "type",
					new BigDecimal(56), "Municipio", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField174, fileConfig2, "type",
					new BigDecimal(57), "MunicipioExtranjero", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField175, fileConfig2, "type",
					new BigDecimal(58), "CodPostal", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField176, fileConfig2, "type",
					new BigDecimal(59), "CodPostalExtrajero", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField177, fileConfig2, "type",
					new BigDecimal(60), "NumApartado", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField178, fileConfig2, "type",
					new BigDecimal(61), "Localidad", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField179, fileConfig2, "type",
					new BigDecimal(62), "Telefono", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));
			fileColums2.add(new FileColum(reportField180, fileConfig2, "type",
					new BigDecimal(63), "Relacion", "", "format",
					ReportUtilities.fileColumBlockRepe, versionAdmin));

			fileConfigDAO.create(fileConfig2);

			for (FileColum fileColumExample : fileColums2) {
				fileColumDAO.create(fileColumExample);
			}

		} catch (Exception e) {
			logger.error("Error in installation DMO_DP: " + e.getMessage());
		}

	}

}
