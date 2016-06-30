package com.entities.entity.loader;

// Generated 11-feb-2015 16:49:54 by Hibernate Tools 4.0.0

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

/**
 * LoadRawData generated by hbm2java
 */
@Entity
@Table(name = "T_LOAD_RAW_DATA")
public class LoadRawData implements VersionableAdapter {

	private static final long serialVersionUID = -5530516821829715574L;
	
	private long id;
	private FileColum fileColum;
	private LoadRaw loadRaw;
	private String loadRawDataText;
	private String loadRawDataBlock;
	private String loadRawDataType;

	@Embedded
	private VersionAuditor versionAuditor;
	@Version
	@Column(name = "VERSION", nullable = false)
	int version;

	public LoadRawData() {
	}

	public LoadRawData(long loadRawDataId, FileColum fileColum,
			LoadRaw loadRaw, String loadRawDataType,
			VersionAuditor versionAuditor) {
		this.id = loadRawDataId;
		this.fileColum = fileColum;
		this.loadRaw = loadRaw;
		this.loadRawDataType = loadRawDataType;
		this.versionAuditor = versionAuditor;
	}

	public LoadRawData(FileColum fileColum, LoadRaw loadRaw,
			String loadRawDataText, String loadRawDataType,
			VersionAuditor versionAuditor) {
		this.fileColum = fileColum;
		this.loadRaw = loadRaw;
		this.loadRawDataText = loadRawDataText;
		this.loadRawDataType = loadRawDataType;
		this.versionAuditor = versionAuditor;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@SequenceGenerator(name = "GEN_LOAD_RAW_DATA", sequenceName = "SEQ_LOAD_RAW_DATA", initialValue = 1, allocationSize = 1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_LOAD_RAW_DATA")
	@Column(name = "LOAD_RAW_DATA_ID", unique = true, nullable = false, length = 10)
	public long getId() {
		return this.id;
	}

	public void setId(long loadRawDataId) {
		this.id = loadRawDataId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "FILE_COLUM_ID", nullable = false, foreignKey = @ForeignKey(name = "T_LOAD_RAW_DATA_FK_FILE_COL"))
	@JoinColumn(name = "FILE_COLUM_ID", nullable = false)
	public FileColum getFileColum() {
		return this.fileColum;
	}

	public void setFileColum(FileColum fileColum) {
		this.fileColum = fileColum;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "LOAD_RAW_ID", nullable = false, foreignKey = @ForeignKey(name = "T_LOAD_RAW_DATA_FK_LOAD_RAW"))
	@JoinColumn(name = "LOAD_RAW_ID", nullable = false)
	public LoadRaw getLoadRaw() {
		return this.loadRaw;
	}

	public void setLoadRaw(LoadRaw loadRaw) {
		this.loadRaw = loadRaw;
	}

	@Column(name = "LOAD_RAW_DATA_TEXT", length = 400, nullable = false)
	public String getLoadRawDataText() {
		return this.loadRawDataText;
	}

	public void setLoadRawDataText(String loadRawDataText) {
		this.loadRawDataText = loadRawDataText;
	}

	@Column(name = "LOAD_RAW_DATA_TYPE", nullable = false, length = 40)
	public String getLoadRawDataType() {
		return this.loadRawDataType;
	}

	public void setLoadRawDataType(String loadRawDataType) {
		this.loadRawDataType = loadRawDataType;
	}

	@Column(name = "LOAD_RAW_DATA_BLOCK", nullable = true, length = 40)
	public String getLoadRawDataBlock() {
		return this.loadRawDataBlock;
	}

	public void setLoadRawDataBlock(String loadRawDataBlock) {
		this.loadRawDataBlock = loadRawDataBlock;
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
		if (object instanceof LoadRawData) {
			// return ((LoadRawData)
			// object).getFileColum().equals(this.fileColum)
			// && ((LoadRawData) object).getLoadRawDataType().equals(
			// this.loadRawDataType)
			// && ((LoadRawData) object).getLoadRawDataText().equals(
			// this.loadRawDataText)
			// && ((LoadRawData) object).getLoadRawDataBlock().equals(
			// this.loadRawDataBlock)
			// && ((LoadRawData) object).getLoadRaw().equals(this.loadRaw);
			boolean ret = ((LoadRawData) object).getFileColum().equals(
					this.fileColum)
					&& ((LoadRawData) object).getLoadRawDataType().equals(
							this.loadRawDataType)
					&& ((LoadRawData) object).getLoadRawDataText().equals(
							this.loadRawDataText)
					&& ((LoadRawData) object).getLoadRaw().equals(this.loadRaw);
			if (this.loadRawDataBlock == null
					&& ((LoadRawData) object).getLoadRawDataBlock() == null) {
				// return ret
			} else {
				// one or two loadRawDataBlock can be null
				if (this.loadRawDataBlock == null
						&& ((LoadRawData) object).getLoadRawDataBlock() != null) {
					ret = false;
					if (this.loadRawDataBlock != null
							&& ((LoadRawData) object).getLoadRawDataBlock() == null) {
						ret = false;
					} else {
						ret = ret
								&& ((LoadRawData) object).getLoadRawDataBlock()
										.equals(this.loadRawDataBlock);
					}
				}
			}
		}
		return false;
	}
}