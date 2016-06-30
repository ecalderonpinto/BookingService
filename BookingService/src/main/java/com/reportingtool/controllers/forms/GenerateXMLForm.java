package com.reportingtool.controllers.forms;

import com.entities.entity.reportingtool.ReportExecution;

public class GenerateXMLForm {

	private ReportExecution reportExecution;
	private String outputXML;
	private boolean hasErrors = false;
	private boolean valid = true;

	public ReportExecution getReportExecution() {
		return reportExecution;
	}

	public void setReportExecution(ReportExecution reportExecution) {
		this.reportExecution = reportExecution;
	}

	public String getOutputXML() {
		return outputXML;
	}

	public void setOutputXML(String outputXML) {
		this.outputXML = outputXML;
	}

	public boolean isHasErrors() {
		return hasErrors;
	}

	public void setHasErrors(boolean hasErrors) {
		this.hasErrors = hasErrors;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
}
