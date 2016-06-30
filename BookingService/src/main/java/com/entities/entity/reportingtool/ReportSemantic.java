package com.entities.entity.reportingtool;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.entities.utilities.hibernate.VersionAuditor;
import com.entities.utilities.hibernate.VersionableAdapter;

@Entity
@Table(name = "T_REPORT_SEMANTIC")
public class ReportSemantic implements VersionableAdapter {

	private static final long serialVersionUID = 8129563952741177904L;
	
	private long id;
	private ReportCatalog reportCatalog;
	private String reportingSemanticName;
	private String reportingSemanticRule;
	private String reportingSemanticDesc;
	private String reportingSemanticSugg;
	private ReportField reportField;

	@Embedded
	private VersionAuditor versionAuditor;
	@Version
	@Column(name = "VERSION", nullable = false)
	int version;

	public ReportSemantic() {
	}

	public ReportSemantic(long id, ReportCatalog reportCatalog,
			String reportingSemanticName, String reportingSemanticRule,
			VersionAuditor versionAuditor) {
		this.id = id;
		this.reportCatalog = reportCatalog;
		this.reportingSemanticName = reportingSemanticName;
		this.reportingSemanticRule = reportingSemanticRule;
		this.versionAuditor = versionAuditor;
	}

	public ReportSemantic(ReportCatalog reportCatalog, ReportField reportField,
			String reportingSemanticName, String reportingSemanticRule,
			String reportingSemanticDesc, String reportingSemanticSugg,
			VersionAuditor versionAuditor) {
		this.reportCatalog = reportCatalog;
		this.reportField = reportField;
		this.reportingSemanticName = reportingSemanticName;
		this.reportingSemanticRule = reportingSemanticRule;
		this.reportingSemanticDesc = reportingSemanticDesc;
		this.reportingSemanticSugg = reportingSemanticSugg;
		this.versionAuditor = versionAuditor;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@SequenceGenerator(name = "GEN_REPORT_SEMANTIC", sequenceName = "SEQ_REPORT_SEMANTIC", initialValue = 1, allocationSize = 1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_REPORT_SEMANTIC")
	@Column(name = "REPORT_SEMANTIC_ID", unique = true, nullable = false, length = 10)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "REPORT_CATALOG_ID", nullable = false, foreignKey = @ForeignKey(name = "T_REPORT_SEM_FK_CATALOG"))
	@JoinColumn(name = "REPORT_CATALOG_ID", nullable = false)
	public ReportCatalog getReportCatalog() {
		return reportCatalog;
	}

	public void setReportCatalog(ReportCatalog reportCatalog) {
		this.reportCatalog = reportCatalog;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "REPORT_FIELD_ID", foreignKey = @ForeignKey(name = "T_REPORT_SEM_FK_FIELD"))
	@JoinColumn(name = "REPORT_FIELD_ID")
	public ReportField getReportField() {
		return reportField;
	}

	public void setReportField(ReportField reportField) {
		this.reportField = reportField;
	}

	@Column(name = "REPORT_SEMANTIC_NAME", nullable = false, length=100)
	public String getReportingSemanticName() {
		return reportingSemanticName;
	}

	public void setReportingSemanticName(String reportingSemanticName) {
		this.reportingSemanticName = reportingSemanticName;
	}

	@Column(name = "REPORT_SEMANTIC_RULE", nullable = false, length=4000)
	public String getReportingSemanticRule() {
		return reportingSemanticRule;
	}

	public void setReportingSemanticRule(String reportingSemanticRule) {
		this.reportingSemanticRule = reportingSemanticRule;
	}

	@Column(name = "REPORT_SEMANTIC_DESC", length=256)
	public String getReportingSemanticDesc() {
		return reportingSemanticDesc;
	}

	public void setReportingSemanticDesc(String reportingSemanticDesc) {
		this.reportingSemanticDesc = reportingSemanticDesc;
	}

	@Column(name = "REPORT_SEMANTIC_SUGG", length=256)
	public String getReportingSemanticSugg() {
		return reportingSemanticSugg;
	}

	public void setReportingSemanticSugg(String reportingSemanticSugg) {
		this.reportingSemanticSugg = reportingSemanticSugg;
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
		if (object instanceof ReportSemantic) {
			return ((ReportSemantic) object).getReportCatalog().equals(
					this.reportCatalog)
					&& ((ReportSemantic) object).getReportingSemanticRule()
							.equals(this.reportingSemanticRule);

		}
		return false;
	}
}
