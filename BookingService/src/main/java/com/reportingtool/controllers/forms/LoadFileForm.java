package com.reportingtool.controllers.forms;

import org.springframework.web.multipart.MultipartFile;

public class LoadFileForm {

	private String selectFileConfigs;
	private MultipartFile inputFile;

	public String getSelectFileConfigs() {
		return selectFileConfigs;
	}

	public void setSelectFileConfigs(String selectFileConfigs) {
		this.selectFileConfigs = selectFileConfigs;
	}

	public MultipartFile getInputFile() {
		return inputFile;
	}

	public void setInputFile(MultipartFile inputFile) {
		this.inputFile = inputFile;
	}

}
