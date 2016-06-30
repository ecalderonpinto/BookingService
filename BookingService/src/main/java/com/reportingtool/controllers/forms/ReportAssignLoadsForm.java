package com.reportingtool.controllers.forms;

import java.util.List;

import com.entities.entity.reportingtool.ReportExecution;


public class ReportAssignLoadsForm {

	private ReportExecution reportExecution;
	private List<String> selectLoads;
	
	public ReportExecution getReportExecution() {
		return reportExecution;
	}
	public void setReportExecution(ReportExecution reportExecution) {
		this.reportExecution = reportExecution;
	}
	public List<String> getSelectLoads() {
		return selectLoads;
	}
	public void setSelectLoads(List<String> selectLoads) {
		this.selectLoads = selectLoads;
	}

}
