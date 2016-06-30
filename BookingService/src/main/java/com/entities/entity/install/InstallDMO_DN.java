package com.entities.entity.install;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.entities.dao.reportingtool.ReportCatalogDAO;
import com.entities.dao.reportingtool.ReportFieldDAO;
import com.entities.dictionary.ReportCatalogLevelEnum;
import com.entities.entity.reportingtool.ReportCatalog;
import com.entities.entity.reportingtool.ReportField;
import com.entities.utilities.hibernate.VersionAuditor;
import com.reportingtool.utilities.ReportUtilities;

public class InstallDMO_DN implements InstallAdapter {

	private ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(InstallDMO_DN.class);

	public InstallDMO_DN(ApplicationContext applicationContext) {
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
					ReportCatalogLevelEnum.DEPARTMENT.getReportLevel(), ReportUtilities.reportCatalogDMO_DN_Name, "", null, null, null, versionAdmin);

			ReportFieldDAO reportFieldDAO = (ReportFieldDAO) applicationContext
					.getBean("reportFieldDAO");

			ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) applicationContext
					.getBean("reportCatalogDAO");
			reportCatalogDAO.create(reportCatalog);

			ReportField reportFieldx1 = new ReportField(reportCatalog, null,
					"X", "DeclaracionNegativa", new BigInteger("0"), null,
					"", null, null, "1.0", "1,1", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportFieldx1);

			ReportField reportFieldx2 = new ReportField(reportCatalog,
					reportFieldx1, "X", "Operacion", new BigInteger("0"), null,
					"", null, null, "1.0", "1,n", versionField, null, null,
					null, versionAdmin);
			reportFieldDAO.create(reportFieldx2);

			// (1) <ID>
			ReportField reportField1 = new ReportField(reportCatalog,
					reportFieldx2, "A", "ID", new BigInteger("1"), ".{1,5}", "",
					"DatosEntrada", "xs:string", "1.01", "1,n",
					versionField, null, null, null, versionAdmin);
			reportField1.setReportFieldMandatory(true);
			reportFieldDAO.create(reportField1);
			
			// (2) <NombreEntidad>
			ReportField reportField2 = new ReportField(reportCatalog,
					reportFieldx2, "A", "NombreEntidad", new BigInteger("2"), ".{1,80}", "",
					"DatosEntrada", "xs:string", "1.02", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField2);

			// (3) <CIFNIFEntidad>
			ReportField reportField3 = new ReportField(reportCatalog,
					reportFieldx2, "A", "CIFNIFEntidad", new BigInteger("3"), ".{1,15}", "",
					"DatosEntrada", "xs:string", "1.03", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField3);
			
			// (4) <MesDeclaracion>
			// ReportField reportField4 = new ReportField(reportCatalog,
			// reportFieldx2, "D", "MesDeclaracion", new BigInteger("4"),
			// "(19[0-9]{2}|20[0-9]{2})/([0][1-9]|[1][0-2])", "",
			// "DatosEntrada", "yyyy/mm", "1.04", "0,n",
			// versionField, null, null, null, versionAdmin);
			// reportFieldDAO.create(reportField4);
			
			// (5) <Representante>
			ReportField reportField5 = new ReportField(reportCatalog,
					reportFieldx2, "A", "Representante", new BigInteger("5"), ".{1,160}", "",
					"DatosEntrada", "xs:string", "1.05", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField5);
			
			// (6) <FechaDeclaracion>
			ReportField reportField6 = new ReportField(reportCatalog,
					reportFieldx2, "D", "FechaDeclaracion", new BigInteger("6"), "(10|20|[0-2][1-9]|[3][0-1])\\/([0][1-9]|[1][0-2])\\/(19[0-9]{2}|20[0-9]{2})", "",
					"DatosEntrada", "FECHA_GEN", "1.06", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField6);
			
			////////////////////////////////////
			
			// (7) <FechaDesde>
			ReportField reportField7 = new ReportField(reportCatalog,
					reportFieldx2, "D", "FechaDesde", new BigInteger("7"), "(10|20|[0-2][1-9]|[3][0-1])\\/([0][1-9]|[1][0-2])\\/(19[0-9]{2}|20[0-9]{2})", "",
					"Periodo", "FECHA_GEN", "2.08", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField7);
			
			// (8) <FechaHasta>
			ReportField reportField8 = new ReportField(reportCatalog,
					reportFieldx2, "D", "FechaHasta", new BigInteger("8"), "(10|20|[0-2][1-9]|[3][0-1])\\/([0][1-9]|[1][0-2])\\/(19[0-9]{2}|20[0-9]{2})", "",
					"Periodo", "FECHA_GEN", "2.08", "0,n",
					versionField, null, null, null, versionAdmin);
			reportFieldDAO.create(reportField8);
			
			////////////////////////////////////


		} catch (Exception e) {
			logger.error("Error in installation DMO_DN: " + e.getMessage());
		}

	}

}
