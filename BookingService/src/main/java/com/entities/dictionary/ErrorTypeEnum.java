package com.entities.dictionary;

/**
 * Enum with all Error.errorType values
 * 
 * @author Alberto
 *
 */
public enum ErrorTypeEnum {

	
	LOADER("LOADER", "Error load"), 
	CONTROLLER("CONTROLLER", "Error controller"), 
	VALIDATOR("VALIDATOR","Error validator"),
	FORMAT("FORMAT","Error format"),
	GENERATION("GENERATION","Error generation"),
	SYNTAXIS("SYNTAXIS","Error syntaxis"),
	SEMANTIC("SEMANTIC","Error semantic"),
	TRANSLATE("TRANSLATE","Error translate"),
	STATUS("STATUS","Error status"),
	REPORTING("REPORTING","Error report"),
	;

	private String errorType = null;
	private String description = null;

	ErrorTypeEnum(String errorType, String description) {
		this.errorType = errorType;
		this.description = description;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
