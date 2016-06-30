package com.entities.entity.reportingtool;

// Generated 11-feb-2015 16:49:54 by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.entities.utilities.hibernate.VersionAuditor;
import com.entities.utilities.hibernate.VersionableAdapter;

/**
 * Fund generated by hbm2java
 */
@Entity
@Table(name = "T_FUND")
public class Fund implements VersionableAdapter {

	private static final long serialVersionUID = 844216768252149990L;
	
	private long id;
	private Company company;
	private String fundName;
	private String fundIsin;
	private String fundCode;
	private String fundDesc;
	private String fundClass;
	private List<FundGroup> fundGroups = new ArrayList<FundGroup>();
	private List<ReportExecution> reportExecutions = new ArrayList<ReportExecution>();

	@Embedded
	private VersionAuditor versionAuditor;
	@Version
	@Column(name = "VERSION", nullable = false)
	int version;

	public Fund() {
	}

	public Fund(long fundId, Company company, String fundName,
			VersionAuditor versionAuditor) {
		this.id = fundId;
		this.company = company;
		this.fundName = fundName;
		this.versionAuditor = versionAuditor;
	}

	public Fund(Company company, String fundName, String fundIsin,
			String fundCode, String fundDesc, String fundClass,
			VersionAuditor versionAuditor) {
		this.company = company;
		this.fundName = fundName;
		this.fundIsin = fundIsin;
		this.fundCode = fundCode;
		this.fundDesc = fundDesc;
		this.fundClass = fundClass;
		this.versionAuditor = versionAuditor;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@SequenceGenerator(name = "GEN_FUND", sequenceName = "SEQ_FUND", initialValue = 1, allocationSize = 1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_FUND")
	@Column(name = "FUND_ID", unique = true, nullable = false, length = 10)
	public long getId() {
		return this.id;
	}

	public void setId(long fundId) {
		this.id = fundId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "COMPANY_ID", nullable = false, foreignKey = @ForeignKey(name = "T_FUND_FK_COMPANY"))
	@JoinColumn(name = "COMPANY_ID", nullable = false)
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Column(name = "FUND_NAME", nullable = false)
	public String getFundName() {
		return this.fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	@Column(name = "FUND_ISIN", length = 40)
	public String getFundIsin() {
		return this.fundIsin;
	}

	public void setFundIsin(String fundIsin) {
		this.fundIsin = fundIsin;
	}

	@Column(name = "FUND_CODE", length = 40)
	public String getFundCode() {
		return this.fundCode;
	}

	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}

	@Column(name = "FUND_DESC")
	public String getFundDesc() {
		return this.fundDesc;
	}

	public void setFundDesc(String fundDesc) {
		this.fundDesc = fundDesc;
	}

	@Column(name = "FUND_CLASS")
	public String getFundClass() {
		return this.fundClass;
	}

	public void setFundClass(String fundClass) {
		this.fundClass = fundClass;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fund")
	public List<FundGroup> getFundGroups() {
		return this.fundGroups;
	}

	public void setFundGroups(List<FundGroup> fundGroups) {
		this.fundGroups = fundGroups;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fund")
	public List<ReportExecution> getReportExecutions() {
		return this.reportExecutions;
	}

	public void setReportExecutions(List<ReportExecution> reportExecutions) {
		this.reportExecutions = reportExecutions;
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
		if (object instanceof Fund) {
			return ((Fund) object).getFundName().equals(this.fundName)
					&& ((Fund) object).getFundCode().equals(this.fundCode)
					&& ((Fund) object).getFundIsin().equals(this.fundIsin)
					&& ((Fund) object).getCompany().equals(this.company);

		}
		return false;
	}
}
