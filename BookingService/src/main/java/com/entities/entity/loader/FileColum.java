package com.entities.entity.loader;

// Generated 11-feb-2015 16:49:54 by Hibernate Tools 4.0.0

import java.math.BigDecimal;
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

import com.entities.entity.reportingtool.ReportField;
import com.entities.utilities.hibernate.VersionAuditor;
import com.entities.utilities.hibernate.VersionableAdapter;

/**
 * FileColum generated by hbm2java
 */
@Entity
@Table(name = "T_FILE_COLUM")
public class FileColum implements VersionableAdapter {

	private static final long serialVersionUID = 7707065088836907715L;
	
	private long id;
	private ReportField reportField;
	private FileConfig fileConfig;
	private String columType;
	private BigDecimal columNumber;
	private String columName;
	private String columDesc;
	private String columFormat;
	private String columBlock;
	private List<LoadRawData> loadRawDatas = new ArrayList<LoadRawData>();
	private List<FileColumList> fileColumLists = new ArrayList<FileColumList>();

	@Embedded
	private VersionAuditor versionAuditor;
	@Version
	@Column(name = "VERSION", nullable = false)
	int version;

	public FileColum() {
	}

	public FileColum(long fileColumId, FileConfig fileConfig, String columType,
			VersionAuditor versionAuditor) {
		this.id = fileColumId;
		this.fileConfig = fileConfig;
		this.columType = columType;
		this.versionAuditor = versionAuditor;
	}

	public FileColum(ReportField reportField, FileConfig fileConfig,
			String columType, BigDecimal columNumber, String columName,
			String columDesc, String columFormat,
			List<LoadRawData> loadRawDatas, List<FileColumList> fileColumLists,
			VersionAuditor versionAuditor) {
		this.reportField = reportField;
		this.fileConfig = fileConfig;
		this.columType = columType;
		this.columNumber = columNumber;
		this.columName = columName;
		this.columDesc = columDesc;
		this.columFormat = columFormat;
		this.loadRawDatas = loadRawDatas;
		this.fileColumLists = fileColumLists;
		this.versionAuditor = versionAuditor;
	}
	
	public FileColum(ReportField reportField, FileConfig fileConfig,
			String columType, BigDecimal columNumber, String columName,
			String columDesc, String columFormat,
			String columBlock,
			VersionAuditor versionAuditor) {
		this.reportField = reportField;
		this.fileConfig = fileConfig;
		this.columType = columType;
		this.columNumber = columNumber;
		this.columName = columName;
		this.columDesc = columDesc;
		this.columFormat = columFormat;
		this.columBlock = columBlock;
		this.versionAuditor = versionAuditor;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@SequenceGenerator(name = "GEN_FILE_COLUM", sequenceName = "SEQ_FILE_COLUM", initialValue = 1, allocationSize = 1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_FILE_COLUM")
	@Column(name = "FILE_COLUM_ID", unique = true, nullable = false, length = 10)
	public long getId() {
		return this.id;
	}

	public void setId(long fileColumId) {
		this.id = fileColumId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "REPORT_FIELD_ID", foreignKey = @ForeignKey(name = "T_FILE_COLUM_FK_REPORT_FIELD"))
	@JoinColumn(name = "REPORT_FIELD_ID")
	public ReportField getReportField() {
		return this.reportField;
	}

	public void setReportField(ReportField reportField) {
		this.reportField = reportField;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "FILE_CONFIG_ID", nullable = false, foreignKey = @ForeignKey(name = "T_FILE_COLUM_FK_FILE_CONFIG"))
	@JoinColumn(name = "FILE_CONFIG_ID", nullable = false)
	public FileConfig getFileConfig() {
		return this.fileConfig;
	}

	public void setFileConfig(FileConfig fileConfig) {
		this.fileConfig = fileConfig;
	}

	@Column(name = "COLUM_TYPE", nullable = false, length = 40)
	public String getColumType() {
		return this.columType;
	}

	public void setColumType(String columType) {
		this.columType = columType;
	}

	@Column(name = "COLUM_NUMBER", precision = 22, scale = 0)
	public BigDecimal getColumNumber() {
		return this.columNumber;
	}

	public void setColumNumber(BigDecimal columNumber) {
		this.columNumber = columNumber;
	}

	@Column(name = "COLUM_NAME")
	public String getColumName() {
		return this.columName;
	}

	public void setColumName(String columName) {
		this.columName = columName;
	}

	@Column(name = "COLUM_DESC")
	public String getColumDesc() {
		return this.columDesc;
	}

	public void setColumDesc(String columDesc) {
		this.columDesc = columDesc;
	}

	@Column(name = "COLUM_FORMAT")
	public String getColumFormat() {
		return this.columFormat;
	}

	public void setColumFormat(String columFormat) {
		this.columFormat = columFormat;
	}

	@Column(name = "COLUM_BLOCK", nullable = true)
	public String getColumBlock() {
		return this.columBlock;
	}

	public void setColumBlock(String columBlock) {
		this.columBlock = columBlock;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fileColum")
	public List<LoadRawData> getLoadRawDatas() {
		return this.loadRawDatas;
	}

	public void setLoadRawDatas(List<LoadRawData> loadRawDatas) {
		this.loadRawDatas = loadRawDatas;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "fileColum")
	public List<FileColumList> getFileColumLists() {
		return this.fileColumLists;
	}

	public void setFileColumLists(List<FileColumList> fileColumLists) {
		this.fileColumLists = fileColumLists;
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
		if (object instanceof FileColum) {
			// return ((FileColum) object).getColumName().equals(this.columName)
			// && ((FileColum) object).getFileConfig().equals(
			// this.fileConfig)
			// && ((FileColum) object).getColumType().equals(
			// this.columType)
			// && ((FileColum) object).getColumBlock().equals(
			// this.columBlock)
			// && ((FileColum) object).getColumNumber().equals(
			// this.columNumber);
			boolean ret = ((FileColum) object).getColumName().equals(
					this.columName)
					&& ((FileColum) object).getFileConfig().equals(
							this.fileConfig)
					&& ((FileColum) object).getColumType().equals(
							this.columType)
					&& ((FileColum) object).getColumNumber().equals(
							this.columNumber);

			if (this.columBlock == null
					&& ((FileColum) object).getColumBlock() == null) {
				// return ret
			} else {
				// one or two columBlock can be null
				if (this.columBlock == null
						&& ((FileColum) object).getColumBlock() != null) {
					ret = false;
					if (this.columBlock != null
							&& ((FileColum) object).getColumBlock() == null) {
						ret = false;
					} else {
						ret = ret
								&& ((FileColum) object).getColumBlock().equals(
										this.columBlock);
					}
				}
			}
		}
		return false;
	}
}
