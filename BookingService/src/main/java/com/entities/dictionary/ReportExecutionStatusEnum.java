package com.entities.dictionary;

/**
 * Enum with all ReportExecution.reportStatus values
 * 
 * @author Alberto
 *
 */
public enum ReportExecutionStatusEnum {

	EMPTY("EMPTY", "Report empty, only defaul datas created."), 
	CREATION("CREATION", "Report incomplete"), 
	READY("READY","Report ready to generate XML"),
	SENT("SENT","Report sent and not able to modify");

	private String reportExecutionStatus = null;
	private String description = null;

	ReportExecutionStatusEnum(String reportExecutionStatus, String description) {
		this.reportExecutionStatus = reportExecutionStatus;
		this.description = description;
	}

	public String getReportExecutionStatus() {
		return reportExecutionStatus;
	}

	public void setReportExecutionStatus(String code) {
		this.reportExecutionStatus = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
