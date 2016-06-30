package com.reportingtool.controllers.forms;

import org.springframework.web.multipart.MultipartFile;

import com.entities.entity.reportingtool.ReportExecution;

public class LoadXMLFileForm {

	private MultipartFile inputFile;
	private ReportExecution reportExecution;
	private boolean update;

	public MultipartFile getInputFile() {
		return inputFile;
	}

	public void setInputFile(MultipartFile inputFile) {
		this.inputFile = inputFile;
	}
	
	public ReportExecution getReportExecution() {
		return reportExecution;
	}

	public void setReportExecution(ReportExecution reportExecution) {
		this.reportExecution = reportExecution;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

}
