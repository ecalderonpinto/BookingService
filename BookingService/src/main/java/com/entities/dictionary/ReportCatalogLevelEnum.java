package com.entities.dictionary;

/**
 * Enum with all Error.errorType values
 * 
 * @author Alberto
 *
 */
public enum ReportCatalogLevelEnum {

	
	COMPANY("COMPANY", "Report at Company level, for example AIFM."), 
	DEPARTMENT("DEPARTMENT", "Report at Department level, for example DMO."), 
	FUND("FUND","Report at Fund level, for example AIF."),
	;

	private String reportLevel = null;
	private String description = null;

	ReportCatalogLevelEnum(String reportLevel, String description) {
		this.reportLevel = reportLevel;
		this.description = description;
	}

	public String getReportLevel() {
		return reportLevel;
	}

	public void setReportLevel(String reportLevel) {
		this.reportLevel = reportLevel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
