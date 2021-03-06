package com.entities.entity.reportingtool;

// Generated 11-feb-2015 17:15:14 by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.entities.utilities.hibernate.VersionAuditor;
import com.entities.utilities.hibernate.VersionableAdapter;

/**
 * ReportCatalog generated by hbm2java
 */
@Entity
@Table(name = "T_REPORT_CATALOG")
public class ReportCatalog implements VersionableAdapter {

	private static final long serialVersionUID = 5873798867818892460L;
	
	private long id;
	private String reportVersion;
	private String reportLevel;
	private String reportCatalogName;
	private String reportCatalogDesc;
	private List<ReportCustom> reportCustoms = new ArrayList<ReportCustom>();
	private List<ReportField> reportFields = new ArrayList<ReportField>();
	private List<ReportExecution> reportExecutions = new ArrayList<ReportExecution>();
	private List<ReportSemantic> reportSemantics = new ArrayList<ReportSemantic>();

	@Embedded
	private VersionAuditor versionAuditor;
	@Version
	@Column(name = "VERSION", nullable = false)
	int version;

	public ReportCatalog() {
	}

	public ReportCatalog(long reportCatalogId, String reportVersion,
			String reportLevel, String reportCatalogName,
			VersionAuditor versionAuditor) {
		this.id = reportCatalogId;
		this.reportVersion = reportVersion;
		this.reportLevel = reportLevel;
		this.reportCatalogName = reportCatalogName;
		this.versionAuditor = versionAuditor;
	}

	public ReportCatalog(String reportVersion, String reportLevel,
			String reportCatalogName, String reportCatalogDesc,
			List<ReportCustom> reportCustoms, List<ReportField> reportFields,
			List<ReportExecution> reportExecutions, VersionAuditor versionAuditor) {
		this.reportVersion = reportVersion;
		this.reportLevel = reportLevel;
		this.reportCatalogName = reportCatalogName;
		this.reportCatalogDesc = reportCatalogDesc;
		this.reportCustoms = reportCustoms;
		this.reportFields = reportFields;
		this.reportExecutions = reportExecutions;
		this.versionAuditor = versionAuditor;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@SequenceGenerator(name = "GEN_REPORT_CATALOG", sequenceName = "SEQ_REPORT_CATALOG", initialValue = 1, allocationSize = 1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_REPORT_CATALOG")
	@Column(name = "REPORT_CATALOG_ID", unique = true, nullable = false, length = 10)
	public long getId() {
		return this.id;
	}

	public void setId(long reportCatalogId) {
		this.id = reportCatalogId;
	}

	@Column(name = "REPORT_VERSION", nullable = false, length = 10)
	public String getReportVersion() {
		return this.reportVersion;
	}

	public void setReportVersion(String reportVersion) {
		this.reportVersion = reportVersion;
	}

	@Column(name = "REPORT_LEVEL", nullable = false, length = 10)
	public String getReportLevel() {
		return this.reportLevel;
	}

	public void setReportLevel(String reportLevel) {
		this.reportLevel = reportLevel;
	}

	@Column(name = "REPORT_CATALOG_NAME", nullable = false, length = 100)
	public String getReportCatalogName() {
		return this.reportCatalogName;
	}

	public void setReportCatalogName(String reportCatalogName) {
		this.reportCatalogName = reportCatalogName;
	}

	@Column(name = "REPORT_CATALOG_DESC")
	public String getReportCatalogDesc() {
		return this.reportCatalogDesc;
	}

	public void setReportCatalogDesc(String reportCatalogDesc) {
		this.reportCatalogDesc = reportCatalogDesc;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reportCatalog")
	public List<ReportCustom> getReportCustoms() {
		return this.reportCustoms;
	}

	public void setReportCustoms(List<ReportCustom> reportCustoms) {
		this.reportCustoms = reportCustoms;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reportCatalog")
	@OrderBy("reportFieldNum ASC")
	@Cascade({ CascadeType.SAVE_UPDATE })
	public List<ReportField> getReportFields() {
		return this.reportFields;
	}

	public void setReportFields(List<ReportField> reportFields) {
		this.reportFields = reportFields;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reportCatalog")
	public List<ReportExecution> getReportExecutions() {
		return this.reportExecutions;
	}

	public void setReportExecutions(List<ReportExecution> reportExecutions) {
		this.reportExecutions = reportExecutions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reportCatalog")
	public List<ReportSemantic> getReportSemantics() {
		return this.reportSemantics;
	}

	public void setReportSemantics(List<ReportSemantic> reportSemantics) {
		this.reportSemantics = reportSemantics;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public VersionAuditor getVersionAuditor() {
		return versionAuditor;
	}

	public void setVersionAuditor(VersionAuditor _auditor) {
		this.versionAuditor = _auditor;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof ReportCatalog) {
			return ((ReportCatalog) object).getReportCatalogName().equals(
					this.reportCatalogName)
					&& ((ReportCatalog) object).getReportLevel().equals(
							this.reportLevel)
					&& ((ReportCatalog) object).getReportVersion().equals(
							this.reportVersion);

		}
		return false;
	}
}
